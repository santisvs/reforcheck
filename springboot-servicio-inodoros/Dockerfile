FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-inodoros.jar
ENTRYPOINT ["java","-jar","/servicio-inodoros.jar"]