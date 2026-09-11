package ui.components

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import com.example.shakeeat.R
import androidx.compose.foundation.background
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
import com.example.shakeeat.ui.theme.background_beige
import kotlin.math.sqrt

@Composable
fun ShakeScreenLayout(navController: NavController, modifier : Modifier = Modifier) {
    val context = LocalContext.current
    val sensorManager = remember {
        context.getSystemService(SensorManager::class.java)
    }
    val shakeDetector = remember {
        ShakeDetector {
            navController.navigate("food_suggestion")
        }
    }




    DisposableEffect(sensorManager) {
        val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(
            shakeDetector,
            accelerometer,
            SensorManager.SENSOR_DELAY_GAME
        )

        onDispose {
            sensorManager?.unregisterListener(shakeDetector)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = background_beige),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.shake),
            contentDescription = stringResource(R.string.shake_screen),
            modifier = Modifier.size(300.dp, 300.dp).padding(top = 80.dp)
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
        private val shakeThreshold = 12.0f
        private val shakeCooldown = 500L


        @RequiresApi(Build.VERSION_CODES.CUPCAKE)
        override fun onSensorChanged(event: SensorEvent) {
            val (x, y, z) = event.values
            val gForce = sqrt(x * x + y * y + z * z) / SensorManager.GRAVITY_EARTH

            val now = System.currentTimeMillis()
            if (gForce > SHAKE_THRESHOLD && now - lastShakeTime > SHAKE_TIME_LAPSE) {
                lastShakeTime = now
                onShake()
            }
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        }


        companion object {
            private const val SHAKE_THRESHOLD = 12.0f
            private const val SHAKE_TIME_LAPSE = 500
        }
    }

    @Preview(
        showBackground = true,
        showSystemUi = true
    )
    @Composable
    fun ShakeScreenPreview() {
        val NavController = rememberNavController()
        ShakeScreenLayout(
            navController = NavController,
        )
    }
