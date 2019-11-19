CREATE TABLE `meite_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `seckill_id` int(10) unsigned NOT NULL,
  `user_phone` varchar(15) DEFAULT NULL,
  `status` int(10) unsigned DEFAULT '0',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `meite_seckil` (
  `seckill_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `inventory` int(10) unsigned DEFAULT '0',
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `version` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`seckill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8;