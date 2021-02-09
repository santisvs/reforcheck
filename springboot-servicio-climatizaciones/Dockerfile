FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-climatizaciones.jar
ENTRYPOINT ["java","-jar","/servicio-climatizaciones.jar"]