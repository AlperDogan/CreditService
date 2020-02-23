FROM maven:3.6.3-jdk-8-slim

COPY . /CreditRestServiceMongo

WORKDIR /CreditRestServiceMongo

RUN mvn prepare-package && \
    mvn jar:jar

CMD java -jar target/CreditRestServiceMongo-1.0-SNAPSHOT.jar

