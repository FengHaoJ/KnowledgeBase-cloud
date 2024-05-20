/*
 Navicat Premium Data Transfer

 Source Server         : centos
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.184.130:3306
 Source Schema         : kb-cloud

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 18/05/2024 14:42:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` bigint(20) NOT NULL COMMENT '评论id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '评论者id',
  `k_id` bigint(20) NULL DEFAULT NULL COMMENT '知识id',
  `parent_comment_id` bigint(20) NULL DEFAULT NULL COMMENT '父评论id（若有）',
  `comment` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '评论内容更新时间',
  `status` int(3) NULL DEFAULT NULL COMMENT '状态',
  `permission` int(3) NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for k_preview
-- ----------------------------
DROP TABLE IF EXISTS `k_preview`;
CREATE TABLE `k_preview`  (
  `id` bigint(20) NOT NULL COMMENT '（同知识表）知识id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '（同知识表）知识名称',
  `authors` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识创建者',
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识权利方',
  `abstract` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识摘要',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识关键词',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识路径',
  `kbase_id` bigint(20) NULL DEFAULT NULL COMMENT '知识所属知识库id',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '知识完成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of k_preview
-- ----------------------------

-- ----------------------------
-- Table structure for k_statistics
-- ----------------------------
DROP TABLE IF EXISTS `k_statistics`;
CREATE TABLE `k_statistics`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `k_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的知识id',
  `kb_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的知识库id',
  `collect` bigint(50) NULL DEFAULT NULL COMMENT '收藏量',
  `forward` bigint(50) NULL DEFAULT NULL COMMENT '转发量',
  `view` bigint(50) NULL DEFAULT NULL COMMENT '浏览量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '数据更新时间',
  `status` int(3) NULL DEFAULT NULL COMMENT '状态',
  `permission` int(3) NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of k_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for kbases
-- ----------------------------
DROP TABLE IF EXISTS `kbases`;
CREATE TABLE `kbases`  (
  `id` bigint(20) NOT NULL COMMENT '知识库id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识库名称',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '知识库所属用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '知识库创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '知识库数据更新时间',
  `permission` int(2) NULL DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kbases
-- ----------------------------
INSERT INTO `kbases` VALUES (1, '排序算法', 1, '2024-05-12 21:09:01', '2024-05-12 21:09:03', 0);
INSERT INTO `kbases` VALUES (2, '查找算法', 1, '2024-05-15 22:21:15', '2024-05-15 22:21:18', 0);

-- ----------------------------
-- Table structure for ks
-- ----------------------------
DROP TABLE IF EXISTS `ks`;
CREATE TABLE `ks`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '知识id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识名称',
  `data_type` int(2) NULL DEFAULT NULL COMMENT '知识类型（文件、md文件、图像、视频）',
  `data_content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据内容（文本或者是oss地址）',
  `kbase_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的知识库id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '所属的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '最后一次更新时间',
  `permission` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ks
-- ----------------------------
INSERT INTO `ks` VALUES (1, '冒泡排序', 0, '冒泡排序（Bubble Sort），是一种计算机科学领域的较简单的排序算法。\r\n它重复地走访过要排序的元素列，依次比较两个相邻的元素，如果顺序（如从大到小、首字母从Z到A）错误就把他们交换过来。走访元素的工作是重复地进行，直到没有相邻元素需要交换，也就是说该元素列已经排序完成。\r\n这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端（升序或降序排列），就如同碳酸饮料中二氧化碳的气泡最终会上浮到顶端一样，故名“冒泡排序”。', 1, 1, '2024-05-12 21:10:31', '2024-05-12 21:10:34', 0);
INSERT INTO `ks` VALUES (2, '快速排序', 0, '快速排序采用的是分治思想，即在一个无序的序列中选取一个任意的基准元素pivot，利用pivot将待排序的序列分成两部分，前面部分元素均小于或等于基准元素，后面部分均大于或等于基准元素，然后采用递归的方法分别对前后两部分重复上述操作，直到将无序序列排列成有序序列。', 1, 1, '2024-05-17 21:50:50', '2024-05-17 21:50:50', 0);

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`  (
  `id` bigint(20) NOT NULL COMMENT '权限id',
  `menuname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menus
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `rolename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------

-- ----------------------------
-- Table structure for root
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root`  (
  `id` bigint(20) NOT NULL COMMENT '管理员id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `status` int(2) NULL DEFAULT NULL COMMENT '账号身份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES (1, '1', '123', NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(6) NULL DEFAULT NULL COMMENT '用户状态',
  `permission` int(2) NULL DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '1', '123456', '11111111111', '2024-04-29 17:00:36', '2024-04-29 17:00:36', 1, 1);

-- ----------------------------
-- Table structure for users_menus
-- ----------------------------
DROP TABLE IF EXISTS `users_menus`;
CREATE TABLE `users_menus`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_menus
-- ----------------------------

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
