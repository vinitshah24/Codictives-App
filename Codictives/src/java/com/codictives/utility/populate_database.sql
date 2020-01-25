DROP DATABASE IF EXISTS codictives;
CREATE DATABASE IF NOT EXISTS codictives;
USE codictives;
DROP TABLE IF EXISTS userconnections;
DROP TABLE IF EXISTS connections;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS feedback;

CREATE TABLE IF NOT EXISTS users (
    userid INT AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    zip VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL,
    PRIMARY KEY (userid),
    UNIQUE (username)
);

INSERT INTO users (username, password, firstname , lastname, email, address, city, zip, country) VALUES 
    ("admin", MD5(CONCAT("admin","cd4tv5")), "Everett", "Rubenfeld", "apattini5@stanford.edu", "440 Dayton Circle", "Charlotte", "28079", "USA"), 
    ("user", MD5(CONCAT("user","cd4tv5")), "Sam", "Buckle", "ebuckleb@virginia.edu","3581 High Crossing Drive", "Toronto", "26578", "Canada"),
    ("vinit", MD5(CONCAT("vinit","cd4tv5")), "Vinit", "Shah", "vinit@meetup.com","220 Dorton Parkway", "Mumbai", "42586", "UK");

USE codictives;
CREATE TABLE IF NOT EXISTS connections (
    connectionid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    topic VARCHAR(50) NOT NULL,
    details VARCHAR(10000) NOT NULL,
    location VARCHAR(50) NOT NULL,
    date VARCHAR(50) NOT NULL,
    time VARCHAR(50) NOT NULL,
    ownerid INT NOT NULL,
    CONSTRAINT fk_userid FOREIGN KEY (ownerid)
        REFERENCES users (userid)
);
INSERT INTO connections (name, topic, details , location, date, time, ownerid) VALUES 
("Understanding Javascript", "Front End Technologies",
	"Javascript conference will host over 20 speakers, which will deal not only with React, but with all cutting-edge mobile and web techologies. The event will provide plenty of time to explore different front-end and back-end ecosystems.",
  "UNC Charlotte", "2020-05-28", "13:00", 1),
("Mastering Node.js", "Front End Technologies",
	"NodeJS event is focused on the entire Node.js ecosystem where the speakers will be sharing latest news and information through talks, workshops, and their experiences within the Node community. The event will include introductory remarks and training sessions from the industry professionsls.",
	"UNC Charlotte", "2020-01-12", "14:30", 2),
("Developing Angular Web Applications","Front End Technologies", "Angular event brings you a programme of sessions from 50+ speakers, including members of Angular team at Google and Facebook. The event will include introductory remarks, a structured Q&A from the audience, and a breakout session for one-on-one advising.",
	"UNC Charlotte", "2020-05-29", "15:00", 3),
("Objected Oriented Programming with Java", "Back End Technologies",
	"Java conference will offer more than 100 exhibitors and 200+ speakers from the leading tech companies in the U.S. This event will explore which technologies and methodologies developers need to know now, what the future of software will bring, and how to build better, more inclusive, and collaborative team.",
	"UNC Charlotte", "2020-02-20", "16:00", 1),
    ("Exploring Python Fundamentals", "Back End Technologies",
	"Python event will feature training sessions on variety of Python projects that are often given by the creators or maintainers of the projects. The conference will be focused on various topics in Django and Python development.",
	"UNC Charlotte", "2020-06-14", "17:30", 2),
("Functional Programming with Scala","Back End Technologies",
	"Scala conference brings you a programme of sessions from 100+ speakers allowing the participants to discover and learn about the latest developments in the Scala world. The experts will cover several other areas like Apache Kafka and Apache Spark.",
	"UNC Charlotte", "2020-08-14", "12:30", 3),
("Taming Big Data with Hadoop Ecosystem", "Big Data Technologies",
	"Hadoop conference will provide a unique opportunity to connect will leaders, innovaters and practicioners in Hadoop industry. The conference will explore major topics like BI, data analytics, data management and storage, security and privacy, and data governance.",
	"UNC Charlotte", "2019-07-21", "14:00", 1),
("Analyzing Big Data with Spark", "Big Data Technologies",
	"Spark event will be focused entirely on Apache Spark—assembling the best engineers, scientists, analysts, and executives from around the globe to share their knowledge and receive expert training on the open-source powerhouse. The event will further explore topics like big data, machine learning, data engineering, and data science.",
	"UNC Charlotte", "2019-10-30", "13:30", 2),
("Data Warehousing with Cassandra", "Big Data Technologies", 
	"Apache Cassandra conference will offer the participants to meet, network, and learn about what’s new and what’s coming in Cassandra. The conference will provide a further opportunity for people to bring the best minds together and learn how to achieve real-world success from open source community.",
	"UNC Charlotte", "2019-09-17", "18:00", 3);

USE codictives;
CREATE TABLE IF NOT EXISTS userconnections (
    userconnectionid INT AUTO_INCREMENT,
    userid INT,
    connectionid INT,
    rsvp VARCHAR(5),
    PRIMARY KEY (userconnectionid),
    CONSTRAINT fk_userid_usercon FOREIGN KEY (userid)
        REFERENCES users (userid),
    CONSTRAINT fk_connectionid_usercon FOREIGN KEY (connectionid)
        REFERENCES connections (connectionid)
);
INSERT INTO userconnections (userid, connectionid, rsvp) VALUES (1, 1, "yes"), (1,6, "no"), 
	(2, 4, "yes"), (2, 8, "no"), (3, 3,"yes"), (3, 6,"no"), (3, 9,"maybe");

USE codictives;
CREATE TABLE IF NOT EXISTS feedback (
    contactid INT AUTO_INCREMENT,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    topic VARCHAR(100),
    message VARCHAR(1500),
    PRIMARY KEY (contactid)
);
INSERT INTO feedback (firstname,lastname,topic,message) 
	VALUES 
	("John","Miller","Signup","Forgot Password"), ("Sam","Smith","Connections","Help creating new Connection");


-- SELECT * FROM users;
-- SELECT COUNT(*) > 0 AS output FROM users WHERE username = "user" AND password = MD5(CONCAT("user","cd4tv5"));