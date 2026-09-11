package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun FoodSuggestionScreenLayout(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){


        Text(
            text = "Food Suggestion Screen",
            modifier = Modifier.fillMaxSize()

        )

    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun FoodSuggestionScreenPreview() {
    val NavController = rememberNavController()
    FoodSuggestionScreenLayout(
        navController = NavController,
        )
}


