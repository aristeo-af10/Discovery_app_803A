package com.example.app_qr.screens // CORREGIDO: com.example con X

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

// 1. Importación requerida para la barra superior de Material 3
import androidx.compose.material3.ExperimentalMaterial3Api

// 2. Añadimos la anotación experimental para habilitar el TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoLaboralScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campo Laboral") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3E0)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "💼 ¿Dónde trabajan nuestros egresados?",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Los ingenieros en sistemas son altamente demandados",
                            fontSize = 14.sp
                        )
                    }
                }
            }

            item {
                CampoCard(
                    empresa = "Google",
                    puesto = "Software Engineer",
                    salario = "$80,000 - $150,000 MXN",
                    color = Color(0xFFEA4335)
                )
            }

            item {
                CampoCard(
                    empresa = "Microsoft",
                    puesto = "Cloud Architect",
                    salario = "$75,000 - $140,000 MXN",
                    color = Color(0xFF00A4EF)
                )
            }

            item {
                CampoCard(
                    empresa = "Amazon Web Services",
                    puesto = "DevOps Engineer",
                    salario = "$70,000 - $130,000 MXN",
                    color = Color(0xFFFF9900)
                )
            }

            item {
                CampoCard(
                    empresa = "Startups Tecnológicas",
                    puesto = "Full Stack Developer",
                    salario = "$50,000 - $100,000 MXN",
                    color = Color(0xFF7B1FA2)
                )
            }

            item {
                CampoCard(
                    empresa = "Consultoras TI",
                    puesto = "IT Consultant",
                    salario = "$60,000 - $120,000 MXN",
                    color = Color(0xFF00897B)
                )
            }

            item {
                InfoAdicional()
            }
        }
    }
}

@Composable
fun CampoCard(empresa: String, puesto: String, salario: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Text("💼", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = empresa, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = puesto, fontSize = 14.sp, color = Color.Gray)
                Text(text = salario, fontSize = 12.sp, color = color, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun InfoAdicional() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📊 Datos del mercado:", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("• 95% de los egresados trabajan en el primer año")
            Text("• Salario inicial promedio: $25,000 - $35,000 MXN")
            Text("• Demanda creciente 15% anual")
            Text("• Posibilidad de trabajo remoto internacional")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CampoLaboralScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    CampoLaboralScreen(navController = androidx.navigation.NavController(context))
}