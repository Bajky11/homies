# Použití JDK 17 (nebo jiného, podle vaší verze)
FROM eclipse-temurin:21-jdk

# Nastavení pracovního adresáře v kontejneru
WORKDIR /app

# Kopírování JAR souboru do kontejneru
COPY target/*.jar app.jar

# Exponování portu aplikace (shodné s nastavením v application.properties)
EXPOSE 8080

# Spuštění aplikace
ENTRYPOINT ["java", "-jar", "app.jar"]
