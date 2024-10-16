# ContractForge
**ContractForge** is a Spring Boot API that manages sales contracts and their associated items, providing CRUD operations, filtering capabilities, and status-based transitions.
It includes authentication via JWT tokens and interacts with a PostgreSQL database.

## Table of Contents
- [API Endpoints](#api-endpoints)
- [Building the Project](#building-the-project)
- [Running the Project](#running-the-project)
- [Docker Instructions](#docker-instructions)
- [Curl Examples](#curl-examples)

## API Endpoints

### 1. **GET /api/contracts**

- **Description:** Retrieve all contracts with optional filtering by buyer name and status.

- **Query Parameters:**
  - **buyer:**  (optional): Filter by buyer's name.
  - **status:** (optional): Filter by contract status (CREATED, ORDERED, DELIVERED).

- **Response:**
  - A list of contracts.

### 2. **GET /api/contracts/{id}**

- **Description:** Retrieve a specific contract by ID along with associated items.

- **Response:**
  - Contract details including associated items.

### 3. **POST /api/contracts**

- **Description:** Create a new contract. The contract is created with the status CREATED.

- **Input:**
  - Buyer information and associated items.

- **Response:**
  - Response: The created contract's ID.

### 4. **PUT /api/contracts/{id}**

- **Description:** Update an existing contract, including changing its status (subject to validation rules).

- **Input:**
  - Updated contract data.

- **Response:**
  - Updated contract information.
 
### 5. **DELETE /api/contracts/{id}**

- **Description:** Soft delete a contract. Only contracts with status CREATED can be deleted.

- **Response:**
  - No content.

### 6. **GET /api/items**

- **Description:** Retrieve all items associated with a contract.

- **Response:**
  - A list of items.

### 7. **POST /api/items**

- **Description:** Add a new item to a specific contract.

- **Input:**
  - Item data, including name, supplier, and quantity.

- **Response:**
  - Response: The created item ID.

### 8. **PUT /api/items/{id}**

- **Description:** Update the details of an existing item.

- **Input:**
  - Updated item data.

- **Response:**
  - Updated item information.
 
### 9. **DELETE /api/items/{id}**

- **Description:** Delete an item from a contract.

- **Response:**
  - No content.

## Building the Project

To build the project, run the following command:

      mvn clean install

## Running the Project

      mvn spring-boot:run

## Docker Instructions

To run the application using Docker, follow these steps:
**Build Docker Image:**

    docker build -t contractforge .

**Run Docker Compose:**
  
    docker-compose up


## Curl examples
 (TODO)
  #### 1. Create a new contract
         curl -X POST http://localhost:8080
  #### 2. Retrieve all contracts
         curl -X POST http://localhost:8080
  #### 3. Retrieve a specific contract by ID
         curl -X POST http://localhost:8080
  #### 4. Update a contract
         curl -X POST http://localhost:8080
  #### 5. Delete a contract
         curl -X POST http://localhost:8080
         
