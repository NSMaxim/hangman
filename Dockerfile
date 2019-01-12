#FROM java:latest
#RUN mkdir hangman
#COPY ./target/hangman-1.0-SNAPSHOT.jar /hangman
#WORKDIR /hangman 
#CMD ["java", "-jar" , "hangman-1.0-SNAPSHOT.jar"]

FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/NSMaxim/hangman.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/hangman /app
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/hangman-1.0-SNAPSHOT.jar /app
CMD ["java", "-jar" , "hangman-1.0-SNAPSHOT.jar"]