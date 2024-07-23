CREATE TABLE users (
                                     `id` INT NOT NULL auto_increment,
                                     `login` VARCHAR(45) NOT NULL,
                                     `password` VARCHAR(45) NOT NULL,
                                     `role` VARCHAR(45) NOT NULL,
                                     PRIMARY KEY (`id`));

