
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
                      id INT NOT NULL AUTO_INCREMENT,
                      nombres VARCHAR(100) NOT NULL,
                      ape_pat VARCHAR(100),
                      ape_mat VARCHAR(100),
                      edad INT,
                      email VARCHAR(50),
                      PRIMARY KEY (id));