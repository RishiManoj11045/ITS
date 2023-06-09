use project_its ;

-- Resetting the admin info 
alter table admintable1 auto_increment= 1 ;
alter table admintable2 auto_increment= 1 ;
delete from admintable1 ;
delete from admintable2 ;

-- Resetting the faculty info
alter table faculty auto_increment = 1;
delete from faculty ;

-- Resetting Seller info
alter table seller auto_increment = 1;
delete from seller ;

-- Resetting operationsmanager tables 
alter table classoperationsmanagertable auto_increment= 1 ;
alter table libraryoperationsmanagertable auto_increment= 1 ;
alter table officeoperationsmanagertable auto_increment= 1 ;
alter table laboperationsmanagertable auto_increment= 1 ;
delete from classoperationsmanagertable ;
delete from libraryoperationsmanagertable ;
delete from officeoperationsmanagertable ;
delete from laboperationsmanagertable ;

-- Resetting supervisors tables
alter table classsupervisortable auto_increment= 1 ;
alter table labsupervisortable auto_increment= 1 ;
alter table librarysupervisortable auto_increment= 1 ;
alter table officesupervisortable auto_increment= 1 ;
delete from classsupervisortable ;
delete from labsupervisortable ;
delete from librarysupervisortable ;
delete from officesupervisortable ;

-- Resetting item list tables 
alter table classroomitemlist auto_increment= 1 ;
alter table labitemlist auto_increment= 1 ;
alter table libraryitemlist auto_increment= 1 ;
alter table officeitemlist auto_increment= 1 ;
delete from classroomitemlist ;
delete from labitemlist ;
delete from libraryitemlist ;
delete from officeitemlist ;

-- Resetting objectclasses tables 
alter table classroomobjectclasses auto_increment= 1 ;
alter table labobjectclasses auto_increment= 1 ;
alter table libraryobjectclasses auto_increment= 1 ;
alter table officeobjectclasses auto_increment= 1 ;
delete from classroomobjectclasses ;
delete from labobjectclasses ;
delete from libraryobjectclasses ;
delete from officeobjectclasses ;






 










