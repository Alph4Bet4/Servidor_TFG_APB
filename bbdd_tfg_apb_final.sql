-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: bbdd_tfg_apb
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades` (
  `id_actividad` int NOT NULL AUTO_INCREMENT,
  `tipo_actividad` varchar(45) NOT NULL,
  `descripcion_actividad` varchar(255) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `cantidad_max_personas` int NOT NULL DEFAULT '1',
  `cantidad_actual_personas` int NOT NULL DEFAULT '0',
  `estado` enum('Disponible','Finalizado','Completo','Cancelado','EnCurso') NOT NULL DEFAULT 'Disponible',
  `id_creador_ofertante` int DEFAULT NULL,
  PRIMARY KEY (`id_actividad`),
  KEY `id_creador_ofertante_idx` (`id_creador_ofertante`),
  CONSTRAINT `id_creador_ofertante` FOREIGN KEY (`id_creador_ofertante`) REFERENCES `ofertante` (`id_ofertante`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (1,'Salto de 5m','Enseña a personas a saltar a 5 metros','Calle Puerta Frita','2023-02-25','19:31:00','20:31:00',3,3,'Disponible',1),(2,'Cocina','Una actividad que enseñe a cocinar comida con pasta.','Parque de Miraflores','2024-06-19','15:00:00','15:00:00',10,0,'Disponible',4),(3,'Cocina a lo natural','Enseña a personas a cocinar con cosas encontradas en un campo','Av. Ronda Norte, 33-31','2024-05-02','19:30:00','20:31:00',4,0,'Disponible',NULL),(4,'Canto','Quedada para cantar ','La Giralda','2024-04-20','12:04:00','12:10:00',5,0,'Finalizado',1),(5,'Parkour','Clases de parkour personalizadas','Puerta Jerez','2024-04-20','12:18:00','12:20:00',1,0,'Finalizado',2),(6,'Dibujo','Eseñamos a personas a dibujar como verdaderos profesionales.','C. Ramón Areces','2024-04-23','15:00:00','17:00:00',3,0,'Disponible',2),(7,'Juegos de mesa','Quiero a una persona que me enseñe varios juegos de mesa','Isla Mágica','2024-06-28','17:00:00','20:30:00',5,0,'Disponible',NULL),(52,'Club de la lectura','Vamos a hacer una quedada para leer el libro de las mil historias','Parque del Alamillo','2024-07-18','10:00:00','15:00:00',5,0,'Disponible',4);
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividades_seq`
--

DROP TABLE IF EXISTS `actividades_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades_seq`
--

LOCK TABLES `actividades_seq` WRITE;
/*!40000 ALTER TABLE `actividades_seq` DISABLE KEYS */;
INSERT INTO `actividades_seq` VALUES (151);
/*!40000 ALTER TABLE `actividades_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumidor`
--

DROP TABLE IF EXISTS `consumidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumidor` (
  `id_consumidor` int NOT NULL AUTO_INCREMENT,
  `nombre_consumidor` varchar(45) NOT NULL,
  `primer_apellido_consumidor` varchar(45) DEFAULT NULL,
  `segundo_apellido_consumidor` varchar(45) DEFAULT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `email_consumidor` varchar(100) NOT NULL,
  PRIMARY KEY (`id_consumidor`),
  UNIQUE KEY `email_consumidor_UNIQUE` (`email_consumidor`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumidor`
--

LOCK TABLES `consumidor` WRITE;
/*!40000 ALTER TABLE `consumidor` DISABLE KEYS */;
INSERT INTO `consumidor` VALUES (1,'Alberto','Rodrigez','Gutierrez','1000:398a0bfc249d3a70e35763fe15387673:8b1217e376a8daaace199155dabbb49668b5a49c57f0b84dacabd37ff8b51b23','asdqwe@adsqwe.es'),(6,'Rafael Antonio','Ortega','Romero del Castillo','1000:f2fa9387efd94b1de429ca6da62d2214:00140d02bfeaaa08061ff0e33bbccb52b1981b7c9946730d6feb87687a6cf621','rafaorc@gmail.com'),(8,'Lorena','Cabello','Corriente','1000:90f9f62cf088cf8d674ea39402b3d907:fc5f5cdc9f1eceb10b96c4dc8095dedb7dd405a905e0cd231e77a1efab24b10e','lorecc@gmail.com');
/*!40000 ALTER TABLE `consumidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajechat`
--

DROP TABLE IF EXISTS `mensajechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensajechat` (
  `id_mensaje` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `id_consumidor` varchar(255) DEFAULT NULL,
  `id_ofertante` varchar(255) DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajechat`
--

LOCK TABLES `mensajechat` WRITE;
/*!40000 ALTER TABLE `mensajechat` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensajechat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ofertante`
--

DROP TABLE IF EXISTS `ofertante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ofertante` (
  `id_ofertante` int NOT NULL AUTO_INCREMENT,
  `nombre_ofertante` varchar(45) NOT NULL,
  `primer_apellido_ofertante` varchar(45) DEFAULT NULL,
  `segundo_apellido_ofertante` varchar(45) DEFAULT NULL,
  `contrasenia` varchar(255) DEFAULT NULL,
  `nombre_empresa` varchar(45) DEFAULT NULL,
  `email_ofertante` varchar(100) NOT NULL,
  `is_administrador` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_ofertante`),
  UNIQUE KEY `email_UNIQUE` (`email_ofertante`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ofertante`
--

LOCK TABLES `ofertante` WRITE;
/*!40000 ALTER TABLE `ofertante` DISABLE KEYS */;
INSERT INTO `ofertante` VALUES (1,'Daniel','Ruano','Ruiz','1000:206e9cea6f73304e202c2667489f2444:ad12bc1f3c2312a52dc70d610e7d123c5a8c94b3e6fbf2dc40895bddae301145','null','a@a.es',_binary '\0'),(2,'Adrian','Perez','Blanco','1000:71802675961a9b546a3a169a5081c31d:9fb589e97d276632a43ae9e04637fe57a975ff0be0047f36b9e0f7b3d1a8c3e4','Pruebas AAA.','aperez@gmail.com',_binary '\0'),(3,'Lucia','Ortega','Velazquez','1000:a139fdaf6bdaef8aa581ec25e5d89d10:2b4e6b1d8cf8e28251d216099061c4f302a135440ed735288c8440ba33383d5e','null','LuxiaVO@gmail.com',_binary '\0'),(4,'Susana','Ortega','Vazquez','1000:d458084944f93596e88d5d3011f4bab5:65236e70ea22bcebefc18a109fdaf209da126870effa2eff922a60f2fe683b35','Serraderia S.A.','suovo981@gmail.com',_binary '\0'),(5,'root','','','1000:a4a0549b48faa354a12e2d6b4e0bc9eb:97d4554dc6ce1b8c300b1b45597ae942e131b5827d83dae354770a504c5806e1','null','root@root.es',_binary '');
/*!40000 ALTER TABLE `ofertante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participacion_actividades`
--

DROP TABLE IF EXISTS `participacion_actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participacion_actividades` (
  `id_participacion_actividades` int NOT NULL AUTO_INCREMENT,
  `id_actividad` int NOT NULL,
  `id_consumidor` int NOT NULL,
  PRIMARY KEY (`id_participacion_actividades`),
  KEY `id_consumidor_participacion_idx` (`id_consumidor`),
  KEY `id_actividad_participacion_idx` (`id_actividad`),
  CONSTRAINT `id_actividad_participacion` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`),
  CONSTRAINT `id_consumidor_participacion` FOREIGN KEY (`id_consumidor`) REFERENCES `consumidor` (`id_consumidor`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participacion_actividades`
--

LOCK TABLES `participacion_actividades` WRITE;
/*!40000 ALTER TABLE `participacion_actividades` DISABLE KEYS */;
INSERT INTO `participacion_actividades` VALUES (3,1,1),(13,1,6),(14,1,8);
/*!40000 ALTER TABLE `participacion_actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recursos`
--

DROP TABLE IF EXISTS `recursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recursos` (
  `id_Recurso` int NOT NULL AUTO_INCREMENT,
  `id_actividad` int NOT NULL,
  `Nombre_recurso` varchar(100) DEFAULT NULL,
  `Descripcion` varchar(200) DEFAULT NULL,
  `Cantidad` int DEFAULT NULL,
  `is_ofertada_por_ofertante` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_Recurso`),
  KEY `id_actividad_recurso_idx` (`id_actividad`),
  CONSTRAINT `id_actividad_recurso` FOREIGN KEY (`id_actividad`) REFERENCES `actividades` (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recursos`
--

LOCK TABLES `recursos` WRITE;
/*!40000 ALTER TABLE `recursos` DISABLE KEYS */;
INSERT INTO `recursos` VALUES (3,52,'Silla','Necesitaremos una silla para no cansarnos',1,_binary '\0'),(4,52,'Libro','El libro del que vamos a hablar',5,_binary '');
/*!40000 ALTER TABLE `recursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sugerencia_actividades`
--

DROP TABLE IF EXISTS `sugerencia_actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sugerencia_actividades` (
  `id_sugerencia` int NOT NULL AUTO_INCREMENT,
  `id_consumidor` int NOT NULL,
  `id_actividad_sugerida` int NOT NULL,
  PRIMARY KEY (`id_sugerencia`),
  KEY `id_consumidor_idx` (`id_consumidor`),
  KEY `id_actividad_idx` (`id_actividad_sugerida`),
  CONSTRAINT `id_actividad` FOREIGN KEY (`id_actividad_sugerida`) REFERENCES `actividades` (`id_actividad`),
  CONSTRAINT `id_consumidor` FOREIGN KEY (`id_consumidor`) REFERENCES `consumidor` (`id_consumidor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencia_actividades`
--

LOCK TABLES `sugerencia_actividades` WRITE;
/*!40000 ALTER TABLE `sugerencia_actividades` DISABLE KEYS */;
INSERT INTO `sugerencia_actividades` VALUES (1,1,1),(2,1,3),(3,6,7),(4,8,2);
/*!40000 ALTER TABLE `sugerencia_actividades` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-21 19:38:46
