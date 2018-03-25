/*
Navicat MySQL Data Transfer

Source Server         : lzz
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : the_lost

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-03-25 23:32:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for achievement
-- ----------------------------
DROP TABLE IF EXISTS `achievement`;
CREATE TABLE `achievement` (
  `id` int(11) NOT NULL auto_increment,
  `achievementCode` varchar(255) default NULL COMMENT '任务代码',
  `achievementName` varchar(255) default NULL COMMENT '任务名',
  `achievementDesc` varchar(255) default NULL COMMENT '任务描述',
  `reward` varchar(255) default NULL COMMENT '奖励（数据存储格式：${道具id}：${道具数量}，...）',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of achievement
-- ----------------------------

-- ----------------------------
-- Table structure for endgamelog
-- ----------------------------
DROP TABLE IF EXISTS `endgamelog`;
CREATE TABLE `endgamelog` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `getFruitsQuan` int(11) default NULL COMMENT '水果获取数',
  `getCoinsQuan` int(11) default NULL COMMENT '金币获取数',
  `getDiamondsQuan` int(11) default NULL COMMENT '钻石获取数',
  `getBloodVialQuan` int(11) default NULL COMMENT '血瓶获取量',
  `getRewardsQuan` int(11) default NULL COMMENT '奖励道具数',
  `getInvinciblePotionsQuan` int(11) default NULL COMMENT '无敌药水获取数',
  `getRevivesQuan` int(11) default NULL COMMENT '还魂香获取数',
  `getPnvuQuan` int(11) default NULL COMMENT '血瓶获取数',
  `totalScore` int(11) default NULL COMMENT '总得分',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of endgamelog
-- ----------------------------

-- ----------------------------
-- Table structure for likedlog
-- ----------------------------
DROP TABLE IF EXISTS `likedlog`;
CREATE TABLE `likedlog` (
  `id` int(11) NOT NULL auto_increment,
  `fromWhoId` int(11) default NULL COMMENT '点赞者id',
  `toWhoId` int(11) default NULL COMMENT '被点赞者id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '该行记录是否有效',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of likedlog
-- ----------------------------

-- ----------------------------
-- Table structure for mission
-- ----------------------------
DROP TABLE IF EXISTS `mission`;
CREATE TABLE `mission` (
  `id` int(11) NOT NULL auto_increment,
  `missionName` varchar(255) default NULL COMMENT '任务名称',
  `missionDesc` varchar(255) default NULL COMMENT '任务描述',
  `reward` varchar(255) default NULL COMMENT '奖励（数据存储格式：${道具id}：${道具数量}，...）',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mission
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player
-- ----------------------------
INSERT INTO `player` VALUES ('1', 'test1', '123456', '男', '浙江宁波', '0', '2018-03-21 21:35:05', '1', '0');
INSERT INTO `player` VALUES ('2', 'test2', '123456', '男', '浙江杭州', '0', '2018-03-24 01:07:57', '1', '0');

-- ----------------------------
-- Table structure for player_ach
-- ----------------------------
DROP TABLE IF EXISTS `player_ach`;
CREATE TABLE `player_ach` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `achievementId` int(11) default NULL COMMENT '成就id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player_ach
-- ----------------------------

-- ----------------------------
-- Table structure for player_mission
-- ----------------------------
DROP TABLE IF EXISTS `player_mission`;
CREATE TABLE `player_mission` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `missionId` int(11) default NULL COMMENT '任务id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player_mission
-- ----------------------------

-- ----------------------------
-- Table structure for player_prop
-- ----------------------------
DROP TABLE IF EXISTS `player_prop`;
CREATE TABLE `player_prop` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `propId` int(11) default NULL COMMENT '道具id',
  `ownQuan` int(11) default NULL COMMENT '拥有量',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`),
  KEY `玩家id` (`playerId`),
  KEY `道具id` (`propId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player_prop
-- ----------------------------

-- ----------------------------
-- Table structure for player_role
-- ----------------------------
DROP TABLE IF EXISTS `player_role`;
CREATE TABLE `player_role` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `roleId` int(11) default NULL COMMENT '角色id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player_role
-- ----------------------------

-- ----------------------------
-- Table structure for player_viewskin
-- ----------------------------
DROP TABLE IF EXISTS `player_viewskin`;
CREATE TABLE `player_viewskin` (
  `id` int(11) NOT NULL auto_increment,
  `playerId` int(11) default NULL COMMENT '玩家id',
  `skinId` int(11) default NULL COMMENT '场景皮肤id',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of player_viewskin
-- ----------------------------

-- ----------------------------
-- Table structure for prop
-- ----------------------------
DROP TABLE IF EXISTS `prop`;
CREATE TABLE `prop` (
  `id` int(11) NOT NULL auto_increment,
  `propName` varchar(255) default NULL COMMENT '道具名',
  `propDesc` varchar(255) default NULL COMMENT '道具描述',
  `coinCost` int(11) default NULL COMMENT '消费金币数',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prop
-- ----------------------------
INSERT INTO `prop` VALUES ('1', '金币', '游戏常规货币', null, '2018-03-23 23:49:35', null, '1');
INSERT INTO `prop` VALUES ('2', '钻石', '用来购买皮肤等高级物件', '88', '2018-03-23 23:51:01', null, '1');
INSERT INTO `prop` VALUES ('3', '奖励关道具', '通往奖励关的道具', '288', '2018-03-23 23:52:25', null, '1');
INSERT INTO `prop` VALUES ('4', '血瓶', '补充丢失的HP', '28', '2018-03-23 23:52:58', null, '1');
INSERT INTO `prop` VALUES ('5', '无敌药水', '吃了后免疫所有障碍物', '38', '2018-03-23 23:53:29', null, '1');
INSERT INTO `prop` VALUES ('6', '还魂香', '死后复生道具', '188', '2018-03-23 23:54:05', null, '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `roleCode` varchar(255) default NULL COMMENT '角色代码',
  `roleName` varchar(255) default NULL COMMENT '角色名',
  `roleDesc` varchar(255) default NULL COMMENT '角色描述',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for sendcoinslog
-- ----------------------------
DROP TABLE IF EXISTS `sendcoinslog`;
CREATE TABLE `sendcoinslog` (
  `id` int(11) NOT NULL auto_increment,
  `fromWhoId` int(11) default NULL COMMENT '赠送金币者id',
  `toWhoId` int(11) default NULL COMMENT '被赠送金币者',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '该行记录是否有效',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sendcoinslog
-- ----------------------------

-- ----------------------------
-- Table structure for viewskin
-- ----------------------------
DROP TABLE IF EXISTS `viewskin`;
CREATE TABLE `viewskin` (
  `id` int(11) NOT NULL auto_increment,
  `skinCode` varchar(255) default NULL COMMENT '场景代码',
  `skinName` varchar(255) default NULL COMMENT '场景皮肤名',
  `skinDesc` varchar(255) default NULL COMMENT '场景描述',
  `createTime` datetime default NULL COMMENT '创建时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  `isValid` int(11) default '1' COMMENT '记录有效标记（0：无效；1：有效）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of viewskin
-- ----------------------------
INSERT INTO `viewskin` VALUES ('1', 'S001', '魔界', null, '2018-03-24 00:11:25', null, '1');
INSERT INTO `viewskin` VALUES ('2', 'S002', '绿木丛', null, '2018-03-24 00:11:49', null, '1');
