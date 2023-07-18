create table tbl_board(
    bno number primary key,
    title varchar2(200) not null,
    content varchar2(4000),
    writer varchar2(50) not null,
    regdate timestamp default sysdate,
    viewcnt number default 0
);

select * from tbl_board;