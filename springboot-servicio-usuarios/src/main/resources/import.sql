------ IMPORT PARA DATABASE H2 ------
--INSERT INTO `role` (name) VALUES ('ROLE_OWNER');
--INSERT INTO `role` (name) VALUES ('ROLE_REFOR_WORKER');
--INSERT INTO `role` (name) VALUES ('ROLE_EXT_WORKER');
--INSERT INTO `role` (name) VALUES ('ROLE_ADMIN');
--INSERT INTO `role` (name) VALUES ('ROLE_SUPER_ADMIN');

--INSERT INTO `userapp` (username, password, enabled, name, lastname, email) VALUES ('andres','$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',1, 'Andres', 'Guzman','profesor@bolsadeideas.com');
--INSERT INTO `userapp` (username, password, enabled, name, lastname, email) VALUES ('admin','$2a$10$/UkEWOBmxwSjPXDhQH1cZu9rDjwgKn8vjHc4JugHHQU.nGCmbtqnS',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');
--INSERT INTO `userapp` (username, password, enabled, name, lastname, email) VALUES ('carla','$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',1, 'Carla', 'Bene','carla07@bolsadeideas.com');
--INSERT INTO `userapp` (username, password, enabled, name, lastname, email) VALUES ('marcos','$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',1, 'Marcos', 'Rali','marcosraly@bolsadeideas.com');
--INSERT INTO `userapp` (username, password, enabled, name, lastname, email) VALUES ('lola','$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',1, 'Dolores', 'Gala','doloresga@bolsadeideas.com');

--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (1, 1);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (2, 1);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (2, 2);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (2, 3);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (2, 4);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (2, 5);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (3, 2);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (4, 3);
--INSERT INTO `userapp_role` (userapp_id, role_id) VALUES (5, 4);

------ IMPORT PARA DATABASE POSTGRE ------
INSERT INTO roles (nombre) VALUES ('ROLE_OWNER');
INSERT INTO roles (nombre) VALUES ('ROLE_REFOR_WORKER');
INSERT INTO roles (nombre) VALUES ('ROLE_EXT_WORKER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_SUPER_ADMIN');

INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono) VALUES ('propietario',0,'$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',true, 'Andres', 'Guzman','profesor@bolsadeideas.com','12234567A','656677890');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono) VALUES ('propietario',0,'$2a$10$/UkEWOBmxwSjPXDhQH1cZu9rDjwgKn8vjHc4JugHHQU.nGCmbtqnS',true, 'John', 'Doe','jhon.doe@bolsadeideas.com','45543212H','656987865');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono) VALUES ('propietario',0,'$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',true, 'Carla', 'Bene','carla07@bolsadeideas.com','54435678P','623009988');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono) VALUES ('propietario',0,'$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',true, 'Marcos', 'Rali','marcosraly@bolsadeideas.com','76656432K','678787543');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono) VALUES ('propietario',0,'$2a$10$m8/FGML1cYRA4lqCkjyvj.TEXrizQK8l/kzzNaRALrfyoJS0ozkbi',true, 'Dolores', 'Gala','doloresga@bolsadeideas.com','22245675G','645559210');

INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono, nomina) VALUES ('reforchecker',1,'$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',true, 'Manuel', 'Loma','manuel.loma@reforcheck.com','12234567A','609876543','nomina1');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono, nomina) VALUES ('reforchecker',1,'$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',true, 'Santiago', 'Vallejo','s.vallejo@reforcheck.com','53392862H','630373288','nomina2');
INSERT INTO usuario (dtype, tipo, password, activo, nombre, apellidos, email, dni, telefono, nomina) VALUES ('reforchecker',1,'$2a$10$lPbz5xG8CINplzMPUeEpvutoLN98A6D.6F/mGNva44W6eTbsPpagC',true, 'Claudio', 'Jimenez','c.jimenez@reforcheck.com','56676543D','638987656','nomina3');

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (3, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (4, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (5, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (6, 2);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (7, 5);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (8, 4);