FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-revestimientos.jar
ENTRYPOINT ["java","-jar","/servicio-revestimientos.jar"]