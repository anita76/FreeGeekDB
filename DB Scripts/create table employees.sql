CREATE TABLE employees (
	ID  number(5) not null,
	fullTime CHAR(3) not null,
	supervisorID number(5),
	PRIMARY KEY (ID)
	FOREIGN KEY (ID) REFERENCES User (ID)
				ON UPDATE NO ACTION
				ON DELETE CASCADE
	FOREIGN KEY (supervisorID) REFERENCES Employee (ID)
				ON UPDATE NO ACTION
				ON DELETE SET NULL
);
