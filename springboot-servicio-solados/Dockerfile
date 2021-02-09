FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-solados.jar
ENTRYPOINT ["java","-jar","/servicio-solados.jar"]