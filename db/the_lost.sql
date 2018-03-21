/*
Navicat MySQL Data Transfer

Source Server         : lzz
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : the_lost

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-03-21 22:58:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for player
-- ----------------------------
DROP TABLE IF EXISTS `player`;
CREATE TABLE `player` (
  `id` int(11) NOT NULL auto_increment,
  `userName` varchar(100) default NULL COMMENT '用户名',
  `password` varchar(100) default NULL COMMENT '密码',
  `sex` varchar(10) default NULL COMMENT '性别',
  `area` varchar(10) default NULL COMMENT '地区',
  `loginStatus` int(11) default '0' COMMENT '登陆状态（0：不在线，1：在线）',
  `createTime` datetime default NULL COMMENT '创建时间',
  `status` int(11) default '1' COMMENT '启用状态（0：禁用，1：启用）',
  `isDelete` int(11) default '0' COMMENT '是否删除（1：是，0：否）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES ('1', 'test1', '123456', '男', '浙江宁波', '0', '2018-03-21 21:35:05', '1', '0');
