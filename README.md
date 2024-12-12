# Mobile Development Learning Path

## 🚀 Getting Started
1. Clone the repository:

2. Open the project in **Android Studio**.
3. Sync **Gradle files**.
4. Run the project on an **emulator** or a **physical device**.

## 🛠 Tech Stack & Architecture

### Core Technologies
- **Language:** Kotlin
- **Minimum SDK:** 30 (Android 11)
- **Architecture Pattern:** MVVM (Model-View-ViewModel)

## 📱 Features & Implementation

### 1. Authentication
- Login and Registration functionality for user accounts.
- Token-based authentication to secure the login process.
- Secure credential management to protect sensitive user data.

### 2. Recipe Management
- **Random Recipe Discovery** for exploring new recipe ideas.
- **Recipe Search Functionality** to search for recipes based on ingredients or titles.
- **Favorite Recipe Management** to allow users to save and view their favorite recipes.

### 3. Navigation
- **Bottom Navigation** with 4 main sections:
  - Home
  - Search
  - Favorites
  - Profile
- **Fragment-based navigation** for a smooth, seamless user experience across screens.

### 4. Data Storage
- **Room Database** for persistent local storage of recipes and user data.
- **SharedPreferences** for storing user settings like preferences and login state.
- **Token Management** to securely manage authentication tokens and ensure a safe user session.

## 📱 UI Components
- **Material Design** components for a modern and user-friendly interface.
- **Custom Layouts** for displaying recipe cards and detailed information.
- **Responsive Design** to ensure compatibility with different screen sizes and resolutions.

## 🧪 Testing
- **Unit Tests** for testing repository methods and business logic.
- **UI Tests** to validate the main user interactions and ensure they work as expected.
- **Integration Tests** to verify the correct functionality of API calls and data flow.

## 📝 Development Guidelines
1. Follow the **MVVM architecture pattern** to maintain a clean separation of concerns.
2. Use **ViewBinding** for type-safe interactions with views.
3. Implement **Coroutines** for handling asynchronous operations efficiently.
4. Handle **Configuration Changes** (e.g., screen rotation) appropriately.
5. Implement **Error Handling** to ensure stability and a smooth user experience.

---

