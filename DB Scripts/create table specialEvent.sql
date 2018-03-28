create table specialEvent(
	eventDATE date,
	name char(30), 
	entranceFee  number(5), 
	startTIME char(20), 
	endTIME char(20),
	capacity number(5),
	PRIMARY KEY (eventDATE, startTIME)
);

