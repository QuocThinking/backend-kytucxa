DROP DATABASE IF EXISTS HotelRoom;
CREATE DATABASE HotelRoom;
USE HotelRoom;

-- Table to represent buildings
CREATE TABLE building (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- Staff (Can_bo)
CREATE TABLE can_bo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_can_bo VARCHAR(255) NOT NULL,
    ngay_den DATE,
    ngay_di DATE
);

-- Room details, with building_id and tang_id
CREATE TABLE phong_can_bo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_phong VARCHAR(255) NOT NULL,
    loai_phong VARCHAR(50),
    canbo_id INT,
    tang_id INT,
    building_id INT,
    FOREIGN KEY (canbo_id) REFERENCES can_bo(id),
    FOREIGN KEY (tang_id) REFERENCES tang(id),
    FOREIGN KEY (building_id) REFERENCES building(id)
);

-- Floor table (Tang)
CREATE TABLE tang (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_tang VARCHAR(255) NOT NULL
);

-- Staff Images (Hinh_anh_can_bo)
CREATE TABLE hinh_anh_can_bo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    canbo_id INT,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (canbo_id) REFERENCES can_bo(id)
);

-- Room Images (Hinh_anh_phong)
CREATE TABLE hinh_anh_phong (
    id INT PRIMARY KEY AUTO_INCREMENT,
    phong_id INT,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (phong_id) REFERENCES phong_can_bo(id)
);

-- User table for security
CREATE TABLE `user` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ten_nhan_vien VARCHAR(255) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    rules ENUM('ADMIN','MANAGER') DEFAULT 'MANAGER'
);

-- Insert data for building
INSERT INTO building (name)
VALUES 
('aph1'),
('aph2');

-- Insert data for can_bo (staff)
INSERT INTO can_bo (ten_can_bo, ngay_den, ngay_di)
VALUES 
('Nguyen Van A', '2024-01-01', '2024-06-01'),
('Le Thi B', '2024-02-01', '2024-07-01'),
('Tran Van C', '2024-03-01', '2024-08-01'),
('Pham Thi D', '2024-04-01', '2024-09-01'),
('Nguyen Van E', '2024-05-01', '2024-10-01');

-- Insert data for tang (floor)
INSERT INTO tang (ten_tang)
VALUES 
('Floor 1'),
('Floor 2'),
('Floor 3'),
('Floor 4'),
('Floor 5');

-- Insert data for phong_can_bo (rooms)
INSERT INTO phong_can_bo (ten_phong, loai_phong, canbo_id, tang_id, building_id)
VALUES 
('Room 101', 'Single', 1, 1, 1),
('Room 102', 'Double', 2, 1, 1),
('Room 201', 'Single', NULL, 2, 1),
('Room 202', 'Double', 3, 2, 1),
('Room 301', 'Single', NULL, 3, 2);

-- Insert data for hinh_anh_can_bo (staff images)
INSERT INTO hinh_anh_can_bo (canbo_id, url)
VALUES 
(1, 'https://example.com/staff1.jpg'),
(2, 'https://example.com/staff2.jpg'),
(3, 'https://example.com/staff3.jpg'),
(4, 'https://example.com/staff4.jpg'),
(5, 'https://example.com/staff5.jpg');

-- Insert data for hinh_anh_phong (room images)
INSERT INTO hinh_anh_phong (phong_id, url)
VALUES 
(1, 'https://example.com/room101.jpg'),
(2, 'https://example.com/room102.jpg'),
(3, 'https://example.com/room201.jpg'),
(4, 'https://example.com/room202.jpg'),
(5, 'https://example.com/room301.jpg');

-- Insert data for user table (security)
INSERT INTO `user` (ten_nhan_vien, user_name, `password`, rules)
VALUES 
('Admin', 'admin', 'admin123', 'ADMIN'),
('Manager 1', 'manager1', 'pass123', 'MANAGER'),
('Manager 2', 'manager2', 'pass123', 'MANAGER'),
('Manager 3', 'manager3', 'pass123', 'MANAGER'),
('Manager 4', 'manager4', 'pass123', 'MANAGER');


ALTER TABLE phong_can_bo 
ADD COLUMN status ENUM('VACANT', 'OCCUPIED') DEFAULT 'VACANT';

DESCRIBE can_bo;

ALTER TABLE phong_can_bo
ADD section VARCHAR(50);
