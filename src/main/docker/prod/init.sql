CREATE TABLE IF NOT EXISTS player (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    points INT NOT NULL,
    rank INT NOT NULL
);

INSERT INTO public.player(last_name, first_name, birth_date, points, rank) VALUES
('Djokovic', 'Novak', '1987-05-22', 9855, 1),
('Alcaraz', 'Carlos', '2003-05-05', 8805, 2),
('Jannik', 'Sinner', '2001-08-16', 8270, 3),
('Daniil', 'Medvedev', '1996-02-11', 8015, 4),
('Andrey', 'Rublev', '1997-10-20', 5110, 5),
('Alexander', 'Zverev', '1997-04-20', 5085, 6),
('Holger', 'Rune', '2003-04-29', 3700, 7),
('Hubert', 'Hurkacz', '1997-02-11', 3395, 8),
('Alex', 'de Minaur', '1999-02-17', 3210, 9),
('Taylor', 'Fritz', '1997-10-28', 3150, 10);


CREATE TABLE IF NOT EXISTS dyma_user (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL
);

INSERT INTO dyma_user(login, password, last_name, first_name) VALUES
('admin', '$2a$12$VLMmCnWg6g1ZWfctUUYpWeyfArfbPzlq1EC1hi5BPSQeJWMwjmpdy', 'Dyma', 'Admin'),
('visitor', '$2a$12$ACcMbD/j30wmsucWNZpMaeJaO2w0tBIswOzDMOjZhVvEp6RzPhgWS', 'Doe', 'John');

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
