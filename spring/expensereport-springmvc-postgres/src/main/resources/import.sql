insert into role(roleid,rolename) values(nextval('hibernate_sequence'),'ROLE_USER');
insert into role(roleid,rolename) values(nextval('hibernate_sequence'),'ROLE_MANAGER');
insert into users(userid,emailid,enabled,password,username,role_roleid) values(nextval('hibernate_sequence'),'admin@expense.com',true,'admin','admin',2);
insert into expenseType(id,name) values(nextval('hibernate_sequence'),'MEDICAL');
insert into expenseType(id,name) values(nextval('hibernate_sequence'),'TRAVEL');
insert into expenseType(id,name) values(nextval('hibernate_sequence'),'TELEPHONE');
insert into expenseType(id,name) values(nextval('hibernate_sequence'),'GYM');
insert into expenseType(id,name) values(nextval('hibernate_sequence'),'EDUCATION');