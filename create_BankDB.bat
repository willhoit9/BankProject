@ECHO on


ECHO Attempting to create the FarmDB database . . . 
sqlcmd -S localhost\SQLExpress -E /i createBankDB.sql
ECHO.
ECHO If no error message is shown, then the database was created successfully.
ECHO.
PAUSE