FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-plantas.jar
ENTRYPOINT ["java","-jar","/servicio-plantas.jar"]