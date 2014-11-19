CREATE TABLE `tb_friendship` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`friends` VARCHAR(2048) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_FRIEND` (`user_id`),
	CONSTRAINT `FK_FRIEND` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
)
COMMENT='朋友关系表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
