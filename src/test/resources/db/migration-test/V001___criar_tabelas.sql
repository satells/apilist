CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL
);

CREATE TABLE resource (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    api VARCHAR(255) NOT NULL,
    description TEXT,
    auth VARCHAR(255),
    https BOOLEAN,
    cors VARCHAR(255),
    link VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);