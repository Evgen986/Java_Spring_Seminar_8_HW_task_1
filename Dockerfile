# Используем базовый образ с поддержкой Java
FROM openjdk:19-slim

# Копируем JAR-файл приложения в контейнер
COPY target/*.jar /app/users.jar

# Параметры для базы данных (пример)
ENV DB_URL=jdbc:postgresql://db:5432/seminar_3
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=123

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "/app/users.jar"]