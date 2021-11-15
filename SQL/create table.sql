CREATE SCHEMA PROGETTO;
USE PROGETTO;

CREATE TABLE cliente(
id_cliente VARCHAR(16) NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cognome VARCHAR(50) NOT NULL,
telefono VARCHAR(13) NOT NULL,
data_registrazioni DATE NOT NULL,
via VARCHAR(50) NOT NULL, /* OPPURE VIA */
cap VARCHAR(5) NOT NULL,
citta VARCHAR(20) NOT NULL,
numero_civico int NOT NULL 
);

CREATE TABLE ristorante(
id_ristorante VARCHAR(50) NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
telefono VARCHAR(13) NOT NULL,
max_prenotazioni INT,
coda_ordini INT ,
via VARCHAR(50) NOT NULL,
numero_civico int NOT NULL,
cap VARCHAR(5) NOT NULL,
citta VARCHAR(20) NOT NULL
);

CREATE TABLE rider(
cf_rider VARCHAR(16) NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cognome VARCHAR(50) NOT NULL,
telefono VARCHAR(13) NOT NULL,
data_primo_impiego DATE NOT NULL,  
score_medio DOUBLE ,
quota DOUBLE NOT NULL,
disponibilita BOOL,
targa VARCHAR(7),
veicolo VARCHAR(50) 
);

CREATE TABLE societadelivery(
p_iva VARCHAR(20) NOT NULL PRIMARY KEY, /* vedere range di una partita iva*/
nome VARCHAR(50) NOT NULL,
nominativo_amm VARCHAR(50) NOT NULL
);

CREATE TABLE interno(
id_interno VARCHAR(20) NOT NULL PRIMARY KEY,
descrizione VARCHAR(100),
data_inizio Date NOT NULL,
cadenza_settimanale VARCHAR(20) NOT NULL
);

CREATE TABLE esterno(
id_esterno VARCHAR(20) NOT NULL PRIMARY KEY,
descrizione VARCHAR(100),
data_inizio Date NOT NULL,
cadenza_settimanale VARCHAR(20) NOT NULL
);

CREATE TABLE ordine(
numero_giornaliero INT NOT NULL,
data1 DATE NOT NULL,
tipo VARCHAR(10) NOT NULL,
descrizione VARCHAR(100),
stato VARCHAR(10) NOT NULL,
id_cliente VARCHAR(50)NOT NULL auto_increment,
id_ristorante VARCHAR(50) NOT NULL auto_increment,
PRIMARY KEY(numero_giornaliero,data1,id_ristorante),

FOREIGN KEY(id_ristorante) REFERENCES ristorante(id_ristorante)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);

 CREATE TABLE consegna_rider(
 nominativo_ricevente VARCHAR(50),
 orario_effettivo TIME NOT NULL,
 orario_presunto TIME NOT NULL,
 score DOUBLE NOT NULL,
 data1 DATE NOT NULL,
 cf_rider VARCHAR(16) NOT NULL,
 id_cliente VARCHAR(50) NOT NULL,
 FOREIGN KEY (cf_rider) REFERENCES rider(cf_rider)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE dipendente(
cf_dipendente VARCHAR(16) PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cognome VARCHAR(50) NOT NULL,
telefono VARCHAR(13) NOT NULL,
anni_esperienza int NOT NULL,
curriculum VARCHAR(500) NOT NULL,
tipo_contratto VARCHAR(20),
data_presa_servizio DATE NOT NULL,
id_interno VARCHAR(20)NOT NULL,
FOREIGN KEY (id_interno) REFERENCES interno(id_interno)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE consegna_dipendente(
 cf_dipendente VARCHAR(16) NOT NULL,  
 id_cliente VARCHAR(50) NOT NULL,
 nominativo_ricevente VARCHAR(50),
 orario_effettivo TIME,
 orario_presunto TIME,
 FOREIGN KEY (cf_dipendente) REFERENCES dipendente(cf_dipendente)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);



CREATE TABLE impiegato( 
cf_rider VARCHAR(16) NOT NULL,
p_iva VARCHAR(20) NOT NULL,
PRIMARY KEY (cf_rider,p_iva),
FOREIGN KEY (cf_rider) REFERENCES rider(cf_rider)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (p_iva) REFERENCES societadelivery(p_iva)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE affida_interno(
id_ristorante VARCHAR(50) NOT NULL,
id_interno VARCHAR(20) NOT NULL,
PRIMARY KEY(id_ristorante,id_interno),
FOREIGN KEY (id_ristorante) REFERENCES ristorante(id_ristorante)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (id_interno) REFERENCES interno(id_interno)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE TABLE affida_esterno(
id_ristorante VARCHAR(50) NOT NULL,
id_esterno VARCHAR(20) NOT NULL,
PRIMARY KEY(id_ristorante,id_esterno),
FOREIGN KEY (id_ristorante) REFERENCES ristorante(id_ristorante)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
FOREIGN KEY (id_esterno) REFERENCES esterno(id_esterno)
	ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE assegnato(
id_esterno VARCHAR(20) NOT NULL,
p_iva VARCHAR(20) NOT NULL,
PRIMARY KEY(id_esterno,p_iva),
FOREIGN KEY (id_esterno) REFERENCES esterno(id_esterno)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
FOREIGN KEY (p_iva) REFERENCES societadelivery(p_iva)
		ON UPDATE CASCADE
        ON DELETE CASCADE
 );