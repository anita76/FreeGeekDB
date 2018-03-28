create table volunteerShifts(
    station int,
    shiftDate date,
    morningShift char(3),
    shiftType char(30),
    volunteerID int,
    signupDate date,
    trainingReq char(30),
    instructEmpID int,
    primary key(station, shiftDate, morningShift, shiftType),
    foreign key(volunteerID) references volunteers(id)
                            on delete cascade,
    foreign key(instructEmpID) references employees(id)
                            on delete set null
);
