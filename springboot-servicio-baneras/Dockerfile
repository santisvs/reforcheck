FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-baneras.jar
ENTRYPOINT ["java","-jar","/servicio-baneras.jar"]