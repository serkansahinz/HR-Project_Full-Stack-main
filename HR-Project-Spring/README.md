# HR Project

## Human Resources Management System

A project created to manage human resources processes for commercial companies through a platform.
You can access the promotional video of the project from this link.

[YouTube Video](https://www.youtube.com/watch?v=tM-Fa5lB-zc&ab_channel=%C3%9CnalGaniBerk)


### Architecture

<img src="https://github.com/onurbass/HR-Project_Full-Stack/blob/main/HR-Project-Spring/src/main/resources/images/cloud-architecture.jpg?rav=true" alt="Icon" >

### About the Project

This human resources application encompasses four different roles: admin, company manager, employee, and guest. Each role is equipped with a control panel, profile, and pages designed for their respective tasks.

When a company manager registers, the records are approved by the admin. Once approved, the company manager gains access to the system and can then add employees to the system.

The employee profile page includes personal information, salary details, and shift schedules. The employee control panel also provides general holiday information and details related to the company they are employed by.

Employees can submit comments about their companies, but these comments are only published after admin approval.

For company managers, there is a special page containing comprehensive information about their company's financial status, including income, expenses, and profit/loss data.

Guests who register for the application can access information and comments related to all registered companies within the application.

### Usage

To run these sample applications, you need to have Gradle and JDK 8 or higher versions. Additionally, you'll need to run MongoDB, PostgreSQL, and RabbitMQ in a Docker container, so Docker must be installed on your local machine.

### Docker Compose Usage

1. By running the Docker compose file within the project, you can install and run the necessary dependencies in Docker.
