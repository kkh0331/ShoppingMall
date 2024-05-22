CREATE TABLE IF NOT EXISTS member(
    id int unsigned not null auto_increment primary key,
    user_id varchar(31) not null,
	pw varchar(31) not null,
    name varchar(31) not null,
	email varchar(31) not null,
    contact varchar(13) not null comment "전화번호"
);