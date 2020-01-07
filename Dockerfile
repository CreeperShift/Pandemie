FROM gradle:jdk11 as builder
EXPOSE 50123

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:11
EXPOSE 50123
COPY --from=builder /home/gradle/src/build/libs/pandemie-0.1-SNAPSHOT.jar /app/
WORKDIR /app

ENTRYPOINT ["java","-jar","./pandemie-0.1-SNAPSHOT.jar"]