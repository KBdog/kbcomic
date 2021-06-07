/*
 Navicat Premium Data Transfer

 Source Server         : mysql-connection
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : kbcomic

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/06/2021 17:21:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_comic
-- ----------------------------
DROP TABLE IF EXISTS `all_comic`;
CREATE TABLE `all_comic`  (
  `comic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '漫画id',
  `comic_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '漫画名',
  `comic_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '漫画描述',
  `comic_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '封面url',
  `create_time` datetime(0) DEFAULT NULL COMMENT '收录时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`comic_id`) USING BTREE,
  FULLTEXT INDEX `full_index`(`comic_name`) WITH PARSER `ngram`
) ENGINE = InnoDB AUTO_INCREMENT = 557 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '漫画简介' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
