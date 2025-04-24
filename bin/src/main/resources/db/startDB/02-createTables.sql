CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL
);

CREATE TABLE resource (
    id SERIAL PRIMARY KEY,
    api VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    auth VARCHAR(255),
    https BOOLEAN,
    cors VARCHAR(255),
    link VARCHAR(255) NOT NULL,
    category_id INTEGER REFERENCES category(id) NOT NULL
);