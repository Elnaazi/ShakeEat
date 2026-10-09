package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shakeeat.ui.theme.background_beige
import com.example.shakeeat.ui.theme.button_beige
import com.example.shakeeat.ui.theme.shadow

@Composable
fun AddDishScreenLayout(
    navContoller: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_beige)
    ) {
        Text(
            text = "Add a new Dish",
            fontSize = 24.sp
        )

        Button(
            onClick = { },
            modifier = Modifier
                .shadow(
                    elevation = 13.dp,
                    shape = RoundedCornerShape(70),
                    ambientColor = shadow,
                ),
            colors = ButtonDefaults.buttonColors(containerColor = button_beige),
        ) {
            Text(
                text = "Pick picture from gallery",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddDishScreenPreview() {
    val navController = rememberNavController()
    AddDishScreenLayout(
        navContoller = navController
    )
}
