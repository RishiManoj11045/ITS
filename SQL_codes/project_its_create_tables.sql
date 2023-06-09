use project_its ;

-- Tables for storing admin info 
create table admintable1 (
	id int auto_increment primary key ,
    name varchar(40),
    iith_id VARCHAR(40) unique 
);

create table admintable2(
	iith_id VARCHAR(40) primary key ,
    iith_mailid VARCHAR(40),
    password VARCHAR(40) ,
    contact_number VARCHAR(10) 
);

-- Table for storing faculty info
create table faculty(
	id int primary key auto_increment,
    name Varchar(40),
    iith_id Varchar(10),
    iith_mail varchar(20),
    password varchar(20),
    contact_number varchar(10) 
);

-- Table for storing seller info
create table seller(
	id int auto_increment primary key ,
	name Varchar(40),
    company varchar(40),
    mail_id varchar(40),
    contact_number varchaR(10),
    item varchar(20) 
);

-- Table for storing the different fields 
create table fields(
	id int auto_increment primary key ,
    field_name varchar(10)
);

-- Tables for storing info about types of Object classes in a field
create table labobjectclasses(
		id int primary key auto_increment ,
        objecttype varchar(80)
);

create table officeobjectclasses(
		id int primary key auto_increment ,
        objecttype varchar(80)
);

create table classroomobjectclasses(
		id int primary key auto_increment ,
        objecttype varchar(80)
);

create table libraryobjectclasses(
		id int primary key auto_increment ,
        objecttype varchar(80)
);

-- Tables for storing info of supervisors
create table labsupervisortable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
        objectmaintained varchar(30),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);

create table classsupervisortable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
         objectmaintained varchar(30),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);
create table officesupervisortable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
         objectmaintained varchar(30),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);
create table librarysupervisortable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
         objectmaintained varchar(30),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);

-- Tables for storing info of operation managers
create table laboperationsmanagertable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);

create table classoperationsmanagertable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);
create table officeoperationsmanagertable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);
create table libraryoperationsmanagertable(
		id int primary key auto_increment ,
        iith_id varchar(15) ,
        name varchar(40) ,
        fieldofoperations varchar(20),
        appointedby varchar(20) ,
        iith_mailid varchar(30) ,
        password varchar(40) ,
        contactnumber varchar(10)
);


-- Tables for storing items info in a field 
create table labitemlist(
				id int primary key auto_increment ,
				iith_id varchar(30) ,
				type varchar(30) ,
				company varchar(40) ,
				company_id varchar(40),
				price varchar(10),
				seller varchar(20),
				buyer varchar(20),
				supervisor varchar(20),
				currentlocation varchar(20),
				status varchar(15),
				dateofbuying varchar(15),
				fieldofobject varchar(20)
);
create table classroomitemlist(
				id int primary key auto_increment ,
				iith_id varchar(30) ,
				type varchar(30) ,
				company varchar(40) ,
				company_id varchar(40),
				price varchar(10),
				seller varchar(20),
				buyer varchar(20),
				supervisor varchar(20),
				currentlocation varchar(20),
				status varchar(15),
				dateofbuying varchar(15),
				fieldofobject varchar(20)
);
create table officeitemlist(
				id int primary key auto_increment ,
				iith_id varchar(30) ,
				type varchar(30) ,
				company varchar(40) ,
				company_id varchar(40),
				price varchar(10),
				seller varchar(20),
				buyer varchar(20),
				supervisor varchar(20),
				currentlocation varchar(20),
				status varchar(15),
				dateofbuying varchar(15),
				fieldofobject varchar(20)
);
create table libraryitemlist(
				id int primary key auto_increment ,
				iith_id varchar(30) ,
				type varchar(30) ,
				company varchar(40) ,
				company_id varchar(40),
				price varchar(10),
				seller varchar(20),
				buyer varchar(20),
				supervisor varchar(20),
				currentlocation varchar(20),
				status varchar(15),
				dateofbuying varchar(15),
				fieldofobject varchar(20)
);


