create table tbl_student(
    sno varchar2(4) primary key,
    sname varchar2(10) not null,
    syear number(1) not null check (syear between 1 and 4),
    gender char(1) not null check (gender in ('M', 'F')),
    major varchar2(10) not null,
    score number(3) default 0 not null check (score between 0 and 100)
);