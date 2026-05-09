FROM eclipse-temurin:17-jdk as build

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Giai đoạn 2: Tạo Image chạy app (siêu nhẹ)
FROM eclipse-temurin:17-jdk
WORKDIR /app
# Chỉ copy file JAR từ giai đoạn build sang
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java","-Xmx300m", "-jar", "app.jar"]