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

 Date: 14/03/2023 13:31:39
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
                             `role_id` int(11) NOT NULL DEFAULT 0 COMMENT '角色id',
                             `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                             `mobile_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
                             `type` int(11) NULL DEFAULT NULL COMMENT '用户类型',
                             `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
                             `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
                             `enabled` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否启用: 0.禁用 1.启用',
                             `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次登录ip',
                             `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
                             `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `idx_account`(`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_admin
-- ----------------------------
INSERT INTO `cm_admin` VALUES ('ad_000000000000', '默认管理员', 'admin', '03a1ccbeceef20f8c7305e5b07b7c92838ea99a289820b5be3a10c3cd42f290e', 1, NULL, NULL, NULL, NULL, NULL, '1', '10.50.7.15', '2023-03-14 13:29:18', '2023-02-15 14:30:51', '2023-02-15 14:30:51', '0');

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

-- ----------------------------
-- Table structure for cm_role
-- ----------------------------
DROP TABLE IF EXISTS `cm_role`;
CREATE TABLE `cm_role`  (
                            `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                            `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用 0.禁用 1.启用',
                            `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_role
-- ----------------------------
INSERT INTO `cm_role` VALUES (1, '超级管理员', NULL, 1, '2023-03-14 13:30:08', '2023-03-13 16:14:46', '0');

-- ----------------------------
-- Table structure for cm_role_api
-- ----------------------------
DROP TABLE IF EXISTS `cm_role_api`;
CREATE TABLE `cm_role_api`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `role_id` int(11) NOT NULL COMMENT '角色id',
                                `api_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口编码',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色接口权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_role_api
-- ----------------------------
INSERT INTO `cm_role_api` VALUES (1, 1, 'cmAdmin:changePass');
INSERT INTO `cm_role_api` VALUES (2, 1, 'cmAdmin:getRole');

-- ----------------------------
-- Table structure for cm_sys_api
-- ----------------------------
DROP TABLE IF EXISTS `cm_sys_api`;
CREATE TABLE `cm_sys_api`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `kind` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '种类',
                               `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '接口编码,唯一,对应注解中的编码,格式为-种类:标识',
                               `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口名称,对应swagger注解中的名称',
                               `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接口地址',
                               `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
                               `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `is_del` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除: 0.未删除 1.已删除',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE INDEX `idx_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cm_sys_api
-- ----------------------------
INSERT INTO `cm_sys_api` VALUES (1, 'cmAdmin', 'cmAdmin:changePass', '管理员修改密码', '/common/admin/api/changePass', 'POST', '2023-03-13 15:16:27', '2023-03-13 15:16:27', '0');
INSERT INTO `cm_sys_api` VALUES (2, 'cmAdmin', 'cmAdmin:getRole', '分页获取角色列表', '/common/role/api/getRole', 'POST', '2023-03-13 15:16:27', '2023-03-13 15:16:27', '0');

SET FOREIGN_KEY_CHECKS = 1;
