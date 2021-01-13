DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY auto_increment,
                     NAME VARCHAR(255));
DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY auto_increment,
                    NAME VARCHAR(255));
DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID BIGINT PRIMARY KEY auto_increment,
                   NAME VARCHAR(255),
                   GENRE_ID BIGINT,
                   foreign key (GENRE_ID) references GENRES(ID));
DROP TABLE IF EXISTS COMMENTS;
CREATE TABLE COMMENTS(ID BIGINT PRIMARY KEY auto_increment,
                      REVIEW VARCHAR(5000),
                      BOOK_ID BIGINT,
                      foreign key (BOOK_ID) references BOOKS(ID));
DROP TABLE IF EXISTS BOOK_AUTHORS;
CREATE TABLE BOOK_AUTHORS(BOOK_ID BIGINT,
                          AUTHOR_ID BIGINT,
                          primary key (BOOK_ID, AUTHOR_ID),
                          foreign key (BOOK_ID) references BOOKS(ID),
                          foreign key (AUTHOR_ID) references AUTHORS(ID));
