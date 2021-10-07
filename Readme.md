# Book Finder Project

![logo](https://www.kindpng.com/picc/m/133-1331656_the-library-png-free-the-library-transparent-images.png)

<h3>Participants</h3>
* Emil Latypov B20-03 (e.latypov@innopolis.university)
* Azamat Shakirov B20-03 (a.shakirov@innopolis.university)
* Bekzat Rakhimbayev B20-03 (b.rakhimbayev@innopolis.university)
* Shohjahon Khamrakulov B20-03 (s.khamrakulov@innopolis.university)
* Arlan Kuralbayev B20-03 (a.kuralbayev@innopolis.university)

<hr>

<h3>Project Description</h3>

<b>Book Finder</b> was developed to organize and manage work of library.
The library system has Database of books and UI to interact with users. 

<b>Library Database</b> stores list of books, which are represented as .csv file. After
each change the actual information is updating and saving in the file. Books contain name, author, 
number of pages, category, year of publication, location and status. To make it easier to find
the book, the application provides the user with information about the shelf, row and column number, 
where book is located. After borrowing the book becomes inaccessible to other users until 
it is returned.

<b>UserInterface</b> is a wrapper, which provides to the user an opportunity to look for 
books of interest to him by <i>book name</i> or <i>author name</i>, borrow available books and
return them and add a new ones by terminal.

<h3>Available features:</h3> 
* Search any book by name or by author
* Get basic information about books such as name, author, category, realized year, page number etc.
* Keep track location of any book by just shelf, column and row number
* Easy to track book's status if book is borrowed by someone or not
* If book status is available, there is an option to borrow
* Return borrowed book by just entering its ID
* Add new book to database

<hr>
<h3>How to use</h3>
<b>Book Finder</b> has user-friendly and easy to use User Interface on terminal.
Limiting user control on Program by applying numbers for 
each command and it helps to use commands like real application buttons. <br>
It is carefully ensured that user can only enter numbers that belong to available commands, 
program keeps giving notifications until user enters valid input. <br>
For example: <br>

```
-----------------------------------------
Welcome to Book Finder
-----------------------------------------
Please choose following commands:
0 -> Search
1 -> Return book
2 -> Add new book
3 -> Exit
Enter number >> 0

-----------------------------------------
SEARCHING!
-----------------------------------------
Please choose following commands:
0 -> Search by Name
1 -> Search by Author
2 -> Go Back
Enter number >> 0

-----------------------------------------
SEARCHING BY NAME
-----------------------------------------
0 -> Go Back
Please choose commands or Search: agile
--------------------------------
FOUND RESULTS:
0 --> Agile Web Development with Rails BY: Sam Ruby, Dave Thomas, David Heinemeier Hansson
1 --> Agile web development with rails: a Pragmatic guide BY: Dave Thomas, David Heinemeier Hansson, Leon Breedt, Mike Clark, Thomas Fuchs, Andrea Schwarz
2 --> Agile.Web.Development.with.Rails.4th.Edition BY: the pragmatic programmers
Choose book from results
Enter number >> 0

-----------------------------------------
BOOK DESCRIPTION
-----------------------------------------
ID: 0
Name: Agile Web Development with Rails
Author: Sam Ruby, Dave Thomas, David Heinemeier Hansson
Category: Web development
Year: 2010
Pages: 472
Location -> shelf: 4 column: 3 row: 3
Status: Available
------------------------------------
0 -> Borrow book
1 -> Go Back
Enter number >> 0
You have successfully borrowed book with id = 0
-----------------------------------------
Welcome to Book Finder
-----------------------------------------
Please choose following commands:
0 -> Search
1 -> Return book
2 -> Add new book
3 -> Exit
Enter number >>
```

etc.

<hr>
<h3>Project structure</h3>
At the beginning of the program, the control is forwarded into <u>UserInterface</u>'s 
<b>startUISession</b> function. <u>UserInterface</u> is responsible for any communications
with user, and each of its function helps with that. It provides laconic interface for user (which is described above).
During it's work it makes requests to <u>LibraryDatabase</u>. It is single-instance
class (implemented by <b>Singleton</b> pattern), which stores list of Books inside
and provides methods to manipulate them and update database permanently (even in the disk).
LibraryDatabase is inherited from <u>Database</u> class, which works with 
the database file (.csv format) in low level and provides high-level methods 
to manipulate data, doesn't matter which fields are stored in table. Main structure
we are working with in out program is class <u>Book</u>. It stores all the data
about book. Auxiliary class <u>Location</u> represents location of the book. Enum 
<u>Status</u> shows if book is free (<i>STATUS_AVAILABLE</i>) or borrowed (<i>STATUS_UNAVAILABLE</i>).


<hr>
<h3> Why did we use Singleton?</h3>
Singleton pattern is used to design our Library Database. It should 
be ensured that from any place of our program we are working with the same
instance of database - doesn't matter if it is called from UserInterface or Book class.
In this task we used Classic Singleton implementation

