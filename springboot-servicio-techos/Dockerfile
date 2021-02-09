FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-techos.jar
ENTRYPOINT ["java","-jar","/servicio-techos.jar"]