# Courses Management System (Internship Assignment - ASC, IIT Bombay)

This project implements a full-stack **Courses Management System** using **Java Spring Boot** for the backend and **ReactJS** for the frontend. It is fully Dockerized and integrated with **GitHub Actions** CI/CD to build and push Docker images to Docker Hub automatically.

---

## Project Structure

courses-app/
├── backend/ # Spring Boot backend
├── frontend/ # ReactJS frontend
├── docker-compose.yml # Compose file to run full stack

yaml
Copy
Edit

---

## Features

- RESTful API to manage courses (CRUD operations)
- Responsive ReactJS frontend with course listing & creation UI
- GitHub Actions for CI/CD
- DockerHub deployment
- `docker-compose` for local full-stack run

---

## DockerHub Repositories

- **Backend Image:** [`<welshia1511>/courses-backend`](https://hub.docker.com/repository/docker/welshia1511/course-api-backend/general)
- **Frontend Image:** [`<welshia1511>/courses-frontend`](https://hub.docker.com/r/welshia1511/courses-frontend)


---

##  GitHub Actions CI/CD

Each push to the `main` branch triggers:

- **Build** the Docker image
- **Login** to DockerHub
- **Push** the image to DockerHub

This is done separately for:
- [Backend GitHub Repo](https://github.com/welshiaaranha/courses-api-backend)
- [Frontend GitHub Repo](https://github.com/welshiaaranha/courses-api-frontend)

> Secrets (`DOCKER_USERNAME`, `DOCKER_PASSWORD`) are stored securely in each repo under `Settings → Secrets and variables → Actions`.

---

## 🧪 How to Run Locally

### Prerequisites:
- Docker
- Docker Compose

---

### Step 1: Clone the Repos

```bash
git clone https://github.com/welshiaaranha/courses-api-backend.git
git clone https://github.com/welshiaaranha/courses-api-frontend.git
Step 2: Create docker-compose.yml (in root folder)
yaml
Copy
Edit
version: "3.8"
services:
  backend:
    image: welshia1511/courses-backend:latest
    ports:
      - "8080:8080"
  frontend:
    image: welshia1511/courses-frontend:latest
    ports:
      - "3000:3000"
    environment:
      - REACT_APP_BACKEND_URL=http://localhost:8080
Step 3: Run Locally
bash
Copy
Edit
docker-compose up
Backend: http://localhost:8080

Frontend: http://localhost:3000


## Contact
Created by Welshia Aranha for the Application Software Centre Internship Assignment, IIT Bombay.

GitHub: https://github.com/welshia1511

DockerHub: https://hub.docker.com/u/welshia1511
