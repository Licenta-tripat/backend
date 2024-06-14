FROM --platform=linux/amd64 azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY build/libs/*.jar tripit.jar
ENTRYPOINT ["java","-jar","/tripit.jar"]