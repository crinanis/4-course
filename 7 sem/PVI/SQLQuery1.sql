create database wsr;
use wsr;
drop table wsref;
drop table wsrefcomment;

CREATE TABLE WSREF
(
    id int identity(1,1) primary key,
    url varchar(100),
    description varchar(100),
    minus int,
    plus int,
)

CREATE TABLE WSREFCOMMENT
(
    id int NOT NULL identity(1,1) primary key,
    wsref_id int foreign key references WSREF(id) on delete cascade,
    session_id varchar(50),
    stamp date,
    comment varchar(200),
	usr varchar(100),
)

go
CREATE OR ALTER PROCEDURE AddWSREF
    @url varchar(100),
    @description varchar(100),
    @minus int,
    @plus int
AS
BEGIN
    INSERT INTO WSREF (url, description, minus, plus)
    VALUES (@url, @description, @minus, @plus);
END;

go
CREATE OR ALTER PROCEDURE AddWSREFComment
    @wsref_id int,
    @session_id varchar(50),
    @stamp date,
    @comment varchar(200),
	@usr varchar(100)
AS
BEGIN
    INSERT INTO WSREFCOMMENT (wsref_id, session_id, stamp, comment, usr)
    VALUES (@wsref_id, @session_id, @stamp, @comment, @usr);
END;
