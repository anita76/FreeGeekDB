create table volunteers(
    id int not null,
    orientSignUpDate date,
    points int,
    orientDate date,
    orientTime time,
    primary key(id),
    foreign key(id) references user(id)
                        on update no action
                        on delete cascade
    foreign key(orientDate, orientTime) references volunteerOrient
                        on update no action
                        on delete set null
);
