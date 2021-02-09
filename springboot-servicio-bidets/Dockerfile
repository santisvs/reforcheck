FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-bidets.jar
ENTRYPOINT ["java","-jar","/servicio-bidets.jar"]