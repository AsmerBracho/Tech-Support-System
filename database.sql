-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.26-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for gui_assignment
CREATE DATABASE IF NOT EXISTS `gui_assignment` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `gui_assignment`;

-- Dumping structure for table gui_assignment.ticket
CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subjet` varchar(150) NOT NULL DEFAULT '0',
  `description` varchar(3500) NOT NULL DEFAULT '0',
  `priority` varchar(50) NOT NULL DEFAULT '0',
  `date` bigint(20) NOT NULL DEFAULT '0',
  `tech` varchar(50) NOT NULL DEFAULT '0',
  `status` varchar(50) NOT NULL DEFAULT '0',
  `close_date` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table gui_assignment.ticket: ~9 rows (approximately)
DELETE FROM `ticket`;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`id`, `subjet`, `description`, `priority`, `date`, `tech`, `status`, `close_date`) VALUES
	(2, 'Incompetence or Duplicity?', 'This is honestly the worst company I have ever dealt with in my entire life. \r\nIf anyone is considering starting a contract with them I would urge them not to.', 'Urgent', 1512311999305, 'Johan', 'Closed', 1514378863104),
	(3, 'Absolutely Awful', 'Every time I have had any contact with them it has been a complete disaster which has been slow and never resolves\r\n the issue first time. AVOID at all costs despite what deals they try to offer you.', 'Urgent', 1512312016816, 'Johan', 'Opened', 0),
	(4, 'Going down hill every day', 'Going down hill every day, why do they don\'t learn from there mistakes. \r\nAs it not rocket science, they where good for the start and now they have\r\n fallen down to the bottom.', 'Urgent', 1512317979517, 'John', 'Closed', 1512319805061),
	(5, 'Deceptive Check out Practices', 'Terrible and deceptive customer service practices.\r\nWhen completing check out it says will price match a better offer.\r\n When you call to do this they say it is only on handsets,\r\n even though the offer appears when purchasing a sim only contract.', 'LongTerm', 1512320194573, 'James', 'Opened', 0),
	(7, 'Never in my life have I been treated like this', 'Never in my life have I been treated this bad by a company rude,\r\n arrogant and and told different things by different advisers.', 'Urgent', 1512322131932, 'James', 'Opened', 0),
	(8, 'Cheats and liars', 'Vodafone misled me on purpose at point of sale when I upgraded my phone. \r\nThe sales rep told me I would get Sky sports for 2 years free to watch on my TV so I would take a higher tariff that obviously cost more. \r\nWhen I tried to claim my offer it turned out to be Sky sports app which is basically rubbish and not what I was promised. \r\nWhen I brought this up with Vodafone they just said no that was not the case and offered me 60 pounds which is an insult.\r\n I raised it with Ofcom ref00473787 but still nothing happened and they have just blanked me since.\r\n I call and they leave me on hold. Awful company, will be going back to O2 when my contract runs out. STAY AWAY FROM VODAFONE.', 'LongTerm', 1512336170945, 'Johan', 'Closed', 1514572141034),
	(9, 'Worst company ever - AVOID LIKE THE PLAGUE', 'Simply the worst company out there. \nCynical treatment of customers. \nWhatever you do, don’t get a contract with them. Try getting\n through to customer services to obtain the code to cancel your contract \nand take your number elsewhere, and it will take well over an hour, \nbecause at this point they will try anything to stall you, including offering\n you a much better deal than the one they’ve just offered or the one you’ve \nbeen on for two years. Being a Vodafone customer is a truly horrible experience.\nAvoid like the plague.', 'Normal', 1514559260857, 'Johan', 'Opened', 0),
	(10, 'Pish up in a brewery', 'Delivered phone to the wrong address six times. \r\nThey put Ed Sheeran on when you\'re on hold, \r\nwhich is usually for at least half an hour. \r\nPeople on the phone have no idea what they\'re talking about.\r\n Go with absolutely anyone else.', 'Urgent', 1514559401410, 'Jhon', 'Opened', 0),
	(11, 'It was my bad experience with vodafone', 'I want to share with you my experience with vodafone.\n I was using Vodafone services from last march 2017. But every month \nthe bill of it has come around 800 something whenever my plan was \nonly 499 and monthly bill should be come around 560. This process\n was going on continuously from last some months. When my last months\n bill came to be of 1400 then I call Customer Care to correct the \nbill but at that time I didn’t get any response. I called in October last. After\n ', 'Urgent', 1514559655362, 'Jhon', 'Opened', 0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;

-- Dumping structure for table gui_assignment.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL DEFAULT '0',
  `password` varchar(200) NOT NULL DEFAULT '0',
  `type` varchar(100) NOT NULL DEFAULT '0',
  `emmail` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Dumping data for table gui_assignment.users: ~8 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `type`, `emmail`, `firstName`, `lastName`) VALUES
	(1, 'asmer', '7db13bb2dadd127e7a79bb665e697879', 'Admin', 'asmer.alef@gmail.com', 'Asmer', 'Bracho'),
	(5, 'johan', '7db13bb2dadd127e7a79bb665e697879', 'TechSupport', 'johan@johan.com', 'Johan', 'Perdomo'),
	(13, 'james', '7db13bb2dadd127e7a79bb665e697879', 'TechSupport', 'james@james.com', 'James', 'Smith'),
	(14, 'andrea', '7db13bb2dadd127e7a79bb665e697879', 'Admin', 'andre@gmail.com', 'Andrea', 'Bonita'),
	(15, 'marbe', '7db13bb2dadd127e7a79bb665e697879', 'Manager', 'marbe@hotmail.com', 'Marbella', 'Briceno'),
	(16, 'naco', '7db13bb2dadd127e7a79bb665e697879', 'Admin', 'naquito@gmail.com', 'Draco', 'Esteban'),
	(17, 'john', '7db13bb2dadd127e7a79bb665e697879', 'TechSupport', 'thejohn@gmail.com', 'John', 'Briceno'),
	(18, 'admin', '80afb819430c4978b9086ace01eb7dbe', 'Admin', 'admin@admin.com', 'Admin', 'Admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
