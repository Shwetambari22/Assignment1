Problem Statement

Write a spring boot application that maintains an Inventory of Employee data .
1.	API to accept uploading of data as a flat file with line separated data
  a.	POST /api/employee?action=upload should accept a file 
  b.	FILE  CONTENT is lines of names and age – example 
    GANGULY  32
    SACHIN TEND    44
  c.	The file may have upto 1000,000 data points 
  d.	POST api to upload file should return a task identifying this processing 
  e.	Task should have a get status API to track the completion success or failure of the task
  f.	When the processing is done the data should be persisted in the database.
2.	CRUD API for employee objects

Solution

1. Clone the inventory repo @ https://github.com/Shwetambari22/Assignment1.git
2. Import maven project "inventory"
3. MySQL DB is used. To set up the schema, run scripts in folder "db_scripts_inventory"
4. In application.properties set root_path = local directory path
5. Run the app
6. It can be tested via postman
7. The endpoints available are:
  GET
  
  http://localhost:8081/api/employee/{id}
  
  http://localhost:8081/api/employee/all
  
  http://localhost:8081/api/employee/uploadstatus/{taskid}
  
  POST
  
  http://localhost:8081/api/employee
  
  payload:
  {
    "firstName": "JACK",
    "lastName": "SMITH",
    "age": 45
  }
  
  http://localhost:8081/api/employee?action=upload
  Other settings in postman: Body->form-data => key: file value: <upload file>, key: name value: filename
  
  PUT
  
  http://localhost:8081/api/employee
  
  payload:
  {
    "id": 1,
    "firstName": "JACK",
    "lastName": "SMITH",
    "age": 45
  }
  
  DELETE
  
  http://localhost:8081/api/employee/{id}
  
  
  
