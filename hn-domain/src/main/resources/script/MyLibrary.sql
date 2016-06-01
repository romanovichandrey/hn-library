create database mylibrary;
use mylibrary;
create table T_USER (
F_ID bigint(20) not null auto_increment,
F_FIRSTNAME varchar(50) not null,
F_LASTNAME varchar(50) not null,
F_TELEPHONE varchar(15) not null,
F_EMAIL varchar(60) not null,
F_LOGIN varchar(50) not null,
F_PASSWORD varchar(30) not null,
primary key (F_ID)
)
engine=InnoDB default charset=utf8 collate=utf8_unicode_ci;

create table T_ADRESS (
F_ID bigint(20) not null auto_increment,
F_STREET varchar(45) not null,
F_CITY varchar(45) not null,
F_STATE varchar(45) null default null,
F_COUNTRY varchar(45) not null,
primary key (F_ID),
constraint FKUSER foreign key (F_ID) references t_user(F_ID)
)
engine=InnoDB default charset=utf8;

create table T_CATEGORY (
F_ID bigint(10) not null auto_increment,
F_NAME varchar(100) not null,
primary key (F_ID)
)
engine=InnoDB default charset=utf8 collate=utf8_unicode_ci;

create table T_BOOK (
F_ID bigint(20) not null auto_increment,
F_NAME varchar(100) not null,
F_DESCRIPTION varchar(255) not null,
F_YEARPUBLISHING varchar (45) not null,
F_CATEGORY_ID bigint(10) null default null,
F_USER_ID bigint(20) null default null,
primary key (F_ID),
index FK_CAT (F_CATEGORY_ID),
constraint FK_CAT foreign key (F_CATEGORY_ID) references t_category (F_ID),
index FK_USER (F_USER_ID),
constraint FK_USER foreign key (F_USER_ID) references t_user (F_ID)
)
engine=InnoDB default charset=utf8 collate=utf8_unicode_ci;

create table T_AUTOR (
F_ID bigint(10) not null auto_increment,
F_FIRSTNAME varchar(60) not null,
F_LASTNAME varchar(60) not null,
primary key (F_ID)
)
engine=InnoDB default charset=utf8 collate=utf8_unicode_ci;

create table BOOK_AUTOR (
BOOK_ID bigint(20) not null,
AUTOR_ID bigint(20) not null,
primary key (BOOK_ID, AUTOR_ID),
index FK_AUTOR (AUTOR_ID),
constraint FK_BOOK foreign key (BOOK_ID) references t_book (F_ID),
constraint FK_AUTOR foreign key (AUTOR_ID) references t_autor (F_ID)
)
engine=InnoDB default charset=utf8 collate=utf8_unicode_ci;

CREATE TABLE T_USERPROFILE (
F_ID bigint(20) not null,
F_USER_ID bigint(20) not null,
F_ROLE VARCHAR(10) not null,

)