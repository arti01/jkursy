--
-- PostgreSQL database dump
--

-- Started on 2011-08-30 16:20:52

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1752 (class 1262 OID 76112)
-- Name: taniejauto; Type: DATABASE; Schema: -; Owner: slow
--

CREATE DATABASE taniejauto WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE taniejauto OWNER TO slow;

\connect taniejauto

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 296 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1471 (class 1259 OID 76125)
-- Dependencies: 1740 6
-- Name: auto; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE auto (
    idauto integer NOT NULL,
    typ character varying(100) NOT NULL,
    opis text,
    idmarka integer NOT NULL,
    cena double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.auto OWNER TO slow;

--
-- TOC entry 1470 (class 1259 OID 76123)
-- Dependencies: 6 1471
-- Name: auto_idauto_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE auto_idauto_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.auto_idauto_seq OWNER TO slow;

--
-- TOC entry 1755 (class 0 OID 0)
-- Dependencies: 1470
-- Name: auto_idauto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE auto_idauto_seq OWNED BY auto.idauto;


--
-- TOC entry 1756 (class 0 OID 0)
-- Dependencies: 1470
-- Name: auto_idauto_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('auto_idauto_seq', 6, true);


--
-- TOC entry 1469 (class 1259 OID 76115)
-- Dependencies: 6
-- Name: marka; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE marka (
    idmarka integer NOT NULL,
    marka character varying(100) NOT NULL
);


ALTER TABLE public.marka OWNER TO slow;

--
-- TOC entry 1468 (class 1259 OID 76113)
-- Dependencies: 1469 6
-- Name: marki_idmarki_seq; Type: SEQUENCE; Schema: public; Owner: slow
--

CREATE SEQUENCE marki_idmarki_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.marki_idmarki_seq OWNER TO slow;

--
-- TOC entry 1757 (class 0 OID 0)
-- Dependencies: 1468
-- Name: marki_idmarki_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE marki_idmarki_seq OWNED BY marka.idmarka;


--
-- TOC entry 1758 (class 0 OID 0)
-- Dependencies: 1468
-- Name: marki_idmarki_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('marki_idmarki_seq', 4, true);


--
-- TOC entry 1739 (class 2604 OID 76128)
-- Dependencies: 1471 1470 1471
-- Name: idauto; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE auto ALTER COLUMN idauto SET DEFAULT nextval('auto_idauto_seq'::regclass);


--
-- TOC entry 1738 (class 2604 OID 76118)
-- Dependencies: 1468 1469 1469
-- Name: idmarka; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE marka ALTER COLUMN idmarka SET DEFAULT nextval('marki_idmarki_seq'::regclass);


--
-- TOC entry 1749 (class 0 OID 76125)
-- Dependencies: 1471
-- Data for Name: auto; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (1, '323F', 'jakas', 1, 0);
INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (2, '121', 'asdasd', 1, 234);
INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (3, 'MX-5', '<h6>Ford Fiesta</h6>
<p>5dr Titanium</p>
<p>1,4 16V 96 KM</p>

<p><strong><span style="text-decoration: underline;">Nowy od dealera</span></strong><br />
<strong> </strong><strong><span style="text-decoration: underline;">Rok produkcji: 2011<br />
Gwarancja</span></strong></p>
<h6>Wyposażenie standardowe</h6>
<ul>
<li>• Układ ABS z elektronicznym układem podziału siły hamowania (EBD)</li>
<li>• Poduszki powietrzne- poduszki czołowe kierowcy i pasażera z przodu</li>
<li>• Boczne poduszki powietrzne</li>
<li>• Przednie lampy przeciwmgielne</li>

<li>• Elektroniczna układ stabilizacji toru jazdy (ESP) </li>
<li>• Układ kontroli trakcji (TSC)</li>
<li>• Układ wspomagania awaryjnego hamowania (EBA)</li>
<li>• Klimatyzacja manualna</li>
<li>• Komputer pokładowy</li>
<li>• Kierownica pokryta skórą </li>
<li>• Elektryczny układ wspomagania kierownicy </li>
<li>• Podgrzewane lusterka boczne </li>
<li>• Elektrycznie sterowane szyby przednie </li>

<li>• Centralny zamek sterowany pilotem, 2 składne kluczyki </li>
</ul>
<h6>Wyposażenie dodatkowe</h6>
<ul>
<li>•	Koło zapasowe </li>
<li>•	Lakier metalizowany </li>
<li>•	15” obręcze z lekkich stopów</li>
<li>•	Klimatyzacja automatyczna</li>
<li>•	Podgrzewana przednia szyba</li>
<li>•	Pilot parkowania- przód i tył</li>

<li>•	Radioodtwarzacz płyt CD/MP3, 6 głośników, gniazdo AUX-IN i USB, zestaw głośnomówiący Bluetooth  z funkcją sterowania głosem, zdalne sterowanie odtwarzacza </li>
</ul>
<h6>Oferta standardowa</h6>
<p>54 150 PLN brutto</p>
<h6>Rabat</h6>
<p><strong>8% -  4 050 PLN</strong> brutto</p>
<h6>Nasza oferta</h6>
<p><span style="font-weight: bold; font-size: 1.5em;">50 100 PLN </span>brutto</p>', 1, 2345);
INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (5, 'GTV', 'łądna', 3, 333);
INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (6, '147', 'tez ladna', 3, 345);
INSERT INTO auto (idauto, typ, opis, idmarka, cena) VALUES (4, '924S', 'prosiaczek', 2, 34535);


--
-- TOC entry 1748 (class 0 OID 76115)
-- Dependencies: 1469
-- Data for Name: marka; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO marka (idmarka, marka) VALUES (1, 'mazda');
INSERT INTO marka (idmarka, marka) VALUES (2, 'porsche');
INSERT INTO marka (idmarka, marka) VALUES (3, 'alfa');


--
-- TOC entry 1746 (class 2606 OID 76133)
-- Dependencies: 1471 1471
-- Name: auto_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY auto
    ADD CONSTRAINT auto_pkey PRIMARY KEY (idauto);


--
-- TOC entry 1742 (class 2606 OID 76122)
-- Dependencies: 1469 1469
-- Name: marki_marka_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY marka
    ADD CONSTRAINT marki_marka_key UNIQUE (marka);


--
-- TOC entry 1744 (class 2606 OID 76120)
-- Dependencies: 1469 1469
-- Name: marki_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY marka
    ADD CONSTRAINT marki_pkey PRIMARY KEY (idmarka);


--
-- TOC entry 1747 (class 2606 OID 76134)
-- Dependencies: 1471 1469 1743
-- Name: auto_idmarki_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY auto
    ADD CONSTRAINT auto_idmarki_fkey FOREIGN KEY (idmarka) REFERENCES marka(idmarka);


--
-- TOC entry 1754 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: slow
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM slow;
GRANT ALL ON SCHEMA public TO slow;


-- Completed on 2011-08-30 16:20:54

--
-- PostgreSQL database dump complete
--

