-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: db_devmngsys
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_brw`
--

DROP TABLE IF EXISTS `tb_brw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_brw` (
  `id` varchar(30) NOT NULL,
  `devid` varchar(20) DEFAULT NULL,
  `brwerid` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_brw`
--

LOCK TABLES `tb_brw` WRITE;
/*!40000 ALTER TABLE `tb_brw` DISABLE KEYS */;
INSERT INTO `tb_brw` VALUES ('brwID_20191219001','Dev001',-1,'周四 12月 19 10:38:24 CST 2019','设备转移'),('brwID_20191219002','Dev001',-2,'周四 12月 19 10:49:54 CST 2019','设备转移'),('brwID_20191219003','Dev001',-2,'周四 12月 19 10:49:59 CST 2019','设备转移'),('brwID_20191219004','Dev001',-2,'周四 12月 19 10:50:09 CST 2019','设备转移'),('brwID_20191219005','Dev001',-1,'周四 12月 19 10:52:11 CST 2019','设备转移'),('brwID_20191219006','Dev001',-1,'周四 12月 19 10:54:11 CST 2019','设备转移'),('brwID_20191219007','Dev001',-1,'周四 12月 19 11:18:06 CST 2019','设备转移'),('brwID_20191219008','Dev001',-1,'周四 12月 19 11:19:21 CST 2019','设备转移'),('brwID_20191219009','Dev001',-1,'周四 12月 19 11:20:19 CST 2019','设备转移'),('brwID_20191219010','Dev001',-1,'周四 12月 19 11:21:42 CST 2019','设备转移'),('brwID_20191219011','Dev001',-1,'周四 12月 19 11:22:01 CST 2019','设备转移'),('brwID_20191219012','Dev001',-1,'周四 12月 19 12:21:04 CST 2019','设备转移'),('brwID_20191223001','Dev001',-2,'周一 12月 23 11:06:45 CST 2019','设备转移'),('brwID_20191223002','Dev001',-2,'周一 12月 23 11:07:01 CST 2019','设备转移'),('brwID_20191223003','Dev001',-1,'周一 12月 23 12:45:52 CST 2019','设备转移'),('brwID_20191223004','Dev001',-1,'周一 12月 23 14:34:02 CST 2019','设备转移'),('brwID_20191223005','Dev001',0,'周一 12月 23 14:38:02 CST 2019','设备转移'),('brwID_20200116001','Dev001',0,'周四 1月 16 09:23:57 CST 2020','设备转移'),('brwID_20200119001','Dev001',-1,'周日 1月 19 13:43:50 CST 2020','设备转移'),('brwID_20200119002','Dev001',-1,'周日 1月 19 13:47:20 CST 2020','设备转移'),('brwID_20200119003','Dev001',-1,'周日 1月 19 15:28:56 CST 2020','设备转移'),('brwID_20200119004','Dev001',-1,'周日 1月 19 15:30:37 CST 2020','设备转移'),('brwID_20200119005','Dev001',-1,'周日 1月 19 15:33:08 CST 2020','设备转移'),('brwID_20200119006','Dev001',-1,'周日 1月 19 15:56:57 CST 2020','设备转移'),('brwID_20200119007','Dev001',-1,'周日 1月 19 16:31:06 CST 2020','设备转移'),('brwID_20200119008','Dev001',-1,'周日 1月 19 16:38:41 CST 2020','设备转移'),('brwID_20200119009','Dev001',-1,'周日 1月 19 16:41:50 CST 2020','设备转移'),('brwID_20200119010','Dev001',-1,'周日 1月 19 16:42:55 CST 2020','设备转移'),('brwID_20200119011','Dev001',-1,'周日 1月 19 16:45:42 CST 2020','设备转移'),('brwID_20200119012','Dev001',-1,'周日 1月 19 16:52:40 CST 2020','设备转移'),('brwID_20200119013','Dev001',-1,'周日 1月 19 16:54:24 CST 2020','设备转移'),('brwID_20200119014','Dev001',-1,'周日 1月 19 16:55:27 CST 2020','设备转移'),('brwID_20200119015','Dev001',-1,'周日 1月 19 16:56:12 CST 2020','设备转移'),('brwID_20200119016','Dev001',-1,'周日 1月 19 17:04:00 CST 2020','设备转移'),('brwID_20200119017','Dev001',-1,'周日 1月 19 17:04:44 CST 2020','设备转移'),('brwID_20200119018','Dev001',-1,'周日 1月 19 17:05:44 CST 2020','设备转移'),('brwID_20200119019','Dev001',-1,'周日 1月 19 17:06:19 CST 2020','设备转移'),('brwID_20200310001','Dev002',0,'周二 3月 10 09:05:52 CST 2020','设备转移'),('brwID_20200310002','Dev002',-1,'周二 3月 10 09:06:36 CST 2020','设备转移'),('brwID_20200310003','Dev002',0,'周二 3月 10 11:04:48 CST 2020','设备转移'),('brwID_20200310004','Dev002',-1,'周二 3月 10 11:05:11 CST 2020','设备转移'),('brwID_20200313001','Dev001',0,'周五 3月 13 17:04:41 CST 2020',''),('brwID_20200313002','Dev001',0,'周五 3月 13 17:05:02 CST 2020','');
/*!40000 ALTER TABLE `tb_brw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_devinfo`
--

DROP TABLE IF EXISTS `tb_devinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_devinfo` (
  `id` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `des` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `req` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_devinfo`
--

LOCK TABLES `tb_devinfo` WRITE;
/*!40000 ALTER TABLE `tb_devinfo` DISABLE KEYS */;
INSERT INTO `tb_devinfo` VALUES ('Dev001','设备001','工号：0','','',''),('Dev002','设备002','工号：-1','','',''),('Dev003','设备003','库存中','','','');
/*!40000 ALTER TABLE `tb_devinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rtn`
--

DROP TABLE IF EXISTS `tb_rtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_rtn` (
  `id` varchar(30) NOT NULL,
  `devid` varchar(20) DEFAULT NULL,
  `rtnerid` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rtn`
--

LOCK TABLES `tb_rtn` WRITE;
/*!40000 ALTER TABLE `tb_rtn` DISABLE KEYS */;
INSERT INTO `tb_rtn` VALUES ('rtnID_20200313001','Dev001',0,'周五 3月 13 15:50:15 CST 2020',''),('rtnID_20200313002','Dev001',0,'周五 3月 13 17:04:52 CST 2020','');
/*!40000 ALTER TABLE `tb_rtn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userinfo`
--

DROP TABLE IF EXISTS `tb_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tb_userinfo` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `pos` varchar(20) DEFAULT NULL,
  `dep` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `isadmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userinfo`
--

LOCK TABLES `tb_userinfo` WRITE;
/*!40000 ALTER TABLE `tb_userinfo` DISABLE KEYS */;
INSERT INTO `tb_userinfo` VALUES (-2,'用户','user','123456','','','no','no','',0),(-1,'管理员','admin','123456','','','no','no','',1),(0,'超级管理员','root','123456','','','no','no','',1);
/*!40000 ALTER TABLE `tb_userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-17 17:00:35
