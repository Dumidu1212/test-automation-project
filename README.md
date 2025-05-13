# Test Automation Project

This is a Spring Boot-based test automation project for demonstrating:
- Functional Testing (User Registration, Login)
- API Testing (Retrieve User Profile)
- Database Testing (Persistence Verification)

## Folder Structure

- `registration/` - Manual vs Automated Testing
- `login/` - Functional Login Tests
- `profile/` - API Testing
- `database/` - Database Verification

test-automation-project/
            │
            ├── 📁 src/
            │   ├── 📁 main/
            │   │   └── 📁 java/
            │   │       └── com/
            │   │           └── sliit/
            │   │               └── testing/
            │   │                   ├── 📁 registration/       ← Kasundi - User Registration (Manual vs Automated)
            │   │                   │   ├── RegistrationController.java
            │   │                   │   ├── RegistrationService.java
            │   │                   │   └── User.java
            │   │                   │
            │   │                   ├── 📁 login/              ← Dewhara - User Login (Tool Evolution)
            │   │                   │   ├── LoginController.java
            │   │                   │   ├── LoginService.java
            │   │                   │   └── Credentials.java
            │   │                   │
            │   │                   ├── 📁 profile/            ← Anjana - Retrieve User Profile (API Testing)
            │   │                   │   ├── ProfileController.java
            │   │                   │   ├── ProfileService.java
            │   │                   │   └── Profile.java
            │   │                   │
            │   │                   └── 📁 database/           ← Dumidu - Verify Persistence (Database Testing)
            │   │                       ├── UserRepository.java
            │   │                       └── DBUtils.java
            │
            ├── 📁 src/
            │   ├── 📁 test/
            │   │   └── 📁 java/
            │   │       └── com/
            │   │           └── sliit/
            │   │               └── testing/
            │   │                   ├── 📁 registration/
            │   │                   │   └── RegistrationTest.java
            │   │                   │
            │   │                   ├── 📁 login/
            │   │                   │   └── LoginTest.java
            │   │                   │
            │   │                   ├── 📁 profile/
            │   │                   │   └── ProfileApiTest.java
            │   │                   │
            │   │                   └── 📁 database/
            │   │                       └── PersistenceTest.java
            │
            ├── 📁 docs/
            │   ├── assignment-report.pdf     ← Group PDF report (Part A)
            │   └── screenshots/              ← Screenshots of test execution
            │
            ├── 📁 logs/
            │   └── execution.log             ← Log outputs from test runs
            │
            ├── 📄 pom.xml                    ← Maven configuration with JUnit, Mockito, etc.
            └── 📄 README.md                  ← Overview of project + member responsibilities

---

## 👥 Team Responsibilities

| Member   | Role Description                                | Module Tested           |
|----------|--------------------------------------------------|--------------------------|
| Kasundi  | Manual vs Automated Testing                     | User Registration        |
| Dewhara  | Evolution of Test Automation Tools              | User Login               |
| Anjana   | Importance of Test Automation                   | API: Retrieve Profile    |
| Dumidu   | Challenges in Test Automation                   | DB: Data Persistence     |

---

## 🛠 Technologies Used

- Java 17
- Spring Boot
- JUnit 5
- Maven
- Spring MockMvc
- RestAssured (for API testing)
- H2 / MySQL
- JPA / Hibernate

---

## ✅ How to Run Tests

### Prerequisites

- Java 17+
- Maven installed

### Steps

```bash
# Clone the repo
git clone https://github.com/   /test-automation-project.git
cd test-automation-project

# Run all tests
mvn clean test

 
