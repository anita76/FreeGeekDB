CREATE TABLE volunteerOrient(
	orientDate date, 
	startTime char(20), 
	endTime char(20), 
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, startTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE CASCADE
);

