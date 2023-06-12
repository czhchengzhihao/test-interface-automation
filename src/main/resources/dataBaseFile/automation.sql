/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : automation

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 12/06/2023 11:17:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cases_management
-- ----------------------------
DROP TABLE IF EXISTS `cases_management`;
CREATE TABLE `cases_management`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块',
  `interfaceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  `apiURL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口地址',
  `casesName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用例名称',
  `requestMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `theGinseng` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入参',
  `expectedResults` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预期结果',
  `actualResults` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际结果',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cases_management
-- ----------------------------
INSERT INTO `cases_management` VALUES (1, 'aa', '产品目录筛选-列表', '/crm-price/price/getListGroup', '列表', 'post', '{\r\n    \"dept\":\"\",\r\n    \"flag\":\"\",\r\n    \"key\":\"\",\r\n    \"type\":\"\",\r\n    \"useType\":\"\"\r\n}', 'true', 'true', '2021-07-02 14:34:06', '2021-07-02 14:34:08', NULL);
INSERT INTO `cases_management` VALUES (2, '查询产品目录-模糊查询', '产品目录筛选-列表', '/crm-price/price/getListGroup', '查询', 'post', '{\r\n    \"dept\":\"\",\r\n    \"flag\":\"\",\r\n    \"key\":\"明御数据库安全网关\",\r\n    \"type\":\"\",\r\n    \"useType\":\"\"\r\n}', 'true', 'true', '2021-07-02 14:34:47', '2021-07-02 14:34:49', NULL);

-- ----------------------------
-- Table structure for response_result
-- ----------------------------
DROP TABLE IF EXISTS `response_result`;
CREATE TABLE `response_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `success` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口请求是否成功',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误信息',
  `responseData` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应结果',
  `creationTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of response_result
-- ----------------------------
INSERT INTO `response_result` VALUES (12, 'aaa', 'success', 'ccc', '2021-09-13 14:59:09', 'aaa');
INSERT INTO `response_result` VALUES (13, 'true', 'message', '登陆成功', '2021-09-16 16:43:54', 'aaa');
INSERT INTO `response_result` VALUES (14, 'true', 'responseData', '{\"success\":true}', '2021-09-16 16:58:31', '测试');
INSERT INTO `response_result` VALUES (15, '2222', '1111', '{\"success\":false}', '2022-05-17 16:19:41', '测试');

SET FOREIGN_KEY_CHECKS = 1;
