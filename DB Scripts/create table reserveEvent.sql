CREATE TABLE reserveEvents(
	eventDate char(20) not null,
	eventStartTime char(20) not null,
	purchaseDate char(20),
	userID number(5) not null,
	Primary key(eventDate, eventStartTime,userID),
	FOREIGN KEY (eventDate, eventStartTime) REFERENCES specialEvent( eventDATE, startTIME)
					ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES users( ID)
					ON DELETE SET NULL
);
