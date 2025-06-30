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
-- Table structure for table `cost_breakdown`
--

DROP TABLE IF EXISTS `cost_breakdown`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost_breakdown` (
  `id` int NOT NULL AUTO_INCREMENT,
  `financial_data_id` int NOT NULL COMMENT '关联的财务数据ID',
  `cost_category` varchar(50) NOT NULL COMMENT '成本类别',
  `cost_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '成本金额',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_cost_breakdown_financial_data` (`financial_data_id`),
  CONSTRAINT `fk_cost_breakdown_financial_data` FOREIGN KEY (`financial_data_id`) REFERENCES `financial_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='成本明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cost_categories`
--

DROP TABLE IF EXISTS `cost_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cost_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL COMMENT '类别名称',
  `display_order` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
  `color_code` varchar(20) DEFAULT NULL COMMENT '图表显示颜色代码',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='成本类别配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `financial_data`
--

DROP TABLE IF EXISTS `financial_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financial_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `period_type` varchar(20) NOT NULL COMMENT '周期类型: daily, weekly, monthly, quarterly, yearly',
  `period_start` date NOT NULL COMMENT '周期开始日期',
  `period_end` date NOT NULL COMMENT '周期结束日期',
  `total_revenue` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '总收入',
  `total_cost` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '总成本',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_period_type_dates` (`period_type`,`period_start`,`period_end`),
  KEY `idx_period_start` (`period_start`),
  KEY `idx_period_end` (`period_end`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='财务数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `health_plan`
--

DROP TABLE IF EXISTS `health_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `health_plan` (
  `plan_today` varchar(250) NOT NULL,
  `checkin` tinyint(1) NOT NULL,
  `day_progress` varchar(5) NOT NULL,
  `week_progress` varchar(5) NOT NULL,
  `attention` varchar(255) DEFAULT NULL,
  `plan_id` int NOT NULL,
  `goal_id` int NOT NULL,
  `patient_id` int NOT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `FK_HEALTH_P_IMPLEMENT_ELDERLYP` (`patient_id`),
  KEY `FK_HEALTH_P_PLAN_HEALTH_G` (`goal_id`),
  CONSTRAINT `FK_HEALTH_P_IMPLEMENT_ELDERLYP` FOREIGN KEY (`patient_id`) REFERENCES `elderlyprofile` (`patient_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_HEALTH_P_PLAN_HEALTH_G` FOREIGN KEY (`goal_id`) REFERENCES `health_goal` (`goal_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `monthly_financial_trends`
--

DROP TABLE IF EXISTS `monthly_financial_trends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monthly_financial_trends` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL COMMENT '年份',
  `month` int NOT NULL COMMENT '月份 (1-12)',
  `revenue` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '月度收入',
  `cost` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '月度成本',
  `profit` decimal(15,2) GENERATED ALWAYS AS ((`revenue` - `cost`)) STORED COMMENT '月度利润',
  `roi` decimal(8,2) GENERATED ALWAYS AS ((case when (`cost` > 0) then (((`revenue` - `cost`) / `cost`) * 100) else NULL end)) STORED COMMENT 'ROI (%)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_year_month` (`year`,`month`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='月度财务趋势表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quarterly_financial_data`
--

DROP TABLE IF EXISTS `quarterly_financial_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarterly_financial_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL COMMENT '年份',
  `quarter` int NOT NULL COMMENT '季度 (1-4)',
  `revenue` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '季度收入',
  `cost` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '季度成本',
  `profit` decimal(15,2) GENERATED ALWAYS AS ((`revenue` - `cost`)) STORED COMMENT '季度利润',
  `roi` decimal(8,2) GENERATED ALWAYS AS ((case when (`cost` > 0) then (((`revenue` - `cost`) / `cost`) * 100) else NULL end)) STORED COMMENT 'ROI (%)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_year_quarter` (`year`,`quarter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='季度财务数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`raffle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revenue_breakdown`
--

DROP TABLE IF EXISTS `revenue_breakdown`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenue_breakdown` (
  `id` int NOT NULL AUTO_INCREMENT,
  `financial_data_id` int NOT NULL COMMENT '关联的财务数据ID',
  `revenue_category` varchar(50) NOT NULL COMMENT '收入类别',
  `revenue_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '收入金额',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_revenue_breakdown_financial_data` (`financial_data_id`),
  CONSTRAINT `fk_revenue_breakdown_financial_data` FOREIGN KEY (`financial_data_id`) REFERENCES `financial_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收入明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `yearly_financial_data`
--

DROP TABLE IF EXISTS `yearly_financial_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yearly_financial_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` int NOT NULL COMMENT '年份',
  `revenue` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '年度收入',
  `cost` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '年度成本',
  `profit` decimal(15,2) GENERATED ALWAYS AS ((`revenue` - `cost`)) STORED COMMENT '年度利润',
  `roi` decimal(8,2) GENERATED ALWAYS AS ((case when (`cost` > 0) then (((`revenue` - `cost`) / `cost`) * 100) else NULL end)) STORED COMMENT 'ROI (%)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='年度财务数据表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-30 15:37:01
