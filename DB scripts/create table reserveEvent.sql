CREATE TABLE reserveEvents(
	eventDate date not null,
	eventStartTime date not null,
	purchaseDate date,
	userID number(5) not null,
	Primary key(eventDate, eventStartTime,userID),
	FOREIGN KEY (eventDate, eventStartTime) REFERENCES specialEvent( eventDATE, startTIME)
					ON DELETE CASCADE,
    FOREIGN KEY (userID) REFERENCES users( ID)
					ON DELETE SET NULL
);