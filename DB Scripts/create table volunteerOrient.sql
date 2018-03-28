CREATE TABLE VolunteerOrient(
	orientDate char(20), 
	orientTime char(20), 
	endTime char(20), 
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, orientTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE CASCADE
);

