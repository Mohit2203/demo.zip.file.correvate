# setting the base image required to run your app
FROM openjdk:8
# creating a jar file by application
ARG JAR_FILE=target/*.jar
# copy / add contents from current dir to container
ADD ${JAR_FILE} demo.zip.file.correvat-0.0.1.jar
# Make port 8080 available to others
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.zip.file.correvat-0.0.1.jar"]