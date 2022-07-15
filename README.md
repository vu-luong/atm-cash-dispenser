# ATM Cash Dispenser

Coding practical assignment for ALL AGED CARE

![Screenshot from 2022-07-15 16-43-59](https://user-images.githubusercontent.com/8142030/179166832-8c904163-eee6-4cc5-ab5d-b68df16ec0f5.png)

# How to run?

1. Open project in IntelliJ: Open -> Choose folder containing the source code -> Wait for IntelliJ to download dependencies and to index the project.
2. Navigate to `src/main/java/com/vuluong/atmcashdispenser/AtmCashDispenserApplication.java` -> right click this file and select `Run AtmCashDispenserApplication`
3. Open a browser and visit http://localhost:8080 to see the web application

# Details

### 1. Technologies: 
  - **Java Spring for** backend
  - **Thymeleaf** for server-side rendering client
  - **RESTful APIs** for communication between frontend and backend
  - **Bootstrap 5** for responsive UIs
 
### 2. Features:
  - Amount initialization for all Australian denominations and coinage
  - Update the amounts after initialization
  - Withdrawal cash
  - Optimization to dispense combinations of cash that leave options open
  - Threshold notification
  
### 3. Algorithms
  - Sort in ascesding order the cash items regarding their remaining amounts
  
  - Represent the problem by a graph
  
  - Use DFS to find all paths in the graph corresponding to valid cash combinations
  
  - Output the path that has the maximum value of the lowest remaining amount.

### 4. Discussions
  - The time complexity of the algorithm is O(N1 * N2 * ... * N11), where N1, N2,..., and N11 is the amounts of 5-cent coin, 10-cent coin,..., and 100-dollar note, respectively.
  - We can speed up the search procedure by parallelization: split the graph into multiple parts, and then perform different searches on those parts separately, as shown in the following figure:
  
