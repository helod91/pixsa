package com.roche.android.bpi.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun HomeTabsScreen() {
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationItem(
            title = "First",
            icon = Icons.Filled.AccountBox,
            hasBadge = true
        ),

        NavigationItem(
            title = "Second",
            icon = Icons.Filled.Info
        ),

        NavigationItem(
            title = "Third",
            icon = Icons.Filled.Settings,
            hasBadgeNumbers = true,
            badgeNumber = 3
        ),

        NavigationItem(
            icon = Icons.Filled.Info
        ),

        NavigationItem(
            title = "Fifth",
            icon = Icons.Filled.Info
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            TabRow(
                selectedTabIndex = selectedItemIndex,
                containerColor = MaterialTheme.colorScheme.onPrimary, // Background color of the bar
                contentColor = MaterialTheme.colorScheme.primary // Color of the selected item and the underline
            ) {
                items.forEachIndexed { index, navigationItem ->
                    Tab(
                        text = {
                            Text(
                                text = navigationItem.title ?: "",
                                fontSize = 12.sp
                            )
                        },
                        selected = selectedItemIndex == index,
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (navigationItem.hasBadge) {
                                        Badge() // Adds just a dot, without number text
                                    }

                                    if (navigationItem.hasBadgeNumbers) {
                                        Badge {
                                            Text(navigationItem.badgeNumber.toString())
                                        } // Adds a dot with a number text
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = navigationItem.title
                                )
                            }
                        },
                        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant, // Color of the unselected items
                        onClick = { selectedItemIndex = index }
                    )
                }
            }
        }
    ) {
        Box(Modifier.padding(it)) {
            when (selectedItemIndex) {
                0 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Text("First tab")
                }

                1 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Text("Second tab")
                }

                2 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Text("Third tab")
                }

                3 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Text("Fourth tab")
                }

                4 -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Text("Fifth tab")
                }
            }
        }
    }
}