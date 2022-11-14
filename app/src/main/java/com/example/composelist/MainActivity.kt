package com.example.composelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelist.ui.theme.ComposeListTheme

data class BroadcastMessage(
    val title: String,
    val message: String,
    val color: Color
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                BroadcastMessages()
            }
        }
    }
}

@Composable
private fun BroadcastMessages(
    messages: List<BroadcastMessage> = listOf(
        BroadcastMessage("sajashjsdj dsjhgdsjh bsjdasjhas usagdjyasg sdgahs ", "hjsdgjhsdgh hjdgfjhdsgfjhsdg fjhgfjdhgfjdyfgjydgfjyg fjysdgfjygdfjegfjhewgfhjewgfjhgewhf wegjhfgwehjfgewhjfghjwefgwjehfg ewjhfgejwhfgwjefgjwehfghjwefgew fgjweh", Color.Gray),
        BroadcastMessage("Surprise Test Alert", "ashashdasvshj gshjd sdhgsdjgjdewgdedfgej dfjegfjehwg fjewgfjewfg ejwgf", Color.Gray),
        BroadcastMessage("Fire Alert", "sjfsdhfgdjhgfdjh fdhgfhjdgfhd fhdgfhsdgfhdsgfjdhsfgjshdgfhjdsgfjhsdgfhjsdgfjhdsgfjhdgfjh gfhdjhsfgj ds", Color.Gray),
        BroadcastMessage("Earthquake Alert", "hjfdhjfdhjfgdhj dgfhdgfdhjf hdgfhdgf hdsgdhjgf sdhfgdhfgdsh fhdsfhsdfghds fdgsfhgsd fhsdjhfgdsjh ", Color.Gray),
        BroadcastMessage("Meteorite Alert", "sjhsjhdgfhjd fdhjgfjhdsgf dgfjdshgfjsdgfyjesgfjhgjygf udfgeufg sjefg s", Color.Gray),
        BroadcastMessage("Tsunami Alert", "hdshfdehj fhesgfhje fhegfjhewgfjhew fjhewgjfhegwfewjyf gegjwfgewfgeywfghewdgshdgfhsdgfh dsfghdsgfhdsgfhgdsgfh fgefgsdhfgsegfjyesgjhdgfjhsdgfjhsdgfhsdgfhsdgfewygfhds", Color.Gray),
        BroadcastMessage("Water pollution Alert", "asfwdsfa fesfwef wewfe wwefew erfergregre gregrr g reg regreger greger gergreg ergerg erg reg regerg erg ergr egerg erg erger gregreg reg erg ergerg erger gergerg erger gerg erg erg reg ergreg reg regregregr hrehr th rth rthth rth trhth th trhrthrer rth trj trjtjyj ", Color.Gray),
        BroadcastMessage("Air pollution Alert", "ashjsdh efguwe fuew gjuewguje gufegufgewuf ewfgey gfeuyw gfyewgfywe yweuywge ygfewuyfgeuyfgeruy fgewruyfgwuyergfywef gwueygfuwyegfu", Color.Gray),
        BroadcastMessage("Spaceship crash Alert", "hddfhdhjfg djgfjdgfjegfye fyewgfyewg uf gewuyf fguwyegfueywgfuyewgfuyewg fuywegfuyewgfuygewuywefuyfweuyfeuywf uwey", Color.Gray)
    )
) {
    LazyColumn {
        items(items = messages) { message ->
            SingleBroadcastMessage(broadcastMessage = message)
            Divider(color = Color.Black)
        }  
    }
}

@Composable
private fun SingleBroadcastMessage(broadcastMessage: BroadcastMessage) {

    Surface(
        color = broadcastMessage.color,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Text(
                    text = broadcastMessage.title,
                    modifier = Modifier.weight(1f) ,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1
                )
                Image(painter = painterResource(id = R.drawable.ic_baseline_add_circle_24), modifier = Modifier.size(45.dp) , contentDescription = "Broadcast message icon")
            }
            Text(text = broadcastMessage.message, modifier = Modifier.padding(bottom = 14.dp))
        }
    }
}

@Preview(showBackground = true, name = "Single broadcast message", widthDp = 320, heightDp = 320)
@Composable
fun SingleBroadcastMessagePreview() {
    ComposeListTheme {
        BroadcastMessages()
    }
}