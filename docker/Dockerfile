# ─────────────────────────────── build stage ──────────────────────────────── #
FROM maven:3.6.0-jdk-11-slim AS build
LABEL org.opencontainers.image.authors="v.boursinos"

COPY pom.xml /home/app/

RUN mvn -f /home/app/pom.xml -Dspring-boot.repackage.skip --batch-mode package && rm -r /home/app/target
COPY src /home/app/src

RUN mvn -f /home/app/pom.xml -DskipTests --offline --batch-mode package
# ─────────────────────────────── run the jar ──────────────────────────────── #
FROM openjdk:11
LABEL org.opencontainers.image.authors="v.boursinos"

RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 648ACFD622F3D138
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 0E98404D386FA1D9

RUN apt-get update && apt-get install -y python3 python3-pip
RUN python3 -m pip install pip==20.0.2 setuptools

WORKDIR /app

COPY --from=build /home/app/target/hr-platform-0.0.1-SNAPSHOT.jar ./hr-platform.jar
#Since the maven build ended remove the settings.xml in order to not make the credentials available in the image.

RUN chgrp -R 0 /app && \
    chmod -R g=u /app

ENTRYPOINT ["java", "-XX:+UseSerialGC", "-XX:MaxRAM=1024m", "-XX:MaxPermSize=512m", "-Xmx8g", "-Xms256m", "-Xss512k", "-jar", "/app/hr-platform.jar", "0"]