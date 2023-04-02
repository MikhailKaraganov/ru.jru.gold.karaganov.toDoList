FROM tomcat:latest

ADD ./target/root.war /usr/local/tomcat/webapps/

EXPOSE 8080