-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: LNCSA
-- ------------------------------------------------------
-- Server version	5.6.25

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
-- Table structure for table `app_info`
--

DROP TABLE IF EXISTS `app_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageCode` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_info`
--

LOCK TABLES `app_info` WRITE;
/*!40000 ALTER TABLE `app_info` DISABLE KEYS */;
INSERT INTO `app_info` VALUES (1,'2016-06-26 18:30:56','玩音游之前记得先看老黄历！（误',NULL,'http://app.lncsoftware.cn/muger/','disable','音游狗老黄历'),(2,'2016-06-26 18:30:56','本网站',NULL,'http://lncsoftware.cn','working','岭南软件园协会主页');
/*!40000 ALTER TABLE `app_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` text,
  `createDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `previewSentences` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgwrtdbqvt9ucntp82nd3yiuec` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (384,'Nulla tincidunt purus ac risus porta varius. Quisque volutpat metus odio, et eleifend erat mattis eget. In ultrices tortor nulla, nec aliquet nisl aliquam at. Nam maximus pretium erat. Cras quis lectus tincidunt, feugiat velit efficitur, venenatis leo. Suspendisse non ipsum erat. Ut id efficitur enim, ut vestibulum enim. Proin id dignissim metus. Nunc est turpis, cursus porta libero a, tempor eleifend ligula. Praesent sed interdum risus. Vestibulum auctor, ex at euismod hendrerit, nisi magna porta ante, sed molestie tellus ante vitae turpis. Nam semper placerat mi sed sollicitudin. Nam eleifend nisi lorem, vel eleifend neque vehicula a. Etiam laoreet tellus quis tellus lacinia, et venenatis sapien ultrices.','2016-01-01 18:30:05','2016-04-01 18:09:00','Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...','shown','Lorem Ipsum',134),(385,'Donec dignissim egestas nibh, in dignissim enim placerat et. Maecenas tristique nisi viverra velit mattis euismod. Nullam sit amet tincidunt ligula. Duis consectetur placerat sem tempor hendrerit. Aenean dignissim varius est ut euismod. Cras a nibh ac felis rutrum finibus non a sapien. Sed vel maximus lectus. Proin dictum magna urna, eu pharetra ex placerat ut. Aliquam placerat mattis suscipit. In id sollicitudin odio. Sed et egestas nulla. Ut mi augue, dapibus eu pretium in, convallis ut justo. Nulla imperdiet, odio ac lacinia mollis, tortor justo egestas mauris, nec suscipit metus mi ut augue. Curabitur in nibh nibh. Donec blandit euismod consequat. Morbi mollis neque sed est suscipit tristique.','2016-05-01 13:05:09','2016-05-07 10:05:43','Donec dignissim egestas nibh, in dignissim enim placerat et...','shown','Curabitur tristique nibh eu velit',135),(386,'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.','2016-06-26 17:19:00','2016-06-26 17:20:30','There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...','shown','What is Lorem Ipsum?',133),(387,'Integer lobortis malesuada hendrerit. Mauris sagittis vitae enim et posuere. Donec vel nulla mauris. Nulla sed augue id nisi efficitur tristique eu ut ex. Curabitur commodo efficitur ipsum a convallis. Mauris ac ante interdum, ornare sem vel, consectetur ante. Cras justo lacus, porta in viverra eu, suscipit ut augue. Sed non arcu libero.\n\nNullam ut nunc et lorem bibendum posuere sit amet non purus. Aliquam erat volutpat. Donec posuere rhoncus dolor, quis interdum orci placerat et. Nunc maximus euismod nulla, et mattis arcu ornare ac. Cras porta quis nisl non dictum. Vivamus nulla libero, ultricies quis tellus ut, pretium finibus arcu. Nunc ullamcorper justo at ornare mollis. Fusce vel venenatis est. Donec semper lacus purus, a convallis elit vulputate vel. Sed in commodo purus, ut commodo libero.\n\n','2016-06-20 18:19:00','2016-06-26 17:19:00','Integer lobortis malesuada hendrerit. Mauris sagittis vitae enim et posuere....','shown','Donec in leo tortor. Morbi.',136);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag`
--

DROP TABLE IF EXISTS `article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKenqeees0y8hkm7x1p1ittuuye` (`article_id`),
  KEY `FK3nvn435qf5rn1e9ph51e3r55h` (`tag_id`),
  CONSTRAINT `FK3nvn435qf5rn1e9ph51e3r55h` FOREIGN KEY (`tag_id`) REFERENCES `topics` (`id`),
  CONSTRAINT `FKenqeees0y8hkm7x1p1ittuuye` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
INSERT INTO `article_tag` VALUES (78,384,22),(79,384,27),(80,385,22),(81,385,27),(82,386,22),(83,386,27),(84,386,28),(85,387,24),(86,387,23),(87,387,22);
/*!40000 ALTER TABLE `article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bulletin`
--

DROP TABLE IF EXISTS `bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `imageLink` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bulletin`
--

LOCK TABLES `bulletin` WRITE;
/*!40000 ALTER TABLE `bulletin` DISABLE KEYS */;
INSERT INTO `bulletin` VALUES (73,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sollicitudin risus elit, in iaculis risus molestie nec. Duis auctor sit amet ipsum sed ornare. Pellentesque habitant morbi tristique senectus et.','2016-05-04 14:06:33',NULL,NULL,'app_guide'),(74,'Nunc vulputate, dolor placerat eleifend tempus, mauris sapien porta ipsum, nec dictum magna ante sed magna. Nunc iaculis, erat eu.','2016-05-04 14:20:10',NULL,NULL,'app_info'),(75,'Aliquam purus sapien, rutrum et tellus id, auctor ornare risus. Quisque tincidunt velit nibh, sed convallis nulla volutpat vitae. Vestibulum.','2016-06-03 14:20:10',NULL,NULL,'main_page'),(76,'Nunc rutrum velit ut odio volutpat vulputate. Ut tortor ligula, vehicula vitae lorem nec, ultricies elementum massa. Nam et velit.','2015-12-04 12:50:30','https://docs.docker.com/assets/images/logo-docker-main.svg','http://cn.bing.com','main_page_top'),(77,'Maecenas commodo dolor sed ex dignissim cursus. Proin purus tortor, pellentesque non urna vel, ultricies convallis nulla. Fusce placerat pellentesque.','2015-12-04 22:53:12',NULL,NULL,'article'),(78,'**全部代码都我自己一个人写好辛苦的**\n\n这里是协会网站的码喵一只，求Gayhub互contribute。','2016-01-01 15:56:45','https://avatars2.githubusercontent.com/u/3370303?v=3&s=460','https://github.com/CattenLinger','contact_info'),(79,'**协会QQ群**\n\n请注明届别专业班别姓名…… (PS：校外人士勿进谢谢)','2016-02-04 23:44:32',NULL,'http://jq.qq.com/?_wv=1027&k=2JF3aVW','contact_info'),(80,'**如果网站出现问题请联系我**\n\n - 微博@阪本先生- (最后有个小横线别漏了噢)\n - 邮箱cattenlinger@outlook.com','2016-02-04 23:44:32','http://tp1.sinaimg.cn/1689733680/180/5748763685/1','http://weibo.com/lingerkong','contact_info'),(81,'OurCoders - (我们程序员)','2016-06-26 18:34:55',NULL,'http://ourcoders.com/home/','friend_link'),(82,'广州展云信息技术有限公司','2016-06-26 18:34:55',NULL,'http://zhanyun360.com','friend_link'),(83,'CSS样式框架 - Bootstrap','2016-06-26 18:34:55',NULL,'http://getbootstrap.com','friend_link'),(84,'代码托管 - Github','2016-06-26 18:34:55',NULL,'https://github.com/','friend_link'),(85,'Markdown渲染引擎 - markdown-js','2016-06-26 18:34:55',NULL,'https://github.com/evilstreak/markdown-js','friend_link'),(86,'Markdown编辑器 - Bootstrap Markdown','2016-06-26 18:34:55',NULL,'http://www.codingdrama.com/bootstrap-markdown/','friend_link');
/*!40000 ALTER TABLE `bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commit`
--

DROP TABLE IF EXISTS `commit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `replyTo_id` int(11) DEFAULT NULL,
  `targetArticle_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5aceaqe6o8muc8o5xw75sb1ee` (`replyTo_id`),
  KEY `FKm9hh317l2bemdjtiqjfnjd7kp` (`targetArticle_id`),
  KEY `FKiem2jy266bnljhha8r264hq8m` (`user_id`),
  CONSTRAINT `FK5aceaqe6o8muc8o5xw75sb1ee` FOREIGN KEY (`replyTo_id`) REFERENCES `commit` (`id`),
  CONSTRAINT `FKiem2jy266bnljhha8r264hq8m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm9hh317l2bemdjtiqjfnjd7kp` FOREIGN KEY (`targetArticle_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commit`
--

LOCK TABLES `commit` WRITE;
/*!40000 ALTER TABLE `commit` DISABLE KEYS */;
/*!40000 ALTER TABLE `commit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (25,'green','login','/user/*'),(26,'red','administrator','/admin/*'),(27,'blue','author','/article/*');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (21,'green','Network'),(22,'green','Test'),(23,'blue','System'),(24,'red','Java'),(25,'red','C#'),(26,'red','Java Script'),(27,'gray','Lorem Ipsum'),(28,'blue','Web');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contactInfo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (133,'000000','root','Administrator','000000'),(134,'000000','catten','Catten Linger','000000'),(135,'000000','snipy','Snipy Linger','000000'),(136,'1111111','kitty','Hello Kitty','1111111'),(137,'1111111','bikaqiu','ビガツウ','1111111'),(138,'222222','sakamoto','阪本さん','222222'),(139,'333333','linger','Linger Kong','333333'),(140,'000111','test_user1','Test User','000000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rights`
--

DROP TABLE IF EXISTS `user_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `right_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33bady8c7jrw9flq6dg082bkc` (`right_id`),
  KEY `FKah7mr21d6p3csieikiy7nj0m2` (`user_id`),
  CONSTRAINT `FK33bady8c7jrw9flq6dg082bkc` FOREIGN KEY (`right_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKah7mr21d6p3csieikiy7nj0m2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rights`
--

LOCK TABLES `user_rights` WRITE;
/*!40000 ALTER TABLE `user_rights` DISABLE KEYS */;
INSERT INTO `user_rights` VALUES (241,25,133),(242,25,134),(243,25,136),(244,25,137),(245,25,138),(246,25,139),(247,27,133),(248,27,134),(249,27,137),(250,27,138),(251,26,133),(252,26,134);
/*!40000 ALTER TABLE `user_rights` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-30 15:34:25
