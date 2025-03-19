package com.android.androidtestassignment.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.androidtestassignment.R
import com.android.androidtestassignment.ui.theme.AndroidTestAssignmentTheme
import com.android.androidtestassignment.ui.viewmodels.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen() {
    val viewModel: UsersViewModel = hiltViewModel()
    val users = viewModel.usersStateFlow.collectAsLazyPagingItems()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.title_users))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(users.itemCount) { index ->
                    users[index]?.let {
                        UserListItem(
                            user = it,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {}
                        )
                    }
                }
                when {
                    users.loadState.refresh is LoadState.Loading -> {
                        item { LoadingDialog() }
                    }

                    users.loadState.refresh is LoadState.Error -> {
                        val error = users.loadState.refresh as LoadState.Error
                        //show some error message
                        item {
                            ErrorLayout(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage?:stringResource(R.string.message_generic_error),
                                onRetry = { users.retry() })
                        }
                    }

                    users.loadState.append is LoadState.Loading -> {
                        item { Loading(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) }
                    }

                    users.loadState.append is LoadState.Error -> {
                        val error = users.loadState.append as LoadState.Error
                        //show some error message
                        item {
                            ErrorLayout(
                                message = error.error.localizedMessage
                                    ?: stringResource(R.string.message_generic_error),
                                onRetry = { users.retry() })
                        }
                    }
                }
                //add some blank space at the bottom so the last list item will be completely visible and not overlapped by the fab
                item {
                    Spacer(modifier = Modifier.height(120.dp))
                }
            }
            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .imePadding()
                    .padding(end = 16.dp, bottom = 16.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun UsersPreview() {
    AndroidTestAssignmentTheme {
        UsersScreen()
    }
}