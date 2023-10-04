# Bank-System-Api
api to manage Bank System provide a basics for managing banking operations.


#### Discover endpoints of bank System
[![Run in Postman](https://run.pstmn.io/button.svg)](https://www.postman.com/satellite-astronaut-99993862/workspace/public/collection/27464457-24459eb2-df4b-4880-ac52-381285783e37?action=share&creator=27464457)

### API URL at render
`https://bank-api-service-iiyh.onrender.com`

### ERD
```mermaid
---
title: Bank Schema
---

erDiagram
accounts {
varchar card_number
varchar cvv
decimal balance
boolean status
datetime created_at
int id PK
}
transactions {
varchar transaction_type
decimal amount
text note
datetime created_at
int id PK
}
users {
varchar user_role
varchar first_name
varchar last_name
varchar email
varchar password
varchar phone_number
varchar address
boolean status
datetime created_at
int id PK
}


users ||--o{ accounts : "has"
accounts ||--o{ transactions : "logs"
```



## Requirements
- Java Development Kit (JDK) 17 or above
- PostgreSQL Database (You can either use a local PostgreSQL instance or connect to a remote one)
- If you are running a local PostgreSQL instance, update database credentials from `application.properties`.

## How to Run
1. Clone the project repository from Git (if it's not already cloned).
2. Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).
3. Build the project to resolve dependencies.


## How to Run Using Maven
To run the Spring Boot application using Maven, follow these steps:

1. Open a terminal (command prompt) window or a terminal within your IDE.

2. Navigate to the root directory of the Spring Boot project, where the `pom.xml` file is located.

3. Build the project using Maven by executing the following command:

   ```bash
   mvn clean package
   ```

   This command will compile the Java code, run tests, and package the application into a JAR file.

4. Once the build is successful, you can run the Spring Boot application using the following command:

   ```bash
   mvn spring-boot:run
   ```

   Maven will start the embedded Tomcat server and deploy your application. You should see logs indicating that the application is running.

5. By default, the application will be accessible at `http://localhost:8080`. Open your web browser and navigate to this URL to access the application.

6. If you need to stop the running application, you can press `Ctrl + C` in the terminal where the application is running. This will terminate the Spring Boot application.

**Note:** Ensure that you have properly configured the properties, such as the database URL, username, and password, in the `application.properties` file before running the application. Also, make sure that your PostgreSQL database is running and accessible before starting the Spring Boot application.

Now, you can interact with your Spring Boot application through the specified endpoints and test its functionality.

## Docker
To dockerize the Spring Boot project using the provided Dockerfile, follow these steps:

1. Make sure you have Docker installed on your machine.

2. Save the provided Dockerfile in the root directory of your Spring Boot project, alongside the `pom.xml` file.

3. Open a terminal (command prompt) window or a terminal within your IDE.

4. Navigate to the root directory of your Spring Boot project, where the Dockerfile is located.

5. Build the Docker image using the following command:

   ```bash
   docker build -t quiz-api:latest .
   ```

   This command tells Docker to build an image with the tag `bank-api:latest` using the current directory (`.`) as the build context.

6. Once the Docker build is successful, you can run the Docker container based on the built image using the following command:

   ```bash
   docker run -p 8080:8080 bank-api:latest
   ```

   This command runs a Docker container based on the `bank-api:latest` image and maps port 8080 from the container to port 8080 on the host machine, allowing you to access the Spring Boot application at `http://localhost:8080`.

7. The Spring Boot application is now running inside the Docker container.

After following these steps, your Spring Boot application should be successfully dockerized and running inside a Docker container.

## How to Contribute

We welcome and encourage developers to contribute to the project and help us make it even better. If you are interested in contributing, follow these steps:

- **Fork the Repository**
- **Make your Changes**
- **Create a Pull Request**
- **Wait for Review**: Your pull request will be reviewed by the project maintainers. Make any necessary changes based on their feedback.

**👏🏻👏🏻 Congratulations! 🎉🎊** Your contribution has been accepted and merged into the main repository. You are now a contributor to the project.

---


## Contact
Ahmed Sakr | [@Ahmed-Mohmed-Sakr](https://github.com/Ahmed-Mohmed-Sakr)