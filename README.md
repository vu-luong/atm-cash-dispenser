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
  - Highly maintainable source code
  
### 3. Algorithms
  - Sort in ascesding order the cash items regarding their remaining amounts
  
  - Represent the problem by a graph
  
  ![Screenshot from 2022-07-15 23-01-59](https://user-images.githubusercontent.com/8142030/179228328-c416f1b2-98a8-4737-82c9-1671c8532712.png)
  
  - Use DFS to find all paths in the graph corresponding to valid cash combinations
  
  ![Screenshot from 2022-07-15 23-06-56](https://user-images.githubusercontent.com/8142030/179229167-05583b0f-924b-457a-afc5-e22ce694ae93.png)
  
  - Output the path that has the maximum value of the lowest remaining amount. For example, a user requests to dispense $150, there are two solutions: **(1)** {0 x $5, 1 x $100, 1 x $50}, and **(2)** {0 x $5, 0 x $100, 3 x $50}
    - If solution 1 is used, the remaining amount is {1 x $5, 1 x $100, 2 x $50} => lowest_amount(1) = 1
    - If solution 2 is used, the remaining amount is {1 x $5, 2 x $100, 0 x $50} => lowest_amount(2) = 0
    - lowest_amount(1) > lowest_amount(2) => solution 1 is better than solution 2

### 4. Discussions
  - The time complexity of the algorithm is O(N1 * N2 * ... * N11), where N1, N2,..., and N11 is the amounts of 5-cent coin, 10-cent coin,..., and 100-dollar note, respectively.
  - We can speed up the search procedure by parallelization: split the graph into multiple parts, and then perform different searches on those parts separately, as shown in the following figure:
  
  ![Screenshot from 2022-07-15 23-19-54](https://user-images.githubusercontent.com/8142030/179231270-cdcb0309-2934-42b5-8ec5-61c1c1bec880.png)

