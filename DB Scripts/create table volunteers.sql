create table volunteers(
    id int not null,
    orientSignUpDate date,
    points Number(5),
    orientDate date,
    orientTime date,
    primary key(id),
    foreign key(id) references users(id)
                        on delete cascade,
    foreign key(orientDate, orientTime) references volunteerOrient(orientDate, orientTime)
                        on delete set null
);

