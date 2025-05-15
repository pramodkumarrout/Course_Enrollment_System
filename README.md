# 📚 Course Enrollment System

A Spring Boot-based Course Enrollment application to manage Students, Instructors, Courses, and Enrollments. It follows layered architecture and includes features like CRUD operations, custom API responses, exception handling, pagination, and sorting.

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman (for testing)

---

## 📦 Features

- Full CRUD for:
  - Students
  - Instructors
  - Courses
  - Enrollments
- Custom `ResponseStructure<T>` for API responses
- Global exception handling (`IdNotFoundException`)
- Pagination and Sorting
- Many-to-many relation via `Enrollment`

---

## 📁 Project Structure

```text
jsp.courseenrollment
├── controller
├── service
├── dao
├── entity
├── exception
├── repository
└── util
