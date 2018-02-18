-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 12, 2018 at 03:39 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_spring_rest_security_acl`
--

-- --------------------------------------------------------

--
-- Table structure for table `acl_class`
--

CREATE TABLE `acl_class` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `class` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acl_class`
--

INSERT INTO `acl_class` (`id`, `class`) VALUES
(1, 'com.fireduptech.spring.rest.domain.AthleteAccount');

-- --------------------------------------------------------

--
-- Table structure for table `acl_entry`
--

CREATE TABLE `acl_entry` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `acl_object_identity` bigint(20) UNSIGNED NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) UNSIGNED NOT NULL,
  `mask` int(10) UNSIGNED NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acl_entry`
--

INSERT INTO `acl_entry` (`id`, `acl_object_identity`, `ace_order`, `sid`, `mask`, `granting`, `audit_success`, `audit_failure`) VALUES
(53, 6, 0, 9, 1, 1, 0, 0),
(54, 6, 1, 9, 2, 1, 0, 0),
(55, 6, 2, 3, 1, 1, 0, 0),
(56, 6, 3, 3, 16, 1, 0, 0),
(57, 6, 4, 3, 8, 1, 0, 0),
(95, 13, 0, 18, 1, 1, 0, 0),
(96, 13, 1, 18, 2, 1, 0, 0),
(98, 14, 0, 19, 1, 1, 0, 0),
(99, 14, 1, 19, 2, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `acl_object_identity`
--

CREATE TABLE `acl_object_identity` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `object_id_class` bigint(20) UNSIGNED NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) UNSIGNED DEFAULT NULL,
  `owner_sid` bigint(20) UNSIGNED DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acl_object_identity`
--

INSERT INTO `acl_object_identity` (`id`, `object_id_class`, `object_id_identity`, `parent_object`, `owner_sid`, `entries_inheriting`) VALUES
(6, 1, 20, NULL, 9, 1),
(13, 1, 34, NULL, 18, 1),
(14, 1, 35, NULL, 19, 1);

-- --------------------------------------------------------

--
-- Table structure for table `acl_sid`
--

CREATE TABLE `acl_sid` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `principal` tinyint(1) NOT NULL,
  `sid` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `acl_sid`
--

INSERT INTO `acl_sid` (`id`, `principal`, `sid`) VALUES
(3, 1, 'admin'),
(18, 1, 'atheteAdmin@testing.com'),
(9, 1, 'athlete1'),
(19, 1, 'mccarthy.richard111@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `athlete_account`
--

CREATE TABLE `athlete_account` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL DEFAULT '',
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `account_type` varchar(50) NOT NULL,
  `primary_activity` varchar(50) NOT NULL,
  `registration_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `athlete_account`
--

INSERT INTO `athlete_account` (`id`, `email`, `first_name`, `last_name`, `account_type`, `primary_activity`, `registration_date`) VALUES
(34, 'mccarthy.richard1010@gmail.com', 'Richard', 'McCarthy', 'premium', 'cycling', '2017-12-11'),
(35, 'mccarthy.richard111@gmail.com', 'Richard', 'McCarthy', 'premium', 'cycling', '2017-12-11');

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('atheteAdmin@testing.com', 'ROLE_ATHLETE'),
('athlete1', 'ROLE_ATHLETE'),
('mccarthy.richard111@gmail.com', 'ROLE_ATHLETE');

-- --------------------------------------------------------

--
-- Table structure for table `fixed_deposit_details`
--

CREATE TABLE `fixed_deposit_details` (
  `id` bigint(20) NOT NULL,
  `user` varchar(50) NOT NULL,
  `amount` varchar(50) NOT NULL,
  `tenure` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Contains fixed deposit details';

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('admin', '$2a$11$OwEBBvbV0wSJFbwbgleq4eHtoPLiqE3CDEplm23BLncX0M6.GFUE2', 1),
('atheteAdmin@testing.com', '$2a$11$tL1cF2o8RmIDJVm7csu7Meg46RmZTyMXmp37Fsu3QopE1P0O4XwZ.', 1),
('athlete1', '$2a$11$OwEBBvbV0wSJFbwbgleq4eHtoPLiqE3CDEplm23BLncX0M6.GFUE2', 1),
('mccarthy.richard111@gmail.com', '$2a$11$oMHZgt1FV2wt0lh/L1V6huAqfKungEzh5ACPwn9klg86SW7/4ynRC', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acl_class`
--
ALTER TABLE `acl_class`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_acl_class` (`class`);

--
-- Indexes for table `acl_entry`
--
ALTER TABLE `acl_entry`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_acl_entry` (`acl_object_identity`,`ace_order`),
  ADD KEY `fk_acl_entry_acl` (`sid`);

--
-- Indexes for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_acl_object_identity` (`object_id_class`,`object_id_identity`),
  ADD KEY `fk_acl_object_identity_parent` (`parent_object`),
  ADD KEY `fk_acl_object_identity_owner` (`owner_sid`);

--
-- Indexes for table `acl_sid`
--
ALTER TABLE `acl_sid`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_acl_sid` (`sid`,`principal`);

--
-- Indexes for table `athlete_account`
--
ALTER TABLE `athlete_account`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `authorities_idx_1` (`username`,`authority`);

--
-- Indexes for table `fixed_deposit_details`
--
ALTER TABLE `fixed_deposit_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acl_class`
--
ALTER TABLE `acl_class`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `acl_entry`
--
ALTER TABLE `acl_entry`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `acl_sid`
--
ALTER TABLE `acl_sid`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `athlete_account`
--
ALTER TABLE `athlete_account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `fixed_deposit_details`
--
ALTER TABLE `fixed_deposit_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `acl_entry`
--
ALTER TABLE `acl_entry`
  ADD CONSTRAINT `fk_acl_entry_acl` FOREIGN KEY (`sid`) REFERENCES `acl_sid` (`id`),
  ADD CONSTRAINT `fk_acl_entry_object` FOREIGN KEY (`acl_object_identity`) REFERENCES `acl_object_identity` (`id`);

--
-- Constraints for table `acl_object_identity`
--
ALTER TABLE `acl_object_identity`
  ADD CONSTRAINT `fk_acl_object_identity_class` FOREIGN KEY (`object_id_class`) REFERENCES `acl_class` (`id`),
  ADD CONSTRAINT `fk_acl_object_identity_owner` FOREIGN KEY (`owner_sid`) REFERENCES `acl_sid` (`id`),
  ADD CONSTRAINT `fk_acl_object_identity_parent` FOREIGN KEY (`parent_object`) REFERENCES `acl_object_identity` (`id`);

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
