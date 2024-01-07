FROM openjdk:8
EXPOSE 8080
Add target/managment-0.0.1-SNAPSHOT.jar managment-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java" ,"-jar" , "/managment-0.0.1-SNAPSHOT.jar" ]