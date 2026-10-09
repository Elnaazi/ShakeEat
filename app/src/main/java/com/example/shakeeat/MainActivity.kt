package com.example.shakeeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shakeeat.ui.theme.ShakeEatTheme
import data.AppDatabase
import data.Dish
import ui.components.DishViewModel
import ui.components.FoodSuggestionScreenLayout
import ui.components.MoodSelectionLayout
import ui.components.RecipeScreenLayout
import ui.components.ShakeScreenLayout

class MainActivity : ComponentActivity() {
    private val viewModel: DishViewModel by viewModels {
        viewModelFactory {
            initializer {
                val dao = AppDatabase.getInstance(applicationContext).dishDao()
                DishViewModel(dao)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShakeEatTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mood") {
                    composable("mood") {
                        MoodSelectionLayout(
                            navController = navController,
                            moods = viewModel.moods,
                            onMoodConfirmed = { selectedMoods ->
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("selectedMoods", selectedMoods.toList())
                                navController.navigate("shake")
                            }
                        )
                    }
                    composable("shake") {
                        val selectedMoods = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<List<String>>("selectedMoods")
                            ?: emptyList()

                        ShakeScreenLayout(
                            navController = navController,
                            selectedMoods = selectedMoods,
                            viewModel = viewModel
                        )
                    }
                    composable("food_suggestion") {
                        val selectedDish = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<Dish>("selectedDish")

                        FoodSuggestionScreenLayout(
                            navController = navController,
                            selectedDish = selectedDish
                        )
                    }
                    composable("recipe") {
                        val selectedDish = navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.get<Dish>("selectedDish")

                        RecipeScreenLayout(
                            navController = navController,
                            selectedDish = selectedDish
                        )
                    }
                }
            }
        }
    }
}