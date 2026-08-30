package ui.components

import com.example.shakeeat.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.shakeeat.ui.theme.background_beige

@Composable
fun ShakeScreenLayout(modifier : Modifier = Modifier){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .background(color = background_beige),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Text(
            text = stringResource(R.string.shake_screen)
        )
    }
}
