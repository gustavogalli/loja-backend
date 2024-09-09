-- Inserir usuários
INSERT INTO users (user_id, password, username)
SELECT 1, '$2a$10$NWM0kcUU1csGRPXmz/LpM.z6XCRL2fANh1OQhonCsZC2fskKk5T/q', 'admin'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_id = 1);

INSERT INTO roles (role_id, rolename)
SELECT 1, 'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 1);

INSERT INTO roles (role_id, rolename)
SELECT 2, 'ROLE_USER'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_id = 2);

INSERT INTO users_roles (user_id, role_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM users_roles WHERE user_id = 1 AND role_id = 1);

DELETE FROM produto where id >= 1;

-- Inserir produtos
-- Inserir produtos se eles ainda não existirem
INSERT INTO produto (nome, preco, categoria, foto, vendedor_id, descricao)
SELECT * FROM (SELECT
    'Notebook Dell' AS nome, 3500.00 AS preco, 'Eletrônicos' AS categoria, 'notebook_dell.jpg' AS foto, 1 AS vendedor_id, 'Notebook Dell com processador Intel i7 e 16GB de RAM.' AS descricao
    UNION ALL
    SELECT 'Smartphone Samsung', 2500.00, 'Eletrônicos', 'smartphone_samsung.jpg', 1, 'Smartphone Samsung Galaxy com câmera de 64MP e 128GB de armazenamento.'
    UNION ALL
    SELECT 'Camiseta Polo', 80.00, 'Roupas', 'camiseta_polo.jpg', 1, 'Camiseta polo em algodão, disponível em várias cores.'
    UNION ALL
    SELECT 'Geladeira Brastemp', 2900.00, 'Eletrodomésticos', 'geladeira_brastemp.jpg', 1, 'Geladeira Brastemp com capacidade de 500 litros e tecnologia Frost Free.'
    UNION ALL
    SELECT 'Tênis Nike', 300.00, 'Calçados', 'tenis_nike.jpg', 1, 'Tênis Nike Air Max com amortecimento avançado e design esportivo.'
    UNION ALL

    SELECT 'Cadeira Gamer', 750.00, 'Móveis', 'cadeira_gamer.jpg', 1, 'Cadeira gamer ergonômica com ajuste de altura e apoio lombar.'
    UNION ALL
    SELECT 'Relógio Apple Watch', 1500.00, 'Acessórios', 'apple_watch.jpg', 1, 'Relógio Apple Watch Series 7 com monitoramento de saúde e GPS.'
    UNION ALL
    SELECT 'Cafeteira Expresso', 400.00, 'Eletrodomésticos', 'cafeteira_expresso.jpg', 1, 'Cafeteira expresso automática com capacidade para 2 xícaras.'
) AS new_products
WHERE NOT EXISTS (
    SELECT 1 FROM produto p WHERE p.nome = new_products.nome
);

