--
-- PostgreSQL database dump
--

-- Started on 2010-10-17 08:29:02 CEST

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
-- TOC entry 1508 (class 1259 OID 24836)
-- Dependencies: 6
-- Name: _roles; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _roles (
    rola character varying NOT NULL,
    opis character varying(255) NOT NULL
);


ALTER TABLE public._roles OWNER TO slow;

--
-- TOC entry 1509 (class 1259 OID 24842)
-- Dependencies: 6
-- Name: _user_roles; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _user_roles (
    username character varying(50) NOT NULL,
    role_name character varying(50) NOT NULL,
    id_user_roles integer NOT NULL
);


ALTER TABLE public._user_roles OWNER TO slow;

--
-- TOC entry 1510 (class 1259 OID 24845)
-- Dependencies: 6 1509
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
-- TOC entry 1837 (class 0 OID 0)
-- Dependencies: 1510
-- Name: _user_roles_id_user_roles_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE _user_roles_id_user_roles_seq OWNED BY _user_roles.id_user_roles;


--
-- TOC entry 1838 (class 0 OID 0)
-- Dependencies: 1510
-- Name: _user_roles_id_user_roles_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('_user_roles_id_user_roles_seq', 21, true);


--
-- TOC entry 1511 (class 1259 OID 24847)
-- Dependencies: 1796 1797 1798 1799 1800 1801 1802 1803 6
-- Name: _users; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE _users (
    username character varying(50) NOT NULL,
    userpass character varying(50) NOT NULL,
    email character varying(100) DEFAULT 'email@e.com'::character varying,
    wiadomosc character varying(255) DEFAULT ''::character varying,
    data_zmiany date DEFAULT now() NOT NULL,
    tel1 character varying DEFAULT 0 NOT NULL,
    nip character varying DEFAULT 'nip'::character varying NOT NULL,
    ulica character varying DEFAULT 'ulica'::character varying NOT NULL,
    miasto character varying DEFAULT 'miasto'::character varying NOT NULL,
    imie_nazwisko character varying DEFAULT ''::character varying NOT NULL,
    opis text
);


ALTER TABLE public._users OWNER TO slow;

--
-- TOC entry 1512 (class 1259 OID 24861)
-- Dependencies: 6
-- Name: kursy; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE kursy (
    idkursy integer NOT NULL,
    nazwa character varying(255) NOT NULL,
    opis text,
    dataod date NOT NULL,
    datado date NOT NULL
);


ALTER TABLE public.kursy OWNER TO slow;

--
-- TOC entry 1513 (class 1259 OID 24867)
-- Dependencies: 6 1512
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
-- TOC entry 1839 (class 0 OID 0)
-- Dependencies: 1513
-- Name: kursy_idkursy_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE kursy_idkursy_seq OWNED BY kursy.idkursy;


--
-- TOC entry 1840 (class 0 OID 0)
-- Dependencies: 1513
-- Name: kursy_idkursy_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('kursy_idkursy_seq', 4, true);


--
-- TOC entry 1514 (class 1259 OID 24869)
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
-- TOC entry 1515 (class 1259 OID 24872)
-- Dependencies: 1514 6
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
-- TOC entry 1841 (class 0 OID 0)
-- Dependencies: 1515
-- Name: kursy_users_idkursyusers_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE kursy_users_idkursyusers_seq OWNED BY kursy_users.idkursyusers;


--
-- TOC entry 1842 (class 0 OID 0)
-- Dependencies: 1515
-- Name: kursy_users_idkursyusers_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('kursy_users_idkursyusers_seq', 6, true);


--
-- TOC entry 1517 (class 1259 OID 24910)
-- Dependencies: 6
-- Name: statyczne; Type: TABLE; Schema: public; Owner: slow; Tablespace: 
--

CREATE TABLE statyczne (
    id_statyczne integer NOT NULL,
    tytul character varying(255) NOT NULL,
    opis character varying(255) NOT NULL,
    tresc text NOT NULL,
    lp integer NOT NULL
);


ALTER TABLE public.statyczne OWNER TO slow;

--
-- TOC entry 1516 (class 1259 OID 24908)
-- Dependencies: 6 1517
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
-- TOC entry 1843 (class 0 OID 0)
-- Dependencies: 1516
-- Name: statyczne_id_statyczne_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: slow
--

ALTER SEQUENCE statyczne_id_statyczne_seq OWNED BY statyczne.id_statyczne;


--
-- TOC entry 1844 (class 0 OID 0)
-- Dependencies: 1516
-- Name: statyczne_id_statyczne_seq; Type: SEQUENCE SET; Schema: public; Owner: slow
--

SELECT pg_catalog.setval('statyczne_id_statyczne_seq', 1, true);


--
-- TOC entry 1795 (class 2604 OID 24874)
-- Dependencies: 1510 1509
-- Name: id_user_roles; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE _user_roles ALTER COLUMN id_user_roles SET DEFAULT nextval('_user_roles_id_user_roles_seq'::regclass);


--
-- TOC entry 1804 (class 2604 OID 24875)
-- Dependencies: 1513 1512
-- Name: idkursy; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE kursy ALTER COLUMN idkursy SET DEFAULT nextval('kursy_idkursy_seq'::regclass);


--
-- TOC entry 1805 (class 2604 OID 24876)
-- Dependencies: 1515 1514
-- Name: idkursyusers; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE kursy_users ALTER COLUMN idkursyusers SET DEFAULT nextval('kursy_users_idkursyusers_seq'::regclass);


--
-- TOC entry 1806 (class 2604 OID 24913)
-- Dependencies: 1517 1516 1517
-- Name: id_statyczne; Type: DEFAULT; Schema: public; Owner: slow
--

ALTER TABLE statyczne ALTER COLUMN id_statyczne SET DEFAULT nextval('statyczne_id_statyczne_seq'::regclass);


--
-- TOC entry 1827 (class 0 OID 24836)
-- Dependencies: 1508
-- Data for Name: _roles; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO _roles (rola, opis) VALUES ('admin', 'Administrator');
INSERT INTO _roles (rola, opis) VALUES ('kursant', 'Kursant');
INSERT INTO _roles (rola, opis) VALUES ('wykladowca', 'Wykładowca');


--
-- TOC entry 1828 (class 0 OID 24842)
-- Dependencies: 1509
-- Data for Name: _user_roles; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO _user_roles (username, role_name, id_user_roles) VALUES ('aa', 'admin', 1);
INSERT INTO _user_roles (username, role_name, id_user_roles) VALUES ('kursant', 'kursant', 15);
INSERT INTO _user_roles (username, role_name, id_user_roles) VALUES ('kala', 'admin', 16);
INSERT INTO _user_roles (username, role_name, id_user_roles) VALUES ('wykladowca', 'wykladowca', 21);


--
-- TOC entry 1829 (class 0 OID 24847)
-- Dependencies: 1511
-- Data for Name: _users; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO _users (username, userpass, email, wiadomosc, data_zmiany, tel1, nip, ulica, miasto, imie_nazwisko, opis) VALUES ('aa', 'aa', 'aa@22.pl', 'wiadomosc', '2010-09-17', '0', 'nip', 'ulica', 'miasto', 'admin', NULL);
INSERT INTO _users (username, userpass, email, wiadomosc, data_zmiany, tel1, nip, ulica, miasto, imie_nazwisko, opis) VALUES ('kursant', 'kk', 'email@e.com', '', '2010-09-26', '0', 'nip', 'ulica', 'miasto', 'kursant', NULL);
INSERT INTO _users (username, userpass, email, wiadomosc, data_zmiany, tel1, nip, ulica, miasto, imie_nazwisko, opis) VALUES ('kala', 'kala', 'kk@ll.ll', NULL, '2010-09-26', 'zmiana', 'kk', 'kk', 'kk', 'kala', NULL);
INSERT INTO _users (username, userpass, email, wiadomosc, data_zmiany, tel1, nip, ulica, miasto, imie_nazwisko, opis) VALUES ('wykladowca', 'ww', 'email@e.com', '', '2010-10-10', '0', 'nip', 'ulica', 'miasto', 'wykladowca sdfsdfsdf', 'opisssssss');


--
-- TOC entry 1830 (class 0 OID 24861)
-- Dependencies: 1512
-- Data for Name: kursy; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO kursy (idkursy, nazwa, opis, dataod, datado) VALUES (3, 'asdasd', 'asdasdasd', '2010-01-10', '2010-10-30');
INSERT INTO kursy (idkursy, nazwa, opis, dataod, datado) VALUES (4, 'gggggggggg', 'eeeeeeeeeee', '2010-01-01', '2010-10-01');


--
-- TOC entry 1831 (class 0 OID 24869)
-- Dependencies: 1514
-- Data for Name: kursy_users; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO kursy_users (idkursyusers, idkursy, username) VALUES (6, 3, 'wykladowca');


--
-- TOC entry 1832 (class 0 OID 24910)
-- Dependencies: 1517
-- Data for Name: statyczne; Type: TABLE DATA; Schema: public; Owner: slow
--

INSERT INTO statyczne (id_statyczne, tytul, opis, tresc, lp) VALUES (0, 'aaaaa', 'aaaaaaaa', 'aaaaaaaaaaaa111111111111111', 0);
INSERT INTO statyczne (id_statyczne, tytul, opis, tresc, lp) VALUES (1, 'pomoccccccccc', 'opissssssssssssssss', '<hr size="2" width="100%"><br><hr size="2" width="100%">a to jej treść<br><br><span style="color: rgb(255, 0, 0); font-weight: bold;">Uwaga - ikonka past nie działa, nie wiem dlaczego, trzeba używać ctrl+v :)</span><br><br><span style="background-color: rgb(255, 255, 0);">Zdjęcia dodaje się jako linki do obrazków na sieci</span><br><br><ul><li><b>sdfsdfsfd</b></li><li><strike>sdfsdfsdf</strike></li><li>sdfsdfsd</li></ul><div align="right"><blockquote><i><u>taki po prostu prosty edytorek.</u></i><br><br><div align="justify">sdfsdfsdfsdfsdf sdfsdf tyrtrtyrtyxg dgdgdfg dfgdfgfdg fdgg sdfsdfsdfdsfsdfsdfdsfsdfsdfsdf sdfsdf sfdsdfsdf&nbsp;&nbsp; <br><br>Bardzo bym chciał, żeby Ryszard Kalisz zaangażował się w takie kwestie 
jak ustawa o in vitro, ordynacja wyborcza, konstytucja. Myślę, że Rysiu z
 w<br></div></blockquote></div>', 1);


--
-- TOC entry 1808 (class 2606 OID 24878)
-- Dependencies: 1508 1508
-- Name: _roles_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _roles
    ADD CONSTRAINT _roles_pkey PRIMARY KEY (rola);


--
-- TOC entry 1810 (class 2606 OID 24880)
-- Dependencies: 1509 1509
-- Name: _user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _user_roles
    ADD CONSTRAINT _user_roles_pkey PRIMARY KEY (id_user_roles);


--
-- TOC entry 1812 (class 2606 OID 24882)
-- Dependencies: 1511 1511
-- Name: _users_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY _users
    ADD CONSTRAINT _users_pkey PRIMARY KEY (username);


--
-- TOC entry 1814 (class 2606 OID 24884)
-- Dependencies: 1512 1512
-- Name: kursy_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY kursy
    ADD CONSTRAINT kursy_pkey PRIMARY KEY (idkursy);


--
-- TOC entry 1816 (class 2606 OID 24886)
-- Dependencies: 1514 1514
-- Name: kursy_users_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_pkey PRIMARY KEY (idkursyusers);


--
-- TOC entry 1818 (class 2606 OID 24928)
-- Dependencies: 1517 1517
-- Name: statyczne_lp_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_lp_key UNIQUE (lp);


--
-- TOC entry 1820 (class 2606 OID 24918)
-- Dependencies: 1517 1517
-- Name: statyczne_pkey; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_pkey PRIMARY KEY (id_statyczne);


--
-- TOC entry 1822 (class 2606 OID 24930)
-- Dependencies: 1517 1517
-- Name: statyczne_tytul_key; Type: CONSTRAINT; Schema: public; Owner: slow; Tablespace: 
--

ALTER TABLE ONLY statyczne
    ADD CONSTRAINT statyczne_tytul_key UNIQUE (tytul);


--
-- TOC entry 1823 (class 2606 OID 24887)
-- Dependencies: 1508 1807 1509
-- Name: _user_roles_role_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY _user_roles
    ADD CONSTRAINT _user_roles_role_name_fkey FOREIGN KEY (role_name) REFERENCES _roles(rola);


--
-- TOC entry 1824 (class 2606 OID 24892)
-- Dependencies: 1511 1509 1811
-- Name: _user_roles_user_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY _user_roles
    ADD CONSTRAINT _user_roles_user_name_fkey FOREIGN KEY (username) REFERENCES _users(username) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 1825 (class 2606 OID 24897)
-- Dependencies: 1813 1514 1512
-- Name: kursy_users_idkursy_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_idkursy_fkey FOREIGN KEY (idkursy) REFERENCES kursy(idkursy);


--
-- TOC entry 1826 (class 2606 OID 24902)
-- Dependencies: 1811 1511 1514
-- Name: kursy_users_username_fkey; Type: FK CONSTRAINT; Schema: public; Owner: slow
--

ALTER TABLE ONLY kursy_users
    ADD CONSTRAINT kursy_users_username_fkey FOREIGN KEY (username) REFERENCES _users(username);


--
-- TOC entry 1836 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: slow
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM slow;
GRANT ALL ON SCHEMA public TO slow;


-- Completed on 2010-10-17 08:29:02 CEST

--
-- PostgreSQL database dump complete
--

