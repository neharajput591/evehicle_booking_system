⚡ E-Vehicle Booking System

A complete web application that allows users to browse, purchase, and manage electric vehicle bookings. Includes secure user authentication, shopping cart, ordering system, and a powerful admin dashboard with full management and analytics.

🚀 Tech Stack
Frontend

HTML

CSS

JavaScript

Backend

Java

Spring Boot (main backend framework)

Spring Security (authentication & authorization)

Spring MVC / REST API

Spring Data JPA / Hibernate

Database

MySQL

🔐 Special Features

✔ Spring Security authentication

✔ Role-Based Access Control (USER, ADMIN)

✔ REST API Endpoints for cart, orders, vehicles, feedback

✔ Session-based login (for web application)

✔ Secure Admin Dashboard

📌 Features Overview
👤 User Features

📝 Register a new account

🔐 Login securely via Spring Security

🚗 View all available vehicles

🛒 Add vehicles to cart

💵 Buy a vehicle (place an order)

📦 View all placed orders

🗣️ Submit feedback for vehicles

👤 View their profile

🚪 Logout securely

🛠️ Admin Features

Accessible only with valid admin credentials.

Admin can:

➕ Add new vehicle entries

✏️ Edit / Delete vehicles

👥 View registered users

📮 View user feedback

📑 View all orders

📊 Access analytics dashboard (sales, orders, revenue, etc.)

📂 Project Structure
E-Vehicle-Booking-System/
 ├── src/main/java
 │   ├── controllers/
 │   ├── services/
 │   ├── repository/
 │   ├── models/
 │   └── security/ (Spring Security config)
 ├── src/main/resources
 │   ├── templates/  (HTML, Thymeleaf)
 │   └── static/     (CSS, JS, Images)
 ├── application.properties
 └── README.md

🚀 Getting Started
1️⃣ Clone the repository
git clone https://github.com/your-username/evehicle-booking-system.git
cd evehicle-booking-system

2️⃣ Configure MySQL

Update your database credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/evehicle
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the backend
mvn spring-boot:run

4️⃣ Open the app

Visit in browser:

http://localhost:8080/

🔑 Default Admin Login
Email: admin@gmail.com
Password: admin@123


🔗 API Endpoints (Short Overview)
User APIs
Method	Endpoint	Description
POST	/register	Register user
POST	/login	Login user
GET	/User/vehicle	View vehicles
POST	/User/addtocart	Add item to cart
GET	/User/orders	View orders
Admin APIs
Method	Endpoint	Description
POST	/Admin/addvehicle	Add vehicle
GET	/Admin/users	View users
GET	/Admin/orders	View orders
GET	/Admin/feedbacks	View feedback
GET	/Admin/analytics	View analytics
📈 Future Improvements

RazorPay / Stripe Payment Gateway

Email Notification System

JWT-based Authentication

Mobile Responsive UI

Vehicle Comparison Page

🤝 Contributing

PRs and suggestions are always welcome.
Feel free to raise an issue or contribute enhancements.

📜 License

This project is open-source and available under the MIT License.