# Automation Framework for SauseDemo
## Overview
This is a repository for final task of test automation Java lab. Implemented by Anna Kurta.

---
## Prerequisites
- Java 17 should be installed to run this project
- Project is built using Maven. 
- Chrome and Firefox browsers are supported and need to be installed on computer to run

## Technologies
- Maven build tool
- JUnit 5
- Cucumber
- Selenium WebDriver
- Allure
## Project structure
Project follows maven standard structure.
In main folder, there are the following packages:
- config (classes related to driver provisioning and properties)
- page_objects
- utils (additional classes, helpers)

There is also resource folder with project.properties file where driver, wait timeouts and credentials are set.

In test folder, there are the following packages:
- junittests - old implementation without Cucumber (it is now commented)
- runner - Junit runner
- steps - package with step definitions

Also in test folder there is resources folder.
Resource folder contains:
- features
- allure.properties
- junit-platform.properties
- log4j2.properties
## How to run tests
There are 2 versions of the tests in the project
- JUnit 5 plain tests (commented out)
- Cucumber features

By default - Cucumber implementation will be running.
To run the tests - you should execute in command line:
> mvn clean test

Tests will be executed in parallel.
After the run is complete - execute this command to generate a report:
> mvn allure:serve

If you want to run tests with different browser, you can do this in 2 ways:
- change the value in project.properties
- execute the next command:
>mvn clean test -Ddriver=your_driver

