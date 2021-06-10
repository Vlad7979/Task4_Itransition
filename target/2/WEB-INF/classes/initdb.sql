CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    email varchar(255) NOT NULL,
    regDate DATE NOT NULL,
    lastLogIn DATE NOT NULL,
    status BOOLEAN NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

alter table users modify id bigint(20) not null auto_increment;

truncate table users;

alter table users auto_increment = 1;

SET @@auto_increment_increment = 1;

SET @@auto_increment_offset = 1;

SELECT @@auto_increment_increment;

SELECT @@auto_increment_offset;

SET @@GLOBAL.auto_increment_increment=1;

SET GLOBAL auto_increment_increment = 1;

SHOW VARIABLES LIKE 'auto_inc%';

STOP GROUP_REPLICATION;

SHOW VARIABLES LIKE '%group_replication_auto_increment_increment%';

insert into users(name, email, regdate, lastlogin, status, password) VALUES ('asd', 'asd@.a', '2000-12-12', '2000-12-12', false, '12')