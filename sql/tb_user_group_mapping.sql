CREATE TABLE `tb_user_group_mapping` (
	`user_id` INT(11) NOT NULL,
	`group_id` INT(11) NOT NULL,
	PRIMARY KEY (`user_id`, `group_id`),
	INDEX `FK_GROUP` (`group_id`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
	CONSTRAINT `FK_GROUP` FOREIGN KEY (`group_id`) REFERENCES `tb_user_group` (`id`)
)
COMMENT='user、user_group中间表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
