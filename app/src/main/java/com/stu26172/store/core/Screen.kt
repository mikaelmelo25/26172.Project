package com.stu26172.store.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String?,
    val icon: ImageVector?
) {
    data object Profile : Screen("profile", "Profile", Icons.Outlined.Person)
    data object Auth : Screen("auth", "Auth", null)
    data object Home : Screen("home",  "Home", null)
    data object ProductDetails : Screen("product_details", "", null)
    data object Cart : Screen("cart", "My Cart", Icons.Outlined.ShoppingCart)
}