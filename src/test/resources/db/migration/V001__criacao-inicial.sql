CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL
);

CREATE TABLE resource (
    id INT AUTO_INCREMENT PRIMARY KEY,
    api VARCHAR(255) NOT NULL,
    description CLOB NOT NULL,
    auth VARCHAR(255),
    https BOOLEAN,
    cors VARCHAR(255),
    link VARCHAR(255) NOT NULL,
    category_id INT REFERENCES category(id) NOT NULL
);

insert into category (id,name,slug) values (54,'Mauro','maurooooooooo');