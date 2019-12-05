FROM ubuntu
ARG DEBIAN_FRONTEND=noninteractive
RUN apt update -y && apt upgrade -y
RUN apt install openjdk-8-jdk -y
RUN apt install gtk2.0 libgtk2.0-dev -y
RUN mkdir /eclipse
COPY eclipse /eclipse/
CMD [ "/eclipse/eclipse" ]
