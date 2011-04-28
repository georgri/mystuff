--
-- Create schema tvprogram
--

CREATE DATABASE IF NOT EXISTS tvprogram;
USE tvprogram;

--
-- Definition of table `channel`
--

DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel` (
  `chid` int(10) unsigned NOT NULL auto_increment,
  `chtitle` varchar(100) NOT NULL,
  `logo` VARCHAR(45),
  PRIMARY KEY  (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Definition of table `program`
--

DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `pid` int(10) unsigned NOT NULL auto_increment,
  `chid` int(10) unsigned NOT NULL,
  `ptitle` varchar(200) NOT NULL,
  `kind` enum('information','film','soap','sport','kids','entertainment','education','various') NOT NULL,
  `ptime` datetime NOT NULL,
  `duration` int(10) unsigned NOT NULL default '0',
  `reccurence` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`pid`),
  KEY `FK_program` (`chid`),
  CONSTRAINT `FK_program` FOREIGN KEY (`chid`) REFERENCES `channel` (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;