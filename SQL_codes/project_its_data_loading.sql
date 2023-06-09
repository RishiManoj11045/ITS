INSERT INTO admintable1(name,iith_id) Values("Anudeep","AD1")  ;
INSERT INTO admintable1(name,iith_id) Values("Pranav","AD2")  ;
INSERT INTO admintable1(name,iith_id) Values("Sujith","AD3")  ;
INSERT INTO admintable1(name,iith_id) Values("Srinith","AD4")  ;
INSERT INTO admintable2(iith_id,iith_mailid,password ,contact_number) Values("AD1","ad1@iith.ac.in","1234","7396512")  ;
INSERT INTO admintable2(iith_id,iith_mailid,password ,contact_number) Values("AD2","ad2@iith.ac.in","1234","9177115")  ;
INSERT INTO admintable2(iith_id,iith_mailid,password ,contact_number) Values("AD3","ad3@iith.ac.in","1234","9992332")  ;
INSERT INTO admintable2(iith_id,iith_mailid,password ,contact_number) Values("AD4","ad4@iith.ac.in","1234","8422307")  ;


INSERT INTO faculty(name,iith_id,iith_mail,password,contact_number) values('Anudeep','CS1','CS1@iith.ac.in','1234','76512369');
INSERT INTO faculty(name,iith_id,iith_mail,password,contact_number) values('Pranav','CS2','CS2@iith.ac.in','1234','9912332');
INSERT INTO faculty(name,iith_id,iith_mail,password,contact_number) values('Dev','CS3','CS3@iith.ac.in','1234','9110095');
INSERT INTO faculty(name,iith_id,iith_mail,password,contact_number) values('Vish','CS4','CS4@iith.ac.in','1234','8465430');
INSERT INTO faculty(name,iith_id,iith_mail,password,contact_number) values('Sujith','CS5','CS5@iith.ac.in','1234','8822307');

insert into seller(name,company,mail_id,contact_number,item) values("Pranav","A","pranav@gmail.com","910095","Lights");

INSERT INTO fields(field_name) values("Lab");
INSERT INTO fields(field_name) values("Classroom");
INSERT INTO fields(field_name) values("Office");
INSERT INTO fields(field_name) values("Library");

Insert into laboperationsmanagertable(iith_id,name,fieldofoperations,appointedby,iith_mailid,password,contactnumber) values("OMLB3","Pranav","Lab","AD1","omlb3@iith.ac.in","1234","99332") ;
Insert into classoperationsmanagertable(iith_id,name,fieldofoperations,appointedby,iith_mailid,password,contactnumber) values("OMCL1","Anu","Class","AD1","omcl1@iith.ac.in","1234","9110095") ;
Insert into officeoperationsmanagertable(iith_id,name,fieldofoperations,appointedby,iith_mailid,password,contactnumber) values("OMOF1","Anu","Office","AD1","omof1@iith.ac.in","1234","9110095") ;
Insert into libraryoperationsmanagertable(iith_id,name,fieldofoperations,appointedby,iith_mailid,password,contactnumber) values("OMLR1","Anu","Library","AD1","omlr1@iith.ac.in","1234","9010095") ;

Insert into labsupervisortable(iith_id,name,fieldofoperations,objectmaintained,appointedby,iith_mailid,password,contactnumber) values("SVLB1","Anu","Lab","Bench","AD1","svlb1@iith.ac.in","1234","91795") ;
Insert into classsupervisortable(iith_id,name,fieldofoperations,objectmaintained,appointedby,iith_mailid,password,contactnumber) values("SVCL1","Anu","Class","Bench","AD1","svcl1@iith.ac.in","1234","7110095") ;
Insert into officesupervisortable(iith_id,name,fieldofoperations,objectmaintained,appointedby,iith_mailid,password,contactnumber) values("SVOF1","Anu","Office","Bench","AD1","svof1@iith.ac.in","1234","917095") ;
Insert into librarysupervisortable(iith_id,name,fieldofoperations,objectmaintained,appointedby,iith_mailid,password,contactnumber) values("SVLR1","Anu","Library","Bench","AD1","svlr1@iith.ac.in","1234","910095") ;

