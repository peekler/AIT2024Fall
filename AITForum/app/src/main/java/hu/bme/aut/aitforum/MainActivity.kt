package hu.bme.aut.aitforum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.aitforum.ui.navigation.Screen
import hu.bme.aut.aitforum.ui.screen.login.LoginScreen
import hu.bme.aut.aitforum.ui.screen.messages.MessagesScreen
import hu.bme.aut.aitforum.ui.screen.writemessage.WriteMessageScreen
import hu.bme.aut.aitforum.ui.theme.AITForumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AITForumTheme {
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
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Messages.route)
                }
            )
        }
        composable(Screen.Messages.route) {
            MessagesScreen(
                onWriteMessageClick = {
                    navController.navigate(Screen.WritePost.route)
                }
            )
        }
        composable(Screen.WritePost.route) {
            WriteMessageScreen()
        }
    }
}
