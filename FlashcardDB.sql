CREATE DATABASE FlashcardDB;
USE FlashcardDB;

--Tao bang User
CREATE TABLE [user](
	user_id INT IDENTITY(1,1) PRIMARY KEY,
	username NVARCHAR(100) NOT NULL UNIQUE,
	email VARCHAR(100) UNIQUE NOT NULL,
	phone_number VARCHAR(12) UNIQUE,
	[password] VARCHAR(100) NOT NULL,
	vip_status BIT DEFAULT 0, --No vip = 0; vip = 1
	[role] BIT DEFAULT 0, --Admin = 1, User = 0
	dob DATE
);

--Bang Flashcard Set
CREATE TABLE flashcard_set(
	flashcard_set_id INT IDENTITY(1,1) PRIMARY KEY,
	time_created DATETIME DEFAULT GETDATE(),	--Mac dinh la thoi diem khi them the moi
	user_id INT NOT NULL,
	[status] NVARCHAR(10) CHECK ([status] IN ('New', 'Learning', 'Known', 'Forgotten')) DEFAULT 'New',
	number_of_flashcards INT DEFAULT 0,
	score FLOAT DEFAULT 0,
	tags VARCHAR(100),	--The phan loai
	visibility NVARCHAR(10) CHECK (visibility IN ('Everyone', 'Only me')) DEFAULT 'Only me',
	FOREIGN KEY (user_id) REFERENCES [user](user_id) ON DELETE NO ACTION -- khong cho phep xoa user
);


--Tao bang Flashcard
CREATE TABLE flashcard (
    flashcard_id INT IDENTITY(1,1) PRIMARY KEY,
    flashcard_set_id INT,
    term VARCHAR(255) NOT NULL,
    [definition] TEXT NOT NULL,
    FOREIGN KEY (flashcard_set_id) REFERENCES flashcard_set(flashcard_set_id) ON DELETE CASCADE
	--Khi mot flashcard set bi xoa thi toan bo flash card trong bo cug bi xoa
);

-- Xóa bảng phụ thuộc trước
DROP TABLE flashcard;
DROP TABLE quick_storage;

-- Sau đó xóa bảng flashcard_Set
DROP TABLE flashcard_set;


--Create table Quick storage
CREATE TABLE quick_storage (
    user_id INT NOT NULL,
    flashcard_set_id INT NOT NULL,
    PRIMARY KEY (user_id, flashcard_set_id),
    FOREIGN KEY (user_id) REFERENCES [user]([user_ID]) ON DELETE NO ACTION,
    FOREIGN KEY (flashcard_set_id) REFERENCES flashcard_set(flashcard_set_id) ON DELETE CASCADE
	--Khi xoa mot flash card set, du lieu lien quan se bi xoa trong quick storage
);

-------------------------------------TEST DATABASE-------------------------------------------------------
--Them du lieu vao [user]
INSERT INTO [user] (username, email, phone_number, [password], vip_status, [role], dob)
VALUES 
('user1', 'user1@example.com', '0987654321', 'password123', 0, 0, '1995-06-15'),
('admin1', 'admin1@example.com', '0123456789', 'adminpass', 1, 1, '1990-01-01'),
('user2', 'user2@example.com', '0345678901', 'password456', 0, 0, '1998-09-21');

--Them du lieu vao flashcard set
INSERT INTO flashcard_Set (user_id, [status], number_of_flashcards, score, tags, visibility)
VALUES 
(1, 'New', 10, 85.5, 'Math, Science', 'Everyone'),
(2, 'Learning', 20, 90.0, 'English, Vocabulary', 'Only me'),
(3, 'Known', 15, 70.0, 'History, Geography', 'Everyone');

--Them du lieu vao flashcard
INSERT INTO flashcard (flashcard_set_id, term, [definition])
VALUES 
(1, 'Pythagorean Theorem', 'In a right triangle, a² + b² = c²'),
(1, 'Newton’s First Law', 'An object at rest stays at rest unless acted upon by an external force'),
(2, 'Photosynthesis', 'The process by which green plants convert sunlight into energy'),
(2, 'Oxymoron', 'A figure of speech in which contradictory terms appear in conjunction'),
(3, 'World War II', 'A global war that lasted from 1939 to 1945.');

--Them du lieu vao bang quick storage
INSERT INTO quick_storage (user_id, flashcard_set_id)
VALUES 
(1, 1), -- User 1 luu Flashcard_Set 1
(2, 2), -- User 2 luu Flashcard_Set 2
(3, 3), -- User 3 luu Flashcard_Set 3
(1, 3); -- User 1 luu Flashcard_Set 3

--Kiem tra du lieu
SELECT*FROM[user];
SELECT*FROM flashcard;
SELECT*FROM flashcard_set;
SELECT*FROM quick_storage

--Xoa flashcard set
DELETE FROM flashcard_Set WHERE flashcard_set_id = 1;

--Xoa  User
DELETE FROM [user] WHERE user_id = 1;

--Hien thi toan bo tat ca flashcard cua user
SELECT fs.flashcard_set_ID, fs.tags, f.term, f.definition
FROM flashcard_Set fs
JOIN flashcard f ON fs.flashcard_set_ID = f.flashcard_Set_ID
WHERE fs.user_id = 2;







