------ IMPORT PARA DATABASE H2 ------
--INSERT INTO `rol` (name) VALUES ('ROLE_USER');
--INSERT INTO `rol` (name) VALUES ('ROLE_ADMIN');

--INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('andres','$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',1, 'Andres', 'Guzman','profesor@bolsadeideas.com', 1);
--INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('admin','$2a$10$/UkEWOBmxwSjPXDhQH1cZu9rDjwgKn8vjHc4JugHHQU.nGCmbtqnS',1, 'John', 'Doe','jhon.doe@bolsadeideas.com', 2);
--INSERT INTO `usuario` (username, password, enabled, name, lastname, email, rol_id) VALUES ('carla','$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',1, 'Carla', 'Bene','carla07@bolsadeideas.com', 1);

--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (1, 1);
--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (2, 2);
--INSERT INTO `usuario_rol` (usuario_id, rol_id) VALUES (3, 1);

------ IMPORT PARA DATABASE POSTGRE ------
INSERT INTO rol (name) VALUES ('ROLE_USER');
INSERT INTO rol (name) VALUES ('ROLE_ADMIN');

INSERT INTO usuario (username, password, enabled, name, lastname, email, rol_id) VALUES ('andres','$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',true, 'Andres', 'Guzman','profesor@bolsadeideas.com', 1);
INSERT INTO usuario (username, password, enabled, name, lastname, email, rol_id) VALUES ('admin','$2a$10$/UkEWOBmxwSjPXDhQH1cZu9rDjwgKn8vjHc4JugHHQU.nGCmbtqnS',true, 'John', 'Doe','jhon.doe@bolsadeideas.com', 2);
INSERT INTO usuario (username, password, enabled, name, lastname, email, rol_id) VALUES ('carla','$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',true, 'Carla', 'Bene','carla07@bolsadeideas.com', 1);