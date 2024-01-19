package br.com.ale.composefirstapp.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ale.composefirstapp.R
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
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_image),
                contentDescription = "Logo TODO"
            )
            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier.width(170.dp)) {
                Text(
                    text="Seu aplicativo favorito de afarezes", style = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        textAlign = TextAlign.Center
                    ),
                )

            }
        }
    }
}

@Preview
@Composable
fun SplashScreen() {
    Main()
}