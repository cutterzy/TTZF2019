/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : tiebar

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-23 11:20:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `a_id` int(11) NOT NULL,
  `u_id` int(11) DEFAULT NULL,
  `ca_id` int(11) DEFAULT NULL,
  `a_content` varchar(255) DEFAULT NULL,
  `a_type` int(11) DEFAULT NULL,
  `to_u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `fk_4` (`u_id`),
  KEY `fk_5` (`ca_id`),
  KEY `fk_6` (`to_u_id`),
  CONSTRAINT `fk_4` FOREIGN KEY (`u_id`) REFERENCES `userinfo` (`u_id`),
  CONSTRAINT `fk_5` FOREIGN KEY (`ca_id`) REFERENCES `cardinfo` (`ca_id`),
  CONSTRAINT `fk_6` FOREIGN KEY (`to_u_id`) REFERENCES `userinfo` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for barinfo
-- ----------------------------
DROP TABLE IF EXISTS `barinfo`;
CREATE TABLE `barinfo` (
  `b_id` int(11) NOT NULL,
  `b_name` varchar(20) DEFAULT NULL,
  `b_content` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `ca_num` int(11) DEFAULT NULL,
  `u_num` int(11) DEFAULT NULL,
  `b_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_id`),
  KEY `fk_1` (`u_id`),
  KEY `b_name` (`b_name`),
  CONSTRAINT `fk_1` FOREIGN KEY (`u_id`) REFERENCES `userinfo` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of barinfo
-- ----------------------------

-- ----------------------------
-- Table structure for cardinfo
-- ----------------------------
DROP TABLE IF EXISTS `cardinfo`;
CREATE TABLE `cardinfo` (
  `ca_id` int(11) NOT NULL,
  `ca_title` varchar(20) DEFAULT NULL,
  `ca_content` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `ca_time` datetime DEFAULT NULL,
  `b_id` int(11) DEFAULT NULL,
  `ca_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ca_id`),
  KEY `fk_2` (`u_id`),
  KEY `fk_3` (`b_id`),
  CONSTRAINT `fk_2` FOREIGN KEY (`u_id`) REFERENCES `userinfo` (`u_id`),
  CONSTRAINT `fk_3` FOREIGN KEY (`b_id`) REFERENCES `barinfo` (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cardinfo
-- ----------------------------

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check` (
  `ch_id` int(11) NOT NULL,
  `b_id` int(11) DEFAULT NULL,
  `b_name` varchar(20) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `ch_state` int(11) DEFAULT NULL,
  `ch_u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ch_id`),
  KEY `fk_9` (`b_id`),
  KEY `fk_10` (`b_name`),
  KEY `fk_11` (`u_id`),
  KEY `fk_12` (`ch_u_id`),
  CONSTRAINT `fk_10` FOREIGN KEY (`b_name`) REFERENCES `barinfo` (`b_name`),
  CONSTRAINT `fk_11` FOREIGN KEY (`u_id`) REFERENCES `userinfo` (`u_id`),
  CONSTRAINT `fk_12` FOREIGN KEY (`ch_u_id`) REFERENCES `userinfo` (`u_id`),
  CONSTRAINT `fk_9` FOREIGN KEY (`b_id`) REFERENCES `barinfo` (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check
-- ----------------------------

-- ----------------------------
-- Table structure for close
-- ----------------------------
DROP TABLE IF EXISTS `close`;
CREATE TABLE `close` (
  `cl_id` int(11) NOT NULL,
  `u_id` int(11) DEFAULT NULL,
  `cl_type` int(11) DEFAULT NULL,
  `cl_time` datetime DEFAULT NULL,
  `cl_scope` int(11) DEFAULT NULL,
  `cl_barid` int(11) DEFAULT NULL,
  `cl_u_id` int(11) DEFAULT NULL,
  `cl_reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cl_id`),
  KEY `fk_7` (`u_id`),
  KEY `fk_8` (`cl_u_id`),
  CONSTRAINT `fk_7` FOREIGN KEY (`u_id`) REFERENCES `userinfo` (`u_id`),
  CONSTRAINT `fk_8` FOREIGN KEY (`cl_u_id`) REFERENCES `userinfo` (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of close
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `user_all_info`;
CREATE TABLE `user_all_info` (
  `u_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `NICK_NAME` varchar(50) NOT NULL,
  `IMG_URL` varchar(20) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `explain` varchar(255) DEFAULT NULL,
  `role` int(4) DEFAULT NULL,
  `state` int(4) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
