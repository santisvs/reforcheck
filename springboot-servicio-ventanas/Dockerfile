FROM openjdk:8
VOLUME /tmp
ADD ./target/springboot-servicio-ventanas-0.0.1-SNAPSHOT.jar servicio-ventanas.jar
ENTRYPOINT ["java","-jar","/servicio-ventanas.jar"]