CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS usuario (
    cpf cpf PRIMARY KEY,
    nome varchar(100) NOT NULL,
    email varchar(30) NOT NULL,
    admin boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS credenciais (
    user_cpf cpf PRIMARY KEY,
    senha varchar(100) NOT NULL,
    FOREIGN KEY (user_cpf) REFERENCES usuario (cpf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS livro (
    titulo varchar(100) PRIMARY KEY,
    ano ano NOT NULL,
    paginas smallint NOT NULL,
    genero varchar(30),
    editora varchar(50),
    edicao varchar(30),
    quantidade smallint,
    CONSTRAINT quantidade_positiva CHECK (quantidade >= 0)
);

CREATE TABLE IF NOT EXISTS autor_livro (
    livro_titulo varchar(100),
    autor_nome varchar(100),
    PRIMARY KEY (livro_titulo, autor_nome),
    FOREIGN KEY (livro_titulo) REFERENCES livro (titulo) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS aluga (
    user_cpf CPF,
    livro_titulo varchar(100),
    data timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    data_entrega timestamp WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_cpf, livro_titulo),
    FOREIGN KEY (user_cpf) REFERENCES usuario (cpf) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (livro_titulo) REFERENCES livro (titulo) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO usuario (cpf, nome, email, admin)
VALUES ('00000000000', 'admin', 'admin@email.com', true)
ON CONFLICT DO NOTHING;

INSERT INTO credenciais (user_cpf, senha)
VALUES ('00000000000', crypt('123456', gen_salt('md5')))
ON CONFLICT DO NOTHING;






