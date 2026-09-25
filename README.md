# Trimly — URL Shortener

[![Live Demo](https://img.shields.io/badge/Live-App-blue?style=flat-square&logo=render)](https://trimly-wty8.onrender.com/)
[![GitHub Repo](https://img.shields.io/badge/GitHub-Repository-black?style=flat-square&logo=github)](https://github.com/SouravGorai2004/trimly-url-shortener)

A lightweight, production-ready URL shortening service built to demonstrate clean Low-Level Design (LLD), robust Java Spring Boot backend architecture, and a minimal React frontend. 

---

## Features

- **Collision-Free Shortening:** Utilizes a mathematical Base62 encoder derived from auto-increment database IDs instead of random guessing.
- **Instant Redirection:** Fast `302 FOUND` HTTP redirection handling traffic seamlessly.
- **Click Analytics:** Automatically tracks and increments a click counter every time a short link is accessed.
- **Data Integrity & Validation:** Centralized exception handling (`@ControllerAdvice`) with strict URL formatting validation.
- **Decoupled Architecture:** Built as a standalone REST API communicating with a modern React SPA via CORS.

---

## 🛠️ Tech Stack

### Backend
- **Language & Framework:** Java 17, Spring Boot, Spring Web, Spring Data JPA / Hibernate
- **Database:** PostgreSQL
- **Containerization & Deployment:** Docker, Render

### Frontend
- **Framework:** React, Vite
- **Styling:** Vanilla CSS (Minimalist developer design)

---

## 📐 System Architecture & Low-Level Design

Trimly follows a strict 3-tier enterprise structure separating web routing, business rules, and database persistence:

```text
React Frontend 
    ↓ (REST API / JSON)
Spring Boot Controller (DTO Mapping & Validation)
    ↓
Spring Service (Base62 Encoding & Business Logic)
    ↓
Spring Data JPA Repository
    ↓
PostgreSQL Database (url_mappings table)
