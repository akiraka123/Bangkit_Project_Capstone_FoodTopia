# API Endpoints

## Public Endpoints
- **GET** `/` - Get all recipes
- **GET** `/search` - Search recipes
- **GET** `/random` - Get random recipes
- **GET** `/paginated` - Get paginated recipes
- **GET** `/:id` - Get a recipe by ID

## Protected Endpoints (Authentication Required)
- **POST** `/` - Add a new recipe
- **PUT** `/:id` - Update a recipe by ID
- **DELETE** `/:id` - Delete a recipe by ID