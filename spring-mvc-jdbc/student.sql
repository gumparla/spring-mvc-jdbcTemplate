--
-- Database: `shopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
`id` int(11) NOT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `mobno` bigint(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `dob`, `email`, `mobno`, `name`, `password`) VALUES
(1, '2015-01-15 02:09:32', 'hello@gmail.com', 2324, 'Tarunkumar', 'hellohello');

ALTER TABLE `student`
 ADD PRIMARY KEY (`id`);

ALTER TABLE `student`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
