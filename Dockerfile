FROM gradle:6.0.1-jdk11 as builder

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY build.gradle settings.gradle /usr/app/

COPY gradle $APP_HOME/gradle

COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

EXPOSE 8090

RUN mkdir -p /app
ADD build/libs/ip-challenge-meli.jar /app/ip-challenge-meli.jar

ENTRYPOINT ["java", "-jar", "/app/ip-challenge-meli.jar"]