/*
Navicat MySQL Data Transfer

Source Server         : guo
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : mjgz

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2016-08-09 09:03:10
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
INSERT INTO `mjorder` VALUES ('1470621848982', '07c8c6bba00644e0b14ee94486bfa871', '1470621848982', '3', '123');
INSERT INTO `mjorder` VALUES ('1470642711392', '07c8c6bba00644e0b14ee94486bfa871', '1470642711392', '3', '测试节点3');
INSERT INTO `mjorder` VALUES ('1470643976281', '07c8c6bba00644e0b14ee94486bfa871', '1470643976281', '3', '测试节点3');
INSERT INTO `mjorder` VALUES ('1470651943226', '07c8c6bba00644e0b14ee94486bfa871', '1470651943226', '3', '测试节点1');
INSERT INTO `mjorder` VALUES ('1470655990171', '07c8c6bba00644e0b14ee94486bfa871', '1470655990171', '3', '测试节点3');

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node` (
  `nodeName` varchar(255) NOT NULL,
  `orderId` varchar(255) NOT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `overtime` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nodeName`,`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES ('123', '1470621848982', '2016-08-08 15:47', '132', '2', '2016-08-08 15:48');
INSERT INTO `node` VALUES ('测试节点1', '1470642711392', '2016-08-08 15:53', '132', '2', '2016-08-08 15:54');
INSERT INTO `node` VALUES ('测试节点1', '1470643976281', '2016-08-08 16:18', '132', '2', '2016-08-08 16:21');
INSERT INTO `node` VALUES ('测试节点1', '1470651943226', '2016-08-08 19:28', '132', '2', '2016-08-08 19:29');
INSERT INTO `node` VALUES ('测试节点1', '1470655990171', '2016-08-08 19:34', '132', '2', '2016-08-08 19:37');
INSERT INTO `node` VALUES ('测试节点2', '1470642711392', '2016-08-08 15:54', '132', '2', '2016-08-08 15:55');
INSERT INTO `node` VALUES ('测试节点2', '1470643976281', '2016-08-08 16:21', '132', '2', '2016-08-08 16:21');
INSERT INTO `node` VALUES ('测试节点2', '1470651943226', '2016-08-08 18:50', '132', '2', '2016-08-08 19:28');
INSERT INTO `node` VALUES ('测试节点2', '1470655990171', '2016-08-08 19:37', '132', '2', '2016-08-08 19:45');
INSERT INTO `node` VALUES ('测试节点3', '1470642711392', '2016-08-08 15:55', '132', '2', '2016-08-08 15:55');
INSERT INTO `node` VALUES ('测试节点3', '1470643976281', '2016-08-08 16:21', '132', '2', '2016-08-08 16:21');
INSERT INTO `node` VALUES ('测试节点3', '1470651943226', '2016-08-08 18:26', '132', '2', '2016-08-08 18:50');
INSERT INTO `node` VALUES ('测试节点3', '1470655990171', '2016-08-08 19:45', '132', '2', '2016-08-08 19:46');

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
INSERT INTO `productionnode` VALUES ('0', '结束');
INSERT INTO `productionnode` VALUES ('1', '测试节点2');
INSERT INTO `productionnode` VALUES ('2', '测试节点3');
INSERT INTO `productionnode` VALUES ('bcdd4a9bda114edcb7e0f43ed702fd4c', '测试节点1');

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
INSERT INTO `track` VALUES ('0cf3821a42914ddcbe13122becb3de56', '家人', '测试节点3', '1470643976281', '2016-08-08 16:21');
INSERT INTO `track` VALUES ('2352156c36354cb18620c9085985652d', '家人', '测试节点1', '1470642711392', '2016-08-08 15:53');
INSERT INTO `track` VALUES ('2dcfd4580eb64a1c898bcd5a41d51ffa', '测试数据', '测试节点2', '1470651943226', '2016-08-08 18:50');
INSERT INTO `track` VALUES ('3a0bd396ef03428a9e7abb5495aad43c', '家人', '测试节点3', '1470651943226', '2016-08-08 18:26');
INSERT INTO `track` VALUES ('3eb84932e5fd42999473b0c36658385e', '测试人员', '测试节点2', '1470643976281', '2016-08-08 16:21');
INSERT INTO `track` VALUES ('65d118602a0a4b8f91511d1d7a05d64a', '测试数据', '测试节点2', '1470642711392', '2016-08-08 15:54');
INSERT INTO `track` VALUES ('7cc6043acc4741dbaca952063dfba833', '测试人员', '测试节点1', '1470655990171', '2016-08-08 19:34');
INSERT INTO `track` VALUES ('7ee5f460a6054f919cfee9fa969f9a30', '测试人员', '测试节点1', '1470643976281', '2016-08-08 16:18');
INSERT INTO `track` VALUES ('8665347871c041f1a476478946c39ccd', '测试人员', '测试节点1', '1470651943226', '2016-08-08 19:28');
INSERT INTO `track` VALUES ('b7df4c08c7b849c7a5532808b5352287', '家人', '测试节点3', '1470655990171', '2016-08-08 19:45');
INSERT INTO `track` VALUES ('bc89dbde5fc04047891ce6c9d190270c', '测试人员', '123', '1470621848982', '2016-08-08 15:47');
INSERT INTO `track` VALUES ('c2bfd379874342d5b44c8b8766d05f77', '家人', '测试节点3', '1470642711392', '2016-08-08 15:55');
INSERT INTO `track` VALUES ('f3f2a26539844ae6937b646c3e8220a1', '家人', '测试节点2', '1470655990171', '2016-08-08 19:37');

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
