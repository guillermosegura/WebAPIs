INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (1, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (2, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (3, '1984', 'George Orwell', 'Dystopian');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (4, 'Pride and Prejudice', 'Jane Austen', 'Romance');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (5, 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (6, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (7, 'To the Lighthouse', 'Virginia Woolf', 'Modernist');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (8, 'The Alchemist', 'Paulo Coelho', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (9, 'Brave New World', 'Aldous Huxley', 'Dystopian');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (10, 'Moby-Dick', 'Herman Melville', 'Adventure');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (11, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (12, 'The Odyssey', 'Homer', 'Epic');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (13, 'Jane Eyre', 'Charlotte Bronte', 'Romance');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (14, 'The Divine Comedy', 'Dante Alighieri', 'Poetry');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (15, 'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 'Magical Realism');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (16, 'The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 'Mystery');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (17, 'Anna Karenina', 'Leo Tolstoy', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (18, 'Crime and Punishment', 'Fyodor Dostoevsky', 'Fiction');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (19, 'The Picture of Dorian Gray', 'Oscar Wilde', 'Gothic');
INSERT INTO TC_Book (cd_id, nb_title, nb_author, nd_genre) VALUES (20, 'Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Fantasy');


INSERT INTO TC_Professor (cd_id, nb_name, nb_lastname, nb_email) VALUES
(1, 'John', 'Smith', 'john.smith@univerxity.edu'),
(2, 'Emma', 'Johnson', 'emma.johnson@univerxity.edu'),
(3, 'Michael', 'Williams', 'michael.williams@univerxity.edu'),
(4, 'Sophia', 'Brown', 'sophia.brown@univerxity.edu'),
(5, 'Daniel', 'Jones', 'daniel.jones@univerxity.edu'),
(6, 'Olivia', 'Miller', 'olivia.miller@univerxity.edu'),
(7, 'Matthew', 'Davis', 'matthew.davis@univerxity.edu'),
(8, 'Emily', 'Garcia', 'emily.garcia@univerxity.edu'),
(9, 'Christopher', 'Rodriguez', 'christopher.rodriguez@univerxity.edu'),
(10, 'Ava', 'Martinez', 'ava.martinez@univerxity.edu');

INSERT INTO TC_Student (cd_id, nb_name, nb_lastname, nb_email, cd_advisor) VALUES
(1, 'John', 'Doe', 'john.doe@univerxity.edu', 1),
(2, 'Jane', 'Smith', 'jane.smith@univerxity.edu', 2),
(3, 'Michael', 'Johnson', 'michael.johnson@univerxity.edu', 3),
(4, 'Emily', 'Brown', 'emily.brown@univerxity.edu', 4),
(5, 'David', 'Wilson', 'david.wilson@univerxity.edu', 5),
(6, 'Sarah', 'Taylor', 'sarah.taylor@univerxity.edu', 6),
(7, 'Daniel', 'Miller', 'daniel.miller@univerxity.edu', 7),
(8, 'Olivia', 'Anderson', 'olivia.anderson@univerxity.edu', 8),
(9, 'Matthew', 'Thomas', 'matthew.thomas@univerxity.edu', 9),
(10, 'Emma', 'Martinez', 'emma.martinez@univerxity.edu', 10),
(11, 'Christopher', 'Garcia', 'christopher.garcia@univerxity.edu', 1),
(12, 'Sophia', 'Lopez', 'sophia.lopez@univerxity.edu', 2),
(13, 'Andrew', 'Hernandez', 'andrew.hernandez@univerxity.edu', 3),
(14, 'Isabella', 'Gonzalez', 'isabella.gonzalez@univerxity.edu', 4),
(15, 'Joshua', 'Adams', 'joshua.adams@univerxity.edu', 5),
(16, 'Ava', 'Campbell', 'ava.campbell@univerxity.edu', 6),
(17, 'Joseph', 'Collins', 'joseph.collins@univerxity.edu', 7),
(18, 'Mia', 'Stewart', 'mia.stewart@univerxity.edu', 8),
(19, 'William', 'Morris', 'william.morris@univerxity.edu', 9),
(20, 'Victoria', 'Lee', 'victoria.lee@univerxity.edu', 10);


INSERT INTO TC_Course (cd_id, nb_name, cd_instructor) VALUES
(1, 'Mathematics', 1),
(2, 'Physics', 2),
(3, 'Chemistry', 3),
(4, 'English', 4),
(5, 'History', 5),
(6, 'Computer Science', 6);


INSERT INTO TR_StudentCourse (cd_id, cd_student, cd_course) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 1),
(5, 2, 4),
(6, 3, 2),
(7, 3, 3),
(8, 4, 1),
(9, 4, 4),
(10, 5, 2),
(11, 5, 3),
(12, 6, 1),
(13, 6, 4),
(14, 7, 2),
(15, 7, 5),
(16, 8, 3),
(17, 8, 5),
(18, 9, 1),
(19, 9, 2),
(20, 10, 3);

INSERT INTO TR_StudentCourse (cd_id, cd_student, cd_course) VALUES
(21, 11, 1),
(22, 11, 3),
(23, 12, 2),
(24, 12, 4),
(25, 13, 1),
(26, 13, 5),
(27, 14, 3),
(28, 14, 5),
(29, 15, 2),
(30, 15, 4),
(31, 16, 1),
(32, 16, 3),
(33, 17, 2),
(34, 17, 5),
(35, 18, 1),
(36, 18, 2),
(37, 19, 3),
(38, 19, 4),
(39, 20, 1),
(40, 20, 5);
