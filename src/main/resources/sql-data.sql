create schema <schema-name>;
show databases;

use springsecuritydemo;
show tables;

select * from springsecuritydemo.user;
insert into springsecuritydemo.user values(1, "9909909900", "password1", "username1");
update springsecuritydemo.user set password = "$2a$10$25b.R8/xVu1MqSDCRont7O6OnkHQJ4v0Mxqf54kfQRF4tVvlrQ4l." where user_id = 1;

select * from springsecuritydemo.authorities;
insert into springsecuritydemo.authorities values(1, "USER", 1);
update springsecuritydemo.authorities set authority = "ROLE_USER" where id = 1;