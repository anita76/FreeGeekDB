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

