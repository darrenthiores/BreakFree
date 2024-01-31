package com.binus.core_ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    Home(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    Profile(
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Profile.name -> Profile
                else -> null
            }
    }
}