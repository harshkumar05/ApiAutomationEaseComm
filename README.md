# API Automation Framework for EaseCommerce

This is an API automation framework developed for the EaseCommerce platform. The framework is built to facilitate automated testing for various APIs, ensuring stability and reliability across different modules.

### Tech-Stack
    Java-17
    Maven-3.9
    Rest-Assured
    Cucumber

## Key Features

- **Page Object Model (POM)** Design Pattern
- Modular approach with well-defined folders and structure
- Easy integration with CI/CD tools like Jenkins
- Detailed reporting and logging
- Supports data-driven testing using YAML files
- Ability to execute tests via command line

## Framework Structure

The framework is organized under the `src/test/java` directory. The structure is divided into four main folders:

### 1. `modules/`
This folder contains the core API modules and pages, where the main validation logic is written. Currently, we have two main modules:
- **Base**: Contains shared logic for common API interactions.
- **Warehouse**: Contains API tests specific to the warehouse module.

Each module follows the **Page Object Model** (POM) design pattern to keep the code clean, maintainable, and reusable.

### 2. `runner/`
This folder contains the **runner file**, which is used to execute tests via command line. It also manages the glue between step definitions and plugins for reporting.
- The runner file configures and runs the tests, integrates with Cucumber for BDD, and handles reporting and logging.

### 3. `utils/`
This folder contains common utility classes that can be used across different modules.
- **Hooks**: Contains application-level hooks that run before and after each test scenario. These hooks manage setup and teardown of the application state.
- **YamlUtils**: Utility class for loading test data from YAML files. This allows data-driven testing and makes the framework flexible and scalable.

### 4. `stepdefinitions/`
This folder contains step definitions written as per modules and feature files. Each module has its own set of step definitions:
- **WarehouseSteps**: Contains step definitions for the warehouse-related features.
- **BaseSteps**: Contains step definitions for the common, shared features.

### 5. `src/test/resources/`
This folder contains resource files for the tests. It is divided into multiple subfolders:

- **environment/**: Stores environment-specific configuration files, such as credentials and host information for different environments like production, staging, etc. For example, `prod.yml` will contain production-specific details like `username`, `password`, and `host`.

  Example `prod.yml`:
  ```yaml
  username: "prodUser"
  password: "prodPassword"
  host: "https://prod.api.easecommerce.com"
  ```

- **features/**: Contains the feature files for different modules, written in Gherkin syntax. For instance, the `warehouse` module will have its own set of feature files under this folder.

- **report/**: Stores the reporting files, including:
    - **Extent Reports XML and Properties File**: For detailed execution reports.
    - **Schema Files**: Used for cross-validating API responses with JSON Schema.


### To Execute the Test Scenarios
    mvn test -D"cucumber.filter.tags=<tag-name>" -Denv=prod
    e.g for running satus code 200 validation
    mvn test -D"cucumber.filter.tags=@sc-200" -Denv=prod

### Example Folder Structure

```bash
src/test/java
├── modules
│   ├── Base
│   └── Warehouse
├── runner
│   └── TestRunner.java
├── utils
│   ├── Hooks.java
│   └── YamlUtils.java
└── stepdefinitions
    ├── BaseSteps.java
    └── WarehouseSteps.java

src/test/resources
├── environment
│   └── prod.yml
├── features
│   └── Warehouse.feature
└── report
    ├── extentReport.xml
    ├── extentReport.properties
    └── responseSchema.json

