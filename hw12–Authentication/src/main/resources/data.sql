insert into genres (id, name) values (1, 'adventure');
insert into genres (id, name) values (2, 'classics');
insert into genres (id, name) values (3, 'detective');
insert into genres (id, name) values (4, 'fantasy');

insert into authors (id, name) values (1, 'Joan Druett');
insert into authors (id, name) values (2, 'Lee Child');
insert into authors (id, name) values (3, 'Andrew Child');
insert into authors (id, name) values (4, 'Antoine de Saint-Exupéry');
insert into authors (id, name) values (5, 'Ray Bradbury');
insert into authors (id, name) values (6, 'Agatha Christie');
insert into authors (id, name) values (7, 'Fletcher Pratt');
insert into authors (id, name) values (8, 'L. Sprague de Camp');

insert into books (id, name, genre_id)
    values (1, 'Island of the Lost', 1);
insert into books (id, name, genre_id)
    values (2, 'In the Wake of Madness', 1);
insert into books (id, name, genre_id)
    values (3, 'The Sentinel: A Jack Reacher Novel', 1);
insert into books (id, name, genre_id)
    values (4, 'The Little Prince', 2);
insert into books (id, name, genre_id)
    values (5, 'Fahrenheit 451', 2);
insert into books (id, name, genre_id)
    values (6, 'Dandelion Wine', 2);
insert into books (id, name, genre_id)
    values (7, 'The Man in the Brown Suit', 3);
insert into books (id, name, genre_id)
    values (8, 'The Complete Compleat Enchanter', 4);

insert into book_authors(book_id, author_id) values (1, 1);
insert into book_authors(book_id, author_id) values (2, 1);
insert into book_authors(book_id, author_id) values (3, 2);
insert into book_authors(book_id, author_id) values (3, 3);
insert into book_authors(book_id, author_id) values (4, 4);
insert into book_authors(book_id, author_id) values (5, 5);
insert into book_authors(book_id, author_id) values (6, 5);
insert into book_authors(book_id, author_id) values (7, 6);
insert into book_authors(book_id, author_id) values (8, 7);
insert into book_authors(book_id, author_id) values (8, 8);

insert into users(id, name, username, password, enabled) values (1, 'Admin', 'admin', '$2a$11$yRxuh20cOknH3KYQz5wZ6OoHBAHQHTGy8kVGfSpcxPXatFj32bEle', 1); -- password admin