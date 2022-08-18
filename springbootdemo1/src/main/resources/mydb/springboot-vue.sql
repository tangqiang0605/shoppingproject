/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : springboot-vue

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 18/08/2022 23:06:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '顾客id',
  `cname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '顾客昵称',
  `cpassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '顾客密码',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (2, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (3, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (4, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (5, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (6, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (7, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (8, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (9, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (10, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (11, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (12, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (13, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (14, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (15, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (16, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (17, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (18, '炫酷小火龙', '1');
INSERT INTO `customer` VALUES (19, '炫酷小火龙', '1');

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `did` int NOT NULL AUTO_INCREMENT,
  `dname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `dpassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (1, '超速闪电侠', '1');
INSERT INTO `delivery` VALUES (2, '1', '1');
INSERT INTO `delivery` VALUES (3, '1', '1');
INSERT INTO `delivery` VALUES (4, '1', '1');
INSERT INTO `delivery` VALUES (5, '111', '1');
INSERT INTO `delivery` VALUES (6, '1', '1');
INSERT INTO `delivery` VALUES (7, '1', '1');
INSERT INTO `delivery` VALUES (8, '1', '1');
INSERT INTO `delivery` VALUES (9, '华强', '1111');
INSERT INTO `delivery` VALUES (10, 'fdfasdfdsafdas', '1');
INSERT INTO `delivery` VALUES (11, '1', '1');
INSERT INTO `delivery` VALUES (12, '1', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `gid` int NOT NULL AUTO_INCREMENT,
  `gsave` bigint NULL DEFAULT 0 COMMENT '库存',
  `gsales` bigint NULL DEFAULT 0 COMMENT '销售量',
  `sid` int NULL DEFAULT NULL COMMENT '商家',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封禁中，已上架，仓库，申请解除中',
  `srcid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片资源id',
  `gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `gonlinenum` int NULL DEFAULT 0,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (102, 10, 30, 102, '仓库中', '100', '汉堡背包', 0, NULL);
INSERT INTO `goods` VALUES (103, 289, 21, 101, '仓库中', '100', '汉堡背包', 10, NULL);
INSERT INTO `goods` VALUES (104, 300, 10, 101, '仓库中', '100', '汉堡背包', 10, NULL);
INSERT INTO `goods` VALUES (105, 10, 11, 100, '仓库中', '120', '老婆壁纸超值特惠优惠实惠价格公道数量有限', 299, NULL);
INSERT INTO `goods` VALUES (112, 300, 0, 100, '仓库中', '161', '欢', 1111, NULL);
INSERT INTO `goods` VALUES (113, 300, 1, 100, '仓库中', '145', 'a', 99, NULL);
INSERT INTO `goods` VALUES (114, 1, 0, 100, '仓库中', '100', 'a', 300, NULL);
INSERT INTO `goods` VALUES (115, 1, 0, 100, '仓库中', '100', 'a', 300, NULL);
INSERT INTO `goods` VALUES (116, 300, 0, 100, '仓库中', '141', 'a', 1, NULL);
INSERT INTO `goods` VALUES (120, 1, 0, 100, '仓库中', '100', 'a', 300, NULL);
INSERT INTO `goods` VALUES (122, 1, 0, 100, '仓库中', '100', 'a', 300, NULL);
INSERT INTO `goods` VALUES (123, 300, 1, 100, '仓库中', '144', 'a', 0, NULL);
INSERT INTO `goods` VALUES (124, 1, 0, 100, '仓库中', '100', '1', 300, NULL);
INSERT INTO `goods` VALUES (127, 1, 0, 100, '已上架', '100', '1', 300, NULL);
INSERT INTO `goods` VALUES (128, 1, 0, 100, '已上架', '100', '1', 300, NULL);
INSERT INTO `goods` VALUES (129, 1, 0, 100, '仓库中', '100', '1', 300, NULL);
INSERT INTO `goods` VALUES (130, 1, 0, 100, '仓库中', '121', '1', 300, NULL);
INSERT INTO `goods` VALUES (132, 0, 0, 100, '已删除', '130', '1', 1, '2022-08-18 15:30:30');
INSERT INTO `goods` VALUES (134, 111111, 0, 100, '已删除', '133', '1', 0, '2022-08-18 15:30:30');
INSERT INTO `goods` VALUES (135, 1993, 6, 100, '已删除', '133', 'q', 0, '2022-08-18 15:30:30');
INSERT INTO `goods` VALUES (136, 1, 0, 100, '已删除', '134', '1', 0, '2022-08-18 15:30:30');
INSERT INTO `goods` VALUES (137, 2, 0, 100, '已删除', '135', '2', 0, '2022-08-18 15:30:30');
INSERT INTO `goods` VALUES (138, 0, 0, 100, '已删除', '135', '2', 2, NULL);
INSERT INTO `goods` VALUES (140, 1, 0, 100, '已删除', '146', '1', 0, NULL);
INSERT INTO `goods` VALUES (141, 1, 0, 100, '已删除', '146', '1', 0, NULL);
INSERT INTO `goods` VALUES (142, 2, 0, 100, '已删除', '147', '2', 0, NULL);
INSERT INTO `goods` VALUES (143, 1, 0, 100, '已删除', '148', '1', 0, NULL);
INSERT INTO `goods` VALUES (145, 1, 0, 100, '已删除', '150', '1', 0, NULL);
INSERT INTO `goods` VALUES (149, 0, 0, 100, '已删除', '154', 'a', 111, NULL);
INSERT INTO `goods` VALUES (152, 0, 1, 1, '已上架', '163', '1', 100, NULL);
INSERT INTO `goods` VALUES (153, 0, 2, 1, '已上架', '167', '2', 10, NULL);
INSERT INTO `goods` VALUES (154, 0, 0, 1, '已上架', '162', '3', 3, NULL);
INSERT INTO `goods` VALUES (155, 0, 4, 1, '已删除', '162', '4', 0, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `oid` int NOT NULL AUTO_INCREMENT,
  `ostate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `did` int NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `sid` int NULL DEFAULT NULL,
  `isdeli` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '已完成', 5, 1, 101, 0);
INSERT INTO `orders` VALUES (2, '已完成', 5, 1, 102, 1);
INSERT INTO `orders` VALUES (3, '已完成', 5, 1, 101, 1);
INSERT INTO `orders` VALUES (4, '已完成', 5, 1, 100, 1);
INSERT INTO `orders` VALUES (5, '配送中', 9, 1, 100, 1);
INSERT INTO `orders` VALUES (7, '配送中', 9, 1, 102, 0);
INSERT INTO `orders` VALUES (8, '已送达', 5, 1, 101, 0);
INSERT INTO `orders` VALUES (9, '配送中', 9, 1, 100, 0);
INSERT INTO `orders` VALUES (10, '配送中', 9, 1, 100, 0);
INSERT INTO `orders` VALUES (11, '配送中', 9, 1, 102, 1);
INSERT INTO `orders` VALUES (12, '已送达', 5, 1, 102, 1);
INSERT INTO `orders` VALUES (13, '已送达', 5, 17, 100, 1);
INSERT INTO `orders` VALUES (14, '已送达', 5, 17, 101, 1);
INSERT INTO `orders` VALUES (15, '已送达', 5, 17, 100, 1);
INSERT INTO `orders` VALUES (16, '配送中', 5, 11, 101, 1);
INSERT INTO `orders` VALUES (17, '配送中', 5, 11, 101, 0);
INSERT INTO `orders` VALUES (18, '配送中', 5, 11, 100, 0);
INSERT INTO `orders` VALUES (19, '待发货', NULL, 1, 102, 1);
INSERT INTO `orders` VALUES (20, '配送中', 5, 1, 100, 1);
INSERT INTO `orders` VALUES (21, '待发货', NULL, 1, 100, 0);
INSERT INTO `orders` VALUES (22, '待发货', NULL, 1, 102, 1);
INSERT INTO `orders` VALUES (23, '已发货', NULL, 1, 1, 0);
INSERT INTO `orders` VALUES (24, '已发货', NULL, 1, 1, 0);
INSERT INTO `orders` VALUES (25, '待发货', NULL, 1, 1, 0);

-- ----------------------------
-- Table structure for orderson
-- ----------------------------
DROP TABLE IF EXISTS `orderson`;
CREATE TABLE `orderson`  (
  `oid` int NULL DEFAULT NULL,
  `gid` int NULL DEFAULT NULL,
  `oamount` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderson
-- ----------------------------
INSERT INTO `orderson` VALUES (1, 103, 1);
INSERT INTO `orderson` VALUES (1, 104, 1);
INSERT INTO `orderson` VALUES (2, 102, 1);
INSERT INTO `orderson` VALUES (3, 103, 1);
INSERT INTO `orderson` VALUES (3, 104, 1);
INSERT INTO `orderson` VALUES (4, 100, 1);
INSERT INTO `orderson` VALUES (4, 105, 1);
INSERT INTO `orderson` VALUES (4, 110, 1);
INSERT INTO `orderson` VALUES (5, 100, 1);
INSERT INTO `orderson` VALUES (5, 105, 1);
INSERT INTO `orderson` VALUES (5, 110, 1);
INSERT INTO `orderson` VALUES (7, 102, 1);
INSERT INTO `orderson` VALUES (8, 103, 1);
INSERT INTO `orderson` VALUES (8, 104, 1);
INSERT INTO `orderson` VALUES (9, 100, 1);
INSERT INTO `orderson` VALUES (9, 105, 1);
INSERT INTO `orderson` VALUES (10, 100, 1);
INSERT INTO `orderson` VALUES (10, 105, 1);
INSERT INTO `orderson` VALUES (11, 102, 1);
INSERT INTO `orderson` VALUES (12, 102, 5);
INSERT INTO `orderson` VALUES (13, 100, 2);
INSERT INTO `orderson` VALUES (13, 105, 1);
INSERT INTO `orderson` VALUES (13, 110, 2);
INSERT INTO `orderson` VALUES (14, 103, 9);
INSERT INTO `orderson` VALUES (15, 110, 1);
INSERT INTO `orderson` VALUES (16, 103, 1);
INSERT INTO `orderson` VALUES (17, 103, 1);
INSERT INTO `orderson` VALUES (18, 135, 1);
INSERT INTO `orderson` VALUES (18, 133, 1);
INSERT INTO `orderson` VALUES (19, 102, 5);
INSERT INTO `orderson` VALUES (20, 135, 5);
INSERT INTO `orderson` VALUES (21, 113, 1);
INSERT INTO `orderson` VALUES (21, 123, 1);
INSERT INTO `orderson` VALUES (22, 102, 15);
INSERT INTO `orderson` VALUES (23, 153, 1);
INSERT INTO `orderson` VALUES (23, 152, 1);
INSERT INTO `orderson` VALUES (24, 155, 3);
INSERT INTO `orderson` VALUES (25, 155, 1);
INSERT INTO `orderson` VALUES (25, 153, 1);

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `cid` int NULL DEFAULT NULL,
  `gid` int NULL DEFAULT NULL,
  `oamount` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (1, 148, 1);
INSERT INTO `shoppingcart` VALUES (1, 131, 1);
INSERT INTO `shoppingcart` VALUES (1, 133, 1);

-- ----------------------------
-- Table structure for src
-- ----------------------------
DROP TABLE IF EXISTS `src`;
CREATE TABLE `src`  (
  `srcid` int NOT NULL AUTO_INCREMENT,
  `srcurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `srcname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`srcid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 162 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of src
-- ----------------------------
INSERT INTO `src` VALUES (100, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', '汉堡');
INSERT INTO `src` VALUES (103, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-99dd5d60d78e616f0841f3cb72bed12d_hd.jpg&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1662941678&t=57b6900a55081221021a5fca675ced2e', 'lingboli');
INSERT INTO `src` VALUES (108, 'http://localhost:8181/src/14888e3044864f0e908b408fe38dda9a', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\14888e3044864f0e908b408fe38dda9a_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (109, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.zhimg.com%2F50%2Fv2-99dd5d60d78e616f0841f3cb72bed12d_hd.jpg&refer=http%3A%2F%2Fpic2.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1662941678&t=57b6900a55081221021a5fca675ced2e', 'lingboli');
INSERT INTO `src` VALUES (110, 'http://localhost:8181/src/7f110ea3bb324bc8a421cfd5045f57de', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\7f110ea3bb324bc8a421cfd5045f57de_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (111, 'http://localhost:8181/src/aa33ca7145624e0184f19deec39c46c4', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\aa33ca7145624e0184f19deec39c46c4_1.webp');
INSERT INTO `src` VALUES (112, 'http://localhost:8181/src/3e1eefa93479410a96d0386b237b0ade', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\3e1eefa93479410a96d0386b237b0ade_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (113, 'http://localhost:8181/src/2584edd1ae244e8bace37ebd45c6973c', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\2584edd1ae244e8bace37ebd45c6973c_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (114, 'http://localhost:8181/src/73220c27cbe9493b8cd8b1c7443b4270', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\73220c27cbe9493b8cd8b1c7443b4270_2.png');
INSERT INTO `src` VALUES (115, 'http://localhost:8181/src/fbb1c0f6f8ac4cceaee7ba1d078ff0c6', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\fbb1c0f6f8ac4cceaee7ba1d078ff0c6_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (116, 'http://localhost:8181/src/4baf872b3e3b474faa6e049e9e5a80c1', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\4baf872b3e3b474faa6e049e9e5a80c1_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (117, 'http://localhost:8181/src/138e19e25e154c9e90da1307f959e1cb', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\138e19e25e154c9e90da1307f959e1cb_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (118, 'http://localhost:8181/src/4cac0fa32a104abfb4cb423a248ef35f', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\4cac0fa32a104abfb4cb423a248ef35f_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (119, 'http://localhost:8181/src/0b08839a04a540ffa0de0903723cee00', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\0b08839a04a540ffa0de0903723cee00_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (120, 'http://localhost:8181/src/83a409f86e934234aabed98a56bb3133', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\83a409f86e934234aabed98a56bb3133_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (121, 'http://localhost:8181/src/d2bc17a41c7f41a8a2e57024a73ed137', 'D:\\tplcode\\project\\springbootvue2\\springbootdemo1\\src\\main\\resources\\files\\d2bc17a41c7f41a8a2e57024a73ed137_5b0a20cd35ea8f4ce37f53594b31cea4f2172529.jpg@942w_1169h_progressive.webp');
INSERT INTO `src` VALUES (122, 'http://localhost:8181/src/98ee19394d48452ea3595f6adacad8bd', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\98ee19394d48452ea3595f6adacad8bd_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (123, 'http://localhost:8181/src/d327be9ce456482fadba825837f73ea7', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\d327be9ce456482fadba825837f73ea7_fb861b78de5ee48ea565ab2d2347ab5dd60fbd96.jpg@942w_1389h_progressive.webp');
INSERT INTO `src` VALUES (124, 'http://localhost:8181/src/68907f23ede0473b97792008e3206a3c', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\68907f23ede0473b97792008e3206a3c_3b6d955b72d835cbb158e5f0e2c7dc00c254d122.jpg@942w_1320h_progressive.webp');
INSERT INTO `src` VALUES (125, 'http://localhost:8181/src/6b05ad04057944e3942896d7022d8613', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\6b05ad04057944e3942896d7022d8613_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (126, 'http://localhost:8181/src/69a7289224114a8fb71d9222a09675ca', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\69a7289224114a8fb71d9222a09675ca_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (127, 'http://localhost:8181/src/45426aa3eae14e34822b4aa3972c92b4', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\45426aa3eae14e34822b4aa3972c92b4_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (128, 'http://localhost:8181/src/036363515d3244cd9d5360db5aff5c53', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\036363515d3244cd9d5360db5aff5c53_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (129, 'http://localhost:8181/src/a0436a10712f45868f5778d6ab0cbe21', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\a0436a10712f45868f5778d6ab0cbe21_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (130, 'http://localhost:8181/src/8c710ed52d984e58825b722e91ee7c29', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\8c710ed52d984e58825b722e91ee7c29_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (131, 'http://localhost:8181/src/0a3ba175a5bd4b1082ef43cb326c8465', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\0a3ba175a5bd4b1082ef43cb326c8465_22a3823e37fc5ef11ae1f46156f12e41bad1de26.jpg@942w_668h_progressive.webp');
INSERT INTO `src` VALUES (132, 'http://localhost:8181/src/d50e793edb1b4df6af82ee563f51e5fc', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\d50e793edb1b4df6af82ee563f51e5fc_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (133, 'http://localhost:8181/src/151d078cf45347ada3387cd7611e48d7', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\151d078cf45347ada3387cd7611e48d7_浮图秀图片_wallhaven.cc_20220321233559.jpg');
INSERT INTO `src` VALUES (134, 'http://localhost:8181/src/f87ad41a69a64584ae23eb2dea37e153', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\f87ad41a69a64584ae23eb2dea37e153_22a3823e37fc5ef11ae1f46156f12e41bad1de26.jpg@942w_668h_progressive.webp');
INSERT INTO `src` VALUES (135, 'http://localhost:8181/src/7248da4678444caca66fb31833c6502b', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\7248da4678444caca66fb31833c6502b_3b6d955b72d835cbb158e5f0e2c7dc00c254d122.jpg@942w_1320h_progressive.webp');
INSERT INTO `src` VALUES (136, 'http://localhost:8181/src/985d7754fc1440f99a2efff82cf25972', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\985d7754fc1440f99a2efff82cf25972_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (137, 'http://localhost:8181/src/afc57e2cdc214af0884a96f6624038ce', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\afc57e2cdc214af0884a96f6624038ce_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (138, 'http://localhost:8181/src/b1dccd1a13b3498aa17e43e18d45283b', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\b1dccd1a13b3498aa17e43e18d45283b_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (139, 'http://localhost:8181/src/cb51951083d3473e9f4942dd58100ca1', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\cb51951083d3473e9f4942dd58100ca1_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (140, 'http://localhost:8181/src/b3e869a871b24305ad489bde64d07db1', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\b3e869a871b24305ad489bde64d07db1_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (141, 'http://localhost:8181/src/b2ada540b8034f1299cd44e0393caf19', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\b2ada540b8034f1299cd44e0393caf19_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (142, 'http://localhost:8181/src/0fe1a8b5ed384468acbb3a41f81e1dec', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\0fe1a8b5ed384468acbb3a41f81e1dec_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (143, 'http://localhost:8181/src/9eb9f3220f7746289e87431d9372f2ad', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\9eb9f3220f7746289e87431d9372f2ad_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (144, 'http://localhost:8181/src/9188e843069e48069374231255a11dca', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\9188e843069e48069374231255a11dca_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (145, 'http://localhost:8181/src/edccb68d65434977bd80c913dd58dd43', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\edccb68d65434977bd80c913dd58dd43_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (146, 'http://localhost:8181/src/870aed4651e1450e8da7d5283fd5a8b9', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\870aed4651e1450e8da7d5283fd5a8b9_2.png');
INSERT INTO `src` VALUES (147, 'http://localhost:8181/src/f87f8453b67e4cb496cfae8432ee6625', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\f87f8453b67e4cb496cfae8432ee6625_2b6eb920fe2cb9088cb1bf373552769b341e3d76.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (148, 'http://localhost:8181/src/02ee7d512a5844bf8c3a48206ce7bb09', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\02ee7d512a5844bf8c3a48206ce7bb09_fb861b78de5ee48ea565ab2d2347ab5dd60fbd96.jpg@942w_1389h_progressive.webp');
INSERT INTO `src` VALUES (149, 'http://localhost:8181/src/721f67ad272449aea75fa76d26d798bf', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\721f67ad272449aea75fa76d26d798bf_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (150, 'http://localhost:8181/src/fee74659c96e4358af3c7ef8211ce1a0', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\fee74659c96e4358af3c7ef8211ce1a0_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (151, 'http://localhost:8181/src/096e631bc1c142ed88a1ef6d112fb2a0', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\096e631bc1c142ed88a1ef6d112fb2a0_c53c811f880411ebb6edd017c2d2eca2 (2).jpg');
INSERT INTO `src` VALUES (152, 'http://localhost:8181/src/f3dcf9b107084a7d84fdae4256333022', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\f3dcf9b107084a7d84fdae4256333022_wife.webp');
INSERT INTO `src` VALUES (153, 'http://localhost:8181/src/5463cc53d27246a582a3ab6787a2c628', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\5463cc53d27246a582a3ab6787a2c628_22a3823e37fc5ef11ae1f46156f12e41bad1de26.jpg@942w_668h_progressive.webp');
INSERT INTO `src` VALUES (154, 'http://localhost:8181/src/9614c3414b464d1982e12864a2a1d57f', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\9614c3414b464d1982e12864a2a1d57f_cat.png');
INSERT INTO `src` VALUES (155, 'http://localhost:8181/src/fb55fefad71b47ceb2c720c289cdc6ba', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\fb55fefad71b47ceb2c720c289cdc6ba_c53c811f880411ebb6edd017c2d2eca2 (2).jpg');
INSERT INTO `src` VALUES (156, 'http://localhost:8181/src/ec9b5d20f15d4b6b841203df7a6b8930', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\ec9b5d20f15d4b6b841203df7a6b8930_浮图秀图片_www.bilibili.com_20220113174442.jpg');
INSERT INTO `src` VALUES (157, 'http://localhost:8181/src/4c4bec16b7454c28ac0f00a57dd11664', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\4c4bec16b7454c28ac0f00a57dd11664_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (158, 'http://localhost:8181/src/2ff4e733aafc425b909dd176e0adc7f2', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\2ff4e733aafc425b909dd176e0adc7f2_22a3823e37fc5ef11ae1f46156f12e41bad1de26.jpg@942w_668h_progressive.webp');
INSERT INTO `src` VALUES (159, 'http://localhost:8181/src/c2107ca28b6a4e07a0a775ef779823fd', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\c2107ca28b6a4e07a0a775ef779823fd_微信图片_20220113224703.jpg');
INSERT INTO `src` VALUES (160, 'http://localhost:8181/src/c2d06a7e634c4cffb00fd2c8777f2e62', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\c2d06a7e634c4cffb00fd2c8777f2e62_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (161, 'http://localhost:8181/src/65dd6da2dec44cf28a5014b8e956e136', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\65dd6da2dec44cf28a5014b8e956e136_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (162, 'http://localhost:8181/src/f83ff78138c346b0920b4da834dee668', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\f83ff78138c346b0920b4da834dee668_010150239b3a1c37af551ec4739abb2a4518be21.jpg@942w_1803h_progressive.webp');
INSERT INTO `src` VALUES (163, 'http://localhost:8181/src/e70919810f274bd7b0ef3255644b6faa', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\e70919810f274bd7b0ef3255644b6faa_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (164, 'http://localhost:8181/src/a76cd047efb04589a170400a4583b4f0', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\a76cd047efb04589a170400a4583b4f0_16326457677012.thumb.jpg');
INSERT INTO `src` VALUES (165, 'http://localhost:8181/src/8e568f7e3bf24a838954f4eb49bf5207', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\8e568f7e3bf24a838954f4eb49bf5207_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (166, 'http://localhost:8181/src/11dc668164a34bba82993c361962b488', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\11dc668164a34bba82993c361962b488_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');
INSERT INTO `src` VALUES (167, 'http://localhost:8181/src/5babd4fde9cd46b994a5948aef3d1d91', 'D:\\tplcode\\project\\springbootvue3\\springbootdemo1\\src\\main\\resources\\files\\5babd4fde9cd46b994a5948aef3d1d91_879544dccd1ca6e8422f27f6e07ccbbb66d7af2b.jpg@942w_1334h_progressive.webp');

-- ----------------------------
-- Table structure for storekeeper
-- ----------------------------
DROP TABLE IF EXISTS `storekeeper`;
CREATE TABLE `storekeeper`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `spassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `isban` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storekeeper
-- ----------------------------
INSERT INTO `storekeeper` VALUES (1, '大臂斗超市', '1', 0);
INSERT INTO `storekeeper` VALUES (100, '大臂斗超市', '1', 0);
INSERT INTO `storekeeper` VALUES (101, 'niconico', '1111', 0);
INSERT INTO `storekeeper` VALUES (102, '好吃家', '1111', 0);
INSERT INTO `storekeeper` VALUES (103, '好吃家', '1111', 0);
INSERT INTO `storekeeper` VALUES (104, '好吃家', '1111', 0);
INSERT INTO `storekeeper` VALUES (106, '1', '1', 0);
INSERT INTO `storekeeper` VALUES (107, '1', '1', 0);
INSERT INTO `storekeeper` VALUES (108, '1', '1', 0);
INSERT INTO `storekeeper` VALUES (109, '1', '1', 0);
INSERT INTO `storekeeper` VALUES (110, '1', '1', 0);

SET FOREIGN_KEY_CHECKS = 1;
