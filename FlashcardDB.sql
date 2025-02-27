
CREATE DATABASE flashcarddb;
USE flashcarddb;


CREATE TABLE [user] (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(12) UNIQUE,
    [password] VARCHAR(100) NOT NULL,
    vip_status BIT DEFAULT 0,   -- No vip = 0; vip = 1 
    [role] BIT DEFAULT 0,         -- Admin = 1, User = 0
    dob DATE
);
GO


CREATE TABLE flashcard_set (
    flashcard_set_id INT IDENTITY(1,1) PRIMARY KEY,
    time_created DATETIME DEFAULT GETDATE(),
    author INT NOT NULL,
    [status] NVARCHAR(10) CHECK (status IN ('New', 'Learning', 'Known', 'Forgotten')) DEFAULT 'New',
    number_of_flashcards INT DEFAULT 0,
    score FLOAT DEFAULT 0,
    tags VARCHAR(100),      
    visibility NVARCHAR(10) CHECK (visibility IN ('Everyone', 'Only me')) DEFAULT 'Only me',
    FOREIGN KEY (author) REFERENCES [user](user_id) ON DELETE CASCADE 
);
GO

CREATE TABLE flashcard (
    flashcard_id INT IDENTITY(1,1) PRIMARY KEY,
    flashcard_set_id INT,
    term VARCHAR(255) NOT NULL,
    [definition] TEXT NOT NULL,
    FOREIGN KEY (flashcard_set_id) REFERENCES flashcard_set(flashcard_set_id) ON DELETE CASCADE
);
GO

CREATE TABLE quick_storage (
    user_id INT,
    flashcard_set_id INT,
    PRIMARY KEY (user_id, flashcard_set_id),
    FOREIGN KEY (user_id) REFERENCES [user](user_id) ON DELETE NO ACTION,
    FOREIGN KEY (flashcard_set_id) REFERENCES flashcard_set(flashcard_set_id) ON DELETE CASCADE
);
GO



-- Thêm dữ liệu mẫu vào bảng flashcard_set
INSERT INTO flashcard_set (author, [status], number_of_flashcards, score, tags, visibility)
VALUES 
(1, 'New', 10, 85.5, 'Math, Science', 'Everyone'),
(2, 'Learning', 20, 90.0, 'English, Vocabulary', 'Only me');
GO

-- Thêm dữ liệu mẫu vào bảng quick_storage
INSERT INTO quick_storage (user_id, flashcard_set_id)
VALUES 
(1, 1), -- User 1 lưu flashcard_set 1
(2, 2), -- User 2 lưu flashcard_set 2
(1, 2); -- User 1 lưu flashcard_set 2
GO

-- Xem dữ liệu từ bảng user
SELECT * FROM [user];
GO
