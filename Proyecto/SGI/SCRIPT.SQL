--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-03-31 17:42:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2366 (class 1262 OID 16393)
-- Dependencies: 2365
-- Name: SGI; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "SGI" IS 'Base de datos para el Sistema de gestion de inventarios (SGI).';


--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2368 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 208 (class 1259 OID 16568)
-- Name: art-per; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-per" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serie character(50),
    articulo bigint
);


ALTER TABLE "art-per" OWNER TO postgres;

--
-- TOC entry 2369 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE "art-per"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-per" IS 'Descripcion articulos de perforacion.';


--
-- TOC entry 207 (class 1259 OID 16566)
-- Name: ART-PER_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "ART-PER_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "ART-PER_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2370 (class 0 OID 0)
-- Dependencies: 207
-- Name: ART-PER_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ART-PER_secuencia_seq" OWNED BY "art-per".secuencia;


--
-- TOC entry 206 (class 1259 OID 16555)
-- Name: art-top; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-top" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serie character(50),
    articulo bigint
);


ALTER TABLE "art-top" OWNER TO postgres;

--
-- TOC entry 2371 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE "art-top"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-top" IS 'Descripcion de los articulos de topografia.';


--
-- TOC entry 205 (class 1259 OID 16553)
-- Name: ART-TOP_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "ART-TOP_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "ART-TOP_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2372 (class 0 OID 0)
-- Dependencies: 205
-- Name: ART-TOP_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ART-TOP_secuencia_seq" OWNED BY "art-top".secuencia;


--
-- TOC entry 210 (class 1259 OID 16581)
-- Name: art-vehi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-vehi" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    color character(50),
    chasis character(80),
    motor character(80),
    "fechaExpSOAT" date,
    "fechaVencimientoTM" date,
    articulo bigint,
    placa character(10),
    linea character(50),
    cilindraje integer,
    servicio character(20),
    clase character(20),
    carroceria character(30),
    combustible character(20),
    capacidad character(30),
    reg character(30),
    vin character(30),
    serie character(40),
    propietario character(80),
    "fechaMatricula" date,
    "fechaLicenciaTransito" date,
    "tafiaSOAT" double precision,
    "primaSOAT" double precision,
    "contribucionFOSYGA" double precision,
    "tasaRUNT" double precision,
    "totalSOAT" double precision,
    at character(1),
    "fechaTecnicomecanica" date,
    "numeroTarjetaOper" character(1),
    "fechaExpTO" date,
    "fechaVencimientoTO" date,
    "numeroPolizaCA" character(1),
    "fechaExpPCA" date,
    "fechaVencimientoPCA" date,
    "numeroPolizaECA" character(1),
    "fechaExpECA" date,
    "fechaVencimientoECA" date
);


ALTER TABLE "art-vehi" OWNER TO postgres;

--
-- TOC entry 2373 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE "art-vehi"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-vehi" IS 'Descripcion de los articulos en la categoria vehiculos.';


--
-- TOC entry 209 (class 1259 OID 16579)
-- Name: ART-VEHI_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "ART-VEHI_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "ART-VEHI_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2374 (class 0 OID 0)
-- Dependencies: 209
-- Name: ART-VEHI_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "ART-VEHI_secuencia_seq" OWNED BY "art-vehi".secuencia;


--
-- TOC entry 196 (class 1259 OID 16491)
-- Name: articulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE articulo (
    secuencia bigint NOT NULL,
    codigo character(100),
    nombre character(100),
    costo double precision,
    factura bigint,
    prestado boolean,
    estado boolean
);


ALTER TABLE articulo OWNER TO postgres;

--
-- TOC entry 2375 (class 0 OID 0)
-- Dependencies: 196
-- Name: TABLE articulo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE articulo IS 'Elementos que existen en el inventario.';


--
-- TOC entry 195 (class 1259 OID 16489)
-- Name: Articulos_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Articulos_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Articulos_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2376 (class 0 OID 0)
-- Dependencies: 195
-- Name: Articulos_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Articulos_secuencia_seq" OWNED BY articulo.secuencia;


--
-- TOC entry 192 (class 1259 OID 16465)
-- Name: cargo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cargo (
    secuencia bigint NOT NULL,
    nombre character(100)
);


ALTER TABLE cargo OWNER TO postgres;

--
-- TOC entry 2377 (class 0 OID 0)
-- Dependencies: 192
-- Name: TABLE cargo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE cargo IS 'Cargo actual que puede tener un responsable.';


--
-- TOC entry 191 (class 1259 OID 16463)
-- Name: Cargo_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Cargo_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Cargo_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2378 (class 0 OID 0)
-- Dependencies: 191
-- Name: Cargo_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Cargo_secuencia_seq" OWNED BY cargo.secuencia;


--
-- TOC entry 194 (class 1259 OID 16473)
-- Name: ciudad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ciudad (
    secuencia bigint NOT NULL,
    nombre character(100)
);


ALTER TABLE ciudad OWNER TO postgres;

--
-- TOC entry 2379 (class 0 OID 0)
-- Dependencies: 194
-- Name: TABLE ciudad; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE ciudad IS 'Ciudades capitales de Colombia.';


--
-- TOC entry 193 (class 1259 OID 16471)
-- Name: Ciudad_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Ciudad_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Ciudad_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2380 (class 0 OID 0)
-- Dependencies: 193
-- Name: Ciudad_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Ciudad_secuencia_seq" OWNED BY ciudad.secuencia;


--
-- TOC entry 212 (class 1259 OID 16607)
-- Name: clasificacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE clasificacion (
    secuencia bigint NOT NULL,
    nombre character(50),
    categoria bigint
);


ALTER TABLE clasificacion OWNER TO postgres;

--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 212
-- Name: TABLE clasificacion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE clasificacion IS 'Clasificacion de los articulos.';


--
-- TOC entry 211 (class 1259 OID 16605)
-- Name: Clasificacion_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Clasificacion_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Clasificacion_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 211
-- Name: Clasificacion_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Clasificacion_secuencia_seq" OWNED BY clasificacion.secuencia;


--
-- TOC entry 198 (class 1259 OID 16499)
-- Name: art-equi-com; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-equi-com" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serie character(50),
    "discoDuro" character(50),
    memoria character(50),
    procesador character(50),
    "kitErgonomico" boolean,
    proveedor character(100),
    observacion character(1000),
    articulo bigint
);


ALTER TABLE "art-equi-com" OWNER TO postgres;

--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 198
-- Name: TABLE "art-equi-com"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-equi-com" IS 'Detalle de los equipos de computo.';


--
-- TOC entry 197 (class 1259 OID 16497)
-- Name: EQUI-COM_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "EQUI-COM_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "EQUI-COM_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 197
-- Name: EQUI-COM_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "EQUI-COM_secuencia_seq" OWNED BY "art-equi-com".secuencia;


--
-- TOC entry 218 (class 1259 OID 16763)
-- Name: Empresa_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Empresa_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Empresa_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16412)
-- Name: entidadContratante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "entidadContratante" (
    secuencia bigint NOT NULL,
    nit bigint NOT NULL,
    nombre character(255) NOT NULL
);


ALTER TABLE "entidadContratante" OWNER TO postgres;

--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 186
-- Name: TABLE "entidadContratante"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "entidadContratante" IS 'Contiene las entidades contratantes relacionadas con los proyectos.';


--
-- TOC entry 185 (class 1259 OID 16410)
-- Name: EntidadContratante_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "EntidadContratante_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "EntidadContratante_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 185
-- Name: EntidadContratante_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "EntidadContratante_secuencia_seq" OWNED BY "entidadContratante".secuencia;


--
-- TOC entry 204 (class 1259 OID 16542)
-- Name: art-lab; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-lab" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serie character(50),
    articulo bigint
);


ALTER TABLE "art-lab" OWNER TO postgres;

--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE "art-lab"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-lab" IS 'Dscripcion de los articulos de laboratorio.';


--
-- TOC entry 203 (class 1259 OID 16540)
-- Name: LAB_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "LAB_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "LAB_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 203
-- Name: LAB_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "LAB_secuencia_seq" OWNED BY "art-lab".secuencia;


--
-- TOC entry 200 (class 1259 OID 16516)
-- Name: art-mbs-mje; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-mbs-mje" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serial character(50),
    articulo bigint,
    color character(50)
);


ALTER TABLE "art-mbs-mje" OWNER TO postgres;

--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 200
-- Name: TABLE "art-mbs-mje"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-mbs-mje" IS 'Descripcion articulos de menaje.';


--
-- TOC entry 199 (class 1259 OID 16514)
-- Name: MBS-MJE_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "MBS-MJE_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "MBS-MJE_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 199
-- Name: MBS-MJE_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "MBS-MJE_secuencia_seq" OWNED BY "art-mbs-mje".secuencia;


--
-- TOC entry 202 (class 1259 OID 16529)
-- Name: art-mbs-ofc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "art-mbs-ofc" (
    secuencia bigint NOT NULL,
    clasificacion bigint,
    marca bigint,
    modelo character(50),
    serial character(50),
    articulo bigint
);


ALTER TABLE "art-mbs-ofc" OWNER TO postgres;

--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 202
-- Name: TABLE "art-mbs-ofc"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE "art-mbs-ofc" IS 'Descripcion de los articulos de oficina.';


--
-- TOC entry 201 (class 1259 OID 16527)
-- Name: MBS-OFC_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "MBS-OFC_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "MBS-OFC_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 201
-- Name: MBS-OFC_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "MBS-OFC_secuencia_seq" OWNED BY "art-mbs-ofc".secuencia;


--
-- TOC entry 215 (class 1259 OID 16625)
-- Name: marca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE marca (
    secuencia bigint NOT NULL,
    nombre character(50),
    categoria bigint
);


ALTER TABLE marca OWNER TO postgres;

--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE marca; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE marca IS 'Marca de los articulos.';


--
-- TOC entry 214 (class 1259 OID 16623)
-- Name: Marca_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Marca_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Marca_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 214
-- Name: Marca_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Marca_secuencia_seq" OWNED BY marca.secuencia;


--
-- TOC entry 217 (class 1259 OID 16727)
-- Name: prestamo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prestamo (
    secuencia bigint NOT NULL,
    articulo bigint,
    responsable bigint,
    observaciones character(500),
    fecha date,
    estado boolean
);


ALTER TABLE prestamo OWNER TO postgres;

--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE prestamo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE prestamo IS 'Relacion entre los articulos y los responsables.';


--
-- TOC entry 216 (class 1259 OID 16725)
-- Name: Prestamo_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Prestamo_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Prestamo_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 216
-- Name: Prestamo_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Prestamo_secuencia_seq" OWNED BY prestamo.secuencia;


--
-- TOC entry 188 (class 1259 OID 16425)
-- Name: proyecto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proyecto (
    secuencia bigint NOT NULL,
    codigo character(50),
    nombre character(255),
    empresa bigint,
    "entidadContratante" bigint,
    "objetoContrato" character(1000),
    "fechaInicial" date,
    "fechaFinal" date,
    estado smallint
);


ALTER TABLE proyecto OWNER TO postgres;

--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 188
-- Name: TABLE proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE proyecto IS 'Contiene la informacion de cada uno de los proyectos en las empresas.';


--
-- TOC entry 187 (class 1259 OID 16423)
-- Name: Proyecto_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Proyecto_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Proyecto_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 187
-- Name: Proyecto_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Proyecto_secuencia_seq" OWNED BY proyecto.secuencia;


--
-- TOC entry 190 (class 1259 OID 16450)
-- Name: responsable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE responsable (
    secuencia bigint NOT NULL,
    identificacion bigint,
    "nombreCompleto" character(100),
    cargo bigint,
    email character(100),
    telefono bigint,
    ciudad bigint,
    proyecto bigint,
    estado boolean
);


ALTER TABLE responsable OWNER TO postgres;

--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 190
-- Name: TABLE responsable; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE responsable IS 'Responsable de la custodia de los articulos asignados.';


--
-- TOC entry 189 (class 1259 OID 16448)
-- Name: Responsable_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Responsable_secuencia_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Responsable_secuencia_seq" OWNER TO postgres;

--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 189
-- Name: Responsable_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Responsable_secuencia_seq" OWNED BY responsable.secuencia;


--
-- TOC entry 220 (class 1259 OID 16787)
-- Name: auditoria_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auditoria_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auditoria_secuencia_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16799)
-- Name: auditoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auditoria (
    secuencia bigint DEFAULT nextval('auditoria_secuencia_seq'::regclass) NOT NULL,
    fecha timestamp without time zone,
    usuario character(50),
    accion character(50),
    tabla character(50),
    detalle character(1000)
);


ALTER TABLE auditoria OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16613)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categoria (
    secuencia bigint NOT NULL,
    nombre character(50)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE categoria; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE categoria IS 'Categorias de los articulos.';


--
-- TOC entry 219 (class 1259 OID 16765)
-- Name: empresa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE empresa (
    secuencia bigint DEFAULT nextval('"Empresa_secuencia_seq"'::regclass) NOT NULL,
    "centroCosto" bigint NOT NULL,
    nombre character(255) NOT NULL
);


ALTER TABLE empresa OWNER TO postgres;

--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE empresa; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE empresa IS 'Contienes las empresas asociadas a los proyectos.';


--
-- TOC entry 2118 (class 2604 OID 16502)
-- Name: art-equi-com secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-equi-com" ALTER COLUMN secuencia SET DEFAULT nextval('"EQUI-COM_secuencia_seq"'::regclass);


--
-- TOC entry 2121 (class 2604 OID 16545)
-- Name: art-lab secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-lab" ALTER COLUMN secuencia SET DEFAULT nextval('"LAB_secuencia_seq"'::regclass);


--
-- TOC entry 2119 (class 2604 OID 16519)
-- Name: art-mbs-mje secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-mje" ALTER COLUMN secuencia SET DEFAULT nextval('"MBS-MJE_secuencia_seq"'::regclass);


--
-- TOC entry 2120 (class 2604 OID 16532)
-- Name: art-mbs-ofc secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-ofc" ALTER COLUMN secuencia SET DEFAULT nextval('"MBS-OFC_secuencia_seq"'::regclass);


--
-- TOC entry 2123 (class 2604 OID 16571)
-- Name: art-per secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-per" ALTER COLUMN secuencia SET DEFAULT nextval('"ART-PER_secuencia_seq"'::regclass);


--
-- TOC entry 2122 (class 2604 OID 16558)
-- Name: art-top secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-top" ALTER COLUMN secuencia SET DEFAULT nextval('"ART-TOP_secuencia_seq"'::regclass);


--
-- TOC entry 2124 (class 2604 OID 16584)
-- Name: art-vehi secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-vehi" ALTER COLUMN secuencia SET DEFAULT nextval('"ART-VEHI_secuencia_seq"'::regclass);


--
-- TOC entry 2117 (class 2604 OID 16494)
-- Name: articulo secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulo ALTER COLUMN secuencia SET DEFAULT nextval('"Articulos_secuencia_seq"'::regclass);


--
-- TOC entry 2115 (class 2604 OID 16468)
-- Name: cargo secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cargo ALTER COLUMN secuencia SET DEFAULT nextval('"Cargo_secuencia_seq"'::regclass);


--
-- TOC entry 2116 (class 2604 OID 16476)
-- Name: ciudad secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ciudad ALTER COLUMN secuencia SET DEFAULT nextval('"Ciudad_secuencia_seq"'::regclass);


--
-- TOC entry 2125 (class 2604 OID 16610)
-- Name: clasificacion secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clasificacion ALTER COLUMN secuencia SET DEFAULT nextval('"Clasificacion_secuencia_seq"'::regclass);


--
-- TOC entry 2112 (class 2604 OID 16415)
-- Name: entidadContratante secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "entidadContratante" ALTER COLUMN secuencia SET DEFAULT nextval('"EntidadContratante_secuencia_seq"'::regclass);


--
-- TOC entry 2126 (class 2604 OID 16628)
-- Name: marca secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marca ALTER COLUMN secuencia SET DEFAULT nextval('"Marca_secuencia_seq"'::regclass);


--
-- TOC entry 2127 (class 2604 OID 16730)
-- Name: prestamo secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prestamo ALTER COLUMN secuencia SET DEFAULT nextval('"Prestamo_secuencia_seq"'::regclass);


--
-- TOC entry 2113 (class 2604 OID 16428)
-- Name: proyecto secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyecto ALTER COLUMN secuencia SET DEFAULT nextval('"Proyecto_secuencia_seq"'::regclass);


--
-- TOC entry 2114 (class 2604 OID 16453)
-- Name: responsable secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable ALTER COLUMN secuencia SET DEFAULT nextval('"Responsable_secuencia_seq"'::regclass);


--
-- TOC entry 2403 (class 0 OID 0)
-- Dependencies: 207
-- Name: ART-PER_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ART-PER_secuencia_seq"', 1, false);


--
-- TOC entry 2404 (class 0 OID 0)
-- Dependencies: 205
-- Name: ART-TOP_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ART-TOP_secuencia_seq"', 1, false);


--
-- TOC entry 2405 (class 0 OID 0)
-- Dependencies: 209
-- Name: ART-VEHI_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"ART-VEHI_secuencia_seq"', 1, false);


--
-- TOC entry 2406 (class 0 OID 0)
-- Dependencies: 195
-- Name: Articulos_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Articulos_secuencia_seq"', 1, false);


--
-- TOC entry 2407 (class 0 OID 0)
-- Dependencies: 191
-- Name: Cargo_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Cargo_secuencia_seq"', 1, false);


--
-- TOC entry 2408 (class 0 OID 0)
-- Dependencies: 193
-- Name: Ciudad_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Ciudad_secuencia_seq"', 1, false);


--
-- TOC entry 2409 (class 0 OID 0)
-- Dependencies: 211
-- Name: Clasificacion_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Clasificacion_secuencia_seq"', 1, false);


--
-- TOC entry 2410 (class 0 OID 0)
-- Dependencies: 197
-- Name: EQUI-COM_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"EQUI-COM_secuencia_seq"', 1, false);


--
-- TOC entry 2411 (class 0 OID 0)
-- Dependencies: 218
-- Name: Empresa_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Empresa_secuencia_seq"', 5, true);


--
-- TOC entry 2412 (class 0 OID 0)
-- Dependencies: 185
-- Name: EntidadContratante_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"EntidadContratante_secuencia_seq"', 1, false);


--
-- TOC entry 2413 (class 0 OID 0)
-- Dependencies: 203
-- Name: LAB_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"LAB_secuencia_seq"', 1, false);


--
-- TOC entry 2414 (class 0 OID 0)
-- Dependencies: 199
-- Name: MBS-MJE_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"MBS-MJE_secuencia_seq"', 1, false);


--
-- TOC entry 2415 (class 0 OID 0)
-- Dependencies: 201
-- Name: MBS-OFC_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"MBS-OFC_secuencia_seq"', 1, false);


--
-- TOC entry 2416 (class 0 OID 0)
-- Dependencies: 214
-- Name: Marca_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Marca_secuencia_seq"', 1, false);


--
-- TOC entry 2417 (class 0 OID 0)
-- Dependencies: 216
-- Name: Prestamo_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Prestamo_secuencia_seq"', 1, false);


--
-- TOC entry 2418 (class 0 OID 0)
-- Dependencies: 187
-- Name: Proyecto_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Proyecto_secuencia_seq"', 1, false);


--
-- TOC entry 2419 (class 0 OID 0)
-- Dependencies: 189
-- Name: Responsable_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"Responsable_secuencia_seq"', 1, false);


--
-- TOC entry 2337 (class 0 OID 16499)
-- Dependencies: 198
-- Data for Name: art-equi-com; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-equi-com" (secuencia, clasificacion, marca, modelo, serie, "discoDuro", memoria, procesador, "kitErgonomico", proveedor, observacion, articulo) FROM stdin;
\.


--
-- TOC entry 2343 (class 0 OID 16542)
-- Dependencies: 204
-- Data for Name: art-lab; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-lab" (secuencia, clasificacion, marca, modelo, serie, articulo) FROM stdin;
\.


--
-- TOC entry 2339 (class 0 OID 16516)
-- Dependencies: 200
-- Data for Name: art-mbs-mje; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-mbs-mje" (secuencia, clasificacion, marca, modelo, serial, articulo, color) FROM stdin;
\.


--
-- TOC entry 2341 (class 0 OID 16529)
-- Dependencies: 202
-- Data for Name: art-mbs-ofc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-mbs-ofc" (secuencia, clasificacion, marca, modelo, serial, articulo) FROM stdin;
\.


--
-- TOC entry 2347 (class 0 OID 16568)
-- Dependencies: 208
-- Data for Name: art-per; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-per" (secuencia, clasificacion, marca, modelo, serie, articulo) FROM stdin;
\.


--
-- TOC entry 2345 (class 0 OID 16555)
-- Dependencies: 206
-- Data for Name: art-top; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-top" (secuencia, clasificacion, marca, modelo, serie, articulo) FROM stdin;
\.


--
-- TOC entry 2349 (class 0 OID 16581)
-- Dependencies: 210
-- Data for Name: art-vehi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "art-vehi" (secuencia, clasificacion, marca, modelo, color, chasis, motor, "fechaExpSOAT", "fechaVencimientoTM", articulo, placa, linea, cilindraje, servicio, clase, carroceria, combustible, capacidad, reg, vin, serie, propietario, "fechaMatricula", "fechaLicenciaTransito", "tafiaSOAT", "primaSOAT", "contribucionFOSYGA", "tasaRUNT", "totalSOAT", at, "fechaTecnicomecanica", "numeroTarjetaOper", "fechaExpTO", "fechaVencimientoTO", "numeroPolizaCA", "fechaExpPCA", "fechaVencimientoPCA", "numeroPolizaECA", "fechaExpECA", "fechaVencimientoECA") FROM stdin;
\.


--
-- TOC entry 2335 (class 0 OID 16491)
-- Dependencies: 196
-- Data for Name: articulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY articulo (secuencia, codigo, nombre, costo, factura, prestado, estado) FROM stdin;
\.


--
-- TOC entry 2360 (class 0 OID 16799)
-- Dependencies: 221
-- Data for Name: auditoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auditoria (secuencia, fecha, usuario, accion, tabla, detalle) FROM stdin;
1	2017-03-31 17:21:37.894	\N	INSERT                                            	Empresa                                           	serialVersionUID: 1 - centroCosto: 102 - nombre: PRAGMA S.A - _persistence_shouldRefreshFetchGroup: false -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
2	2017-03-31 17:21:47.122	\N	UPDATE                                            	Empresa                                           	serialVersionUID: 1 - secuencia: 3 - centroCosto: 101 - nombre: COBOY S.A.S - _persistence_primaryKey: 3 - _persistence_cacheKey: [3: 3: null: 1490998830795: com.co.sgi.Entidad.Empresa[ secuencia=3 ]] - _persistence_listener: AttributeChangeListener(null) - _persistence_shouldRefreshFetchGroup: false -  / serialVersionUID: 1 - secuencia: 3 - centroCosto: 110 - nombre: COBOY S.A.S - _persistence_primaryKey: 3 - _persistence_cacheKey: [3: 3: null: 1490998830795: com.co.sgi.Entidad.Empresa[ secuencia=3 ]] - _persistence_listener: AttributeChangeListener(ObjectChangeSet(3, com.co.sgi.Entidad.Empresa)[org.eclipse.persistence.internal.sessions.DirectToFieldChangeRecord(centroCosto)]) - _persistence_shouldRefreshFetchGroup: false -                                                                                                                                                                                                                                                                          
\.


--
-- TOC entry 2420 (class 0 OID 0)
-- Dependencies: 220
-- Name: auditoria_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auditoria_secuencia_seq', 2, true);


--
-- TOC entry 2331 (class 0 OID 16465)
-- Dependencies: 192
-- Data for Name: cargo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cargo (secuencia, nombre) FROM stdin;
\.


--
-- TOC entry 2352 (class 0 OID 16613)
-- Dependencies: 213
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categoria (secuencia, nombre) FROM stdin;
\.


--
-- TOC entry 2333 (class 0 OID 16473)
-- Dependencies: 194
-- Data for Name: ciudad; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ciudad (secuencia, nombre) FROM stdin;
\.


--
-- TOC entry 2351 (class 0 OID 16607)
-- Dependencies: 212
-- Data for Name: clasificacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY clasificacion (secuencia, nombre, categoria) FROM stdin;
\.


--
-- TOC entry 2358 (class 0 OID 16765)
-- Dependencies: 219
-- Data for Name: empresa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY empresa (secuencia, "centroCosto", nombre) FROM stdin;
4	102	ETB S.A                                                                                                                                                                                                                                                        
5	102	PRAGMA S.A                                                                                                                                                                                                                                                     
3	110	COBOY S.A.S                                                                                                                                                                                                                                                    
\.


--
-- TOC entry 2325 (class 0 OID 16412)
-- Dependencies: 186
-- Data for Name: entidadContratante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "entidadContratante" (secuencia, nit, nombre) FROM stdin;
\.


--
-- TOC entry 2354 (class 0 OID 16625)
-- Dependencies: 215
-- Data for Name: marca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY marca (secuencia, nombre, categoria) FROM stdin;
\.


--
-- TOC entry 2356 (class 0 OID 16727)
-- Dependencies: 217
-- Data for Name: prestamo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prestamo (secuencia, articulo, responsable, observaciones, fecha, estado) FROM stdin;
\.


--
-- TOC entry 2327 (class 0 OID 16425)
-- Dependencies: 188
-- Data for Name: proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY proyecto (secuencia, codigo, nombre, empresa, "entidadContratante", "objetoContrato", "fechaInicial", "fechaFinal", estado) FROM stdin;
\.


--
-- TOC entry 2329 (class 0 OID 16450)
-- Dependencies: 190
-- Data for Name: responsable; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY responsable (secuencia, identificacion, "nombreCompleto", cargo, email, telefono, ciudad, proyecto, estado) FROM stdin;
\.


--
-- TOC entry 2161 (class 2606 OID 16573)
-- Name: art-per ART-PER_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-per"
    ADD CONSTRAINT "ART-PER_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2159 (class 2606 OID 16560)
-- Name: art-top ART-TOP_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-top"
    ADD CONSTRAINT "ART-TOP_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2163 (class 2606 OID 16586)
-- Name: art-vehi ART-VEHI_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-vehi"
    ADD CONSTRAINT "ART-VEHI_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2149 (class 2606 OID 16496)
-- Name: articulo Articulos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulo
    ADD CONSTRAINT "Articulos_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2145 (class 2606 OID 16470)
-- Name: cargo Cargo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT "Cargo_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2167 (class 2606 OID 16617)
-- Name: categoria Categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT "Categoria_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2147 (class 2606 OID 16478)
-- Name: ciudad Ciudad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ciudad
    ADD CONSTRAINT "Ciudad_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2165 (class 2606 OID 16612)
-- Name: clasificacion Clasificacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clasificacion
    ADD CONSTRAINT "Clasificacion_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2151 (class 2606 OID 16507)
-- Name: art-equi-com EQUI-COM_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-equi-com"
    ADD CONSTRAINT "EQUI-COM_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2173 (class 2606 OID 16770)
-- Name: empresa Empresa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT "Empresa_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2131 (class 2606 OID 16417)
-- Name: entidadContratante EntidadContratante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "entidadContratante"
    ADD CONSTRAINT "EntidadContratante_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2157 (class 2606 OID 16547)
-- Name: art-lab LAB_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-lab"
    ADD CONSTRAINT "LAB_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2153 (class 2606 OID 16521)
-- Name: art-mbs-mje MBS-MJE_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-mje"
    ADD CONSTRAINT "MBS-MJE_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2155 (class 2606 OID 16534)
-- Name: art-mbs-ofc MBS-OFC_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-ofc"
    ADD CONSTRAINT "MBS-OFC_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2169 (class 2606 OID 16630)
-- Name: marca Marca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marca
    ADD CONSTRAINT "Marca_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2171 (class 2606 OID 16735)
-- Name: prestamo Prestamo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prestamo
    ADD CONSTRAINT "Prestamo_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2135 (class 2606 OID 16433)
-- Name: proyecto Proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyecto
    ADD CONSTRAINT "Proyecto_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2141 (class 2606 OID 16455)
-- Name: responsable Responsable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable
    ADD CONSTRAINT "Responsable_pkey" PRIMARY KEY (secuencia);


--
-- TOC entry 2175 (class 2606 OID 16772)
-- Name: empresa UnicaEmpresa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY empresa
    ADD CONSTRAINT "UnicaEmpresa" UNIQUE ("centroCosto", nombre);


--
-- TOC entry 2133 (class 2606 OID 16419)
-- Name: entidadContratante UnicaEntidadConntratante; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "entidadContratante"
    ADD CONSTRAINT "UnicaEntidadConntratante" UNIQUE (nit, nombre);


--
-- TOC entry 2143 (class 2606 OID 16457)
-- Name: responsable UnicaIdentificacionResponsable; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable
    ADD CONSTRAINT "UnicaIdentificacionResponsable" UNIQUE (identificacion);


--
-- TOC entry 2137 (class 2606 OID 16435)
-- Name: proyecto UnicoCodigoProyecto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyecto
    ADD CONSTRAINT "UnicoCodigoProyecto" UNIQUE (codigo);


--
-- TOC entry 2139 (class 2606 OID 16437)
-- Name: proyecto UnicoNombreProyecto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyecto
    ADD CONSTRAINT "UnicoNombreProyecto" UNIQUE (nombre);


--
-- TOC entry 2177 (class 2606 OID 16807)
-- Name: auditoria auditoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auditoria
    ADD CONSTRAINT auditoria_pkey PRIMARY KEY (secuencia);


--
-- TOC entry 2184 (class 2606 OID 16641)
-- Name: art-equi-com ART-EQUI-COM_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-equi-com"
    ADD CONSTRAINT "ART-EQUI-COM_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2183 (class 2606 OID 16636)
-- Name: art-equi-com ART-EQUI-COM_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-equi-com"
    ADD CONSTRAINT "ART-EQUI-COM_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2192 (class 2606 OID 16646)
-- Name: art-lab ART-LAB_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-lab"
    ADD CONSTRAINT "ART-LAB_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2193 (class 2606 OID 16651)
-- Name: art-lab ART-LAB_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-lab"
    ADD CONSTRAINT "ART-LAB_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2186 (class 2606 OID 16656)
-- Name: art-mbs-mje ART-MBS-MJE_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-mje"
    ADD CONSTRAINT "ART-MBS-MJE_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2187 (class 2606 OID 16661)
-- Name: art-mbs-mje ART-MBS-MJE_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-mje"
    ADD CONSTRAINT "ART-MBS-MJE_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2189 (class 2606 OID 16666)
-- Name: art-mbs-ofc ART-MBS-OFC_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-ofc"
    ADD CONSTRAINT "ART-MBS-OFC_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2190 (class 2606 OID 16671)
-- Name: art-mbs-ofc ART-MBS-OFC_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-ofc"
    ADD CONSTRAINT "ART-MBS-OFC_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2197 (class 2606 OID 16574)
-- Name: art-per ART-PER_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-per"
    ADD CONSTRAINT "ART-PER_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2198 (class 2606 OID 16676)
-- Name: art-per ART-PER_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-per"
    ADD CONSTRAINT "ART-PER_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2199 (class 2606 OID 16681)
-- Name: art-per ART-PER_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-per"
    ADD CONSTRAINT "ART-PER_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2194 (class 2606 OID 16561)
-- Name: art-top ART-TOP_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-top"
    ADD CONSTRAINT "ART-TOP_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2195 (class 2606 OID 16705)
-- Name: art-top ART-TOP_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-top"
    ADD CONSTRAINT "ART-TOP_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2196 (class 2606 OID 16710)
-- Name: art-top ART-TOP_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-top"
    ADD CONSTRAINT "ART-TOP_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2200 (class 2606 OID 16587)
-- Name: art-vehi ART-VEHI_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-vehi"
    ADD CONSTRAINT "ART-VEHI_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2201 (class 2606 OID 16715)
-- Name: art-vehi ART-VEHI_clasificacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-vehi"
    ADD CONSTRAINT "ART-VEHI_clasificacion_fkey" FOREIGN KEY (clasificacion) REFERENCES clasificacion(secuencia);


--
-- TOC entry 2202 (class 2606 OID 16720)
-- Name: art-vehi ART-VEHI_marca_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-vehi"
    ADD CONSTRAINT "ART-VEHI_marca_fkey" FOREIGN KEY (marca) REFERENCES marca(secuencia);


--
-- TOC entry 2203 (class 2606 OID 16618)
-- Name: clasificacion Clasificacion_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clasificacion
    ADD CONSTRAINT "Clasificacion_categoria_fkey" FOREIGN KEY (categoria) REFERENCES categoria(secuencia);


--
-- TOC entry 2182 (class 2606 OID 16509)
-- Name: art-equi-com EQUI-COM_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-equi-com"
    ADD CONSTRAINT "EQUI-COM_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2180 (class 2606 OID 16479)
-- Name: responsable FCargo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable
    ADD CONSTRAINT "FCargo" FOREIGN KEY (cargo) REFERENCES cargo(secuencia);


--
-- TOC entry 2181 (class 2606 OID 16484)
-- Name: responsable FCiudad; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable
    ADD CONSTRAINT "FCiudad" FOREIGN KEY (ciudad) REFERENCES ciudad(secuencia);


--
-- TOC entry 2178 (class 2606 OID 16443)
-- Name: proyecto FEntidadContratante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyecto
    ADD CONSTRAINT "FEntidadContratante" FOREIGN KEY ("entidadContratante") REFERENCES "entidadContratante"(secuencia);


--
-- TOC entry 2421 (class 0 OID 0)
-- Dependencies: 2178
-- Name: CONSTRAINT "FEntidadContratante" ON proyecto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON CONSTRAINT "FEntidadContratante" ON proyecto IS 'Relación con Entidad contratante.';


--
-- TOC entry 2179 (class 2606 OID 16458)
-- Name: responsable FProyecto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY responsable
    ADD CONSTRAINT "FProyecto" FOREIGN KEY (proyecto) REFERENCES proyecto(secuencia);


--
-- TOC entry 2191 (class 2606 OID 16548)
-- Name: art-lab LAB_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-lab"
    ADD CONSTRAINT "LAB_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2185 (class 2606 OID 16522)
-- Name: art-mbs-mje MBS-MJE_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-mje"
    ADD CONSTRAINT "MBS-MJE_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2188 (class 2606 OID 16535)
-- Name: art-mbs-ofc MBS-OFC_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "art-mbs-ofc"
    ADD CONSTRAINT "MBS-OFC_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2204 (class 2606 OID 16631)
-- Name: marca Marca_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marca
    ADD CONSTRAINT "Marca_categoria_fkey" FOREIGN KEY (categoria) REFERENCES categoria(secuencia);


--
-- TOC entry 2205 (class 2606 OID 16736)
-- Name: prestamo Prestamo_articulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prestamo
    ADD CONSTRAINT "Prestamo_articulo_fkey" FOREIGN KEY (articulo) REFERENCES articulo(secuencia);


--
-- TOC entry 2206 (class 2606 OID 16741)
-- Name: prestamo Prestamo_responsable_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prestamo
    ADD CONSTRAINT "Prestamo_responsable_fkey" FOREIGN KEY (responsable) REFERENCES responsable(secuencia);


-- Completed on 2017-03-31 17:42:22

--
-- PostgreSQL database dump complete
--

