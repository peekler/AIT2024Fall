package hu.bme.aut.httpdemo.ui.screen.nasa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.httpdemo.data.nasa.RoverPhotos
import hu.bme.aut.httpdemo.network.NasaAPI
import hu.bme.aut.httpdemo.ui.navigation.Screen
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface MarsUiState {
    data class Success(val rowerPhotsResult: RoverPhotos) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

@HiltViewModel
class NasaMarsViewModel @Inject constructor(
    val nasaAPI: NasaAPI
) : ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    init {
        getRoverPhotos("2015-6-3")
    }

    fun getRoverPhotos(date: String) {
        marsUiState = MarsUiState.Loading
        viewModelScope.launch {
            marsUiState = try {
                val result = nasaAPI.getRoverPhotos(
                    date,"DEMO_KEY"
                )
                MarsUiState.Success(result)
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}