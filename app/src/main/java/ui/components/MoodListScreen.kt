package ui.components


import android.R.attr.fontFamily
import android.R.attr.shape
import android.R.color.white
import android.R.id.bold
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shakeeat.R
import com.example.shakeeat.ui.theme.background_beige
import com.example.shakeeat.ui.theme.button_beige
import com.example.shakeeat.ui.theme.selected_card
import com.example.shakeeat.ui.theme.shadow
import com.example.shakeeat.ui.theme.text_brown
import com.example.shakeeat.ui.theme.white_card


@Composable
fun MoodSelectionLayout(navController: NavController, modifier : Modifier = Modifier, moods: List<String>, onMoodConfirmed: (Set<String>) -> Unit) {
    var selectedMoods by rememberSaveable {mutableStateOf(setOf<String>())}
    Column (
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(state = rememberScrollState())
            .background(background_beige),
            horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text (
            text = stringResource(R.string.mood_question),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = text_brown,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 80.dp)

        )

        moods. forEach {mood ->
            Card(
                onClick = {
                    selectedMoods = if (selectedMoods.contains(mood))
                        selectedMoods - mood
                    else
                        selectedMoods + mood
                },

                colors = if(selectedMoods.contains(mood))
                    CardDefaults.cardColors(
                        containerColor = selected_card
                    )
                    else

                    CardDefaults.cardColors(
                    containerColor = white_card
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(horizontal = 30.dp, vertical = 12.dp)
                    .shadow(
                    12.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = shadow

                )
            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = mood,
                        fontSize = 18.sp
                    )
                }
            }
        }

        Button(
            onClick = {onMoodConfirmed(selectedMoods); navController.navigate("shake")},
            modifier = Modifier
                .padding(vertical = 80.dp)
                .shadow(
                    13.dp,
                    shape = RoundedCornerShape(70),
                    ambientColor = shadow,
                    ),
            colors = ButtonDefaults.buttonColors(
                containerColor = button_beige
            )
        ){
            Text(
                text = "Let's eat!",
                fontSize = 20.sp,
                )
        }
    }
}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MoodListScreenPreview(){
    val NavController = rememberNavController()
    MoodSelectionLayout(
        navController = NavController,
        moods = listOf("lazy", "spicy", "savory", "comfort food"),
        onMoodConfirmed = {}
    )
}

