package com.dfcruz.talkie.ui.feature.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatScreenViewModel = koinViewModel()
) {
    val messages = viewModel.messages.collectAsStateWithLifecycle()
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(messages.value, { it.id }) {
            ChatMessage(message = it.message)
        }
    }
}


@Composable
private fun ChatMessage(
    modifier: Modifier = Modifier,
    message: String,
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
    ) {
        Text(message)
    }
}