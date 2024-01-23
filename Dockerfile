FROM openjdk:8
EXPOSE 8080
Add target/student-managment.jar student-managment.jar
ENTRYPOINT [ "java" ,"-jar" , "/student-managment.jar" ]