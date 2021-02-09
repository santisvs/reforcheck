#!/bin/sh
echo "################################################"
echo "########## Ccompilar paquetes COMMONS ##########"
echo "################################################"
./crear_libreria.sh commons
./crear_libreria.sh commons-entities-mysql
./crear_libreria.sh commons-entities-postgresql
echo "####################################"
echo "########## CREAR IMAGENES ##########"
echo "####################################"
cd /home/santisvs/Windows_Desarrollo/reforcheck/docker-compose
pwd
dos2unix crear_imagen.sh
echo "##########################################"
echo "########## IMAGENES ESTRUCUTURA ##########"
echo "##########################################"
./crear_imagen.sh config-server &
./crear_imagen.sh eureka-server &
./crear_imagen.sh oauth &
./crear_imagen.sh zuul-server &
./crear_imagen.sh fabricante &
wait
echo "##########################################"
echo "########## IMAGENES ELEMENTOS 1 ##########"
echo "##########################################"
./crear_imagen.sh baneras
./crear_imagen.sh bidets &
./crear_imagen.sh duchas &
./crear_imagen.sh inodoros &
./crear_imagen.sh lavabos &
./crear_imagen.sh puertas &
wait
./crear_imagen.sh radiadores
./crear_imagen.sh revestimientos &
./crear_imagen.sh ventanas &
./crear_imagen.sh armarios &
./crear_imagen.sh climatizaciones &
./crear_imagen.sh iluminaciones &
wait
./crear_imagen.sh instalaciones
./crear_imagen.sh mobiliarioobras &
./crear_imagen.sh pinturas &
./crear_imagen.sh rodapies &
./crear_imagen.sh solados &
./crear_imagen.sh techos &
wait
echo "##########################################"
echo "########## IMAGENES PRINCIPALES ##########"
echo "##########################################"
./crear_imagen.sh estancias
./crear_imagen.sh plantas &
./crear_imagen.sh propiedades &
./crear_imagen.sh usuarios &
wait
cd /home/santisvs/Windows_Desarrollo/reforcheck/zipkin-server
pwd
docker build -t zipkin-server:0.0.1-SNAPSHOT .
wait
docker rmi $(docker images | tail -n +2 | awk '$1 == "<none>" {print $'3'}')

