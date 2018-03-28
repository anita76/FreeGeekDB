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
