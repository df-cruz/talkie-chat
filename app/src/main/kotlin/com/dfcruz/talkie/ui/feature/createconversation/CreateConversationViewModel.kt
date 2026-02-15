package com.dfcruz.talkie.ui.feature.createconversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfcruz.talkie.domain.usecase.CreateConversationUseCase
import com.dfcruz.talkie.system.ContactsProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateConversationViewModel(
    contactsProvider: ContactsProvider,
    private val createConversationUseCase: CreateConversationUseCase,
) : ViewModel() {

    private val _events = Channel<CreateConversationEvent>()
    val events = _events.receiveAsFlow()

    private val _contacts: MutableStateFlow<List<ContactUiModel>> = MutableStateFlow(listOf())
    val contacts: StateFlow<List<ContactUiModel>> = _contacts.asStateFlow()

    private var createConversationJob: Job? = null

    init {
        val systemContacts = contactsProvider.getContacts()
            .map {
                ContactUiModel(
                    id = it.id.orEmpty(),
                    name = it.name.orEmpty(),
                    contact = it.contact.orEmpty(),
                )
            }

        _contacts.update { systemContacts }
    }

    fun createConversation(contactName: String, contact: String) {
        if (createConversationJob?.isActive == true) return
        createConversationJob = viewModelScope.launch {
            val conversation = createConversationUseCase(contactName, contact)
            val event = if (conversation != null) {
                CreateConversationEvent.ConversationCreated(conversation.id)
            } else {
                CreateConversationEvent.ShowError("Error creating conversation")
            }
            _events.send(event)
        }
    }
}