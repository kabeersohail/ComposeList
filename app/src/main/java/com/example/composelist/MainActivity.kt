package com.example.composelist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebView
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.composelist.Constants.ENCODING
import com.example.composelist.Constants.HTML_TAGS
import com.example.composelist.Constants.IMAGE_TAG
import com.example.composelist.Constants.LINKS
import com.example.composelist.Constants.LLLL
import com.example.composelist.Constants.MIME_TYPE
import com.example.composelist.Constants.SAMPLE_HTML_ONE
import com.example.composelist.Constants.SAMPLE_HTML_THREE
import com.example.composelist.Constants.SAMPLE_HTML_TWO
import com.example.composelist.ui.theme.ComposeListTheme

data class BroadcastMessage(
    val title: String,
    val message: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                Greetings()
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to basics code labs")
        Button(
            modifier = Modifier.padding(24.dp),
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
    val expanded = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.gray_100))
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
                .fillMaxWidth()
        ) {
            val mContext = LocalContext.current
            val webView = remember { WebView(mContext) }
            val titleView = remember {
                TextView(mContext)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Surface(modifier = Modifier
                    .weight(1F, false)
                    .clip(RoundedCornerShape(10.dp))) {
                    AndroidView(factory = { titleView }) { broadcastTitle ->
                        broadcastTitle.setPadding(24, 0, 24, 0)
                        broadcastTitle.text = broadcastMessage.title
                        broadcastTitle.textSize = 30F
                        broadcastTitle.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray_200))
                        broadcastTitle.ellipsize = TextUtils.TruncateAt.MARQUEE
                        broadcastTitle.isSingleLine = true
                        broadcastTitle.marqueeRepeatLimit = -1
                        broadcastTitle.setOnClickListener {
                            broadcastTitle.isSelected = !broadcastTitle.isSelected
                        }
                        broadcastTitle.typeface = Typeface.DEFAULT_BOLD
                        broadcastTitle.setTextColor(ContextCompat.getColor(mContext, R.color.title_text_color))
                    }
                }

                OutlinedButton(onClick = { expanded.value = !expanded.value }, shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor =  colorResource(id = R.color.gray_200))) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded.value) {
                            stringResource(id = R.string.show_less)
                        } else {
                            stringResource(id = R.string.show_more)
                        },
                        tint = colorResource(id = R.color.link_gray)
                    )
                }
            }

            if(expanded.value) {
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .wrapContentHeight()
                ) {
                    AndroidView(factory = { webView }, modifier = Modifier
                        .fillMaxWidth()) { webView ->
                        webView.loadData(broadcastMessage.message, MIME_TYPE , ENCODING)
                    }

                    webView.webChromeClient = WebChromeClient()
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.gray_100))) {
        Text(text = "Message delivered time", modifier = Modifier.align(CenterHorizontally), color = colorResource(
            id = R.color.title_text_color
        ))
    }

}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    broadcastMessages: List<BroadcastMessage> = listOf(
        BroadcastMessage("TAG 1", SAMPLE_HTML_ONE),
        BroadcastMessage("TAG 2", SAMPLE_HTML_TWO),
        BroadcastMessage("TAG 3", SAMPLE_HTML_THREE),
        BroadcastMessage("TAG 4", IMAGE_TAG),
        BroadcastMessage("TAG 5", HTML_TAGS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 6", LINKS),
        BroadcastMessage("TAG 7",LLLL)
    )
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(Color.Black)
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

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun CardPreview() {
    ComposeListTheme {
        CardContent(BroadcastMessage("",""))
    }
}

















