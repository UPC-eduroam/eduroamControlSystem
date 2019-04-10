FROM ubuntu:16.04

RUN apt-get update \
    && apt-get install -qq --no-install-recommends -y git maven openjdk-8-jdk

RUN git clone https://github.com/UPC-eduroam/eduroamControlSystem-Backend.git \
    && cd eduroamControlSystem-Backend \
    && mvn package -D skipTests \
    && mv target/eduroam-control-system-backend-0.0.1-SNAPSHOT.jar /eduroam.jar