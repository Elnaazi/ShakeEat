package ui.components

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shakeeat.R
import com.example.shakeeat.ui.theme.background_beige
import data.Dish
import data.DishDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.math.sqrt

@Composable
fun ShakeScreenLayout(
    navController: NavController,
    modifier: Modifier = Modifier,
    selectedMoods: List<String>,
    viewModel: DishViewModel
) {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(SensorManager::class.java) }
    val shakeDetector = remember {
        ShakeDetector {
            val randomDish = viewModel.getRandomDishByMoods(selectedMoods)
            Log.d("ShakeDetector", "moods=$selectedMoods dish=$randomDish")

            if (randomDish != null) {
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("selectedDish", randomDish)
                navController.navigate("food_suggestion")
            }
        }
    }

    DisposableEffect(sensorManager) {
        val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_GAME)

        onDispose {
            sensorManager?.unregisterListener(shakeDetector)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = background_beige),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.shake),
            contentDescription = stringResource(R.string.shake_screen),
            modifier = Modifier
                .size(300.dp)
                .padding(top = 80.dp)
        )
        Text(
            modifier = Modifier.padding(top = 120.dp),
            text = stringResource(R.string.shake_screen),
            fontSize = 22.sp
        )
    }
}

class ShakeDetector(
    private val onShake: () -> Unit
) : SensorEventListener {
    private var lastShakeTime: Long = 0

    override fun onSensorChanged(event: SensorEvent) {
        val (x, y, z) = event.values
        val gForce = sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH

        val now = System.currentTimeMillis()
        if (gForce > SHAKE_THRESHOLD && now - lastShakeTime > SHAKE_TIME_LAPSE) {
            lastShakeTime = now
            onShake()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    companion object {
        private const val SHAKE_THRESHOLD = 1.01f
        private const val SHAKE_TIME_LAPSE = 500L
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShakeScreenPreview() {
    val previewViewModel = DishViewModel(object : DishDao {
        override fun getAllDishes(): Flow<List<Dish>> = flowOf(emptyList())

        override fun getDishesByMood(mood: String): Flow<List<Dish>> = flowOf(emptyList())

        override suspend fun insert(dish: Dish) = Unit

        override suspend fun insertAll(dishes: List<Dish>) = Unit

        override suspend fun delete(dish: Dish) = Unit

        override suspend fun update(dish: Dish) = Unit
    })

    ShakeScreenLayout(
        navController = rememberNavController(),
        selectedMoods = listOf("comfort food"),
        viewModel = previewViewModel
    )
}
