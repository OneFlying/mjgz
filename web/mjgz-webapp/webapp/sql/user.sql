/*
Navicat MySQL Data Transfer

Source Server         : guo
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : mjgz

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2016-08-09 21:52:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户号码',
  `duty` varchar(255) DEFAULT NULL COMMENT '职务',
  `passwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
