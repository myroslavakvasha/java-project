create database NasaImages
go

use NasaImages
go

create table Category (
	Id int primary key identity,
	Name nvarchar(255) not null
)
go

create table Mission (
	Id int primary key identity,
	Name nvarchar(255) not null,
	Description nvarchar(max)
)
go

create table Photographer (
	Id int primary key identity,
	Name nvarchar(255) not null,
	Surname nvarchar(255) not null
)
go

create table NasaImage (
	Id int primary key identity,
	Title nvarchar(255),
	Link nvarchar(255),
	Description nvarchar(max),
	PicturePath nvarchar(100),
	PublishedDate nvarchar(100),
	IdMission int foreign key references Mission(Id),
	IdPhotographer int foreign key references Photographer(Id)
)
go

create table NasaImageCategory (
	IdNasaImage int foreign key references NasaImage(Id),
	IdCategory int foreign key references Category(Id),
	primary key(IdNasaImage, IdCategory)
)
go

create table [User] (
	Id int primary key identity,
	Username nvarchar(100) unique not null,
	PasswordHash nvarchar(255) not null,
	Role nvarchar(30) not null check (Role in ('ADMIN', 'USER'))
)
go

-- category

CREATE PROCEDURE createCategory
    @Name NVARCHAR(255)
AS
BEGIN
    INSERT INTO Category (Name) VALUES (@Name)
END
GO

CREATE PROCEDURE updateCategory
    @Id INT,
    @Name NVARCHAR(255)
AS
BEGIN
    UPDATE Category SET Name = @Name WHERE Id = @Id
END
GO

CREATE PROCEDURE deleteCategory
    @Id INT
AS
BEGIN
    DELETE FROM Category WHERE Id = @Id
END
GO

CREATE PROCEDURE selectCategory
    @Id INT
AS
BEGIN
    SELECT * FROM Category WHERE Id = @Id
END
GO

CREATE PROCEDURE selectCategories
AS
BEGIN
    SELECT * FROM Category
END
GO

-- mission

CREATE PROCEDURE createMission
    @Name NVARCHAR(255),
    @Description NVARCHAR(MAX)
AS
BEGIN
    INSERT INTO Mission (Name, Description) VALUES (@Name, @Description)
END
GO

CREATE PROCEDURE updateMission
    @Id INT,
    @Name NVARCHAR(255),
    @Description NVARCHAR(MAX)
AS
BEGIN
    UPDATE Mission SET Name = @Name, Description = @Description WHERE Id = @Id
END
GO

CREATE PROCEDURE deleteMission
    @Id INT
AS
BEGIN
    DELETE FROM Mission WHERE Id = @Id
END
GO

CREATE PROCEDURE selectMission
    @Id INT
AS
BEGIN
    SELECT * FROM Mission WHERE Id = @Id
END
GO

CREATE PROCEDURE selectMissions
AS
BEGIN
    SELECT * FROM Mission
END
GO

-- photographer

CREATE PROCEDURE createPhotographer
    @Name NVARCHAR(255),
    @Surname NVARCHAR(255)
AS
BEGIN
    INSERT INTO Photographer (Name, Surname) VALUES (@Name, @Surname)
END
GO

CREATE PROCEDURE updatePhotographer
    @Id INT,
    @Name NVARCHAR(255),
    @Surname NVARCHAR(255)
AS
BEGIN
    UPDATE Photographer SET Name = @Name, Surname = @Surname WHERE Id = @Id
END
GO

CREATE PROCEDURE deletePhotographer
    @Id INT
AS
BEGIN
    DELETE FROM Photographer WHERE Id = @Id
END
GO

CREATE PROCEDURE selectPhotographer
    @Id INT
AS
BEGIN
    SELECT * FROM Photographer WHERE Id = @Id
END
GO

CREATE PROCEDURE selectPhotographers
AS
BEGIN
    SELECT * FROM Photographer
END
GO

-- nasa image

CREATE PROCEDURE createNasaImage
    @Title NVARCHAR(255),
    @Link NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @PicturePath NVARCHAR(100),
    @PublishedDate NVARCHAR(100),
    @IdMission INT,
    @IdPhotographer INT,
    @Id INT OUTPUT
AS
BEGIN
    INSERT INTO NasaImage (Title, Link, Description, PicturePath, PublishedDate, IdMission, IdPhotographer)
    VALUES (@Title, @Link, @Description, @PicturePath, @PublishedDate, @IdMission, @IdPhotographer)
    SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateNasaImage
    @Id INT,
    @Title NVARCHAR(255),
    @Link NVARCHAR(255),
    @Description NVARCHAR(MAX),
    @PicturePath NVARCHAR(100),
    @PublishedDate NVARCHAR(100),
    @IdMission INT,
    @IdPhotographer INT
AS
BEGIN
    UPDATE NasaImage SET
        Title = @Title,
        Link = @Link,
        Description = @Description,
        PicturePath = @PicturePath,
        PublishedDate = @PublishedDate,
        IdMission = @IdMission,
        IdPhotographer = @IdPhotographer
    WHERE Id = @Id
END
GO

CREATE PROCEDURE deleteNasaImage
    @Id INT
AS
BEGIN
    DELETE FROM NasaImageCategory WHERE IdNasaImage = @Id
    DELETE FROM NasaImage WHERE Id = @Id
END
GO

CREATE PROCEDURE selectNasaImage
    @Id INT
AS
BEGIN
    SELECT * FROM NasaImage WHERE Id = @Id
END
GO

CREATE PROCEDURE selectNasaImages
AS
BEGIN
    SELECT * FROM NasaImage
END
GO

-- nasa image category

CREATE PROCEDURE addCategoryToImage
    @IdNasaImage INT,
    @IdCategory INT
AS
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM NasaImageCategory 
        WHERE IdNasaImage = @IdNasaImage AND IdCategory = @IdCategory
    )
    BEGIN
        INSERT INTO NasaImageCategory (IdNasaImage, IdCategory)
        VALUES (@IdNasaImage, @IdCategory)
    END
END
GO

CREATE PROCEDURE removeCategoryFromImage
    @IdNasaImage INT,
    @IdCategory INT
AS
BEGIN
    DELETE FROM NasaImageCategory 
    WHERE IdNasaImage = @IdNasaImage AND IdCategory = @IdCategory
END
GO

CREATE PROCEDURE selectCategoriesForImage
    @IdNasaImage INT
AS
BEGIN
    SELECT c.* FROM Category c
    INNER JOIN NasaImageCategory nic ON c.Id = nic.IdCategory
    WHERE nic.IdNasaImage = @IdNasaImage
END
GO

-- user

CREATE PROCEDURE createUser
    @Username NVARCHAR(100),
    @PasswordHash NVARCHAR(255),
    @Role NVARCHAR(30)
AS
BEGIN
    INSERT INTO [User] (Username, PasswordHash, Role)
    VALUES (@Username, @PasswordHash, @Role)
END
GO

CREATE PROCEDURE selectUserByUsername
    @Username NVARCHAR(100)
AS
BEGIN
    SELECT * FROM [User] WHERE Username = @Username
END
GO

-- seed data

SET IDENTITY_INSERT Category ON
INSERT INTO Category (Id, Name) VALUES (1, 'Mars')
INSERT INTO Category (Id, Name) VALUES (2, 'Moon')
INSERT INTO Category (Id, Name) VALUES (3, 'Earth')
INSERT INTO Category (Id, Name) VALUES (4, 'Sun')
INSERT INTO Category (Id, Name) VALUES (5, 'Deep Space')
INSERT INTO Category (Id, Name) VALUES (6, 'ISS')
INSERT INTO Category (Id, Name) VALUES (7, 'Nebula')
INSERT INTO Category (Id, Name) VALUES (8, 'Galaxy')
INSERT INTO Category (Id, Name) VALUES (9, 'Asteroid')
INSERT INTO Category (Id, Name) VALUES (10, 'Spacecraft')
SET IDENTITY_INSERT Category OFF
GO

SET IDENTITY_INSERT Mission ON
INSERT INTO Mission (Id, Name, Description) VALUES (1, 'Curiosity', 'Mars Science Laboratory rover exploring Gale Crater since 2012')
INSERT INTO Mission (Id, Name, Description) VALUES (2, 'Perseverance', 'Mars 2020 rover searching for signs of ancient life')
INSERT INTO Mission (Id, Name, Description) VALUES (3, 'Hubble', 'Space telescope observing the universe since 1990')
INSERT INTO Mission (Id, Name, Description) VALUES (4, 'James Webb', 'Next generation space telescope launched in 2021')
INSERT INTO Mission (Id, Name, Description) VALUES (5, 'Artemis', 'Program to return humans to the Moon')
INSERT INTO Mission (Id, Name, Description) VALUES (6, 'Apollo', 'Historic program that landed humans on the Moon')
INSERT INTO Mission (Id, Name, Description) VALUES (7, 'Voyager', 'Twin spacecraft exploring the outer solar system')
INSERT INTO Mission (Id, Name, Description) VALUES (8, 'Juno', 'Spacecraft studying Jupiter since 2016')
INSERT INTO Mission (Id, Name, Description) VALUES (9, 'ISS', 'International Space Station orbiting Earth')
INSERT INTO Mission (Id, Name, Description) VALUES (10, 'Unknown', 'Mission not identified')
SET IDENTITY_INSERT Mission OFF
GO

SET IDENTITY_INSERT Photographer ON
INSERT INTO Photographer (Id, Name, Surname) VALUES (1, 'Neil', 'Armstrong')
INSERT INTO Photographer (Id, Name, Surname) VALUES (2, 'Buzz', 'Aldrin')
INSERT INTO Photographer (Id, Name, Surname) VALUES (3, 'Sally', 'Ride')
INSERT INTO Photographer (Id, Name, Surname) VALUES (4, 'Mae', 'Jemison')
INSERT INTO Photographer (Id, Name, Surname) VALUES (5, 'Edwin', 'Hubble')
INSERT INTO Photographer (Id, Name, Surname) VALUES (6, 'Carl', 'Sagan')
INSERT INTO Photographer (Id, Name, Surname) VALUES (7, 'Valentina', 'Tereshkova')
INSERT INTO Photographer (Id, Name, Surname) VALUES (8, 'Yuri', 'Gagarin')
INSERT INTO Photographer (Id, Name, Surname) VALUES (9, 'John', 'Glenn')
INSERT INTO Photographer (Id, Name, Surname) VALUES (10, 'Christa', 'McAuliffe')
SET IDENTITY_INSERT Photographer OFF
GO

EXEC createUser 'admin', '$2a$12$zWHCV/RdX6ROuibOgoWOM.pugj14TWW6LgAifEBHvMKEcVZsFAiSa', 'ADMIN'
GO

-- delete all

CREATE PROCEDURE deleteAll
AS
BEGIN
    DELETE FROM NasaImageCategory
    DELETE FROM NasaImage
    DELETE FROM Category WHERE Id > 10
    DELETE FROM Mission WHERE Id > 10
    DELETE FROM Photographer WHERE Id > 10
END
GO

-- export

CREATE PROCEDURE selectNasaImagesForExport
AS
BEGIN
    SELECT
        i.Id,
        i.Title,
        i.Link,
        i.Description,
        i.PicturePath,
        i.PublishedDate,
        m.Name AS MissionName,
        CASE
            WHEN p.Name IS NOT NULL
            THEN p.Name + ' ' + p.Surname
            ELSE NULL
        END AS PhotographerName
    FROM NasaImage i
    LEFT JOIN Mission m ON i.IdMission = m.Id
    LEFT JOIN Photographer p ON i.IdPhotographer = p.Id
    ORDER BY i.Id
END
GO

CREATE PROCEDURE selectCategoryNamesForImage
    @IdNasaImage INT
AS
BEGIN
    SELECT c.Name
    FROM Category c
    INNER JOIN NasaImageCategory nic ON c.Id = nic.IdCategory
    WHERE nic.IdNasaImage = @IdNasaImage
END
GO