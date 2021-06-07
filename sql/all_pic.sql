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

 Date: 07/06/2021 17:21:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_pic
-- ----------------------------
DROP TABLE IF EXISTS `all_pic`;
CREATE TABLE `all_pic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `pic_id` int(255) NOT NULL COMMENT '图片顺序',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片url',
  `chapter_id` int(11) NOT NULL COMMENT '章节id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pic_id`(`pic_id`) USING BTREE,
  INDEX `chapter_id`(`chapter_id`) USING BTREE,
  CONSTRAINT `all_pic_ibfk_1` FOREIGN KEY (`chapter_id`) REFERENCES `all_chapter` (`chapter_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 218685 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图片信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
