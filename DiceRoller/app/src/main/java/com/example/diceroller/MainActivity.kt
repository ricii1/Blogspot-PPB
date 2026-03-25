package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp(){
    DiceRollerWithImageAndButton()
}

@Composable
fun DiceRollerWithImageAndButton(modifier: Modifier = Modifier){

    var result by remember { mutableStateOf(1) }
    var rolling by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painterResource(id = imageResource),
            contentDescription = "Dice rolled $result"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!rolling) {
                    rolling = true

                    scope.launch {
                        repeat(10) {
                            result = (1..6).random()
                            delay(80)
                        }
                        result = (1..6).random()
                        rolling = false
                    }
                }
            }
        ){
            Text(text = "Roll")
        }
    }
}