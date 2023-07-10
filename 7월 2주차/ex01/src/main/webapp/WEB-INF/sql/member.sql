create table tbl_member(
    userid varchar2(50) not null,
    userpw varchar2(50) not null,
    username varchar2(50) not null,
    email varchar2(100),
    regdate timestamp default sysdate,
    updatedate timestamp default sysdate,
    primary key (userid)
);

select * from tbl_member;