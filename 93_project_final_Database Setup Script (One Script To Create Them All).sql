--00--

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE reserveEvents';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE specialEvents';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE volunteerShifts';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE volunteers';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE volunteerOrients';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE employees';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;


BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE users';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;



--01--

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


--02--

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


--03--

CREATE TABLE volunteerOrients(
	orientDate date not null,
	startTime varchar2(20) not null,
	endTime varchar2(20),
	instructorID number(5) NOT NULL,
	PRIMARY KEY (orientDate, startTime),
	FOREIGN KEY (instructorID) REFERENCES employees (ID)
						ON DELETE SET NULL
);


--04--

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


--05--

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


--06--

create table specialEvents(
	eventDATE date not null,
	name varchar2(30),
	entranceFee  decimal(5,2),
	startTIME varchar2(20) not null,
	endTIME varchar2(20),
	capacity number(5),
	PRIMARY KEY (eventDATE, startTIME)
);


--07--

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


--11--

insert into users
values('00001', 'Bennet', 'Abraham',
'bennetabraham@gmail.com', '7786807881', '2014-03-19');
insert into users
values('00002', 'Bennet', 'Abraham',
'bennetabraham2@gmail.com', '7786807882', '2014-03-19');
insert into users
values('00003', 'Green', 'Marjorie',
'greenmarjorie@hotmail.com', '7786807883', '2014-03-20');
insert into users
values('00004', 'Carson', 'Cheryl',
'carsoncheryl@hotmail.com', '7786807884', '2014-03-21');
insert into users
values('00005', 'Ringer', 'Albert',
'ringeralbert@yahoo.com', '7786807885', '2015-02-19');
insert into users
values('00006', 'Ringer', 'Anne',
'ringeranne@gmail.com', '7786807886', '2015-01-19');
insert into users
values('00007', 'DeFrance', 'Michel',
'defrancemichel@gmail.com', '7786807887', '2015-12-19');
insert into users
values('00008', 'Panteley', 'Sylvia',
'panteleysylvia@gmail.com', '7786807888', '2014-03-19');
insert into users
values('00009', 'McBadden', 'Heather',
'mcbaddenheather@gmail.com', '7786807889', '2015-03-19');
insert into users
values('00010', 'Stringer', 'Dirk',
'stringerdirk123@gmail.com', '7786807890', '2014-01-22');
insert into users
values('00011', 'Karsen', 'Livia',
'karsenlivia123@gmail.com', '7786807891', '2018-01-24');
insert into users
values('00012', 'MacFeather', 'Stearns',
'macfeatherstearns@gmail.com', '7786807892', '2018-01-26');
insert into users
values('00013', 'Dull', 'Ann',
'dullann@gmail.com', '7786807893', '2018-01-30');
insert into users
values('00014', 'Yokomoto', 'Akiko',
'yokomotoakiko233@yahoo.com', '7786807894', '2018-03-01');
insert into users
values('00015', 'Gringlesby', 'Burt',
'gringlesbyburt1@gmail.com', '7786807895', '2018-03-02');
insert into users
values('00016', 'White', 'Johnson',
'whitejohnson@gmail.com', '7786807896', '2018-03-03');
insert into users
values('00017', 'del Castillo', 'Innes',
'delcastilloinnes@gmail.com', '7786807897', '2018-03-04');
insert into users
values('00018', 'Hunter', 'Sheryl',
'huntersheryl@gmail.com', '7786807898', '2018-03-05');
insert into users
values('00019', 'Locksley', 'Chastity',
'locksleychastity@gmail.com', '7786807899', '2018-03-06');
insert into users
values('00020', 'Blotchet-Halls', 'Reginald',
'blotchethalls123@gmail.com', '7786807900', '2017-10-07');
insert into users
values('00021', 'Smith', 'Meander',
'smithmeander@gmail.com', '7786807901', '2017-07-02');
insert into users
values('00022', 'Natashia', 'Thoma',
'natashiathoma@gmail.com', '7786807902', '2016-04-02');
insert into users
values('00023', 'Susanne', 'Cusick',
'susannecusick@gmail.com', '7786807903', '2015-04-02');
insert into users
values('00024', 'Evia', 'Rideout',
'eviarideout@gmail.com', '7786807904', '2017-04-02');
insert into users
values('00025', 'Alfredia', 'Wiemer',
'alfrediawiemer@gmail.com', '7786807905', '2018-03-02');
insert into users
values('00026', 'Lianne', 'Gose',
'liannegose@gmail.com', '7786807906', '2017-05-02');
insert into users
values('00027', 'Chelsea', 'Rabideau',
'chelsearabideau@gmail.com', '7786807907', '2017-01-02');
insert into users
values('00028', 'Kiera', 'Asbill',
'kieraasbill@gmail.com', '7786807908', '2018-03-13');
insert into users
values('00029', 'Lara', 'Thigpen',
'larathigpen@gmail.com', '7786807909', '2016-04-22');
insert into users
values('00030', 'Lupita', 'Moffat',
'lupitamoffat@gmail.com', '7786807910', '2016-04-23');
insert into users
values ('00031','Carl', 'Denis', 'carl.denis@hotmail.com', '6049601404', '2015-06-12');
insert into users
values('00032','Allen', 'White', 'aw1990@ymail.com', '7783117657', '2015-06-01');
insert into users
values('00033', 'Jessica', 'Pam', 'jessie88@gmail.com', '60477712879', '2015-01-01');
insert into users
values('00034', 'Linda', 'Lee', 'linda.lee@gmail.com', '6047649283', '2015-01-09');
insert into users
values('00035','George', 'Kaiser', 'gkaiser23@outlook.com', '7789872323', '2015-01-19');
insert into users
values('00036','Lucy', 'Wong', 'c.wong@outlook.com', '6047658789', '2014-01-16');
insert into users
values('00037','Jamie', 'Graham', 'jamie.graham@yahoo.com', '6044457867', '2014-02-02');
insert into users
values('00038','Denis', 'Paige', 'denis34345@gmail.com', '6048993465', '2014-09-01');
insert into users
values('00039','Arya', 'Doust', 'aryaemail@ymail.com', '7789887839', '2014-08-05');
insert into users
values('00040', 'Robert', 'Zhang', 'rzhang@gmail.com', '6045567898', '2016-07-02');
insert into users
values('00041','Sandra', 'Koosh', 'sandrak97@gmail.com', '7786799757', '2016-07-15');
insert into users
values('00042','Tristan', 'Cruz', 'tristanc67@ymail.com', '6042341514', '2016-07-16');
insert into users
values('00043','Victor', 'Camino', 'v.camino@gmail.com', '7784578767', '2016-07-20');
insert into users
values('00044', 'Mariana', 'Fernanadez', 'mari.fernanadez@gmail.com', '6042334896', '2017-03-09');
insert into users
values('00045', 'Jasmine', 'Abadi', 'j.abadi34@gmail.com', '6045767585', '2016-12-30');
insert into users
values('00046', 'Andrea', 'Chavez', 'andrea.chavez@hotmail.com', '7783762668', '2017-08-13');
insert into users
values('00047', 'Renee', 'Moreau', 'rmoreau44@gmail.com', '7784769020', '2017-05-02');
insert into users
values('00048', 'Joon', 'Kim', 'joon.kim3452@hotmail.com', '6040891920', '2017-07-09');
insert into users
values('00049', 'Lola', 'May', 'lola.may1994@gmail.com', '7785053873', '2017-06-10');
insert into users
values('00050', 'Paula', 'Decius', 'paula.decius@hotmail.com', '7784007686', '2014-10-21');
insert into users
values('00051', 'Shari', 'Carlson',
'ShariCarlson@gmail.com', '7786808881', '2014-03-19');
insert into users
values('00052', 'Christiant', 'Allen',
'christiantAllen2@gmail.com', '7786808882', '2014-03-19');
insert into users
values('00053', 'Isabel', 'Patton',
'Isabelpatton@hotmail.com', '7786808883', '2014-03-20');
insert into users
values('00054', 'Leslie', 'Fox',
'lesliefox@hotmail.com', '7786808884', '2014-03-21');
insert into users
values('00055', 'Terry', 'Carter',
'terryCarter@yahoo.com', '7786808885', '2015-02-19');
insert into users
values('00056', 'Mitchell', 'Morris',
'mitchellmorri@gmail.com', '7786808886', '2015-01-19');
insert into users
values('00057', 'Barry', 'Bowen',
'barrybowen2l@gmail.com', '7786808887', '2015-12-19');
insert into users
values('00058', 'Amy', 'Kelly',
'panteleysylvia@gmail.com', '7786808888', '2014-03-19');
insert into users
values('00059', 'Sherri', 'Salazar',
'sherrisalazar4@gmail.com', '7786808889', '2015-03-19');
insert into users
values('00060', 'Chris', 'Page',
'christpage123@gmail.com', '7786808890', '2014-01-22');
insert into users
values('00061', 'Carl', 'Obrien',
'carlobrien3@gmail.com', '7786808891', '2018-01-24');
insert into users
values('00062', 'Enrique', 'Boone',
'enriqueboones@gmail.com', '7786808892', '2018-01-26');
insert into users
values('00063', 'April', 'Mcbride',
'dullann@gmail.com', '7786808893', '2018-01-30');
insert into users
values('00064', 'Tasha', 'Jefferson',
'tashajefferson233@yahoo.com', '7786808894', '2018-03-01');
insert into users
values('00065', 'Brett', 'Watkins',
'brettwatkins3@gmail.com', '7786808895', '2018-03-02');
insert into users
values('00066', 'Vicki', 'Holmes',
'vickiholmes4@gmail.com', '7786808896', '2018-03-03');
insert into users
values('00067', 'Blake', 'Fletcher',
'blakefletcher6@gmail.com', '7786808897', '2018-03-04');
insert into users
values('00068', 'Robyn', 'Haynes',
'robynhaynes6@gmail.com', '7786808898', '2018-03-05');
insert into users
values('00069', 'Regina', 'Barton',
'reginabarton134@gmail.com', '7786808899', '2018-03-06');
insert into users
values('00070', 'Laverne', 'Shelton',
'laverneshelton7@gmail.com', '7786808900', '2017-10-07');
insert into users
values('00071', 'Hugh', 'Massey',
'hughmassey71@gmail.com', '7786808901', '2017-07-02');
insert into users
values('00072', 'Armando', 'Walker',
'armandowalker71@gmail.com', '7786808902', '2016-04-02');
insert into users
values('00073', 'Rogelio', 'Simpson',
'rogeliosimpson73@gmail.com', '7786808903', '2015-04-02');
insert into users
values('00074', 'Christy', 'Frank',
'christyfrank74@gmail.com', '7786808904', '2017-04-02');
insert into users
values('00075', 'Douglas', 'Johnson',
'douglasjohnsonr@gmail.com', '7786808905', '2018-03-02');
insert into users
values('00076', 'Debbie', 'Rhodes',
'debbierhodes2@gmail.com', '7786808906', '2017-05-02');
insert into users
values('00077', 'Annie', 'Reyes',
'anniereyes8@gmail.com', '7786808907', '2017-01-02');
insert into users
values('00078', 'Elsie', 'Ramsey',
'elsierameys@gmail.com', '7786808908', '2018-03-13');
insert into users
values('00079', 'Barbara', 'Collins',
'lbababalons@gmail.com', '7786808909', '2016-04-22');
insert into users
values('00080', 'Marguerite', 'Flowers',
'margueriteflowerss@gmail.com', '7786808910', '2016-04-23');
insert into users
values ('00081','Thomas', 'Paul', 'thomaspaul@hotmail.com', '6049801404', '2015-06-12');
insert into users
values('00082','Ronnie', 'Mitchell', 'ronniemitchell@ymail.com', '7783118657', '2015-06-01');
insert into users
values('00083', 'Mattie', 'Sutton', 'jmattie88@gmail.com', '60477812879', '2015-01-01');
insert into users
values('00084', 'Roman', 'Wagner', 'romanwagner@gmail.com', '6048649283', '2015-01-09');
insert into users
values('00085','Eva', 'Cross', 'evacross23@outlook.com', '7789882323', '2015-01-19');
insert into users
values('00086','Lucy', 'Wong', 'lucywong2@outlook.com', '6047658819', '2014-11-16');
insert into users
values('00087','Louis', 'Moreno', 'louismerone@yahoo.com', '6044458867', '2014-02-02');
insert into users
values('00088','Johnnie', 'Diaz', 'jhonene34345@gmail.com', '6048993865', '2014-09-01');
insert into users
values('00089','Freddie', 'Logan', 'freddielogan@ymail.com', '7789888839', '2014-08-05');
insert into users
values('00090', 'Vanessa', 'Rios', 'vanessarios@gmail.com', '6045567888', '2016-07-02');
insert into users
values('00091','Emma', 'Briggs', 'emmabriggs@gmail.com', '7786899757', '2016-07-15');
insert into users
values('00092','May', 'Moody', 'maymoody2@ymail.com', '6042341584', '2016-07-16');
insert into users
values('00093','Marlene', 'Bennett', 'marlenebennett@gmail.com', '7784878767', '2016-07-20');
insert into users
values('00094', 'Marsha', 'Rice', 'marsharicez@gmail.com', '6042384896', '2017-03-09');
insert into users
values('00095', 'Cathy', 'Holland', 'cathyholland4@gmail.com', '6045767885', '2016-12-30');
insert into users
values('00096', 'Neal', 'Boyd', 'nealboyds@hotmail.com', '7783768668', '2017-08-13');
insert into users
values('00097', 'Connie', 'Carpenter', 'conniecarpenter44@gmail.com', '7784769820', '2017-05-02');
insert into users
values('00098', 'Joonjoon', 'Kim', 'joonjoonkim452@hotmail.com', '6040898921', '2017-01-09');
insert into users
values('00099', 'Lola', 'May', 'lola.may1996@gmail.com', '7785053884', '2017-06-20');
insert into users
values('00100', 'Paula', 'Decius', 'paula.decius123@hotmail.com', '7784087606', '2014-12-21');


--12--

insert into employees
values('00001', 'yes', '00001');
insert into employees
values('00002', 'yes', '00001');
insert into employees
values('00003', 'yes', '00001');
insert into employees
values('00004', 'yes', '00001');
insert into employees
values('00005', 'yes', '00001');
insert into employees
values('00006', 'yes', '00006');
insert into employees
values('00007', 'yes', '00006');
insert into employees
values('00008', 'yes', '00006');
insert into employees
values('00009', 'yes', '00006');
insert into employees
values('00010', 'yes', '00006');
insert into employees
values('00011', 'yes', '00011');
insert into employees
values('00012', 'yes', '00011');
insert into employees
values('00013', 'yes', '00011');
insert into employees
values('00014', 'yes', '00011');
insert into employees
values('00015', 'yes', '00015');
insert into employees
values('00016', 'no', '00015');
insert into employees
values('00017', 'no', '00015');
insert into employees
values('00018', 'no', '00015');
insert into employees
values('00019', 'no', '00015');
insert into employees
values('00020', 'no', '00015');
insert into employees
values('00021', 'no', '00015');
insert into employees
values('00022', 'no', '00015');
insert into employees
values('00023', 'no', '00015');
insert into employees
values('00024', 'no', '00015');
insert into employees
values('00025', 'no', '00015');
insert into employees
values('00026', 'no', '00015');
insert into employees
values('00027', 'no', '00001');
insert into employees
values('00028', 'no', '00001');
insert into employees
values('00029', 'no', '00015');


--13--

insert into volunteerOrients
values('2015-01-20', '10:30:00', '12:30:00', '00001');

insert into volunteerOrients
values('2015-06-22', '12:30:00', '14:30:00', '00001');

insert into volunteerOrients
values('2016-05-01', '11:30:00', '13:30:00', '00005');

insert into volunteerOrients
values('2016-05-02', '11:30:00', '13:30:00', '00005');

insert into volunteerOrients
values('2016-08-01', '11:30:00', '13:30:00', '00007');

insert into volunteerOrients
values('2016-05-11', '11:30:00', '13:30:00', '00005');

insert into volunteerOrients
values('2017-01-15', '12:30:00', '14:30:00', '00022');

insert into volunteerOrients
values('2017-05-05', '12:30:00', '14:30:00', '00022');

insert into volunteerOrients
values('2017-03-20', '12:30:00', '14:30:00', '00029');

insert into volunteerOrients
values('2017-08-31', '10:30:00', '12:30:00', '00022');

insert into volunteerOrients
values('2017-05-23', '12:30:00', '14:30:00', '00010');

insert into volunteerOrients
values('2017-07-20', '11:30:00', '13:30:00', '00008');

insert into volunteerOrients
values('2018-05-01', '11:30:00', '13:30:00', '00020');


--14--

insert into volunteers
values ('00030', '2015-6-12', '40', '2015-06-22', '12:30:00');
insert into volunteers
values ('00031', '2015-6-12', '40', '2015-06-22', '12:30:00');
insert into volunteers
values('00032', '2015-6-10', '20', '2015-06-22', '12:30:00');
insert into volunteers
values('00033', '2015-1-15', '35', '2015-01-20', '10:30:00');
insert into volunteers
values('00034', '2015-1-19', '25', '2015-01-20', '10:30:00');
insert into volunteers
values('00035', '2015-1-19', '20', '2015-01-20', '10:30:00');
insert into volunteers
values('00036', '2016-7-7', '20', '2016-08-01', '11:30:00');
insert into volunteers
values('00037', '2016-7-15', '30', '2016-08-01', '11:30:00');
insert into volunteers
values('00038', '2016-7-16', '30', '2016-08-01', '11:30:00');
insert into volunteers
values('00039', '2016-7-30', '10', '2016-08-01', '11:30:00');
insert into volunteers
values('00040', '2017-3-19', '30', '2017-03-20', '12:30:00');
insert into volunteers
values('00041', '2017-1-19', '0', '2017-03-20', '12:30:00');
insert into volunteers
values('00042', '2017-8-13', '30', '2017-08-31', '10:30:00');
insert into volunteers
values('00043', '2017-5-7', '25', '2017-05-23', '12:30:00');
insert into volunteers
values('00044', '2017-7-9', '35', '2017-07-20', '11:30:00');
insert into volunteers
values('00045', '2017-7-1', '0', '2017-07-20', '11:30:00');
insert into volunteers
values('00046', '2016-4-23','20','2016-05-01','11:30:00');
insert into volunteers
values('00047', '2016-4-26', '10', '2016-05-01', '11:30:00');
insert into volunteers 
values('00048', '2017-1-3', '15', '2017-01-15', '12:30:00');
insert into volunteers
values('00049', '2018-4-15', '0', '2018-05-01', '11:30:00');


--15--

insert into volunteerShifts
values('1','2016-06-06', 'yes', 'dismantle', '00030', '2016-06-01', 'yes', '00010');
insert into volunteerShifts
values('2', '2016-07-19', 'yes', 'RAM testing', '00030', '2016-07-10', 'yes', '00009');
insert into volunteerShifts
values('1', '2016-11-05', 'no', 'RAM testing', '00030', '2016-10-10', 'no', null);
insert into volunteerShifts
values('2', '2017-01-05', 'no', 'cleaning', '00030', '2017-01-09', 'no', null);
insert into volunteerShifts
values('2', '2017-06-05', 'no', 'dismantle', '00030', '2017-06-21', 'no', null);
insert into volunteerShifts
values('1', '2017-08-15', 'no', 'dismantle', '00030', '2017-08-22', 'no', null);
insert into volunteerShifts
values('2', '2017-09-12', 'no', 'cleaning', '00030', '2017-10-10', 'no', null);
insert into volunteerShifts
values('2', '2017-11-05', 'yes', 'hard drive install', '00030', '2017-10-10', 'yes', '00010');
insert into volunteerShifts
values('2', '2018-01-06', 'no', 'hard drive install', '00030', '2018-01-30','no', null);
insert into volunteerShifts
values('1', '2018-03-14', 'no', 'RAM testing', '00030', '2018-03-20','no', null);
insert into volunteerShifts
values('2', '2018-08-05', 'no', 'dismantle', '00030', '2018-01-10','no', null);
insert into volunteerShifts
values('1', '2018-05-06', 'no', 'hard drive install', '00030', '2016-03-10','no', null);
insert into volunteerShifts
values('2', '2018-04-20', 'no', 'cleaning', '00030', '2018-02-22','no', null);


insert into volunteerShifts
values('1','2015-07-16', 'no', 'dismantle', '00031', '2015-07-01', 'yes', '00010');
insert into volunteerShifts
values('1','2015-12-01', 'no', 'RAM testing', '00031', '2015-11-13', 'yes', '00010');
insert into volunteerShifts
values('1','2016-01-07', 'no', 'dismantle', '00031', '2015-12-01', 'no', null);
insert into volunteerShifts
values('2','2016-03-04', 'yes', 'dismantle', '00031', '2016-01-01', 'no', null);
insert into volunteerShifts
values('1','2016-10-04', 'yes', 'RAM testing', '00031', '2016-09-01', 'no', null);
insert into volunteerShifts
values('2','2017-12-20', 'no', 'dismantle', '00031', '2017-11-01', 'no', null);
insert into volunteerShifts
values('2','2017-08-24', 'no', 'dismantle', '00031', '2017-05-11', 'no', null);
insert into volunteerShifts
values('1','2017-01-14', 'yes', 'RAM testing', '00031', '2017-01-01', 'no', null);
insert into volunteerShifts
values('2','2018-10-04', 'no', 'hard drive install', '00031', '2018-01-01', 'yes', '00028');
insert into volunteerShifts
values('2','2018-05-04', 'yes', 'hard drive install', '00031', '2018-01-01', 'no', null);
insert into volunteerShifts
values('2','2018-04-04', 'yes', 'dismantle', '00031', '2018-02-01', 'no', null);


insert into volunteerShifts
values('1','2016-07-16', 'no', 'cleaning', '00032', '2016-07-01', 'no', null);
insert into volunteerShifts
values('2','2016-12-01', 'no', 'RAM testing', '00032', '2016-11-13', 'yes', '00001');
insert into volunteerShifts
values('2','2016-12-04', 'no', 'RAM testing', '00032', '2016-02-11', 'no', null);
insert into volunteerShifts
values('2','2017-11-20', 'no', 'cleaning', '00032', '2017-11-01', 'no', null);
insert into volunteerShifts
values('2','2017-12-10', 'no', 'RAM testing', '00032', '2017-07-01', 'no', null);
insert into volunteerShifts
values('2','2018-12-23', 'yes', 'hard drive install', '00032', '2018-01-01', 'no', null);
insert into volunteerShifts
values('2','2018-11-03', 'no', 'dismantle', '00032', '2018-02-01', 'no', null);
insert into volunteerShifts
values('1', '2018-09-09', 'yes', 'hard drive install', '00032', '2018-09-01', 'no', null);


insert into volunteerShifts
values('2','2015-02-03', 'yes', 'cleaning', '00033', '2015-02-01', 'no', null);
insert into volunteerShifts
values('2','2015-12-13', 'yes', 'dismantle', '00033', '2015-11-01', 'yes', '00002');
insert into volunteerShifts
values('2','2015-12-27', 'no', 'RAM testing', '00033', '2015-12-03', 'yes', '00004');
insert into volunteerShifts
values('1','2016-01-13', 'no', 'hard drive install', '00033', '2015-12-01', 'yes', '00003');
insert into volunteerShifts
values('1','2016-05-12', 'yes', 'cleaning', '00033', '2016-04-21', 'no', null);
insert into volunteerShifts
values('2','2016-12-16', 'no', 'dismantle', '00033', '2016-12-11', 'no', null);
insert into volunteerShifts
values('2','2016-11-09', 'no', 'dismantle', '00033', '2016-07-11', 'no', null);
insert into volunteerShifts
values('1','2016-02-23', 'no', 'cleaning', '00033', '2016-02-21', 'no', null);
insert into volunteerShifts
values('2','2018-01-03', 'yes', 'RAM testing', '00033', '2017-12-21', 'no', null);
insert into volunteerShifts
values('2','2018-12-24', 'no', 'dismantle', '00033', '2018-02-01', 'no', null);
insert into volunteerShifts
values('2','2018-08-28', 'yes', 'dismantle', '00033', '2018-03-14', 'no', null);
insert into volunteerShifts
values('1','2018-03-30', 'yes', 'RAM testing', '00033', '2018-03-28', 'no', null);
insert into volunteerShifts
values('2','2018-10-24', 'no', 'cleaning', '00033', '2018-03-21', 'no', null);


insert into volunteerShifts
values('1','2016-01-01', 'no', 'hard drive install', '00034', '2015-12-11', 'yes', '00005');
insert into volunteerShifts
values('1','2016-02-01', 'yes', 'cleaning', '00034', '2016-01-21', 'no', null);
insert into volunteerShifts
values('2','2016-03-01', 'no', 'dismantle', '00034', '2016-01-11', 'yes', '00005');
insert into volunteerShifts
values('2','2016-04-01', 'yes', 'hard drive install', '00034', '2016-04-01', 'no', null);
insert into volunteerShifts
values('1','2016-05-01', 'no', 'cleaning', '00034', '2016-02-21', 'no', null);
insert into volunteerShifts
values('1','2018-01-01', 'no', 'cleaning', '00034', '2017-12-25', 'no', null);
insert into volunteerShifts
values('1','2018-02-01', 'yes', 'dismantle', '00034', '2018-01-11', 'no', null);
insert into volunteerShifts
values('2','2018-03-01', 'no', 'dismantle', '00034', '2018-02-10', 'no', null);
insert into volunteerShifts
values('1','2018-04-01', 'yes', 'cleaning', '00034', '2018-03-20', 'no', null);
insert into volunteerShifts
values('1','2018-05-01', 'no', 'cleaning', '00034', '2018-03-21', 'no', null);
insert into volunteerShifts
values('2','2018-06-01', 'no', 'dismantle', '00034', '2018-05-23', 'no', null);
insert into volunteerShifts
values('1', '2017-12-01', 'yes', 'hard drive install', '00034', '2017-03-03', 'no',null);
insert into volunteerShifts
values('1','2017-01-01', 'no', 'cleaning', '00034', '2016-11-21', 'no', null);


insert into volunteerShifts
values('1','2015-12-12', 'no', 'hard drive install', '00035', '2015-12-11', 'yes', '00005');
insert into volunteerShifts
values('1','2015-02-01', 'yes', 'cleaning', '00035', '2015-01-21', 'no', null);
insert into volunteerShifts
values('2','2015-03-01', 'no', 'dismantle', '00035', '2015-01-11', 'yes', '00005');
insert into volunteerShifts
values('1','2015-05-01', 'no', 'cleaning', '00035', '2015-02-21', 'no', null);
insert into volunteerShifts
values('1','2017-02-01', 'yes', 'dismantle', '00035', '2017-01-11', 'no', null);
insert into volunteerShifts
values('2','2017-03-01', 'no', 'dismantle', '00035', '2017-02-10', 'no', null);
insert into volunteerShifts
values('1','2017-04-01', 'yes', 'cleaning', '00035', '2017-03-20', 'no', null);
insert into volunteerShifts
values('1','2017-05-01', 'no', 'cleaning', '00035', '2017-03-21', 'no', null);
insert into volunteerShifts
values('1', '2018-12-01', 'yes', 'hard drive install', '00035', '2018-03-03', 'no',null);


insert into volunteerShifts
values('1','2014-07-09', 'yes', 'dismantle', '00036', '2014-07-01','yes','00001');
insert into volunteerShifts
values('2','2014-10-09', 'no', 'dismantle', '00036', '2014-10-02','no',null);
insert into volunteerShifts
values('1','2014-12-19', 'yes', 'RAM testing', '00036', '2014-11-21','yes','00010');
insert into volunteerShifts
values('1','2014-11-18', 'yes', 'RAM testing', '00036', '2014-07-11','no',null);
insert into volunteerShifts
values('1','2014-12-31', 'yes', 'dismantle', '00036', '2014-12-16','no',null);
insert into volunteerShifts
values('1','2016-07-09', 'yes', 'dismantle', '00036', '2016-07-01','yes','00001');
insert into volunteerShifts
values('2','2016-10-09', 'no', 'dismantle', '00036', '2016-10-02','no',null);
insert into volunteerShifts
values('1','2016-12-19', 'yes', 'RAM testing', '00036', '2016-11-21','yes','00010');
insert into volunteerShifts
values('1','2016-11-18', 'yes', 'RAM testing', '00036', '2016-07-11','no',null);
insert into volunteerShifts
values('1','2016-12-31', 'yes', 'dismantle', '00036', '2016-12-16','no',null);
insert into volunteerShifts
values('1','2018-07-09', 'yes', 'dismantle', '00036', '2018-07-01','yes','00001');
insert into volunteerShifts
values('2','2018-10-09', 'no', 'dismantle', '00036', '2018-01-02','no',null);
insert into volunteerShifts
values('1','2018-12-19', 'yes', 'RAM testing', '00036', '2018-03-21','yes','00010');
insert into volunteerShifts
values('1','2018-11-18', 'yes', 'RAM testing', '00036', '2018-02-11','no',null);
insert into volunteerShifts
values('1','2018-12-31', 'yes', 'dismantle', '00036', '2018-02-16','no',null);


insert into volunteerShifts
values('1','2014-03-19', 'no', 'hard drive install', '00037', '2014-03-02','yes','00001');
insert into volunteerShifts
values('1','2014-11-09', 'yes', 'dismantle', '00037', '2014-10-02','yes','00010');
insert into volunteerShifts
values('1','2014-11-29', 'yes', 'RAM testing', '00037', '2014-11-21','yes','00010');
insert into volunteerShifts
values('1','2014-12-24', 'no', 'cleaning', '00037', '2014-07-21','no',null);
insert into volunteerShifts
values('2','2014-05-31', 'yes', 'cleaning', '00037', '2014-04-16','no',null);
insert into volunteerShifts
values('1','2015-07-09', 'yes', 'dismantle', '00037', '2015-07-01','yes','00001');
insert into volunteerShifts
values('2','2015-10-09', 'no', 'dismantle', '00037', '2015-10-02','no',null);
insert into volunteerShifts
values('1','2015-12-19', 'yes', 'RAM testing', '00037', '2015-11-21','yes','00010');
insert into volunteerShifts
values('1','2015-11-18', 'yes', 'RAM testing', '00037', '2015-02-11','no',null);
insert into volunteerShifts
values('1','2015-12-31', 'yes', 'dismantle', '00037', '2015-02-16','no',null);
insert into volunteerShifts
values('2', '2018-12-12', 'no', 'dismantle', '00037', '2018-03-17','no',null);


insert into volunteerShifts
values('2','2015-11-15', 'yes', 'cleaning', '00038', '2015-10-16','no',null);
insert into volunteerShifts
values('2','2015-10-15', 'yes', 'dismantle', '00038', '2015-09-16','yes','00020');
insert into volunteerShifts
values('1','2015-09-15', 'yes', 'cleaning', '00038', '2015-08-16','no',null);
insert into volunteerShifts
values('2','2015-08-15', 'yes', 'dismantle', '00038', '2015-07-16','no',null);
insert into volunteerShifts
values('1','2015-07-15', 'yes', 'RAM testing', '00038', '2015-06-16','yes','00001');
insert into volunteerShifts
values('2','2017-11-13', 'no', 'dismantle', '00038', '2015-10-11','no',null);
insert into volunteerShifts
values('1','2017-09-12', 'yes', 'cleaning', '00038', '2015-08-11','no',null);
insert into volunteerShifts
values('2','2017-05-15', 'no', 'cleaning', '00038', '2015-03-16','no',null);
insert into volunteerShifts
values('1','2017-01-15', 'no', 'RAM testing', '00038', '2017-01-10','no',null);
insert into volunteerShifts
values('2','2016-11-15', 'no', 'hard drive install', '00038', '2016-10-16','yes','00020');
insert into volunteerShifts
values('2','2016-01-15', 'no', 'hard drive install', '00038', '2015-10-16','no',null);


insert into volunteerShifts
values('2','2016-11-15', 'yes', 'cleaning', '00039', '2016-10-16','no',null);
insert into volunteerShifts
values('2','2016-10-15', 'yes', 'dismantle', '00039', '2016-09-16','yes','00020');
insert into volunteerShifts
values('1','2016-09-15', 'yes', 'cleaning', '00039', '2016-08-16','no',null);
insert into volunteerShifts
values('2','2016-08-15', 'yes', 'dismantle', '00039', '2016-07-16','no',null);
insert into volunteerShifts
values('1','2016-07-15', 'yes', 'RAM testing', '00039', '2016-06-16','yes','00001');
insert into volunteerShifts
values('2','2018-11-13', 'no', 'dismantle', '00039', '2017-10-11','no',null);
insert into volunteerShifts
values('1','2018-09-12', 'yes', 'cleaning', '00039', '2018-01-11','no',null);
insert into volunteerShifts
values('2','2018-05-15', 'no', 'cleaning', '00039', '2018-03-16','no',null);
insert into volunteerShifts
values('1','2018-01-15', 'no', 'RAM testing', '00039', '2018-03-10','no',null);
insert into volunteerShifts
values('2','2018-11-15', 'no', 'hard drive install', '00039', '2018-01-16','yes','00020');
insert into volunteerShifts
values('2','2017-01-15', 'no', 'hard drive install', '00039', '2016-10-16','no',null);

insert into volunteerShifts
values('1','2017-03-23', 'yes', 'RAM testing', '00040', '2017-01-21','yes','00015');
insert into volunteerShifts
values('2','2017-06-25','no','hard drive install','00040','2017-06-05','yes','00014');
insert into volunteerShifts
values('1','2017-08-17','no','dismantle','00040','2017-08-01','yes','00013');
insert into volunteerShifts
values('1','2017-10-13','no','hard drive install','00040','2017-06-13','no',null);
insert into volunteerShifts
values('1','2017-11-12','yes','hard drive install','00040','2017-07-13','no',null);
insert into volunteerShifts
values('1','2017-12-13','no','dismantle','00040','2017-11-11','no',null);
insert into volunteerShifts
values('1','2018-03-23', 'yes', 'RAM testing', '00040', '2018-01-21','no',null);
insert into volunteerShifts
values('2','2018-06-25','no','hard drive install','00040','2018-02-05','no',null);
insert into volunteerShifts
values('1','2018-08-17','no','dismantle','00040','2017-08-01','no',null);
insert into volunteerShifts
values('1','2018-10-13','no','hard drive install','00040','2018-02-13','no',null);
insert into volunteerShifts
values('1','2018-11-12','yes','hard drive install','00040','2018-01-13','no',null);
insert into volunteerShifts
values('1','2018-12-13','no','dismantle','00040','2017-11-11','no',null);


--16--

insert into specialEvents
values ('2013-06-30', 
        'Mid Year Party', 30, 
        '18:00:00', 
        '22:00:00', 
        50);

insert into specialEvents
values ('2013-12-03', 
        'Winter Party', 20, 
        '18:00:00', 
        '22:00:00', 
        50);

insert into specialEvents
values ('2014-02-03', 
        'Spring Party', 2.5,
        '18:00:00', 
        '22:00:00', 
        100);

insert into specialEvents
values ('2014-07-30', 
        'Mid Year Party', 10,
        '18:00:00', 
        '22:00:00', 
        50);

insert into specialEvents
values ('2014-10-30',
        'Halloween Party', 12.5,
        '18:00:00',
        '22:00:00',
        30);

insert into specialEvents
values ('2014-11-30', 
        'Winter Party', 5,
        '18:00:00',
        '22:00:00', 
        70);

insert into specialEvents
values ('2015-03-30', 
        'Spring Party', 3,
        '18:00:00',
        '22:00:00',
        50);

insert into specialEvents
values ('2015-07-30',
        'Mid Year Party', 10,
        '18:00:00', 
        '22:00:00',  
        20);

insert into specialEvents
values ('2015-10-29',
        'Halloween Party', 40,
        '18:00:00',
        '21:00:00',
        30);

insert into specialEvents
values ('2015/11/30', 
        'Winter Party', 10,
        '18:00:00',
        '22:00:00', 
        60);

insert into specialEvents
values ('2016/01/30', 
        'Spring Party', 30, 
        '18:00:00',  
        '22:00:00', 
        60);

insert into specialEvents
values ('2016/07/15', 
        'Mid Year Party', 50, 
        '18:00:00', 
        '22:00:00', 
        100);

insert into specialEvents
values ('2016-10-31',
        'Halloween Party', 5,
        '18:00:00',
        '21:00:00',
        40);

insert into specialEvents
values ('2016-12-06',
        'Winter Party', 20,
        '18:00:00',
        '22:00:00',
        25);


insert into specialEvents
values ('2017-02-03',
        'Spring Party', 50,
        '18:00:00',
        '22:00:00',
        30);

insert into specialEvents
values ('2017-06-30',
        'Mid Year Party', 30,
        '18:00:00',
        '22:00:00',
        40);

insert into specialEvents
values ('2017-10-31',
        'Halloween Party', 50,
        '18:00:00',
        '22:00:00',
        30);

insert into specialEvents
values ('2018-02-05',
        'Spring Party', 50,
        '18:00:00',
        '22:00:00',
        30);

insert into specialEvents
values ('2018-06-20',
        'Mid Year Party', 30,
        '18:00:00',
        '22:00:00',
        40);

insert into specialEvents
values ('2018-10-31',
        'Halloween Party', 50,
        '18:00:00',
        '22:00:00',
        30);

insert into specialEvents
values ('2018-12-06',
        'Winter Party', 20,
        '18:00:00',
        '22:00:00',
        25);


--17--

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-01', '00001');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-01', '00002');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00003');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00004');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00005');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00006');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00007');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-06', '00008');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00009');

insert into reserveEvents
    values('2013-06-30', '18:00:00', '2013-06-05', '00010');


insert into reserveEvents
    values('2013-12-03', '18:00:00', '2013-09-25', '00001');

insert into reserveEvents
    values('2013-12-03', '18:00:00', '2013-10-25', '00012');

insert into reserveEvents
    values('2013-12-03', '18:00:00', '2013-10-25', '00003');

insert into reserveEvents
    values('2013-12-03', '18:00:00', '2013-10-25', '00014');


insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-01', '00001');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-02', '00022');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-03', '00023');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-03', '00024');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-01', '00025');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-01', '00026');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-01', '00027');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-03', '00008');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-01', '00009');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-03', '00030');

insert into reserveEvents
    values('2014-02-03', '18:00:00', '2014-01-03', '00031');


insert into reserveEvents
    values('2014-07-30', '18:00:00', '2014-05-21', '00035');

insert into reserveEvents
    values('2014-07-30', '18:00:00', '2014-05-21', '00036');

insert into reserveEvents
    values('2014-07-30', '18:00:00', '2014-05-21', '00037');


insert into reserveEvents
    values('2014-11-30', '18:00:00', '2014-10-21', '00040');

insert into reserveEvents
    values('2014-11-30', '18:00:00', '2014-10-21', '00041');

insert into reserveEvents
    values('2014-11-30', '18:00:00', '2014-10-21', '00042');

insert into reserveEvents
    values('2014-11-30', '18:00:00', '2014-10-21', '00043');


insert into reserveEvents
    values('2015-03-30', '18:00:00', '2015-02-09', '00046');

insert into reserveEvents
    values('2015-03-30', '18:00:00', '2015-02-09', '00047');

insert into reserveEvents
    values('2015-03-30', '18:00:00', '2015-02-09', '00048');


insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00010');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00001');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00052');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00053');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00054');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00055');

insert into reserveEvents
    values('2015-07-30', '18:00:00', '2015-06-19', '00056');


insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00060');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00001');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-09-29', '00062');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00003');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00064');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-21', '00065');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00066');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-24', '00007');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00068');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-25', '00069');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00070');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-24', '00071');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00072');

insert into reserveEvents
    values('2015-11-30', '18:00:00', '2015-10-29', '00073');


insert into reserveEvents
    values('2016-01-30', '18:00:00', '2016-01-03', '00080');

insert into reserveEvents
    values('2016-01-30', '18:00:00', '2016-01-03', '00001');


insert into reserveEvents
    values('2016-07-15', '18:00:00', '2016-06-16', '00006');

insert into reserveEvents
    values('2016-07-15', '18:00:00', '2016-06-16', '00007');

insert into reserveEvents
    values('2016-07-15', '18:00:00', '2016-06-16', '00008');


insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00001');

insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00002');

insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00003');

insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00004');

insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00005');

insert into reserveEvents
    values('2018-06-20', '18:00:00', '2018-01-16', '00006');


insert into reserveEvents
    values('2018-12-06', '18:00:00', '2018-01-18', '00001');

