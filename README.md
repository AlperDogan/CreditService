# Example Credit Service with Spring Boot Mongodb

## To run
Clone this repository and simply run `docker-compose up`.

## Explanation
This project is developed by Spring Boot, MongoDB and Docker technologies.

It has three Entities(DB Documnets)
1)Customer
2)CreditScore
3)CreditLimit

Initial assumed data of CreditScore DB save with project startup.

Main method of project is apply method of CreditLimitController,
This method takes Customer args(identiyNumber,name,surname,phoneNumber)
if this Customer that has identiyNumber isn't exist, it will create or if isn't exist,it update.
then releated creditScore is taken, 
creditLimit calculate and save CreditLimit record.

Note: It was assumed that a message was sent with the relevant phone number.
Note2: If creditScore is between 500-1000 and mountly salary more than 5000, credit limit is assumed to be 20000.(Unspecified in question)

Unit Tests are developed for CreditLimitService and CustomerController that has logic.
