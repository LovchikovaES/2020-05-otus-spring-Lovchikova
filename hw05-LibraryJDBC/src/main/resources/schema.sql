DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(ID BIGINT PRIMARY KEY auto_increment,
                   NAME VARCHAR(255));
DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(ID BIGINT PRIMARY KEY auto_increment,
                    NAME VARCHAR(255));
DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(ID BIGINT PRIMARY KEY auto_increment,
                   NAME VARCHAR(255),
                   AUTHOR_ID BIGINT,
                   GENRE_ID BIGINT,
                   foreign key (AUTHOR_ID) references AUTHORS(ID),
                   foreign key (GENRE_ID) references GENRES(ID));