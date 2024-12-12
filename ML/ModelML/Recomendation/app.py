import re

import joblib
import pandas as pd
from flask import Flask, jsonify, request
from sklearn.metrics.pairwise import cosine_similarity

# Load model dan vectorizer
vectorizer = joblib.load("tfidf_vectorizer.pkl")
data = pd.read_csv("processed_recipes.csv")


# Fungsi untuk membersihkan input bahan makanan
def clean_ingredients(ingredients):
    if isinstance(ingredients, str):
        return re.sub(
            r"[^\w\s]", "", ingredients.lower()
        )  # Hapus karakter khusus dan ubah ke lowercase
    return ""


# Fungsi untuk merekomendasikan makanan
def recommend_food(user_input, top_n=5):
    user_input_cleaned = clean_ingredients(user_input)
    user_tfidf = vectorizer.transform([user_input_cleaned])

    similarity_scores = cosine_similarity(
        user_tfidf, vectorizer.transform(data["ingredients_cleaned"])
    )
    top_indices = similarity_scores[0].argsort()[-top_n:][::-1]

    recommendations = data.iloc[top_indices][["Name", "ingredients_cleaned"]]
    return recommendations


# Membuat aplikasi Flask
app = Flask(__name__)


# API Endpoint untuk mendapatkan rekomendasi makanan
@app.route("/recommend", methods=["POST"])
def recommend():
    try:
        # Ambil input bahan makanan dari request
        input_data = request.json
        user_input = input_data.get("ingredients", "")

        # Dapatkan rekomendasi
        recommendations = recommend_food(user_input)

        # Format rekomendasi menjadi JSON
        result = recommendations.to_dict(orient="records")

        return jsonify({"recommendations": result}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 400


# Jalankan server Flask
if __name__ == "__main__":
    app.run(debug=True)
