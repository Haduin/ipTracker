FROM openjdk:21-jdk
WORKDIR /app
COPY build/libs/ipTracker-wit-0.1.jar app.jar
CMD ["java", "-jar", "app.jar"]