FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-fabricantes.jar
ENTRYPOINT ["java","-jar","/servicio-fabricantes.jar"]