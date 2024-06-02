CREATE DATABASE  IF NOT EXISTS `pet` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pet`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: pet
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (38,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-09'),(39,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-09'),(40,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-16'),(41,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-16'),(42,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-16'),(43,'Nguyễn Vệt Hoàng','hoangnguyen68@gmail.com','0314837135','Tân Phú','2022-06-16'),(44,'Nguyễn Vệt Hoàng','hoangnguyen68@gmail.com','0314837135','Tân Phú','2022-06-16'),(45,'Nguyễn Vệt Hoàng','hoangnguyen68@gmail.com','0314837135','Tân Phú','2022-06-16'),(46,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(47,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(48,'Nguyen Van Khanh','khanhku147@gmail.com','0908675144','Bến Tre','2022-06-17'),(49,'Nguyen Van Khanh','khangku157@gmail.com','0908675146','Bien Hoa','2022-06-17'),(50,'Trần Duy Khánh','tranduykhanh587@gmail.com','0334013454','Bến Tre','2022-06-17'),(51,'Lâm Gia Hân','Hana7@gmail.com','0344817365','Lâm Đồng','2022-06-17'),(52,'Lê Minh Tâm','mingtam@gmail.com','0917388361','Sa Đéc','2022-06-17'),(53,'Lại Văn Lâm','vanlam@gmail.com','0917331234','Cần Thơ','2022-06-17'),(54,'Trần Văn Ơn','onlaza147@gmail.com','0311234123','Bien Hoa','2022-06-17'),(55,'Trần Văn Ơn','onlaza147@gmail.com','0311234123','Bien Hoa','2022-06-17'),(56,'Nguyen Van Khanh','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(57,'Nguyen Van Khanh','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(58,'Nguyen Van Khanh','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(59,'Nguyen Van Khanh','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(60,'Lê Ăn Ban','logolban@gmail.com','0338173651','Gia Lai','2022-06-17'),(61,'Nguyen Van Khanh','khanhkhanh@gmail.com','0331456754','Bến tre','2022-06-17'),(62,'Nguyen Van Khanh','khanhkhanh@gmail.com','0331456754','Bến tre','2022-06-17'),(63,'Lê Thị Linh Ka','linhka147@gmail.com','0371645161','Bien Hoa','2022-06-17'),(64,'Lê Thị Linh Ka','linhka147@gmail.com','0371645161','Bien Hoa','2022-06-17'),(65,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(66,'Nguyễn Văn Lan','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(67,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(68,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(69,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(70,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(71,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(72,'Nguyen Van Khang','khangku147@gmail.com','0908675145','Bien Hoa','2022-06-17'),(73,'NVK','khangku147@gmail.com','0908675145','Bien hoa','2022-06-17');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` varchar(40) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('AD','Quản trị hệ thống'),('KD','Kinh doanh'),('NS','Nhân sự'),('NV','Nhân viên'),('TC','Tài chính'),('TN','Thu ngân');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `bill_path` varchar(45) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_201` (`user_id`),
  KEY `fk_202` (`customer_id`),
  CONSTRAINT `fk_201` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_202` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (8,3,38,1760000,'1','2022-06-09'),(9,3,39,600000,'1','2022-06-09'),(12,3,49,150000,'srcfileill12.pdf','2022-06-17'),(13,26,50,465000,'13','2022-06-17'),(14,3,51,0,'14','2022-06-17'),(15,3,51,300000,'15','2022-06-17'),(16,26,52,450000,'16','2022-06-17'),(17,26,52,1780000,'17','2022-06-17'),(18,26,53,4779998,'18','2022-06-17'),(19,26,54,4300000,'19','2022-06-17'),(20,26,55,4600000,'20','2022-06-17'),(21,3,56,150000,'21','2022-06-17'),(22,3,56,1450000,'22','2022-06-17'),(23,3,57,1450000,'23','2022-06-17'),(24,3,57,1900000,'24','2022-06-17'),(25,3,58,1900000,'25','2022-06-17'),(26,3,59,5100000,'26','2022-06-17'),(27,26,60,3300000,'27','2022-06-17'),(28,26,61,500000,'28','2022-06-17'),(29,26,61,300000,'29','2022-06-17'),(30,26,62,300000,'30','2022-06-17'),(31,3,63,250000,'31','2022-06-17'),(32,3,64,1800000,'32','2022-06-17'),(33,3,65,1350000,'33','2022-06-17'),(34,3,66,500000,'34','2022-06-17'),(35,3,67,7100000,'35','2022-06-17'),(36,3,68,400000,'36','2022-06-17'),(37,3,69,115000,'37','2022-06-17'),(38,3,70,2130000,'38','2022-06-17'),(39,3,71,4850000,'39','2022-06-17'),(40,3,72,250000,'40','2022-06-17'),(41,3,73,13300000,'41','2022-06-17');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `color` varchar(200) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `retail_price` int DEFAULT NULL,
  `vendor_price` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `importday` date DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `adopted` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_3` (`category_id`),
  CONSTRAINT `fk_3` FOREIGN KEY (`category_id`) REFERENCES `pet_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (16,1,'Cỏ','Vàng','2021-05-07','Cái',20.5,70,15000000,10000000,'Thông minh và trung thành với chủ','2022-04-03','\\src\\file\\petImage\\16.JPG',1),(17,1,'Pug','Trắng','2021-06-04','Cái',14,60,6000000,5600000,'Có bộ lông ngắn, Ngoài ra, đầu và mắt khá to, đặc biệt nhất là da mặt chảy xệ thành nếp, thích được vuốt ve và vu tính.','2022-04-03','\\src\\file\\petImage\\17.jfif',1),(18,1,'Poodle','Nâu','2021-06-04','Cái',5,40,5300000,5000000,'Bộ lông xoăn cùng đôi tai rũ xuống, đầu khá tròn, vô cùng thân thiện, đáng yêu nên được nhiều phái đẹp yêu thích.','2022-04-17','\\src\\file\\petImage\\18.jpg',0),(19,2,'Ai cập','Không có lông','2021-06-07','Đực',6.5,15.7,12000000,11250000,'Tinh nghịch nhưng không kém phần đáng yêu','2022-04-10','\\src\\file\\petImage\\19.jpg',1),(20,2,'Lông ngắn','Xám','2021-05-06','Cái',15,40,1900000,1100000,'Rất dễ thương và không quậy phá','2022-03-15','\\src\\file\\petImage\\20.jpg',0),(21,4,'Minilop','Trắng','2021-12-01','Cái',5,30,1200000,1000000,'Nhỏ nhắn đáng yêu','2022-03-14','\\src\\file\\petImage\\21.jpg',0),(22,1,'Chihuahua','Vàng','2020-06-10','Đực',2,20,4000000,3500000,'vóc dáng nhỏ nhắn với cái đầu nhỏ, tai dựng đứng, mắt to tròn. Chihuahua khá thông minh, nhanh nhẹn, thích được chiều và rất trung thành với chủ.','2022-01-02','\\src\\file\\petImage\\22.jpg',1),(23,1,'Bắc Kinh','Trắng','2019-06-04','Cái',5,23,3000000,2500000,'Bộ lông chỉ có một màu, mũi hếch, giữa mũi và trán có phần bị gãy, mõm ngắn','2022-02-21','\\src\\file\\petImage\\23.jpg',0),(24,1,'Bắc Kinh lai Nhật','Trắng đốm nâu','2020-06-13','Cái',6,20,3000000,3500000,'Mũi không hếch, phần gãy giữa mũi và trán mõm dài.','2022-03-21','\\src\\file\\petImage\\24.jpg',1),(25,1,'Dachshund (chó Lạp Xưởng/Xúc Xích)','Đen','2018-06-10','Đực',14,25,6500000,6000000,'Chân ngắn, tai dài cụp xuống rất dễ gần, thân thiện và trung thành với chủ.','2022-01-17','\\src\\file\\petImage\\25.jpg',0),(26,1,'Phú Quốc','Màu vện sọc đen','2020-02-18','Cái',12,48,2500000,2000000,'Có lông xoáy trên sống lưng (hay xoáy hai bên cổ) và cực khôn lanh, thông minh và rất trung thành.','2022-05-01','\\src\\file\\petImage\\26.jpg',1),(27,1,'Poodle','Nâu','2021-12-29','Đực',3,30,10000000,9000000,'Bộ lông xoăn cùng đôi tai rũ xuống, đầu khá tròn, vô cùng thân thiện, đáng yêu nên được nhiều phái đẹp yêu thích.','2022-03-08','\\src\\file\\petImage\\27.jpg',1),(28,1,'Pug','Vàng sẫm','2019-06-05','Đực',8,30,16000000,15000000,'Có bộ lông ngắn, Ngoài ra, đầu và mắt khá to, đặc biệt nhất là da mặt chảy xệ thành nếp, thích được vuốt ve và vui tính.','2022-05-15','\\src\\file\\petImage\\28.jpg',0),(29,1,'Alaska','Nâu Trắng','2020-08-13','Cái',35,60,12000000,11000000,'Đôi mắt tựa hạt hạnh nhân rất năng động, thân thiện và khá hiền lành.','2022-04-18','\\src\\file\\petImage\\29.jpg',0),(30,1,'Husky','Đen trắng','2019-03-12','Đực',17,51,8000000,7500000,'Dũng mãnh nhưng hài hước','2022-05-09','\\src\\file\\petImage\\30.jpg',0),(31,1,'Samoyed','Trắng','2020-06-11','Cái',17,50,7000000,6500000,'Tai tam giác với phần đỉnh hơi tù, luôn vểnh lên, đuôi cong cuộn tròn ở lưng, mắt hình hạnh nhân, rất đáng yêu, dễ gần và thông minh.','2022-05-01','\\src\\file\\petImage\\31.jpg',0),(32,1,'Pomeranian ','Trắng','2021-05-03','Cái',3,30,9000000,8500000,'hai má hóm, mõm nhỏ và có bộ lông dài với nhiều màu khác nhau (trắng/vàng/kem/nâu,...). Phốc sóc dễ dạy nhưng cũng khá tinh nghịch và thông minh.','2022-03-15','\\src\\file\\petImage\\32.jpg',0),(33,1,'Beagle','Vàng nâu','2021-06-10','Đực',33,40,5000000,4500000,'thân hình hơi vuông, tai cụp cùng bộ lông tam thể đặc trưng (trắng, đen và vàng nâu), luôn năng động, vui vẻ và có bản tính săn mồi','2022-05-23','\\src\\file\\petImage\\33.jpg',0),(34,2,'Xiêm','Trắng đen','2021-06-04','Cái',13.5,41,1800000,1200000,'Có màu đen trên đỉnh đầu','2022-03-15','\\src\\file\\petImage\\34.jpg',1),(35,2,'Xiêm','Vàng sẫm','2021-06-04','Cái',7,30,6000000,5500000,'Giống thuần chủng','2022-05-11','\\src\\file\\petImage\\35.jpg',0),(36,2,'Anh','Trắng','2021-11-17','Đực',7,30,4000000,3500000,'Có bộ lông dài','2022-04-05','\\src\\file\\petImage\\36.jpg',0),(37,2,'Anh','Xám đậm','2022-01-12','Cái',5,20,7500000,7000000,'Lông ngắn nhưng dày nên rất mềm mại','2022-06-07','\\src\\file\\petImage\\37.jpg',0),(38,2,'Ba tư','Vàng','2019-11-14','Đực',17,55,12050000,11500000,'Mặt xệ lông mềm mại dễ chịu, dễ nuôi.','2022-03-15','\\src\\file\\petImage\\38.jpg',0),(39,2,'Ba Tư','Nâu cam','2021-06-30','Đực',12,70,7500000,7000000,'Lông ngắn ria mép dài, lỗ tai nhọn.','2022-06-14','\\src\\file\\petImage\\39.jpg',0),(40,2,'Ba Tư','Trắng','2020-06-05','Cái',15,80,6500000,6000000,'Chinchilla là sự lai tạo giữa mèo Ba Tư và giống mèo bản địa của Nam Phi','2022-06-06','\\src\\file\\petImage\\40.jpg',0),(41,2,'Munchkin ','Xám tro','2021-06-11','Cái',9,33,15000000,14000000,'Chân ngắn cũn cỡn và dáng đi rất ngộ nghĩnh. Dù chân ngắn nhưng loài mèo cảnh này chưa bao giờ sợ độ cao, dù nhảy không tới nhưng mèo Munchkin sẽ luôn tìm cách để đi lên.','2022-05-09','\\src\\file\\petImage\\41.jpg',1),(42,2,'Tai cụp Scottish Fold','Xám đậm','2020-06-12','Cái',13,61,12000000,11000000,'Thích quấn chủ, ngọt ngào.','2022-04-05','\\src\\file\\petImage\\42.jpg',0),(43,6,'Ba đuôi','Trắng đỏ','2022-05-10','Cái',0.2,7,90000,70000,'Có số lượng rất nhiều cùng loại.','2022-06-08','\\src\\file\\petImage\\43.jpg',0),(44,6,'Hồng két','Hồng chấm trắng','2022-05-31','Đực',0.17,5,170000,100000,'Có rất nhiều tại shop','2022-06-15','\\src\\file\\petImage\\44.jpg',0),(45,6,'Xiêm','Xanh nước biển','2022-05-17','Đực',0.12,2,900000,700000,'Có rất nhiều tại shop','2022-06-15','\\src\\file\\petImage\\45.jpg',0),(46,6,'Xiêm','Vàng','2022-05-18','Đực',0.12,2,100000,70000,'Có rất nhiều tại shop','2022-06-15','\\src\\file\\petImage\\46.jpg',0),(47,6,'Hồng két','Hồng đỏ','2022-05-02','Cái',0.17,6,200000,150000,'Có rất nhiều tại Shop','2022-06-15','\\src\\file\\petImage\\47.jpg',0),(48,6,'Koi','Trắng đỏ','2022-04-12','Cái',0.5,12,1800000,1000000,'Có rất nhiều tại Shop','2022-06-15','\\src\\file\\petImage\\48.jpg',0),(50,7,'Chào mào','Nâu trắng đỏ','2022-03-14','Cái',0.12,11,700000,900000,'Lông mượt hót hay','2022-06-15','\\src\\file\\petImage\\50.jpg',0),(51,7,'Chích chòe','Đen trắng','2022-04-12','Đực',0.11,9,850000,650000,'Thích ăn côn trùng','2022-06-15','\\src\\file\\petImage\\51.jpg',0),(52,7,'Chích chòe','Đen nâu','2022-04-11','Cái',0.11,5,900000,700000,'Thích ăn côn trùng','2022-06-15','\\src\\file\\petImage\\52.jpg',0),(53,7,'Cu','Nâu','2022-03-14','Đực',0.2,10,770000,400000,'Gáy rất to và sung , và cánh có chấm bi','2022-06-15','\\src\\file\\petImage\\53.jpg',0),(54,7,'Cu','Xanh vàng','2022-04-05','Cái',0.2,8,450000,300000,'Gáy to và thích ăn sâu','2022-06-15','\\src\\file\\petImage\\54.jpg',0),(55,7,'Sáo','Đen nâu','2022-04-05','Đực',0.3,11,550000,400000,'Có thể nói tiếng người','2022-06-15','\\src\\file\\petImage\\55.jpg',0),(56,7,'Vàng anh','Vàng','2022-01-10','Cái',0.12,6,1500000,1000000,'Hót hay, mỏ đỏ','2022-04-10','\\src\\file\\petImage\\56.jpg',0),(57,7,'Vàng anh','Vàng','2022-02-08','Đực',0.13,5,150000,100000,'Mỏ đen, cánh đen, đuôi đen.','2022-03-01','\\src\\file\\petImage\\57.jpg',1),(58,7,'Vẹt','Đỏ','2021-04-08','Đực',0.4,40,750000,450000,'Mỏ trắng, lông nhiều màu','2022-02-13','\\src\\file\\petImage\\58.jpg',0),(59,8,'Chameleon','Xanh nhạt, vàng','2021-12-14','Đực',1,12,4000000,3000000,'Khi buồn, stress chúng sẽ có những đốm đen xung quanh cơ thể.','2022-01-11','\\src\\file\\petImage\\59.jpg',0),(60,8,'Chameleon','Đen, xanh dương.','2021-11-16','Đực',0.6,14,50000000,40000000,'Khi trở lên hung dữ có thể thay đổi sang các màu sẫm hơn như đen, xanh dương.','2022-02-02','\\src\\file\\petImage\\60.jpg',0),(61,8,'Chameleon','Vàng, Xanh lá','2021-12-08','Cái',0.7,13,3500000,3000000,'Meller’s Chameleon khá dữ tợn nếu như bạn là người mới học nuôi loài tắc kè hoa thì không nên lựa chọn nuôi loài này vội vì đòi hỏi kinh nghiệm nuôi tắc kè khá lâu mới nên chơi dòng này.','2022-02-16','\\src\\file\\petImage\\61.jpg',1),(62,8,'Chameleon','Vàng, nâu','2021-10-14','Đực',0.7,17,150000,100000,'ác màu sắc được nuôi làm cảnh nhiều thường là màu xanh lá cây, màu vàng, màu nâu có xọc chạy bên cạnh','2022-02-14','\\src\\file\\petImage\\62.jpg',0),(63,8,'Chameleon','Xanh lá','2021-01-11','Cái',0.8,20,4500000,3700000,'Flapneck tắc kè hoa, loài tắc kè này có màu xanh lá cây, màu nâu nhạt và một dải màu vàng chạy theo khắp cơ thể.','2022-03-24','\\src\\file\\petImage\\63.jpg',0),(64,8,'Chameleon','Xanh lá, nâu','2021-12-08','Đực',0.4,13,3500000,3000000,'Tuổi thọ của chúng là 3 năm, nhiệt độ môi trường nuôi là 23 – 25 độ C, độ ẩm 75 – 85%.','2022-03-21','\\src\\file\\petImage\\64.jpg',0),(65,8,'Chameleon','Xanh lá, đen, trắng','2022-01-03','Cái',0.4,12,2500000,2000000,'Được tìm thấy ở vùng Madagascar, màu sắc của chúng thường là màu xanh lá cây, màu vàng và một chú màu xanh lam quanh mắt và bàn chân.','2022-05-11','\\src\\file\\petImage\\65.jpg',0),(66,5,'Lang','Nâu trắng','2021-10-16','Cái',0.2,5,1000000,700000,'Được nuôi trong lồng và ăn những đồ ăn như hạt ngũ cốc, rau củ…','2022-04-18','\\src\\file\\petImage\\66.jpg',0),(67,5,'Hươu','Nâu, trắng','2021-11-23','Cái',0.2,4,980000,700000,'Bộ lông khá đẹp với phần lưng, đầu và đuôi có màu vàng như lông hươu. Phần bụng và tứ chi được bao phủ bởi một lớp lông trắng muốt.','2022-05-15','\\src\\file\\petImage\\67.jpg',0),(68,5,'Hamster','Xám khói','2021-06-18','Cái',0.1,4,900000,700000,'Loại Hamster winter white Rất hiền và rất dễ nuôi. Vì cơ thể của winter white cũng ko quá to như hamster bear, nên cũng ko cần dọn dẹp nhiều như bear','2022-01-10','\\src\\file\\petImage\\68.jpg',0),(69,5,'Hamtser','Nâu','2021-10-12','Đực',0.3,5,700000,400000,'Giống Hamster Bear','2022-02-15','\\src\\file\\petImage\\69.png',0),(70,5,'Hamster','Nâu trắng','2021-12-23','Cái',0.3,4,900000,700000,'Hamster Campell có nhiều màu sắc đa dạng: đen, xám, bò sữa hoặc những màu sáng như trắng, vàng chanh','2022-04-11','\\src\\file\\petImage\\70.png',0),(71,5,'Hamster','Vàng trắng','2022-02-09','Đực',0.11,3,550000,400000,'Hamster Roborovskil à loài nhỏ nhất trong các giống chuột hamster','2022-05-09','\\src\\file\\petImage\\71.png',0),(72,5,'Hamster','Xám khói','2021-12-15','Cái',0.12,4,1100000,800000,'Giống hamster Winter White ','2022-04-04','\\src\\file\\petImage\\72.png',0),(73,4,'Blanc de Hotot','Trắng','2021-11-09','Cái',4,44,900000,700000,'Có ‘eyeliner’ ở quanh đôi mắt màu nâu cực kỳ độc đáo. Nó có một cái đuôi nhỏ và đôi tai có kích thước trung bình dựng đứng. Bộ lông ngắn và bóng mượt và lông bảo vệ cực dài.','2022-02-06','\\src\\file\\petImage\\73.png',1),(74,4,'Mini Rex','Xám','2021-07-22','Đực',1.5,30,900000,500000,'Rất thân thiện và thường rất điềm tĩnh với trẻ nhỏ, nhưng có thể vặn vẹo nếu bị giữ quá chặt.','2022-02-22','\\src\\file\\petImage\\74.jpg',1),(75,4,'Mini Statin','Nâu trắng','2021-11-10','Cái',1.5,17,650000,450000,'Không có vấn đề sức khỏe nào được biết ngoài những lo ngại về sức khỏe chung của thỏ.','2022-04-12','\\src\\file\\petImage\\75.jpg',0),(76,4,'Hà Lan','Nâu','2022-01-13','Cái',0.5,9,740000,450000,'Cần không gian thoáng và tập thể dục thường xuyên','2022-05-10','\\src\\file\\petImage\\76.jpg',0),(77,4,'Hà Lan','Nâu','2021-11-16','Đực',0.9,9,500000,450000,' Rụng nhiều trong những tháng mùa hè, vì vậy bạn cần chải lông chò chúng nhiều hơn trong thời gian đó','2022-05-17','\\src\\file\\petImage\\77.jpg',0),(78,4,'Ba Lan','Nâu đen','2021-09-22','Cái',1.3,11,950000,700000,'Nên nuôi ở trong nhà. Vì kích thước nhỏ nên chúng cần ít không gian','2022-03-21','\\src\\file\\petImage\\78.jpg',0),(79,4,'Lion head','Xám nâu','2021-08-26','Đực',1.2,11,900000,500000,'Cần chải chuốt và chăm sóc lông nhiều hơn các giống khác vì lông có thể dễ bị nhão hoặc rối.','2022-03-14','\\src\\file\\petImage\\79.jpg',0),(80,4,'Minilop','Xám trắng','2021-11-10','Đực',2,20,1100000,700000,'Rất thích được ôm ấp và tương tác với con người, bao gồm cả việc được cưng nựng và cưng chiều. Cực kỳ ngọt ngào và dễ tính và dễ dàng huấn luyện, thỏ Minilop là vật nuôi lý tưởng cho trẻ em.','2022-01-03','\\src\\file\\petImage\\80.jpg',0),(81,4,'Rex','Nâu','2021-02-08','Đực',3.5,50,2300000,1500000,'Rex dễ bị tình trạng răng mọc quá mức, đầy hơi và mắc búi lông tapilu.','2022-01-17','\\src\\file\\petImage\\81.jpg',0),(82,7,'Chào mào','Nâu','2022-03-14','Cái',0.2,9,150000,100000,'Gáy rất hay','2022-06-08','\\src\\file\\petImage\\82.jpg',0);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_category`
--

DROP TABLE IF EXISTS `pet_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_category`
--

LOCK TABLES `pet_category` WRITE;
/*!40000 ALTER TABLE `pet_category` DISABLE KEYS */;
INSERT INTO `pet_category` VALUES (1,'Chó'),(2,'Mèo'),(4,'Thỏ'),(5,'Chuột'),(6,'Cá'),(7,'Chim'),(8,'Tắc kè hoa');
/*!40000 ALTER TABLE `pet_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_order`
--

DROP TABLE IF EXISTS `pet_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `pet_id` int DEFAULT NULL,
  `qr_to_web` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_501` (`pet_id`),
  KEY `fk_502` (`order_id`),
  CONSTRAINT `fk_501` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_502` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_order`
--

LOCK TABLES `pet_order` WRITE;
/*!40000 ALTER TABLE `pet_order` DISABLE KEYS */;
INSERT INTO `pet_order` VALUES (4,8,16,'\\src\\file\\petOrderQrcode\\16.pdf'),(5,8,17,'\\src\\file\\petOrderQrcode\\17.pdf'),(6,8,19,'\\src\\file\\petOrderQrcode\\19.pdf'),(9,18,24,'\\src\\file\\petOrderQrcode\\24.pdf'),(10,19,22,'\\src\\file\\petOrderQrcode\\22.pdf'),(11,20,22,'\\src\\file\\petOrderQrcode\\22.pdf'),(12,27,74,'\\src\\file\\petOrderQrcode\\74.pdf'),(13,33,57,'\\src\\file\\petOrderQrcode\\57.pdf'),(14,35,61,'\\src\\file\\petOrderQrcode\\61.pdf'),(15,38,73,'\\src\\file\\petOrderQrcode\\73.pdf'),(16,39,26,'\\src\\file\\petOrderQrcode\\26.pdf'),(17,41,27,'\\src\\file\\petOrderQrcode\\27.pdf');
/*!40000 ALTER TABLE `pet_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `product_category_id` int DEFAULT NULL,
  `pet_category_id` int DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `vendor_price` int DEFAULT NULL,
  `retail_price` int DEFAULT NULL,
  `barcode` varchar(45) DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_401` (`product_category_id`),
  KEY `fk_402` (`pet_category_id`),
  CONSTRAINT `fk_401` FOREIGN KEY (`product_category_id`) REFERENCES `product_category` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_402` FOREIGN KEY (`pet_category_id`) REFERENCES `pet_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,'Sữa bột predogen',3,1,'Tăng trí tuệ cho chó con.',93,40000,50000,'6179319',0,'\\src\\file\\productImage\\5.jpg'),(6,'Thức ăn Pedigree',3,1,'Giúp chó ăn khỏe hơn, ko lười ăn.',90,100000,150000,'7620670',0,'\\src\\file\\productImage\\6.jpg'),(7,'Thức ăn Ganador',3,1,'Tăng cường sự cứng cáp của xương',90,194000,250000,'3423118',0,'\\src\\file\\productImage\\7.jpg'),(8,'Thức ăn Smartheart',3,1,'Thức ăn cao cấp cho chó lớn.',8,1000000,1200000,'5739124',0,'\\src\\file\\productImage\\8.jpg'),(9,'Thức ăn hưu cơ Nautural core',3,1,'Bổ xung chất đạm cho chó con',76,100000,150000,'7601433',0,'\\src\\file\\productImage\\9.jpg'),(10,'Thức ăn Zenith',3,1,'Bổ sung chất đạm',48,500000,600000,'8623606',0,'\\src\\file\\productImage\\10.jpg'),(11,'Thức ăn Royal Canin',3,1,'Bổ xung canxi cho cún cưng',64,100000,150000,'8989484',0,'\\src\\file\\productImage\\11.jpg'),(12,'Thức ăn SmartHeart',3,1,'Thức ăn cao cấp cho chó của pháp',23,1000000,1300000,'7001691',0,'\\src\\file\\productImage\\12.png'),(13,'Thức ăn Royal Canin',3,1,'Bổ xung canxi cho chó con',70,700000,850000,'1278090',0,'\\src\\file\\productImage\\13.jpg'),(14,'Thức ăn me-o',3,2,'Vị Cá Ngừ',100,100000,150000,'1342156',0,'\\src\\file\\productImage\\14.jpg'),(15,'Thức ăn Royal Canin',3,2,'Bổ xung caxi cho mèo',70,95000,110000,'6914844',0,'\\src\\file\\productImage\\15.jpg'),(16,'Thức ăn Catsrang',3,2,'Thức ăn viên',70,100000,150000,'5118747',0,'\\src\\file\\productImage\\16.jpg'),(17,'Thức ăn Cat Eye',3,2,'Thức ăn cao cấp',20,1000000,1300000,'6792457',0,'\\src\\file\\productImage\\17.jpg'),(18,'Thức ăn Shanghal',3,6,'Thức ăn cho cá cảnh',200,50000,70000,'9134802',0,'\\src\\file\\productImage\\18.jpg'),(19,'Thức ăn Nhật',3,6,'Xuất sứ từ Nhật',100,60000,75000,'2076323',0,'\\src\\file\\productImage\\19.jpg'),(20,'Thức ăn Sapphire',3,6,'Thức ăn loại nhỏ',194,20000,30000,'9008214',0,'\\src\\file\\productImage\\20.jpg'),(21,'Thức ăn Aqua master',3,6,'Thức ăn cho cá lớn',30,1000000,1200000,'8198593',0,'\\src\\file\\productImage\\21.jpg'),(22,'Thức ăn Jolly',3,4,'Xuất xứ từ trung quốc',30,300000,350000,'7107693',0,'\\src\\file\\productImage\\22.jpg'),(23,'Fullvit',3,4,'Cung cấp nhiều chất sơ cho thỏ',63,100000,150000,'5516630',0,'\\src\\file\\productImage\\23.jpg'),(24,'Thức ăn Pronutri',3,4,'Bù khoáng bù nước cho thỏ con',50,700000,850000,'9630348',0,'\\src\\file\\productImage\\24.jpg'),(25,'Thức Hiền Bảo Khánh',3,7,'Thức an cho chim chích chòe',30,100000,150000,'5016967',0,'\\src\\file\\productImage\\25.png'),(26,'Ấu trùng Vương Việt Anh',3,7,'Ấu trùng siêu cao cấp',30,200000,250000,'1352903',0,'\\src\\file\\productImage\\26.png'),(27,'Cám Thúy Tuấn',3,7,'Cám Chim cao cấp cho chào mào',40,500000,650000,'6101941',0,'\\src\\file\\productImage\\27.jpg'),(28,'Cám trứng',3,7,'Cám loại 1',97,140000,155000,'4289631',0,'\\src\\file\\productImage\\28.jpg'),(29,'Thức ăn Sunburst',3,5,'Bổ sung dưỡng chất cực cao',50,100000,150000,'2148084',0,'\\src\\file\\productImage\\29.jpg'),(30,'Thức ăn Care complex',3,5,'Ngũ cốc dinh dưỡng cho hamster',24,50000,60000,'2194622',0,'\\src\\file\\productImage\\30.jpg'),(31,'Thức ăn Ramster boop',3,5,'Ngũ cốc dinh dưỡng cho hamster',62,40000,55000,'5023494',0,'\\src\\file\\productImage\\31.jpg'),(32,'Thức ăn Calcium',3,8,'Bổ xung canxi cho tắc kè hoa',20,500000,650000,'7313703',0,'\\src\\file\\productImage\\32.jpg'),(33,'Thức ăn Calcium',3,8,'Thức ăn bổ xung canxi cho tắc kè cỡ lớn',29,1000000,1200000,'9296415',0,'\\src\\file\\productImage\\33.jpg'),(34,'Lồng sắt ',6,8,'Lồng sắt nuôi tắc kè',6,2000000,2400000,'3343642',0,'\\src\\file\\productImage\\34.jpg'),(35,'Chuồng ',6,8,'Chuồng nuôi tắc kè',4,1000000,1200000,'7694653',0,'\\src\\file\\productImage\\35.jpg'),(36,'Bể kính',6,8,'Bể kính nuôi tắc kè hoa',5,1600000,1800000,'3404988',0,'\\src\\file\\productImage\\36.jpg'),(37,'Bát đựng',6,8,'Dùng để đựng nước uống hoặc thức ăn',49,80000,100000,'1235969',0,'\\src\\file\\productImage\\37.jpg'),(38,'Bể Kính',6,6,'Bể kính nuôi cá',5,1100000,1150000,'3332600',0,'\\src\\file\\productImage\\38.jpg'),(39,'Bể kính thủy sinh',6,6,'Bể kính nuôi cá có thủy sinh',5,3000000,3500000,'4860157',0,'\\src\\file\\productImage\\39.jpg'),(40,'Sỏi nuôi cá',6,6,'Bán theo túi',15,50000,65000,'5477803',0,'\\src\\file\\productImage\\40.jpg'),(41,'Vượt lưới',6,6,'Nhỏ gọn siêu bền',100,10000,13000,'6885968',0,'\\src\\file\\productImage\\41.jpg'),(42,'Lồng chim',6,7,'Lồng Chim Bằng gỗ',20,100000,150000,'9881907',0,'\\src\\file\\productImage\\42.jpg'),(43,'Lồng chim',6,7,'Lòng Chim bằng kim loại',20,100000,150000,'3988633',0,'\\src\\file\\productImage\\43.jpg'),(44,'Khây đựng',6,7,'Đùng để đựng đồ ăn thức uống',20,50000,65000,'8396983',0,'\\src\\file\\productImage\\44.jpg'),(45,'Balo',6,1,'Balo cho xinh xắn',15,290000,350000,'2959599',0,'\\src\\file\\productImage\\45.jpg'),(46,'Rọ mỗm',6,1,'Rọ mỗn cho chó cỡ lớn',20,3000000,3500000,'7511341',0,'\\src\\file\\productImage\\46.jpg'),(47,'Vòng cổ',6,1,'Vòng cổ có gai đầy cá tính',18,3100000,450000,'3348555',0,'\\src\\file\\productImage\\47.crdownload'),(48,'Đây đeo',6,1,'Vô cùng chắc chắn',100,100000,150000,'5089768',0,'\\src\\file\\productImage\\48.jpg'),(49,'Bát đựng',3,1,'Bát đựng thức ăn nước uống cho chó',87,100000,150000,'9293539',0,'\\src\\file\\productImage\\49.jpg'),(50,'Khây đựng nước',6,5,'Đựng nước uống cho hamster',20,100000,150000,'5408555',0,'\\src\\file\\productImage\\50.jpg'),(51,'Lồng ',4,5,'Lồng chuột rất to dủ để 2 con ỏ',10,2100000,250000,'8782714',0,'\\src\\file\\productImage\\51.crdownload'),(52,'Lồng chuột',6,5,'Lồng nuôi chuột to',6,1200000,1500000,'4420241',0,'\\src\\file\\productImage\\52.jpg'),(53,'Khây đựng',6,2,'Đựng thức ăn và nước uống cho mèo',20,100000,150000,'3446038',0,'\\src\\file\\productImage\\53.jpg'),(54,'Vòng cổ',6,2,'Vòng cổ của doremon',30,200000,250000,'3700501',0,'\\src\\file\\productImage\\54.png'),(55,'Balo',6,2,'Balo đựng mèo',17,1100000,1510000,'8155873',0,'\\src\\file\\productImage\\55.jpg'),(56,'Balo',6,2,'Balo đựng mèo ',13,1100000,1500000,'5988130',0,'\\src\\file\\productImage\\56.jpg'),(57,'Giường',6,2,'Giường để mèo chơi đùa',7,2100000,2500000,'3684832',0,'\\src\\file\\productImage\\57.jpg'),(58,'Váy',5,1,'Váy đỏ xinh xắn',20,500000,600000,'1766605',0,'\\src\\file\\productImage\\58.jpg'),(59,'Đồ lân',5,1,'Đồ đi chơi tết',14,230000,290000,'3331261',0,'\\src\\file\\productImage\\59.jpg'),(60,'Đồ cosplay',5,1,'Đồ đi chơi halloween',13,410000,515000,'8933385',0,'\\src\\file\\productImage\\60.jpg'),(61,'Đồ cosplay',5,2,'Đồ đi chơi halloween',13,400000,450000,'8326691',0,'\\src\\file\\productImage\\61.jpg'),(62,'Áo',5,2,'Áo supreme cực ngầu',30,100000,150000,'6224708',0,'\\src\\file\\productImage\\62.jpg'),(63,'Áo',5,2,'Đồ nhẫn giả',50,100000,150000,'3678376',0,'\\src\\file\\productImage\\63.jpg'),(64,'Cây câu ',4,2,'Để huấn luyện mèo',20,100000,150000,'2883165',0,'\\src\\file\\productImage\\64.jpg'),(65,'Trụ dây cuốn',4,2,'Để mèo leo trèo',20,100000,150000,'7230312',0,'\\src\\file\\productImage\\65.png'),(66,'Cầu trược',4,2,'Cầu trược cho mèo',20,100000,150000,'9787689',0,'\\src\\file\\productImage\\66.png'),(67,'Xương nhựa',4,1,'Để chó gặm nhắm',20,100000,150000,'3004985',0,'\\src\\file\\productImage\\67.jpg'),(68,'Gói hình xương',4,1,'Để chó chơi, nằm nghỉ',20,100000,150000,'3435564',0,'\\src\\file\\productImage\\68.jpg'),(69,'Xương nhựa',4,1,'Để chó gặm nhắm',20,100000,150000,'1681104',0,'\\src\\file\\productImage\\69.jpg'),(70,'Dây cuộn',4,1,'Để chó chơi đùa',20,100000,150000,'5559987',0,'\\src\\file\\productImage\\70.jpg'),(71,'Lòng',6,4,'Nuôi nhốt thỏ',16,1100000,1650000,'3749961',0,'\\src\\file\\productImage\\71.jpg'),(72,'Cà rốt bằng vải',4,4,'Để thỏ gặm nhắm',20,100000,150000,'9998347',0,'\\src\\file\\productImage\\72.jpg'),(73,'Chuồng thỏ',6,4,'Nuôi nhốt thỏ kiển',15,1200000,1500000,'7331208',0,'\\src\\file\\productImage\\73.jpg'),(74,'Bóng cỏ',4,1,'Tự nhiên thuân thiện môi trường',20,100000,150000,'5122672',0,'\\src\\file\\productImage\\74.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (3,'Thức ăn'),(4,'Đồ chơi'),(5,'Quần áo'),(6,'Phụ kiện');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_import`
--

DROP TABLE IF EXISTS `product_import`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_import` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_7` (`product_id`),
  CONSTRAINT `fk_7` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_import`
--

LOCK TABLES `product_import` WRITE;
/*!40000 ALTER TABLE `product_import` DISABLE KEYS */;
INSERT INTO `product_import` VALUES (1,8,20,'2022-06-12'),(4,5,3,'2022-06-12'),(6,5,5,'2022-06-12'),(8,9,100,'2022-06-16'),(9,10,50,'2022-06-16'),(10,11,70,'2022-06-16'),(11,12,30,'2022-06-16'),(12,13,70,'2022-06-16'),(13,14,100,'2022-06-16'),(14,15,70,'2022-06-16'),(15,16,70,'2022-06-16'),(16,17,20,'2022-06-16'),(17,18,200,'2022-06-16'),(18,19,100,'2022-06-16'),(19,20,200,'2022-06-16'),(20,21,30,'2022-06-16'),(21,22,30,'2022-06-16'),(22,23,70,'2022-06-16'),(23,24,50,'2022-06-16'),(24,25,30,'2022-06-16'),(25,26,30,'2022-06-16'),(26,27,40,'2022-06-16'),(27,28,100,'2022-06-16'),(28,29,50,'2022-06-16'),(29,30,30,'2022-06-16'),(30,31,62,'2022-06-16'),(31,32,20,'2022-06-16'),(32,33,2010,'2022-06-16'),(33,34,6,'2022-06-16'),(34,35,4,'2022-06-16'),(35,36,5,'2022-06-16'),(36,37,50,'2022-06-16'),(37,38,5,'2022-06-16'),(38,39,5,'2022-06-16'),(39,40,15,'2022-06-16'),(40,41,100,'2022-06-16'),(41,42,20,'2022-06-16'),(42,43,20,'2022-06-16'),(43,44,20,'2022-06-16'),(44,45,15,'2022-06-16'),(45,46,20,'2022-06-16'),(46,47,20,'2022-06-16'),(47,48,100,'2022-06-16'),(48,49,100,'2022-06-16'),(49,50,20,'2022-06-16'),(50,51,10,'2022-06-16'),(51,52,1,'2022-06-16'),(52,53,20,'2022-06-16'),(53,54,30,'2022-06-16'),(54,55,17,'2022-06-16'),(55,56,13,'2022-06-16'),(56,57,7,'2022-06-16'),(57,58,20,'2022-06-16'),(58,59,14,'2022-06-16'),(59,60,13,'2022-06-16'),(60,61,13,'2022-06-16'),(61,62,30,'2022-06-16'),(62,63,50,'2022-06-16'),(63,64,20,'2022-06-16'),(64,65,20,'2022-06-16'),(65,66,20,'2022-06-16'),(66,67,20,'2022-06-16'),(67,68,20,'2022-06-16'),(68,69,20,'2022-06-16'),(69,70,20,'2022-06-16'),(70,71,16,'2022-06-16'),(71,72,20,'2022-06-16'),(72,73,15,'2022-06-16'),(73,74,20,'2022-06-16');
/*!40000 ALTER TABLE `product_import` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_order`
--

DROP TABLE IF EXISTS `product_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_601` (`product_id`),
  KEY `fk_602` (`order_id`),
  CONSTRAINT `fk_601` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_602` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_order`
--

LOCK TABLES `product_order` WRITE;
/*!40000 ALTER TABLE `product_order` DISABLE KEYS */;
INSERT INTO `product_order` VALUES (3,9,5,4,600000),(10,12,6,1,150000),(11,13,28,3,465000),(12,15,9,2,300000),(13,16,9,2,300000),(14,16,5,3,150000),(15,17,9,2,300000),(16,17,30,3,180000),(17,17,33,1,1200000),(18,17,37,1,100000),(19,18,9,2,300000),(20,18,30,3,180000),(21,19,6,2,300000),(22,20,6,2,300000),(23,20,9,2,300000),(24,21,11,1,150000),(25,22,11,1,150000),(26,22,12,1,1300000),(27,23,11,1,150000),(28,23,12,1,1300000),(29,24,11,1,150000),(30,24,12,1,1300000),(31,24,49,3,450000),(32,25,11,1,150000),(33,25,12,1,1300000),(34,25,49,3,450000),(35,26,11,1,150000),(36,26,12,3,3900000),(37,26,49,7,1050000),(38,27,8,2,2400000),(39,28,7,2,500000),(40,29,9,2,300000),(41,30,9,2,300000),(42,31,7,1,250000),(43,32,9,3,450000),(44,32,10,2,1200000),(45,32,5,3,150000),(46,33,8,1,1200000),(47,34,7,2,500000),(48,35,8,3,3600000),(49,38,23,7,1050000),(50,38,20,6,180000),(51,39,6,1,150000),(52,39,8,1,1200000),(53,39,7,4,1000000),(54,41,6,2,300000),(55,41,8,2,2400000),(56,41,9,4,600000);
/*!40000 ALTER TABLE `product_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `mobileNumber` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `CCCD` varchar(200) DEFAULT NULL,
  `dateBirth` date DEFAULT NULL,
  `dateStart` date DEFAULT NULL,
  `department_id` varchar(40) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `securityQuestion` varchar(200) DEFAULT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_1` (`department_id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'Trần Văn Khang','admin@gmail.com','0381738134','Vietnam','1234567891234','2002-02-12','2022-05-05','AD','admin','What primary school did tou attend?','LBT','true'),(7,'Nguyen Van Khang','khangku14@gmail.com','0908675145','Bien Hoa','272916528','2022-05-04','2022-05-03','KD','123','Where u from ?','vietnam','false'),(8,'Trần Duy Khánh','tranduykhanh@gmail.com','0334013453','Bến Tre','321729562','2002-05-20','2022-03-14','KD','Khanh:13','Quê ở đâu ?','Bến Tre','true'),(9,'Lê Thanh Giang','lethanhgiang@gmail.com','0918677014','Gò vấp','321728582','2002-07-16','2022-04-13','NS','Giang:13','Quê ở đâu ?','Gò Vấp','false'),(10,'Nguyễn Việt Hoàng','nguyenviethoang@gmail.com','0365897166','Tân Phú','321405867','2002-11-11','2022-02-23','NV','Hoang:13','Quê ở đâu ?','Tân Phú','true'),(11,'Trần Thị Tuyết Linh','tranthituyetlinh@gmail.com','0917466014','Bình Thuận','327619847','2002-08-24','2022-05-10','NV','Linh:13','Quê ở đâu ?','Bình Thuận','false'),(12,'Tô Thái Duy','tothaiduy@gmail.com','0349183756','Quy Nhơn','328716496','2002-11-21','2022-03-07','NV','Duy:13','Quê ở đâu ?','Quy Nhơn','true'),(13,'Nguyễn Trần Lưỡng Hà','nguyentranluongha@gmail.com','0344771682','Biên Hòa','320191715','2002-09-09','2022-05-17','TN','Ha:13','Quê ở đâu ?','Biên Hòa','false'),(14,'Nguyễn Xuân Tuấn Kiệt','nguyenxuantuankiet@gmail.com','0918677012','Biên Hòa','321746596','2002-07-22','2022-07-13','KD','Kiet:13','Quê ở đâu ?','Biên Hòa','false'),(15,'Trần Vũ Phong','tranvuphong@gmail.com','0918677014','Bến Tre','228837361','2000-06-09','2022-03-29','NS','Phong:13','Món ăn yêu thích là gì?','Cá kho','true'),(16,'Lê Thị Gia Hân','lethigiahan@gmail.com','0344817161','Lâm Đồng','283647586','2001-06-19','2022-03-14','NV','Han:13','Thích đi du lịch ở đâu ?','Đà Lạt','false'),(17,'Phạm Huy Hùng','phamhuyhung@gmail.com','0918375647','Lâm Đồng','247581945','2002-06-13','2022-04-12','TC','Hung:13','Quê ở đâu ?','Lâm Đồng','true'),(18,'Nguyễn Văn Khang','nguyenvankhang@gmail.com','0937182734','Đồng Nai','273618456','2002-06-09','2022-04-30','KD','Khang:13','Quê ở đâu ?','Đồng Nai','true'),(19,'Đinh Võ Xuân Hoàn','dinhvoxuanhoan@gmail.com','0381273845','Gò vấp','324104856','2002-07-15','2022-05-09','NS','Hoan:13','Quê ở đâu ?','Gò Vấp','true'),(20,'Nguyễn Lê Thái Hiền','nguyenlethaihien@gmail.com','0337164534','Thủ Đức','321876354','2002-06-18','2022-06-06','NS','Hien:13','Quê ở đâu ?','Thủ Đức','false'),(21,'Phạm Thái Mỹ Duyên','phamthaimyduyen@gmail.com','0908675235','Gò Vấp','272916528','2002-06-19','2022-06-13','NV','Duyen:13','Quê ở đâu ?','Gò Vấp','true'),(22,'Võ Nguyễn Thanh Tú','vonguyenthanhtu@gmail.com','0331234567','Bến Tre','321456745','2002-12-06','2022-06-05','NV','Tu:13','Quê ở đâu ?','Bến Tre ?','false'),(23,'Bùi Văn Thuận ','buivanthuan@gmail.com','0918377546','Bến Tre','231938475','2002-07-11','2022-04-04','TC','Thuan:13','Quê ở đâu ?','Bến tre','true'),(24,'Dương Bảo Tâm','duongbaotam@gmail.com','0317182734','Bến Tre','321817161','2002-05-13','2022-05-08','NV','Tam:13','Quê ở đâu ?','Bến Tre','false'),(25,'Lê Minh Hải Đăng','leminhhaidang@gmail.com','0318172631','Bến Tre','231847561','2002-05-23','2022-05-08','NV','Dang:13','Quê ở đâu ?','Bến Tre','true'),(26,'Lâm Ngọc Huy','lamngochuy@gmail.com','0917361823','Biên Hòa ','213918234','2002-04-08','2022-05-09','TN','Huy:13','Quê ở đâu ?','Biên Hòa','true'),(27,'Nguyễn Bao Trung','nguyenbaotrung@gmail.com','0983716351','Vũng Tàu','237149531','2002-05-27','2022-03-08','NV','Trung:13','Thích chơi game gì ?','Liên minh','true'),(28,'Phan Tấn Trung','phantantrung@gmail.com','0334917361','Sa Đéc','321817161','2002-05-06','2022-03-10','NV','Trung:13','Thích chơi game gì ?','Liên minh','false'),(29,'Trần Văn Liêm','tranvanliem@gmail.com','0313817361','Bắc Ninh','321817506','2002-07-20','2022-01-23','NS','Liem:13','Nhà có mấy anh chị em ?','2','false'),(30,'Lại Văn Sâm','laivansam@gmail.com','0391837461','Cali','321948123','2002-09-30','2022-01-13','TC','Sam:13','Có khát nước không ?','Có','true'),(31,'Nguyễn Trấn Thành','nguyentranthanh@gmail.com','0319283145','TPHCM','321039485','2002-12-09','2021-11-15','KD','Thanh:13','Có hay khóc không ?','có','true'),(32,'Lê Thị Ngọc Trân','lethingoctran@gmail.com','0918377164','Phú Yên','321039456','2003-02-10','2022-02-08','NV','Tran:13','Có giòn không ?','Không giòn','false');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-21 19:11:30
