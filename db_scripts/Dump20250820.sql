-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: shec_psims
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `alert_condition`
--

DROP TABLE IF EXISTS `alert_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert_condition` (
  `requirement_id` int NOT NULL,
  `warning_id` int NOT NULL,
  `attribute` varchar(30) NOT NULL,
  `relation` varchar(100) NOT NULL,
  `value` int DEFAULT NULL,
  `left_value` int DEFAULT NULL,
  `right_value` int DEFAULT NULL,
  PRIMARY KEY (`requirement_id`),
  KEY `FK_ALERT_CO_BASE_ALERT_SE` (`warning_id`),
  CONSTRAINT `FK_ALERT_CO_BASE_ALERT_SE` FOREIGN KEY (`warning_id`) REFERENCES `alert_set` (`warning_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_condition`
--

LOCK TABLES `alert_condition` WRITE;
/*!40000 ALTER TABLE `alert_condition` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_record`
--

DROP TABLE IF EXISTS `alert_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert_record` (
  `warning_record_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `dicease_name` varchar(50) DEFAULT NULL,
  `dicease_type` varchar(50) DEFAULT NULL,
  `warning_level` int DEFAULT NULL,
  `information` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`warning_record_id`),
  KEY `FK_ALERT_RE_TRIGGER_ELDERLYP` (`patient_id`),
  CONSTRAINT `FK_ALERT_RE_TRIGGER_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_record`
--

LOCK TABLES `alert_record` WRITE;
/*!40000 ALTER TABLE `alert_record` DISABLE KEYS */;
INSERT INTO `alert_record` VALUES (1,1,'2025-05-28','08:30:00','高血压','慢性病',1,'血压偏高，达到145/92'),(2,2,'2025-05-28','09:15:00','糖尿病','慢性病',2,'空腹血糖达到9.2mmol/L，需要调整用药'),(3,5,'2025-05-28','10:30:00','心律失常','心脏问题',3,'监测到心律不齐，心率达到105次/分钟'),(4,9,'2025-05-28','11:45:00','冠心病','心脏问题',2,'患者报告胸闷症状，需进行进一步检查'),(5,3,'2025-05-28','13:20:00','脑缺血','神经系统',3,'短暂性脑缺血发作，需紧急处理'),(6,15,'2025-05-28','14:10:00','高血压','慢性病',1,'血压升至160/95，建议复查'),(7,11,'2025-05-28','15:30:00','心绞痛','心脏问题',2,'患者报告活动后胸痛'),(8,19,'2025-05-28','16:45:00','冠心病','心脏问题',2,'心电图检查显示ST段轻度改变'),(9,25,'2025-05-28','17:20:00','心律失常','心脏问题',3,'出现室性早搏，需要进一步观察'),(10,7,'2025-05-28','18:05:00','肝功能异常','消化系统',1,'转氨酶轻度升高'),(11,4,'2025-05-27','08:45:00','骨质疏松','骨骼系统',1,'骨密度检查结果异常'),(12,10,'2025-05-27','09:30:00','糖尿病','慢性病',2,'糖化血红蛋白达到8.1%'),(13,23,'2025-05-27','10:20:00','脑梗后遗症','神经系统',2,'左侧肢体活动受限加重'),(14,6,'2025-05-27','11:10:00','慢阻肺','呼吸系统',2,'呼吸困难症状加重'),(15,8,'2025-05-27','13:45:00','中风后遗症','神经系统',1,'右侧肢体康复进展缓慢'),(16,14,'2025-05-27','14:30:00','胃炎','消化系统',1,'出现上腹部不适'),(17,20,'2025-05-27','15:15:00','认知障碍','神经系统',2,'近期记忆力下降明显'),(18,12,'2025-05-27','16:00:00','骨质疏松','骨骼系统',1,'腰痛症状加重'),(19,16,'2025-05-27','16:50:00','类风湿关节炎','免疫系统',2,'关节肿痛加重'),(20,22,'2025-05-27','17:40:00','白内障','眼科问题',1,'视力进一步下降'),(21,17,'2025-05-26','08:50:00','肾功能不全','泌尿系统',2,'肌酐升高至150umol/L'),(22,13,'2025-05-26','09:40:00','前列腺肥大','泌尿系统',1,'夜尿次数增加'),(23,27,'2025-05-26','10:35:00','高血压','慢性病',3,'血压达到165/100，药物控制效果不佳'),(24,21,'2025-05-26','11:25:00','慢性咳嗽','呼吸系统',1,'夜间咳嗽加重'),(25,18,'2025-05-26','13:15:00','白内障','眼科问题',1,'视物模糊加重'),(26,24,'2025-05-26','14:05:00','高血压','慢性病',1,'血压波动较大'),(27,26,'2025-05-26','15:00:00','慢性胃炎','消化系统',1,'胃痛症状加重'),(28,5,'2025-05-26','15:50:00','高血脂','代谢问题',2,'甘油三酯显著升高'),(29,1,'2025-05-26','16:40:00','高尿酸血症','代谢问题',1,'尿酸水平升高'),(30,2,'2025-05-26','17:30:00','视网膜病变','眼科问题',2,'眼底检查显示病变加重');
/*!40000 ALTER TABLE `alert_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_set`
--

DROP TABLE IF EXISTS `alert_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert_set` (
  `warning_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `dicease_name` varchar(50) DEFAULT NULL,
  `dicease_type` varchar(50) DEFAULT NULL,
  `warning_level` int DEFAULT NULL,
  PRIMARY KEY (`warning_id`),
  KEY `FK_ALERT_SE_SET_ELDERLYP` (`patient_id`),
  CONSTRAINT `FK_ALERT_SE_SET_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_set`
--

LOCK TABLES `alert_set` WRITE;
/*!40000 ALTER TABLE `alert_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `answer_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `questionnaire_id` int NOT NULL,
  `submit_date` date NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `answer_user_user_id_fk` (`user_id`),
  KEY `answer_questionnaires_questionnaire_id_fk` (`questionnaire_id`),
  CONSTRAINT `answer_questionnaires_questionnaire_id_fk` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaires` (`questionnaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (5,1,2,'2025-08-11'),(6,1,2,'2025-08-11'),(7,1,2,'2025-08-11'),(8,1,2,'2025-08-11'),(9,1,2,'2025-08-11'),(22,18,2,'2025-08-15'),(23,18,3,'2025-08-16'),(24,18,2,'2025-08-16'),(25,19,2,'2025-08-16');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_log`
--

DROP TABLE IF EXISTS `audit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_log` (
  `log_id` int NOT NULL,
  `user_id` int NOT NULL,
  `action` varchar(100) NOT NULL,
  `timestamp` timestamp NOT NULL,
  `ip_address` varchar(15) DEFAULT NULL,
  `details` text,
  PRIMARY KEY (`log_id`),
  KEY `FK_AUDIT_LO_GENERATE_USER` (`user_id`),
  CONSTRAINT `FK_ADUIT_LO_GENERATE_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_log`
--

LOCK TABLES `audit_log` WRITE;
/*!40000 ALTER TABLE `audit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultation_record`
--

DROP TABLE IF EXISTS `consultation_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultation_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `content` varchar(250) DEFAULT NULL,
  `doctor_name` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation_record`
--

LOCK TABLES `consultation_record` WRITE;
/*!40000 ALTER TABLE `consultation_record` DISABLE KEYS */;
INSERT INTO `consultation_record` VALUES (1,'2025-07-17','111','1','19',0),(2,'2025-07-17','医生你好','1','19',1),(3,'2025-07-17','1','1','19',1),(4,'2025-07-17','医生你好，我有点感冒怎么办','1','19',1),(5,'2025-07-17','医生你好','1','19',1),(6,'2025-07-17','你好，身体哪里不舒服','1','19',0),(7,'2025-07-17','头疼','1','19',1),(8,'2025-07-17','有感冒症状吗','王医生','19',0),(9,'2025-07-21','1','李医生','张三',1),(10,'2025-07-21','你好','李医生','张三',1),(11,'2025-07-21','李医生你好','李医生','李四',1),(12,'2025-07-21','你好张三','李医生','lisi',0),(13,'2025-07-23','1','李医生','lisi',0),(14,'2025-07-23','1','李医生','lisi',1),(15,'2025-07-23','1','李医生','张三',1),(16,'2025-07-23','1','李医生','张三',0),(17,'2025-07-23','2','李医生','张三',1),(18,'2025-07-23','1','李医生','张三',0),(19,'2025-07-23','1','李医生','张三',1),(20,'2025-07-23','1','李医生','张三',1),(21,'2025-07-23','222','李医生','张三',1),(22,'2025-07-23','1','李医生','张三',0),(23,'2025-07-23','2222','李医生','张三',0),(24,'2025-07-23','222222222','李医生','张三',0),(25,'2025-07-23','111','李医生','张三',0),(26,'2025-07-23','1','李医生','张三',0),(27,'2025-07-23','2','李医生','张三',0),(28,'2025-07-23','3','李医生','张三',0),(29,'2025-07-23','1','李医生','张三',1),(30,'2025-07-24','123','李医生','张三',1),(31,'2025-07-24','12','李医生','张三',0),(32,'2025-08-13','111','李医生','张三',0),(33,'2025-08-13','1','李医生','张三',0),(34,'2025-08-13','2','李医生','张三',0),(35,'2025-08-13','1','李医生','张三',1),(36,'2025-08-13','1','李医生','张三',1),(37,'2025-08-13','2','李医生','张三',1),(38,'2025-08-13','123','李医生','张三',1),(39,'2025-08-13','2','李医生','张三',0);
/*!40000 ALTER TABLE `consultation_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_usage`
--

DROP TABLE IF EXISTS `device_usage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_usage` (
  `usage_id` int NOT NULL,
  `UserID` int NOT NULL,
  `patient_id` int NOT NULL,
  `device_id` int NOT NULL,
  `use_time` datetime NOT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`usage_id`),
  KEY `FK_DEVICE_U_OPERATE_USER` (`UserID`),
  KEY `FK_DEVICE_U_SERVE_ELDERLYP` (`patient_id`),
  KEY `FK_DEVICE_U_USE_DEVICES` (`device_id`),
  CONSTRAINT `FK_DEVICE_U_OPERATE_USER` FOREIGN KEY (`UserID`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_DEVICE_U_SERVE_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_DEVICE_U_USE_DEVICES` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_usage`
--

LOCK TABLES `device_usage` WRITE;
/*!40000 ALTER TABLE `device_usage` DISABLE KEYS */;
/*!40000 ALTER TABLE `device_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices` (
  `device_id` int NOT NULL,
  `device_code` varchar(50) NOT NULL,
  `device_name` varchar(100) NOT NULL,
  `device_type` varchar(10) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `last_maintenance` date DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'75580','N-invas est c ffr sw aly cta','监测','正常','药房','2024-03-13'),(2,'92520','Laryngeal function studies','康复','停用','外科手术室','2024-03-10'),(3,'58346','Insert heyman uteri capsule','监测','维修','影像科','2023-07-11'),(4,'0043U','Tbrg b grp antb 4 prtn igm','康复','停用','影像科','2024-06-25'),(5,'0315U','Onc cutan sq cll ca mrna 40','康复','维修','ICU','2024-08-27'),(6,'G6012','Radiation treatment delivery','治疗','维修','影像科','2025-01-03'),(7,'70450','Ct head/brain w/o dye','诊断','维修','影像科','2024-08-17'),(8,'73225','Mr angio upr extr w/o&w/dye','监测','停用','ICU','2025-01-02'),(9,'0408U','Iaad blk ac wv bsnsr sarscv2','监测','正常','检验科','2024-09-07'),(10,'Q0112','Potassium hydroxide preps','监测','维修','内科病房','2023-10-25'),(11,'75565','Card MRI veloc flow mapping','治疗','维修','检验科','2023-11-20'),(12,'0514U','Gi ibd ia quan deter adl lvl','康复','维修','ICU','2024-11-24'),(13,'Q0113','Pinworm examinations','治疗','正常','急诊科','2023-05-24'),(14,'0341U','Ftl aneup dna seq cmpr alys','康复','维修','内科病房','2024-04-26'),(15,'0865T','Quan mri alys brn w/o dx mri','康复','维修','检验科','2024-10-10'),(16,'0223U','Nfct ds 22 trgt sars-cov-2 ','康复','停用','急诊科','2025-05-15'),(17,'0260U','Rare ds id opt genome mapg','康复','正常','急诊科','2024-06-24'),(18,'97610','Low frequency non-thermal US','诊断','正常','急诊科','2024-07-05'),(19,'78099','Endocrine nuclear procedure','诊断','停用','药房','2023-08-11'),(20,'19298','Place breast rad tube/caths','诊断','正常','ICU','2024-03-20'),(21,'73201','Ct upper extremity w/dye','监测','维修','检验科','2025-02-20'),(22,'0239U','Trgt gen seq alys pnl 311+','康复','停用','外科手术室','2024-05-22'),(23,'70547','Mr angiography neck w/o dye','康复','维修','中心供应室','2024-02-01'),(24,'0470U','Onc orop detcj mrd 8 dna hpv','监测','维修','影像科','2023-12-15'),(25,'78104','Bone marrow imaging body','康复','维修','药房','2024-12-13'),(26,'0038U ','Vitamin d srm microsamp quan','监测','停用','ICU','2024-02-29'),(27,'0021U','Onc prst8 detcj 8 autoantb','治疗','正常','外科手术室','2025-03-08'),(28,'97610','Low frequency non-thermal US','康复','正常','中心供应室','2023-09-11'),(29,'0177U','Onc brst ca dna pik3ca 11','诊断','维修','影像科','2023-09-23'),(30,'74250','X-ray xm sm int 1cntrst std','诊断','停用','急诊科','2024-05-18'),(31,'0413U','Onc hl neo opt gen mapg dna','诊断','维修','药房','2024-10-30'),(32,'Q0114','Fern test','监测','维修','影像科','2023-07-21'),(33,'98977','Rem ther mntr dv sply mscskl','治疗','正常','影像科','2025-03-17'),(34,'0633T','Ct breast w/3d uni c-','监测','维修','急诊科','2025-02-18'),(35,'G0339','Robot lin-radsurg com, first','治疗','正常','检验科','2025-03-06'),(36,'G0563','Sbrt w/positron emission del','康复','维修','检验科','2024-12-08'),(37,'77073','X-rays bone length studies','治疗','停用','药房','2024-03-05'),(38,'0174U','Onc solid tumor 30 prtn trgt','监测','正常','ICU','2024-03-29'),(39,'A9526','Nitrogen N-13 ammonia','监测','维修','检验科','2023-05-21'),(40,'93980','Penile vascular study','治疗','停用','中心供应室','2024-04-05'),(41,'72202','X-ray exam sacroiliac joints','治疗','正常','药房','2024-12-20'),(42,'78730','Urinary bladder retention','诊断','维修','ICU','2023-09-20'),(43,'G0475 ','Hiv combination assay ','治疗','正常','中心供应室','2023-05-17'),(44,'G6005','Radiation treatment delivery','康复','维修','外科手术室','2025-01-20'),(45,'0038U ','Vitamin d srm microsamp quan','诊断','停用','检验科','2025-01-27'),(46,'A9506','Tc-99m graphite crucible','治疗','正常','影像科','2024-02-07'),(47,'73521','X-ray exam hips bi 2 views','治疗','停用','检验科','2023-10-17'),(48,'0859T','Ncntc ifr spctrsc o/t pad ea','监测','维修','急诊科','2023-12-30'),(49,'0900T','N-invas est aqmbf asstv cmr','治疗','停用','内科病房','2023-07-25'),(50,'Q9982','Flutemetamol f18 diagnostic','诊断','维修','ICU','2023-09-29'),(51,'G6007 ','Radiation treatment delivery','治疗','正常','中心供应室','2023-09-14'),(52,'Q0113','Pinworm examinations','治疗','正常','中心供应室','2024-08-31'),(53,'78610','Brain flow imaging only','监测','维修','中心供应室','2023-10-17'),(54,'0486U','Onc pan sol tum ngs cfctdna','诊断','正常','内科病房','2024-11-10'),(55,'77620','Hyperthermia treatment','治疗','维修','内科病房','2024-03-07'),(56,'70487','Ct maxillofacial w/dye','监测','正常','检验科','2024-12-31'),(57,'19296','Place po breast cath for rad','监测','停用','影像科','2025-03-28'),(58,'77072','X-rays for bone age','监测','停用','中心供应室','2024-05-04'),(59,'72131','Ct lumbar spine w/o dye','监测','维修','影像科','2025-04-05'),(60,'78492','Myocrd img pet mlt rst&strs','监测','正常','影像科','2024-08-24'),(61,'78499','Cardiovascular nuclear exam','监测','停用','急诊科','2024-02-08'),(62,'A9608','Flotufolastat f18 diag 1 mci','治疗','停用','急诊科','2024-11-22'),(63,'0516U','Rx metab rxgenomic gnotyp 40','诊断','停用','药房','2025-04-09'),(64,'C1719','Brachytx, NS, Non-HDRIr-192','监测','停用','急诊科','2024-05-16'),(65,'78099','Endocrine nuclear procedure','康复','正常','急诊科','2025-02-01'),(66,'0395U','Onc lng multiomics plsm alg','治疗','维修','急诊科','2023-11-09'),(67,'A9590','Iodine i-131 iobenguane 1mci','监测','正常','检验科','2025-01-03');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_comment`
--

DROP TABLE IF EXISTS `doctor_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `all_score` int NOT NULL,
  `profession_score` int NOT NULL,
  `time_score` int NOT NULL,
  `attitude_score` int NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `comment` varchar(250) NOT NULL,
  `user_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `average` double DEFAULT NULL,
  `admin_id` int NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_comment`
--

LOCK TABLES `doctor_comment` WRITE;
/*!40000 ALTER TABLE `doctor_comment` DISABLE KEYS */;
INSERT INTO `doctor_comment` VALUES (1,5,5,5,5,'回复速度快','医生专业性很强，服务态度很好',19,1,'2025-07-15',NULL,0),(2,5,5,5,5,'回复速度快,专业性强','1234567890',19,1,'2025-07-15',NULL,0),(3,4,5,3,2,'','1234567890',19,1,'2025-07-15',NULL,0),(4,4,5,5,2,'专业性强,回复速度快','医生很专业，解决了我的问题',19,1,'2025-07-15',NULL,0),(5,4,2,2,2,'','<h3>服务评价：</h3>',19,1,'2025-07-15',NULL,0),(6,5,4,4,3,'','1234567890',19,1,'2025-07-16',NULL,0),(7,3,5,5,5,'','1234567890',19,1,'2025-07-16',NULL,0),(8,3,5,5,5,'','1234567890',1,1,'2025-07-16',4.5,1),(9,3,4,3,2,'','医生专业性非常强，很可靠',1,1,'2025-07-24',3.3,1),(10,3,5,4,4,'专业性强,态度亲和','1234567890000000000000000000000000000000000000000000000000000000000000000000000',1,19,'2025-08-13',4,1),(11,4,3,4,5,'态度亲和','1234567890',1,19,'2025-08-13',4,1);
/*!40000 ALTER TABLE `doctor_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_patient`
--

DROP TABLE IF EXISTS `doctor_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_patient` (
  `doctor_patient_id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`doctor_patient_id`),
  KEY `fk_doctor_patient_doctor_id` (`doctor_id`),
  KEY `fk_doctor_patient_patient_id` (`patient_id`),
  CONSTRAINT `fk_doctor_patient_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_doctor_patient_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_patient`
--

LOCK TABLES `doctor_patient` WRITE;
/*!40000 ALTER TABLE `doctor_patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elderlyprofile`
--

DROP TABLE IF EXISTS `elderlyprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elderlyprofile` (
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `id_number` varchar(18) DEFAULT NULL,
  `contact_info` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `family_medical_history` text,
  `allergy_history` text,
  `past_medical_history` text,
  `height` float DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `blood_pressure` varchar(10) DEFAULT NULL,
  `heart_rate` int DEFAULT NULL,
  `admission_date` datetime DEFAULT NULL COMMENT '入院时间',
  `discharge_date` datetime DEFAULT NULL COMMENT '出院时间',
  `status` varchar(20) DEFAULT '住院中' COMMENT '状态：住院中/已出院/转院',
  `create_date` date DEFAULT NULL COMMENT '记录创建日期',
  `last_update` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`patient_id`),
  KEY `idx_status` (`status`),
  KEY `idx_admission_date` (`admission_date`),
  KEY `idx_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elderlyprofile`
--

LOCK TABLES `elderlyprofile` WRITE;
/*!40000 ALTER TABLE `elderlyprofile` DISABLE KEYS */;
INSERT INTO `elderlyprofile` VALUES (1,'张三','男',68,'1957-05-15','110101195705153456','13800138001','北京市朝阳区建国路89号','父亲有高血压病史','青霉素过敏','2020年做过心脏搭桥手术',172.5,70.3,'138/85',72,'2025-05-15 00:00:00',NULL,'住院中','2025-05-01','2025-05-29 15:12:15'),(2,'李四','女',75,'1950-10-22','110101195010224567','13900139002','北京市海淀区中关村南路66号','母亲有糖尿病史','无','长期高血压，2018年诊断2型糖尿病',162,58.5,'145/90',78,'2025-05-22 00:00:00',NULL,'住院中','2025-05-17','2025-05-29 15:12:15'),(3,'王五','男',82,'1943-03-18','110101194303185678','13700137003','北京市西城区德胜门外大街12号','家族有肝癌病史','碘过敏','2015年脑梗塞，2019年诊断前列腺增生',168,62.1,'135/82',68,'2025-05-21 00:00:00',NULL,'住院中','2025-05-22','2025-05-29 15:12:15'),(4,'赵六','女',70,'1955-12-05','110101195512056789','13600136004','北京市东城区东四十条33号','无明显家族病史','海鲜过敏','骨质疏松，2021年右髋关节置换',158.5,55.2,'128/75',65,'2025-05-21 00:00:00',NULL,'住院中','2025-05-07','2025-05-29 15:12:15'),(5,'钱七','男',76,'1949-08-30','110101194908307890','13500135005','北京市丰台区丰台路99号','父亲有冠心病史','磺胺类药物过敏','高血压、高血脂，2017年心肌梗死',170,75.6,'150/95',85,'2025-05-02 00:00:00','2025-05-03 00:00:00','已出院','2025-05-19','2025-05-29 15:12:15'),(6,'孙八','女',80,'1945-04-12','110101194504128901','13400134006','北京市石景山区石景山路45号','母亲有老年痴呆','无','关节炎，慢性支气管炎',155,50.3,'132/80',70,'2025-05-01 00:00:00',NULL,'住院中','2025-05-09','2025-05-29 15:12:15'),(7,'周九','男',65,'1960-11-24','110101196011249012','13300133007','北京市通州区通州路188号','兄弟有糖尿病史','无','轻度脂肪肝，胆固醇偏高',175,80.2,'142/88',76,'2025-05-13 00:00:00','2025-05-22 00:00:00','转院','2025-05-10','2025-05-29 15:12:15'),(8,'吴十','女',72,'1953-07-07','110101195307070123','13200132008','北京市昌平区昌平路56号','无明显家族病史','青霉素过敏','2019年中风，右侧轻度偏瘫',160,48.9,'125/78',72,'2025-05-08 00:00:00',NULL,'住院中','2025-05-13','2025-05-29 15:12:15'),(9,'郑十一','男',78,'1947-02-14','110101194702141234','13100131009','北京市大兴区大兴路77号','父亲有心脏病','无','冠心病，2020年植入心脏支架',173,68.4,'130/82',80,'2025-05-12 00:00:00',NULL,'住院中','2025-05-21','2025-05-29 15:12:15'),(10,'王十二','女',69,'1956-09-29','110101195609292345','13000130010','北京市顺义区顺义路100号','母亲有高血压','磺胺类药物过敏','2型糖尿病，视网膜病变',159,60.7,'140/85',75,'2025-05-09 00:00:00','2025-05-11 00:00:00','已出院','2025-05-11','2025-05-29 15:12:15'),(11,'刘十三','男',77,'1948-06-18','110101194806183456','13900139011','北京市房山区房山路23号','父母均有高血压','青霉素过敏','多年高血压，2018年轻微脑梗',171,72.8,'155/92',82,'2025-05-29 00:00:00',NULL,'住院中','2025-05-23','2025-05-29 15:12:15'),(12,'陈十四','女',84,'1941-12-30','110101194112304567','13800138012','北京市门头沟区门头沟路66号','姐姐有糖尿病','对花粉过敏','骨质疏松，曾骨折两次',156,45.3,'122/76',68,'2025-05-27 00:00:00',NULL,'住院中','2025-05-06','2025-05-29 15:12:15'),(13,'杨十五','男',66,'1959-03-05','110101195903055678','13700137013','北京市平谷区平谷路88号','无明显家族病史','无','轻度高血压，前列腺肥大',178,85.1,'145/88',78,'2025-05-11 00:00:00',NULL,'住院中','2025-05-08','2025-05-29 15:12:15'),(14,'张十六','女',73,'1952-08-11','110101195208116789','13600136014','北京市怀柔区怀柔路55号','姐妹有心脏病史','对虾过敏','高血脂，慢性胃炎',163,52.6,'130/80',70,'2025-05-06 00:00:00','2025-05-06 00:00:00','转院','2025-05-06','2025-05-29 15:12:15'),(15,'黄十七','男',81,'1944-05-22','110101194405227890','13500135015','北京市密云区密云路33号','父亲有中风史','无','2016年中风，左侧轻度瘫痪，高血压',169,65.3,'152/90',84,'2025-05-13 00:00:00','2025-05-21 00:00:00','已出院','2025-05-17','2025-05-29 15:12:15'),(16,'赵十八','女',74,'1951-01-17','110101195101178901','13400134016','北京市延庆区延庆路77号','母亲有糖尿病史','青霉素过敏','类风湿性关节炎，骨质疏松',157,51.2,'135/82',72,'2025-05-17 00:00:00',NULL,'住院中','2025-05-05','2025-05-29 15:12:15'),(17,'吴十九','男',67,'1958-11-03','110101195811039012','13300133017','北京市东城区东直门外大街45号','父亲有心脏病','对某些水果过敏','轻度高血压，2019年检查出早期肾功能不全',176,78.9,'138/86',76,'2025-05-04 00:00:00',NULL,'住院中','2025-05-06','2025-05-29 15:12:15'),(18,'徐二十','女',79,'1946-07-29','110101194607290123','13200132018','北京市西城区西直门内大街12号','无明显家族病史','无','糖尿病，白内障已手术',161,53,'125/75',68,'2025-05-19 00:00:00',NULL,'住院中','2025-05-17','2025-05-29 15:12:15'),(19,'周二一','男',71,'1954-04-15','110101195404151234','13100131019','北京市朝阳区朝阳路168号','叔叔有高血压','对某些药物过敏','冠心病，高血压',174,76.5,'148/92',80,'2025-05-28 00:00:00',NULL,'住院中','2025-05-02','2025-05-29 15:12:15'),(20,'孙二二','女',83,'1942-09-08','110101194209082345','13000130020','北京市海淀区中关村北路33号','母亲有糖尿病','青霉素过敏','骨质疏松，轻度认知障碍',154,48.7,'130/78',70,'2025-05-15 00:00:00','2025-05-18 00:00:00','已出院','2025-05-07','2025-05-29 15:12:15'),(21,'李二三','男',64,'1961-02-20','110101196102203456','13900139021','北京市丰台区丰台南路22号','无明显家族病史','无','高血脂，慢性咳嗽',177,82.3,'135/85',74,'2025-05-24 00:00:00','2025-05-28 00:00:00','转院','2025-05-09','2025-05-29 15:12:15'),(22,'王二四','女',76,'1949-10-14','110101194910144567','13800138022','北京市石景山区石景山西路11号','母亲有高血压','对某些海鲜过敏','2型糖尿病，白内障，高血压',158,54.1,'142/88',78,'2025-05-02 00:00:00',NULL,'住院中','2025-05-15','2025-05-29 15:12:15'),(23,'张二五','男',80,'1945-06-30','110101194506305678','13700137023','北京市通州区通州北路76号','父亲有肾病史','无','高血压，2017年脑梗塞',170,65.8,'150/92',82,'2025-05-11 00:00:00',NULL,'住院中','2025-05-08','2025-05-29 15:12:15'),(24,'刘二六','女',68,'1957-12-25','110101195712256789','13600136024','北京市昌平区昌平南路50号','姐妹有糖尿病','青霉素过敏','关节炎，高血压',159,58.3,'135/85',75,'2025-05-07 00:00:00',NULL,'住院中','2025-05-12','2025-05-29 15:12:15'),(25,'陈二七','男',77,'1948-08-05','110101194808057890','13500135025','北京市大兴区大兴西路15号','父亲有中风史','无','冠心病，有两个支架',175,79.6,'145/90',85,'2025-05-09 00:00:00','2025-05-12 00:00:00','已出院','2025-05-09','2025-05-29 15:12:15'),(26,'杨二八','女',72,'1953-03-19','110101195303198901','13400134026','北京市顺义区顺义东路28号','无明显家族病史','对某些药物过敏','骨质疏松，慢性胃炎',156,50.5,'128/76',70,'2025-05-21 00:00:00',NULL,'住院中','2025-05-19','2025-05-29 15:12:15'),(27,'赵二九','男',84,'1941-11-11','110101194111119012','13300133027','北京市房山区房山南路8号','兄弟有心脏病','无','高血压，前列腺增生',168,63.7,'152/94',88,'2025-04-30 00:00:00',NULL,'住院中','2025-05-05','2025-05-29 15:12:15');
/*!40000 ALTER TABLE `elderlyprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_contact`
--

DROP TABLE IF EXISTS `emergency_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emergency_contact` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `relation` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `fk_emergency_contact_patient_id` (`patient_id`),
  CONSTRAINT `fk_emergency_contact_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_contact`
--

LOCK TABLES `emergency_contact` WRITE;
/*!40000 ALTER TABLE `emergency_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation` (
  `score` int NOT NULL,
  `content` varchar(100) NOT NULL,
  `date` date DEFAULT NULL,
  `tag` varchar(30) DEFAULT NULL,
  `evaluation_id` int NOT NULL,
  `user_id` int NOT NULL,
  `use_user_id` int NOT NULL,
  PRIMARY KEY (`evaluation_id`),
  KEY `FK_EVALUATI_EVALUATE_USER` (`user_id`),
  KEY `FK_EVALUATI_RESPONSE_USER` (`use_user_id`),
  CONSTRAINT `FK_EVALUATI_EVALUATE_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_EVALUATI_RESPONSE_USER` FOREIGN KEY (`use_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL,
  `user_id` int NOT NULL,
  `feedback_text` text NOT NULL,
  `submitted_at` timestamp NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `FK_FEEDBACK_SEND_USER` (`user_id`),
  CONSTRAINT `FK_FEEDBACK_SEND_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_file`
--

DROP TABLE IF EXISTS `followup_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followup_file` (
  `file_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `visit_id` int NOT NULL,
  `file_location` varchar(100) NOT NULL,
  PRIMARY KEY (`file_id`),
  KEY `FK_FOLLOWUP_ATTACH1_FOLLOWUP` (`visit_id`),
  KEY `FK_FOLLOWUP_ATTACH2_ELDERLYP` (`patient_id`),
  CONSTRAINT `FK_FOLLOWUP_ATTACH1_FOLLOWUP` FOREIGN KEY (`visit_id`) REFERENCES `followup_record` (`visit_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_FOLLOWUP_ATTACH2_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_file`
--

LOCK TABLES `followup_file` WRITE;
/*!40000 ALTER TABLE `followup_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_record`
--

DROP TABLE IF EXISTS `followup_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followup_record` (
  `visit_id` int NOT NULL,
  `visit_plan_id` int NOT NULL,
  `user_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `visit_record` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `FK_FOLLOWUP_FOLLOWED__ELDERLYP` (`patient_id`),
  KEY `FK_FOLLOWUP_FOLLOWUP_USER` (`user_id`),
  KEY `FK_FOLLOWUP_GENERATE2_FOLLOWUP` (`visit_plan_id`),
  CONSTRAINT `FK_FOLLOWUP_FOLLOWED__ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_FOLLOWUP_FOLLOWUP_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_FOLLOWUP_GENERATE2_FOLLOWUP` FOREIGN KEY (`visit_plan_id`) REFERENCES `followupplan` (`visit_plan_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_record`
--

LOCK TABLES `followup_record` WRITE;
/*!40000 ALTER TABLE `followup_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followupplan`
--

DROP TABLE IF EXISTS `followupplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followupplan` (
  `visit_plan_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `user_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`visit_plan_id`),
  KEY `FK_FOLLOWUP_ARRANGE_ELDERLYP` (`patient_id`),
  KEY `FK_FOLLOWUP_RESPONSIB_USER` (`user_id`),
  CONSTRAINT `FK_FOLLOWUP_ARRANGE_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_FOLLOWUP_RESPONSIB_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followupplan`
--

LOCK TABLES `followupplan` WRITE;
/*!40000 ALTER TABLE `followupplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `followupplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) NOT NULL,
  `goods_image` varchar(255) NOT NULL,
  `points` int NOT NULL,
  `des` varchar(255) NOT NULL,
  `number` int NOT NULL,
  `status` enum('已上架','已下架') DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (5,'智能手环','/images/1755592948629.jpg',800,'这款智能手环可以记录日常活动量、监测心率和睡眠质量，并通过简单的界面显示信息',20,'已上架'),(6,'助听器','/images/2025-06/img_2.png',1800,'助听器提供清晰的语音放大功能',1,'已上架'),(7,'无糖健康零食组合','/images/2025-06/img_2.png',200,'包含多种无糖或低糖的健康零食，如坚果、果干和全麦饼干',1,'已上架'),(8,'防滑浴室安全套装','/images/2025-06/img_3.png',250,'包括防滑垫、扶手和淋浴座椅',1,'已上架'),(9,'便携式血压计','/images/2025-06/img_4.png',500,'大屏幕和清晰的数字显示，自动记录和存储多组测量结果',1,'已上架'),(10,'助行器','/images/2025-06/img.png',300,'结构稳固，高度可调，适应不同身高，配有防滑手把和大轮子，便于室内外使用，减少跌倒风险',1,'已上架'),(11,'电子健康秤','/images/2025-06/img_5.png',200,'测量体脂率、肌肉量和水分含量等健康指标。LCD显示屏清晰易读，自动记录和分析健康数据',0,'已上架'),(28,'123456','/images/2025-06/img_5.png',200,'12345',19,'已上架'),(30,'1234','/images/2025-06/img_5.png',12,'1234',20,'已下架'),(31,'1234','/images/2025-06/img_5.png',200,'13245',2,'已下架'),(32,'1234567','/images/2025-08/5e2956dc-ebf8-44eb-9a9c-56e1ced3394a.jpg',200,'1111',1,'已下架'),(33,'1111,111','/images/2025-08/314ead79-c637-4f78-85de-a78da13a0437.jpg',20,'12345',200,'已下架'),(34,'1111,111','/images/2025-08/6e1f7bd5-7c72-44e8-b1b1-200c16e5c70d.jpg',20,'12345',200,'已下架'),(35,'智能手环','/images/d6c98934-db9e-47a4-aede-2677f17d64fe.jpg',800,'智能手环',20,'已上架');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_goal`
--

DROP TABLE IF EXISTS `health_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_goal` (
  `goal_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `goaltype` varchar(30) NOT NULL,
  `goal` varchar(150) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`goal_id`),
  KEY `FK_HEALTH_G_SET1_ELDERLYP` (`patient_id`),
  CONSTRAINT `FK_HEALTH_G_SET1_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_goal`
--

LOCK TABLES `health_goal` WRITE;
/*!40000 ALTER TABLE `health_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_plan`
--

DROP TABLE IF EXISTS `health_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `progress` double NOT NULL,
  `tag` varchar(100) NOT NULL,
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_plan`
--

LOCK TABLES `health_plan` WRITE;
/*!40000 ALTER TABLE `health_plan` DISABLE KEYS */;
INSERT INTO `health_plan` VALUES (1,2,19,'2023-10-10','2025-08-07',100,'体重管理'),(2,3,19,'2023-10-10','2023-10-10',0,'体重管理'),(3,4,19,'2025-08-05','2025-08-05',0,'体重管理'),(4,5,19,'2025-08-05','2025-08-05',0,'体重管理'),(10,6,19,'2025-08-05','2025-08-05',0,'体重管理'),(11,3,19,'2025-08-05','2025-08-05',0,'体重管理'),(12,1,19,'2025-08-05','2025-08-05',8,'体重管理'),(13,3,19,'2025-08-05','2025-08-05',0,'体重管理'),(14,2,19,'2025-08-05','2025-08-05',-1,'体重管理');
/*!40000 ALTER TABLE `health_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_record`
--

DROP TABLE IF EXISTS `health_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_record` (
  `health_record_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `id_number` varchar(18) DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `family_medical_history` text,
  `allergy_history` text,
  `past_medical_history` text,
  `height` float DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `blood_pressure` varchar(10) DEFAULT NULL,
  `heart_rate` int DEFAULT NULL,
  `admission_date` datetime DEFAULT NULL COMMENT '入院时间',
  `discharge_date` datetime DEFAULT NULL COMMENT '出院时间',
  `status` varchar(20) DEFAULT '住院中' COMMENT '状态：住院中/已出院/转院',
  `create_date` datetime DEFAULT NULL COMMENT '记录创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`health_record_id`),
  KEY `fk_health_record_patient_id` (`patient_id`),
  KEY `idx_status` (`status`),
  KEY `idx_admission_date` (`admission_date`),
  KEY `idx_create_date` (`create_date`),
  CONSTRAINT `fk_health_record_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_record`
--

LOCK TABLES `health_record` WRITE;
/*!40000 ALTER TABLE `health_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inviteuser`
--

DROP TABLE IF EXISTS `inviteuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inviteuser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `invite_code` char(6) DEFAULT NULL,
  `connect_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inviteuser`
--

LOCK TABLES `inviteuser` WRITE;
/*!40000 ALTER TABLE `inviteuser` DISABLE KEYS */;
INSERT INTO `inviteuser` VALUES (6,'lili','456','702b9c',1);
/*!40000 ALTER TABLE `inviteuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_record`
--

DROP TABLE IF EXISTS `medical_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_record` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `diagnosis` text,
  `treatment_plan` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_medical_record_patient_id` (`patient_id`),
  CONSTRAINT `fk_medical_record_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicalrecord`
--

DROP TABLE IF EXISTS `medicalrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicalrecord` (
  `record_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `diagnosis` text,
  `treatment_plan` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `FK_MEDICALR_INCLUDE_ELDERLYP` (`patient_id`),
  CONSTRAINT `FK_MEDICALR_INCLUDE_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicalrecord`
--

LOCK TABLES `medicalrecord` WRITE;
/*!40000 ALTER TABLE `medicalrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_inventory`
--

DROP TABLE IF EXISTS `medicine_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine_inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `medicine_id` varchar(36) NOT NULL COMMENT '药品ID',
  `stock` int NOT NULL DEFAULT '0' COMMENT '当前库存',
  `daily_usage` decimal(10,2) DEFAULT '0.00' COMMENT '日均用量',
  `days_remaining` int DEFAULT '999' COMMENT '预计剩余天数',
  `warning_level` varchar(20) DEFAULT 'normal' COMMENT '预警级别: normal, warning, critical',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_medicine_id` (`medicine_id`),
  CONSTRAINT `medicine_inventory_ibfk_1` FOREIGN KEY (`medicine_id`) REFERENCES `medicines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='药品库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_inventory`
--

LOCK TABLES `medicine_inventory` WRITE;
/*!40000 ALTER TABLE `medicine_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicines`
--

DROP TABLE IF EXISTS `medicines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicines` (
  `id` varchar(36) NOT NULL COMMENT '药品ID',
  `code` varchar(20) NOT NULL COMMENT '药品编码',
  `name` varchar(100) NOT NULL COMMENT '药品名称',
  `type` varchar(50) NOT NULL COMMENT '药品类型',
  `unit` varchar(20) NOT NULL COMMENT '单位',
  `min_stock` int NOT NULL DEFAULT '0' COMMENT '最低库存',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-停用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='药品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicines`
--

LOCK TABLES `medicines` WRITE;
/*!40000 ALTER TABLE `medicines` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_management`
--

DROP TABLE IF EXISTS `patient_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_management` (
  `management_id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` int NOT NULL,
  `patient_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`management_id`),
  UNIQUE KEY `unique_doctor_id_patient_id` (`doctor_id`,`patient_id`),
  KEY `fk_patient_management_doctor_id` (`doctor_id`),
  KEY `fk_patient_management_patient_id` (`patient_id`),
  CONSTRAINT `fk_patient_management_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_patient_management_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_management`
--

LOCK TABLES `patient_management` WRITE;
/*!40000 ALTER TABLE `patient_management` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `permission_id` int NOT NULL,
  `permission_name` varchar(50) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_detail`
--

DROP TABLE IF EXISTS `plan_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_detail` (
  `plan_id` int NOT NULL,
  `des` varchar(255) DEFAULT NULL,
  `day` int DEFAULT NULL,
  `week` int DEFAULT NULL,
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `status` int NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `plan_detail_health_plan_plan_id_fk` (`plan_id`),
  CONSTRAINT `plan_detail_health_plan_plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `health_plan` (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_detail`
--

LOCK TABLES `plan_detail` WRITE;
/*!40000 ALTER TABLE `plan_detail` DISABLE KEYS */;
INSERT INTO `plan_detail` VALUES (1,'饮食计划：第一天计划热身动态拉伸（10分钟）。  深蹲 3组×15次。  俯卧撑（跪姿可选）3组×10-12次。  运动计划：哑铃划船 3组×12次/侧。  平板支撑 3组×30秒。  放松：静态拉伸（8分钟）。',1,1,1,0),(1,'饮食计划：第二天计划热身动态拉伸（10分钟）。  深蹲 3组×15次。  俯卧撑（跪姿可选）3组×10-12次。  运动计划：哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',2,1,2,0),(2,'早餐：燕麦粥，午餐：鸡胸肉沙拉，晚餐：蔬菜汤',1,1,3,0),(2,'早餐：全麦面包，午餐：烤鱼，晚餐：水果',2,1,4,0),(10,'早餐： 豆浆一碗（约200毫升） 煮玉米一根 鸡蛋一个 午餐： 烤鱼100克 炒菠菜200克 糙米饭半碗 晚餐： 鸡胸肉沙拉（鸡胸肉50克，生菜、番茄、黄瓜适量） 蒸红薯100克 运动计划： 游泳或水中有氧运动30分钟 放松拉伸15分钟',0,1,8,0),(11,'热身：动态拉伸（10分钟）  深蹲 3组×15次  俯卧撑（跪姿可选）3组×10-12次  哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,1,9,0),(11,'快走/慢跑交替 30分钟（2分钟快走+1分钟慢跑）  开合跳 3组×30秒  跳绳（或模拟动作）3组×1分钟  拉伸放松（10分钟）',0,2,10,0),(11,'热身：跳绳5分钟+动态拉伸  负重深蹲（可用水瓶）4组×12次  标准俯卧撑 4组×8-10次  哑铃肩推 3组×10次  侧平板支撑 2组×30秒/侧  俄罗斯转体 3组×20次',0,3,11,0),(11,'间歇跑 20分钟（1分钟冲刺+2分钟恢复）  登山步 3组×40秒  波比跳 3组×10次  拉伸+泡沫轴放松',0,4,12,0),(12,'饮食计划：热身动态拉伸（10分钟）。  深蹲 3组×15次。  俯卧撑（跪姿可选）3组×10-12次。  运动计划：哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,1,13,1),(12,'饮食计划：热身动态拉伸（10分钟）。  深蹲 3组×15次。  俯卧撑（跪姿可选）3组×10-12次。  运动计划：哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,2,14,0),(12,'饮食计划：热身动态拉伸（10分钟）。  深蹲 3组×15次。  俯卧撑（跪姿可选）3组×10-12次。  运动计划：哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,3,15,0),(12,'间歇跑 20分钟（1分钟冲刺+2分钟恢复）  登山步 3组×40秒  波比跳 3组×10次  拉伸+泡沫轴放松',0,4,16,0),(12,'热身：动态拉伸（10分钟）  深蹲 3组×15次  俯卧撑（跪姿可选）3组×10-12次  哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,5,17,0),(12,'快走/慢跑交替 30分钟（2分钟快走+1分钟慢跑）  开合跳 3组×30秒  跳绳（或模拟动作）3组×1分钟  拉伸放松（10分钟）',0,6,18,0),(12,'热身：跳绳5分钟+动态拉伸  负重深蹲（可用水瓶）4组×12次  标准俯卧撑 4组×8-10次  哑铃肩推 3组×10次  侧平板支撑 2组×30秒/侧  俄罗斯转体 3组×20次',0,7,19,0),(12,'间歇跑 20分钟（1分钟冲刺+2分钟恢复）  登山步 3组×40秒  波比跳 3组×10次  拉伸+泡沫轴放松',0,8,20,0),(12,'热身：动态拉伸（10分钟）  深蹲 3组×15次  俯卧撑（跪姿可选）3组×10-12次  哑铃划船 3组×12次/侧  平板支撑 3组×30秒  放松：静态拉伸（8分钟）',0,9,21,0),(12,'快走/慢跑交替 30分钟（2分钟快走+1分钟慢跑）  开合跳 3组×30秒  跳绳（或模拟动作）3组×1分钟  拉伸放松（10分钟）',0,10,22,0),(12,'热身：跳绳5分钟+动态拉伸  负重深蹲（可用水瓶）4组×12次  标准俯卧撑 4组×8-10次  哑铃肩推 3组×10次  侧平板支撑 2组×30秒/侧  俄罗斯转体 3组×20次',0,11,23,0),(12,'间歇跑 20分钟（1分钟冲刺+2分钟恢复）  登山步 3组×40秒  波比跳 3组×10次  拉伸+泡沫轴放松',0,12,24,0),(13,'resetForm() {   // 重置为初始状态   if (this.deadline === 1) {     this.dailyPlans = [{ description: \'\' }]; // 保留一个空计划   } else {     this.weeklyPlans = [{ description: \'\' }]; // 保留一个空计划   }      // 重置其他相关状态   this.isLoop = false;   this.loopTime = 1; }',1,0,25,0),(14,'resetForm() {   // 重置为初始状态   if (this.deadline === 1) {     this.dailyPlans = [{ description: \'\' }]; // 保留一个空计划   } else {     this.weeklyPlans = [{ description: \'\' }]; // 保留一个空计划   }      // 重置其他相关状态   this.isLoop = false;   this.loopTime = 1; }',1,0,26,0);
/*!40000 ALTER TABLE `plan_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `prescription_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `record_id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `medication_info` text,
  `note` text,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `fk_prescription_patient_id` (`patient_id`),
  KEY `fk_prescription_record_id` (`record_id`),
  CONSTRAINT `fk_prescription_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_prescription_record_id` FOREIGN KEY (`record_id`) REFERENCES `medical_record` (`record_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `questionnaire_id` int NOT NULL,
  `question` varchar(255) NOT NULL,
  `sort_order` int NOT NULL,
  `question_type` enum('文本题','单选题','多选题') DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `question_questionnaires_questionnaire_id_fk` (`questionnaire_id`),
  CONSTRAINT `question_questionnaires_questionnaire_id_fk` FOREIGN KEY (`questionnaire_id`) REFERENCES `questionnaires` (`questionnaire_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (65,2,'您对我们的产品整体满意度如何？',1,'单选题'),(66,2,'您喜欢我们产品的哪些方面？（可多选）',2,'多选题'),(67,2,'您对我们的产品有什么改进建议？',3,'文本题'),(70,3,'333',1,'单选题');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer`
--

DROP TABLE IF EXISTS `question_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer_id` int NOT NULL,
  `question_id` int NOT NULL,
  `option_id` int DEFAULT NULL,
  `text_answer` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question_answer_answer_answer_id_fk` (`answer_id`),
  KEY `question_answer_question_options_option_id_fk` (`option_id`),
  KEY `question_answer_question_question_id_fk` (`question_id`),
  CONSTRAINT `question_answer_answer_answer_id_fk` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`answer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_answer_question_options_option_id_fk` FOREIGN KEY (`option_id`) REFERENCES `question_options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_answer_question_question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer`
--

LOCK TABLES `question_answer` WRITE;
/*!40000 ALTER TABLE `question_answer` DISABLE KEYS */;
INSERT INTO `question_answer` VALUES (43,23,70,173,NULL),(44,24,65,161,NULL),(45,24,66,167,NULL),(46,24,67,NULL,'没什么改进建议123'),(47,25,65,161,NULL),(48,25,66,167,NULL),(49,25,66,166,NULL),(50,25,66,168,NULL),(51,25,67,NULL,'1234567890');
/*!40000 ALTER TABLE `question_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_options`
--

DROP TABLE IF EXISTS `question_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_options` (
  `option_id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`option_id`),
  KEY `question_options_question_question_id_fk` (`question_id`),
  CONSTRAINT `question_options_question_question_id_fk` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_options`
--

LOCK TABLES `question_options` WRITE;
/*!40000 ALTER TABLE `question_options` DISABLE KEYS */;
INSERT INTO `question_options` VALUES (161,'产品质量',65),(162,'价格合理',65),(163,'售后服务',65),(164,'用户界面',65),(165,'产品质量',66),(166,'价格合理',66),(167,'售后服务',66),(168,'用户界面',66),(173,'选项1',70),(174,'选项2',70);
/*!40000 ALTER TABLE `question_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaires`
--

DROP TABLE IF EXISTS `questionnaires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questionnaires` (
  `questionnaire_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `des` varchar(150) NOT NULL,
  `status` enum('草稿','已发布') NOT NULL,
  `admin_id` int NOT NULL COMMENT '需要关联管理员表',
  `create_date` date NOT NULL,
  `update_date` date NOT NULL,
  PRIMARY KEY (`questionnaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaires`
--

LOCK TABLES `questionnaires` WRITE;
/*!40000 ALTER TABLE `questionnaires` DISABLE KEYS */;
INSERT INTO `questionnaires` VALUES (2,'用户对于产品的满意度查询','了解客户对我们产品和服务的满意度情况','已发布',1,'2025-08-21','2025-08-18'),(3,'对用户满意度的第二次调查','调查满意度','已发布',1,'2025-08-15','2025-08-16');
/*!40000 ALTER TABLE `questionnaires` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raffle`
--

DROP TABLE IF EXISTS `raffle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raffle` (
  `raffle_id` int NOT NULL AUTO_INCREMENT,
  `raffle_name` varchar(250) DEFAULT NULL,
  `spend_points` int DEFAULT NULL,
  `round_id` int DEFAULT NULL,
  `winning_prob` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `round_name` varchar(20) NOT NULL,
  `is_need_address` int NOT NULL,
  PRIMARY KEY (`raffle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raffle`
--

LOCK TABLES `raffle` WRITE;
/*!40000 ALTER TABLE `raffle` DISABLE KEYS */;
INSERT INTO `raffle` VALUES (9,'200积分',30,1,5,'/images/1755585046857.jpg?t=1755585046861?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',0),(10,'空气净化器',30,1,15,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',1),(11,'300积分',30,1,10,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',0),(12,'谢谢惠顾',30,1,10,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',0),(13,'谢谢惠顾',30,1,10,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',0),(14,'肩颈按摩仪',30,1,20,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',1),(15,'50积分',30,1,5,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',0),(16,'健康食谱礼包',30,1,25,'/images/2025-06/img_4.png?t=1755584589307?t=1755584974245?t=1755585719265?t=1755594211893?t=1755675281925?t=1755676018545','双十一抽奖',1),(17,'华为运动手环',50,2,10,'/images/1755594668383.jpg?t=1755594668392?t=1755675508015?t=1755677089778?t=1755677151179?t=1755677682233?t=1755677854107','双十二抽奖',1),(18,'20积分',50,2,5,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(19,'300积分',50,2,5,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(20,'谢谢惠顾',50,2,10,'/images/1755594413736.jpg?t=1755594413743?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(21,'谢谢惠顾',50,2,20,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(22,'肩颈按摩仪',50,2,35,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',1),(23,'10积分',50,2,5,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(24,'60积分',50,2,10,'/images/2025-06/img_4.png?t=1755589599204?t=1755594328012?t=1755594373596?t=1755594416193?t=1755594650803?t=1755675508015?t=1755677089778?t=1755677854107','双十二抽奖',0),(57,'1',10,3,10,'/images/1755675925298.jpg?t=1755675925306?t=1755677127915?t=1755677988507','测试轮盘3',0),(58,'2',10,3,30,'/images/1755675934425.jpg?t=1755675934437?t=1755677127915?t=1755677988507','测试轮盘3',1),(59,'3',10,3,10,'/images/1755675941962.jpg?t=1755675941965?t=1755677127915?t=1755677988507','测试轮盘3',0),(60,'4',10,3,10,'/images/1755675950818.jpg?t=1755675950831?t=1755677127915?t=1755677988507','测试轮盘3',1),(61,'5',10,3,10,'/images/1755675959269.jpg?t=1755675959275?t=1755677127915?t=1755677988507','测试轮盘3',1),(62,'6',10,3,10,'/images/1755675966812.jpg?t=1755675966816?t=1755677127915?t=1755677988507','测试轮盘3',0),(63,'7',10,3,10,'/images/1755675975388.jpg?t=1755675975402?t=1755677127915?t=1755677988507','测试轮盘3',0),(64,'8',10,3,10,'/images/1755675985778.jpg?t=1755675985784?t=1755677127915?t=1755677988507','测试轮盘3',0);
/*!40000 ALTER TABLE `raffle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` int NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_ROLE_PER_PERMIT2_PERMISSI` (`permission_id`),
  CONSTRAINT `FK_ROLE_PER_PERMIT2_PERMISSI` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROLE_PER_PERMIT_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_comment`
--

DROP TABLE IF EXISTS `service_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_comment` (
  `scomment_id` int NOT NULL AUTO_INCREMENT,
  `service_score` int DEFAULT NULL,
  `response_score` int DEFAULT NULL,
  `completeness_score` int DEFAULT NULL,
  `communication_score` int DEFAULT NULL,
  `privacy_score` int DEFAULT NULL,
  `service_label` varchar(50) DEFAULT NULL,
  `service_comment` varchar(255) DEFAULT NULL,
  `sender_id` int DEFAULT NULL,
  `admin_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `service_average` double DEFAULT NULL,
  PRIMARY KEY (`scomment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_comment`
--

LOCK TABLES `service_comment` WRITE;
/*!40000 ALTER TABLE `service_comment` DISABLE KEYS */;
INSERT INTO `service_comment` VALUES (1,5,5,5,5,5,'回复速度快','医生专业性很强，服务态度很好',19,1,'2025-07-16',5),(2,3,4,4,4,4,'','1234567890',19,1,'2025-07-16',3.8),(3,1,2,2,3,3,'操作繁琐','一般般，不推荐，回复的特别慢',19,1,'2025-07-16',2.2),(4,4,2,3,3,4,'专业可靠,耐心细致','咨询服务相应速度很快，可以快速给用户回复',1,1,'2025-07-18',3.2),(5,5,5,4,3,5,'专业可靠','1234567890',1,1,'2025-08-13',4.4),(6,4,3,3,5,2,'术语过多,耐心细致','1234567890',19,1,'2025-08-13',3.4);
/*!40000 ALTER TABLE `service_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stored_file`
--

DROP TABLE IF EXISTS `stored_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stored_file` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `session_id` varchar(20) DEFAULT NULL,
  `user_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `file_url` varchar(250) DEFAULT NULL,
  `file_name` varchar(250) DEFAULT NULL,
  `storage_path` varchar(255) DEFAULT NULL,
  `file_type` varchar(100) DEFAULT NULL,
  `size` mediumtext,
  `upload_time` date DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stored_file`
--

LOCK TABLES `stored_file` WRITE;
/*!40000 ALTER TABLE `stored_file` DISABLE KEYS */;
INSERT INTO `stored_file` VALUES (1,'1',1,0,NULL,'36ef1a90c80ec2793a6e3b5dc14e86ab.jpg',NULL,'image/jpeg','357020','2025-07-22'),(2,'1',1,0,NULL,'企业实习中期检查表.docx',NULL,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','468323','2025-07-22'),(3,'1',1,0,NULL,'企业实习中期检查表.docx',NULL,'application/vnd.openxmlformats-officedocument.wordprocessingml.document','468323','2025-07-22'),(4,'1',1,0,'C:/files/7573_企业实习中期检查表.docx','企业实习中期检查表.docx','C:\\uploads\\7573_企业实习中期检查表.docx','application/vnd.openxmlformats-officedocument.wordprocessingml.document','468323','2025-07-22'),(5,'a',1,19,'C:/files/3205_c09ddd3a82322f0a6c90f857e33685f2.jpg','c09ddd3a82322f0a6c90f857e33685f2.jpg','C:\\uploads\\3205_c09ddd3a82322f0a6c90f857e33685f2.jpg','image/jpeg','423258','2025-07-23'),(6,'a',1,19,'C:/files/8692_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','C:\\uploads\\8692_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','image/jpeg','357020','2025-07-23'),(7,'a',1,19,'C:/files/4699_c09ddd3a82322f0a6c90f857e33685f2.jpg','c09ddd3a82322f0a6c90f857e33685f2.jpg','C:\\uploads\\4699_c09ddd3a82322f0a6c90f857e33685f2.jpg','image/jpeg','423258','2025-07-23'),(8,'a',1,19,'C:/files/5671_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','C:\\uploads\\5671_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','image/jpeg','357020','2025-07-23'),(9,'a',1,19,'C:/files/5235_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','C:\\uploads\\5235_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-23'),(10,'a',1,19,'C:/files/8876_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','C:\\uploads\\8876_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','image/jpeg','357020','2025-07-23'),(11,'a',1,19,'C:/files/8629_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','C:\\uploads\\8629_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-23'),(12,'a',1,19,'C:/files/7931_c09ddd3a82322f0a6c90f857e33685f2.jpg','c09ddd3a82322f0a6c90f857e33685f2.jpg','C:\\uploads\\7931_c09ddd3a82322f0a6c90f857e33685f2.jpg','image/jpeg','423258','2025-07-23'),(13,'a',1,19,'C:/files/7842_c09ddd3a82322f0a6c90f857e33685f2.jpg','c09ddd3a82322f0a6c90f857e33685f2.jpg','C:\\uploads\\7842_c09ddd3a82322f0a6c90f857e33685f2.jpg','image/jpeg','423258','2025-07-23'),(14,'a',1,19,'C:/files/2802_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','C:\\uploads\\2802_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-23'),(15,'a',1,19,'C:/files/1718_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','C:\\uploads\\1718_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(16,'a',1,19,'C:/files/7396_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','C:\\uploads\\7396_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(17,'a',1,19,'C:/files/5450_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','C:\\uploads\\5450_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(18,'a',1,19,'/upload/9560_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','C:\\uploads\\9560_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(19,'a',1,19,'http://localhost:8080/upload/8630_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','\\upload\\8630_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(20,'a',1,19,'http://localhost:8080/upload/8351_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','\\upload\\8351_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-24'),(21,'a',1,19,'http://localhost:8080/upload/3461_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','\\upload\\3461_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(22,'a',1,19,'/upload/1861_7c220eb3b3b938efc26ba7c85a3a138b.jpg','7c220eb3b3b938efc26ba7c85a3a138b.jpg','src\\main\\resources\\static\\upload\\1861_7c220eb3b3b938efc26ba7c85a3a138b.jpg','image/jpeg','366685','2025-07-24'),(23,'a',1,19,'http://localhost:8080/upload/6950_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','src\\main\\resources\\static\\upload\\6950_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','image/jpeg','357020','2025-07-24'),(24,'a',1,19,'http://localhost:8080/upload/8123_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\8123_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(25,'a',1,19,'/upload/1537_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','src\\main\\resources\\static\\upload\\1537_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-24'),(26,'a',1,19,'/upload/5048_d4ea3ec86181c6d005b39a65a0517012.jpg','d4ea3ec86181c6d005b39a65a0517012.jpg','src\\main\\resources\\static\\upload\\5048_d4ea3ec86181c6d005b39a65a0517012.jpg','image/jpeg','176052','2025-07-24'),(27,'a',1,19,'/upload/3412_思想汇报.docx','思想汇报.docx','src\\main\\resources\\static\\upload\\3412_思想汇报.docx','application/vnd.openxmlformats-officedocument.wordprocessingml.document','22134','2025-07-24'),(28,'a',1,19,'/upload/4016_企业实习中期检查表.pdf','企业实习中期检查表.pdf','src\\main\\resources\\static\\upload\\4016_企业实习中期检查表.pdf','application/pdf','436224','2025-07-24'),(29,'a',1,19,'/upload/2283_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\2283_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(30,'a',1,19,'/upload/9329_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','src\\main\\resources\\static\\upload\\9329_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(31,'a',1,19,'/upload/4319_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','src\\main\\resources\\static\\upload\\4319_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-07-24'),(32,'a',1,19,'/upload/7054_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\7054_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(33,'a',1,19,'/upload/3513_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\3513_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(34,'a',1,19,'/upload/3832_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\3832_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(35,'a',1,19,'/upload/4074_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\4074_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(36,'a',1,19,'/upload/4135_8148c864c1212a9e1371ae0d7ce76fb9.jpg','8148c864c1212a9e1371ae0d7ce76fb9.jpg','src\\main\\resources\\static\\upload\\4135_8148c864c1212a9e1371ae0d7ce76fb9.jpg','image/jpeg','676071','2025-07-24'),(37,'a',1,19,'/upload/1786_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\1786_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(38,'a',1,19,'/upload/2856_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\2856_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(39,'a',1,19,'/upload/6766_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\6766_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-07-24'),(40,'a',1,19,'/upload/9478_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\9478_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13'),(41,'a',1,19,'/upload/9411_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\9411_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13'),(42,'a',1,19,'/upload/2745_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\2745_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13'),(43,'a',1,19,'/upload/6061_运动手环.jpg','运动手环.jpg','src\\main\\resources\\static\\upload\\6061_运动手环.jpg','image/jpeg','96230','2025-08-13'),(44,'a',1,19,'/upload/9223_助听器.png!ww7006','助听器.png!ww7006','src\\main\\resources\\static\\upload\\9223_助听器.png!ww7006','application/octet-stream','231977','2025-08-13'),(45,'a',1,19,'/upload/3010_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\3010_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13'),(46,'a',1,19,'/upload/9123_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','src\\main\\resources\\static\\upload\\9123_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-08-13'),(47,'a',1,19,'/upload/5106_7c220eb3b3b938efc26ba7c85a3a138b.jpg','7c220eb3b3b938efc26ba7c85a3a138b.jpg','src\\main\\resources\\static\\upload\\5106_7c220eb3b3b938efc26ba7c85a3a138b.jpg','image/jpeg','366685','2025-08-13'),(48,'a',1,19,'/upload/4018_助听器.png!ww7006','助听器.png!ww7006','src\\main\\resources\\static\\upload\\4018_助听器.png!ww7006','application/octet-stream','231977','2025-08-13'),(49,'a',1,19,'/upload/3108_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\3108_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13'),(50,'a',1,19,'/upload/7220_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','src\\main\\resources\\static\\upload\\7220_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-08-13'),(51,'a',1,19,'/upload/1802_7c220eb3b3b938efc26ba7c85a3a138b.jpg','7c220eb3b3b938efc26ba7c85a3a138b.jpg','src\\main\\resources\\static\\upload\\1802_7c220eb3b3b938efc26ba7c85a3a138b.jpg','image/jpeg','366685','2025-08-13'),(52,'a',1,19,'/upload/8596_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','src\\main\\resources\\static\\upload\\8596_36ef1a90c80ec2793a6e3b5dc14e86ab.jpg','image/jpeg','357020','2025-08-13'),(53,'a',1,19,'/upload/1774_552ac005f5e7007f05988d35af647f74.jpg','552ac005f5e7007f05988d35af647f74.jpg','src\\main\\resources\\static\\upload\\1774_552ac005f5e7007f05988d35af647f74.jpg','image/jpeg','287736','2025-08-13'),(54,'a',1,19,'/upload/7355_7c220eb3b3b938efc26ba7c85a3a138b.jpg','7c220eb3b3b938efc26ba7c85a3a138b.jpg','src\\main\\resources\\static\\upload\\7355_7c220eb3b3b938efc26ba7c85a3a138b.jpg','image/jpeg','366685','2025-08-13'),(55,'a',1,19,'/upload/8701_3849d7ed21fae830617c22d763599a9f.jpg','3849d7ed21fae830617c22d763599a9f.jpg','src\\main\\resources\\static\\upload\\8701_3849d7ed21fae830617c22d763599a9f.jpg','image/jpeg','637445','2025-08-13');
/*!40000 ALTER TABLE `stored_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `age` int DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `password_hash` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `userpoints` int DEFAULT NULL,
  `invite_code` char(6) DEFAULT NULL,
  `invite_sum` int DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'lisi',28,'女','123456','12345','13109951157','李四','1234567','中国','河南',NULL,170,'160011',0,NULL),(2,'lili',0,NULL,'12345678',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'李医生',22,'男','123456','12345','12345678910','李四','12345','中国','河南','外科',1440,'49e950',0,'上海市');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_content` varchar(100) NOT NULL,
  `user_address` varchar(255) NOT NULL,
  `user_phone` char(11) NOT NULL,
  `order_status` enum('未发货','已发货','已取消','已完成') NOT NULL,
  `order_date` date NOT NULL,
  `order_sum` int DEFAULT NULL,
  `order_source` enum('积分兑换','积分抽奖') NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_order_user_user_id_fk` (`user_id`),
  CONSTRAINT `user_order_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (1,1,'水果','上海奉贤区','13109952231','已取消','2025-08-10',20,'积分兑换'),(2,1,'水果','上海奉贤区','13109952231','已取消','2025-08-10',20,'积分兑换'),(3,1,'空气净化器','22255','12345678910','已取消','2025-08-12',300,'积分兑换'),(4,1,'助行器','浙江省','13109951157','已取消','2025-08-12',100,'积分兑换'),(5,1,'电子健康秤','22255','13109951157','已完成','2025-08-12',40,'积分兑换'),(6,19,'空气净化器','12345','12345678910','已发货','2025-08-12',506,'积分兑换'),(7,19,'123456','12345','12345678910','已取消','2025-08-18',800,'积分兑换'),(8,19,'肩颈按摩仪','12345','12345678910','已完成','2025-08-19',30,'积分兑换'),(9,19,'健康食谱礼包','12345','12345678910','已取消','2025-08-19',1,'积分兑换'),(10,19,'电子健康秤','12345','12345678910','已发货','2025-08-19',200,'积分兑换'),(11,19,'健康食谱礼包','12345','12345678910','已发货','2025-08-20',0,'积分抽奖'),(12,19,'健康食谱礼包','12345','12345678910','已完成','2025-08-20',0,'积分抽奖'),(13,19,'健康食谱礼包','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(14,19,'5','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(15,19,'5','12345','12345678910','已发货','2025-08-20',0,'积分抽奖'),(16,19,'肩颈按摩仪','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(17,19,'华为运动手环','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(18,19,'华为运动手环','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(19,19,'肩颈按摩仪','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(20,19,'2','12345','12345678910','未发货','2025-08-20',0,'积分抽奖'),(21,19,'肩颈按摩仪','12345','12345678910','未发货','2025-08-20',0,'积分抽奖');
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_USER_ROL_ASSIGN2_ROLE` (`role_id`),
  CONSTRAINT `FK_USER_ROL_ASSIGN2_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_USER_ROL_ASSIGN_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight_goal`
--

DROP TABLE IF EXISTS `weight_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weight_goal` (
  `weight_id` int NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `gender` char(1) NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `goal_weight` double NOT NULL,
  `complete_month` int NOT NULL,
  `user_id` int NOT NULL,
  `doctor_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`weight_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_goal`
--

LOCK TABLES `weight_goal` WRITE;
/*!40000 ALTER TABLE `weight_goal` DISABLE KEYS */;
INSERT INTO `weight_goal` VALUES (1,19,'女',160,47,48,1,1,19,'2025-07-30',0),(2,18,'女',160,47,49,1,2,1,'2025-08-08',0),(3,22,'女',160,47,50,1,3,19,'2023-01-01',0),(4,45,'男',176,65,60,5,4,19,'2020-01-20',0);
/*!40000 ALTER TABLE `weight_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'shec_psims'
--

--
-- Dumping routines for database 'shec_psims'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-20 19:37:22
