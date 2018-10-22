insert into course_details (id, name, created_date, updated_date) 
	values (10001, 'Hibernate JPA', sysdate, sysdate);
insert into course_details (id, name, created_date, updated_date) 
	values (10002, 'Spring JPA', sysdate, sysdate);
insert into course_details (id, name, created_date, updated_date)
	values (10003, 'Springboot JPA', sysdate, sysdate);
insert into course_details (id, name, created_date, updated_date)
	values (10004, 'SpringbootHibernate JPA', sysdate, sysdate);	
insert into course_details (id, name, created_date, updated_date)
	values (10005, 'Angular JS', sysdate, sysdate);	
insert into course_details (id, name, created_date, updated_date)
	values (10006, 'JavaScript', sysdate, sysdate);

insert into passport (id, name, created_date, updated_date) 
	values (40001, '545445454466', sysdate, sysdate);
insert into passport (id, name, created_date, updated_date) 
	values (40002, '25454545454', sysdate, sysdate);
insert into passport (id, name, created_date, updated_date) 
	values (40003, '878451215454', sysdate, sysdate);
		
insert into student (id, name, passport_id, created_date, updated_date) 
	values (20001, 'Ranga', 40001, sysdate, sysdate);
insert into student (id, name, passport_id, created_date, updated_date) 
	values (20002, 'Adam', 40002, sysdate, sysdate);
insert into student (id, name, passport_id, created_date, updated_date) 
	values (20003, 'Jane',40003, sysdate, sysdate);		
		
insert into review (id, rating ,description, course_id ,created_date, updated_date) 
	values (50001, '5' , 'Great Course',10001 ,sysdate, sysdate);
insert into review (id, rating, description, course_id, created_date, updated_date) 
	values (50002, '4' , 'Wondefull', 10001, sysdate, sysdate);
insert into review (id, rating ,description, course_id, created_date, updated_date) 
	values (50003, '5' , 'Goood Course', 10003, sysdate, sysdate);
	
	
insert into student_course (student_id, course_id) 
	values (20001, 10001);
insert into student_course (student_id, course_id) 
	values (20001, 10002);
insert into student_course (student_id, course_id) 
	values (20001, 10003);	
	
insert into student_course (student_id, course_id) 
	values (20002, 10001);
	
insert into student_course (student_id, course_id) 
	values (20003, 10001);
	
insert into student_course (student_id, course_id) 
	values (20002, 10004);
	
