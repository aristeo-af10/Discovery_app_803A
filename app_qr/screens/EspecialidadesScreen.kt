package com.example.app_qr.screens // CORREGIDO: com.example con X

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview


// 2. Añadimos la anotación experimental para habilitar el TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspecialidadesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Especialidades") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "🗺️ Mapa de Especialidades",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Elige tu área de especialización en ISC",
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            val especialidades = listOf(
                Especialidad(
                    nombre = "🖥️ Desarrollo de Software",
                    descripcion = "Creación de aplicaciones web, móviles y de escritorio",
                    materias = "Arquitectura de software, Patrones de diseño, DevOps",
                    salida = "Desarrollador Full Stack, Tech Lead"
                ),
                Especialidad(
                    nombre = "🤖 Inteligencia Artificial",
                    descripcion = "Machine Learning, Deep Learning y Visión por Computadora",
                    materias = "Python, TensorFlow, Redes Neuralales, NLP",
                    salida = "Data Scientist, ML Engineer"
                ),
                Especialidad(
                    nombre = "🔒 Ciberseguridad",
                    descripcion = "Protección de sistemas y datos contra amenazas",
                    materias = "Ethical Hacking, Forense Digital, ISO 27001",
                    salida = "Security Analyst, Penetration Tester"
                ),
                Especialidad(
                    nombre = "☁️ Cloud Computing",
                    descripcion = "Infraestructura en la nube y servicios escalables",
                    materias = "AWS, Azure, Docker, Kubernetes",
                    salida = "Cloud Architect, DevOps Engineer"
                ),
                Especialidad(
                    nombre = "📊 Ciencia de Datos",
                    descripcion = "Análisis y visualización de grandes volúmenes de datos",
                    materias = "SQL, Power BI, Estadística avanzada",
                    salida = "Data Analyst, Business Intelligence"
                ),
                Especialidad(
                    nombre = "🌐 Redes y Comunicaciones",
                    descripcion = "Diseño y administración de infraestructura de red",
                    materias = "Cisco CCNA, VLAN, Firewalls, Protocolos",
                    salida = "Network Engineer, Administrator"
                )
            )

            items(especialidades) { especialidad ->
                EspecialidadCard(especialidad)
            }
        }
    }
}

data class Especialidad(
    val nombre: String,
    val descripcion: String,
    val materias: String,
    val salida: String
)

@Composable
fun EspecialidadCard(especialidad: Especialidad) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = especialidad.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = especialidad.descripcion, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // CORRECCIÓN: Se cambió el viejo Divider() por HorizontalDivider() de M3
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

            Spacer(modifier = Modifier.height(8.dp))
            Text("📚 Materias clave:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(especialidad.materias, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text("🎯 Salida laboral:", fontWeight = FontWeight.Bold, fontSize = 12.sp)
            Text(especialidad.salida, fontSize = 12.sp, color = Color(0xFF4CAF50))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun EspecialidadesScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    EspecialidadesScreen(navController = androidx.navigation.NavController(context))
}