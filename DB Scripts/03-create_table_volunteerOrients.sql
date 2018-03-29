CREATE TABLE volunteerOrients(
	orientDate date not null,
	startTime varchar2(20) not null,
	endTime varchar2(20),
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, startTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE SET NULL
);

