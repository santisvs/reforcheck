FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-pinturas.jar
ENTRYPOINT ["java","-jar","/servicio-pinturas.jar"]