# FROM openjdk:17
# EXPOSE 8080
# Add target/student-managment.jar student-managment.jar
# ENTRYPOINT [ "java" ,"-jar" , "/student-managment.jar" ]

#  Stage 2: Build application image
FROM openjdk:17

# Download MySQL JDBC driver (or equivalent for your DBMS)
COPY https://cdn.mysql.com/connector/j/mysql-connector-java-8.0.36.jar /lib/mysql-connector-java.jar


# Copy your application jar/war file
COPY target/student-managment.jar student-managment.jar

# Set environment variables for database connection
ENV DATABASE_HOST jdbc:mysql://mysqldb:3306/student
ENV DATABASE_PORT 3306
ENV DATABASE_NAME student
ENV DATABASE_USER root
ENV DATABASE_PASSWORD root
# Expose port 8080
EXPOSE 8080

# Entrypoint: run application
ENTRYPOINT ["java", "-jar", "/student-managment.jar"]
