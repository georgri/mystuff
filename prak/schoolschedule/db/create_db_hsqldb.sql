CREATE SCHEMA tvprogram AUTHORIZATION DBA

--
-- Definition of table `channel`
--

CREATE TABLE tvprogram.channel (
  chid integer NOT NULL PRIMARY KEY,
  chtitle varchar(100) NOT NULL,
  logo VARCHAR(45)
)

--
-- Definition of table `program`
--

CREATE TABLE tvprogram.program (
  pid integer NOT NULL PRIMARY KEY,
  chid integer NOT NULL,
  ptitle varchar(200) NOT NULL,
  kind varchar(20) NOT NULL,
  ptime timestamp NOT NULL,
  duration integer NOT NULL,
  reccurence integer NOT NULL
);