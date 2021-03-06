insert into genres (id, `name`) values (1, 'adventure');
insert into genres (id, `name`) values (2, 'classics');
insert into genres (id, `name`) values (3, 'detective');

insert into authors (id, `name`) values (1, 'Sir Arthur Conan Doyle');
insert into authors (id, `name`) values (2, 'Charles Dickens');
insert into authors (id, `name`) values (3, 'Lee Child');

insert into books (id, `name`, `author_id`, `genre_id`)
    values (1, 'Blue Moon', 3, 1);
insert into books (id, `name`, `author_id`, `genre_id`)
    values (2, 'Sherlock Holmes', 1, 3);
insert into books (id, `name`, `author_id`, `genre_id`)
    values (3, 'Oliver Twist', 2, 2);
insert into books (id, `name`, `author_id`, `genre_id`)
    values (4, 'Hard Times', 2, 2);
insert into books (id, `name`, `author_id`, `genre_id`)
    values (5, 'A Tale of Two Cities', 2, 2);