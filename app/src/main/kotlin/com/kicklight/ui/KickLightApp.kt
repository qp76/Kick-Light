package com.kicklight.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.kicklight.ui.navigation.KickLightNavHost
import com.kicklight.ui.navigation.NavigationRoute
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Public

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int? = null
)

/**
 * Main KickLight application composable
 * Contains bottom navigation and navigation host
 */
@Composable
fun KickLightApp() {
    val navController = rememberNavController()
    var selectedNavItem by remember { mutableIntStateOf(0) }

    val navItems = listOf(
        BottomNavItem("Home", Icons.Filled.Home, NavigationRoute.Home.route),
        BottomNavItem("Following", Icons.Filled.Favorite, NavigationRoute.Following.route),
        BottomNavItem("Search", Icons.Filled.Search, NavigationRoute.Search.route),
        BottomNavItem("Web", Icons.Filled.Public, NavigationRoute.Web.route),
        BottomNavItem("Settings", Icons.Filled.Settings, NavigationRoute.Settings.route)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavItem == index,
                        onClick = {
                            selectedNavItem = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(item.title) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    item.badgeCount?.let {
                                        Badge { Text(it.toString()) }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                KickLightNavHost(
                    navController = navController,
                    onNavigateToChannel = { slug ->
                        navController.navigate(NavigationRoute.Channel.createRoute(slug))
                    }
                )
            }
        }
    )
}
