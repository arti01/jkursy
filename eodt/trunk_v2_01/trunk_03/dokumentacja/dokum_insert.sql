INSERT INTO user_roles (id, role_name) VALUES (5, 'eoddok_rej');
INSERT INTO user_roles (id, role_name) VALUES (6, 'eoddok_odb');
INSERT INTO user_roles (id, role_name) VALUES (7, 'eoddok_cfg');

INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (5, 1);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (6, 1);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (7, 1);

INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (5, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (6, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (7, 2);

INSERT INTO dc_typ_flow (id, nazwa, opis ) VALUES (1, 'akceptacje', 'dokument podlegający procesowi akceptacji');
INSERT INTO dc_typ_flow (id, nazwa, opis ) VALUES (2, 'informacje', 'dokument do zapoznania się i ewentualnie potwierdzenia');

INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (1, 'nowy', 'dokument po zarejestrowaniu');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (2, 'w trakcie', 'dokument w trakcie akceptacji lub dystrybucji');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (3, 'zamknięty', 'dokument zamknięty');

INSERT INTO DC_AKCEPT_TYP_KROKU (id, nazwa ) VALUES (1, 'wszyscy muszą zaakceptować');
INSERT INTO DC_AKCEPT_TYP_KROKU (id, nazwa ) VALUES (2, 'wystarczy jeden do akceptacji');
