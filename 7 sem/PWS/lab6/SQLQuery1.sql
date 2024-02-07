use Students;

create table student(
id int identity(1,1) primary key,
name nvarchar(50)
)

create table note(
id int identity(1,1) primary key,
stud_id int references student(id),
subject nvarchar(20),
note int
)
