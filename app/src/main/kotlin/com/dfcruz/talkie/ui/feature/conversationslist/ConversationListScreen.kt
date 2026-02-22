package com.dfcruz.talkie.ui.feature.conversationslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.R
import com.dfcruz.talkie.ui.feature.conversationslist.component.ConversationItem
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(
    modifier: Modifier = Modifier,
    viewModel: ConversationListViewModel = koinViewModel(),
    onClickConversation: (String) -> Unit,
    onCreateConversation: () -> Unit,
) {
    val conversations = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Conversations")
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
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(conversations.value, { it.id }) {
                ConversationItem(
                    modifier = Modifier.clickable {
                        onClickConversation(it.id)
                    },
                    conversation = it,
                )
            }
        }
    }
}