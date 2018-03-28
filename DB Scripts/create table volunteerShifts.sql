create table volunteerShifts(
    station int,
    shiftDate date,
    morningShift char(3),
    shifType char(30),
    volunteerID int,
    signupDate date,
    trainingReq char(30),
    instructEmpID int,
    CONSTRAINT vsID primary key(station, shiftDate, morningShift, shiftType),
    CONSTRAINT vID foreign key(volunteerID) references volunteers(id)
                            on update no action
                            on delete cascade,
    CONSTRAINT iEmpID foreign key(instructEmpID) references employees(id)
                            on update no action
                            on delete set null
);
