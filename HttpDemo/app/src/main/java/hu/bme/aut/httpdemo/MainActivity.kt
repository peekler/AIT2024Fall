package hu.bme.aut.httpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.httpdemo.ui.navigation.Screen
import hu.bme.aut.httpdemo.ui.screen.MainScreen
import hu.bme.aut.httpdemo.ui.screen.money.MoneyApiScreen
import hu.bme.aut.httpdemo.ui.theme.HttpDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HttpDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                onMoneyAPISelected = { navController.navigate(Screen.MoneyRatesAPI.route) },
                onNasaMarsAPISelected = { navController.navigate(Screen.NasaMarsAPI.route) },
            )
        }
        composable(Screen.MoneyRatesAPI.route) {
            MoneyApiScreen()
        }
        composable(Screen.NasaMarsAPI.route) {
            //NasaMarsApiScreen()
        }
    }
}

