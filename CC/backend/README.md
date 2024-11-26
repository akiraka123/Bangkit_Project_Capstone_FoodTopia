# FoodTopia Backend

FoodTopia Backend is the server-side application for managing food recipes, user authentication, and favorite recipes. Built using **Node.js**, this backend integrates with **Google Cloud Platform (GCP)** and **NoSQL Firestore** for data storage.

## Features
- User Authentication (Registration, Login, Google Login)
- Recipe Management (Add, Update, Delete, Search, Paginated, Random)
- Favorite Recipes Management
- Secure endpoints with JWT-based authentication
- Integration with Machine Learning for food recommendations (planned)

---

## Table of Contents
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
  - [Auth Routes](#auth-routes)
  - [Recipe Routes](#recipe-routes)
  - [Favorite Routes](#favorite-routes)
- [Technologies](#Technologies)
- [Environment Variables](#environment-variables)
- [License](#license)

---

### Installation
Clone this repository:
```bash
  git clone --branch CC https://github.com/akiraka123/Bangkit_Project_Capstone_FoodTopia.git
  cd backend
```
### Install Dependencies
  ```bash
    npm install
  ```

### Inisialisasi Firestore Service Key
1. Prepare your Firestore service key JSON file. This file can be obtained from [Google Cloud Console](https://console.cloud.google.com/).
2. Put the JSON file into your project directory. Example: `firebase-key.json`.
3. Change the Firestore initialization code to read your JSON file like this ( firebase.js):

  ```javascript
    const admin = require('firebase-admin');
    const serviceAccount = require('./firebase-key.json'); //Replace with the name and location of your JSON file

    admin.initializeApp({
      credential: admin.credential.cert(serviceAccount),
    });
    module.exports = admin;
  ```

### Start
```bash
  npm run dev
```

---

## API Endpoints

### Auth Routes
| Method | Endpoint               | Description                  |
|--------|-------------------------|------------------------------|
| POST   | `/api/auth/register`    | Register a new user          |
| POST   | `/api/auth/login`       | Login with email and password|
| POST   | `/api/auth/google`      | Login with Google            |
| POST   | `/api/auth/forgot-password` | Send password reset link |
| POST   | `/api/auth/reset-password`  | Reset user password        |
| GET    | `/api/auth/user`        | Fetch the logged-in username (Auth) |

### Recipe Routes
| Method | Endpoint                 | Description                  |
|--------|---------------------------|------------------------------|
| GET    | `/api/recipes/`           | Get all recipes              |
| GET    | `/api/recipes/search`     | Search recipes by keyword    |
| GET    | `/api/recipes/random`     | Get random recipes           |
| GET    | `/api/recipes/paginated`  | Get paginated recipes        |
| GET    | `/api/recipes/:id`        | Get recipe by ID             |
| POST   | `/api/recipes/`           | Add a new recipe (Auth)      |
| PUT    | `/api/recipes/:id`        | Update a recipe (Auth)       |
| DELETE | `/api/recipes/:id`        | Delete a recipe (Auth)       |

### Favorite Routes
| Method | Endpoint                 | Description                  |
|--------|---------------------------|------------------------------|
| POST   | `/api/favorite/`          | Add recipe to favorites (Auth) |
| GET    | `/api/favorite/`          | Get all favorite recipes (Auth) |
| DELETE | `/api/favorite/:id`       | Remove recipe from favorites (Auth) |

---

## Technologies
- **Node.js** with **Express.js**
- **Firestore (NoSQL)** for database
- **GCP Storage** for image handling
- **JWT** for user authentication
- **bcrypt** for password hashing
---

## Environment Variables

Create a `.env` file in the root of your project and configure the following variables:

  ```env
    PORT=3000
    SECRET_KEY=your_jwt_secret
    RESET_PASSWORD_EXPIRATION= set_time(sec)_reset_password
  ```
## License
This project is licensed under the MIT License. See the LICENSE file for details.
