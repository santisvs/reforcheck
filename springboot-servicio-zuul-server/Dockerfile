FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8090
ADD ./target/${JAR_FILE} servicio-zuul-server.jar
ENTRYPOINT ["java","-jar","/servicio-zuul-server.jar"]