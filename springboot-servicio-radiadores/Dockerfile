FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-radiadores.jar
ENTRYPOINT ["java","-jar","/servicio-radiadores.jar"]