-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: shec_psims
-- ------------------------------------------------------
-- Server version	8.0.37

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

--
-- Dumping data for table `alert_condition`
--

LOCK TABLES `alert_condition` WRITE;
/*!40000 ALTER TABLE `alert_condition` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `alert_record`
--

LOCK TABLES `alert_record` WRITE;
/*!40000 ALTER TABLE `alert_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `alert_set`
--

LOCK TABLES `alert_set` WRITE;
/*!40000 ALTER TABLE `alert_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `audit_log`
--

LOCK TABLES `audit_log` WRITE;
/*!40000 ALTER TABLE `audit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `device_usage`
--

LOCK TABLES `device_usage` WRITE;
/*!40000 ALTER TABLE `device_usage` DISABLE KEYS */;
/*!40000 ALTER TABLE `device_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'75580','N-invas est c ffr sw aly cta','监测','正常','药房','2024-03-13'),(2,'92520','Laryngeal function studies','康复','停用','外科手术室','2024-03-10'),(3,'58346','Insert heyman uteri capsule','监测','维修','影像科','2023-07-11'),(4,'0043U','Tbrg b grp antb 4 prtn igm','康复','停用','影像科','2024-06-25'),(5,'0315U','Onc cutan sq cll ca mrna 40','康复','维修','ICU','2024-08-27'),(6,'G6012','Radiation treatment delivery','治疗','维修','影像科','2025-01-03'),(7,'70450','Ct head/brain w/o dye','诊断','维修','影像科','2024-08-17'),(8,'73225','Mr angio upr extr w/o&w/dye','监测','停用','ICU','2025-01-02'),(9,'0408U','Iaad blk ac wv bsnsr sarscv2','监测','正常','检验科','2024-09-07'),(10,'Q0112','Potassium hydroxide preps','监测','维修','内科病房','2023-10-25'),(11,'75565','Card MRI veloc flow mapping','治疗','维修','检验科','2023-11-20'),(12,'0514U','Gi ibd ia quan deter adl lvl','康复','维修','ICU','2024-11-24'),(13,'Q0113','Pinworm examinations','治疗','正常','急诊科','2023-05-24'),(14,'0341U','Ftl aneup dna seq cmpr alys','康复','维修','内科病房','2024-04-26'),(15,'0865T','Quan mri alys brn w/o dx mri','康复','维修','检验科','2024-10-10'),(16,'0223U','Nfct ds 22 trgt sars-cov-2 ','康复','停用','急诊科','2025-05-15'),(17,'0260U','Rare ds id opt genome mapg','康复','正常','急诊科','2024-06-24'),(18,'97610','Low frequency non-thermal US','诊断','正常','急诊科','2024-07-05'),(19,'78099','Endocrine nuclear procedure','诊断','停用','药房','2023-08-11'),(20,'19298','Place breast rad tube/caths','诊断','正常','ICU','2024-03-20'),(21,'73201','Ct upper extremity w/dye','监测','维修','检验科','2025-02-20'),(22,'0239U','Trgt gen seq alys pnl 311+','康复','停用','外科手术室','2024-05-22'),(23,'70547','Mr angiography neck w/o dye','康复','维修','中心供应室','2024-02-01'),(24,'0470U','Onc orop detcj mrd 8 dna hpv','监测','维修','影像科','2023-12-15'),(25,'78104','Bone marrow imaging body','康复','维修','药房','2024-12-13'),(26,'0038U ','Vitamin d srm microsamp quan','监测','停用','ICU','2024-02-29'),(27,'0021U','Onc prst8 detcj 8 autoantb','治疗','正常','外科手术室','2025-03-08'),(28,'97610','Low frequency non-thermal US','康复','正常','中心供应室','2023-09-11'),(29,'0177U','Onc brst ca dna pik3ca 11','诊断','维修','影像科','2023-09-23'),(30,'74250','X-ray xm sm int 1cntrst std','诊断','停用','急诊科','2024-05-18'),(31,'0413U','Onc hl neo opt gen mapg dna','诊断','维修','药房','2024-10-30'),(32,'Q0114','Fern test','监测','维修','影像科','2023-07-21'),(33,'98977','Rem ther mntr dv sply mscskl','治疗','正常','影像科','2025-03-17'),(34,'0633T','Ct breast w/3d uni c-','监测','维修','急诊科','2025-02-18'),(35,'G0339','Robot lin-radsurg com, first','治疗','正常','检验科','2025-03-06'),(36,'G0563','Sbrt w/positron emission del','康复','维修','检验科','2024-12-08'),(37,'77073','X-rays bone length studies','治疗','停用','药房','2024-03-05'),(38,'0174U','Onc solid tumor 30 prtn trgt','监测','正常','ICU','2024-03-29'),(39,'A9526','Nitrogen N-13 ammonia','监测','维修','检验科','2023-05-21'),(40,'93980','Penile vascular study','治疗','停用','中心供应室','2024-04-05'),(41,'72202','X-ray exam sacroiliac joints','治疗','正常','药房','2024-12-20'),(42,'78730','Urinary bladder retention','诊断','维修','ICU','2023-09-20'),(43,'G0475 ','Hiv combination assay ','治疗','正常','中心供应室','2023-05-17'),(44,'G6005','Radiation treatment delivery','康复','维修','外科手术室','2025-01-20'),(45,'0038U ','Vitamin d srm microsamp quan','诊断','停用','检验科','2025-01-27'),(46,'A9506','Tc-99m graphite crucible','治疗','正常','影像科','2024-02-07'),(47,'73521','X-ray exam hips bi 2 views','治疗','停用','检验科','2023-10-17'),(48,'0859T','Ncntc ifr spctrsc o/t pad ea','监测','维修','急诊科','2023-12-30'),(49,'0900T','N-invas est aqmbf asstv cmr','治疗','停用','内科病房','2023-07-25'),(50,'Q9982','Flutemetamol f18 diagnostic','诊断','维修','ICU','2023-09-29'),(51,'G6007 ','Radiation treatment delivery','治疗','正常','中心供应室','2023-09-14'),(52,'Q0113','Pinworm examinations','治疗','正常','中心供应室','2024-08-31'),(53,'78610','Brain flow imaging only','监测','维修','中心供应室','2023-10-17'),(54,'0486U','Onc pan sol tum ngs cfctdna','诊断','正常','内科病房','2024-11-10'),(55,'77620','Hyperthermia treatment','治疗','维修','内科病房','2024-03-07'),(56,'70487','Ct maxillofacial w/dye','监测','正常','检验科','2024-12-31'),(57,'19296','Place po breast cath for rad','监测','停用','影像科','2025-03-28'),(58,'77072','X-rays for bone age','监测','停用','中心供应室','2024-05-04'),(59,'72131','Ct lumbar spine w/o dye','监测','维修','影像科','2025-04-05'),(60,'78492','Myocrd img pet mlt rst&strs','监测','正常','影像科','2024-08-24'),(61,'78499','Cardiovascular nuclear exam','监测','停用','急诊科','2024-02-08'),(62,'A9608','Flotufolastat f18 diag 1 mci','治疗','停用','急诊科','2024-11-22'),(63,'0516U','Rx metab rxgenomic gnotyp 40','诊断','停用','药房','2025-04-09'),(64,'C1719','Brachytx, NS, Non-HDRIr-192','监测','停用','急诊科','2024-05-16'),(65,'78099','Endocrine nuclear procedure','康复','正常','急诊科','2025-02-01'),(66,'0395U','Onc lng multiomics plsm alg','治疗','维修','急诊科','2023-11-09'),(67,'A9590','Iodine i-131 iobenguane 1mci','监测','正常','检验科','2025-01-03');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `doctor_patient`
--

LOCK TABLES `doctor_patient` WRITE;
/*!40000 ALTER TABLE `doctor_patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `elderlyprofile`
--

LOCK TABLES `elderlyprofile` WRITE;
/*!40000 ALTER TABLE `elderlyprofile` DISABLE KEYS */;
INSERT INTO `elderlyprofile` VALUES (1,'Allix Adanet','Female',29,'2024-06-09','53-485-4564','584-137-6438',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Allissa MacArdle','Female',23,'2024-08-16','25-398-6234','815-800-9818',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Burk Mathison','Male',73,'2024-07-31','58-202-6430','909-613-1871',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'Suellen Issitt','Female',1,'2025-05-06','00-134-4096','406-446-0824',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Scarlett Foulsham','Female',48,'2024-11-29','41-414-3496','705-309-1783',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Markos Russam','Male',17,'2025-01-23','26-670-2233','320-495-5823',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Merrick Arthur','Male',41,'2025-04-26','69-474-2238','261-432-6225',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'Jordon Coye','Male',10,'2025-04-15','86-917-5920','216-939-6235',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'Cinderella Sterrick','Female',26,'2024-09-26','58-859-1443','195-387-4690',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'Sianna Cammell','Female',39,'2024-09-05','95-853-7601','168-411-4703',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'Ira Biasetti','Male',83,'2025-03-08','33-811-0039','203-881-0927',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'Arlyne Woodyear','Female',20,'2024-10-23','46-009-2483','570-137-5449',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'Lizette De Normanville','Female',59,'2024-07-11','87-755-1048','484-319-1692',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'Gusta Unger','Female',36,'2024-12-12','40-465-7338','667-694-9809',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'Clair Pellett','Female',34,'2025-02-18','54-679-8325','948-718-3690',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'Halli Rijkeseis','Female',66,'2024-08-05','83-958-9202','290-503-7429',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'Noach Yurchenko','Male',31,'2024-08-29','39-379-7460','232-412-1991',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'Gawain Reyburn','Male',47,'2024-10-07','95-238-0459','211-906-3937',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'Amby Branthwaite','Male',58,'2025-04-04','87-396-4829','685-141-8105',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'Faydra Maris','Female',2,'2025-04-09','72-401-4304','341-374-9026',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'Mireille Cona','Agender',53,'2024-09-08','68-106-8526','586-174-8387',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'Crosby Chessman','Male',50,'2024-08-20','19-812-2549','107-555-8093',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'Tamarra Eble','Female',6,'2024-11-02','80-352-2062','230-626-6045',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'Lorita Huffy','Female',14,'2024-09-12','91-858-7754','916-682-0604',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'Sunshine Fattori','Female',79,'2024-06-17','82-422-7591','610-367-1554',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'Mayor Proffitt','Male',26,'2024-10-16','19-995-5757','924-333-1343',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'Clayton Scutcheon','Male',80,'2024-09-30','49-043-6770','787-467-3391',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'Collete Thurske','Female',74,'2024-11-16','58-177-1179','875-836-3320',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'Lyndell Sauniere','Female',31,'2024-08-22','53-630-4668','464-988-0633',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `elderlyprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `emergency_contact`
--

LOCK TABLES `emergency_contact` WRITE;
/*!40000 ALTER TABLE `emergency_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `followup_file`
--

LOCK TABLES `followup_file` WRITE;
/*!40000 ALTER TABLE `followup_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `followup_record`
--

LOCK TABLES `followup_record` WRITE;
/*!40000 ALTER TABLE `followup_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `followupplan`
--

LOCK TABLES `followupplan` WRITE;
/*!40000 ALTER TABLE `followupplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `followupplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (3,'饮料','http://localhost:8080/admin/goods?fileUrl=2025-05/b00b9d4e-81f4-4c80-8efb-846afb6e4ac9.jpg',5),(5,'水果','http://localhost:8080/admin/goods/static?fileUrl=2025-05/14d800ba-a01d-49a9-9399-ffc23720eafd.jpg',10),(6,'运动手环','http://localhost:8080/admin/goods/static?fileUrl=2025-05/7840b525-f8f9-4de0-9f8e-27d060ebfb86.jpg',1200);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_goal`
--

LOCK TABLES `health_goal` WRITE;
/*!40000 ALTER TABLE `health_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_plan`
--

LOCK TABLES `health_plan` WRITE;
/*!40000 ALTER TABLE `health_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_record`
--

LOCK TABLES `health_record` WRITE;
/*!40000 ALTER TABLE `health_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inviteuser`
--

LOCK TABLES `inviteuser` WRITE;
/*!40000 ALTER TABLE `inviteuser` DISABLE KEYS */;
INSERT INTO `inviteuser` VALUES (6,'lili','456','702b9c',1);
/*!40000 ALTER TABLE `inviteuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicalrecord`
--

LOCK TABLES `medicalrecord` WRITE;
/*!40000 ALTER TABLE `medicalrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicalrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicine_inventory`
--

LOCK TABLES `medicine_inventory` WRITE;
/*!40000 ALTER TABLE `medicine_inventory` DISABLE KEYS */;
INSERT INTO `medicine_inventory` VALUES (1,'m001',120,5.00,24,'normal','2025-05-23 08:11:26'),(2,'m002',50,8.00,6,'warning','2025-05-23 08:11:26'),(3,'m003',15,5.00,3,'critical','2025-05-23 08:11:26'),(4,'m004',35,2.00,17,'normal','2025-05-23 08:11:26'),(5,'m005',10,1.50,6,'warning','2025-05-23 08:11:26'),(6,'m006',45,2.00,22,'normal','2025-05-26 14:45:12'),(7,'m007',12,1.50,8,'warning','2025-05-26 14:45:12'),(8,'m008',50,3.00,16,'normal','2025-05-26 14:45:12'),(9,'m009',5,1.00,5,'critical','2025-05-26 14:45:12'),(10,'m010',25,2.50,10,'normal','2025-05-26 14:45:12'),(11,'m011',10,2.00,5,'critical','2025-05-26 14:45:12'),(12,'m012',25,1.00,25,'normal','2025-05-26 14:45:12'),(13,'m013',60,3.00,20,'normal','2025-05-26 14:45:12'),(14,'m014',18,1.20,15,'warning','2025-05-26 14:45:12'),(15,'m015',120,5.00,24,'normal','2025-05-26 14:45:12');
/*!40000 ALTER TABLE `medicine_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicines`
--

LOCK TABLES `medicines` WRITE;
/*!40000 ALTER TABLE `medicines` DISABLE KEYS */;
INSERT INTO `medicines` VALUES ('m001','FFGMY001','复方感冒药','感冒类','瓶',50,1,'2025-05-23 08:11:26','2025-05-23 08:11:26'),('m002','AMXLJN002','阿莫西林胶囊','抗生素类','盒',100,1,'2025-05-23 08:11:26','2025-05-23 08:11:26'),('m003','TBKLJN003','头孢克洛胶囊','抗生素类','盒',30,1,'2025-05-23 08:11:26','2025-05-23 08:11:26'),('m004','FBDMS004','氟比洛芬凝胶贴膏','外用药品','袋',20,1,'2025-05-23 08:11:26','2025-05-23 08:11:26'),('m005','JKYS005','健康一生牌钙片','保健品','瓶',15,1,'2025-05-23 08:11:26','2025-05-23 08:11:26'),('m006','XYJN006','硝苯地平缓释片','慢性病类','瓶',30,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m007','AJJY007','氨茶碱片','呼吸系统类','瓶',25,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m008','ETYY008','儿童退热贴','儿童用药类','袋',40,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m009','JJYY009','救心丸','急救类','盒',10,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m010','WCPJ010','维生素C泡腾片','保健品','瓶',20,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m011','KBYLJN011','奥司他韦胶囊','抗病毒类','盒',15,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m012','ZTYY012','布洛芬缓释胶囊','止痛类','瓶',20,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m013','PFYY013','皮炎平软膏','皮肤科用药','支',50,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m014','XYJY014','消炎解毒丸','中药类','袋',30,1,'2025-05-26 14:45:12','2025-05-26 14:45:12'),('m015','JRSWJ015','酒精棉片','外用消毒类','包',100,1,'2025-05-26 14:45:12','2025-05-26 14:45:12');
/*!40000 ALTER TABLE `medicines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'图表比较简陋，建议优化','针对界面设计，您有什么建议吗',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `raffle`
--

LOCK TABLES `raffle` WRITE;
/*!40000 ALTER TABLE `raffle` DISABLE KEYS */;
INSERT INTO `raffle` VALUES (9,'运动手环',150,1,0.09),(10,'运动手环',150,1,0.11),(11,'运动手环',150,1,0.05),(12,'谢谢惠顾',150,1,0.3),(13,'谢谢惠顾',150,1,0.3),(14,'300积分',150,1,0.05),(15,'10积分',150,1,0.05),(16,'50积分',150,1,0.05);
/*!40000 ALTER TABLE `raffle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'lisi',22,'男','12345678','123456','11122223333','李四','Street2',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'zhaozhao',22,'男','123456','12345','123','张三','Street2','中国','北京','外科',0,NULL,0,'北京市'),(23,'lili',0,NULL,'456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-28 10:36:16
