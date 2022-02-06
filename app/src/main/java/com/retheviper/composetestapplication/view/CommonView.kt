package com.retheviper.composetestapplication.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.retheviper.composetestapplication.ui.theme.Purple700

enum class BottomMenu {
    PICTURES,
    USERS,
    SETTINGS
}

@Composable
fun TopAppBarView() {
    TopAppBar(
        title = { Text(text = "Members List", color = Color.White) },
        backgroundColor = Purple700
    )
}

/**
 * TODO Navigation
 */
@Composable
fun BottomAppBarView(onClickEvery: (() -> Unit)?) {
    val selectedItem = remember { mutableStateOf(BottomMenu.USERS) }

    fun onClickAction(selectedItemValue: BottomMenu) {
        if (onClickEvery != null) {
            onClickEvery()
        }
        selectedItem.value = selectedItemValue
    }

    BottomAppBar(
        content = {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = BottomMenu.PICTURES.name
                    )
                },
                selected = selectedItem.value == BottomMenu.PICTURES,
                onClick = { onClickAction(BottomMenu.USERS) }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = BottomMenu.USERS.name
                    )
                },
                selected = selectedItem.value == BottomMenu.USERS,
                onClick = { onClickAction(BottomMenu.USERS) }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = BottomMenu.SETTINGS.name
                    )
                },
                selected = selectedItem.value == BottomMenu.SETTINGS,
                onClick = { onClickAction(BottomMenu.SETTINGS) }
            )
        }
    )
}
