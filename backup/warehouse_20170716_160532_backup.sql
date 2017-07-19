-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: warehouse
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chucvu`
--

DROP TABLE IF EXISTS `chucvu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chucvu` (
  `machucvu` varchar(255) NOT NULL,
  `tenchucvu` varchar(255) NOT NULL,
  PRIMARY KEY (`machucvu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chucvu`
--

LOCK TABLES `chucvu` WRITE;
/*!40000 ALTER TABLE `chucvu` DISABLE KEYS */;
INSERT INTO `chucvu` VALUES ('001','TEST'),('002','kế toán');
/*!40000 ALTER TABLE `chucvu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `congtrinh`
--

DROP TABLE IF EXISTS `congtrinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `congtrinh` (
  `macongtrinh` varchar(255) NOT NULL,
  `diachicongtrinh` varchar(255) NOT NULL,
  `ngaybatdau` varchar(255) NOT NULL,
  `ngaycapnhat` varchar(255) NOT NULL,
  `ngayketthuc` varchar(255) NOT NULL,
  `tencongtrinh` varchar(255) NOT NULL,
  `trangthai` varchar(255) NOT NULL,
  PRIMARY KEY (`macongtrinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `congtrinh`
--

LOCK TABLES `congtrinh` WRITE;
/*!40000 ALTER TABLE `congtrinh` DISABLE KEYS */;
INSERT INTO `congtrinh` VALUES ('001','Hai Phong','2017-07-10 00:00:00','2017-07-10 20:33:06','2017-07-30 00:00:00','Cong trinh so 1.','CREATED'),('002','Hà Nội','2017-07-15 00:00:00','2017-07-15 10:09:44','2017-07-18 00:00:00','Công trình nhà máy xí nghiệp thuốc lá Thăng Long.','CREATED');
/*!40000 ALTER TABLE `congtrinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donvi`
--

DROP TABLE IF EXISTS `donvi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donvi` (
  `madonvi` varchar(255) NOT NULL,
  `tendonvi` varchar(255) NOT NULL,
  PRIMARY KEY (`madonvi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donvi`
--

LOCK TABLES `donvi` WRITE;
/*!40000 ALTER TABLE `donvi` DISABLE KEYS */;
INSERT INTO `donvi` VALUES ('001','kg'),('002','bao');
/*!40000 ALTER TABLE `donvi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dutoan`
--

DROP TABLE IF EXISTS `dutoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dutoan` (
  `mahanghoa` varchar(255) NOT NULL,
  `macongtrinh` varchar(255) NOT NULL,
  `khoiluongdutoan` int(11) NOT NULL,
  `tongkhoiluong` int(11) NOT NULL,
  PRIMARY KEY (`mahanghoa`,`macongtrinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dutoan`
--

LOCK TABLES `dutoan` WRITE;
/*!40000 ALTER TABLE `dutoan` DISABLE KEYS */;
INSERT INTO `dutoan` VALUES ('001','001',0,605),('001','002',0,10),('002','002',0,7),('003','001',0,170),('003','002',0,15);
/*!40000 ALTER TABLE `dutoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hanghoa`
--

DROP TABLE IF EXISTS `hanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hanghoa` (
  `mahanghoa` varchar(255) NOT NULL,
  `gia` int(11) NOT NULL,
  `donvi` varchar(255) NOT NULL,
  `maloaihanghoa` varchar(255) NOT NULL,
  `tenhanghoa` varchar(255) NOT NULL,
  PRIMARY KEY (`mahanghoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hanghoa`
--

LOCK TABLES `hanghoa` WRITE;
/*!40000 ALTER TABLE `hanghoa` DISABLE KEYS */;
INSERT INTO `hanghoa` VALUES ('001',2200,'001','001','thep'),('002',1500,'001','001','sat'),('003',1000,'002','001','xi mang');
/*!40000 ALTER TABLE `hanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL,
  `datemodify` varchar(255) NOT NULL,
  `datenew` varchar(255) NOT NULL,
  `dateold` varchar(255) NOT NULL,
  `khoiluongcu` int(11) NOT NULL,
  `khoiluongmoi` int(11) NOT NULL,
  `macongtrinh` varchar(255) NOT NULL,
  `mahanghoacu` varchar(255) NOT NULL,
  `mahanghoamoi` varchar(255) NOT NULL,
  `tennguoidung` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,'INSERT','2017-07-10 20:40:30','2017-07-10 00:00:00','1970-01-01 00:00:00',0,50,'001','','003','kenydinh','IN'),(2,'INSERT','2017-07-10 20:41:45','2017-07-10 00:00:00','1970-01-01 00:00:00',0,100,'001','','001','kenydinh','IN'),(3,'INSERT','2017-07-10 20:42:03','2017-07-10 00:00:00','1970-01-01 00:00:00',0,120,'001','','003','kenydinh','IN'),(4,'INSERT','2017-07-10 20:42:18','2017-07-10 00:00:00','1970-01-01 00:00:00',0,300,'001','','002','kenydinh','IN'),(5,'INSERT','2017-07-10 20:42:42','2017-07-10 00:00:00','1970-01-01 00:00:00',0,60,'001','','001','kenydinh','OUT'),(6,'INSERT','2017-07-10 20:43:04','2017-07-10 00:00:00','1970-01-01 00:00:00',0,150,'001','','003','kenydinh','OUT'),(7,'INSERT','2017-07-10 20:43:21','2017-07-10 00:00:00','1970-01-01 00:00:00',0,50,'001','','002','kenydinh','OUT'),(8,'INSERT','2017-07-10 20:43:35','2017-07-10 00:00:00','1970-01-01 00:00:00',0,150,'001','','002','kenydinh','OUT'),(9,'INSERT','2017-07-10 20:44:03','2017-07-10 00:00:00','1970-01-01 00:00:00',0,50,'001','','002','kenydinh','IN'),(10,'UPDATE','2017-07-10 20:44:24','2017-07-10 00:00:00','2017-07-10 00:00:00',50,100,'001','002','002','kenydinh','IN'),(11,'UPDATE','2017-07-10 21:19:52','2017-07-10 00:00:00','2017-07-10 00:00:00',100,200,'001','002','002','kenydinh','IN'),(12,'UPDATE','2017-07-10 21:20:12','2017-07-10 00:00:00','2017-07-10 00:00:00',200,200,'001','002','001','kenydinh','IN'),(13,'UPDATE','2017-07-10 21:20:36','2017-07-10 00:00:00','2017-07-10 00:00:00',300,300,'001','002','001','kenydinh','IN'),(14,'INSERT','2017-07-10 21:20:58','2017-07-10 00:00:00','1970-01-01 00:00:00',0,300,'001','','002','kenydinh','IN'),(15,'DELETE','2017-07-10 21:21:19','1970-01-01 00:00:00','2017-07-10 00:00:00',300,0,'001','002','','kenydinh','IN'),(16,'INSERT','2017-07-15 10:10:21','2017-07-15 00:00:00','1970-01-01 00:00:00',0,10,'002','','001','kenydinh','IN'),(17,'INSERT','2017-07-15 14:35:23','2017-07-15 00:00:00','1970-01-01 00:00:00',0,15,'002','','003','kenydinh','IN'),(18,'INSERT','2017-07-15 14:35:32','2017-07-15 00:00:00','1970-01-01 00:00:00',0,7,'002','','002','kenydinh','IN'),(19,'Thêm mới','2017-07-16 09:40:12','2017-07-06 00:00:00','1970-01-01 00:00:00',0,5,'001','','001','kenydinh','Nhập dữ liệu');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaihanghoa`
--

DROP TABLE IF EXISTS `loaihanghoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loaihanghoa` (
  `maloaihanghoa` varchar(255) NOT NULL,
  `tenloaihanghoa` varchar(255) NOT NULL,
  PRIMARY KEY (`maloaihanghoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaihanghoa`
--

LOCK TABLES `loaihanghoa` WRITE;
/*!40000 ALTER TABLE `loaihanghoa` DISABLE KEYS */;
INSERT INTO `loaihanghoa` VALUES ('001','Vat Lieu Xay Dung');
/*!40000 ALTER TABLE `loaihanghoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoiban`
--

DROP TABLE IF EXISTS `nguoiban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoiban` (
  `manguoiban` varchar(255) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `tennguoiban` varchar(255) NOT NULL,
  PRIMARY KEY (`manguoiban`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoiban`
--

LOCK TABLES `nguoiban` WRITE;
/*!40000 ALTER TABLE `nguoiban` DISABLE KEYS */;
INSERT INTO `nguoiban` VALUES ('001','def','dddddd@dddd','123456789','abc');
/*!40000 ALTER TABLE `nguoiban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taikhoan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) NOT NULL,
  `diachi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `machucvu` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'\0','Root','root@warehouse','root','e10adc3949ba59abbe56e057f20f883e',1,'000000000','root'),(3,'\0','Ha Noi','duymatngu@gmail.com','001','e10adc3949ba59abbe56e057f20f883e',2,'123456789','khuongduy'),(4,'\0','Ha Noi','kenydinh@gmail.com','001','7eba2da76ef1b7b03ae15689d6dbad34',1,'01633397627','kenydinh'),(5,'\0','test','test@test','001','98f6bcd4621d373cade4e832627b4f6',2,'987654321','test');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vatlieunhap`
--

DROP TABLE IF EXISTS `vatlieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vatlieunhap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `khoiluong` int(11) NOT NULL,
  `macongtrinh` varchar(255) NOT NULL,
  `mahanghoa` varchar(255) NOT NULL,
  `manguoinhap` varchar(255) NOT NULL,
  `ngaynhap` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vatlieunhap`
--

LOCK TABLES `vatlieunhap` WRITE;
/*!40000 ALTER TABLE `vatlieunhap` DISABLE KEYS */;
INSERT INTO `vatlieunhap` VALUES (1,50,'001','003','kenydinh','2017-07-10 00:00:00'),(2,100,'001','001','kenydinh','2017-07-10 00:00:00'),(3,120,'001','003','kenydinh','2017-07-10 00:00:00'),(4,300,'001','001','kenydinh','2017-07-10 00:00:00'),(5,200,'001','001','kenydinh','2017-07-10 00:00:00'),(7,10,'002','001','kenydinh','2017-07-15 00:00:00'),(8,15,'002','003','kenydinh','2017-07-15 00:00:00'),(9,7,'002','002','kenydinh','2017-07-15 00:00:00'),(10,5,'001','001','kenydinh','2017-07-06 00:00:00');
/*!40000 ALTER TABLE `vatlieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vatlieutonkho`
--

DROP TABLE IF EXISTS `vatlieutonkho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vatlieutonkho` (
  `mahanghoa` varchar(255) NOT NULL,
  `macongtrinh` varchar(255) NOT NULL,
  `khoiluongton` int(11) NOT NULL,
  PRIMARY KEY (`mahanghoa`,`macongtrinh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vatlieutonkho`
--

LOCK TABLES `vatlieutonkho` WRITE;
/*!40000 ALTER TABLE `vatlieutonkho` DISABLE KEYS */;
INSERT INTO `vatlieutonkho` VALUES ('001','001',545),('001','002',10),('002','001',-200),('002','002',7),('003','001',20),('003','002',15);
/*!40000 ALTER TABLE `vatlieutonkho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vatlieuxuat`
--

DROP TABLE IF EXISTS `vatlieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vatlieuxuat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `khoiluong` int(11) NOT NULL,
  `macongtrinh` varchar(255) NOT NULL,
  `mahanghoa` varchar(255) NOT NULL,
  `manguoixuat` varchar(255) NOT NULL,
  `ngayxuat` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vatlieuxuat`
--

LOCK TABLES `vatlieuxuat` WRITE;
/*!40000 ALTER TABLE `vatlieuxuat` DISABLE KEYS */;
INSERT INTO `vatlieuxuat` VALUES (1,60,'001','001','kenydinh','2017-07-10 00:00:00'),(2,150,'001','003','kenydinh','2017-07-10 00:00:00'),(3,50,'001','002','kenydinh','2017-07-10 00:00:00'),(4,150,'001','002','kenydinh','2017-07-10 00:00:00');
/*!40000 ALTER TABLE `vatlieuxuat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-16 16:05:33
