FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-lavabos.jar
ENTRYPOINT ["java","-jar","/servicio-lavabos.jar"]