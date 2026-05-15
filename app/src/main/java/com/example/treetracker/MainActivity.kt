package com.example.treetracker


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.treetracker.ui.theme.TreeTrackerTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import android.annotation.SuppressLint
import com.google.android.gms.location.LocationServices
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

val Sandal = Color(0xFFD7B98E)
val DarkWood = Color(0xFF421404)
val LightCream = Color(0xFFFFF3E0)
val treeList = mutableListOf<Tree>()
data class Tree(
    val treeId: String,
    val girth: String,
    val imageUri: String,
    val location: String,
    val age: String
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreeTrackerTheme {
                MainScreen()
            }
        }

    }
}
@Composable
fun MainScreen() {

    var currentScreen by remember { mutableStateOf("home") }
    val context = androidx.compose.ui.platform.LocalContext.current

    Scaffold { padding ->

        if (currentScreen == "home") {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightCream)
                    .padding(padding)
                    .padding(16.dp)
            ) {

                Text(
                    text = "🌳 Gandha-Siri Tree Tracker",
                    style = MaterialTheme.typography.headlineMedium,
                    color = DarkWood
                )
                Text(
                    text = "Smart Sandalwood Tree Tracker",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF6D4C41)
                )
                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        WoodButton(
                            text = "🌳 Register Tree",
                            onClick = { currentScreen = "add" }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        WoodButton(
                            text = "📋 View Trees",
                            onClick = { currentScreen = "view" }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        WoodButton(
                            text = "📈 Growth Tracker",
                            onClick = { currentScreen = "growth" }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        WoodButton(
                            text = "🚨 Send Alert",
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "🚨 Security Alert sent to nearby farmers/neighbors!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    }
                }
            }
        } else if (currentScreen == "add") {


            AddTreeScreen {
                currentScreen = "home"
            }

        } else if (currentScreen == "view") {

            ViewTreesScreen {
                currentScreen = "home"
            }
        }
        else if (currentScreen == "growth") {
            GrowthTrackerScreen {
                currentScreen = "home"
            }
        }
    }
}
@SuppressLint("MissingPermission")
@Composable
fun AddTreeScreen(onBack: () -> Unit) {

    var girth by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(10.dp))

    var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri
    }
    val context = androidx.compose.ui.platform.LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationText by remember { mutableStateOf("Location not fetched") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5EFE6))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                    Text(
                        text = "🌳 Register New Tree",
                        style = MaterialTheme.typography.headlineMedium,
                        color = DarkWood
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = girth,
                        onValueChange = { girth = it },
                        label = { Text("Enter Girth") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("Enter Tree Age") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    WoodButton(
                        text = "📷 Select Tree Photo",
                        onClick = {
                            imagePickerLauncher.launch("image/*")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                if (selectedImageUri != null) {

                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = "Selected Tree Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                } else {

                    Text(
                        text = "No photo selected",
                        color = Color.Gray
                    )
                }

                    Spacer(modifier = Modifier.height(20.dp))

                    WoodButton(
                        text = "📍 Get Location",
                        onClick = {
                            fusedLocationClient.getCurrentLocation(
                                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                                null
                            ).addOnSuccessListener { location ->
                                if (location != null) {
                                    locationText =
                                        "Lat: ${location.latitude}, Lng: ${location.longitude}"
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Location unavailable",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(locationText)

                    Button(
                        onClick = {

                            val tree = Tree(
                                treeId = "TREE_${System.currentTimeMillis()}",
                                girth = girth,
                                imageUri = selectedImageUri?.toString() ?: "No photo",
                                location = locationText,
                                age = age
                            )

                            treeList.add(tree)

                            onBack()
                        },

                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Tree")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    WoodButton(
                        text = "⬅ Back",
                        onClick = { onBack() }
                    )
                }
            }
        }
    }
@Composable
fun ViewTreesScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registered Trees",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        treeList.forEach { tree ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF3E9DC)
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "🌳 ${tree.treeId}",
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkWood
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("📏 Girth: ${tree.girth} cm")

                    Text("📅 Age: ${tree.age} years")

                    Text(
                        "🌲 Heartwood Estimate: ${
                            calculateMaturity(tree.age)
                        } years remaining"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AsyncImage(
                        model = tree.imageUri,
                        contentDescription = "Tree Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text("📍 ${tree.location}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

@Composable
fun GrowthTrackerScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Growth Tracker",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        treeList.forEach { tree ->
            val girthValue = tree.girth.toFloatOrNull() ?: 0f

            Text("${tree.treeId} - Girth: ${tree.girth} cm")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(Sandal)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(girthValue / 100f)
                        .height(24.dp)
                        .background(DarkWood)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = { onBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

fun calculateMaturity(age: String): Int {
    val ageValue = age.toIntOrNull() ?: 0
    val remainingYears = 15 - ageValue
    return if (remainingYears < 0) 0 else remainingYears
}
@Composable
fun WoodButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkWood
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}