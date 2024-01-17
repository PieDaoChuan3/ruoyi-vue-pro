/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : ruoyi-vue-pro

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 17/01/2024 09:48:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '影片id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '影片名称',
  `classify` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `director` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导演',
  `hero` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '男主教',
  `heroine` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '女主角',
  `production` date NOT NULL COMMENT '制作时间',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_film_name`(`name`) USING BTREE,
  INDEX `idx_direct_class`(`director`, `classify`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '影片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES (1, '倩女幽魂-3', '恐怖', '徐克', '张国荣', '王祖贤', '1989-10-09', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (2, '蜀山传', '玄幻', '徐克', '郑伊健', '张伯芝', '1993-04-23', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (3, '英雄', '武侠', '张艺谋', '李连杰', '章子怡', '2002-12-24', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (4, '升级', '科幻', '大卫-叶茨2', '罗根-马谢尔-格林', 'unknown', '2020-04-23', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (5, '神奇动物', '科幻', '大卫-叶茨2', '埃迪·雷德梅恩', 'unknown', '2022-01-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (6, '扫黑决战', '枪战', '大卫-叶茨2', '姜武', '李倩', '2021-12-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (7, '月球陨落', '灾难  科幻', '罗兰·艾', '帕特里克·威', '哈莉·贝瑞', '2022-12-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (8, '星际之门', '科幻', '大卫-叶茨2', '李察·狄', 'unknown', '2020-01-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (10, '龙潭虎穴', '动作', '安德列·', '李连杰', '胡凯莉', '2016-02-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (11, '12勇士', '动作', '尼科莱·', '迈克尔·珊', '埃尔莎', '2021-12-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (12, '不忠', '伦理', '阿德里安·莱', '理查·基尔', '黛安·莲恩', '2014-12-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (13, '快递员', '枪战', '扎克瑞', '加里·奥', '欧嘉·柯', '2022-02-21', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (14, '神秘海域', '科幻', '大卫-叶茨2', '彻\r\n导演\r\n\r\n汤姆·赫', 'unknowsxxxx', '2022-01-13', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (15, '狙击手：终极猎杀', '枪战', 'Claudio', 'Tom Ber', 'Danay G', '2018-03-23', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (16, '机械师2：复活', '科幻', '大卫-叶茨2', '杰森·斯', 'unknown', '2016-08-23', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (17, '敢死队', '枪战', '西尔维斯', '李连杰', 'unknown', '2010-08-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (18, '全球风暴', '科幻  动作', '迪安·德', '吉姆·斯', 'unknown', '2022-01-10', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (19, '杀手：代号47', '动作', '泽维尔', '蒂莫西·奥', '欧嘉·柯', '2014-12-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (20, '侏罗纪公园3', '科幻', '大卫-叶茨2', '山姆·尼尔', 'unknown', '2014-06-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (21, '环太平洋', '科幻', '大卫-叶茨2', '查理·汉', 'unknown', '2013-03-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (22, '伸冤人', '枪战', '安东尼', '丹泽尔', '科洛·莫', '2015-02-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (23, '午夜凶铃3（美版）', '恐怖', 'F·贾维尔·', '亚历克斯·', '玛蒂尔达', '2020-03-12', NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_film` VALUES (102, 'film name test-add only', '枪战', '太空人', 'you know', '-', '2022-11-29', NULL, NULL, NULL, NULL, b'0', 1);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_time` datetime(6) NULL DEFAULT NULL COMMENT '下单时间',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `quality` int(11) NULL DEFAULT NULL COMMENT '购买票数',
  `ticket_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电影票id',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `schedule_id` int(11) NULL DEFAULT NULL COMMENT '场次id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK1fpovo17x0pgfmp5edddqry6i`(`schedule_id`) USING BTREE,
  CONSTRAINT `FK1fpovo17x0pgfmp5edddqry6i` FOREIGN KEY (`schedule_id`) REFERENCES `t_schedule` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影票订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (4, '2023-01-10 21:22:23.386000', 250, 2, '86114100', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (5, '2023-01-10 21:22:27.750000', 250, 1, '71989007', 34, 5, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (6, '2023-01-13 16:12:23.221000', 250, 2, '66415014', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (7, '2023-01-13 16:13:29.216000', 250, 2, '06360643', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (8, '2023-01-13 16:15:05.844000', 250, 2, '75634428', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (9, '2023-01-13 16:17:32.987000', 250, 2, '21555664', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (10, '2023-01-13 16:21:47.394000', 250, 2, '79151167', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (11, '2023-01-13 16:23:06.236000', 250, 2, '40650301', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (12, '2023-01-13 16:23:41.499000', 250, 2, '17671507', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (15, '2023-01-13 16:39:49.527000', 250, 3, '15706976', 34, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (17, '2023-01-13 17:32:12.605000', 260, 2, '47203838', 34, 7, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (18, '2023-01-13 17:32:19.746000', 250, 2, '41708690', 34, 10, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (19, '2023-01-14 09:25:25.726000', 250, 3, '17111915', 34, 9, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (20, '2023-01-14 09:25:30.979000', 200, 2, '60957137', 34, 3, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (21, '2023-01-14 09:25:38.740000', 270, 1, '43872193', 34, 16, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (22, '2023-01-14 09:25:44.515000', 280, 2, '50509052', 34, 12, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (23, '2023-01-14 09:26:35.290000', 300, 1, '45738367', 35, 15, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (24, '2023-01-14 09:26:40.570000', 250, 2, '95853970', 35, 10, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (25, '2023-01-14 09:26:46.658000', 200, 3, '03950606', 35, 3, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (26, '2023-01-14 09:27:07.650000', 200, 3, '33516827', 35, 3, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (27, '2023-01-14 09:27:15.090000', 200, 2, '07621525', 35, 4, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (28, '2023-01-14 09:27:57.034000', 280, 2, '49408236', 35, 8, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (29, '2023-01-16 09:41:00.023000', 300, 3, '29838298', 36, 15, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_order` VALUES (30, '2024-01-09 13:36:34.306059', 200, 10, '17520039', 1, 1, '1', '2024-01-09 13:36:34', '1', '2024-01-09 13:36:34', b'0', 1);
INSERT INTO `t_order` VALUES (31, '2024-01-09 15:00:27.376008', 280, 5, '28308789', 1, 8, '1', '2024-01-09 15:00:27', '1', '2024-01-09 15:00:27', b'0', 1);
INSERT INTO `t_order` VALUES (32, '2024-01-09 15:35:59.487622', 270, 1, '17468316', 1, 16, '1', '2024-01-09 15:35:59', '1', '2024-01-09 15:35:59', b'0', 1);
INSERT INTO `t_order` VALUES (33, '2024-01-09 15:45:32.378226', 200, 1, '95879090', 1, 1, '1', '2024-01-09 15:45:32', '1', '2024-01-09 15:45:32', b'0', 1);
INSERT INTO `t_order` VALUES (34, '2024-01-10 12:06:07.217205', 270, 1, '13337563', 1, 16, '1', '2024-01-10 12:06:07', '1', '2024-01-10 12:06:07', b'0', 1);

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场次id',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `quota` int(11) NULL DEFAULT NULL COMMENT '余票数量',
  `show_time` datetime(6) NULL DEFAULT NULL COMMENT '放映时间',
  `f_id` int(11) NULL DEFAULT NULL COMMENT '电影id',
  `theater_id` int(11) NULL DEFAULT NULL COMMENT '剧院id',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK63hfmaxpnyxnxf10sy1wn89oc`(`f_id`) USING BTREE,
  INDEX `FKip90jdmvulcorrmgov74ony2a`(`theater_id`) USING BTREE,
  CONSTRAINT `FK63hfmaxpnyxnxf10sy1wn89oc` FOREIGN KEY (`f_id`) REFERENCES `t_film` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKip90jdmvulcorrmgov74ony2a` FOREIGN KEY (`theater_id`) REFERENCES `t_theater` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影场次表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule
-- ----------------------------
INSERT INTO `t_schedule` VALUES (1, 200, 189, '2023-02-13 09:00:00.000000', 2, 1, 2, NULL, NULL, '1', '2024-01-09 13:36:34', b'0', 1);
INSERT INTO `t_schedule` VALUES (2, 250, 270, '2023-02-13 14:00:00.000000', 2, 2, 12, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (3, 200, 342, '2023-02-14 10:00:00.000000', 3, 3, 3, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (4, 200, 194, '2023-02-14 10:00:00.000000', 3, 1, 1, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (5, 250, 349, '2023-02-14 20:00:00.000000', 3, 2, 1, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (6, 250, 350, '2023-02-17 17:00:36.000000', 4, 2, 0, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (7, 260, 348, '2023-02-17 22:01:19.000000', 4, 2, 1, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (8, 280, 193, '2023-02-17 17:02:48.000000', 4, 3, 2, NULL, NULL, '1', '2024-01-09 15:00:27', b'0', 1);
INSERT INTO `t_schedule` VALUES (9, 250, 267, '2023-02-17 17:03:19.000000', 6, 1, 1, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (10, 250, 266, '2023-02-17 19:03:49.000000', 6, 1, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (11, 250, 270, '2023-02-18 09:19:36.000000', 8, 1, 0, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (12, 280, 198, '2023-02-18 11:20:05.000000', 7, 1, 1, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (13, 290, 250, '2023-01-14 09:20:51.000000', 6, 3, 0, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (14, 300, 200, '2023-01-14 11:21:18.000000', 10, 2, 0, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (15, 300, 196, '2023-02-21 09:22:08.000000', 13, 2, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_schedule` VALUES (16, 270, 147, '2023-02-21 11:22:35.000000', 8, 3, 3, NULL, NULL, '1', '2024-01-09 15:35:59', b'0', 1);

-- ----------------------------
-- Table structure for t_statistics
-- ----------------------------
DROP TABLE IF EXISTS `t_statistics`;
CREATE TABLE `t_statistics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售统计id',
  `amount` int(11) NULL DEFAULT NULL COMMENT '总额',
  `quality` int(11) NULL DEFAULT NULL COMMENT '数量',
  `schedule_id` int(11) NULL DEFAULT NULL COMMENT '场次id',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKorro34omc42w58f8n7xwih236`(`schedule_id`) USING BTREE,
  CONSTRAINT `FKorro34omc42w58f8n7xwih236` FOREIGN KEY (`schedule_id`) REFERENCES `t_schedule` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影票销售统计id' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_statistics
-- ----------------------------
INSERT INTO `t_statistics` VALUES (2, 4750, 19, 2, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (3, 250, 1, 5, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (4, 520, 2, 7, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (5, 1000, 4, 10, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (6, 750, 3, 9, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (7, 1600, 8, 3, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (8, 1080, 4, 16, NULL, NULL, '1', '2024-01-10 12:06:07', b'0', 1);
INSERT INTO `t_statistics` VALUES (9, 560, 2, 12, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (10, 1200, 4, 15, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (11, 400, 2, 4, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (12, 2520, 9, 8, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_statistics` VALUES (13, 2200, 11, 1, '1', '2024-01-09 13:36:34', '1', '2024-01-09 15:45:32', b'0', 1);

-- ----------------------------
-- Table structure for t_theater
-- ----------------------------
DROP TABLE IF EXISTS `t_theater`;
CREATE TABLE `t_theater`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '剧院id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '剧院名称',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '剧院地址',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `capacity` int(11) NOT NULL COMMENT '座位数量',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint(20) NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电影院表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_theater
-- ----------------------------
INSERT INTO `t_theater` VALUES (1, '光明影院', '合肥长江路211号', '64689323', 200, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_theater` VALUES (2, '解放电影院', '肥西路3号', '64612121', 300, NULL, NULL, NULL, NULL, b'0', 1);
INSERT INTO `t_theater` VALUES (3, '人民大剧院', '解放路433号', '64623232', 350, NULL, NULL, NULL, NULL, b'0', 1);

SET FOREIGN_KEY_CHECKS = 1;
