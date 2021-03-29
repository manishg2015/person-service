#docker file uses java 11 version
FROM openjdk:11-slim
RUN apt-get update && apt-get install -y wget && apt-get install -y libssl-dev
VOLUME /tmp
RUN wget -O dd-java-agent.jar 'https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.datadoghq&a=dd-java-agent&v=0.43.0'
RUN chmod +x dd-java-agent.jar
RUN addgroup --system mgarg && adduser --system mgarg --ingroup mgarg
USER mgarg:mgarg
WORKDIR = /usr/mgarg/app
ADD ./target/person-service-1.0.jar  /usr/mgarg/app/app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xms512m -Xmx1048m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -javaagent:dd-java-agent.jar -Ddd.service.name=person-service \
            -Ddd.trace.global.tags=env:cdt -Ddd.trace.enabled=true \
            -Ddd.logs.injection=true -jar /usr/mgarg/app/app.jar"]

