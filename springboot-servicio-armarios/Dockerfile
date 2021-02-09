FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-armarios.jar
ENTRYPOINT ["java","-jar","/servicio-armarios.jar"]