FROM java:8-jdk-alpine
ADD target/spring-rabbit-sql-example.jar /app/app.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-Dfile.encoding=UTF-8", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "/app/app.jar"]