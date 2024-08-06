ALTER TABLE `project_cds`.`users`
    CHANGE COLUMN `email` `email` VARCHAR(255) NULL DEFAULT NULL AFTER `login`;
