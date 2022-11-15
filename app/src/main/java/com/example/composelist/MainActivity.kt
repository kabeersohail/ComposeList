package com.example.composelist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.util.LinkifyCompat
import com.example.composelist.ui.theme.ComposeListTheme

data class BroadcastMessage(
    val title: String,
    val message: String
)

const val loremIpsum =
    "Hello This is a text containing a lot of links including www.google.com, www.netflix.com, www.gmail.com, www.amazonprime.com etc..."

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = colorResource(id = R.color.gray_100)) {
        if (shouldShowOnboarding) {
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


    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.gray_200))
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            val mContext = LocalContext.current
            val messageView = remember { TextView(mContext) }
            val titleView = remember {
                TextView(mContext)
            }

            AndroidView(factory = { titleView }) { broadcastTitle ->
                broadcastTitle.background = mContext.getDrawable(R.drawable.lite_gray_rectangle)
                broadcastTitle.setPadding(24, 0, 24, 0)
                broadcastTitle.text = broadcastMessage.title
                broadcastTitle.textSize = 30F
                broadcastTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
                broadcastTitle.isSingleLine = true
                broadcastTitle.marqueeRepeatLimit = -1
                broadcastTitle.setOnClickListener {
                    broadcastTitle.isSelected = !broadcastTitle.isSelected
                }
                broadcastTitle.typeface = Typeface.DEFAULT_BOLD
                broadcastTitle.setTextColor(mContext.resources.getColor(R.color.title_text_color))
            }

            AndroidView(factory = { messageView }) { textView ->
//                textView.background = mContext.getDrawable(R.drawable.lite_gray_rectangle)
                textView.setPadding(24, 24, 24, 24)
                textView.text = broadcastMessage.message
                LinkifyCompat.addLinks(textView, Linkify.WEB_URLS)
                textView.textSize = 18F
                textView.setLinkTextColor(mContext.resources.getColor(R.color.link_gray))
                textView.movementMethod = LinkMovementMethod.getInstance()
                textView.maxLines = if (expanded.value) Int.MAX_VALUE else 3
                textView.setTextColor(mContext.resources.getColor(R.color.message_text_color))
            }

//            Text(text = broadcastMessage.message, color = colorResource(id = R.color.text_gray), maxLines = if(expanded.value) Int.MAX_VALUE else 3)

        }
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) {
                    stringResource(id = R.string.show_less)
                } else {
                    stringResource(id = R.string.show_more)
                },
                tint = Color.LightGray
            )
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    broadcastMessages: List<BroadcastMessage> = listOf(
        BroadcastMessage("Title is very very very very long title", loremIpsum),
        BroadcastMessage(
            "This is based on text length, just need to check what is the max value before it allows marquee",
            loremIpsum
        ),
        BroadcastMessage("Title 3", loremIpsum),
        BroadcastMessage("Title 4", loremIpsum),
        BroadcastMessage("Title 5", loremIpsum),
        BroadcastMessage("Title 6", loremIpsum),
        BroadcastMessage("Title 7", loremIpsum),
        BroadcastMessage("Title 8", loremIpsum),
    )
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(colorResource(id = R.color.gray_100)),
    ) {
        items(items = broadcastMessages) { broadcastMessage ->
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

















