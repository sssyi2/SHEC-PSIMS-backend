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
-- Table structure for table `ai_model_cache_config`
--

DROP TABLE IF EXISTS `ai_model_cache_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_model_cache_config` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_id` int NOT NULL COMMENT '关联ai_models表',
  `cache_key_prefix` varchar(50) NOT NULL COMMENT '缓存key前缀',
  `cache_ttl` int NOT NULL DEFAULT '3600' COMMENT '缓存TTL(秒)',
  `enable_cache` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用缓存',
  `max_cache_size` int DEFAULT '1000' COMMENT '最大缓存条目数',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_model_cache` (`model_id`),
  KEY `idx_cache_enabled` (`enable_cache`),
  CONSTRAINT `fk_model_cache_model` FOREIGN KEY (`model_id`) REFERENCES `ai_models` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI模型缓存配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ai_model_performance`
--

DROP TABLE IF EXISTS `ai_model_performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_model_performance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_id` int NOT NULL COMMENT '模型ID',
  `date` date NOT NULL COMMENT '统计日期',
  `prediction_count` int NOT NULL DEFAULT '0' COMMENT '预测次数',
  `avg_response_time` decimal(8,3) DEFAULT NULL COMMENT '平均响应时间(秒)',
  `avg_confidence` decimal(5,4) DEFAULT NULL COMMENT '平均置信度',
  `error_count` int NOT NULL DEFAULT '0' COMMENT '错误次数',
  `cache_hit_rate` decimal(5,2) DEFAULT NULL COMMENT '缓存命中率(%)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_model_date` (`model_id`,`date`),
  KEY `idx_date` (`date`),
  KEY `idx_prediction_count` (`prediction_count`),
  CONSTRAINT `fk_model_performance_model` FOREIGN KEY (`model_id`) REFERENCES `ai_models` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI模型性能统计表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `ai_model_performance_summary`
--

DROP TABLE IF EXISTS `ai_model_performance_summary`;
/*!50001 DROP VIEW IF EXISTS `ai_model_performance_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ai_model_performance_summary` AS SELECT 
 1 AS `model_name`,
 1 AS `model_version`,
 1 AS `total_predictions_30d`,
 1 AS `avg_confidence_30d`,
 1 AS `last_used_date`,
 1 AS `status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `ai_models`
--

DROP TABLE IF EXISTS `ai_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_models` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model_name` varchar(100) NOT NULL COMMENT '模型名称',
  `model_version` varchar(50) NOT NULL COMMENT '模型版本',
  `model_type` varchar(50) NOT NULL COMMENT '模型类型: LSTM/GRU/Transformer',
  `model_path` varchar(255) NOT NULL COMMENT '模型文件路径',
  `configuration` json DEFAULT NULL COMMENT '模型配置参数',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_model_name_version` (`model_name`,`model_version`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI模型配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ai_prediction_results`
--

DROP TABLE IF EXISTS `ai_prediction_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_prediction_results` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL COMMENT '患者ID',
  `prediction_id` varchar(36) NOT NULL COMMENT '预测任务ID',
  `prediction_type` varchar(50) NOT NULL COMMENT '预测类型: health_trend/risk_assessment',
  `input_data` json NOT NULL COMMENT '输入数据(JSON格式)',
  `prediction_data` json NOT NULL COMMENT '预测结果(JSON格式)',
  `confidence_score` decimal(5,4) DEFAULT NULL COMMENT '置信度分数',
  `model_version` varchar(50) DEFAULT NULL COMMENT '模型版本',
  `prediction_period` int DEFAULT '30' COMMENT '预测周期(天)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_at` timestamp NULL DEFAULT NULL COMMENT '预测结果过期时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_prediction_id` (`prediction_id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_created_at` (`created_at`),
  KEY `idx_patient_prediction_type` (`patient_id`,`prediction_type`),
  KEY `idx_model_version` (`model_version`),
  KEY `idx_confidence_score` (`confidence_score`),
  KEY `idx_expires_at` (`expires_at`),
  KEY `idx_prediction_type_created` (`prediction_type`,`created_at` DESC),
  CONSTRAINT `fk_ai_prediction_patient` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='AI预测结果表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `ai_prediction_summary`
--

DROP TABLE IF EXISTS `ai_prediction_summary`;
/*!50001 DROP VIEW IF EXISTS `ai_prediction_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `ai_prediction_summary` AS SELECT 
 1 AS `patient_id`,
 1 AS `patient_name`,
 1 AS `total_predictions`,
 1 AS `last_prediction_date`,
 1 AS `avg_confidence`,
 1 AS `current_risk_level`,
 1 AS `current_ai_score`*/;
SET character_set_client = @saved_cs_client;

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
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices` (
  `device_id` int NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `device_name` varchar(100) NOT NULL COMMENT '设备名称',
  `device_model` varchar(100) DEFAULT NULL COMMENT '设备型号',
  `status` int NOT NULL DEFAULT '0' COMMENT '设备状态：0-闲置, 1-使用中, 2-维修中',
  `department_name` varchar(100) DEFAULT NULL COMMENT '所属部门名称',
  `purchase_date` datetime DEFAULT NULL COMMENT '购买日期',
  `last_maintenance_date` datetime DEFAULT NULL COMMENT '最后维护日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`device_id`),
  KEY `idx_department_name` (`department_name`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='设备信息表';
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
  `last_prediction_date` datetime DEFAULT NULL COMMENT '最后预测时间',
  `risk_level` varchar(20) DEFAULT 'unknown' COMMENT '风险等级: low/medium/high/critical',
  `ai_score` decimal(5,2) DEFAULT NULL COMMENT 'AI健康评分',
  `prediction_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用AI预测',
  PRIMARY KEY (`health_record_id`),
  KEY `fk_health_record_patient_id` (`patient_id`),
  KEY `idx_status` (`status`),
  KEY `idx_admission_date` (`admission_date`),
  KEY `idx_create_date` (`create_date`),
  KEY `idx_risk_level` (`risk_level`),
  KEY `idx_ai_score` (`ai_score`),
  KEY `idx_prediction_enabled` (`prediction_enabled`),
  CONSTRAINT `fk_health_record_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `ai_diagnosis_suggestion` text COMMENT 'AI诊断建议',
  `ai_confidence` decimal(5,4) DEFAULT NULL COMMENT 'AI诊断置信度',
  `prediction_data` json DEFAULT NULL COMMENT '相关预测数据',
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
-- Table structure for table `patient_ai_preferences`
--

DROP TABLE IF EXISTS `patient_ai_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_ai_preferences` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL COMMENT '患者ID',
  `enable_auto_prediction` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否自动预测',
  `prediction_frequency` int NOT NULL DEFAULT '7' COMMENT '预测频率(天)',
  `notification_enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用通知',
  `risk_threshold` decimal(5,4) DEFAULT '0.7000' COMMENT '风险阈值',
  `preferred_models` json DEFAULT NULL COMMENT '偏好模型列表',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_patient_preferences` (`patient_id`),
  KEY `idx_auto_prediction` (`enable_auto_prediction`),
  CONSTRAINT `fk_patient_ai_preferences` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='患者AI预测偏好配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patient_health_metrics`
--

DROP TABLE IF EXISTS `patient_health_metrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_health_metrics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL COMMENT '患者ID',
  `record_date` date NOT NULL COMMENT '记录日期',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `disease_name` varchar(100) DEFAULT NULL COMMENT '疾病名称',
  `systolic_pressure` decimal(5,2) DEFAULT NULL COMMENT '收缩压',
  `diastolic_pressure` decimal(5,2) DEFAULT NULL COMMENT '舒张压',
  `blood_sugar` decimal(5,2) DEFAULT NULL COMMENT '血糖值',
  `bmi` decimal(5,2) DEFAULT NULL COMMENT 'BMI指数',
  `other_metrics` json DEFAULT NULL COMMENT '其他指标(JSON格式)',
  `data_source` varchar(50) DEFAULT 'manual' COMMENT '数据来源: manual/device/import',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_patient_date` (`patient_id`,`record_date`),
  KEY `idx_record_date` (`record_date`),
  KEY `idx_patient_metrics_date` (`patient_id`,`record_date`,`created_at`),
  KEY `idx_data_source` (`data_source`),
  KEY `idx_systolic_pressure` (`systolic_pressure`),
  KEY `idx_blood_sugar` (`blood_sugar`),
  KEY `idx_bmi_date` (`bmi`,`record_date`),
  CONSTRAINT `fk_patient_health_metrics_patient` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='患者健康指标历史数据表';
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `prediction_analysis`
--

DROP TABLE IF EXISTS `prediction_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prediction_analysis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prediction_id` int NOT NULL COMMENT '预测结果ID',
  `patient_id` int NOT NULL COMMENT '患者ID',
  `analysis_type` varchar(50) NOT NULL COMMENT '分析类型: trend/risk/recommendation',
  `analysis_content` text NOT NULL COMMENT '分析内容',
  `severity_level` varchar(20) DEFAULT 'normal' COMMENT '严重程度: normal/warning/critical',
  `recommendations` json DEFAULT NULL COMMENT '建议措施',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_prediction_id` (`prediction_id`),
  KEY `idx_patient_id` (`patient_id`),
  CONSTRAINT `fk_prediction_analysis_patient` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_prediction_analysis_prediction` FOREIGN KEY (`prediction_id`) REFERENCES `ai_prediction_results` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预测分析报告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prediction_tasks`
--

DROP TABLE IF EXISTS `prediction_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prediction_tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` varchar(36) NOT NULL COMMENT '任务ID',
  `patient_id` int NOT NULL COMMENT '患者ID',
  `task_type` varchar(50) NOT NULL COMMENT '任务类型',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态: pending/running/completed/failed',
  `input_parameters` json DEFAULT NULL COMMENT '输入参数',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `error_message` text COMMENT '错误信息',
  `result_id` int DEFAULT NULL COMMENT '关联的预测结果ID',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `priority` int DEFAULT '5' COMMENT '浼樺厛绾?1-10)',
  `retry_count` int DEFAULT '0' COMMENT '閲嶈瘯娆℃暟',
  `max_retries` int DEFAULT '3' COMMENT '鏈?ぇ閲嶈瘯娆℃暟',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_id` (`task_id`),
  KEY `idx_patient_id` (`patient_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`),
  KEY `fk_prediction_tasks_result` (`result_id`),
  KEY `idx_status_priority` (`status`,`priority`),
  KEY `idx_retry_count` (`retry_count`),
  CONSTRAINT `fk_prediction_tasks_patient` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_prediction_tasks_result` FOREIGN KEY (`result_id`) REFERENCES `ai_prediction_results` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预测任务记录表';
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
  PRIMARY KEY (`user_id`),
  KEY `idx_age_gender` (`age`,`gender`)
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

--
-- Final view structure for view `ai_model_performance_summary`
--

/*!50001 DROP VIEW IF EXISTS `ai_model_performance_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ai_model_performance_summary` AS select `m`.`model_name` AS `model_name`,`m`.`model_version` AS `model_version`,count(`p`.`id`) AS `total_predictions_30d`,avg(`p`.`confidence_score`) AS `avg_confidence_30d`,max(`p`.`created_at`) AS `last_used_date`,(case when (`m`.`is_active` = 1) then 'Active' else 'Inactive' end) AS `status` from (`ai_models` `m` left join `ai_prediction_results` `p` on(((`p`.`model_version` = `m`.`model_version`) and (`p`.`created_at` >= (now() - interval 30 day))))) group by `m`.`id`,`m`.`model_name`,`m`.`model_version`,`m`.`is_active` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ai_prediction_summary`
--

/*!50001 DROP VIEW IF EXISTS `ai_prediction_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ai_prediction_summary` AS select `p`.`patient_id` AS `patient_id`,`u`.`real_name` AS `patient_name`,count(0) AS `total_predictions`,max(`p`.`created_at`) AS `last_prediction_date`,avg(`p`.`confidence_score`) AS `avg_confidence`,`hr`.`risk_level` AS `current_risk_level`,`hr`.`ai_score` AS `current_ai_score` from ((`ai_prediction_results` `p` left join `user` `u` on((`p`.`patient_id` = `u`.`user_id`))) left join `health_record` `hr` on((`p`.`patient_id` = `hr`.`patient_id`))) where (`p`.`created_at` >= (now() - interval 30 day)) group by `p`.`patient_id`,`u`.`real_name`,`hr`.`risk_level`,`hr`.`ai_score` */;
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

-- Dump completed on 2025-07-24 11:38:48
