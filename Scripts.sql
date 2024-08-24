CREATE TABLE users (
  user_id SERIAL,
  user_first_name varchar(20) DEFAULT NULL,
  user_last_name varchar(20) DEFAULT NULL,
  user_name varchar(250) DEFAULT NULL,
  user_password varchar(20) DEFAULT NULL,
  PRIMARY KEY (user_id)
);
 
CREATE TABLE todos (
  todos_id SERIAL,
  todos_description varchar(255) DEFAULT NULL,
  todos_is_done boolean NOT NULL,
  todos_target_date timestamp(6) DEFAULT NULL,
  todos_username varchar(255) DEFAULT NULL,
  todos_title varchar(255) DEFAULT NULL,
  PRIMARY KEY (todos_id)
);


