ALTER TABLE `project_cds`.`users`
    ADD COLUMN `email` VARCHAR(45) NOT NULL AFTER `role`,
CHANGE COLUMN `password` `password` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `role` `role` TINYINT NOT NULL ;
