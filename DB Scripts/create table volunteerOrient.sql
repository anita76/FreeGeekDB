CREATE TABLE VolunteerOrient(
	orientDate DATE, 
	orientTime TIME, 
	endTime TIME, 
	instructorID number(5) NOT NULL,
	PRIMARY KEY (date, startTIME),
	FOREIGN KEY (instructorID) REFERENCES Employee (ID)
						ON UPDATE NO ACTION
						ON DELETE CASCADE
);
