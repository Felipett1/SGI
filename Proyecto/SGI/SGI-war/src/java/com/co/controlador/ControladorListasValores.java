package com.co.controlador;

import com.co.sgi.Entidad.Ruta;
import com.co.sgi.persistencia.interfaz.IPGenerico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Felipe Triviño
 */
@Named(value = "controladorListasValores")
@ApplicationScoped
public class ControladorListasValores {

    @EJB
    private IPGenerico pGenerico;
    private Ruta ruta;
    private static final String TABLA = "Ruta";
    private Properties prop = new Properties();
    //Listas de valores
    private List<String> cargos, ciudades, discoDuro,
            memoria, procesador, tarjetaVideo, servicio;

    /**
     * Creates a new instance of ControladorListasValores
     */
    public ControladorListasValores() {
        //System.out.println(this.getClass().getSimpleName());
    }

    @PostConstruct
    public void init() {
        for (Ruta r : (List<Ruta>) (Object) pGenerico.consultar(TABLA)) {
            if (r.getDescripcion().equalsIgnoreCase("PATH_GENERAL")) {
                ruta = r;
            }
        }
        iniciarListas();
    }

    public void iniciarListas() {
        InputStream input = null;
        try {
            input = new FileInputStream(new File(ruta.getRuta()));
            prop.load(input);

            cargos = listaValor(prop.getProperty("cargos"), null);
            ciudades = listaValor(prop.getProperty("ciudades"), null);
            discoDuro = listaValor(prop.getProperty("discoDuro"), null);
            memoria = listaValor(prop.getProperty("memoria"), null);
            procesador = listaValor(prop.getProperty("procesador"), null);
            tarjetaVideo = listaValor(prop.getProperty("tarjetaVideo"), null);
            servicio = listaValor(prop.getProperty("servicio"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<String> listaValor(String ruta, String filtro) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        List<String> lista = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(ruta));
            while ((line = br.readLine()) != null) {
                String[] valores = line.split(cvsSplitBy);
                if (filtro == null) {
                    lista.add(valores[0]);
                } else {
                    if (valores[0].equalsIgnoreCase(filtro)) {
                        lista.add(valores[1]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lista;
    }

    public List<String> getCargos() {
        return cargos;
    }

    public List<String> getCiudades() {
        return ciudades;
    }

    public List<String> getDiscoDuro() {
        return discoDuro;
    }

    public List<String> getMemoria() {
        return memoria;
    }

    public List<String> getProcesador() {
        return procesador;
    }

    public List<String> getTarjetaVideo() {
        return tarjetaVideo;
    }

    public Properties getProp() {
        return prop;
    }

    public List<String> getServicio() {
        return servicio;
    }

}
