package com.kmcoding.pinmanager.ui.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmcoding.pinmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinAppBar(
    currentScreen: NavScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    toggleIsSearchActive: () -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    isSearchActive: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        TopAppBar(
            title = { Text(stringResource(currentScreen.title)) },
            colors =
                TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            modifier = Modifier.weight(1f),
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                }
            },
        )

        if (!canNavigateBack) {
            Row(modifier = Modifier.padding(top = 32.dp), verticalAlignment = Alignment.CenterVertically) {
                PinAppBarAction(
                    toggleIsSearchActive = toggleIsSearchActive,
                    isSearchActive = false,
                )

                if (isSearchActive) {
                    TextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.enter_query_here),
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSecondary,
                            )
                        },
                        colors =
                            TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                            ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    PinAppBarAction(
                        toggleIsSearchActive = toggleIsSearchActive,
                        isSearchActive = true,
                    )
                }
            }
        }
    }
}

@Composable
fun PinAppBarAction(
    toggleIsSearchActive: () -> Unit,
    isSearchActive: Boolean = false,
) {
    IconButton(onClick = toggleIsSearchActive) {
        Icon(
            imageVector = if (isSearchActive) Icons.Default.Close else Icons.Default.Search,
            contentDescription =
                stringResource(
                    id = if (isSearchActive) R.string.close else R.string.search,
                ),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PinAppBarPreviewSearchInactive() {
    PinAppBar(
        currentScreen = NavScreen.Home,
        canNavigateBack = false,
        navigateUp = {},
        onSearchQueryChange = {},
        toggleIsSearchActive = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PinAppBarPreviewSearchActive() {
    PinAppBar(
        currentScreen = NavScreen.Home,
        canNavigateBack = false,
        navigateUp = {},
        onSearchQueryChange = {},
        toggleIsSearchActive = {},
        isSearchActive = true,
        searchQuery = "Loc",
    )
}

@Preview(showBackground = true)
@Composable
fun PinAppBarPreviewSearchActiveWithPlaceholder() {
    PinAppBar(
        currentScreen = NavScreen.Home,
        canNavigateBack = false,
        navigateUp = {},
        onSearchQueryChange = {},
        toggleIsSearchActive = {},
        isSearchActive = true,
    )
}
