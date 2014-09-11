INSERT INTO user_roles (id, role_name, opis) VALUES (5, 'eoddok_rej','dokumenty - rejestracja');
INSERT INTO user_roles (id, role_name, opis) VALUES (6, 'eoddok_odb','dokumenty - odbiór');
INSERT INTO user_roles (id, role_name, opis) VALUES (7, 'eoddok_cfg','dokumenty - konfiguracja');
INSERT INTO user_roles (id, role_name, opis) VALUES (8, 'eoddok_arc','dokumenty - archiwizacja');
INSERT INTO user_roles (id, role_name, opis) VALUES (9, 'eod_ogl','dodawanie ogloszen');
INSERT INTO user_roles (id, role_name, opis) VALUES (10, 'eod_um_cfg','urzadzenia med-grupy i mastergrupy');
INSERT INTO user_roles (id, role_name, opis) VALUES (11, 'eod_um_sprz','urzadzenia med-sprzet');

INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (5, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (6, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (7, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (8, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (9, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (10, 3);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (11, 3);

INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (5, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (6, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (7, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (8, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (9, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (10, 2);
INSERT INTO uzytkownik_user_roles (role_id, uzytkownik_id ) VALUES (11, 2);

INSERT INTO dc_typ_flow (id, nazwa, opis ) VALUES (1, 'akceptacje', 'dokument podlegający procesowi akceptacji');
INSERT INTO dc_typ_flow (id, nazwa, opis ) VALUES (2, 'informacje', 'dokument do zapoznania się i ewentualnie potwierdzenia');

INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (1, 'nowy', 'dokument po zarejestrowaniu');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (2, 'w trakcie', 'dokument w trakcie akceptacji lub dystrybucji');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (3, 'zamknięty', 'dokument zamknięty');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (4, 'anulowany', 'dokument anulowany');
INSERT INTO dc_dokument_status (id, nazwa, opis ) VALUES (5, 'odrzucony', 'dokument odrzucony');
INSERT INTO dc_dokument_status (id, nazwa, opis,  kolor) VALUES (6, 'w archiwum', 'dokument w archiwum', 'brown');

INSERT INTO DC_AKCEPT_TYP_KROKU (id, nazwa ) VALUES (1, 'wszyscy muszą zaakceptować');
INSERT INTO DC_AKCEPT_TYP_KROKU (id, nazwa ) VALUES (2, 'wystarczy jeden do akceptacji');

INSERT INTO DC_AKCEPT_STATUS (id, nazwa ) VALUES (1, 'początkowy');
INSERT INTO DC_AKCEPT_STATUS (id, nazwa ) VALUES (2, 'brak akceptu');
INSERT INTO DC_AKCEPT_STATUS (id, nazwa ) VALUES (3, 'częściowa akceptacja');
INSERT INTO DC_AKCEPT_STATUS (id, nazwa ) VALUES (4, 'zaakceptowany');
INSERT INTO DC_AKCEPT_STATUS (id, nazwa ) VALUES (5, 'odrzucony');

INSERT INTO dc_teczka (id, nazwa, opis ) VALUES (1, 'brak teczki', 'brak teczki');
INSERT INTO dc_zrodlo (id, nazwa, opis ) VALUES (1, 'brak źródła', 'brak źródła');
INSERT INTO dc_kontrahenci (id, nazwa, nip_regon, osoba_kontak, www, email, adres, info_dod) VALUES (1, 'brak', null, null, null, null, null, 'brak kontrahenta');

INSERT INTO dc_rodzaj_grupa (id, nazwa, opis) VALUES (1, 'wychodzące', 'dokumenty wychodzące');
INSERT INTO dc_rodzaj_grupa (id, nazwa, opis) VALUES (2, 'przychodzące', 'dokumenty przychodzące');
INSERT INTO dc_rodzaj_grupa (id, nazwa, opis) VALUES (3, 'wewnętrzne', 'dokumenty wewnętrzne');

INSERT INTO config (id, nazwa, opis, wartosc) VALUES (12, 'dirImportSkan', 'katalog importu dokumentow', '/home/arti01/tmp/1');
INSERT INTO config (id, nazwa, opis, wartosc) VALUES (13, 'dirImportSkanBak', 'katalog importu dokumentow', '/home/arti01/tmp/1/bak');
INSERT INTO config (id, nazwa, opis, wartosc) VALUES (14, 'kluczLicencji', 'kluczLicencji', 'kluczLicencji');

