
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL,
  `permName` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `permTag` varchar(50) DEFAULT NULL COMMENT '权限标识符',
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_permission` VALUES ('1', '查询订单', 'showOrder', '/showOrder');
INSERT INTO `sys_permission` VALUES ('2', '添加订单', 'addOrder', '/addOrder');
INSERT INTO `sys_permission` VALUES ('3', '修改订单', 'updateOrder', '/updateOrder');
INSERT INTO `sys_permission` VALUES ('4', '删除订单', 'deleteOrder', '/deleteOrder');


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL,
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `roleDesc` varchar(50) DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'add_user', '添加管理员');


DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) DEFAULT NULL,
  `perm_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`perm_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');


DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `createDate` date DEFAULT NULL COMMENT '创建时间',
  `lastLoginTime` date DEFAULT NULL COMMENT '最后登录时间',
  `enabled` int(5) DEFAULT NULL COMMENT '是否可用',
  `accountNonExpired` int(5) DEFAULT NULL COMMENT '是否过期',
  `accountNonLocked` int(5) DEFAULT NULL COMMENT '是否锁定',
  `credentialsNonExpired` int(5) DEFAULT NULL COMMENT '证书是否过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_user` VALUES ('1', 'admin', '张三', '15a013bcac0c50049356b322e955035e', '2018-11-13', '2018-11-13', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'userAdd', '小余', '15a013bcac0c50049356b322e955035e', '2018-11-13', '2018-11-13', '1', '1', '1', '1');


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
