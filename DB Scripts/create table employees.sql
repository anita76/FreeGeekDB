CREATE TABLE employees (
	ID  number(5) not null,
	fullTime CHAR(3) not null,
	supervisorID number(5),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES users (ID)
				ON DELETE CASCADE,
	FOREIGN KEY (supervisorID) REFERENCES employees (ID)
				ON DELETE SET NULL
);
