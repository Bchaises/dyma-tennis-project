CREATE TABLE IF NOT EXISTS player (
    id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    points INT NOT NULL,
    ranks INT NOT NULL
);

INSERT INTO player (last_name, first_name, birth_date, points, ranks) VALUES
('Nadal', 'Rafael', '1986-06-03', 5000, 1),
('Djokovic', 'Novak', '1987-05-22', 4000, 2),
('Federer', 'Roger', '1981-08-08', 3000, 3),
('Murray', 'Andy', '1987-05-15', 2000, 4);
