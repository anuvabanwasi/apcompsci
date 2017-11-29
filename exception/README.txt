AP Computer Science: Exception Project - Anuva Banwasi


Introduction:
The purpose of this one week long project is to create a program that demonstrates understanding of exception handling. Last week, I had written a basic program that reads a file and looks to see if one of the lines contains a certain keyword. 


Over the past days, I have added more features to this program. The first feature I added was user-input. The program takes in user input of the file path of the file they would like to store and what topic they would like to store it under. Then, the program reads the file and sees if the keyword the user entered is in the file at least 3 times. If so, it stores the file in a Page object using the Page class I wrote and sets the object’s topic to the user input. If the program does not contain the keyword at least three times, the program tells the user this information and asks if they still want to store the file under that keyword. If the user still confirms yes, then the program stores the file in a Page object  using the Page class and sets the object’s topic to the user input. After entering in several files, the program asks the user what topic they are interested in. The program then prints all of the files/pages that the user previously stored under the entered topic. 


In building this program, I have learned and used many programming concepts including exception handling, encapsulation, iterative loops, etc. The Vocab 11 was incorporated into the project like so:


How I will be meeting the criteria:
Each vocabulary word will be used in this project:
1. Try - I used a try block in taking in a file path for the readFile() method because if the File does not exist, this will throw and FileNotFoundException. 


2. Catch - I used a catch block to catch the FileNotFoundException in the case that the try block throws this exception because the file path entered in by the user does not exist. 


3. Finally - Executes even if there was an error and the exception was handled. In this case it closes the BufferedReader if it is not null.


4. Error - In this process, I was faced with multiple compile time, runtime, and semantic errors. An interesting semantic error I came across was that I had originally made the variable count, that represents the number of times a keyword occurs in a file, an instance variable and increment it every time a file contained the keyword. This was a problem because when determining if a keyword is present in the file more than three times, I used the check count >= 3 and this would be true sometimes even when the keyword was not in the file because it was keeping a running total for all of the files instead of redefining count for each file and calculating the number of times a keyword was within it (which is what it is supposed to do). I fixed this by making count a local variable in the readFile() method so that for each file, the count variable is reset and is an accurate representation of how many times the keyword is in the file.


5. Compile time error -  Some compile time errors that I faced was when I have did not deal with all of the exceptions with try catch blocks and the compiler reported that a certain exception has not been handled. An example of a compile error I came across was when I created a list of Page() objects in one method and then tried to use it in another. Because Java has method scope, this caused a compile time error and I had to move the creation of the list to be an instance variable to fix the problem. 


6. Runtime error - I had to add a catch block to deal with a FileNotFoundExcpetion when the File path entered in by the user was invalid and then it was used to access the file. By writing a catch block to catch this exception, I was able to overcome this error and handle other scenarios besides the working case.   


7. Error correction code - In case the user forgot to enter the path to a file and hits ‘return’ the program re-prompts the user to enter the path to the file name.  If the user again decides to hit enter without giving a filename the program exits with an error code of -1.


Resources:
1.https://stackoverflow.com/questions/5600422/method-to-find-string-inside-of-the-text-file-then-getting-the-following-lines 
        - I used this resource to learn how to read a file going line by line. 
2. https://www.javatpoint.com/exception-handling-in-java
- I used this resource to learn more about exception handling. 
3. https://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html
-  I used this resource to learn more about the catch block
4. https://beginnersbook.com/2017/10/java-string-contains-method/ 
- I used this resource to review the contains() method in Java and used it so that I could look for the keyword within lines instead of just using keyword.equals(scanner.nextLine) which only returns true if the keyword is only a line by itself. 
5. https://www.mkyong.com/java/how-to-read-file-from-java-bufferedreader-example/
* I used this resource to read a file using BufferedReader and implement try, catch and finally statements in java