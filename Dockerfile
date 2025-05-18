# 1. Java ortamı indir
FROM openjdk:17-jdk-slim

# 2. Jar dosyasını app.jar olarak içeri kopyala
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 3. Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "/app.jar"]
