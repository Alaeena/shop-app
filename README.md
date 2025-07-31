# 🛒 Shop App – E-commerce Web Project

## 🚨 Disclaimer

**This project was created for educational purposes only.** It is not affiliated with or endorsed by any real company or brand. This is a personal learning project and should not be mistaken for an official commercial product.

---

## 🌐 Live Demo

* **Frontend (GitHub Pages)**: [https://alaeena.github.io/shop-app/](https://alaeena.github.io/shop-app/)
* **Backend (Azure App Service)**: [https://alaeena.azurewebsites.net](https://alaeena.azurewebsites.net)

---

## 📦 Project Overview

Shop App is a full-stack **e-commerce web application** developed as a personal project using:

* **Frontend**: ReactJS
* **Backend**: Spring Boot
* **Database**: PostgreSQL
* **Search**: Elasticsearch
* **Authentication**: JWT, OAuth2, email verification
* **Session Management**: Redis (for development), PostgreSQL (for production caching)
* **CI/CD**: GitHub Actions
* **Containerization**: Docker, Docker Compose
* **Payment Integration**: VNPay, QR code payments

---

## 👥 User Roles & Sample Accounts

### 🧑‍💼 Admin Account

* **Username**: `admin`
* **Password**: `admin@123`

### 👤 User Account

* **Email**: `ducnbk7a1@gmail.com`
* **Password**: `vanduc@123`

---

## ✨ Key Features

### 👤 User Features

* Register and login using **JWT** or **OAuth2**, with **email verification**
* Browse, search, filter, and paginate product listings
* Add, edit, or delete items in the shopping cart
* Create and manage orders
* Make payments via **VNPay** or **QR code**
* Manage user profile and view order history

### 🔧 Admin Features

* Add, edit, or delete product categories
* Enable or disable user accounts

---

## 🚀 Deployment & CI/CD

* The application uses **GitHub Actions** for automated testing and CI/CD pipelines.
* **Backend** is hosted on **Azure App Service**
* **Frontend** is hosted via **GitHub Pages**

---

## 🛠️ Setup & Run Locally with Docker

To run the project locally, make sure you have [Docker](https://www.docker.com/) installed.

### 🗂️ Project Structure

After cloning and extracting the project, the directory structure will look like this:
![Project structure](https://github.com/Alaeena/shop-app/assets/151113431/9a03e648-a49e-41d2-a4ac-9c91c581a41c)

### 📦 Run the Project

In the root directory, run:

```bash
docker-compose up
```

All services should be up and running within approximately **1 minute**.

| Service          | URL                                            |
| ---------------- | ---------------------------------------------- |
| Frontend (React) | [http://localhost:3000](http://localhost:3000) |
| Backend (Spring) | [http://localhost:8080](http://localhost:8080) |
| Elasticsearch    | [http://localhost:9200](http://localhost:9200) |

---

## 🧠 Environment Configuration

* **Development** uses **Redis** for token whitelisting.
* **Production** uses **PostgreSQL** for caching and data persistence.
