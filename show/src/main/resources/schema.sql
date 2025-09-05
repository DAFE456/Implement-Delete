DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP SEQUENCE IF EXISTS authors_id_sequence;

CREATE SEQUENCE authors_id_sequence
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 99999999
    START WITH 1
    CACHE 1;

CREATE TABLE authors (
                         id BIGINT DEFAULT nextval('authors_id_sequence') NOT NULL,
                         name TEXT,
                         age INTEGER,
                         CONSTRAINT author_p PRIMARY KEY (id)
);

CREATE TABLE books (
                       isbn TEXT NOT NULL,
                       title TEXT,
                       author_id BIGINT NOT NULL,
                       CONSTRAINT books_p PRIMARY KEY (isbn),
                       CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id)
);
