CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `nickname` varchar(128) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `signature` varchar(256) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8