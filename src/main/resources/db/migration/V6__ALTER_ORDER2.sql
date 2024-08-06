ALTER TABLE `project_cds`.`users`
    CHANGE COLUMN `password` `password` VARCHAR(255) NOT NULL AFTER `login`;
