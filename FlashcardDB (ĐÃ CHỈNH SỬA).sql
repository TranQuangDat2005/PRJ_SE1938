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
	dob DATE,
	  verificationCode text, -- Mã người dùng
	  isVerification BIT DEFAULT 0
);

INSERT INTO [user] (username, email, phone_number, [password], vip_status, [role], dob, verificationCode, isVerification)
VALUES 
('user2', 'user2@example.com', '0987654322', 'password123', 0, 0, '1995-06-15', NULL, 1);

SELECT *
FROM [user]


DELETE FROM [user] WHERE user_id = 10;
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



