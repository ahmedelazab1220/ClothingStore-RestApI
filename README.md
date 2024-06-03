# Clothing Store Rest Api Project

This project is a comprehensive solution for managing a clothing store, built using modern technologies to ensure robust performance and security. It provides RESTful APIs for `creating`, `reading`, `updating` , and `deleting` and other operations store-related data.


# Technologies Used

**`Spring Boot (lastest version 3.3.0)`**: A powerful, feature-rich framework for building Java applications.

**`Spring Data JPA`**: Simplifies database interactions by providing a robust data access layer.

**`MySQL`**: A reliable relational database management system used for storing the application's data.

**`Hibernate ORM`**: An object-relational mapping (ORM) framework for Java applications, simplifying database interactions by mapping Java objects to database tables.

**`Spring Security`**: Utilized along with JSON Web Tokens (`JWT`) for secure access to the application's endpoints.

**`Lombok`**: Reduces boilerplate code by providing annotations to automatically generate getters, setters, constructors, and other common methods.

**`Spring DevTools`**: Enhances development experience by providing hot reloading and other useful features.

**`MapStruct`**: Simplifies mapping between layers.

**`Java 21`**: The latest long-term support (LTS) version of Java, offering new features and improvements.

# Features

**`CRUD Operations`**: Supports Create, Read, Update, and Delete operations for managing clothing store data.

**`Secure Access`**: Uses Spring Security to protect endpoints and ensure that only authorized users can perform certain actions.

**`Efficient Data Management`** : Utilizes Spring Data JPA for seamless interaction with the MySQL database.

**`Logical Operations`**: Supports Varitey Queries To Database.

**`Spring Validation`**: Implements validation logic using Spring's validation framework to ensure data integrity and enforce business rules.

**`DTOs (Data Transfer Objects)`**: Utilizes DTOs to transfer data between layers, ensuring a clean separation of concerns.

**`SOLID Principle`**: The project adheres to SOLID principles, ensuring maintainability, scalability, and code readability.

# Project Structure

```

MyClothingStore-Project
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── luv2code
│   │   │           └── demo
│   │   │               ├── ClothingStoreRestApIApplication.java
│   │   │               ├── controller
|   |   |               |   └── AuthenticationController  
|   |   |               |   └── AuthorizationController
|   |   |               |   └── CategoryController
|   |   |               |   └── OrderController
|   |   |               |   └── ProductController
│   │   │               ├── service
│   │   │               |   └── AuthenticationService
│   │   │               |   └── CategoryService
│   │   │               |   └── JwtService
│   │   │               |   └── OrderService
│   │   │               |   └── ProductService
│   │   │               |   └── UserService
|   |   |               |   ├── impl
│   │   │               |       └── AuthenticationServiceImpl
│   │   │               |       └── JwtServiceImpl
│   │   │               |       └── CategoryServiceImpl
│   │   │               |       └── OrderServiceImpl
│   │   │               |       └── ProductServiceImpl
│   │   │               |       └── UserServiceImpl  
│   │   │               ├── repository
│   │   │               |   └── CategoryRepository
│   │   │               |   └── UserRepository
│   │   │               |   └── RoleRepository
│   │   │               |   └── ProductRepository
│   │   │               |   └── OrderRepository
│   │   │               ├── security
│   │   │               |   └── SecurityUser
│   │   │               ├── exc
│   │   │               |   └── CustomAuthenticationEntry
│   │   │               |   └── ErrorResponse
│   │   │               |   └── RestExceptionHandler
│   │   │               |   └── StatusCode
│   │   │               |   ├── Custom
│   │   │               |       └── NotFoundException
│   │   │               |       └── NotFoundTypeException
│   │   │               |       └── QuantityNotAvailableException
│   │   │               ├── entity
│   │   │               |   └── User
│   │   │               |   └── Product
│   │   │               |   └── Role
│   │   │               |   └── Category
│   │   │               |   └── Order
│   │   │               |   └── OrderItem
│   │   │               ├── dto
│   │   │               |   ├── mapper
│   │   │               |       └── SystemMapper
│   │   │               |   ├── response
│   │   │               |       └── JwtResponse
│   │   │               |       └── ProductDtoResponse
│   │   │               |       └── UserDto
│   │   │               |   ├── request
│   │   │               |       └── LoginRequest
│   │   │               |       └── OrderDtoRequest
│   │   │               |       └── OrderItemRequest
│   │   │               |       └── ProductDtoRequest
│   │   │               |       └── RegisterRequest
│   │   │               └── config
|   |   |                   └── SecurityConfiguration
|   |   |                   ├── filter
│   │   │                       └── JwtAuthenticationFilter
│   │   └── resources
│   │       ├── application.properties
│   └── test
│       └── java
│           └── com
│               └── luv2code
│                   └── demo
│                       └── ClothingStoreRestApIApplication.java
└── pom.xml

```

# Installation

  - **`Clone the repository`**:
     
    - git clone https://github.com/ahmedelazab1220/ClothingStore-RestApI.git
    - you can also download Zip file and extract.  
      

  - **`Set up the MySQL database`**:
    
    ```

      spring.datasource.url=jdbc:mysql://localhost:3306/[your_database_name]?useSSL=false
      spring.datasource.username=[your_username]
      spring.datasource.password=[your_password]
      spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      spring.jpa.properties.hibernate.format.sql=true
    
    ```
    ***`note`*** : if you need to create database automatically you can use this `spring.datasource.url=jdbc:mysql://localhost:3306/[your_database_name]?createDatabaseIfNotExist=true` instead of first line

  - **`Build and run the application`**:     
   
    - mvn clean install
    - mvn spring-boot:run


# Entity RelationShip Diagram

  ![ERD](https://github.com/ahmedelazab1220/ClothingStore-RestApI/assets/105994948/f079c908-3228-4dc4-87c8-1dd950db16ec)
 
  also i make database optimization (`Database indexes`) 

# Testing Endpoints By Postman
   
   i make collection and called clothing_store and make subfolders for each parent endpoint (`Controller`)

   ![Postman](https://github.com/ahmedelazab1220/ClothingStore-RestApI/assets/105994948/a7880af1-31bb-40a5-9a6e-00fb39f3f871) 


# Example Request and Response

   **`Request ` Post ` | http://localhost:8080/api/v1/auth/register`**
   
   ```json
    
    {
       "email" : "ahmed@gmail.com",
       "password" : "12345678", 
       "phoneNumber" : "025257975",
       "role" : "MANAGER"
    } 

   ```

   **`Response`**
   
   ```json

    {
       "email" : "ahmed@gmail.com",
       "password" : "12345678", 
       "phoneNumber" : "025257975",
       "role" : {
          "role_id" : 1,
          "role_name" "MANAGER": 
       }
    }

   ```

# License

This project is licensed under the MIT License - see the <a href = "https://github.com/ahmedelazab1220/ClothingStore-RestApI/blob/main/LICENSE"> LICENSE </a> file for details.

# Conclusion

The Clothing Store Management System is designed to be a robust and secure solution for managing a clothing store's inventory and operations. By leveraging Spring Boot, Spring Data JPA, Spring Security with JWT, and adhering to SOLID principles, this application ensures a seamless and secure user experience. Whether you're looking to create, read, update, or delete store items, this system provides the necessary endpoints and functionality to manage your store effectively. Contributions and feedback are welcome to continuously improve and extend the capabilities of this application