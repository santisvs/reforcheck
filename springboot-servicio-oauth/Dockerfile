FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
EXPOSE 9100
ADD ./target/${JAR_FILE} servicio-oauth-server.jar
ENTRYPOINT ["java","-jar","/servicio-oauth-server.jar"]