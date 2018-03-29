create table users (
    id  number(5) not null,
    firstName varchar2(30),
    lastName varchar2(30),
    email varchar2(50) unique,
    phone varchar2(20),
    membershipStartData date,
    primary key (id),
    CONSTRAINT mailchck CHECK (REGEXP_LIKE (email, '^(\S+)\@(\S+)\.(\S+)$'))
);

CREATE TABLE employees (
	ID  number(5) not null,
	fullTime varchar2(3),
	supervisorID number(5),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES users (ID)
				ON DELETE CASCADE,
	FOREIGN KEY (supervisorID) REFERENCES employees (ID)
				ON DELETE SET NULL
);

CREATE TABLE volunteerOrients(
	orientDate date not null,
	startTime varchar2(20) not null,
	endTime varchar2(20),
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, startTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE SET NULL
);

create table volunteers(
    id number(5) not null,
    orientSignUpDate date,
    points Number(5),
    orientDate date,
    orientTime varchar2(20),
    primary key(id),
    foreign key(id) references users(id)
                        on delete cascade,
    foreign key(orientDate, orientTime) references volunteerOrients(orientDate, startTime)
                        on delete set null
);

create table volunteerShifts(
    station number(1),
    shiftDate date,
    morningShift varchar2(3),
    shiftType varchar2(30),
    volunteerID number(5),
    signupDate date,
    trainingReq varchar2(30),
    instructEmpID number(5),
    primary key(station, shiftDate, morningShift, shiftType),
    foreign key(volunteerID) references volunteers(id)
                            on delete set null,
    foreign key(instructEmpID) references employees(id)
                            on delete set null
);

create table specialEvents(
	eventDATE date not null,
	name varchar2(30),
	entranceFee  decimal(5,2),
	startTIME varchar2(20) not null,
	endTIME varchar2(20),
	capacity number(5),
	PRIMARY KEY (eventDATE, startTIME)
);

CREATE TABLE reserveEvents(
	eventDate date not null,
	eventStartTime varchar2(20) not null,
	purchaseDate date,
	userID number(5) not null,
	Primary key(eventDate, eventStartTime,userID),
	FOREIGN KEY (eventDate, eventStartTime) REFERENCES specialEvents( eventDATE, startTIME)
					ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES users( ID)
					ON DELETE SET NULL
);


