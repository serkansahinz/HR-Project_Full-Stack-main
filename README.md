# HR Project

## Human Resources Management System

A project created to manage human resources processes for commercial companies through a platform.

### Architecture

<img src="https://github.com/serkansahinz/HR-Project_Full-Stack-main/blob/main/HR-Project-Spring/src/main/resources/images/cloud-architecture.jpg?rav=true" alt="Icon" >

### About the Project

Human resources application encompasses four different roles: admin, company manager, employee, and guest. Each role is equipped with a control panel, profile, and pages designed for their respective tasks.

When a company manager registers, the records are approved by the admin. Once approved, the company manager gains access to the system and can then add employees to the system.

The employee profile page includes personal information, salary details, and shift schedules. The employee control panel also provides general holiday information and details related to the company they are employed by.

Employees can submit comments about their companies, but these comments require admin approval to be published.

Company managers have a special page containing comprehensive information about their company's financial status which includes income, expenses, and profit/loss data.

Guests can register into the application and access information and comments related to all registered companies within the application.

### Usage

To run these sample applications, you need to have Gradle and JDK 8 or higher versions. Additionally, you'll need to run MongoDB, PostgreSQL, and RabbitMQ in a Docker container, so Docker must be installed on your local machine.

### Docker Compose Usage

By running the Docker compose file within the project, you can install and run the necessary dependencies in Docker.
