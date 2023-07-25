create table tbl_user(
    u_id varchar2(50) primary key,
    u_pw varchar2(50) not null,
    u_name varchar2(100) not null,
    u_point number default 0 not null
);

create table tbl_message(
    m_id number primary key,
    targetid varchar2(50) references tbl_user(u_id),
    sender varchar2(50) references tbl_user(u_id),
    message varchar2(500) not null,
    opendate timestamp,
    senddate timestamp default sysdate
);

create sequence seq_message_id;