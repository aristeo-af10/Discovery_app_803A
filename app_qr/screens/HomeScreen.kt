package com.example.app_qr.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

// IMPORTS DE ICONOS NATIVOS DE MATERIAL 3
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Comment

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("qr") },
                containerColor = Color(0xFF25D366)
            ) {
                Icon(Icons.Default.CameraAlt, contentDescription = "QR")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                WelcomeCard()
            }

            item {
                InfoCard(
                    title = "¿Qué aprenderás?",
                    description = "Descubre las tecnologías y habilidades que desarrollarás",
                    icon = Icons.Default.School,
                    color = Color(0xFF2196F3)
                )

                ExpandableSection(
                    items = listOf(
                        "💻 Programación" to "Java, Kotlin, Python, JavaScript",
                        "🤖 Inteligencia Artificial" to "Machine Learning, Redes Neuronales",
                        "🔒 Ciberseguridad" to "Protección de datos, Ethical Hacking",
                        "🌐 Redes" to "Cisco, AWS, Cloud Computing"
                    )
                )
            }

            item {
                BotonMenu(
                    text = "Ver Plan de Estudios",
                    onClick = { navController.navigate("plan") },
                    icon = Icons.Default.MenuBook,
                    color = Color(0xFF4CAF50)
                )
            }

            item {
                BotonMenu(
                    text = "Campo Laboral",
                    onClick = { navController.navigate("campo") },
                    icon = Icons.Default.Work,
                    color = Color(0xFFFF9800)
                )
            }

            item {
                BotonMenu(
                    text = "Especialidades",
                    onClick = { navController.navigate("especialidades") },
                    icon = Icons.AutoMirrored.Filled.List,
                    color = Color(0xFF9C27B0)
                )
            }

            item {
                BotonMenu(
                    text = "Test Vocacional",
                    onClick = { navController.navigate("quiz") },
                    icon = Icons.Default.Assignment,
                    color = Color(0xFFF44336)
                )
            }

            item {
                ContactCard()
            }

            item {
                CreditsSection()
            }
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A237E)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🎓 ¡Bienvenido!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Descubre por qué ISC en el Instituto Tecnológico Superior de Zongolica es la carrera del futuro",
                color = Color(0xFFB3E5FC),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun InfoCard(title: String, description: String, icon: ImageVector, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
                Text(text = description, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ExpandableSection(items: List<Pair<String, String>>) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "📚 Contenido detallado",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Icon(
                    if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                items.forEach { (tema, detalle) ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(text = tema, fontWeight = FontWeight.Bold)
                        Text(text = detalle, fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun BotonMenu(text: String, onClick: () -> Unit, icon: ImageVector, color: Color) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ContactCard() {
    val context = LocalContext.current
    var showPhoneDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("📞 ¿Tienes dudas?", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ICONO WHATSAPP (Con funcionalidad)
                IconButton(
                    onClick = {
                        try {
                            val number = "522722833260" // Formato internacional (+52 para México)
                            val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse("https://api.whatsapp.com/send?phone=$number")
                            }
                            context.startActivity(sendIntent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "No se pudo abrir WhatsApp", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Comment,
                        contentDescription = "WhatsApp",
                        tint = Color(0xFF25D366),
                        modifier = Modifier.size(32.dp)
                    )
                }

                // ICONO EMAIL (Con funcionalidad)
                IconButton(
                    onClick = {
                        try {
                            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:difusion@zongolica.tecnm.mx")
                                putExtra(Intent.EXTRA_SUBJECT, "Informes sobre ISC - Aplicación")
                            }
                            context.startActivity(Intent.createChooser(emailIntent, "Enviar correo con:"))
                        } catch (e: Exception) {
                            Toast.makeText(context, "No se encontró una aplicación de correo", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Email",
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(32.dp)
                    )
                }

                // ICONO TELÉFONO (Abre menú emergente para seleccionar entre los números)
                IconButton(onClick = { showPhoneDialog = true }) {
                    Icon(
                        Icons.Default.Phone,
                        contentDescription = "Llamar",
                        tint = Color(0xFF1976D2),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }

    // CUADRO DE DIÁLOGO NATIVO DE MATERIAL 3 PARA SELECCIONAR NÚMERO DE TELÉFONO
    if (showPhoneDialog) {
        AlertDialog(
            onDismissRequest = { showPhoneDialog = false },
            title = { Text("Llamar al Instituto", fontWeight = FontWeight.Bold) },
            text = { Text("Selecciona la oficina a la que deseas comunicarte:") },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                        onClick = {
                            showPhoneDialog = false
                            try {
                                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2787326716"))
                                context.startActivity(callIntent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "No se pudo realizar la llamada", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Oficinas Centrales (278 732 67 16)")
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E)),
                        onClick = {
                            showPhoneDialog = false
                            try {
                                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:2727270449"))
                                context.startActivity(callIntent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "No se pudo realizar la llamada", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Nogales/Alterno (272 727 04 49)")
                    }

                    TextButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { showPhoneDialog = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        )
    }
}

@Composable
fun CreditsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.LightGray.copy(alpha = 0.5f)
        )
        Text(
            text = "Aplicación Desarrollada Por:",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Daymara Angeles Monroy, Jesus Aristeo Arenas Facio, Brisa del Carmen Gines Palestino, Juan Uriel Guapillo Rodriguez y Katia Rojas Serrano",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1A237E), // Azul institucional del Tec
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Grupo 803-A. Extensión Académica de Nogales",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current
    val mockNavController = NavController(context)
    HomeScreen(navController = mockNavController)
}