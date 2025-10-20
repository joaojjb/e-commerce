-- MySQL dump 10.13  Distrib 8.4.0, for Linux (aarch64)
--
-- Host: localhost    Database: produtos_db
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Criar database
CREATE DATABASE IF NOT EXISTS produtos_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE produtos_db;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
                           `id` binary(16) NOT NULL,
                           `data_atualizacao` datetime(6) DEFAULT NULL,
                           `data_cadastro` datetime(6) NOT NULL,
                           `motivo_cancelamento` text,
                           `status_pedido` enum('CANCELADO','PAGO','PENDENTE') NOT NULL,
                           `tipo_pagamento` enum('BOLETO','CREDITO','DEBITO','PIX') DEFAULT NULL,
                           `valor_total` decimal(10,2) DEFAULT NULL,
                           `atualizado_por` binary(16) DEFAULT NULL,
                           `criado_por` binary(16) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKhxvlvbax0kb8r4pm8hm57p8q` (`atualizado_por`),
                           KEY `FK11lnhgx7sd16gmun3oyhfj7u8` (`criado_por`),
                           CONSTRAINT `FK11lnhgx7sd16gmun3oyhfj7u8` FOREIGN KEY (`criado_por`) REFERENCES `users` (`id`),
                           CONSTRAINT `FKhxvlvbax0kb8r4pm8hm57p8q` FOREIGN KEY (`atualizado_por`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (_binary '\'��J̧\�B�|B\�','2025-10-20 00:51:19.733098','2025-10-20 00:50:55.249382',NULL,'PAGO','CREDITO',5610.00,_binary 'P@\�a�C��,��X\�\�',_binary 'P@\�a�C��,��X\�\�'),(_binary 'y��\��BL�\�\�\�','2025-10-19 21:24:04.861582','2025-10-19 21:23:08.316076',NULL,'PAGO','PIX',17599.95,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary '��\�K\�\�Ge�C	~,ҟ�','2025-10-20 00:52:18.091743','2025-10-20 00:52:01.422225',NULL,'PAGO','CREDITO',7050.00,_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '0ӎ�OF8�\�^ӄ_\Z\�'),(_binary '�Q\n�\rJ��\�;P{��','2025-10-19 20:37:12.511103','2025-10-19 20:33:59.824108',NULL,'PAGO','BOLETO',35199.90,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary '�8\r!F/J\�Yy�\�g1','2025-10-19 21:39:27.232305','2025-10-19 21:39:09.269489',NULL,'PAGO','PIX',17599.95,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�'),(_binary '�\�\�5_B\\�ք��\�','2025-10-20 00:30:33.366486','2025-10-19 22:33:46.085334','Estoque insuficiente no momento do pagamento para: Notebook Dell Inspiron 15 (Disponível: 0, Necessário: 5)','CANCELADO',NULL,17499.95,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary '�X�;Mٙn��:G4','2025-10-19 22:53:48.480562','2025-10-19 22:53:17.702035',NULL,'PAGO','PIX',101250.00,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary 'Ňp\"\�L\r���	\\\r','2025-10-20 00:53:19.095546','2025-10-20 00:53:07.713045',NULL,'PAGO','CREDITO',23250.00,_binary 'z��m\�\�J��9\0��#c�',_binary 'z��m\�\�J��9\0��#c�'),(_binary 'ȃ�:�Ob�l\0�o\\	b','2025-10-20 00:39:03.750875','2025-10-20 00:39:03.750871',NULL,'PENDENTE',NULL,5250.00,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�'),(_binary '�k����J���Icva','2025-10-19 22:30:59.434290','2025-10-19 22:30:34.571735',NULL,'PAGO','PIX',100000.00,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
                            `id` binary(16) NOT NULL,
                            `data_atualizacao` datetime(6) DEFAULT NULL,
                            `data_cadastro` datetime(6) NOT NULL,
                            `categoria` enum('ALIMENTOS','ELETRONICOS','ROUPAS') NOT NULL,
                            `descricao` text,
                            `nome` varchar(255) NOT NULL,
                            `preco` decimal(10,2) NOT NULL,
                            `quantidade_estoque` int NOT NULL,
                            `atualizado_por` binary(16) DEFAULT NULL,
                            `criado_por` binary(16) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKcoenp1c1att18ix4si287cuvg` (`atualizado_por`),
                            KEY `FKhh8fent3tjjix9ybc2wkll195` (`criado_por`),
                            CONSTRAINT `FKcoenp1c1att18ix4si287cuvg` FOREIGN KEY (`atualizado_por`) REFERENCES `users` (`id`),
                            CONSTRAINT `FKhh8fent3tjjix9ybc2wkll195` FOREIGN KEY (`criado_por`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (_binary '�YN�hK4���$N�A�','2025-10-19 20:33:26.581863','2025-10-19 20:33:26.581850','ALIMENTOS','Arroz da Roça','Arroz',20.00,5,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary 'B�\\i6LH�\0�-��\�','2025-10-19 22:29:39.853960','2025-10-19 22:27:48.816181','ALIMENTOS','Batata Doce','Batata Doce',10.00,4800,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary ']\�(\��IP�\�ɾ�[}\�','2025-10-20 00:49:02.719227','2025-10-20 00:49:02.719223','ROUPAS','Moletom Manga Longa','Moletom',180.00,888,_binary 'P@\�a�C��,��X\�\�',_binary 'P@\�a�C��,��X\�\�'),(_binary '�\�QȤ�G?�=p3\\J\�\�','2025-10-19 22:52:30.156145','2025-10-19 22:52:30.156138','ALIMENTOS','Mostarda Boa','Mostarda 300g',25.00,800,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary '\�2]u~M1����.\�@','2025-10-19 20:32:14.628592','2025-10-19 20:32:14.628588','ELETRONICOS','Notebook com processador Intel i7, 16GB RAM, SSD 512GB, ideal para trabalho e desenvolvimento','Notebook Dell Inspiron 15',3499.99,0,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�'),(_binary '\�\"t`bC��t��\�Ŝ\\','2025-10-20 00:36:31.910319','2025-10-20 00:35:52.875751','ALIMENTOS','Mostarda Boa 2','Mostarda 300g',25.00,1000,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos_pedidos`
--

DROP TABLE IF EXISTS `produtos_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos_pedidos` (
                                    `id` binary(16) NOT NULL,
                                    `data_atualizacao` datetime(6) DEFAULT NULL,
                                    `data_cadastro` datetime(6) NOT NULL,
                                    `preco_total` decimal(10,2) NOT NULL,
                                    `preco_unitario` decimal(10,2) NOT NULL,
                                    `quantidade` int NOT NULL,
                                    `atualizado_por` binary(16) DEFAULT NULL,
                                    `criado_por` binary(16) DEFAULT NULL,
                                    `pedido_id` binary(16) NOT NULL,
                                    `produto_id` binary(16) NOT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `FKh0mle5aowiofy6tvt3k9hto96` (`atualizado_por`),
                                    KEY `FK74r91vi892085nkasohlmj8rs` (`criado_por`),
                                    KEY `FK196r9hhh84iadx1rscm7cwwar` (`pedido_id`),
                                    KEY `FKsdr5vdgpbuhct31kyo09jrph9` (`produto_id`),
                                    CONSTRAINT `FK196r9hhh84iadx1rscm7cwwar` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
                                    CONSTRAINT `FK74r91vi892085nkasohlmj8rs` FOREIGN KEY (`criado_por`) REFERENCES `users` (`id`),
                                    CONSTRAINT `FKh0mle5aowiofy6tvt3k9hto96` FOREIGN KEY (`atualizado_por`) REFERENCES `users` (`id`),
                                    CONSTRAINT `FKsdr5vdgpbuhct31kyo09jrph9` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos_pedidos`
--

LOCK TABLES `produtos_pedidos` WRITE;
/*!40000 ALTER TABLE `produtos_pedidos` DISABLE KEYS */;
INSERT INTO `produtos_pedidos` VALUES (_binary '\r}��e�B\�w?�ן','2025-10-19 22:53:17.709592','2025-10-19 22:53:17.709590',1250.00,25.00,50,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�X�;Mٙn��:G4',_binary '�\�QȤ�G?�=p3\\J\�\�'),(_binary '[\�A�\'A�r','2025-10-19 21:39:09.292032','2025-10-19 21:39:09.292030',100.00,20.00,5,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�',_binary '�8\r!F/J\�Yy�\�g1',_binary '�YN�hK4���$N�A�'),(_binary '$�iH�	L߰)SWx�\�','2025-10-20 00:52:01.427542','2025-10-20 00:52:01.427539',1800.00,180.00,10,_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '��\�K\�\�Ge�C	~,ҟ�',_binary ']\�(\��IP�\�ɾ�[}\�'),(_binary ',�[�\�qG��2��\'aE/','2025-10-19 22:53:17.709110','2025-10-19 22:53:17.709107',100000.00,10.00,10000,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�X�;Mٙn��:G4',_binary 'B�\\i6LH�\0�-��\�'),(_binary 'Y\�)\�?G\'�Bm3�,\0S','2025-10-19 21:39:09.291682','2025-10-19 21:39:09.291679',17499.95,3499.99,5,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�',_binary '�8\r!F/J\�Yy�\�g1',_binary '\�2]u~M1����.\�@'),(_binary '\\\��\�{cE\�Rz\�\�4�','2025-10-19 20:33:59.835275','2025-10-19 20:33:59.835272',34999.90,3499.99,10,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�Q\n�\rJ��\�;P{��',_binary '\�2]u~M1����.\�@'),(_binary 'l\�Z��\�A��>\��\n�\ni','2025-10-19 21:23:08.373792','2025-10-19 21:23:08.373779',100.00,20.00,5,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary 'y��\��BL�\�\�\�',_binary '�YN�hK4���$N�A�'),(_binary 'x\�\�k �HG�v~\�\n-\�','2025-10-19 22:30:34.588098','2025-10-19 22:30:34.588095',100000.00,10.00,10000,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�k����J���Icva',_binary 'B�\\i6LH�\0�-��\�'),(_binary '�N���+Do��gW\�\��4','2025-10-20 00:53:07.715377','2025-10-20 00:53:07.715373',1250.00,25.00,50,_binary 'z��m\�\�J��9\0��#c�',_binary 'z��m\�\�J��9\0��#c�',_binary 'Ňp\"\�L\r���	\\\r',_binary '�\�QȤ�G?�=p3\\J\�\�'),(_binary '�\"�\�q|M\Z�.�HR0\�','2025-10-20 00:39:03.758773','2025-10-20 00:39:03.758771',1250.00,25.00,50,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�',_binary 'ȃ�:�Ob�l\0�o\\	b',_binary '�\�QȤ�G?�=p3\\J\�\�'),(_binary '���=\�7CE��\0Q\�\�','2025-10-20 00:50:55.253280','2025-10-20 00:50:55.253277',360.00,180.00,2,_binary 'P@\�a�C��,��X\�\�',_binary 'P@\�a�C��,��X\�\�',_binary '\'��J̧\�B�|B\�',_binary ']\�(\��IP�\�ɾ�[}\�'),(_binary '��\�@N��\�BA_o\�','2025-10-20 00:53:07.713849','2025-10-20 00:53:07.713846',4000.00,10.00,400,_binary 'z��m\�\�J��9\0��#c�',_binary 'z��m\�\�J��9\0��#c�',_binary 'Ňp\"\�L\r���	\\\r',_binary 'B�\\i6LH�\0�-��\�'),(_binary '�6�sf\�Eĕ\0r��ʢ','2025-10-20 00:50:55.252828','2025-10-20 00:50:55.252825',4000.00,10.00,400,_binary 'P@\�a�C��,��X\�\�',_binary 'P@\�a�C��,��X\�\�',_binary '\'��J̧\�B�|B\�',_binary 'B�\\i6LH�\0�-��\�'),(_binary '�!n5�OMp���\�z��Q','2025-10-20 00:52:01.423026','2025-10-20 00:52:01.423024',4000.00,10.00,400,_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '��\�K\�\�Ge�C	~,ҟ�',_binary 'B�\\i6LH�\0�-��\�'),(_binary '�\�#\�A��J\���','2025-10-19 20:33:59.835743','2025-10-19 20:33:59.835741',200.00,20.00,10,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�Q\n�\rJ��\�;P{��',_binary '�YN�hK4���$N�A�'),(_binary '�\�&CƭJTL\�InO','2025-10-20 00:52:01.424095','2025-10-20 00:52:01.424091',1250.00,25.00,50,_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '0ӎ�OF8�\�^ӄ_\Z\�',_binary '��\�K\�\�Ge�C	~,ҟ�',_binary '�\�QȤ�G?�=p3\\J\�\�'),(_binary '\�\�d\�\�N�����\�\�Z\Z','2025-10-20 00:39:03.758307','2025-10-20 00:39:03.758304',4000.00,10.00,400,_binary 'J�G\�A��\��@�A\�\�',_binary 'J�G\�A��\��@�A\�\�',_binary 'ȃ�:�Ob�l\0�o\\	b',_binary 'B�\\i6LH�\0�-��\�'),(_binary '\�`\�^IФ\�d�M','2025-10-20 00:53:07.715732','2025-10-20 00:53:07.715724',18000.00,180.00,100,_binary 'z��m\�\�J��9\0��#c�',_binary 'z��m\�\�J��9\0��#c�',_binary 'Ňp\"\�L\r���	\\\r',_binary ']\�(\��IP�\�ɾ�[}\�'),(_binary '桦2�@��C?�sm�','2025-10-19 21:23:08.368267','2025-10-19 21:23:08.368264',17499.95,3499.99,5,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary 'y��\��BL�\�\�\�',_binary '\�2]u~M1����.\�@'),(_binary '\�\�GbzA����J�\��v','2025-10-20 00:50:55.253156','2025-10-20 00:50:55.253150',1250.00,25.00,50,_binary 'P@\�a�C��,��X\�\�',_binary 'P@\�a�C��,��X\�\�',_binary '\'��J̧\�B�|B\�',_binary '�\�QȤ�G?�=p3\\J\�\�'),(_binary '���WRLٙ��lԎ\�B','2025-10-19 22:33:46.087527','2025-10-19 22:33:46.087512',17499.95,3499.99,5,_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '^\�˯Ce�1Ȝ5\�{\�',_binary '�\�\�5_B\\�ք��\�',_binary '\�2]u~M1����.\�@');
/*!40000 ALTER TABLE `produtos_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` binary(16) NOT NULL,
  `data_atualizacao` datetime(6) DEFAULT NULL,
  `data_cadastro` datetime(6) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '^\�˯Ce�1Ȝ5\�{\�','2025-10-19 20:31:50.760693','2025-10-19 20:31:50.760666','$2a$10$3IpTHXUs1KN7hEhN5mgVEe.WvOcHxDZzdrkmpzVYRdUPaV/MSSyWG','ADMIN','admin'),(_binary '0ӎ�OF8�\�^ӄ_\Z\�','2025-10-20 00:41:17.210427','2025-10-20 00:41:17.210352','$2a$10$C8pT7TCpwovPxhu2u6PGc.RV2TKpmqPfvCH2n/nTlOKyy18a6a6I6','USER','Joao'),(_binary 'J�G\�A��\��@�A\�\�','2025-10-19 20:32:01.021577','2025-10-19 20:32:01.021475','$2a$10$39ByA2C16b6FwFUKwQbUVulnT.dXWlqMPhkKEDF6lt2n50J5WlGS2','USER','user'),(_binary 'P@\�a�C��,��X\�\�','2025-10-20 00:41:50.599284','2025-10-20 00:41:50.599197','$2a$10$76OfFcJciMCboAmqQR1bx..ZJ49EH8Jwe1TklTGpcj6bXiit01xIi','ADMIN','Lucas'),(_binary 'wo*7\�/LӘ\�z�\���','2025-10-20 00:41:35.141045','2025-10-20 00:41:35.140966','$2a$10$Fd57.6M0xU4FbFbPZzVA6OAdw0ZFCGoMGadLkBAdt14vXBC.cVy0G','USER','Jose'),(_binary 'z��m\�\�J��9\0��#c�','2025-10-20 00:41:25.658286','2025-10-20 00:41:25.658181','$2a$10$8gRuMbh6l/XqCaV9th.0bOqnptCcFoUpv5mNG31K93xz/fmcthHke','USER','Camila');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-20  1:09:11
