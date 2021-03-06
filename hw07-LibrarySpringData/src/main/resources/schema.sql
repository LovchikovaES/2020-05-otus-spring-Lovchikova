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
drop table IF EXISTS COMMENTS;
create TABLE COMMENTS(ID BIGINT PRIMARY KEY auto_increment,
                      REVIEW VARCHAR(5000),
                      BOOK_ID BIGINT,
                      foreign key (BOOK_ID) references BOOKS(ID));
drop table IF EXISTS BOOK_AUTHORS;
create TABLE BOOK_AUTHORS(BOOK_ID BIGINT,
                          AUTHOR_ID BIGINT,
                          primary key (BOOK_ID, AUTHOR_ID),
                          foreign key (BOOK_ID) references BOOKS(ID),
                          foreign key (AUTHOR_ID) references AUTHORS(ID));
