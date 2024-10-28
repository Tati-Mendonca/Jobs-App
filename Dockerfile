FROM openjdk:17-jdk

COPY target/job_management-0.0.1-SNAPSHOT.jar /app/jobis.jar

CMD ["java", "-jar", "/app/jobis.jar"]

LABEL authors="Tatiane Mendonça"

#ENTRYPOINT ["top", "-b"]