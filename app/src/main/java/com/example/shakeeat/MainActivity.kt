package com.example.shakeeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.AppDatabase
import com.example.shakeeat.ui.theme.ShakeEatTheme
import ui.components.DishViewModel
import ui.components.DishViewModelFactory
import ui.components.MoodSelectionLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val factory = DishViewModelFactory(AppDatabase.getInstance(applicationContext).dishDao())
        val viewModel = ViewModelProvider(this, factory)[DishViewModel::class.java]
        setContent {
            MoodSelectionLayout(
                moods = viewModel.moods,
                onMoodConfirmed = { selectedMoods -> }
            )
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "mood"){
                composable("mood") {
                    MoodSelectionLayout(
                        moods = viewModel.moods,
                        onMoodConfirmed = { selectedMoods ->
                            navController.navigate("shake")
                        }
                    )
                }

                composable("shake"){

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShakeEatTheme {
        Greeting("Android")
    }
}