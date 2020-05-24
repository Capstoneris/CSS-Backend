SELECT css.users.name AS username FROM css.users WHERE css.users.id=1;

SELECT distinct id AS userid, name AS username from css.users join css.users_in_groups ON (css.users.id = css.users_in_groups.user) WHERE css.users_in_groups.user IN ( SELECT css.users_in_groups.group FROM css.users_in_groups WHERE users_in_groups.user =1);

select title from css.groups where id=2;

select id from css.groups where UPPER(title) like Upper('ADmins');

select distinct css.users.id as userId, css.users.name as userName from css.groups join css.users_in_groups on (css.groups.id=css.users_in_groups.group) join css.users on (css.users_in_groups.user=css.users.id) where css.groups.id=1;

select distinct css.users.id as userId, css.users.name as userName from css.groups join css.users_in_groups on (css.groups.id=css.users_in_groups.group) join css.users on (css.users_in_groups.user=css.users.id) where UPPER(css.groups.title) like Upper('walmaRt');

select * from css.messages where id=1;

insert into css.messages VALUES (default,1,1,current_timestamp,'zdf2');

SELECT css.users.id as userid, css.users_in_groups.group as usergroup FROM css.users join css.users_in_groups on (css.users.id=css.users_in_groups.user) WHERE css.users.name like 'Herbert';

select * from css.groups;
select * from css.users_in_groups;
select * from css.users;

insert into css.users_in_groups values (1, 4);
insert into css.users_in_groups values (2, 4);
insert into css.users_in_groups values (1, 3);
insert into css.users_in_groups values (5, 1);

insert into css.users values (5, 'Hans');

insert into css.groups values (3, 'drei');
insert into css.groups values (4, 'vier');
insert into css.groups values (5, 'fuenf');

delete FROM css.users_in_groups where css.users_in_groups.group=4;
delete from css.groups where id=4;

delete FROM css.users_in_groups where css.users_in_groups.user=5;
delete from css.users where id=5;