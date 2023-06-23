FROM openjdk:11-jdk
COPY /target/*.jar calculator.jar
ENTRYPOINT ["java","-jar","/calculator.jar"]
