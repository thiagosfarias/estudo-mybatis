DROP TABLE IF EXISTS produtos;

CREATE TABLE produtos
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    nome          VARCHAR(255) NOT NULL,
    codigoBarra   VARCHAR(255) NOT NULL
);

INSERT INTO produtos (nome, codigoBarra) VALUES ('Notebook', '102215500000');
INSERT INTO produtos (nome, codigoBarra) VALUES ('iPhone', '18468000000215');
INSERT INTO produtos (nome, codigoBarra) VALUES ('tablet', '78945620000225');
