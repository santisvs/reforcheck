FROM openjdk:8
VOLUME /tmp
EXPOSE 8002
ADD ./target/springboot-servicio-items-0.0.1-SNAPSHOT.jar servicio-items.jar
ENTRYPOINT ["java","-jar","/servicio-items.jar"]