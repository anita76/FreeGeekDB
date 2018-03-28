create table volunteers(
    id int not null,
    orientSignUpDate date,
    points int,
    orientDate date,
    orientTime date,
    CONSTRAINT vid primary key(id),
    CONSTRAINT vlid foreign key(id) references user(id)
                        on update no action
                        on delete cascade
    CONSTRAINT oTime foreign key(orientDate, orientTime) references volunteerOrient
                        on update no action
                        on delete set null
);
