
# PetStore Monotlithic APP SpringBoot

Getting started

This is a monolithic app made on springboot with 3 controllers (Pet , Order and User) on each controller there is a CRUD (create , read , update , delete) and uses PostgresSQL as a database 

## Running the App 

### Code Source 

to get the full code source for the app before running it just run this command 
> git clone [https://gitlab.forge.berger-levrault.com/bl-drit/bl.drit.experiments/software.engineering/ecodesing/stage-of-louay-2024/use-cases/petstore-monotlithic-app-springboot.git](https://gitlab.forge.berger-levrault.com/bl-drit/bl.drit.experiments/software.engineering/ecodesing/stage-of-louay-2024/use-cases/petstore-monotlithic-app-springboot.git)

### Running the App locally 

to run the app locally few steps have to be followed at first 

***Database : ***

for the database we use postgres docker container that we configure as the following 
> docker run  —  name postgres-container -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:latest

![](https://cdn-images-1.medium.com/max/2000/1*WMKQNbfcBvsiE7czOwbGFw.png)

and once the container is created we can just connect to it and create the database called “**petstore**” 
> docker exec -it postgres-container psql -U postgres

and then just run this “CREATE DATABASE petstore ;” 

![](https://cdn-images-1.medium.com/max/2000/1*jwSsx6SdwlqIrYojmxILvA.png)

and now the database is ready for use 

***The App : ***

after cloning the code source just navigate to /ressources/application.properties and change the configuration of the database connection as the following 

(Update this file just if you didn’t follow the same ports and commands as me) 

![](https://cdn-images-1.medium.com/max/2000/1*i2HieFyAjvKbOtoTkqaFzg.png)

now just in the command line run this command “**mvn clean package**” 

![](https://cdn-images-1.medium.com/max/2000/1*IbyP_vnR9opdA31484Hykg.png)

and now just run “**java -jar PetStore-Monolithique-0.0.1-SNAPSHOT.jar**”

![](https://cdn-images-1.medium.com/max/2000/1*zrirAMWytiZfGNUyGsmMKQ.png)

### Running the App using Docker 

in the repository there is already a dockerfile for the spring app and a docker-compose to link the app with the database 

init.sql : is a file used so that the database will be created once the volume of postgres is mounted

wait-for-it.sh : it is a bash script used to make the spring container wait for the postgres container to be set up and working well 
> docker-compose up — build 

![](https://cdn-images-1.medium.com/max/2000/1*MQX8rabh0bXzDO7TsCoJhA.png)

once all is created we can just see our containers 

![](https://cdn-images-1.medium.com/max/3208/1*IL2Plo_da7piDVlLwXl-0A.png)

and here is the welcome page 

![](https://cdn-images-1.medium.com/max/2000/1*BsRxgWCR4pfUjxJugpGoHQ.png)

## Using Postman with Protobuf Data

To interact with the protobuf data in Postman, follow the steps outlined in the [Using Postman with Protobuf Data user guide](https://blevraultgroup.sharepoint.com/:w:/t/StagedeAdemTelemetry/EafoZAn7HSRJv0YFSO7zdT0BawKIe_nMoE8l-_8GIXwtpA?e=z1yJIP).
