package com.retheviper.composetestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.retheviper.composetestapplication.ui.theme.ComposeTestApplicationTheme
import com.retheviper.composetestapplication.view.UserListScreen
import com.retheviper.composetestapplication.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestApplicationTheme {
                val userListViewModel = ViewModelProvider(this)[UserListViewModel::class.java]
                UserListScreen(userListViewModel, this)
            }
        }
    }
}