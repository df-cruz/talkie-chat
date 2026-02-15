package com.dfcruz.talkie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.dfcruz.talkie.ui.feature.chat.ChatNavRoute
import com.dfcruz.talkie.ui.feature.chat.ChatScreen
import com.dfcruz.talkie.ui.feature.chat.ChatScreenViewModel
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListNavRoute
import com.dfcruz.talkie.ui.feature.conversationslist.ConversationListScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TalkieNavDisplay(
    modifier: Modifier = Modifier,
) {
    val backStack = rememberNavBackStack(ConversationListNavRoute)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<ChatNavRoute> { key ->
                ChatScreen(viewModel = koinViewModel<ChatScreenViewModel> {
                    parametersOf(key)
                })
            }
            entry<ConversationListNavRoute> {
                ConversationListScreen { id ->
                    backStack.add(ChatNavRoute(id))
                }
            }
        }
    )
}