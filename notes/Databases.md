# Databases
- can perform aggregated mathematical calculations on columns
- like a special type of spreadsheet
- however differneces are that a spreadsheet can only be opened up and used by one person at a time - primary use case
- databases typically intended to be used by multiple programmes or multiple people simultaneously
- spreadsheets are much more limited in the amount of data they can handle in any given time
- typically that limitation may have somethign to do with how much memory is in a computer or how much data you can squeee into one givn file
- most professional databases dont have same types of restrictions
- databases specialise in handling massive amounts of data, in the form of records, and in terms of the number of columns you can have per records as well, and actualyl even going further than that, databases can typcialyl contain numerous tables, and you can relate the data from one table to another table, as long as they have some data in common between them
- several types of db in common use today
- most popular, and probably oldest type is relational database.
- reltaional db most similar to a spreadsheet: a record with columns forming tables, and then you can have multiple tables, and those tables can relate to each other.
- other types of db that are not table and relationally based (as we see with a typically spreadsheet)
- Object databases - have a lot in common with what we have been ding with Java code: we create classes and from those classes we create objects. Can retrieve the objects basically int he same form that they were in when we put them in, whereas with relational dbs and some other types, a translation typically has to be done by the programmer to transalte thte object from Java objects into somethign that is usually a little more flat, like in a spreadsheet
- NoSQL databases
- At its most basic level a db is really just a computer program - it's a specialised program that knows how to take in and return massive amounts of data. These applications have to be installed on out computer somewhat similarly to how any other type of application would need to be installed
- Databases dont usually come with a GUI, that's not what their real purpose is, instead they specialise in taking in a lot of data, and returning that data, typically to other programs that are called database clients.
- those clients may in turn present user with a GUI or they may not, they may just process the data and then do somethign with it
- most databases are installed on computers. For busness purposes those computers are often dedicated cmputers that primarily specialise in running just the db software, and typically computers that are used in that way are reffered to as 'servers'
- if you have a server that is primarily or exclusively running a database application then it is common to hear the server itself referred to as 'the database' - this is why the term database can be use somewhat interachanngeably in the business world - as the db software, as well as the computer itself
- additionally these computers or servers will typucallly be setup on a network so that they can be interacted with by other computers on the same network. THese other computers that may connect to the database would be considered clients. And from this we get a common cpmputing pattern called 'client-server'
- the clients may request data from the server and then the server by way of the db application will look up that data, typically records if its a relational db, and return those records to the client, the client can then process those records or display them on the screen for the user to interact with, or anything other thing like that.
- With this type of client-server archtiecture it si possible to allow millions of individiaul users to access and interact with the same set of data, hosted on just one db serve. 
- It is also possible to group a set of database servers together into what is called a cluster, thereby enabling the cluster to act as one really massive, even larger virtual database server, which can server even  more simulatenous users
- access times of microseconds, even nano seconds.
- SQL programming language - Structured Query Language - invented in early 70's. Based on the mathmatics of Sets or Set Theory. 
- SQL enables relational db clients to insert and retrieve massive amounts of data very quickly becuase the db server can interpret the sql language commands that are sent from clients to it, to figure out what records it needs to quickly retrieve and then returnt to the clients.
- Much of the functioanlity of the Streams API is really just mimicking capabalities that were enabled by the combination of the SQL programmign language and dbs for decades: being able to retrieve lots of records, sort them, order, filter, group them by various columns, reducing down to summaries, or averages, all of these types of operations have been in existence with SQL and relational dbs for decades.


```sql
CREATE TABLE PEOPLE(ID BIGINT AUTO_INCREMENT, FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255), DOB TIMESTAMP, SALARY DECIMAL);

INSERT INTO PEOPLE (FIRST_NAME, LAST_NAME, DOB, SALARY) VALUES ('Jake', 'Smith', '1978-05-01 19:13:43', 120000.00);

SELECT * FROM PEOPLE;

SELECT * FROM PEOPLE WHERE FIRST_NAME LIKE 'J%';

SELECT * FROM PEOPLE WHERE SALARY > 25000;

SELECT * FROM PEOPLE WHERE DOB < DATE '1980-01-01';

SELECT * FROM PEOPLE ORDER BY FIRST_NAME;

SELECT * FROM PEOPLE ORDER BY FIRST_NAME DESC;

SELECT * FROM PEOPLE ORDER BY DOB DESC, FIRST_NAME DESC;

SELECT SUM(SALARY), LAST_NAME FROM PEOPLE GROUP BY LAST_NAME;

SELECT LAST_NAME, CAST(AVG(SALARY) AS BIGINT) FROM PEOPLE GROUP BY LAST_NAME;

SELECT LAST_NAME, COUNT(*) FROM PEOPLE GROUP BY LAST_NAME;

SELECT LAST_NAME, COUNT(LAST_NAME) FROM PEOPLE GROUP BY LAST_NAME;

SELECT UPPER(LAST_NAME), FIRST_NAME, DOB FROM PEOPLE;

SELECT CONCAT(LAST_NAME, ', ', FIRST_NAME) AS NAME, DATEADD(YEAR, 10, DOB) AS NEW_DOB FROM PEOPLE;

SELECT '1970-10-31';

SELECT REGEXP_REPLACE('1970-10-31', '\d{4}-', 'aa-');

UPDATE PEOPLE SET FIRST_NAME='Sabrina' WHERE FIRST_NAME='Susan';

UPDATE PEOPLE SET LAST_NAME='Jonson' WHERE LAST_NAME='Johnson';

DELETE FROM PEOPLE WHERE LAST_NAME='Smith';

ALTER TABLE PEOPLE ADD COLUMN EMAIL VARCHAR(255);

UPDATE PEOPLE SET EMAIL='nothing@nowhere.com';

ALTER TABLE PEOPLE DROP COLUMN EMAIL;

ALTER TABLE PEOPLE ADD COLUMN DELETE_FLAG BOOLEAN;

UPDATE PEOPLE SET DELETE_FLAG=FALSE;

UPDATE PEOPLE SET DELETE_FLAG=TRUE WHERE LAST_NAME='Smith';
```

- h2 is written in Java most likely uses standard Java expression engine. 
- in business and pro programming almost certainly won't be using H2
- IN BUSINESS most popular database engines by far are either Oracle or MSSQL Server or DE2 from IBM
- These are commercial database engines used at enterprise level
- h2 can be used in etnerprise for running tests because it can run in memory really quickly, but never for production deployment where there could be 100000000's of customers actually needing to do things.
- other open source database engines are Postgres or MySql - heavily robust, used to support massive amounts of data and sophisticated features like clustering.
- Be careful using the DELETE command or when dropping things in SQL especially if operating on corporate data.
- Typically in any corportion or company they are not going to allow the developers to have access tot he production database servers, so that we can just willy nilly be executing commands of this sort against the database.
- typucally instead they'll have you write out a SQL script and then have you test that script out against test data on a clone database or a test database, something that isnt in their final production place, where real business is getting done.
- there may be DBAs that have to look over the script and approve it and the DBAs may be the ones who actually run the script, and then only after they've confirmed that everything is safe then maybe your script gets to run in production.
- a lot of more lean and agile companies might not have that level of checks and balances to go through, but in one way or another you better be testing out your commands on test data or some other environment before deciding to roll it otu to rpoduction data.
- a pattern that you'll see frequently in business, instead of deleting records, a lot of times companies will add a column that just shows that a record is supposed to act as if it was deleted.
- other common columns you'll see in a business context would be additional date fields to show when was the last time we updated or deleted one of these records, for auditing purposes.
- vast majority of Java applications really earn their pay is in accessing large amounts of data that most businesses that most businesses happen to have.