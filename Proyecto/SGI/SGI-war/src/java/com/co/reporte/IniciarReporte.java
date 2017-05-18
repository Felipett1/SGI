package com.co.reporte;

import com.co.sgi.Entidad.Ruta;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@ManagedBean
@SessionScoped
public class IniciarReporte implements Serializable {

    @EJB
    private IPGenerico pGenerico;
    private Ruta ruta;
    private String rutaGeneral;
    private static final String TABLA = "Ruta";
    private Connection conexion = null;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/SGI";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "felipe21370";

    public IniciarReporte() {
    }

    @PostConstruct
    public void inicar() {
        for (Ruta r : (List<Ruta>) (Object) pGenerico.consultar(TABLA)) {
            if (r.getDescripcion().equalsIgnoreCase("PATH_REPORTE")) {
                ruta = r;
            }
        }
        String r = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        rutaGeneral = r.substring(0, r.indexOf("WEB-INF")) + "recursos/verReporte/";
        iniciarConexion();
    }

    public void iniciarConexion() {
        try {
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error IniciarReporte.iniciarConexion: " + e);
        }
    }

    public String ejecutarReporte(String nombreReporte, String nombreArchivo, Map parametros) {
        try {
            parametros.put("rutaImagenes", ruta.getRuta());
            File archivo = new File(ruta.getRuta() + nombreReporte + ".jasper");
            JasperReport masterReport;
            masterReport = (JasperReport) JRLoader.loadObject(archivo);
            JasperPrint imprimir = JasperFillManager.fillReport(masterReport, parametros, conexion);
            String outFileName = rutaGeneral + nombreArchivo;
            Exporter exporter = new JRPdfExporter();

            List<JasperPrint> jpl = new ArrayList<>();
            jpl.add(imprimir);
            exporter.setExporterInput(SimpleExporterInput.getInstance(jpl));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outFileName));
            exporter.exportReport();

            return outFileName;
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println("Error: IniciarReporte.ejecutarReporte: " + e);
            System.out.println("************************************");
            if (e.getCause() != null) {
                return "Error: INICIARREPORTE " + e.toString() + "\n" + e.getCause().toString();
            } else {
                return "Error: INICIARREPORTE " + e.toString();
            }
        }
    }

    @PreDestroy
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error cerrar: " + e);
            System.out.println("Error causa: " + e.getCause());
        }
    }
}
