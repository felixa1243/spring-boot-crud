FROM eclipse-temurin:11-jammy
RUN mkdir /opt/app
ENV SPRING_PROFILE=default
COPY target/restapidemo-1.0-SNAPSHOT.jar /opt/app
CMD ["java","-jar","/opt/app/restapidemo-1.0-SNAPSHOT.jar"]
EXPOSE 8080