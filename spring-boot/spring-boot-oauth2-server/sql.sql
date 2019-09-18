

CREATE TABLE `m_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `app_id` varchar(255) DEFAULT NULL COMMENT '应用id',
  `app_secret` varchar(255) DEFAULT NULL COMMENT '应用密钥',
  `is_flag` varchar(255) DEFAULT NULL COMMENT '是否可用',
  `access_token` varchar(255) DEFAULT NULL COMMENT '记录最新的token',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;