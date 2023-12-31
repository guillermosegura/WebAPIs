DROP TABLE IF EXISTS TC_Book;

-- Tabla TC_Book
CREATE TABLE TC_Book (
  cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 21) PRIMARY KEY,
  nb_title NVARCHAR(100) NOT NULL,
  nb_author NVARCHAR(100) NOT NULL,
  nd_genre NVARCHAR(100) NOT NULL
);


-- Tabla TC_Professor
CREATE TABLE TC_Professor (
    cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 21) PRIMARY KEY,
    nb_name NVARCHAR(255),
    nb_lastname NVARCHAR(255),
    nb_email NVARCHAR(255)
);

-- Tabla TC_Student
CREATE TABLE TC_Student (
    cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 21) PRIMARY KEY,
    nb_name NVARCHAR(255),
    nb_lastname NVARCHAR(255),
    nb_email NVARCHAR(255),
    cd_advisor INT,
    CONSTRAINT FK_Advisor FOREIGN KEY (cd_advisor) REFERENCES TC_Professor (cd_id)
);

-- Tabla TC_Course
CREATE TABLE TC_Course (
    cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 21) PRIMARY KEY,
    nb_name NVARCHAR(255),
    cd_instructor INT,
    CONSTRAINT FK_Instructor FOREIGN KEY (cd_instructor) REFERENCES TC_Professor (cd_id)
);

-- Tabla TR_StudentCourse
CREATE TABLE TR_StudentCourse (
    cd_id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 41) PRIMARY KEY,
    cd_student INT,
    cd_course INT,
    CONSTRAINT FK_Student FOREIGN KEY (cd_student) REFERENCES TC_Student (cd_id),
    CONSTRAINT FK_Course FOREIGN KEY (cd_course) REFERENCES TC_Course (cd_id)
);
