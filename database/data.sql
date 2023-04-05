
insert into user(user_id,username,name) values("1b80114c","fred","Fred");
insert into user(user_id,username,name) values("cf66dae3","wilma","Wilma");
insert into user(user_id,username,name) values("a8b9800d","barney","Barney");
insert into user(user_id,username,name) values("66223e28","betty","Betty");

select * from user;

select * from user where username="fred";


insert into task (task_id,description, priority, due_date, user_id,username) values (1,"test",2,"2023-05-04","1b80114c","fred");

select * from task;