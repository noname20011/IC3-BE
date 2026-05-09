FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Giai đoạn 2: Tạo Image chạy app (siêu nhẹ)
FROM openjdk:17-jdk-slim
WORKDIR /app
# Chỉ copy file JAR từ giai đoạn build sang
COPY --from=build /app/target/study-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
CMD ["java","-Xmx300m", "-jar", "appjar"]