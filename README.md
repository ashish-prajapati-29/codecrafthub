📘 CodeCraftHub

CodeCraftHub is a simple Spring Boot REST API that allows developers to manage and track courses they want to learn. It is designed for beginners to understand REST APIs, JSON file storage, and backend architecture without using a database.

🚀 Features

📚 Create, read, update, and delete courses (CRUD)

📁 Data stored in a local courses.json file (no database required)

🆔 Auto-generated course IDs

⏱ Auto-generated createdAt timestamp

✅ Input validation (required fields, valid status)

❌ Error handling for invalid input and missing resources

🧠 Beginner-friendly structure (Controller → Service)

🛠️ Tech Stack

Java 17+

Spring Boot

Jackson (JSON processing)

Maven

📦 Installation
1. Clone the repository
git clone https://github.com/your-username/codecrafthub.git
cd codecrafthub
2. Build the project
mvn clean install
▶️ Running the Application
mvn spring-boot:run

Or run the main class:

CodeCraftHubApplication.java
Application will start at:
http://localhost:8080
📁 JSON Storage

File: courses.json

Automatically created if not present

Stored in project root

🌐 API Endpoints
🔹 1. Create Course

POST /api/courses

Request Body
{
  "name": "Spring Boot",
  "description": "Learn REST APIs",
  "targetDate": "2026-04-01",
  "status": "In Progress"
}
Response
{
  "id": 1,
  "name": "Spring Boot",
  "description": "Learn REST APIs",
  "targetDate": "2026-04-01",
  "status": "In Progress",
  "createdAt": "2026-03-19T12:30:00"
}
🔹 2. Get All Courses

GET /api/courses

🔹 3. Get Course by ID

GET /api/courses/{id}

Example:

GET /api/courses/1
🔹 4. Update Course

PUT /api/courses/{id}

Request Body
{
  "name": "Spring Boot Advanced",
  "description": "Deep dive into Spring",
  "targetDate": "2026-05-01",
  "status": "Completed"
}
🔹 5. Delete Course

DELETE /api/courses/{id}

🧪 Sample cURL Commands
➕ Create Course
curl -X POST http://localhost:8080/api/courses \
-H "Content-Type: application/json" \
-d '{
  "name": "Java Basics",
  "description": "Learn Java fundamentals",
  "targetDate": "2026-04-10",
  "status": "Not Started"
}'
📥 Get All Courses
curl http://localhost:8080/api/courses
🔍 Get Course by ID
curl http://localhost:8080/api/courses/1
✏️ Update Course
curl -X PUT http://localhost:8080/api/courses/1 \
-H "Content-Type: application/json" \
-d '{
  "name": "Java Advanced",
  "description": "Advanced Java topics",
  "targetDate": "2026-05-15",
  "status": "In Progress"
}'
❌ Delete Course
curl -X DELETE http://localhost:8080/api/courses/1
⚠️ Validation Rules
Field	Requirement
name	Required
description	Required
targetDate	Required (YYYY-MM-DD)
status	Must be one of: Not Started, In Progress, Completed
🧯 Error Handling
Scenario	Error Message
Missing name	"Course name is required"
Missing description	"Course description is required"
Missing target date	"Target date is required"
Invalid status	"Invalid status value"
Course not found	"Course not found"
File error	"Error reading/writing courses file"
🛠️ Troubleshooting Guide
❗ Application not starting

Check Java version (Java 17+ recommended)

Run:

mvn clean install
❗ Port already in use

Change port in application.properties:

server.port=8081
❗ courses.json not created

Ensure app has write permissions

Run the app once → file will auto-create

❗ JSON parsing errors

Ensure date format is:

YYYY-MM-DD
❗ API not working

Check URL:

http://localhost:8080/api/courses

Use Postman or curl for testing

🎯 Future Enhancements

🔍 Search & filter courses

📊 Progress tracking dashboard

🌐 Frontend UI (React/Angular)

🔐 Authentication (Spring Security)

🙌 Contributing

Feel free to fork and improve this project!