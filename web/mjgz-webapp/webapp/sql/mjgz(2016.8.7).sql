/*
Navicat MySQL Data Transfer

Source Server         : guo
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : mjgz

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2016-08-07 18:22:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` varchar(255) NOT NULL,
  `num` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL,
  `stantard` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `life` varchar(255) DEFAULT NULL,
  `drawing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('07c8c6bba00644e0b14ee94486bfa871', '123', '123', '132', '123', '123', '123');

-- ----------------------------
-- Table structure for mjorder
-- ----------------------------
DROP TABLE IF EXISTS `mjorder`;
CREATE TABLE `mjorder` (
  `id` varchar(255) NOT NULL,
  `materialId` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mjorder
-- ----------------------------
INSERT INTO `mjorder` VALUES ('1470562709962', '07c8c6bba00644e0b14ee94486bfa871', 'E:\\SVN\\mjgz\\trunk\\web\\mjgz-webapp\\webapp\\barcodeimg\\1470562709962.png', '0', null);
INSERT INTO `mjorder` VALUES ('1470562902792', '07c8c6bba00644e0b14ee94486bfa871', 'E:\\SVN\\mjgz\\trunk\\web\\mjgz-webapp\\webapp\\barcodeimg\\1470562902792.png', '0', null);

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `nodeName` varchar(255) NOT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `overtime` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`nodeName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('123', '1470562902792', null, '132', '0');
INSERT INTO `node` VALUES ('1234', '1470562709962', null, '132', '0');

-- ----------------------------
-- Table structure for productionnode
-- ----------------------------
DROP TABLE IF EXISTS `productionnode`;
CREATE TABLE `productionnode` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productionnode
-- ----------------------------
INSERT INTO `productionnode` VALUES ('bcdd4a9bda114edcb7e0f43ed702fd4c', 'fasdf');

-- ----------------------------
-- Table structure for track
-- ----------------------------
DROP TABLE IF EXISTS `track`;
CREATE TABLE `track` (
  `id` varchar(255) NOT NULL,
  `workName` varchar(255) DEFAULT NULL,
  `node` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of track
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户号码',
  `duty` varchar(255) DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
