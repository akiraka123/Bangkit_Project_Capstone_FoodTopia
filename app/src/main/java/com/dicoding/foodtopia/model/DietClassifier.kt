package com.dicoding.foodtopia.model

import android.content.Context
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class DietClassifier(private val context: Context) {
    private var interpreter: Interpreter? = null
    
    init {
        try {
            val modelFile = "model.tflite"
            val fileDescriptor = context.assets.openFd(modelFile)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            
            val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            interpreter = Interpreter(modelBuffer)
            Log.d("DietClassifier", "Model loaded successfully")
        } catch (e: Exception) {
            Log.e("DietClassifier", "Error loading model", e)
            e.printStackTrace()
        }
    }

    fun classify(
        calories: Float,
        fatContent: Float,
        saturatedFatContent: Float,
        sodiumContent: Float,
        carbohydrateContent: Float,
        fiberContent: Float,
        sugarContent: Float,
        proteinContent: Float
    ): Int {
        if (interpreter == null) {
            Log.e("DietClassifier", "Interpreter is null")
            return 0
        }

        try {
            // Prepare input data
            val inputArray = floatArrayOf(
                calories,
                fatContent,
                saturatedFatContent,
                sodiumContent,
                carbohydrateContent,
                fiberContent,
                sugarContent,
                proteinContent
            )
            
            Log.d("DietClassifier", "Input values: ${inputArray.joinToString()}")
            
            val inputBuffer = ByteBuffer.allocateDirect(8 * 4) // 8 values * 4 bytes per float
            inputBuffer.order(ByteOrder.nativeOrder())
            for (value in inputArray) {
                inputBuffer.putFloat(value)
            }
            
            // Prepare output buffer for 5 float values (5 classes)
            val outputBuffer = ByteBuffer.allocateDirect(5 * 4) // 5 values * 4 bytes per float
            outputBuffer.order(ByteOrder.nativeOrder())
            
            // Run inference
            interpreter?.run(inputBuffer, outputBuffer)
            
            // Get result - find index of highest probability
            outputBuffer.rewind()
            var maxIndex = 0
            var maxValue = outputBuffer.getFloat()
            
            for (i in 1..4) {
                val value = outputBuffer.getFloat()
                if (value > maxValue) {
                    maxValue = value
                    maxIndex = i
                }
            }
            
            // Add 1 because our classes are 1-based (1 to 5)
            val result = maxIndex + 1
            Log.d("DietClassifier", "Classification result: $result (probability: $maxValue)")
            return result
            
        } catch (e: Exception) {
            Log.e("DietClassifier", "Error during classification", e)
            e.printStackTrace()
            return 0
        }
    }

    fun getDietLabel(classIndex: Int): String {
        return when (classIndex) {
            1 -> "Low Calorie and Low Fat Diet"
            2 -> "High Protein Diet"
            3 -> "Low Carb Diet"
            4 -> "Balanced Healthy Diet"
            5 -> "Not a Diet Food"
            else -> "Unclassified"
        }
    }

    fun close() {
        interpreter?.close()
    }
}
