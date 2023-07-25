create table tbl_reply(
    rno number primary key,
    bno number references tbl_board(bno),
    replytext varchar2(1000) not null,
    replyer varchar2(50) not null,
    regdate timestamp default sysdate,
    updatedate timestamp 
);

create sequence seq_reply_rno;