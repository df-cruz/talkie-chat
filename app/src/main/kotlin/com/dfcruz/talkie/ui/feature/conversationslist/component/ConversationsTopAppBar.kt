package com.dfcruz.talkie.ui.feature.conversationslist.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.component.TalkieTopAppBar
import com.dfcruz.talkie.ui.theme.TalkieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationsTopAppBar(
    modifier: Modifier = Modifier,
    onCreateConversation: () -> Unit = {}
) {
    TalkieTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Conversations",
                style = TalkieTheme.typography.titleLarge,
            )
        },
        actions = {
            IconButton(
                onClick = onCreateConversation
            ) {
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = null
                )
            }
        }
    )
}