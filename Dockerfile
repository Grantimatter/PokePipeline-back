FROM tomcat:9.0.40

COPY ./target/*.war $CATALINA_HOME/webapps

EXPOSE 8080