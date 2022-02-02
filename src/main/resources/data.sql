DELETE FROM books;

INSERT INTO books (id, isbn, title, author, description) VALUES (1, '978-0201633610', 'Design Patterns', 'Erich Gamma', 'Mit Design Patterns lassen sich wiederkehrende Aufgaben in der objektorientierten Softwareentwicklung effektiv lösen.');

INSERT INTO books (id, isbn, title, author, description) VALUES (2, '978-3826655487', 'Clean Code', 'Robert C. Martin', 'Das einzige praxisnahe Buch, mit dem Sie lernen, guten Code zu schreiben!');

INSERT INTO books (id, isbn, title, author, description) VALUES (3, '978-3836211161', 'Coding for Fun', 'Gottfried Wolmeringer', 'Dieses unterhaltsam geschriebene Buch führt Sie spielerisch durch die spektakuläre Geschichte unserer Blechkollegen.');

DELETE FROM users;
INSERT INTO users (username, password, role) VALUES ('dominik', '$2a$12$sOnydHMGBYcF3n.tVmXLqefKvpXbPTUQH/y60bpdB7FYmpAhHtmmq', 'ADMIN');
