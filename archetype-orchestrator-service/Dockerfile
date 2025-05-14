
FROM alpine:latest
 
# Instalar openjdk11 desde repositorio alpine community
# Configurar timezone
RUN    apk --no-cache add openjdk17 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community tzdata  \
    && cp /usr/share/zoneinfo/Europe/Madrid /etc/localtime \
    && echo "Europe/Madrid" > /etc/timezone \
    && apk del tzdata

#Añadir lenguajes
RUN apk update 
RUN apk upgrade
RUN apk add --no-cache sed musl-locales
ENV LANG es_ES.UTF-8
ENV LANGUAGE es_ES:es
ENV LC_ALL es_ES.UTF-8
RUN echo "export LANG=es_ES.UTF-8" >> /etc/profile \
    && echo "export LANGUAGE=es_ES.UTF-8" >> /etc/profile \
    && echo "export LC_ALL=es_ES.UTF-8" >> /etc/profile    
     
# Añadir Jar
ARG APP_FILE=target/*.jar
#ENV APP $APP_FILE
ENV APP_HOME /opt/apps
COPY $APP_FILE $APP_HOME
#COPY $APP $APP_HOME/
 
#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar

# Volumens
VOLUME /tmp
#WORKDIR $APP_HOME
 
# Puertos
EXPOSE 8083
 
RUN ls -la $APP_HOME
RUN echo "exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME"
#RUN echo "exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar " 
# Set the default command to run on boot
ENTRYPOINT ["sh", "-c"]
#CMD ["exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
CMD ["exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME"]  