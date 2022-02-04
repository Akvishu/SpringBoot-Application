# SpringBoot-Application
Spring boot application with CRUD operation of Employee. In it , I have developed restful APIs to create, read, update and delete employee. I have used Spring JDBCTemplate for database connectivity.

##Steps to access this project:

Step 1 - Open project url and clone the project on local machine.

Step 2- Open command line and go to project folder, then run below maven command to run
  >> ""
  
  
3- Apllication is up please by visiting url : 
  >> "http://localhost:8080/h2-ui"  to access in memory database call this url And see the login credentials in project "Application.properties" file
  
  PostMapping >> "http://localhost:8080/api/employees" Run this url in Postman with Post method to add new employee
  GetMapping >> "http://localhost:8080/api/employees" Run this url in Postman with Get method to see all the employees details
  GetMapping>> "http://localhost:8080/api/employees/{id}" Run this url in Postman with Get method to see any specific employees details by providing employee id in url.
  PutMapping>> "http://localhost:8080/api/employees/{id}" Run this url in Postman with Put method to update employee details by providing employee id in url.
  DeleteMapping>> ""http://localhost:8080/api/employees/{id}" Run this url in Postman with Delete method to delete employee details by providing employee id in url.
  
