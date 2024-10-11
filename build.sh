#!/bin/bash

# Cài đặt Maven
echo "Cài đặt Maven..."
apt-get update && apt-get install -y maven

# Kiểm tra phiên bản Maven
mvn -version

# Chạy lệnh build
echo "Chạy lệnh build..."
mvn clean package -DskipTests
