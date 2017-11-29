Link to GitHub: https://github.com/anuvabanwasi/apcompsci/tree/master/sorting




Anuva Banwasi - AP Computer Science
In this assignment, I wrote a program for each of three sorting algorithms: Bubble Sort, Insertion Sort, and Selection Sort. 


Bubble Sort: The bubbleSort() method works by having a for nested within another for loop, both of which have a counter initialized to 0 that increments each time by one and goes through the loop while the counter < array length - 1. The algorithm compares the value of one index to the value of the index next to it - if the value at the second index is less than the value at the first index, the method calls the swap method on the two indices. This process creates an invariant that at iteration i in an ​array ​A[1..n], ​array ​elements ​from ​1 ​to ​i (A[i..i])are ​in ​sorted ​order. Larger elements will eventually "bubble" to the end of the array. This process has an order of growth of n^2 as it pushes the "last" element to the end in k steps and repeats this process n times. Though it may not be as efficient as other algorithms, Bubble Sort is quite intuitive and good starting point for other algorithms. 


Insertion Sort: The insertionSort() method works differently than the bubbleSort() method though it still uses a for loop nested within a for loop. In this case, after swapping two elements, the program enters a for loop where it checks if the swapped element can be swapped further within the previous indices of the array. Thus, this method inserts the element into its correct position the first time around. In short, it puts the "next" item from the unsorted portion into its correct position in the sorted portion in at most k steps. Usually, insertion sort ends up doing this algorithm in less than k steps and therefore, can be faster than bubble sort. The array [3, 1, 4, 1, 5] will take 5 steps using Insertion Sort compared to 14 steps using Bubble Sort. However, in the worst case, insertion sort may take just as many times as bubble sort and both algorithms have an order of growth of n^2. 


Selection Sort: Selection Sort is somewhat similar to Bubble Sort, but it works to find the most extreme element in the unsorted partition and swap it with the "first" of the sorted partition. The partition itself is then incremented so that it is between the boundary between the sorted portion and the unsorted portion of the array. The selectionSort() method in this case finds the minimum value in the unsorted portion using the findMin() method and then swaps the index of the minimum value with the "first" element in the sorted portion with the call swap(arr, i, indexOfMin) where i is the counter in the outer loop. This process also has an order of growth of n^2 as there is a for loop nested within a for loop. Selection Sort is usually more efficient than Bubble Sort although it is possible that in the worst case scenario, they will take the same time. It is usually not very effective when dealing with larger arrays - which can be sorted easier by algorithms like Merge Sort. 


Learnings: I found this project to be extremely interesting because I was able to apply the concepts we learned in class about the sorting algorithms to actually writing this sorting algorithms in code. I was also able to trace the algorithms this way through testing my code, which helped me understand the process and O notations of the algorithms. 


Resources: Some resources that I used for this project include - 
https://www.youtube.com/watch?v=WaNLJf8xzC4 (Ted Talk on Sorting Algorithms using books)
https://www.khanacademy.org/computing/computer-science/algorithms/insertion-sort/a/insertion-sort (Khan Academy article on Insertion Sort)
https://www.youtube.com/watch?time_continue=50&v=xWBP4lzkoyM (Video on Selection Sort)
http://www.geeksforgeeks.org/insertion-sort/ => I wrote my own version of Insertion Sort and also had a second version from this website
http://www.geeksforgeeks.org/selection-sort/ (article on selection sort)