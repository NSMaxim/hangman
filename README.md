Simple Hangman app with spring-boot

<h4>Instructions to start</h4>

_Pre-requirements:_
- Java 1.8+
- Maven (or some IDE like Intellij IDEA) 

_Steps:_
1. build the project using maven with command _mvn clean package_
2. go to newly created \target folder and run command _java -jar .\hangman-1.0-SNAPSHOT.jar_
3. open browser and go to _http://localhost:8080/_

<h4>How to build and start only with Docker</h4>

_Steps:_
1. run command to build the image - _docker build -t hangmanapp:v0.2 ._
2. run command to get newly built image id - _docker images_, find the newly created **image_id**
2. run command to start the image - _docker run -t -d -p 9080:8080 **iamge_id**_
