FROM tomcat:8.5-alpine
#VOLUME /tmp
#COPY ../target/server-1.war /usr/local/tomcat/webapps/app.war

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh
COPY ./target/server-1.war /usr/local/tomcat/webapps/app.war

CMD ["catalina.sh", "run"]
