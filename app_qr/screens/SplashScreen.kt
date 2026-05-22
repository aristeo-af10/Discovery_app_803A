package com.example.app_qr.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.app_qr.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0.6f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Lanzamos las animaciones de escala y opacidad en paralelo para una entrada elegante
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )
        }
        launch {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            )
        }

        // Tiempo de visualización de la Splash Screen (2.8 segundos)
        delay(2800)

        // Navegación fluida hacia la pantalla principal
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212), // Gris carbón ultra oscuro (evita que la pantalla se vea completamente plana)
                        Color(0xFF000000)  // Negro absoluto en la base
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
                .padding(horizontal = 24.dp) // Colchón a los lados para que el texto no toque los bordes
        ) {
            Image(
                painter = painterResource(id = R.drawable.isc), // Tu imagen isc.png
                contentDescription = "Logotipo del Instituto Tecnológico Superior de Zongolica",
                modifier = Modifier
                    .size(180.dp) // Tamaño óptimo para mantener equilibrio visual
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Instituto Tecnológico Superior de Zongolica",
                fontSize = 22.sp, // Tamaño limpio para múltiples líneas
                fontWeight = FontWeight.Bold,
                color = Color.White, // Blanco puro para un contraste perfecto sobre el fondo negro
                textAlign = TextAlign.Center, // Fuerza el centrado de cada línea
                modifier = Modifier.fillMaxWidth() // Usa todo el ancho para centrarse correctamente
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ingeniería en Sistemas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF81D4FA), // Celeste luminoso de alta visibilidad en pantallas oscuras
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Computacionales",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF81D4FA), // Celeste luminoso de alta visibilidad en pantallas oscuras
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    SplashScreen(navController = androidx.navigation.NavController(context))
}