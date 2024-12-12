# CapstoneProject-FoodTopia

## Product Name: “FoodTopia: Revolutionizing Nutritional Management“

### Theme: “Health Innovation: Empowering Communities for Better Nutrition and Well-being”

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
<img src="https://via.placeholder.com/800x350" alt="Architecture" width="auto" height=350>

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

### Steps
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build the project and resolve any dependencies.
4. Run and debug the project on your preferred Android emulator or device.

---

## App Screenshots

### Onboarding Pages
<img src="https://via.placeholder.com/350x600" alt="Onboarding 1" width="auto" height=350>
<img src="https://via.placeholder.com/350x600" alt="Onboarding 2" width="auto" height=350>

### Recipe Pages
<img src="https://via.placeholder.com/350x600" alt="Recipe 1" width="auto" height=350>
<img src="https://via.placeholder.com/350x600" alt="Recipe 2" width="auto" height=350>

### Nutrition Pages
<img src="https://via.placeholder.com/350x600" alt="Nutrition 1" width="auto" height=350>
<img src="https://via.placeholder.com/350x600" alt="Nutrition 2" width="auto" height=350>

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
