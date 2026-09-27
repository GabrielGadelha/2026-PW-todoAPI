-- 1. Tabela de Usuários
CREATE TABLE tb_usuarios (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             email VARCHAR(255) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             role VARCHAR(50) NOT NULL,
                             CONSTRAINT uk_tb_usuarios_email UNIQUE (email)
);

-- 2. Tabela de Tarefas
CREATE TABLE tb_todos (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          id_user BIGINT NOT NULL,
                          item VARCHAR(255) NOT NULL,
                          prazo DATE,
                          estado VARCHAR(50),
                          conclusao DATE,
                          CONSTRAINT fk_todos_usuario FOREIGN KEY (id_user)
                              REFERENCES tb_usuarios (id)
                              ON DELETE CASCADE
);

-- 3. Índice para performance em buscas por usuário
CREATE INDEX idx_tb_todos_id_user ON tb_todos (id_user);