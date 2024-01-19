package br.com.ale.composefirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.ale.composefirstapp.ui.splashscreen.SplashScreen
import br.com.ale.composefirstapp.ui.theme.AppTheme
import br.com.ale.composefirstapp.ui.todolist.TodoListScreen
import br.com.ale.composefirstapp.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH_SCREEN
                ) {
                    composable(Routes.SPLASH_SCREEN) {
                        SplashScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }

                    composable(Routes.TASK_LIST) {
                        TodoListScreen()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    TodoListScreen()
}