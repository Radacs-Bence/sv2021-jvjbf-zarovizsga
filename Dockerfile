FROM adoptopenjdk:16-jre-hotspot
WORKDIR /opt/app
COPY target/*.jar finalexam.jar
CMD ["java", "-jar", "finalexam.jar"]