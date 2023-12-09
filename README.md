# CRUD-Microservice



Components:
Authentication/Authorization:
Manages user authentication and authorization using Azure AD or JWT.

Load Balancer:
Distributes incoming requests across multiple instances of the microservice for scalability.

Microservice Instances:
Hosts the CRUD operations for Employee entities.
Implements business logic, validation, and interacts with the MongoDB database and catches results for frequent access.

MongoDB Database:
Stores Employee Entities.
Single write region and multiple read regions for read-heavy traffic, to keep latency numbers low.
Utilizes sharding and indexing for efficient retrieval and updates.

Cache:
Redis Cache stores frequently accessed data to improve read performance and reduce latency.
We could alternatively use a Least Recently Used (LRU) cache which effectively removes items from the cache that haven’t been accessed in a long time.
Monitoring and Logging:
Monitors service health, performance metrics, and logs for debugging.
Integrates with external monitoring tools.

3. API Design
Endpoints:
Create Employee:
POST /api/employees
Get Employee by ID:
GET /api/employees/{id}
Get All Employees:
GET /api/employees
Update Employee:
PUT /api/employees/{id}
Delete Employee:
DELETE /api/employees/{id}

5. Test cases
Create (POST /employees)
Positive Test Cases:
Valid Employee Data:
Send a POST request with valid employee data.
Verify that the response status code is 201 (Created).
Confirm the new employee is persisted in the database.
      Negative Test Cases:
            Invalid Employee Data:
Send a POST request with invalid employee data (e.g., missing required fields).
Verify that the response status code is 400 (Bad Request).
Confirm that the employee is not added to the database.

2. Read (GET /employees/{id} and GET /employees)
      Positive Test Cases:
Get Employee by ID:
Send a GET request with a valid employee ID.
Verify that the response status code is 200 (OK).
Confirm that the retrieved employee data matches the expected data.
Get All Employees:
Send a GET request to retrieve all employees.
Verify that the response status code is 200 (OK).
Confirm that the returned list contains the expected number of employees.
      Negative Test Cases:
Get Nonexistent Employee:
Send a GET request with an ID for an employee that does not exist.
Verify that the response status code is 404 (Not Found).
Update (PUT /employees/{id})
Positive Test Cases:
Update Employee Data:
Send a PUT request with a valid employee ID and updated data.
Verify that the response status code is 200 (OK).
Confirm that the employee data is updated in the database.
Negative Test Cases:
Update Nonexistent Employee:
Send a PUT request with an ID for an employee that does not exist.
Verify that the response status code is 404 (Not Found).
Invalid Update Data:

Send a PUT request with invalid update data (e.g., missing required fields).
Verify that the response status code is 400 (Bad Request).
Confirm that the employee data is not updated in the database.
 Delete (DELETE /employees/{id})
Positive Test Cases:
Delete Employee:
Send a DELETE request with a valid employee ID.
Verify that the response status code is 204 (No Content).
Confirm that the employee is deleted from the database.
Negative Test Cases:
Delete Nonexistent Employee:
Send a DELETE request with an ID for an employee that does not exist.
Verify that the response status code is 404 (Not Found).

