CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO usuario (login, password, role)
VALUES
('tranquiloAdmin', '123456', 'ADMIN'),
('tranquiloUser',  '$2a$10$Dow1M8U0KoaZf1B9ouU5U.nJx1FQxg4w5ZcCe1rU2z/d7OQ/Gq1qi', 'USUARIO');
