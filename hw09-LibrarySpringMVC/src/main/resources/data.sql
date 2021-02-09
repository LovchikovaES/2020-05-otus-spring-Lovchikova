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

insert into comments(id, review, book_id) values (1, 'I am an engineer and in a position to appreciate how hard the tasks accomplished by these shipwrecked men were. Especially impressive was Raynal, the Frenchman from the Grafton. I am not sure I could even duplicate his bird cage, much less his concrete chimney or his handmade nails or his new boat made out of the old shipwreck. I read in the epilogue that original account published of the Grafton shipwreck by the survivors ignited a craze at the time to steer away from technology and get back to first principles like gardening, and shipmaking. I feel the same way today.', 1);
insert into comments(id, review, book_id) values (2, 'Joan Druett’s wonderful account of two ships which wrecked on New Zealand’s sub-Antarctic Auckland Islands is masterfully done. Never just a dry account of the tasks required for survival, she paints portraits of the two crews with all their strengths and shortcomings. Although the two ships smashed into the forbidding rocky coasts within a short period of each other, neither knew the other was there. Captain Musgrave exhibited good leadership and, that along with the skill and ingenuity of the Frenchman Raynal, resulted in all five men to survive. Misfortune struck the Scottish ship, being burdened with a weak captain and a crew unwilling to do what was required to live.', 1);
insert into comments(id, review, book_id) values (3, 'Wow..fantastic and incredibly well researched book. Couldnt put this down and finished it in a day! This book as incredibly well written. So often books like these can quickly become dry and you loose interest. Joan was able to use an amazing amount of research and backstory to build up an engaging authentic masterpiece. She was also able to weave together multiple plots quite successfully, and as reader, I wasnt confused. Another reader pointed out similarities in this tale and Ernest Shackletons adventure, which I would definitely agree with.', 1);
insert into comments(id, review, book_id) values (4, 'This book is a neat read since the author sets the stage from a historical and economical perspective to help the reader understand the rough nature of whaling and being a seaman who could be out at sea for years in some cases. Book is very well researched and you can feel like you are really there oh so many years ago and even sailing out to sea.', 2);
insert into comments(id, review, book_id) values (5, 'This was a very well written book that have the reader a glimpse of life aboard a whaling ship in the mid 1800s. Also the tale of a murder committed on the ship and the retribution meted out because of the crime.', 2);
insert into comments(id, review, book_id) values (6, 'Whenever one of my favorite authors adds a co-writer I usually try them out for a couple of books. Most of the time it doesnt work but there have been a few where it has. This collaboration is not one of those that works. The Reacher in this book is simply not the Jack Reacher we all know and love to read about. This guy does not make me care what happens to him. He has mounted up and jumped far, far over the shark. This might be the last collaboration Jack Reacher for me. Id have to be pretty bored to read the next one. I finished this book but only because I paid for it.', 3);
insert into comments(id, review, book_id) values (7, 'Finally I read THE LITTLE PRINCE. Such a cute book with cute-cute illustrations in it. I had my eye on this book for quite a time. I finally picked this book in the morning and read it. This book is not only for children but for grown-ups too.', 4);
insert into comments(id, review, book_id) values (8, 'Us humans could learn a thing or two about this book, made me realise how materialistic we really are and how we are constantly making the same mistakes. If you can get this book cheap, buy it, read it, let your kids read and who knows we all might be better people going forward. Now that is worth a couple of quid of anyones money!', 4);
insert into comments(id, review, book_id) values (9, 'This is a story that never loses its appeal. I am a child again when I read the words within these pages, celebrating summers that were born 40 years before me. Yet I feel drawn in as if I am the hero, his feelings mirror my own. Ray Bradbury was a genius, evoking feelings I have buried for decades. This story is simple, and timeless. If you havent read this for 30 years, its time to revisit hometown summer vacation memories.', 6);
insert into comments(id, review, book_id) values (10, 'good story', 8);
insert into comments(id, review, book_id) values (11, 'This is classic sci-fi by a master', 8);
insert into comments(id, review, book_id) values (12, 'Great book. Timeless stories', 8);