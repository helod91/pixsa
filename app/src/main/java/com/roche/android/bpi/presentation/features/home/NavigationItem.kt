package com.roche.android.bpi.presentation.features.home

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val icon: ImageVector,
    val title: String? = null,
    val hasBadge: Boolean = false,
    val hasBadgeNumbers: Boolean = false,
    val badgeNumber: Int = 0
)