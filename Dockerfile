FROM ubuntu:18.04


RUN apt-get update
RUN apt-get install -y software-properties-common && \
    add-apt-repository ppa:linuxuprising/java -y && \
    apt-get update && \
    echo oracle-java11-installer shared/accepted-oracle-license-v1-2 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java11-installer

VOLUME /tmp
COPY ./pet-clinic-web/build/libs/pet-clinic-web-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
