DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(1024) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT '0');