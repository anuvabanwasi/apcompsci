Introduction


The objective of this project is to design and implement an application that displays the US flag to scale with dimensions based on the US flag specifications, http://www.usflag.org/flag.specs.html. The application is built using the Java’s graphics libraries such as AWT and Swing (Java docs can be found at https://docs.oracle.com/javase/8/docs/api/ ). Since it is a Java application the project can be executed by running the executable jar file (If the computer supports Java, the application can be run by downloading the jar file by using the command line). To resize the flag hover on the bottom right of the frame and drag it diagonally using a mouse or trackpad.


Usage


1. Download the executable jar file from github to a computer that has java installed
2. Open command line terminal and run the below commands on the command line
   1. cd <path to where jar file is downloaded>
   2. java - jar usflag.jar


What does the program do?


The program consists of 6 classes - Flag.java, FlagConstants.java, FlagUtil.java, Star.java, Stripe.java and Union.java. Flag.java contains the main() method of the application.


Flag.java - The main() method in Flag.java does the following - Creates a new JFrame, obtains a content pane from the JFrame, creates a JPanel which paints the US Flag, adds this JPanel to the contentPane. The paintComponent() method of JPanel gets the initial height and width of the Flag Panel from its parent. This step is critical for the ability to resize all components of the flag relative to the width and height of the panel. It invokes separate methods called paintUnion(), paintStripes() and paintStars() to draw the Union, Stripes and Stars respectively.


Stripe.java - This class contains logic to draw the 13 stripes on the US Flag which are drawn using filled rectangles at specified x and y coordinates with specified width and height.  


Union.java - This class contains logic to draw the canton on the US Flag which is drawn using filled rectangles at specified x and y coordinates with specified width and height. 


Star.java - This class uses trigonometry & geometry to draw a pentagram (star). The critical part of the code is to compute 5 points each on an outer and inner circle. It is important to ensure that the coordinates are generated in a particular order so that when fillPolygon() (in Graphics2D package) is invoked it can connect the coordinates on the outer and inner circles to form a Star shape and then color the Star. 


FlagConstants and FlagUtil - These are helper classes that uses the US flag ratio specs at http://www.usflag.org/flag.specs.html to perform calculations for setting the relative dimensions of the Union, Stripe and Star.


General notes for the program are that it allows the user to resize the US Flag by dragging the window frame diagonally across using a mouse or a trackpad. Java AWT has in built functionality to drag and expand windows but the contents inside the window (in this case the US Flag) needs to be resized using ratios and fair amount of calculations. All the calculations, geometry and trigonometry used to draw the Stars and Stripes are documented in the code comments. It is important to note that the flag dimensions are calculated based on setting the height scale of the flag to 1.0 and computing distances based on the height scale. Also the top right corner of the frame is considered as origin by Java AWT so all coordinates are set based on the x and y distances from the origin.


What the program does not do?


I have tested and debugged the program thoroughly. The application resizes the flag upon dragging. It does not have separate UI controls to resize the window frame. For instance it does not have a slider that expands or contracts the window. Instead it allows the user to resize the window and resize all components of the flag using window drag and expand feature.


Learnings


I learned a lot from this project since I was able to discover how the java.awt.* and javax.swing.* packages can be used to create a GUI interface. Initially, I started off by trying to draw and resize one star in the window frame. I quickly realized that every time I attempted to resize the window the JVM would call paintComponent. I learnt that I had to dynamically configure the flag panel’s width and height by calling its parent JPanel’s getSize() methods and accordingly set the Flag’s width and height. This would ensure that the Union, Stripes and Stars are redrawn with the scaled dimensions every time the window frame is repainted. 

I also learned that I needed to understand coordinate geometry really well in order to populate the x and y coordinate arrays. I also learned to use ratios for all my calculations based on the flag height. Getting the Star right was the hardest part since I needed to understand the polar coordinate system along with cartesian coordinate system. I had to convert degrees to radians and plot the points of the pentagram on an inner circle and an outer circle. Additionally, at first when I tried to draw the star I did not know in what order the points needed to be joined so I just populated the arrays represented the points of the pentagram in sequential order and called fillPolygon() which did not yield the intended result. After some more experimentation and testing, I realized that I needed to populate the even indices of the array and the odd indices of the array separately. The even indices represent points on the outer circle while the odd indices represent points on the inner circle. 

I also learned how to organize my code into classes and methods. This made it easier for me to test my code and make changes to it. The other advantage of modules is when I wanted to make changes, I could restrict it to only the portion of the code that needed editing instead of changing too many parts of the program. In addition, I was able to comment parts of the code that I didn’t want to test and just make changes to the code I wanted to test and run the program since its organized by classes and methods. After I had most of the program working, I went through the code line by line and where I had logic that was being repeated in multiple places I converted it into one method and called the same method from places where I needed to perform that particular piece of logic. I found this to be very useful and am planning to make my programs in future as modular as possible from the beginning instead of putting it off to the end.


Acknowledgements


I would like to thank Mr. Kuszmaul for encouraging us to start planning the project ahead of time so we could work on it in manageable chunks. I would like to thank my coding partner Elaine for brainstorming ideas with me. I also would like to thank my 7 year old younger brother who was always happy to try out my application by resizing the frame and counting the stars and stripes and for asking me questions like, “Why does the US Flag have 50 stars and 13 stripes?”