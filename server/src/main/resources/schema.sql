/*
 Navicat Premium Data Transfer

 Source Server         : one
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : schema

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 07/01/2025 23:24:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  `link_url` varchar(255) DEFAULT NULL,
  `sort_order` int DEFAULT '0',
  `status` tinyint DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of banner
-- ----------------------------
BEGIN;
INSERT INTO `banner` (`id`, `image_url`, `link_url`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (1, '/uploads/banner1.png', '/pages/series/detail?id=1', 1, 1, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `banner` (`id`, `image_url`, `link_url`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (2, '/uploads/banner2.png', '/pages/series/detail?id=2', 2, 1, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `banner` (`id`, `image_url`, `link_url`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (3, '/uploads/banner3.png', '/pages/series/detail?id=3', 3, 1, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `banner` (`id`, `image_url`, `link_url`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (4, '/uploads/banner4.png', '/pages/series/detail?id=4', 4, 1, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `banner` (`id`, `image_url`, `link_url`, `sort_order`, `status`, `create_time`, `update_time`) VALUES (5, '/uploads/banner5.png', '/pages/series/detail?id=5', 5, 1, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
COMMIT;

-- ----------------------------
-- Table structure for box_series
-- ----------------------------
DROP TABLE IF EXISTS `box_series`;
CREATE TABLE `box_series` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text,
  `image_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int DEFAULT '1',
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of box_series
-- ----------------------------
BEGIN;
INSERT INTO `box_series` (`id`, `name`, `description`, `image_url`, `create_time`, `update_time`, `status`, `price`) VALUES (1, '动漫英雄', '收集你喜欢的动漫角色', 'http://localhost:8081/uploads/aeba5e90-acf1-4783-a76f-af9df3d1f946.png', '2025-01-02 10:58:28', '2025-01-07 14:37:37', 1, 29.90);
INSERT INTO `box_series` (`id`, `name`, `description`, `image_url`, `create_time`, `update_time`, `status`, `price`) VALUES (2, '萌系宠物', '可爱的动物伙伴们', 'http://localhost:8081/uploads/cutePet.png', '2025-01-02 10:58:28', '2025-01-07 14:37:37', 1, 29.90);
INSERT INTO `box_series` (`id`, `name`, `description`, `image_url`, `create_time`, `update_time`, `status`, `price`) VALUES (3, '科幻机甲', '未来世界的机械战士', 'http://localhost:8081/uploads/Sci-fi mech.png', '2025-01-02 10:58:28', '2025-01-07 14:37:37', 1, 49.90);
INSERT INTO `box_series` (`id`, `name`, `description`, `image_url`, `create_time`, `update_time`, `status`, `price`) VALUES (4, '魔法少女', '充满魔力的美少女系列', 'http://localhost:8081/uploads/Magical Girl.png', '2025-01-02 10:58:28', '2025-01-07 14:37:37', 1, 19.90);
COMMIT;

-- ----------------------------
-- Table structure for box_style
-- ----------------------------
DROP TABLE IF EXISTS `box_style`;
CREATE TABLE `box_style` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `series_id` bigint NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text,
  `stock` int DEFAULT '0',
  `probability` decimal(5,2) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `series_id` (`series_id`),
  CONSTRAINT `box_style_ibfk_1` FOREIGN KEY (`series_id`) REFERENCES `box_series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of box_style
-- ----------------------------
BEGIN;
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (1, 1, '火影忍者-鸣人', '九尾查克拉模式', 100, 0.08, 'http://localhost:8081/uploads/ca31f5b9-749a-4d8e-b19d-7cdfd9b825f6.jpeg', '2025-01-02 10:58:28', '2025-01-07 14:58:41');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (2, 1, '海贼王-路飞', '四档形态', 100, 0.05, 'http://localhost:8081/uploads/luffy.png', '2025-01-02 10:58:28', '2025-01-07 13:23:44');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (3, 1, '七龙珠-悟空', '超级赛亚人', 100, 0.05, 'http://localhost:8081/uploads/goku.png', '2025-01-02 10:58:28', '2025-01-07 13:23:46');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (4, 1, '灌篮高手-樱木', '红头暴扣', 100, 0.05, 'http://localhost:8081/uploads/sakuragi.png', '2025-01-02 10:58:28', '2025-01-07 13:23:47');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (5, 1, '名侦探柯南', '真相只有一个', 100, 0.05, 'http://localhost:8081/uploads/conan.png', '2025-01-02 10:58:28', '2025-01-07 13:23:49');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (6, 2, '柴犬', '可爱的日本柴犬', 200, 0.10, 'http://localhost:8081/uploads/shiba.png', '2025-01-02 10:58:28', '2025-01-07 13:23:51');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (7, 2, '布偶猫', '优雅的布偶猫', 200, 0.10, 'http://localhost:8081/uploads/ragdoll.png', '2025-01-02 10:58:28', '2025-01-07 13:23:53');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (8, 2, '小仓鼠', '圆滚滚的仓鼠', 200, 0.10, 'http://localhost:8081/uploads/hamster.png', '2025-01-02 10:58:28', '2025-01-07 13:23:54');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (9, 2, '荷兰猪', '胖乎乎的豚鼠', 200, 0.10, 'http://localhost:8081/uploads/guineapig.png', '2025-01-02 10:58:28', '2025-01-07 13:23:55');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (10, 2, '小兔子', '蹦蹦跳跳的兔子', 200, 0.10, 'http://localhost:8081/uploads/rabbit.png', '2025-01-02 10:58:28', '2025-01-07 13:23:56');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (11, 3, '高达-自由', '自由高达', 50, 0.03, 'http://localhost:8081/uploads/freedom.png', '2025-01-02 10:58:28', '2025-01-07 13:23:58');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (12, 3, '高达-强袭', '强袭高达', 50, 0.03, 'http://localhost:8081/uploads/strike.png', '2025-01-02 10:58:28', '2025-01-07 13:23:59');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (13, 3, '高达-命运', '命运高达', 50, 0.03, 'http://localhost:8081/uploads/destiny.png', '2025-01-02 10:58:28', '2025-01-07 13:24:00');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (14, 3, '高达-正义', '正义高达', 50, 0.03, 'http://localhost:8081/uploads/justice.png', '2025-01-02 10:58:28', '2025-01-07 13:24:01');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (15, 3, '高达-强化自由', '强化自由高达', 50, 0.03, 'http://localhost:8081/uploads/sf.png', '2025-01-02 10:58:28', '2025-01-07 13:24:02');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (16, 4, '小圆', '圆环之理', 100, 0.05, 'http://localhost:8081/uploads/madoka.png', '2025-01-02 10:58:28', '2025-01-07 13:24:03');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (17, 4, '小樱', '库洛魔法使', 100, 0.05, 'http://localhost:8081/uploads/sakura.png', '2025-01-02 10:58:28', '2025-01-07 13:24:04');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (18, 4, '美琪', '星光闪耀', 100, 0.05, 'http://localhost:8081/uploads/miki.png', '2025-01-02 10:58:28', '2025-01-07 13:24:05');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (19, 4, '小忧', '月光幻想', 100, 0.05, 'http://localhost:8081/uploads/yu.png', '2025-01-02 10:58:28', '2025-01-07 13:24:06');
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`) VALUES (20, 4, '小焰', '时间守护者', 100, 0.05, 'http://localhost:8081/uploads/homura.png', '2025-01-02 10:58:28', '2025-01-07 13:24:08');
COMMIT;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `ip_address` varchar(50) DEFAULT NULL,
  `login_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `login_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `box_style_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `box_style_id` (`box_style_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`box_style_id`) REFERENCES `box_style` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (1, 1, 1, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (2, 2, 5, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (3, 3, 9, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (4, 4, 2, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (5, 5, 6, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (6, 6, 10, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (7, 7, 3, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (8, 8, 7, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (9, 9, 11, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (10, 10, 4, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (11, 11, 8, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (12, 12, 12, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (13, 13, 1, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (14, 14, 5, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (15, 15, 9, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (16, 16, 2, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (17, 17, 6, 1, 19.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (18, 18, 10, 1, 39.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (19, 19, 3, 1, 29.90, '2025-01-02 10:58:28');
INSERT INTO `order_detail` (`id`, `order_id`, `box_style_id`, `quantity`, `price`, `create_time`) VALUES (20, 20, 11, 1, 39.90, '2025-01-02 10:58:28');
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (1, 2, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (2, 2, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (3, 3, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (4, 4, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (5, 5, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (6, 6, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (7, 7, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (8, 8, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (9, 9, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (10, 10, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (11, 11, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (12, 12, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (13, 13, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (14, 14, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (15, 15, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (16, 16, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (17, 17, 19.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (18, 18, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (19, 19, 29.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`) VALUES (20, 20, 39.90, 'COMPLETED', '2025-01-02 10:58:28', '2025-01-02 10:58:28');
COMMIT;

-- ----------------------------
-- Table structure for payment_record
-- ----------------------------
DROP TABLE IF EXISTS `payment_record`;
CREATE TABLE `payment_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `payment_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `payment_record_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of payment_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recharge_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `box_style_id` bigint NOT NULL,
  `content` text NOT NULL,
  `rating` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `box_style_id` (`box_style_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`box_style_id`) REFERENCES `box_style` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of review
-- ----------------------------
BEGIN;
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (1, 2, 1, '很精致的手办，做工非常好！', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (2, 2, 5, '超可爱的柴犬，和图片一模一样', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (3, 3, 9, '高达的细节处理得很到位', 4, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (4, 4, 2, '路飞的表情刻画得很生动', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (5, 5, 6, '布偶猫太可爱了，很满意', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (6, 6, 10, '强袭高达的涂装很精美', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (7, 7, 3, '悟空的发型做得很帅气', 4, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (8, 8, 7, '小仓鼠萌萌的，很喜欢', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (9, 9, 11, '命运高达的武器很酷', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (10, 10, 4, '樱木的动作很有张力', 4, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (11, 11, 8, '荷兰猪圆滚滚的很可爱', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (12, 12, 12, '正义高达的配色很漂亮', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (13, 13, 1, '鸣人的九尾模式太帅了', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (14, 14, 5, '柴犬的表情很到位', 4, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (15, 15, 9, '自由高达的翅膀很精致', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (16, 16, 2, '路飞的四档造型很独特', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (17, 17, 6, '布偶猫的毛发很柔顺', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (18, 18, 10, '强袭高达的动作很帅', 4, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (19, 19, 3, '悟空的肌肉线条很完美', 5, '2025-01-02 10:58:28');
INSERT INTO `review` (`id`, `user_id`, `box_style_id`, `content`, `rating`, `create_time`) VALUES (20, 20, 11, '命运高达的涂装很精美', 5, '2025-01-02 10:58:28');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `role` varchar(20) DEFAULT 'USER' COMMENT '角色',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信openId',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '微信昵称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别 0-未知 1-男 2-女',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `language` varchar(32) DEFAULT NULL COMMENT '语言',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态 0-禁用 1-正常',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_open_id` (`open_id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (1, 'admin', '$2a$10$znpMPOXjgnVACmYJR.HM5OzlHcnG.i/MVTDrvG2TKu9/7ZpKDrj/6', '管理员', '13800000000', '2025-01-02 10:58:28', '2025-01-04 18:52:23', 'ADMIN', 0.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (2, 'user1', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '张三', '13800000001', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 100.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (3, 'user2', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '李四', '13800000002', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 200.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (4, 'user3', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '王五', '13800000003', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 300.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (5, 'user4', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '赵六', '13800000004', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 400.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (6, 'user5', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '孙七', '13800000005', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 500.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (7, 'user6', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '周八', '13800000006', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 600.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (8, 'user7', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '吴九', '13800000007', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 700.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (9, 'user8', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '郑十', '13800000008', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 800.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (10, 'user9', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '冯十一', '13800000009', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 900.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (11, 'user10', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '陈十二', '13800000010', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1000.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (12, 'user11', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '褚十三', '13800000011', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1100.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (13, 'user12', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '卫十四', '13800000012', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1200.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (14, 'user13', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '蒋十五', '13800000013', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1300.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (15, 'user14', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '沈十六', '13800000014', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1400.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (16, 'user15', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '韩十七', '13800000015', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1500.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (17, 'user16', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '杨十八', '13800000016', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1600.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (18, 'user17', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '朱十九', '13800000017', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1700.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (19, 'user18', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '秦二十', '13800000018', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1800.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (20, 'user19', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '尤二一', '13800000019', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 1900.00);
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `create_time`, `update_time`, `role`, `balance`) VALUES (21, 'user20', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '许二二', '13800000020', '2025-01-02 10:58:28', '2025-01-02 10:58:28', 'USER', 2000.00);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
