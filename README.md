# 💰 JPMorgan Midas Core Simulation (Forage)

This is a backend simulation project completed as part of the **JPMorgan Chase Software Engineering Virtual Experience** on Forage.

The Midas Core system processes real-time financial transactions using **Apache Kafka**, validates and updates user balances using a **relational database**, communicates with an **external incentives API**, and exposes a **REST API** for querying user balances.

---

## 🔧 Technologies Used

- Java 17
- Spring Boot
- Apache Kafka
- Spring Data JPA
- H2 In-Memory Database
- REST APIs with Spring MVC
- Maven
- Embedded Kafka (for testing)

---

## 🧠 What I Learned

- Setting up a Spring Boot microservice with Kafka integration.
- Validating and processing streaming financial transactions.
- Using Spring Data JPA for entity persistence.
- Connecting to external APIs using RestTemplate.
- Designing and testing RESTful endpoints.
- Writing integration tests using an embedded Kafka instance.
- Debugging and tracing transaction flow in real-time.

---

## 🧱 Features

| Feature                         | Description                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| Kafka Listener                  | Listens to a topic and receives incoming financial transactions.           |
| Transaction Validation          | Ensures sender and recipient are valid and sender has enough balance.      |
| H2 Database Integration         | Stores users and transaction records with relational mappings.             |
| Incentive API Integration       | Calls external API to get incentive rewards for each valid transaction.    |
| REST API for User Balance       | `/balance?userId=X` returns JSON response with user balance.               |
| Full Test Coverage              | Each step is verified using structured test classes (TaskOneTests → TaskFiveTests). |

---

## 🧪 How to Run

### ✅ Prerequisites

Make sure you have the following installed:

- Java 17
- Maven (or use the Maven Wrapper `./mvnw`)
- Git
- An IDE like IntelliJ or VS Code

---

### 1. Clone the Repository

```bash
git clone https://github.com/trisid12/forage-jpmc-midas-core.git
cd forage-jpmc-midas-core
```
### 2. Start the Incentive APi
```bash
cd services
java -jar transaction-incentive-api.jar
```
Run the Spring Boot main Application

Access the API at
http://localhost:33400

Query Use Balance
GET http://localhost:33400/balance?userId=1


This project was completed as part of the JPMorgan Chase Software Engineering Virtual Experience.

