//DROP TABLE IF EXISTS exploiter;
/*CREATE TABLE exploiter(id serial PRIMARY KEY, 
first_name VARCHAR(128), 
last_name VARCHAR(128), 
category VARCHAR(64) );*/

DROP TABLE IF EXISTS exploiter;
CREATE TABLE exploiter(id serial PRIMARY KEY, 
fname VARCHAR(128), 
lname VARCHAR(128),
birthDate DATE,
phone VARCHAR(128),
email VARCHAR(128),
category VARCHAR(128), 
adresse integer,
FOREIGN KEY(adresse_id) REFERENCES adresse(id));

DROP TABLE IF EXISTS guidedtour;
CREATE TABLE guidedtour(
id serial PRIMARY KEY,
exploiter_id integer,  
date_tour date,  
FOREIGN KEY(exploiter_id) REFERENCES exploiter(id));

CREATE TABLE adresse(
id serial PRIMARY KEY,
num VARCHAR(128),
street VARCHAR(128),
zip VARCHAR(128),
)