FROM ubuntu:22.10

# install jdk and other utilities
RUN apt update \
&& apt-get upgrade -y \
&& apt-get install -y openjdk-17-jre openjdk-17-jdk wget
RUN apt-get install -y gnupg
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get -y install google-chrome-stable
RUN useradd -ms /bin/bash runner
RUN chown -R runner /usr/bin/google-chrome
USER runner
MAINTAINER "Anderson Lameck <andylamax@programmer.net>"
ENV GRADLE_USER_HOME /gradle
ENV API_MODE mock
ENV API_URL <runner>
WORKDIR /project
CMD ./gradlew :pimonitor-server-app:cleanTest :pimonitor-server-app:test --fail-fast \
&& ./gradlew cleanJvmTest jvmTest --fail-fast \
&& ./gradlew cleanJsNodeTest jsNodeTest \
&& ./gradlew cleanJsBrowserTest jsBrowserTest \
&& ./gradlew build