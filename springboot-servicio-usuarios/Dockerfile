FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-usuarios.jar
ENTRYPOINT ["java","-jar","/servicio-usuarios.jar"]