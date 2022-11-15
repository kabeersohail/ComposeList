package com.example.composelist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelist.ui.theme.ComposeListTheme
import com.example.composelist.ui.theme.LightBlue

data class BroadcastMessage(
    val title: String,
    val message: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colors.background) {
        if(shouldShowOnboarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to basics code labs")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
private fun Greeting(broadcastMessage: BroadcastMessage) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(broadcastMessage)
    }
}

@Composable
private fun CardContent(broadcastMessage: BroadcastMessage) {
    val expanded = rememberSaveable { mutableStateOf(false) }


    Row(modifier = Modifier
        .background(color = LightBlue)
        .padding(12.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = broadcastMessage.title,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
            )

            Text(
                text = broadcastMessage.message,
                maxLines = if(expanded.value) Int.MAX_VALUE else 2
            )

        }
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(imageVector = if(expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, contentDescription = if(expanded.value) {
                stringResource(id = R.string.show_less)
            } else {
                stringResource(id = R.string.show_more)
            })
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    broadcastMessages: List<BroadcastMessage> = listOf(
        BroadcastMessage("Exam alert", "Please click on following link to start your exam -> www.netflix.com. For any queries please refer to the following link www.google.com"),
        BroadcastMessage("Cyclone alert", "Please leave the town ASAP"),
        BroadcastMessage("Tsunami alert", "Please don't go to beach"),
        BroadcastMessage("Earth quake alert", "Please get out of the build"),
        BroadcastMessage("Fire alert", "Please get out of the building"),
        BroadcastMessage("What if title is very long", "Please get out of the building"),
        BroadcastMessage("What if title is very very very very very long", "Please get out of the building"),
    )
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items (items = broadcastMessages) { broadcastMessage ->
            Greeting(broadcastMessage = broadcastMessage)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun GreetingsPreview() {
    ComposeListTheme {
        Greetings()
    }
}

@Preview
@Composable
fun MyAppPreview() {
    ComposeListTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeListTheme {
        MyApp()
    }
}



@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposeListTheme {
        OnBoardingScreen(onContinueClicked = {}) // Do nothing on click.
    }
}