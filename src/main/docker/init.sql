CREATE TABLE IF NOT EXISTS player (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    points INT NOT NULL,
    rank INT NOT NULL
);

INSERT INTO player (last_name, first_name, birth_date, points, rank) VALUES
('Nadal', 'Rafael', '1986-06-03', 5000, 1),
('Djokovic', 'Novak', '1987-05-22', 4000, 2),
('Federer', 'Roger', '1981-08-08', 3000, 3),
('Murray', 'Andy', '1987-05-15', 2000, 4);

CREATE TABLE IF NOT EXISTS dyma_user (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL
);

INSERT INTO dyma_user(login, password, last_name, first_name) VALUES
("admin", "$2y$10$IPaCh4jXJHMLSXxTDv3kQ.XuMwzdxoD7It.dOqKHrdqSjeK9sOFla", "Dyma", "Admin"),
("user", "$2y$10$JREma18qt3KbgQ2Nu3zlWeJXGkBTPymYgv8f/4HY9GxHcA5VrxEZW", "Doe", "John");

CREATE TABLE IF NOT EXISTS dyma_role (
    name VARCHAR(50) NOT NULL PRIMARY KEY
);

INSERT INTO dyma_role(name) VALUES
("ROLE_ADMIN"),
("ROLE_USER");

CREATE TABLE IF NOT EXISTS dyma_user_role (
    user_id INT(11) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES dyma_user(id),
    FOREIGN KEY (role_name) REFERENCES dyma_role(name)
);

INSERT INTO dyma_user_role(user_id, role_name) VALUES
(1, "ROLE_ADMIN"),
(1, "ROLE_USER"),
(2, "ROLE_USER");
