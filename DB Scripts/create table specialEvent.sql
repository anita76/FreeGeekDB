create table specialEvent(
	eventDATE date,
	name char(30), 
	entranceFee  integer, 
	startTIME char(20), 
	endTIME char(20),
	capacity integer,
	PRIMARY KEY (eventDATE, startTIME)
);

