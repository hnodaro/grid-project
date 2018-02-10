FROM java:8
VOLUME /tmp
ADD grid-project-1.jar grid.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh","-c","java -jar /grid.jar"]