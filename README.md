# RiverTechOddsGame 
An interview project to simulate odds game that allows to create player with unique username and default balance of 1000 credits 
and make bets. 

## Installation
Clone repository:
```bash
git clone https://github.com/akuczmierowski/RiverTechOddsGame.git
git checkout main
```
Install JDK 17 from: https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe
Install Docker from https://docs.docker.com/desktop/setup/install/windows-install/ and run it
Open project in IntelliJ Idea
Go to Terminal, make sure that you are in main directory RiverTechOddsGame and run:
```
docker-compose up 
```
It will create a container running MariaDB that is used in this project
Go to Maven tab in IntelliJ and run install command or in terminal:
```
mvn spring-boot:run
```
This will download all required dependencies and start the application

## Usage

Open RiverTechOddsGameApplication and run main method. 
Visit 
```
http://localhost:8080/swagger-ui/index.html
```
to open Swagger and browse all endpoints

Server is set for listening on port 8080
