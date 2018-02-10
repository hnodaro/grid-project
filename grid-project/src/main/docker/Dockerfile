FROM java:8
VOLUME /tmp
ADD grid-project-0.0.1-SNAPSHOT.jar grid.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh","-c","java -jar /grid.jar"]