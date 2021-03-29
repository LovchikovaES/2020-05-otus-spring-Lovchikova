drop table IF EXISTS AUTHORS;
create TABLE AUTHORS(ID BIGINT PRIMARY KEY auto_increment,
                     NAME VARCHAR(255));
drop table IF EXISTS GENRES;
create TABLE GENRES(ID BIGINT PRIMARY KEY auto_increment,
                    NAME VARCHAR(255));
drop table IF EXISTS BOOKS;
create TABLE BOOKS(ID BIGINT PRIMARY KEY auto_increment,
                   NAME VARCHAR(255),
                   GENRE_ID BIGINT,
                   foreign key (GENRE_ID) references GENRES(ID));
drop table IF EXISTS BOOK_AUTHORS;
create TABLE BOOK_AUTHORS(BOOK_ID BIGINT,
                          AUTHOR_ID BIGINT,
                          primary key (BOOK_ID, AUTHOR_ID),
                          foreign key (BOOK_ID) references BOOKS(ID),
                          foreign key (AUTHOR_ID) references AUTHORS(ID));
drop table IF EXISTS USERS;
create TABLE USERS(ID BIGINT PRIMARY KEY auto_increment,
                          NAME VARCHAR(255),
                          USERNAME VARCHAR(50),
                          PASSWORD VARCHAR(60),
                          enabled BOOLEAN);

drop table IF EXISTS ROLES;
create TABLE ROLES(ID BIGINT PRIMARY KEY auto_increment,
                   NAME VARCHAR(255));

drop table IF EXISTS PRIVILEGES;
create TABLE CUSTOM_PRIVILEGES(ID BIGINT PRIMARY KEY auto_increment,
                        NAME VARCHAR(255));

drop table IF EXISTS USERS_ROLES;
create TABLE USERS_ROLES(USER_ID BIGINT,
                         ROLE_ID BIGINT,
                         primary key (USER_ID, ROLE_ID),
                         foreign key (USER_ID) references USERS(ID),
                         foreign key (ROLE_ID) references ROLES(ID));

drop table IF EXISTS ROLES_PRIVILEGES;
create TABLE ROLES_PRIVILEGES(ROLE_ID BIGINT,
                              PRIVILEGE_ID BIGINT,
                         primary key (ROLE_ID, PRIVILEGE_ID),
                         foreign key (ROLE_ID) references ROLES(ID),
                         foreign key (PRIVILEGE_ID) references CUSTOM_PRIVILEGES(ID));