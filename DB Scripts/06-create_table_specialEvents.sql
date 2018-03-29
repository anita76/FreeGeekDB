create table specialEvents(
	eventDATE date not null,
	name varchar2(30),
	entranceFee  decimal(5,2),
	startTIME varchar2(20) not null,
	endTIME varchar2(20),
	capacity number(5),
	PRIMARY KEY (eventDATE, startTIME)
);

