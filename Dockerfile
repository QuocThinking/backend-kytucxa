# Sử dụng image OpenJDK làm nền tảng
FROM openjdk:17-jdk-slim

# Cài đặt Maven
RUN apt-get update && apt-get install -y maven

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Chạy lệnh Maven để build dự án
RUN mvn clean package

# Sao chép file JAR đã build vào image
COPY target/hotelroom-0.0.1-SNAPSHOT.jar app.jar

# Cấu hình để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
