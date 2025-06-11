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
INSERT INTO `alert_condition` VALUES (1,1,'收缩压','>',160,NULL,NULL),(2,1,'舒张压','>',100,NULL,NULL),(3,2,'空腹血糖','>',7,NULL,NULL),(4,2,'餐后2小时血糖','>',11,NULL,NULL),(5,3,'简易智能状态检查评分','<',24,NULL,NULL);
/*!40000 ALTER TABLE `alert_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `alert_record`
--

LOCK TABLES `alert_record` WRITE;
/*!40000 ALTER TABLE `alert_record` DISABLE KEYS */;
INSERT INTO `alert_record` VALUES (1,7,'2025-05-20','08:15:00','高血压','慢性病',2,'血压测量165/102mmHg，高于警戒值，建议立即服用降压药'),(2,8,'2025-05-21','14:30:00','糖尿病','慢性病',3,'餐后2小时血糖13.2mmol/L，超出警戒值，需调整胰岛素剂量'),(3,9,'2025-05-22','10:45:00','认知障碍','神经系统疾病',2,'简易智能状态检查评分为22分，低于警戒值，建议增加认知训练'),(4,10,'2025-05-23','16:20:00','骨质疏松','骨骼系统疾病',1,'痛感评分达到7分，超出正常范围，请注意休息并合理用药'),(5,11,'2025-05-24','09:10:00','脑卒中','心脑血管疾病',3,'出现短暂性言语不清，可能是短暂性脑缺血发作，需立即就医');
/*!40000 ALTER TABLE `alert_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `alert_set`
--

LOCK TABLES `alert_set` WRITE;
/*!40000 ALTER TABLE `alert_set` DISABLE KEYS */;
INSERT INTO `alert_set` VALUES (1,7,'高血压','慢性病',2),(2,8,'糖尿病','慢性病',3),(3,9,'认知障碍','神经系统疾病',2),(4,10,'骨质疏松','骨骼系统疾病',1),(5,11,'脑卒中','心脑血管疾病',3);
/*!40000 ALTER TABLE `alert_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `audit_log`
--

LOCK TABLES `audit_log` WRITE;
/*!40000 ALTER TABLE `audit_log` DISABLE KEYS */;
INSERT INTO `audit_log` VALUES (1,1,'用户登录','2025-06-01 08:30:00','192.168.1.100','系统管理员登录系统'),(2,2,'查看患者记录','2025-06-01 09:15:00','192.168.1.101','医生查看患者ID:7的健康记录'),(3,5,'更新患者信息','2025-06-01 10:45:00','192.168.1.102','护士更新患者ID:8的基本信息'),(4,3,'添加处方','2025-06-01 11:30:00','192.168.1.103','医生为患者ID:9添加新处方'),(5,1,'系统配置修改','2025-06-01 14:20:00','192.168.1.100','修改系统预警阈值设置');
/*!40000 ALTER TABLE `audit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `device_usage`
--

LOCK TABLES `device_usage` WRITE;
/*!40000 ALTER TABLE `device_usage` DISABLE KEYS */;
INSERT INTO `device_usage` VALUES (1,2,7,1,'2025-06-01 09:30:00',15),(2,3,8,2,'2025-06-02 10:45:00',20),(3,4,9,4,'2025-06-03 14:15:00',30),(4,2,10,5,'2025-06-04 11:00:00',45),(5,3,11,1,'2025-06-05 16:30:00',10);
/*!40000 ALTER TABLE `device_usage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'BP001','欧姆龙电子血压计','诊断设备','正常使用','诊室A','2025-04-15'),(2,'ECG002','飞利浦心电图仪','诊断设备','正常使用','检查室B','2025-03-20'),(3,'XR003','西门子X光机','影像设备','维修中','放射科','2025-02-10'),(4,'US004','迈瑞彩超机','影像设备','正常使用','超声室','2025-05-05'),(5,'PT005','康复训练设备组','康复设备','正常使用','康复中心','2025-04-25');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `emergency_contact`
--

LOCK TABLES `emergency_contact` WRITE;
/*!40000 ALTER TABLE `emergency_contact` DISABLE KEYS */;
INSERT INTO `emergency_contact` VALUES (1,7,'陈老伯','陈小明','13800138001','儿子'),(2,7,'陈老伯','陈小红','13800138002','女儿'),(3,8,'王奶奶','王华','13800138003','儿子'),(4,9,'张爷爷','张丽','13800138004','女儿'),(5,10,'刘阿姨','刘强','13800138005','儿子'),(6,11,'李大爷','李梅','13800138006','女儿');
/*!40000 ALTER TABLE `emergency_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (5,'医生态度亲切，解释详细，很满意此次咨询。','2025-06-01','专业,耐心',1,7,2),(4,'护士服务态度好，但等待时间有点长。','2025-06-02','服务好,效率待提高',2,8,5),(5,'医生很专业，给出的建议非常有帮助。','2025-06-03','专业,有效',3,9,3),(3,'随访过程有些仓促，希望能有更多交流时间。','2025-06-04','时间不足',4,10,4),(5,'康复指导非常细致，对恢复很有帮助。','2025-06-05','专业,耐心,效果好',5,11,6);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,7,'系统操作简单直观，但健康数据查看页面加载有点慢。','2025-06-01 10:30:00','已处理'),(2,8,'希望能增加更多的健康饮食建议功能。','2025-06-02 15:45:00','待处理'),(3,9,'随访提醒功能很实用，但提醒时间设置不够灵活。','2025-06-03 09:20:00','处理中'),(4,10,'健康计划的界面很友好，操作也方便。','2025-06-04 14:10:00','已处理'),(5,11,'希望能增加与医生即时沟通的功能。','2025-06-05 11:25:00','待处理');
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
INSERT INTO `followup_record` VALUES (1,1,2,7,'2025-06-15','09:35:00','血压控制良好，维持当前用药；嘱咐适当增加活动量'),(2,2,3,8,'2025-06-16','10:20:00','血糖波动较大，调整胰岛素剂量；强调饮食控制的重要性'),(3,3,4,9,'2025-06-17','14:05:00','认知功能略有改善，继续当前治疗；增加认知训练频次'),(4,4,2,10,'2025-06-18','11:35:00','骨密度较前增加5%，治疗效果良好；建议增加钙的摄入'),(5,5,3,11,'2025-06-19','15:50:00','血糖控制尚可，康复训练效果明显；调整药物剂量，增加肢体功能训练');
/*!40000 ALTER TABLE `followup_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `followupplan`
--

LOCK TABLES `followupplan` WRITE;
/*!40000 ALTER TABLE `followupplan` DISABLE KEYS */;
INSERT INTO `followupplan` VALUES (1,7,2,'2025-06-15','09:30:00','检查血压控制情况，评估药物疗效'),(2,8,3,'2025-06-16','10:15:00','血糖控制随访，调整胰岛素用量'),(3,9,4,'2025-06-17','14:00:00','评估认知功能变化，调整治疗方案'),(4,10,2,'2025-06-18','11:30:00','骨密度检查，评估治疗效果'),(5,11,3,'2025-06-19','15:45:00','随访血糖控制和中风康复进展');
/*!40000 ALTER TABLE `followupplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'营养健康餐券','meal_voucher.jpg',200),(2,'按摩服务券','massage_voucher.jpg',300),(3,'健康书籍','health_book.jpg',150),(4,'体检优惠券','checkup_coupon.jpg',400),(5,'康复器材套装','rehab_kit.jpg',500),(6,'健康小零食','healthy_snack.jpg',100);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_goal`
--

LOCK TABLES `health_goal` WRITE;
/*!40000 ALTER TABLE `health_goal` DISABLE KEYS */;
INSERT INTO `health_goal` VALUES (1,7,'血压管理','每日保持血压在140/90mmHg以下','长期'),(2,8,'血糖控制','控制餐后2小时血糖在10mmol/L以下','长期'),(3,9,'认知功能','每天完成30分钟认知训练','短期'),(4,10,'骨健康','每周进行3次30分钟以上的适度有氧运动','中期'),(5,11,'康复训练','三个月内恢复80%的肢体活动功能','中期');
/*!40000 ALTER TABLE `health_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_plan`
--

LOCK TABLES `health_plan` WRITE;
/*!40000 ALTER TABLE `health_plan` DISABLE KEYS */;
INSERT INTO `health_plan` VALUES ('测量血压两次，记录数值；减少盐摄入量',1,'100%','90%','避免剧烈运动，保证充足睡眠',1,1,7),('监测血糖三次，早餐前、午餐后2小时、晚餐后2小时',1,'100%','85%','控制碳水化合物摄入，避免甜食',2,2,8),('完成记忆力训练游戏；阅读30分钟',1,'80%','75%','保持规律作息，避免过度疲劳',3,3,9),('进行30分钟散步；做骨质疏松体操',0,'50%','60%','注意安全，避免跌倒风险',4,4,10),('完成上肢康复训练；练习精细动作',1,'90%','80%','循序渐进，不要过度疲劳',5,5,11);
/*!40000 ALTER TABLE `health_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `health_record`
--

LOCK TABLES `health_record` WRITE;
/*!40000 ALTER TABLE `health_record` DISABLE KEYS */;
INSERT INTO `health_record` VALUES (1,7,'陈老伯','男',70,'1955-05-15','320111195505150011','13900000007','上海市浦东新区张江路100号','父亲有高血压病史，母亲有糖尿病病史','花粉过敏','2010年确诊高血压；2015年髋关节置换',172.5,68.3,'135/85',72,'2025-05-01 10:30:00',NULL,'住院中','2025-05-01 10:30:00','2025-06-01 09:15:00'),(2,8,'王奶奶','女',68,'1957-08-20','320111195708200024','13900000008','上海市静安区南京西路500号','父母均有冠心病','青霉素过敏','2012年确诊糖尿病；2018年白内障手术',158,56.7,'145/88',75,'2025-05-02 14:15:00',NULL,'住院中','2025-05-02 14:15:00','2025-06-02 14:00:00'),(3,9,'张爷爷','男',72,'1953-03-10','320111195303100038','13900000009','上海市徐汇区华山路200号','家族中有老年痴呆症患者','无','2005年心脏搭桥；2019年确诊轻度认知障碍',170,63.5,'130/80',68,'2025-05-03 09:40:00',NULL,'住院中','2025-05-03 09:40:00','2025-06-03 10:20:00'),(4,10,'刘阿姨','女',65,'1960-12-05','320111196012050046','13900000010','上海市黄浦区福州路300号','无明显家族病史','海鲜过敏','2016年确诊骨质疏松；2020年膝关节炎',160.5,58.2,'125/78',70,'2025-05-04 11:20:00',NULL,'住院中','2025-05-04 11:20:00','2025-06-04 11:30:00'),(5,11,'李大爷','男',75,'1950-06-30','320111195006300057','13900000011','上海市杨浦区四平路150号','父亲有脑卒中病史','对某些抗生素过敏','2008年确诊2型糖尿病；2017年轻度中风',168,65.8,'142/90',74,'2025-05-05 16:00:00','2025-05-10 11:00:00','已出院','2025-05-05 16:00:00','2025-05-10 11:00:00');
/*!40000 ALTER TABLE `health_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inviteuser`
--

LOCK TABLES `inviteuser` WRITE;
/*!40000 ALTER TABLE `inviteuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `inviteuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medical_record`
--

LOCK TABLES `medical_record` WRITE;
/*!40000 ALTER TABLE `medical_record` DISABLE KEYS */;
INSERT INTO `medical_record` VALUES (1,7,'陈老伯','男',70,'原发性高血压，稳定期；髋关节置换术后','1. 继续服用降压药物；2. 定期监测血压；3. 髋关节功能锻炼','2025-05-01 11:30:00','2025-06-01 09:15:00'),(2,8,'王奶奶','女',68,'2型糖尿病，白内障术后','1. 胰岛素治疗；2. 低糖饮食；3. 定期眼底检查','2025-05-02 15:00:00','2025-06-02 14:00:00'),(3,9,'张爷爷','男',72,'轻度认知障碍，冠状动脉疾病','1. 记忆力训练；2. 服用脑循环改善药物；3. 心脏用药','2025-05-03 10:20:00','2025-06-03 10:20:00'),(4,10,'刘阿姨','女',65,'骨质疏松症，膝关节炎','1. 补钙治疗；2. 理疗和物理治疗；3. 减轻膝关节负担','2025-05-04 12:00:00','2025-06-04 11:30:00'),(5,11,'李大爷','男',75,'2型糖尿病，缺血性脑卒中后遗症','1. 糖尿病饮食和药物控制；2. 肢体康复训练；3. 脑血管扩张药物治疗','2025-05-05 16:30:00','2025-05-10 10:30:00');
/*!40000 ALTER TABLE `medical_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicine_inventory`
--

LOCK TABLES `medicine_inventory` WRITE;
/*!40000 ALTER TABLE `medicine_inventory` DISABLE KEYS */;
INSERT INTO `medicine_inventory` VALUES (1,'1001',45,3.50,12,'normal','2025-06-01 10:00:00'),(2,'1002',18,1.20,15,'normal','2025-06-01 10:00:00'),(3,'1003',25,5.00,5,'warning','2025-06-01 10:00:00'),(4,'1004',20,4.00,5,'warning','2025-06-01 10:00:00'),(5,'1005',15,5.50,2,'critical','2025-06-01 10:00:00'),(6,'1006',40,3.20,12,'normal','2025-06-01 10:00:00'),(7,'1007',55,2.80,19,'normal','2025-06-01 10:00:00'),(8,'1008',12,4.00,3,'warning','2025-06-01 10:00:00'),(9,'1009',8,3.00,2,'critical','2025-06-01 10:00:00'),(10,'1010',50,2.50,20,'normal','2025-06-01 10:00:00');
/*!40000 ALTER TABLE `medicine_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medicines`
--

LOCK TABLES `medicines` WRITE;
/*!40000 ALTER TABLE `medicines` DISABLE KEYS */;
INSERT INTO `medicines` VALUES ('1001','XBDP001','硝苯地平缓释片','心血管类','盒',20,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1002','GJYS002','甘精胰岛素注射液','内分泌类','支',15,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1003','YGYT003','银杏叶提取物片','神经系统类','盒',30,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1004','GEQD004','钙尔奇D片','骨科类','瓶',25,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1005','BLFS005','布洛芬缓释胶囊','镇痛类','盒',40,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1006','EMSG006','二甲双胍片','内分泌类','盒',35,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1007','ASPN007','阿司匹林肠溶片','心血管类','瓶',50,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1008','AMXL008','阿莫西林胶囊','抗生素','盒',60,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1009','LSTN009','洛沙坦钾片','心血管类','盒',30,1,'2025-05-01 00:00:00','2025-05-01 00:00:00'),('1010','OMZL010','奥美拉唑肠溶胶囊','消化系统类','盒',45,1,'2025-05-01 00:00:00','2025-05-01 00:00:00');
/*!40000 ALTER TABLE `medicines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `patient_management`
--

LOCK TABLES `patient_management` WRITE;
/*!40000 ALTER TABLE `patient_management` DISABLE KEYS */;
INSERT INTO `patient_management` VALUES (1,2,7,'陈老伯'),(2,3,8,'王奶奶'),(3,4,9,'张爷爷'),(4,2,10,'刘阿姨'),(5,3,11,'李大爷');
/*!40000 ALTER TABLE `patient_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (101,'用户管理'),(102,'角色管理'),(103,'权限管理'),(201,'患者管理'),(202,'健康记录查看'),(203,'健康记录编辑'),(204,'处方管理'),(301,'随访管理'),(302,'健康预警设置'),(401,'设备管理'),(501,'药品管理'),(601,'系统日志查看');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (1,7,1,'陈老伯','硝苯地平缓释片，每日1次，每次1片，口服','饭后服用，监测血压变化','2025-05-01','2025-08-01','2025-05-01 11:45:00','2025-05-01 11:45:00'),(2,8,2,'王奶奶','甘精胰岛素注射液，每日1次，每次10单位，皮下注射','晚餐前注射，需监测血糖','2025-05-02','2025-08-02','2025-05-02 15:15:00','2025-05-02 15:15:00'),(3,9,3,'张爷爷','银杏叶提取物片，每日3次，每次1片，口服','饭后服用，注意用药反应','2025-05-03','2025-08-03','2025-05-03 10:30:00','2025-05-03 10:30:00'),(4,10,4,'刘阿姨','钙尔奇D片，每日2次，每次1片，口服；布洛芬缓释胶囊，疼痛时服用，每次1粒，口服','钙片饭后服用，布洛芬胃不适时停用','2025-05-04','2025-08-04','2025-05-04 12:15:00','2025-05-04 12:15:00'),(5,11,5,'李大爷','二甲双胍片，每日3次，每次0.5g，口服；阿司匹林肠溶片，每日1次，每次100mg，口服','二甲双胍饭前服用，阿司匹林饭后服用，避免空腹','2025-05-05','2025-08-05','2025-05-05 16:45:00','2025-05-05 16:45:00');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'您可以在\"健康档案管理\"菜单中查看您的健康档案信息，包括基本信息、病史、用药记录等。','如何查看我的健康档案？',1),(2,'在\"健康管理\"中选择\"健康目标\"功能，点击\"添加新目标\"按钮，填写相关信息即可。','如何设置健康目标？',1),(3,'请点击登录页面的\"忘记密码\"链接，按照提示操作，通过手机或邮箱验证后重置密码。','忘记密码怎么办？',1),(4,'在\"我的预约\"页面可以查看您的所有预约记录，包括待确认、已确认和已完成的预约。','如何查看我的预约记录？',1),(5,'在\"医生咨询\"功能中选择您的主治医生，可以发起在线咨询或预约面诊。','如何联系我的主治医生？',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `raffle`
--

LOCK TABLES `raffle` WRITE;
/*!40000 ALTER TABLE `raffle` DISABLE KEYS */;
INSERT INTO `raffle` VALUES (1,'健康小礼品',50,1,0.5),(2,'康复辅助用品',100,2,0.3),(3,'健康检测套装',150,3,0.2),(4,'特色保健服务',200,4,0.1),(5,'高级康复设备体验',250,5,0.05);
/*!40000 ALTER TABLE `raffle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员'),(2,'医生'),(3,'护士'),(4,'老人/患者');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,101),(1,102),(1,103),(1,201),(2,201),(3,201),(1,202),(2,202),(3,202),(4,202),(1,203),(2,203),(1,204),(2,204),(1,301),(2,301),(3,301),(1,302),(2,302),(1,401),(1,501),(1,601);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin',35,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','admin@shec-psims.com','13900000001','系统管理员',NULL,NULL,NULL,'管理部',0,NULL,NULL,NULL),(2,'doctor1',42,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','doctor1@shec-psims.com','13900000002','王医生',NULL,NULL,NULL,'内科',100,NULL,NULL,NULL),(3,'doctor2',38,'女','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','doctor2@shec-psims.com','13900000003','张医生',NULL,NULL,NULL,'外科',50,NULL,NULL,NULL),(4,'doctor3',45,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','doctor3@shec-psims.com','13900000004','李医生',NULL,NULL,NULL,'神经内科',80,NULL,NULL,NULL),(5,'nurse1',28,'女','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','nurse1@shec-psims.com','13900000005','赵护士',NULL,NULL,NULL,'内科',40,NULL,NULL,NULL),(6,'nurse2',30,'女','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','nurse2@shec-psims.com','13900000006','钱护士',NULL,NULL,NULL,'外科',30,NULL,NULL,NULL),(7,'patient1',70,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','patient1@example.com','13900000007','陈老伯',NULL,NULL,NULL,NULL,200,NULL,NULL,NULL),(8,'patient2',68,'女','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','patient2@example.com','13900000008','王奶奶',NULL,NULL,NULL,NULL,150,NULL,NULL,NULL),(9,'patient3',72,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','patient3@example.com','13900000009','张爷爷',NULL,NULL,NULL,NULL,180,NULL,NULL,NULL),(10,'patient4',65,'女','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','patient4@example.com','13900000010','刘阿姨',NULL,NULL,NULL,NULL,120,NULL,NULL,NULL),(11,'patient5',75,'男','$2a$10$1qAz2wSx3eDc4rFv5tGb5.lfQzT.gR2U1Sv4xNrpYzBPb.cURxjny','patient5@example.com','13900000011','李大爷',NULL,NULL,NULL,NULL,90,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2),(5,3),(6,3),(7,4),(8,4),(9,4),(10,4),(11,4);
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

-- Dump completed on 2025-06-11 11:27:02
