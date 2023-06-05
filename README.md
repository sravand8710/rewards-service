Rewards Service can be started from an IDE by running `Application.java` or through maven `mvn spring-boot:run`

It has 3 endpoints  
- /transactions : returns all the transactions within date range (default is 3 months)  
Examples :  
  http://localhost:8080/retail/transactions  
  http://localhost:8080/retail/transactions?startDate=2022-01-01&endDate=2023-06-04


- /rewards : returns rewards for all customers with transactions within the date range (default is 3 months)  
Examples :  
  http://localhost:8080/retail/rewards  
  http://localhost:8080/retail/rewards?startDate=2022-01-01&endDate=2023-06-04


- /rewards/{customerId} : returns rewards for a customer with transactions within the date range (default is 3 months)  
Examples :  
  http://localhost:8080/retail/rewards/frederickford  
  http://localhost:8080/retail/rewards/frederickford?startDate=2022-01-01&endDate=2023-06-04
