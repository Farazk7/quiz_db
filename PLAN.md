PLAN.md — Quiz Management System

1. Introduction
This document outlines the functional scope, architecture, and implementation strategy for the Quiz Management System, developed as part of the technical assessment.
The objective is to build a production-ready, maintainable, and deployable solution within the given time constraints.
The system provides an Admin Panel for quiz management and a Public Page for taking quizzes.

2. Scope
Admin :
Secure login using JWT
Create quizzes with multiple question types:
i) MCQ
ii) True/False
iii) Text-based questions
Manage quizzes (view/delete)

Users :
Access public quiz pages
Take a quiz without authentication
Submit the attempt
View the score/results

The solution focuses on delivering all core, functional features required for evaluation.

3. Assumptions
Two roles exist: ADMIN (secured) and PUBLIC USER (anonymous).
JWT authentication is used only for Admin routes.
Only Admin creates quizzes; users simply attempt them.
The frontend is deliberately minimal to focus on functionality and backend robustness.
MySQL is used locally and deployed via Railway.
Backend is deployed on Render.

4. Out of Scope

The following features were intentionally excluded due to time constraints and assessment guidelines:
i) Quiz timers or countdown
ii) Leaderboards
iii) Multi-attempt tracking
iv) CSV/bulk upload
v) Email/OTP verification
vi) Analytics or admin reports
vii) Rich UI using React/Angular
viii) User registration or profiles
ix) Unit Testing using Junit and Mockito

These can be added in future, but are not essential for the core working system.

5. Functional Requirements
Admin Requirements :
i) Authenticate using email/password
ii) Create quizzes with multiple questions
iii) Choose question type
iv) Set correct answers
v) View all quizzes
vi) Delete quizzes
vii) Public Requirements
viii) Load quiz data from backend
ix) Attempt quiz without login
x) Submit answers
xi) Receive and view result immediately
xii) Security Requirements
xiii) JWT-based authentication
xiv) Protected admin endpoints
xv) Public APIs for loading and submitting quiz attempts

6. Non-Functional Requirements
i) Clean layered architecture (Controller → Service → Repository)
ii) Secure admin operations
iii) Clear error handling and validations
iv) Easily deployable backend
v) Simple, understandable codebase for reviewers
vi) Minimal dependencies
vii) Fully working end-to-end flow

7. Architecture Overview
Backend :
Built using Spring Boot with a layered architecture:
i) Controller Layer
Exposes REST endpoints for authentication, quiz management, and quiz attempts.
ii) Service Layer
Encapsulates business logic such as quiz creation, scoring, validations, and token generation.
iii) Repository Layer
Uses Spring Data JPA to communicate with MySQL.
iv) Security Layer
v) JWT token generation
vi) Custom filter for authentication
vii) Role-based access for Admin APIs

Frontend
i) Pure HTML + CSS + JavaScript
ii) Uses fetch() to call backend APIs
iii) Lightweight, easy to deploy with no build tools required

8. Technology Stack
Java 17
Spring Boot 3.x
Spring Web, Spring Security, Spring Data JPA
MySQL
HTML, CSS, JavaScript
Render for backend deployment
Railway for MySQL hosting
GitHub for version control (4+ commits mandatory)

9. Development Plan

Phase 1 – Project Setup
Create Spring Boot project
Configure dependencies
Define entities, repositories
Write initial PLAN.md
Commit #1

Phase 2 – Backend Features
Implement Admin authentication (JWT)
Implement quiz creation
Add question & choice handling
Implement public quiz fetch
Implement attempt evaluation logic
Add DTOs and validations
Commit #2

Phase 3 – Security
Configure JWT filter
Protect admin endpoints
Test admin flows
Commit #3

Phase 4 – Frontend
Build admin login page
Build admin dashboard
Build create quiz page
Build quiz attempt page
Build quiz result page
Integrate backend APIs
Test end-to-end flow
Commit #4

Phase 5 – Deployment
Host database on Railway
Deploy backend on Render
Add environment variables
Test deployed version

10. Future Improvements (If More Time Was Available)
Modern UI using React
Timed quizzes
Reporting and analytics
Question randomization
User registrations and multiple attempts
Leaderboards
CSV upload for bulk question creation
Better admin dashboard with charts

11. Conclusion
This plan provides a structured approach to delivering a complete, functional, and production-ready Quiz Management System.
The focus is on correctness, stability, deployment readiness, and clean engineering practices—ensuring evaluators can easily run, test, and understand the system.