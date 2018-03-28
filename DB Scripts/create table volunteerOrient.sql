CREATE TABLE VolunteerOrient(
	orientDate date, 
	orientTime date, 
	endTime date, 
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, orientTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE CASCADE
);
