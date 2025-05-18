# Test Automation Framework [Foodics Task]

## 1. Project Overview

This is a **hybrid test automation framework** that supports testing across:

* **Web platform** (e.g., [Amazon Egypt](https://www.amazon.eg/-/en/ref=nav_logo))
* **Mobile applications** (Android/iOS)
* **REST APIs** (e.g., [Reqres API](https://reqres.in/))

### Use Cases:

* **API Testing:** Performs CRU operations on the `/users` endpoint of `https://reqres.in/`
* **Web Testing:** Adds products priced between **10,000 and 15,000 EGP** to the cart and verifies total cart price.
* **Mobile Testing:** Covered using Appium-based test cases.

---

## 2. Technology Stack

* **Java 17**
* **Maven**
* **Selenium 4.20.0**
* **Appium 9.4.0**
* **TestNG 7.8.0**
* **Cucumber 7.22.1**
* **Allure 2.29.1**
* **ExtentReports 5.1.2**
* **Rest-Assured**
* **Log4j + SLF4J**

---

## 3. Project Structure / Modules

```
├── src/
│   ├── main/                          # Core framework code
│   │   ├── java/
│   │   │   ├── api/                   # RestAssured clients
│   │   │   ├── mobile/                # Appium drivers
│   │   │   ├── utilities/             # Helpers (logger, waits)
│   │   │   └── web/                   # Selenium Page Objects
│   │   └── resources/                 # Config files
│   │
│   └── test/                          # Test implementations
│       ├── java/
│       │   ├── features/              # Cucumber .feature files
│       │   ├── runners/               # TestNG/Cucumber runners
│       │   └── steps/                 # Step definitions
│       └── resources/                 # Test data
│
├── testing/                           # Test suites (XML)
├── logs/                              # Execution logs
├── screenshots/                       # Failed test screenshots
└── target/                            # Generated reports
```

### Design Patterns:

* **Page Object Model** for Web UI testing
* **TestNG XML** suite configurations for targeted runs
* **Separation of concerns** (src/main for core, src/test for testing)

---

## 4. How to Run the Tests

### A. Using Maven:

```bash
mvn clean test -DsuiteXmlFile=testng-web.xml
mvn clean test -DsuiteXmlFile=testng-api.xml
mvn clean test -DsuiteXmlFile=testng-mobile.xml
mvn clean test -DsuiteXmlFile=WebParallel.xml  > for running parallel browser ? Please update Hooks.class before running parallel with 
## 🧪 Web Test Setup


## Before each test method, the WebDriver instance is initialized using the browser parameter from the TestNG suite. The URL is read from the configuration file and the driver is navigated accordingly.

```java
/**
 * Sets up the WebDriver instance before each test method.
 */
@Parameters("browser")
@BeforeMethod
public void setUpDriver(String browser) {
    try {
        DriverManager.initializeDriver(browser);
        String url = ConfigUtils.get_PortalURL();
        DriverManager.navigate(url);
        Log4JLogger.logINFO(Hooks.class, "Navigated to URL: " + url);
    } catch (Exception e) {
        Log4JLogger.logERROR(Hooks.class, "Error during driver setup.");
        ExceptionHandling.handleException(e);
    }
}

```

### B. Using TestNG Suite Files:

You can run directly from:

* `testng-web.xml`
* `testng-api.xml`
* `testng-mobile.xml`
* `WebParallel.xml`

---

## 5. Environment Configuration

* Uses `.properties` files for execution control:

    * `web.properties` → for web execution
    * `mobile.properties` → for mobile configs
* Values read via `Commons Configuration` library.
* Platform = **Local execution only** (for now).

---

## 6. Reports and Logs

* **Allure Reports:** Automatically generated in `target/allure-results`
* **ExtentReports:** HTML reports located in `/test-output/extent-report`
* **Logs:** Saved in `logs/` directory with separate logs per test suite.
* **Screenshots:** Saved automatically on test failures in `screenshots/`

### Allure Report Preview:

📸 [View Screenshot](screenshots/allure-example.png)

### Allure Report URL (example):

📎 `http://localhost:63342/allure-report/index.html`

---

## 7. Setup Instructions

### Prerequisites:

* Java 17
* Maven 3.8+
* Android Studio (for mobile testing)
* Node.js & Appium (mobile automation)

### Setup Commands:

```bash
# Clone the repo
git clone https://github.com/your-org/cyber-safety-app-automation.git
cd Foodics-AutomationTask

# Install dependencies and build
mvn clean install

# Run tests
mvn test -DsuiteXmlFile=testng-web.xml
mvn test -DsuiteXmlFile=testng-api.xml

```

---

## 8. Test Data

* Stored in JSON files under `src/test/resources/testdata/`
* **Static**

---

## 9. Contact

**Author:** AMR EL-GARHI

📧 Email : [amrahmedibra6@gmail.com](mailto:amrahmedibra6@gmail.com)

💼 LinkedIn : [https://www.linkedin.com/in/amr-el-garhi-7a29521b4/y](https://www.linkedin.com/in/amr-el-garhi-7a29521b4/)

---

## 10. Screenshots

Example screenshots attached in `/screenshots`:

* `allure-example.png` — Allure test report view
* `failure-logs.png` — Error screenshots captured on failure

---

> This automation framework provides a scalable, maintainable solution for cross-platform testing with integrated reporting, configuration, and multi-suite execution. Contributions welcome!
