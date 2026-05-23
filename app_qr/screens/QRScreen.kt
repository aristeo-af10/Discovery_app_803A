package com.example.app_qr.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview

// Enumeración interna para alternar entre ambos contenidos de códigos QR
enum class QrResourceType {
    OFERTA_EDUCATIVA,
    RETICULA_PDF
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRScreen(navController: NavController) {
    // Enlaces de destino oficiales
    val urlOferta = "https://zongolica.tecnm.mx/?oferta-educativa=ing-en-sistemas-computacionales"
    // Enlace directo de descarga optimizado con el ID de tu archivo de Google Drive
    val urlReticulaPdf = "https://drive.google.com/uc?export=download&id=1L_7vxjUu8eHDAPL8g9yUxw6ORlMtL03T"

    // Estado para controlar qué QR se encuentra activo actualmente
    var selectedTab by remember { mutableStateOf(QrResourceType.OFERTA_EDUCATIVA) }

    // Cambia el contenido del texto y del QR dinámicamente según la pestaña seleccionada
    val currentUrl = if (selectedTab == QrResourceType.OFERTA_EDUCATIVA) urlOferta else urlReticulaPdf
    val currentTitle = if (selectedTab == QrResourceType.OFERTA_EDUCATIVA) "📱 ¡Conoce tu Carrera!" else "📄 Plan de Estudios"
    val currentDescription = if (selectedTab == QrResourceType.OFERTA_EDUCATIVA) {
        "Escanéame para abrir el portal oficial del TecNM Campus Zongolica y descubrir más de Ingeniería en Sistemas Computacionales"
    } else {
        "Escanéame para descargar directamente el PDF oficial de la retícula curricular del plan ISIC-2010-224"
    }

    // Generación del código QR de forma reactiva al cambiar la pestaña seleccionada
    val qrBitmap = remember(currentUrl) {
        generateQRCode(currentUrl)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Oferta Educativa ISC",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A237E) // Azul del Tecnológico Nacional de México
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Fondo gris de contraste uniforme
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    
                    // Selector de Pestañas Interactivas (Para cambiar entre QR Oferta y QR PDF)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (selectedTab == QrResourceType.OFERTA_EDUCATIVA) Color(0xFF1A237E) else Color.Transparent,
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable { selectedTab = QrResourceType.OFERTA_EDUCATIVA }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Portal Web",
                                color = if (selectedTab == QrResourceType.OFERTA_EDUCATIVA) Color.White else Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (selectedTab == QrResourceType.RETICULA_PDF) Color(0xFF1A237E) else Color.Transparent,
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable { selectedTab = QrResourceType.RETICULA_PDF }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Descargar PDF",
                                color = if (selectedTab == QrResourceType.RETICULA_PDF) Color.White else Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    // Título dinámico
                    Text(
                        text = currentTitle,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A237E),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Descripción dinámica
                    Text(
                        text = currentDescription,
                        color = Color.Gray,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Renderizado del código QR dinámico según el estado seleccionado
                    qrBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Código QR interactivo",
                            modifier = Modifier.size(230.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A237E) // Botón a tono con la barra superior
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Volver al Inicio",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

// Función utilitaria usando la librería ZXing para generar el código QR como un Bitmap (Intacta)
fun generateQRCode(content: String): Bitmap? {
    return try {
        val multiFormatWriter = MultiFormatWriter()
        val matrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400)
        val barcodeEncoder = BarcodeEncoder()
        barcodeEncoder.createBitmap(matrix)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Preview(showBackground = true)
@Composable
fun QRScreenPreview() {
    val context = androidx.compose.ui.platform.LocalContext.current
    QRScreen(navController = androidx.navigation.NavController(context))
}
