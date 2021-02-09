FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-duchas.jar
ENTRYPOINT ["java","-jar","/servicio-duchas.jar"]