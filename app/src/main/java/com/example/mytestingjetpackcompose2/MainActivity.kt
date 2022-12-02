package com.example.mytestingjetpackcompose2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytestingjetpackcompose2.ui.theme.MyTestingJetPackCompose2Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTestingJetPackCompose2Theme{
                Surface(modifier = Modifier.fillMaxSize()) {
                  //  MessageCard(Message("Juan Acuña", "Jetpack Compose"))
                    Conversation(SampleData.conversationSample)
                }
            }

        }
    }
}

data class Message (val title: String, val body: String)

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        messages.map { item { MessageCard(it) } }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter =  painterResource(R.drawable.mobdev_desing),
            contentDescription ="Logo",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) } //variable is expanded or not

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.secondary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
        Text(
            text = msg.title,
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle1
        )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
        Text(
            text = msg.body,
            modifier = Modifier.padding(all = 4.dp),
            // If the message is expanded, we display all its content, otherwise we only display the first line
            maxLines = if (isExpanded) Int.MAX_VALUE else 1,
            style = MaterialTheme.typography.body2
        )
    }
        }
    }
}

@Preview(name = "Ligth Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    MyTestingJetPackCompose2Theme {
        Surface {
            Conversation(SampleData.conversationSample)
            /*MessageCard(
                msg = Message("Juan Acuña", "Take a look at Jetpack Compose, it's great!")
            )*/
        }
    }
}


/*
@Preview
@Composable
fun PreviewConversation() {
    MyTestingJetPackCompose2Theme {
        Conversation(SampleData.conversationSample)
    }
}*/
