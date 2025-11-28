PLAN.md — Quiz Management System
1. Introduction

This document outlines the scope, architecture, and development plan for the Quiz Management System. The goal is to build a simple yet production-ready quiz platform with a Spring Boot backend, MySQL database, a minimal frontend, and cloud deployment. This file represents the thought process and planning before implementation.

2. Scope

The system allows:

Admin to create and manage quizzes and questions.

Users to view and attempt quizzes.

Result calculation after quiz submission.

The solution will remain lightweight and focused due to assessment time constraints.

3. Assumptions

Two user roles: ADMIN and USER.

Simple username/password authentication using JWT.

MySQL is used locally during development and hosted on Railway for deployment.

Backend will deploy on Render.

Frontend will be minimal HTML + JavaScript.

A question belongs to one quiz only.

4. Out of Scope

No timer for quizzes

No leaderboard

No multiple attempt history

No email notifications

No bulk upload

No detailed analytics

5. Functional Requirements
   Admin Features

Create quiz

Update quiz

Delete quiz

Add questions to quiz

View all quizzes

User Features

View available quizzes

Attempt a quiz

Submit answers

Receive score

Authentication

Register new user

Login to generate JWT token

Access control based on roles

6. Non-Functional Requirements

Clean layered architecture

Secure endpoints using JWT

Proper error and exception handling

Easy to deploy

Minimal configuration needed for evaluators

Readable code and structured packages

7. Architecture Overview

A three-layered Spring Boot architecture:

Controller Layer

Handles REST APIs for quizzes, questions, and authentication.

Service Layer

Contains business logic such as quiz creation, question operations, and scoring.

Repository Layer

Spring Data JPA repositories interacting with MySQL.

8. Database Schema
   User

id (Long) — Primary Key
username (String) — Unique
password (String) — Encrypted
role (String) — ADMIN / USER

Quiz

id (Long) — Primary Key
title (String) — Quiz name
description (String) — Optional details

Question

id (Long) — Primary Key
quiz_id (Long) — FK to Quiz
question_text (String)
optionA (String)
optionB (String)
optionC (String)
optionD (String)
correct_option (String: A/B/C/D)

9. Technology Stack

Java 17

Spring Boot 3.5.x

Spring Web

Spring JPA

Spring Security (JWT)

MySQL

HTML + JavaScript

Render (backend deployment)

Railway (MySQL hosting)

10. Development Plan (Recording Flow)
    Phase 1 — Setup (First 30 mins)

Create project using Spring Initializr

Configure application.properties

Create folder structure

Create entities and repositories

Create PLAN.md

First Git commit

Phase 2 — Core Logic

Implement services

Implement controllers

Configure JWT authentication

Add DTOs

Add global exception handling

Second Git commit

Phase 3 — Frontend + Integration

Create simple HTML pages

Integrate backend APIs

Test complete user flow

Third Git commit

Phase 4 — Deployment

Host database on Railway

Deploy backend on Render

Test deployed system

Final commit

11. Enhancements (If More Time Available)

React-based UI

Quiz history & analytics

Leaderboards

Timed quizzes

Bulk CSV upload

Email notifications

Admin dashboard

12. Conclusion

This plan ensures a clear, simple, and evaluatable Quiz Management System with proper architecture, clean code, and smooth deployment. The project will be built in small, incremental phases for clarity and maintainability.