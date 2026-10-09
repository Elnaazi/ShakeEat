package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.shakeeat.ui.theme.background_beige
import com.example.shakeeat.ui.theme.button_beige
import com.example.shakeeat.ui.theme.shadow
import data.Dish

@Composable
fun FoodSuggestionScreenLayout(
    navController: NavController,
    modifier: Modifier = Modifier,
    selectedDish: Dish? = null
) {
    val dish = selectedDish ?: Dish(
        dishName = "Spaghetti Bolognese",
        mood = "comfort food",
        imagePath = "android.resource://com.example.shakeeat/drawable/food_pasta"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background_beige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DishImage(
            dish = dish,
            modifier = Modifier
                .size(220.dp)
                .clip(RoundedCornerShape(28.dp))
                .shadow(12.dp, RoundedCornerShape(28.dp), ambientColor = shadow)
        )

        Text(
            text = dish.dishName,
            fontSize = 26.sp,
            modifier = Modifier.padding(top = 18.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = { navController.navigate("recipe") },
            modifier = Modifier
                .shadow(
                    elevation = 13.dp,
                    shape = RoundedCornerShape(percent = 70),
                    ambientColor = shadow
                ),
            colors = ButtonDefaults.buttonColors(containerColor = button_beige)
        ) {
            Text(
                text = "Let's get cooking!",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("mood") },
            modifier = Modifier
                .shadow(
                    elevation = 13.dp,
                    shape = RoundedCornerShape(percent = 70),
                    ambientColor = shadow
                ),
            colors = ButtonDefaults.buttonColors(containerColor = button_beige)
        ) {
            Text(
                text = "Not in the mood for this",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun DishImage(
    dish: Dish,
    modifier: Modifier = Modifier
) {
    val imagePath = if (!dish.imagePath.isNullOrEmpty()) {
        dish.imagePath
    } else {
        "android.resource://com.example.shakeeat/drawable/food_pasta"
    }

    AsyncImage(
        model = imagePath,
        contentDescription = dish.dishName,
        modifier = modifier
    )
}

@Composable
fun RecipeScreenLayout(
    navController: NavController,
    selectedDish: Dish? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background_beige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = selectedDish?.dishName ?: "Your dish",
            fontSize = 26.sp,
            modifier = Modifier.padding(bottom = 18.dp)
        )
        Text(
            text = "Recipe details coming soon!",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        Button(
            onClick = { navController.navigate("mood") },
            modifier = Modifier
                .shadow(
                    elevation = 13.dp,
                    shape = RoundedCornerShape(percent = 70),
                    ambientColor = shadow
                ),
            colors = ButtonDefaults.buttonColors(containerColor = button_beige)
        ) {
            Text(text = "Back to moods", fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FoodSuggestionScreenPreview() {
    val navController = rememberNavController()
    FoodSuggestionScreenLayout(
        navController = navController,
        selectedDish = Dish(
            dishName = "Spaghetti Bolognese",
            mood = "comfort food",
            imagePath = "android.resource://com.example.shakeeat/drawable/food_pasta"
        )
    )
}
