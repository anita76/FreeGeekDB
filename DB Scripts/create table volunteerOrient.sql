CREATE TABLE VolunteerOrient(
	orientDate date, 
	orientTime date, 
	endTime date, 
	instructorID number(5) NOT NULL,
	CONSTRAINT eventTime PRIMARY KEY (date, startTIME),
	CONSTRAINT instructor FOREIGN KEY (instructorID) REFERENCES Employee (ID)
						ON UPDATE NO ACTION
						ON DELETE CASCADE
);
