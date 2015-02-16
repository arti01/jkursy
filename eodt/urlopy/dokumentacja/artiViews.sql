CREATE TABLE extpass
(
  pass character varying(255),
  login character varying(255) NOT NULL,
  rola character varying(255),
  id bigint NOT NULL,
  ulimit character varying(50),
  CONSTRAINT extpass_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE extpass
  OWNER TO eod;


CREATE OR REPLACE VIEW userpass AS 
         SELECT u.adr_email AS username, p.pass AS password
           FROM uzytkownik u
      JOIN passwords p ON p.id = u.haslo_id
UNION 
         SELECT extpass.login AS username, extpass.pass AS password
           FROM extpass;

ALTER TABLE userpass
  OWNER TO eod;
/*
CREATE OR REPLACE VIEW user_roles_view AS 
         SELECT u.adr_email AS username, ur.role_name
           FROM user_roles ur
      JOIN uzytkownik_user_roles uur ON ur.id = uur.role_id
   JOIN uzytkownik u ON u.id = uur.uzytkownik_id
UNION 
         SELECT extpass.login AS username, extpass.rola AS role_name
           FROM extpass;
*/
CREATE OR REPLACE VIEW user_roles_view AS 
SELECT u.adr_email AS username, ur.role_name
           FROM user_roles ur
      JOIN uzytkownik_user_roles uur ON ur.id = uur.role_id
   JOIN uzytkownik u ON u.id = uur.uzytkownik_id
UNION 
         SELECT extpass.login AS username, extpass.rola AS role_name
           FROM extpass
UNION 
         SELECT ex.login AS username, ur.role_name AS role_name
           FROM extpass ex 
           join uzytkownik u on ex.login=u.ext_id
           JOIN uzytkownik_user_roles uur ON u.id = uur.uzytkownik_id 
	   JOIN user_roles ur ON ur.id = uur.role_id

ALTER TABLE user_roles_view
  OWNER TO eod;


