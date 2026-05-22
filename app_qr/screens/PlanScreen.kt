package com.example.app_qr.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview

// Clase de datos para clasificar y pintar estéticamente las materias de la retícula
data class Materia(
    val nombre: String,
    val clave: String,
    val colorArea: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanScreen(navController: NavController) {
    // Definimos la paleta de colores oficial según áreas académicas para un contraste impecable (AAA)
    val colorCienciasBasicas = Color(0xFFFFCC80)  // Naranja suave (Matemáticas, Física, Química)
    val colorProgramacion = Color(0xFFA5D6A7)     // Verde pastel (Software, Algoritmos, Web)
    val colorDatosYRedes = Color(0xFF90CAF9)      // Azul luminoso (Bases de Datos, Sistemas Operativos, Redes)
    val colorDesarrolloIng = Color(0xFFEF9A9A)    // Rojo coral suave (Ingeniería de Software)
    val colorHumanasAdmin = Color(0xFFFFF59D)     // Amarillo sutil (Ética, Investigación, Administración)
    val colorResidencias = Color(0xFFCE93D8)      // Morado elegante (Especialidad / Proyecto Final)

    // Mapa completo con la retícula exacta del TecNM Campus Zongolica de image_ed3a83.jpg
    val reticulaOficial = listOf(
        "1er Semestre" to listOf(
            Materia("Cálculo Diferencial", "ACF-0901", colorCienciasBasicas),
            Materia("Fundamentos de Programación", "AED-1285", colorProgramacion),
            Materia("Taller de Ética", "ACA-0907", colorHumanasAdmin),
            Materia("Matemáticas Discretas", "AEF-1041", colorCienciasBasicas),
            Materia("Taller de Administración", "SCH-1024", colorHumanasAdmin),
            Materia("Fundamentos de Investigación", "ACC-0906", colorHumanasAdmin)
        ),
        "2do Semestre" to listOf(
            Materia("Cálculo Integral", "ACF-0902", colorCienciasBasicas),
            Materia("Programación Orientada a Objetos", "AED-1286", colorProgramacion),
            Materia("Contabilidad Financiera", "AEC-1008", colorHumanasAdmin),
            Materia("Química", "AEC-1058", colorCienciasBasicas),
            Materia("Álgebra Lineal", "ACF-0903", colorCienciasBasicas),
            Materia("Probabilidad y Estadística", "AEF-1052", colorCienciasBasicas)
        ),
        "3er Semestre" to listOf(
            Materia("Cálculo Vectorial", "ACF-0904", colorCienciasBasicas),
            Materia("Estructura de Datos", "AED-1026", colorProgramacion),
            Materia("Cultura Empresarial", "SCC-1005", colorHumanasAdmin),
            Materia("Investigación de Operaciones", "SCC-1013", colorHumanasAdmin),
            Materia("Desarrollo Sustentable", "ACD-0908", colorHumanasAdmin),
            Materia("Física General", "SCF-1006", colorCienciasBasicas)
        ),
        "4to Semestre" to listOf(
            Materia("Ecuaciones Diferenciales", "ACF-0905", colorCienciasBasicas),
            Materia("Métodos Numéricos", "SCC-1017", colorCienciasBasicas),
            Materia("Tópicos Avanzados de Programación", "SCD-1027", colorProgramacion),
            Materia("Fundamentos de Bases de Datos", "AEF-1031", colorDatosYRedes),
            Materia("Simulación", "SCD-1022", colorCienciasBasicas),
            Materia("Principios Eléctricos y Aplicaciones Digitales", "SCD-1018", colorDatosYRedes)
        ),
        "5to Semestre" to listOf(
            Materia("Graficación", "SCC-1010", colorProgramacion),
            Materia("Fundamentos de Telecomunicaciones", "AEC-1034", colorDatosYRedes),
            Materia("Sistemas Operativos", "AEC-1061", colorDatosYRedes),
            Materia("Taller de Base de Datos", "SCA-1025", colorDatosYRedes),
            Materia("Fundamentos de Ingeniería de Software", "SCC-1007", colorDesarrolloIng),
            Materia("Arquitectura de Computadoras", "SCD-1003", colorDatosYRedes)
        ),
        "6to Semestre" to listOf(
            Materia("Lenguajes y Autómatas I", "SCD-1015", colorProgramacion),
            Materia("Redes de Computadoras", "SCD-1021", colorDatosYRedes),
            Materia("Taller de Sistemas Operativos", "SCA-1026", colorDatosYRedes),
            Materia("Administración de Bases de Datos", "SCB-1001", colorDatosYRedes),
            Materia("Ingeniería de Software", "SCD-1011", colorDesarrolloIng),
            Materia("Lenguajes de Interfaz", "SCC-1014", colorDatosYRedes),
            Materia("Taller de Investigación I", "ACA-0909", colorHumanasAdmin)
        ),
        "7mo Semestre" to listOf(
            Materia("Lenguajes y Autómatas II", "SCD-1016", colorProgramacion),
            Materia("Conmutación y Enrutamiento de Redes de Datos", "SCD-1004", colorDatosYRedes),
            Materia("Taller de Investigación II", "ACA-0910", colorHumanasAdmin),
            Materia("Programación Web", "AEB-1055", colorProgramacion),
            Materia("Gestión de Proyectos de Software", "SCG-1009", colorDesarrolloIng),
            Materia("Sistemas Programables", "SCC-1023", colorDatosYRedes),
            Materia("Administración de Redes", "SCA-1002", colorDatosYRedes)
        ),
        "8vo Semestre" to listOf(
            Materia("Programación Lógica y Funcional", "SCC-1019", colorProgramacion),
            Materia("Inteligencia Artificial", "SCC-1012", colorProgramacion),
            Materia("Interacción Humana Computadora", "NEH-2102", colorHumanasAdmin),
            Materia("Lenguajes Web", "AEH-2103", colorProgramacion),
            Materia("Ingeniería Web", "AEC-2104", colorDesarrolloIng),
            Materia("Tópicos Selectos de Aplicaciones Móviles", "AEB-2105", colorProgramacion),
            Materia("Verificación y Validación de Software", "AEC-2106", colorDesarrolloIng)
        ),
        "9no Semestre" to listOf(
            Materia("Residencias Profesionales", "Plan ISIC-2010-224", colorResidencias)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Plan de Estudios",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Fondo gris suave de contraste
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "🎓 Retícula Oficial de la Carrera",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Ingeniería en Sistemas Computacionales\nTecNM Campus Zongolica",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // Renderizado de las tarjetas expandibles por cada semestre
            reticulaOficial.forEach { (semestre, materias) ->
                item {
                    ExpandableSemestreCard(semestre = semestre, materias = materias)
                }
            }
        }
    }
}

@Composable
fun ExpandableSemestreCard(semestre: String, materias: List<Materia>) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Fila de encabezado del Semestre
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = semestre,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E)
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                    tint = Color(0xFF1A237E)
                )
            }

            // Animación elegante de apertura para desplegar la lista de asignaturas de forma fluida
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(300)),
                exit = shrinkVertically(animationSpec = tween(300))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))

                    materias.forEach { materia ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Círculo o barra de color del área académica de la asignatura
                            Box(
                                modifier = Modifier
                                    .size(width = 6.dp, height = 36.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(materia.colorArea)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = materia.nombre,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                                Text(
                                    text = materia.clave,
                                    fontSize = 11.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanScreenPreview() {
    val mockNavController = androidx.navigation.compose.rememberNavController()
    PlanScreen(navController = mockNavController)
}