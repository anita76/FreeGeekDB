create table volunteerShifts(
    station int,
    shiftDate date,
    morningShift char(3),
    shifType char(30),
    volunteerID int,
    signupDate date,
    trainingReq char(30),
    instructEmpID int,
    primary key(station, shiftDate, morningShift, shiftType),
    foreign key(volunteerID) references volunteers(id)
                            on update no action
                            on delete cascade,
    foreign key(instructEmpID) references employees(id)
                            on update no action
                            on delete set null
);
