package br.com.ale.composefirstapp.ui.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ale.composefirstapp.utils.Routes
import br.com.ale.composefirstapp.utils.UiEvent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        onNavigate(UiEvent.Navigate(Routes.TASK_LIST))
    }
    Main()
}

@Composable
fun Main() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash")
    }
}

@Preview
@Composable
fun SplashScreen() {
    Main()
}