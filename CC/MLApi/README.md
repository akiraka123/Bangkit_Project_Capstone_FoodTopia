# Diet Prediction API

Diet Prediction API is a RESTful API service for predicting diet categories based on nutritional input values such as calories, fat, protein, and more. This API uses a TensorFlow Lite model deployed via Flask.

---

## Features
- Predict diet categories:
  - **Low-Calorie and Low-Fat Diet**
  - **High-Protein Diet**
  - **Low-Carbohydrate Diet**
  - **Balanced Healthy Diet**
  - **Not a Diet Food**
- REST API endpoint for integration with other applications.
- Deployable to **Google Cloud Run** for scalability and performance.

---

## Prerequisites
**Python** version 3.11 or newer.

---

## Local Installation
1. **Clone this repository:**
```bash
   git clone https://github.com/akiraka123/diet-prediction-api.git
   cd diet-prediction-api
```
2. **Run the local server:**
```bash
  python app.py
```
3. **Access the API**
```bash
  http://localhost:5000/predict
```

#### Request Body
A JSON object with the following properties:

| Field                  | Type     | Required | Description                                |
|------------------------|----------|----------|--------------------------------------------|
| `Calories`             | `float`  | Yes      | Total calories                             |
| `FatContent`           | `float`  | Yes      | Total fat content                          |
| `SaturatedFatContent`  | `float`  | Yes      | Total saturated fat content                |
| `SodiumContent`        | `float`  | Yes      | Total sodium content                       |
| `CarbohydrateContent`  | `float`  | Yes      | Total carbohydrate content                 |
| `FiberContent`         | `float`  | Yes      | Total fiber content                        |
| `SugarContent`         | `float`  | Yes      | Total sugar content                        |
| `ProteinContent`       | `float`  | Yes      | Total protein content                      |

#### Example Request Body
```json
  {
      "Calories": 200,
      "FatContent": 5,
      "SaturatedFatContent": 2,
      "SodiumContent": 150,
      "CarbohydrateContent": 20,
      "FiberContent": 5,
      "SugarContent": 8,
      "ProteinContent": 10
  }
```
### Diet Categories

The Diet Prediction API classifies food into the following diet categories:

| ID  | Category                       |
|-----|--------------------------------|
| 1   | Low-Calorie and Low-Fat Diet   |
| 2   | High-Protein Diet              |
| 3   | Low-Carbohydrate Diet          |
| 4   | Balanced Healthy Diet          |
| 5   | Not a Diet Food                |