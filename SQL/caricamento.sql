INSERT INTO cliente(id_cliente, nome, cognome, telefono, data_registrazioni, via, cap, citta, numero_civico) VALUES
("1543", "Teodoro", "Capozzi", "3333957615", "2019/07/19", "Via piazza della Bubba", "84020", "San Rufo ", "10"),
("1134", "Massimo", "Massi", "3880123456", "2019/10/20", "Via Olivella ", "84131", "Salerno", "27"),
("1264", "Antonio", "Bidone", "3331213564", "2020/03/12", "Via dei fra", "85020", "Ricigliano", "12");

INSERT INTO rider(cf_rider, nome, cognome, telefono, data_primo_impiego, score_medio, quota, disponibilita, targa, veicolo) VALUES
("CPZTDR72L09G192U", "Pietro", "Panno", "3405678941", "2018/08/12", "4.3", "12", "0", "TX765IJ", "auto"),
("MSSMSM80A01H703F", "Marco", "Larso", "3204656826", "2019/04/13", "3.6", "7", "1", "FY789PO", "moto"),
("NTNBDN76A08H703G", "Antonio", "Cassano", "3456571995", "2020/11/1", "2.6", "5", "0", "NULL", "NULL");

INSERT INTO societadelivery(p_iva, nome, nominativo_amm) VALUES
("04917150155", "glovo", "Pietro Colombo"),
("04512170132", "just eat", "Mario Balotelli"),
("06717450189", "alfonsino", "Sandro Tonali");

INSERT INTO esterno(id_esterno, descrizione, data_inizio, cadenza_settimanale) VALUES
("100","servizio di consegna esterno","2017/04/03", "lun-ven"),
("23", "servizio di consegna esterno","2015/12/24", "lun-ven"),
("178", "servizio di consegna esterno","2019/03/14", "sab-dom");

INSERT INTO interno(id_interno, descrizione, data_inizio, cadenza_settimanale) VALUES
("34", "servizio di consegna interno","2017/04/03", "lun-ven"),
("66", "servizio di consegna interno","2016/06/19", "lun-ven"),
("15", "servizio di consegna interno","2020/04/22", "sab-dom");

INSERT INTO ristorante(id_ristorante, nome, telefono, max_prenotazioni, coda_ordini, via, numero_civico, cap, citta) VALUES
("78", "la tavernetta", "089251474","36", "0", "via degli scacchi", "23", "84235", "salerno"),
("86", "scacciapensier", "082825144","14", "0", "via degli dei", "1", "84126", "salerno"),
("25", "osteria nonna maria", "089225365","40", "0", "via aragona", "4", "84432", "napoli");

INSERT INTO dipendente(cf_dipendente, nome, cognome, telefono, anni_esperienza, curriculum, tipo_contratto, data_presa_servizio, id_interno) VALUES
("AAFTDR14L09G192U", "Marco", "Annati", "3201452784", "3", "neodiplomato", "indeterminato", "2017/02/14", "15"),
("ABFCSR76PP9G40KT", "Leonardo", "Pavoletti", "3311456786", "5", "disoccupato", "indeterminato", "2015/04/30", "34"),
("IOFTKR22L07G252P", "Gianluca", "Caprari", "3471453478", "0", "neodiplomato", "fisso", "2020/12/09", "66");

INSERT INTO ordine(numero_giornaliero, data1, tipo, descrizione, stato, id_cliente, id_ristorante) VALUES
("7", "2020/04/16", "primo", "bolognese", "ordine", "1264", "78"),
("3", "2020/07/22", "secondo", "ravioli", "espletato", "1134", "25"),
("2", "2020/12/16", "contorno", "patatine", "consegnato", "1543", "86");

INSERT INTO affida_interno(id_ristorante, id_interno) VALUES
("25", "15"),
("86", "34"),
("78", "66");

INSERT INTO affida_esterno(id_ristorante, id_esterno) VALUES
("25", "100"),
("86", "178"),
("78", "23");

INSERT INTO consegna_rider(nominativo_ricevente, orario_presunto, orario_effettivo, score, data1, cf_rider, id_cliente) VALUES
("Teodoro Capozzi", "20.30", "20.25", "4", "2020/07/22", "MSSMSM80A01H703F", "1543"),
("Antonio Bidone", "20.45", "21.00", "3.5", "2020/04/16", "CPZTDR72L09G192U", "1264"),
("Massimo Massi", "20.00", "21.00", "2", "2020/12/16", "NTNBDN76A08H703G", "1134");

INSERT INTO consegna_dipendente(nominativo_ricevente, orario_presunto, orario_effettivo, cf_dipendente, id_cliente) VALUES
("Teodoro Capozzi", "20.30", "20.36", "AAFTDR14L09G192U", "1543"),
("Antonio Bidone", "20.45", "21.30", "IOFTKR22L07G252P", "1264"),
("Massimo Massi", "20.00", "20.05", "ABFCSR76PP9G40KT", "1134");

INSERT INTO impiegato(cf_rider, p_iva) VALUES
("MSSMSM80A01H703F","04917150155"),
("NTNBDN76A08H703G", "06717450189"),
("CPZTDR72L09G192U", "04512170132");

INSERT INTO assegnato(id_esterno, p_iva) VALUES
("100", "04512170132"),
("23", "04917150155"),
("178", "06717450189");