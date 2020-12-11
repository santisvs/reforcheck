::Script para levantar servidor Zipkin conectado con RabbitMQ para recibir los mensajes enviados por RabbitMQ 
@echo off
::Conexion con rabbit server (puerto 5672) rabbit web (puerto 15672)
set RABBIT_ADDRESSES=localhost:5672
set STORAGE_TYPE=mysql
set MYSQL_USER=zipkin
set MYSQL_PASS=zipkin
java -jar ./zipkin-server-2.23.0-exec.jar