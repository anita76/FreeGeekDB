CREATE TABLE employees (
	ID  number(5) not null,
	fullTime CHAR(3) not null,
	supervisorID number(5),
	CONSTRAINT employeeID PRIMARY KEY (ID)
	CONSTRAINT ownID FOREIGN KEY (ID) REFERENCES User (ID)
				ON UPDATE NO ACTION
				ON DELETE CASCADE
	CONSTRAINT supervisor FOREIGN KEY (supervisorID) REFERENCES Employee (ID)
				ON UPDATE NO ACTION
				ON DELETE SET NULL
);
