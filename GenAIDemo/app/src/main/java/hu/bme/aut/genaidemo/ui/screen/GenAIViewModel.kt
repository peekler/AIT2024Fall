package hu.bme.aut.genaidemo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GenAIViewModel: ViewModel() {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = "YOUR_KEY_HERE"
    )

    private val _textGenerationResult = MutableStateFlow<String?>(null)
    val textGenerationResult = _textGenerationResult.asStateFlow()

    fun generateJoke(prompt: String = "Tell me a joke") {
        _textGenerationResult.value = "Generating..."
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // This is the real stuff...
                val result = generativeModel.generateContent(prompt)
                _textGenerationResult.value = result.text
            } catch (e: Exception) {
                _textGenerationResult.value = "Error: ${e.message}"
            }
        }
    }

}