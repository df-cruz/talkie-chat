package com.dfcruz.talkie.ui.feature.createconversation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.feature.createconversation.component.ContactsList
import com.dfcruz.talkie.ui.feature.createconversation.component.CreateConversationTopAppBar
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

    val contacts = viewModel.contacts.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = { CreateConversationTopAppBar(onBackPressed = onClosed) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        ContactsList(
            modifier = Modifier.padding(padding),
            contacts = contacts.value,
            onContactClick = {
                viewModel.createConversation(it.name, it.contact)
            },
        )
    }
}