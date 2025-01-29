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

 Date: 29/01/2025 10:27:19
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
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标志 0-未删除 1-已删除',
  PRIMARY KEY (`id`),
  KEY `series_id` (`series_id`),
  CONSTRAINT `box_style_ibfk_1` FOREIGN KEY (`series_id`) REFERENCES `box_series` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of box_style
-- ----------------------------
BEGIN;
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 1, '火影忍者-鸣人', '九尾查克拉模式', 100, 0.09, 'http://localhost:8081/uploads/8946b3b9-32a3-4cb9-a7d7-66610a6375d8.png', '2025-01-02 10:58:28', '2025-01-07 14:58:41', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (2, 1, '海贼王-路飞', '四档形态', 100, 0.05, 'http://localhost:8081/uploads/luffy.png', '2025-01-02 10:58:28', '2025-01-07 13:23:44', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (3, 1, '七龙珠-悟空', '超级赛亚人', 100, 0.05, 'http://localhost:8081/uploads/goku.png', '2025-01-02 10:58:28', '2025-01-07 13:23:46', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (4, 1, '灌篮高手-樱木', '红头暴扣', 100, 0.05, 'http://localhost:8081/uploads/sakuragi.png', '2025-01-02 10:58:28', '2025-01-07 13:23:47', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (5, 1, '名侦探柯南', '真相只有一个', 100, 0.05, 'http://localhost:8081/uploads/conan.png', '2025-01-02 10:58:28', '2025-01-07 13:23:49', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (6, 2, '柴犬', '可爱的日本柴犬', 200, 0.10, 'http://localhost:8081/uploads/shiba.png', '2025-01-02 10:58:28', '2025-01-07 13:23:51', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (7, 2, '布偶猫', '优雅的布偶猫', 200, 0.10, 'http://localhost:8081/uploads/ragdoll.png', '2025-01-02 10:58:28', '2025-01-07 13:23:53', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (8, 2, '小仓鼠', '圆滚滚的仓鼠', 200, 0.10, 'http://localhost:8081/uploads/hamster.png', '2025-01-02 10:58:28', '2025-01-07 13:23:54', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (9, 2, '荷兰猪', '胖乎乎的豚鼠', 200, 0.10, 'http://localhost:8081/uploads/guineapig.png', '2025-01-02 10:58:28', '2025-01-07 13:23:55', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (10, 2, '小兔子', '蹦蹦跳跳的兔子', 200, 0.10, 'http://localhost:8081/uploads/rabbit.png', '2025-01-02 10:58:28', '2025-01-07 13:23:56', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (11, 3, '高达-自由', '自由高达', 50, 0.03, 'http://localhost:8081/uploads/freedom.png', '2025-01-02 10:58:28', '2025-01-07 13:23:58', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (12, 3, '高达-强袭', '强袭高达', 50, 0.03, 'http://localhost:8081/uploads/strike.png', '2025-01-02 10:58:28', '2025-01-07 13:23:59', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (13, 3, '高达-命运', '命运高达', 50, 0.03, 'http://localhost:8081/uploads/destiny.png', '2025-01-02 10:58:28', '2025-01-07 13:24:00', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (14, 3, '高达-正义', '正义高达', 50, 0.03, 'http://localhost:8081/uploads/justice.png', '2025-01-02 10:58:28', '2025-01-07 13:24:01', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (15, 3, '高达-强化自由', '强化自由高达', 50, 0.03, 'http://localhost:8081/uploads/sf.png', '2025-01-02 10:58:28', '2025-01-07 13:24:02', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (16, 4, '小圆', '圆环之理', 100, 0.05, 'http://localhost:8081/uploads/madoka.png', '2025-01-02 10:58:28', '2025-01-07 13:24:03', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (17, 4, '小樱', '库洛魔法使', 100, 0.05, 'http://localhost:8081/uploads/sakura.png', '2025-01-02 10:58:28', '2025-01-07 13:24:04', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (18, 4, '美琪', '星光闪耀', 100, 0.05, 'http://localhost:8081/uploads/miki.png', '2025-01-02 10:58:28', '2025-01-07 13:24:05', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (19, 4, '小忧', '月光幻想', 100, 0.05, 'http://localhost:8081/uploads/yu.png', '2025-01-02 10:58:28', '2025-01-07 13:24:06', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (20, 4, '小焰', '时间守护者', 100, 0.05, 'http://localhost:8081/uploads/homura.png', '2025-01-02 10:58:28', '2025-01-07 13:24:08', 0);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (21, 2, '1', NULL, 3, 0.03, 'http://localhost:8081/uploads/47cfd866-82d6-4415-aab8-d3c8015220b8.png', '2025-01-08 20:39:07', '2025-01-08 20:39:13', 1);
INSERT INTO `box_style` (`id`, `series_id`, `name`, `description`, `stock`, `probability`, `image_url`, `create_time`, `update_time`, `is_deleted`) VALUES (22, 1, '1', NULL, 1, 0.01, 'http://localhost:8081/uploads/86f42381-a3bd-4e19-8324-f20b9be61318.png', '2025-01-08 22:04:38', '2025-01-08 22:04:41', 1);
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
  KEY `box_style_id` (`box_style_id`)
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
  `box_style_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1879205416324329479 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1736873103179, 22, 29.90, 'COMPLETED', '2025-01-15 00:45:03', '2025-01-15 00:45:03', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1736873119708, 22, 29.90, 'COMPLETED', '2025-01-15 00:45:19', '2025-01-15 00:45:19', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1736873138161, 22, 29.90, 'COMPLETED', '2025-01-15 00:45:38', '2025-01-15 00:45:38', 10);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1736873141675, 22, 29.90, 'COMPLETED', '2025-01-15 00:45:41', '2025-01-15 00:45:41', 10);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1736925783267, 22, 49.90, 'COMPLETED', '2025-01-15 15:23:03', '2025-01-15 15:23:03', 15);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1879205416324329474, 22, 29.90, 'COMPLETED', '2025-01-15 00:34:19', '2025-01-15 00:34:19', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1879205416324329475, 22, 29.90, 'COMPLETED', '2025-01-15 00:38:54', '2025-01-15 00:38:54', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1879205416324329476, 22, 29.90, 'COMPLETED', '2025-01-15 00:40:33', '2025-01-15 00:40:33', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1879205416324329477, 22, 29.90, 'COMPLETED', '2025-01-15 00:42:15', '2025-01-15 00:42:15', 5);
INSERT INTO `orders` (`id`, `user_id`, `total_amount`, `status`, `create_time`, `update_time`, `box_style_id`) VALUES (1879205416324329478, 22, 29.90, 'COMPLETED', '2025-01-15 00:43:20', '2025-01-15 00:43:20', 5);
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
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `role` varchar(20) DEFAULT 'USER' COMMENT '角色',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `open_id` varchar(64) DEFAULT NULL COMMENT '微信openId',
  `sessionKey` varchar(255) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL COMMENT '微信昵称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `gender` tinyint DEFAULT NULL COMMENT '性别 0-未知 1-男 2-女',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `language` varchar(32) DEFAULT NULL COMMENT '语言',
  `status` tinyint DEFAULT '1' COMMENT '状态 0-禁用 1-正常',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_open_id` (`open_id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (1, 'admin', '$2a$10$znpMPOXjgnVACmYJR.HM5OzlHcnG.i/MVTDrvG2TKu9/7ZpKDrj/6', '管理员', '13800000000', 'ADMIN', 0.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-04 18:52:23');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (2, 'user1', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '张三', '13800000001', 'USER', 100.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (3, 'user2', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '李四', '13800000002', 'USER', 200.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (4, 'user3', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '王五', '13800000003', 'USER', 300.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (5, 'user4', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '赵六', '13800000004', 'USER', 400.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (6, 'user5', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '孙七', '13800000005', 'USER', 500.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (7, 'user6', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '周八', '13800000006', 'USER', 600.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (8, 'user7', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '吴九', '13800000007', 'USER', 700.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (9, 'user8', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '郑十', '13800000008', 'USER', 800.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (10, 'user9', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '冯十一', '13800000009', 'USER', 900.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (11, 'user10', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '陈十二', '13800000010', 'USER', 1000.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (12, 'user11', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '褚十三', '13800000011', 'USER', 1100.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (13, 'user12', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '卫十四', '13800000012', 'USER', 1200.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (14, 'user13', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '蒋十五', '13800000013', 'USER', 1300.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (15, 'user14', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '沈十六', '13800000014', 'USER', 1400.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (16, 'user15', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '韩十七', '13800000015', 'USER', 1500.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (17, 'user16', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '杨十八', '13800000016', 'USER', 1600.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (18, 'user17', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '朱十九', '13800000017', 'USER', 1700.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (19, 'user18', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '秦二十', '13800000018', 'USER', 1800.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (20, 'user19', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '尤二一', '13800000019', 'USER', 1900.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (21, 'user20', '$2a$10$NlZmXfP8X8X8X8X8X8X8X.2P.6NPGUNTEhPhwzQKmxwDnZ8Yj0y6q', '许二二', '13800000020', 'USER', 2000.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2025-01-02 10:58:28', '2025-01-02 10:58:28');
INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `phone`, `role`, `balance`, `open_id`, `sessionKey`, `nick_name`, `avatar_url`, `gender`, `country`, `province`, `city`, `language`, `status`, `last_login_time`, `create_time`, `update_time`) VALUES (22, NULL, NULL, NULL, NULL, 'USER', 583.70, 'oEKiS6w8a-A3pQS41vtSAx1dVzd4', 'z/tuI29TEaF+XWAmBN+QdQ==', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
