# Sử dụng OpenJDK 17
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã build vào image
COPY target/hotelroom-0.0.1-SNAPSHOT.jar app.jar


# Mở cổng cho ứng dụng
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
