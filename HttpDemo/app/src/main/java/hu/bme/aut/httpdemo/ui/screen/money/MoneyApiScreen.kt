package hu.bme.aut.httpdemo.ui.screen.money

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.httpdemo.data.money.MoneyResult


@Composable
fun MoneyApiScreen(
    moneyViewModel: MoneyViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            moneyViewModel.getRates()
        }) {
            Text(text = "Refresh")
        }
        when (moneyViewModel.moneyUiState) {
            is MoneyUiState.Init -> Text("Press refresh to do something...")
            is MoneyUiState.Loading -> CircularProgressIndicator()
            is MoneyUiState.Success -> MoneyResultScreen(
                (moneyViewModel.moneyUiState as MoneyUiState.Success).moneyRates)
            is MoneyUiState.Error -> Text(text = "Error...")
        }
    }

}

@Composable
fun MoneyResultScreen(moneyRates: MoneyResult) {
    Column() {
        Text(text = "Base: USD")
        Text(text = "USD: ${moneyRates.rates?.uSD}")
        Text(text = "EUR: ${moneyRates.rates?.eUR}")
        Text(text = "HUF: ${moneyRates.rates?.hUF}")
        Text(text = "HUF: ${moneyRates.rates?.gBP}")
    }
}
