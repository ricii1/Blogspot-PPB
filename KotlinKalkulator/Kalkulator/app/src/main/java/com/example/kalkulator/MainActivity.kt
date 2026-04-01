package com.example.kalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalkulator.ui.theme.KalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Kalkulator(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Kalkulator(modifier: Modifier = Modifier) {
    // State untuk menyimpan data input dan hasil
    var angka1 by remember { mutableStateOf("") }
    var angka2 by remember { mutableStateOf("") }
    var hasil by remember { mutableStateOf("Hasil: 0") }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("KALKULATOR SEDERHANA", style = MaterialTheme.typography.headlineMedium)

        TextField(
            value = angka1,
            onValueChange = { angka1 = it },
            label = { Text("Angka Pertama") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = angka2,
            onValueChange = { angka2 = it },
            label = { Text("Angka Kedua") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("+", "-", "*", "/", "mod").forEach { op ->
                Button(
                    onClick = {
                        try {
                            val n1 = angka1.toDouble()
                            val n2 = angka2.toDouble()

                            val res = when (op) {
                                "+" -> n1 + n2
                                "-" -> n1 - n2
                                "*" -> n1 * n2
                                "/" -> {
                                    if (n2 == 0.0) throw ArithmeticException("Pembagian nol")
                                    n1 / n2
                                }
                                "mod" -> {
                                    if (n2 == 0.0) throw ArithmeticException("Modulo nol")
                                    n1 % n2
                                }
                                else -> 0.0
                            }

                            hasil = "Hasil: $res"
                            errorMsg = ""

                        } catch (e: NumberFormatException) {
                            errorMsg = "Input harus berupa angka!"
                            hasil = "Hasil: Error"

                        } catch (e: ArithmeticException) {
                            errorMsg = "Tidak bisa menghitung dengan angka nol!"
                            hasil = "Hasil: Error Pembagian atau Modulo dengan 0"

                        } catch (e: Exception) {
                            errorMsg = "Terjadi kesalahan: ${e.localizedMessage}"
                        }
                    },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(op)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(hasil, style = MaterialTheme.typography.headlineSmall)

        if (errorMsg.isNotEmpty()) {
            Text(errorMsg, color = Color.Red, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KalkulatorPreview() {
    KalkulatorTheme {
        Kalkulator()
    }
}