-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.50-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema woc2
--

CREATE DATABASE IF NOT EXISTS woc2;
USE woc2;

--
-- Temporary table structure for view `ccomments`
--
DROP TABLE IF EXISTS `ccomments`;
DROP VIEW IF EXISTS `ccomments`;
CREATE TABLE `ccomments` (
  `comId` int(11),
  `userId` int(11),
  `userName` varchar(20),
  `userLevel` int(11),
  `userStatus` int(11),
  `comComment` text,
  `releaseTime` datetime,
  `nickName` varchar(20)
);

--
-- Temporary table structure for view `commodities`
--
DROP TABLE IF EXISTS `commodities`;
DROP VIEW IF EXISTS `commodities`;
CREATE TABLE `commodities` (
  `comId` int(11),
  `ownerId` int(11),
  `ownerName` varchar(20),
  `nickName` varchar(20),
  `comName` varchar(15),
  `property` int(11),
  `comTypeName` varchar(20),
  `price` decimal(10,2),
  `comRegion` varchar(45),
  `describe` text,
  `status` int(11),
  `releaseTime` datetime,
  `offTime` datetime,
  `requiredScore` int(11),
  `picturePath` varchar(50),
  `desire` varchar(45),
  `count(distinct `commodity`.comId)` bigint(21)
);

--
-- Temporary table structure for view `commoditiesinf`
--
DROP TABLE IF EXISTS `commoditiesinf`;
DROP VIEW IF EXISTS `commoditiesinf`;
CREATE TABLE `commoditiesinf` (
  `comId` int(11),
  `ownerId` int(11),
  `ownerName` varchar(20),
  `nickName` varchar(20),
  `comName` varchar(15),
  `property` int(11),
  `comTypeName` varchar(20),
  `price` decimal(10,2),
  `comRegion` varchar(45),
  `describe` text,
  `status` int(11),
  `releaseTime` datetime,
  `offTime` datetime,
  `requiredScore` int(11),
  `picturePath` varchar(50),
  `desire` varchar(45),
  `comfocus` bigint(21)
);

--
-- Temporary table structure for view `friends`
--
DROP TABLE IF EXISTS `friends`;
DROP VIEW IF EXISTS `friends`;
CREATE TABLE `friends` (
  `userId` int(11),
  `friendId` int(11),
  `friendName` varchar(20),
  `friendLevel` int(11),
  `friendStatus` int(11),
  `location` varchar(45),
  `phone` varchar(20),
  `postCode` int(11),
  `email` varchar(20),
  `gender` varchar(6),
  `hobby` varchar(45),
  `major` varchar(20),
  `score` int(11),
  `nickName` varchar(20),
  `friendRegion` varchar(45),
  `friendPhoto` varchar(60)
);

--
-- Definition of table `attention`
--

DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention` (
  `attId` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`attId`),
  KEY `FK_User_Attention` (`userId`),
  KEY `FK_Focus_Commodity` (`comId`),
  CONSTRAINT `FK_Focus_Commodity` FOREIGN KEY (`comId`) REFERENCES `commodity` (`comId`),
  CONSTRAINT `FK_User_Attention` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attention`
--

/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
INSERT INTO `attention` (`attId`,`comId`,`userId`) VALUES 
 (9,78,38),
 (11,76,37),
 (13,90,28);
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;


--
-- Definition of table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `blId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `managerId` int(11) DEFAULT NULL,
  `blLevel` int(11) NOT NULL,
  `blTime` datetime NOT NULL,
  `lastTime` int(11) NOT NULL,
  PRIMARY KEY (`blId`),
  KEY `FK_Manager_BlackList` (`managerId`),
  KEY `FK_User_BlackList` (`userId`),
  CONSTRAINT `FK_Manager_BlackList` FOREIGN KEY (`managerId`) REFERENCES `manager` (`managerId`),
  CONSTRAINT `FK_User_BlackList` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `blacklist`
--

/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;


--
-- Definition of table `ccomment`
--

DROP TABLE IF EXISTS `ccomment`;
CREATE TABLE `ccomment` (
  `comCommentId` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `releaseTime` datetime NOT NULL,
  `comComment` text,
  PRIMARY KEY (`comCommentId`),
  KEY `FK_Commodity_CComment` (`comId`),
  KEY `FK_User_CComment` (`userId`),
  CONSTRAINT `FK_Commodity_CComment` FOREIGN KEY (`comId`) REFERENCES `commodity` (`comId`),
  CONSTRAINT `FK_User_CComment` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ccomment`
--

/*!40000 ALTER TABLE `ccomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ccomment` ENABLE KEYS */;


--
-- Definition of table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `chatId` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` int(11) DEFAULT NULL,
  `receiverId` int(11) DEFAULT NULL,
  `chatContent` varchar(200) NOT NULL,
  PRIMARY KEY (`chatId`),
  KEY `FK_receiverId` (`senderId`),
  KEY `FK_senderId` (`receiverId`),
  CONSTRAINT `FK_receiverId` FOREIGN KEY (`senderId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_senderId` FOREIGN KEY (`receiverId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chat`
--

/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


--
-- Definition of table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `comId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `comName` varchar(15) NOT NULL,
  `property` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `comregion` varchar(45) NOT NULL,
  `describe` text,
  `status` int(11) NOT NULL,
  `offTime` datetime DEFAULT NULL,
  `requiredScore` int(11) NOT NULL,
  `releaseTime` datetime NOT NULL,
  PRIMARY KEY (`comId`),
  KEY `FK_User_Commodity` (`userId`),
  CONSTRAINT `FK_User_Commodity` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commodity`
--

/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` (`comId`,`userId`,`comName`,`property`,`type`,`price`,`comregion`,`describe`,`status`,`offTime`,`requiredScore`,`releaseTime`) VALUES 
 (76,29,'初音未来歌姬计划',0,3,'250.00','天津','音乐节奏类游戏',0,NULL,0,'2014-08-18 14:15:46'),
 (77,28,'测试商品书籍',0,4,'0.00','天津','全新',0,NULL,0,'2014-08-18 14:16:00'),
 (78,29,'灵魂献祭',0,3,'250.00','天津','act游戏',0,NULL,0,'2014-08-18 14:17:12'),
 (79,28,'测试商品其它',0,9,'0.00','天津','全新',0,NULL,0,'2014-08-18 14:17:27'),
 (80,32,'索尼克赛车',0,3,'150.00','天津','赛车类游戏',0,NULL,0,'2014-08-18 14:18:34'),
 (81,27,'测试商品LYH',0,8,'0.00','天津','',0,NULL,0,'2014-08-18 14:19:09'),
 (82,34,'测试商品LYH2',0,6,'0.00','天津','',0,NULL,0,'2014-08-18 14:20:34'),
 (83,34,'测试商品LUH3',0,2,'200.00','天津','',0,NULL,0,'2014-08-18 14:26:05'),
 (84,36,'测试商品LYH4',0,8,'0.00','天津','',0,NULL,0,'2014-08-18 14:29:44'),
 (85,38,'陈氏秘籍',0,1,'100.00','天津','武功心法，绝世独传',0,NULL,0,'2014-08-18 15:07:23'),
 (86,29,'杀戮地带',0,3,'240.00','天津','第一人称射击类游戏',0,NULL,0,'2014-08-18 15:39:17'),
 (87,32,'噬神者2',0,3,'210.00','天津','act游戏，以快节奏的刷怪而体现其游戏性。',0,NULL,0,'2014-08-18 15:41:35'),
 (88,35,'真三国无双',0,3,'160.00','天津','割草！！！',0,NULL,0,'2014-08-18 15:42:36'),
 (89,38,'1',0,1,'1.00','1','',0,NULL,0,'2014-08-18 16:09:37'),
 (90,28,'书籍',0,1,'12.00','天津','全新',0,NULL,0,'2014-08-19 14:07:11');
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;


--
-- Definition of table `commodity_type`
--

DROP TABLE IF EXISTS `commodity_type`;
CREATE TABLE `commodity_type` (
  `comTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `comTypeName` varchar(20) NOT NULL,
  PRIMARY KEY (`comTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commodity_type`
--

/*!40000 ALTER TABLE `commodity_type` DISABLE KEYS */;
INSERT INTO `commodity_type` (`comTypeId`,`comTypeName`) VALUES 
 (1,'交易书籍'),
 (2,'交易用品'),
 (3,'交易其它'),
 (4,'交换书籍'),
 (5,'交换用品'),
 (6,'交换其它'),
 (7,'漂流书籍'),
 (8,'漂流用品'),
 (9,'漂流其它');
/*!40000 ALTER TABLE `commodity_type` ENABLE KEYS */;


--
-- Definition of table `desire`
--

DROP TABLE IF EXISTS `desire`;
CREATE TABLE `desire` (
  `desire` varchar(45) DEFAULT NULL,
  `desId` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) DEFAULT NULL,
  PRIMARY KEY (`desId`),
  KEY `FK_Commodity_Desire` (`comId`),
  CONSTRAINT `FK_Commodity_Desire` FOREIGN KEY (`comId`) REFERENCES `commodity` (`comId`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `desire`
--

/*!40000 ALTER TABLE `desire` DISABLE KEYS */;
INSERT INTO `desire` (`desire`,`desId`,`comId`) VALUES 
 (NULL,56,76),
 ('',57,77),
 (NULL,58,78),
 (NULL,59,79),
 (NULL,60,80),
 (NULL,61,81),
 (NULL,62,82),
 (NULL,63,83),
 (NULL,64,84),
 (NULL,65,85),
 (NULL,66,86),
 (NULL,67,87),
 (NULL,68,88),
 (NULL,69,89),
 (NULL,70,90);
/*!40000 ALTER TABLE `desire` ENABLE KEYS */;


--
-- Definition of table `detail_inf`
--

DROP TABLE IF EXISTS `detail_inf`;
CREATE TABLE `detail_inf` (
  `infId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `postCode` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `gender` varchar(6) NOT NULL,
  `hobby` varchar(45) DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `nickName` varchar(20) NOT NULL,
  `userRegion` varchar(45) DEFAULT NULL,
  `userPhoto` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`infId`),
  KEY `FK_userId` (`userId`),
  CONSTRAINT `FK_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detail_inf`
--

/*!40000 ALTER TABLE `detail_inf` DISABLE KEYS */;
INSERT INTO `detail_inf` (`infId`,`userId`,`location`,`phone`,`postCode`,`email`,`gender`,`hobby`,`major`,`score`,`nickName`,`userRegion`,`userPhoto`) VALUES 
 (27,27,'天津','',0,'','女','天津','',0,'罗永洪','','19389072271440.jpg'),
 (28,28,'天津','18202600899',0,'','男','天津','软件工程',0,'李秀星','天津','18662256471200.jpg'),
 (29,29,'天津市南开区卫津路','15620623836',300071,'408618870@qq.com','男','天津市南开区卫津路','软件工程',0,'许精策1','天津市','19803576714173.jpg'),
 (30,30,'天津',NULL,0,NULL,'男',NULL,NULL,0,'李秀星2',NULL,NULL),
 (31,31,'天津',NULL,0,NULL,'男',NULL,NULL,0,'李秀星3',NULL,NULL),
 (32,32,'天津市南开区卫津路','15620623836',300071,'408618870@qq.com','男','天津市南开区卫津路','软件工程',0,'许精策2','天津','19755965438257.jpg'),
 (33,33,'天津','15022761971',0,'1213652432@qq.com','男','天津','软件学院',0,'陈展昊','天津','4082695651119.jpg'),
 (34,34,'天津','',0,'','男','天津','',0,'罗永洪2','','19356840028493.jpg'),
 (35,35,'天津市南开区卫津路','15620623836',300071,'408618870@qq.com','男','天津市南开区卫津路','软件工程',0,'许精策3','天津市','19844536476033.jpg'),
 (36,36,'天津','',0,'','女','天津','',0,'罗永洪3','','19870435734901.jpg'),
 (37,37,'天津','',0,'','男','天津','',0,'陈展昊2','','19869645986938.jpg'),
 (38,38,'天津','',0,'','男','天津','',0,'陈展昊3','','24716546092974.jpg'),
 (39,39,'天津',NULL,0,NULL,'男',NULL,NULL,0,'许精策',NULL,NULL),
 (40,40,'天津','',0,'','男','天津','软件工程',0,'李秀星','','17092168752845.jpg'),
 (41,41,'天津',NULL,0,NULL,'男',NULL,NULL,0,'李秀星',NULL,NULL);
/*!40000 ALTER TABLE `detail_inf` ENABLE KEYS */;


--
-- Definition of table `friend`
--

DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `relationshipId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `friendId` int(11) DEFAULT NULL,
  PRIMARY KEY (`relationshipId`),
  KEY `FK_FriendId` (`friendId`),
  KEY `FK_UserId_Friend` (`userId`),
  CONSTRAINT `FK_FriendId` FOREIGN KEY (`friendId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_UserId_Friend` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `friend`
--

/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` (`relationshipId`,`userId`,`friendId`) VALUES 
 (26,33,32),
 (27,38,34),
 (28,38,28),
 (29,37,32),
 (30,37,38),
 (31,29,38),
 (32,28,35),
 (33,28,32),
 (34,28,33),
 (35,28,37),
 (36,33,32),
 (37,33,34),
 (38,33,38),
 (39,37,29),
 (40,37,28),
 (41,29,28),
 (42,29,32),
 (43,30,28),
 (44,30,29),
 (45,30,31),
 (46,30,32),
 (47,31,27),
 (48,31,28),
 (49,31,30),
 (50,32,33),
 (51,32,34),
 (52,32,27),
 (53,38,29),
 (54,38,35),
 (55,38,36),
 (56,36,28),
 (57,36,29),
 (58,36,31),
 (59,37,30),
 (60,28,38),
 (61,28,28);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;


--
-- Definition of table `manager`
--

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerId` int(11) NOT NULL AUTO_INCREMENT,
  `managerName` varchar(30) NOT NULL,
  `managerPassword` varchar(15) NOT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `manager`
--

/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;


--
-- Definition of table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `ordSubTime` datetime NOT NULL,
  `ordSucTime` datetime DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FK_Commodity_Order` (`comId`),
  KEY `FK_User_Order` (`userId`),
  CONSTRAINT `FK_Commodity_Order` FOREIGN KEY (`comId`) REFERENCES `commodity` (`comId`),
  CONSTRAINT `FK_User_Order` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order`
--

/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`orderId`,`comId`,`userId`,`ordSubTime`,`ordSucTime`,`name`,`location`,`email`,`phone`) VALUES 
 (15,78,37,'2014-08-18 14:32:33',NULL,'陈展昊','天津','1213652432@qq.com','15022761971'),
 (16,88,28,'2014-08-18 15:45:18',NULL,'李秀星','天津','safsadf','111'),
 (17,78,28,'2014-08-18 16:09:31',NULL,'李秀星','天津','1212','1212'),
 (18,78,33,'2014-08-19 10:42:18',NULL,'陈展昊','天津','dgzxczh@163.com','15022761971'),
 (19,90,28,'2014-08-19 14:11:01',NULL,'李秀星','天津','12121@12.com','31231231');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;


--
-- Definition of table `picture`
--

DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `PictureId` int(11) NOT NULL AUTO_INCREMENT,
  `comId` int(11) DEFAULT NULL,
  `Path` varchar(50) NOT NULL,
  PRIMARY KEY (`PictureId`),
  KEY `FK_Commodity_Picture` (`comId`),
  CONSTRAINT `FK_Commodity_Picture` FOREIGN KEY (`comId`) REFERENCES `commodity` (`comId`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `picture`
--

/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` (`PictureId`,`comId`,`Path`) VALUES 
 (73,76,'19019503028298.jpg'),
 (74,76,'19019503568974.jpg'),
 (75,77,'19033692760307.jpg'),
 (76,77,'19033694611853.jpg'),
 (77,78,'19106084862799.jpg'),
 (78,78,'19106085429132.jpg'),
 (79,79,'19120388436252.jpg'),
 (80,79,'19120390612484.jpg'),
 (81,80,'19188115337050.jpg'),
 (82,80,'19188115879125.jpg'),
 (83,81,'19222621704104.jpg'),
 (84,81,'19222624150907.jpg'),
 (85,82,'19307760156800.jpg'),
 (86,82,'19307761896853.jpg'),
 (87,83,'19639057021953.jpg'),
 (88,83,'19639058790929.jpg'),
 (89,84,'19858087439290.jpg'),
 (90,84,'19858088003758.jpg'),
 (91,85,'22116989561082.jpg'),
 (92,85,'22116990425044.jpg'),
 (93,86,'24030479990091.jpg'),
 (94,86,'24030480789676.jpg'),
 (95,87,'24168720489192.jpg'),
 (96,87,'24168723249952.jpg'),
 (97,88,'24229345215764.jpg'),
 (98,88,'24229347709218.jpg'),
 (99,89,'25850957052882.jpg'),
 (100,90,'18648782175298.jpg'),
 (101,90,'18648784111731.jpg');
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;


--
-- Definition of table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `postName` varchar(20) NOT NULL,
  `postProperty` int(11) NOT NULL,
  `postEnterNum` int(11) NOT NULL,
  `postTime` datetime NOT NULL,
  `postScore` decimal(65,1) NOT NULL,
  `scoreCount` int(11) NOT NULL,
  `lastComTime` datetime DEFAULT NULL,
  PRIMARY KEY (`postId`),
  KEY `FK_User_Post` (`userId`),
  CONSTRAINT `FK_User_Post` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post`
--

/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`postId`,`userId`,`postName`,`postProperty`,`postEnterNum`,`postTime`,`postScore`,`scoreCount`,`lastComTime`) VALUES 
 (32,33,'太极张三丰',103,0,'2014-08-18 14:17:19','1.0',1,'2014-08-18 14:17:19'),
 (33,33,'求离散课件',202,0,'2014-08-18 14:18:10','0.0',0,'2014-08-18 14:18:10'),
 (34,33,'水上公园野餐',603,0,'2014-08-18 14:18:52','1.0',1,'2014-08-18 14:18:52'),
 (35,27,'这是LYH发的第一个贴',402,0,'2014-08-18 14:24:12','0.0',0,'2014-08-18 14:24:12'),
 (36,36,'这是LYH发的第二个贴',101,0,'2014-08-18 14:27:01','2.0',2,'2014-08-18 14:27:01'),
 (37,28,'今日',101,0,'2014-08-19 14:14:24','1.0',1,'2014-08-19 14:14:24');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;


--
-- Definition of table `post_comment`
--

DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment` (
  `postComId` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `postComContent` text NOT NULL,
  `postReplyId` int(11) NOT NULL,
  `postComTime` datetime NOT NULL,
  PRIMARY KEY (`postComId`),
  KEY `FK_Post_PComment` (`postId`),
  KEY `FK_User_PComment` (`userId`),
  CONSTRAINT `FK_Post_PComment` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`),
  CONSTRAINT `FK_User_PComment` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_comment`
--

/*!40000 ALTER TABLE `post_comment` DISABLE KEYS */;
INSERT INTO `post_comment` (`postComId`,`postId`,`userId`,`postComContent`,`postReplyId`,`postComTime`) VALUES 
 (29,36,38,'好帖！！',0,'2014-08-18 14:27:44'),
 (30,35,36,'发帖啊',0,'2014-08-18 14:30:12'),
 (31,36,36,'这贴不错',29,'2014-08-18 14:30:29'),
 (32,36,37,'同意',31,'2014-08-18 14:31:01'),
 (33,35,37,'好吧',30,'2014-08-18 14:34:27'),
 (35,36,29,'二楼是我的！！！！',0,'2014-08-18 14:35:46'),
 (36,36,28,'我也同意',32,'2014-08-18 15:52:22'),
 (37,36,28,'说的冯绍峰',32,'2014-08-18 16:19:32'),
 (38,36,28,'宋德福',31,'2014-08-18 16:20:23'),
 (39,36,33,'顶！！',0,'2014-08-19 10:08:55'),
 (40,37,28,'很好',0,'2014-08-19 14:15:13'),
 (41,37,28,'很好',40,'2014-08-19 14:15:24'),
 (42,37,28,'很好',40,'2014-08-19 14:15:32'),
 (43,37,28,'很好',42,'2014-08-19 14:15:40');
/*!40000 ALTER TABLE `post_comment` ENABLE KEYS */;


--
-- Definition of table `post_resource`
--

DROP TABLE IF EXISTS `post_resource`;
CREATE TABLE `post_resource` (
  `resId` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) DEFAULT NULL,
  `resPath` varchar(50) NOT NULL,
  PRIMARY KEY (`resId`),
  KEY `FK_Post_Resource` (`postId`),
  CONSTRAINT `FK_Post_Resource` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_resource`
--

/*!40000 ALTER TABLE `post_resource` DISABLE KEYS */;
INSERT INTO `post_resource` (`resId`,`postId`,`resPath`) VALUES 
 (5,32,'19112753686882.jpg'),
 (6,33,'19164129269135.jpg'),
 (7,34,'default.jpg'),
 (8,35,'19525567146681.jpg'),
 (9,35,'19525569217951.zip'),
 (10,36,'19695076224113.jpg'),
 (11,36,'19695077945506.zip'),
 (12,37,'19081352623024.jpg'),
 (13,37,'19081354595844.zip');
/*!40000 ALTER TABLE `post_resource` ENABLE KEYS */;


--
-- Definition of table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
CREATE TABLE `post_type` (
  `postTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `postTypeName` varchar(20) NOT NULL,
  PRIMARY KEY (`postTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_type`
--

/*!40000 ALTER TABLE `post_type` DISABLE KEYS */;
INSERT INTO `post_type` (`postTypeId`,`postTypeName`) VALUES 
 (101,'电影'),
 (102,'惊悚电影'),
 (103,'动作电影'),
 (104,'爱情电影'),
 (105,'喜剧电影'),
 (106,'纪录片'),
 (201,'教学'),
 (202,'理科教学'),
 (203,'文科教学'),
 (301,'音乐'),
 (302,'钢琴曲'),
 (303,'抒情歌曲'),
 (401,'读书'),
 (402,'小说'),
 (403,'文章批评'),
 (501,'畅谈'),
 (601,'活动'),
 (602,'校内活动'),
 (603,'校外活动');
/*!40000 ALTER TABLE `post_type` ENABLE KEYS */;


--
-- Definition of table `score_record`
--

DROP TABLE IF EXISTS `score_record`;
CREATE TABLE `score_record` (
  `scoreRecordId` int(11) NOT NULL AUTO_INCREMENT,
  `postId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`scoreRecordId`),
  KEY `FK_Post_Record` (`postId`),
  KEY `FK_User_Record` (`userId`),
  CONSTRAINT `FK_Post_Record` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`),
  CONSTRAINT `FK_User_Record` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `score_record`
--

/*!40000 ALTER TABLE `score_record` DISABLE KEYS */;
INSERT INTO `score_record` (`scoreRecordId`,`postId`,`userId`) VALUES 
 (26,36,29),
 (27,36,28),
 (28,32,28),
 (29,34,29),
 (30,37,28);
/*!40000 ALTER TABLE `score_record` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `userPassword` varchar(32) NOT NULL,
  `userLevel` int(11) NOT NULL,
  `userStatus` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`,`userName`,`userPassword`,`userLevel`,`userStatus`) VALUES 
 (27,'1212585','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (28,'1212574','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (29,'1212607','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (30,'1212575','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (31,'1212576','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (32,'1212608','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (33,'1212561','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (34,'1212586','f1293bcae648b20fb3329c21b9af1638',0,0),
 (35,'1212609','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (36,'1212587','f1293bcae648b20fb3329c21b9af1638',0,0),
 (37,'1212562','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (38,'1212563','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (39,'1212610','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (40,'1212577','a9d1b462ee15671846aca03c427fc0bc',0,0),
 (41,'1212578','a9d1b462ee15671846aca03c427fc0bc',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of view `ccomments`
--

DROP TABLE IF EXISTS `ccomments`;
DROP VIEW IF EXISTS `ccomments`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ccomments` AS (select distinct `ccomment`.`comId` AS `comId`,`ccomment`.`userId` AS `userId`,`user`.`userName` AS `userName`,`user`.`userLevel` AS `userLevel`,`user`.`userStatus` AS `userStatus`,`ccomment`.`comComment` AS `comComment`,`ccomment`.`releaseTime` AS `releaseTime`,`detail_inf`.`nickName` AS `nickName` from ((`ccomment` left join `user` on((`ccomment`.`userId` = `user`.`userId`))) left join `detail_inf` on((`ccomment`.`userId` = `detail_inf`.`userId`))));

--
-- Definition of view `commodities`
--

DROP TABLE IF EXISTS `commodities`;
DROP VIEW IF EXISTS `commodities`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `commodities` AS (select distinct `commodity`.`comId` AS `comId`,`commodity`.`userId` AS `ownerId`,`user`.`userName` AS `ownerName`,`detail_inf`.`nickName` AS `nickName`,`commodity`.`comName` AS `comName`,`commodity`.`property` AS `property`,`commodity_type`.`comTypeName` AS `comTypeName`,`commodity`.`price` AS `price`,`commodity`.`comregion` AS `comRegion`,`commodity`.`describe` AS `describe`,`commodity`.`status` AS `status`,`commodity`.`releaseTime` AS `releaseTime`,`commodity`.`offTime` AS `offTime`,`commodity`.`requiredScore` AS `requiredScore`,`picture`.`Path` AS `picturePath`,`desire`.`desire` AS `desire`,count(distinct `commodity`.`comId`) AS `count(distinct ``commodity``.comId)` from (((((`commodity` left join `user` on((`commodity`.`userId` = `user`.`userId`))) left join `commodity_type` on((`commodity`.`type` = `commodity_type`.`comTypeId`))) left join `picture` on((`commodity`.`comId` = `picture`.`comId`))) left join `detail_inf` on((`commodity`.`userId` = `detail_inf`.`userId`))) left join `desire` on((`commodity`.`comId` = `desire`.`comId`))) group by `commodity`.`comId`);

--
-- Definition of view `commoditiesinf`
--

DROP TABLE IF EXISTS `commoditiesinf`;
DROP VIEW IF EXISTS `commoditiesinf`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `commoditiesinf` AS select `commodities`.`comId` AS `comId`,`commodities`.`ownerId` AS `ownerId`,`commodities`.`ownerName` AS `ownerName`,`commodities`.`nickName` AS `nickName`,`commodities`.`comName` AS `comName`,`commodities`.`property` AS `property`,`commodities`.`comTypeName` AS `comTypeName`,`commodities`.`price` AS `price`,`commodities`.`comRegion` AS `comRegion`,`commodities`.`describe` AS `describe`,`commodities`.`status` AS `status`,`commodities`.`releaseTime` AS `releaseTime`,`commodities`.`offTime` AS `offTime`,`commodities`.`requiredScore` AS `requiredScore`,`commodities`.`picturePath` AS `picturePath`,`commodities`.`desire` AS `desire`,count(`attention`.`userId`) AS `comfocus` from (`commodities` left join `attention` on((`commodities`.`comId` = `attention`.`comId`))) group by `commodities`.`comId` order by count(`commodities`.`releaseTime`) desc,count(`attention`.`userId`) desc;

--
-- Definition of view `friends`
--

DROP TABLE IF EXISTS `friends`;
DROP VIEW IF EXISTS `friends`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `friends` AS (select distinct `friend`.`userId` AS `userId`,`friend`.`friendId` AS `friendId`,`user`.`userName` AS `friendName`,`user`.`userLevel` AS `friendLevel`,`user`.`userStatus` AS `friendStatus`,`detail_inf`.`location` AS `location`,`detail_inf`.`phone` AS `phone`,`detail_inf`.`postCode` AS `postCode`,`detail_inf`.`email` AS `email`,`detail_inf`.`gender` AS `gender`,`detail_inf`.`hobby` AS `hobby`,`detail_inf`.`major` AS `major`,`detail_inf`.`score` AS `score`,`detail_inf`.`nickName` AS `nickName`,`detail_inf`.`userRegion` AS `friendRegion`,`detail_inf`.`userPhoto` AS `friendPhoto` from ((`user` join `detail_inf`) join `friend`) where ((`friend`.`friendId` = `detail_inf`.`userId`) and (`user`.`userId` = `friend`.`friendId`)));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
