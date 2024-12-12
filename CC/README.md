# FoodTopia API Documentation

FoodTopia API is divided into two main services:

1. **Backend API (Node.js)**: Handles user authentication, recipe management, and favorite recipes.
2. **ML API (Flask)**: Provides diet category predictions based on nutritional input values.
3. **firestore-CSV-import** This is a project to import csv file to firestore with firebase_service key in the form of JSON file. Follow the steps below to run the project.

---

## **Backend API**

### **Auth Routes**

| Method | Endpoint               | Description                       |
|--------|-------------------------|-----------------------------------|
| POST   | `/api/auth/register`    | Register a new user               |
| POST   | `/api/auth/login`       | Login with email and password     |
| POST   | `/api/auth/google`      | Login with Google                 |
| POST   | `/api/auth/forgot-password` | Send password reset link        |
| GET    | `/api/auth/user`        | Fetch the logged-in username (Auth required) |

---

### **Recipe Routes**

| Method | Endpoint                 | Description                       |
|--------|---------------------------|-----------------------------------|
| GET    | `/api/recipes/`           | Get all recipes                   |
| GET    | `/api/recipes/search`     | Search recipes by keyword or name |
| GET    | `/api/recipes/random`     | Get random recipes                |
| GET    | `/api/recipes/:id`        | Get a recipe by its ID            |
| POST   | `/api/recipes/`           | Add a new recipe (Auth required)  |
| PUT    | `/api/recipes/:id`        | Update a recipe by its ID (Auth required) |
| DELETE | `/api/recipes/:id`        | Delete a recipe by its ID (Auth required) |

---

### **Favorite Routes**

| Method | Endpoint                 | Description                       |
|--------|---------------------------|-----------------------------------|
| POST   | `/api/favorite/`          | Add a recipe to favorites (Auth required) |
| GET    | `/api/favorite/`          | Get all favorite recipes (Auth required) |
| DELETE | `/api/favorite/:id`       | Remove a recipe from favorites (Auth required) |

---

## **ML API**

### **Predict Diet Category**

| Method | Endpoint               | Description                                    |
|--------|-------------------------|------------------------------------------------|
| POST   | `/predict`              | Predict diet category based on nutrition input|

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.
