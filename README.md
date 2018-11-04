# VisaProblems

Binary Tree Serialization

1. Simple main file to prove correctness of code.  Decided to use a main file rather than a unit test.  But this can as easily be written as an regression unit test
2. When deserializing, I need to know the type to interpret the file contents. Alternatively, I could have added metadata to the file to identify data types etc.  For the purpose of this exercise, I am assuming the tree is an integer tree
3. I have assumed that this is a binary tree and not a binary search tree
4. I could have used a framework like lombok to add setters/getters but didnt because if you dont it will need some set up.
5. For the tree, I have written a generic class
6. I could have used a logger but for the purposes of this exercise, I have just used a simple sysout
7. For serialization, I have assumed that the tree is a full tree with nulls for all missing nodes.  Leaf nodes are written as null.  This is not very efficiency from a space perspsctive if the tree is not a full tree.  Alternatively, I could have also used a bit map to specify the structure of the tree and then written the numbers in series. For example, lets say we have a three node tree with root of 10, null left node and 12 as right child.  Then we can write a bit map such as 101 and then 10 12.  This would have been more space efficient.  But that is a tad harder to write. Hence, I have taken the easier approach

Meeting Point

1. I am using an array of integers for coordinates (instead of double)
2. I am using two arrays to take the input - one the coordinates of the houses and the second the coordinates of the restaurants
3. I am using both arrays are sorted.  If not, we can sort them in O(n log n) + O (m log n) time
4. I have written both the brute force solution and O (M + N) solution
Keep track of total number of houses to the left side of the restaurant and to the right side.  Move to the next restaurant.  See how many restaurants move from the right side to the left side.  For these houses alone compute the additional or reduced total distance.  So, for example, if one house moves from the right to the left. Then, we can compute the difference in distances as how much the total distance increases by or decreases by.  For houses that remain on the same side, the additional distance is simply the difference between the number of house on the left side multiplied by the distance between the two restauratns and the number of houses on the right side multiples by the distance between the two restaurants.
5. The output of the code should illustrate this better