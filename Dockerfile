FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/course-listing-v1.jar
ADD ${JAR_FILE} course-listing-v1.jar
ENTRYPOINT ["java","-jar","/course-listing-v1.jar"]