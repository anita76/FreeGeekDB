create table users (
    id  number(5) not null,
    firstName varchar2(30),
    lastName varchar2(30),
    email varchar2(50) unique,
    phone varchar2(20),
    membershipStartData date,
    primary key (id),
    CONSTRAINT mailchck CHECK (REGEXP_LIKE (email, '^(\S+)\@(\S+)\.(\S+)$'))
);
