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
  PRIMARY KEY  (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `channel`
--
INSERT INTO `channel` (`chid`,`chtitle`) VALUES 
 (1, 'Первый канал'),
 (2, 'Россия 1'),
 (3, 'ТВ Центр'),
 (4, 'НТВ'),
 (5, 'Россия Культура'),
 (6, 'СТС'),
 (7, 'ТНТ')
;

--
-- Definition of table `program`
--

DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `pid` int(10) unsigned NOT NULL auto_increment,
  `chid` int(10) unsigned NOT NULL,
  `ptitle` varchar(200) NOT NULL,
  `kind` enum('information','film','soap','sport','kids','entertainment','education','various') NOT NULL,
  `time` datetime NOT NULL,
  `duration` int(10) unsigned NOT NULL default '0',
  `reccurence` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`tid`),
  KEY `FK_program` (`chid`),
  CONSTRAINT `FK_program` FOREIGN KEY (`chid`) REFERENCES `channel` (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `program`
--
INSERT INTO `program` (`pid`,`chid`,`ptitle`,`kind`,`time`,`duration`,`reccurence`) VALUES 
 (   1,  1,  'Новости'                          , 'information'    , '2010-01-25 09:00:00',  20, 1),
 (   2,  1,  'Малахов+'                         , 'various'        , '2010-01-25 09:20:00',  60, 1),
 (   3,  1,  'Модный приговор'                  , 'entertainment'  , '2010-01-25 10:20:00',  60, 1),
 (   4,  1,  'Контрольная закупка'              , 'information'    , '2010-01-25 11:20:00',  40, 1),
 (   5,  1,  'Новости'                          , 'information'    , '2010-01-25 12:00:00',  20, 1),
 (   6,  1,  'Участок'                          , 'information'    , '2010-01-25 12:20:00',  60, 1),
 (   7,  1,  'Детективы'                        , 'soap'           , '2010-01-25 13:20:00',  40, 1),
 (   8,  1,  'Другие новости'                   , 'information'    , '2010-01-25 14:00:00',  20, 1),
 (   9,  2,  'Когда на юг улетят журавли'       , 'film'           , '2010-01-25 11:50:00', 110, 0),
 (  10,  2,  'Вести. Дежурная часть'            , 'information'    , '2010-01-25 13:40:00',  20, 1),
 (  11,  2,  'Вести'                            , 'information'    , '2010-01-25 14:00:00',  30, 1),
 (  12,  2,  'Местное время. Вести-Москва'      , 'information'    , '2010-01-25 14:30:00',  20, 1),
 (  13,  2,  'Когда на юг улетят журавли'       , 'film'           , '2010-01-25 14:50:00', 110, 0)
;

