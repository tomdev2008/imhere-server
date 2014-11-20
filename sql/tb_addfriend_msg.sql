CREATE TABLE `tb_addfriend_msg` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`from_user_id` INT(11) NOT NULL,
	`from_user_nickname` VARCHAR(128) NOT NULL,
	`from_user_mobile` VARCHAR(32) NOT NULL,
	`to_user_id` INT(11) NULL,
	`to_user_mobile` VARCHAR(32) NOT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='添加好友信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
