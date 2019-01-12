FROM java:latest
RUN mkdir hangman
COPY ./target/hangman-1.0-SNAPSHOT.jar /hangman
WORKDIR /hangman 
CMD ["java", "-jar" , "hangman-1.0-SNAPSHOT.jar"]