package com.example.app_qr.screens // CORREGIDO: com.example con X

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

// 1. Importación requerida para la TopAppBar de Material 3
import androidx.compose.material3.ExperimentalMaterial3Api

// 2. Anotación para habilitar componentes de la API experimental
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(navController: NavController) {
    var currentQuestion by remember { mutableStateOf(0) }
    val answers = remember { mutableStateListOf<Int?>(null, null, null, null, null) }
    var showResult by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    val questions = listOf(
        Question(
            "1. ¿Te gusta resolver problemas complejos?",
            listOf("Sí, me encanta" to 3, "A veces" to 2, "No, prefiero evitar" to 1)
        ),
        Question(
            "2. ¿Qué tan cómodo te sientes con la tecnología?",
            listOf("Experto, me apasiona" to 3, "Uso lo básico" to 2, "No me gusta mucho" to 1)
        ),
        Question(
            "3. ¿Prefieres trabajar en equipo o individual?",
            listOf("En equipo" to 3, "Ambos por igual" to 2, "Individual" to 1)
        ),
        Question(
            "4. ¿Te gusta aprender cosas nuevas constantemente?",
            listOf("Sí, todo el tiempo" to 3, "Cuando es necesario" to 2, "Prefiero rutinas" to 1)
        ),
        Question(
            "5. ¿Te interesa crear soluciones innovadoras?",
            listOf("Es mi pasión" to 3, "Podría ser" to 2, "No me interesa" to 1)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Test Vocacional") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (showResult) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (score >= 12) Color(0xFF4CAF50) else if (score >= 8) Color(0xFFFF9800) else Color(0xFFF44336)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (score >= 12) "🎉 ¡Excelente! 🎉"
                            else if (score >= 8) "👍 ¡Buen perfil! 👍"
                            else "🤔 Podría mejorar 🤔",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Tu puntuación: $score / 15",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = if (score >= 12) "¡Tienes un perfil excelente para ISC!"
                            else if (score >= 8) "Tienes buen potencial para ISC."
                            else "Quizás deberías explorar otras áreas.",
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                currentQuestion = 0
                                score = 0
                                showResult = false
                                answers.clear()
                                repeat(questions.size) { answers.add(null) }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text(text = "Reiniciar Test", color = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { navController.navigateUp() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.8f))
                        ) {
                            Text(text = "Volver al Inicio", color = Color.Black)
                        }
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    LinearProgressIndicator(
                        progress = { (currentQuestion + 1).toFloat() / questions.size.toFloat() },
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF2196F3)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pregunta ${currentQuestion + 1} de ${questions.size}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text(
                                text = questions[currentQuestion].text,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(24.dp))

                            questions[currentQuestion].options.forEachIndexed { index, (option, _) ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = answers[currentQuestion] == index,
                                        onClick = {
                                            answers[currentQuestion] = index
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = option)
                                }
                            }
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if (currentQuestion > 0) {
                            Button(onClick = { currentQuestion-- }) {
                                Text("Anterior")
                            }
                        }

                        if (currentQuestion < questions.size - 1) {
                            Button(
                                onClick = { if (answers[currentQuestion] != null) currentQuestion++ },
                                enabled = answers[currentQuestion] != null
                            ) {
                                Text("Siguiente")
                            }
                        } else {
                            Button(
                                onClick = {
                                    score = answers.mapIndexed { index, selectedIndex ->
                                        if (selectedIndex != null) {
                                            questions[index].options[selectedIndex].second
                                        } else 0
                                    }.sum()
                                    showResult = true
                                },
                                enabled = answers.all { it != null }
                            ) {
                                Text("Ver Resultado")
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Question(
    val text: String,
    val options: List<Pair<String, Int>>
)
@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    QuizScreen(navController = androidx.navigation.NavController(context))
}