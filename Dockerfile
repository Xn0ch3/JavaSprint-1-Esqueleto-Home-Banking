FROM gradle:8.5-jdk17-alpine

COPY . .

EXPOSE 8080

RUN gradle build

ENTRYPOINT ["java", "-jar","build/libs/XNHomeBanking-0.0.1-SNAPSHOT.jar"]