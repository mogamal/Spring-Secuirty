insert into users(username, password,enabled)values ('moh','12345',1);
insert into authorities(authority,username)values ('read','moh');
insert into authorities(authority,username)values ('write','moh');

select * from users;
select * from authorities;