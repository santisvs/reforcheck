FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-mobiliarioobras.jar
ENTRYPOINT ["java","-jar","/servicio-mobiliarioobras.jar"]