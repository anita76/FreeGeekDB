create table users (
    id  number(5) not null,
    firstName char(30) not null,
    lastName char(30) not null,
    email char(50) unique,
    phone# number(10),
    membershipStartData date,
    primary key (id)
);
