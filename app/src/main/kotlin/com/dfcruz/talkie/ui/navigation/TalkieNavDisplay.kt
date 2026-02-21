package com.dfcruz.talkie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.dfcruz.talkie.ui.feature.chat.ChatNavRoute
import com.dfcruz.talkie.ui.feature.chat.ChatScreen
import com.dfcruz.talkie.ui.feature.chat.ChatScreenViewModel
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListNavRoute
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListScreen
import com.dfcruz.talkie.ui.feature.createconversation.CreateConversationNavRoute
import com.dfcruz.talkie.ui.feature.createconversation.CreateConversationScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TalkieNavDisplay(
) {
    val backStack = rememberNavBackStack(ConversationListNavRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<ChatNavRoute> { key ->
                val viewModel = koinViewModel<ChatScreenViewModel> {
                    parametersOf(key.conversationId)
                }
                ChatScreen(viewModel = viewModel) {
                    backStack.removeLastOrNull()
                }
            }
            entry<ConversationListNavRoute> {
                ConversationListScreen(
                    onClickConversation = { id -> backStack.add(ChatNavRoute(id)) },
                    onCreateConversation = { backStack.add(CreateConversationNavRoute) }
                )
            }
            entry<CreateConversationNavRoute> {
                CreateConversationScreen(
                    onClosed = { backStack.removeLastOrNull() },
                    onCreatedConversation = { id ->
                        backStack.removeLastOrNull()
                        backStack.add(ChatNavRoute(id))
                    }
                )
            }
        }
    )
}