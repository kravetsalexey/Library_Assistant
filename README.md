Library Assistant

Description:

The Library Assistant project aims to develop a system that assists librarians in managing books, users, and book loans within a library. It provides functionalities such as adding new books, registering users, and tracking book loans. The system aims to streamline library operations, improve user experience, and ensure efficient management of library resources.

Features:

Book Management: Allows librarians to add new books to the library database, update existing book details, and remove books from circulation if necessary.
User Registration: Provides functionality for registering new users, updating user information, and managing user accounts within the library system.
Book Loan Tracking: Enables librarians to track book loans, including loan duration, due dates, and overdue notifications. It also allows users to check their loan status and history.
Search and Filtering: Offers search and filtering capabilities to find books based on various criteria such as title, author, genre, and availability.
Reporting: Generates reports on book loans, user activity, and inventory status to provide insights into library usage and trends.

Technologies Used:

Java: The backend of the application is built using Java programming language, providing robustness and scalability.
Spring Boot: Utilized for rapid development of RESTful APIs and backend services, simplifying configuration and deployment.
Spring Data JPA: Used for interacting with the database, providing seamless integration with the underlying data store.
Hibernate: Provides object-relational mapping (ORM) capabilities, enabling the application to work with relational databases using Java objects.
MySQL: Chosen as the database management system for storing and managing library data efficiently.
JUnit: Employed for writing unit tests to ensure the reliability and correctness of the application's components.
Mockito: Utilized for creating mock objects and simulating behavior in unit tests, facilitating isolated testing of components.
Swagger: Integrated for API documentation and exploration, enabling developers to understand and interact with the application's RESTful endpoints easily.

Getting Started:

Prerequisites:

JDK (Java Development Kit)
Maven
MySQL Server

Setup:

Clone the repository to your local machine.
Configure the MySQL database settings in the application.properties file.
Build the project using Maven: mvn clean install.
Run the application: java -jar target/library-assistant-3.2.4.jar.

Usage:

Access the application through the provided endpoints, as documented in the Swagger UI.
Use the provided API endpoints to interact with the application, such as adding books, registering users, and managing book loans.
