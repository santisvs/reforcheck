FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-puertas.jar
ENTRYPOINT ["java","-jar","/servicio-puertas.jar"]