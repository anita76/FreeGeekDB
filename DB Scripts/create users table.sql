create table users (
    id  int not null,
    firstName char(30) not null,
    lastName char(30) not null,
    email char(50) unique,
    phone# number(10),
    membershipStartData date,
    primary key (id)
);
