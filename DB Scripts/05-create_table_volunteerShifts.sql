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

