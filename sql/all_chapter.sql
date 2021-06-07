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

 Date: 07/06/2021 17:21:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for all_chapter
-- ----------------------------
DROP TABLE IF EXISTS `all_chapter`;
CREATE TABLE `all_chapter`  (
  `chapter_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '章节id',
  `chapter_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名',
  `comic_id` int(11) NOT NULL COMMENT '漫画id',
  PRIMARY KEY (`chapter_id`) USING BTREE,
  INDEX `comic_id`(`comic_id`) USING BTREE,
  CONSTRAINT `all_chapter_ibfk_1` FOREIGN KEY (`comic_id`) REFERENCES `all_comic` (`comic_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9973 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '漫画章节' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
