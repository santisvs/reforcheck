INSERT INTO `rol` (name) VALUES ('ROLE_USER');
INSERT INTO `rol` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('andres','12345',1, 'Andres', 'Guzman','profesor@bolsadeideas.com', 1);
INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('admin','abcde',1, 'John', 'Doe','jhon.doe@bolsadeideas.com', 2);
INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('carla','2345',1, 'Carla', 'Bene','carla07@bolsadeideas.com', 1);

--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (1, 1);
--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (2, 2);
--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (3, 1);