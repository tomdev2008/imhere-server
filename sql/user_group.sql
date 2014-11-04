CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) DEFAULT NULL,
  `labels` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8