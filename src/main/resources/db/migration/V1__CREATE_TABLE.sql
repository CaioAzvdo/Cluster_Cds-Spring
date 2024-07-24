CREATE TABLE cds (
                                         `id` INT NOT NULL auto_increment,
                                         `name` VARCHAR(45) NOT NULL,
                                         `genre` VARCHAR(45) NOT NULL,
                                         `author` VARCHAR(45) NOT NULL,
                                         `register_date` DATETIME NOT NULL,
                                         PRIMARY KEY (`id`));
