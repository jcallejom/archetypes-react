
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

#basic-security container creates group and user
#RUN addgroup -S saga-group && adduser -S saga-user -G saga-group
#USER saga-user:saga-group

RUN mkdir /opt/apps
# Añadir Jar
ARG APP_FILE=target/*.jar
#ENV APP $APP_FILE
ENV APP_HOME /opt/apps
COPY $APP_FILE $APP_HOME
#COPY $APP $APP_HOME/
 
#ARG JAR_FILE=target/*.jar
#ADD ${JAR_FILE} app.jar
#ADD build/libs/app.jar /app.jar
#ADD build/agent/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

# Añadir OTTEL agent
#descarga previa si no se tiene https://opentelemetry.io/docs/languages/java/getting-started/
#ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar 
ARG APP_OTTEL=docker/agent/opentelemetry-javaagent.jar
ADD $APP_OTTEL $APP_HOME/opentelemetry-javaagent.jar

#ADD opentelemetry-javaagent-extension.jar /opentelemetry-javaagent-extension.jar
#COPY $APP_OTTEL $APP_HOME

# Volumens
VOLUME /tmp
#WORKDIR $APP_HOME
 
# Puertos
#EXPOSE 8080
#Data open telemetry $APP_HOME/opentelemetry-javaagent.jar"
ENV JAVA_OPTS=-javaagent:"$APP_HOME/opentelemetry-javaagent.jar"  
  #\
  #OTEL_TRACES_EXPORTER=logging \
  #OTEL_METRICS_EXPORTER=logging \
  #OTEL_LOGS_EXPORTER=logging \
  #OTEL_METRIC_EXPORT_INTERVAL=15000
 
#RUN ls -la $APP_HOME
#Este run arranca el container y no es lo que queremos
#RUN echo "exec java $JAVA_OPTS -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME"
#RUN echo "exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar" 
# Set the default command to run on boot
#ENTRYPOINT ["sh", "-c"]
#CMD ["exec java -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
#CMD ["exec java $JAVA_OPTS -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME"]  

ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar $APP_HOME"] 