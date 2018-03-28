create table specialEvent(
	eventDATE char(20),
	name char(30), 
	entranceFee  integer, 
	startTIME char(20), 
	endTIME char(20),
	capacity integer,
	PRIMARY KEY (eventDATE, startTIME)
);

