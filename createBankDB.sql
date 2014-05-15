--IF EXISTS(SELECT 1 FROM master.dbo.sysdatabases WHERE name = 'BankDB') 
--BEGIN
--DROP DATABASE BankDB
--END
--GO

--CREATE DATABASE BankDB
--GO

USE [BankDB]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO

IF EXISTS (SELECT 1 FROM sys.objects 
			where name = 'FK_Transact_Account')
			ALTER TABLE [dbo].[Transact] DROP CONSTRAINT FK_Transact_Account;

IF EXISTS (SELECT 1 FROM sys.objects 
			where name = 'FK_Account_Users')
			ALTER TABLE [dbo].[Account] DROP CONSTRAINT FK_Account_Users;

 IF EXISTS(SELECT name FROM BankDB.sys.tables 
 		  WHERE name = 'Users')
 		  DROP TABLE Users;
 GO
 
 IF EXISTS(SELECT name FROM BankDB.sys.tables 
 		  WHERE name = 'Account')
 		  DROP TABLE Account; 
 GO
 
 IF EXISTS(SELECT name FROM BankDB.sys.tables 
 		  WHERE name = 'Transact')
 		  DROP TABLE Transact; 
 GO


CREATE TABLE [dbo].[Users](
	[UserID] 	 [int] 		IDENTITY(1,1) 	NOT NULL,
	[UserName]	 [nvarchar](25) 			NOT NULL,
	[FirstName]	 [nvarchar](50) 			NOT NULL,
	[LastName] 	 [nvarchar](50) 			NOT NULL,
	[Password] 	 [nvarchar](100) 			NOT NULL,
	[Email] 	 [nvarchar](200) 			NOT NULL,
	[Active] 	 [bit] 						NOT NULL DEFAULT('1'),

 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],

 CONSTRAINT [UQ_UsersEmail] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],

 CONSTRAINT [UQ_UsersName] UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]

) ON [PRIMARY]

GO



CREATE TABLE [dbo].[Account](
	[AccountID]  	[int] 		IDENTITY(1,1) 	NOT NULL,
	[UserID]	 	[int]		 				NOT NULL,
	[Balance]	 	[Money]	 					NOT NULL DEFAULT(0),
	[LastModDate]	[datetime]					NOT NULL,
	[Active] 	 	[bit] 						NOT NULL DEFAULT('1'),

 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],


) ON [PRIMARY]

GO




CREATE TABLE [dbo].[Transact](
	[TransactionID]	[int] 		IDENTITY(1,1) 	NOT NULL,
	[AccountID]  	[int] 					 	NOT NULL,	
	[Amount]	 	[Money]	 					NOT NULL,
	[Remaining]	 	[Money]	 					NOT NULL,
	[TransactionType][varchar](8)				NOT NULL,
	[TransactionDate][datetime]					NOT NULL,

 CONSTRAINT [PK_Transact] PRIMARY KEY CLUSTERED 
(
	[TransactionID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],


) ON [PRIMARY]

GO





/* *** ALTER TABLES *** */
/* *** ALTER TABLES *** */



/* *** Adds a contraint that only allows withdraw or deposit for transaction type *** */



ALTER TABLE [dbo].[Transact]  WITH CHECK ADD  CONSTRAINT [CK_Transact] CHECK  (([TransactionType]='withdraw' OR [TransactionType]='deposit'))
GO

ALTER TABLE [dbo].[Transact] CHECK CONSTRAINT [CK_Transact]
GO


/* *** Add the foreign key constraints on the Account table *** */



ALTER TABLE [dbo].[Account]  WITH NOCHECK ADD  CONSTRAINT [FK_Account_Users] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO


/* *** Add the foreign key constraints on the Transact table *** */

ALTER TABLE [dbo].[Transact]  WITH NOCHECK ADD  CONSTRAINT [FK_Transact_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO





/* *** INSERT STATEMENTS *** */
/* *** INSERT STATEMENTS *** */


INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'andrew', 'Andrew', 'Willhoit', 'andrew', 'andrew.willhoit@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'brad', 'Brad', 'Bummers', 'brad', 'brad@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'craig', 'Craig', 'Cowers', 'craig', 'craig@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'derek', 'Derek', 'Darcey', 'derek', 'derek@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'eric', 'Eric', 'Ericson', 'eric', 'eric@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'frank', 'Frank', 'Frankson', 'frank', 'frank@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'george', 'George', 'Georgeson', 'george', 'george@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'henry', 'Henry', 'Henryson', 'henry', 'henry@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'isaac', 'Isaac', 'Isaacson', 'isaac', 'isaac@gmail.com')
INSERT [dbo].[Users]( [UserName], [FirstName], [LastName], [Password], [Email]) VALUES ( 'jerry', 'jerry', 'Jerryson', 'jerry', 'jerry@gmail.com')


INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '1', '2000.00', '1999-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '2', '2000.00', '1997-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '3', '4000.00', '2000-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '4', '6123.45', '2001-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '5', '1234.15', '2003-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '6', '5555.00', '2004-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '7', '8989.00', '2005-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '8', '9000.00', '2006-12-25 12:30:15')
INSERT [dbo].[Account]( [UserID], [Balance], [LastModDate]) VALUES ( '9', '9500.00', '2010-12-25 12:30:15')


INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '1', '1996.00', '1996.00', 'deposit',  '1998-10-25 22:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '1', '10.00',   '1986.00', 'withdraw', '1999-10-25 22:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '1', '11.00',   '1975.00', 'withdraw', '1999-11-25 10:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '1', '12.00',   '1987.00', 'deposit',  '1999-12-23 11:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '1', '13.00',   '2000.00', 'deposit',  '1999-12-25 12:30:15')

INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '1974.00', '1974.00', 'deposit',  '1994-12-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '14.00', '1988.00', 'deposit',  '1994-12-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '15.00', '2003.00', 'deposit',  '1995-12-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '16.00', '1987.00', 'withdraw', '1996-12-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '32.00', '1955.00', 'withdraw', '1997-10-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '2', '45.00', '2000.00', 'deposit',  '1997-12-25 12:30:15')

INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '3', '23.00', '2000.00', 'withdraw', '1999-10-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '3', '24.00', '2000.00', 'deposit',  '1999-11-25 12:30:15')
INSERT [dbo].[Transact](  [AccountID], [Amount], [Remaining], [TransactionType], [TransactionDate]) VALUES (  '3', '10.00', '2000.00', 'withdraw', '2000-12-25 12:30:15')



SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO











--GET TRANSACTION HISTORY BY ACCOUNTID
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_GetAllTransactionsForAccount' AND type = 'P')
   DROP PROCEDURE sp_GetAllTransactionsForAccount
GO

CREATE PROCEDURE [dbo].[sp_GetAllTransactionsForAccount]
	(@AccountID int)
AS
	SELECT * FROM Transact
	WHERE AccountID = @AccountID
	ORDER BY TransactionDate  
	Return
GO



--GET TRANSACTION HISTORY BY USERID
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_GetTransactionsFromUserID' AND type = 'P')
   DROP PROCEDURE sp_GetTransactionsFromUserID
GO

CREATE PROCEDURE [dbo].[sp_GetTransactionsFromUserID]
	(@UserID int)
AS
	SELECT TransactionDate, TransactionType, Amount, Remaining
	FROM Transact t
	JOIN Account a
	ON t.AccountID = a.AccountID
	WHERE t.AccountID = 
	(SELECT a.AccountID 
	WHERE a.UserID = @UserID)
 
	RETURN 
GO


--WITHDRAW
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_WithdrawFromAccount' AND type = 'P')
   DROP PROCEDURE sp_WithdrawFromAccount
GO

CREATE PROCEDURE [dbo].[sp_WithdrawFromAccount]
	(@AccountID int,
	 @Amount money	)
AS
	DECLARE @NowDate datetime = GETDATE();
	 UPDATE Account 
 SET Balance= Balance - @Amount,
 	LastModDate= @NowDate
 WHERE AccountID = @AccountID

 INSERT INTO Transact
 (AccountID,
  Amount,
  Remaining,
  TransactionType,
  TransactionDate)
 VALUES
 (@AccountID,
  @Amount,
  (SELECT Balance
	FROM Account
	WHERE Account.AccountID = @AccountID),
    'withdraw',
    @NowDate)
 
	RETURN @@ROWCOUNT
GO


--DEPOSIT
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_DepositToAccount' AND type = 'P')
   DROP PROCEDURE sp_DepositToAccount
GO

CREATE PROCEDURE [dbo].[sp_DepositToAccount]
	(@AccountID int,
	 @Amount money	)
AS
	DECLARE @NowDate datetime = GETDATE();
	 UPDATE Account 
 SET Balance= Balance + @Amount,
 	LastModDate= @NowDate
 WHERE AccountID = @AccountID

 INSERT INTO Transact
 (AccountID,
  Amount,
  Remaining,
  TransactionType,
  TransactionDate)
 VALUES
 (@AccountID,
  @Amount,
  (SELECT Balance
	FROM Account
	WHERE Account.AccountID = @AccountID),
    'deposit',
    @NowDate)
 
	RETURN @@ROWCOUNT
GO






--CREATE USER
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_CreateUser' AND type = 'P')
   DROP PROCEDURE sp_CreateUser
GO

CREATE PROCEDURE [dbo].[sp_CreateUser]
	(@UserName nvarchar(25),
	 @FirstName nvarchar(50),
	 @LastName nvarchar(50),
	 @Password nvarchar(100),
	 @Email nvarchar(200))
AS
	DECLARE @NowDate datetime = GETDATE();
	

 INSERT INTO Users
 (UserName,
  FirstName,
  LastName,
  Password,
  Email)
 VALUES
 (@UserName,
  @FirstName,
  @LastName,
  @Password,
  @Email)

 DECLARE @ID int = @@IDENTITY

 INSERT INTO Account
 ( UserID,
   Balance,
   LastModDate)
 VALUES
 ( (SELECT UserID 
 	FROM Users
 	WHERE UserName = @UserName),
    0,
    @NowDate)

 	RETURN @ID
	--RETURN @@ROWCOUNT
GO



--LOGIN
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_Login' AND type = 'P')
   DROP PROCEDURE sp_Login
GO

CREATE PROCEDURE [dbo].[sp_Login]
	(@UserName nvarchar(25),
	 @Password nvarchar(100))
AS

SELECT u.UserID, u.UserName, u.FirstName, u.LastName, u.Password, u.Email, u.Active, a.AccountID, a.Balance, a.LastModDate
FROM Users u 
JOIN Account a
ON u.UserID = a.UserID 
WHERE UserName = @UserName
and Password = @Password
	RETURN
GO


--GET ACCT BY ACCTID
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_GetAcct' AND type = 'P')
   DROP PROCEDURE sp_GetAcct
GO

CREATE PROCEDURE [dbo].[sp_GetAcct]
	(@AcctID int)
AS

SELECT u.UserID, a.AccountID, u.UserName, u.FirstName, u.LastName, u.Email, a.Balance, a.LastModDate
FROM Users u 
JOIN Account a
ON u.UserID = a.UserID 
WHERE a.AccountID = @AcctID
	RETURN
GO



--USERNAME UNIQUNESS CHECK
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_UsernameCheck' AND type = 'P')
   DROP PROCEDURE sp_UsernameCheck
GO
CREATE PROCEDURE [dbo].[sp_UsernameCheck]
	(@UserName nvarchar(25))
AS
DECLARE @result int;
-- IF EXISTS (SELECT 1 FROM Users WHERE UserName = @UserName)
-- SELECT '1'
-- else 
-- SELECT '0'
IF EXISTS (SELECT 1 FROM Users WHERE UserName = @UserName)
set @result = 1
else 
set @result = 0
	
	select @result
GO

--EMAIL UNIQUNESS CHECK
IF EXISTS (SELECT name FROM sysobjects 
         WHERE name = 'sp_EmailCheck' AND type = 'P')
   DROP PROCEDURE sp_EmailCheck
GO
CREATE PROCEDURE [dbo].[sp_EmailCheck]
	(@Email nvarchar(200))
AS

DECLARE @result int;

IF EXISTS (SELECT 1 FROM Users WHERE Email = @Email)
set @result = 1
else 
set @result = 0
	
	select @result
GO


grant execute ON sp_GetAcct to demo;
grant execute ON sp_EmailCheck to demo;
grant execute ON sp_UsernameCheck to demo;
grant execute on sp_Login to demo;
grant execute on sp_CreateUser to demo;
grant execute on sp_DepositToAccount to demo;
grant execute on sp_WithdrawFromAccount to demo;
grant execute on sp_GetTransactionsFromUserID to demo;
grant execute on sp_GetAllTransactionsForAccount to demo;





-- grant execute on sp_DepositFromAccount to demo;


-- UPDATE Account 
-- SET Balance=2001,
-- 	LastModDate= '1997-12-25 12:30:35'
-- WHERE AccountID = 1

-- SELECT * FROM Account





--WITHDRAW

 -- DECLARE @AccountID int = 1;
 -- DECLARE @Ammount money = 5.00;
 -- DECLARE @NowDate datetime = GETDATE();

 -- UPDATE Account 
 -- SET Balance= Balance - @Ammount,
 -- 	LastModDate= @NowDate
 -- WHERE AccountID = 1

 -- INSERT INTO Transact
 -- (AccountID,
 --  Amount,
 --  Remaining,
 --  TransactionType,
 --  TransactionDate)
 -- VALUES
 -- (@AccountID,
 --  @Ammount,
 --  (SELECT Balance
	-- FROM Account
	-- WHERE Account.AccountID = @AccountID),
 --    'withdraw',
 --    @NowDate)

 -- SELECT * FROM Account
 -- SELECT * FROM Transact








-- GET TRANSACTION HISTORY BY USER ID

-- Declare @UserID int = 1

-- select TransactionDate, TransactionType, Amount, Remaining
-- from Transact t
-- JOIN Account a
-- ON t.AccountID = a.AccountID
-- where t.AccountID = 
-- (select a.AccountID 
-- Where a.UserID = @UserID)










-- USE [BankDB];

-- IF EXISTS(SELECT name FROM BankDB.sys.tables 
-- 		  WHERE name = 'Users')
-- 		  DROP TABLE Users 
-- GO








-- select * from Users

-- DECLARE @Email nvarchar(200) = 'andrew.willhoit@gmail.com';
-- DECLARE @result int;

-- IF EXISTS (SELECT 1 FROM Users WHERE Email = @Email)
-- set @result = 1
-- else 
-- set @result = 0
	
-- 	select @result
	
-- select * from Users
-- where Email = 'andrew.willhoit@gmail.com'	
-- 	