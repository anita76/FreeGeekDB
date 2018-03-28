create table specialEvent(
	eventDATE date,
	name char(30), 
	entranceFee  integer, 
	startTIME date, 
	endTIME date,
	capacity integer,
	PRIMARY KEY (eventDATE, startTIME)
);
