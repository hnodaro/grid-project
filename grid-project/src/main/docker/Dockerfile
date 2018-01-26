FROM java:8
VOLUME /tmp
ADD stock-restServer.jar grid.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh","-c","java -jar /grid.jar"]