# CRUD-Microservice

Employee Microservice – Design Doc
1. Introduction
1.1 Problem Statement:
The Goal is to provide scalable and reliable Microservice APIs for managing Employee entities through CRUD operations capable of serving 100K RPS while ensuring 99.99% reliability.

1.2 Scope
This microservice will handle high request per second (RPS) scenarios with publicly exposed endpoints. It will focus on creating, retrieving, updating, and deleting Employee entities.
1.3 Assumptions
We need a highly scalable database such as MongoDB or Cosmos DB to accommodate the high number of requests.
The Service will use a caching store to cache frequently accessed results to avoid hitting the database too many times.
The APIs are publicly exposed; hence, they require secure authentication and authorization mechanisms such as Azure AD auth or JWT to safeguard against authorized access and data breaches.
The service is deployed across multiple regions so that clients are closer to their region and get the response with low latency.


![image](https://github.com/nkazi09/CRUD-Microservice/assets/70826183/814d05bb-760a-4039-8ebf-e5052553d962)

