/*
 Navicat Premium Data Transfer

 Source Server         : ming
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : tim

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 21/12/2020 09:25:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `u_account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `f_account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lately` tinyint(1) NOT NULL DEFAULT 0,
  `groupId` tinyint(2) NOT NULL DEFAULT 0,
  UNIQUE INDEX `u_account`(`u_account`, `f_account`) USING BTREE,
  UNIQUE INDEX `u_account_2`(`u_account`, `f_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('17634721886', '17634721880', 0, 3);
INSERT INTO `friends` VALUES ('17634721886', '17634721881', 0, 2);
INSERT INTO `friends` VALUES ('17634721886', '17634721882', 0, 2);
INSERT INTO `friends` VALUES ('17634721886', '17634721883', 0, 3);
INSERT INTO `friends` VALUES ('17634721886', '17634721885', 0, 1);
INSERT INTO `friends` VALUES ('17634721886', '17634721887', 0, 1);
INSERT INTO `friends` VALUES ('17634721886', '17634721888', 0, 1);
INSERT INTO `friends` VALUES ('17634721886', '17634721889', 0, 2);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `account` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `groupId` tinyint(2) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`account`, `groupId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('17634721886', 1, '我的好友');
INSERT INTO `group` VALUES ('17634721886', 2, '家人');
INSERT INTO `group` VALUES ('17634721887', 1, '我的好友');
INSERT INTO `group` VALUES ('17634721886', 3, '朋友');
INSERT INTO `group` VALUES ('17634721880', 1, '我的好友');
INSERT INTO `group` VALUES ('17634721885', 1, '我的好友');
INSERT INTO `group` VALUES ('17634721888', 1, '我的好友');
INSERT INTO `group` VALUES ('17634721889', 1, '我的好友');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '男',
  `age` int(3) NOT NULL DEFAULT 0,
  `birthday` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_UNIQUE`(`account`) USING BTREE,
  UNIQUE INDEX `id_UNIQUE`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '17634721886', '12345678', 'ming', '_2020.12.18_19875c15dcf54238a1b75cca9d3de837284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 20, '1999/10/17', 1);
INSERT INTO `user` VALUES (2, '17634721885', '12345678', 'meng', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/15', 0);
INSERT INTO `user` VALUES (3, '17634721887', '12345678', 'ming', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/15', 0);
INSERT INTO `user` VALUES (4, '17634721888', '12345678', 'ming', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/15', 0);
INSERT INTO `user` VALUES (5, '17634721889', '12345678', 'ming', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/17', 0);
INSERT INTO `user` VALUES (14, '17634721880', '12345678', 'ming', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/22', 0);
INSERT INTO `user` VALUES (15, '17634721881', '12345678', 'meng', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/22', 0);
INSERT INTO `user` VALUES (16, '17634721882', '12345678', 'meng', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/22', 0);
INSERT INTO `user` VALUES (17, '17634721883', '12345678', 'meng', '_2020.07.22_0bbb09c38cd3416aacd22fa461ec3ab0284BC18221BFB0FC182E8C17CEF52EB0.jpg', '男', 0, '2020/07/22', 0);

SET FOREIGN_KEY_CHECKS = 1;
