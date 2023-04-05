create database todo;
use todo;

drop table user;

CREATE TABLE user (
  user_id CHAR(8) NOT NULL,
  username VARCHAR(255) NOT NULL,
  name VARCHAR(255),
  constraint user_pk PRIMARY KEY (`user_id`)
);

drop table task;

CREATE TABLE task (
  task_id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255),
  priority INT,
  due_date DATE,
  user_id CHAR(8),
  username VARCHAR(255),
  constraint task_pk PRIMARY KEY (task_id),
  constraint  task_user_pk FOREIGN KEY (user_id) REFERENCES user(user_id),
  CHECK (priority >= 1 AND priority <= 3)
);

