FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-estancias.jar
ENTRYPOINT ["java","-jar","/servicio-estancias.jar"]