FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-instalaciones.jar
ENTRYPOINT ["java","-jar","/servicio-instalaciones.jar"]