--
-- PostgreSQL database dump
--

-- Started on 2011-02-09 05:31:27 CET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1565 (class 1259 OID 24836)
-- Dependencies: 6
-- Name: _roles; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _roles (
    rola character varying NOT NULL,
    opis character varying(255) NOT NULL
);


ALTER TABLE public._roles OWNER TO slow;

--
-- TOC entry 1576 (class 1259 OID 24933)
-- Dependencies: 6
-- Name: _roles_rola_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE _roles_rola_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public._roles_rola_seq OWNER TO slow;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 1576
-- Name: _roles_rola_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('_roles_rola_seq', 1, false);


--
-- TOC entry 1566 (class 1259 OID 24842)
-- Dependencies: 6
-- Name: _users__roles; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _users__roles (
    username character varying(50) NOT NULL,
    roles_rola character varying(50) NOT NULL,
    id_user_roles integer NOT NULL
);


ALTER TABLE public._users__roles OWNER TO slow;

--
-- TOC entry 1567 (class 1259 OID 24845)
-- Dependencies: 1566 6
-- Name: _user_roles_id_user_roles_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE _user_roles_id_user_roles_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public._user_roles_id_user_roles_seq OWNER TO slow;

--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 1567
-- Name: _user_roles_id_user_roles_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE _user_roles_id_user_roles_seq OWNED BY _users__roles.id_user_roles;


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 1567
-- Name: _user_roles_id_user_roles_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('_user_roles_id_user_roles_seq', 128, true);


--
-- TOC entry 1568 (class 1259 OID 24847)
-- Dependencies: 1884 1885 1886 1887 1888 1889 1890 1891 6
-- Name: _users; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _users (
    username character varying(50) NOT NULL,
    userpass character varying(50) NOT NULL,
    email character varying(100) DEFAULT 'email@e.com'::character varying,
    wiadomosc character varying(255) DEFAULT ''::character varying,
    data_zmiany timestamp with time zone DEFAULT now() NOT NULL,
    tel1 character varying DEFAULT 0 NOT NULL,
    nip character varying DEFAULT 'nip'::character varying,
    ulica character varying DEFAULT 'ulica'::character varying NOT NULL,
    miasto character varying DEFAULT 'miasto'::character varying NOT NULL,
    imie_nazwisko character varying DEFAULT ''::character varying NOT NULL,
    opis text
);


ALTER TABLE public._users OWNER TO slow;

--
-- TOC entry 1577 (class 1259 OID 24935)
-- Dependencies: 6
-- Name: _users_username_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE _users_username_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public._users_username_seq OWNER TO slow;

--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 1577
-- Name: _users_username_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('_users_username_seq', 1, false);


--
-- TOC entry 1605 (class 1259 OID 107132)
-- Dependencies: 6
-- Name: absolwforposty; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE absolwforposty (
    idabsolwforposty integer NOT NULL,
    datadodania timestamp with time zone NOT NULL,
    username character varying(50) NOT NULL,
    idabsolwforwatki integer NOT NULL,
    tresc text NOT NULL
);


ALTER TABLE public.absolwforposty OWNER TO slow;

--
-- TOC entry 1604 (class 1259 OID 107130)
-- Dependencies: 6 1605
-- Name: absolwforposty_idabsolwforposty_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE absolwforposty_idabsolwforposty_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.absolwforposty_idabsolwforposty_seq OWNER TO slow;

--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 1604
-- Name: absolwforposty_idabsolwforposty_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE absolwforposty_idabsolwforposty_seq OWNED BY absolwforposty.idabsolwforposty;


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 1604
-- Name: absolwforposty_idabsolwforposty_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('absolwforposty_idabsolwforposty_seq', 1, false);


--
-- TOC entry 1603 (class 1259 OID 107118)
-- Dependencies: 1923 6
-- Name: absolwforwatki; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE absolwforwatki (
    idabsolwforwatki integer NOT NULL,
    datadodania timestamp with time zone DEFAULT now() NOT NULL,
    tytul character varying(255) NOT NULL,
    username character varying(50) NOT NULL
);


ALTER TABLE public.absolwforwatki OWNER TO slow;

--
-- TOC entry 1602 (class 1259 OID 107116)
-- Dependencies: 1603 6
-- Name: absolwforwatki_idabsolwforwatki_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE absolwforwatki_idabsolwforwatki_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.absolwforwatki_idabsolwforwatki_seq OWNER TO slow;

--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 1602
-- Name: absolwforwatki_idabsolwforwatki_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE absolwforwatki_idabsolwforwatki_seq OWNED BY absolwforwatki.idabsolwforwatki;


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 1602
-- Name: absolwforwatki_idabsolwforwatki_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('absolwforwatki_idabsolwforwatki_seq', 3, true);


--
-- TOC entry 1601 (class 1259 OID 90727)
-- Dependencies: 6
-- Name: boksy; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE boksy (
    idboksy integer NOT NULL,
    idboksycfg integer NOT NULL,
    naglowek character varying(255) NOT NULL,
    tresc text,
    obraz bytea
);


ALTER TABLE public.boksy OWNER TO slow;

--
-- TOC entry 1600 (class 1259 OID 90725)
-- Dependencies: 1601 6
-- Name: boksy_idboksy_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE boksy_idboksy_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.boksy_idboksy_seq OWNER TO slow;

--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 1600
-- Name: boksy_idboksy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE boksy_idboksy_seq OWNED BY boksy.idboksy;


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 1600
-- Name: boksy_idboksy_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('boksy_idboksy_seq', 9, true);


--
-- TOC entry 1599 (class 1259 OID 90717)
-- Dependencies: 1919 1920 6
-- Name: boksycfg; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE boksycfg (
    idboksycfg integer NOT NULL,
    nazwa character varying(250) NOT NULL,
    widoczny boolean DEFAULT true NOT NULL,
    iloscelementow integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.boksycfg OWNER TO slow;

--
-- TOC entry 1598 (class 1259 OID 90715)
-- Dependencies: 1599 6
-- Name: boksycfg_idboksycfg_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE boksycfg_idboksycfg_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.boksycfg_idboksycfg_seq OWNER TO slow;

--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 1598
-- Name: boksycfg_idboksycfg_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE boksycfg_idboksycfg_seq OWNED BY boksycfg.idboksycfg;


--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 1598
-- Name: boksycfg_idboksycfg_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('boksycfg_idboksycfg_seq', 1, true);


--
-- TOC entry 1597 (class 1259 OID 82502)
-- Dependencies: 1917 6
-- Name: fotykursantkoment; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE fotykursantkoment (
    idfotykursantkoment integer NOT NULL,
    username character varying(50) NOT NULL,
    tresc text NOT NULL,
    datadodania timestamp with time zone DEFAULT now() NOT NULL,
    idlekcjafotykursant integer NOT NULL
);


ALTER TABLE public.fotykursantkoment OWNER TO slow;

--
-- TOC entry 1596 (class 1259 OID 82500)
-- Dependencies: 6 1597
-- Name: fotykursantkoment_idfotykursantkoment_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE fotykursantkoment_idfotykursantkoment_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.fotykursantkoment_idfotykursantkoment_seq OWNER TO slow;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 1596
-- Name: fotykursantkoment_idfotykursantkoment_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE fotykursantkoment_idfotykursantkoment_seq OWNED BY fotykursantkoment.idfotykursantkoment;


--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 1596
-- Name: fotykursantkoment_idfotykursantkoment_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('fotykursantkoment_idfotykursantkoment_seq', 11, true);


--
-- TOC entry 1575 (class 1259 OID 24931)
-- Dependencies: 6
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO slow;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 1575
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- TOC entry 1569 (class 1259 OID 24861)
-- Dependencies: 1893 1894 1895 6
-- Name: kursy; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE kursy (
    idkursy integer NOT NULL,
    nazwa character varying(255) NOT NULL,
    opis text NOT NULL,
    dataod date NOT NULL,
    datado date NOT NULL,
    opis_krotki text NOT NULL,
    stacjonarny boolean DEFAULT false NOT NULL,
    idpoziomyzaawansowania integer NOT NULL,
    wielkoscgrupy integer DEFAULT 1 NOT NULL,
    idtypykursu integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.kursy OWNER TO slow;

--
-- TOC entry 1570 (class 1259 OID 24867)
-- Dependencies: 6 1569
-- Name: kursy_idkursy_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE kursy_idkursy_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.kursy_idkursy_seq OWNER TO slow;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 1570
-- Name: kursy_idkursy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE kursy_idkursy_seq OWNED BY kursy.idkursy;


--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 1570
-- Name: kursy_idkursy_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('kursy_idkursy_seq', 8, true);


--
-- TOC entry 1571 (class 1259 OID 24869)
-- Dependencies: 6
-- Name: kursy_users; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE kursy_users (
    idkursyusers integer NOT NULL,
    idkursy integer NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.kursy_users OWNER TO slow;

--
-- TOC entry 1572 (class 1259 OID 24872)
-- Dependencies: 6 1571
-- Name: kursy_users_idkursyusers_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE kursy_users_idkursyusers_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.kursy_users_idkursyusers_seq OWNER TO slow;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 1572
-- Name: kursy_users_idkursyusers_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE kursy_users_idkursyusers_seq OWNED BY kursy_users.idkursyusers;


--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 1572
-- Name: kursy_users_idkursyusers_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('kursy_users_idkursyusers_seq', 47, true);


--
-- TOC entry 1579 (class 1259 OID 49511)
-- Dependencies: 1899 6
-- Name: lekcja; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE lekcja (
    idlekcja integer NOT NULL,
    tytul character varying(255) NOT NULL,
    tresc text NOT NULL,
    datazmiany timestamp with time zone,
    idkursy integer NOT NULL,
    lp integer NOT NULL,
    widoczna boolean DEFAULT false NOT NULL
);


ALTER TABLE public.lekcja OWNER TO slow;

--
-- TOC entry 1578 (class 1259 OID 49509)
-- Dependencies: 6 1579
-- Name: lekcja_idlekcja_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE lekcja_idlekcja_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lekcja_idlekcja_seq OWNER TO slow;

--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 1578
-- Name: lekcja_idlekcja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE lekcja_idlekcja_seq OWNED BY lekcja.idlekcja;


--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 1578
-- Name: lekcja_idlekcja_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('lekcja_idlekcja_seq', 20, true);


--
-- TOC entry 1581 (class 1259 OID 49522)
-- Dependencies: 1901 6
-- Name: lekcjafoty; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE lekcjafoty (
    idlekcjafoty integer NOT NULL,
    datadodania timestamp with time zone,
    opis text,
    plikmini bytea,
    plik bytea,
    idlekcja integer NOT NULL,
    lp integer DEFAULT 0 NOT NULL,
    exif text
);


ALTER TABLE public.lekcjafoty OWNER TO slow;

--
-- TOC entry 1580 (class 1259 OID 49520)
-- Dependencies: 6 1581
-- Name: lekcjafoty_idlekcjafoty_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE lekcjafoty_idlekcjafoty_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lekcjafoty_idlekcjafoty_seq OWNER TO slow;

--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 1580
-- Name: lekcjafoty_idlekcjafoty_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE lekcjafoty_idlekcjafoty_seq OWNED BY lekcjafoty.idlekcjafoty;


--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 1580
-- Name: lekcjafoty_idlekcjafoty_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('lekcjafoty_idlekcjafoty_seq', 64, true);


--
-- TOC entry 1591 (class 1259 OID 82389)
-- Dependencies: 1911 6
-- Name: lekcjafotykursant; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE lekcjafotykursant (
    idlekcjafotykursant integer NOT NULL,
    datadodania timestamp with time zone DEFAULT now() NOT NULL,
    opis text,
    plikmini bytea,
    plik bytea,
    idlekcja integer NOT NULL,
    exif text,
    username character varying(50) NOT NULL
);


ALTER TABLE public.lekcjafotykursant OWNER TO slow;

--
-- TOC entry 1590 (class 1259 OID 82387)
-- Dependencies: 1591 6
-- Name: lekcjafotykursant_idlekcjafotykursant_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE lekcjafotykursant_idlekcjafotykursant_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lekcjafotykursant_idlekcjafotykursant_seq OWNER TO slow;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 1590
-- Name: lekcjafotykursant_idlekcjafotykursant_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE lekcjafotykursant_idlekcjafotykursant_seq OWNED BY lekcjafotykursant.idlekcjafotykursant;


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 1590
-- Name: lekcjafotykursant_idlekcjafotykursant_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('lekcjafotykursant_idlekcjafotykursant_seq', 6, true);


--
-- TOC entry 1589 (class 1259 OID 82351)
-- Dependencies: 1909 6
-- Name: lekcjakoment; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE lekcjakoment (
    idlekcjakoment integer NOT NULL,
    idlekcja integer NOT NULL,
    username character varying(50) NOT NULL,
    tresc text NOT NULL,
    datadodania timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.lekcjakoment OWNER TO slow;

--
-- TOC entry 1588 (class 1259 OID 82349)
-- Dependencies: 1589 6
-- Name: lekcjakoment_idlekcjakoment_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE lekcjakoment_idlekcjakoment_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lekcjakoment_idlekcjakoment_seq OWNER TO slow;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 1588
-- Name: lekcjakoment_idlekcjakoment_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE lekcjakoment_idlekcjakoment_seq OWNED BY lekcjakoment.idlekcjakoment;


--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 1588
-- Name: lekcjakoment_idlekcjakoment_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('lekcjakoment_idlekcjakoment_seq', 19, true);


--
-- TOC entry 1583 (class 1259 OID 49538)
-- Dependencies: 1903 6
-- Name: lekcjapliki; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE lekcjapliki (
    idlekcjapliki integer NOT NULL,
    datadodania timestamp with time zone,
    opis text,
    plik bytea,
    idlekcja integer NOT NULL,
    nazwapliku character varying DEFAULT 'plik'::character varying NOT NULL
);


ALTER TABLE public.lekcjapliki OWNER TO slow;

--
-- TOC entry 1582 (class 1259 OID 49536)
-- Dependencies: 1583 6
-- Name: lekcjapliki_idlekcjapliki_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE lekcjapliki_idlekcjapliki_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lekcjapliki_idlekcjapliki_seq OWNER TO slow;

--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 1582
-- Name: lekcjapliki_idlekcjapliki_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE lekcjapliki_idlekcjapliki_seq OWNED BY lekcjapliki.idlekcjapliki;


--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 1582
-- Name: lekcjapliki_idlekcjapliki_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('lekcjapliki_idlekcjapliki_seq', 37, true);


--
-- TOC entry 1585 (class 1259 OID 66047)
-- Dependencies: 1905 6
-- Name: newsy; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE newsy (
    idnewsy integer NOT NULL,
    tytul character varying(255) NOT NULL,
    tresc text,
    datadodania date DEFAULT now() NOT NULL
);


ALTER TABLE public.newsy OWNER TO slow;

--
-- TOC entry 1584 (class 1259 OID 66045)
-- Dependencies: 6 1585
-- Name: newsy_idnewsy_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE newsy_idnewsy_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.newsy_idnewsy_seq OWNER TO slow;

--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 1584
-- Name: newsy_idnewsy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE newsy_idnewsy_seq OWNED BY newsy.idnewsy;


--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 1584
-- Name: newsy_idnewsy_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('newsy_idnewsy_seq', 8, true);


--
-- TOC entry 1587 (class 1259 OID 74151)
-- Dependencies: 1907 6
-- Name: newsykursy; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE newsykursy (
    idnewsykursy integer NOT NULL,
    tytul character varying(255) NOT NULL,
    tresc text,
    datadodania date DEFAULT now() NOT NULL,
    idkursy integer NOT NULL
);


ALTER TABLE public.newsykursy OWNER TO slow;

--
-- TOC entry 1586 (class 1259 OID 74149)
-- Dependencies: 6 1587
-- Name: newsykursy_idnewsykursy_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE newsykursy_idnewsykursy_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.newsykursy_idnewsykursy_seq OWNER TO slow;

--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 1586
-- Name: newsykursy_idnewsykursy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE newsykursy_idnewsykursy_seq OWNED BY newsykursy.idnewsykursy;


--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 1586
-- Name: newsykursy_idnewsykursy_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('newsykursy_idnewsykursy_seq', 9, true);


--
-- TOC entry 1593 (class 1259 OID 82412)
-- Dependencies: 1913 6
-- Name: poziomyzaawansowania; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE poziomyzaawansowania (
    idpoziomyzaawansowania integer NOT NULL,
    nazwa character varying(255) NOT NULL,
    opis text DEFAULT ''::text NOT NULL
);


ALTER TABLE public.poziomyzaawansowania OWNER TO slow;

--
-- TOC entry 1592 (class 1259 OID 82410)
-- Dependencies: 1593 6
-- Name: poziomyzaawansowania_idpoziomyzaawansowania_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE poziomyzaawansowania_idpoziomyzaawansowania_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.poziomyzaawansowania_idpoziomyzaawansowania_seq OWNER TO slow;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 1592
-- Name: poziomyzaawansowania_idpoziomyzaawansowania_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE poziomyzaawansowania_idpoziomyzaawansowania_seq OWNED BY poziomyzaawansowania.idpoziomyzaawansowania;


--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 1592
-- Name: poziomyzaawansowania_idpoziomyzaawansowania_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('poziomyzaawansowania_idpoziomyzaawansowania_seq', 93, true);


--
-- TOC entry 1574 (class 1259 OID 24910)
-- Dependencies: 6
-- Name: statyczne; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE statyczne (
    id_statyczne integer NOT NULL,
    tytul character varying(255) NOT NULL,
    opis character varying(255) NOT NULL,
    tresc text NOT NULL,
    lp integer NOT NULL,
    rola character varying(255)
);


ALTER TABLE public.statyczne OWNER TO slow;

--
-- TOC entry 1573 (class 1259 OID 24908)
-- Dependencies: 6 1574
-- Name: statyczne_id_statyczne_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE statyczne_id_statyczne_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.statyczne_id_statyczne_seq OWNER TO slow;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 1573
-- Name: statyczne_id_statyczne_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE statyczne_id_statyczne_seq OWNED BY statyczne.id_statyczne;


--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 1573
-- Name: statyczne_id_statyczne_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('statyczne_id_statyczne_seq', 164, true);


--
-- TOC entry 1595 (class 1259 OID 82454)
-- Dependencies: 1915 6
-- Name: typykursu; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE typykursu (
    idtypykursu integer NOT NULL,
    nazwa character varying(255) NOT NULL,
    opis text DEFAULT ''::text NOT NULL
);


ALTER TABLE public.typykursu OWNER TO slow;

--
-- TOC entry 1594 (class 1259 OID 82452)
-- Dependencies: 1595 6
-- Name: typykursu_idtypykursu_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE typykursu_idtypykursu_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.typykursu_idtypykursu_seq OWNER TO slow;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 1594
-- Name: typykursu_idtypykursu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE typykursu_idtypykursu_seq OWNED BY typykursu.idtypykursu;


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 1594
-- Name: typykursu_idtypykursu_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('typykursu_idtypykursu_seq', 37, true);


--
-- TOC entry 1883 (class 2604 OID 24874)
-- Dependencies: 1567 1566
-- Name: id_user_roles; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE _users__roles ALTER COLUMN id_user_roles SET DEFAULT nextval('_user_roles_id_user_roles_seq'::regclass);


--
-- TOC entry 1924 (class 2604 OID 107135)
-- Dependencies: 1604 1605 1605
-- Name: idabsolwforposty; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE absolwforposty ALTER COLUMN idabsolwforposty SET DEFAULT nextval('absolwforposty_idabsolwforposty_seq'::regclass);


--
-- TOC entry 1922 (class 2604 OID 107121)
-- Dependencies: 1602 1603 1603
-- Name: idabsolwforwatki; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE absolwforwatki ALTER COLUMN idabsolwforwatki SET DEFAULT nextval('absolwforwatki_idabsolwforwatki_seq'::regclass);


--
-- TOC entry 1921 (class 2604 OID 107099)
-- Dependencies: 1600 1601 1601
-- Name: idboksy; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE boksy ALTER COLUMN idboksy SET DEFAULT nextval('boksy_idboksy_seq'::regclass);


--
-- TOC entry 1918 (class 2604 OID 90720)
-- Dependencies: 1599 1598 1599
-- Name: idboksycfg; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE boksycfg ALTER COLUMN idboksycfg SET DEFAULT nextval('boksycfg_idboksycfg_seq'::regclass);


--
-- TOC entry 1916 (class 2604 OID 82505)
-- Dependencies: 1596 1597 1597
-- Name: idfotykursantkoment; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE fotykursantkoment ALTER COLUMN idfotykursantkoment SET DEFAULT nextval('fotykursantkoment_idfotykursantkoment_seq'::regclass);


--
-- TOC entry 1892 (class 2604 OID 24875)
-- Dependencies: 1570 1569
-- Name: idkursy; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE kursy ALTER COLUMN idkursy SET DEFAULT nextval('kursy_idkursy_seq'::regclass);


--
-- TOC entry 1896 (class 2604 OID 24876)
-- Dependencies: 1572 1571
-- Name: idkursyusers; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE kursy_users ALTER COLUMN idkursyusers SET DEFAULT nextval('kursy_users_idkursyusers_seq'::regclass);


--
-- TOC entry 1898 (class 2604 OID 49514)
-- Dependencies: 1579 1578 1579
-- Name: idlekcja; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE lekcja ALTER COLUMN idlekcja SET DEFAULT nextval('lekcja_idlekcja_seq'::regclass);


--
-- TOC entry 1900 (class 2604 OID 49525)
-- Dependencies: 1581 1580 1581
-- Name: idlekcjafoty; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE lekcjafoty ALTER COLUMN idlekcjafoty SET DEFAULT nextval('lekcjafoty_idlekcjafoty_seq'::regclass);


--
-- TOC entry 1910 (class 2604 OID 82392)
-- Dependencies: 1591 1590 1591
-- Name: idlekcjafotykursant; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE lekcjafotykursant ALTER COLUMN idlekcjafotykursant SET DEFAULT nextval('lekcjafotykursant_idlekcjafotykursant_seq'::regclass);


--
-- TOC entry 1908 (class 2604 OID 82354)
-- Dependencies: 1588 1589 1589
-- Name: idlekcjakoment; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE lekcjakoment ALTER COLUMN idlekcjakoment SET DEFAULT nextval('lekcjakoment_idlekcjakoment_seq'::regclass);


--
-- TOC entry 1902 (class 2604 OID 49541)
-- Dependencies: 1583 1582 1583
-- Name: idlekcjapliki; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE lekcjapliki ALTER COLUMN idlekcjapliki SET DEFAULT nextval('lekcjapliki_idlekcjapliki_seq'::regclass);


--
-- TOC entry 1904 (class 2604 OID 66050)
-- Dependencies: 1585 1584 1585
-- Name: idnewsy; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE newsy ALTER COLUMN idnewsy SET DEFAULT nextval('newsy_idnewsy_seq'::regclass);


--
-- TOC entry 1906 (class 2604 OID 74154)
-- Dependencies: 1586 1587 1587
-- Name: idnewsykursy; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE newsykursy ALTER COLUMN idnewsykursy SET DEFAULT nextval('newsykursy_idnewsykursy_seq'::regclass);


--
-- TOC entry 1912 (class 2604 OID 82415)
-- Dependencies: 1592 1593 1593
-- Name: idpoziomyzaawansowania; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE poziomyzaawansowania ALTER COLUMN idpoziomyzaawansowania SET DEFAULT nextval('poziomyzaawansowania_idpoziomyzaawansowania_seq'::regclass);


--
-- TOC entry 1897 (class 2604 OID 24913)
-- Dependencies: 1573 1574 1574
-- Name: id_statyczne; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE statyczne ALTER COLUMN id_statyczne SET DEFAULT nextval('statyczne_id_statyczne_seq'::regclass);


--
-- TOC entry 1914 (class 2604 OID 82457)
-- Dependencies: 1595 1594 1595
-- Name: idtypykursu; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE typykursu ALTER COLUMN idtypykursu SET DEFAULT nextval('typykursu_idtypykursu_seq'::regclass);


--
-- TOC entry 2006 (class 0 OID 24836)
-- Dependencies: 1565
-- Data for Name: _roles; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY _roles (rola, opis) FROM stdin;
kursant	Kursant
admin	admin
wykladowca	wykladowca
absolwent	absolwent
\.


--
-- TOC entry 2008 (class 0 OID 24847)
-- Dependencies: 1568
-- Data for Name: _users; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY _users (username, userpass, email, wiadomosc, data_zmiany, tel1, nip, ulica, miasto, imie_nazwisko, opis) FROM stdin;
aa	aa	email@e.com		2010-12-12 09:49:20.345+01	11	123-123-12-12	ulica	miasto	aaa	
kursant	kk	aa@aa.pl		2010-12-17 23:48:24.048+01	32423423423424234234	111-111-11-11	ulica	miasto	kursanttttttttt	
222	222	222@22.22	\N	2010-12-19 14:01:04.122+01	222	222-222-22-22	222	222	wyklad	
ww	ww	www@ss.oo	\N	2010-12-23 08:41:39.095+01	ww	123-123-12-12	ww	ww	www	<p>wwwww</p>
www	www	ww@ww.ww	\N	2010-12-23 08:41:50.665+01	ww	ww	ww	ww	www3333	
zz	zz	kkkk@kk.kk	\N	2011-01-15 13:36:20.886+01	123		kkkkk	kkk	kkk	<p>sssssssssssssssssssssssssssssssss</p>
qqqq	qqqq	qqqq@qqqq.qqqq	\N	2011-01-29 08:14:07.492+01	qqqq		qqqq	qqqq	qqqq	
</td>	</td>	wwww@aa.pl	\N	2011-01-29 08:20:07.411+01	</td>		</td>	</td>	</td>	
qq	qq	qqq@ss.ss	\N	2011-02-07 21:12:32.125+01	2222222222222222	qqq	qqq	qqq	qqq	
\.


--
-- TOC entry 2007 (class 0 OID 24842)
-- Dependencies: 1566
-- Data for Name: _users__roles; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY _users__roles (username, roles_rola, id_user_roles) FROM stdin;
aa	admin	102
222	wykladowca	113
kursant	kursant	114
www	kursant	118
www	wykladowca	119
ww	kursant	120
ww	wykladowca	121
zz	kursant	123
qq	absolwent	128
\.


--
-- TOC entry 2025 (class 0 OID 107132)
-- Dependencies: 1605
-- Data for Name: absolwforposty; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY absolwforposty (idabsolwforposty, datadodania, username, idabsolwforwatki, tresc) FROM stdin;
\.


--
-- TOC entry 2024 (class 0 OID 107118)
-- Dependencies: 1603
-- Data for Name: absolwforwatki; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY absolwforwatki (idabsolwforwatki, datadodania, tytul, username) FROM stdin;
1	2011-02-08 00:00:00+01	zxczxczxc	qq
2	2011-02-08 00:00:00+01	sdfsdf	qq
3	2011-02-09 05:30:14.248+01	eeeeeeeeeee	qq
\.


--
-- TOC entry 2023 (class 0 OID 90727)
-- Dependencies: 1601
-- Data for Name: boksy; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY boksy (idboksy, idboksycfg, naglowek, tresc, obraz) FROM stdin;
1	1	boksik bez obrazka	<p>mozna coś pisać</p>\r\n<p><span style="font-size: small;"><strong>albo wstawić linka:</strong></span></p>\r\n<p><span style="font-size: small;"><strong><a href="http://www.gazeta.pl">www.gazeta.pl</a></strong></span></p>	\N
2	1	boksik z obrazkiem	<p>obrazek - i nie działa</p>	\N
9	1	33333333	<p>333333333</p>	\N
8	1	qqqqqqqqqqqq	<p>ssssssssssssssssss</p>	\N
\.


--
-- TOC entry 2022 (class 0 OID 90717)
-- Dependencies: 1599
-- Data for Name: boksycfg; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY boksycfg (idboksycfg, nazwa, widoczny, iloscelementow) FROM stdin;
1	prawakolumna	t	13
\.


--
-- TOC entry 2021 (class 0 OID 82502)
-- Dependencies: 1597
-- Data for Name: fotykursantkoment; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY fotykursantkoment (idfotykursantkoment, username, tresc, datadodania, idlekcjafotykursant) FROM stdin;
5	zz	ffffffffffffffff	2011-02-02 05:59:00.835+01	5
10	zz	sssssssss	2011-02-02 06:13:45.694+01	5
11	ww	rrrrrrrrrrrrrr	2011-02-02 06:14:10.442+01	5
\.


--
-- TOC entry 2009 (class 0 OID 24861)
-- Dependencies: 1569
-- Data for Name: kursy; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY kursy (idkursy, nazwa, opis, dataod, datado, opis_krotki, stacjonarny, idpoziomyzaawansowania, wielkoscgrupy, idtypykursu) FROM stdin;
5	cos11	<p>hhhhhhhhhhhhhhhhhhhhh</p>	2010-08-29	2011-09-02	cos	t	90	1	1
3	asdasd	<p>sdfsdfsdf</p>\r\n<p>&nbsp;</p>\r\n<p><img title="dddddddddddddd" src="../rfRes/org.richfaces.resource.MediaOutputResource.xhtml?do=eAGNkr9rFEEUx1-WpFAjxFMCFsEjSkDQ2Uo8OQNy4o-DSwJuDCZW73ZfNhtndyYzs3d7CQYtVNBGiHYBBS1j5X8hxCJgKYgWYqGCCIKVs3e5XAgWmfo73!f5ft!b-A4DqYKjt2uL2EDGMQnZVH2RfFN--v7WiyF9mjsAmQSAfq3gSE9VEYITJptFde!D-t8fDvTNwUADeUpWLa12LNdmbB590swXsRQJJYZ5Bg1dFzwg5WGD1Oy7t-Nr65sTDjg1OOBz1HoSYzJQaCO5OZLrGRUlYbkGB7X9E7Q9DAx3FJFwPVIR8mgZ65zKmczH70KtJoZCUoXPL1!!uf-oZFGrXVQFQ71Ik2lcJ!Vw4!nIoWefnnRzg16CVejPPc!aGEynyXaoPBonoxlxNo3hBJkFEVzJpCKtI5HYzvLXN2gLVHC8U4eV7tVh4fepypclkw-0q9jR9ZxePXjs!ZrbuthGshwnuhz!cavGkn-8sVoanF37mU!O4Q83x2D05EqzdYdjUI0xpJuSCwwqdoFMYpSYCi637gLsPYXt3t9szXz9NrJyrVuJY-BYu7ZIsKnUyNRYIWFsdrfZOSMps-YMTLud2aLpozsvTOuqUDHLFkzMi5fOnztzoVT0rYGhy8LuKjHj-6Adzf4BMlv1AA__&amp;time=1293824021103" alt="rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr" width="150" height="100" /></p>\r\n<p>&nbsp;</p>\r\n<p style="text-align: center;" dir="ltr"><span style="color: #a52a2a;"><span style="background-color: #00ff00;"><span style="font-size: 16px;">sdfsdf</span>sdfd<strong><span style="font-size: 28px;"><span style="font-family: Georgia, serif;">sfsd</span></span></strong></span></span><strong><span style="font-size: 28px;"><span style="font-family: Georgia, serif;">f</span></span></strong></p>	2010-01-07	2010-11-27	rrrrrrrrrrrrrrrrrrrrrrrrrrrrrr eeeeeeeeeeeeeeeee	f	92	1	1
4	gggggggggg	<p>asd<span style="background-color: #ffd700;">asdasd</span>asdasdffffffff<strong>fffffffffffffffffffffffffff</strong></p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<h4 style="text-align: left; padding: 0px; margin: 0px;"><span class="Apple-style-span" style="color: #000000; font-family: Tahoma, lucida, arial, helvetica, sans-serif; font-size: 11px;">W sobotę ok. 12.30 na rondzie zderzyły się dwa auta marki volvo i renault</span></h4>\r\n<div id="banP34" style="margin-top: 10px; margin-right: auto; margin-bottom: 0px; margin-left: auto; text-align: left; padding: 0px;">&nbsp;</div>\r\n<div id="art2" style="text-align: left; padding: 0px; margin: 0px;">\r\n<div id="artykul" style="text-align: left; font: normal normal normal 13px/19px Arial, Helvetica, sans-serif; padding: 0px; margin: 0px;"><span class="Apple-style-span" style="color: #000000; font-family: Tahoma, lucida, arial, helvetica, sans-serif; font-size: 11px;">Drog&oacute;wka podaje, że choć zdarzenie wyglądało groźnie, nie ma rannych. Nie ma też utrudnień w ruchu&nbsp;<br style="text-align: left; padding: 0px; margin: 0px;" /> <br style="text-align: left; padding: 0px; margin: 0px;" /> Rondo&nbsp;<a style="text-align: left; text-decoration: none; color: #114488; outline-style: none; outline-width: initial; outline-color: initial; padding: 0px; margin: 0px;" href="http://tematy-poznan.gazeta.pl/%8C/1418,Srodka">Śr&oacute;dka</a>&nbsp;to jedno z najgorszych miejsc na mapie drogowej Poznania, co miesiąc zdarza się tutaj kilkanaście a nawet kilkadziesiąt kolizji i wypadk&oacute;w.</span></div>\r\n</div>\r\n<p><br style="text-align: left; padding: 0px; margin: 0px;" /> <br style="text-align: left; padding: 0px; margin: 0px;" /></p>\r\n<p><span class="Apple-style-span" style="color: #000000; font-family: Tahoma, lucida, arial, helvetica, sans-serif; font-size: 11px;"><span style="text-align: left; padding: 0px; margin: 0px;">Więcej...&nbsp;<a style="text-align: left; text-decoration: none; color: #003399; outline-style: none; outline-width: initial; outline-color: initial; padding: 0px; margin: 0px;" href="http://poznan.gazeta.pl/poznan/1,36001,8557580,Kolizja_na_Srodce__trzy_auta_poturbowane__Ktos_wymusil_.html#ixzz13BmEq2M1">http://poznan.gazeta.pl/poznan/1,36001,8557580,Kolizja_na_Srodce__trzy_auta_poturbowane__Ktos_wymusil_.html#ixzz13BmEq2M1</a></span></span></p>	0029-01-12	2010-09-29	111	f	90	1	1
8	11111111	<p>sdfsdfsdfsdf</p>	2010-12-31	2011-02-01	1111111111111	f	92	1	5
\.


--
-- TOC entry 2010 (class 0 OID 24869)
-- Dependencies: 1571
-- Data for Name: kursy_users; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY kursy_users (idkursyusers, idkursy, username) FROM stdin;
18	4	kursant
26	4	222
34	5	kursant
35	5	ww
36	3	ww
39	5	zz
40	5	222
46	3	qqqq
47	4	qqqq
\.


--
-- TOC entry 2012 (class 0 OID 49511)
-- Dependencies: 1579
-- Data for Name: lekcja; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY lekcja (idlekcja, tytul, tresc, datazmiany, idkursy, lp, widoczna) FROM stdin;
18	222222222222	<p>2222222222222222222</p>	2010-12-22 07:25:56.621+01	3	1	f
17	3333	<p>333333333333</p>	2010-12-22 05:50:23.482+01	3	2	f
19	3333333zzzzzzzzzzzzzzzzzz	<p>3333333333333333333333333</p>	2011-01-30 13:11:43.132+01	5	1	t
\.


--
-- TOC entry 2013 (class 0 OID 49522)
-- Dependencies: 1581
-- Data for Name: lekcjafoty; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY lekcjafoty (idlekcjafoty, datadodania, opis, plikmini, plik, idlekcja, lp, exif) FROM stdin;
64	\N	\N	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\000d\\000\\226\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\266<7\\241\\035\\177T\\026\\355<V\\360(\\015,\\262\\310\\020\\001\\234`\\023\\334\\376<\\002pq\\212\\312\\212\\031n$\\021\\303\\033\\310\\347\\242\\242\\222O\\340+kK\\022\\351\\227\\357a\\251\\333H-\\256\\201\\215\\243n\\025\\234d+g\\276\\030\\365\\025\\023\\225\\227\\231t\\343\\314\\325\\366=#P\\322\\374\\026\\226m\\247<0\\301\\022Fv\\334$\\1772\\002\\330W2c\\346\\311\\030\\007$q\\214\\342\\274\\202\\356\\331\\354\\356\\345\\267vF1\\261\\033\\320\\345\\\\ve=\\301\\034\\203\\334\\021^\\304\\260E\\025\\254\\261\\303l\\271yL\\241\\030\\235\\252s\\221\\220G\\\\\\201\\316;\\360k\\3125\\326\\270\\223Uw\\272\\307\\234R<\\341v\\216\\020\\016\\230\\0351\\217\\302\\271\\360\\325\\235F\\323:\\261T\\0254\\2323\\250\\242\\212\\3538\\202\\212(\\240\\002\\254\\332i\\367W\\373\\376\\315\\026\\375\\230\\335\\363\\001\\214\\375O\\261\\252\\325\\324\\3705ro8\\004\\374\\235\\263\\375\\352\\306\\275GN\\233\\2225\\241MT\\250\\242\\314\\257\\370G5c\\377\\000.\\237\\371\\021\\177\\306\\247\\227A\\326\\034\\251{\\030\\370\\376\\341\\215s\\365\\301\\256\\355\\224\\025\\015\\320\\221R*\\203\\202s\\237Bk\\315y\\205N\\313\\372\\371\\236\\227\\324)\\367\\177\\327\\310\\363\\317\\370F\\365fU\\333d:v\\225y\\375jE\\360\\326\\260\\247\\235=\\031s\\222\\246E\\355\\333;\\263\\337\\326\\275\\006%\\022\\015\\301H\\036\\335\\351\\356\\031\\010 \\023\\223\\326\\223\\314j\\355e\\375|\\303\\352\\024\\373\\277\\353\\344y\\333\\370WY'#O\\333\\352\\004\\311\\217\\375\\012\\206\\360\\266\\262\\317\\306\\236\\253\\223\\302\\211\\227\\372\\265zF\\365\\030f\\316y P\\010U\\007\\0318\\035*\\177\\264j\\366_\\217\\371\\217\\352\\024\\273\\277\\303\\374\\2178\\036\\027\\326\\002\\344\\351\\340\\374\\300\\377\\000\\256L\\034~9\\347\\353\\377\\000\\326F\\360\\346\\252\\021q\\247*\\270`wy\\252A\\343\\246\\013}I\\376\\225\\351\\021\\263\\026#h>\\365$\\220\\206\\207\\014\\012\\237\\257#\\232?\\264j\\365K\\361\\377\\0000\\372\\205>\\357\\360\\377\\000#\\313\\377\\000\\341\\031\\325\\313\\005\\373\\030\\317\\375uO\\361\\244o\\015j\\350\\011kL\\001\\377\\000MS\\374k\\320\\335\\374\\227\\031\\\\s\\235\\300zv\\376t\\233\\213\\017\\220c\\214\\343\\245_\\366\\205^\\313\\372\\371\\213\\352\\024\\273\\277\\353\\344cx3N\\271\\323F\\246n\\020\\333\\334Ko\\266\\011W\\346+\\3247)\\222\\275G\\345Ve\\321m\\356\\274\\241\\034I3\\375\\330\\331\\243\\306\\376\\006N\\017@\\000\\030\\364\\000q\\326\\257\\306\\014A\\371(\\314\\010\\030\\316\\010\\037O\\303\\212\\273\\242\\203\\375\\245\\023l\\017\\034(\\331,N0\\007\\371\\375i\\323\\254\\347U7\\273\\377\\000 \\225%\\012|\\251h\\211\\240\\202\\347\\354 E<ry\\304\\034\\311\\2373 \\340\\205^s\\363`v\\350zu\\031^5\\360\\273\\336\\351\\220\\335\\031\\025/-\\243\\021\\307\\026A\\336\\245\\311 \\267\\367\\2673\\266~\\277Q\\325<\\006\\347dY\\231JD\\273\\235c\\005C\\266Y\\362\\271\\3440a\\234{v\\254}^\\351\\346\\325\\204\\015\\274E\\032\\252\\374\\375I\\3062{z\\376f\\2724\\245yE\\030\\312\\365\\022\\214\\231\\345c\\303\\332\\241b>\\313\\310\\377\\000\\246\\213\\3765\\237,o\\014\\257\\024\\203\\016\\214U\\206z\\021\\326\\275%\\216\\025X3o\\3063^y\\250\\343\\373R\\357\\035<\\347\\377\\000\\320\\215^\\033\\021*\\255\\251\\031bp\\360\\245\\024\\343\\324\\255E\\024Wa\\306\\025\\324\\3700fK\\274\\222>\\340\\343\\376\\005\\\\\\265u~\\012m\\262^\\036\\377\\000&?\\361\\352\\346\\306\\177\\006_\\327S\\247\\011\\374h\\377\\000]\\016\\307\\313\\310\\307'\\246q\\333\\361\\2451a\\006O \\365\\356(Vf]\\334\\216p1\\306~\\264\\365\\306\\340\\033\\277A^\\005\\317pta\\200\\354\\007\\035\\271\\374\\350Q\\307$\\016\\177\\317\\025\\013\\273+\\355\\306G$\\006\\352*H\\217\\3140C~\\034\\342\\225\\200{\\215\\340\\200O\\247&\\2260v\\234\\234\\361\\222Gzg\\336\\310$\\343\\260\\247(!\\270\\300\\365\\343\\332\\220\\017B\\25299\\000\\372R\\273\\226\\005z\\017N\\264\\300:\\017\\345Q\\266\\357\\225A\\002\\213\\001\\034\\321y\\304\\343\\035:\\347\\031\\366\\246\\303\\271N\\336G\\256O\\270\\253\\261G\\310\\\\\\362E1\\343!\\266\\343\\327\\221\\333\\327\\371S\\346\\350\\004\\022\\246\\345*r7|\\247\\007\\037\\255di\\372\\234\\232\\014\\323\\301#5\\304\\0044j\\347%\\227\\345\\311\\007\\351\\221\\320w\\355\\322\\267\\231x\\030\\030\\003\\236\\237\\215g\\352\\032\\250\\266\\205\\254$\\234\\010\\244\\033\\3326]\\301\\362H\\364\\343\\201]XI\\332v\\261\\206%{\\227:\\004\\361U\\206\\237l\\257p\\214\\302}\\222#\\206R\\030m\\0039\\007\\030\\312\\221\\221\\334c\\257\\025\\207.\\252\\272\\265\\323^,R\\240.\\304y\\200\\014\\214\\014\\021\\355\\301\\353X3j\\366\\336J,\\026a\\244L,fN\\221\\256X\\225Q\\236\\001-\\234z\\3769\\336\\323eK\\270&\\271X\\314qy\\233c\\217$\\343\\0123\\226\\357\\327\\323\\212\\354\\304M\\250]\\243\\222\\222R\\225\\223*\\312?{\\302\\262g\\004\\001\\333\\201^y\\252\\340k\\027\\270\\351\\366\\211?\\364#^\\2256<\\365b\\271\\311\\004\\034\\343<\\327\\233j\\374\\353W\\344\\177\\317\\304\\237\\372\\021\\245\\200w\\223\\364\\014z\\264"S\\242\\212+\\324<\\300\\256\\253\\301\\231\\305\\363/Q\\345\\361\\214\\347\\357W+]W\\203[i\\274\\307\\\\\\307\\307\\375\\365\\\\\\330\\317\\340\\313\\345\\371\\2358O\\343G\\372\\350v\\012YN\\334\\216\\271\\3018\\006\\236\\257\\320\\200s\\300\\315@\\301\\201Q\\236GS\\217\\302\\245Q\\203\\216\\236\\325\\340\\263\\334\\004\\213q\\336@V\\343\\247\\377\\000Z\\246@@$\\036s\\236;T\\021\\306L\\274\\201\\202*\\320CI\\261\\015e\\013\\357\\370\\324\\253\\206\\372\\364\\250\\317\\337\\312\\343\\361\\2473\\005\\007w\\034\\201\\322\\244\\004\\311\\\\\\345x\\0351O\\020n\\301\\035\\007\\275,P\\2660\\303 \\236j\\340\\312\\345T\\022q\\330g\\371Rlef_%\\201#\\257\\345\\322\\242c\\2269'\\0352?\\302\\264Z#\\2207\\036N\\016?\\317\\275VxX\\022B\\355\\000s\\232\\224\\306U|\\263\\036q\\357\\\\~\\274\\354\\372\\263\\241\\351\\032\\252\\217n3\\375k\\266\\2261\\3461N=~\\265\\306k+\\235zq\\214p\\277\\372\\010\\256\\374\\017\\361\\037\\241\\311\\213\\370\\027\\251\\235\\010\\006E\\035\\261]\\356\\202ao\\017%\\254\\233\\021eg}\\344\\221\\265\\267\\021\\223\\307\\240\\037\\225p\\211\\024\\2214,\\353\\205pJ\\234\\365\\031#\\371\\203]\\226\\237&4\\033p\\011\\3408 {\\273\\177\\215t\\343%h+w9\\360\\321\\274\\265\\031}\\034\\226\\323\\224\\225Ln8 \\363\\350F\\010\\377\\000\\365\\032\\363=P\\347W\\275?\\364\\335\\377\\000\\364#^\\203"\\344\\214\\220Oo\\226\\274\\363Q\\301\\324\\356\\361\\323\\316\\177\\375\\010\\325`\\027\\274\\303\\037\\360\\242\\265\\024Q^\\231\\346\\005u^\\014]\\337m\\343<\\307\\377\\000\\263W+]'\\204\\256\\241\\265{\\2234\\361E\\235\\230\\363\\034.~\\367\\255sb\\323t]\\277\\255N\\214#J\\262\\277\\365\\241\\332\\205\\352z\\200y\\253)\\011\\221\\010\\030\\316;Vg\\366\\266\\235\\264\\251\\277\\26699\\000\\312\\274~\\265"kZr\\347\\032\\205\\276\\323\\330\\314\\243\\372\\327\\204\\341>\\307\\267\\317\\036\\346\\202[\\272.IPG\\035jQ\\270\\017S\\237J\\246\\232\\346\\227\\267\\037\\332\\026\\237\\214\\3523\\372\\323\\216\\265\\245\\237\\371\\211Y\\375\\014\\353\\3765.\\023\\354\\034\\361\\356X\\306\\030\\365\\365\\246\\2723\\266\\334\\223\\351\\217\\376\\265W]gK\\301\\316\\243i\\221\\330\\316\\274\\376\\264\\015gKF?\\3610\\264<d\\201:\\376]h\\344\\237`\\347\\217sB\\020\\321\\217\\230\\026c\\235\\275:U\\261\\030\\332\\011\\303c\\003\\034}\\177\\317\\343Y\\351\\255\\351\\015\\260\\177iX\\251\\034\\377\\000\\307\\302\\363\\355\\326\\254&\\277\\243\\230\\331N\\251b1\\353p\\236\\230\\365\\250p\\237f\\034\\361\\356^Y9\\301\\001\\270?63\\212\\2576\\000\\332\\030\\354\\3479\\346\\252I\\256\\350\\311\\363\\015N\\315\\212\\222F'^\\177Z\\205\\265\\315%\\363\\377\\000\\023;N\\243\\237=\\177\\306\\222\\247>\\3149\\343\\334\\266xE\\317A\\327\\203\\327\\251\\256/^F\\203[\\234\\360w\\242\\260\\366\\343\\037\\322\\272v\\3264\\256\\243T\\263\\310\\377\\000\\247\\204\\377\\000\\032\\346\\274Au\\005\\336\\251\\023\\333\\334C:\\371\\001KD\\301\\2009<q]\\270(\\31255]\\016lT\\242\\341\\243$\\326\\355\\341\\265\\323\\264WE\\303<LX\\217\\370\\013\\1776o\\316\\264\\364\\271w\\350\\261\\355\\003\\222\\300\\347\\352O?\\201\\254m^\\362+\\253\\015.4\\221\\013\\302\\254\\256\\212\\331+\\367@\\310\\355\\234T\\366\\027\\266\\326\\366\\355\\034\\323\\305\\021g\\310\\363\\034.x\\003\\275oV\\016TU\\367\\273\\374\\331\\235)%W\\312\\337\\242-N\\333\\\\\\363\\327\\327\\255y\\346\\242I\\324\\356\\311\\352f\\177\\375\\010\\327q.\\243`\\344\\250\\274\\203\\004\\365\\022\\257\\370\\377\\000\\234\\327\\015~\\301\\365\\033\\226V\\014\\014\\256C\\003\\220y<\\326\\330\\030\\264\\335\\314\\361\\322N*\\314\\257E\\024W\\244y\\241E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001Z\\332Q\\304$\\377\\000\\267\\375\\005e\\001\\222\\007\\255h[3[FC\\355\\000\\234\\344\\234TOk\\027\\015\\356]\\000\\215F\\340\\236\\345H\\376_\\322\\242\\327\\272[\\377\\000\\300\\277\\245\\021\\335\\3075\\324\\222\\002\\000\\012\\243\\223\\202O?\\343N\\326%\\216[(\\312\\221\\235\\374~G?\\322\\241]I\\032;8\\263\\022\\212(\\255\\214\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\011al\\023\\300<g\\232t\\362\\226UL(Q\\316\\000\\357E\\025\\037h\\277\\262AE\\024U\\220\\024QE\\000\\177\\377\\331	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\001\\220\\002X\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212+OD\\321g\\326\\256\\374\\250\\316\\330\\327\\227s\\333\\377\\000\\257I\\273\\015&\\335\\221J\\332\\326{\\311\\204PF\\316\\347\\260\\035+\\320<7\\360\\246\\347SSs\\251\\336\\307il\\237|\\016[\\246q\\236\\307\\363\\256\\303\\301\\236\\033\\261\\2047\\225\\026\\330"\\3102`n\\220\\216X\\223\\355\\220\\001\\354\\304zWAqx\\367o\\210\\327\\313\\264\\\\\\371P\\257\\177\\366\\217\\327\\377\\000\\256y\\351\\233\\2337\\215$\\267<\\367V\\370o\\241*:\\351\\327\\332\\210\\231A\\301\\222\\000\\310O\\347\\232\\363\\215OK\\272\\322n\\332\\336\\352<\\021\\321\\207F\\036\\242\\276\\205\\2126\\221\\310v\\332\\271\\344(\\254o\\023\\370Z\\333\\304\\032s\\307\\013\\252\\334&Ln8\\033\\260p\\017\\2618\\315(\\315\\365\\034\\351\\246\\264<\\032\\212\\236\\362\\322{\\033\\271-\\256bh\\246\\215\\266\\2727Pj\\012\\330\\346\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200&\\265\\266\\222\\362\\352+x\\207\\317#\\005\\031\\3503\\353^\\277\\341_\\016\\234\\0152\\317\\021\\025\\217\\314k\\207\\214\\221#eW\\216}\\372\\377\\000\\262Gn8\\277\\000\\330\\302.\\347\\325\\257\\023u\\255\\24623\\215\\314{~<\\003\\354Oz\\357S\\307w?i\\206Ac\\032C\\033\\214,Q\\021\\362g\\220\\016:\\373\\3265%\\255\\216\\2320v\\272:Kx/\\242\\360\\372\\351\\326\\2262\\206\\033Q\\3351\\2007|\\374\\236rF\\177\\032y\\267\\3731o>\\342\\336\\325\\025p\\032G\\344{\\025\\353\\371\\032\\311\\325\\274ou\\177g\\014Vp43\\314q"\\266?Lrs\\375k\\216\\325\\265\\025\\262\\234\\377\\000i\\352\\037\\277\\003>DD\\273\\365\\034`t\\340\\347\\014T\\326.\\242\\275\\226\\246\\352\\234\\255w\\241\\337\\177l\\350\\220\\022%\\275\\270f\\357\\266\\021\\267\\360\\344U\\270&\\323\\357\\371\\323\\256\\304\\217\\217\\270\\303k\\037\\240\\347?\\235y\\265\\235\\306\\203q\\020\\204]K\\023\\267\\003\\355\\021\\225?\\367\\321\\310\\374\\315c\\275\\346\\243\\242\\352XIY\\274\\262\\035YO;s\\200A\\030\\346\\224*);Z\\303\\235>U{\\233?\\023t#$I\\254\\305\\030W\\217\\021\\334\\0020H\\310\\012\\177\\016\\237\\217\\265y\\205{\\252kv~'\\321\\266^\\300\\321\\311t\\214\\255\\306\\325\\220\\001\\303\\001\\234\\343\\234n\\366\\306x\\025\\341\\323\\302\\366\\327\\022\\301 \\304\\221\\271F\\036\\340\\340\\327M9'\\241\\311Z\\0154\\373\\221\\321E\\025\\241\\210QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\332\\3702\\353f\\217\\251E\\031"h\\344\\212a\\223\\301\\031\\307\\363\\251f\\265\\273\\270\\211gk\\363\\001$\\252\\342a\\022\\240\\364\\311#'\\203\\311\\353\\3061\\\\\\256\\215\\250\\2357PYO\\372\\267S\\034\\200\\214\\360F3\\365\\035k\\323\\354M\\273\\370f)\\323a\\220\\355\\004\\306J\\363\\311'\\217rO\\343\\\\X\\247\\310\\324\\254z8O~.&^\\207os\\177\\241\\337\\235Fy\\035Y\\000\\215\\270\\005x\\004}\\336\\344\\234\\036\\343\\220q\\310\\254\\253m\\001\\240\\274@\\221\\021\\023#+\\024C#\\203\\307;C\\251\\350z\\347\\036\\271\\256\\273Ih\\347\\320\\245\\213~\\334\\222\\333\\216yP\\345\\211\\372T\\366\\215oqp\\214\\241\\\\\\002\\001R\\277\\221\\000\\327\\037\\266\\224\\\\\\254v{\\024\\322\\271\\035\\204\\027\\261[\\01318\\231UB\\215\\313\\2023\\223\\352@\\034\\343\\034\\343\\035y\\343+X\\321\\206\\243,2D\\312\\233\\217\\2236z*\\363\\223\\365\\037\\257\\265v\\256#\\265\\267-\\032\\250\\307\\335\\343\\275sRH\\327\\023\\264\\204\\374\\231\\302\\214u\\036\\246\\261\\2476\\345\\314\\215\\034"\\343\\312\\312q\\336\\026[o\\265\\221\\015\\305\\345\\300\\375\\346\\003\\3423\\201\\273\\216\\200\\017\\227\\007\\324\\372W\\235x\\2027\\213\\304z\\222H\\245X]I\\220\\177\\3365\\265\\004\\362\\334N\\277h\\225\\245fF\\000\\271\\311\\001r1\\237\\256j\\247\\214\\324\\177\\302B\\322\\361\\272x\\243\\231\\261\\334\\262\\344\\327\\255B\\012\\0373\\313\\305K\\235\\\\\\347\\350\\242\\212\\3518\\202\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\245\\224~\\352\\023\\376\\311\\037\\251\\250\\321w\\272\\250\\356q]RxV\\377\\000X\\236\\321-\\243H\\355\\226\\020\\276k\\0209\\007\\237\\251$\\324\\316j*\\362*\\020rv\\211\\312Q^\\224\\236\\033\\321\\3574\\271\\364\\310Q\\341\\277\\201\\2172\\014;\\021\\333\\337\\257\\037^\\325\\316^\\370"\\376\\326\\326K\\230\\356-\\246\\211Sx\\001\\210v\\035O\\030\\343\\003\\236H\\366\\315a\\014U9;=\\015\\347\\205\\251\\025u\\251\\314Wg\\341[\\365\\227K\\222\\315\\333/\\023d\\003\\335\\017\\370\\034\\376u\\313^\\330MbPJP\\357\\007\\033Nj]\\036\\377\\000\\3737R\\216s\\312\\037\\225\\307\\373'\\374\\347\\360\\253\\253\\025R\\032\\012\\214\\235*\\236\\361\\350\\211.<\\273[\\273\\325\\021\\244\\203\\010$\\033\\201\\031#p\\034\\236@\\030=\\361\\301\\305l\\013{i\\025\\026\\031\\320\\3143"\\363\\207c\\324\\234\\036y'\\255U\\267\\323\\346{q$.\\261\\347$*\\341{\\372b\\257\\206\\232\\330\\010\\244\\220\\310@\\317<\\200q\\326\\274\\211>\\307\\263\\024#\\336\\311-\\253\\307 \\371\\225O\\377\\000\\257\\371\\326sn\\212\\331\\231P\\264\\2062UA\\347=\\277\\014\\367\\253\\300\\011\\246D\\306\\351\\034d/\\267\\251\\366\\242KU\\212DS\\376\\271\\330\\274\\204t\\302\\343\\201\\376\\316z\\177\\273\\353R\\232E\\234,\\0262\\301\\342\\021lb&\\0378\\355|`\\035\\300\\023\\374\\3537\\306f1\\342I\\241\\215\\203\\213x\\343\\201\\230\\034\\202U@5\\335\\337Z\\213{\\341\\250\\307\\2012\\250\\010[\\225\\007<~#q \\372\\342\\270/\\021xwQ\\322|\\233\\353\\250g6\\327\\237<s\\3108rF\\356\\277C\\237\\377\\000Q\\257W\\01754\\231\\344\\342\\340\\340\\354`\\321E\\025\\324p\\205\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\022\\244!\\227sM\\034c\\031\\033\\2119\\374\\201\\307\\343S\\213\\020\\276X\\232]\\245\\313p\\2407\\312\\007\\\\\\347\\030\\367\\351\\301\\347\\212\\2124\\205\\324\\023"\\243\\000w\\011\\001\\301\\364\\306\\321\\374\\375:\\363\\303\\326\\345\\014b\\011\\024\\210H\\352\\275T\\372\\217\\313\\221\\337\\362\\304\\273\\364)[\\251^@\\202B#fd\\354YpO\\341\\223HT\\200\\017\\034\\214\\360j\\345\\264\\260\\333\\207c$\\201\\360\\002\\264D\\253u\\347\\364\\030\\374z\\036\\322\\033\\210\\232d2\\\\\\310\\351\\034\\213\\263%\\230\\354\\344\\236\\275\\017\\0128\\307\\343G3\\354\\034\\253\\271FH\\374\\275\\200\\237\\230\\250b1\\323=?L\\037\\306\\232\\006s\\323\\217z\\277=\\363\\341\\012\\221\\222\\034\\221\\235\\330\\3361\\200}6\\343\\217Q\\315"Nm\\255D%\\312\\272H$\\333\\216Cg\\371\\200\\007\\346E\\027v\\013+\\224B\\2268PI\\306x\\244\\253\\222\\334!\\267d24\\262t\\313\\022FK\\022\\314\\271\\351\\321\\177Z \\232\\030|\\223\\346I\\265\\224\\211\\204x\\007\\357t\\344r0\\007\\007\\336\\213\\260\\262)\\321Z\\237l\\033\\342-}1`\\354\\254\\300\\222UH\\340\\202F{\\234\\372\\340{\\032cL\\261F\\252&\\2212\\025\\374\\260\\010\\012s\\374#\\327\\001H'\\250$\\365\\245\\314\\373\\007*\\356gQVn\\356\\336\\341\\210,6\\226.B\\023\\264\\222s\\234\\036\\3438\\317\\260\\253&\\3765x\\232\\022\\352T\\005\\223\\004\\257\\230\\241Tu\\0079\\340\\373t\\367\\247w\\330,\\273\\231\\304\\02588\\351\\236\\016i+L^\\306\\351\\211%\\227s}\\366RG\\336;\\237\\003\\240\\350\\007\\241\\311\\317\\261%\\341\\226\\0072l\\304\\212\\333\\216y\\334\\314\\011 }\\024\\017L\\347\\232\\\\\\317\\260\\371Ws2\\212\\261!\\023^)\\232c\\265\\266\\356|\\356*\\270\\035}H\\037\\312\\237=\\312=\\264P\\306YWqy\\024\\014\\000x\\000\\001\\337\\000u'\\275U\\311\\261R\\237\\034fG\\332=\\013\\037\\240\\031?\\312\\264\\005\\364Q\\247\\226\\262J\\360\\355%Q\\230\\222\\271F]\\2751\\324\\347#\\267\\257J\\223\\373MJN\\2134\\252\\204\\217+wU\\001[\\030\\003 r\\024q\\327'\\360\\227'\\330\\245\\025\\334\\310\\242\\264\\276\\334\\201\\321\\014\\263\\033l*\\225S\\206\\030\\034\\360r0I<{\\012I/2\\261\\223y+K\\031#z\\226'\\034\\362\\244\\343\\031\\316\\016y\\340})\\363>\\302\\345]\\314\\352+I\\247\\201\\224F\\327r\\274\\033\\200T`X\\242\\220Fy\\343#\\030\\351\\306\\3563U\\004\\276|\\222Iq!g(p_''\\377\\000\\325\\323\\337\\035\\250L\\032!\\301\\332[\\214\\003\\216\\274\\323\\322 \\320\\311!`6`\\001\\334\\223\\377\\000\\326\\007\\374\\232\\236\\362o8!\\373D\\222\\205\\030\\314\\214I$\\362N9\\307a\\370T\\326\\263\\301\\034(\\242v\\202@\\303t\\250\\016\\342\\033!\\261\\216\\300\\005\\374s\\212\\033v\\270$\\257b\\206\\306\\007\\004`\\343<\\361\\3063M\\255(/c\\202A*\\355\\314\\212\\306D\\003h\\031'+\\300\\351\\2000;\\036i\\004\\355-\\270H\\256e\\363\\034\\021*\\273\\354@\\011\\316F8\\307Q\\317\\257\\002\\216g\\3309Ws:\\212\\265sv\\3234\\252\\247\\021\\311)\\224\\2578\\004\\377\\000\\365\\276\\225hj>H\\015\\024\\247p\\000`er\\021v\\247\\352I \\361\\300\\024]\\366\\013.\\346c\\002\\254T\\343 \\343\\203\\221IV&\\223\\314e\\205f\\304!\\216\\001\\316\\304\\344\\364\\357\\214{f\\256G<(U\\343\\270b\\353\\001\\013\\270\\220U\\202\\221\\200q\\323\\222@\\030\\353\\330\\201C\\223\\004\\221\\227E+3;\\026f,\\304\\344\\222rI\\244\\252$(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\352\\274\\035\\341yu\\233\\264\\270\\270\\005l#p\\314\\177\\277\\203\\323\\374\\377\\000\\211\\036\\241\\254\\337[\\276\\236c\\261\\011\\366\\213\\006Yc\\0129h\\327\\001\\300\\375G\\341\\\\\\277\\204X\\277\\206l\\326"\\311\\2279\\012p\\011\\334~\\367\\265\\032\\205\\304\\220\\3111IQe\\022>Z6\\344\\243pF9\\300\\350\\007\\341^Mi:\\223w\\350z\\364i(A8\\365W\\023\\304\\262\\302\\232\\272j6W\\002Y%Q\\271cl\\355`\\000\\033\\271\\351\\217\\346}3Xr\\254\\272\\235\\373H\\226\\3443|\\336R6W=\\334\\364\\344\\365?\\316\\244\\211%\\272\\220E\\002u\\357\\330}kM\\355\\222\\316#g\\031\\314\\244\\346\\342Q\\324\\037\\371\\346=\\275}\\376\\231*>\\352K\\251R\\327^\\207\\025\\250\\332^]jM\\014K\\347\\204\\340\\030\\301\\33223\\324\\320\\236\\035\\274\\011\\272\\343d9\\373\\240\\220I\\374\\273Wh\\221\\307\\030\\307\\012\\276\\203\\275T\\324\\030\\371\\201p\\001\\3562N=\\271\\256\\332U$\\325\\226\\307\\024\\251E\\266\\331>\\221\\255\\352v\\226\\013\\014\\226\\351(\\213\\345\\022\\356\\033\\210\\372~?\\326\\265\\243\\324\\345\\276b\\260Z\\263\\316F6\\345T\\003\\323\\236O\\371\\355Y\\021\\355\\003\\315#\\234\\001\\355\\307\\025<2\\317d\\377\\000iI\\031>p\\030\\006\\347\\035\\376\\225\\311Z\\021\\346vGe\\032\\217f\\316\\317M\\323~\\305nZB$\\272~^M\\240n8\\300\\351\\355\\212\\255zP\\\\\\312\\371\\001cEBO`9\\317\\376=\\372S\\346\\325\\205\\226\\217\\024\\356<\\311[\\345A\\237\\275\\357\\364\\306\\017\\345\\353X\\227\\240I\\010\\270{\\207p\\330\\177\\235\\260\\244\\372\\205\\035\\372\\327\\034b\\333\\273:\\\\\\322\\330\\212\\372\\351.\\274\\270\\243\\017\\2059\\003\\2737A\\201\\214\\361\\237\\3075\\323_\\350\\361I\\360\\266\\342\\302\\355\\213:\\206\\222\\020P\\215\\223\\022H\\000\\364\\306\\016\\011\\35095\\227\\341\\335\\021\\245t\\276\\272\\033!V\\316\\030\\225\\300\\347\\251\\035\\311\\300\\002\\256k\\332\\224\\272\\225\\312X\\333\\341`\\210a\\266\\347\\037O\\363\\236~\\225\\351\\320\\207\\263W<\\372\\262u\\036\\247\\203\\321^\\201\\342o\\010,\\266m\\177a\\031[\\210\\362f\\213\\373\\343=W\\337\\271\\257?#\\007\\006\\273#%%\\241\\30188\\2730\\242\\212*\\211\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200-C\\246\\336\\\\D%\\212\\006d=\\030b\\244\\376\\306\\324\\177\\347\\321\\377\\000J\\3524\\021\\235\\016\\337\\337v\\177\\357\\243Z\\250\\270\\005\\2708\\366\\257:\\2462q\\223I-\\017J\\236\\012\\022\\212\\223oS\\204\\376\\302\\324\\363\\217\\261\\311\\372R\\377\\000`\\352\\237\\363\\347'\\351]\\340S\\236\\275\\373\\364\\251\\020\\014\\263d\\343\\246Ef\\361\\363\\354\\213\\372\\204;\\263\\317\\377\\000\\260uO\\371\\363\\223\\364\\243\\373\\013S\\377\\000\\2379?J\\364&\\031<\\021\\264\\364\\3158|\\334\\234g\\006\\227\\366\\205N\\310\\177P\\247\\335\\236y\\375\\201\\252\\347\\037b\\223\\323\\265!\\3205A\\326\\312O\\322\\275\\024\\020W\\234\\373S\\231\\270\\301\\351\\337>\\224\\277\\264*vA\\375\\237O\\273<\\337\\373\\013S\\377\\000\\2379?J?\\260\\365<\\343\\354rg\\323\\212\\364S\\324s\\363\\016\\2075\\011V\\003*3\\351Mf\\023\\354\\203\\352\\020\\356\\316#\\3737\\\\.X\\3033;`nb\\013q\\350O#\\360\\250\\016\\203\\252\\000\\177\\320\\337\\217q\\3765\\3501\\347q.\\0063R;c\\3459$\\363K\\353\\363[$\\037P\\203\\352\\317;\\376\\300\\325\\177\\347\\312N\\231\\355H4-P\\343\\026r\\034\\375+\\321C\\216I\\306:\\016y\\247aO\\000`g\\031\\365\\351G\\366\\205N\\310?\\263\\351\\367g\\235\\246\\205\\253#n\\026/\\323\\370\\200#\\3624\\035\\003Wf,l\\245$\\363\\222FO\\353^\\222\\017\\0358\\036\\203\\374\\377\\000\\223H\\255\\271\\271\\310'\\2474\\277\\264jvC\\376\\317\\207vy\\260\\360\\356\\254zYH\\177\\021\\3764\\277\\360\\216\\352\\374\\377\\000\\240\\311\\300\\317Q\\3765\\351j\\315\\324`\\020\\177\\375t\\252X\\263\\036s\\300\\305/\\355\\032\\235\\220\\277\\263\\351\\367g\\232\\177\\3027\\254\\177\\317\\204\\277\\247\\370\\323\\377\\000\\341\\036\\327Lb/\\261\\317\\263vvn\\030\\317\\2563^\\232\\233T\\344\\343\\007'\\2558\\260`\\000#4\\277\\264\\252vC\\376\\317\\247\\335\\236X|9\\253\\250\\311\\261\\220~#\\374i\\017\\207uq\\377\\000.2z\\366\\377\\000\\032\\365\\027\\007\\206\\003\\221\\214\\347\\2754(l\\343#\\362\\377\\000"\\237\\366\\225N\\310_\\331\\364\\373\\263\\314\\177\\341\\034\\325\\317\\374\\270\\311\\372\\177\\215\\003\\303\\232\\301\\031\\373\\014\\230\\372\\217\\361\\257R\\034\\023\\324\\250\\034s\\326\\241\\313(m\\2709\\341}\\270\\243\\373J\\247d?\\354\\372}\\331\\346\\203\\303z\\301\\351a'\\346?\\306\\201\\341\\275`\\234\\013\\0113\\365\\037\\343^\\235\\226\\333\\234\\214\\347\\222=)F{\\361\\333\\245/\\355*\\235\\220\\177g\\323\\356\\3170>\\032\\326A\\301\\260\\227\\364\\377\\000\\032?\\341\\032\\3263\\217\\260K\\353\\324\\177\\215z\\210\\371\\270\\340t\\2476\\303\\223\\220\\024\\016\\007\\255\\037\\332U; \\376\\317\\247\\335\\236Y\\377\\000\\010\\336\\261\\377\\000>\\022\\376\\237\\343G\\374#Z\\307\\374\\370I\\371\\217\\361\\257P\\344\\003\\363p;\\032hb\\253\\236\\01199?\\215\\037\\332U; \\376\\317\\247\\335\\236a\\377\\000\\010\\346\\257\\307\\372\\014\\234\\373\\217\\361\\247\\246\\201\\255\\300\\373\\343\\264\\2366\\037\\304\\254\\001\\376u\\351\\271\\030\\030''\\271\\351F;\\347<rsG\\366\\225N\\310?\\263\\351\\367g\\226\\235\\003U\\\\f\\312Q\\221\\221\\322\\220\\350:\\240\\353g'\\351^\\235&Wn\\356@9\\367\\252\\345rv\\200\\300\\221\\323\\332\\251f5;!\\177g\\323\\356\\3179\\032\\016\\250\\335,\\344?\\210\\240\\350:\\242\\365\\263\\220}q^\\222\\200\\007'\\030#\\256i\\247\\014\\037p#\\323\\276)\\377\\000h\\324\\354\\203\\373>\\237vy\\317\\366\\006\\251\\200~\\307&\\017NG\\370\\323\\217\\207upple\\007\\323\\212\\364=\\354"\\311\\\\\\014\\367\\372\\212\\231\\013\\226\\3119#\\237\\255'\\230\\324\\354\\203\\373>\\237vy\\251\\360\\366\\254\\006M\\214\\270\\365\\342\\223\\376\\021\\375W\\031\\373\\024\\237\\247\\370\\327\\245\\360\\027r\\343\\232\\215\\262\\011P\\300\\003\\307Z?\\264jvA\\375\\237O\\273<\\343\\376\\021\\375W\\376|\\244\\374\\307\\370\\320\\332\\006\\252\\277z\\312A\\371W\\243\\356\\014\\277\\206\\177\\372\\324\\303\\202\\312Fp\\007Pi\\377\\000hT\\354\\203\\373>\\237vy\\320\\320\\265Bp,\\344\\374\\305/\\366\\006\\253\\377\\000>R~\\225\\350c\\005K7\\006\\244-\\261w\\261\\302\\377\\000?\\245\\037\\332\\025; \\372\\205>\\354\\253\\340\\227\\236\\306\\315m\\257ah\\214nJ\\356\\003\\346Rs\\374\\363\\372V\\277\\210t\\311u\\007\\263\\201\\036\\024\\217\\314b\\223\\355\\347\\014r\\023\\003\\260\\035\\377\\000\\023\\311$\\343\\331E\\366\\275Gk\\3623\\330}\\356:V\\362\\351\\211\\270\\332\\302\\316\\221\\207\\311\\012\\307\\033\\200\\3130\\036\\274\\221\\370V5\\037\\277\\314\\367\\334\\350\\244\\275\\313-\\266$M:\\323B\\323\\344\\226B$(\\277 <on\\337\\\\\\361\\\\\\374h\\362\\273\\310\\377\\000\\353\\034\\227s\\352{\\324\\017!\\222\\365U\\313>\\337\\2241n\\001\\317a\\323\\324g\\333\\336\\265l\\241\\033\\334\\267A\\201\\232vpW{\\2639\\276wn\\210\\216\\330\\210\\204\\242H\\220\\225*w6~\\\\\\002\\304~<V\\\\\\020\\235B\\362Rz\\220H\\372\\326\\275\\364Q\\375\\232i\\243o\\336<\\306\\0223\\325v\\241\\377\\000\\032\\267\\244i\\313\\014\\001\\310\\3131\\316\\177\\012\\364)\\273A\\034\\362\\335\\243\\037L\\206#s\\032\\\\\\205\\332\\034\\034:\\356]\\303\\234\\021\\3378\\375+\\255}&\\013\\310\\246\\222\\341T2\\354\\362TF~pz\\235\\335\\000\\353\\307\\370\\326c\\331!\\236v`|\\260\\245\\333o\\\\\\001\\234\\217~\\343\\336\\266\\264\\233\\243sc\\033\\005 \\015\\214\\303\\037\\305\\203\\234}x\\374\\352\\264d\\263\\236\\271\\360\\362\\334\\254\\320\\211\\366\\316\\214\\2425\\224\\226v\\007\\270\\354\\007^kWO\\360\\365\\265\\245\\213\\317\\252\\222\\354\\254\\025\\\\\\267\\030\\347\\345U\\035I\\343\\323\\247\\324\\324\\367\\372\\2640\\336\\034\\037>\\351I\\033S\\375\\\\}\\261\\216\\347\\277<{\\032\\202\\326\\332\\363Y\\273-<\\216Uyf\\354\\007\\240\\364\\376T\\254\\202\\354\\232}Bk\\323\\036\\320\\303h\\3040\\356\\335\\203\\214\\027c\\334\\366\\035\\200\\251\\254tuU\\006Q\\201\\325\\275\\315jGo\\005\\242\\371p\\250\\317r;\\375i\\327N \\266\\311\\343\\212\\253w&\\375\\021I\\343{\\213\\261\\035\\254`\\272\\016\\027\\246\\343\\216\\231\\257\\037\\361W\\206\\245\\213X\\222]5<\\373Y\\276ue\\004`\\344\\344s\\356\\017=\\0163^\\301mr,4\\331o&*\\022e'\\314\\334>@\\010\\316{\\363\\310\\300\\356\\243\\327\\216\\036Y\\345\\276\\271\\232\\342Vb\\314K\\001\\237\\273\\350\\005D\\352:j\\353p\\344S\\321\\236w\\375\\205\\252d\\017\\261\\311\\223\\364\\246\\377\\000b\\3529\\307\\331\\037\\364\\257ARY@'\\2763\\330TS&[\\345$\\014\\001\\232\\347\\372\\364\\357f\\221\\267\\324i\\332\\367g\\005\\375\\215\\250\\377\\000\\317\\253\\376\\224\\343\\241\\352j2l\\344\\037\\225w1\\005\\022\\222\\3126\\216\\347\\212\\234;\\027\\000\\022G\\007u\\017\\035>\\310\\177P\\207vy\\334\\372U\\365\\264/,\\326\\354\\221\\2467\\022G\\034\\342\\251\\327y\\342F\\007E\\272\\004\\215\\343nO\\257\\314+\\203\\256\\3145gV\\034\\3228\\2614U)\\362\\240\\242\\212+\\240\\347\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;}\\000gD\\266\\036\\245\\277\\364#Z\\334c\\000\\344\\2360+3\\303\\240\\377\\000b[\\177\\300\\277\\364#Z\\312\\011nEx5\\337\\357%\\352\\317z\\217\\360\\343\\350\\207\\306\\243\\013\\217\\304\\342\\224\\003\\203\\337&\\204\\015\\300\\340\\001\\316jUM\\243$\\362N\\017\\275`\\331\\261\\003\\222\\010\\3343\\330\\012\\000\\300\\311\\317l\\373S\\376\\371\\316\\334\\372qI"\\220GP8\\007\\361\\240\\007m\\343\\031\\374\\351X\\362@\\034Q\\300U<\\364\\354(\\332\\033\\034\\236i\\000\\212\\0037\\\\{\\372\\323\\260U\\001\\034\\2202y\\342\\225T\\221\\374\\251\\350W'\\236)\\\\d92\\015\\300\\005\\316s\\232\\012\\266H\\344\\217\\255NT\\374\\330lsD\\2016\\217\\233\\240\\310\\350(\\270\\021\\355\\004\\343\\323\\361\\342\\235\\200P\\201\\372w\\240\\206`F0s\\316x\\251\\212\\0367\\036zg\\332\\220\\014+\\225\\030'\\217Jb\\202\\254\\270\\316H\\357S\\270\\332\\203\\327\\353LP\\007 r\\007ZI\\200\\230\\375\\313v8\\353\\216\\224\\340\\301]\\230\\372\\201J\\244r\\016\\001\\307Bj)NyRp}\\372Q\\270\\023+\\021\\3169=\\277\\255>2\\244z\\237Z\\204H@\\334\\307\\000\\364\\353\\305:7\\2121\\307\\335\\317sI\\240\\034\\356\\0008\\311\\374=iw\\226]\\270\\000\\001\\3169\\250\\244@\\347x\\312\\374\\334\\016\\200\\324\\340l\\364\\364\\346\\200\\032\\344\\034\\003\\221\\267\\217\\306\\243\\301\\034\\014\\347\\267=\\352l\\202\\304\\364\\342\\230\\270*0{zP\\200\\012\\223\\214\\023\\232r\\202\\000\\0358\\315\\000\\344\\347\\240\\243p\\012\\016>\\235\\351\\000\\204\\362H$\\037ZP\\344\\203\\203\\323\\261\\246\\347\\203\\206\\340\\373\\322n\\371;\\363\\351L\\011\\027\\234\\223\\234\\217\\347I\\260\\360?\\316)\\250\\337)\\311\\247\\207\\\\\\251\\316s\\324\\372\\322\\001@\\004\\234\\340sJ\\313\\375\\334g\\261<R\\020N\\007\\\\\\037J\\220\\002O\\312\\247\\336\\220\\312\\323)bN\\334\\214~\\002\\2531#\\2221\\3168\\305i\\025\\030P\\000$\\016s\\334UY\\342\\031-\\317?\\255TX\\025\\320+\\026`\\33768\\311\\246\\311\\221\\201\\264\\344u"\\224\\25620A#\\031"\\2238;O\\031<\\373\\326\\202\\035\\031%I\\306=\\263\\374\\251A\\313\\202\\307\\216\\231\\250\\2030a\\216\\247\\257\\267\\265H\\334\\034\\2779\\365\\244\\027\\036\\374\\307\\270\\216\\0019!\\2524\\214\\2020rs\\310\\307\\363\\377\\000=\\351\\350\\312_88\\366\\372R\\216\\035\\262pOc@\\014 \\354\\347\\247\\\\SX\\205\\317S\\203\\322\\200\\340z\\361\\327\\024\\236X\\306\\016\\017\\034\\342\\200\\0301\\222s\\324c&\\242\\231\\267\\310\\250\\207!GoZs(P6\\356+\\327\\216\\364\\221\\015\\331lr\\3075\\321J:\\334\\302\\263\\323\\227\\270\\373I^\\322\\345&U\\004\\241\\007\\004\\365\\307\\275nO\\253D-\\336Xcs#G\\345\\252\\036rI\\311$\\373\\177\\237Z\\313A\\225\\007b\\375M6e\\006\\026i2\\221\\016\\010Q\\202\\336\\302\\256II\\352e\\0318F\\310\\242\\261\\226\\273\\215U\\206\\360\\301\\233\\0038\\003\\267\\343\\375+L\\312\\261\\251E\\371\\244<\\340V}\\262\\310\\216\\322\\214!~\\270^\\336\\202\\254\\225Uc\\264\\021\\236rz\\223\\236\\264\\252M-\\012\\247M\\265q\\213\\024\\262\\313 \\306G\\015\\201\\364\\307\\364\\256\\257K\\210\\311c\\030\\003\\2221\\232\\345\\022\\341\\355.Q\\323\\030\\307L\\360k\\263\\322'\\006\\334:\\262ms\\2203\\367k\\272\\213\\346\\202l\\347\\252\\271d\\322\\033\\255*Y\\351\\246%\\307\\2317\\3109\\301\\347\\251\\377\\000>\\265sI\\203\\354:d2\\236\\024\\235\\355\\376\\350\\344\\376\\213Y\\272\\271\\373n\\263gnyT\\344\\343\\334\\217\\360\\255mr\\341m4I\\200\\030-\\027\\224\\203\\375\\3568\\3747V\\306]\\016;O\\2129\\032[\\273\\211vC\\031\\313\\310A<\\223\\216\\336\\346\\245{\\373\\302.m\\354nDp\\211r\\207$1\\300\\366\\365\\343\\203[66\\022G\\242K\\014H\\014\\322\\304\\312\\003p2A\\002\\271\\251\\222m:\\351\\222\\352 \\2308)#p\\353\\217\\257<\\001\\320\\376]\\262\\250\\344\\2666\\244\\242\\336\\247U\\240\\336\\274\\362\\213k\\271srrB\\3549\\300\\031$\\340\\000:\\217\\362i|A>\\345hP\\340w"\\252xe/\\274\\307\\324\\031-\\305\\274\\371\\014w|\\307\\034\\002\\270\\030\\3529\\347\\377\\000\\257[[\\273!\\346\\333\\200\\313\\270\\363W\\026\\371U\\314\\344\\227;\\261W\\304\\332\\204\\314 \\2601\\030\\025T4\\203x>a\\354N:t\\376\\276\\225\\235i\\036\\350\\237>\\231\\250ond\\3245K\\213\\231$Y\\013\\276\\003\\254a\\001\\003\\201\\362\\216\\235*\\305\\271\\314n\\242\\270\\353J\\354\\322\\222\\324\\245\\023\\235\\362\\246\\0067\\221\\317\\261<\\322\\314\\240\\000\\275\\301\\347#\\250\\251Z,\\334\\317\\216\\330?\\245D\\356J\\215\\255\\270\\343\\327\\326\\271\\245\\361\\350vC\\341"i\\012(\\005\\001\\007\\240\\315I\\023F\\312N\\320\\200s\\203\\374\\251\\201X\\253g\\005G^qLF\\336\\207\\007\\234\\360?\\235+\\024P\\327\\344\\007D\\271\\030\\012p\\247\\036\\2778\\256\\036\\273\\257\\020\\001\\375\\2019'\\0146\\200\\007\\177\\231z\\376U\\302\\327\\251\\201\\376\\033\\365\\377\\000#\\312\\307\\377\\000\\021z\\177\\230QE\\025\\332q\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\035\\367\\2078\\320-\\211\\351\\227\\357\\376\\321\\255@\\003\\021\\327=\\177\\245exhn\\321-\\367`\\017\\233\\377\\000B5\\260S\\030\\333\\327\\256O\\245|\\375\\177\\342\\313\\325\\236\\375\\037\\341\\307\\321\\002\\215\\244\\201\\323\\201\\221S\\260\\014\\271\\312\\221\\217Nj">PCg'\\257\\343R'\\011\\216\\240c\\030\\025\\2135\\031\\214\\020OOoJS\\203\\036\\323\\334t\\2438\\340\\365\\031\\306;S$a\\200\\024c\\360\\351@\\301A\\0308?J\\221>\\357\\012s\\326\\241R\\3001n\\325/\\007\\030\\353\\216\\3640\\036\\031W\\237_Q\\322\\234\\250\\006w\\016@\\365\\250\\3161\\226\\317\\327\\3374\\273\\360\\254s\\223\\217\\351R\\003]\\370\\345G\\003\\326\\241\\217s\\250^\\000\\037\\245Jr\\353\\270\\366\\352\\015K\\030\\0025\\\\\\361\\324S\\275\\220\\011\\034x\\033\\213\\022\\277J\\237\\234t\\031\\340\\012PF\\354\\364\\374(\\310g;\\216G\\257\\247\\371\\342\\241\\260\\030\\247q\\001\\211\\335\\351H\\333G\\251>\\376\\264\\371\\016\\006U~L\\375i\\201w\\256\\354\\201\\307\\\\S\\002)I1\\202\\016\\033\\326\\221N#%\\307Q\\305J\\360\\206\\005I\\307l\\212k\\240\\012\\243-\\307\\035)\\246\\200\\221U\\012\\371D\\026\\033z\\323C\\243\\262\\257\\226v\\372\\236\\335(\\211dG\\007;\\220\\343<\\236*\\301l\\022P\\014\\373\\201\\323\\353R\\300i;H\\343\\201\\323\\372Ry\\200\\203\\300&\\233)\\0148\\0041\\0038\\342\\240'i8 /LP\\220\\023\\014\\226\\003\\234\\373\\236\\224E\\220\\274\\362I\\317Z\\024\\364\\007&\\244'h\\3169\\002\\200\\016\\203\\236\\202\\243v\\343\\000r*L\\203\\203\\202I\\343\\236\\324\\326M\\314\\017\\003\\212\\020\\021\\202s\\236\\346\\220d\\347\\256i\\370l\\020\\012\\343\\337\\265"\\250*3\\237\\362i\\200\\305\\005\\\\\\034\\344T\\340\\020\\006@\\300\\355\\351M\\347\\200\\001\\374\\252P:\\037jM\\200\\001\\363`\\343\\247\\245J\\204\\343\\223\\220;\\346\\230\\253\\363e\\200\\343\\241\\305J\\240m\\300\\007\\037\\312\\245\\200\\234\\357 \\036\\002\\323\\031\\266\\362\\312I\\372T\\222\\002\\027\\201\\206\\307\\036\\202\\242\\311.z\\021\\237\\342\\037\\347\\322\\200+J\\243\\315\\347\\364\\250\\244\\350N8'?\\347\\374\\367\\253\\014\\340e\\224n"\\241\\227\\001\\203\\221\\271O?\\216:U\\240)\\264\\205dV \\216rG\\265L\\\\\\024C\\216\\017<\\363\\317\\365\\357Q\\262u'fG\\012?\\2559\\034\\206c\\3239=pG\\255h\\304>97\\003\\201\\234\\217_\\345Ow\\302\\206\\025Z"\\305q\\234\\014\\324\\310\\243\\313\\303\\021\\236\\231\\374\\3515`\\020\\006b88\\024\\025<s\\214\\217^\\377\\000\\347\\024\\365\\000\\257<\\216:\\016\\243\\212x\\033\\217\\353\\307\\350i\\\\evS\\206\\300\\007\\257CQ\\304V\\031\\274\\271\\016W=j\\333g\\007\\234\\014SY\\006Ha\\2707\\267\\025p\\251\\312D\\341\\314N6\\220\\011`\\020rMW\\226s4\\252v\\342$? =\\375\\352#l\\001$\\201\\2203O\\031\\035N\\006\\0068\\252\\224\\325\\264"4\\335\\365\\023q\\004\\356\\353F~}\\243\\337\\377\\000\\327A\\000\\261$\\361\\237\\241\\244Q\\265\\206psY\\232\\214t3\\334\\305\\017_^{WY\\245\\331\\223\\026\\\\\\340.\\006>\\225\\316\\351\\013\\347]\\311/^@\\025\\331\\333\\256\\333G\\003\\257O\\307\\025\\354R\\217,\\024O:\\254\\257&\\314\\3151<\\315m\\246\\004\\355\\014O$\\2361\\201\\374\\352_\\026\\336\\203\\025\\264\\000\\035\\230\\336\\336\\374\\221\\372\\020\\337\\235G\\245\\263}\\254*c\\346\\333\\237\\363\\370S\\265\\230\\204\\272\\354v\\303\\225H\\227\\237r3\\374\\332\\264\\276\\206}J\\266\\332\\315\\315\\267\\226\\012\\226^\\344\\367\\025\\265\\006\\241\\014\\323\\213\\221\\014b}\\233<\\302\\243p^\\270\\317\\\\f\\234`R\\271\\003\\003\\332\\241k5c\\222\\250~\\242\\215P\\264b\\313w\\035\\264{"\\210\\004\\034\\204A\\265Fy5\\310kw/4\\336c\\246\\024\\366\\007\\255u\\377\\000c>Y\\034\\005>\\225\\307\\353q\\220\\261\\205\\031\\371\\261\\372\\322m\\255J\\212]\\014\\330\\201\\010\\001\\353\\216~\\265~\\314|\\244\\237\\342l\\012\\242\\274\\3765\\241\\003\\0174F\\275\\021\\177Z\\340\\231\\255=\\310\\230\\223uq\\203\\2001\\232\\257\\202N\\0062\\017Q\\376~\\265 %\\357dP\\240\\357\\317_Q\\376MDA_\\275\\303\\177\\010'\\255c?\\210\\352\\246\\357\\020Q\\206\\351\\300\\317\\003\\326\\230\\011P>^z\\347\\037^\\324\\214\\377\\0007\\241\\310\\003\\234\\320\\010#<\\375?\\255+\\026gk\\271\\032\\035\\340\\354v{\\340\\356Z\\341\\353\\275\\361\\007\\374\\213\\367\\000g\\356\\246r?\\333Z\\340\\253\\324\\300\\377\\000\\015\\372\\376\\210\\362\\261\\377\\000\\304^\\237\\346\\024QEv\\234AE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\007y\\341\\302\\253\\241\\333\\022N~c\\377\\000\\217\\032\\331\\214\\214s\\223\\237\\322\\261<<q\\241[s\\375\\377\\000\\375\\010\\326\\344k\\270\\344\\014`v\\365\\257\\237\\257\\374Iz\\263\\337\\241\\3748\\372!\\340\\003\\264`\\220\\275\\351P\\235\\247\\234z\\234\\3207)\\350}?Zp\\017\\374 t\\315`l3<n \\002i\\256Wp \\356\\007\\246;\\3765+G\\220\\273\\272u\\305\\005F\\006q\\327\\214Qp R0\\0068\\340\\232y%\\224\\001\\232U@\\\\\\355\\003\\353\\351R\\200\\000S\\263\\236\\306\\206\\300\\201\\267\\347<c=\\2004\\3249p\\017\\004\\034m\\253\\004\\226\\004\\361\\236\\235j\\017,\\271\\340\\036\\235sM0$N\\031\\270\\343\\27156\\3009\\350;S!P\\0008\\353\\320\\036\\365#>:\\363\\237J\\207\\270\\022\\201\\236\\2143\\237ZB\\340\\215\\330\\343\\247\\326\\221\\203\\020\\240\\361\\323#\\333\\024\\240\\251\\\\\\0360r*@Lo\\332\\303;q\\320\\367\\245\\033\\263\\215\\271\\355\\212\\024\\355P\\024\\223\\317OJG\\311!\\216\\356:\\346\\230\\014g\\340u>\\370\\374\\251\\312\\274d\\343"\\206\\371y+\\327\\2574(=A\\3104\\000\\340\\3018\\332q\\327#\\237\\302\\234Ht\\001\\030\\014\\321\\260\\022\\2708\\242U\\013\\310nz\\232@FT\\001\\203\\311\\365\\246a@%\\217\\347NP\\340\\227$\\001\\375\\321Mq\\221\\214s\\353\\355T\\200g\\234\\006p0=\\177\\372\\324\\377\\0003s\\206\\353\\222\\000\\307\\363\\252\\343\\207\\310\\3502zU\\210\\327\\012@\\004\\020i\\264\\200\\221NA\\003\\220?\\012\\012\\356\\306G'\\265&\\320\\030`\\362O5(\\002\\240\\010\\202\\021\\301\\300\\367\\240\\015\\241Cg\\351R\\270\\331\\234\\237\\247\\2753\\004\\260,2=E\\027\\001\\020\\002\\330\\317^\\231\\251P(\\034\\343\\000r=\\252=\\270\\311\\030\\0079\\034S\\300\\332\\275\\017?\\255\\014\\011\\027\\201\\216\\000\\245S\\264\\201\\2203\\322\\231\\234\\0208\\347\\236i\\245\\362\\275\\261\\353H\\007\\360A\\306\\011\\376|\\324R\\226\\007\\031\\301\\317\\\\\\320\\256\\334\\016\\375\\270\\3155\\313m\\316H\\307\\353M \\000\\216\\204\\000F\\177Z\\211\\367\\2701\\234\\002\\007\\004\\017jvO'\\223\\317~)8'\\240\\310\\3655H\\012\\362\\022>e\\034\\343o\\246\\177\\3175Y\\331\\313\\036\\203\\034\\014\\343\\2778\\253S\\260\\344`\\221\\2368\\377\\000>\\225V@\\276^Nr~\\350\\003\\365\\255"&\\0106\\344\\262\\344t\\003\\247\\341V\\020\\214\\347\\031c\\352*(\\267H\\210Kd\\367\\307n\\325a\\006\\0063\\306rq\\376\\177\\316(\\223\\004<d\\221\\267\\005\\2621\\307\\347OU\\331\\234\\221\\352y\\353\\326\\230H\\033Xd\\201\\317\\035\\351\\353\\206\\371\\210\\301\\307J\\315\\214R\\006\\321\\202y\\364\\244<\\214\\377\\000\\017\\257\\245<\\361\\357\\337\\247J\\211\\270\\311\\035\\006F?\\302\\20426\\004\\220\\333\\2609?Jg\\314d\\307\\342*R\\011\\\\\\236:\\377\\000\\237\\322\\243\\333\\216\\2141\\216\\265HG%m\\257_\\033\\311d\\220oA\\222\\321\\0366\\373\\016\\365\\323Z]\\305yj%\\214\\365\\003\\203\\330\\373\\373\\326n\\257\\241\\245\\306\\353\\233U\\333p\\016J\\347\\207\\366\\372\\3266\\237\\250M\\247\\334\\264\\245H\\347l\\261\\225\\306\\177\\372\\342\\273\\345\\010V\\20755f\\2168\\316t\\245\\313=Q\\333h\\014\\336~\\334|\\273\\2075\\330\\223\\267M\\235\\2757\\177*\\340t\\215^\\331\\335\\314\\014\\333r0Xc?\\347\\372W^\\267E\\264\\353\\205-\\220T\\220\\177\\012\\355L\\346d\\372:\\005\\277\\347\\031\\000\\177\\350?\\375z\\255l\\355s\\255\\264\\216w\\035\\277\\310\\340Q\\243Le\\325\\356[\\241TU\\003\\360\\002\\215(\\252kSFX\\026RG\\327\\004\\323\\021\\321L\\236\\\\*\\243\\277Z\\201P\\261\\340U\\351Yr)\\201\\2219\\253\\261\\003\\036!\\025\\266OS\\\\=\\370\\337p\\221\\214}\\375\\340\\236\\301O\\377\\000^\\272\\373\\333\\320\\027\\034\\023\\330W),\\036^\\240['\\204\\001H=\\362s\\374\\205s\\342g\\313M\\263j\\021\\274\\2220\\024\\210\\256\\0361\\222\\021\\331T\\236\\340\\032\\271j\\245\\025\\234\\375\\347\\244\\236\\335~\\335"3\\251\\014\\025\\227\\035\\270\\301\\376B\\221\\004\\261J#n@\\350k\\217\\233\\23275\\345\\345\\220\\310\\243a;;\\002p\\347\\372\\325R\\300\\311\\265\\206\\342\\247\\201\\232\\327d\\005T\\203\\223\\353YR)\\363\\230(\\0349^~\\265\\022\\327Sj~\\356\\203\\031_~\\012\\3659\\311\\307Z6\\205\\316AS\\222H\\364\\245*X\\223\\320\\361\\267\\232@\\233\\210\\005\\371\\317\\347RjV\\361\\010#\\303wD\\360\\016\\336\\337\\355\\255p\\025\\350^!\\213o\\206.I\\317\\033?-\\353^{^\\226_\\3747\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\277\\016\\177\\310\\022\\330g?{\\217m\\306\\267b pH\\036\\225\\203\\341\\334\\035\\016\\337\\201\\374\\\\\\377\\000\\300\\215mD\\314\\330\\007\\006\\276\\177\\021\\374Iz\\263\\337\\241\\3748\\372"\\300*\\304\\000\\331\\031\\317<S\\207\\313\\310\\344\\236\\377\\000\\347\\351B\\234\\234\\220q\\327\\236\\264\\361\\234\\003\\217\\250\\025\\316\\315\\206\\216H\\340\\343\\267\\2657hV\\343\\030\\251J\\200I\\300\\037\\215&\\335\\303\\2468\\007\\236\\264\\256\\003\\025~c\\216\\375\\001\\251q\\301\\310\\246\\020\\333\\263\\327\\257\\343N#\\240=\\017\\250\\244\\300@\\274\\363\\214{zSPm\\003\\3278\\031\\364\\305\\014\\300d\\001\\301\\246\\256\\007\\314y\\367\\365\\377\\0008\\2461\\340\\234q\\370c\\326\\236\\250\\001?)=\\377\\000\\032b? \\022\\271\\307'=i\\356\\345\\027\\012rOA\\217\\326\\220\\211YNq\\2029\\342\\233\\200\\244\\234\\020\\177\\245"\\223\\265\\031\\217#\\256)Y\\376b:\\016\\234\\364\\244\\003db\\000\\000\\216}M5\\011*[#>\\336\\265\\034\\304\\271\\300 \\021\\353O\\014\\002c\\256)\\364\\002O\\367\\277\\032\\025\\310`9\\367\\246g$c\\276N\\015+\\014\\266\\007L\\344\\232,\\004\\244\\365\\340c\\024\\322wdg?Jh\\317C\\374\\351\\274\\2223\\237\\245+\\000\\340q\\306rh\\306\\376H\\351A\\003\\214\\034\\361\\23227\\3438=i\\200\\325D\\\\\\260\\344\\366'\\265.\\374\\200\\335\\305.B\\365\\034z\\366\\246\\2667t\\344\\361\\232\\000z6\\342\\254\\017jp\\347\\216\\240z\\212li\\261Ilz\\322\\203\\301\\003\\036\\346\\220\\017\\030n\\017Zi9\\347\\003\\351M\\033\\227\\257\\\\w\\244\\035F}(\\260\\023\\216y=\\273f\\230X\\017\\2550\\261\\003\\0352i\\240\\016q\\306h\\260\\022\\027\\366\\300\\365\\244\\334\\017\\031\\374\\015G\\234\\036\\303\\332\\203\\300\\351\\311\\364\\351N\\300;\\247^G\\000`\\322\\311\\323\\345\\034S\\010\\030\\003\\030\\031\\024\\222\\002G\\313\\311>\\375\\005\\000\\005\\207\\\\\\234\\226\\300\\367\\246\\343\\345=\\270\\353L\\306Skg\\035i\\373Y\\343$u\\350\\017\\364\\246\\003\\020\\226`\\033'>\\203\\275D\\320\\347*B\\364\\343\\324}j\\322\\220\\033\\337\\353K\\267s\\251 d\\366\\007\\245;\\330\\012`(\\001\\006~\\240\\375\\177\\306\\245(\\003\\015\\271\\007\\327\\036\\264\\342\\200\\222\\000\\330\\340\\364\\306\\007\\353H\\024\\237\\227\\236\\204\\214\\236\\017\\250\\375)\\334\\004V\\344\\203\\221\\203\\334\\323\\311\\316v\\2022y\\347\\363\\243\\345O\\224\\2022:\\376\\024\\340\\274\\362\\007<\\217J@*\\343`\\316\\017l\\365\\240/\\033\\206*ERA\\347\\034c$S\\014yf=8\\354\\177\\017\\360\\251\\002>w\\036\\376\\230\\030\\250\\377\\000\\204\\361\\301\\351\\317J\\260r\\2401\\004g\\221\\305@z\\020G~j\\220\\014\\015\\273\\220O\\312x=+\\234\\3618\\201|\\242\\255\\266\\354\\360q\\335\\016{\\364\\353\\374\\353\\244o\\223;s\\317j\\346\\255,\\345\\325u\\371%\\2362a\\215\\210\\303\\017\\310\\021\\372\\377\\000\\372\\353\\253\\012\\255.n\\306\\025\\365\\217/s2\\322\\342]>\\343x\\217|n\\234!8\\317\\036\\274\\343\\237J\\352l\\365[i\\241+\\015\\323\\024\\3071\\034\\251\\004\\216\\207\\377\\000\\255\\221Y7\\366\\017\\346a\\225\\227?:\\2021\\201\\350=\\277\\303\\362\\315\\226\\330#\\355m\\214\\300\\366\\357^\\257\\251\\346\\335\\305\\371\\035\\325\\266\\261.\\237\\250M+\\302W~6\\361\\301\\036\\2655\\306\\244?\\264>\\323n\\333I\\371\\260GL\\234\\377\\000Z\\344,o\\356`\\362\\355\\240O7z\\355\\216!\\030onBa\\211\\343\\277\\327\\336\\267\\244\\271\\270:\\002\\353/\\014Q\\037\\265}\\207\\3119 \\220\\244\\234g\\346\\037w\\271<\\324\\271X\\264\\323:\\017\\370J\\2320\\026pCz\\216ME'\\211\\274\\337\\270$\\306:\\271\\332+\\217\\271\\325\\235\\337tD@\\274aS\\007\\365 \\232\\316\\271\\324ZL\\371\\215\\221\\351\\236\\277\\205=\\305\\314\\221\\324j>'\\020d3\\0370s\\362\\366\\374\\377\\000\\255P\\322<B\\367\\2277>z\\262\\240\\\\\\304\\240\\347\\007\\323>\\2479\\315s\\276E\\345\\344\\261\\306cd\\014\\240\\251p@\\333\\353\\364\\343\\025\\277me\\035\\264*\\221\\203\\200rO\\251\\254k\\250\\270\\362\\276\\243\\204\\344\\245\\314\\213l\\322;y\\322\\037\\230\\367\\253+3l\\001\\276l\\372\\322O\\030X\\343Q\\327\\034\\325\\203\\002\\305\\002\\264\\207\\003nMr6\\254n\\223\\271P\\311">\\345r\\007\\241\\351I2\\027\\224\\273(R\\330#\\037\\347\\255Y\\216/\\231\\035\\306\\011\\354N6\\372g\\336\\241\\272o\\336\\0003\\376\\025\\224\\247wdoN\\233Z\\262\\251]\\316r\\000\\307\\031\\307?\\347\\374)c\\030\\227%r:\\363\\365\\243\\346\\337\\362\\365'\\034\\234\\012\\003\\021\\306\\334\\220s\\3654\\215H\\274I \\177\\015]\\343\\375\\217\\375\\014W\\235W\\241\\370\\224\\005\\360\\345\\320\\311'\\344\\316G\\373B\\274\\362\\275,\\277\\370O\\327\\364G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\274\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\357\\015\\014\\350v\\3351\\363\\177\\350F\\267"!\\000\\035sX^\\032 hV\\374\\343\\226\\377\\000\\320\\215m+m\\301<\\366\\305|\\366#\\370\\222\\365g\\277C\\370q\\364E\\200\\340g8\\351N\\022`u\\307\\037\\225U\\363\\010RY\\206\\011\\374:\\324\\371,\\000\\372~\\025\\203F\\304\\233\\271\\030\\347\\003\\351\\223G\\\\\\360x\\340SO'\\220\\007\\341\\355Lf*T\\023\\310\\307\\322\\225\\200y#y\\307\\003\\277\\275!\\313\\0363\\203\\332\\230H-\\273\\275(\\366=GJv\\030\\340\\006y\\034{\\320\\310=;\\234\\016\\224\\370\\372\\363\\234\\036\\177\\032q\\003v@\\332\\304w<\\012W\\021\\022)\\003;Ic\\316)pJ)\\340\\220{\\324\\216\\277/\\004n\\317\\006\\253\\000\\340\\2608$q\\234\\365\\241j\\004\\343$r@\\344\\322\\002\\273\\217\\\\\\216\\265\\023I\\263*9\\366\\307J|\\214\\011\\000\\034\\237N\\224X\\006\\354?y\\272\\372\\016qNF\\0161\\216MFH#h\\376\\037\\326\\214\\014\\015\\275\\0161LD\\241\\200\\224\\203\\216\\0074\\365\\371r@\\353L\\003q;\\207\\266E9\\024\\005'\\030\\036\\207\\322\\220\\013\\2779=\\000\\240\\020W"\\207C\\264 \\317\\2550\\215\\254\\001\\007\\030\\305\\000=\\210\\3654\\3020\\344\\200rGJbm`J\\261\\012*O/,A=zQ\\260\\002\\222\\331\\015\\200G\\030\\241\\321\\231\\301\\007\\012?Z\\000\\301\\000\\360(\\007k\\002wc\\034\\012\\000\\227pS\\3637_\\312\\221\\030\\022\\012\\364\\353\\232\\204\\270lq\\317\\265=x\\217\\320\\223J\\300) \\375\\323\\327\\200)\\333G\\\\\\324`\\005\\372\\323\\210\\300'<b\\200\\034X\\343\\247\\261\\244`\\017Q\\370S\\261\\221\\3079\\355Lo\\227 \\234P18\\031\\334I\\310\\246\\257@\\030\\216E\\014\\304\\214\\200H\\006\\2246\\343LB\\225R\\334\\037znN\\354s\\3374\\322T6A\\350;S<\\302\\240\\206\\004\\372\\016\\231\\242\\303\\270\\363\\316I\\357\\203\\365\\024\\344e\\031\\034\\221\\301\\007\\2550:\\236J1\\301\\307\\035\\352\\302\\2041\\227U\\333\\221\\365\\241\\200\\324\\000\\364\\000t\\031'\\257\\326\\246D\\347 \\2002s\\221\\237\\363\\326\\2300\\023\\034sS\\250]\\243 q\\306}=\\352\\033\\031\\002\\256\\346\\007\\345\\003\\034\\021L\\030\\022\\205\\000\\026\\307\\347\\326\\2348\\\\d\\372\\014\\214R\\004\\016I\\300\\355\\371u\\307\\363\\252\\020\\302K\\273d}\\3409\\030\\372\\361DaFr\\177\\012s\\014;\\223\\337\\202\\010\\376\\224\\204\\215\\375x#\\271\\240\\007\\014\\234\\036\\207\\374\\377\\000\\215\\007z\\225-\\201\\234\\366\\247\\362Fx\\307\\003\\326\\207\\000\\226\\030`\\247\\356\\347\\265 \\032S\\235\\303\\277|g\\025\\001\\035:\\216q\\323\\336\\245b\\347\\220q\\203\\305G\\264\\261\\311\\000\\200zu\\305R\\030\\326 /*\\017=)\\366\\227\\037f\\272\\216h\\366\\006S\\321\\323pa\\350Gz\\037 r9\\364\\367\\246\\250\\012G\\\\\\377\\000\\236\\325Q\\223\\213\\272&QMY\\235\\012\\\\\\332j\\261\\264ok\\024\\276_\\022&74~\\353\\237\\274>\\236\\331\\305s\\236 \\323t\\330bh\\341*gs\\201\\203\\312\\257|\\216\\243\\323\\007\\276pN\\015fd\\356`X\\344\\023\\327\\232\\177%\\267L\\\\\\223\\311-\\316k\\326\\366\\316\\336g\\230\\326\\272\\031\\321\\351\\220\\034\\211\\241,\\007\\000G9]\\374u;\\225\\275\\270\\004t\\367\\253\\267\\267\\027\\027:%\\276\\224\\236k,w-)\\226|\\006g *\\200\\024\\221\\2007rNNs\\201W\\225\\255\\233\\003*\\017c\\264\\323.!\\205P\\225\\232-\\270\\345Kb\\241V\\327\\336\\037\\263\\323C%<3<\\262\\200<\\367_d\\333\\237\\347Vf\\320[MdG\\201D\\214\\003\\003\\301a]\\015\\267\\210g\\213K[t\\010\\363\\257\\002w\\005\\230\\2578\\316x\\004{u\\300\\3105\\213q\\250Egt\\262\\335\\310\\346I\\016\\347*2G\\277\\371\\364\\355[:\\313h\\352\\305\\032k\\251\\012\\302\\320\\242\\311,\\033p>g\\\\\\267\\036\\377\\000\\375l\\365\\253q\\306\\256\\350\\007#\\255m\\333\\352~\\035kl}\\271\\030c\\253\\002\\244~\\030\\256r\\353R\\263\\203R\\222+v\\3148'\\314\\013\\205\\335\\376\\177\\017\\324\\236^iM\\273\\255N\\211B\\021WL\\322\\220\\306$\\016\\340\\2208\\000\\014\\344\\366\\0251\\205\\344q$\\270\\3341\\210\\307 g\\245Ik\\000\\214\\244\\322a\\234\\250!\\261\\214\\003\\351\\350i\\314\\025\\237 \\340\\001\\234c\\374\\373\\327\\024\\352\\364\\211\\321\\010ud\\022+m\\334\\244z\\340\\363\\307\\275P\\237\\370I\\037/\\277j\\320\\334K>A<\\361\\317\\362\\252\\327\\0309#\\031\\364<\\342\\246\\016\\314\\266Q\\301`1\\320\\347\\220i\\361\\250\\363B\\225\\372v\\251\\000+\\220\\277xr\\010\\357OB\\026\\343$pq\\234V\\215\\212\\305\\037\\024\\177\\310\\265rG\\012v\\343=~\\372\\327\\234W\\245x\\234c\\302\\267g$\\214\\246\\011\\357\\363\\256k\\315k\\324\\313\\277\\204\\375\\177\\310\\362\\263\\017\\342\\257O\\363\\012(\\242\\273\\316\\020\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\272\\360\\350\\316\\211o\\300?{\\360\\371\\215l)\\301\\371\\217S\\310\\037\\347\\336\\262<8\\177\\342Kl3\\317\\314q\\353\\363\\032\\330\\012F\\334\\340\\034\\365\\374k\\300\\257\\374Iz\\263\\337\\243\\3748\\372!\\356I\\3063\\215\\331\\344\\342\\254\\037\\231@\\035N3\\364\\252\\344ns\\351\\331{u\\2531\\355$\\014\\014\\001\\327\\257\\025\\316\\315\\207\\025 }\\356x\\034\\032`\\\\\\214\\236;\\361Sc\\035z\\365<S\\012\\222T\\014\\034\\366\\0257\\0020\\270a\\307\\031\\025"&\\006X\\001B\\215\\271\\310\\371\\201\\351C\\276\\324\\306\\177\\032\\000Pv\\200\\000\\031\\034\\217jp\\003\\030#'9$\\324\\001\\271\\364\\031\\251T\\356\\343''\\365\\241\\240\\011r\\252s\\221\\357\\351Q\\003\\301Vo\\233\\034\\221\\326\\236J\\225*\\254\\003\\237^\\325\\020\\302)\\311\\317\\365\\241\\001\\033>\\331\\200'y\\307?\\235\\030\\221\\3347Nx\\252\\305\\211\\221\\244\\3439\\350{{U\\325+"\\361\\313\\021\\376sV\\364\\020.\\314m\\007'9\\024\\357\\356*\\340\\036\\270\\305DT\\243g85.\\342y\\003\\006\\244\\011\\000\\334H$\\3434`\\347\\033O\\024\\001\\362r0M8\\002x$R\\030\\3269L\\206\\000\\375zS0[#$\\216\\234\\323\\230nc\\273\\267\\037Z\\013lnNH\\353\\216h\\020F\\212\\252p?\\012\\223\\035\\363\\307J\\215d\\343\\000`{S\\201\\310\\355\\307\\255\\014\\005d!\\301\\307ZN\\243\\333\\326\\234~\\360\\347\\267\\245 !\\234\\372\\016)\\000\\236^\\325\\351\\223\\237\\312\\234\\010<\\364\\2470\\344q\\364\\244\\034\\374\\247\\217\\360\\242\\340\\033G\\343M\\333\\327\\334S\\311\\300\\355\\322\\233\\223\\370\\320\\000\\006q\\202})\\304\\361\\302\\361\\3074\\335\\374\\375z\\232^\\204\\343\\256:\\177*\\000\\214\\374\\243\\345\\030\\003\\322\\235\\234\\257\\004g\\035qO\\015\\202v\\343#\\261\\246}\\347\\343\\2469\\346\\200#\\037.3\\203\\236\\207\\332\\230\\352ZA\\362\\200\\331\\253\\005\\001\\214\\022\\006q\\214\\212\\215P\\226\\004c\\270\\315;\\214rD\\011\\034\\360\\017#\\336\\245\\001P\\340t\\317\\347\\3054\\014\\215\\307\\234\\366\\035jbG\\251\\343\\267\\\\T\\260\\000H=\\011\\300\\353\\305J\\270\\333\\324\\343\\337\\322\\2402\\000\\300\\203\\330\\200\\011\\305L\\2101\\313\\202\\303\\234`\\012\\2262\\031B\\254[\\2018\\307Q\\330\\361\\315F\\245be\\015\\236\\371\\004\\366\\253D\\006\\214\\200w\\022x"\\242(\\025\\272\\177\\026?\\317\\345M0 _\\230\\003\\264r\\006\\001\\353Ml\\207Q\\217\\271\\306zS\\3353\\210\\3279=\\177\\300~t\\2618u\\030\\307\\\\\\177\\237\\312\\250C\\202\\237\\342R\\243\\326\\237\\3161\\200F9\\372\\324j\\270\\030\\343\\257q\\322\\245`1\\214\\016{v\\251\\002\\026]\\243\\004.\\007\\2454\\000\\273s\\316F\\017\\034S\\336B9 z\\340z\\322\\003\\307=:\\344\\232\\240\\030\\3708 \\362I\\343=j+\\207\\021\\333\\310\\354q\\201\\324z\\236\\237\\255K&L\\237.O\\245d\\370\\206F\\217J!\\0167H\\240\\375:\\343\\364\\255)G\\232i\\021R\\\\\\260l\\003\\307\\331\\2238\\343\\203M\\226\\346+t\\014\\362\\014\\021\\331\\177\\256+\\230\\022H\\027h\\225\\300\\364\\014qH\\010\\035@5\\353{\\016\\354\\363=\\257\\221\\3206\\255f\\212A\\231\\233=\\225K\\177<\\012\\213\\373j\\330\\014G\\275q\\353\\020\\347\\365\\254@\\024\\267J\\177\\313\\330\\361\\364\\252\\3661'\\3322\\364\\332\\304\\357\\237(\\355\\031\\341\\210\\301\\377\\000?\\215gJ\\317;\\227\\222Fv\\365c\\222is\\316\\005\\00265q\\214c\\2612\\223\\226\\344\\266\\3731\\223\\367\\275\\352\\345\\234bMN\\324>\\335\\236r\\344\\036A\\371\\207Z\\242\\250A\\316Eh\\351h\\323\\3536\\020\\3062\\315:\\177:SZ;\\016\\033\\253\\235\\333\\252?`O~s\\376zU\\031\\2123m^\\016y#\\232\\276\\312\\350\\345H\\351\\324b\\250LB\\271*\\244\\343\\247\\031\\257\\002;\\236\\311\\023dr}q\\221\\334\\372\\3242\\005\\330\\314H<\\014\\202}\\252l\\356\\034\\343\\214\\0168\\347\\374\\346\\242\\227\\036K`\\003\\221\\21685\\242\\006S,W\\2000\\030\\367\\376T\\350\\276bN>\\\\\\361Q\\341\\231x\\371\\207\\246*uR\\331X\\360\\253\\234\\363\\316NO\\377\\000^\\264d\\224\\374N\\373\\2745w\\236\\237&=\\276u\\2578\\257F\\361/\\036\\030\\272\\007\\257\\311\\327\\375\\345\\2579\\257S.\\376\\023\\365\\377\\000#\\312\\314?\\212\\275?\\314(\\242\\212\\3578B\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\016\\373\\303k\\235\\002\\003\\214\\223\\270\\177\\343\\306\\265\\324n\\013\\214`\\372VW\\206\\217\\374S\\326\\377\\000\\360.\\177\\340F\\265\\017\\337Q\\234\\000?*\\371\\372\\337\\305\\227\\253=\\372?\\303\\217\\242\\037\\234\\217\\233\\003\\035\\007\\255K\\007\\030<d~U\\022\\020A+\\333\\232r\\361\\236:\\0021X\\263Rgu\\034\\016s\\300\\305(ls\\236\\015BpI<\\343\\035)C\\022\\300\\266q\\266\\246\\303$\\311\\335\\217_\\320\\322\\266\\0168\\034t\\364\\246)\\014\\330n\\347\\212q\\302\\216\\016E \\020)\\\\dpx\\367\\241\\230\\025\\367\\007\\257j\\030\\222A \\220i\\252v\\214\\034\\023\\326\\230\\012\\373z\\257^\\340s\\212\\202a\\265r\\305\\263\\3375!\\005X\\270\\031?\\375zVM\\331\\014A=y\\344SZ\\001I2\\331\\340\\376\\037\\215[\\266\\0001\\007\\377\\000\\327L\\362J\\365\\003\\003\\221OT\\330\\025\\263\\317aM\\273\\210\\225\\206\\322=\\271\\372\\323T\\200\\241W9\\035\\315?wO\\322\\243\\010\\330,G\\275H\\022\\241\\344\\202N\\007zE$\\263n9\\301\\340Q\\214\\002W\\007\\330P\\240\\206\\307\\037Z@\\0147>\\007\\003\\2514\\3606\\234w4\\004\\307\\2474\\343\\327\\320w\\315\\027\\002&PN\\010\\000\\367\\247\\223\\205-\\327\\322\\214\\005\\301\\31098\\311\\245\\335\\301\\317Q@\\010\\011#\\000\\3658\\2449\\317N\\236\\224\\374\\340g\\362\\244\\030+\\352A\\311\\240\\007#\\222\\271>\\270\\247\\025#\\004R*t\\007\\201\\232^y\\306>\\225 4\\201\\232@\\017B)X\\340s\\301\\365\\244\\0161\\221\\310\\366\\246\\000zt\\357\\305\\030\\310\\351\\336\\205!\\226\\234A\\301 g\\332\\200\\032p[\\351\\315.T\\035\\277\\376\\272N9#\\003\\003\\031\\243\\312\\031\\334y\\307z\\000\\216Fe\\007\\034\\322&\\343\\031\\031 \\261\\316jB\\244\\267n{\\236\\224\\370\\243\\307\\314\\027w\\256E;\\35012W\\012\\001&\\230\\271.9\\347\\370\\263\\376}\\205X\\010G'\\030=\\005D\\024\\264\\277w8\\000\\363\\350*@~\\003\\201\\270\\237\\224|\\244\\016\\336\\225b6`\\252:\\203\\337\\024\\305\\\\\\003\\236\\270\\346\\232$\\371\\200#\\353\\315N\\343\\036\\024\\010\\316\\325\\351\\352i\\216\\313\\317\\000\\014g\\353\\364\\374\\351Y\\216\\315\\270\\000\\203\\223\\203\\326\\242Y<\\303\\265\\372\\216N{\\323H\\007\\025,T\\222A\\307\\\\\\372\\323\\010\\335\\037\\336\\301\\030\\311\\375:T\\212\\270b\\305\\270\\036\\235qMA\\227\\301_\\370\\021\\357\\307Jb\\033\\026wm$\\003\\320\\014v\\307Z{\\235\\271\\340s\\320v\\246\\010\\2126\\364\\313z\\216\\364\\207\\005\\317##\\216E\\0006A\\206\\030\\301\\311\\357\\351M?0\\316wg\\034\\365\\245s\\311\\030\\355\\30740\\001z\\340\\343\\031\\364\\246\\002`\\347=\\317sX\\236'\\223m\\264\\021ww\\334\\177\\017\\377\\000]m\\344\\202r\\247\\031\\301\\346\\271\\357\\023\\310\\014\\226\\321\\363\\362\\3569\\366<\\177\\354\\265\\323\\205W\\252\\2141.\\324\\331\\200:R\\342\\220t\\245&\\275\\223\\312\\005\\031\\220})[\\24578e\\245n\\264\\000\\2509\\317\\245L\\243#\\247\\265B\\010\\332*\\314C T\\261\\241\\376X\\333\\212\\336\\360l\\013&\\253\\366\\206\\300\\020`z\\340\\236\\377\\000\\226k\\023\\240\\256\\247\\302\\036Zi\\262\\312\\251\\363\\031v\\271\\365\\003\\004}:\\3265\\252(B\\354\\326\\234\\034\\345dv\\272\\205\\244W\\020\\265\\354\\021\\341\\217\\336\\214s\\203\\216@\\376u\\313NAm\\335\\312\\201\\205\\3435\\324\\351\\327\\253\\014\\333Kn\\211\\270`F\\007\\266k#Y\\323~\\315\\276\\346\\002\\036\\007=\\277\\204\\372\\037\\177\\363\\336\\274\\352\\324\\323\\367\\342u\\341\\3524\\375\\234\\214G\\001rs\\317^EC"\\225V\\357\\201\\270\\363\\357\\376x\\251\\227\\234\\377\\000\\343\\331=*\\031>s\\30677\\035\\253\\024u\\262\\240%d\\301\\307#\\031'\\245<\\310\\273\\206\\320=x\\250\\325\\231]\\201\\355\\324\\347\\250\\245t\\005\\202\\220\\001\\025\\255\\210+x\\235\\324\\370n\\345W\\222Jg?\\357\\012\\363\\272\\357\\274@\\270\\360\\345\\337\\314\\010\\001\\000\\307\\373\\313\\\\\\015z\\231z\\2657\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200=\\003\\303G\\036\\037\\266\\364%\\271\\317\\373f\\264X\\225|\\343\\240\\355Y\\376\\033\\037\\361OZw\\345\\270\\377\\000\\201\\232\\322+\\220\\331=\\275q\\315|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\017\\203\\356\\007\\004\\343\\234\\372z\\324\\252\\300\\266\\006\\000\\316G\\2555\\031|\\275\\240\\214\\223\\216:R\\250\\005\\016\\007\\003\\236\\265\\2135\\000@\\335\\201\\353\\237zp\\000\\014\\343\\22009\\246\\001\\214\\214\\217bi\\352A=GPi\\014#\\334I#\\267?\\2157;\\260A\\364\\247\\241\\352G\\177\\322\\242<\\2203\\333\\024\\000\\362\\331\\306\\017\\276i\\252C7\\271\\355\\216\\264}\\322\\017L\\232@\\016F\\016?\\012\\000z\\261''\\247\\362\\246yD7\\007\\003\\036\\264n\\035\\327\\251\\343\\035jE\\330\\240\\237S\\323\\024l!@l8$\\201\\332\\221O<\\200A>\\274\\323\\362J7# \\367=icQ\\215\\304\\236\\230\\244\\002l\\316\\341\\270\\364<z\\322\\250\\001\\000<\\363\\220OZ\\003\\205\\356:\\344\\366\\245\\017\\204\\034v\\357H\\004\\303t\\031\\311&\\227\\030\\004\\372\\234\\361J\\031\\227\\030\\003\\2569\\247\\026\\033\\271\\374(\\001\\312\\244\\200q\\316;\\320cf\\0079\\305\\001\\360@\\007\\024+\\234c\\250\\366\\251\\030\\334\\0169\\350)\\277t\\374\\247\\217j\\013g%y\\036\\224\\212HPO4\\3049\\201`6\\367\\353\\236\\324\\251\\203\\236N\\341\\336\\232\\252J\\234\\034\\036\\302\\234\\257\\202\\024\\360~\\235h\\002\\\\d\\322c\\320\\036iW$\\023\\306\\017_cJ\\007\\006\\244v"a\\220\\001\\346\\240T?\\2075a\\362\\244\\343\\247\\267\\255\\035\\206sUq\\014U\\307S\\333\\201O$\\252\\373c\\275\\033p\\0019\\003\\371Sq\\222I;x\\240\\007&Y}\\001\\247\\272|\\235:RE\\323\\003\\203\\333\\232z\\206a\\216\\247\\267\\245H\\304\\330O\\030\\031\\351\\232th\\304\\214\\221\\327\\363\\025")\\003\\346\\306}\\251\\3416d\\251\\341\\272\\212W\\001\\214\\003|\\207\\267>\\224\\236N\\321\\362\\236s\\372\\032\\225\\202\\356\\351\\264\\376X\\245n\\210TrO8=\\251\\\\ed\\310\\005T\\221\\236EBw\\344\\003\\214c\\007\\212\\235\\201\\204\\340d\\361\\202G\\2451\\201r[i#\\006\\251\\000\\201\\263\\363\\221\\327\\236)\\230\\371\\311\\310\\036\\376\\324\\375\\245B\\200\\275\\177J\\037fWy\\333\\234`\\216\\240\\363\\377\\000\\326\\246\\003C2\\222\\254\\016z\\012x\\310Pq\\333<\\236*)"`\\240\\256\\007~:\\212S&\\344\\000\\023\\300\\344\\012\\004+\\005$\\266N\\356\\234S[!@9=\\371\\245\\013\\301$e\\217\\030\\364\\246\\310C\\246\\006\\320p(\\000Q\\236\\011#\\216\\001\\343\\374\\367\\244~c\\003#\\007\\234\\367\\244\\031]\\247#\\246\\005\\005\\267'a\\223\\237R\\017\\371\\3050\\021\\201*\\017Z\\346|N\\002\\337@\\274\\377\\000\\252\\311\\037\\360#]WE9#\\203\\317J\\344\\274J\\333\\365E\\037\\335\\210)\\366\\344\\237\\353]x/\\342\\234\\330\\257\\341\\231\\024\\001\\232)\\3508\\257\\\\\\363\\006\\037\\274\\277QNq\\203Ls\\237\\316\\236\\34784\\000\\213\\311\\305\\\\\\210|\\265MO\\315W!\\373\\2652\\032\\036\\337t\\375+\\243\\360\\233\\225\\323\\347]\\300\\003'C\\364\\256q\\276\\343}+\\242\\360\\324e\\264\\266\\300\\343\\316\\353\\217a\\3765\\311\\214\\376\\013:\\260\\337\\304GC\\035\\312\\304\\345pI8\\006\\267\\254n!\\324-d\\205\\237 \\247\\316\\207\\034\\214u\\037\\355\\017\\324\\0169\\000\\036SqG;\\210 \\236\\343\\2559.\\245\\202U\\225>\\360\\354O\\371\\364\\257>\\215W\\015\\036\\307]Z*z\\255\\304\\3254\\371\\264\\313\\303\\033|\\310\\303tn\\335\\030{U\\011\\012\\206\\001z\\373\\365\\256\\241\\265\\013}v\\333\\354wR\\030\\344\\317\\312\\344\\214\\241\\347\\221\\352\\017q\\333\\237j\\345\\244\\006\\336f\\212A\\262H\\230\\253\\251<\\206\\007\\004\\037\\312\\252pI\\336;\\005)\\266\\255-\\321I\\362\\262\\234\\343\\251\\372\\032p\\005\\330\\225>\\371\\024\\311\\244\\334O\\034\\347\\267\\2751d\\340`d\\367\\377\\000\\032\\253hU\\312\\376$\\332|=1Q\\216\\023\\257\\373\\313\\\\\\005w\\336!?\\361O\\335\\014\\362\\002~?:\\327\\003^\\236\\003\\370o\\327\\374\\217/\\037\\374E\\351\\376aE\\024Wq\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\035\\311\\320-1\\376\\337\\376\\206\\325\\254\\234\\311\\216\\207\\002\\262<8q\\241[\\020~a\\273\\003\\376\\006ka8\\310=\\207\\257\\177\\255|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\001\\342>x$\\323\\267a\\\\\\343 \\361\\300\\351JFc\\35294\\310\\325P\\266\\321\\214\\363\\315djF\\357*\\002\\304\\241\\036\\2258?\\273\\313g\\257N\\365\\0139\\334@<\\036\\370\\355\\353I\\274\\347\\031\\347\\372Q`,\\307\\367\\000$\\361\\315'\\033\\300\\340\\340\\365\\307Z\\007\\3128\\344t\\036\\337\\347\\024\\252\\240\\000A\\351\\357R\\000\\0279\\364\\007\\031\\245\\362\\313\\015\\247\\200y\\353B\\234\\270\\365\\355N=1\\236OqHde9\\034\\361\\307~\\206\\206\\332\\304\\357br:z\\322\\375\\016\\017\\034\\324l\\030d\\216ri\\210X\\303\\025\\347\\216\\235MO\\203\\301n\\234\\344u\\342\\241L\\200\\000\\317<sS\\243\\025?/\\335\\377\\000=\\3510\\023\\007vs\\323\\236i\\300t\\371A\\004q\\232\\220\\221\\234\\021\\301\\244;\\010\\345\\271\\305+\\200\\303\\220\\015<.x\\036\\235\\251\\304q\\307JL`\\237Lv\\244\\002d\\216O_\\345K\\367c\\007\\241\\366\\245'\\003\\346\\031\\036\\324\\323\\327v\\336\\203\\037\\205\\0006>W\\323\\036\\224\\374w\\250\\274\\302[\\000r;\\012\\230\\246W\\251\\024\\330\\013\\221\\264\\036\\331\\353@ g\\237\\312\\232wy\\211\\201\\201\\334\\212r\\205R@\\373\\315\\311\\366\\251\\030\\002\\335A\\037JV~\\0118\\315\\012\\006\\341\\337\\006\\243f \\340\\363\\333?\\375j\\000\\013\\216F\\177\\012w8\\303.x\\344z\\325v\\033\\271=:u\\372\\324\\261\\2068^\\2120W\\003\\257\\371\\305U\\204H\\312x\\351\\216\\274\\012\\031\\030\\257\\342)\\330\\300\\307\\245<\\002S=9\\353Sq\\202\\242\\252`\\364<\\340\\324\\261r3\\217|\\032@\\204\\000G\\362\\251\\027-\\363\\001\\232\\226\\300\\010%:q\\353ND8\\347\\245&\\340\\270\\031\\033\\216x\\317&\\2348p\\334\\217\\306\\220\\001@\\337yI\\031\\364\\316\\005+\\302\\20123\\217\\257JP\\245G\\035:\\365\\246\\230\\222@pJ\\263~\\030\\244\\003\\012)=Kw\\332Fj4+\\274\\240\\343\\236\\377\\000\\347\\024\\217\\013\\356WR_\\034T\\213\\0239;A\\357\\200\\313\\214f\\230\\312\\351\\270\\347 \\216h`|\\316~\\246\\256\\244C\\356\\226$\\363\\305B\\352"R1\\306px\\247p!nJ\\252\\223\\273\\241\\250\\372\\277\\316\\024\\201\\223\\323\\257\\277\\351HC\\0318\\3163\\222q\\322\\234\\235p\\006Nq\\236\\225B\\034\\310\\253\\036Ks\\374 \\324 u>\\231\\034\\001\\212\\234\\260g*\\006H\\000\\203\\376\\177\\3174\\326M\\251\\201\\320\\222s\\326\\204\\004{r\\003\\036;\\376T\\3008\\300\\354F*c\\235\\271\\347\\261<\\322\\005\\343\\030\\317\\270\\355B` \\000\\2141\\306y \\232\\344<F\\310uR\\021\\211;\\001n:\\037\\377\\000V+\\257\\031\\340\\343\\220y\\346\\270\\337\\020\\017\\370\\235\\316zp\\277\\310Wf\\005~\\367\\344r\\342\\377\\000\\207\\3633;\\324\\3102*!\\326\\247\\215y\\025\\3533\\316Ey;\\323\\372\\306\\0156O\\274G\\245*\\037\\220\\257\\343L\\004^\\265n\\037\\273T\\373\\325\\270>\\355L\\201\\022\\236\\206\\272\\257\\012\\177\\310\\035\\363\\201\\211\\211\\377\\000\\307Er\\325\\321xZf\\026\\223"\\234\\004`\\377\\000\\230\\307\\364\\256LZ\\275\\026t\\341\\337\\357\\021\\266\\311\\311S\\301\\252\\222\\246\\336rH\\317\\246\\015[.\\244\\021\\2021\\316I\\367\\357Tn\\231\\223\\356\\020\\244\\364\\257&;\\236\\221Vm\\204\\344r\\335\\371\\351PJ\\3573\\273I+\\031\\031\\216\\347'$\\344\\365\\3159\\210V=Os\\3765\\023;3c\\256\\006?\\012\\350\\211,\\212L\\356\\311\\307\\035OcI\\036\\001\\004\\037\\316\\234\\313\\320\\221\\223\\317\\024\\315\\244\\000s\\223\\272\\254\\222\\226\\274\\017\\366\\005\\317\\037\\334$\\377\\000\\300\\226\\270j\\356\\274@\\341\\264+\\234\\034d/\\037\\360%\\256\\026\\275<\\017\\360\\337\\257\\371\\036^;\\370\\213\\323\\374\\302\\212(\\256\\323\\210(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\357\\2746?\\342Ans\\311\\335\\307\\374\\014\\326\\240\\345X\\001\\236++\\303{\\277\\260\\255\\266\\365%\\272\\377\\000\\274kb0\\0306;\\216\\375\\215|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\016\\\\\\210\\271\\317\\034})\\252@<\\026\\247\\200\\246&9\\367\\365\\346\\233\\323'\\216;V&\\244l\\003\\003\\214\\363\\322\\224!'<\\221\\214\\202hT\\347'\\247Lb\\245'\\236;\\212m\\200(\\347\\223\\201\\237\\363\\374\\205*\\223\\306Np?\\012\\177E\\372\\361@\\030\\\\w\\002\\244c\\024\\202\\370\\347\\036\\3650\\\\)\\301\\034q\\212\\207i\\3162?\\016\\364\\360\\330<\\203\\354)0\\020\\214\\366\\347\\240\\305D\\370\\351\\315N\\304\\023\\214\\217\\361\\240\\250\\356W8<\\232w\\001\\261\\340tn:{T\\300`\\017\\177\\326\\241T;\\3169\\031\\344\\212\\235A#$\\217\\255&\\002\\221\\205\\306H\\317Zh\\214\\267L\\016s\\212\\223\\004\\022M;\\250<\\014}*n\\000\\314@\\332\\0178\\240 \\340\\202z\\347\\024\\244pq\\374\\350\\004w\\372c\\322\\220\\010\\331\\311\\355\\323\\2656I0\\334t\\372u\\247\\0207rM6M\\337\\300:\\203B\\002.\\014\\204\\347\\203\\351\\370\\323\\212y\\247\\357\\235\\240v\\245P\\334\\007\\364\\340\\346\\245\\331\\203\\264p3\\311\\246\\330\\010F\\010+\\310\\306)\\204\\204c\\225\\300\\300$\\323\\360\\300\\356l`\\016q\\336\\231\\260\\263r{\\367\\356) \\035\\346*\\256\\342\\033\\031\\300\\365\\241\\260X\\200\\271#\\237\\233\\275Bd\\345\\262\\240\\014\\340c\\250\\246\\217\\232Uu$\\2208\\311\\343\\245;\\000\\360\\035\\233 `w\\343\\374\\363S\\230HC\\263\\214\\216\\0014\\345&M\\304\\360==jRC\\01468\\343\\007\\212M\\201\\032\\306\\337*\\261<py\\352*^\\235\\017A\\234S\\372s\\200F=\\177Z\\204\\246\\\\\\016\\011\\034\\034T\\2012\\347'~\\000\\316(\\313*\\261S\\3162>\\264\\205I\\014\\007\\256\\177\\012G`\\004ci \\344ds\\376\\177\\372\\324\\200@T\\315\\2200I\\306\\010\\353R+\\023\\220x \\373u\\250Ws\\214.v\\216\\016H\\300\\353V\\342\\210\\225\\332p0>\\360\\357\\306(c\\024\\251\\306\\024q\\301\\372Tk\\034\\2079\\003vx\\307q\\353S\\202#EA\\214\\364\\345\\262i\\241\\211\\037t\\344g\\034v\\244\\0036\\262\\026\\022K\\222O\\360\\236\\242\\222'\\000\\223\\267\\234\\363\\333\\336\\234\\350\\257\\225\\317\\030\\300\\307\\370\\323\\327\\021\\347\\256{\\374\\277J\\004A3\\311\\346\\001\\032\\206\\004\\200Fq\\3056PYUx-\\367\\215Zx\\225\\233\\334\\216q\\364\\250\\314e\\237\\202@\\035\\016(\\270\\024DBM\\304\\021\\364\\365\\241UB\\365\\351\\370f\\254\\010H|\\214\\016\\330\\31740T\\016p\\001\\007 c\\364\\252\\270\\306\\014"\\345\\206H\\344\\346\\232\\343sv<t\\007\\247\\265)\\310\\213\\004d\\3658\\246\\034\\237\\230\\256\\011\\000t\\353@\\204\\341\\227\\240\\003?\\2253\\220\\245\\217\\247LqR\\005\\012\\243~\\027<\\021Q\\250\\336\\204\\202v\\373S\\000\\301'r\\236\\304\\327\\033\\342\\020WY\\233#\\357*\\343\\362\\025\\331\\341\\267\\003\\216\\376\\234W'\\342\\300\\006\\255\\031P\\0000\\251\\375Z\\273p/\\367\\277#\\233\\027\\3743\\021~\\360\\253q\\016j\\254c-WP`W\\253#\\316\\211NQ\\373\\306\\245\\021\\272\\252JF\\022L\\240>\\343\\007\\372\\212Y\\000\\332\\304\\372\\232\\350/\\264\\361\\027\\203l%\\000\\027YD\\254\\336\\201\\307\\377\\000\\263Q:\\212\\016)\\365v*0r\\273\\354s}\\352\\314\\035*\\273\\2141\\253\\026\\377\\000t\\325\\275\\210[\\223\\326\\377\\000\\205\\225\\376\\315r\\352\\271\\311\\013\\317N\\365\\201]\\027\\207\\233\\032K\\001\\367\\274\\366\\376K\\\\\\230\\277\\3413\\247\\016\\277x\\215Wp\\300\\206\\310>\\370\\254\\367$\\271\\015\\221\\214\\361\\375?\\235_g\\014\\330#\\0319\\317\\255g\\312\\205\\\\\\257$\\343\\217J\\362\\340zD`\\200\\276\\2479<\\367\\250X\\016\\031F\\033w\\347R\\242\\362p\\033\\034\\001Q\\037\\231\\272\\214\\366\\377\\000\\032\\325\\011\\210q\\2203\\305B\\261\\014pr\\011\\007\\024d\\034\\344\\202;\\037\\302\\236\\212\\331Q\\330\\365\\031\\307\\353W\\261;\\231\\236 \\300\\321.\\000\\035vs\\377\\000\\002\\025\\304Wk\\257\\357\\032E\\310\\317\\313\\205\\377\\000\\320\\205qU\\352\\340\\277\\206\\375O+\\035\\374E\\351\\376aE\\024Wa\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\033\\031\\320m\\273\\037\\234\\363\\376\\361\\255E\\227\\022\\355\\350;\\266:\\363\\376\\177:\\314\\360\\326\\177\\260m\\261\\376\\327\\036\\2779\\2556\\034n\\034\\036\\275\\005|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\022\\237\\365c8\\364?\\322\\201\\311\\012:\\361\\370\\366\\250c-\\345e\\260[\\246?\\317\\341R3\\025\\371v\\364\\031'5\\223F\\242\\217\\\\\\377\\000\\365\\352@\\006x\\356}x\\250\\300\\031n\\370\\306>\\2658NG\\256F9\\251c\\032{\\360FMI\\267)\\310\\004\\001\\353L\\350@\\350\\005X\\030#\\221\\202z{T\\261\\220\\225\\030\\004Tm\\311\\004\\016=*\\323\\000\\006z}j&Q\\236F8\\305\\011\\210\\200dw\\351\\353\\336\\23470\\034\\217^\\234\\321\\267\\234t>\\275\\005*\\202\\007#\\234U\\000\\271\\307\\000\\365\\251\\211ld\\000N{\\324 \\220\\333\\233\\257z\\231I\\316T\\037S\\232\\226\\003\\3019\\3032\\237\\366z\\323\\307^N\\0069\\250\\326N9\\3508\\034\\323\\367\\002;\\324\\260\\036@\\307ny\\246\\340\\2059"\\224{\\367\\246\\226l\\035\\334c\\234\\343\\212@+dg\\326\\231 ;\\371\\343\\003\\203\\351N\\035r\\240u\\306}*7\\334N6\\034\\016\\234b\\232\\001W\\337\\030\\366\\251G'\\217\\361\\250\\321A\\007\\013\\217\\247Js~\\355N\\033\\223\\323&\\200\\024n\\311'\\277zC\\200\\240\\363\\302\\375EG(\\221\\200U\\316}\\272{\\320\\310\\242\\022\\233\\260y\\033\\251\\330\\006\\221\\031\\347#\\257\\343\\326\\243WQ\\200\\250\\330\\355\\3061J\\274\\343\\003\\251\\364\\357N\\316\\027\\346\\355\\351L\\007#\\221\\363\\034~\\\\\\325\\220\\003\\250<\\025=I<\\212\\250\\024\\266\\000\\344s\\216\\335\\252\\304\\012\\254\\312\\216r@\\357\\307\\371\\353I\\201d\\376\\354\\3412\\017@\\015H\\261\\003\\2228\\371z\\367\\317\\2455P\\031A<v\\347\\353O\\226TO\\274\\016s\\363zV`D\\315\\211T\\002A+\\216~\\275jFF\\223j\\216\\024rI\\025\\001\\036d\\203\\250\\007\\201V\\331\\227vr\\000\\366\\374;P\\300\\211!\\216\\030\\366\\2530\\005\\262OO\\363\\322\\244F\\014\\3465$`pr\\015B\\203\\0231c\\2362\\253\\216\\265>\\304E \\360X\\362q\\202qI\\200\\035\\252\\243\\000u\\347w\\265:(\\333hf \\356'$\\372v\\246\\034\\025\\3712On\\337Zqv\\362\\263\\333=\\017oj\\000P\\233\\216\\325\\357\\372\\324\\330\\033@'\\222q\\310\\024\\330\\335D#$t\\3019\\307\\341R\\205\\371F8\\3079\\035\\363I\\201Y\\260\\011\\034\\000;\\3434+\\344|\\247\\357{t\\245t\\311\\333\\234\\237C\\3764\\322B\\222\\335@\\357\\236\\324\\0000,\\371\\005@\\007\\245@\\355\\221\\200\\012\\234\\014qN'\\007\\004\\347\\237N\\365\\033\\037\\233 \\340s\\201T\\2063\\201\\307P\\3039\\306)\\301\\224\\002\\010'\\217L\\347\\265&y\\003\\004g\\2758\\217\\335\\214pq\\372\\177\\221L\\006I\\363\\025\\031\\300\\034\\221\\237jE@\\252\\025\\007\\312\\006=\\215.\\354\\002\\010\\365\\033z\\321\\221\\267\\345\\003\\034c\\035~\\264\\010aP\\2520q\\323\\025\\310\\370\\257\\215J\\023\\236\\014#\\037\\367\\323W`H\\300\\004\\344W!\\342\\354\\215B\\034\\216<\\241\\203\\370\\232\\354\\301\\177\\024\\346\\305\\177\\014\\311\\205q\\317\\255Y\\252\\3610\\332\\006y\\251\\035\\261^\\263\\334\\363\\321\\014\\303\\026\\344\\373\\327\\243\\21316\\203\\035\\221Psj#\\\\\\256p\\333@\\317\\347^p\\331c\\027\\246\\341\\232\\365T\\012B\\263\\022\\024\\216=\\372\\017\\347\\\\\\030\\3715\\313c\\253\\012\\223\\346<\\252u\\001\\262;\\323\\255\\217$S\\365!\\267R\\271\\\\\\001\\373\\3068^\\203\\236\\237\\207J\\212\\335\\200\\223\\236\\342\\275\\004\\357\\033\\234\\215ZV-\\364\\025\\320\\370p\\343O\\224s\\304\\244\\036=\\205s\\315\\367k\\242\\360\\372\\025\\322\\313\\236\\222J][\\351\\307\\364\\256<g\\360\\216\\2347\\361\\015WV\\340s\\234\\347\\377\\000\\255U\\335\\010\\335\\223\\301\\356?\\317\\275N\\345@\\300'\\035\\215W\\232M\\244\\251\\007\\327\\332\\274\\264z%y\\0300!Tn\\307^\\231\\252\\222\\025\\030\\341\\277\\016\\242\\254\\270\\334\\011\\034}j\\263\\002\\010\\306N8\\034\\364\\255\\242K"\\3068\\344\\343\\326\\234\\011\\353\\216>\\2249\\310\\3160q\\232hf\\333\\202\\007\\025\\241%\\017\\020\\374\\332%\\313q\\316\\337\\375\\010W\\015]\\256\\274\\300\\350\\227\\000\\017\\356\\377\\000\\350B\\270\\252\\3650?\\303~\\277\\344yx\\357\\342/O\\363\\012(\\242\\273\\016 \\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\275\\360\\340?\\3306\\304\\017\\357\\017\\374y\\253\\\\\\000w\\000z\\360N1\\212\\312\\360\\336?\\341\\037\\267\\311?\\305\\333\\375\\263[\\201@\\012\\275\\010\\374k\\347\\253\\277\\336K\\325\\237AG\\370q\\364Dq\\305\\362\\226\\352\\270\\000\\221\\330\\366\\251\\012\\201(l|\\303\\214\\343\\212\\010%\\260\\033\\345\\307B{RD\\314[i\\310#\\265bh#\\2569\\342\\236\\214\\300c'\\247P?\\317\\371\\024\\216\\270\\007\\275\\013\\264\\221\\2001\\355@\\311\\011\\311\\317\\034\\372\\034\\032\\221Wq\\351\\310\\003\\234\\323\\024\\002G<\\377\\000J\\2226\\303\\214\\257\\346:T0\\036\\221\\226\\310#\\251\\307\\006\\220\\307\\273=\\015L\\213\\203\\216\\247\\257\\025;(\\306B\\217\\360\\251\\270\\031\\2466\\007\\356\\234{\\016\\364\\341\\013\\267\\247\\345V\\317\\000\\360A\\353K\\356}sO\\230\\012\\342\\335G\\336\\347\\256)Z1\\214c\\223\\332\\254\\221\\200\\006x\\250\\311\\004\\347\\320v\\377\\000?ZW\\002\\017/\\000\\001\\222}\\277\\012xRI'\\217\\306\\234x'\\203I\\301\\004\\367=(\\001\\010\\302\\360qHy\\\\\\001\\237\\353O#\\2363\\217Z0\\010\\351\\323\\255\\000F[\\2660=i7\\035\\330#\\2029\\365\\247\\262\\256\\334\\037\\255&\\016\\354~t\\300\\027\\034c\\223\\336\\206M\\307\\034u\\240\\266\\006\\007\\\\SyV!H\\305\\000\\036c\\200\\000\\374\\361\\323\\377\\000\\257C!\\012[\\222\\336\\203\\2659\\272\\234\\201\\2029\\244r\\344aI\\305\\000D\\027\\017\\203\\323\\351\\376}iv\\022\\271\\000\\236;u\\247\\347#\\033\\261\\326\\247]\\3040\\007\\234qE\\300\\257\\345\\237\\272xf\\247F\\205\\244\\007\\257b\\304\\366\\343\\247\\345S\\035\\210\\252\\254\\271lv\\367\\353O\\021\\224\\\\\\200\\002\\2578\\315+\\200\\366q\\024M&\\336\\203\\257\\343P\\263\\270*B\\235\\275\\300\\035\\271\\367\\244y\\377\\000{\\345\\222vw\\307\\255O\\032|\\303\\034\\234\\372\\365\\245\\260\\010\\0232.\\345\\344s\\365\\025cf>\\352.1\\203\\350j\\035\\333X:\\020K\\014\\355#\\222{\\325\\245E`3\\221\\2360sR\\300\\257 .0N\\334\\364\\365\\244\\004\\011q\\234\\234u\\353\\237z\\260T\\354\\012:\\347\\214{\\373}=i\\253\\036\\334\\347\\036\\243\\353E\\300M\\200\\021\\2343g\\214\\372f\\202\\027\\314\\312\\201\\203\\200r=)\\314\\024\\025=\\217_\\\\R\\031\\025\\270#\\217\\247\\347H\\001\\012\\262\\365'\\036\\334\\177\\236\\2254y\\330\\0118\\030\\317\\036\\365\\036\\325\\316\\320@\\343<\\372\\323^p\\012\\204$s\\200F3@\\016\\332_w\\0319\\344\\236I\\246:\\222A\\310\\344\\377\\000\\017j{\\037\\227j\\200}s\\370S$rG\\031\\366\\024\\001\\003\\235\\273\\210\\343\\266G\\341Q+dm\\003\\036\\224\\347\\335\\367[\\214\\216;\\346\\232X\\026S\\225\\300\\343\\216\\325v\\030\\364\\377\\000Z\\255\\2163\\214z\\320_,\\247\\003\\237N\\324\\000r\\270 \\017\\245*\\214\\202\\331$\\214pO\\255\\000#tf\\306\\0279\\246\\016I\\3162\\017zt\\231g\\332FF{t\\246\\220\\240\\340ps\\214\\347\\332\\201\\006\\320O'\\212\\345\\374d\\240\\233F\\357\\363/\\362\\256\\241~\\357\\\\\\363\\322\\271\\257\\030!\\362\\355\\033\\370A#>\\365\\323\\203\\37620\\304\\377\\000\\011\\234\\312\\203\\212\\232L\\371c\\326\\240SVO\\372\\257\\302\\275\\246yh|\\010\\032hT\\217\\342\\003\\365\\257PrT\\2258\\345\\211\\343\\217Z\\363+\\177\\370\\370\\207\\375\\365\\376u\\351R|\\304\\000\\006H\\343-\\214W\\231\\230}\\237\\231\\337\\204\\352y\\267\\210Qb\\361\\005\\342\\2466\\371\\244\\3753T#\\341\\305\\\\\\327\\031d\\326.6\\223\\200q\\317\\260\\305QRA\\353^\\225?\\201z\\034U>6X\\232c\\267\\003\\212\\352<?3>\\223\\345\\347\\210\\344!W=\\007\\007\\371\\346\\271\\025V\\226@:\\327G\\341\\311\\002\\315sns\\226P\\301\\2751\\377\\000\\355~\\225\\317\\213\\215\\351?#l4\\255P\\336\\224\\356C\\203\\203\\353\\217\\322\\252\\272\\234\\234\\267\\315\\236\\306\\254?\\335\\031\\030<\\344b\\252\\267\\246z\\016+\\312\\211\\351\\210FG8\\007\\035\\252\\026\\000\\034\\234\\023\\320\\373\\232\\225\\363\\260\\343\\003\\336\\252\\237\\233#'\\203\\370\\012\\322(L\\211\\333#\\337\\260\\246\\206a\\306x\\317C\\351K!\\000\\374\\304\\373SG$\\234\\217\\306\\26533\\365\\321\\215\\022|\\020~\\356\\177\\357\\241\\\\]v\\272\\360o\\354K\\202I\\307\\312\\177\\361\\341\\\\Uzx/\\341\\277S\\313\\307\\177\\021z\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336xl\\377\\000\\304\\212\\337\\003$n\\343\\327\\3465\\265\\0318\\306\\354\\343\\214\\232\\304\\360\\373\\021\\241Z`\\003\\367\\307\\376<kZ0\\304\\000\\307\\007\\034\\036\\275k\\300\\256\\277y/V{\\364?\\207\\037DZ\\016\\027\\346'\\201\\317\\034\\346\\234\\256\\2620?\\305\\364\\250\\220\\215\\204\\037_\\2552=\\306\\\\\\250\\3439\\351\\332\\271\\354l[p\\016q\\220@\\340\\373TE\\006\\375\\303\\2778\\317\\025!9R\\002\\340\\220)\\254;\\037\\312\\222\\001\\252w?\\\\c\\270\\247\\022\\352Ag9\\311\\355\\357N\\306\\356\\234\\234\\214\\261\\372Se\\300^\\247'\\216h\\031f\\031\\361&\\017Q\\351\\370\\377\\000\\205Y2d\\361\\221\\305g\\306\\243#\\220O9\\317z\\237q>\\307\\030\\250kQ\\017\\335\\236\\244\\036\\377\\000Jpl\\220H w\\003\\275D\\0160H\\366\\006\\236\\215\\270\\034\\016=\\315+\\0016IA\\216\\240s\\357MbH\\300<\\216\\274\\322\\207\\000}\\357\\322\\220r\\007 \\346\\220\\011\\267\\000c\\257ZNA'h\\247\\034\\000F\\177\\372\\324\\3020\\331\\307\\326\\230\\017\\316\\000>\\335\\251\\240\\223\\317\\343A\\311\\317\\035E7;F\\354|\\264\\000\\244\\221\\337?Ni\\000'\\030\\243\\234{\\364\\245\\317\\250\\372\\320\\000\\023\\203\\353\\365\\243\\243t\\036\\336\\275)\\300\\027\\302\\364\\356h`\\011\\340s\\330b\\200#a\\225'i8\\340\\342\\233\\273\\0121\\300\\305H\\331)\\267\\035{c\\2653\\311\\375\\346T\\236\\224\\300_\\230\\016\\024\\034\\365\\247\\250\\314 n#\\236NzR(\\004\\220zg'\\025'\\312[$q\\301\\244\\003a\\215\\324\\234\\221\\222N\\006j\\362m\\034?\\031\\033rG\\351U\\025T\\2000\\013\\251\\343\\035\\352\\332\\220\\361cqb\\244\\036jX\\025\\246\\204)\\004\\217\\227\\031\\317\\275.\\034n%\\302\\216\\304v\\253\\022\\375\\334.\\012\\236\\203\\336\\253\\256\\346\\223,w\\01603E\\306Y\\210\\007o\\233\\3468\\310$\\367\\241b\\332\\373\\224d\\2227du\\035\\350\\302\\250\\001\\230\\227\\354\\005*H\\371\\034\\356\\\\\\014c\\257N\\277\\255H\\211\\312\\027\\313n\\000\\221\\370\\323\\034\\267\\226\\271\\340\\016HS\\203R\\037\\365\\177(-\\202q\\217\\363\\353H\\255\\214\\202\\270\\317j@F\\334\\266}\\007SQ\\362\\024\\034\\341G\\030\\307N\\365),[\\031#\\217L\\367\\246\\010\\233\\267\\013\\337>\\264\\300\\201\\306\\016>m\\330# \\3674\\354\\000\\252\\003pq\\357Hr\\024\\021\\222C`\\235\\2650UL\\221\\200N\\0119\\340\\323\\001\\246BF@8\\036\\275\\215FX\\214\\214\\223\\307#\\337\\322\\235p\\006\\000\\030PqQ\\234\\266Td\\225\\350O\\343B\\001\\236V\\033%\\227\\000v\\357M;x\\300\\302\\340\\036\\005H~\\347\\336\\311'\\031\\035\\252"\\270\\307\\004\\366\\000\\014SC\\0342\\330\\031\\344\\3674\\346Q\\260(\\037JA\\222\\273T\\220\\330'\\247Jv\\3371q\\330\\016\\270\\357@\\210\\374\\314\\201\\335q\\311\\244-\\222\\001\\311\\357\\202iH\\003#9\\347\\036\\302\\242\\313\\007;\\363\\214`S\\002EPX\\023\\270\\020s\\307\\025\\211\\342\\325\\022i(\\374\\026Y\\207?PkaX\\211Bc\\214VO\\212\\276]'\\007\\034\\312\\007\\271\\353\\376\\025\\276\\037\\370\\2612\\255\\3746qK\\367\\205[?\\352\\352\\247\\275[\\037\\352\\253\\334\\221\\344\\242{A\\376\\223\\010\\377\\000m\\177\\235z9!\\201R\\001\\035y\\2578\\263`\\267P\\263t\\016\\244\\376u\\350{\\327\\313\\312\\343\\346\\031\\2573\\037\\274N\\374&\\314\\363=H\\356\\325\\256\\316s\\373\\326\\376uXu\\251n\\263\\366\\373\\217\\372\\350\\337\\316\\243A\\223^\\234~\\024pK\\342e\\350\\302\\354\\004\\014f\\264\\2642\\027Rpy\\314x\\037\\230\\254\\350\\306\\020\\001V\\264\\311D:\\2323\\014\\251\\371MeY^\\015\\033Rv\\232gB\\314T+\\016\\0118\\250\\330\\206\\364\\030\\007<\\364\\241\\331\\210,3\\234\\367\\364\\246\\020\\310\\017\\034\\364\\317\\371\\372W\\220\\221\\352\\007\\315\\321\\217\\004\\036*\\243\\023\\222\\000\\306O~x\\253\\004\\222>|\\037\\177j\\256\\361\\260s\\330d\\2661W\\0211\\2547\\003\\234g\\327\\025\\026\\007\\363\\315<\\214\\023\\200q\\214\\363\\332\\220\\206bB\\214q\\212\\320\\223?[`\\376\\037\\235\\273\\374\\243\\377\\000\\036\\025\\305Wc\\255FSD\\270\\347#\\345\\311\\317\\373B\\270\\352\\364\\360_\\003\\365<\\254o\\361\\025\\373\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336\\370p7\\366\\025\\251\\035>p\\177\\357\\263[\\000\\204P\\000\\307\\256\\177:\\340\\254|Gyaf\\226\\321G\\001D\\316\\013\\251$\\344\\347\\326\\254\\217\\030_\\214\\177\\243\\332q\\376\\303\\177\\361U\\344\\325\\301\\325\\224\\333]\\317V\\226.\\224`\\223\\354v\\340\\343,\\010\\310\\037\\235)\\221\\214g\\345_\\360\\256\\037\\376\\023\\035G\\376x\\332\\377\\000\\337-\\377\\000\\305R\\177\\302_\\250d\\221\\025\\260'\\321[\\377\\000\\212\\254\\376\\243T\\323\\353\\324\\216\\3503\\006\\303\\017\\257\\370S\\325\\303\\021\\310\\3348\\351\\326\\270\\037\\370K\\365\\014`\\303lG\\272\\267\\377\\000\\025N\\377\\000\\204\\307Q\\306<\\253l\\177\\272\\337\\374U/\\250U\\017\\257R;\\320G'\\257\\275\\017\\222F}3\\214\\327\\004<e\\250\\001\\217"\\327\\003\\375\\226\\377\\000\\342\\251\\337\\360\\232j\\004`\\333\\332\\0361\\312\\267\\377\\000\\025K\\352\\025\\207\\365\\352'y\\234d\\016{\\234P\\006N@<w\\365\\256\\023\\376\\023mK9\\362m\\177\\357\\226\\377\\000\\342\\250>5\\324\\217\\374\\261\\265\\377\\000\\276[\\377\\000\\212\\243\\352\\025\\203\\353\\324N\\354\\271\\307'\\236\\274T\\201\\313\\001\\214q^}\\377\\000\\011\\236\\243\\214y6\\277\\367\\313\\177\\361T\\377\\000\\370M\\265!\\322\\013O\\373\\341\\277\\370\\252?\\263\\353vA\\365\\352G\\240\\006#'4\\375\\334\\001\\323\\275y\\347\\374&\\372\\226I\\362,\\377\\000\\357\\206\\377\\000\\342\\251\\177\\3418\\324\\277\\347\\336\\323\\333\\344n?\\361\\352_\\331\\365\\203\\353\\324\\217Cc\\321\\216I\\353\\315)\\366\\317\\343^w\\377\\000\\011\\316\\247\\214y\\026\\230\\351\\215\\255\\377\\000\\305S\\277\\341<\\3250@\\202\\320g\\375\\206\\377\\000\\342\\251\\177g\\326\\362\\017\\257Q=\\020\\0169\\034\\372\\023Mh\\310S\\223\\317\\322\\274\\367\\376\\023\\315S\\037\\361\\357g\\377\\000|7\\377\\000\\025J|{\\252\\037\\371w\\262\\030\\364F\\377\\000\\342\\250\\376\\317\\257\\344\\037^\\242w\\205O8<\\021\\234z\\323\\201\\000\\214\\236\\235\\217\\362\\256\\003\\376\\023\\275L\\214}\\236\\317\\037\\356\\277\\377\\000\\025L\\377\\000\\204\\343R8\\375\\305\\247\\037\\354\\267\\377\\000\\025G\\366}`\\372\\365\\023\\321\\224\\223\\3174w\\003\\365\\257<\\036;\\324\\306?\\321\\354\\316:e\\033\\377\\000\\212\\245\\377\\000\\204\\367T\\000\\201od21\\367\\033\\377\\000\\212\\243\\373>\\270\\276\\275H\\364\\027$.@-\\3764\\245\\304Q\\345G\\323=k\\317\\177\\341=\\325?\\347\\332\\313\\376\\370\\177\\376*\\230\\3368\\324\\334\\363\\015\\2561\\2006\\266?\\364*?\\263\\353y\\007\\327\\250\\235\\371v2oQ\\306{\\372\\323\\325\\217%\\201\\310\\353\\307\\025\\347k\\343mIzCh9\\317\\010\\337\\374U;\\376\\023\\235S\\004y6\\274\\377\\000\\262\\377\\000\\374U?\\354\\372\\336C\\372\\365\\023\\322\\306\\010\\\\\\014q\\221\\374\\350W\\371\\276\\371\\000s\\317~\\246\\274\\343\\376\\023\\355W96\\366G\\035>F\\343\\377\\000\\036\\244\\377\\000\\204\\367U\\316L6\\204\\373\\243\\177\\361U?\\331\\325\\374\\203\\353\\324\\217O \\222\\012\\236\\371\\371y\\245E\\303g\\205\\031={\\327\\231\\257\\304-YG\\374{Y\\177\\337\\017\\377\\000\\305R\\217\\210\\272\\270\\307\\372=\\227\\037\\364\\315\\277\\370\\252\\237\\354\\352\\376A\\365\\352G\\250\\355\\033\\216NI\\343 \\347\\212l`\\030\\324\\221\\265\\200\\351\\2209\\307J\\363\\023\\361\\033X\\377\\000\\237{.\\230\\373\\217\\377\\000\\305SO\\304=X\\343\\375\\032\\313\\267\\360?\\377\\000\\025G\\366m\\177!}z\\221\\352\\252\\344;\\021\\367G\\344hb\\3318\\307\\\\\\021\\372\\327\\226\\257\\304}aG\\026\\326\\031\\316rc\\177\\376*\\227\\376\\026N\\261\\377\\000>\\266\\037\\367\\355\\377\\000\\370\\252?\\263k\\366A\\365\\332G\\2509$n\\000c8\\367\\246\\222A\\310c\\363W\\230\\177\\302\\307\\325\\373Z\\330\\017\\373f\\377\\000\\374U\\037\\360\\261\\365\\214c\\354\\3268\\377\\000q\\377\\000\\370\\252?\\263k\\371\\007\\327i\\036\\226\\013(;z`\\363P\\371\\241\\360zrFs\\351^l~ \\352\\347?\\270\\263\\347?\\300\\337\\374U0\\370\\373Vo\\371ci\\357\\362\\267?\\370\\365?\\354\\332\\376C\\372\\365#\\321\\343pPrW\\234\\200~\\202\\236X\\357\\310\\306p~e\\352:\\327\\232\\017\\035\\352c?\\350\\366\\177\\367\\303\\177\\361T\\277\\360\\236\\352\\270\\003\\310\\263\\000\\014p\\215\\377\\000\\305S\\376\\316\\255\\344\\037^\\242zC\\277\\335\\343\\004zS\\202\\340\\021\\216;{W\\232\\267\\217uV\\\\\\030,\\372\\365\\330\\337\\374U)\\361\\376\\254s\\373\\233?O\\270\\337\\374U\\037\\331\\325\\374\\203\\353\\324OI\\005rA\\307\\324\\216i\\331\\005\\363\\3108\\353\\237\\274=z\\327\\232\\377\\000\\302\\301\\325\\201\\310\\202\\317\\376\\370o\\376*\\217\\370X\\032\\267\\374\\373\\331g\\327\\313o\\376*\\227\\366u\\177/\\274>\\275D\\364B\\000l\\002r\\017Ji;\\301\\004\\025\\034\\021\\334\\212\\363\\246\\361\\346\\250\\335m\\354\\377\\000\\357\\206\\377\\000\\342\\250\\377\\000\\204\\363T\\300\\036E\\237\\375\\360\\337\\374U?\\354\\352\\342\\372\\365#\\322x\\300#\\215\\247\\007\\234\\326/\\213\\023\\376%\\003\\007;g\\\\\\347\\350\\325\\310\\257\\217uUb\\302\\013>\\177\\330o\\376*\\222O\\024\\337kQ\\033k\\230\\355\\3265>g\\356\\324\\203\\236\\235\\311\\365\\255)`kBjOdML])\\305\\305u \\253q\\363\\030\\252\\225f\\003\\224\\257I\\234H\\225N\\323\\237J\\357 s%\\252\\026\\332\\030\\240<\\375\\005pg\\205\\315B|m\\251\\333\\271\\205a\\264\\332\\207\\003(\\337L\\375\\352\\343\\304a\\347Y.N\\207E\\032\\321\\245~a\\267\\243\\376&\\027=\\377\\000z\\337\\316\\231\\037Z[\\207\\363.$s\\214\\263\\022q\\353I\\037z\\353J\\321\\261\\316\\365\\221u>\\350\\247\\302\\376]\\322?\\367H?\\2551>\\350\\240\\360\\301\\252Z\\271kC\\245`J\\222\\0063\\336\\220\\356|\\216\\240\\364\\315qg\\306\\032\\207#\\311\\265\\307\\246\\306\\377\\000\\032A\\343\\015@\\000\\0046\\274\\177\\260\\337\\374Up\\375F\\261\\331\\365\\332Gf>PT\\220q\\3275\\023\\225'\\360\\374\\353\\217\\377\\000\\204\\266\\377\\000$\\230m\\217\\374\\005\\277\\370\\252i\\361U\\361\\037\\352m\\277\\357\\226\\377\\000\\342\\252\\226\\012\\250\\276\\273H\\352_\\324\\2561\\326\\235\\036NJ\\365\\365'\\265rG\\304\\367\\2540b\\267?Uo\\361\\241|Oz\\234\\010\\340\\306s\\312\\267\\370\\325}N\\255\\205\\365\\312F\\367\\210Wf\\213q\\203\\301\\011\\377\\000\\241\\012\\341\\353V\\363\\304\\027w\\326\\257o,p\\205|d\\2509\\340\\203\\353\\355YU\\333\\205\\245*p\\264\\216\\034UX\\324\\232q\\354\\024QEt\\234\\301E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001W4\\342\\005\\303g\\272\\221\\372\\212\\247V,A7#\\036\\206\\224\\266\\034w6jh[\\034T5"u\\025\\316\\316\\204[?r\\271[\\256.\\244\\377\\000x\\327R\\307\\021g\\332\\271\\213\\337\\370\\373\\177\\255U-\\311\\253\\261\\264\\307'>\\274\\323\\24385\\030\\350>\\203\\371S\\227\\203H\\242\\372\\375\\321J{}i\\251\\367i\\325\\231g#2l\\235\\327\\321\\210\\250\\352\\336\\244\\024j\\023\\005\\030\\031\\037\\312\\252WZ\\325\\034\\217F\\024QE1\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005Y\\260\\377\\000\\217\\265\\372\\032\\255V\\364\\361\\233\\234\\372)\\245-\\207\\035\\315jx<\\212`\\351J:\\326\\007Ap\\363\\011\\372W=\\251&\\331\\303z\\212\\350\\027\\230?\\003X\\372\\242\\376\\345\\033\\2708\\242\\236\\214U\\026\\205\\355\\270\\030\\377\\000d\\037\\322\\220\\016i\\212\\305\\2223\\353\\032\\377\\000*\\221z\\212\\030\\313\\2500\\242\\234)\\027\\356\\212QY\\226s:\\240\\306\\2450\\367\\007\\364\\025N\\264\\365\\270\\366\\335\\253\\343\\357/\\362\\254\\312\\352\\217\\302\\216Y\\253I\\205\\024QTHQE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000U\\275<\\376\\375\\277\\335\\376\\265R\\254\\331M\\344LX\\306\\035H\\332A8\\245-\\207\\035\\315u#\\024\\265\\030\\232\\336C\\204|\\037B)\\334\\257\\322\\260:\\013\\260\\266b"\\263u\\025\\315\\233\\023\\330\\212\\273n\\343v=j\\246\\2506\\332\\260<d\\321\\037\\210%\\360\\205\\271\\315\\274G\\375\\232\\231z\\324\\026\\303\\026\\320\\373\\255N:\\323{\\211l]\\210\\3460jJ\\206\\003\\306*j\\314\\320\\305\\327\\227\\230\\033\\375\\341\\374\\253\\032\\272\\015n-\\326j\\377\\000\\334o\\347\\\\\\375tS\\370Nz\\237\\020QE\\025faE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001ND,i\\2654N\\027\\203I\\215\\013\\344\\214z\\323\\302\\036\\200T\\361\\224&\\246\\304k\\311\\307\\326\\263r5P*\\210X\\216F*h\\326h\\327\\207#\\234{RIr\\007\\013\\203U\\236\\344\\366\\346\\205v\\027\\2124#\\236T`X/\\036\\325J\\376\\371\\356\\334\\002\\002\\242\\364\\002\\240k\\211\\033\\276**\\250\\306\\332\\262%;\\253#f\\320\\267\\330\\342-\\310\\347\\037\\235N\\030U+=Cd\\036K\\307\\220\\240\\355a\\375ja{\\003\\0342\\262\\373\\216j\\032w4\\213V/E0S\\317\\351VD\\250{\\326_\\332-\\273H\\337\\367\\317\\377\\000^\\246\\216\\352\\33382\\021\\365Z\\207\\022\\324\\207\\352\\304\\0356N\\234\\343\\371\\212\\346\\253OR\\277YA\\267\\2150\\200\\362\\307\\277\\341Y\\225\\2655dcQ\\246\\364\\012(\\242\\254\\314(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000P\\354\\275\\015)\\221\\317V4\\332(\\013\\212I=M%\\024P\\001E\\024P\\005\\244P\\2109\\3523N\\010[\\240\\252\\361\\311\\201\\265\\272z\\372T\\3511\\035\\0105\\0153D\\320\\361\\013\\347\\224\\317\\343V\\241\\264\\030\\015\\212\\254.$\\364\\030\\241\\257\\233i\\000\\205\\377\\000w\\257\\347P\\324\\231i\\305n\\032\\214(\\230e<\\216*\\205>YZV\\313\\037\\302\\231ZE4\\254\\314\\244\\323wAE\\024U\\022\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\177\\377\\331	19	1	[Exif] Orientation - Top, left side (Horizontal / normal)<br/>[Exif] X Resolution - 72 dots per inch<br/>[Exif] Y Resolution - 72 dots per inch<br/>[Exif] Resolution Unit - Inch<br/>[Exif] Software - Adobe Photoshop CS3 Windows<br/>[Exif] Date/Time - 2008:09:22 11:29:40<br/>[Exif] Color Space - Undefined<br/>[Exif] Exif Image Width - 1280 pixels<br/>[Exif] Exif Image Height - 1024 pixels<br/>[Exif] Compression - JPEG (old-style)<br/>[Exif] Thumbnail Offset - 302 bytes<br/>[Exif] Thumbnail Length - 3479 bytes<br/>[Exif] Thumbnail Data - [3479 bytes of thumbnail data]<br/>
\.


--
-- TOC entry 2018 (class 0 OID 82389)
-- Dependencies: 1591
-- Data for Name: lekcjafotykursant; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY lekcjafotykursant (idlekcjafotykursant, datadodania, opis, plikmini, plik, idlekcja, exif, username) FROM stdin;
5	2011-01-31 23:36:41.139+01	\N	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\000d\\000\\226\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\266<7\\241\\035\\177T\\026\\355<V\\360(\\015,\\262\\310\\020\\001\\234`\\023\\334\\376<\\002pq\\212\\312\\212\\031n$\\021\\303\\033\\310\\347\\242\\242\\222O\\340+kK\\022\\351\\227\\357a\\251\\333H-\\256\\201\\215\\243n\\025\\234d+g\\276\\030\\365\\025\\023\\225\\227\\231t\\343\\314\\325\\366=#P\\322\\374\\026\\226m\\247<0\\301\\022Fv\\334$\\1772\\002\\330W2c\\346\\311\\030\\007$q\\214\\342\\274\\202\\356\\331\\354\\356\\345\\267vF1\\261\\033\\320\\345\\\\ve=\\301\\034\\203\\334\\021^\\304\\260E\\025\\254\\261\\303l\\271yL\\241\\030\\235\\252s\\221\\220G\\\\\\201\\316;\\360k\\3125\\326\\270\\223Uw\\272\\307\\234R<\\341v\\216\\020\\016\\230\\0351\\217\\302\\271\\360\\325\\235F\\323:\\261T\\0254\\2323\\250\\242\\212\\3538\\202\\212(\\240\\002\\254\\332i\\367W\\373\\376\\315\\026\\375\\230\\335\\363\\001\\214\\375O\\261\\252\\325\\324\\3705ro8\\004\\374\\235\\263\\375\\352\\306\\275GN\\233\\2225\\241MT\\250\\242\\314\\257\\370G5c\\377\\000.\\237\\371\\021\\177\\306\\247\\227A\\326\\034\\251{\\030\\370\\376\\341\\215s\\365\\301\\256\\355\\224\\025\\015\\320\\221R*\\203\\202s\\237Bk\\315y\\205N\\313\\372\\371\\236\\227\\324)\\367\\177\\327\\310\\363\\317\\370F\\365fU\\333d:v\\225y\\375jE\\360\\326\\260\\247\\235=\\031s\\222\\246E\\355\\333;\\263\\337\\326\\275\\006%\\022\\015\\301H\\036\\335\\351\\356\\031\\010 \\023\\223\\326\\223\\314j\\355e\\375|\\303\\352\\024\\373\\277\\353\\344y\\333\\370WY'#O\\333\\352\\004\\311\\217\\375\\012\\206\\360\\266\\262\\317\\306\\236\\253\\223\\302\\211\\227\\372\\265zF\\365\\030f\\316y P\\010U\\007\\0318\\035*\\177\\264j\\366_\\217\\371\\217\\352\\024\\273\\277\\303\\374\\2178\\036\\027\\326\\002\\344\\351\\340\\374\\300\\377\\000\\256L\\034~9\\347\\353\\377\\000\\326F\\360\\346\\252\\021q\\247*\\270`wy\\252A\\343\\246\\013}I\\376\\225\\351\\021\\263\\026#h>\\365$\\220\\206\\207\\014\\012\\237\\257#\\232?\\264j\\365K\\361\\377\\0000\\372\\205>\\357\\360\\377\\000#\\313\\377\\000\\341\\031\\325\\313\\005\\373\\030\\317\\375uO\\361\\244o\\015j\\350\\011kL\\001\\377\\000MS\\374k\\320\\335\\374\\227\\031\\\\s\\235\\300zv\\376t\\233\\213\\017\\220c\\214\\343\\245_\\366\\205^\\313\\372\\371\\213\\352\\024\\273\\277\\353\\344cx3N\\271\\323F\\246n\\020\\333\\334Ko\\266\\011W\\346+\\3247)\\222\\275G\\345Ve\\321m\\356\\274\\241\\034I3\\375\\330\\331\\243\\306\\376\\006N\\017@\\000\\030\\364\\000q\\326\\257\\306\\014A\\371(\\314\\010\\030\\316\\010\\037O\\303\\212\\273\\242\\203\\375\\245\\023l\\017\\034(\\331,N0\\007\\371\\375i\\323\\254\\347U7\\273\\377\\000 \\225%\\012|\\251h\\211\\240\\202\\347\\354 E<ry\\304\\034\\311\\2373 \\340\\205^s\\363`v\\350zu\\031^5\\360\\273\\336\\351\\220\\335\\031\\025/-\\243\\021\\307\\026A\\336\\245\\311 \\267\\367\\2673\\266~\\277Q\\325<\\006\\347dY\\231JD\\273\\235c\\005C\\266Y\\362\\271\\3440a\\234{v\\254}^\\351\\346\\325\\204\\015\\274E\\032\\252\\374\\375I\\3062{z\\376f\\2724\\245yE\\030\\312\\365\\022\\214\\231\\345c\\303\\332\\241b>\\313\\310\\377\\000\\246\\213\\3765\\237,o\\014\\257\\024\\203\\016\\214U\\206z\\021\\326\\275%\\216\\025X3o\\3063^y\\250\\343\\373R\\357\\035<\\347\\377\\000\\320\\215^\\033\\021*\\255\\251\\031bp\\360\\245\\024\\343\\324\\255E\\024Wa\\306\\025\\324\\3700fK\\274\\222>\\340\\343\\376\\005\\\\\\265u~\\012m\\262^\\036\\377\\000&?\\361\\352\\346\\306\\177\\006_\\327S\\247\\011\\374h\\377\\000]\\016\\307\\313\\310\\307'\\246q\\333\\361\\2451a\\006O \\365\\356(Vf]\\334\\216p1\\306~\\264\\365\\306\\340\\033\\277A^\\005\\317pta\\200\\354\\007\\035\\271\\374\\350Q\\307$\\016\\177\\317\\025\\013\\273+\\355\\306G$\\006\\352*H\\217\\3140C~\\034\\342\\225\\200{\\215\\340\\200O\\247&\\2260v\\234\\234\\361\\222Gzg\\336\\310$\\343\\260\\247(!\\270\\300\\365\\343\\332\\220\\017B\\25299\\000\\372R\\273\\226\\005z\\017N\\264\\300:\\017\\345Q\\266\\357\\225A\\002\\213\\001\\034\\321y\\304\\343\\035:\\347\\031\\366\\246\\303\\271N\\336G\\256O\\270\\253\\261G\\310\\\\\\362E1\\343!\\266\\343\\327\\221\\333\\327\\371S\\346\\350\\004\\022\\246\\345*r7|\\247\\007\\037\\255di\\372\\234\\232\\014\\323\\301#5\\304\\0044j\\347%\\227\\345\\311\\007\\351\\221\\320w\\355\\322\\267\\231x\\030\\030\\003\\236\\237\\215g\\352\\032\\250\\266\\205\\254$\\234\\010\\244\\033\\3326]\\301\\362H\\364\\343\\201]XI\\332v\\261\\206%{\\227:\\004\\361U\\206\\237l\\257p\\214\\302}\\222#\\206R\\030m\\0039\\007\\030\\312\\221\\221\\334c\\257\\025\\207.\\252\\272\\265\\323^,R\\240.\\304y\\200\\014\\214\\014\\021\\355\\301\\353X3j\\366\\336J,\\026a\\244L,fN\\221\\256X\\225Q\\236\\001-\\234z\\3769\\336\\323eK\\270&\\271X\\314qy\\233c\\217$\\343\\0123\\226\\357\\327\\323\\212\\354\\304M\\250]\\243\\222\\222R\\225\\223*\\312?{\\302\\262g\\004\\001\\333\\201^y\\252\\340k\\027\\270\\351\\366\\211?\\364#^\\2256<\\365b\\271\\311\\004\\034\\343<\\327\\233j\\374\\353W\\344\\177\\317\\304\\237\\372\\021\\245\\200w\\223\\364\\014z\\264"S\\242\\212+\\324<\\300\\256\\253\\301\\231\\305\\363/Q\\345\\361\\214\\347\\357W+]W\\203[i\\274\\307\\\\\\307\\307\\375\\365\\\\\\330\\317\\340\\313\\345\\371\\2358O\\343G\\372\\350v\\012YN\\334\\216\\271\\3018\\006\\236\\257\\320\\200s\\300\\315@\\301\\201Q\\236GS\\217\\302\\245Q\\203\\216\\236\\325\\340\\263\\334\\004\\213q\\336@V\\343\\247\\377\\000Z\\246@@$\\036s\\236;T\\021\\306L\\274\\201\\202*\\320CI\\261\\015e\\013\\357\\370\\324\\253\\206\\372\\364\\250\\317\\337\\312\\343\\361\\2473\\005\\007w\\034\\201\\322\\244\\004\\311\\\\\\345x\\0351O\\020n\\301\\035\\007\\275,P\\2660\\303 \\236j\\340\\312\\345T\\022q\\330g\\371Rlef_%\\201#\\257\\345\\322\\242c\\2269'\\0352?\\302\\264Z#\\2207\\036N\\016?\\317\\275VxX\\022B\\355\\000s\\232\\224\\306U|\\263\\036q\\357\\\\~\\274\\354\\372\\263\\241\\351\\032\\252\\217n3\\375k\\266\\2261\\3461N=~\\265\\306k+\\235zq\\214p\\277\\372\\010\\256\\374\\017\\361\\037\\241\\311\\213\\370\\027\\251\\235\\010\\006E\\035\\261]\\356\\202ao\\017%\\254\\233\\021eg}\\344\\221\\265\\267\\021\\223\\307\\240\\037\\225p\\211\\024\\2214,\\353\\205pJ\\234\\365\\031#\\371\\203]\\226\\237&4\\033p\\011\\3408 {\\273\\177\\215t\\343%h+w9\\360\\321\\274\\265\\031}\\034\\226\\323\\224\\225Ln8 \\363\\350F\\010\\377\\000\\365\\032\\363=P\\347W\\275?\\364\\335\\377\\000\\364#^\\203"\\344\\214\\220Oo\\226\\274\\363Q\\301\\324\\356\\361\\323\\316\\177\\375\\010\\325`\\027\\274\\303\\037\\360\\242\\265\\024Q^\\231\\346\\005u^\\014]\\337m\\343<\\307\\377\\000\\263W+]'\\204\\256\\241\\265{\\2234\\361E\\235\\230\\363\\034.~\\367\\255sb\\323t]\\277\\255N\\214#J\\262\\277\\365\\241\\332\\205\\352z\\200y\\253)\\011\\221\\010\\030\\316;Vg\\366\\266\\235\\264\\251\\277\\26699\\000\\312\\274~\\265"kZr\\347\\032\\205\\276\\323\\330\\314\\243\\372\\327\\204\\341>\\307\\267\\317\\036\\346\\202[\\272.IPG\\035jQ\\270\\017S\\237J\\246\\232\\346\\227\\267\\037\\332\\026\\237\\214\\3523\\372\\323\\216\\265\\245\\237\\371\\211Y\\375\\014\\353\\3765.\\023\\354\\034\\361\\356X\\306\\030\\365\\365\\246\\2723\\266\\334\\223\\351\\217\\376\\265W]gK\\301\\316\\243i\\221\\330\\316\\274\\376\\264\\015gKF?\\3610\\264<d\\201:\\376]h\\344\\237`\\347\\217sB\\020\\321\\217\\230\\026c\\235\\275:U\\261\\030\\332\\011\\303c\\003\\034}\\177\\317\\343Y\\351\\255\\351\\015\\260\\177iX\\251\\034\\377\\000\\307\\302\\363\\355\\326\\254&\\277\\243\\230\\331N\\251b1\\353p\\236\\230\\365\\250p\\237f\\034\\361\\356^Y9\\301\\001\\270?63\\212\\2576\\000\\332\\030\\354\\3479\\346\\252I\\256\\350\\311\\363\\015N\\315\\212\\222F'^\\177Z\\205\\265\\315%\\363\\377\\000\\023;N\\243\\237=\\177\\306\\222\\247>\\3149\\343\\334\\266xE\\317A\\327\\203\\327\\251\\256/^F\\203[\\234\\360w\\242\\260\\366\\343\\037\\322\\272v\\3264\\256\\243T\\263\\310\\377\\000\\247\\204\\377\\000\\032\\346\\274Au\\005\\336\\251\\023\\333\\334C:\\371\\001KD\\301\\2009<q]\\270(\\31255]\\016lT\\242\\341\\243$\\326\\355\\341\\265\\323\\264WE\\303<LX\\217\\370\\013\\1776o\\316\\264\\364\\271w\\350\\261\\355\\003\\222\\300\\347\\352O?\\201\\254m^\\362+\\253\\015.4\\221\\013\\302\\254\\256\\212\\331+\\367@\\310\\355\\234T\\366\\027\\266\\326\\366\\355\\034\\323\\305\\021g\\310\\363\\034.x\\003\\275oV\\016TU\\367\\273\\374\\331\\235)%W\\312\\337\\242-N\\333\\\\\\363\\327\\327\\255y\\346\\242I\\324\\356\\311\\352f\\177\\375\\010\\327q.\\243`\\344\\250\\274\\203\\004\\365\\022\\257\\370\\377\\000\\234\\327\\015~\\301\\365\\033\\226V\\014\\014\\256C\\003\\220y<\\326\\330\\030\\264\\335\\314\\361\\322N*\\314\\257E\\024W\\244y\\241E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001Z\\332Q\\304$\\377\\000\\267\\375\\005e\\001\\222\\007\\255h[3[FC\\355\\000\\234\\344\\234TOk\\027\\015\\356]\\000\\215F\\340\\236\\345H\\376_\\322\\242\\327\\272[\\377\\000\\300\\277\\245\\021\\335\\3075\\324\\222\\002\\000\\012\\243\\223\\202O?\\343N\\326%\\216[(\\312\\221\\235\\374~G?\\322\\241]I\\032;8\\263\\022\\212(\\255\\214\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\011al\\023\\300<g\\232t\\362\\226UL(Q\\316\\000\\357E\\025\\037h\\277\\262AE\\024U\\220\\024QE\\000\\177\\377\\331	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\001\\220\\002X\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212+OD\\321g\\326\\256\\374\\250\\316\\330\\327\\227s\\333\\377\\000\\257I\\273\\015&\\335\\221J\\332\\326{\\311\\204PF\\316\\347\\260\\035+\\320<7\\360\\246\\347SSs\\251\\336\\307il\\237|\\016[\\246q\\236\\307\\363\\256\\303\\301\\236\\033\\261\\2047\\225\\026\\330"\\3102`n\\220\\216X\\223\\355\\220\\001\\354\\304zWAqx\\367o\\210\\327\\313\\264\\\\\\371P\\257\\177\\366\\217\\327\\377\\000\\256y\\351\\233\\2337\\215$\\267<\\367V\\370o\\241*:\\351\\327\\332\\210\\231A\\301\\222\\000\\310O\\347\\232\\363\\215OK\\272\\322n\\332\\336\\352<\\021\\321\\207F\\036\\242\\276\\205\\2126\\221\\310v\\332\\271\\344(\\254o\\023\\370Z\\333\\304\\032s\\307\\013\\252\\334&Ln8\\033\\260p\\017\\2618\\315(\\315\\365\\034\\351\\246\\264<\\032\\212\\236\\362\\322{\\033\\271-\\256bh\\246\\215\\266\\2727Pj\\012\\330\\346\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200&\\265\\266\\222\\362\\352+x\\207\\317#\\005\\031\\3503\\353^\\277\\341_\\016\\234\\0152\\317\\021\\025\\217\\314k\\207\\214\\221#eW\\216}\\372\\377\\000\\262Gn8\\277\\000\\330\\302.\\347\\325\\257\\023u\\255\\24623\\215\\314{~<\\003\\354Oz\\357S\\307w?i\\206Ac\\032C\\033\\214,Q\\021\\362g\\220\\016:\\373\\3265%\\255\\216\\2320v\\272:Kx/\\242\\360\\372\\351\\326\\2262\\206\\033Q\\3351\\2007|\\374\\236rF\\177\\032y\\267\\3731o>\\342\\336\\325\\025p\\032G\\344{\\025\\353\\371\\032\\311\\325\\274ou\\177g\\014Vp43\\314q"\\266?Lrs\\375k\\216\\325\\265\\025\\262\\234\\377\\000i\\352\\037\\277\\003>DD\\273\\365\\034`t\\340\\347\\014T\\326.\\242\\275\\226\\246\\352\\234\\255w\\241\\337\\177l\\350\\220\\022%\\275\\270f\\357\\266\\021\\267\\360\\344U\\270&\\323\\357\\371\\323\\256\\304\\217\\217\\270\\303k\\037\\240\\347?\\235y\\265\\235\\306\\203q\\020\\204]K\\023\\267\\003\\355\\021\\225?\\367\\321\\310\\374\\315c\\275\\346\\243\\242\\352XIY\\274\\262\\035YO;s\\200A\\030\\346\\224*);Z\\303\\235>U{\\233?\\023t#$I\\254\\305\\030W\\217\\021\\334\\0020H\\310\\012\\177\\016\\237\\217\\265y\\205{\\252kv~'\\321\\266^\\300\\321\\311t\\214\\255\\306\\325\\220\\001\\303\\001\\234\\343\\234n\\366\\306x\\025\\341\\323\\302\\366\\327\\022\\301 \\304\\221\\271F\\036\\340\\340\\327M9'\\241\\311Z\\0154\\373\\221\\321E\\025\\241\\210QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\332\\3702\\353f\\217\\251E\\031"h\\344\\212a\\223\\301\\031\\307\\363\\251f\\265\\273\\270\\211gk\\363\\001$\\252\\342a\\022\\240\\364\\311#'\\203\\311\\353\\3061\\\\\\256\\215\\250\\2357PYO\\372\\267S\\034\\200\\214\\360F3\\365\\035k\\323\\354M\\273\\370f)\\323a\\220\\355\\004\\306J\\363\\311'\\217rO\\343\\\\X\\247\\310\\324\\254z8O~.&^\\207os\\177\\241\\337\\235Fy\\035Y\\000\\215\\270\\005x\\004}\\336\\344\\234\\036\\343\\220q\\310\\254\\253m\\001\\240\\274@\\221\\021\\023#+\\024C#\\203\\307;C\\251\\350z\\347\\036\\271\\256\\273Ih\\347\\320\\245\\213~\\334\\222\\333\\216yP\\345\\211\\372T\\366\\215oqp\\214\\241\\\\\\002\\001R\\277\\221\\000\\327\\037\\266\\224\\\\\\254v{\\024\\322\\271\\035\\204\\027\\261[\\01318\\231UB\\215\\313\\2023\\223\\352@\\034\\343\\034\\343\\035y\\343+X\\321\\206\\243,2D\\312\\233\\217\\2236z*\\363\\223\\365\\037\\257\\265v\\256#\\265\\267-\\032\\250\\307\\335\\343\\275sRH\\327\\023\\264\\204\\374\\231\\302\\214u\\036\\246\\261\\2476\\345\\314\\215\\034"\\343\\312\\312q\\336\\026[o\\265\\221\\015\\305\\345\\300\\375\\346\\003\\3423\\201\\273\\216\\200\\017\\227\\007\\324\\372W\\235x\\2027\\213\\304z\\222H\\245X]I\\220\\177\\3365\\265\\004\\362\\334N\\277h\\225\\245fF\\000\\271\\311\\001r1\\237\\256j\\247\\214\\324\\177\\302B\\322\\361\\272x\\243\\231\\261\\334\\262\\344\\327\\255B\\012\\0373\\313\\305K\\235\\\\\\347\\350\\242\\212\\3518\\202\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\245\\224~\\352\\023\\376\\311\\037\\251\\250\\321w\\272\\250\\356q]RxV\\377\\000X\\236\\321-\\243H\\355\\226\\020\\276k\\0209\\007\\237\\251$\\324\\316j*\\362*\\020rv\\211\\312Q^\\224\\236\\033\\321\\3574\\271\\364\\310Q\\341\\277\\201\\2172\\014;\\021\\333\\337\\257\\037^\\325\\316^\\370"\\376\\326\\326K\\230\\356-\\246\\211Sx\\001\\210v\\035O\\030\\343\\003\\236H\\366\\315a\\014U9;=\\015\\347\\205\\251\\025u\\251\\314Wg\\341[\\365\\227K\\222\\315\\333/\\023d\\003\\335\\017\\370\\034\\376u\\313^\\330MbPJP\\357\\007\\033Nj]\\036\\377\\000\\3737R\\216s\\312\\037\\225\\307\\373'\\374\\347\\360\\253\\253\\025R\\032\\012\\214\\235*\\236\\361\\350\\211.<\\273[\\273\\325\\021\\244\\203\\010$\\033\\201\\031#p\\034\\236@\\030=\\361\\301\\305l\\013{i\\025\\026\\031\\320\\3143"\\363\\207c\\324\\234\\036y'\\255U\\267\\323\\346{q$.\\261\\347$*\\341{\\372b\\257\\206\\232\\330\\010\\244\\220\\310@\\317<\\200q\\326\\274\\211>\\307\\263\\024#\\336\\311-\\253\\307 \\371\\225O\\377\\000\\257\\371\\326sn\\212\\331\\231P\\264\\2062UA\\347=\\277\\014\\367\\253\\300\\011\\246D\\306\\351\\034d/\\267\\251\\366\\242KU\\212DS\\376\\271\\330\\274\\204t\\302\\343\\201\\376\\316z\\177\\273\\353R\\232E\\234,\\0262\\301\\342\\021lb&\\0378\\355|`\\035\\300\\023\\374\\3537\\306f1\\342I\\241\\215\\203\\213x\\343\\201\\230\\034\\202U@5\\335\\337Z\\213{\\341\\250\\307\\2012\\250\\010[\\225\\007<~#q \\372\\342\\270/\\021xwQ\\322|\\233\\353\\250g6\\327\\237<s\\3108rF\\356\\277C\\237\\377\\000Q\\257W\\01754\\231\\344\\342\\340\\340\\354`\\321E\\025\\324p\\205\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\022\\244!\\227sM\\034c\\031\\033\\2119\\374\\201\\307\\343S\\213\\020\\276X\\232]\\245\\313p\\2407\\312\\007\\\\\\347\\030\\367\\351\\301\\347\\212\\2124\\205\\324\\023"\\243\\000w\\011\\001\\301\\364\\306\\321\\374\\375:\\363\\303\\326\\345\\014b\\011\\024\\210H\\352\\275T\\372\\217\\313\\221\\337\\362\\304\\273\\364)[\\251^@\\202B#fd\\354YpO\\341\\223HT\\200\\017\\034\\214\\360j\\345\\264\\260\\333\\207c$\\201\\360\\002\\264D\\253u\\347\\364\\030\\374z\\036\\322\\033\\210\\232d2\\\\\\310\\351\\034\\213\\263%\\230\\354\\344\\236\\275\\017\\0128\\307\\343G3\\354\\034\\253\\271FH\\374\\275\\200\\237\\230\\250b1\\323=?L\\037\\306\\232\\006s\\323\\217z\\277=\\363\\341\\012\\221\\222\\034\\221\\235\\330\\3361\\200}6\\343\\217Q\\315"Nm\\255D%\\312\\272H$\\333\\216Cg\\371\\200\\007\\346E\\027v\\013+\\224B\\2268PI\\306x\\244\\253\\222\\334!\\267d24\\262t\\313\\022FK\\022\\314\\271\\351\\321\\177Z \\232\\030|\\223\\346I\\265\\224\\211\\204x\\007\\357t\\344r0\\007\\007\\336\\213\\260\\262)\\321Z\\237l\\033\\342-}1`\\354\\254\\300\\222UH\\340\\202F{\\234\\372\\340{\\032cL\\261F\\252&\\2212\\025\\374\\260\\010\\012s\\374#\\327\\001H'\\250$\\365\\245\\314\\373\\007*\\356gQVn\\356\\336\\341\\210,6\\226.B\\023\\264\\222s\\234\\036\\3438\\317\\260\\253&\\3765x\\232\\022\\352T\\005\\223\\004\\257\\230\\241Tu\\0079\\340\\373t\\367\\247w\\330,\\273\\231\\304\\02588\\351\\236\\016i+L^\\306\\351\\211%\\227s}\\366RG\\336;\\237\\003\\240\\350\\007\\241\\311\\317\\261%\\341\\226\\0072l\\304\\212\\333\\216y\\334\\314\\011 }\\024\\017L\\347\\232\\\\\\317\\260\\371Ws2\\212\\261!\\023^)\\232c\\265\\266\\356|\\356*\\270\\035}H\\037\\312\\237=\\312=\\264P\\306YWqy\\024\\014\\000x\\000\\001\\337\\000u'\\275U\\311\\261R\\237\\034fG\\332=\\013\\037\\240\\031?\\312\\264\\005\\364Q\\247\\226\\262J\\360\\355%Q\\230\\222\\271F]\\2751\\324\\347#\\267\\257J\\223\\373MJN\\2134\\252\\204\\217+wU\\001[\\030\\003 r\\024q\\327'\\360\\227'\\330\\245\\025\\334\\310\\242\\264\\276\\334\\201\\321\\014\\263\\033l*\\225S\\206\\030\\034\\360r0I<{\\012I/2\\261\\223y+K\\031#z\\226'\\034\\362\\244\\343\\031\\316\\016y\\340})\\363>\\302\\345]\\314\\352+I\\247\\201\\224F\\327r\\274\\033\\200T`X\\242\\220Fy\\343#\\030\\351\\306\\3563U\\004\\276|\\222Iq!g(p_''\\377\\000\\325\\323\\337\\035\\250L\\032!\\301\\332[\\214\\003\\216\\274\\323\\322 \\320\\311!`6`\\001\\334\\223\\377\\000\\326\\007\\374\\232\\236\\362o8!\\373D\\222\\205\\030\\314\\214I$\\362N9\\307a\\370T\\326\\263\\301\\034(\\242v\\202@\\303t\\250\\016\\342\\033!\\261\\216\\300\\005\\374s\\212\\033v\\270$\\257b\\206\\306\\007\\004`\\343<\\361\\3063M\\255(/c\\202A*\\355\\314\\212\\306D\\003h\\031'+\\300\\351\\2000;\\036i\\004\\355-\\270H\\256e\\363\\034\\021*\\273\\354@\\011\\316F8\\307Q\\317\\257\\002\\216g\\3309Ws:\\212\\265sv\\3234\\252\\247\\021\\311)\\224\\2578\\004\\377\\000\\365\\276\\225hj>H\\015\\024\\247p\\000`er\\021v\\247\\352I \\361\\300\\024]\\366\\013.\\346c\\002\\254T\\343 \\343\\203\\221IV&\\223\\314e\\205f\\304!\\216\\001\\316\\304\\344\\364\\357\\214{f\\256G<(U\\343\\270b\\353\\001\\013\\270\\220U\\202\\221\\200q\\323\\222@\\030\\353\\330\\201C\\223\\004\\221\\227E+3;\\026f,\\304\\344\\222rI\\244\\252$(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\352\\274\\035\\341yu\\233\\264\\270\\270\\005l#p\\314\\177\\277\\203\\323\\374\\377\\000\\211\\036\\241\\254\\337[\\276\\236c\\261\\011\\366\\213\\006Yc\\0129h\\327\\001\\300\\375G\\341\\\\\\277\\204X\\277\\206l\\326"\\311\\2279\\012p\\011\\334~\\367\\265\\032\\205\\304\\220\\3111IQe\\022>Z6\\344\\243pF9\\300\\350\\007\\341^Mi:\\223w\\350z\\364i(A8\\365W\\023\\304\\262\\302\\232\\272j6W\\002Y%Q\\271cl\\355`\\000\\033\\271\\351\\217\\346}3Xr\\254\\272\\235\\373H\\226\\3443|\\336R6W=\\334\\364\\344\\365?\\316\\244\\211%\\272\\220E\\002u\\357\\330}kM\\355\\222\\316#g\\031\\314\\244\\346\\342Q\\324\\037\\371\\346=\\275}\\376\\231*>\\352K\\251R\\327^\\207\\025\\250\\332^]jM\\014K\\347\\204\\340\\030\\301\\33223\\324\\320\\236\\035\\274\\011\\272\\343d9\\373\\240\\220I\\374\\273Wh\\221\\307\\030\\307\\012\\276\\203\\275T\\324\\030\\371\\201p\\001\\3562N=\\271\\256\\332U$\\325\\226\\307\\024\\251E\\266\\331>\\221\\255\\352v\\226\\013\\014\\226\\351(\\213\\345\\022\\356\\033\\210\\372~?\\326\\265\\243\\324\\345\\276b\\260Z\\263\\316F6\\345T\\003\\323\\236O\\371\\355Y\\021\\355\\003\\315#\\234\\001\\355\\307\\025<2\\317d\\377\\000iI\\031>p\\030\\006\\347\\035\\376\\225\\311Z\\021\\346vGe\\032\\217f\\316\\317M\\323~\\305nZB$\\272~^M\\240n8\\300\\351\\355\\212\\255zP\\\\\\312\\371\\001cEBO`9\\317\\376=\\372S\\346\\325\\205\\226\\217\\024\\356<\\311[\\345A\\237\\275\\357\\364\\306\\017\\345\\353X\\227\\240I\\010\\270{\\207p\\330\\177\\235\\260\\244\\372\\205\\035\\372\\327\\034b\\333\\273:\\\\\\322\\330\\212\\372\\351.\\274\\270\\243\\017\\2059\\003\\2737A\\201\\214\\361\\237\\3075\\323_\\350\\361I\\360\\266\\342\\302\\355\\213:\\206\\222\\020P\\215\\223\\022H\\000\\364\\306\\016\\011\\35095\\227\\341\\335\\021\\245t\\276\\272\\033!V\\316\\030\\225\\300\\347\\251\\035\\311\\300\\002\\256k\\332\\224\\272\\225\\312X\\333\\341`\\210a\\266\\347\\037O\\363\\236~\\225\\351\\320\\207\\263W<\\372\\262u\\036\\247\\203\\321^\\201\\342o\\010,\\266m\\177a\\031[\\210\\362f\\213\\373\\343=W\\337\\271\\257?#\\007\\006\\273#%%\\241\\30188\\2730\\242\\212*\\211\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200-C\\246\\336\\\\D%\\212\\006d=\\030b\\244\\376\\306\\324\\177\\347\\321\\377\\000J\\3524\\021\\235\\016\\337\\337v\\177\\357\\243Z\\250\\270\\005\\2708\\366\\257:\\2462q\\223I-\\017J\\236\\012\\022\\212\\223oS\\204\\376\\302\\324\\363\\217\\261\\311\\372R\\377\\000`\\352\\237\\363\\347'\\351]\\340S\\236\\275\\373\\364\\251\\020\\014\\263d\\343\\246Ef\\361\\363\\354\\213\\372\\204;\\263\\317\\377\\000\\260uO\\371\\363\\223\\364\\243\\373\\013S\\377\\000\\2379?J\\364&\\031<\\021\\264\\364\\3158|\\334\\234g\\006\\227\\366\\205N\\310\\177P\\247\\335\\236y\\375\\201\\252\\347\\037b\\223\\323\\265!\\3205A\\326\\312O\\322\\275\\024\\020W\\234\\373S\\231\\270\\301\\351\\337>\\224\\277\\264*vA\\375\\237O\\273<\\337\\373\\013S\\377\\000\\2379?J?\\260\\365<\\343\\354rg\\323\\212\\364S\\324s\\363\\016\\2075\\011V\\003*3\\351Mf\\023\\354\\203\\352\\020\\356\\316#\\3737\\\\.X\\3033;`nb\\013q\\350O#\\360\\250\\016\\203\\252\\000\\177\\320\\337\\217q\\3765\\3501\\347q.\\0063R;c\\3459$\\363K\\353\\363[$\\037P\\203\\352\\317;\\376\\300\\325\\177\\347\\312N\\231\\355H4-P\\343\\026r\\034\\375+\\321C\\216I\\306:\\016y\\247aO\\000`g\\031\\365\\351G\\366\\205N\\310?\\263\\351\\367g\\235\\246\\205\\253#n\\026/\\323\\370\\200#\\3624\\035\\003Wf,l\\245$\\363\\222FO\\353^\\222\\017\\0358\\036\\203\\374\\377\\000\\223H\\255\\271\\271\\310'\\2474\\277\\264jvC\\376\\317\\207vy\\260\\360\\356\\254zYH\\177\\021\\3764\\277\\360\\216\\352\\374\\377\\000\\240\\311\\300\\317Q\\3765\\351j\\315\\324`\\020\\177\\375t\\252X\\263\\036s\\300\\305/\\355\\032\\235\\220\\277\\263\\351\\367g\\232\\177\\3027\\254\\177\\317\\204\\277\\247\\370\\323\\377\\000\\341\\036\\327Lb/\\261\\317\\263vvn\\030\\317\\2563^\\232\\233T\\344\\343\\007'\\2558\\260`\\000#4\\277\\264\\252vC\\376\\317\\247\\335\\236X|9\\253\\250\\311\\261\\220~#\\374i\\017\\207uq\\377\\000.2z\\366\\377\\000\\032\\365\\027\\007\\206\\003\\221\\214\\347\\2754(l\\343#\\362\\377\\000"\\237\\366\\225N\\310_\\331\\364\\373\\263\\314\\177\\341\\034\\325\\317\\374\\270\\311\\372\\177\\215\\003\\303\\232\\301\\031\\373\\014\\230\\372\\217\\361\\257R\\034\\023\\324\\250\\034s\\326\\241\\313(m\\2709\\341}\\270\\243\\373J\\247d?\\354\\372}\\331\\346\\203\\303z\\301\\351a'\\346?\\306\\201\\341\\275`\\234\\013\\0113\\365\\037\\343^\\235\\226\\333\\234\\214\\347\\222=)F{\\361\\333\\245/\\355*\\235\\220\\177g\\323\\356\\3170>\\032\\326A\\301\\260\\227\\364\\377\\000\\032?\\341\\032\\3263\\217\\260K\\353\\324\\177\\215z\\210\\371\\270\\340t\\2476\\303\\223\\220\\024\\016\\007\\255\\037\\332U; \\376\\317\\247\\335\\236Y\\377\\000\\010\\336\\261\\377\\000>\\022\\376\\237\\343G\\374#Z\\307\\374\\370I\\371\\217\\361\\257P\\344\\003\\363p;\\032hb\\253\\236\\01199?\\215\\037\\332U; \\376\\317\\247\\335\\236a\\377\\000\\010\\346\\257\\307\\372\\014\\234\\373\\217\\361\\247\\246\\201\\255\\300\\373\\343\\264\\2366\\037\\304\\254\\001\\376u\\351\\271\\030\\030''\\271\\351F;\\347<rsG\\366\\225N\\310?\\263\\351\\367g\\226\\235\\003U\\\\f\\312Q\\221\\221\\322\\220\\350:\\240\\353g'\\351^\\235&Wn\\356@9\\367\\252\\345rv\\200\\300\\221\\323\\332\\251f5;!\\177g\\323\\356\\3179\\032\\016\\250\\335,\\344?\\210\\240\\350:\\242\\365\\263\\220}q^\\222\\200\\007'\\030#\\256i\\247\\014\\037p#\\323\\276)\\377\\000h\\324\\354\\203\\373>\\237vy\\317\\366\\006\\251\\200~\\307&\\017NG\\370\\323\\217\\207upple\\007\\323\\212\\364=\\354"\\311\\\\\\014\\367\\372\\212\\231\\013\\226\\3119#\\237\\255'\\230\\324\\354\\203\\373>\\237vy\\251\\360\\366\\254\\006M\\214\\270\\365\\342\\223\\376\\021\\375W\\031\\373\\024\\237\\247\\370\\327\\245\\360\\027r\\343\\232\\215\\262\\011P\\300\\003\\307Z?\\264jvA\\375\\237O\\273<\\343\\376\\021\\375W\\376|\\244\\374\\307\\370\\320\\332\\006\\252\\277z\\312A\\371W\\243\\356\\014\\277\\206\\177\\372\\324\\303\\202\\312Fp\\007Pi\\377\\000hT\\354\\203\\373>\\237vy\\320\\320\\265Bp,\\344\\374\\305/\\366\\006\\253\\377\\000>R~\\225\\350c\\005K7\\006\\244-\\261w\\261\\302\\377\\000?\\245\\037\\332\\025; \\372\\205>\\354\\253\\340\\227\\236\\306\\315m\\257ah\\214nJ\\356\\003\\346Rs\\374\\363\\372V\\277\\210t\\311u\\007\\263\\201\\036\\024\\217\\314b\\223\\355\\347\\014r\\023\\003\\260\\035\\377\\000\\023\\311$\\343\\331E\\366\\275Gk\\3623\\330}\\356:V\\362\\351\\211\\270\\332\\302\\316\\221\\207\\311\\012\\307\\033\\200\\3130\\036\\274\\221\\370V5\\037\\277\\314\\367\\334\\350\\244\\275\\313-\\266$M:\\323B\\323\\344\\226B$(\\277 <on\\337\\\\\\361\\\\\\374h\\362\\273\\310\\377\\000\\353\\034\\227s\\352{\\324\\017!\\222\\365U\\313>\\337\\2241n\\001\\317a\\323\\324g\\333\\336\\265l\\241\\033\\334\\267A\\201\\232vpW{\\2639\\276wn\\210\\216\\330\\210\\204\\242H\\220\\225*w6~\\\\\\002\\304~<V\\\\\\020\\235B\\362Rz\\220H\\372\\326\\275\\364Q\\375\\232i\\243o\\336<\\306\\0223\\325v\\241\\377\\000\\032\\267\\244i\\313\\014\\001\\310\\3131\\316\\177\\012\\364)\\273A\\034\\362\\335\\243\\037L\\206#s\\032\\\\\\205\\332\\034\\034:\\356]\\303\\234\\021\\3378\\375+\\255}&\\013\\310\\246\\222\\341T2\\354\\362TF~pz\\235\\335\\000\\353\\307\\370\\326c\\331!\\236v`|\\260\\245\\333o\\\\\\001\\234\\217~\\343\\336\\266\\264\\233\\243sc\\033\\005 \\015\\214\\303\\037\\305\\203\\234}x\\374\\352\\264d\\263\\236\\271\\360\\362\\334\\254\\320\\211\\366\\316\\214\\2425\\224\\226v\\007\\270\\354\\007^kWO\\360\\365\\265\\245\\213\\317\\252\\222\\354\\254\\025\\\\\\267\\030\\347\\345U\\035I\\343\\323\\247\\324\\324\\367\\372\\2640\\336\\034\\037>\\351I\\033S\\375\\\\}\\261\\216\\347\\277<{\\032\\202\\326\\332\\363Y\\273-<\\216Uyf\\354\\007\\240\\364\\376T\\254\\202\\354\\232}Bk\\323\\036\\320\\303h\\3040\\356\\335\\203\\214\\027c\\334\\366\\035\\200\\251\\254tuU\\006Q\\201\\325\\275\\315jGo\\005\\242\\371p\\250\\317r;\\375i\\327N \\266\\311\\343\\212\\253w&\\375\\021I\\343{\\213\\261\\035\\254`\\272\\016\\027\\246\\343\\216\\231\\257\\037\\361W\\206\\245\\213X\\222]5<\\373Y\\276ue\\004`\\344\\344s\\356\\017=\\0163^\\301mr,4\\331o&*\\022e'\\314\\334>@\\010\\316{\\363\\310\\300\\356\\243\\327\\216\\036Y\\345\\276\\271\\232\\342Vb\\314K\\001\\237\\273\\350\\005D\\352:j\\353p\\344S\\321\\236w\\375\\205\\252d\\017\\261\\311\\223\\364\\246\\377\\000b\\3529\\307\\331\\037\\364\\257ARY@'\\2763\\330TS&[\\345$\\014\\001\\232\\347\\372\\364\\357f\\221\\267\\324i\\332\\367g\\005\\375\\215\\250\\377\\000\\317\\253\\376\\224\\343\\241\\352j2l\\344\\037\\225w1\\005\\022\\222\\3126\\216\\347\\212\\234;\\027\\000\\022G\\007u\\017\\035>\\310\\177P\\207vy\\334\\372U\\365\\264/,\\326\\354\\221\\2467\\022G\\034\\342\\251\\327y\\342F\\007E\\272\\004\\215\\343nO\\257\\314+\\203\\256\\3145gV\\034\\3228\\2614U)\\362\\240\\242\\212+\\240\\347\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;}\\000gD\\266\\036\\245\\277\\364#Z\\334c\\000\\344\\2360+3\\303\\240\\377\\000b[\\177\\300\\277\\364#Z\\312\\011nEx5\\337\\357%\\352\\317z\\217\\360\\343\\350\\207\\306\\243\\013\\217\\304\\342\\224\\003\\203\\337&\\204\\015\\300\\340\\001\\316jUM\\243$\\362N\\017\\275`\\331\\261\\003\\222\\010\\3343\\330\\012\\000\\300\\311\\317l\\373S\\376\\371\\316\\334\\372qI"\\220GP8\\007\\361\\240\\007m\\343\\031\\374\\351X\\362@\\034Q\\300U<\\364\\354(\\332\\033\\034\\236i\\000\\212\\0037\\\\{\\372\\323\\260U\\001\\034\\2202y\\342\\225T\\221\\374\\251\\350W'\\236)\\\\d92\\015\\300\\005\\316s\\232\\012\\266H\\344\\217\\255NT\\374\\330lsD\\2016\\217\\233\\240\\310\\350(\\270\\021\\355\\004\\343\\323\\361\\342\\235\\200P\\201\\372w\\240\\206`F0s\\316x\\251\\212\\0367\\036zg\\332\\220\\014+\\225\\030'\\217Jb\\202\\254\\270\\316H\\357S\\270\\332\\203\\327\\353LP\\007 r\\007ZI\\200\\230\\375\\313v8\\353\\216\\224\\340\\301]\\230\\372\\201J\\244r\\016\\001\\307Bj)NyRp}\\372Q\\270\\023+\\021\\3169=\\277\\255>2\\244z\\237Z\\204H@\\334\\307\\000\\364\\353\\305:7\\2121\\307\\335\\317sI\\240\\034\\356\\0008\\311\\374=iw\\226]\\270\\000\\001\\3169\\250\\244@\\347x\\312\\374\\334\\016\\200\\324\\340l\\364\\364\\346\\200\\032\\344\\034\\003\\221\\267\\217\\306\\243\\301\\034\\014\\347\\267=\\352l\\202\\304\\364\\342\\230\\270*0{zP\\200\\012\\223\\214\\023\\232r\\202\\000\\0358\\315\\000\\344\\347\\240\\243p\\012\\016>\\235\\351\\000\\204\\362H$\\037ZP\\344\\203\\203\\323\\261\\246\\347\\203\\206\\340\\373\\322n\\371;\\363\\351L\\011\\027\\234\\223\\234\\217\\347I\\260\\360?\\316)\\250\\337)\\311\\247\\207\\\\\\251\\316s\\324\\372\\322\\001@\\004\\234\\340sJ\\313\\375\\334g\\261<R\\020N\\007\\\\\\037J\\220\\002O\\312\\247\\336\\220\\312\\323)bN\\334\\214~\\002\\2531#\\2221\\3168\\305i\\025\\030P\\000$\\016s\\334UY\\342\\031-\\317?\\255TX\\025\\320+\\026`\\33768\\311\\246\\311\\221\\201\\264\\344u"\\224\\25620A#\\031"\\2238;O\\031<\\373\\326\\202\\035\\031%I\\306=\\263\\374\\251A\\313\\202\\307\\216\\231\\250\\2030a\\216\\247\\257\\267\\265H\\334\\034\\2779\\365\\244\\027\\036\\374\\307\\270\\216\\0019!\\2524\\214\\2020rs\\310\\307\\363\\377\\000=\\351\\350\\312_88\\366\\372R\\216\\035\\262pOc@\\014 \\354\\347\\247\\\\SX\\205\\317S\\203\\322\\200\\340z\\361\\327\\024\\236X\\306\\016\\017\\034\\342\\200\\0301\\222s\\324c&\\242\\231\\267\\310\\250\\207!GoZs(P6\\356+\\327\\216\\364\\221\\015\\331lr\\3075\\321J:\\334\\302\\263\\323\\227\\270\\373I^\\322\\345&U\\004\\241\\007\\004\\365\\307\\275nO\\253D-\\336Xcs#G\\345\\252\\036rI\\311$\\373\\177\\237Z\\313A\\225\\007b\\375M6e\\006\\026i2\\221\\016\\010Q\\202\\336\\302\\256II\\352e\\0318F\\310\\242\\261\\226\\273\\215U\\206\\360\\301\\233\\0038\\003\\267\\343\\375+L\\312\\261\\251E\\371\\244<\\340V}\\262\\310\\216\\322\\214!~\\270^\\336\\202\\254\\225Uc\\264\\021\\236rz\\223\\236\\264\\252M-\\012\\247M\\265q\\213\\024\\262\\313 \\306G\\015\\201\\364\\307\\364\\256\\257K\\210\\311c\\030\\003\\2221\\232\\345\\022\\341\\355.Q\\323\\030\\307L\\360k\\263\\322'\\006\\334:\\262ms\\2203\\367k\\272\\213\\346\\202l\\347\\252\\271d\\322\\033\\255*Y\\351\\246%\\307\\2317\\3109\\301\\347\\251\\377\\000>\\265sI\\203\\354:d2\\236\\024\\235\\355\\376\\350\\344\\376\\213Y\\272\\271\\373n\\263gnyT\\344\\343\\334\\217\\360\\255mr\\341m4I\\200\\030-\\027\\224\\203\\375\\3568\\3747V\\306]\\016;O\\2129\\032[\\273\\211vC\\031\\313\\310A<\\223\\216\\336\\346\\245{\\373\\302.m\\354nDp\\211r\\207$1\\300\\366\\365\\343\\203[66\\022G\\242K\\014H\\014\\322\\304\\312\\003p2A\\002\\271\\251\\222m:\\351\\222\\352 \\2308)#p\\353\\217\\257<\\001\\320\\376]\\262\\250\\344\\2666\\244\\242\\336\\247U\\240\\336\\274\\362\\213k\\271srrB\\3549\\300\\031$\\340\\000:\\217\\362i|A>\\345hP\\340w"\\252xe/\\274\\307\\324\\031-\\305\\274\\371\\014w|\\307\\034\\002\\270\\030\\3529\\347\\377\\000\\257[[\\273!\\346\\333\\200\\313\\270\\363W\\026\\371U\\314\\344\\227;\\261W\\304\\332\\204\\314 \\2601\\030\\025T4\\203x>a\\354N:t\\376\\276\\225\\235i\\036\\350\\237>\\231\\250ond\\3245K\\213\\231$Y\\013\\276\\003\\254a\\001\\003\\201\\362\\216\\235*\\305\\271\\314n\\242\\270\\353J\\354\\322\\222\\324\\245\\023\\235\\362\\246\\0067\\221\\317\\261<\\322\\314\\240\\000\\275\\301\\347#\\250\\251Z,\\334\\317\\216\\330?\\245D\\356J\\215\\255\\270\\343\\327\\326\\271\\245\\361\\350vC\\341"i\\012(\\005\\001\\007\\240\\315I\\023F\\312N\\320\\200s\\203\\374\\251\\201X\\253g\\005G^qLF\\336\\207\\007\\234\\360?\\235+\\024P\\327\\344\\007D\\271\\030\\012p\\247\\036\\2778\\256\\036\\273\\257\\020\\001\\375\\2019'\\0146\\200\\007\\177\\231z\\376U\\302\\327\\251\\201\\376\\033\\365\\377\\000#\\312\\307\\377\\000\\021z\\177\\230QE\\025\\332q\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\035\\367\\2078\\320-\\211\\351\\227\\357\\376\\321\\255@\\003\\021\\327=\\177\\245exhn\\321-\\367`\\017\\233\\377\\000B5\\260S\\030\\333\\327\\256O\\245|\\375\\177\\342\\313\\325\\236\\375\\037\\341\\307\\321\\002\\215\\244\\201\\323\\201\\221S\\260\\014\\271\\312\\221\\217Nj">PCg'\\257\\343R'\\011\\216\\240c\\030\\025\\2135\\031\\214\\020OOoJS\\203\\036\\323\\334t\\2438\\340\\365\\031\\306;S$a\\200\\024c\\360\\351@\\301A\\0308?J\\221>\\357\\012s\\326\\241R\\3001n\\325/\\007\\030\\353\\216\\3640\\036\\031W\\237_Q\\322\\234\\250\\006w\\016@\\365\\250\\3161\\226\\317\\327\\3374\\273\\360\\254s\\223\\217\\351R\\003]\\370\\345G\\003\\326\\241\\217s\\250^\\000\\037\\245Jr\\353\\270\\366\\352\\015K\\030\\0025\\\\\\361\\324S\\275\\220\\011\\034x\\033\\213\\022\\277J\\237\\234t\\031\\340\\012PF\\354\\364\\374(\\310g;\\216G\\257\\247\\371\\342\\241\\260\\030\\247q\\001\\211\\335\\351H\\333G\\251>\\376\\264\\371\\016\\006U~L\\375i\\201w\\256\\354\\201\\307\\\\S\\002)I1\\202\\016\\033\\326\\221N#%\\307Q\\305J\\360\\206\\005I\\307l\\212k\\240\\012\\243-\\307\\035)\\246\\200\\221U\\012\\371D\\026\\033z\\323C\\243\\262\\257\\226v\\372\\236\\335(\\211dG\\007;\\220\\343<\\236*\\301l\\022P\\014\\373\\201\\323\\353R\\300i;H\\343\\201\\323\\372Ry\\200\\203\\300&\\233)\\0148\\0041\\0038\\342\\240'i8 /LP\\220\\023\\014\\226\\003\\234\\373\\236\\224E\\220\\274\\362I\\317Z\\024\\364\\007&\\244'h\\3169\\002\\200\\016\\203\\236\\202\\243v\\343\\000r*L\\203\\203\\202I\\343\\236\\324\\326M\\314\\017\\003\\212\\020\\021\\202s\\236\\346\\220d\\347\\256i\\370l\\020\\012\\343\\337\\265"\\250*3\\237\\362i\\200\\305\\005\\\\\\034\\344T\\340\\020\\006@\\300\\355\\351M\\347\\200\\001\\374\\252P:\\037jM\\200\\001\\363`\\343\\247\\245J\\204\\343\\223\\220;\\346\\230\\253\\363e\\200\\343\\241\\305J\\240m\\300\\007\\037\\312\\245\\200\\234\\357 \\036\\002\\323\\031\\266\\362\\312I\\372T\\222\\002\\027\\201\\206\\307\\036\\202\\242\\311.z\\021\\237\\342\\037\\347\\322\\200+J\\243\\315\\347\\364\\250\\244\\350N8'?\\347\\374\\367\\253\\014\\340e\\224n"\\241\\227\\001\\203\\221\\271O?\\216:U\\240)\\264\\205dV \\216rG\\265L\\\\\\024C\\216\\017<\\363\\317\\365\\357Q\\262u'fG\\012?\\2559\\034\\206c\\3239=pG\\255h\\304>97\\003\\201\\234\\217_\\345Ow\\302\\206\\025Z"\\305q\\234\\014\\324\\310\\243\\313\\303\\021\\236\\231\\374\\3515`\\020\\006b88\\024\\025<s\\214\\217^\\377\\000\\347\\024\\365\\000\\257<\\216:\\016\\243\\212x\\033\\217\\353\\307\\350i\\\\evS\\206\\300\\007\\257CQ\\304V\\031\\274\\271\\016W=j\\333g\\007\\234\\014SY\\006Ha\\2707\\267\\025p\\251\\312D\\341\\314N6\\220\\011`\\020rMW\\226s4\\252v\\342$? =\\375\\352#l\\001$\\201\\2203O\\031\\035N\\006\\0068\\252\\224\\325\\264"4\\335\\365\\023q\\004\\356\\353F~}\\243\\337\\377\\000\\327A\\000\\261$\\361\\237\\241\\244Q\\265\\206psY\\232\\214t3\\334\\305\\017_^{WY\\245\\331\\223\\026\\\\\\340.\\006>\\225\\316\\351\\013\\347]\\311/^@\\025\\331\\333\\256\\333G\\003\\257O\\307\\025\\354R\\217,\\024O:\\254\\257&\\314\\3151<\\315m\\246\\004\\355\\014O$\\2361\\201\\374\\352_\\026\\336\\203\\025\\264\\000\\035\\230\\336\\336\\374\\221\\372\\020\\337\\235G\\245\\263}\\254*c\\346\\333\\237\\363\\370S\\265\\230\\204\\272\\354v\\303\\225H\\227\\237r3\\374\\332\\264\\276\\206}J\\266\\332\\315\\315\\267\\226\\012\\226^\\344\\367\\025\\265\\006\\241\\014\\323\\213\\221\\014b}\\233<\\302\\243p^\\270\\317\\\\f\\234`R\\271\\003\\003\\332\\241k5c\\222\\250~\\242\\215P\\264b\\313w\\035\\264{"\\210\\004\\034\\204A\\265Fy5\\310kw/4\\336c\\246\\024\\366\\007\\255u\\377\\000c>Y\\034\\005>\\225\\307\\353q\\220\\261\\205\\031\\371\\261\\372\\322m\\255J\\212]\\014\\330\\201\\010\\001\\353\\216~\\265~\\314|\\244\\237\\342l\\012\\242\\274\\3765\\241\\003\\0174F\\275\\021\\177Z\\340\\231\\255=\\310\\230\\223uq\\203\\2001\\232\\257\\202N\\0062\\017Q\\376~\\265 %\\357dP\\240\\357\\317_Q\\376MDA_\\275\\303\\177\\010'\\255c?\\210\\352\\246\\357\\020Q\\206\\351\\300\\317\\003\\326\\230\\011P>^z\\347\\037^\\324\\214\\377\\0007\\241\\310\\003\\234\\320\\010#<\\375?\\255+\\026gk\\271\\032\\035\\340\\354v{\\340\\356Z\\341\\353\\275\\361\\007\\374\\213\\367\\000g\\356\\246r?\\333Z\\340\\253\\324\\300\\377\\000\\015\\372\\376\\210\\362\\261\\377\\000\\304^\\237\\346\\024QEv\\234AE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\007y\\341\\302\\253\\241\\333\\022N~c\\377\\000\\217\\032\\331\\214\\214s\\223\\237\\322\\261<<q\\241[s\\375\\377\\000\\375\\010\\326\\344k\\270\\344\\014`v\\365\\257\\237\\257\\374Iz\\263\\337\\241\\3748\\372!\\340\\003\\264`\\220\\275\\351P\\235\\247\\234z\\234\\3207)\\350}?Zp\\017\\374 t\\315`l3<n \\002i\\256Wp \\356\\007\\246;\\3765+G\\220\\273\\272u\\305\\005F\\006q\\327\\214Qp R0\\0068\\340\\232y%\\224\\001\\232U@\\\\\\355\\003\\353\\351R\\200\\000S\\263\\236\\306\\206\\300\\201\\267\\347<c=\\2004\\3249p\\017\\004\\034m\\253\\004\\226\\004\\361\\236\\235j\\017,\\271\\340\\036\\235sM0$N\\031\\270\\343\\27156\\3009\\350;S!P\\0008\\353\\320\\036\\365#>:\\363\\237J\\207\\270\\022\\201\\236\\2143\\237ZB\\340\\215\\330\\343\\247\\326\\221\\203\\020\\240\\361\\323#\\333\\024\\240\\251\\\\\\0360r*@Lo\\332\\303;q\\320\\367\\245\\033\\263\\215\\271\\355\\212\\024\\355P\\024\\223\\317OJG\\311!\\216\\356:\\346\\230\\014g\\340u>\\370\\374\\251\\312\\274d\\343"\\206\\371y+\\327\\2574(=A\\3104\\000\\340\\3018\\332q\\327#\\237\\302\\234Ht\\001\\030\\014\\321\\260\\022\\2708\\242U\\013\\310nz\\232@FT\\001\\203\\311\\365\\246a@%\\217\\347NP\\340\\227$\\001\\375\\321Mq\\221\\214s\\353\\355T\\200g\\234\\006p0=\\177\\372\\324\\377\\0003s\\206\\353\\222\\000\\307\\363\\252\\343\\207\\310\\3502zU\\210\\327\\012@\\004\\020i\\264\\200\\221NA\\003\\220?\\012\\012\\356\\306G'\\265&\\320\\030`\\362O5(\\002\\240\\010\\202\\021\\301\\300\\367\\240\\015\\241Cg\\351R\\270\\331\\234\\237\\247\\2753\\004\\260,2=E\\027\\001\\020\\002\\330\\317^\\231\\251P(\\034\\343\\000r=\\252=\\270\\311\\030\\0079\\034S\\300\\332\\275\\017?\\255\\014\\011\\027\\201\\216\\000\\245S\\264\\201\\2203\\322\\231\\234\\0208\\347\\236i\\245\\362\\275\\261\\353H\\007\\360A\\306\\011\\376|\\324R\\226\\007\\031\\301\\317\\\\\\320\\256\\334\\016\\375\\270\\3155\\313m\\316H\\307\\353M \\000\\216\\204\\000F\\177Z\\211\\367\\2701\\234\\002\\007\\004\\017jvO'\\223\\317~)8'\\240\\310\\3655H\\012\\362\\022>e\\034\\343o\\246\\177\\3175Y\\331\\313\\036\\203\\034\\014\\343\\2778\\253S\\260\\344`\\221\\2368\\377\\000>\\225V@\\276^Nr~\\350\\003\\365\\255"&\\0106\\344\\262\\344t\\003\\247\\341V\\020\\214\\347\\031c\\352*(\\267H\\210Kd\\367\\307n\\325a\\006\\0063\\306rq\\376\\177\\316(\\223\\004<d\\221\\267\\005\\2621\\307\\347OU\\331\\234\\221\\352y\\353\\326\\230H\\033Xd\\201\\317\\035\\351\\353\\206\\371\\210\\301\\307J\\315\\214R\\006\\321\\202y\\364\\244<\\214\\377\\000\\017\\257\\245<\\361\\357\\337\\247J\\211\\270\\311\\035\\006F?\\302\\20426\\004\\220\\333\\2609?Jg\\314d\\307\\342*R\\011\\\\\\236:\\377\\000\\237\\322\\243\\333\\216\\2141\\216\\265HG%m\\257_\\033\\311d\\220oA\\222\\321\\0366\\373\\016\\365\\323Z]\\305yj%\\214\\365\\003\\203\\330\\373\\373\\326n\\257\\241\\245\\306\\353\\233U\\333p\\016J\\347\\207\\366\\372\\3266\\237\\250M\\247\\334\\264\\245H\\347l\\261\\225\\306\\177\\372\\342\\273\\345\\010V\\20755f\\2168\\316t\\245\\313=Q\\333h\\014\\336~\\334|\\273\\2075\\330\\223\\267M\\235\\2757\\177*\\340t\\215^\\331\\335\\314\\014\\333r0Xc?\\347\\372W^\\267E\\264\\353\\205-\\220T\\220\\177\\012\\355L\\346d\\372:\\005\\277\\347\\031\\000\\177\\350?\\375z\\255l\\355s\\255\\264\\216w\\035\\277\\310\\340Q\\243Le\\325\\356[\\241TU\\003\\360\\002\\215(\\252kSFX\\026RG\\327\\004\\323\\021\\321L\\236\\\\*\\243\\277Z\\201P\\261\\340U\\351Yr)\\201\\2219\\253\\261\\003\\036!\\025\\266OS\\\\=\\370\\337p\\221\\214}\\375\\340\\236\\301O\\377\\000^\\272\\373\\333\\320\\027\\034\\023\\330W),\\036^\\240['\\204\\001H=\\362s\\374\\205s\\342g\\313M\\263j\\021\\274\\2220\\024\\210\\256\\0361\\222\\021\\331T\\236\\340\\032\\271j\\245\\025\\234\\375\\347\\244\\236\\335~\\335"3\\251\\014\\025\\227\\035\\270\\301\\376B\\221\\004\\261J#n@\\350k\\217\\233\\23275\\345\\345\\220\\310\\243a;;\\002p\\347\\372\\325R\\300\\311\\265\\206\\342\\247\\201\\232\\327d\\005T\\203\\223\\353YR)\\363\\230(\\0349^~\\265\\022\\327Sj~\\356\\203\\031_~\\012\\3659\\311\\307Z6\\205\\316AS\\222H\\364\\245*X\\223\\320\\361\\267\\232@\\233\\210\\005\\371\\317\\347RjV\\361\\010#\\303wD\\360\\016\\336\\337\\355\\255p\\025\\350^!\\213o\\206.I\\317\\033?-\\353^{^\\226_\\3747\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\277\\016\\177\\310\\022\\330g?{\\217m\\306\\267b pH\\036\\225\\203\\341\\334\\035\\016\\337\\201\\374\\\\\\377\\000\\300\\215mD\\314\\330\\007\\006\\276\\177\\021\\374Iz\\263\\337\\241\\3748\\372"\\300*\\304\\000\\331\\031\\317<S\\207\\313\\310\\344\\236\\377\\000\\347\\351B\\234\\234\\220q\\327\\236\\264\\361\\234\\003\\217\\250\\025\\316\\315\\206\\216H\\340\\343\\267\\2657hV\\343\\030\\251J\\200I\\300\\037\\215&\\335\\303\\2468\\007\\236\\264\\256\\003\\025~c\\216\\375\\001\\251q\\301\\310\\246\\020\\333\\263\\327\\257\\343N#\\240=\\017\\250\\244\\300@\\274\\363\\214{zSPm\\003\\3278\\031\\364\\305\\014\\300d\\001\\301\\246\\256\\007\\314y\\367\\365\\377\\0008\\2461\\340\\234q\\370c\\326\\236\\250\\001?)=\\377\\000\\032b? \\022\\271\\307'=i\\356\\345\\027\\012rOA\\217\\326\\220\\211YNq\\2029\\342\\233\\200\\244\\234\\020\\177\\245"\\223\\265\\031\\217#\\256)Y\\376b:\\016\\234\\364\\244\\003db\\000\\000\\216}M5\\011*[#>\\336\\265\\034\\304\\271\\300 \\021\\353O\\014\\002c\\256)\\364\\002O\\367\\277\\032\\025\\310`9\\367\\246g$c\\276N\\015+\\014\\266\\007L\\344\\232,\\004\\244\\365\\340c\\024\\322wdg?Jh\\317C\\374\\351\\274\\2223\\237\\245+\\000\\340q\\306rh\\306\\376H\\351A\\003\\214\\034\\361\\23227\\3438=i\\200\\325D\\\\\\260\\344\\366'\\265.\\374\\200\\335\\305.B\\365\\034z\\366\\246\\2667t\\344\\361\\232\\000z6\\342\\254\\017jp\\347\\216\\240z\\212li\\261Ilz\\322\\203\\301\\003\\036\\346\\220\\017\\030n\\017Zi9\\347\\003\\351M\\033\\227\\257\\\\w\\244\\035F}(\\260\\023\\216y=\\273f\\230X\\017\\2550\\261\\003\\0352i\\240\\016q\\306h\\260\\022\\027\\366\\300\\365\\244\\334\\017\\031\\374\\015G\\234\\036\\303\\332\\203\\300\\351\\311\\364\\351N\\300;\\247^G\\000`\\322\\311\\323\\345\\034S\\010\\030\\003\\030\\031\\024\\222\\002G\\313\\311>\\375\\005\\000\\005\\207\\\\\\234\\226\\300\\367\\246\\343\\345=\\270\\353L\\306Skg\\035i\\373Y\\343$u\\350\\017\\364\\246\\003\\020\\226`\\033'>\\203\\275D\\320\\347*B\\364\\343\\324}j\\322\\220\\033\\337\\353K\\267s\\251 d\\366\\007\\245;\\330\\012`(\\001\\006~\\240\\375\\177\\306\\245(\\003\\015\\271\\007\\327\\036\\264\\342\\200\\222\\000\\330\\340\\364\\306\\007\\353H\\024\\237\\227\\236\\204\\214\\236\\017\\250\\375)\\334\\004V\\344\\203\\221\\203\\334\\323\\311\\316v\\2022y\\347\\363\\243\\345O\\224\\2022:\\376\\024\\340\\274\\362\\007<\\217J@*\\343`\\316\\017l\\365\\240/\\033\\206*ERA\\347\\034c$S\\014yf=8\\354\\177\\017\\360\\251\\002>w\\036\\376\\230\\030\\250\\377\\000\\204\\361\\301\\351\\317J\\260r\\2401\\004g\\221\\305@z\\020G~j\\220\\014\\015\\273\\220O\\312x=+\\234\\3618\\201|\\242\\255\\266\\354\\360q\\335\\016{\\364\\353\\374\\353\\244o\\223;s\\317j\\346\\255,\\345\\325u\\371%\\2362a\\215\\210\\303\\017\\310\\021\\372\\377\\000\\372\\353\\253\\012\\255.n\\306\\025\\365\\217/s2\\322\\342]>\\343x\\217|n\\234!8\\317\\036\\274\\343\\237J\\352l\\365[i\\241+\\015\\323\\024\\3071\\034\\251\\004\\216\\207\\377\\000\\255\\221Y7\\366\\017\\346a\\225\\227?:\\2021\\201\\350=\\277\\303\\362\\315\\226\\330#\\355m\\214\\300\\366\\357^\\257\\251\\346\\335\\305\\371\\035\\325\\266\\261.\\237\\250M+\\302W~6\\361\\301\\036\\2655\\306\\244?\\264>\\323n\\333I\\371\\260GL\\234\\377\\000Z\\344,o\\356`\\362\\355\\240O7z\\355\\216!\\030onBa\\211\\343\\277\\327\\336\\267\\244\\271\\270:\\002\\353/\\014Q\\037\\265}\\207\\3119 \\220\\244\\234g\\346\\037w\\271<\\324\\271X\\264\\323:\\017\\370J\\2320\\026pCz\\216ME'\\211\\274\\337\\270$\\306:\\271\\332+\\217\\271\\325\\235\\337tD@\\274aS\\007\\365 \\232\\316\\271\\324ZL\\371\\215\\221\\351\\236\\277\\205=\\305\\314\\221\\324j>'\\020d3\\0370s\\362\\366\\374\\377\\000\\255P\\322<B\\367\\2277>z\\262\\240\\\\\\304\\240\\347\\007\\323>\\2479\\315s\\276E\\345\\344\\261\\306cd\\014\\240\\251p@\\333\\353\\364\\343\\025\\277me\\035\\264*\\221\\203\\200rO\\251\\254k\\250\\270\\362\\276\\243\\204\\344\\245\\314\\213l\\322;y\\322\\037\\230\\367\\253+3l\\001\\276l\\372\\322O\\030X\\343Q\\327\\034\\325\\203\\002\\305\\002\\264\\207\\003nMr6\\254n\\223\\271P\\311">\\345r\\007\\241\\351I2\\027\\224\\273(R\\330#\\037\\347\\255Y\\216/\\231\\035\\306\\011\\354N6\\372g\\336\\241\\272o\\336\\0003\\376\\025\\224\\247wdoN\\233Z\\262\\251]\\316r\\000\\307\\031\\307?\\347\\374)c\\030\\227%r:\\363\\365\\243\\346\\337\\362\\365'\\034\\234\\012\\003\\021\\306\\334\\220s\\3654\\215H\\274I \\177\\015]\\343\\375\\217\\375\\014W\\235W\\241\\370\\224\\005\\360\\345\\320\\311'\\344\\316G\\373B\\274\\362\\275,\\277\\370O\\327\\364G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\274\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\357\\015\\014\\350v\\3351\\363\\177\\350F\\267"!\\000\\035sX^\\032 hV\\374\\343\\226\\377\\000\\320\\215m+m\\301<\\366\\305|\\366#\\370\\222\\365g\\277C\\370q\\364E\\200\\340g8\\351N\\022`u\\307\\037\\225U\\363\\010RY\\206\\011\\374:\\324\\371,\\000\\372~\\025\\203F\\304\\233\\271\\030\\347\\003\\351\\223G\\\\\\360x\\340SO'\\220\\007\\341\\355Lf*T\\023\\310\\307\\322\\225\\200y#y\\307\\003\\277\\275!\\313\\0363\\203\\332\\230H-\\273\\275(\\366=GJv\\030\\340\\006y\\034{\\320\\310=;\\234\\016\\224\\370\\372\\363\\234\\036\\177\\032q\\003v@\\332\\304w<\\012W\\021\\022)\\003;Ic\\316)pJ)\\340\\220{\\324\\216\\277/\\004n\\317\\006\\253\\000\\340\\2608$q\\234\\365\\241j\\004\\343$r@\\344\\322\\002\\273\\217\\\\\\216\\265\\023I\\263*9\\366\\307J|\\214\\011\\000\\034\\237N\\224X\\006\\354?y\\272\\372\\016qNF\\0161\\216MFH#h\\376\\037\\326\\214\\014\\015\\275\\0161LD\\241\\200\\224\\203\\216\\0074\\365\\371r@\\353L\\003q;\\207\\266E9\\024\\005'\\030\\036\\207\\322\\220\\013\\2779=\\000\\240\\020W"\\207C\\264 \\317\\2550\\215\\254\\001\\007\\030\\305\\000=\\210\\3654\\3020\\344\\200rGJbm`J\\261\\012*O/,A=zQ\\260\\002\\222\\331\\015\\200G\\030\\241\\321\\231\\301\\007\\012?Z\\000\\301\\000\\360(\\007k\\002wc\\034\\012\\000\\227pS\\3637_\\312\\221\\030\\022\\012\\364\\353\\232\\204\\270lq\\317\\265=x\\217\\320\\223J\\300) \\375\\323\\327\\200)\\333G\\\\\\324`\\005\\372\\323\\210\\300'<b\\200\\034X\\343\\247\\261\\244`\\017Q\\370S\\261\\221\\3079\\355Lo\\227 \\234P18\\031\\334I\\310\\246\\257@\\030\\216E\\014\\304\\214\\200H\\006\\2246\\343LB\\225R\\334\\037znN\\354s\\3374\\322T6A\\350;S<\\302\\240\\206\\004\\372\\016\\231\\242\\303\\270\\363\\316I\\357\\203\\365\\024\\344e\\031\\034\\221\\301\\007\\2550:\\236J1\\301\\307\\035\\352\\302\\2041\\227U\\333\\221\\365\\241\\200\\324\\000\\364\\000t\\031'\\257\\326\\246D\\347 \\2002s\\221\\237\\363\\326\\2300\\023\\034sS\\250]\\243 q\\306}=\\352\\033\\031\\002\\256\\346\\007\\345\\003\\034\\021L\\030\\022\\205\\000\\026\\307\\347\\326\\2348\\\\d\\372\\014\\214R\\004\\016I\\300\\355\\371u\\307\\363\\252\\020\\302K\\273d}\\3409\\030\\372\\361DaFr\\177\\012s\\014;\\223\\337\\202\\010\\376\\224\\204\\215\\375x#\\271\\240\\007\\014\\234\\036\\207\\374\\377\\000\\215\\007z\\225-\\201\\234\\366\\247\\362Fx\\307\\003\\326\\207\\000\\226\\030`\\247\\356\\347\\265 \\032S\\235\\303\\277|g\\025\\001\\035:\\216q\\323\\336\\245b\\347\\220q\\203\\305G\\264\\261\\311\\000\\200zu\\305R\\030\\326 /*\\017=)\\366\\227\\037f\\272\\216h\\366\\006S\\321\\323pa\\350Gz\\037 r9\\364\\367\\246\\250\\012G\\\\\\377\\000\\236\\325Q\\223\\213\\272&QMY\\235\\012\\\\\\332j\\261\\264ok\\024\\276_\\022&74~\\353\\237\\274>\\236\\331\\305s\\236 \\323t\\330bh\\341*gs\\201\\203\\312\\257|\\216\\243\\323\\007\\276pN\\015fd\\356`X\\344\\023\\327\\232\\177%\\267L\\\\\\223\\311-\\316k\\326\\366\\316\\336g\\230\\326\\272\\031\\321\\351\\220\\034\\211\\241,\\007\\000G9]\\374u;\\225\\275\\270\\004t\\367\\253\\267\\267\\027\\027:%\\276\\224\\236k,w-)\\226|\\006g *\\200\\024\\221\\2007rNNs\\201W\\225\\255\\233\\003*\\017c\\264\\323.!\\205P\\225\\232-\\270\\345Kb\\241V\\327\\336\\037\\263\\323C%<3<\\262\\200<\\367_d\\333\\237\\347Vf\\320[MdG\\201D\\214\\003\\003\\301a]\\015\\267\\210g\\213K[t\\010\\363\\257\\002w\\005\\230\\2578\\316x\\004{u\\300\\3105\\213q\\250Egt\\262\\335\\310\\346I\\016\\347*2G\\277\\371\\364\\355[:\\313h\\352\\305\\032k\\251\\012\\302\\320\\242\\311,\\033p>g\\\\\\267\\036\\377\\000\\375l\\365\\253q\\306\\256\\350\\007#\\255m\\333\\352~\\035kl}\\271\\030c\\253\\002\\244~\\030\\256r\\353R\\263\\203R\\222+v\\3148'\\314\\013\\205\\335\\376\\177\\017\\324\\236^iM\\273\\255N\\211B\\021WL\\322\\220\\306$\\016\\340\\2208\\000\\014\\344\\366\\0251\\205\\344q$\\270\\3341\\210\\307 g\\245Ik\\000\\214\\244\\322a\\234\\250!\\261\\214\\003\\351\\350i\\314\\025\\237 \\340\\001\\234c\\374\\373\\327\\024\\352\\364\\211\\321\\010ud\\022+m\\334\\244z\\340\\363\\307\\275P\\237\\370I\\037/\\277j\\320\\334K>A<\\361\\317\\362\\252\\327\\0309#\\031\\364<\\342\\246\\016\\314\\266Q\\301`1\\320\\347\\220i\\361\\250\\363B\\225\\372v\\251\\000+\\220\\277xr\\010\\357OB\\026\\343$pq\\234V\\215\\212\\305\\037\\024\\177\\310\\265rG\\012v\\343=~\\372\\327\\234W\\245x\\234c\\302\\267g$\\214\\246\\011\\357\\363\\256k\\315k\\324\\313\\277\\204\\375\\177\\310\\362\\263\\017\\342\\257O\\363\\012(\\242\\273\\316\\020\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\272\\360\\350\\316\\211o\\300?{\\360\\371\\215l)\\301\\371\\217S\\310\\037\\347\\336\\262<8\\177\\342Kl3\\317\\314q\\353\\363\\032\\330\\012F\\334\\340\\034\\365\\374k\\300\\257\\374Iz\\263\\337\\243\\3748\\372!\\356I\\3063\\215\\331\\344\\342\\254\\037\\231@\\035N3\\364\\252\\344ns\\351\\331{u\\2531\\355$\\014\\014\\001\\327\\257\\025\\316\\315\\207\\025 }\\356x\\034\\032`\\\\\\214\\236;\\361Sc\\035z\\365<S\\012\\222T\\014\\034\\366\\0257\\0020\\270a\\307\\031\\025"&\\006X\\001B\\215\\271\\310\\371\\201\\351C\\276\\324\\306\\177\\032\\000Pv\\200\\000\\031\\034\\217jp\\003\\030#'9$\\324\\001\\271\\364\\031\\251T\\356\\343''\\365\\241\\240\\011r\\252s\\221\\357\\351Q\\003\\301Vo\\233\\034\\221\\326\\236J\\225*\\254\\003\\237^\\325\\020\\302)\\311\\317\\365\\241\\001\\033>\\331\\200'y\\307?\\235\\030\\221\\3347Nx\\252\\305\\211\\221\\244\\3439\\350{{U\\325+"\\361\\313\\021\\376sV\\364\\020.\\314m\\007'9\\024\\357\\356*\\340\\036\\270\\305DT\\243g85.\\342y\\003\\006\\244\\011\\000\\334H$\\3434`\\347\\033O\\024\\001\\362r0M8\\002x$R\\030\\3269L\\206\\000\\375zS0[#$\\216\\234\\323\\230nc\\273\\267\\037Z\\013lnNH\\353\\216h\\020F\\212\\252p?\\012\\223\\035\\363\\307J\\215d\\343\\000`{S\\201\\310\\355\\307\\255\\014\\005d!\\301\\307ZN\\243\\333\\326\\234~\\360\\347\\267\\245 !\\234\\372\\016)\\000\\236^\\325\\351\\223\\237\\312\\234\\010<\\364\\2470\\344q\\364\\244\\034\\374\\247\\217\\360\\242\\340\\033G\\343M\\333\\327\\334S\\311\\300\\355\\322\\233\\223\\370\\320\\000\\006q\\202})\\304\\361\\302\\361\\3074\\335\\374\\375z\\232^\\204\\343\\256:\\177*\\000\\214\\374\\243\\345\\030\\003\\322\\235\\234\\257\\004g\\035qO\\015\\202v\\343#\\261\\246}\\347\\343\\2469\\346\\200#\\037.3\\203\\236\\207\\332\\230\\352ZA\\362\\200\\331\\253\\005\\001\\214\\022\\006q\\214\\212\\215P\\226\\004c\\270\\315;\\214rD\\011\\034\\360\\017#\\336\\245\\001P\\340t\\317\\347\\3054\\014\\215\\307\\234\\366\\035jbG\\251\\343\\267\\\\T\\260\\000H=\\011\\300\\353\\305J\\270\\333\\324\\343\\337\\322\\2402\\000\\300\\203\\330\\200\\011\\305L\\2101\\313\\202\\303\\234`\\012\\2262\\031B\\254[\\2018\\307Q\\330\\361\\315F\\245be\\015\\236\\371\\004\\366\\253D\\006\\214\\200w\\022x"\\242(\\025\\272\\177\\026?\\317\\345M0 _\\230\\003\\264r\\006\\001\\353Ml\\207Q\\217\\271\\306zS\\3353\\210\\3279=\\177\\300~t\\2618u\\030\\307\\\\\\177\\237\\312\\250C\\202\\237\\342R\\243\\326\\237\\3161\\200F9\\372\\324j\\270\\030\\343\\257q\\322\\245`1\\214\\016{v\\251\\002\\026]\\243\\004.\\007\\2454\\000\\273s\\316F\\017\\034S\\336B9 z\\340z\\322\\003\\307=:\\344\\232\\240\\030\\3708 \\362I\\343=j+\\207\\021\\333\\310\\354q\\201\\324z\\236\\237\\255K&L\\237.O\\245d\\370\\206F\\217J!\\0167H\\240\\375:\\343\\364\\255)G\\232i\\021R\\\\\\260l\\003\\307\\331\\2238\\343\\203M\\226\\346+t\\014\\362\\014\\021\\331\\177\\256+\\230\\022H\\027h\\225\\300\\364\\014qH\\010\\035@5\\353{\\016\\354\\363=\\257\\221\\3206\\255f\\212A\\231\\233=\\225K\\177<\\012\\213\\373j\\330\\014G\\275q\\353\\020\\347\\365\\254@\\024\\267J\\177\\313\\330\\361\\364\\252\\3661'\\3322\\364\\332\\304\\357\\237(\\355\\031\\341\\210\\301\\377\\000?\\215gJ\\317;\\227\\222Fv\\365c\\222is\\316\\005\\00265q\\214c\\2612\\223\\226\\344\\266\\3731\\223\\367\\275\\352\\345\\234bMN\\324>\\335\\236r\\344\\036A\\371\\207Z\\242\\250A\\316Eh\\351h\\323\\3536\\020\\3062\\315:\\177:SZ;\\016\\033\\253\\235\\333\\252?`O~s\\376zU\\031\\2123m^\\016y#\\232\\276\\312\\350\\345H\\351\\324b\\250LB\\271*\\244\\343\\247\\031\\257\\002;\\236\\311\\023dr}q\\221\\334\\372\\3242\\005\\330\\314H<\\014\\202}\\252l\\356\\034\\343\\214\\0168\\347\\374\\346\\242\\227\\036K`\\003\\221\\21685\\242\\006S,W\\2000\\030\\367\\376T\\350\\276bN>\\\\\\361Q\\341\\231x\\371\\207\\246*uR\\331X\\360\\253\\234\\363\\316NO\\377\\000^\\264d\\224\\374N\\373\\2745w\\236\\237&=\\276u\\2578\\257F\\361/\\036\\030\\272\\007\\257\\311\\327\\375\\345\\2579\\257S.\\376\\023\\365\\377\\000#\\312\\314?\\212\\275?\\314(\\242\\212\\3578B\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\016\\373\\303k\\235\\002\\003\\214\\223\\270\\177\\343\\306\\265\\324n\\013\\214`\\372VW\\206\\217\\374S\\326\\377\\000\\360.\\177\\340F\\265\\017\\337Q\\234\\000?*\\371\\372\\337\\305\\227\\253=\\372?\\303\\217\\242\\037\\234\\217\\233\\003\\035\\007\\255K\\007\\030<d~U\\022\\020A+\\333\\232r\\361\\236:\\0021X\\263Rgu\\034\\016s\\300\\305(ls\\236\\015BpI<\\343\\035)C\\022\\300\\266q\\266\\246\\303$\\311\\335\\217_\\320\\322\\266\\0168\\034t\\364\\246)\\014\\330n\\347\\212q\\302\\216\\016E \\020)\\\\dpx\\367\\241\\230\\025\\367\\007\\257j\\030\\222A \\220i\\252v\\214\\034\\023\\326\\230\\012\\373z\\257^\\340s\\212\\202a\\265r\\305\\263\\3375!\\005X\\270\\031?\\375zVM\\331\\014A=y\\344SZ\\001I2\\331\\340\\376\\037\\215[\\266\\0001\\007\\377\\000\\327L\\362J\\365\\003\\003\\221OT\\330\\025\\263\\317aM\\273\\210\\225\\206\\322=\\271\\372\\323T\\200\\241W9\\035\\315?wO\\322\\243\\010\\330,G\\275H\\022\\241\\344\\202N\\007zE$\\263n9\\301\\340Q\\214\\002W\\007\\330P\\240\\206\\307\\037Z@\\0147>\\007\\003\\2514\\3606\\234w4\\004\\307\\2474\\343\\327\\320w\\315\\027\\002&PN\\010\\000\\367\\247\\223\\205-\\327\\322\\214\\005\\301\\31098\\311\\245\\335\\301\\317Q@\\010\\011#\\000\\3658\\2449\\317N\\236\\224\\374\\340g\\362\\244\\030+\\352A\\311\\240\\007#\\222\\271>\\270\\247\\025#\\004R*t\\007\\201\\232^y\\306>\\225 4\\201\\232@\\017B)X\\340s\\301\\365\\244\\0161\\221\\310\\366\\246\\000zt\\357\\305\\030\\310\\351\\336\\205!\\226\\234A\\301 g\\332\\200\\032p[\\351\\315.T\\035\\277\\376\\272N9#\\003\\003\\031\\243\\312\\031\\334y\\307z\\000\\216Fe\\007\\034\\322&\\343\\031\\031 \\261\\316jB\\244\\267n{\\236\\224\\370\\243\\307\\314\\027w\\256E;\\35012W\\012\\001&\\230\\271.9\\347\\370\\263\\376}\\205X\\010G'\\030=\\005D\\024\\264\\277w8\\000\\363\\350*@~\\003\\201\\270\\237\\224|\\244\\016\\336\\225b6`\\252:\\203\\337\\024\\305\\\\\\003\\236\\270\\346\\232$\\371\\200#\\353\\315N\\343\\036\\024\\010\\316\\325\\351\\352i\\216\\313\\317\\000\\014g\\353\\364\\374\\351Y\\216\\315\\270\\000\\203\\223\\203\\326\\242Y<\\303\\265\\372\\216N{\\323H\\007\\025,T\\222A\\307\\\\\\372\\323\\010\\335\\037\\336\\301\\030\\311\\375:T\\212\\270b\\305\\270\\036\\235qMA\\227\\301_\\370\\021\\357\\307Jb\\033\\026wm$\\003\\320\\014v\\307Z{\\235\\271\\340s\\320v\\246\\010\\2126\\364\\313z\\216\\364\\207\\005\\317##\\216E\\0006A\\206\\030\\301\\311\\357\\351M?0\\316wg\\034\\365\\245s\\311\\030\\355\\30740\\001z\\340\\343\\031\\364\\246\\002`\\347=\\317sX\\236'\\223m\\264\\021ww\\334\\177\\017\\377\\000]m\\344\\202r\\247\\031\\301\\346\\271\\357\\023\\310\\014\\226\\321\\363\\362\\3569\\366<\\177\\354\\265\\323\\205W\\252\\2141.\\324\\331\\200:R\\342\\220t\\245&\\275\\223\\312\\005\\031\\220})[\\24578e\\245n\\264\\000\\2509\\317\\245L\\243#\\247\\265B\\010\\332*\\314C T\\261\\241\\376X\\333\\212\\336\\360l\\013&\\253\\366\\206\\300\\020`z\\340\\236\\377\\000\\226k\\023\\240\\256\\247\\302\\036Zi\\262\\312\\251\\363\\031v\\271\\365\\003\\004}:\\3265\\252(B\\354\\326\\234\\034\\345dv\\272\\205\\244W\\020\\265\\354\\021\\341\\217\\336\\214s\\203\\216@\\376u\\313NAm\\335\\312\\201\\205\\3435\\324\\351\\327\\253\\014\\333Kn\\211\\270`F\\007\\266k#Y\\323~\\315\\276\\346\\002\\036\\007=\\277\\204\\372\\037\\177\\363\\336\\274\\352\\324\\323\\367\\342u\\341\\3524\\375\\234\\214G\\001rs\\317^EC"\\225V\\357\\201\\270\\363\\357\\376x\\251\\227\\234\\377\\000\\343\\331=*\\031>s\\30677\\035\\253\\024u\\262\\240%d\\301\\307#\\031'\\245<\\310\\273\\206\\320=x\\250\\325\\231]\\201\\355\\324\\347\\250\\245t\\005\\202\\220\\001\\025\\255\\210+x\\235\\324\\370n\\345W\\222Jg?\\357\\012\\363\\272\\357\\274@\\270\\360\\345\\337\\314\\010\\001\\000\\307\\373\\313\\\\\\015z\\231z\\2657\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200=\\003\\303G\\036\\037\\266\\364%\\271\\317\\373f\\264X\\225|\\343\\240\\355Y\\376\\033\\037\\361OZw\\345\\270\\377\\000\\201\\232\\322+\\220\\331=\\275q\\315|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\017\\203\\356\\007\\004\\343\\234\\372z\\324\\252\\300\\266\\006\\000\\316G\\2555\\031|\\275\\240\\214\\223\\216:R\\250\\005\\016\\007\\003\\236\\265\\2135\\000@\\335\\201\\353\\237zp\\000\\014\\343\\22009\\246\\001\\214\\214\\217bi\\352A=GPi\\014#\\334I#\\267?\\2157;\\260A\\364\\247\\241\\352G\\177\\322\\242<\\2203\\333\\024\\000\\362\\331\\306\\017\\276i\\252C7\\271\\355\\216\\264}\\322\\017L\\232@\\016F\\016?\\012\\000z\\261''\\247\\362\\246yD7\\007\\003\\036\\264n\\035\\327\\251\\343\\035jE\\330\\240\\237S\\323\\024l!@l8$\\201\\332\\221O<\\200A>\\274\\323\\362J7# \\367=icQ\\215\\304\\236\\230\\244\\002l\\316\\341\\270\\364<z\\322\\250\\001\\000<\\363\\220OZ\\003\\205\\356:\\344\\366\\245\\017\\204\\034v\\357H\\004\\303t\\031\\311&\\227\\030\\004\\372\\234\\361J\\031\\227\\030\\003\\2569\\247\\026\\033\\271\\374(\\001\\312\\244\\200q\\316;\\320cf\\0079\\305\\001\\360@\\007\\024+\\234c\\250\\366\\251\\030\\334\\0169\\350)\\277t\\374\\247\\217j\\013g%y\\036\\224\\212HPO4\\3049\\201`6\\367\\353\\236\\324\\251\\203\\236N\\341\\336\\232\\252J\\234\\034\\036\\302\\234\\257\\202\\024\\360~\\235h\\002\\\\d\\322c\\320\\036iW$\\023\\306\\017_cJ\\007\\006\\244v"a\\220\\001\\346\\240T?\\2075a\\362\\244\\343\\247\\267\\255\\035\\206sUq\\014U\\307S\\333\\201O$\\252\\373c\\275\\033p\\0019\\003\\371Sq\\222I;x\\240\\007&Y}\\001\\247\\272|\\235:RE\\323\\003\\203\\333\\232z\\206a\\216\\247\\267\\245H\\304\\330O\\030\\031\\351\\232th\\304\\214\\221\\327\\363\\025")\\003\\346\\306}\\251\\3416d\\251\\341\\272\\212W\\001\\214\\003|\\207\\267>\\224\\236N\\321\\362\\236s\\372\\032\\225\\202\\356\\351\\264\\376X\\245n\\210TrO8=\\251\\\\ed\\310\\005T\\221\\236EBw\\344\\003\\214c\\007\\212\\235\\201\\204\\340d\\361\\202G\\2451\\201r[i#\\006\\251\\000\\201\\263\\363\\221\\327\\236)\\230\\371\\311\\310\\036\\376\\324\\375\\245B\\200\\275\\177J\\037fWy\\333\\234`\\216\\240\\363\\377\\000\\326\\246\\003C2\\222\\254\\016z\\012x\\310Pq\\333<\\236*)"`\\240\\256\\007~:\\212S&\\344\\000\\023\\300\\344\\012\\004+\\005$\\266N\\356\\234S[!@9=\\371\\245\\013\\301$e\\217\\030\\364\\246\\310C\\246\\006\\320p(\\000Q\\236\\011#\\216\\001\\343\\374\\367\\244~c\\003#\\007\\234\\367\\244\\031]\\247#\\246\\005\\005\\267'a\\223\\237R\\017\\371\\3050\\021\\201*\\017Z\\346|N\\002\\337@\\274\\377\\000\\252\\311\\037\\360#]WE9#\\203\\317J\\344\\274J\\333\\365E\\037\\335\\210)\\366\\344\\237\\353]x/\\342\\234\\330\\257\\341\\231\\024\\001\\232)\\3508\\257\\\\\\363\\006\\037\\274\\277QNq\\203Ls\\237\\316\\236\\34784\\000\\213\\311\\305\\\\\\210|\\265MO\\315W!\\373\\2652\\032\\036\\337t\\375+\\243\\360\\233\\225\\323\\347]\\300\\003'C\\364\\256q\\276\\343}+\\242\\360\\324e\\264\\266\\300\\343\\316\\353\\217a\\3765\\311\\214\\376\\013:\\260\\337\\304GC\\035\\312\\304\\345pI8\\006\\267\\254n!\\324-d\\205\\237 \\247\\316\\207\\034\\214u\\037\\355\\017\\324\\0169\\000\\036SqG;\\210 \\236\\343\\2559.\\245\\202U\\225>\\360\\354O\\371\\364\\257>\\215W\\015\\036\\307]Z*z\\255\\304\\3254\\371\\264\\313\\303\\033|\\310\\303tn\\335\\030{U\\011\\012\\206\\001z\\373\\365\\256\\241\\265\\013}v\\333\\354wR\\030\\344\\317\\312\\344\\214\\241\\347\\221\\352\\017q\\333\\237j\\345\\244\\006\\336f\\212A\\262H\\230\\253\\251<\\206\\007\\004\\037\\312\\252pI\\336;\\005)\\266\\255-\\321I\\362\\262\\234\\343\\251\\372\\032p\\005\\330\\225>\\371\\024\\311\\244\\334O\\034\\347\\267\\2751d\\340`d\\367\\377\\000\\032\\253hU\\312\\376$\\332|=1Q\\216\\023\\257\\373\\313\\\\\\005w\\336!?\\361O\\335\\014\\362\\002~?:\\327\\003^\\236\\003\\370o\\327\\374\\217/\\037\\374E\\351\\376aE\\024Wq\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\035\\311\\320-1\\376\\337\\376\\206\\325\\254\\234\\311\\216\\207\\002\\262<8q\\241[\\020~a\\273\\003\\376\\006ka8\\310=\\207\\257\\177\\255|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\001\\342>x$\\323\\267a\\\\\\343 \\361\\300\\351JFc\\35294\\310\\325P\\266\\321\\214\\363\\315djF\\357*\\002\\304\\241\\036\\2258?\\273\\313g\\257N\\365\\0139\\334@<\\036\\370\\355\\353I\\274\\347\\031\\347\\372Q`,\\307\\367\\000$\\361\\315'\\033\\300\\340\\340\\365\\307Z\\007\\3128\\344t\\036\\337\\347\\024\\252\\240\\000A\\351\\357R\\000\\0279\\364\\007\\031\\245\\362\\313\\015\\247\\200y\\353B\\234\\270\\365\\355N=1\\236OqHde9\\034\\361\\307~\\206\\206\\332\\304\\357br:z\\322\\375\\016\\017\\034\\324l\\030d\\216ri\\210X\\303\\025\\347\\216\\235MO\\203\\301n\\234\\344u\\342\\241L\\200\\000\\317<sS\\243\\025?/\\335\\377\\000=\\3510\\023\\007vs\\323\\236i\\300t\\371A\\004q\\232\\220\\221\\234\\021\\301\\244;\\010\\345\\271\\305+\\200\\303\\220\\015<.x\\036\\235\\251\\304q\\307JL`\\237Lv\\244\\002d\\216O_\\345K\\367c\\007\\241\\366\\245'\\003\\346\\031\\036\\324\\323\\327v\\336\\203\\037\\205\\0006>W\\323\\036\\224\\374w\\250\\274\\302[\\000r;\\012\\230\\246W\\251\\024\\330\\013\\221\\264\\036\\331\\353@ g\\237\\312\\232wy\\211\\201\\201\\334\\212r\\205R@\\373\\315\\311\\366\\251\\030\\002\\335A\\037JV~\\0118\\315\\012\\006\\341\\337\\006\\243f \\340\\363\\333?\\375j\\000\\013\\216F\\177\\012w8\\303.x\\344z\\325v\\033\\271=:u\\372\\324\\261\\2068^\\2120W\\003\\257\\371\\305U\\204H\\312x\\351\\216\\274\\012\\031\\030\\257\\342)\\330\\300\\307\\245<\\002S=9\\353Sq\\202\\242\\252`\\364<\\340\\324\\261r3\\217|\\032@\\204\\000G\\362\\251\\027-\\363\\001\\232\\226\\300\\010%:q\\353ND8\\347\\245&\\340\\270\\031\\033\\216x\\317&\\2348p\\334\\217\\306\\220\\001@\\337yI\\031\\364\\316\\005+\\302\\20123\\217\\257JP\\245G\\035:\\365\\246\\230\\222@pJ\\263~\\030\\244\\003\\012)=Kw\\332Fj4+\\274\\240\\343\\236\\377\\000\\347\\024\\217\\013\\356WR_\\034T\\213\\0239;A\\357\\200\\313\\214f\\230\\312\\351\\270\\347 \\216h`|\\316~\\246\\256\\244C\\356\\226$\\363\\305B\\352"R1\\306px\\247p!nJ\\252\\223\\273\\241\\250\\372\\277\\316\\024\\201\\223\\323\\257\\277\\351HC\\0318\\3163\\222q\\322\\234\\235p\\006Nq\\236\\225B\\034\\310\\253\\036Ks\\374 \\324 u>\\231\\034\\001\\212\\234\\260g*\\006H\\000\\203\\376\\177\\3174\\326M\\251\\201\\320\\222s\\326\\204\\004{r\\003\\036;\\376T\\3008\\300\\354F*c\\235\\271\\347\\261<\\322\\005\\343\\030\\317\\270\\355B` \\000\\2141\\306y \\232\\344<F\\310uR\\021\\211;\\001n:\\037\\377\\000V+\\257\\031\\340\\343\\220y\\346\\270\\337\\020\\017\\370\\235\\316zp\\277\\310Wf\\005~\\367\\344r\\342\\377\\000\\207\\3633;\\324\\3102*!\\326\\247\\215y\\025\\3533\\316Ey;\\323\\372\\306\\0156O\\274G\\245*\\037\\220\\257\\343L\\004^\\265n\\037\\273T\\373\\325\\270>\\355L\\201\\022\\236\\206\\272\\257\\012\\177\\310\\035\\363\\201\\211\\211\\377\\000\\307Er\\325\\321xZf\\026\\223"\\234\\004`\\377\\000\\230\\307\\364\\256LZ\\275\\026t\\341\\337\\357\\021\\266\\311\\311S\\301\\252\\222\\246\\336rH\\317\\246\\015[.\\244\\021\\2021\\316I\\367\\357Tn\\231\\223\\356\\020\\244\\364\\257&;\\236\\221Vm\\204\\344r\\335\\371\\351PJ\\3573\\273I+\\031\\031\\216\\347'$\\344\\365\\3159\\210V=Os\\3765\\023;3c\\256\\006?\\012\\350\\211,\\212L\\356\\311\\307\\035OcI\\036\\001\\004\\037\\316\\234\\313\\320\\221\\223\\317\\024\\315\\244\\000s\\223\\272\\254\\222\\226\\274\\017\\366\\005\\317\\037\\334$\\377\\000\\300\\226\\270j\\356\\274@\\341\\264+\\234\\034d/\\037\\360%\\256\\026\\275<\\017\\360\\337\\257\\371\\036^;\\370\\213\\323\\374\\302\\212(\\256\\323\\210(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\357\\2746?\\342Ans\\311\\335\\307\\374\\014\\326\\240\\345X\\001\\236++\\303{\\277\\260\\255\\266\\365%\\272\\377\\000\\274kb0\\0306;\\216\\375\\215|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\016\\\\\\210\\271\\317\\034})\\252@<\\026\\247\\200\\246&9\\367\\365\\346\\233\\323'\\216;V&\\244l\\003\\003\\214\\363\\322\\224!'<\\221\\214\\202hT\\347'\\247Lb\\245'\\236;\\212m\\200(\\347\\223\\201\\237\\363\\374\\205*\\223\\306Np?\\012\\177E\\372\\361@\\030\\\\w\\002\\244c\\024\\202\\370\\347\\036\\3650\\\\)\\301\\034q\\212\\207i\\3162?\\016\\364\\360\\330<\\203\\354)0\\020\\214\\366\\347\\240\\305D\\370\\351\\315N\\304\\023\\214\\217\\361\\240\\250\\356W8<\\232w\\001\\261\\340tn:{T\\300`\\017\\177\\326\\241T;\\3169\\031\\344\\212\\235A#$\\217\\255&\\002\\221\\205\\306H\\317Zh\\214\\267L\\016s\\212\\223\\004\\022M;\\250<\\014}*n\\000\\314@\\332\\0178\\240 \\340\\202z\\347\\024\\244pq\\374\\350\\004w\\372c\\322\\220\\010\\331\\311\\355\\323\\2656I0\\334t\\372u\\247\\0207rM6M\\337\\300:\\203B\\002.\\014\\204\\347\\203\\351\\370\\323\\212y\\247\\357\\235\\240v\\245P\\334\\007\\364\\340\\346\\245\\331\\203\\264p3\\311\\246\\330\\010F\\010+\\310\\306)\\204\\204c\\225\\300\\300$\\323\\360\\300\\356l`\\016q\\336\\231\\260\\263r{\\367\\356) \\035\\346*\\256\\342\\033\\031\\300\\365\\241\\260X\\200\\271#\\237\\233\\275Bd\\345\\262\\240\\014\\340c\\250\\246\\217\\232Uu$\\2208\\311\\343\\245;\\000\\360\\035\\233 `w\\343\\374\\363S\\230HC\\263\\214\\216\\0014\\345&M\\304\\360==jRC\\01468\\343\\007\\212M\\201\\032\\306\\337*\\261<py\\352*^\\235\\017A\\234S\\372s\\200F=\\177Z\\204\\246\\\\\\016\\011\\034\\034T\\2012\\347'~\\000\\316(\\313*\\261S\\3162>\\264\\205I\\014\\007\\256\\177\\012G`\\004ci \\344ds\\376\\177\\372\\324\\200@T\\315\\2200I\\306\\010\\353R+\\023\\220x \\373u\\250Ws\\214.v\\216\\016H\\300\\353V\\342\\210\\225\\332p0>\\360\\357\\306(c\\024\\251\\306\\024q\\301\\372Tk\\034\\2079\\003vx\\307q\\353S\\202#EA\\214\\364\\345\\262i\\241\\211\\037t\\344g\\034v\\244\\0036\\262\\026\\022K\\222O\\360\\236\\242\\222'\\000\\223\\267\\234\\363\\333\\336\\234\\350\\257\\225\\317\\030\\300\\307\\370\\323\\327\\021\\347\\256{\\374\\277J\\004A3\\311\\346\\001\\032\\206\\004\\200Fq\\3056PYUx-\\367\\215Zx\\225\\233\\334\\216q\\364\\250\\314e\\237\\202@\\035\\016(\\270\\024DBM\\304\\021\\364\\365\\241UB\\365\\351\\370f\\254\\010H|\\214\\016\\330\\31740T\\016p\\001\\007 c\\364\\252\\270\\306\\014"\\345\\206H\\344\\346\\232\\343sv<t\\007\\247\\265)\\310\\213\\004d\\3658\\246\\034\\237\\230\\256\\011\\000t\\353@\\204\\341\\227\\240\\003?\\2253\\220\\245\\217\\247LqR\\005\\012\\243~\\027<\\021Q\\250\\336\\204\\202v\\373S\\000\\301'r\\236\\304\\327\\033\\342\\020WY\\233#\\357*\\343\\362\\025\\331\\341\\267\\003\\216\\376\\234W'\\342\\300\\006\\255\\031P\\0000\\251\\375Z\\273p/\\367\\277#\\233\\027\\3743\\021~\\360\\253q\\016j\\254c-WP`W\\253#\\316\\211NQ\\373\\306\\245\\021\\272\\252JF\\022L\\240>\\343\\007\\372\\212Y\\000\\332\\304\\372\\232\\350/\\264\\361\\027\\203l%\\000\\027YD\\254\\336\\201\\307\\377\\000\\263Q:\\212\\016)\\365v*0r\\273\\354s}\\352\\314\\035*\\273\\2141\\253\\026\\377\\000t\\325\\275\\210[\\223\\326\\377\\000\\205\\225\\376\\315r\\352\\271\\311\\013\\317N\\365\\201]\\027\\207\\233\\032K\\001\\367\\274\\366\\376K\\\\\\230\\277\\3413\\247\\016\\277x\\215Wp\\300\\206\\310>\\370\\254\\367$\\271\\015\\221\\214\\361\\375?\\235_g\\014\\330#\\0319\\317\\255g\\312\\205\\\\\\257$\\343\\217J\\362\\340zD`\\200\\276\\2479<\\367\\250X\\016\\031F\\033w\\347R\\242\\362p\\033\\034\\001Q\\037\\231\\272\\214\\366\\377\\000\\032\\325\\011\\210q\\2203\\305B\\261\\014pr\\011\\007\\024d\\034\\344\\202;\\037\\302\\236\\212\\331Q\\330\\365\\031\\307\\353W\\261;\\231\\236 \\300\\321.\\000\\035vs\\377\\000\\002\\025\\304Wk\\257\\357\\032E\\310\\317\\313\\205\\377\\000\\320\\205qU\\352\\340\\277\\206\\375O+\\035\\374E\\351\\376aE\\024Wa\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\033\\031\\320m\\273\\037\\234\\363\\376\\361\\255E\\227\\022\\355\\350;\\266:\\363\\376\\177:\\314\\360\\326\\177\\260m\\261\\376\\327\\036\\2779\\2556\\034n\\034\\036\\275\\005|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\022\\237\\365c8\\364?\\322\\201\\311\\012:\\361\\370\\366\\250c-\\345e\\260[\\246?\\317\\341R3\\025\\371v\\364\\031'5\\223F\\242\\217\\\\\\377\\000\\365\\352@\\006x\\356}x\\250\\300\\031n\\370\\306>\\2658NG\\256F9\\251c\\032{\\360FMI\\267)\\310\\004\\001\\353L\\350@\\350\\005X\\030#\\221\\202z{T\\261\\220\\225\\030\\004Tm\\311\\004\\016=*\\323\\000\\006z}j&Q\\236F8\\305\\011\\210\\200dw\\351\\353\\336\\23470\\034\\217^\\234\\321\\267\\234t>\\275\\005*\\202\\007#\\234U\\000\\271\\307\\000\\365\\251\\211ld\\000N{\\324 \\220\\333\\233\\257z\\231I\\316T\\037S\\232\\226\\003\\3019\\3032\\237\\366z\\323\\307^N\\0069\\250\\326N9\\3508\\034\\323\\367\\002;\\324\\260\\036@\\307ny\\246\\340\\2059"\\224{\\367\\246\\226l\\035\\334c\\234\\343\\212@+dg\\326\\231 ;\\371\\343\\003\\203\\351N\\035r\\240u\\306}*7\\334N6\\034\\016\\234b\\232\\001W\\337\\030\\366\\251G'\\217\\361\\250\\321A\\007\\013\\217\\247Js~\\355N\\033\\223\\323&\\200\\024n\\311'\\277zC\\200\\240\\363\\302\\375EG(\\221\\200U\\316}\\272{\\320\\310\\242\\022\\233\\260y\\033\\251\\330\\006\\221\\031\\347#\\257\\343\\326\\243WQ\\200\\250\\330\\355\\3061J\\274\\343\\003\\251\\364\\357N\\316\\027\\346\\355\\351L\\007#\\221\\363\\034~\\\\\\325\\220\\003\\250<\\025=I<\\212\\250\\024\\266\\000\\344s\\216\\335\\252\\304\\012\\254\\312\\216r@\\357\\307\\371\\353I\\201d\\376\\354\\3412\\017@\\015H\\261\\003\\2228\\371z\\367\\317\\2455P\\031A<v\\347\\353O\\226TO\\274\\016s\\363zV`D\\315\\211T\\002A+\\216~\\275jFF\\223j\\216\\024rI\\025\\001\\036d\\203\\250\\007\\201V\\331\\227vr\\000\\366\\374;P\\300\\211!\\216\\030\\366\\2530\\005\\262OO\\363\\322\\244F\\014\\3465$`pr\\015B\\203\\0231c\\2362\\253\\216\\265>\\304E \\360X\\362q\\202qI\\200\\035\\252\\243\\000u\\347w\\265:(\\333hf \\356'$\\372v\\246\\034\\025\\3712On\\337Zqv\\362\\263\\333=\\017oj\\000P\\233\\216\\325\\357\\372\\324\\330\\033@'\\222q\\310\\024\\330\\335D#$t\\3019\\307\\341R\\205\\371F8\\3079\\035\\363I\\201Y\\260\\011\\034\\000;\\3434+\\344|\\247\\357{t\\245t\\311\\333\\234\\237C\\3764\\322B\\222\\335@\\357\\236\\324\\0000,\\371\\005@\\007\\245@\\355\\221\\200\\012\\234\\014qN'\\007\\004\\347\\237N\\365\\033\\037\\233 \\340s\\201T\\2063\\201\\307P\\3039\\306)\\301\\224\\002\\010'\\217L\\347\\265&y\\003\\004g\\2758\\217\\335\\214pq\\372\\177\\221L\\006I\\363\\025\\031\\300\\034\\221\\237jE@\\252\\025\\007\\312\\006=\\215.\\354\\002\\010\\365\\033z\\321\\221\\267\\345\\003\\034c\\035~\\264\\010aP\\2520q\\323\\025\\310\\370\\257\\215J\\023\\236\\014#\\037\\367\\323W`H\\300\\004\\344W!\\342\\354\\215B\\034\\216<\\241\\203\\370\\232\\354\\301\\177\\024\\346\\305\\177\\014\\311\\205q\\317\\255Y\\252\\3610\\332\\006y\\251\\035\\261^\\263\\334\\363\\321\\014\\303\\026\\344\\373\\327\\243\\21316\\203\\035\\221Psj#\\\\\\256p\\333@\\317\\347^p\\331c\\027\\246\\341\\232\\365T\\012B\\263\\022\\024\\216=\\372\\017\\347\\\\\\030\\3715\\313c\\253\\012\\223\\346<\\252u\\001\\262;\\323\\255\\217$S\\365!\\267R\\271\\\\\\001\\373\\3068^\\203\\236\\237\\207J\\212\\335\\200\\223\\236\\342\\275\\004\\357\\033\\234\\215ZV-\\364\\025\\320\\370p\\343O\\224s\\304\\244\\036=\\205s\\315\\367k\\242\\360\\372\\025\\322\\313\\236\\222J][\\351\\307\\364\\256<g\\360\\216\\2347\\361\\015WV\\340s\\234\\347\\377\\000\\255U\\335\\010\\335\\223\\301\\356?\\317\\275N\\345@\\300'\\035\\215W\\232M\\244\\251\\007\\327\\332\\274\\264z%y\\0300!Tn\\307^\\231\\252\\222\\025\\030\\341\\277\\016\\242\\254\\270\\334\\011\\034}j\\263\\002\\010\\306N8\\034\\364\\255\\242K"\\3068\\344\\343\\326\\234\\011\\353\\216>\\2249\\310\\3160q\\232hf\\333\\202\\007\\025\\241%\\017\\020\\374\\332%\\313q\\316\\337\\375\\010W\\015]\\256\\274\\300\\350\\227\\000\\017\\356\\377\\000\\350B\\270\\252\\3650?\\303~\\277\\344yx\\357\\342/O\\363\\012(\\242\\273\\016 \\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\275\\360\\340?\\3306\\304\\017\\357\\017\\374y\\253\\\\\\000w\\000z\\360N1\\212\\312\\360\\336?\\341\\037\\267\\311?\\305\\333\\375\\263[\\201@\\012\\275\\010\\374k\\347\\253\\277\\336K\\325\\237AG\\370q\\364Dq\\305\\362\\226\\352\\270\\000\\221\\330\\366\\251\\012\\201(l|\\303\\214\\343\\212\\010%\\260\\033\\345\\307B{RD\\314[i\\310#\\265bh#\\2569\\342\\236\\214\\300c'\\247P?\\317\\371\\024\\216\\270\\007\\275\\013\\264\\221\\2001\\355@\\311\\011\\311\\317\\034\\372\\034\\032\\221Wq\\351\\310\\003\\234\\323\\024\\002G<\\377\\000J\\2226\\303\\214\\257\\346:T0\\036\\221\\226\\310#\\251\\307\\006\\220\\307\\273=\\015L\\213\\203\\216\\247\\257\\025;(\\306B\\217\\360\\251\\270\\031\\2466\\007\\356\\234{\\016\\364\\341\\013\\267\\247\\345V\\317\\000\\360A\\353K\\356}sO\\230\\012\\342\\335G\\336\\347\\256)Z1\\214c\\223\\332\\254\\221\\200\\006x\\250\\311\\004\\347\\320v\\377\\000?ZW\\002\\017/\\000\\001\\222}\\277\\012xRI'\\217\\306\\234x'\\203I\\301\\004\\367=(\\001\\010\\302\\360qHy\\\\\\001\\237\\353O#\\2363\\217Z0\\010\\351\\323\\255\\000F[\\2660=i7\\035\\330#\\2029\\365\\247\\262\\256\\334\\037\\255&\\016\\354~t\\300\\027\\034c\\223\\336\\206M\\307\\034u\\240\\266\\006\\007\\\\SyV!H\\305\\000\\036c\\200\\000\\374\\361\\323\\377\\000\\257C!\\012[\\222\\336\\203\\2659\\272\\234\\201\\2029\\244r\\344aI\\305\\000D\\027\\017\\203\\323\\351\\376}iv\\022\\271\\000\\236;u\\247\\347#\\033\\261\\326\\247]\\3040\\007\\234qE\\300\\257\\345\\237\\272xf\\247F\\205\\244\\007\\257b\\304\\366\\343\\247\\345S\\035\\210\\252\\254\\271lv\\367\\353O\\021\\224\\\\\\200\\002\\2578\\315+\\200\\366q\\024M&\\336\\203\\257\\343P\\263\\270*B\\235\\275\\300\\035\\271\\367\\244y\\377\\000{\\345\\222vw\\307\\255O\\032|\\303\\034\\234\\372\\365\\245\\260\\010\\0232.\\345\\344s\\365\\025cf>\\352.1\\203\\350j\\035\\333X:\\020K\\014\\355#\\222{\\325\\245E`3\\221\\2360sR\\300\\257 .0N\\334\\364\\365\\244\\004\\011q\\234\\234u\\353\\237z\\260T\\354\\012:\\347\\214{\\373}=i\\253\\036\\334\\347\\036\\243\\353E\\300M\\200\\021\\2343g\\214\\372f\\202\\027\\314\\312\\201\\203\\200r=)\\314\\024\\025=\\217_\\\\R\\031\\025\\270#\\217\\247\\347H\\001\\012\\262\\365'\\036\\334\\177\\236\\2254y\\330\\0118\\030\\317\\036\\365\\036\\325\\316\\320@\\343<\\372\\323^p\\012\\204$s\\200F3@\\016\\332_w\\0319\\344\\236I\\246:\\222A\\310\\344\\377\\000\\017j{\\037\\227j\\200}s\\370S$rG\\031\\366\\024\\001\\003\\235\\273\\210\\343\\266G\\341Q+dm\\003\\036\\224\\347\\335\\367[\\214\\216;\\346\\232X\\026S\\225\\300\\343\\216\\325v\\030\\364\\377\\000Z\\255\\2163\\214z\\320_,\\247\\003\\237N\\324\\000r\\270 \\017\\245*\\214\\202\\331$\\214pO\\255\\000#tf\\306\\0279\\246\\016I\\3162\\017zt\\231g\\332FF{t\\246\\220\\240\\340ps\\214\\347\\332\\201\\006\\320O'\\212\\345\\374d\\240\\233F\\357\\363/\\362\\256\\241~\\357\\\\\\363\\322\\271\\257\\030!\\362\\355\\033\\370A#>\\365\\323\\203\\37620\\304\\377\\000\\011\\234\\312\\203\\212\\232L\\371c\\326\\240SVO\\372\\257\\302\\275\\246yh|\\010\\032hT\\217\\342\\003\\365\\257PrT\\2258\\345\\211\\343\\217Z\\363+\\177\\370\\370\\207\\375\\365\\376u\\351R|\\304\\000\\006H\\343-\\214W\\231\\230}\\237\\231\\337\\204\\352y\\267\\210Qb\\361\\005\\342\\2466\\371\\244\\3753T#\\341\\305\\\\\\327\\031d\\326.6\\223\\200q\\317\\260\\305QRA\\353^\\225?\\201z\\034U>6X\\232c\\267\\003\\212\\352<?3>\\223\\345\\347\\210\\344!W=\\007\\007\\371\\346\\271\\025V\\226@:\\327G\\341\\311\\002\\315sns\\226P\\301\\2751\\377\\000\\355~\\225\\317\\213\\215\\351?#l4\\255P\\336\\224\\356C\\203\\203\\353\\217\\322\\252\\272\\234\\234\\267\\315\\236\\306\\254?\\335\\031\\030<\\344b\\252\\267\\246z\\016+\\312\\211\\351\\210FG8\\007\\035\\252\\026\\000\\034\\234\\023\\320\\373\\232\\225\\363\\260\\343\\003\\336\\252\\237\\233#'\\203\\370\\012\\322(L\\211\\333#\\337\\260\\246\\206a\\306x\\317C\\351K!\\000\\374\\304\\373SG$\\234\\217\\306\\26533\\365\\321\\215\\022|\\020~\\356\\177\\357\\241\\\\]v\\272\\360o\\354K\\202I\\307\\312\\177\\361\\341\\\\Uzx/\\341\\277S\\313\\307\\177\\021z\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336xl\\377\\000\\304\\212\\337\\003$n\\343\\327\\3465\\265\\0318\\306\\354\\343\\214\\232\\304\\360\\373\\021\\241Z`\\003\\367\\307\\376<kZ0\\304\\000\\307\\007\\034\\036\\275k\\300\\256\\277y/V{\\364?\\207\\037DZ\\016\\027\\346'\\201\\317\\034\\346\\234\\256\\2620?\\305\\364\\250\\220\\215\\204\\037_\\2552=\\306\\\\\\250\\3439\\351\\332\\271\\354l[p\\016q\\220@\\340\\373TE\\006\\375\\303\\2778\\317\\025!9R\\002\\340\\220)\\254;\\037\\312\\222\\001\\252w?\\\\c\\270\\247\\022\\352Ag9\\311\\355\\357N\\306\\356\\234\\234\\214\\261\\372Se\\300^\\247'\\216h\\031f\\031\\361&\\017Q\\351\\370\\377\\000\\205Y2d\\361\\221\\305g\\306\\243#\\220O9\\317z\\237q>\\307\\030\\250kQ\\017\\335\\236\\244\\036\\377\\000Jpl\\220H w\\003\\275D\\0160H\\366\\006\\236\\215\\270\\034\\016=\\315+\\0016IA\\216\\240s\\357MbH\\300<\\216\\274\\322\\207\\000}\\357\\322\\220r\\007 \\346\\220\\011\\267\\000c\\257ZNA'h\\247\\034\\000F\\177\\372\\324\\3020\\331\\307\\326\\230\\017\\316\\000>\\335\\251\\240\\223\\317\\343A\\311\\317\\035E7;F\\354|\\264\\000\\244\\221\\337?Ni\\000'\\030\\243\\234{\\364\\245\\317\\250\\372\\320\\000\\023\\203\\353\\365\\243\\243t\\036\\336\\275)\\300\\027\\302\\364\\356h`\\011\\340s\\330b\\200#a\\225'i8\\340\\342\\233\\273\\0121\\300\\305H\\331)\\267\\035{c\\2653\\311\\375\\346T\\236\\224\\300_\\230\\016\\024\\034\\365\\247\\250\\314 n#\\236NzR(\\004\\220zg'\\025'\\312[$q\\301\\244\\003a\\215\\324\\234\\221\\222N\\006j\\362m\\034?\\031\\033rG\\351U\\025T\\2000\\013\\251\\343\\035\\352\\332\\220\\361cqb\\244\\036jX\\025\\246\\204)\\004\\217\\227\\031\\317\\275.\\034n%\\302\\216\\304v\\253\\022\\375\\334.\\012\\236\\203\\336\\253\\256\\346\\223,w\\01603E\\306Y\\210\\007o\\233\\3468\\310$\\367\\241b\\332\\373\\224d\\2227du\\035\\350\\302\\250\\001\\230\\227\\354\\005*H\\371\\034\\356\\\\\\014c\\257N\\277\\255H\\211\\312\\027\\313n\\000\\221\\370\\323\\034\\267\\226\\271\\340\\016HS\\203R\\037\\365\\177(-\\202q\\217\\363\\353H\\255\\214\\202\\270\\317j@F\\334\\266}\\007SQ\\362\\024\\034\\341G\\030\\307N\\365),[\\031#\\217L\\367\\246\\010\\233\\267\\013\\337>\\264\\300\\201\\306\\016>m\\330# \\3674\\354\\000\\252\\003pq\\357Hr\\024\\021\\222C`\\235\\2650UL\\221\\200N\\0119\\340\\323\\001\\246BF@8\\036\\275\\215FX\\214\\214\\223\\307#\\337\\322\\235p\\006\\000\\030PqQ\\234\\266Td\\225\\350O\\343B\\001\\236V\\033%\\227\\000v\\357M;x\\300\\302\\340\\036\\005H~\\347\\336\\311'\\031\\035\\252"\\270\\307\\004\\366\\000\\014SC\\0342\\330\\031\\344\\3674\\346Q\\260(\\037JA\\222\\273T\\220\\330'\\247Jv\\3371q\\330\\016\\270\\357@\\210\\374\\314\\201\\335q\\311\\244-\\222\\001\\311\\357\\202iH\\003#9\\347\\036\\302\\242\\313\\007;\\363\\214`S\\002EPX\\023\\270\\020s\\307\\025\\211\\342\\325\\022i(\\374\\026Y\\207?PkaX\\211Bc\\214VO\\212\\276]'\\007\\034\\312\\007\\271\\353\\376\\025\\276\\037\\370\\2612\\255\\3746qK\\367\\205[?\\352\\352\\247\\275[\\037\\352\\253\\334\\221\\344\\242{A\\376\\223\\010\\377\\000m\\177\\235z9!\\201R\\001\\035y\\2578\\263`\\267P\\263t\\016\\244\\376u\\350{\\327\\313\\312\\343\\346\\031\\2573\\037\\274N\\374&\\314\\363=H\\356\\325\\256\\316s\\373\\326\\376uXu\\251n\\263\\366\\373\\217\\372\\350\\337\\316\\243A\\223^\\234~\\024pK\\342e\\350\\302\\354\\004\\014f\\264\\2642\\027Rpy\\314x\\037\\230\\254\\350\\306\\020\\001V\\264\\311D:\\2323\\014\\251\\371MeY^\\015\\033Rv\\232gB\\314T+\\016\\0118\\250\\330\\206\\364\\030\\007<\\364\\241\\331\\210,3\\234\\367\\364\\246\\020\\310\\017\\034\\364\\317\\371\\372W\\220\\221\\352\\007\\315\\321\\217\\004\\036*\\243\\023\\222\\000\\306O~x\\253\\004\\222>|\\037\\177j\\256\\361\\260s\\330d\\2661W\\0211\\2547\\003\\234g\\327\\025\\026\\007\\363\\315<\\214\\023\\200q\\214\\363\\332\\220\\206bB\\214q\\212\\320\\223?[`\\376\\037\\235\\273\\374\\243\\377\\000\\036\\025\\305Wc\\255FSD\\270\\347#\\345\\311\\317\\373B\\270\\352\\364\\360_\\003\\365<\\254o\\361\\025\\373\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336\\370p7\\366\\025\\251\\035>p\\177\\357\\263[\\000\\204P\\000\\307\\256\\177:\\340\\254|Gyaf\\226\\321G\\001D\\316\\013\\251$\\344\\347\\326\\254\\217\\030_\\214\\177\\243\\332q\\376\\303\\177\\361U\\344\\325\\301\\325\\224\\333]\\317V\\226.\\224`\\223\\354v\\340\\343,\\010\\310\\037\\235)\\221\\214g\\345_\\360\\256\\037\\376\\023\\035G\\376x\\332\\377\\000\\337-\\377\\000\\305R\\177\\302_\\250d\\221\\025\\260'\\321[\\377\\000\\212\\254\\376\\243T\\323\\353\\324\\216\\3503\\006\\303\\017\\257\\370S\\325\\303\\021\\310\\3348\\351\\326\\270\\037\\370K\\365\\014`\\303lG\\272\\267\\377\\000\\025N\\377\\000\\204\\307Q\\306<\\253l\\177\\272\\337\\374U/\\250U\\017\\257R;\\320G'\\257\\275\\017\\222F}3\\214\\327\\004<e\\250\\001\\217"\\327\\003\\375\\226\\377\\000\\342\\251\\337\\360\\232j\\004`\\333\\332\\0361\\312\\267\\377\\000\\025K\\352\\025\\207\\365\\352'y\\234d\\016{\\234P\\006N@<w\\365\\256\\023\\376\\023mK9\\362m\\177\\357\\226\\377\\000\\342\\250>5\\324\\217\\374\\261\\265\\377\\000\\276[\\377\\000\\212\\243\\352\\025\\203\\353\\324N\\354\\271\\307'\\236\\274T\\201\\313\\001\\214q^}\\377\\000\\011\\236\\243\\214y6\\277\\367\\313\\177\\361T\\377\\000\\370M\\265!\\322\\013O\\373\\341\\277\\370\\252?\\263\\353vA\\365\\352G\\240\\006#'4\\375\\334\\001\\323\\275y\\347\\374&\\372\\226I\\362,\\377\\000\\357\\206\\377\\000\\342\\251\\177\\3418\\324\\277\\347\\336\\323\\333\\344n?\\361\\352_\\331\\365\\203\\353\\324\\217Cc\\321\\216I\\353\\315)\\366\\317\\343^w\\377\\000\\011\\316\\247\\214y\\026\\230\\351\\215\\255\\377\\000\\305S\\277\\341<\\3250@\\202\\320g\\375\\206\\377\\000\\342\\251\\177g\\326\\362\\017\\257Q=\\020\\0169\\034\\372\\023Mh\\310S\\223\\317\\322\\274\\367\\376\\023\\315S\\037\\361\\357g\\377\\000|7\\377\\000\\025J|{\\252\\037\\371w\\262\\030\\364F\\377\\000\\342\\250\\376\\317\\257\\344\\037^\\242w\\205O8<\\021\\234z\\323\\201\\000\\214\\236\\235\\217\\362\\256\\003\\376\\023\\275L\\214}\\236\\317\\037\\356\\277\\377\\000\\025L\\377\\000\\204\\343R8\\375\\305\\247\\037\\354\\267\\377\\000\\025G\\366}`\\372\\365\\023\\321\\224\\223\\3174w\\003\\365\\257<\\036;\\324\\306?\\321\\354\\316:e\\033\\377\\000\\212\\245\\377\\000\\204\\367T\\000\\201od21\\367\\033\\377\\000\\212\\243\\373>\\270\\276\\275H\\364\\027$.@-\\3764\\245\\304Q\\345G\\323=k\\317\\177\\341=\\325?\\347\\332\\313\\376\\370\\177\\376*\\230\\3368\\324\\334\\363\\015\\2561\\2006\\266?\\364*?\\263\\353y\\007\\327\\250\\235\\371v2oQ\\306{\\372\\323\\325\\217%\\201\\310\\353\\307\\025\\347k\\343mIzCh9\\317\\010\\337\\374U;\\376\\023\\235S\\004y6\\274\\377\\000\\262\\377\\000\\374U?\\354\\372\\336C\\372\\365\\023\\322\\306\\010\\\\\\014q\\221\\374\\350W\\371\\276\\371\\000s\\317~\\246\\274\\343\\376\\023\\355W96\\366G\\035>F\\343\\377\\000\\036\\244\\377\\000\\204\\367U\\316L6\\204\\373\\243\\177\\361U?\\331\\325\\374\\203\\353\\324\\217O \\222\\012\\236\\371\\371y\\245E\\303g\\205\\031={\\327\\231\\257\\304-YG\\374{Y\\177\\337\\017\\377\\000\\305R\\217\\210\\272\\270\\307\\372=\\227\\037\\364\\315\\277\\370\\252\\237\\354\\352\\376A\\365\\352G\\250\\355\\033\\216NI\\343 \\347\\212l`\\030\\324\\221\\265\\200\\351\\2209\\307J\\363\\023\\361\\033X\\377\\000\\237{.\\230\\373\\217\\377\\000\\305SO\\304=X\\343\\375\\032\\313\\267\\360?\\377\\000\\025G\\366m\\177!}z\\221\\352\\252\\344;\\021\\367G\\344hb\\3318\\307\\\\\\021\\372\\327\\226\\257\\304}aG\\026\\326\\031\\316rc\\177\\376*\\227\\376\\026N\\261\\377\\000>\\266\\037\\367\\355\\377\\000\\370\\252?\\263k\\366A\\365\\332G\\2509$n\\000c8\\367\\246\\222A\\310c\\363W\\230\\177\\302\\307\\325\\373Z\\330\\017\\373f\\377\\000\\374U\\037\\360\\261\\365\\214c\\354\\3268\\377\\000q\\377\\000\\370\\252?\\263k\\371\\007\\327i\\036\\226\\013(;z`\\363P\\371\\241\\360zrFs\\351^l~ \\352\\347?\\270\\263\\347?\\300\\337\\374U0\\370\\373Vo\\371ci\\357\\362\\267?\\370\\365?\\354\\332\\376C\\372\\365#\\321\\343pPrW\\234\\200~\\202\\236X\\357\\310\\306p~e\\352:\\327\\232\\017\\035\\352c?\\350\\366\\177\\367\\303\\177\\361T\\277\\360\\236\\352\\270\\003\\310\\263\\000\\014p\\215\\377\\000\\305S\\376\\316\\255\\344\\037^\\242zC\\277\\335\\343\\004zS\\202\\340\\021\\216;{W\\232\\267\\217uV\\\\\\030,\\372\\365\\330\\337\\374U)\\361\\376\\254s\\373\\233?O\\270\\337\\374U\\037\\331\\325\\374\\203\\353\\324OI\\005rA\\307\\324\\216i\\331\\005\\363\\3108\\353\\237\\274=z\\327\\232\\377\\000\\302\\301\\325\\201\\310\\202\\317\\376\\370o\\376*\\217\\370X\\032\\267\\374\\373\\331g\\327\\313o\\376*\\227\\366u\\177/\\274>\\275D\\364B\\000l\\002r\\017Ji;\\301\\004\\025\\034\\021\\334\\212\\363\\246\\361\\346\\250\\335m\\354\\377\\000\\357\\206\\377\\000\\342\\250\\377\\000\\204\\363T\\300\\036E\\237\\375\\360\\337\\374U?\\354\\352\\342\\372\\365#\\322x\\300#\\215\\247\\007\\234\\326/\\213\\023\\376%\\003\\007;g\\\\\\347\\350\\325\\310\\257\\217uUb\\302\\013>\\177\\330o\\376*\\222O\\024\\337kQ\\033k\\230\\355\\3265>g\\356\\324\\203\\236\\235\\311\\365\\255)`kBjOdML])\\305\\305u \\253q\\363\\030\\252\\225f\\003\\224\\257I\\234H\\225N\\323\\237J\\357 s%\\252\\026\\332\\030\\240<\\375\\005pg\\205\\315B|m\\251\\333\\271\\205a\\264\\332\\207\\003(\\337L\\375\\352\\343\\304a\\347Y.N\\207E\\032\\321\\245~a\\267\\243\\376&\\027=\\377\\000z\\337\\316\\231\\037Z[\\207\\363.$s\\214\\263\\022q\\353I\\037z\\353J\\321\\261\\316\\365\\221u>\\350\\247\\302\\376]\\322?\\367H?\\2551>\\350\\240\\360\\301\\252Z\\271kC\\245`J\\222\\0063\\336\\220\\356|\\216\\240\\364\\315qg\\306\\032\\207#\\311\\265\\307\\246\\306\\377\\000\\032A\\343\\015@\\000\\0046\\274\\177\\260\\337\\374Up\\375F\\261\\331\\365\\332Gf>PT\\220q\\3275\\023\\225'\\360\\374\\353\\217\\377\\000\\204\\266\\377\\000$\\230m\\217\\374\\005\\277\\370\\252i\\361U\\361\\037\\352m\\277\\357\\226\\377\\000\\342\\252\\226\\012\\250\\276\\273H\\352_\\324\\2561\\326\\235\\036NJ\\365\\365'\\265rG\\304\\367\\2540b\\267?Uo\\361\\241|Oz\\234\\010\\340\\306s\\312\\267\\370\\325}N\\255\\205\\365\\312F\\367\\210Wf\\213q\\203\\301\\011\\377\\000\\241\\012\\341\\353V\\363\\304\\027w\\326\\257o,p\\205|d\\2509\\340\\203\\353\\355YU\\333\\205\\245*p\\264\\216\\034UX\\324\\232q\\354\\024QEt\\234\\301E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001W4\\342\\005\\303g\\272\\221\\372\\212\\247V,A7#\\036\\206\\224\\266\\034w6jh[\\034T5"u\\025\\316\\316\\204[?r\\271[\\256.\\244\\377\\000x\\327R\\307\\021g\\332\\271\\213\\337\\370\\373\\177\\255U-\\311\\253\\261\\264\\307'>\\274\\323\\24385\\030\\350>\\203\\371S\\227\\203H\\242\\372\\375\\321J{}i\\251\\367i\\325\\231g#2l\\235\\327\\321\\210\\250\\352\\336\\244\\024j\\023\\005\\030\\031\\037\\312\\252WZ\\325\\034\\217F\\024QE1\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005Y\\260\\377\\000\\217\\265\\372\\032\\255V\\364\\361\\233\\234\\372)\\245-\\207\\035\\315jx<\\212`\\351J:\\326\\007Ap\\363\\011\\372W=\\251&\\331\\303z\\212\\350\\027\\230?\\003X\\372\\242\\376\\345\\033\\2708\\242\\236\\214U\\026\\205\\355\\270\\030\\377\\000d\\037\\322\\220\\016i\\212\\305\\2223\\353\\032\\377\\000*\\221z\\212\\030\\313\\2500\\242\\234)\\027\\356\\212QY\\226s:\\240\\306\\2450\\367\\007\\364\\025N\\264\\365\\270\\366\\335\\253\\343\\357/\\362\\254\\312\\352\\217\\302\\216Y\\253I\\205\\024QTHQE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000U\\275<\\376\\375\\277\\335\\376\\265R\\254\\331M\\344LX\\306\\035H\\332A8\\245-\\207\\035\\315u#\\024\\265\\030\\232\\336C\\204|\\037B)\\334\\257\\322\\260:\\013\\260\\266b"\\263u\\025\\315\\233\\023\\330\\212\\273n\\343v=j\\246\\2506\\332\\260<d\\321\\037\\210%\\360\\205\\271\\315\\274G\\375\\232\\231z\\324\\026\\303\\026\\320\\373\\255N:\\323{\\211l]\\210\\3460jJ\\206\\003\\306*j\\314\\320\\305\\327\\227\\230\\033\\375\\341\\374\\253\\032\\272\\015n-\\326j\\377\\000\\334o\\347\\\\\\375tS\\370Nz\\237\\020QE\\025faE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001ND,i\\2654N\\027\\203I\\215\\013\\344\\214z\\323\\302\\036\\200T\\361\\224&\\246\\304k\\311\\307\\326\\263r5P*\\210X\\216F*h\\326h\\327\\207#\\234{RIr\\007\\013\\203U\\236\\344\\366\\346\\205v\\027\\2124#\\236T`X/\\036\\325J\\376\\371\\356\\334\\002\\002\\242\\364\\002\\240k\\211\\033\\276**\\250\\306\\332\\262%;\\253#f\\320\\267\\330\\342-\\310\\347\\037\\235N\\030U+=Cd\\036K\\307\\220\\240\\355a\\375ja{\\003\\0342\\262\\373\\216j\\032w4\\213V/E0S\\317\\351VD\\250{\\326_\\332-\\273H\\337\\367\\317\\377\\000^\\246\\216\\352\\33382\\021\\365Z\\207\\022\\324\\207\\352\\304\\0356N\\234\\343\\371\\212\\346\\253OR\\277YA\\267\\2150\\200\\362\\307\\277\\341Y\\225\\2655dcQ\\246\\364\\012(\\242\\254\\314(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000P\\354\\275\\015)\\221\\317V4\\332(\\013\\212I=M%\\024P\\001E\\024P\\005\\244P\\2109\\3523N\\010[\\240\\252\\361\\311\\201\\265\\272z\\372T\\3511\\035\\0105\\0153D\\320\\361\\013\\347\\224\\317\\343V\\241\\264\\030\\015\\212\\254.$\\364\\030\\241\\257\\233i\\000\\205\\377\\000w\\257\\347P\\324\\231i\\305n\\032\\214(\\230e<\\216*\\205>YZV\\313\\037\\302\\231ZE4\\254\\314\\244\\323wAE\\024U\\022\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\177\\377\\331	19	[Exif] Orientation - Top, left side (Horizontal / normal)<br/>[Exif] X Resolution - 72 dots per inch<br/>[Exif] Y Resolution - 72 dots per inch<br/>[Exif] Resolution Unit - Inch<br/>[Exif] Software - Adobe Photoshop CS3 Windows<br/>[Exif] Date/Time - 2008:09:22 11:29:40<br/>[Exif] Color Space - Undefined<br/>[Exif] Exif Image Width - 1280 pixels<br/>[Exif] Exif Image Height - 1024 pixels<br/>[Exif] Compression - JPEG (old-style)<br/>[Exif] Thumbnail Offset - 302 bytes<br/>[Exif] Thumbnail Length - 3479 bytes<br/>[Exif] Thumbnail Data - [3479 bytes of thumbnail data]<br/>	zz
6	2011-02-05 11:18:01.216+01	\N	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\000d\\000\\226\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\266<7\\241\\035\\177T\\026\\355<V\\360(\\015,\\262\\310\\020\\001\\234`\\023\\334\\376<\\002pq\\212\\312\\212\\031n$\\021\\303\\033\\310\\347\\242\\242\\222O\\340+kK\\022\\351\\227\\357a\\251\\333H-\\256\\201\\215\\243n\\025\\234d+g\\276\\030\\365\\025\\023\\225\\227\\231t\\343\\314\\325\\366=#P\\322\\374\\026\\226m\\247<0\\301\\022Fv\\334$\\1772\\002\\330W2c\\346\\311\\030\\007$q\\214\\342\\274\\202\\356\\331\\354\\356\\345\\267vF1\\261\\033\\320\\345\\\\ve=\\301\\034\\203\\334\\021^\\304\\260E\\025\\254\\261\\303l\\271yL\\241\\030\\235\\252s\\221\\220G\\\\\\201\\316;\\360k\\3125\\326\\270\\223Uw\\272\\307\\234R<\\341v\\216\\020\\016\\230\\0351\\217\\302\\271\\360\\325\\235F\\323:\\261T\\0254\\2323\\250\\242\\212\\3538\\202\\212(\\240\\002\\254\\332i\\367W\\373\\376\\315\\026\\375\\230\\335\\363\\001\\214\\375O\\261\\252\\325\\324\\3705ro8\\004\\374\\235\\263\\375\\352\\306\\275GN\\233\\2225\\241MT\\250\\242\\314\\257\\370G5c\\377\\000.\\237\\371\\021\\177\\306\\247\\227A\\326\\034\\251{\\030\\370\\376\\341\\215s\\365\\301\\256\\355\\224\\025\\015\\320\\221R*\\203\\202s\\237Bk\\315y\\205N\\313\\372\\371\\236\\227\\324)\\367\\177\\327\\310\\363\\317\\370F\\365fU\\333d:v\\225y\\375jE\\360\\326\\260\\247\\235=\\031s\\222\\246E\\355\\333;\\263\\337\\326\\275\\006%\\022\\015\\301H\\036\\335\\351\\356\\031\\010 \\023\\223\\326\\223\\314j\\355e\\375|\\303\\352\\024\\373\\277\\353\\344y\\333\\370WY'#O\\333\\352\\004\\311\\217\\375\\012\\206\\360\\266\\262\\317\\306\\236\\253\\223\\302\\211\\227\\372\\265zF\\365\\030f\\316y P\\010U\\007\\0318\\035*\\177\\264j\\366_\\217\\371\\217\\352\\024\\273\\277\\303\\374\\2178\\036\\027\\326\\002\\344\\351\\340\\374\\300\\377\\000\\256L\\034~9\\347\\353\\377\\000\\326F\\360\\346\\252\\021q\\247*\\270`wy\\252A\\343\\246\\013}I\\376\\225\\351\\021\\263\\026#h>\\365$\\220\\206\\207\\014\\012\\237\\257#\\232?\\264j\\365K\\361\\377\\0000\\372\\205>\\357\\360\\377\\000#\\313\\377\\000\\341\\031\\325\\313\\005\\373\\030\\317\\375uO\\361\\244o\\015j\\350\\011kL\\001\\377\\000MS\\374k\\320\\335\\374\\227\\031\\\\s\\235\\300zv\\376t\\233\\213\\017\\220c\\214\\343\\245_\\366\\205^\\313\\372\\371\\213\\352\\024\\273\\277\\353\\344cx3N\\271\\323F\\246n\\020\\333\\334Ko\\266\\011W\\346+\\3247)\\222\\275G\\345Ve\\321m\\356\\274\\241\\034I3\\375\\330\\331\\243\\306\\376\\006N\\017@\\000\\030\\364\\000q\\326\\257\\306\\014A\\371(\\314\\010\\030\\316\\010\\037O\\303\\212\\273\\242\\203\\375\\245\\023l\\017\\034(\\331,N0\\007\\371\\375i\\323\\254\\347U7\\273\\377\\000 \\225%\\012|\\251h\\211\\240\\202\\347\\354 E<ry\\304\\034\\311\\2373 \\340\\205^s\\363`v\\350zu\\031^5\\360\\273\\336\\351\\220\\335\\031\\025/-\\243\\021\\307\\026A\\336\\245\\311 \\267\\367\\2673\\266~\\277Q\\325<\\006\\347dY\\231JD\\273\\235c\\005C\\266Y\\362\\271\\3440a\\234{v\\254}^\\351\\346\\325\\204\\015\\274E\\032\\252\\374\\375I\\3062{z\\376f\\2724\\245yE\\030\\312\\365\\022\\214\\231\\345c\\303\\332\\241b>\\313\\310\\377\\000\\246\\213\\3765\\237,o\\014\\257\\024\\203\\016\\214U\\206z\\021\\326\\275%\\216\\025X3o\\3063^y\\250\\343\\373R\\357\\035<\\347\\377\\000\\320\\215^\\033\\021*\\255\\251\\031bp\\360\\245\\024\\343\\324\\255E\\024Wa\\306\\025\\324\\3700fK\\274\\222>\\340\\343\\376\\005\\\\\\265u~\\012m\\262^\\036\\377\\000&?\\361\\352\\346\\306\\177\\006_\\327S\\247\\011\\374h\\377\\000]\\016\\307\\313\\310\\307'\\246q\\333\\361\\2451a\\006O \\365\\356(Vf]\\334\\216p1\\306~\\264\\365\\306\\340\\033\\277A^\\005\\317pta\\200\\354\\007\\035\\271\\374\\350Q\\307$\\016\\177\\317\\025\\013\\273+\\355\\306G$\\006\\352*H\\217\\3140C~\\034\\342\\225\\200{\\215\\340\\200O\\247&\\2260v\\234\\234\\361\\222Gzg\\336\\310$\\343\\260\\247(!\\270\\300\\365\\343\\332\\220\\017B\\25299\\000\\372R\\273\\226\\005z\\017N\\264\\300:\\017\\345Q\\266\\357\\225A\\002\\213\\001\\034\\321y\\304\\343\\035:\\347\\031\\366\\246\\303\\271N\\336G\\256O\\270\\253\\261G\\310\\\\\\362E1\\343!\\266\\343\\327\\221\\333\\327\\371S\\346\\350\\004\\022\\246\\345*r7|\\247\\007\\037\\255di\\372\\234\\232\\014\\323\\301#5\\304\\0044j\\347%\\227\\345\\311\\007\\351\\221\\320w\\355\\322\\267\\231x\\030\\030\\003\\236\\237\\215g\\352\\032\\250\\266\\205\\254$\\234\\010\\244\\033\\3326]\\301\\362H\\364\\343\\201]XI\\332v\\261\\206%{\\227:\\004\\361U\\206\\237l\\257p\\214\\302}\\222#\\206R\\030m\\0039\\007\\030\\312\\221\\221\\334c\\257\\025\\207.\\252\\272\\265\\323^,R\\240.\\304y\\200\\014\\214\\014\\021\\355\\301\\353X3j\\366\\336J,\\026a\\244L,fN\\221\\256X\\225Q\\236\\001-\\234z\\3769\\336\\323eK\\270&\\271X\\314qy\\233c\\217$\\343\\0123\\226\\357\\327\\323\\212\\354\\304M\\250]\\243\\222\\222R\\225\\223*\\312?{\\302\\262g\\004\\001\\333\\201^y\\252\\340k\\027\\270\\351\\366\\211?\\364#^\\2256<\\365b\\271\\311\\004\\034\\343<\\327\\233j\\374\\353W\\344\\177\\317\\304\\237\\372\\021\\245\\200w\\223\\364\\014z\\264"S\\242\\212+\\324<\\300\\256\\253\\301\\231\\305\\363/Q\\345\\361\\214\\347\\357W+]W\\203[i\\274\\307\\\\\\307\\307\\375\\365\\\\\\330\\317\\340\\313\\345\\371\\2358O\\343G\\372\\350v\\012YN\\334\\216\\271\\3018\\006\\236\\257\\320\\200s\\300\\315@\\301\\201Q\\236GS\\217\\302\\245Q\\203\\216\\236\\325\\340\\263\\334\\004\\213q\\336@V\\343\\247\\377\\000Z\\246@@$\\036s\\236;T\\021\\306L\\274\\201\\202*\\320CI\\261\\015e\\013\\357\\370\\324\\253\\206\\372\\364\\250\\317\\337\\312\\343\\361\\2473\\005\\007w\\034\\201\\322\\244\\004\\311\\\\\\345x\\0351O\\020n\\301\\035\\007\\275,P\\2660\\303 \\236j\\340\\312\\345T\\022q\\330g\\371Rlef_%\\201#\\257\\345\\322\\242c\\2269'\\0352?\\302\\264Z#\\2207\\036N\\016?\\317\\275VxX\\022B\\355\\000s\\232\\224\\306U|\\263\\036q\\357\\\\~\\274\\354\\372\\263\\241\\351\\032\\252\\217n3\\375k\\266\\2261\\3461N=~\\265\\306k+\\235zq\\214p\\277\\372\\010\\256\\374\\017\\361\\037\\241\\311\\213\\370\\027\\251\\235\\010\\006E\\035\\261]\\356\\202ao\\017%\\254\\233\\021eg}\\344\\221\\265\\267\\021\\223\\307\\240\\037\\225p\\211\\024\\2214,\\353\\205pJ\\234\\365\\031#\\371\\203]\\226\\237&4\\033p\\011\\3408 {\\273\\177\\215t\\343%h+w9\\360\\321\\274\\265\\031}\\034\\226\\323\\224\\225Ln8 \\363\\350F\\010\\377\\000\\365\\032\\363=P\\347W\\275?\\364\\335\\377\\000\\364#^\\203"\\344\\214\\220Oo\\226\\274\\363Q\\301\\324\\356\\361\\323\\316\\177\\375\\010\\325`\\027\\274\\303\\037\\360\\242\\265\\024Q^\\231\\346\\005u^\\014]\\337m\\343<\\307\\377\\000\\263W+]'\\204\\256\\241\\265{\\2234\\361E\\235\\230\\363\\034.~\\367\\255sb\\323t]\\277\\255N\\214#J\\262\\277\\365\\241\\332\\205\\352z\\200y\\253)\\011\\221\\010\\030\\316;Vg\\366\\266\\235\\264\\251\\277\\26699\\000\\312\\274~\\265"kZr\\347\\032\\205\\276\\323\\330\\314\\243\\372\\327\\204\\341>\\307\\267\\317\\036\\346\\202[\\272.IPG\\035jQ\\270\\017S\\237J\\246\\232\\346\\227\\267\\037\\332\\026\\237\\214\\3523\\372\\323\\216\\265\\245\\237\\371\\211Y\\375\\014\\353\\3765.\\023\\354\\034\\361\\356X\\306\\030\\365\\365\\246\\2723\\266\\334\\223\\351\\217\\376\\265W]gK\\301\\316\\243i\\221\\330\\316\\274\\376\\264\\015gKF?\\3610\\264<d\\201:\\376]h\\344\\237`\\347\\217sB\\020\\321\\217\\230\\026c\\235\\275:U\\261\\030\\332\\011\\303c\\003\\034}\\177\\317\\343Y\\351\\255\\351\\015\\260\\177iX\\251\\034\\377\\000\\307\\302\\363\\355\\326\\254&\\277\\243\\230\\331N\\251b1\\353p\\236\\230\\365\\250p\\237f\\034\\361\\356^Y9\\301\\001\\270?63\\212\\2576\\000\\332\\030\\354\\3479\\346\\252I\\256\\350\\311\\363\\015N\\315\\212\\222F'^\\177Z\\205\\265\\315%\\363\\377\\000\\023;N\\243\\237=\\177\\306\\222\\247>\\3149\\343\\334\\266xE\\317A\\327\\203\\327\\251\\256/^F\\203[\\234\\360w\\242\\260\\366\\343\\037\\322\\272v\\3264\\256\\243T\\263\\310\\377\\000\\247\\204\\377\\000\\032\\346\\274Au\\005\\336\\251\\023\\333\\334C:\\371\\001KD\\301\\2009<q]\\270(\\31255]\\016lT\\242\\341\\243$\\326\\355\\341\\265\\323\\264WE\\303<LX\\217\\370\\013\\1776o\\316\\264\\364\\271w\\350\\261\\355\\003\\222\\300\\347\\352O?\\201\\254m^\\362+\\253\\015.4\\221\\013\\302\\254\\256\\212\\331+\\367@\\310\\355\\234T\\366\\027\\266\\326\\366\\355\\034\\323\\305\\021g\\310\\363\\034.x\\003\\275oV\\016TU\\367\\273\\374\\331\\235)%W\\312\\337\\242-N\\333\\\\\\363\\327\\327\\255y\\346\\242I\\324\\356\\311\\352f\\177\\375\\010\\327q.\\243`\\344\\250\\274\\203\\004\\365\\022\\257\\370\\377\\000\\234\\327\\015~\\301\\365\\033\\226V\\014\\014\\256C\\003\\220y<\\326\\330\\030\\264\\335\\314\\361\\322N*\\314\\257E\\024W\\244y\\241E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001Z\\332Q\\304$\\377\\000\\267\\375\\005e\\001\\222\\007\\255h[3[FC\\355\\000\\234\\344\\234TOk\\027\\015\\356]\\000\\215F\\340\\236\\345H\\376_\\322\\242\\327\\272[\\377\\000\\300\\277\\245\\021\\335\\3075\\324\\222\\002\\000\\012\\243\\223\\202O?\\343N\\326%\\216[(\\312\\221\\235\\374~G?\\322\\241]I\\032;8\\263\\022\\212(\\255\\214\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\011al\\023\\300<g\\232t\\362\\226UL(Q\\316\\000\\357E\\025\\037h\\277\\262AE\\024U\\220\\024QE\\000\\177\\377\\331	\\377\\330\\377\\340\\000\\020JFIF\\000\\001\\002\\000\\000\\001\\000\\001\\000\\000\\377\\333\\000C\\000\\010\\006\\006\\007\\006\\005\\010\\007\\007\\007\\011\\011\\010\\012\\014\\024\\015\\014\\013\\013\\014\\031\\022\\023\\017\\024\\035\\032\\037\\036\\035\\032\\034\\034 $.' ",#\\034\\034(7),01444\\037'9=82<.342\\377\\333\\000C\\001\\011\\011\\011\\014\\013\\014\\030\\015\\015\\0302!\\034!22222222222222222222222222222222222222222222222222\\377\\300\\000\\021\\010\\001\\220\\002X\\003\\001"\\000\\002\\021\\001\\003\\021\\001\\377\\304\\000\\037\\000\\000\\001\\005\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\020\\000\\002\\001\\003\\003\\002\\004\\003\\005\\005\\004\\004\\000\\000\\001}\\001\\002\\003\\000\\004\\021\\005\\022!1A\\006\\023Qa\\007"q\\0242\\201\\221\\241\\010#B\\261\\301\\025R\\321\\360$3br\\202\\011\\012\\026\\027\\030\\031\\032%&'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\341\\342\\343\\344\\345\\346\\347\\350\\351\\352\\361\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\304\\000\\037\\001\\000\\003\\001\\001\\001\\001\\001\\001\\001\\001\\001\\000\\000\\000\\000\\000\\000\\001\\002\\003\\004\\005\\006\\007\\010\\011\\012\\013\\377\\304\\000\\265\\021\\000\\002\\001\\002\\004\\004\\003\\004\\007\\005\\004\\004\\000\\001\\002w\\000\\001\\002\\003\\021\\004\\005!1\\006\\022AQ\\007aq\\023"2\\201\\010\\024B\\221\\241\\261\\301\\011#3R\\360\\025br\\321\\012\\026$4\\341%\\361\\027\\030\\031\\032&'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz\\202\\203\\204\\205\\206\\207\\210\\211\\212\\222\\223\\224\\225\\226\\227\\230\\231\\232\\242\\243\\244\\245\\246\\247\\250\\251\\252\\262\\263\\264\\265\\266\\267\\270\\271\\272\\302\\303\\304\\305\\306\\307\\310\\311\\312\\322\\323\\324\\325\\326\\327\\330\\331\\332\\342\\343\\344\\345\\346\\347\\350\\351\\352\\362\\363\\364\\365\\366\\367\\370\\371\\372\\377\\332\\000\\014\\003\\001\\000\\002\\021\\003\\021\\000?\\000\\371\\376\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212+OD\\321g\\326\\256\\374\\250\\316\\330\\327\\227s\\333\\377\\000\\257I\\273\\015&\\335\\221J\\332\\326{\\311\\204PF\\316\\347\\260\\035+\\320<7\\360\\246\\347SSs\\251\\336\\307il\\237|\\016[\\246q\\236\\307\\363\\256\\303\\301\\236\\033\\261\\2047\\225\\026\\330"\\3102`n\\220\\216X\\223\\355\\220\\001\\354\\304zWAqx\\367o\\210\\327\\313\\264\\\\\\371P\\257\\177\\366\\217\\327\\377\\000\\256y\\351\\233\\2337\\215$\\267<\\367V\\370o\\241*:\\351\\327\\332\\210\\231A\\301\\222\\000\\310O\\347\\232\\363\\215OK\\272\\322n\\332\\336\\352<\\021\\321\\207F\\036\\242\\276\\205\\2126\\221\\310v\\332\\271\\344(\\254o\\023\\370Z\\333\\304\\032s\\307\\013\\252\\334&Ln8\\033\\260p\\017\\2618\\315(\\315\\365\\034\\351\\246\\264<\\032\\212\\236\\362\\322{\\033\\271-\\256bh\\246\\215\\266\\2727Pj\\012\\330\\346\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200&\\265\\266\\222\\362\\352+x\\207\\317#\\005\\031\\3503\\353^\\277\\341_\\016\\234\\0152\\317\\021\\025\\217\\314k\\207\\214\\221#eW\\216}\\372\\377\\000\\262Gn8\\277\\000\\330\\302.\\347\\325\\257\\023u\\255\\24623\\215\\314{~<\\003\\354Oz\\357S\\307w?i\\206Ac\\032C\\033\\214,Q\\021\\362g\\220\\016:\\373\\3265%\\255\\216\\2320v\\272:Kx/\\242\\360\\372\\351\\326\\2262\\206\\033Q\\3351\\2007|\\374\\236rF\\177\\032y\\267\\3731o>\\342\\336\\325\\025p\\032G\\344{\\025\\353\\371\\032\\311\\325\\274ou\\177g\\014Vp43\\314q"\\266?Lrs\\375k\\216\\325\\265\\025\\262\\234\\377\\000i\\352\\037\\277\\003>DD\\273\\365\\034`t\\340\\347\\014T\\326.\\242\\275\\226\\246\\352\\234\\255w\\241\\337\\177l\\350\\220\\022%\\275\\270f\\357\\266\\021\\267\\360\\344U\\270&\\323\\357\\371\\323\\256\\304\\217\\217\\270\\303k\\037\\240\\347?\\235y\\265\\235\\306\\203q\\020\\204]K\\023\\267\\003\\355\\021\\225?\\367\\321\\310\\374\\315c\\275\\346\\243\\242\\352XIY\\274\\262\\035YO;s\\200A\\030\\346\\224*);Z\\303\\235>U{\\233?\\023t#$I\\254\\305\\030W\\217\\021\\334\\0020H\\310\\012\\177\\016\\237\\217\\265y\\205{\\252kv~'\\321\\266^\\300\\321\\311t\\214\\255\\306\\325\\220\\001\\303\\001\\234\\343\\234n\\366\\306x\\025\\341\\323\\302\\366\\327\\022\\301 \\304\\221\\271F\\036\\340\\340\\327M9'\\241\\311Z\\0154\\373\\221\\321E\\025\\241\\210QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\332\\3702\\353f\\217\\251E\\031"h\\344\\212a\\223\\301\\031\\307\\363\\251f\\265\\273\\270\\211gk\\363\\001$\\252\\342a\\022\\240\\364\\311#'\\203\\311\\353\\3061\\\\\\256\\215\\250\\2357PYO\\372\\267S\\034\\200\\214\\360F3\\365\\035k\\323\\354M\\273\\370f)\\323a\\220\\355\\004\\306J\\363\\311'\\217rO\\343\\\\X\\247\\310\\324\\254z8O~.&^\\207os\\177\\241\\337\\235Fy\\035Y\\000\\215\\270\\005x\\004}\\336\\344\\234\\036\\343\\220q\\310\\254\\253m\\001\\240\\274@\\221\\021\\023#+\\024C#\\203\\307;C\\251\\350z\\347\\036\\271\\256\\273Ih\\347\\320\\245\\213~\\334\\222\\333\\216yP\\345\\211\\372T\\366\\215oqp\\214\\241\\\\\\002\\001R\\277\\221\\000\\327\\037\\266\\224\\\\\\254v{\\024\\322\\271\\035\\204\\027\\261[\\01318\\231UB\\215\\313\\2023\\223\\352@\\034\\343\\034\\343\\035y\\343+X\\321\\206\\243,2D\\312\\233\\217\\2236z*\\363\\223\\365\\037\\257\\265v\\256#\\265\\267-\\032\\250\\307\\335\\343\\275sRH\\327\\023\\264\\204\\374\\231\\302\\214u\\036\\246\\261\\2476\\345\\314\\215\\034"\\343\\312\\312q\\336\\026[o\\265\\221\\015\\305\\345\\300\\375\\346\\003\\3423\\201\\273\\216\\200\\017\\227\\007\\324\\372W\\235x\\2027\\213\\304z\\222H\\245X]I\\220\\177\\3365\\265\\004\\362\\334N\\277h\\225\\245fF\\000\\271\\311\\001r1\\237\\256j\\247\\214\\324\\177\\302B\\322\\361\\272x\\243\\231\\261\\334\\262\\344\\327\\255B\\012\\0373\\313\\305K\\235\\\\\\347\\350\\242\\212\\3518\\202\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\245\\224~\\352\\023\\376\\311\\037\\251\\250\\321w\\272\\250\\356q]RxV\\377\\000X\\236\\321-\\243H\\355\\226\\020\\276k\\0209\\007\\237\\251$\\324\\316j*\\362*\\020rv\\211\\312Q^\\224\\236\\033\\321\\3574\\271\\364\\310Q\\341\\277\\201\\2172\\014;\\021\\333\\337\\257\\037^\\325\\316^\\370"\\376\\326\\326K\\230\\356-\\246\\211Sx\\001\\210v\\035O\\030\\343\\003\\236H\\366\\315a\\014U9;=\\015\\347\\205\\251\\025u\\251\\314Wg\\341[\\365\\227K\\222\\315\\333/\\023d\\003\\335\\017\\370\\034\\376u\\313^\\330MbPJP\\357\\007\\033Nj]\\036\\377\\000\\3737R\\216s\\312\\037\\225\\307\\373'\\374\\347\\360\\253\\253\\025R\\032\\012\\214\\235*\\236\\361\\350\\211.<\\273[\\273\\325\\021\\244\\203\\010$\\033\\201\\031#p\\034\\236@\\030=\\361\\301\\305l\\013{i\\025\\026\\031\\320\\3143"\\363\\207c\\324\\234\\036y'\\255U\\267\\323\\346{q$.\\261\\347$*\\341{\\372b\\257\\206\\232\\330\\010\\244\\220\\310@\\317<\\200q\\326\\274\\211>\\307\\263\\024#\\336\\311-\\253\\307 \\371\\225O\\377\\000\\257\\371\\326sn\\212\\331\\231P\\264\\2062UA\\347=\\277\\014\\367\\253\\300\\011\\246D\\306\\351\\034d/\\267\\251\\366\\242KU\\212DS\\376\\271\\330\\274\\204t\\302\\343\\201\\376\\316z\\177\\273\\353R\\232E\\234,\\0262\\301\\342\\021lb&\\0378\\355|`\\035\\300\\023\\374\\3537\\306f1\\342I\\241\\215\\203\\213x\\343\\201\\230\\034\\202U@5\\335\\337Z\\213{\\341\\250\\307\\2012\\250\\010[\\225\\007<~#q \\372\\342\\270/\\021xwQ\\322|\\233\\353\\250g6\\327\\237<s\\3108rF\\356\\277C\\237\\377\\000Q\\257W\\01754\\231\\344\\342\\340\\340\\354`\\321E\\025\\324p\\205\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\022\\244!\\227sM\\034c\\031\\033\\2119\\374\\201\\307\\343S\\213\\020\\276X\\232]\\245\\313p\\2407\\312\\007\\\\\\347\\030\\367\\351\\301\\347\\212\\2124\\205\\324\\023"\\243\\000w\\011\\001\\301\\364\\306\\321\\374\\375:\\363\\303\\326\\345\\014b\\011\\024\\210H\\352\\275T\\372\\217\\313\\221\\337\\362\\304\\273\\364)[\\251^@\\202B#fd\\354YpO\\341\\223HT\\200\\017\\034\\214\\360j\\345\\264\\260\\333\\207c$\\201\\360\\002\\264D\\253u\\347\\364\\030\\374z\\036\\322\\033\\210\\232d2\\\\\\310\\351\\034\\213\\263%\\230\\354\\344\\236\\275\\017\\0128\\307\\343G3\\354\\034\\253\\271FH\\374\\275\\200\\237\\230\\250b1\\323=?L\\037\\306\\232\\006s\\323\\217z\\277=\\363\\341\\012\\221\\222\\034\\221\\235\\330\\3361\\200}6\\343\\217Q\\315"Nm\\255D%\\312\\272H$\\333\\216Cg\\371\\200\\007\\346E\\027v\\013+\\224B\\2268PI\\306x\\244\\253\\222\\334!\\267d24\\262t\\313\\022FK\\022\\314\\271\\351\\321\\177Z \\232\\030|\\223\\346I\\265\\224\\211\\204x\\007\\357t\\344r0\\007\\007\\336\\213\\260\\262)\\321Z\\237l\\033\\342-}1`\\354\\254\\300\\222UH\\340\\202F{\\234\\372\\340{\\032cL\\261F\\252&\\2212\\025\\374\\260\\010\\012s\\374#\\327\\001H'\\250$\\365\\245\\314\\373\\007*\\356gQVn\\356\\336\\341\\210,6\\226.B\\023\\264\\222s\\234\\036\\3438\\317\\260\\253&\\3765x\\232\\022\\352T\\005\\223\\004\\257\\230\\241Tu\\0079\\340\\373t\\367\\247w\\330,\\273\\231\\304\\02588\\351\\236\\016i+L^\\306\\351\\211%\\227s}\\366RG\\336;\\237\\003\\240\\350\\007\\241\\311\\317\\261%\\341\\226\\0072l\\304\\212\\333\\216y\\334\\314\\011 }\\024\\017L\\347\\232\\\\\\317\\260\\371Ws2\\212\\261!\\023^)\\232c\\265\\266\\356|\\356*\\270\\035}H\\037\\312\\237=\\312=\\264P\\306YWqy\\024\\014\\000x\\000\\001\\337\\000u'\\275U\\311\\261R\\237\\034fG\\332=\\013\\037\\240\\031?\\312\\264\\005\\364Q\\247\\226\\262J\\360\\355%Q\\230\\222\\271F]\\2751\\324\\347#\\267\\257J\\223\\373MJN\\2134\\252\\204\\217+wU\\001[\\030\\003 r\\024q\\327'\\360\\227'\\330\\245\\025\\334\\310\\242\\264\\276\\334\\201\\321\\014\\263\\033l*\\225S\\206\\030\\034\\360r0I<{\\012I/2\\261\\223y+K\\031#z\\226'\\034\\362\\244\\343\\031\\316\\016y\\340})\\363>\\302\\345]\\314\\352+I\\247\\201\\224F\\327r\\274\\033\\200T`X\\242\\220Fy\\343#\\030\\351\\306\\3563U\\004\\276|\\222Iq!g(p_''\\377\\000\\325\\323\\337\\035\\250L\\032!\\301\\332[\\214\\003\\216\\274\\323\\322 \\320\\311!`6`\\001\\334\\223\\377\\000\\326\\007\\374\\232\\236\\362o8!\\373D\\222\\205\\030\\314\\214I$\\362N9\\307a\\370T\\326\\263\\301\\034(\\242v\\202@\\303t\\250\\016\\342\\033!\\261\\216\\300\\005\\374s\\212\\033v\\270$\\257b\\206\\306\\007\\004`\\343<\\361\\3063M\\255(/c\\202A*\\355\\314\\212\\306D\\003h\\031'+\\300\\351\\2000;\\036i\\004\\355-\\270H\\256e\\363\\034\\021*\\273\\354@\\011\\316F8\\307Q\\317\\257\\002\\216g\\3309Ws:\\212\\265sv\\3234\\252\\247\\021\\311)\\224\\2578\\004\\377\\000\\365\\276\\225hj>H\\015\\024\\247p\\000`er\\021v\\247\\352I \\361\\300\\024]\\366\\013.\\346c\\002\\254T\\343 \\343\\203\\221IV&\\223\\314e\\205f\\304!\\216\\001\\316\\304\\344\\364\\357\\214{f\\256G<(U\\343\\270b\\353\\001\\013\\270\\220U\\202\\221\\200q\\323\\222@\\030\\353\\330\\201C\\223\\004\\221\\227E+3;\\026f,\\304\\344\\222rI\\244\\252$(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\352\\274\\035\\341yu\\233\\264\\270\\270\\005l#p\\314\\177\\277\\203\\323\\374\\377\\000\\211\\036\\241\\254\\337[\\276\\236c\\261\\011\\366\\213\\006Yc\\0129h\\327\\001\\300\\375G\\341\\\\\\277\\204X\\277\\206l\\326"\\311\\2279\\012p\\011\\334~\\367\\265\\032\\205\\304\\220\\3111IQe\\022>Z6\\344\\243pF9\\300\\350\\007\\341^Mi:\\223w\\350z\\364i(A8\\365W\\023\\304\\262\\302\\232\\272j6W\\002Y%Q\\271cl\\355`\\000\\033\\271\\351\\217\\346}3Xr\\254\\272\\235\\373H\\226\\3443|\\336R6W=\\334\\364\\344\\365?\\316\\244\\211%\\272\\220E\\002u\\357\\330}kM\\355\\222\\316#g\\031\\314\\244\\346\\342Q\\324\\037\\371\\346=\\275}\\376\\231*>\\352K\\251R\\327^\\207\\025\\250\\332^]jM\\014K\\347\\204\\340\\030\\301\\33223\\324\\320\\236\\035\\274\\011\\272\\343d9\\373\\240\\220I\\374\\273Wh\\221\\307\\030\\307\\012\\276\\203\\275T\\324\\030\\371\\201p\\001\\3562N=\\271\\256\\332U$\\325\\226\\307\\024\\251E\\266\\331>\\221\\255\\352v\\226\\013\\014\\226\\351(\\213\\345\\022\\356\\033\\210\\372~?\\326\\265\\243\\324\\345\\276b\\260Z\\263\\316F6\\345T\\003\\323\\236O\\371\\355Y\\021\\355\\003\\315#\\234\\001\\355\\307\\025<2\\317d\\377\\000iI\\031>p\\030\\006\\347\\035\\376\\225\\311Z\\021\\346vGe\\032\\217f\\316\\317M\\323~\\305nZB$\\272~^M\\240n8\\300\\351\\355\\212\\255zP\\\\\\312\\371\\001cEBO`9\\317\\376=\\372S\\346\\325\\205\\226\\217\\024\\356<\\311[\\345A\\237\\275\\357\\364\\306\\017\\345\\353X\\227\\240I\\010\\270{\\207p\\330\\177\\235\\260\\244\\372\\205\\035\\372\\327\\034b\\333\\273:\\\\\\322\\330\\212\\372\\351.\\274\\270\\243\\017\\2059\\003\\2737A\\201\\214\\361\\237\\3075\\323_\\350\\361I\\360\\266\\342\\302\\355\\213:\\206\\222\\020P\\215\\223\\022H\\000\\364\\306\\016\\011\\35095\\227\\341\\335\\021\\245t\\276\\272\\033!V\\316\\030\\225\\300\\347\\251\\035\\311\\300\\002\\256k\\332\\224\\272\\225\\312X\\333\\341`\\210a\\266\\347\\037O\\363\\236~\\225\\351\\320\\207\\263W<\\372\\262u\\036\\247\\203\\321^\\201\\342o\\010,\\266m\\177a\\031[\\210\\362f\\213\\373\\343=W\\337\\271\\257?#\\007\\006\\273#%%\\241\\30188\\2730\\242\\212*\\211\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200-C\\246\\336\\\\D%\\212\\006d=\\030b\\244\\376\\306\\324\\177\\347\\321\\377\\000J\\3524\\021\\235\\016\\337\\337v\\177\\357\\243Z\\250\\270\\005\\2708\\366\\257:\\2462q\\223I-\\017J\\236\\012\\022\\212\\223oS\\204\\376\\302\\324\\363\\217\\261\\311\\372R\\377\\000`\\352\\237\\363\\347'\\351]\\340S\\236\\275\\373\\364\\251\\020\\014\\263d\\343\\246Ef\\361\\363\\354\\213\\372\\204;\\263\\317\\377\\000\\260uO\\371\\363\\223\\364\\243\\373\\013S\\377\\000\\2379?J\\364&\\031<\\021\\264\\364\\3158|\\334\\234g\\006\\227\\366\\205N\\310\\177P\\247\\335\\236y\\375\\201\\252\\347\\037b\\223\\323\\265!\\3205A\\326\\312O\\322\\275\\024\\020W\\234\\373S\\231\\270\\301\\351\\337>\\224\\277\\264*vA\\375\\237O\\273<\\337\\373\\013S\\377\\000\\2379?J?\\260\\365<\\343\\354rg\\323\\212\\364S\\324s\\363\\016\\2075\\011V\\003*3\\351Mf\\023\\354\\203\\352\\020\\356\\316#\\3737\\\\.X\\3033;`nb\\013q\\350O#\\360\\250\\016\\203\\252\\000\\177\\320\\337\\217q\\3765\\3501\\347q.\\0063R;c\\3459$\\363K\\353\\363[$\\037P\\203\\352\\317;\\376\\300\\325\\177\\347\\312N\\231\\355H4-P\\343\\026r\\034\\375+\\321C\\216I\\306:\\016y\\247aO\\000`g\\031\\365\\351G\\366\\205N\\310?\\263\\351\\367g\\235\\246\\205\\253#n\\026/\\323\\370\\200#\\3624\\035\\003Wf,l\\245$\\363\\222FO\\353^\\222\\017\\0358\\036\\203\\374\\377\\000\\223H\\255\\271\\271\\310'\\2474\\277\\264jvC\\376\\317\\207vy\\260\\360\\356\\254zYH\\177\\021\\3764\\277\\360\\216\\352\\374\\377\\000\\240\\311\\300\\317Q\\3765\\351j\\315\\324`\\020\\177\\375t\\252X\\263\\036s\\300\\305/\\355\\032\\235\\220\\277\\263\\351\\367g\\232\\177\\3027\\254\\177\\317\\204\\277\\247\\370\\323\\377\\000\\341\\036\\327Lb/\\261\\317\\263vvn\\030\\317\\2563^\\232\\233T\\344\\343\\007'\\2558\\260`\\000#4\\277\\264\\252vC\\376\\317\\247\\335\\236X|9\\253\\250\\311\\261\\220~#\\374i\\017\\207uq\\377\\000.2z\\366\\377\\000\\032\\365\\027\\007\\206\\003\\221\\214\\347\\2754(l\\343#\\362\\377\\000"\\237\\366\\225N\\310_\\331\\364\\373\\263\\314\\177\\341\\034\\325\\317\\374\\270\\311\\372\\177\\215\\003\\303\\232\\301\\031\\373\\014\\230\\372\\217\\361\\257R\\034\\023\\324\\250\\034s\\326\\241\\313(m\\2709\\341}\\270\\243\\373J\\247d?\\354\\372}\\331\\346\\203\\303z\\301\\351a'\\346?\\306\\201\\341\\275`\\234\\013\\0113\\365\\037\\343^\\235\\226\\333\\234\\214\\347\\222=)F{\\361\\333\\245/\\355*\\235\\220\\177g\\323\\356\\3170>\\032\\326A\\301\\260\\227\\364\\377\\000\\032?\\341\\032\\3263\\217\\260K\\353\\324\\177\\215z\\210\\371\\270\\340t\\2476\\303\\223\\220\\024\\016\\007\\255\\037\\332U; \\376\\317\\247\\335\\236Y\\377\\000\\010\\336\\261\\377\\000>\\022\\376\\237\\343G\\374#Z\\307\\374\\370I\\371\\217\\361\\257P\\344\\003\\363p;\\032hb\\253\\236\\01199?\\215\\037\\332U; \\376\\317\\247\\335\\236a\\377\\000\\010\\346\\257\\307\\372\\014\\234\\373\\217\\361\\247\\246\\201\\255\\300\\373\\343\\264\\2366\\037\\304\\254\\001\\376u\\351\\271\\030\\030''\\271\\351F;\\347<rsG\\366\\225N\\310?\\263\\351\\367g\\226\\235\\003U\\\\f\\312Q\\221\\221\\322\\220\\350:\\240\\353g'\\351^\\235&Wn\\356@9\\367\\252\\345rv\\200\\300\\221\\323\\332\\251f5;!\\177g\\323\\356\\3179\\032\\016\\250\\335,\\344?\\210\\240\\350:\\242\\365\\263\\220}q^\\222\\200\\007'\\030#\\256i\\247\\014\\037p#\\323\\276)\\377\\000h\\324\\354\\203\\373>\\237vy\\317\\366\\006\\251\\200~\\307&\\017NG\\370\\323\\217\\207upple\\007\\323\\212\\364=\\354"\\311\\\\\\014\\367\\372\\212\\231\\013\\226\\3119#\\237\\255'\\230\\324\\354\\203\\373>\\237vy\\251\\360\\366\\254\\006M\\214\\270\\365\\342\\223\\376\\021\\375W\\031\\373\\024\\237\\247\\370\\327\\245\\360\\027r\\343\\232\\215\\262\\011P\\300\\003\\307Z?\\264jvA\\375\\237O\\273<\\343\\376\\021\\375W\\376|\\244\\374\\307\\370\\320\\332\\006\\252\\277z\\312A\\371W\\243\\356\\014\\277\\206\\177\\372\\324\\303\\202\\312Fp\\007Pi\\377\\000hT\\354\\203\\373>\\237vy\\320\\320\\265Bp,\\344\\374\\305/\\366\\006\\253\\377\\000>R~\\225\\350c\\005K7\\006\\244-\\261w\\261\\302\\377\\000?\\245\\037\\332\\025; \\372\\205>\\354\\253\\340\\227\\236\\306\\315m\\257ah\\214nJ\\356\\003\\346Rs\\374\\363\\372V\\277\\210t\\311u\\007\\263\\201\\036\\024\\217\\314b\\223\\355\\347\\014r\\023\\003\\260\\035\\377\\000\\023\\311$\\343\\331E\\366\\275Gk\\3623\\330}\\356:V\\362\\351\\211\\270\\332\\302\\316\\221\\207\\311\\012\\307\\033\\200\\3130\\036\\274\\221\\370V5\\037\\277\\314\\367\\334\\350\\244\\275\\313-\\266$M:\\323B\\323\\344\\226B$(\\277 <on\\337\\\\\\361\\\\\\374h\\362\\273\\310\\377\\000\\353\\034\\227s\\352{\\324\\017!\\222\\365U\\313>\\337\\2241n\\001\\317a\\323\\324g\\333\\336\\265l\\241\\033\\334\\267A\\201\\232vpW{\\2639\\276wn\\210\\216\\330\\210\\204\\242H\\220\\225*w6~\\\\\\002\\304~<V\\\\\\020\\235B\\362Rz\\220H\\372\\326\\275\\364Q\\375\\232i\\243o\\336<\\306\\0223\\325v\\241\\377\\000\\032\\267\\244i\\313\\014\\001\\310\\3131\\316\\177\\012\\364)\\273A\\034\\362\\335\\243\\037L\\206#s\\032\\\\\\205\\332\\034\\034:\\356]\\303\\234\\021\\3378\\375+\\255}&\\013\\310\\246\\222\\341T2\\354\\362TF~pz\\235\\335\\000\\353\\307\\370\\326c\\331!\\236v`|\\260\\245\\333o\\\\\\001\\234\\217~\\343\\336\\266\\264\\233\\243sc\\033\\005 \\015\\214\\303\\037\\305\\203\\234}x\\374\\352\\264d\\263\\236\\271\\360\\362\\334\\254\\320\\211\\366\\316\\214\\2425\\224\\226v\\007\\270\\354\\007^kWO\\360\\365\\265\\245\\213\\317\\252\\222\\354\\254\\025\\\\\\267\\030\\347\\345U\\035I\\343\\323\\247\\324\\324\\367\\372\\2640\\336\\034\\037>\\351I\\033S\\375\\\\}\\261\\216\\347\\277<{\\032\\202\\326\\332\\363Y\\273-<\\216Uyf\\354\\007\\240\\364\\376T\\254\\202\\354\\232}Bk\\323\\036\\320\\303h\\3040\\356\\335\\203\\214\\027c\\334\\366\\035\\200\\251\\254tuU\\006Q\\201\\325\\275\\315jGo\\005\\242\\371p\\250\\317r;\\375i\\327N \\266\\311\\343\\212\\253w&\\375\\021I\\343{\\213\\261\\035\\254`\\272\\016\\027\\246\\343\\216\\231\\257\\037\\361W\\206\\245\\213X\\222]5<\\373Y\\276ue\\004`\\344\\344s\\356\\017=\\0163^\\301mr,4\\331o&*\\022e'\\314\\334>@\\010\\316{\\363\\310\\300\\356\\243\\327\\216\\036Y\\345\\276\\271\\232\\342Vb\\314K\\001\\237\\273\\350\\005D\\352:j\\353p\\344S\\321\\236w\\375\\205\\252d\\017\\261\\311\\223\\364\\246\\377\\000b\\3529\\307\\331\\037\\364\\257ARY@'\\2763\\330TS&[\\345$\\014\\001\\232\\347\\372\\364\\357f\\221\\267\\324i\\332\\367g\\005\\375\\215\\250\\377\\000\\317\\253\\376\\224\\343\\241\\352j2l\\344\\037\\225w1\\005\\022\\222\\3126\\216\\347\\212\\234;\\027\\000\\022G\\007u\\017\\035>\\310\\177P\\207vy\\334\\372U\\365\\264/,\\326\\354\\221\\2467\\022G\\034\\342\\251\\327y\\342F\\007E\\272\\004\\215\\343nO\\257\\314+\\203\\256\\3145gV\\034\\3228\\2614U)\\362\\240\\242\\212+\\240\\347\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;}\\000gD\\266\\036\\245\\277\\364#Z\\334c\\000\\344\\2360+3\\303\\240\\377\\000b[\\177\\300\\277\\364#Z\\312\\011nEx5\\337\\357%\\352\\317z\\217\\360\\343\\350\\207\\306\\243\\013\\217\\304\\342\\224\\003\\203\\337&\\204\\015\\300\\340\\001\\316jUM\\243$\\362N\\017\\275`\\331\\261\\003\\222\\010\\3343\\330\\012\\000\\300\\311\\317l\\373S\\376\\371\\316\\334\\372qI"\\220GP8\\007\\361\\240\\007m\\343\\031\\374\\351X\\362@\\034Q\\300U<\\364\\354(\\332\\033\\034\\236i\\000\\212\\0037\\\\{\\372\\323\\260U\\001\\034\\2202y\\342\\225T\\221\\374\\251\\350W'\\236)\\\\d92\\015\\300\\005\\316s\\232\\012\\266H\\344\\217\\255NT\\374\\330lsD\\2016\\217\\233\\240\\310\\350(\\270\\021\\355\\004\\343\\323\\361\\342\\235\\200P\\201\\372w\\240\\206`F0s\\316x\\251\\212\\0367\\036zg\\332\\220\\014+\\225\\030'\\217Jb\\202\\254\\270\\316H\\357S\\270\\332\\203\\327\\353LP\\007 r\\007ZI\\200\\230\\375\\313v8\\353\\216\\224\\340\\301]\\230\\372\\201J\\244r\\016\\001\\307Bj)NyRp}\\372Q\\270\\023+\\021\\3169=\\277\\255>2\\244z\\237Z\\204H@\\334\\307\\000\\364\\353\\305:7\\2121\\307\\335\\317sI\\240\\034\\356\\0008\\311\\374=iw\\226]\\270\\000\\001\\3169\\250\\244@\\347x\\312\\374\\334\\016\\200\\324\\340l\\364\\364\\346\\200\\032\\344\\034\\003\\221\\267\\217\\306\\243\\301\\034\\014\\347\\267=\\352l\\202\\304\\364\\342\\230\\270*0{zP\\200\\012\\223\\214\\023\\232r\\202\\000\\0358\\315\\000\\344\\347\\240\\243p\\012\\016>\\235\\351\\000\\204\\362H$\\037ZP\\344\\203\\203\\323\\261\\246\\347\\203\\206\\340\\373\\322n\\371;\\363\\351L\\011\\027\\234\\223\\234\\217\\347I\\260\\360?\\316)\\250\\337)\\311\\247\\207\\\\\\251\\316s\\324\\372\\322\\001@\\004\\234\\340sJ\\313\\375\\334g\\261<R\\020N\\007\\\\\\037J\\220\\002O\\312\\247\\336\\220\\312\\323)bN\\334\\214~\\002\\2531#\\2221\\3168\\305i\\025\\030P\\000$\\016s\\334UY\\342\\031-\\317?\\255TX\\025\\320+\\026`\\33768\\311\\246\\311\\221\\201\\264\\344u"\\224\\25620A#\\031"\\2238;O\\031<\\373\\326\\202\\035\\031%I\\306=\\263\\374\\251A\\313\\202\\307\\216\\231\\250\\2030a\\216\\247\\257\\267\\265H\\334\\034\\2779\\365\\244\\027\\036\\374\\307\\270\\216\\0019!\\2524\\214\\2020rs\\310\\307\\363\\377\\000=\\351\\350\\312_88\\366\\372R\\216\\035\\262pOc@\\014 \\354\\347\\247\\\\SX\\205\\317S\\203\\322\\200\\340z\\361\\327\\024\\236X\\306\\016\\017\\034\\342\\200\\0301\\222s\\324c&\\242\\231\\267\\310\\250\\207!GoZs(P6\\356+\\327\\216\\364\\221\\015\\331lr\\3075\\321J:\\334\\302\\263\\323\\227\\270\\373I^\\322\\345&U\\004\\241\\007\\004\\365\\307\\275nO\\253D-\\336Xcs#G\\345\\252\\036rI\\311$\\373\\177\\237Z\\313A\\225\\007b\\375M6e\\006\\026i2\\221\\016\\010Q\\202\\336\\302\\256II\\352e\\0318F\\310\\242\\261\\226\\273\\215U\\206\\360\\301\\233\\0038\\003\\267\\343\\375+L\\312\\261\\251E\\371\\244<\\340V}\\262\\310\\216\\322\\214!~\\270^\\336\\202\\254\\225Uc\\264\\021\\236rz\\223\\236\\264\\252M-\\012\\247M\\265q\\213\\024\\262\\313 \\306G\\015\\201\\364\\307\\364\\256\\257K\\210\\311c\\030\\003\\2221\\232\\345\\022\\341\\355.Q\\323\\030\\307L\\360k\\263\\322'\\006\\334:\\262ms\\2203\\367k\\272\\213\\346\\202l\\347\\252\\271d\\322\\033\\255*Y\\351\\246%\\307\\2317\\3109\\301\\347\\251\\377\\000>\\265sI\\203\\354:d2\\236\\024\\235\\355\\376\\350\\344\\376\\213Y\\272\\271\\373n\\263gnyT\\344\\343\\334\\217\\360\\255mr\\341m4I\\200\\030-\\027\\224\\203\\375\\3568\\3747V\\306]\\016;O\\2129\\032[\\273\\211vC\\031\\313\\310A<\\223\\216\\336\\346\\245{\\373\\302.m\\354nDp\\211r\\207$1\\300\\366\\365\\343\\203[66\\022G\\242K\\014H\\014\\322\\304\\312\\003p2A\\002\\271\\251\\222m:\\351\\222\\352 \\2308)#p\\353\\217\\257<\\001\\320\\376]\\262\\250\\344\\2666\\244\\242\\336\\247U\\240\\336\\274\\362\\213k\\271srrB\\3549\\300\\031$\\340\\000:\\217\\362i|A>\\345hP\\340w"\\252xe/\\274\\307\\324\\031-\\305\\274\\371\\014w|\\307\\034\\002\\270\\030\\3529\\347\\377\\000\\257[[\\273!\\346\\333\\200\\313\\270\\363W\\026\\371U\\314\\344\\227;\\261W\\304\\332\\204\\314 \\2601\\030\\025T4\\203x>a\\354N:t\\376\\276\\225\\235i\\036\\350\\237>\\231\\250ond\\3245K\\213\\231$Y\\013\\276\\003\\254a\\001\\003\\201\\362\\216\\235*\\305\\271\\314n\\242\\270\\353J\\354\\322\\222\\324\\245\\023\\235\\362\\246\\0067\\221\\317\\261<\\322\\314\\240\\000\\275\\301\\347#\\250\\251Z,\\334\\317\\216\\330?\\245D\\356J\\215\\255\\270\\343\\327\\326\\271\\245\\361\\350vC\\341"i\\012(\\005\\001\\007\\240\\315I\\023F\\312N\\320\\200s\\203\\374\\251\\201X\\253g\\005G^qLF\\336\\207\\007\\234\\360?\\235+\\024P\\327\\344\\007D\\271\\030\\012p\\247\\036\\2778\\256\\036\\273\\257\\020\\001\\375\\2019'\\0146\\200\\007\\177\\231z\\376U\\302\\327\\251\\201\\376\\033\\365\\377\\000#\\312\\307\\377\\000\\021z\\177\\230QE\\025\\332q\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\035\\367\\2078\\320-\\211\\351\\227\\357\\376\\321\\255@\\003\\021\\327=\\177\\245exhn\\321-\\367`\\017\\233\\377\\000B5\\260S\\030\\333\\327\\256O\\245|\\375\\177\\342\\313\\325\\236\\375\\037\\341\\307\\321\\002\\215\\244\\201\\323\\201\\221S\\260\\014\\271\\312\\221\\217Nj">PCg'\\257\\343R'\\011\\216\\240c\\030\\025\\2135\\031\\214\\020OOoJS\\203\\036\\323\\334t\\2438\\340\\365\\031\\306;S$a\\200\\024c\\360\\351@\\301A\\0308?J\\221>\\357\\012s\\326\\241R\\3001n\\325/\\007\\030\\353\\216\\3640\\036\\031W\\237_Q\\322\\234\\250\\006w\\016@\\365\\250\\3161\\226\\317\\327\\3374\\273\\360\\254s\\223\\217\\351R\\003]\\370\\345G\\003\\326\\241\\217s\\250^\\000\\037\\245Jr\\353\\270\\366\\352\\015K\\030\\0025\\\\\\361\\324S\\275\\220\\011\\034x\\033\\213\\022\\277J\\237\\234t\\031\\340\\012PF\\354\\364\\374(\\310g;\\216G\\257\\247\\371\\342\\241\\260\\030\\247q\\001\\211\\335\\351H\\333G\\251>\\376\\264\\371\\016\\006U~L\\375i\\201w\\256\\354\\201\\307\\\\S\\002)I1\\202\\016\\033\\326\\221N#%\\307Q\\305J\\360\\206\\005I\\307l\\212k\\240\\012\\243-\\307\\035)\\246\\200\\221U\\012\\371D\\026\\033z\\323C\\243\\262\\257\\226v\\372\\236\\335(\\211dG\\007;\\220\\343<\\236*\\301l\\022P\\014\\373\\201\\323\\353R\\300i;H\\343\\201\\323\\372Ry\\200\\203\\300&\\233)\\0148\\0041\\0038\\342\\240'i8 /LP\\220\\023\\014\\226\\003\\234\\373\\236\\224E\\220\\274\\362I\\317Z\\024\\364\\007&\\244'h\\3169\\002\\200\\016\\203\\236\\202\\243v\\343\\000r*L\\203\\203\\202I\\343\\236\\324\\326M\\314\\017\\003\\212\\020\\021\\202s\\236\\346\\220d\\347\\256i\\370l\\020\\012\\343\\337\\265"\\250*3\\237\\362i\\200\\305\\005\\\\\\034\\344T\\340\\020\\006@\\300\\355\\351M\\347\\200\\001\\374\\252P:\\037jM\\200\\001\\363`\\343\\247\\245J\\204\\343\\223\\220;\\346\\230\\253\\363e\\200\\343\\241\\305J\\240m\\300\\007\\037\\312\\245\\200\\234\\357 \\036\\002\\323\\031\\266\\362\\312I\\372T\\222\\002\\027\\201\\206\\307\\036\\202\\242\\311.z\\021\\237\\342\\037\\347\\322\\200+J\\243\\315\\347\\364\\250\\244\\350N8'?\\347\\374\\367\\253\\014\\340e\\224n"\\241\\227\\001\\203\\221\\271O?\\216:U\\240)\\264\\205dV \\216rG\\265L\\\\\\024C\\216\\017<\\363\\317\\365\\357Q\\262u'fG\\012?\\2559\\034\\206c\\3239=pG\\255h\\304>97\\003\\201\\234\\217_\\345Ow\\302\\206\\025Z"\\305q\\234\\014\\324\\310\\243\\313\\303\\021\\236\\231\\374\\3515`\\020\\006b88\\024\\025<s\\214\\217^\\377\\000\\347\\024\\365\\000\\257<\\216:\\016\\243\\212x\\033\\217\\353\\307\\350i\\\\evS\\206\\300\\007\\257CQ\\304V\\031\\274\\271\\016W=j\\333g\\007\\234\\014SY\\006Ha\\2707\\267\\025p\\251\\312D\\341\\314N6\\220\\011`\\020rMW\\226s4\\252v\\342$? =\\375\\352#l\\001$\\201\\2203O\\031\\035N\\006\\0068\\252\\224\\325\\264"4\\335\\365\\023q\\004\\356\\353F~}\\243\\337\\377\\000\\327A\\000\\261$\\361\\237\\241\\244Q\\265\\206psY\\232\\214t3\\334\\305\\017_^{WY\\245\\331\\223\\026\\\\\\340.\\006>\\225\\316\\351\\013\\347]\\311/^@\\025\\331\\333\\256\\333G\\003\\257O\\307\\025\\354R\\217,\\024O:\\254\\257&\\314\\3151<\\315m\\246\\004\\355\\014O$\\2361\\201\\374\\352_\\026\\336\\203\\025\\264\\000\\035\\230\\336\\336\\374\\221\\372\\020\\337\\235G\\245\\263}\\254*c\\346\\333\\237\\363\\370S\\265\\230\\204\\272\\354v\\303\\225H\\227\\237r3\\374\\332\\264\\276\\206}J\\266\\332\\315\\315\\267\\226\\012\\226^\\344\\367\\025\\265\\006\\241\\014\\323\\213\\221\\014b}\\233<\\302\\243p^\\270\\317\\\\f\\234`R\\271\\003\\003\\332\\241k5c\\222\\250~\\242\\215P\\264b\\313w\\035\\264{"\\210\\004\\034\\204A\\265Fy5\\310kw/4\\336c\\246\\024\\366\\007\\255u\\377\\000c>Y\\034\\005>\\225\\307\\353q\\220\\261\\205\\031\\371\\261\\372\\322m\\255J\\212]\\014\\330\\201\\010\\001\\353\\216~\\265~\\314|\\244\\237\\342l\\012\\242\\274\\3765\\241\\003\\0174F\\275\\021\\177Z\\340\\231\\255=\\310\\230\\223uq\\203\\2001\\232\\257\\202N\\0062\\017Q\\376~\\265 %\\357dP\\240\\357\\317_Q\\376MDA_\\275\\303\\177\\010'\\255c?\\210\\352\\246\\357\\020Q\\206\\351\\300\\317\\003\\326\\230\\011P>^z\\347\\037^\\324\\214\\377\\0007\\241\\310\\003\\234\\320\\010#<\\375?\\255+\\026gk\\271\\032\\035\\340\\354v{\\340\\356Z\\341\\353\\275\\361\\007\\374\\213\\367\\000g\\356\\246r?\\333Z\\340\\253\\324\\300\\377\\000\\015\\372\\376\\210\\362\\261\\377\\000\\304^\\237\\346\\024QEv\\234AE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\007y\\341\\302\\253\\241\\333\\022N~c\\377\\000\\217\\032\\331\\214\\214s\\223\\237\\322\\261<<q\\241[s\\375\\377\\000\\375\\010\\326\\344k\\270\\344\\014`v\\365\\257\\237\\257\\374Iz\\263\\337\\241\\3748\\372!\\340\\003\\264`\\220\\275\\351P\\235\\247\\234z\\234\\3207)\\350}?Zp\\017\\374 t\\315`l3<n \\002i\\256Wp \\356\\007\\246;\\3765+G\\220\\273\\272u\\305\\005F\\006q\\327\\214Qp R0\\0068\\340\\232y%\\224\\001\\232U@\\\\\\355\\003\\353\\351R\\200\\000S\\263\\236\\306\\206\\300\\201\\267\\347<c=\\2004\\3249p\\017\\004\\034m\\253\\004\\226\\004\\361\\236\\235j\\017,\\271\\340\\036\\235sM0$N\\031\\270\\343\\27156\\3009\\350;S!P\\0008\\353\\320\\036\\365#>:\\363\\237J\\207\\270\\022\\201\\236\\2143\\237ZB\\340\\215\\330\\343\\247\\326\\221\\203\\020\\240\\361\\323#\\333\\024\\240\\251\\\\\\0360r*@Lo\\332\\303;q\\320\\367\\245\\033\\263\\215\\271\\355\\212\\024\\355P\\024\\223\\317OJG\\311!\\216\\356:\\346\\230\\014g\\340u>\\370\\374\\251\\312\\274d\\343"\\206\\371y+\\327\\2574(=A\\3104\\000\\340\\3018\\332q\\327#\\237\\302\\234Ht\\001\\030\\014\\321\\260\\022\\2708\\242U\\013\\310nz\\232@FT\\001\\203\\311\\365\\246a@%\\217\\347NP\\340\\227$\\001\\375\\321Mq\\221\\214s\\353\\355T\\200g\\234\\006p0=\\177\\372\\324\\377\\0003s\\206\\353\\222\\000\\307\\363\\252\\343\\207\\310\\3502zU\\210\\327\\012@\\004\\020i\\264\\200\\221NA\\003\\220?\\012\\012\\356\\306G'\\265&\\320\\030`\\362O5(\\002\\240\\010\\202\\021\\301\\300\\367\\240\\015\\241Cg\\351R\\270\\331\\234\\237\\247\\2753\\004\\260,2=E\\027\\001\\020\\002\\330\\317^\\231\\251P(\\034\\343\\000r=\\252=\\270\\311\\030\\0079\\034S\\300\\332\\275\\017?\\255\\014\\011\\027\\201\\216\\000\\245S\\264\\201\\2203\\322\\231\\234\\0208\\347\\236i\\245\\362\\275\\261\\353H\\007\\360A\\306\\011\\376|\\324R\\226\\007\\031\\301\\317\\\\\\320\\256\\334\\016\\375\\270\\3155\\313m\\316H\\307\\353M \\000\\216\\204\\000F\\177Z\\211\\367\\2701\\234\\002\\007\\004\\017jvO'\\223\\317~)8'\\240\\310\\3655H\\012\\362\\022>e\\034\\343o\\246\\177\\3175Y\\331\\313\\036\\203\\034\\014\\343\\2778\\253S\\260\\344`\\221\\2368\\377\\000>\\225V@\\276^Nr~\\350\\003\\365\\255"&\\0106\\344\\262\\344t\\003\\247\\341V\\020\\214\\347\\031c\\352*(\\267H\\210Kd\\367\\307n\\325a\\006\\0063\\306rq\\376\\177\\316(\\223\\004<d\\221\\267\\005\\2621\\307\\347OU\\331\\234\\221\\352y\\353\\326\\230H\\033Xd\\201\\317\\035\\351\\353\\206\\371\\210\\301\\307J\\315\\214R\\006\\321\\202y\\364\\244<\\214\\377\\000\\017\\257\\245<\\361\\357\\337\\247J\\211\\270\\311\\035\\006F?\\302\\20426\\004\\220\\333\\2609?Jg\\314d\\307\\342*R\\011\\\\\\236:\\377\\000\\237\\322\\243\\333\\216\\2141\\216\\265HG%m\\257_\\033\\311d\\220oA\\222\\321\\0366\\373\\016\\365\\323Z]\\305yj%\\214\\365\\003\\203\\330\\373\\373\\326n\\257\\241\\245\\306\\353\\233U\\333p\\016J\\347\\207\\366\\372\\3266\\237\\250M\\247\\334\\264\\245H\\347l\\261\\225\\306\\177\\372\\342\\273\\345\\010V\\20755f\\2168\\316t\\245\\313=Q\\333h\\014\\336~\\334|\\273\\2075\\330\\223\\267M\\235\\2757\\177*\\340t\\215^\\331\\335\\314\\014\\333r0Xc?\\347\\372W^\\267E\\264\\353\\205-\\220T\\220\\177\\012\\355L\\346d\\372:\\005\\277\\347\\031\\000\\177\\350?\\375z\\255l\\355s\\255\\264\\216w\\035\\277\\310\\340Q\\243Le\\325\\356[\\241TU\\003\\360\\002\\215(\\252kSFX\\026RG\\327\\004\\323\\021\\321L\\236\\\\*\\243\\277Z\\201P\\261\\340U\\351Yr)\\201\\2219\\253\\261\\003\\036!\\025\\266OS\\\\=\\370\\337p\\221\\214}\\375\\340\\236\\301O\\377\\000^\\272\\373\\333\\320\\027\\034\\023\\330W),\\036^\\240['\\204\\001H=\\362s\\374\\205s\\342g\\313M\\263j\\021\\274\\2220\\024\\210\\256\\0361\\222\\021\\331T\\236\\340\\032\\271j\\245\\025\\234\\375\\347\\244\\236\\335~\\335"3\\251\\014\\025\\227\\035\\270\\301\\376B\\221\\004\\261J#n@\\350k\\217\\233\\23275\\345\\345\\220\\310\\243a;;\\002p\\347\\372\\325R\\300\\311\\265\\206\\342\\247\\201\\232\\327d\\005T\\203\\223\\353YR)\\363\\230(\\0349^~\\265\\022\\327Sj~\\356\\203\\031_~\\012\\3659\\311\\307Z6\\205\\316AS\\222H\\364\\245*X\\223\\320\\361\\267\\232@\\233\\210\\005\\371\\317\\347RjV\\361\\010#\\303wD\\360\\016\\336\\337\\355\\255p\\025\\350^!\\213o\\206.I\\317\\033?-\\353^{^\\226_\\3747\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\277\\016\\177\\310\\022\\330g?{\\217m\\306\\267b pH\\036\\225\\203\\341\\334\\035\\016\\337\\201\\374\\\\\\377\\000\\300\\215mD\\314\\330\\007\\006\\276\\177\\021\\374Iz\\263\\337\\241\\3748\\372"\\300*\\304\\000\\331\\031\\317<S\\207\\313\\310\\344\\236\\377\\000\\347\\351B\\234\\234\\220q\\327\\236\\264\\361\\234\\003\\217\\250\\025\\316\\315\\206\\216H\\340\\343\\267\\2657hV\\343\\030\\251J\\200I\\300\\037\\215&\\335\\303\\2468\\007\\236\\264\\256\\003\\025~c\\216\\375\\001\\251q\\301\\310\\246\\020\\333\\263\\327\\257\\343N#\\240=\\017\\250\\244\\300@\\274\\363\\214{zSPm\\003\\3278\\031\\364\\305\\014\\300d\\001\\301\\246\\256\\007\\314y\\367\\365\\377\\0008\\2461\\340\\234q\\370c\\326\\236\\250\\001?)=\\377\\000\\032b? \\022\\271\\307'=i\\356\\345\\027\\012rOA\\217\\326\\220\\211YNq\\2029\\342\\233\\200\\244\\234\\020\\177\\245"\\223\\265\\031\\217#\\256)Y\\376b:\\016\\234\\364\\244\\003db\\000\\000\\216}M5\\011*[#>\\336\\265\\034\\304\\271\\300 \\021\\353O\\014\\002c\\256)\\364\\002O\\367\\277\\032\\025\\310`9\\367\\246g$c\\276N\\015+\\014\\266\\007L\\344\\232,\\004\\244\\365\\340c\\024\\322wdg?Jh\\317C\\374\\351\\274\\2223\\237\\245+\\000\\340q\\306rh\\306\\376H\\351A\\003\\214\\034\\361\\23227\\3438=i\\200\\325D\\\\\\260\\344\\366'\\265.\\374\\200\\335\\305.B\\365\\034z\\366\\246\\2667t\\344\\361\\232\\000z6\\342\\254\\017jp\\347\\216\\240z\\212li\\261Ilz\\322\\203\\301\\003\\036\\346\\220\\017\\030n\\017Zi9\\347\\003\\351M\\033\\227\\257\\\\w\\244\\035F}(\\260\\023\\216y=\\273f\\230X\\017\\2550\\261\\003\\0352i\\240\\016q\\306h\\260\\022\\027\\366\\300\\365\\244\\334\\017\\031\\374\\015G\\234\\036\\303\\332\\203\\300\\351\\311\\364\\351N\\300;\\247^G\\000`\\322\\311\\323\\345\\034S\\010\\030\\003\\030\\031\\024\\222\\002G\\313\\311>\\375\\005\\000\\005\\207\\\\\\234\\226\\300\\367\\246\\343\\345=\\270\\353L\\306Skg\\035i\\373Y\\343$u\\350\\017\\364\\246\\003\\020\\226`\\033'>\\203\\275D\\320\\347*B\\364\\343\\324}j\\322\\220\\033\\337\\353K\\267s\\251 d\\366\\007\\245;\\330\\012`(\\001\\006~\\240\\375\\177\\306\\245(\\003\\015\\271\\007\\327\\036\\264\\342\\200\\222\\000\\330\\340\\364\\306\\007\\353H\\024\\237\\227\\236\\204\\214\\236\\017\\250\\375)\\334\\004V\\344\\203\\221\\203\\334\\323\\311\\316v\\2022y\\347\\363\\243\\345O\\224\\2022:\\376\\024\\340\\274\\362\\007<\\217J@*\\343`\\316\\017l\\365\\240/\\033\\206*ERA\\347\\034c$S\\014yf=8\\354\\177\\017\\360\\251\\002>w\\036\\376\\230\\030\\250\\377\\000\\204\\361\\301\\351\\317J\\260r\\2401\\004g\\221\\305@z\\020G~j\\220\\014\\015\\273\\220O\\312x=+\\234\\3618\\201|\\242\\255\\266\\354\\360q\\335\\016{\\364\\353\\374\\353\\244o\\223;s\\317j\\346\\255,\\345\\325u\\371%\\2362a\\215\\210\\303\\017\\310\\021\\372\\377\\000\\372\\353\\253\\012\\255.n\\306\\025\\365\\217/s2\\322\\342]>\\343x\\217|n\\234!8\\317\\036\\274\\343\\237J\\352l\\365[i\\241+\\015\\323\\024\\3071\\034\\251\\004\\216\\207\\377\\000\\255\\221Y7\\366\\017\\346a\\225\\227?:\\2021\\201\\350=\\277\\303\\362\\315\\226\\330#\\355m\\214\\300\\366\\357^\\257\\251\\346\\335\\305\\371\\035\\325\\266\\261.\\237\\250M+\\302W~6\\361\\301\\036\\2655\\306\\244?\\264>\\323n\\333I\\371\\260GL\\234\\377\\000Z\\344,o\\356`\\362\\355\\240O7z\\355\\216!\\030onBa\\211\\343\\277\\327\\336\\267\\244\\271\\270:\\002\\353/\\014Q\\037\\265}\\207\\3119 \\220\\244\\234g\\346\\037w\\271<\\324\\271X\\264\\323:\\017\\370J\\2320\\026pCz\\216ME'\\211\\274\\337\\270$\\306:\\271\\332+\\217\\271\\325\\235\\337tD@\\274aS\\007\\365 \\232\\316\\271\\324ZL\\371\\215\\221\\351\\236\\277\\205=\\305\\314\\221\\324j>'\\020d3\\0370s\\362\\366\\374\\377\\000\\255P\\322<B\\367\\2277>z\\262\\240\\\\\\304\\240\\347\\007\\323>\\2479\\315s\\276E\\345\\344\\261\\306cd\\014\\240\\251p@\\333\\353\\364\\343\\025\\277me\\035\\264*\\221\\203\\200rO\\251\\254k\\250\\270\\362\\276\\243\\204\\344\\245\\314\\213l\\322;y\\322\\037\\230\\367\\253+3l\\001\\276l\\372\\322O\\030X\\343Q\\327\\034\\325\\203\\002\\305\\002\\264\\207\\003nMr6\\254n\\223\\271P\\311">\\345r\\007\\241\\351I2\\027\\224\\273(R\\330#\\037\\347\\255Y\\216/\\231\\035\\306\\011\\354N6\\372g\\336\\241\\272o\\336\\0003\\376\\025\\224\\247wdoN\\233Z\\262\\251]\\316r\\000\\307\\031\\307?\\347\\374)c\\030\\227%r:\\363\\365\\243\\346\\337\\362\\365'\\034\\234\\012\\003\\021\\306\\334\\220s\\3654\\215H\\274I \\177\\015]\\343\\375\\217\\375\\014W\\235W\\241\\370\\224\\005\\360\\345\\320\\311'\\344\\316G\\373B\\274\\362\\275,\\277\\370O\\327\\364G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\274\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200;\\357\\015\\014\\350v\\3351\\363\\177\\350F\\267"!\\000\\035sX^\\032 hV\\374\\343\\226\\377\\000\\320\\215m+m\\301<\\366\\305|\\366#\\370\\222\\365g\\277C\\370q\\364E\\200\\340g8\\351N\\022`u\\307\\037\\225U\\363\\010RY\\206\\011\\374:\\324\\371,\\000\\372~\\025\\203F\\304\\233\\271\\030\\347\\003\\351\\223G\\\\\\360x\\340SO'\\220\\007\\341\\355Lf*T\\023\\310\\307\\322\\225\\200y#y\\307\\003\\277\\275!\\313\\0363\\203\\332\\230H-\\273\\275(\\366=GJv\\030\\340\\006y\\034{\\320\\310=;\\234\\016\\224\\370\\372\\363\\234\\036\\177\\032q\\003v@\\332\\304w<\\012W\\021\\022)\\003;Ic\\316)pJ)\\340\\220{\\324\\216\\277/\\004n\\317\\006\\253\\000\\340\\2608$q\\234\\365\\241j\\004\\343$r@\\344\\322\\002\\273\\217\\\\\\216\\265\\023I\\263*9\\366\\307J|\\214\\011\\000\\034\\237N\\224X\\006\\354?y\\272\\372\\016qNF\\0161\\216MFH#h\\376\\037\\326\\214\\014\\015\\275\\0161LD\\241\\200\\224\\203\\216\\0074\\365\\371r@\\353L\\003q;\\207\\266E9\\024\\005'\\030\\036\\207\\322\\220\\013\\2779=\\000\\240\\020W"\\207C\\264 \\317\\2550\\215\\254\\001\\007\\030\\305\\000=\\210\\3654\\3020\\344\\200rGJbm`J\\261\\012*O/,A=zQ\\260\\002\\222\\331\\015\\200G\\030\\241\\321\\231\\301\\007\\012?Z\\000\\301\\000\\360(\\007k\\002wc\\034\\012\\000\\227pS\\3637_\\312\\221\\030\\022\\012\\364\\353\\232\\204\\270lq\\317\\265=x\\217\\320\\223J\\300) \\375\\323\\327\\200)\\333G\\\\\\324`\\005\\372\\323\\210\\300'<b\\200\\034X\\343\\247\\261\\244`\\017Q\\370S\\261\\221\\3079\\355Lo\\227 \\234P18\\031\\334I\\310\\246\\257@\\030\\216E\\014\\304\\214\\200H\\006\\2246\\343LB\\225R\\334\\037znN\\354s\\3374\\322T6A\\350;S<\\302\\240\\206\\004\\372\\016\\231\\242\\303\\270\\363\\316I\\357\\203\\365\\024\\344e\\031\\034\\221\\301\\007\\2550:\\236J1\\301\\307\\035\\352\\302\\2041\\227U\\333\\221\\365\\241\\200\\324\\000\\364\\000t\\031'\\257\\326\\246D\\347 \\2002s\\221\\237\\363\\326\\2300\\023\\034sS\\250]\\243 q\\306}=\\352\\033\\031\\002\\256\\346\\007\\345\\003\\034\\021L\\030\\022\\205\\000\\026\\307\\347\\326\\2348\\\\d\\372\\014\\214R\\004\\016I\\300\\355\\371u\\307\\363\\252\\020\\302K\\273d}\\3409\\030\\372\\361DaFr\\177\\012s\\014;\\223\\337\\202\\010\\376\\224\\204\\215\\375x#\\271\\240\\007\\014\\234\\036\\207\\374\\377\\000\\215\\007z\\225-\\201\\234\\366\\247\\362Fx\\307\\003\\326\\207\\000\\226\\030`\\247\\356\\347\\265 \\032S\\235\\303\\277|g\\025\\001\\035:\\216q\\323\\336\\245b\\347\\220q\\203\\305G\\264\\261\\311\\000\\200zu\\305R\\030\\326 /*\\017=)\\366\\227\\037f\\272\\216h\\366\\006S\\321\\323pa\\350Gz\\037 r9\\364\\367\\246\\250\\012G\\\\\\377\\000\\236\\325Q\\223\\213\\272&QMY\\235\\012\\\\\\332j\\261\\264ok\\024\\276_\\022&74~\\353\\237\\274>\\236\\331\\305s\\236 \\323t\\330bh\\341*gs\\201\\203\\312\\257|\\216\\243\\323\\007\\276pN\\015fd\\356`X\\344\\023\\327\\232\\177%\\267L\\\\\\223\\311-\\316k\\326\\366\\316\\336g\\230\\326\\272\\031\\321\\351\\220\\034\\211\\241,\\007\\000G9]\\374u;\\225\\275\\270\\004t\\367\\253\\267\\267\\027\\027:%\\276\\224\\236k,w-)\\226|\\006g *\\200\\024\\221\\2007rNNs\\201W\\225\\255\\233\\003*\\017c\\264\\323.!\\205P\\225\\232-\\270\\345Kb\\241V\\327\\336\\037\\263\\323C%<3<\\262\\200<\\367_d\\333\\237\\347Vf\\320[MdG\\201D\\214\\003\\003\\301a]\\015\\267\\210g\\213K[t\\010\\363\\257\\002w\\005\\230\\2578\\316x\\004{u\\300\\3105\\213q\\250Egt\\262\\335\\310\\346I\\016\\347*2G\\277\\371\\364\\355[:\\313h\\352\\305\\032k\\251\\012\\302\\320\\242\\311,\\033p>g\\\\\\267\\036\\377\\000\\375l\\365\\253q\\306\\256\\350\\007#\\255m\\333\\352~\\035kl}\\271\\030c\\253\\002\\244~\\030\\256r\\353R\\263\\203R\\222+v\\3148'\\314\\013\\205\\335\\376\\177\\017\\324\\236^iM\\273\\255N\\211B\\021WL\\322\\220\\306$\\016\\340\\2208\\000\\014\\344\\366\\0251\\205\\344q$\\270\\3341\\210\\307 g\\245Ik\\000\\214\\244\\322a\\234\\250!\\261\\214\\003\\351\\350i\\314\\025\\237 \\340\\001\\234c\\374\\373\\327\\024\\352\\364\\211\\321\\010ud\\022+m\\334\\244z\\340\\363\\307\\275P\\237\\370I\\037/\\277j\\320\\334K>A<\\361\\317\\362\\252\\327\\0309#\\031\\364<\\342\\246\\016\\314\\266Q\\301`1\\320\\347\\220i\\361\\250\\363B\\225\\372v\\251\\000+\\220\\277xr\\010\\357OB\\026\\343$pq\\234V\\215\\212\\305\\037\\024\\177\\310\\265rG\\012v\\343=~\\372\\327\\234W\\245x\\234c\\302\\267g$\\214\\246\\011\\357\\363\\256k\\315k\\324\\313\\277\\204\\375\\177\\310\\362\\263\\017\\342\\257O\\363\\012(\\242\\273\\316\\020\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\272\\360\\350\\316\\211o\\300?{\\360\\371\\215l)\\301\\371\\217S\\310\\037\\347\\336\\262<8\\177\\342Kl3\\317\\314q\\353\\363\\032\\330\\012F\\334\\340\\034\\365\\374k\\300\\257\\374Iz\\263\\337\\243\\3748\\372!\\356I\\3063\\215\\331\\344\\342\\254\\037\\231@\\035N3\\364\\252\\344ns\\351\\331{u\\2531\\355$\\014\\014\\001\\327\\257\\025\\316\\315\\207\\025 }\\356x\\034\\032`\\\\\\214\\236;\\361Sc\\035z\\365<S\\012\\222T\\014\\034\\366\\0257\\0020\\270a\\307\\031\\025"&\\006X\\001B\\215\\271\\310\\371\\201\\351C\\276\\324\\306\\177\\032\\000Pv\\200\\000\\031\\034\\217jp\\003\\030#'9$\\324\\001\\271\\364\\031\\251T\\356\\343''\\365\\241\\240\\011r\\252s\\221\\357\\351Q\\003\\301Vo\\233\\034\\221\\326\\236J\\225*\\254\\003\\237^\\325\\020\\302)\\311\\317\\365\\241\\001\\033>\\331\\200'y\\307?\\235\\030\\221\\3347Nx\\252\\305\\211\\221\\244\\3439\\350{{U\\325+"\\361\\313\\021\\376sV\\364\\020.\\314m\\007'9\\024\\357\\356*\\340\\036\\270\\305DT\\243g85.\\342y\\003\\006\\244\\011\\000\\334H$\\3434`\\347\\033O\\024\\001\\362r0M8\\002x$R\\030\\3269L\\206\\000\\375zS0[#$\\216\\234\\323\\230nc\\273\\267\\037Z\\013lnNH\\353\\216h\\020F\\212\\252p?\\012\\223\\035\\363\\307J\\215d\\343\\000`{S\\201\\310\\355\\307\\255\\014\\005d!\\301\\307ZN\\243\\333\\326\\234~\\360\\347\\267\\245 !\\234\\372\\016)\\000\\236^\\325\\351\\223\\237\\312\\234\\010<\\364\\2470\\344q\\364\\244\\034\\374\\247\\217\\360\\242\\340\\033G\\343M\\333\\327\\334S\\311\\300\\355\\322\\233\\223\\370\\320\\000\\006q\\202})\\304\\361\\302\\361\\3074\\335\\374\\375z\\232^\\204\\343\\256:\\177*\\000\\214\\374\\243\\345\\030\\003\\322\\235\\234\\257\\004g\\035qO\\015\\202v\\343#\\261\\246}\\347\\343\\2469\\346\\200#\\037.3\\203\\236\\207\\332\\230\\352ZA\\362\\200\\331\\253\\005\\001\\214\\022\\006q\\214\\212\\215P\\226\\004c\\270\\315;\\214rD\\011\\034\\360\\017#\\336\\245\\001P\\340t\\317\\347\\3054\\014\\215\\307\\234\\366\\035jbG\\251\\343\\267\\\\T\\260\\000H=\\011\\300\\353\\305J\\270\\333\\324\\343\\337\\322\\2402\\000\\300\\203\\330\\200\\011\\305L\\2101\\313\\202\\303\\234`\\012\\2262\\031B\\254[\\2018\\307Q\\330\\361\\315F\\245be\\015\\236\\371\\004\\366\\253D\\006\\214\\200w\\022x"\\242(\\025\\272\\177\\026?\\317\\345M0 _\\230\\003\\264r\\006\\001\\353Ml\\207Q\\217\\271\\306zS\\3353\\210\\3279=\\177\\300~t\\2618u\\030\\307\\\\\\177\\237\\312\\250C\\202\\237\\342R\\243\\326\\237\\3161\\200F9\\372\\324j\\270\\030\\343\\257q\\322\\245`1\\214\\016{v\\251\\002\\026]\\243\\004.\\007\\2454\\000\\273s\\316F\\017\\034S\\336B9 z\\340z\\322\\003\\307=:\\344\\232\\240\\030\\3708 \\362I\\343=j+\\207\\021\\333\\310\\354q\\201\\324z\\236\\237\\255K&L\\237.O\\245d\\370\\206F\\217J!\\0167H\\240\\375:\\343\\364\\255)G\\232i\\021R\\\\\\260l\\003\\307\\331\\2238\\343\\203M\\226\\346+t\\014\\362\\014\\021\\331\\177\\256+\\230\\022H\\027h\\225\\300\\364\\014qH\\010\\035@5\\353{\\016\\354\\363=\\257\\221\\3206\\255f\\212A\\231\\233=\\225K\\177<\\012\\213\\373j\\330\\014G\\275q\\353\\020\\347\\365\\254@\\024\\267J\\177\\313\\330\\361\\364\\252\\3661'\\3322\\364\\332\\304\\357\\237(\\355\\031\\341\\210\\301\\377\\000?\\215gJ\\317;\\227\\222Fv\\365c\\222is\\316\\005\\00265q\\214c\\2612\\223\\226\\344\\266\\3731\\223\\367\\275\\352\\345\\234bMN\\324>\\335\\236r\\344\\036A\\371\\207Z\\242\\250A\\316Eh\\351h\\323\\3536\\020\\3062\\315:\\177:SZ;\\016\\033\\253\\235\\333\\252?`O~s\\376zU\\031\\2123m^\\016y#\\232\\276\\312\\350\\345H\\351\\324b\\250LB\\271*\\244\\343\\247\\031\\257\\002;\\236\\311\\023dr}q\\221\\334\\372\\3242\\005\\330\\314H<\\014\\202}\\252l\\356\\034\\343\\214\\0168\\347\\374\\346\\242\\227\\036K`\\003\\221\\21685\\242\\006S,W\\2000\\030\\367\\376T\\350\\276bN>\\\\\\361Q\\341\\231x\\371\\207\\246*uR\\331X\\360\\253\\234\\363\\316NO\\377\\000^\\264d\\224\\374N\\373\\2745w\\236\\237&=\\276u\\2578\\257F\\361/\\036\\030\\272\\007\\257\\311\\327\\375\\345\\2579\\257S.\\376\\023\\365\\377\\000#\\312\\314?\\212\\275?\\314(\\242\\212\\3578B\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\002\\212(\\240\\016\\373\\303k\\235\\002\\003\\214\\223\\270\\177\\343\\306\\265\\324n\\013\\214`\\372VW\\206\\217\\374S\\326\\377\\000\\360.\\177\\340F\\265\\017\\337Q\\234\\000?*\\371\\372\\337\\305\\227\\253=\\372?\\303\\217\\242\\037\\234\\217\\233\\003\\035\\007\\255K\\007\\030<d~U\\022\\020A+\\333\\232r\\361\\236:\\0021X\\263Rgu\\034\\016s\\300\\305(ls\\236\\015BpI<\\343\\035)C\\022\\300\\266q\\266\\246\\303$\\311\\335\\217_\\320\\322\\266\\0168\\034t\\364\\246)\\014\\330n\\347\\212q\\302\\216\\016E \\020)\\\\dpx\\367\\241\\230\\025\\367\\007\\257j\\030\\222A \\220i\\252v\\214\\034\\023\\326\\230\\012\\373z\\257^\\340s\\212\\202a\\265r\\305\\263\\3375!\\005X\\270\\031?\\375zVM\\331\\014A=y\\344SZ\\001I2\\331\\340\\376\\037\\215[\\266\\0001\\007\\377\\000\\327L\\362J\\365\\003\\003\\221OT\\330\\025\\263\\317aM\\273\\210\\225\\206\\322=\\271\\372\\323T\\200\\241W9\\035\\315?wO\\322\\243\\010\\330,G\\275H\\022\\241\\344\\202N\\007zE$\\263n9\\301\\340Q\\214\\002W\\007\\330P\\240\\206\\307\\037Z@\\0147>\\007\\003\\2514\\3606\\234w4\\004\\307\\2474\\343\\327\\320w\\315\\027\\002&PN\\010\\000\\367\\247\\223\\205-\\327\\322\\214\\005\\301\\31098\\311\\245\\335\\301\\317Q@\\010\\011#\\000\\3658\\2449\\317N\\236\\224\\374\\340g\\362\\244\\030+\\352A\\311\\240\\007#\\222\\271>\\270\\247\\025#\\004R*t\\007\\201\\232^y\\306>\\225 4\\201\\232@\\017B)X\\340s\\301\\365\\244\\0161\\221\\310\\366\\246\\000zt\\357\\305\\030\\310\\351\\336\\205!\\226\\234A\\301 g\\332\\200\\032p[\\351\\315.T\\035\\277\\376\\272N9#\\003\\003\\031\\243\\312\\031\\334y\\307z\\000\\216Fe\\007\\034\\322&\\343\\031\\031 \\261\\316jB\\244\\267n{\\236\\224\\370\\243\\307\\314\\027w\\256E;\\35012W\\012\\001&\\230\\271.9\\347\\370\\263\\376}\\205X\\010G'\\030=\\005D\\024\\264\\277w8\\000\\363\\350*@~\\003\\201\\270\\237\\224|\\244\\016\\336\\225b6`\\252:\\203\\337\\024\\305\\\\\\003\\236\\270\\346\\232$\\371\\200#\\353\\315N\\343\\036\\024\\010\\316\\325\\351\\352i\\216\\313\\317\\000\\014g\\353\\364\\374\\351Y\\216\\315\\270\\000\\203\\223\\203\\326\\242Y<\\303\\265\\372\\216N{\\323H\\007\\025,T\\222A\\307\\\\\\372\\323\\010\\335\\037\\336\\301\\030\\311\\375:T\\212\\270b\\305\\270\\036\\235qMA\\227\\301_\\370\\021\\357\\307Jb\\033\\026wm$\\003\\320\\014v\\307Z{\\235\\271\\340s\\320v\\246\\010\\2126\\364\\313z\\216\\364\\207\\005\\317##\\216E\\0006A\\206\\030\\301\\311\\357\\351M?0\\316wg\\034\\365\\245s\\311\\030\\355\\30740\\001z\\340\\343\\031\\364\\246\\002`\\347=\\317sX\\236'\\223m\\264\\021ww\\334\\177\\017\\377\\000]m\\344\\202r\\247\\031\\301\\346\\271\\357\\023\\310\\014\\226\\321\\363\\362\\3569\\366<\\177\\354\\265\\323\\205W\\252\\2141.\\324\\331\\200:R\\342\\220t\\245&\\275\\223\\312\\005\\031\\220})[\\24578e\\245n\\264\\000\\2509\\317\\245L\\243#\\247\\265B\\010\\332*\\314C T\\261\\241\\376X\\333\\212\\336\\360l\\013&\\253\\366\\206\\300\\020`z\\340\\236\\377\\000\\226k\\023\\240\\256\\247\\302\\036Zi\\262\\312\\251\\363\\031v\\271\\365\\003\\004}:\\3265\\252(B\\354\\326\\234\\034\\345dv\\272\\205\\244W\\020\\265\\354\\021\\341\\217\\336\\214s\\203\\216@\\376u\\313NAm\\335\\312\\201\\205\\3435\\324\\351\\327\\253\\014\\333Kn\\211\\270`F\\007\\266k#Y\\323~\\315\\276\\346\\002\\036\\007=\\277\\204\\372\\037\\177\\363\\336\\274\\352\\324\\323\\367\\342u\\341\\3524\\375\\234\\214G\\001rs\\317^EC"\\225V\\357\\201\\270\\363\\357\\376x\\251\\227\\234\\377\\000\\343\\331=*\\031>s\\30677\\035\\253\\024u\\262\\240%d\\301\\307#\\031'\\245<\\310\\273\\206\\320=x\\250\\325\\231]\\201\\355\\324\\347\\250\\245t\\005\\202\\220\\001\\025\\255\\210+x\\235\\324\\370n\\345W\\222Jg?\\357\\012\\363\\272\\357\\274@\\270\\360\\345\\337\\314\\010\\001\\000\\307\\373\\313\\\\\\015z\\231z\\2657\\353\\376G\\227\\217\\376"\\364\\377\\0000\\242\\212+\\270\\341\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200\\012(\\242\\200=\\003\\303G\\036\\037\\266\\364%\\271\\317\\373f\\264X\\225|\\343\\240\\355Y\\376\\033\\037\\361OZw\\345\\270\\377\\000\\201\\232\\322+\\220\\331=\\275q\\315|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\017\\203\\356\\007\\004\\343\\234\\372z\\324\\252\\300\\266\\006\\000\\316G\\2555\\031|\\275\\240\\214\\223\\216:R\\250\\005\\016\\007\\003\\236\\265\\2135\\000@\\335\\201\\353\\237zp\\000\\014\\343\\22009\\246\\001\\214\\214\\217bi\\352A=GPi\\014#\\334I#\\267?\\2157;\\260A\\364\\247\\241\\352G\\177\\322\\242<\\2203\\333\\024\\000\\362\\331\\306\\017\\276i\\252C7\\271\\355\\216\\264}\\322\\017L\\232@\\016F\\016?\\012\\000z\\261''\\247\\362\\246yD7\\007\\003\\036\\264n\\035\\327\\251\\343\\035jE\\330\\240\\237S\\323\\024l!@l8$\\201\\332\\221O<\\200A>\\274\\323\\362J7# \\367=icQ\\215\\304\\236\\230\\244\\002l\\316\\341\\270\\364<z\\322\\250\\001\\000<\\363\\220OZ\\003\\205\\356:\\344\\366\\245\\017\\204\\034v\\357H\\004\\303t\\031\\311&\\227\\030\\004\\372\\234\\361J\\031\\227\\030\\003\\2569\\247\\026\\033\\271\\374(\\001\\312\\244\\200q\\316;\\320cf\\0079\\305\\001\\360@\\007\\024+\\234c\\250\\366\\251\\030\\334\\0169\\350)\\277t\\374\\247\\217j\\013g%y\\036\\224\\212HPO4\\3049\\201`6\\367\\353\\236\\324\\251\\203\\236N\\341\\336\\232\\252J\\234\\034\\036\\302\\234\\257\\202\\024\\360~\\235h\\002\\\\d\\322c\\320\\036iW$\\023\\306\\017_cJ\\007\\006\\244v"a\\220\\001\\346\\240T?\\2075a\\362\\244\\343\\247\\267\\255\\035\\206sUq\\014U\\307S\\333\\201O$\\252\\373c\\275\\033p\\0019\\003\\371Sq\\222I;x\\240\\007&Y}\\001\\247\\272|\\235:RE\\323\\003\\203\\333\\232z\\206a\\216\\247\\267\\245H\\304\\330O\\030\\031\\351\\232th\\304\\214\\221\\327\\363\\025")\\003\\346\\306}\\251\\3416d\\251\\341\\272\\212W\\001\\214\\003|\\207\\267>\\224\\236N\\321\\362\\236s\\372\\032\\225\\202\\356\\351\\264\\376X\\245n\\210TrO8=\\251\\\\ed\\310\\005T\\221\\236EBw\\344\\003\\214c\\007\\212\\235\\201\\204\\340d\\361\\202G\\2451\\201r[i#\\006\\251\\000\\201\\263\\363\\221\\327\\236)\\230\\371\\311\\310\\036\\376\\324\\375\\245B\\200\\275\\177J\\037fWy\\333\\234`\\216\\240\\363\\377\\000\\326\\246\\003C2\\222\\254\\016z\\012x\\310Pq\\333<\\236*)"`\\240\\256\\007~:\\212S&\\344\\000\\023\\300\\344\\012\\004+\\005$\\266N\\356\\234S[!@9=\\371\\245\\013\\301$e\\217\\030\\364\\246\\310C\\246\\006\\320p(\\000Q\\236\\011#\\216\\001\\343\\374\\367\\244~c\\003#\\007\\234\\367\\244\\031]\\247#\\246\\005\\005\\267'a\\223\\237R\\017\\371\\3050\\021\\201*\\017Z\\346|N\\002\\337@\\274\\377\\000\\252\\311\\037\\360#]WE9#\\203\\317J\\344\\274J\\333\\365E\\037\\335\\210)\\366\\344\\237\\353]x/\\342\\234\\330\\257\\341\\231\\024\\001\\232)\\3508\\257\\\\\\363\\006\\037\\274\\277QNq\\203Ls\\237\\316\\236\\34784\\000\\213\\311\\305\\\\\\210|\\265MO\\315W!\\373\\2652\\032\\036\\337t\\375+\\243\\360\\233\\225\\323\\347]\\300\\003'C\\364\\256q\\276\\343}+\\242\\360\\324e\\264\\266\\300\\343\\316\\353\\217a\\3765\\311\\214\\376\\013:\\260\\337\\304GC\\035\\312\\304\\345pI8\\006\\267\\254n!\\324-d\\205\\237 \\247\\316\\207\\034\\214u\\037\\355\\017\\324\\0169\\000\\036SqG;\\210 \\236\\343\\2559.\\245\\202U\\225>\\360\\354O\\371\\364\\257>\\215W\\015\\036\\307]Z*z\\255\\304\\3254\\371\\264\\313\\303\\033|\\310\\303tn\\335\\030{U\\011\\012\\206\\001z\\373\\365\\256\\241\\265\\013}v\\333\\354wR\\030\\344\\317\\312\\344\\214\\241\\347\\221\\352\\017q\\333\\237j\\345\\244\\006\\336f\\212A\\262H\\230\\253\\251<\\206\\007\\004\\037\\312\\252pI\\336;\\005)\\266\\255-\\321I\\362\\262\\234\\343\\251\\372\\032p\\005\\330\\225>\\371\\024\\311\\244\\334O\\034\\347\\267\\2751d\\340`d\\367\\377\\000\\032\\253hU\\312\\376$\\332|=1Q\\216\\023\\257\\373\\313\\\\\\005w\\336!?\\361O\\335\\014\\362\\002~?:\\327\\003^\\236\\003\\370o\\327\\374\\217/\\037\\374E\\351\\376aE\\024Wq\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\035\\311\\320-1\\376\\337\\376\\206\\325\\254\\234\\311\\216\\207\\002\\262<8q\\241[\\020~a\\273\\003\\376\\006ka8\\310=\\207\\257\\177\\255|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\001\\342>x$\\323\\267a\\\\\\343 \\361\\300\\351JFc\\35294\\310\\325P\\266\\321\\214\\363\\315djF\\357*\\002\\304\\241\\036\\2258?\\273\\313g\\257N\\365\\0139\\334@<\\036\\370\\355\\353I\\274\\347\\031\\347\\372Q`,\\307\\367\\000$\\361\\315'\\033\\300\\340\\340\\365\\307Z\\007\\3128\\344t\\036\\337\\347\\024\\252\\240\\000A\\351\\357R\\000\\0279\\364\\007\\031\\245\\362\\313\\015\\247\\200y\\353B\\234\\270\\365\\355N=1\\236OqHde9\\034\\361\\307~\\206\\206\\332\\304\\357br:z\\322\\375\\016\\017\\034\\324l\\030d\\216ri\\210X\\303\\025\\347\\216\\235MO\\203\\301n\\234\\344u\\342\\241L\\200\\000\\317<sS\\243\\025?/\\335\\377\\000=\\3510\\023\\007vs\\323\\236i\\300t\\371A\\004q\\232\\220\\221\\234\\021\\301\\244;\\010\\345\\271\\305+\\200\\303\\220\\015<.x\\036\\235\\251\\304q\\307JL`\\237Lv\\244\\002d\\216O_\\345K\\367c\\007\\241\\366\\245'\\003\\346\\031\\036\\324\\323\\327v\\336\\203\\037\\205\\0006>W\\323\\036\\224\\374w\\250\\274\\302[\\000r;\\012\\230\\246W\\251\\024\\330\\013\\221\\264\\036\\331\\353@ g\\237\\312\\232wy\\211\\201\\201\\334\\212r\\205R@\\373\\315\\311\\366\\251\\030\\002\\335A\\037JV~\\0118\\315\\012\\006\\341\\337\\006\\243f \\340\\363\\333?\\375j\\000\\013\\216F\\177\\012w8\\303.x\\344z\\325v\\033\\271=:u\\372\\324\\261\\2068^\\2120W\\003\\257\\371\\305U\\204H\\312x\\351\\216\\274\\012\\031\\030\\257\\342)\\330\\300\\307\\245<\\002S=9\\353Sq\\202\\242\\252`\\364<\\340\\324\\261r3\\217|\\032@\\204\\000G\\362\\251\\027-\\363\\001\\232\\226\\300\\010%:q\\353ND8\\347\\245&\\340\\270\\031\\033\\216x\\317&\\2348p\\334\\217\\306\\220\\001@\\337yI\\031\\364\\316\\005+\\302\\20123\\217\\257JP\\245G\\035:\\365\\246\\230\\222@pJ\\263~\\030\\244\\003\\012)=Kw\\332Fj4+\\274\\240\\343\\236\\377\\000\\347\\024\\217\\013\\356WR_\\034T\\213\\0239;A\\357\\200\\313\\214f\\230\\312\\351\\270\\347 \\216h`|\\316~\\246\\256\\244C\\356\\226$\\363\\305B\\352"R1\\306px\\247p!nJ\\252\\223\\273\\241\\250\\372\\277\\316\\024\\201\\223\\323\\257\\277\\351HC\\0318\\3163\\222q\\322\\234\\235p\\006Nq\\236\\225B\\034\\310\\253\\036Ks\\374 \\324 u>\\231\\034\\001\\212\\234\\260g*\\006H\\000\\203\\376\\177\\3174\\326M\\251\\201\\320\\222s\\326\\204\\004{r\\003\\036;\\376T\\3008\\300\\354F*c\\235\\271\\347\\261<\\322\\005\\343\\030\\317\\270\\355B` \\000\\2141\\306y \\232\\344<F\\310uR\\021\\211;\\001n:\\037\\377\\000V+\\257\\031\\340\\343\\220y\\346\\270\\337\\020\\017\\370\\235\\316zp\\277\\310Wf\\005~\\367\\344r\\342\\377\\000\\207\\3633;\\324\\3102*!\\326\\247\\215y\\025\\3533\\316Ey;\\323\\372\\306\\0156O\\274G\\245*\\037\\220\\257\\343L\\004^\\265n\\037\\273T\\373\\325\\270>\\355L\\201\\022\\236\\206\\272\\257\\012\\177\\310\\035\\363\\201\\211\\211\\377\\000\\307Er\\325\\321xZf\\026\\223"\\234\\004`\\377\\000\\230\\307\\364\\256LZ\\275\\026t\\341\\337\\357\\021\\266\\311\\311S\\301\\252\\222\\246\\336rH\\317\\246\\015[.\\244\\021\\2021\\316I\\367\\357Tn\\231\\223\\356\\020\\244\\364\\257&;\\236\\221Vm\\204\\344r\\335\\371\\351PJ\\3573\\273I+\\031\\031\\216\\347'$\\344\\365\\3159\\210V=Os\\3765\\023;3c\\256\\006?\\012\\350\\211,\\212L\\356\\311\\307\\035OcI\\036\\001\\004\\037\\316\\234\\313\\320\\221\\223\\317\\024\\315\\244\\000s\\223\\272\\254\\222\\226\\274\\017\\366\\005\\317\\037\\334$\\377\\000\\300\\226\\270j\\356\\274@\\341\\264+\\234\\034d/\\037\\360%\\256\\026\\275<\\017\\360\\337\\257\\371\\036^;\\370\\213\\323\\374\\302\\212(\\256\\323\\210(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000\\357\\2746?\\342Ans\\311\\335\\307\\374\\014\\326\\240\\345X\\001\\236++\\303{\\277\\260\\255\\266\\365%\\272\\377\\000\\274kb0\\0306;\\216\\375\\215|\\375\\177\\342K\\325\\236\\375\\037\\341\\307\\321\\016\\\\\\210\\271\\317\\034})\\252@<\\026\\247\\200\\246&9\\367\\365\\346\\233\\323'\\216;V&\\244l\\003\\003\\214\\363\\322\\224!'<\\221\\214\\202hT\\347'\\247Lb\\245'\\236;\\212m\\200(\\347\\223\\201\\237\\363\\374\\205*\\223\\306Np?\\012\\177E\\372\\361@\\030\\\\w\\002\\244c\\024\\202\\370\\347\\036\\3650\\\\)\\301\\034q\\212\\207i\\3162?\\016\\364\\360\\330<\\203\\354)0\\020\\214\\366\\347\\240\\305D\\370\\351\\315N\\304\\023\\214\\217\\361\\240\\250\\356W8<\\232w\\001\\261\\340tn:{T\\300`\\017\\177\\326\\241T;\\3169\\031\\344\\212\\235A#$\\217\\255&\\002\\221\\205\\306H\\317Zh\\214\\267L\\016s\\212\\223\\004\\022M;\\250<\\014}*n\\000\\314@\\332\\0178\\240 \\340\\202z\\347\\024\\244pq\\374\\350\\004w\\372c\\322\\220\\010\\331\\311\\355\\323\\2656I0\\334t\\372u\\247\\0207rM6M\\337\\300:\\203B\\002.\\014\\204\\347\\203\\351\\370\\323\\212y\\247\\357\\235\\240v\\245P\\334\\007\\364\\340\\346\\245\\331\\203\\264p3\\311\\246\\330\\010F\\010+\\310\\306)\\204\\204c\\225\\300\\300$\\323\\360\\300\\356l`\\016q\\336\\231\\260\\263r{\\367\\356) \\035\\346*\\256\\342\\033\\031\\300\\365\\241\\260X\\200\\271#\\237\\233\\275Bd\\345\\262\\240\\014\\340c\\250\\246\\217\\232Uu$\\2208\\311\\343\\245;\\000\\360\\035\\233 `w\\343\\374\\363S\\230HC\\263\\214\\216\\0014\\345&M\\304\\360==jRC\\01468\\343\\007\\212M\\201\\032\\306\\337*\\261<py\\352*^\\235\\017A\\234S\\372s\\200F=\\177Z\\204\\246\\\\\\016\\011\\034\\034T\\2012\\347'~\\000\\316(\\313*\\261S\\3162>\\264\\205I\\014\\007\\256\\177\\012G`\\004ci \\344ds\\376\\177\\372\\324\\200@T\\315\\2200I\\306\\010\\353R+\\023\\220x \\373u\\250Ws\\214.v\\216\\016H\\300\\353V\\342\\210\\225\\332p0>\\360\\357\\306(c\\024\\251\\306\\024q\\301\\372Tk\\034\\2079\\003vx\\307q\\353S\\202#EA\\214\\364\\345\\262i\\241\\211\\037t\\344g\\034v\\244\\0036\\262\\026\\022K\\222O\\360\\236\\242\\222'\\000\\223\\267\\234\\363\\333\\336\\234\\350\\257\\225\\317\\030\\300\\307\\370\\323\\327\\021\\347\\256{\\374\\277J\\004A3\\311\\346\\001\\032\\206\\004\\200Fq\\3056PYUx-\\367\\215Zx\\225\\233\\334\\216q\\364\\250\\314e\\237\\202@\\035\\016(\\270\\024DBM\\304\\021\\364\\365\\241UB\\365\\351\\370f\\254\\010H|\\214\\016\\330\\31740T\\016p\\001\\007 c\\364\\252\\270\\306\\014"\\345\\206H\\344\\346\\232\\343sv<t\\007\\247\\265)\\310\\213\\004d\\3658\\246\\034\\237\\230\\256\\011\\000t\\353@\\204\\341\\227\\240\\003?\\2253\\220\\245\\217\\247LqR\\005\\012\\243~\\027<\\021Q\\250\\336\\204\\202v\\373S\\000\\301'r\\236\\304\\327\\033\\342\\020WY\\233#\\357*\\343\\362\\025\\331\\341\\267\\003\\216\\376\\234W'\\342\\300\\006\\255\\031P\\0000\\251\\375Z\\273p/\\367\\277#\\233\\027\\3743\\021~\\360\\253q\\016j\\254c-WP`W\\253#\\316\\211NQ\\373\\306\\245\\021\\272\\252JF\\022L\\240>\\343\\007\\372\\212Y\\000\\332\\304\\372\\232\\350/\\264\\361\\027\\203l%\\000\\027YD\\254\\336\\201\\307\\377\\000\\263Q:\\212\\016)\\365v*0r\\273\\354s}\\352\\314\\035*\\273\\2141\\253\\026\\377\\000t\\325\\275\\210[\\223\\326\\377\\000\\205\\225\\376\\315r\\352\\271\\311\\013\\317N\\365\\201]\\027\\207\\233\\032K\\001\\367\\274\\366\\376K\\\\\\230\\277\\3413\\247\\016\\277x\\215Wp\\300\\206\\310>\\370\\254\\367$\\271\\015\\221\\214\\361\\375?\\235_g\\014\\330#\\0319\\317\\255g\\312\\205\\\\\\257$\\343\\217J\\362\\340zD`\\200\\276\\2479<\\367\\250X\\016\\031F\\033w\\347R\\242\\362p\\033\\034\\001Q\\037\\231\\272\\214\\366\\377\\000\\032\\325\\011\\210q\\2203\\305B\\261\\014pr\\011\\007\\024d\\034\\344\\202;\\037\\302\\236\\212\\331Q\\330\\365\\031\\307\\353W\\261;\\231\\236 \\300\\321.\\000\\035vs\\377\\000\\002\\025\\304Wk\\257\\357\\032E\\310\\317\\313\\205\\377\\000\\320\\205qU\\352\\340\\277\\206\\375O+\\035\\374E\\351\\376aE\\024Wa\\304\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000w\\336\\033\\031\\320m\\273\\037\\234\\363\\376\\361\\255E\\227\\022\\355\\350;\\266:\\363\\376\\177:\\314\\360\\326\\177\\260m\\261\\376\\327\\036\\2779\\2556\\034n\\034\\036\\275\\005|\\375o\\342\\313\\325\\236\\375\\037\\341\\307\\321\\022\\237\\365c8\\364?\\322\\201\\311\\012:\\361\\370\\366\\250c-\\345e\\260[\\246?\\317\\341R3\\025\\371v\\364\\031'5\\223F\\242\\217\\\\\\377\\000\\365\\352@\\006x\\356}x\\250\\300\\031n\\370\\306>\\2658NG\\256F9\\251c\\032{\\360FMI\\267)\\310\\004\\001\\353L\\350@\\350\\005X\\030#\\221\\202z{T\\261\\220\\225\\030\\004Tm\\311\\004\\016=*\\323\\000\\006z}j&Q\\236F8\\305\\011\\210\\200dw\\351\\353\\336\\23470\\034\\217^\\234\\321\\267\\234t>\\275\\005*\\202\\007#\\234U\\000\\271\\307\\000\\365\\251\\211ld\\000N{\\324 \\220\\333\\233\\257z\\231I\\316T\\037S\\232\\226\\003\\3019\\3032\\237\\366z\\323\\307^N\\0069\\250\\326N9\\3508\\034\\323\\367\\002;\\324\\260\\036@\\307ny\\246\\340\\2059"\\224{\\367\\246\\226l\\035\\334c\\234\\343\\212@+dg\\326\\231 ;\\371\\343\\003\\203\\351N\\035r\\240u\\306}*7\\334N6\\034\\016\\234b\\232\\001W\\337\\030\\366\\251G'\\217\\361\\250\\321A\\007\\013\\217\\247Js~\\355N\\033\\223\\323&\\200\\024n\\311'\\277zC\\200\\240\\363\\302\\375EG(\\221\\200U\\316}\\272{\\320\\310\\242\\022\\233\\260y\\033\\251\\330\\006\\221\\031\\347#\\257\\343\\326\\243WQ\\200\\250\\330\\355\\3061J\\274\\343\\003\\251\\364\\357N\\316\\027\\346\\355\\351L\\007#\\221\\363\\034~\\\\\\325\\220\\003\\250<\\025=I<\\212\\250\\024\\266\\000\\344s\\216\\335\\252\\304\\012\\254\\312\\216r@\\357\\307\\371\\353I\\201d\\376\\354\\3412\\017@\\015H\\261\\003\\2228\\371z\\367\\317\\2455P\\031A<v\\347\\353O\\226TO\\274\\016s\\363zV`D\\315\\211T\\002A+\\216~\\275jFF\\223j\\216\\024rI\\025\\001\\036d\\203\\250\\007\\201V\\331\\227vr\\000\\366\\374;P\\300\\211!\\216\\030\\366\\2530\\005\\262OO\\363\\322\\244F\\014\\3465$`pr\\015B\\203\\0231c\\2362\\253\\216\\265>\\304E \\360X\\362q\\202qI\\200\\035\\252\\243\\000u\\347w\\265:(\\333hf \\356'$\\372v\\246\\034\\025\\3712On\\337Zqv\\362\\263\\333=\\017oj\\000P\\233\\216\\325\\357\\372\\324\\330\\033@'\\222q\\310\\024\\330\\335D#$t\\3019\\307\\341R\\205\\371F8\\3079\\035\\363I\\201Y\\260\\011\\034\\000;\\3434+\\344|\\247\\357{t\\245t\\311\\333\\234\\237C\\3764\\322B\\222\\335@\\357\\236\\324\\0000,\\371\\005@\\007\\245@\\355\\221\\200\\012\\234\\014qN'\\007\\004\\347\\237N\\365\\033\\037\\233 \\340s\\201T\\2063\\201\\307P\\3039\\306)\\301\\224\\002\\010'\\217L\\347\\265&y\\003\\004g\\2758\\217\\335\\214pq\\372\\177\\221L\\006I\\363\\025\\031\\300\\034\\221\\237jE@\\252\\025\\007\\312\\006=\\215.\\354\\002\\010\\365\\033z\\321\\221\\267\\345\\003\\034c\\035~\\264\\010aP\\2520q\\323\\025\\310\\370\\257\\215J\\023\\236\\014#\\037\\367\\323W`H\\300\\004\\344W!\\342\\354\\215B\\034\\216<\\241\\203\\370\\232\\354\\301\\177\\024\\346\\305\\177\\014\\311\\205q\\317\\255Y\\252\\3610\\332\\006y\\251\\035\\261^\\263\\334\\363\\321\\014\\303\\026\\344\\373\\327\\243\\21316\\203\\035\\221Psj#\\\\\\256p\\333@\\317\\347^p\\331c\\027\\246\\341\\232\\365T\\012B\\263\\022\\024\\216=\\372\\017\\347\\\\\\030\\3715\\313c\\253\\012\\223\\346<\\252u\\001\\262;\\323\\255\\217$S\\365!\\267R\\271\\\\\\001\\373\\3068^\\203\\236\\237\\207J\\212\\335\\200\\223\\236\\342\\275\\004\\357\\033\\234\\215ZV-\\364\\025\\320\\370p\\343O\\224s\\304\\244\\036=\\205s\\315\\367k\\242\\360\\372\\025\\322\\313\\236\\222J][\\351\\307\\364\\256<g\\360\\216\\2347\\361\\015WV\\340s\\234\\347\\377\\000\\255U\\335\\010\\335\\223\\301\\356?\\317\\275N\\345@\\300'\\035\\215W\\232M\\244\\251\\007\\327\\332\\274\\264z%y\\0300!Tn\\307^\\231\\252\\222\\025\\030\\341\\277\\016\\242\\254\\270\\334\\011\\034}j\\263\\002\\010\\306N8\\034\\364\\255\\242K"\\3068\\344\\343\\326\\234\\011\\353\\216>\\2249\\310\\3160q\\232hf\\333\\202\\007\\025\\241%\\017\\020\\374\\332%\\313q\\316\\337\\375\\010W\\015]\\256\\274\\300\\350\\227\\000\\017\\356\\377\\000\\350B\\270\\252\\3650?\\303~\\277\\344yx\\357\\342/O\\363\\012(\\242\\273\\016 \\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\000\\242\\212(\\003\\275\\360\\340?\\3306\\304\\017\\357\\017\\374y\\253\\\\\\000w\\000z\\360N1\\212\\312\\360\\336?\\341\\037\\267\\311?\\305\\333\\375\\263[\\201@\\012\\275\\010\\374k\\347\\253\\277\\336K\\325\\237AG\\370q\\364Dq\\305\\362\\226\\352\\270\\000\\221\\330\\366\\251\\012\\201(l|\\303\\214\\343\\212\\010%\\260\\033\\345\\307B{RD\\314[i\\310#\\265bh#\\2569\\342\\236\\214\\300c'\\247P?\\317\\371\\024\\216\\270\\007\\275\\013\\264\\221\\2001\\355@\\311\\011\\311\\317\\034\\372\\034\\032\\221Wq\\351\\310\\003\\234\\323\\024\\002G<\\377\\000J\\2226\\303\\214\\257\\346:T0\\036\\221\\226\\310#\\251\\307\\006\\220\\307\\273=\\015L\\213\\203\\216\\247\\257\\025;(\\306B\\217\\360\\251\\270\\031\\2466\\007\\356\\234{\\016\\364\\341\\013\\267\\247\\345V\\317\\000\\360A\\353K\\356}sO\\230\\012\\342\\335G\\336\\347\\256)Z1\\214c\\223\\332\\254\\221\\200\\006x\\250\\311\\004\\347\\320v\\377\\000?ZW\\002\\017/\\000\\001\\222}\\277\\012xRI'\\217\\306\\234x'\\203I\\301\\004\\367=(\\001\\010\\302\\360qHy\\\\\\001\\237\\353O#\\2363\\217Z0\\010\\351\\323\\255\\000F[\\2660=i7\\035\\330#\\2029\\365\\247\\262\\256\\334\\037\\255&\\016\\354~t\\300\\027\\034c\\223\\336\\206M\\307\\034u\\240\\266\\006\\007\\\\SyV!H\\305\\000\\036c\\200\\000\\374\\361\\323\\377\\000\\257C!\\012[\\222\\336\\203\\2659\\272\\234\\201\\2029\\244r\\344aI\\305\\000D\\027\\017\\203\\323\\351\\376}iv\\022\\271\\000\\236;u\\247\\347#\\033\\261\\326\\247]\\3040\\007\\234qE\\300\\257\\345\\237\\272xf\\247F\\205\\244\\007\\257b\\304\\366\\343\\247\\345S\\035\\210\\252\\254\\271lv\\367\\353O\\021\\224\\\\\\200\\002\\2578\\315+\\200\\366q\\024M&\\336\\203\\257\\343P\\263\\270*B\\235\\275\\300\\035\\271\\367\\244y\\377\\000{\\345\\222vw\\307\\255O\\032|\\303\\034\\234\\372\\365\\245\\260\\010\\0232.\\345\\344s\\365\\025cf>\\352.1\\203\\350j\\035\\333X:\\020K\\014\\355#\\222{\\325\\245E`3\\221\\2360sR\\300\\257 .0N\\334\\364\\365\\244\\004\\011q\\234\\234u\\353\\237z\\260T\\354\\012:\\347\\214{\\373}=i\\253\\036\\334\\347\\036\\243\\353E\\300M\\200\\021\\2343g\\214\\372f\\202\\027\\314\\312\\201\\203\\200r=)\\314\\024\\025=\\217_\\\\R\\031\\025\\270#\\217\\247\\347H\\001\\012\\262\\365'\\036\\334\\177\\236\\2254y\\330\\0118\\030\\317\\036\\365\\036\\325\\316\\320@\\343<\\372\\323^p\\012\\204$s\\200F3@\\016\\332_w\\0319\\344\\236I\\246:\\222A\\310\\344\\377\\000\\017j{\\037\\227j\\200}s\\370S$rG\\031\\366\\024\\001\\003\\235\\273\\210\\343\\266G\\341Q+dm\\003\\036\\224\\347\\335\\367[\\214\\216;\\346\\232X\\026S\\225\\300\\343\\216\\325v\\030\\364\\377\\000Z\\255\\2163\\214z\\320_,\\247\\003\\237N\\324\\000r\\270 \\017\\245*\\214\\202\\331$\\214pO\\255\\000#tf\\306\\0279\\246\\016I\\3162\\017zt\\231g\\332FF{t\\246\\220\\240\\340ps\\214\\347\\332\\201\\006\\320O'\\212\\345\\374d\\240\\233F\\357\\363/\\362\\256\\241~\\357\\\\\\363\\322\\271\\257\\030!\\362\\355\\033\\370A#>\\365\\323\\203\\37620\\304\\377\\000\\011\\234\\312\\203\\212\\232L\\371c\\326\\240SVO\\372\\257\\302\\275\\246yh|\\010\\032hT\\217\\342\\003\\365\\257PrT\\2258\\345\\211\\343\\217Z\\363+\\177\\370\\370\\207\\375\\365\\376u\\351R|\\304\\000\\006H\\343-\\214W\\231\\230}\\237\\231\\337\\204\\352y\\267\\210Qb\\361\\005\\342\\2466\\371\\244\\3753T#\\341\\305\\\\\\327\\031d\\326.6\\223\\200q\\317\\260\\305QRA\\353^\\225?\\201z\\034U>6X\\232c\\267\\003\\212\\352<?3>\\223\\345\\347\\210\\344!W=\\007\\007\\371\\346\\271\\025V\\226@:\\327G\\341\\311\\002\\315sns\\226P\\301\\2751\\377\\000\\355~\\225\\317\\213\\215\\351?#l4\\255P\\336\\224\\356C\\203\\203\\353\\217\\322\\252\\272\\234\\234\\267\\315\\236\\306\\254?\\335\\031\\030<\\344b\\252\\267\\246z\\016+\\312\\211\\351\\210FG8\\007\\035\\252\\026\\000\\034\\234\\023\\320\\373\\232\\225\\363\\260\\343\\003\\336\\252\\237\\233#'\\203\\370\\012\\322(L\\211\\333#\\337\\260\\246\\206a\\306x\\317C\\351K!\\000\\374\\304\\373SG$\\234\\217\\306\\26533\\365\\321\\215\\022|\\020~\\356\\177\\357\\241\\\\]v\\272\\360o\\354K\\202I\\307\\312\\177\\361\\341\\\\Uzx/\\341\\277S\\313\\307\\177\\021z\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336xl\\377\\000\\304\\212\\337\\003$n\\343\\327\\3465\\265\\0318\\306\\354\\343\\214\\232\\304\\360\\373\\021\\241Z`\\003\\367\\307\\376<kZ0\\304\\000\\307\\007\\034\\036\\275k\\300\\256\\277y/V{\\364?\\207\\037DZ\\016\\027\\346'\\201\\317\\034\\346\\234\\256\\2620?\\305\\364\\250\\220\\215\\204\\037_\\2552=\\306\\\\\\250\\3439\\351\\332\\271\\354l[p\\016q\\220@\\340\\373TE\\006\\375\\303\\2778\\317\\025!9R\\002\\340\\220)\\254;\\037\\312\\222\\001\\252w?\\\\c\\270\\247\\022\\352Ag9\\311\\355\\357N\\306\\356\\234\\234\\214\\261\\372Se\\300^\\247'\\216h\\031f\\031\\361&\\017Q\\351\\370\\377\\000\\205Y2d\\361\\221\\305g\\306\\243#\\220O9\\317z\\237q>\\307\\030\\250kQ\\017\\335\\236\\244\\036\\377\\000Jpl\\220H w\\003\\275D\\0160H\\366\\006\\236\\215\\270\\034\\016=\\315+\\0016IA\\216\\240s\\357MbH\\300<\\216\\274\\322\\207\\000}\\357\\322\\220r\\007 \\346\\220\\011\\267\\000c\\257ZNA'h\\247\\034\\000F\\177\\372\\324\\3020\\331\\307\\326\\230\\017\\316\\000>\\335\\251\\240\\223\\317\\343A\\311\\317\\035E7;F\\354|\\264\\000\\244\\221\\337?Ni\\000'\\030\\243\\234{\\364\\245\\317\\250\\372\\320\\000\\023\\203\\353\\365\\243\\243t\\036\\336\\275)\\300\\027\\302\\364\\356h`\\011\\340s\\330b\\200#a\\225'i8\\340\\342\\233\\273\\0121\\300\\305H\\331)\\267\\035{c\\2653\\311\\375\\346T\\236\\224\\300_\\230\\016\\024\\034\\365\\247\\250\\314 n#\\236NzR(\\004\\220zg'\\025'\\312[$q\\301\\244\\003a\\215\\324\\234\\221\\222N\\006j\\362m\\034?\\031\\033rG\\351U\\025T\\2000\\013\\251\\343\\035\\352\\332\\220\\361cqb\\244\\036jX\\025\\246\\204)\\004\\217\\227\\031\\317\\275.\\034n%\\302\\216\\304v\\253\\022\\375\\334.\\012\\236\\203\\336\\253\\256\\346\\223,w\\01603E\\306Y\\210\\007o\\233\\3468\\310$\\367\\241b\\332\\373\\224d\\2227du\\035\\350\\302\\250\\001\\230\\227\\354\\005*H\\371\\034\\356\\\\\\014c\\257N\\277\\255H\\211\\312\\027\\313n\\000\\221\\370\\323\\034\\267\\226\\271\\340\\016HS\\203R\\037\\365\\177(-\\202q\\217\\363\\353H\\255\\214\\202\\270\\317j@F\\334\\266}\\007SQ\\362\\024\\034\\341G\\030\\307N\\365),[\\031#\\217L\\367\\246\\010\\233\\267\\013\\337>\\264\\300\\201\\306\\016>m\\330# \\3674\\354\\000\\252\\003pq\\357Hr\\024\\021\\222C`\\235\\2650UL\\221\\200N\\0119\\340\\323\\001\\246BF@8\\036\\275\\215FX\\214\\214\\223\\307#\\337\\322\\235p\\006\\000\\030PqQ\\234\\266Td\\225\\350O\\343B\\001\\236V\\033%\\227\\000v\\357M;x\\300\\302\\340\\036\\005H~\\347\\336\\311'\\031\\035\\252"\\270\\307\\004\\366\\000\\014SC\\0342\\330\\031\\344\\3674\\346Q\\260(\\037JA\\222\\273T\\220\\330'\\247Jv\\3371q\\330\\016\\270\\357@\\210\\374\\314\\201\\335q\\311\\244-\\222\\001\\311\\357\\202iH\\003#9\\347\\036\\302\\242\\313\\007;\\363\\214`S\\002EPX\\023\\270\\020s\\307\\025\\211\\342\\325\\022i(\\374\\026Y\\207?PkaX\\211Bc\\214VO\\212\\276]'\\007\\034\\312\\007\\271\\353\\376\\025\\276\\037\\370\\2612\\255\\3746qK\\367\\205[?\\352\\352\\247\\275[\\037\\352\\253\\334\\221\\344\\242{A\\376\\223\\010\\377\\000m\\177\\235z9!\\201R\\001\\035y\\2578\\263`\\267P\\263t\\016\\244\\376u\\350{\\327\\313\\312\\343\\346\\031\\2573\\037\\274N\\374&\\314\\363=H\\356\\325\\256\\316s\\373\\326\\376uXu\\251n\\263\\366\\373\\217\\372\\350\\337\\316\\243A\\223^\\234~\\024pK\\342e\\350\\302\\354\\004\\014f\\264\\2642\\027Rpy\\314x\\037\\230\\254\\350\\306\\020\\001V\\264\\311D:\\2323\\014\\251\\371MeY^\\015\\033Rv\\232gB\\314T+\\016\\0118\\250\\330\\206\\364\\030\\007<\\364\\241\\331\\210,3\\234\\367\\364\\246\\020\\310\\017\\034\\364\\317\\371\\372W\\220\\221\\352\\007\\315\\321\\217\\004\\036*\\243\\023\\222\\000\\306O~x\\253\\004\\222>|\\037\\177j\\256\\361\\260s\\330d\\2661W\\0211\\2547\\003\\234g\\327\\025\\026\\007\\363\\315<\\214\\023\\200q\\214\\363\\332\\220\\206bB\\214q\\212\\320\\223?[`\\376\\037\\235\\273\\374\\243\\377\\000\\036\\025\\305Wc\\255FSD\\270\\347#\\345\\311\\317\\373B\\270\\352\\364\\360_\\003\\365<\\254o\\361\\025\\373\\005\\024Q]\\207\\030QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\001\\336\\370p7\\366\\025\\251\\035>p\\177\\357\\263[\\000\\204P\\000\\307\\256\\177:\\340\\254|Gyaf\\226\\321G\\001D\\316\\013\\251$\\344\\347\\326\\254\\217\\030_\\214\\177\\243\\332q\\376\\303\\177\\361U\\344\\325\\301\\325\\224\\333]\\317V\\226.\\224`\\223\\354v\\340\\343,\\010\\310\\037\\235)\\221\\214g\\345_\\360\\256\\037\\376\\023\\035G\\376x\\332\\377\\000\\337-\\377\\000\\305R\\177\\302_\\250d\\221\\025\\260'\\321[\\377\\000\\212\\254\\376\\243T\\323\\353\\324\\216\\3503\\006\\303\\017\\257\\370S\\325\\303\\021\\310\\3348\\351\\326\\270\\037\\370K\\365\\014`\\303lG\\272\\267\\377\\000\\025N\\377\\000\\204\\307Q\\306<\\253l\\177\\272\\337\\374U/\\250U\\017\\257R;\\320G'\\257\\275\\017\\222F}3\\214\\327\\004<e\\250\\001\\217"\\327\\003\\375\\226\\377\\000\\342\\251\\337\\360\\232j\\004`\\333\\332\\0361\\312\\267\\377\\000\\025K\\352\\025\\207\\365\\352'y\\234d\\016{\\234P\\006N@<w\\365\\256\\023\\376\\023mK9\\362m\\177\\357\\226\\377\\000\\342\\250>5\\324\\217\\374\\261\\265\\377\\000\\276[\\377\\000\\212\\243\\352\\025\\203\\353\\324N\\354\\271\\307'\\236\\274T\\201\\313\\001\\214q^}\\377\\000\\011\\236\\243\\214y6\\277\\367\\313\\177\\361T\\377\\000\\370M\\265!\\322\\013O\\373\\341\\277\\370\\252?\\263\\353vA\\365\\352G\\240\\006#'4\\375\\334\\001\\323\\275y\\347\\374&\\372\\226I\\362,\\377\\000\\357\\206\\377\\000\\342\\251\\177\\3418\\324\\277\\347\\336\\323\\333\\344n?\\361\\352_\\331\\365\\203\\353\\324\\217Cc\\321\\216I\\353\\315)\\366\\317\\343^w\\377\\000\\011\\316\\247\\214y\\026\\230\\351\\215\\255\\377\\000\\305S\\277\\341<\\3250@\\202\\320g\\375\\206\\377\\000\\342\\251\\177g\\326\\362\\017\\257Q=\\020\\0169\\034\\372\\023Mh\\310S\\223\\317\\322\\274\\367\\376\\023\\315S\\037\\361\\357g\\377\\000|7\\377\\000\\025J|{\\252\\037\\371w\\262\\030\\364F\\377\\000\\342\\250\\376\\317\\257\\344\\037^\\242w\\205O8<\\021\\234z\\323\\201\\000\\214\\236\\235\\217\\362\\256\\003\\376\\023\\275L\\214}\\236\\317\\037\\356\\277\\377\\000\\025L\\377\\000\\204\\343R8\\375\\305\\247\\037\\354\\267\\377\\000\\025G\\366}`\\372\\365\\023\\321\\224\\223\\3174w\\003\\365\\257<\\036;\\324\\306?\\321\\354\\316:e\\033\\377\\000\\212\\245\\377\\000\\204\\367T\\000\\201od21\\367\\033\\377\\000\\212\\243\\373>\\270\\276\\275H\\364\\027$.@-\\3764\\245\\304Q\\345G\\323=k\\317\\177\\341=\\325?\\347\\332\\313\\376\\370\\177\\376*\\230\\3368\\324\\334\\363\\015\\2561\\2006\\266?\\364*?\\263\\353y\\007\\327\\250\\235\\371v2oQ\\306{\\372\\323\\325\\217%\\201\\310\\353\\307\\025\\347k\\343mIzCh9\\317\\010\\337\\374U;\\376\\023\\235S\\004y6\\274\\377\\000\\262\\377\\000\\374U?\\354\\372\\336C\\372\\365\\023\\322\\306\\010\\\\\\014q\\221\\374\\350W\\371\\276\\371\\000s\\317~\\246\\274\\343\\376\\023\\355W96\\366G\\035>F\\343\\377\\000\\036\\244\\377\\000\\204\\367U\\316L6\\204\\373\\243\\177\\361U?\\331\\325\\374\\203\\353\\324\\217O \\222\\012\\236\\371\\371y\\245E\\303g\\205\\031={\\327\\231\\257\\304-YG\\374{Y\\177\\337\\017\\377\\000\\305R\\217\\210\\272\\270\\307\\372=\\227\\037\\364\\315\\277\\370\\252\\237\\354\\352\\376A\\365\\352G\\250\\355\\033\\216NI\\343 \\347\\212l`\\030\\324\\221\\265\\200\\351\\2209\\307J\\363\\023\\361\\033X\\377\\000\\237{.\\230\\373\\217\\377\\000\\305SO\\304=X\\343\\375\\032\\313\\267\\360?\\377\\000\\025G\\366m\\177!}z\\221\\352\\252\\344;\\021\\367G\\344hb\\3318\\307\\\\\\021\\372\\327\\226\\257\\304}aG\\026\\326\\031\\316rc\\177\\376*\\227\\376\\026N\\261\\377\\000>\\266\\037\\367\\355\\377\\000\\370\\252?\\263k\\366A\\365\\332G\\2509$n\\000c8\\367\\246\\222A\\310c\\363W\\230\\177\\302\\307\\325\\373Z\\330\\017\\373f\\377\\000\\374U\\037\\360\\261\\365\\214c\\354\\3268\\377\\000q\\377\\000\\370\\252?\\263k\\371\\007\\327i\\036\\226\\013(;z`\\363P\\371\\241\\360zrFs\\351^l~ \\352\\347?\\270\\263\\347?\\300\\337\\374U0\\370\\373Vo\\371ci\\357\\362\\267?\\370\\365?\\354\\332\\376C\\372\\365#\\321\\343pPrW\\234\\200~\\202\\236X\\357\\310\\306p~e\\352:\\327\\232\\017\\035\\352c?\\350\\366\\177\\367\\303\\177\\361T\\277\\360\\236\\352\\270\\003\\310\\263\\000\\014p\\215\\377\\000\\305S\\376\\316\\255\\344\\037^\\242zC\\277\\335\\343\\004zS\\202\\340\\021\\216;{W\\232\\267\\217uV\\\\\\030,\\372\\365\\330\\337\\374U)\\361\\376\\254s\\373\\233?O\\270\\337\\374U\\037\\331\\325\\374\\203\\353\\324OI\\005rA\\307\\324\\216i\\331\\005\\363\\3108\\353\\237\\274=z\\327\\232\\377\\000\\302\\301\\325\\201\\310\\202\\317\\376\\370o\\376*\\217\\370X\\032\\267\\374\\373\\331g\\327\\313o\\376*\\227\\366u\\177/\\274>\\275D\\364B\\000l\\002r\\017Ji;\\301\\004\\025\\034\\021\\334\\212\\363\\246\\361\\346\\250\\335m\\354\\377\\000\\357\\206\\377\\000\\342\\250\\377\\000\\204\\363T\\300\\036E\\237\\375\\360\\337\\374U?\\354\\352\\342\\372\\365#\\322x\\300#\\215\\247\\007\\234\\326/\\213\\023\\376%\\003\\007;g\\\\\\347\\350\\325\\310\\257\\217uUb\\302\\013>\\177\\330o\\376*\\222O\\024\\337kQ\\033k\\230\\355\\3265>g\\356\\324\\203\\236\\235\\311\\365\\255)`kBjOdML])\\305\\305u \\253q\\363\\030\\252\\225f\\003\\224\\257I\\234H\\225N\\323\\237J\\357 s%\\252\\026\\332\\030\\240<\\375\\005pg\\205\\315B|m\\251\\333\\271\\205a\\264\\332\\207\\003(\\337L\\375\\352\\343\\304a\\347Y.N\\207E\\032\\321\\245~a\\267\\243\\376&\\027=\\377\\000z\\337\\316\\231\\037Z[\\207\\363.$s\\214\\263\\022q\\353I\\037z\\353J\\321\\261\\316\\365\\221u>\\350\\247\\302\\376]\\322?\\367H?\\2551>\\350\\240\\360\\301\\252Z\\271kC\\245`J\\222\\0063\\336\\220\\356|\\216\\240\\364\\315qg\\306\\032\\207#\\311\\265\\307\\246\\306\\377\\000\\032A\\343\\015@\\000\\0046\\274\\177\\260\\337\\374Up\\375F\\261\\331\\365\\332Gf>PT\\220q\\3275\\023\\225'\\360\\374\\353\\217\\377\\000\\204\\266\\377\\000$\\230m\\217\\374\\005\\277\\370\\252i\\361U\\361\\037\\352m\\277\\357\\226\\377\\000\\342\\252\\226\\012\\250\\276\\273H\\352_\\324\\2561\\326\\235\\036NJ\\365\\365'\\265rG\\304\\367\\2540b\\267?Uo\\361\\241|Oz\\234\\010\\340\\306s\\312\\267\\370\\325}N\\255\\205\\365\\312F\\367\\210Wf\\213q\\203\\301\\011\\377\\000\\241\\012\\341\\353V\\363\\304\\027w\\326\\257o,p\\205|d\\2509\\340\\203\\353\\355YU\\333\\205\\245*p\\264\\216\\034UX\\324\\232q\\354\\024QEt\\234\\301E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001W4\\342\\005\\303g\\272\\221\\372\\212\\247V,A7#\\036\\206\\224\\266\\034w6jh[\\034T5"u\\025\\316\\316\\204[?r\\271[\\256.\\244\\377\\000x\\327R\\307\\021g\\332\\271\\213\\337\\370\\373\\177\\255U-\\311\\253\\261\\264\\307'>\\274\\323\\24385\\030\\350>\\203\\371S\\227\\203H\\242\\372\\375\\321J{}i\\251\\367i\\325\\231g#2l\\235\\327\\321\\210\\250\\352\\336\\244\\024j\\023\\005\\030\\031\\037\\312\\252WZ\\325\\034\\217F\\024QE1\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005\\024Q@\\005Y\\260\\377\\000\\217\\265\\372\\032\\255V\\364\\361\\233\\234\\372)\\245-\\207\\035\\315jx<\\212`\\351J:\\326\\007Ap\\363\\011\\372W=\\251&\\331\\303z\\212\\350\\027\\230?\\003X\\372\\242\\376\\345\\033\\2708\\242\\236\\214U\\026\\205\\355\\270\\030\\377\\000d\\037\\322\\220\\016i\\212\\305\\2223\\353\\032\\377\\000*\\221z\\212\\030\\313\\2500\\242\\234)\\027\\356\\212QY\\226s:\\240\\306\\2450\\367\\007\\364\\025N\\264\\365\\270\\366\\335\\253\\343\\357/\\362\\254\\312\\352\\217\\302\\216Y\\253I\\205\\024QTHQE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000QE\\024\\000U\\275<\\376\\375\\277\\335\\376\\265R\\254\\331M\\344LX\\306\\035H\\332A8\\245-\\207\\035\\315u#\\024\\265\\030\\232\\336C\\204|\\037B)\\334\\257\\322\\260:\\013\\260\\266b"\\263u\\025\\315\\233\\023\\330\\212\\273n\\343v=j\\246\\2506\\332\\260<d\\321\\037\\210%\\360\\205\\271\\315\\274G\\375\\232\\231z\\324\\026\\303\\026\\320\\373\\255N:\\323{\\211l]\\210\\3460jJ\\206\\003\\306*j\\314\\320\\305\\327\\227\\230\\033\\375\\341\\374\\253\\032\\272\\015n-\\326j\\377\\000\\334o\\347\\\\\\375tS\\370Nz\\237\\020QE\\025faE\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001E\\024P\\001ND,i\\2654N\\027\\203I\\215\\013\\344\\214z\\323\\302\\036\\200T\\361\\224&\\246\\304k\\311\\307\\326\\263r5P*\\210X\\216F*h\\326h\\327\\207#\\234{RIr\\007\\013\\203U\\236\\344\\366\\346\\205v\\027\\2124#\\236T`X/\\036\\325J\\376\\371\\356\\334\\002\\002\\242\\364\\002\\240k\\211\\033\\276**\\250\\306\\332\\262%;\\253#f\\320\\267\\330\\342-\\310\\347\\037\\235N\\030U+=Cd\\036K\\307\\220\\240\\355a\\375ja{\\003\\0342\\262\\373\\216j\\032w4\\213V/E0S\\317\\351VD\\250{\\326_\\332-\\273H\\337\\367\\317\\377\\000^\\246\\216\\352\\33382\\021\\365Z\\207\\022\\324\\207\\352\\304\\0356N\\234\\343\\371\\212\\346\\253OR\\277YA\\267\\2150\\200\\362\\307\\277\\341Y\\225\\2655dcQ\\246\\364\\012(\\242\\254\\314(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000(\\242\\212\\000P\\354\\275\\015)\\221\\317V4\\332(\\013\\212I=M%\\024P\\001E\\024P\\005\\244P\\2109\\3523N\\010[\\240\\252\\361\\311\\201\\265\\272z\\372T\\3511\\035\\0105\\0153D\\320\\361\\013\\347\\224\\317\\343V\\241\\264\\030\\015\\212\\254.$\\364\\030\\241\\257\\233i\\000\\205\\377\\000w\\257\\347P\\324\\231i\\305n\\032\\214(\\230e<\\216*\\205>YZV\\313\\037\\302\\231ZE4\\254\\314\\244\\323wAE\\024U\\022\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\024QE\\000\\177\\377\\331	19	[Exif] Orientation - Top, left side (Horizontal / normal)<br/>[Exif] X Resolution - 72 dots per inch<br/>[Exif] Y Resolution - 72 dots per inch<br/>[Exif] Resolution Unit - Inch<br/>[Exif] Software - Adobe Photoshop CS3 Windows<br/>[Exif] Date/Time - 2008:09:22 11:29:40<br/>[Exif] Color Space - Undefined<br/>[Exif] Exif Image Width - 1280 pixels<br/>[Exif] Exif Image Height - 1024 pixels<br/>[Exif] Compression - JPEG (old-style)<br/>[Exif] Thumbnail Offset - 302 bytes<br/>[Exif] Thumbnail Length - 3479 bytes<br/>[Exif] Thumbnail Data - [3479 bytes of thumbnail data]<br/>	zz
\.


--
-- TOC entry 2017 (class 0 OID 82351)
-- Dependencies: 1589
-- Data for Name: lekcjakoment; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY lekcjakoment (idlekcjakoment, idlekcja, username, tresc, datadodania) FROM stdin;
\.


--
-- TOC entry 2014 (class 0 OID 49538)
-- Dependencies: 1583
-- Data for Name: lekcjapliki; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY lekcjapliki (idlekcjapliki, datadodania, opis, plik, idlekcja, nazwapliku) FROM stdin;
\.


--
-- TOC entry 2015 (class 0 OID 66047)
-- Dependencies: 1585
-- Data for Name: newsy; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY newsy (idnewsy, tytul, tresc, datadodania) FROM stdin;
6	eeeee	<p>ddddddddddddddddddddddddddddddddddd</p>\r\n<p>dddddddddd</p>\r\n<p>dddddddddddddddddddddddddddddddd</p>	2011-01-13
5	ttttttttttaaaaaaaaaaaaaaaaaaaaa	<p>ffffffffffffrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ddddddddddddddddddddddddddddddddddd dddddddddddddd</p>	2011-01-15
7	sssssssssssssssssss	<p>Wczoraj wieczorem szefowa częstochowskiej PO Halina Rozpondek potwierdziła w&nbsp;oświadczeniu przesłanym PAP, że Ryszard C. był członkiem Platformy od kwietnia 2004 roku. "W styczniu 2006 roku został wykreślony z&nbsp;listy członk&oacute;w partii z&nbsp;powodu braku jakiejkolwiek aktywności. Procedurę wykreślenia rozpoczęto w&nbsp;kwietniu 2005 roku" - napisała Rozpondek.</p>\r\n<p>&nbsp;</p>\r\n<table id="#ad_place_news" class="advertisement news">\r\n<tbody>\r\n<tr>\r\n<td class="skip">&nbsp;</td>\r\n</tr>\r\n<tr>\r\n<td class="ads">\r\n<div id="ad_news">\r\n<object id="ObjectSMP" width="300px" height="250px" data="http://i.interia.pl/20111113_mercedes_300x250.swf?clickTag=http://interia.hit.gemius.pl/hitredir/id=ba1KuA9XM14AEZ70M2mJtZaF375prge5jAwT4S.RuXL.l7/stparam=wjfljjojdm/sarg=00000008485912DD/fastid=1297036692683336155/url=http://gde-default.hit.gemius.pl/hitredir/id=.KEwCvymy1dJ.RJSTo82dMV4TKjNQ5AJw8T85WG6ayD.D7/stparam=lhjegskqwj/sarg=00000003F796FE3D/url=http%3A//www.mercedes-benz.pl/content/poland/mpc/mpc_poland_website/pl/home_mpc/van/home/financial_services/leasing/Wyprzeda__rocznika_2010.html" type="application/x-shockwave-flash">\r\n<param name="name" value="ObjectSMP" />\r\n<param name="src" value="http://i.interia.pl/20111113_mercedes_300x250.swf?clickTag=http://interia.hit.gemius.pl/hitredir/id=ba1KuA9XM14AEZ70M2mJtZaF375prge5jAwT4S.RuXL.l7/stparam=wjfljjojdm/sarg=00000008485912DD/fastid=1297036692683336155/url=http://gde-default.hit.gemius.pl/hitredir/id=.KEwCvymy1dJ.RJSTo82dMV4TKjNQ5AJw8T85WG6ayD.D7/stparam=lhjegskqwj/sarg=00000003F796FE3D/url=http%3A//www.mercedes-benz.pl/content/poland/mpc/mpc_poland_website/pl/home_mpc/van/home/financial_services/leasing/Wyprzeda__rocznika_2010.html" />\r\n<param name="bgcolor" value="#FFFFFF" />\r\n<param name="wmode" value="opaque" />\r\n<param name="quality" value="high" />\r\n</object>\r\n</div>\r\n</td>\r\n</tr>\r\n<tr>\r\n<td class="skipDest"><a id="skipAdnews" name="skipAdnews">&nbsp;</a></td>\r\n</tr>\r\n</tbody>\r\n</table>\r\n<p>Podczas konferencji prasowej w&nbsp;Sejmie rzecznik PiS Adam Hofman i&nbsp;europoseł tej partii Janusz Wojciechowski - kt&oacute;rego Marek Rosiak był asystentem - ocenili, że PO stosuje "strategię węgierską". Przytoczyli cytat z&nbsp;byłego premiera Węgier Ferenca Gyurcsany'ego: "Kłamaliśmy rano, kłamaliśmy wieczorem (...)Kłamaliśmy po to, żeby wygrać wybory".</p>\r\n<p>- Niestety w&nbsp;ostatnich ważnych sprawach, w&nbsp;sprawie mordu w&nbsp;biurze PiS w&nbsp;Łodzi i&nbsp;w sprawie smoleńskiej taktyka robienia zasłony dymnej, sztucznej mgły, m&oacute;wienia p&oacute;łprawd, nieprawdy, zamydlania odpowiedzialności jest stosowana - ocenił Hofman.</p>\r\n<p>Hofman zaprezentował wypowiedź działaczki PO z&nbsp;Czestochowy Izabeli Leszczyny z&nbsp;listopada 2010 r. pytanej czy Ryszard C. był w&nbsp;PO: "Z pewnością przez ostatnie 3 lata nigdy taki pan nie był w&nbsp;moim biurze. Nie ma dowodu, że ten pan kiedykolwiek opłacił składkę, nie mamy go nigdzie w&nbsp;papierach, kt&oacute;re by wskazywały, że u&nbsp;nas funkcjonował".</p>\r\n<p>- Zab&oacute;jca był w&nbsp;przeszłości członkiem PO. Nie twierdzę, że gremia w&nbsp;PO bezpośrednio inspirowały go do tej zbrodni. Ale ukrywanie tego faktu, zaprzeczanie mu, budzi daleko idące podejrzenia, że chodzi przynajmniej o&nbsp;to, żeby odsunąć od siebie moralną wsp&oacute;łodpowiedzialność za tę zbrodnię - powiedział Wojciechowski.</p>\r\n<p>Europoseł uznał, że wydarzenia w&nbsp;Łodzi to nie był atak szaleńca, ale "człowieka kierowanego motywacją polityczną. - Gdzieś tę motywację w&nbsp;sobie wzbudził. Jak się okazuje, członkostwo w&nbsp;PO mogło nie być bez znaczenia - dodał.</p>\r\n<p>Dziś szef klubu Platformy Tomasz Tomczykiewicz wyjaśnił, że Ryszarda C. nie było w&nbsp;rejestrze partii, bo prowadzony jest od trzech lat, a&nbsp;on przestał być członkiem PO w&nbsp;2006 r. - Nie wiedzieliśmy, że Ryszard C. był w&nbsp;PO - dodał.</p>\r\n<p>Do zab&oacute;jstwa Rosiaka doszło 19 października, gdy do ł&oacute;dzkiej siedziby PiS wtargnął 62-letni Ryszard C. i&nbsp;zaatakował znajdujące się tam osoby. Zastrzelił Rosiaka, a&nbsp;p&oacute;źniej strzelił do 39-letniego Pawła Kowalskiego; gdy chybił, zaatakował go paralizatorem i&nbsp;ranił nożem. Świadkowie tragedii twierdzą, że krzyczał, iż chciał zabić Jarosława Kaczyńskiego i&nbsp;"powystrzelać pisowc&oacute;w".</p>\r\n<p>Po zatrzymaniu Ryszard C. usłyszał zarzuty zab&oacute;jstwa i&nbsp;usiłowania zab&oacute;jstwa z&nbsp;użyciem broni palnej. Zdaniem śledczych, motywem ataku była przynależność partyjna obu ofiar. Podejrzany przyznał się do zarzucanych czyn&oacute;w, ale odm&oacute;wił składania wyjaśnień i&nbsp;odpowiedzi na pytania.</p>	2011-01-21
8	pierwszy nius	<p>Praesent a orci in purus dapibus accumsan. Suspendisse rutrum euismod massa, nec elementum velit aliquam ac. Sed euismod hendrerit nunc gravida pellentesque. Curabitur non lorem diam. Vestibulum ut lacus eu nunc volutpat eleifend in volutpat augue. Nullam et sapien at nisi ullamcorper facilisis. Suspendisse consequat ligula ut justo rutrum euismod. Integer consectetur lorem luctus justo porta in hendrerit nibh mattis.</p>	2011-01-22
\.


--
-- TOC entry 2016 (class 0 OID 74151)
-- Dependencies: 1587
-- Data for Name: newsykursy; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY newsykursy (idnewsykursy, tytul, tresc, datadodania, idkursy) FROM stdin;
8	rrrrrrrrrrr	<p>rrrrrrrrrrrrrr</p>	2011-01-15	3
9	ss dddd ffffffffffffffff	<h1 class="documentFirstHeading" style="margin-top: 0px; margin-right: 0px; margin-left: 0px; font-size: 18px;">Aparat "po przejściach" za 104 tysiące dolar&oacute;w</h1>\r\n<h4 style="margin-top: 0px; margin-right: 0px; margin-left: 0px; font-size: 12px; border-bottom-style: none; font-weight: bold;">Leica MP-36 z lat pięćdziesiątych ubiegłego wieku, została sprzedana za 104 tysiące dolar&oacute;w na eBayu. Dlaczego? Ponieważ przeżyła wojny w Czechosłowacji, Wietnamie, Biafrze i Pakistanie.</h4>\r\n<div class="bbtext">\r\n<div class="visualPadding">\r\n<div id="galleryNews" style="float: left; margin-right: 20px; width: 320px; height: 276px; margin-bottom: 10px; position: relative; background-color: #ffffff; border: 1px solid #f0f0f0;">\r\n<div id="image" style="background-color: #ffffff;">\r\n<div>\r\n<div class="foto" style="width: 320px; height: 240px; text-align: center; vertical-align: middle; display: table-cell;"><a style="color: #003a92; background-color: transparent;" title="Leica MP-36, kt&oacute;ra niejedno widziała" href="http://www.chip.pl/images/sprzet/500x_leicaleifcam.jpg/image_preview/500x_leicaleifcam.jpg"><img style="border-style: none; vertical-align: middle;" title="Leica MP-36, kt&oacute;ra niejedno widziała" src="http://www.chip.pl/images/sprzet/500x_leicaleifcam.jpg/image_mini/500x_leicaleifcam.jpg" alt="Leica MP-36, kt&oacute;ra niejedno widziała" /></a></div>\r\n<div class="tekst" style="font-size: 10px; padding: 6px 10px 10px; height: 15px; overflow: hidden; background-color: #f0f0f0; border-bottom: 5px solid #f0f0f0; width: 300px; text-align: left; margin: 0px auto;">Leica MP-36, kt&oacute;ra niejedno widziała</div>\r\n</div>\r\n</div>\r\n</div>\r\n<div class="bbtext">\r\n<div class="plain">\r\n<p style="margin-top: 0px; margin-right: 0px; margin-left: 0px;">Jeśli<span class="Apple-converted-space">&nbsp;</span><span class="highlightedGlossaryTerm" style="border-bottom: 1px dotted #cccccc; background-color: #eef3f5; cursor: help; z-index: 2;" onmouseover="javascript:show_glossary_definition_popup(this, 0)">aparat</span><span class="Apple-converted-space">&nbsp;</span>kosztuje ponad 100 tysięcy dolar&oacute;w, to jego wartość, niekoniecznie ze względu na nowoczesne technologie i sprzęt, musi być ogromna. Tak właśnie było w przypadku analogowego aparatu Leica MP-36, kt&oacute;ry został sprzedany na aukcji eBay za 104 tysiące dolar&oacute;w. Okazuje się, że<span class="Apple-converted-space">&nbsp;</span><span class="highlightedGlossaryTerm" style="border-bottom: 1px dotted #cccccc; background-color: #eef3f5; cursor: help; z-index: 2;" onmouseover="javascript:show_glossary_definition_popup(this, 0)">aparat</span><span class="Apple-converted-space">&nbsp;</span>należał do sławnego szwedzkiego fotoreportera, Leifa Engberga, kt&oacute;ry relacjonował przy jego pomocy między innymi wojny w Czechosłowacji, Wietnamie, Biafrze i Pakistanie, a także Igrzyska Olimpijskie.</p>\r\n<p style="margin-top: 0px; margin-right: 0px; margin-left: 0px;">Kolekcjonerskiego smaczku dodaje fakt, że w latach 1956-57 wyprodukowano tylko 138 takich<span class="Apple-converted-space">&nbsp;</span><span class="highlightedGlossaryTerm" style="border-bottom: 1px dotted #cccccc; background-color: #eef3f5; cursor: help; z-index: 2;" onmouseover="javascript:show_glossary_definition_popup(this, 0)">aparat</span>&oacute;w w kolorze czarnym.</p>\r\n<div class="insideComm" style="text-align: center; clear: both;"><span class="top" style="background-image: url(http://www.chip.pl/d/common/li-bg-smooth.gif); height: 12px; display: block; margin-bottom: 4px;">&nbsp;</span></div>\r\n</div>\r\n</div>\r\n</div>\r\n</div>\r\n<p><span><br /><br />Zobacz więcej:<span class="Apple-converted-space">&nbsp;</span><a style="color: #003399; background-color: transparent;" href="http://www.chip.pl/news/sprzet/kamery-i-aparaty/2011/01/aparat-po-przejsciach-za-104-tysiace-dolarow#ixzz1BCPshdJG">http://www.chip.pl/news/sprzet/kamery-i-aparaty/2011/01/aparat-po-przejsciach-za-104-tysiace-dolarow#ixzz1BCPshdJG</a></span></p>	2011-01-16	5
7	4444444rrrrrrrrrrr	<p>444444444444444rrrrrrrrrrrrrrrrrrrrrrrr</p>\r\n<p>&nbsp;</p>\r\n<ol>\r\n<p><a class="style4" href="http://e-szkolafotografii.pl/index.php?str_id=133">KONTO GRATIS</a>&nbsp;- czyli jak zdobyć&nbsp;<strong>prawo do BEZPŁATNEGO KURSU?</strong></p>\r\n<p>O płatnościach w&nbsp;&nbsp;<a href="http://e-szkolafotografii.pl/index.php?str_id=140"><strong>opłatach</strong></a>, o&nbsp;programach promocyjnych w&nbsp; Regulaminach Płatności i&nbsp;Rezerwacji - linki do regulamin&oacute;w ze strony&nbsp;<a href="http://e-szkolafotografii.pl/index.php?str_id=130"><strong>zasady rezerwacji</strong></a>.</p>\r\n</ol>\r\n<p><span style="font-size: medium; color: #990066; background-color: #ffffcc;"><br /></span></p>\r\n<p><img id="j_idt46:0:j_idt48" src="http://www.akwafoto.pl/jkursyjs/rfRes/org.richfaces.resource.MediaOutputResource.xhtml?do=eAGNkrFrFEEUxl-WpFCjxFMEi-ARJaDoLBiEwBmQU9GDSwKuhiRW73afm73M7kxmZu82FwxaqKCNEO0CClrGyv9CiEXAUhAtxEIFEQQrZ-9yXggWTv3N937f997GVxhIFRy6Wa1jAxnHJGTTtTr5pvT47eyzIX2SOwCZBIB-reBgT1UWghMmm0V15936728O9M3DQAN5SlYtrXY012bsFvqkmS9iKRJKDPMMGroqeEDKwwapuTevJ9bWNycdcKqwx-eo9RTGZKDQRnJzJNczKkrCUhX2avsnaHsYONJRRML1SEXIoxbWOJUymY%21fgVpJDIWkCh-fv%21x198G4Ra10URUM9SJNpXGN1P2Np8P7nnx41M19QC%21BKvTnnmdsDKbTZDtUHo2T0Yw4u47hJJkFEVzOpCKtI5HYzvLXN2gLVHC0U4eV7tZh4eeJ8qclkw-0q%21ir6zm9uPfQ-zG%21db6NZDmOdTn-4VaJJX9%21bXV8cG7tez45h9%21fPAWjx1eay4scg0qMId2QXGBQtgtkEqPElLG1fClt0W2A3eew3f2rrZnPX4ZXrnRrcQwcblcXCTadGpkaKySMzc5GO6ckZdachRm3M180fXQ5Lfp1ZNmCiXnxwtjY6XNni779buiisNtKzMTI%21%21GOZH8AJpT3Tg__&amp;time=1295188917636" alt="" /></p>	2011-01-16	5
\.


--
-- TOC entry 2019 (class 0 OID 82412)
-- Dependencies: 1593
-- Data for Name: poziomyzaawansowania; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY poziomyzaawansowania (idpoziomyzaawansowania, nazwa, opis) FROM stdin;
85	sssssss333333333	
93	ssssss	
90	aaaaa	sssssssssssssss
87	eeeeeeee111adas	eeeeeee
92	sssdfsdfs11111	eeeeeeee
\.


--
-- TOC entry 2011 (class 0 OID 24910)
-- Dependencies: 1574
-- Data for Name: statyczne; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY statyczne (id_statyczne, tytul, opis, tresc, lp, rola) FROM stdin;
161	1	1	<p>1</p>\r\n<p><strong>Dodatki do karm i rola składnik&oacute;w balastowych w diecie ryb.</strong></p>\r\n<p>&nbsp;</p>\r\n<p>Coraz większa gama produkt&oacute;w żywnościowych dla zwierząt zawiera w sobie konserwanty, dodatki smakowe i zapachowe oraz ulepszacze. Niekt&oacute;re z nich takie jak: aromaty, słodzik, dodatki słodzące, barwniki zmieniają właściwości karm w niewielkim stopniu ale znacznie poprawiają ich smakowitość. Inne dodatki do żywności powodują przedłużenie przydatności do spożycia. Jeszcze inne pełnią r&oacute;żnorakie funkcje regulacyjne i wpływają na wiele</p>	3	\N
158	111111111	1111111111	<p>111111111111</p>	2	\N
159	aa	aa	<p>aa</p>	1	\N
162	pomoc dla wykładowców	jak przygotować lekcję	<p>dddddddddddddd</p>	4	wykladowca
164	dla kursantów	jak dodawać zdjęcia	<p>wwwwwwwwwwww</p>	5	kursant
\.


--
-- TOC entry 2020 (class 0 OID 82454)
-- Dependencies: 1595
-- Data for Name: typykursu; Type: TABLE DATA; Schema: public; Owner: slow
--

COPY typykursu (idtypykursu, nazwa, opis) FROM stdin;
37	aaaaaassssssssss	sssssssssssssssssss
5	konsultacje	aaaaaaaa
1	kurs	aaaaaaaaaaaaaaaaaaaa
\.


--
-- TOC entry 1926 (class 2606 OID 24878)
-- Dependencies: 1565 1565
-- Name: _roles_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _roles
    ADD CONSTRAINT _roles_pkey PRIMARY KEY (rola);


--
-- TOC entry 1928 (class 2606 OID 24880)
-- Dependencies: 1566 1566
-- Name: _user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _users__roles
    ADD CONSTRAINT _user_roles_pkey PRIMARY KEY (id_user_roles);


--
-- TOC entry 1930 (class 2606 OID 24882)
-- Dependencies: 1568 1568
-- Name: _users_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _users
    ADD CONSTRAINT _users_pkey PRIMARY KEY (username);


--
-- TOC entry 1984 (class 2606 OID 107140)
-- Dependencies: 1605 1605
-- Name: absolwforposty_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY absolwforposty
    ADD CONSTRAINT absolwforposty_pkey PRIMARY KEY (idabsolwforposty);


--
-- TOC entry 1982 (class 2606 OID 107124)
-- Dependencies: 1603 1603
-- Name: absolwforwatki_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY absolwforwatki
    ADD CONSTRAINT absolwforwatki_pkey PRIMARY KEY (idabsolwforwatki);


--
-- TOC entry 1979 (class 2606 OID 90735)
-- Dependencies: 1601 1601
-- Name: boksy_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY boksy
    ADD CONSTRAINT boksy_pkey PRIMARY KEY (idboksy);


--
-- TOC entry 1977 (class 2606 OID 90724)
-- Dependencies: 1599 1599
-- Name: boksycfg_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY boksycfg
    ADD CONSTRAINT boksycfg_pkey PRIMARY KEY (idboksycfg);


--
-- TOC entry 1975 (class 2606 OID 82511)
-- Dependencies: 1597 1597
-- Name: fotykursantkoment_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY fotykursantkoment
    ADD CONSTRAINT fotykursantkoment_pkey PRIMARY KEY (idfotykursantkoment);


--
-- TOC entry 1934 (class 2606 OID 24884)
-- Dependencies: 1569 1569
-- Name: kursy_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY kursy
    ADD CONSTRAINT kursy_pkey PRIMARY KEY (idkursy);


--
-- TOC entry 1936 (class 2606 OID 24886)
-- Dependencies: 1571 1571
-- Name: kursy_users_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_pkey PRIMARY KEY (idkursyusers);


--
-- TOC entry 1946 (class 2606 OID 49559)
-- Dependencies: 1579 1579 1579
-- Name: lekcja_lp_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcja
    ADD CONSTRAINT lekcja_lp_key UNIQUE (lp, idkursy);


--
-- TOC entry 1948 (class 2606 OID 49519)
-- Dependencies: 1579 1579
-- Name: lekcja_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcja
    ADD CONSTRAINT lekcja_pkey PRIMARY KEY (idlekcja);


--
-- TOC entry 1950 (class 2606 OID 57771)
-- Dependencies: 1581 1581 1581
-- Name: lekcjafoty_lp_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcjafoty
    ADD CONSTRAINT lekcjafoty_lp_key UNIQUE (lp, idlekcja);


--
-- TOC entry 1952 (class 2606 OID 49530)
-- Dependencies: 1581 1581
-- Name: lekcjafoty_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcjafoty
    ADD CONSTRAINT lekcjafoty_pkey PRIMARY KEY (idlekcjafoty);


--
-- TOC entry 1967 (class 2606 OID 82397)
-- Dependencies: 1591 1591
-- Name: lekcjafotykursant_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcjafotykursant
    ADD CONSTRAINT lekcjafotykursant_pkey PRIMARY KEY (idlekcjafotykursant);


--
-- TOC entry 1963 (class 2606 OID 82356)
-- Dependencies: 1589 1589
-- Name: lekcjakoment_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcjakoment
    ADD CONSTRAINT lekcjakoment_pkey PRIMARY KEY (idlekcjakoment);


--
-- TOC entry 1954 (class 2606 OID 49546)
-- Dependencies: 1583 1583
-- Name: lekcjapliki_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY lekcjapliki
    ADD CONSTRAINT lekcjapliki_pkey PRIMARY KEY (idlekcjapliki);


--
-- TOC entry 1956 (class 2606 OID 66056)
-- Dependencies: 1585 1585
-- Name: newsy_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY newsy
    ADD CONSTRAINT newsy_pkey PRIMARY KEY (idnewsy);


--
-- TOC entry 1959 (class 2606 OID 74160)
-- Dependencies: 1587 1587
-- Name: newsykursy_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY newsykursy
    ADD CONSTRAINT newsykursy_pkey PRIMARY KEY (idnewsykursy);


--
-- TOC entry 1969 (class 2606 OID 82417)
-- Dependencies: 1593 1593
-- Name: poziomyzaawansowania_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY poziomyzaawansowania
    ADD CONSTRAINT poziomyzaawansowania_pkey PRIMARY KEY (idpoziomyzaawansowania);


--
-- TOC entry 1939 (class 2606 OID 33126)
-- Dependencies: 1574 1574
-- Name: statyczne_lp_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_lp_key UNIQUE (lp);


--
-- TOC entry 1941 (class 2606 OID 24918)
-- Dependencies: 1574 1574
-- Name: statyczne_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_pkey PRIMARY KEY (id_statyczne);


--
-- TOC entry 1943 (class 2606 OID 24930)
-- Dependencies: 1574 1574
-- Name: statyczne_tytul_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_tytul_key UNIQUE (tytul);


--
-- TOC entry 1971 (class 2606 OID 82459)
-- Dependencies: 1595 1595
-- Name: typykursu_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY typykursu
    ADD CONSTRAINT typykursu_pkey PRIMARY KEY (idtypykursu);


--
-- TOC entry 1944 (class 1259 OID 49557)
-- Dependencies: 1579
-- Name: fki_; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_ ON lekcja USING btree (idkursy);


--
-- TOC entry 1980 (class 1259 OID 90746)
-- Dependencies: 1601
-- Name: fki_boksy_idboksycfg_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_boksy_idboksycfg_fkey ON boksy USING btree (idboksycfg);


--
-- TOC entry 1972 (class 1259 OID 82532)
-- Dependencies: 1597
-- Name: fki_fotykursantkoment_idlekcjafotykursant_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_fotykursantkoment_idlekcjafotykursant_fkey ON fotykursantkoment USING btree (idlekcjafotykursant);


--
-- TOC entry 1973 (class 1259 OID 82517)
-- Dependencies: 1597
-- Name: fki_fotykursantkoment_username_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_fotykursantkoment_username_fkey ON fotykursantkoment USING btree (username);


--
-- TOC entry 1931 (class 1259 OID 82431)
-- Dependencies: 1569
-- Name: fki_kursy_idpoziomyzaawansowania_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_kursy_idpoziomyzaawansowania_fkey ON kursy USING btree (idpoziomyzaawansowania);


--
-- TOC entry 1932 (class 1259 OID 82467)
-- Dependencies: 1569
-- Name: fki_kursy_idtypykursu_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_kursy_idtypykursu_fkey ON kursy USING btree (idtypykursu);


--
-- TOC entry 1964 (class 1259 OID 82409)
-- Dependencies: 1591
-- Name: fki_lekcjafotykursant_idlekcja_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_lekcjafotykursant_idlekcja_fkey ON lekcjafotykursant USING btree (idlekcja);


--
-- TOC entry 1965 (class 1259 OID 82403)
-- Dependencies: 1591
-- Name: fki_lekcjafotykursant_username_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_lekcjafotykursant_username_fkey ON lekcjafotykursant USING btree (username);


--
-- TOC entry 1960 (class 1259 OID 82375)
-- Dependencies: 1589
-- Name: fki_lekcjakoment_idlekcja_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_lekcjakoment_idlekcja_fkey ON lekcjakoment USING btree (idlekcja);


--
-- TOC entry 1961 (class 1259 OID 82376)
-- Dependencies: 1589
-- Name: fki_lekcjakoment_username_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_lekcjakoment_username_fkey ON lekcjakoment USING btree (username);


--
-- TOC entry 1957 (class 1259 OID 74171)
-- Dependencies: 1587
-- Name: fki_newsykursy_idkursy_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_newsykursy_idkursy_fkey ON newsykursy USING btree (idkursy);


--
-- TOC entry 1937 (class 1259 OID 65954)
-- Dependencies: 1574
-- Name: fki_statyczne_rola_fkey; Type: INDEX; Schema: public; Owner: slow; Tablespace: 
--

CREATE INDEX fki_statyczne_rola_fkey ON statyczne USING btree (rola);


--
-- TOC entry 1985 (class 2606 OID 24887)
-- Dependencies: 1566 1925 1565
-- Name: _user_roles_role_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY _users__roles
    ADD CONSTRAINT _user_roles_role_name_fkey FOREIGN KEY (roles_rola) REFERENCES _roles(rola);


--
-- TOC entry 1986 (class 2606 OID 24892)
-- Dependencies: 1568 1566 1929
-- Name: _user_roles_user_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY _users__roles
    ADD CONSTRAINT _user_roles_user_name_fkey FOREIGN KEY (username) REFERENCES _users(username) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2005 (class 2606 OID 107146)
-- Dependencies: 1603 1605 1981
-- Name: absolwforposty_idabsolwforwatki_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY absolwforposty
    ADD CONSTRAINT absolwforposty_idabsolwforwatki_fkey FOREIGN KEY (idabsolwforwatki) REFERENCES absolwforwatki(idabsolwforwatki);


--
-- TOC entry 2004 (class 2606 OID 107141)
-- Dependencies: 1929 1568 1605
-- Name: absolwforposty_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY absolwforposty
    ADD CONSTRAINT absolwforposty_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 2003 (class 2606 OID 107125)
-- Dependencies: 1929 1568 1603
-- Name: absolwforwatki_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY absolwforwatki
    ADD CONSTRAINT absolwforwatki_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 2002 (class 2606 OID 90741)
-- Dependencies: 1599 1601 1976
-- Name: boksy_idboksycfg_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY boksy
    ADD CONSTRAINT boksy_idboksycfg_fkey FOREIGN KEY (idboksycfg) REFERENCES boksycfg(idboksycfg);


--
-- TOC entry 2001 (class 2606 OID 82527)
-- Dependencies: 1966 1597 1591
-- Name: fotykursantkoment_idlekcjafotykursant_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY fotykursantkoment
    ADD CONSTRAINT fotykursantkoment_idlekcjafotykursant_fkey FOREIGN KEY (idlekcjafotykursant) REFERENCES lekcjafotykursant(idlekcjafotykursant);


--
-- TOC entry 2000 (class 2606 OID 82512)
-- Dependencies: 1568 1597 1929
-- Name: fotykursantkoment_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY fotykursantkoment
    ADD CONSTRAINT fotykursantkoment_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 1987 (class 2606 OID 82426)
-- Dependencies: 1569 1593 1968
-- Name: kursy_idpoziomyzaawansowania_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy
    ADD CONSTRAINT kursy_idpoziomyzaawansowania_fkey FOREIGN KEY (idpoziomyzaawansowania) REFERENCES poziomyzaawansowania(idpoziomyzaawansowania);


--
-- TOC entry 1988 (class 2606 OID 82462)
-- Dependencies: 1970 1595 1569
-- Name: kursy_idtypykursu_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy
    ADD CONSTRAINT kursy_idtypykursu_fkey FOREIGN KEY (idtypykursu) REFERENCES typykursu(idtypykursu);


--
-- TOC entry 1989 (class 2606 OID 24897)
-- Dependencies: 1571 1569 1933
-- Name: kursy_users_idkursy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_idkursy_fkey FOREIGN KEY (idkursy) REFERENCES kursy(idkursy);


--
-- TOC entry 1990 (class 2606 OID 24902)
-- Dependencies: 1571 1929 1568
-- Name: kursy_users_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 1992 (class 2606 OID 49552)
-- Dependencies: 1569 1579 1933
-- Name: lekcja_idkursy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcja
    ADD CONSTRAINT lekcja_idkursy_fkey FOREIGN KEY (idkursy) REFERENCES kursy(idkursy);


--
-- TOC entry 1993 (class 2606 OID 49531)
-- Dependencies: 1581 1947 1579
-- Name: lekcjafoty_idlekcja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjafoty
    ADD CONSTRAINT lekcjafoty_idlekcja_fkey FOREIGN KEY (idlekcja) REFERENCES lekcja(idlekcja);


--
-- TOC entry 1999 (class 2606 OID 82404)
-- Dependencies: 1579 1947 1591
-- Name: lekcjafotykursant_idlekcja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjafotykursant
    ADD CONSTRAINT lekcjafotykursant_idlekcja_fkey FOREIGN KEY (idlekcja) REFERENCES lekcja(idlekcja);


--
-- TOC entry 1998 (class 2606 OID 82398)
-- Dependencies: 1591 1929 1568
-- Name: lekcjafotykursant_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjafotykursant
    ADD CONSTRAINT lekcjafotykursant_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 1997 (class 2606 OID 82370)
-- Dependencies: 1579 1589 1947
-- Name: lekcjakoment_idlekcja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjakoment
    ADD CONSTRAINT lekcjakoment_idlekcja_fkey FOREIGN KEY (idlekcja) REFERENCES lekcja(idlekcja);


--
-- TOC entry 1996 (class 2606 OID 82365)
-- Dependencies: 1929 1568 1589
-- Name: lekcjakoment_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjakoment
    ADD CONSTRAINT lekcjakoment_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 1994 (class 2606 OID 49547)
-- Dependencies: 1579 1947 1583
-- Name: lekcjapliki_idlekcja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY lekcjapliki
    ADD CONSTRAINT lekcjapliki_idlekcja_fkey FOREIGN KEY (idlekcja) REFERENCES lekcja(idlekcja);


--
-- TOC entry 1995 (class 2606 OID 74166)
-- Dependencies: 1587 1569 1933
-- Name: newsykursy_idkursy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY newsykursy
    ADD CONSTRAINT newsykursy_idkursy_fkey FOREIGN KEY (idkursy) REFERENCES kursy(idkursy);


--
-- TOC entry 1991 (class 2606 OID 65949)
-- Dependencies: 1565 1925 1574
-- Name: statyczne_rola_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_rola_fkey FOREIGN KEY (rola) REFERENCES _roles(rola);


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: slow
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM slow;
GRANT ALL ON SCHEMA public TO slow;


-- Completed on 2011-02-09 05:31:28 CET

--
-- PostgreSQL database dump complete
--

