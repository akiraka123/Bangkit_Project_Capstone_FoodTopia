# CapstoneProject-FoodTopia

## Product Name: “FoodTopia: Revolutionizing Nutritional Management“

### Theme: “Health Innovation: Empowering Communities for Better Nutrition and Well-being”
<img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/1.jpg" width="auto" height="350">

FoodTopia is an application that leverages cloud computing and machine learning to simplify and enhance daily nutritional management.

Here’s what FoodTopia can do:
* Manage and retrieve recipes
* Predict diet categories based on nutritional input
* Allow users to favorite their preferred recipes
* Provide random recipe suggestions

---

## Team ID: C242-PSXXX

### Team Members
1. **C296B4KY3823** - Ridho Fajar Fahturohman - Pembangunan Nasional "Veteran" University, East Java - Cloud Computing - active  
2. **C004B4KY2S23** - Mochamad Akiraka Harifanda - Sepuluh Nopember Institute of Technology - Cloud Computing - active  
3. **A296B4KY3221** - Nafis Pratama Putra - Pembangunan Nasional "Veteran" University, East Java - Mobile Development - active  
4. **A284B4KY2789** - Muhammad Dzaki Salman - State University of Surabaya - Mobile Development - active  
5. **M180B4KY3145** - Mujadid Fauzan Salim Tamin - Airlangga University - Machine Learning - active  
6. **M004B4KY2088** - Jonathan Christian Simbolon - Sepuluh Nopember Institute of Technology - Machine Learning - active  
7. **M123B4KY2892** - Muhammad Hilmi Wijaya - Electronic Engineering Polytechnic Institute of Surabaya - Machine Learning - active  

---

## Machine Learning Learning Path

### Steps
1. Download the required dataset from Kaggle and save it in the designated folder path.
2. Open your Jupyter Notebook or Colab environment and load the code.
3. Install the required modules listed in the `requirements.txt` file.
4. Run all cells to train the model. Save the trained model as `.tflite` for deployment.

### Dataset Sources
* **Food Classifier Dataset**  
  [Food41 Dataset](https://www.kaggle.com/datasets/kmader/food41)
* **Nutritional Information Dataset**  
  [Dataset Link](https://www.kaggle.com/datasets/liamboyd1/singular-food-items)

### Accuracy and Loss
* **Food Classifier Model**: 85% Accuracy, 0.02 Loss
* **Diet Prediction Model**: 88% Accuracy, 0.015 Loss

---

## Cloud Computing Learning Path

### Architecture
<img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/CC/image/Arsitektur.png" alt="Architecture" width="auto" height=350>

### Backend Features
- User authentication (Register, Login, Google Login, Password Reset)
- Recipe management (CRUD operations)
- Favorite recipes tied to user accounts
- Random recipe suggestions
- Integration with the ML API for predictions

### ML API Features
- Predict diet categories based on nutritional input values:
  - **Low-Calorie and Low-Fat Diet**
  - **High-Protein Diet**
  - **Low-Carbohydrate Diet**
  - **Balanced Healthy Diet**
  - **Not a Diet Food**

---

## Mobile Development Learning Path

### **🚀 Getting Started**
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Sync **Gradle files**.
4. Run the project on an **emulator** or a **physical device**.

### **🛠 Tech Stack & Architecture**

#### **Core Technologies**
- **Language**: Kotlin
- **Minimum SDK**: 30 (Android 11)
- **Architecture Pattern**: MVVM (Model-View-ViewModel)

### **📱 Features & Implementation**

#### **1. Authentication**
- Login and Registration functionality for user accounts.
- Token-based authentication to secure the login process.
- Secure credential management to protect sensitive user data.

#### **2. Recipe Management**
- **Random Recipe Discovery** for exploring new recipe ideas.
- **Recipe Search Functionality** to search for recipes based on ingredients or titles.
- **Favorite Recipe Management** to allow users to save and view their favorite recipes.

#### **3. Navigation**
- **Bottom Navigation** with 4 main sections:
  - Home
  - Search
  - Favorites
  - Profile
- **Fragment-based navigation** for a smooth, seamless user experience across screens.

#### **4. Data Storage**
- **Room Database** for persistent local storage of recipes and user data.
- **SharedPreferences** for storing user settings like preferences and login state.
- **Token Management** to securely manage authentication tokens and ensure a safe user session.

### **📱 UI Components**
- **Material Design** components for a modern and user-friendly interface.
- **Custom Layouts** for displaying recipe cards and detailed information.
- **Responsive Design** to ensure compatibility with different screen sizes and resolutions.

### **📝 Development Guidelines**
1. Follow the **MVVM architecture pattern** to maintain a clean separation of concerns.
2. Use **ViewBinding** for type-safe interactions with views.
3. Implement **Coroutines** for handling asynchronous operations efficiently.
4. Handle **Configuration Changes** (e.g., screen rotation) appropriately.
5. Implement **Error Handling** to ensure stability and a smooth user experience.

---

## App Screenshots

### 1. Onboarding Page  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/1.jpg" width="auto" height="350">
</div>
<br><br>

### 2. Sign Up and Sign In Pages  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/2a.jpg" width="auto" height="350">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/2.jpg" width="auto" height="350">
</div>
<br><br>

### 3. Home Page  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/3.jpg" width="auto" height="350">
</div>
<br><br>

### 4. Detail Page  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/4.jpg" width="auto" height="350">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/5.jpg" width="auto" height="350">
</div>
<br><br>

### 5. Search  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/6.jpg" width="auto" height="350">
</div>
<br><br>

### 6. Favorite  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/7.jpg" width="auto" height="350">
</div>
<br><br>

### 7. Profile  
<div style="display: flex; justify-content: center; gap: 20px;">
   <img src="https://raw.githubusercontent.com/akiraka123/Bangkit_Project_Capstone_FoodTopia/main/MD/screenshots/8.jpg" width="auto" height="350">
</div>
<br><br>

---

## Additional Features
1. **Search Recipes by Keywords or Ingredients**  
   Users can search for recipes based on specific keywords or ingredients.
2. **Random Recipe Suggestions**  
   Random recipes are generated for variety and exploration.
3. **Diet Prediction**  
   Predicts the type of diet a user should follow based on input values such as calories, fat, and protein.

---

## Technologies
- **Node.js** and **Express.js** for Backend
- **Firestore** for Database
- **Flask** and **TensorFlow Lite** for ML API
- **Android Studio** for Mobile Development
- **Google Cloud Run** for Deployment

---

## License
This project is licensed under the MIT License.
