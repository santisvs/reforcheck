FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8761
ADD ./target/${JAR_FILE} servicio-eureka-server.jar
ENTRYPOINT ["java","-jar","/servicio-eureka-server.jar"]