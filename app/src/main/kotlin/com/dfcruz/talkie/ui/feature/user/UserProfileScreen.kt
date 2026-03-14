package com.dfcruz.talkie.ui.feature.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dfcruz.talkie.ui.component.Avatar
import com.dfcruz.talkie.ui.component.AvatarSize
import com.dfcruz.talkie.ui.component.TalkieTopAppBar
import com.dfcruz.talkie.ui.theme.TalkieTheme
import com.dfcruz.talkie.util.compose.ThemePreview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: UserProfileViewModel = koinViewModel(),
    onOpenChat: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    UserProfileScreen(
        modifier = modifier,
        uiState = viewModel.profileDetails.collectAsStateWithLifecycle().value,
        onOpenChat = onOpenChat,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun UserProfileScreen(
    modifier: Modifier = Modifier,
    uiState: UserProfileUiState,
    onOpenChat: () -> Unit,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            UserProfileTopAppBar(onNavigateBack = onNavigateBack)
        },
        containerColor = TalkieTheme.colors.surface,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Avatar(
                name = uiState.displayName,
                imageUrl = uiState.avatarUrl,
                size = AvatarSize.ExtraLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            UserDisplayName(name = uiState.displayName)

            Spacer(modifier = Modifier.height(4.dp))

            Spacer(modifier = Modifier.height(32.dp))

            UserContactSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                phone = uiState.phone,
            )

            Spacer(modifier = Modifier.height(32.dp))

            UserProfileActions(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onOpenChat = onOpenChat,
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserProfileTopAppBar(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
) {
    TalkieTopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    painter = painterResource(com.dfcruz.talkie.R.drawable.arrow_back),
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
private fun UserDisplayName(
    modifier: Modifier = Modifier,
    name: String,
) {
    Text(
        modifier = modifier,
        text = name,
        style = TalkieTheme.typography.headlineSmall,
        color = TalkieTheme.colors.onSurface,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun UserContactSection(
    modifier: Modifier = Modifier,
    phone: String?,
) {
    if (phone == null) return

    Surface(
        modifier = modifier,
        shape = TalkieTheme.shapes.medium,
        color = TalkieTheme.colors.surfaceContainer,
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            ContactRow(
                label = "Phone",
                value = phone,
            )
        }
    }
}

@Composable
private fun ContactRow(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column {
            Text(
                text = label,
                style = TalkieTheme.typography.labelSmall,
                color = TalkieTheme.colors.onSurfaceVariant,
            )
            Text(
                text = value,
                style = TalkieTheme.typography.bodyMedium,
                color = TalkieTheme.colors.onSurface,
            )
        }
    }
}


@Composable
private fun UserProfileActions(
    modifier: Modifier = Modifier,
    onOpenChat: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onOpenChat,
            shape = TalkieTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = TalkieTheme.colors.primary,
                contentColor = TalkieTheme.colors.onPrimary,
            ),
            contentPadding = PaddingValues(vertical = 14.dp),
        ) {
            Text(
                text = "Chat",
                style = TalkieTheme.typography.labelLarge,
            )
        }
    }
}


@Composable
@ThemePreview
fun UserProfileScreenFullPreview() {
    TalkieTheme {
        UserProfileScreen(
            uiState = UserProfileUiState(
                displayName = "Sarah Johnson",
                avatarUrl = null,
                phone = "+1 (555) 012 3456",
            ),
            onOpenChat = {},
            onNavigateBack = {},
        )
    }
}

@Composable
@ThemePreview
fun UserProfileScreenNoContactPreview() {
    TalkieTheme {
        UserProfileScreen(
            uiState = UserProfileUiState(
                displayName = "Alex",
                avatarUrl = null,
                phone = null,
            ),
            onOpenChat = {},
            onNavigateBack = {},
        )
    }
}