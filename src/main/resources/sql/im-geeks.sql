/*
MySQL Data Transfer
Source Host: localhost
Source Database: im-geeks
Target Host: localhost
Target Database: im-geeks
Date: 2016/10/20 23:13:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_chatroom
-- ----------------------------
CREATE TABLE `t_chatroom` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) DEFAULT NULL,
  `roomid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `updatedate` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `createdate` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chatroom_userid` (`userid`),
  CONSTRAINT `chatroom_userid` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
CREATE TABLE `t_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_hobby
-- ----------------------------
CREATE TABLE `t_hobby` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_hoyo
-- ----------------------------
CREATE TABLE `t_hoyo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid1` int(10) DEFAULT NULL,
  `uid2` int(10) DEFAULT NULL,
  `createdate` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `roomid` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid1` (`uid1`),
  KEY `uid2` (`uid2`),
  CONSTRAINT `uid1_user` FOREIGN KEY (`uid1`) REFERENCES `t_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `uid2_user` FOREIGN KEY (`uid2`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_label
-- ----------------------------
CREATE TABLE `t_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `labelname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `labelcode` int(255) DEFAULT NULL,
  `statu` int(255) DEFAULT NULL COMMENT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `rolecode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `headimg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `qq` int(11) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `job` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `createtime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_user_label
-- ----------------------------
CREATE TABLE `t_user_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `labelid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`userid`),
  KEY `lid` (`labelid`),
  CONSTRAINT `lid` FOREIGN KEY (`labelid`) REFERENCES `t_label` (`id`) ON DELETE CASCADE,
  CONSTRAINT `uid` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `statu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `roleid` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_userattention
-- ----------------------------
CREATE TABLE `t_userattention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `otherid` int(11) DEFAULT NULL,
  `createdate` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_usereducation
-- ----------------------------
CREATE TABLE `t_usereducation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `institution` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '机构',
  `begintime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `endtime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `major` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '专业',
  `introduce` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uedu` (`userid`),
  CONSTRAINT `uedu` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_userprofile
-- ----------------------------
CREATE TABLE `t_userprofile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `profile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `up` (`userid`),
  CONSTRAINT `up` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_userproject
-- ----------------------------
CREATE TABLE `t_userproject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `author` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `workimg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `worktitle` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `statu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `upr` (`userid`),
  CONSTRAINT `upr` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_userskill
-- ----------------------------
CREATE TABLE `t_userskill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `proficiency` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `userskill` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uk` (`userid`),
  CONSTRAINT `uk` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_usersupport
-- ----------------------------
CREATE TABLE `t_usersupport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `otherid` int(11) DEFAULT NULL,
  `createdate` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_userworkhistory
-- ----------------------------
CREATE TABLE `t_userworkhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `worktitle` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `begintime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `endtime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `companyname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `workhistory` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `wh` (`userid`),
  CONSTRAINT `wh` FOREIGN KEY (`userid`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_city` VALUES ('1', '无锡', null);
INSERT INTO `t_hobby` VALUES ('1', 'java');
INSERT INTO `t_hoyo` VALUES ('18', '15', '18', '2016-10-16 14:54:02', '5803240c67f3560058be9443');
INSERT INTO `t_label` VALUES ('1', 'web前端开发', '1', '1');
INSERT INTO `t_label` VALUES ('2', 'javascript', '2', '1');
INSERT INTO `t_label` VALUES ('3', 'html5', '2', '1');
INSERT INTO `t_label` VALUES ('4', 'css3', '2', '1');
INSERT INTO `t_role` VALUES ('1', '管理员', '1');
INSERT INTO `t_role` VALUES ('2', '普通用户', '2');
INSERT INTO `t_user` VALUES ('14', '/RES/assets/img/team/img-v1.jpg', 'admin', 'admin', '136992347', '南京', '15380833359', '136992347@qq.com', '高级', 'e10adc3949ba59abbe56e057f20f883e', null, '18', null);
INSERT INTO `t_user` VALUES ('15', '/RES/upload/15/5839baad-8ee8-4d2f-9908-1dc4c28f892c_15.jpg', 'fch', 'fch', '1154016697', '无锡市', '15380833359', 'fch@qq.com', '前端美工', 'e10adc3949ba59abbe56e057f20f883e', 'https://www.baidu.com', '12', null);
INSERT INTO `t_user` VALUES ('16', '/RES/assets/img/team/img-v2.jpg', 'jerry', 'jerry', '123456798', '南京', '15380833359', 'wt@qq.com', '网路工程师', 'e10adc3949ba59abbe56e057f20f883e', 'https://www.baidu.com', '45', null);
INSERT INTO `t_user` VALUES ('17', '/RES/assets/img/team/img4-md.jpg', 'sunbin', 'sunbin', '123456798', '上海', '15380833359', 'sunbin@qq.com', '运维工程师', 'e10adc3949ba59abbe56e057f20f883e', null, null, null);
INSERT INTO `t_user` VALUES ('18', '/RES/assets/img/team/img6-md.jpg', 'cr', 'cr', '123456798', '武汉', '15380833359', 'cr@qq.com', '产品销售', 'e10adc3949ba59abbe56e057f20f883e', null, '55', null);
INSERT INTO `t_user` VALUES ('19', '/RES/assets/img/team/img9-md.jpg', 'ch', 'ch', '123456798', '天津', '15380833359', 'ch@qq.com', '高级工程师', 'e10adc3949ba59abbe56e057f20f883e', null, '44', null);
INSERT INTO `t_user` VALUES ('20', '/RES/assets/img/team/img3-sm.jpg', 'tzy', 'tzy', '123456798', '广州', '15380833359', 'tzy@qq.com', 'web架构师', 'e10adc3949ba59abbe56e057f20f883e', null, '16', null);
INSERT INTO `t_user` VALUES ('21', '/RES/assets/img/team/img-v3.jpg', 'zjw', 'zjw', '123456798', '海南', '15380833359', 'zjw@sina.com', 'Python架构师', 'e10adc3949ba59abbe56e057f20f883e', null, '25', null);
INSERT INTO `t_user_label` VALUES ('5', '15', '4');
INSERT INTO `t_user_label` VALUES ('6', '15', '2');
INSERT INTO `t_user_role` VALUES ('1', '1', '14', '2');
INSERT INTO `t_user_role` VALUES ('2', '2', '15', '2');
INSERT INTO `t_user_role` VALUES ('3', '2', '16', '2');
INSERT INTO `t_user_role` VALUES ('4', '2', '17', '2');
INSERT INTO `t_user_role` VALUES ('5', '2', '18', '2');
INSERT INTO `t_user_role` VALUES ('6', '2', '19', '2');
INSERT INTO `t_user_role` VALUES ('7', '2', '20', '2');
INSERT INTO `t_user_role` VALUES ('8', '2', '21', '2');
INSERT INTO `t_userattention` VALUES ('9', '15', '20', '2016-10-17 21:48:36');
INSERT INTO `t_userattention` VALUES ('10', '15', '21', '2016-10-17 21:48:38');
INSERT INTO `t_userattention` VALUES ('11', '15', '19', '2016-10-17 21:48:43');
INSERT INTO `t_usereducation` VALUES ('1', '15', '南京大学', '1997', '2000', '计算机学院', '在南京大学计算机学院学习.');
INSERT INTO `t_userprofile` VALUES ('1', '14', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('2', '15', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('3', '16', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('4', '17', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('5', '18', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('6', '19', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('7', '20', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userprofile` VALUES ('8', '21', '大家好，我毕业至今一直就业于厦门力泰科技有限公司，从事j2ee企业信息化管理系统的开发， 目前的岗位是项目经理，负责带领一个3-5人的小团队进行系统开发。 参加工作以来，本人先后独立或主持开发过10几个企业信息化管理系统。 其中，**电力有限公司的电费充值卡管理系统、**集团的人力资源管理系统以及移动办公管理平台，这三个系统运行以来深受使用单位的一致好评，为企业节约了人力成本，提高了工作效率。');
INSERT INTO `t_userproject` VALUES ('13', '15', 'fch', '/RES/upload/15/23694a0a-8f48-40d8-b6d0-a6124ced623b_15.jpg', '网易云音乐', 'http://www.baidu.com', '2016-10-20 22:34:53', '0');
INSERT INTO `t_userproject` VALUES ('14', '15', 'fch', '/RES/upload/15/6da7aa39-82ed-4f78-ad63-7359dc0e53b5_15.jpg', '开心网', 'http://www.baidu.com', '2016-10-20 22:35:30', '0');
INSERT INTO `t_userproject` VALUES ('15', '15', 'fch', '/RES/upload/15/50964bdc-9864-4117-81f4-8363516bb2cf_15.jpg', '企鹅网', 'http://ww.qie.com.cn', '2016-10-20 22:36:08', '0');
INSERT INTO `t_userproject` VALUES ('16', '15', 'fch', '/RES/upload/15/1b8ef23c-e9e5-4514-818c-93cd43c485af_15.jpg', '灯塔网', 'http://www.dengta.com.cn', '2016-10-20 22:38:26', '0');
INSERT INTO `t_userproject` VALUES ('17', '15', 'fch', '/RES/upload/15/9582c7ac-3200-4cd0-a965-bd9b614def5c_15.jpg', '百信网', 'http://www.baixin.com', '2016-10-20 22:39:31', '0');
INSERT INTO `t_userproject` VALUES ('18', '15', 'fch', '/RES/upload/15/ef600fe5-1074-4fc4-8208-6fc0273e9374_15.jpg', '考拉', 'http://www.baidu.com', '2016-10-20 22:40:14', '0');
INSERT INTO `t_userproject` VALUES ('19', '15', 'fch', '/RES/upload/15/10961dfc-cc58-4410-a391-b7439ef6b470_15.jpg', '沙漠王', 'http://www.shamo.com', '2016-10-20 22:40:45', '0');
INSERT INTO `t_userproject` VALUES ('20', '15', 'fch', '/RES/upload/15/32cf5ba8-fb53-4de0-a964-8c3c261b5535_15.jpg', '水木', 'http://www.baidu.com,', '2016-10-20 23:08:10', '0');
INSERT INTO `t_userskill` VALUES ('1', '15', '12%', 'HTML');
INSERT INTO `t_userskill` VALUES ('2', '15', '23%', 'Photoshop ');
INSERT INTO `t_userskill` VALUES ('4', '15', '78%', 'Javascript ');
INSERT INTO `t_userskill` VALUES ('5', '16', '81%', 'C++');
INSERT INTO `t_userskill` VALUES ('6', '17', '84%', 'CSS ');
INSERT INTO `t_userskill` VALUES ('7', '18', '99%', 'Oracle');
INSERT INTO `t_userskill` VALUES ('8', '19', '87%', 'Photoshop ');
INSERT INTO `t_userskill` VALUES ('9', '20', '97%', 'HTML');
INSERT INTO `t_userskill` VALUES ('10', '21', '89%', 'Leancloud');
INSERT INTO `t_userskill` VALUES ('11', '21', '96%', 'Javascript ');
INSERT INTO `t_userskill` VALUES ('12', '21', '91%', 'Rub');
INSERT INTO `t_userskill` VALUES ('13', '17', '100%', 'Dw');
INSERT INTO `t_userskill` VALUES ('14', '17', '99%', 'Xhtml');
INSERT INTO `t_userskill` VALUES ('15', '17', '89%', 'ActionScript');
INSERT INTO `t_userskill` VALUES ('16', '20', '81%', 'Python');
INSERT INTO `t_userskill` VALUES ('17', '20', '88%', 'Flash');
INSERT INTO `t_userskill` VALUES ('18', '21', '82%', 'Oracle');
INSERT INTO `t_userskill` VALUES ('19', '18', '77%', 'MySQL');
INSERT INTO `t_userskill` VALUES ('20', '21', '84%', 'SQLServer');
INSERT INTO `t_usersupport` VALUES ('8', '15', '16', '2016-03-16 09:40:57');
INSERT INTO `t_usersupport` VALUES ('9', '18', '15', '2016-03-16 09:41:00');
INSERT INTO `t_usersupport` VALUES ('10', '19', '15', '2016-03-16 09:41:03');
INSERT INTO `t_usersupport` VALUES ('11', '15', '19', '2016-03-16 09:41:06');
INSERT INTO `t_usersupport` VALUES ('12', '15', '20', '2016-03-18 09:39:58');
INSERT INTO `t_usersupport` VALUES ('13', '15', '18', '2016-03-20 15:53:40');
INSERT INTO `t_userworkhistory` VALUES ('1', '15', '图像处理', '2003', '2007', 'Adobe中国分部', '\r\n负责网页产品ui设计.');
INSERT INTO `t_userworkhistory` VALUES ('2', '15', '网站设计', '2007', '2012', '深圳腾讯集团', '\n负责网站页面设计.');
