DROP TABLE IF EXISTS produtos;

CREATE TABLE produtos
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    nome          VARCHAR(255) NOT NULL,
    codigoBarra   VARCHAR(255) NOT NULL
);

INSERT INTO produtos (nome, codigoBarra) VALUES ('Notebook', 'a21sd654q98w4d');
