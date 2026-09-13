package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shakeeat.ui.theme.background_beige
import com.example.shakeeat.ui.theme.button_beige
import com.example.shakeeat.ui.theme.shadow
import data.Dish


@Composable
fun FoodSuggestionScreenLayout(navController: NavController, modifier: Modifier = Modifier, selectedDish : Dish?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_beige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ){
        Image(
            painter = painterResource(),
            contentDescription = "Food suggestion image",
        )
        Button(
            onClick = { navController.navigate("recipe")},
            modifier = Modifier
                .shadow(
                    elevation = 13.dp ,
                    shape = RoundedCornerShape(percent = 70),
                    ambientColor = shadow
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = button_beige
            )
        ){
            Text(
                text = "Let's get cooking!",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("mood")},
            modifier = Modifier
                .shadow(
                    elevation = 13.dp ,
                    shape = RoundedCornerShape(percent = 70),
                    ambientColor = shadow
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = button_beige
            )
        ){
            Text(
                text = "Not in the mood for this",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun FoodSuggestionScreenPreview() {
    val navController = rememberNavController()
    FoodSuggestionScreenLayout(
        navController = navController,
        )
}


