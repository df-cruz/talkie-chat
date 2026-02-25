package com.dfcruz.talkie.ui.feature.createconversation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.component.Avatar
import com.dfcruz.talkie.ui.component.AvatarSize
import com.dfcruz.talkie.ui.theme.TalkieTheme
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateConversationScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateConversationViewModel = koinViewModel(),
    onClosed: () -> Unit,
    onCreatedConversation: (String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is CreateConversationEvent.ConversationCreated -> {
                    onCreatedConversation(event.conversationId)
                }

                is CreateConversationEvent.ShowError -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    val messages = viewModel.contacts.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Create Conversation")
                },
                navigationIcon = {
                    IconButton(onClick = onClosed) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(messages.value, { it.id }) {
                ContactItem(name = it.name) {
                    viewModel.createConversation(it.name, it.contact)
                }
            }
        }
    }
}

@Composable
private fun ContactItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            name = name,
            size = AvatarSize.Medium,
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = name,
            style = TalkieTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ContactItemPreview() {
    TalkieTheme {
        ContactItem(name = "John Doe") { }
    }
}