FROM adoptopenjdk/openjdk8-openj9:alpine-slim
EXPOSE 8080
COPY /applications/app-service/build/libs/*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
