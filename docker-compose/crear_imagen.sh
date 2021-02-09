#!/bin/sh
echo "########## Imagen $1 ##########"
cd /home/santisvs/Windows_Desarrollo/reforcheck/springboot-servicio-$1
pwd
dos2unix mvnw
./mvnw clean package -DskipTests