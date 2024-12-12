from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np

# Load TFLite model
interpreter = tf.lite.Interpreter(model_path="model/diet_model.tflite")
interpreter.allocate_tensors()

# Get input and output details
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()

    # Extract input values
    input_data = np.array([[
        data['Calories'],
        data['FatContent'],
        data['SaturatedFatContent'],
        data['SodiumContent'],
        data['CarbohydrateContent'],
        data['FiberContent'],
        data['SugarContent'],
        data['ProteinContent']
    ]], dtype=np.float32)

    # Set input tensor
    interpreter.set_tensor(input_details[0]['index'], input_data)
    interpreter.invoke()

    # Get output tensor
    output_data = interpreter.get_tensor(output_details[0]['index'])
    predicted_index = np.argmax(output_data)

    # Map prediction to category
    categories = [
        "Diet Rendah Kalori dan Lemak",
        "Diet Tinggi Protein",
        "Diet Rendah Karbohidrat",
        "Diet Sehat Seimbang",
        "Bukan termasuk makanan diet"
    ]

    response = {
        "category": categories[predicted_index],
        "prediction": int(predicted_index + 1)
    }
    return jsonify(response)

if __name__ == "__main__":
    app.run(port=5000)
