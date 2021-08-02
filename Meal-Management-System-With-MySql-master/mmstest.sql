-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2017 at 02:08 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mmstest`
--

-- --------------------------------------------------------

--
-- Table structure for table `informations`
--

CREATE TABLE `informations` (
  `member_id` int(4) NOT NULL,
  `name` varchar(25) NOT NULL,
  `phone_no` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `informations`
--

INSERT INTO `informations` (`member_id`, `name`, `phone_no`) VALUES
(1001, 'Md. Muhtadir Rahman', '+8801811350997'),
(1002, 'Foysal Hossain', '+880181123123'),
(1003, 'Akash Shikder', '+880181456456'),
(1004, 'Kyachinghla Marma', '+880181678678'),
(1006, 'Atiqur Rahman Chitholian', '+880181234567'),
(1007, 'Shafkat Rayhan', '+880182345656');

-- --------------------------------------------------------

--
-- Table structure for table `process`
--

CREATE TABLE `process` (
  `member_id` varchar(4) NOT NULL,
  `process_id` int(5) NOT NULL,
  `meal` int(3) NOT NULL,
  `expenditure` int(4) NOT NULL,
  `due` varchar(7) DEFAULT NULL,
  `back` varchar(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `process`
--

INSERT INTO `process` (`member_id`, `process_id`, `meal`, `expenditure`, `due`, `back`) VALUES
('1001', 26001, 23, 1234, '867.05', '0.00'),
('1002', 26002, 32, 4322, '0.00', '87.57'),
('1002', 26003, 22, 2345, '0.00', '87.57'),
('1001', 26004, 23, 1234, '867.05', '0.00'),
('1005', 26005, 12, 7357, '0.00', '5590.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `informations`
--
ALTER TABLE `informations`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `process`
--
ALTER TABLE `process`
  ADD PRIMARY KEY (`process_id`),
  ADD KEY `process_ibfk_1` (`member_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
