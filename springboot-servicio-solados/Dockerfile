FROM openjdk:8
VOLUME /tmp
ADD ./target/springboot-servicio-solados-0.0.1-SNAPSHOT.jar servicio-solados.jar
ENTRYPOINT ["java","-jar","/servicio-solados.jar"]