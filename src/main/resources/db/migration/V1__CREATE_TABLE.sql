CREATE TABLE cds (
                     id SERIAL NOT NULL,
                     name VARCHAR(45) NOT NULL,
                     genre VARCHAR(45) NOT NULL,
                     author VARCHAR(45) NOT NULL,
                     register_date TIMESTAMP NOT NULL,
                     user_id INT NOT NULL,
                     PRIMARY KEY (id)
);