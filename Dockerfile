FROM openjdk:17
EXPOSE 8080
Add target/student-managment.jar student-managment.jar
ENTRYPOINT [ "java" ,"-jar" , "/student-managment.jar" ]

