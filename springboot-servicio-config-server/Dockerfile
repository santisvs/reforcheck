FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8888
ADD ./target/${JAR_FILE} servicio-config-server.jar
ENTRYPOINT ["java","-jar","/servicio-config-server.jar"]