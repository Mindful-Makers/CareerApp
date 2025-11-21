# Career App - Company Salary Comparison Tool

A web-based Java application for managing and comparing company information including salary ranges, employee counts, and locations.

## Features

- **Company Management**: Add and store company information
- **Input Categories**: 
  - Company Name
  - Type of Company
  - Description
  - Location
  - Number of Employees
  - Salary Min
  - Salary Max
- **Salary Comparison**: Compare salary ranges across multiple companies
- **Web Interface**: Clean, accessible browser-based UI
- **Full Test Coverage**: Comprehensive JUnit test suite

## Project Structure

```
CareerApp/
├── build.xml                          # Ant build configuration
├── lib/
│   └── junit-platform-console-standalone-1.10.1.jar
├── src/
│   ├── app/
│   │   ├── Company.java              # Model class
│   │   ├── CompanyService.java       # Business logic
│   │   ├── WebServer.java            # HTTP server
│   │   └── Launcher.java             # Application entry point
│   └── assets/test/java/
│       ├── CompanyTest.java          # Unit tests
│       └── CompanyServiceTest.java   # Service tests
└── README.md                          # This file
```

## Requirements

- Java JDK 18 or higher
- Apache Ant (for building)

## Building and Running

### Compile the Application
```bash
ant compile
```

### Run Tests
```bash
ant test
```

### Start the Application
```bash
ant run
```

Then open your browser to: **http://localhost:5000**

### Clean Build
```bash
ant clean
```

### Full Build (Clean, Compile, Test)
```bash
ant all
```

## Usage

1. **Start the application** using `ant run`
2. **Open browser** to http://localhost:5000
3. **Add companies** using the input form with all required categories
4. **View companies** in the list below the form
5. **Compare salaries** by clicking the "Compare Companies" button

## API/Architecture

### Model Layer
- `Company`: Represents a company with all attributes

### Service Layer
- `CompanyService`: Manages collection of companies in memory

### Server Layer
- `WebServer`: HTTP server handling requests and generating HTML

### Entry Point
- `Launcher`: Main class that starts the application

## Testing

The project includes comprehensive test coverage:

- **CompanyTest**: 21 unit tests for Company class
- **CompanyServiceTest**: 11 integration tests for CompanyService

All tests use JUnit 5 framework.

## Configuration

Default port: 5000

To change port, set the `PORT` environment variable:
```bash
export PORT=8080
ant run
```

## Technologies Used

- Java 18
- JUnit 5 (Testing)
- Apache Ant (Build tool)
- Java HttpServer (Web server)

## Author

Student Submission for Programming Assignment

## License

Educational Project
