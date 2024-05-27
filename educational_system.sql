-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2024 lúc 12:32 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `educational_system`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `No` int(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`No`, `Name`, `Phone`, `Email`, `Password`) VALUES
(1, 'Admin', '0328166890', 'educationalsystem8@gmail.com', 'd338e26fd13e7d077c09faa5faf66ab4d8ff6b93e02e5e4269376bf4ddb261a76e1c9219591a3fad8071fb1276928ab3dd5fd3621647728ad50065345cdba5bf');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `class`
--

CREATE TABLE `class` (
  `ID_Class` varchar(50) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `ID_Lecturer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `class`
--

INSERT INTO `class` (`ID_Class`, `Name`, `ID_Lecturer`) VALUES
('CSDL(1)', 'Cơ sở dữ liệu (1)', 'GV1'),
('CTDL(1)', 'Cấu trúc dữ liệu (1)', 'GV1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lecturer`
--

CREATE TABLE `lecturer` (
  `ID_Lecturer` varchar(50) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `Date_of_birth` date NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `Degree` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lecturer`
--

INSERT INTO `lecturer` (`ID_Lecturer`, `Name`, `Gender`, `Date_of_birth`, `Address`, `Email`, `Phone`, `Password`, `Degree`) VALUES
('GV1', 'Trần Văn B', 'Nam ', '1980-01-01', 'Quảng Nam', 'ngvanhuy070809@gmail.com', '0945228793', '3d19f993c560667b0795e01157fc0e1b4e68324df7af5c0c838bd331fa2076df1b86f650ffac89e14965eefc1664594507ecdefdeea189645c405f400d97ba11', 'TSi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mark`
--

CREATE TABLE `mark` (
  `ID_Mark` int(11) NOT NULL,
  `ID_Class` varchar(20) NOT NULL,
  `ID_Student` varchar(20) NOT NULL,
  `Ex_Grades` decimal(5,1) DEFAULT NULL,
  `Midterm_Grades` decimal(5,1) DEFAULT NULL,
  `Final_Grades` decimal(5,1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `mark`
--

INSERT INTO `mark` (`ID_Mark`, `ID_Class`, `ID_Student`, `Ex_Grades`, `Midterm_Grades`, `Final_Grades`) VALUES
(7, 'CSDL(1)', '23IT.EB01', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `ID_Notification` int(11) NOT NULL,
  `ID_Lecturer` varchar(20) NOT NULL,
  `ID_Student` varchar(20) NOT NULL,
  `Subject` text NOT NULL,
  `Content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `ID_Student` varchar(50) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `Date_of_birth` date NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Phone` varchar(50) NOT NULL,
  `Password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`ID_Student`, `Name`, `Gender`, `Date_of_birth`, `Address`, `Email`, `Phone`, `Password`) VALUES
('23IT.EB01', 'Nguyễn Văn H', 'Nam ', '2005-01-01', 'Đà Nẵng', 'ngvanhuy0000@gmail.com', '0328166890', 'fe627eeced3bcd4bf40e759c1511e2d4d48065d7b0280af938d816cfae8cb8f7de51477b6225cbd9284e777b7c886e096918f8d81b40379fe06a07531ed632c2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `studentclass`
--

CREATE TABLE `studentclass` (
  `No` int(11) NOT NULL,
  `ID_Class` varchar(50) NOT NULL,
  `ID_Student` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `studentclass`
--

INSERT INTO `studentclass` (`No`, `ID_Class`, `ID_Student`) VALUES
(9, 'CSDL(1)', '23IT.EB01');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`No`);

--
-- Chỉ mục cho bảng `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`ID_Class`),
  ADD KEY `class_lecturer` (`ID_Lecturer`);

--
-- Chỉ mục cho bảng `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`ID_Lecturer`);

--
-- Chỉ mục cho bảng `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`ID_Mark`),
  ADD KEY `mark_class` (`ID_Class`),
  ADD KEY `mark_student` (`ID_Student`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`ID_Notification`),
  ADD KEY `notification_lecturer` (`ID_Lecturer`),
  ADD KEY `notification_student` (`ID_Student`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID_Student`);

--
-- Chỉ mục cho bảng `studentclass`
--
ALTER TABLE `studentclass`
  ADD PRIMARY KEY (`No`),
  ADD KEY `class_foreign` (`ID_Class`),
  ADD KEY `student_foreign` (`ID_Student`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `No` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `mark`
--
ALTER TABLE `mark`
  MODIFY `ID_Mark` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `ID_Notification` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `studentclass`
--
ALTER TABLE `studentclass`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_lecturer` FOREIGN KEY (`ID_Lecturer`) REFERENCES `lecturer` (`ID_Lecturer`);

--
-- Các ràng buộc cho bảng `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `mark_class` FOREIGN KEY (`ID_Class`) REFERENCES `class` (`ID_Class`),
  ADD CONSTRAINT `mark_student` FOREIGN KEY (`ID_Student`) REFERENCES `student` (`ID_Student`);

--
-- Các ràng buộc cho bảng `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_lecturer` FOREIGN KEY (`ID_Lecturer`) REFERENCES `lecturer` (`ID_Lecturer`),
  ADD CONSTRAINT `notification_student` FOREIGN KEY (`ID_Student`) REFERENCES `student` (`ID_Student`);

--
-- Các ràng buộc cho bảng `studentclass`
--
ALTER TABLE `studentclass`
  ADD CONSTRAINT `class_foreign` FOREIGN KEY (`ID_Class`) REFERENCES `class` (`ID_Class`),
  ADD CONSTRAINT `student_foreign` FOREIGN KEY (`ID_Student`) REFERENCES `student` (`ID_Student`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
