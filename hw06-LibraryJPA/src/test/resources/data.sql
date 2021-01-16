insert into genres (id, name) values (1, 'adventure');
insert into genres (id, name) values (2, 'classics');
insert into genres (id, name) values (3, 'fantasy');

insert into authors (id, name) values (1, 'Joan Druett');
insert into authors (id, name) values (2, 'Lee Child');
insert into authors (id, name) values (3, 'Andrew Child');
insert into authors (id, name) values (4, 'Ray Bradbury');

insert into books (id, name, genre_id)
    values (1, 'Island of the Lost', 1);
insert into books (id, name, genre_id)
    values (2, 'In the Wake of Madness', 1);
insert into books (id, name, genre_id)
    values (3, 'The Sentinel: A Jack Reacher Novel', 1);

insert into book_authors(book_id, author_id) values (1, 1);
insert into book_authors(book_id, author_id) values (2, 1);
insert into book_authors(book_id, author_id) values (3, 2);
insert into book_authors(book_id, author_id) values (3, 3);

insert into comments(id, review, book_id) values (1, 'I am an engineer and in a position to appreciate how hard the tasks accomplished by these shipwrecked men were. Especially impressive was Raynal, the Frenchman from the Grafton. I am not sure I could even duplicate his bird cage, much less his concrete chimney or his handmade nails or his new boat made out of the old shipwreck. I read in the epilogue that original account published of the Grafton shipwreck by the survivors ignited a craze at the time to steer away from technology and get back to first principles like gardening, and shipmaking. I feel the same way today.', 1);
insert into comments(id, review, book_id) values (2, 'Joan Druett’s wonderful account of two ships which wrecked on New Zealand’s sub-Antarctic Auckland Islands is masterfully done. Never just a dry account of the tasks required for survival, she paints portraits of the two crews with all their strengths and shortcomings. Although the two ships smashed into the forbidding rocky coasts within a short period of each other, neither knew the other was there. Captain Musgrave exhibited good leadership and, that along with the skill and ingenuity of the Frenchman Raynal, resulted in all five men to survive. Misfortune struck the Scottish ship, being burdened with a weak captain and a crew unwilling to do what was required to live.', 1);
insert into comments(id, review, book_id) values (3, 'Wow..fantastic and incredibly well researched book. Couldnt put this down and finished it in a day! This book as incredibly well written. So often books like these can quickly become dry and you loose interest. Joan was able to use an amazing amount of research and backstory to build up an engaging authentic masterpiece. She was also able to weave together multiple plots quite successfully, and as reader, I wasnt confused. Another reader pointed out similarities in this tale and Ernest Shackletons adventure, which I would definitely agree with.', 1);
insert into comments(id, review, book_id) values (4, 'This book is a neat read since the author sets the stage from a historical and economical perspective to help the reader understand the rough nature of whaling and being a seaman who could be out at sea for years in some cases. Book is very well researched and you can feel like you are really there oh so many years ago and even sailing out to sea.', 2);
insert into comments(id, review, book_id) values (5, 'wonderful book!', 2);