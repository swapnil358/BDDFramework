# LabCorp Job Search Automation

This project automates the job search and application verification process on the LabCorp website using Selenium WebDriver, Cucumber, and REST Assured. It is designed to validate both UI and API components using BDD (Behavior-Driven Development) principles.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [Project Structure](#project-structure)
5. [Running the Tests](#running-the-tests)
6. [Feature Files](#feature-files)
7. [Hooks and Step Definitions](#hooks-and-step-definitions)
8. [How to Add New Tests](#how-to-add-new-tests)
9. [Contributing](#contributing)

---

## Project Overview
This project automates the process of searching for jobs on the LabCorp website and verifying job details. It includes API automation for validating data from external endpoints using REST Assured.

### Key Features:
- **UI Automation**: Navigate and interact with the LabCorp website, verify job listings, and job details.
- **API Automation**: Perform and validate GET and POST requests using REST Assured.
- **BDD Approach**: All test cases are written in Gherkin syntax for better readability and maintainability.

## Technologies Used
- **Java** (JDK 7/8 for backward compatibility)
- **Selenium WebDriver**: Automates browser interactions.
- **Cucumber**: BDD framework for writing feature files and step definitions.
- **REST Assured**: Library for API testing.
- **JUnit**: Test runner.
- **WebDriverManager**: Manages browser drivers automatically.
- **Maven**: Build and dependency management.

## Setup Instructions
1. **Prerequisites**:
    - Ensure you have Java (JDK 7/8) installed.
    - Install Maven for dependency management.
    - A reliable IDE such as IntelliJ IDEA, Eclipse, or Visual Studio Code.

2. **Project Setup**:
    - Clone the repository:
      ```bash
      git clone <repository-url>
      ```
    - Navigate to the project directory:
      ```bash
      cd labcorp-job-search-automation
      ```
    - Build the project using Maven:
      ```bash
      mvn clean install
      ```
    - Update dependencies using:
      ```bash
      mvn dependency:resolve
      ```

## Project Structure
labcorp-job-search-automation

```│
├── src
│   ├── main
│   │   └── java
│   │       ├── pages          # Page Object Model classes
│   │       └── utils          # Utility classes (e.g., browser management, config)
│   │
│   └── resources
│       ├── features           # Cucumber feature files
│       └── payloadJson        # JSON payload files for API tests
│
├── src
│   └── test
│       └── java
│           ├── stepDefinitions # Cucumber step definitions
│           ├── hooks           # Hooks for setup and teardown
│           └── apiTests        # REST Assured API tests
│
├── testData                   # Test data files
│
├── pom.xml                    # Maven configuration file
└── README.md                  # Project documentation ```








