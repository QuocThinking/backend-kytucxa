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

DELETE FROM phong_can_bo;

ALTER TABLE hinh_anh_phong
DROP FOREIGN KEY hinh_anh_phong_ibfk_1;

ALTER TABLE hinh_anh_phong
ADD CONSTRAINT hinh_anh_phong_ibfk_1
FOREIGN KEY (phong_id) REFERENCES phong_can_bo(id) ON DELETE CASCADE;

INSERT INTO phong_can_bo (ten_phong, loai_phong, canbo_id, tang_id, building_id)
VALUES 
-- building 2 floor 1 
('101', 'Single', NULL, 1, 2),
('102', 'Double', NULL, 1, 2),
('103', 'Double', NULL, 1, 2),
('117', 'Single', NULL, 1, 2),
('115', 'Double', NULL, 1, 2),
('112', 'Double', NULL, 1, 2),
('110', 'Double', NULL, 1, 2),
('108', 'Double', NULL, 1, 2),
('116', 'Single', NULL, 1, 2),
('113', 'Double', NULL, 1, 2),
('111', 'Double', NULL, 1, 2),
('109', 'Double', NULL, 1, 2),
('107', 'Double', NULL, 1, 2),
('106', 'Single', NULL, 1, 2),
('105', 'Single', NULL, 1, 2),
-- building 2 floor 2
('201', 'Single', NULL, 2, 2),
('202', 'Single', NULL, 2, 2),
('203', 'Single', NULL, 2, 2),
('205', 'Single', NULL, 2, 2),
('206', 'Single', NULL, 2, 2),
('207', 'Single', NULL, 2, 2),
('208', 'Single', NULL, 2, 2),
('209', 'Single', NULL, 2, 2),
('210', 'Single', NULL, 2, 2),
('212', 'Single', NULL, 2, 2),
('215', 'Single', NULL, 2, 2),
('217', 'Single', NULL, 2, 2),
('219', 'Single', NULL, 2, 2),
('211', 'Single', NULL, 2, 2),
('213', 'Single', NULL, 2, 2),
('216', 'Single', NULL, 2, 2),
('218', 'Single', NULL, 2, 2),
('237', 'Single', NULL, 2, 2),
('235', 'Double', NULL, 2, 2),
('232', 'Double', NULL, 2, 2),
('230', 'Single', NULL, 2, 2),
('228', 'Single', NULL, 2, 2),
('226', 'Single', NULL, 2, 2),
('223', 'Single', NULL, 2, 2),
('236', 'Single', NULL, 2, 2),
('233', 'Double', NULL, 2, 2),
('231', 'Double', NULL, 2, 2),
('229', 'Single', NULL, 2, 2),
('227', 'Single', NULL, 2, 2),
('225', 'Single', NULL, 2, 2),
('222', 'Single', NULL, 2, 2),
('221', 'Single', NULL, 2, 2),
('220', 'Single', NULL, 2, 2),
-- building 2 floor 3
('301', 'Single', NULL, 3, 2),
('302', 'Single', NULL, 3, 2),
('303', 'Single', NULL, 3, 2),
('305', 'Single', NULL, 3, 2),
('306', 'Single', NULL, 3, 2),
('307', 'Single', NULL, 3, 2),
('308', 'Single', NULL, 3, 2),
('309', 'Single', NULL, 3, 2),
('310', 'Single', NULL, 3, 2),
('312', 'Single', NULL, 3, 2),
('315', 'Single', NULL, 3, 2),
('317', 'Single', NULL, 3, 2),
('319', 'Single', NULL, 3, 2),
('311', 'Single', NULL, 3, 2),
('313', 'Single', NULL, 3, 2),
('316', 'Single', NULL, 3, 2),
('318', 'Single', NULL, 3, 2),
('337', 'Single', NULL, 3, 2),
('335', 'Double', NULL, 3, 2),
('332', 'Double', NULL, 3, 2),
('330', 'Single', NULL, 3, 2),
('328', 'Single', NULL, 3, 2),
('326', 'Single', NULL, 3, 2),
('323', 'Single', NULL, 3, 2),
('336', 'Single', NULL, 3, 2),
('333', 'Double', NULL, 3, 2),
('331', 'Double', NULL, 3, 2),
('329', 'Single', NULL, 3, 2),
('327', 'Single', NULL, 3, 2),
('325', 'Single', NULL, 3, 2),
('322', 'Single', NULL, 3, 2),
('321', 'Single', NULL, 3, 2),
('320', 'Single', NULL, 3, 2);

SELECT * FROM phong_can_bo;
