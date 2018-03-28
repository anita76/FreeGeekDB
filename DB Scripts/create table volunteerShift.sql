create table volunteerShift(
    station number(1),
    shiftDate date,
    morningShift char(3),
    shiftType char(30),
    volunteerID number(5),
    signupDate date,
    trainingReq char(30),
    instructEmpID number(5),
    primary key(station, shiftDate, morningShift, shiftType),
    foreign key(volunteerID) references volunteers(id)
                            on delete cascade,
    foreign key(instructEmpID) references employees(id)
                            on delete set null
);

