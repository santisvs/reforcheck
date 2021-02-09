FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-propiedades.jar
ENTRYPOINT ["java","-jar","/servicio-propiedades.jar"]