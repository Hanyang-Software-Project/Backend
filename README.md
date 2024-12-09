# ZIGGS Backend 

This repository contains the backend for **ZIGGS**, an audio-based anomaly detection system designed to enhance home safety. The backend handles user data, processes audio anomaly alerts, and integrates with machine learning models and IoT devices. Built with **Spring Boot**, it connects to a **PostgreSQL** database hosted on **AWS RDS** and integrates **Firebase** for authentication and notifications.

## 🚀 Running the Project Locally

The backend APIs are already hosted on an **AWS EC2** instance, so you can access them without running the project locally. However, if you'd like to run the backend locally for development or testing, follow the steps below.

## 🛠️ Prerequisites

Before running the project locally, ensure you have the following:

- **Java 17**: Install the Java Development Kit (JDK) version 17.
- **Maven**: Install Apache Maven for dependency management.
- **PostgreSQL**: Set up a local instance or use the RDS database credentials.
- **Configuration Files**:
  - `application.yml`: Contains database and other configuration details.
  - `serviceAccountKey.json`: Required for Firebase authentication.

Both files are located in the `src/main/resources` directory.

📧 **Contact us** via the email provided on the main page to obtain these files and credentials.

## 📋 Configuration

### Firebase

Ensure the `serviceAccountKey.json` file is correctly placed in the `src/main/resources` directory. The application expects this file in this location to enable Firebase authentication.

### Application Properties

Ensure the `application.yml` file is placed in the `src/main/resources` directory with the following structure:

```yaml
spring:
  datasource:
    url: <your-database-url>
    username: <your-database-username>
    password: <your-database-password>
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      idle-timeout: 600000
      connection-timeout: 30000
      max-lifetime: 1800000

  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: update

  docker:
    compose:
      enabled: false

  web:
    multipart:
      enabled: true
      max-file-size: 10MB
      max-request-size: 10MB

amazon:
  access-key: <your-amazon-access-key>
  secret-key: <your-amazon-secret-key>
  bucket-name: <your-s3-bucket-name>
  region: <your-aws-region>
  ```

## 🖥️ Steps to Run

1. **Clone the Repository**

  ```bash
  git clone https://github.com/Hanyang-Software-Project/Backend
  cd Backend
  ```


2. **Add Config Files**

  - Place `serviceAccountKey.json` in the `src/main/resources` directory.
  - Ensure `application.yml` is properly configured and placed in the same directory.

3. **Install Dependencies**

  ```bash
  mvn clean install
  ```

4. **Run the Application**

  ```bash
  mvn spring-boot:run
  ```

5. **Access the APIs**

  - The backend will run locally on [http://localhost:8080](http://localhost:8080).
  - You can use tools like **Postman** or **curl** to test the endpoints.


## 📧 Need Help?

If you encounter any issues or have questions, feel free to **contact us** via the email provided on the main page.


