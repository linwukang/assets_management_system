/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80027
Source Host           : 127.0.0.1:3306
Source Database       : assets_management_test

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2023-05-26 12:03:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for assets_borrow
-- ----------------------------
DROP TABLE IF EXISTS `assets_borrow`;
CREATE TABLE `assets_borrow` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借用单号',
  `asset_id` int NOT NULL COMMENT '资产id',
  `borrower_id` int NOT NULL COMMENT '借用人员id',
  `borrow_date` datetime NOT NULL COMMENT '借用日期',
  `borrow_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借用原因',
  `return_date` datetime DEFAULT NULL COMMENT '归还日期',
  `return_state` enum('returned','not_returned') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'not_returned' COMMENT '归还状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `borrower_id` (`borrower_id`) USING BTREE,
  KEY `assets_borrow_ibfk_1` (`asset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='资产借用';

-- ----------------------------
-- Records of assets_borrow
-- ----------------------------
INSERT INTO `assets_borrow` VALUES ('1', 'JY20190103174726', '1', '1', '2023-03-21 16:44:07', '玩', '2023-05-25 00:00:00', 'returned', null);
INSERT INTO `assets_borrow` VALUES ('2', 'JY20190103174722', '2', '2', '2023-03-21 16:44:26', '拿去卖', '2023-05-07 00:00:00', 'returned', '?????????????????????????');
INSERT INTO `assets_borrow` VALUES ('3', 'JY20190106541561', '3', '3', '2023-03-21 17:41:19', '抢', null, 'not_returned', null);
INSERT INTO `assets_borrow` VALUES ('4', 'JY20190108941515', '4', '9', '2023-03-21 17:41:19', '', '2023-05-18 00:00:00', 'returned', null);
INSERT INTO `assets_borrow` VALUES ('5', 'JY20190109848994', '5', '6', '2023-03-21 17:41:19', '', '2023-05-02 00:00:00', 'returned', null);
INSERT INTO `assets_borrow` VALUES ('6', 'JY20230505185608', '1', '4', '2023-05-02 00:00:00', '撒旦', null, 'not_returned', null);
INSERT INTO `assets_borrow` VALUES ('7', 'JY20230505185654', '1', '4', '2023-05-02 00:00:00', '撒旦', null, 'not_returned', 'aea');
INSERT INTO `assets_borrow` VALUES ('8', 'JY20230505185726', '1', '4', '2023-05-02 00:00:00', '撒旦', null, 'not_returned', null);
INSERT INTO `assets_borrow` VALUES ('9', 'JY20230505190011', '1', '4', '2023-05-01 00:00:00', '阿迪斯', null, 'not_returned', null);
INSERT INTO `assets_borrow` VALUES ('10', 'JY20230509112217', '2', '2', '2023-05-01 00:00:00', 'wfafa', '2023-05-01 00:00:00', 'not_returned', 'asd');

-- ----------------------------
-- Table structure for assets_class
-- ----------------------------
DROP TABLE IF EXISTS `assets_class`;
CREATE TABLE `assets_class` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产类别编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产类别名称',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='资产类别';

-- ----------------------------
-- Records of assets_class
-- ----------------------------
INSERT INTO `assets_class` VALUES ('1', 'ZCLB0001', '计算机设备及软件', '1', '2023-03-21 13:09:50');
INSERT INTO `assets_class` VALUES ('2', 'ZCLB0002', '办公设备', '1', '2023-03-21 13:10:19');
INSERT INTO `assets_class` VALUES ('3', 'ZCLB0003', '通信设备', '0', '2023-03-21 13:10:36');
INSERT INTO `assets_class` VALUES ('4', 'ZCLB0004', '车辆类别', '0', '2023-03-21 13:10:58');

-- ----------------------------
-- Table structure for assets_information_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `assets_information_maintenance`;
CREATE TABLE `assets_information_maintenance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产编号',
  `asset_name` varchar(64) NOT NULL COMMENT '资产名称',
  `asset_class_id` int NOT NULL COMMENT '资产类别id',
  `supplier_id` int NOT NULL COMMENT '供应商id',
  `brand_id` int NOT NULL COMMENT '品牌id',
  `gaining_method_id` int NOT NULL COMMENT '取得方式id',
  `storage_time` datetime NOT NULL COMMENT '入库时间',
  `storage_place_id` int unsigned NOT NULL COMMENT '存放地点id',
  `asset_photograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资产照片',
  PRIMARY KEY (`id`),
  KEY `asset_class_id` (`asset_class_id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `brand_id` (`brand_id`),
  KEY `assets_storage_ibfk_4` (`gaining_method_id`),
  KEY `assets_storage_ibfk_5` (`storage_place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COMMENT='资产入库';

-- ----------------------------
-- Records of assets_information_maintenance
-- ----------------------------
INSERT INTO `assets_information_maintenance` VALUES ('1', 'ZC0001', '投影仪', '1', '4', '5', '1', '2023-03-21 16:37:35', '1', '');
INSERT INTO `assets_information_maintenance` VALUES ('2', 'ZC0002', '签字币', '2', '3', '2', '2', '2023-03-22 16:37:35', '3', '');
INSERT INTO `assets_information_maintenance` VALUES ('3', 'ZC0003', '笔记本', '2', '4', '4', '3', '2023-03-22 16:37:35', '5', '');
INSERT INTO `assets_information_maintenance` VALUES ('4', 'ZC0004', '华为手机', '3', '4', '4', '2', '2023-03-22 16:37:35', '3', '');
INSERT INTO `assets_information_maintenance` VALUES ('5', 'ZC0005', '手机S820', '3', '3', '1', '3', '2023-03-22 16:37:35', '4', '');
INSERT INTO `assets_information_maintenance` VALUES ('6', 'ZC0006', '椅子', '2', '2', '2', '1', '2023-03-22 16:37:35', '2', '');
INSERT INTO `assets_information_maintenance` VALUES ('7', 'ZC0007', '桌子', '1', '3', '3', '1', '2023-03-22 16:37:35', '1', '');
INSERT INTO `assets_information_maintenance` VALUES ('8', 'ZC0008', '保温杯', '1', '2', '3', '3', '2023-03-22 16:37:35', '3', '');
INSERT INTO `assets_information_maintenance` VALUES ('9', 'ZC0009', '导弹发射井', '3', '1', '2', '2', '2023-03-22 16:37:35', '2', '');
INSERT INTO `assets_information_maintenance` VALUES ('10', 'ZC0010', '书架', '2', '2', '5', '2', '2023-03-22 16:37:35', '3', '');
INSERT INTO `assets_information_maintenance` VALUES ('11', 'ZC0011', '篮子', '4', '1', '4', '1', '2023-03-22 16:37:35', '3', '');
INSERT INTO `assets_information_maintenance` VALUES ('12', 'ZC0012', '哑铃', '3', '3', '4', '3', '2023-03-22 16:37:35', '5', '');
INSERT INTO `assets_information_maintenance` VALUES ('13', 'ZC0013', '飞机模型', '2', '4', '3', '1', '2023-03-22 16:37:35', '5', '');
INSERT INTO `assets_information_maintenance` VALUES ('14', 'ZC0014', '写真', '2', '1', '1', '1', '2023-03-22 16:37:35', '4', '');
INSERT INTO `assets_information_maintenance` VALUES ('15', 'ZC0015', '橡胶圈', '1', '2', '2', '3', '2023-03-22 16:37:35', '2', '');
INSERT INTO `assets_information_maintenance` VALUES ('16', 'ZC0016', '扎带', '3', '1', '3', '2', '2023-03-22 16:37:35', '5', '');
INSERT INTO `assets_information_maintenance` VALUES ('17', 'ZC0017', '服务器', '4', '4', '4', '1', '2023-03-22 16:37:35', '1', '');

-- ----------------------------
-- Table structure for assets_inventory
-- ----------------------------
DROP TABLE IF EXISTS `assets_inventory`;
CREATE TABLE `assets_inventory` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盘点单编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盘点名称',
  `description` varchar(64) NOT NULL COMMENT '盘点说明',
  `state` enum('not_started','in_progress','finished') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'not_started' COMMENT '盘点单状态',
  `start_date` date DEFAULT NULL COMMENT '开始日期',
  `end_date` date DEFAULT NULL COMMENT '结束日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COMMENT='资产盘点';

-- ----------------------------
-- Records of assets_inventory
-- ----------------------------
INSERT INTO `assets_inventory` VALUES ('11', 'PD20230511123859', '多挖点', '的撒大', 'finished', '2023-05-13', '2023-05-13', '2023-05-11 12:38:59');
INSERT INTO `assets_inventory` VALUES ('12', 'PD20230512212910', '盘点盘点', '无聊', 'in_progress', null, null, '2023-05-12 21:29:10');
INSERT INTO `assets_inventory` VALUES ('13', 'PD20230516215854', '见到啊', '的撒大啊', 'finished', '2023-05-16', '2023-05-16', '2023-05-16 21:58:54');

-- ----------------------------
-- Table structure for assets_inventory_sheet
-- ----------------------------
DROP TABLE IF EXISTS `assets_inventory_sheet`;
CREATE TABLE `assets_inventory_sheet` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `assets_inventory_id` int NOT NULL COMMENT '盘点单id',
  `asset_id` int NOT NULL COMMENT '资产id',
  `result` enum('正常','异常') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盘点结果',
  `is_inventoried` tinyint DEFAULT '0' COMMENT '是否已盘点',
  `remark` varchar(64) DEFAULT NULL COMMENT '盘点备注',
  PRIMARY KEY (`id`),
  KEY `assets_inventory_sheet_ibfk_1` (`assets_inventory_id`),
  KEY `assets_inventory_sheet_ibfk_2` (`asset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb3 COMMENT='资产盘点单';

-- ----------------------------
-- Records of assets_inventory_sheet
-- ----------------------------
INSERT INTO `assets_inventory_sheet` VALUES ('15', '11', '1', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('16', '11', '2', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('17', '11', '3', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('18', '11', '4', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('19', '11', '5', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('20', '12', '4', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('21', '12', '5', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('22', '12', '6', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('23', '12', '7', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('24', '12', '8', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('25', '12', '9', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('26', '12', '10', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('27', '12', '11', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('28', '12', '12', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('29', '12', '13', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('30', '12', '14', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('31', '12', '15', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('32', '12', '16', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('33', '12', '17', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('34', '13', '1', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('35', '13', '2', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('36', '13', '3', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('37', '13', '4', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('38', '13', '5', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('39', '13', '6', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('40', '13', '7', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('41', '13', '8', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('42', '13', '9', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('43', '13', '10', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('44', '13', '11', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('45', '13', '12', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('46', '13', '13', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('47', '13', '14', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('48', '13', '15', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('49', '13', '16', '正常', '1', null);
INSERT INTO `assets_inventory_sheet` VALUES ('50', '13', '17', '异常', '1', '撒旦撒旦撒');

-- ----------------------------
-- Table structure for assets_maintain
-- ----------------------------
DROP TABLE IF EXISTS `assets_maintain`;
CREATE TABLE `assets_maintain` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修单号',
  `asset_id` int NOT NULL COMMENT '资产id',
  `report_maintain_date` date NOT NULL COMMENT '报修日期',
  `report_maintain_personnel_id` int NOT NULL COMMENT '报修人id',
  `report_maintain_reason` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报修原因',
  `maintain_unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修单位',
  `repair_date` date NOT NULL COMMENT '修复日期',
  `maintain_charge` decimal(10,0) NOT NULL COMMENT '维修费用',
  `fault_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '故障说明',
  PRIMARY KEY (`id`),
  KEY `assets_maintain_ibfk_2` (`report_maintain_personnel_id`),
  KEY `assets_maintain_ibfk_1` (`asset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3 COMMENT='资产维护';

-- ----------------------------
-- Records of assets_maintain
-- ----------------------------
INSERT INTO `assets_maintain` VALUES ('1', 'WX202303210000001', '1', '2023-03-21', '1', '爆炸了', '蓝翔', '2023-03-22', '19000', '四分五裂');

-- ----------------------------
-- Table structure for assets_purchase_requisition
-- ----------------------------
DROP TABLE IF EXISTS `assets_purchase_requisition`;
CREATE TABLE `assets_purchase_requisition` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申购单号',
  `asset_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申购资产名',
  `asset_class_id` int NOT NULL COMMENT '资产类别id',
  `proposer_id` int NOT NULL COMMENT '申请人id',
  `number` int NOT NULL DEFAULT '1' COMMENT '申购数量',
  `anticipated_price` decimal(10,0) DEFAULT NULL COMMENT '预计价格',
  `equipment_use_id` int NOT NULL COMMENT '设备用途id',
  `asset_photograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资产照片',
  `suggest` varchar(255) DEFAULT NULL COMMENT '申购建议',
  `purchase_requisition_date` date NOT NULL COMMENT '申购日期',
  `reason` varchar(255) NOT NULL COMMENT '申购理由',
  `application_status` enum('uncommitted','committed','approved','not_approved') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'uncommitted' COMMENT '申请状态',
  `not_approving_reasons` varchar(64) DEFAULT NULL COMMENT '审核不通过原因',
  PRIMARY KEY (`id`),
  KEY `asset_class_id` (`asset_class_id`),
  KEY `proposer_id` (`proposer_id`),
  KEY `equipment_use_id` (`equipment_use_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='资产申购';

-- ----------------------------
-- Records of assets_purchase_requisition
-- ----------------------------
INSERT INTO `assets_purchase_requisition` VALUES ('1', 'SG20230321000001', '华为手机', '3', '1', '10', '60', '1', '\\src\\assets\\user\\images\\OIP.jpg', '', '2023-03-21', '焯！', 'not_approved', '撒旦');
INSERT INTO `assets_purchase_requisition` VALUES ('2', 'SG20230321000002', '苹果电脑', '2', '2', '1', '100000', '2', '\\src\\assets\\user\\images\\86559025_p0_master1200.jpg', '', '2022-11-30', '无', 'not_approved', '太贵了');
INSERT INTO `assets_purchase_requisition` VALUES ('3', 'SG20230514145020', 'sadasda', '2', '3', '1', null, '2', '\\src\\assets\\user\\images\\thumb-1920-908560.jpg', null, '2023-05-07', 'esafad', 'not_approved', '傻逼');

-- ----------------------------
-- Table structure for assets_scrap
-- ----------------------------
DROP TABLE IF EXISTS `assets_scrap`;
CREATE TABLE `assets_scrap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `asset_id` int NOT NULL COMMENT '资产id',
  `code` varchar(64) DEFAULT NULL,
  `proposer_id` int NOT NULL COMMENT '申请人id',
  `scrap_mode_id` int NOT NULL COMMENT '报废方式id',
  `scrap_date` date NOT NULL COMMENT '报废日期',
  `scrap_reason` varchar(255) NOT NULL COMMENT '报废原因',
  `application_status` enum('uncommitted','committed','approved','not_approved') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'uncommitted' COMMENT '申请状态',
  `not_approving_reasons` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审核不通过原因',
  PRIMARY KEY (`id`),
  KEY `proposer_id` (`proposer_id`) USING BTREE,
  KEY `scrap_method_id` (`scrap_mode_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='资产报废';

-- ----------------------------
-- Records of assets_scrap
-- ----------------------------
INSERT INTO `assets_scrap` VALUES ('1', '5', 'BF20230101123123', '1', '1', '2023-05-10', '不知道', 'uncommitted', '');
INSERT INTO `assets_scrap` VALUES ('2', '3', 'BF20230121123123', '9', '3', '2023-05-05', '爆炸了', 'approved', '');

-- ----------------------------
-- Table structure for assets_storage
-- ----------------------------
DROP TABLE IF EXISTS `assets_storage`;
CREATE TABLE `assets_storage` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资产名称',
  `asset_class_id` int NOT NULL COMMENT '资产类别id',
  `supplier_id` int NOT NULL COMMENT '供应商id',
  `brand_id` int NOT NULL COMMENT '品牌id',
  `gaining_method_id` int NOT NULL COMMENT '取得方式id',
  `storage_date` datetime NOT NULL COMMENT '入库时间',
  `storage_place_id` int unsigned NOT NULL COMMENT '存放地点id',
  `state` enum('正常','报废') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '正常',
  `asset_photograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资产照片',
  PRIMARY KEY (`id`),
  KEY `asset_class_id` (`asset_class_id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `brand_id` (`brand_id`),
  KEY `assets_storage_ibfk_4` (`gaining_method_id`),
  KEY `assets_storage_ibfk_5` (`storage_place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COMMENT='资产入库';

-- ----------------------------
-- Records of assets_storage
-- ----------------------------
INSERT INTO `assets_storage` VALUES ('1', 'ZC0001', '投影仪', '1', '4', '5', '1', '2020-01-22 00:00:00', '1', '正常', '');
INSERT INTO `assets_storage` VALUES ('2', 'ZC0002', '签字笔', '2', '3', '2', '2', '2014-07-01 00:00:00', '3', '正常', '');
INSERT INTO `assets_storage` VALUES ('3', 'ZC0003', '笔记本', '2', '4', '1', '3', '2011-09-05 00:00:00', '5', '报废', '');
INSERT INTO `assets_storage` VALUES ('4', 'ZC0004', '华为手机', '3', '4', '4', '2', '2018-03-11 00:00:00', '3', '正常', '');
INSERT INTO `assets_storage` VALUES ('5', 'ZC0005', '手机S820', '3', '3', '1', '3', '2015-06-30 00:00:00', '4', '正常', '');
INSERT INTO `assets_storage` VALUES ('6', 'ZC0006', '椅子', '2', '2', '2', '1', '2023-02-24 00:00:00', '2', '正常', '');
INSERT INTO `assets_storage` VALUES ('7', 'ZC0007', '桌子', '1', '3', '3', '1', '2011-02-10 00:00:00', '1', '正常', '');
INSERT INTO `assets_storage` VALUES ('8', 'ZC0008', '保温杯', '1', '2', '3', '3', '2016-12-25 00:00:00', '3', '正常', '');
INSERT INTO `assets_storage` VALUES ('9', 'ZC0009', '导弹发射井', '3', '1', '2', '2', '2022-08-14 00:00:00', '2', '正常', '');
INSERT INTO `assets_storage` VALUES ('10', 'ZC0010', '书架', '2', '2', '5', '2', '2014-08-19 00:00:00', '5', '正常', '');
INSERT INTO `assets_storage` VALUES ('11', 'ZC0011', '篮子', '4', '1', '4', '1', '2021-05-27 00:00:00', '3', '正常', '');
INSERT INTO `assets_storage` VALUES ('12', 'ZC0012', '哑铃', '3', '3', '4', '3', '2016-11-08 00:00:00', '5', '正常', '');
INSERT INTO `assets_storage` VALUES ('13', 'ZC0013', '飞机模型', '2', '4', '3', '1', '2013-09-22 00:00:00', '5', '正常', '');
INSERT INTO `assets_storage` VALUES ('14', 'ZC0014', '写真', '2', '1', '1', '1', '2019-01-16 00:00:00', '4', '正常', '');
INSERT INTO `assets_storage` VALUES ('15', 'ZC0015', '橡胶圈', '1', '2', '2', '3', '2011-04-19 00:00:00', '2', '正常', '');
INSERT INTO `assets_storage` VALUES ('16', 'ZC0016', '扎带', '3', '1', '3', '2', '2017-10-03 00:00:00', '5', '正常', '');
INSERT INTO `assets_storage` VALUES ('17', 'ZC0017', '服务器', '4', '4', '4', '1', '2023-03-20 00:00:00', '1', '正常', '');

-- ----------------------------
-- Table structure for assets_transfer
-- ----------------------------
DROP TABLE IF EXISTS `assets_transfer`;
CREATE TABLE `assets_transfer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) DEFAULT NULL,
  `assets_borrow_id` int unsigned NOT NULL COMMENT '资产借用id',
  `new_borrower_id` int NOT NULL COMMENT '新借用人id',
  `transfer_date` datetime NOT NULL COMMENT '转移日期',
  `transfer_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转移原因',
  PRIMARY KEY (`id`),
  KEY `assets_transfer_ibfk_1` (`assets_borrow_id`),
  KEY `assets_transfer_ibfk_2` (`new_borrower_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='资产转移';

-- ----------------------------
-- Records of assets_transfer
-- ----------------------------
INSERT INTO `assets_transfer` VALUES ('2', 'ZY20190103174726', '1', '2', '2023-03-21 17:40:24', '抢');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌说明',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='品牌';

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1', '晨光品牌', '晨光', '1', '2023-03-21 13:11:37');
INSERT INTO `brand` VALUES ('2', '戴尔品牌', '戴尔', '1', '2023-03-21 13:12:12');
INSERT INTO `brand` VALUES ('3', '格力品牌', '格力', '1', '2023-03-21 13:12:50');
INSERT INTO `brand` VALUES ('4', '光明品牌', '光明', '1', '2023-03-21 13:13:10');
INSERT INTO `brand` VALUES ('5', '联想品牌', '联想', '1', '2023-03-21 13:13:27');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门简介',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COMMENT='部门';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'BMBM000001', '党政部', null, '2023-03-21 13:33:58');
INSERT INTO `department` VALUES ('2', 'BMBM000002', '后勤部', '', '2023-03-21 13:34:19');
INSERT INTO `department` VALUES ('3', 'BMBM000003', '设备科', null, '2023-03-21 13:34:35');
INSERT INTO `department` VALUES ('4', 'BMBM000004', '法律部', '提供法律支持', '2023-03-26 22:46:19');
INSERT INTO `department` VALUES ('5', 'BMBM000005', '睡觉部', '', '2023-04-02 17:54:28');
INSERT INTO `department` VALUES ('6', 'BMBM000006', '健身部', '', '2023-04-02 19:24:23');
INSERT INTO `department` VALUES ('7', 'BMBM000007', '熬夜部', '修仙', '2023-04-02 19:28:00');
INSERT INTO `department` VALUES ('8', 'BMBM000008', '动画部', null, '2023-04-06 23:34:24');
INSERT INTO `department` VALUES ('9', 'BMBM000010', '科研部', null, '2023-04-09 19:02:56');
INSERT INTO `department` VALUES ('11', 'BMBM000011', '啊的撒', '撒旦', '2023-05-13 14:59:03');

-- ----------------------------
-- Table structure for equipment_use
-- ----------------------------
DROP TABLE IF EXISTS `equipment_use`;
CREATE TABLE `equipment_use` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备用途编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备用途名称',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='设备用途';

-- ----------------------------
-- Records of equipment_use
-- ----------------------------
INSERT INTO `equipment_use` VALUES ('1', 'SBYT0001', '生产', '1', null, '2023-03-21 13:26:56');
INSERT INTO `equipment_use` VALUES ('2', 'SBYT0002', '办公', '1', null, '2023-03-21 13:27:12');
INSERT INTO `equipment_use` VALUES ('3', 'SBYT0003', '基建', '0', '', '2023-03-21 13:27:27');
INSERT INTO `equipment_use` VALUES ('4', 'SBYT0004', '其他', '1', '', '2023-03-21 13:27:40');

-- ----------------------------
-- Table structure for gaining_method
-- ----------------------------
DROP TABLE IF EXISTS `gaining_method`;
CREATE TABLE `gaining_method` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获取方式编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获取方式名称',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `id` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='取得方式';

-- ----------------------------
-- Records of gaining_method
-- ----------------------------
INSERT INTO `gaining_method` VALUES ('1', 'QDFS001', '购置取得', '0', '2023-03-21 13:14:04');
INSERT INTO `gaining_method` VALUES ('2', 'QDFS002', '捐赠取得', '1', '2023-03-21 13:14:23');
INSERT INTO `gaining_method` VALUES ('3', 'QDFS003', '盗窃取得', '1', '2023-03-21 13:14:39');
INSERT INTO `gaining_method` VALUES ('4', 'QDFS004', '其他取得', '1', '2023-03-21 13:15:04');

-- ----------------------------
-- Table structure for personal_info
-- ----------------------------
DROP TABLE IF EXISTS `personal_info`;
CREATE TABLE `personal_info` (
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `login_last_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后登录时间',
  `login_times` int NOT NULL DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='个人信息';

-- ----------------------------
-- Records of personal_info
-- ----------------------------
INSERT INTO `personal_info` VALUES ('leader', '114514', '2023-05-24 19:12:56', '108');
INSERT INTO `personal_info` VALUES ('manager', '114514', '2023-05-24 19:13:00', '68');
INSERT INTO `personal_info` VALUES ('sys_admin', '114514', '2023-05-24 19:13:04', '543');

-- ----------------------------
-- Table structure for personnel
-- ----------------------------
DROP TABLE IF EXISTS `personnel`;
CREATE TABLE `personnel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员名称',
  `department_id` int NOT NULL COMMENT '部门id',
  `category` enum('在编','离任','合同','临时') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员类别',
  `gender` enum('男','女','武装直升机','顺性别女','双性人','跨性别男性','跨性别女性','非二元性别') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `identification_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个人照片',
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3 COMMENT='人员';

-- ----------------------------
-- Records of personnel
-- ----------------------------
INSERT INTO `personnel` VALUES ('1', '1001', '李文涛', '1', '在编', '男', '2000-02-17', '');
INSERT INTO `personnel` VALUES ('2', '1002', '庄严', '5', '合同', '女', '2003-06-10', '');
INSERT INTO `personnel` VALUES ('3', '1003', '王铭章', '3', '离任', '武装直升机', '1999-10-05', '');
INSERT INTO `personnel` VALUES ('4', '1004', '李云飞', '3', '离任', '顺性别女', '2015-03-31', '');
INSERT INTO `personnel` VALUES ('5', '1005', '谢佳琳', '5', '离任', '跨性别女性', '1968-06-25', '');
INSERT INTO `personnel` VALUES ('6', '1006', '刘鸿博', '5', '临时', '男', '1987-02-06', '');
INSERT INTO `personnel` VALUES ('7', '1007', '曾健民', '4', '离任', '女', '1967-07-12', '');
INSERT INTO `personnel` VALUES ('8', '1008', '杨志新', '5', '离任', '女', '1965-10-28', '');
INSERT INTO `personnel` VALUES ('9', '1009', '黄天佑', '2', '在编', '双性人', '1999-05-06', '');
INSERT INTO `personnel` VALUES ('10', '1010', '刘华洋', '5', '离任', '女', '2013-09-12', '');
INSERT INTO `personnel` VALUES ('11', '1011', '蒋婉儿', '1', '离任', '非二元性别', '1972-07-23', '');
INSERT INTO `personnel` VALUES ('12', '1012', '江欣怡', '1', '离任', '女', '1966-02-22', '');
INSERT INTO `personnel` VALUES ('13', '1013', '邹敬一', '4', '离任', '女', '1959-07-20', '');
INSERT INTO `personnel` VALUES ('14', '1014', '向菲菲', '4', '离任', '女', '2007-10-11', '');
INSERT INTO `personnel` VALUES ('15', '1015', '陈涛涛', '4', '离任', '跨性别女性', '2017-01-07', '');
INSERT INTO `personnel` VALUES ('16', '1016', '郭晨光', '4', '在编', '女', '1980-07-08', '');
INSERT INTO `personnel` VALUES ('17', '1017', '鲁志刚', '4', '在编', '女', '1964-08-15', '');
INSERT INTO `personnel` VALUES ('18', '1018', '陈嘉仪', '4', '合同', '女', '1964-01-17', '');
INSERT INTO `personnel` VALUES ('19', '1019', '王昊天', '4', '临时', '非二元性别', '1983-07-02', '');
INSERT INTO `personnel` VALUES ('20', '1020', '庞马克', '1', '临时', '女', '1982-11-20', '');
INSERT INTO `personnel` VALUES ('21', '1021', '王子凝', '2', '在编', '女', '1968-07-30', '');
INSERT INTO `personnel` VALUES ('22', '1022', '朱丹阳', '1', '在编', '女', '1966-09-19', '');
INSERT INTO `personnel` VALUES ('23', '1023', '林爱军', '2', '在编', '男', '1979-12-26', '');
INSERT INTO `personnel` VALUES ('24', '1024', '孙忠诚', '2', '合同', '女', '1967-07-14', '');
INSERT INTO `personnel` VALUES ('25', '1025', '赵瑶瑶', '3', '临时', '女', '1974-09-29', '');
INSERT INTO `personnel` VALUES ('26', '1026', '骆娟娟', '3', '在编', '女', '1974-08-27', '');
INSERT INTO `personnel` VALUES ('27', '1027', '贾兰兰', '3', '在编', '女', '2014-03-18', '');
INSERT INTO `personnel` VALUES ('28', '1028', '邱诗琪', '3', '合同', '女', '1962-05-08', '');
INSERT INTO `personnel` VALUES ('29', '1029', '张钰轩', '2', '临时', '跨性别女性', '1992-02-13', '');
INSERT INTO `personnel` VALUES ('30', '1030', '叶美丽', '2', '在编', '女', '2015-04-09', '');
INSERT INTO `personnel` VALUES ('31', '1031', '沈婧怡', '5', '在编', '女', '1959-12-22', '');
INSERT INTO `personnel` VALUES ('32', '1032', '钟爱忠', '2', '临时', '女', '1957-06-18', '');
INSERT INTO `personnel` VALUES ('33', '1033', '周鸿毅', '2', '在编', '男', '1967-11-06', '');
INSERT INTO `personnel` VALUES ('34', '1034', '陆树阳', '1', '合同', '女', '1985-03-22', '');
INSERT INTO `personnel` VALUES ('35', '1035', '张梓萱', '2', '在编', '男', '1993-01-10', '');
INSERT INTO `personnel` VALUES ('36', '1036', '许天王', '2', '在编', '女', '1995-02-26', '');
INSERT INTO `personnel` VALUES ('37', '1037', '范子涵', '1', '临时', '男', '1975-03-13', '');
INSERT INTO `personnel` VALUES ('38', '1038', '葛忠良', '5', '在编', '女', '1981-12-22', '');
INSERT INTO `personnel` VALUES ('39', '1039', '谭锦锦', '1', '在编', '跨性别男性', '1955-09-16', '');
INSERT INTO `personnel` VALUES ('40', '1040', '岳文彬', '1', '在编', '非二元性别', '1950-10-02', '');
INSERT INTO `personnel` VALUES ('41', '1041', '李欣婷', '4', '合同', '男', '1975-02-01', '');
INSERT INTO `personnel` VALUES ('42', '1042', '郑禹奇', '5', '临时', '跨性别女性', '1961-08-15', '');
INSERT INTO `personnel` VALUES ('43', '1043', '刘逸飞', '4', '在编', '武装直升机', '1986-12-28', '');
INSERT INTO `personnel` VALUES ('44', '1044', '王大喜', '3', '临时', '武装直升机', '1999-10-05', '');

-- ----------------------------
-- Table structure for scrap_mode
-- ----------------------------
DROP TABLE IF EXISTS `scrap_mode`;
CREATE TABLE `scrap_mode` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报废方式编号',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报废方式名称',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' DEFAULT '1' COMMENT '是否已启用',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='报废方式';

-- ----------------------------
-- Records of scrap_mode
-- ----------------------------
INSERT INTO `scrap_mode` VALUES ('1', 'BFFS0001', '变卖', '1', null, '2023-03-21 13:28:35');
INSERT INTO `scrap_mode` VALUES ('2', 'BFFS0002', '丢失', '1', '设备丢失', '2023-03-21 13:28:59');
INSERT INTO `scrap_mode` VALUES ('3', 'BFFS0003', '捐赠', '1', null, '2023-03-21 13:29:22');
INSERT INTO `scrap_mode` VALUES ('4', 'BFFS0004', '其他', '1', '其他', '2023-03-21 13:29:35');
INSERT INTO `scrap_mode` VALUES ('5', '11110000', 'asd', '1', 'asd', '2023-05-24 17:29:19');

-- ----------------------------
-- Table structure for storage_place
-- ----------------------------
DROP TABLE IF EXISTS `storage_place`;
CREATE TABLE `storage_place` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存放地点名称',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='存放地点';

-- ----------------------------
-- Records of storage_place
-- ----------------------------
INSERT INTO `storage_place` VALUES ('1', '1号楼1号仓库', '1', '2023-03-21 13:25:10', null);
INSERT INTO `storage_place` VALUES ('2', '2号仓库', '1', '2023-03-21 13:25:31', null);
INSERT INTO `storage_place` VALUES ('3', '厂库', '1', '2023-03-21 13:25:42', null);
INSERT INTO `storage_place` VALUES ('4', '行政办公室', '0', '2023-03-21 13:26:00', '存放移动设备');
INSERT INTO `storage_place` VALUES ('5', '厕所', '0', '2023-03-21 16:33:51', '存放食物');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type` enum('生产商','零售商','代理商','其他') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已启用',
  `contact_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `contact_tel` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '移动电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='供应商';

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('1', '景宁有限公司', '生产商', '1', '王女士', '15254546563', null, null, '2023-03-21 13:21:53');
INSERT INTO `supplier` VALUES ('2', '深圳华克有限公司', '其他', '1', '丁真', '15232323214', null, null, '2023-03-21 13:23:04');
INSERT INTO `supplier` VALUES ('3', '维信科技发展有限公司', '零售商', '0', '张三', '13884698899', null, null, '2023-03-21 13:23:54');
INSERT INTO `supplier` VALUES ('4', '浙江有限公司', '代理商', '1', '于女士', '15631313141', null, null, '2023-03-21 13:24:31');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（账号）',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `identity` enum('system_administrator','assets_manager','assets_leader') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户登录信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('leader', '123456', 'assets_leader');
INSERT INTO `user_info` VALUES ('manager', '123456', 'assets_manager');
INSERT INTO `user_info` VALUES ('sys_admin', '123456', 'system_administrator');
DROP TRIGGER IF EXISTS `start_inventory`;
DELIMITER ;;
CREATE TRIGGER `start_inventory` BEFORE UPDATE ON `assets_inventory` FOR EACH ROW BEGIN
    IF NEW.state='in_progress' AND OLD.state='not_started' THEN
        SET NEW.start_date=NOW();
    END IF;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_inventory`;
DELIMITER ;;
CREATE TRIGGER `delete_inventory` BEFORE DELETE ON `assets_inventory` FOR EACH ROW BEGIN
delete from assets_inventory_sheet where assets_inventory_id=old.id;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `inventoried`;
DELIMITER ;;
CREATE TRIGGER `inventoried` BEFORE UPDATE ON `assets_inventory_sheet` FOR EACH ROW BEGIN
    update assets_inventory set state='in_progress' where id=new.assets_inventory_id;
    SET new.is_inventoried=IF(new.result is NULL,0,1);
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `scrap_approved`;
DELIMITER ;;
CREATE TRIGGER `scrap_approved` AFTER UPDATE ON `assets_scrap` FOR EACH ROW BEGIN
    UPDATE assets_storage SET state='报废' 
        WHERE id=new.asset_id AND new.application_status='approved';
    UPDATE assets_storage SET state='正常' 
        WHERE id=new.asset_id AND NOT (new.application_status='approved');
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `transfer`;
DELIMITER ;;
CREATE TRIGGER `transfer` AFTER INSERT ON `assets_transfer` FOR EACH ROW -- 新增资产转移后，系统自动将原使用人的借用单状态置为“已归还”，同时各生成一条与新使用人相关的借用单及转移单。
BEGIN
    declare the_asset_id varchar(64);
    select asset_id 
        into the_asset_id 
        from assets_borrow 
        where id=new.assets_borrow_id;
    update assets_borrow set return_state='returned' where id=new.assets_borrow_id;
    insert into assets_borrow(
        asset_id,
        borrower_id,
        borrow_time,
        borrow_reason,
        return_date,
        return_state)
        values(
            the_asset_id,
            new.new_borrower_id,
            now(),
            new.transfer_reason,
            null,
            'not_returned'
    );
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `new_user_info`;
DELIMITER ;;
CREATE TRIGGER `new_user_info` AFTER INSERT ON `user_info` FOR EACH ROW -- 新增用户信息
BEGIN
    insert into personal_info(account, tel, login_last_time, login_times) value(new.username, NULL, NOW(), 0);
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_user_info`;
DELIMITER ;;
CREATE TRIGGER `del_user_info` AFTER DELETE ON `user_info` FOR EACH ROW -- 新增用户信息
BEGIN
    delete from personal_info where old.username=account;
END
;;
DELIMITER ;
