FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-iluminaciones.jar
ENTRYPOINT ["java","-jar","/servicio-iluminaciones.jar"]