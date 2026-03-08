package com.dfcruz.talkie.ui.feature.createconversation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dfcruz.talkie.ui.feature.createconversation.ContactUiModel

@Composable
fun ContactsList(
    modifier: Modifier = Modifier,
    contacts: List<ContactUiModel>,
    onContactClick: (ContactUiModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(contacts, { it.id }) {
            ContactItem(name = it.name) {
                onContactClick(it)
            }
        }
    }
}