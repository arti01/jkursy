CREATE OR REPLACE VIEW user_roles_view AS 
 SELECT u.adr_email AS username, ur.role_name
   FROM user_roles ur
   JOIN uzytkownik_user_roles uur ON ur.id = uur.role_id
   JOIN uzytkownik u ON u.id = uur.uzytkownik_id;

ALTER TABLE user_roles_view
  OWNER TO eod;

CREATE OR REPLACE VIEW userpass AS 
 SELECT u.adr_email AS username, p.pass AS password
   FROM uzytkownik u
   JOIN passwords p ON p.id = u.haslo_id;

ALTER TABLE userpass
  OWNER TO eod;

INSERT INTO config VALUES (1, 'eskalujPoMinutach', 'eskaluj wniosek po uplywie minut', '3');
INSERT INTO config VALUES (2, 'mail_smtp_host', 'mail_smtp_host', 'smtp.gmail.com');
INSERT INTO config VALUES (3, 'mail_smtp_socketFactory_port', 'mail_smtp_socketFactory_port', '465');
INSERT INTO config VALUES (4, 'mail_smtp_port', 'mail_smtp_port', '465');
INSERT INTO config VALUES (5, 'username', 'username', '');
INSERT INTO config VALUES (6, 'password', 'password', '');
INSERT INTO config VALUES (7, 'domysle_haslo', 'domyslne haslo przy zakladaniu usera', 'a');
INSERT INTO config VALUES (8, 'realm_szyfrowanie', 'md5 lub none', 'md5');


--
-- TOC entry 1929 (class 0 OID 28947)
-- Dependencies: 170 1932
-- Data for Name: wn_rodzaje; Type: TABLE DATA; Schema: public; Owner: eod
--

INSERT INTO wn_rodzaje VALUES (2, 'Okolicznościowy');
INSERT INTO wn_rodzaje VALUES (1, 'Wypoczynkowy');


--
-- TOC entry 1930 (class 0 OID 28952)
-- Dependencies: 171 1932
-- Data for Name: wn_statusy; Type: TABLE DATA; Schema: public; Owner: eod
--

INSERT INTO wn_statusy VALUES (2, '#22DD55', 'Wysłany', 'UW');
INSERT INTO wn_statusy VALUES (3, '#00EE00', 'Zaakceptowany', 'AK');
INSERT INTO wn_statusy VALUES (1, '#0000EE', 'Utworzony', 'UT');
INSERT INTO wn_statusy VALUES (4, '#EE0000', 'Odrzucony', 'OD');
INSERT INTO wn_statusy VALUES (5, '#112233', 'Cofnięty', 'CO');

