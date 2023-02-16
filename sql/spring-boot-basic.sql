/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 15/02/2023 16:40:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cm_admin
-- ----------------------------
DROP TABLE IF EXISTS `cm_admin`;
CREATE TABLE `cm_admin`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员id 以ad_开头 共15位',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码 算法:sha256(id+password(明文))',
  `is_super_admin` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否为超级管理员 0.否 1.是',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `type` int(11) NULL DEFAULT NULL COMMENT '用户类型',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录ip',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_account`(`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_admin
-- ----------------------------
INSERT INTO `cm_admin` VALUES ('ad_000000000000', '默认管理员', 'admin', '03a1ccbeceef20f8c7305e5b07b7c92838ea99a289820b5be3a10c3cd42f290e', '1', NULL, NULL, NULL, NULL, NULL, NULL, '2023-02-15 16:33:57', '2023-02-15 14:30:51', '2023-02-15 14:30:51', '0');

-- ----------------------------
-- Table structure for cm_log_admin_operate
-- ----------------------------
DROP TABLE IF EXISTS `cm_log_admin_operate`;
CREATE TABLE `cm_log_admin_operate`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员id',
  `module` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求模块',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'uri',
  `type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型 1.新增 2.删除 3.修改 4.查询',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员用户操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_log_admin_operate
-- ----------------------------

-- ----------------------------
-- Table structure for cm_log_request
-- ----------------------------
DROP TABLE IF EXISTS `cm_log_request`;
CREATE TABLE `cm_log_request`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求uri',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
  `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请求日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_log_request
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
