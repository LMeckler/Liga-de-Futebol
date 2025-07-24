-- Cidade
INSERT INTO tb_Cidade (nome, estado) VALUES ('Ipatinga', 'MG'), ('Belo Horizonte', 'MG'), ('Rio de Janeiro', 'RJ'), ('São Paulo', 'SP');

-- Estadio
INSERT INTO tb_Estadio (nome, capacidade, id_Cidade) VALUES ('Ipatingão', 20000, 1), ('Mineirão', 62000, 2), ('Maracanã', 78833, 3), ('Morumbis', 67000, 4);

-- Pessoa
INSERT INTO tb_Pessoa (nome, cpf, data_Nascimento, id_Cidade) VALUES ('Bruno Henrique', '12345678901', '1990-12-30', 1), ('Gérson Magrão', '12345678902', '1985-06-13', 4), ('Gabriel Barbosa', '12345678903', '1996-08-30', 4), ('Filipe Luís', '12345678904', '1985-08-09', 3);

-- Time
INSERT INTO tb_Time (nome, id_Cidade, id_Estadio) VALUES ('Ipatinga FC', 1, 1), ('Cruzeiro', 2, 2), ('Flamengo', 3, 3), ('São Paulo FC', 4, 4);

-- Usuario
INSERT INTO tb_Usuario (login, senha, nivel_Acesso) VALUES ('ADM', '$2a$10$6dy99fRmhgrUh1LHraKmAO/28o6z0YBvVxLjt.JGCeFeiiRaLzqYq', 'ADMIN');

-- Contrato
INSERT INTO tb_Contrato (data_Inicio, data_Fim, tipo, funcao, situacao, observacao, id_Time, id_Pessoa) VALUES ('2024-01-01', '2025-12-31', 'definitivo', 'jogador', 'ativo', 'Contrato de demonstração jogador', 3, 1);
INSERT INTO tb_Contrato (data_Inicio, data_Fim, tipo, funcao, situacao, observacao, id_Time, id_Pessoa) VALUES ('2024-01-01', '2025-12-31', 'emprestimo', 'jogador', 'ativo', 'Contrato de demonstração empréstimo', 2, 3);
INSERT INTO tb_Contrato (data_Inicio, data_Fim, tipo, funcao, situacao, observacao, id_Time, id_Pessoa) VALUES ('2024-01-01', '2025-12-31', 'definitivo', 'treinador', 'ativo', 'Contrato de demonstração treinador', 3, 4);
INSERT INTO tb_Contrato (data_Inicio, data_Fim, tipo, funcao, situacao, observacao, id_Time, id_Pessoa) VALUES ('2024-01-01', '2024-12-31', 'definitivo', 'jogador', 'inativo', 'Contrato de demonstração inativo', 1, 2);

-- TimeRestricao
INSERT INTO tb_Time_Restricao (descricao, data_Inicio, data_Fim, id_Time) VALUES ('Restrição de exemplo para o time', '2024-06-01', '2024-07-01', 4);

-- JogadorRestricao
INSERT INTO tb_Jogador_Restricao (descricao, data_Inicio, data_Fim, id_Jogador) VALUES ('Restrição de exemplo para o jogador', '2025-06-01', '2025-07-01', 2);



/* testar no console h2:
SELECT * FROM TB_USUARIO;
SELECT * FROM TB_CIDADE;
SELECT * FROM TB_ESTADIO;
SELECT * FROM TB_TIME;
SELECT * FROM TB_PESSOA;
SELECT * FROM TB_CONTRATO;
SELECT * FROM TB_JOGADOR_RESTRICAO;
SELECT * FROM TB_TIME_RESTRICAO
*/ 