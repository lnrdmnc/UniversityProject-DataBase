-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: progetto
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `affida_esterno`
--

DROP TABLE IF EXISTS `affida_esterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affida_esterno` (
  `id_ristorante` varchar(50) NOT NULL,
  `id_esterno` varchar(20) NOT NULL,
  PRIMARY KEY (`id_ristorante`,`id_esterno`),
  KEY `id_esterno` (`id_esterno`),
  CONSTRAINT `affida_esterno_ibfk_1` FOREIGN KEY (`id_ristorante`) REFERENCES `ristorante` (`id_ristorante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `affida_esterno_ibfk_2` FOREIGN KEY (`id_esterno`) REFERENCES `esterno` (`id_esterno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affida_esterno`
--

LOCK TABLES `affida_esterno` WRITE;
/*!40000 ALTER TABLE `affida_esterno` DISABLE KEYS */;
INSERT INTO `affida_esterno` VALUES ('25','10'),('86','10'),('25','100'),('86','119'),('86','178'),('25','23'),('78','23');
/*!40000 ALTER TABLE `affida_esterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `affida_interno`
--

DROP TABLE IF EXISTS `affida_interno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affida_interno` (
  `id_ristorante` varchar(50) NOT NULL,
  `id_interno` varchar(20) NOT NULL,
  PRIMARY KEY (`id_ristorante`,`id_interno`),
  KEY `id_interno` (`id_interno`),
  CONSTRAINT `affida_interno_ibfk_1` FOREIGN KEY (`id_ristorante`) REFERENCES `ristorante` (`id_ristorante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `affida_interno_ibfk_2` FOREIGN KEY (`id_interno`) REFERENCES `interno` (`id_interno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affida_interno`
--

LOCK TABLES `affida_interno` WRITE;
/*!40000 ALTER TABLE `affida_interno` DISABLE KEYS */;
INSERT INTO `affida_interno` VALUES ('25','15'),('86','34'),('78','66');
/*!40000 ALTER TABLE `affida_interno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assegnato`
--

DROP TABLE IF EXISTS `assegnato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assegnato` (
  `id_esterno` varchar(20) NOT NULL,
  `p_iva` varchar(20) NOT NULL,
  PRIMARY KEY (`id_esterno`,`p_iva`),
  KEY `p_iva` (`p_iva`),
  CONSTRAINT `assegnato_ibfk_1` FOREIGN KEY (`id_esterno`) REFERENCES `esterno` (`id_esterno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assegnato_ibfk_2` FOREIGN KEY (`p_iva`) REFERENCES `societadelivery` (`p_iva`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assegnato`
--

LOCK TABLES `assegnato` WRITE;
/*!40000 ALTER TABLE `assegnato` DISABLE KEYS */;
INSERT INTO `assegnato` VALUES ('100','04512170132'),('10','04512170234'),('23','04917150155'),('119','05712350179'),('178','06717450189');
/*!40000 ALTER TABLE `assegnato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` varchar(16) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `data_registrazioni` date NOT NULL,
  `via` varchar(50) NOT NULL,
  `cap` varchar(5) NOT NULL,
  `citta` varchar(20) NOT NULL,
  `numero_civico` int NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('1000','Giuseppe','Verdi','3331597934','2020-07-18','Viale Giuseppe Verdi','84020','Giffoni',10),('1134','Massimo','Massi','3880123456','2019-10-20','Via Olivella ','84131','Salerno',27),('1264','Antonio','Bidone','3331213564','2020-03-12','Via dei fra','85020','Ricigliano',12),('1543','Teodoro','Capozzi','3333957615','2019-07-19','Via piazza della Bubba','84020','San Rufo ',10),('1727','Algero','Capozzoli','3342395966','2020-07-15','Via del muro','84567','Roma ',10);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consegna_dipendente`
--

DROP TABLE IF EXISTS `consegna_dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegna_dipendente` (
  `cf_dipendente` varchar(16) NOT NULL,
  `id_cliente` varchar(50) NOT NULL,
  `nominativo_ricevente` varchar(50) DEFAULT NULL,
  `orario_effettivo` time DEFAULT NULL,
  `orario_presunto` time DEFAULT NULL,
  `data1` date NOT NULL,
  KEY `cf_dipendente` (`cf_dipendente`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `consegna_dipendente_ibfk_1` FOREIGN KEY (`cf_dipendente`) REFERENCES `dipendente` (`cf_dipendente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `consegna_dipendente_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consegna_dipendente`
--

LOCK TABLES `consegna_dipendente` WRITE;
/*!40000 ALTER TABLE `consegna_dipendente` DISABLE KEYS */;
INSERT INTO `consegna_dipendente` VALUES ('AAFTDR14L09G192U','1000','Giuseppe Verdi','22:00:00','20:30:00','2021-01-01'),('IOFTKR22L07G252P','1000','Giuseppe Verdi','21:30:00','20:45:00','2021-01-01'),('AAFTDR14L09G192U','1543','Teodoro Capozzi','00:00:20','00:00:20','2021-01-02'),('IOFTKR22L07G252P','1264','Antonio Bidone','00:00:21','00:00:20','2021-01-02'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03','00:00:20','2021-01-03'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03',NULL,'2021-01-03'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03',NULL,'2021-01-03'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03',NULL,'2021-01-03'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03',NULL,'2021-01-03'),('ABFCSR76PP9G40KT','1134','Massimo Massi','14:04:03',NULL,'2021-01-03');
/*!40000 ALTER TABLE `consegna_dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consegna_rider`
--

DROP TABLE IF EXISTS `consegna_rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consegna_rider` (
  `nominativo_ricevente` varchar(50) DEFAULT NULL,
  `orario_effettivo` time DEFAULT NULL,
  `orario_presunto` time NOT NULL,
  `score` double DEFAULT NULL,
  `data1` date NOT NULL,
  `cf_rider` varchar(16) NOT NULL,
  `id_cliente` varchar(50) NOT NULL,
  KEY `cf_rider` (`cf_rider`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `consegna_rider_ibfk_1` FOREIGN KEY (`cf_rider`) REFERENCES `rider` (`cf_rider`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `consegna_rider_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consegna_rider`
--

LOCK TABLES `consegna_rider` WRITE;
/*!40000 ALTER TABLE `consegna_rider` DISABLE KEYS */;
INSERT INTO `consegna_rider` VALUES ('Teodoro Capozzi','19:04:52','20:00:30',4,'2020-07-22','MSSMSM80A01H703F','1543'),('Antonio Bidone','21:00:00','20:45:00',3.5,'2020-04-16','CPZTDR72L09G192U','1264'),('Massimo Massi','18:08:47','20:00:00',2,'2020-12-16','NTNBDN76A08H703G','1134'),('Teodoro Capozzi','19:04:52','10:00:00',NULL,'2020-07-25','MSSMSM80A01H703F','1543'),('Antonio Bidone','13:30:00','12:00:00',5,'2020-04-15','CPZTDR72L09G192U','1264'),('Massimo Massi','18:08:47','10:00:00',NULL,'2020-12-15','NTNBDN76A08H703G','1134'),('Massimo Massi','18:08:47','21:30:00',2,'2020-12-26','NTNBDN76A08H703G','1134'),('Massimo Massi','18:08:47','20:00:00',1,'2020-12-27','NTNBDN76A08H703G','1134'),('Massimo Massi','18:08:47','22:00:00',1,'2020-12-28','NTNBDN76A08H703G','1134'),('Antonio Bidone','22:30:00','22:00:00',1,'2020-12-28','CPZTDR72L09G192U','1264'),('Teodoro Capozzi','19:04:52','22:10:00',2,'2020-12-28','NTNBDN76A08H703G','1543'),('Giuseppe Verdi','22:30:00','22:00:00',1,'2020-12-31','CPZTDR72L09G192U','1000'),('Giuseppe Verdi','22:25:00','22:10:00',2,'2020-01-01','NTNBDN76A08H703G','1000'),('Giuseppe Verdi','22:25:00','22:10:00',NULL,'2020-01-03','NTNBDN76A08H703G','1000'),('Massimo Massi','18:08:47','18:00:00',5,'2021-01-03','NTNBDN76A08H703G','1134'),('Massimo Massi','18:08:47','18:00:00',NULL,'2021-01-02','NTNBDN76A08H703G','1134'),('Teodoro Capozzi','19:04:52','18:30:00',NULL,'2020-12-28','NTNBDN76A08H703G','1543');
/*!40000 ALTER TABLE `consegna_rider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendente` (
  `cf_dipendente` varchar(16) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `anni_esperienza` int NOT NULL,
  `curriculum` varchar(500) NOT NULL,
  `tipo_contratto` varchar(20) DEFAULT NULL,
  `data_presa_servizio` date NOT NULL,
  `id_interno` varchar(20) NOT NULL,
  `disponibilita` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cf_dipendente`),
  KEY `id_interno` (`id_interno`),
  CONSTRAINT `dipendente_ibfk_1` FOREIGN KEY (`id_interno`) REFERENCES `interno` (`id_interno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES ('1234567890123456','Bruno','Carpentieri','+393311456789',0,'grande','indeterminato','2021-01-01','15',NULL),('AAFTDR14L09G192U','Marco','Annati','3201452784',3,'neodiplomato','indeterminato','2017-02-14','15',0),('ABFCSR76PP9G40KT','Leonardo','Pavoletti','3311456786',5,'disoccupato','indeterminato','2015-04-30','34',0),('CRCMRT87K15H703F','Ciro','Mertens','3651234456',4,'diplomato','indeterminato','2021-01-01','66',0),('IOFTKR22L07G252P','Gianluca','Caprari','3471453478',0,'neodiplomato','fisso','2020-12-09','66',0);
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `disponibilita`
--

DROP TABLE IF EXISTS `disponibilita`;
/*!50001 DROP VIEW IF EXISTS `disponibilita`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `disponibilita` AS SELECT 
 1 AS `max_prenotazioni`,
 1 AS `coda_ordini`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `esterno`
--

DROP TABLE IF EXISTS `esterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `esterno` (
  `id_esterno` varchar(20) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `data_inizio` date NOT NULL,
  `cadenza_settimanale` varchar(20) NOT NULL,
  PRIMARY KEY (`id_esterno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esterno`
--

LOCK TABLES `esterno` WRITE;
/*!40000 ALTER TABLE `esterno` DISABLE KEYS */;
INSERT INTO `esterno` VALUES ('10','servizio di consegna esterno','2012-12-31','lun-sab'),('100','servizio di consegna esterno','2017-04-03','lun-ven'),('119','servizio di consegna esterno','2018-12-11','lun-ven'),('178','servizio di consegna esterno','2019-03-14','sab-dom'),('23','servizio di consegna esterno','2015-12-24','lun-ven');
/*!40000 ALTER TABLE `esterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impiegato`
--

DROP TABLE IF EXISTS `impiegato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `impiegato` (
  `cf_rider` varchar(16) NOT NULL,
  `p_iva` varchar(20) NOT NULL,
  PRIMARY KEY (`cf_rider`,`p_iva`),
  KEY `p_iva` (`p_iva`),
  CONSTRAINT `impiegato_ibfk_1` FOREIGN KEY (`cf_rider`) REFERENCES `rider` (`cf_rider`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `impiegato_ibfk_2` FOREIGN KEY (`p_iva`) REFERENCES `societadelivery` (`p_iva`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impiegato`
--

LOCK TABLES `impiegato` WRITE;
/*!40000 ALTER TABLE `impiegato` DISABLE KEYS */;
INSERT INTO `impiegato` VALUES ('CPZTDR72L09G192U','04512170132'),('MSSMSM80A01H703F','04917150155'),('NTNBDN76A08H703G','06717450189');
/*!40000 ALTER TABLE `impiegato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interno`
--

DROP TABLE IF EXISTS `interno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interno` (
  `id_interno` varchar(20) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `data_inizio` date NOT NULL,
  `cadenza_settimanale` varchar(20) NOT NULL,
  PRIMARY KEY (`id_interno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interno`
--

LOCK TABLES `interno` WRITE;
/*!40000 ALTER TABLE `interno` DISABLE KEYS */;
INSERT INTO `interno` VALUES ('15','servizio di consegna interno','2020-04-22','sab-dom'),('34','servizio di consegna interno','2017-04-03','lun-ven'),('66','servizio di consegna interno','2016-06-19','lun-ven');
/*!40000 ALTER TABLE `interno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `numero_giornaliero` int NOT NULL,
  `data1` date NOT NULL,
  `tipo` varchar(10) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `stato` varchar(10) NOT NULL,
  `id_cliente` varchar(50) NOT NULL,
  `id_ristorante` varchar(50) NOT NULL,
  PRIMARY KEY (`numero_giornaliero`,`data1`,`id_ristorante`),
  KEY `id_ristorante` (`id_ristorante`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`id_ristorante`) REFERENCES `ristorante` (`id_ristorante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (1,'2020-07-22','secondo','ravioli','consegnato','1134','25'),(1,'2021-01-07','primo','pizza','ordinato','1134','86'),(2,'2020-03-15','primo','cotoletta','ordinato','1264','86'),(3,'2020-03-15','primo','pesce','ordinato','1264','86'),(4,'2020-03-15','primo','pesce','ordinato','1264','86'),(5,'2020-04-16','primo','bolognese','ordinato','1264','86'),(9,'2020-03-15','secondo','tagliatelle ','ordinato','1264','86'),(12,'2020-03-15','secondo','carne','ordinato','1264','25'),(34,'2021-01-02','primo','pizza','ordinato','1264','86'),(36,'2021-01-02','primo','pizza','ordinato','1264','86'),(37,'2021-01-02','primo','pizza','ordinato','1264','86'),(38,'2021-01-02','primo','pizza','ordinato','1264','86'),(39,'2021-01-02','primo','pizza','ordinato','1264','86'),(40,'2021-01-02','primo','pizza','ordinato','1264','86'),(41,'2021-01-02','primo','pizza','ordinato','1264','86'),(42,'2021-01-02','primo','pizza','ordinato','1264','86'),(43,'2021-01-02','primo','pizza','ordinato','1264','86'),(44,'2021-01-02','primo','pizza','ordinato','1264','86'),(45,'2021-01-02','primo','pizza','ordinato','1543','25'),(46,'2021-01-02','secondo','patatine','consegnato','1134','78'),(46,'2021-01-07','primo','pasta','ordinato','1134','78'),(46,'2021-01-07','primo','pasta','ordinato','1134','86'),(47,'2021-01-02','primo','fusilli','consegnato','1134','78'),(48,'2021-01-02','primo','pizza','consegnato','1134','86'),(49,'2020-12-26','primo','ravioli','consegnato','1134','86'),(50,'2020-12-27','primo','tonno','consegnato','1134','86'),(51,'2020-12-28','primo','pasta e tonno','consegnato','1134','86'),(52,'2020-12-28','primo','pizza','consegnato','1543','25'),(53,'2020-12-28','primo','pizza','consegnato','1264','78'),(54,'2020-12-29','primo','pizza','consegnato','1264','78'),(55,'2020-12-30','secondo','carne','consegnato','1543','78'),(56,'2020-12-31','primo','pizza','consegnato','1000','86'),(57,'2020-01-01','primo','cotoletta','consegnato','1000','86'),(58,'2020-01-01','primo','pizza','consegnato','1000','86'),(59,'2020-01-01','secondo ','patate','consegnato','1000','25'),(60,'2020-01-02','primo','pasta','consegnato','1000','86'),(61,'2020-01-03','secondo','pesce','consegnato','1000','86'),(62,'2021-01-03','primo','patate','consegnato','1134','86');
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rider`
--

DROP TABLE IF EXISTS `rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rider` (
  `cf_rider` varchar(16) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `data_primo_impiego` date NOT NULL,
  `score_medio` double DEFAULT NULL,
  `quota` double NOT NULL,
  `disponibilita` tinyint(1) DEFAULT NULL,
  `targa` varchar(7) DEFAULT NULL,
  `veicolo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cf_rider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rider`
--

LOCK TABLES `rider` WRITE;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
INSERT INTO `rider` VALUES ('CPZTDR72L09G192U','Pietro','Panno','3405678941','2018-08-12',2.625,12,0,'TX765IJ','auto'),('MSSMSM80A01H703F','Marco','Larso','3204656826','2019-04-13',0,7,0,'FY789PO','moto'),('NTNBDN76A08H703G','Antonio','Cassano','3456571995','2020-11-01',2.142857142857143,5,0,NULL,NULL);
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ristorante`
--

DROP TABLE IF EXISTS `ristorante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ristorante` (
  `id_ristorante` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `max_prenotazioni` int DEFAULT NULL,
  `coda_ordini` int NOT NULL AUTO_INCREMENT,
  `via` varchar(50) NOT NULL,
  `numero_civico` int NOT NULL,
  `cap` varchar(5) NOT NULL,
  `citta` varchar(20) NOT NULL,
  PRIMARY KEY (`id_ristorante`),
  UNIQUE KEY `coda_ordini` (`coda_ordini`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ristorante`
--

LOCK TABLES `ristorante` WRITE;
/*!40000 ALTER TABLE `ristorante` DISABLE KEYS */;
INSERT INTO `ristorante` VALUES ('25','osteria nonna maria','089225365',100,3,'via aragona',4,'84432','napoli'),('78','la tavernetta','089251474',100,1,'via degli scacchi',23,'84235','salerno'),('86','scacciapensier','082825144',100,17,'via degli dei',1,'84126','salerno');
/*!40000 ALTER TABLE `ristorante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `societadelivery`
--

DROP TABLE IF EXISTS `societadelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `societadelivery` (
  `p_iva` varchar(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `nominativo_amm` varchar(50) NOT NULL,
  PRIMARY KEY (`p_iva`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `societadelivery`
--

LOCK TABLES `societadelivery` WRITE;
/*!40000 ALTER TABLE `societadelivery` DISABLE KEYS */;
INSERT INTO `societadelivery` VALUES ('04512170132','just eat','Mario Balotelli'),('04512170234','food delivery','Giovanni Bamonte'),('04917150155','glovo','Pietro Colombo'),('05712350179','toro mariconda','Ciro de Cesare'),('06717450189','alfonsino','Sandro Tonali');
/*!40000 ALTER TABLE `societadelivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'progetto'
--

--
-- Dumping routines for database 'progetto'
--

--
-- Final view structure for view `disponibilita`
--

/*!50001 DROP VIEW IF EXISTS `disponibilita`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `disponibilita` (`max_prenotazioni`,`coda_ordini`) AS select `ristorante`.`max_prenotazioni` AS `max_prenotazioni`,(select count(0) AS `coda_ordini` from (`ordine` join `ristorante` on((`ordine`.`id_ristorante` = `ristorante`.`id_ristorante`))) where ((`ristorante`.`nome` = 'scacciapensier') and (`ordine`.`stato` = 'ordinato'))) AS `Name_exp_2` from `ristorante` where ((`ristorante`.`nome` = 'scacciapensier') and (`ristorante`.`max_prenotazioni` > (select count(0) AS `coda_ordini` from (`ordine` join `ristorante` on((`ordine`.`id_ristorante` = `ristorante`.`id_ristorante`))) where ((`ristorante`.`nome` = 'scacciapensier') and (`ordine`.`stato` = 'ordinato'))))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-07 19:47:34
