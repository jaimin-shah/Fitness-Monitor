-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2015 at 08:41 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `fitness`
--

-- --------------------------------------------------------

--
-- Table structure for table `consumption`
--

CREATE TABLE IF NOT EXISTS `consumption` (
  `date` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `calories` float NOT NULL,
  `diet_target` float NOT NULL,
  `target_complete` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consumption`
--

INSERT INTO `consumption` (`date`, `uid`, `calories`, `diet_target`, `target_complete`) VALUES
('15/03/2015', 1, 1400, 2000, 1),
('15/03/2015', 1, 1500, 1200, 0),
('15/03/2015', 2, 2900, 5200, 1),
('15/03/2015', 1, 0, 200, 1),
('25/03/2015', 2, 4200, 5000, 1),
('26/03/2015', 2, 2400, 1200, 0);

-- --------------------------------------------------------

--
-- Table structure for table `diets`
--

CREATE TABLE IF NOT EXISTS `diets` (
  `diet_id` int(11) NOT NULL,
  `diet_name` varchar(20) NOT NULL,
  `diet_calories` float NOT NULL,
  `protein` float NOT NULL,
  `carbohydrate` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diets`
--

INSERT INTO `diets` (`diet_id`, `diet_name`, `diet_calories`, `protein`, `carbohydrate`) VALUES
(1, 'Apple', 100, 20, 0),
(2, 'Banana', 300, 60, 0),
(3, 'Milk', 600, 63, 0),
(4, 'Eggs', 800, 100, 0);

-- --------------------------------------------------------

--
-- Table structure for table `machines`
--

CREATE TABLE IF NOT EXISTS `machines` (
  `machine_id` int(11) NOT NULL,
  `equip_name` varchar(20) NOT NULL,
  `burning_capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `machines`
--

INSERT INTO `machines` (`machine_id`, `equip_name`, `burning_capacity`) VALUES
(1, 'Dumbles', 500),
(2, 'Walker', 600),
(3, 'Crunch Machine', 700);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `password` varchar(25) NOT NULL,
  `name` varchar(20) NOT NULL,
  `id` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `height` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `birth_date` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`password`, `name`, `id`, `gender`, `height`, `weight`, `birth_date`) VALUES
('123', 'sddd', 1, 'Male', 23, 22, '8/3/2015'),
('1234', 'gf', 2, 'Male', 45, 45, '27/3/2001'),
('1234', 'ther', 4, 'Female', 23, 11, '1/3/2015'),
('1234', 'kjh', 5, 'Male', 20, 20, '11/3/2015');

-- --------------------------------------------------------

--
-- Table structure for table `workout`
--

CREATE TABLE IF NOT EXISTS `workout` (
  `date` varchar(15) NOT NULL,
  `calories_burned` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `gym_target` int(11) NOT NULL,
  `target_complete` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workout`
--

INSERT INTO `workout` (`date`, `calories_burned`, `uid`, `gym_target`, `target_complete`) VALUES
('17/03/2015', 0, 1, 1203, 0),
('17/03/2015', 1300, 1, 1233, 1),
('17/03/2015', 2300, 2, 8500, 0),
('17/03/2015', 2300, 2, 2000, 1),
('20/03/2015', 700, 1, 900, 0),
('21/03/2015', 4500, 1, 5000, 0),
('26/03/2015', 1200, 2, 5412, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
