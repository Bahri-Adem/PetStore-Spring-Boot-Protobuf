FROM openjdk:11

WORKDIR /app

COPY target/PetStore-Monolithique-0.0.1-SNAPSHOT.jar PetStore-Monolithique-0.0.1-SNAPSHOT.jar
COPY ./wait-for-it.sh .

RUN chmod +x ./wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "postgres_db:5432", "--", "java", "-jar", "PetStore-Monolithique-0.0.1-SNAPSHOT.jar"]
