FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} servicio-rodapies.jar
ENTRYPOINT ["java","-jar","/servicio-rodapies.jar"]