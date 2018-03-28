create table volunteers(
    id int not null,
    orientSignUpDate char(20),
    points Number(5),
    orientDate char(20),
    orientTime char(20),
    primary key(id),
    foreign key(id) references users(id)
                        on delete cascade,
    foreign key(orientDate, orientTime) references volunteerOrient(orientDate, orientTime)
                        on delete set null
);

