CREATE TABLE users (
                       id SERIAL NOT NULL,
                       login VARCHAR(45) NOT NULL,
                       password VARCHAR(45) NOT NULL,
                       role VARCHAR(45) NOT NULL,
                       PRIMARY KEY (id)
);