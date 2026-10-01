package com.kicklight.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kicklight.ui.screen.HomeScreen
import com.kicklight.ui.screen.SearchScreen
import com.kicklight.ui.screen.ChannelScreen
import com.kicklight.ui.screen.WebScreen
import com.kicklight.ui.screen.SettingsScreen
import com.kicklight.ui.viewmodel.HomeViewModel
import com.kicklight.ui.viewmodel.SearchViewModel
import com.kicklight.ui.viewmodel.ChannelViewModel
import com.kicklight.ui.viewmodel.WebViewModel
import com.kicklight.ui.viewmodel.SettingsViewModel

/**
 * Navigation route definitions
 */
sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
    object Following : NavigationRoute("following")
    object Search : NavigationRoute("search")
    object Web : NavigationRoute("web")
    object Settings : NavigationRoute("settings")
    object Channel : NavigationRoute("channel/{channelSlug}") {
        fun createRoute(slug: String) = "channel/$slug"
    }
    object Player : NavigationRoute("player/{streamId}") {
        fun createRoute(streamId: String) = "player/$streamId"
    }
}

/**
 * Main navigation host for KickLight
 */
@Composable
fun KickLightNavHost(
    navController: NavHostController = rememberNavController(),
    onNavigateToChannel: (String) -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.route
    ) {
        // Home Screen
        composable(NavigationRoute.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onChannelClick = { slug ->
                    navController.navigate(NavigationRoute.Channel.createRoute(slug))
                },
                onStreamClick = { streamId ->
                    navController.navigate(NavigationRoute.Player.createRoute(streamId))
                }
            )
        }

        // Search Screen
        composable(NavigationRoute.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                viewModel = viewModel,
                onChannelClick = { slug ->
                    navController.navigate(NavigationRoute.Channel.createRoute(slug))
                },
                onStreamClick = { streamId ->
                    navController.navigate(NavigationRoute.Player.createRoute(streamId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Channel Screen
        composable(
            route = NavigationRoute.Channel.route,
            arguments = listOf(
                androidx.navigation.navArgument("channelSlug") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->
            val channelSlug = backStackEntry.arguments?.getString("channelSlug") ?: return@composable
            val viewModel: ChannelViewModel = hiltViewModel()
            ChannelScreen(
                viewModel = viewModel,
                channelSlug = channelSlug,
                onStreamClick = { streamId ->
                    navController.navigate(NavigationRoute.Player.createRoute(streamId))
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Web Screen
        composable(NavigationRoute.Web.route) {
            val viewModel: WebViewModel = hiltViewModel()
            WebScreen(
                viewModel = viewModel
            )
        }

        // Settings Screen
        composable(NavigationRoute.Settings.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

fun NavController.navigateToChannel(slug: String) {
    navigate(NavigationRoute.Channel.createRoute(slug))
}

fun NavController.navigateToPlayer(streamId: String) {
    navigate(NavigationRoute.Player.createRoute(streamId))
}

fun NavController.navigateToHome() {
    navigate(NavigationRoute.Home.route) {
        popUpTo(NavigationRoute.Home.route) { inclusive = true }
    }
}

fun NavController.navigateToSearch() {
    navigate(NavigationRoute.Search.route)
}

fun NavController.navigateToWeb() {
    navigate(NavigationRoute.Web.route)
}

fun NavController.navigateToSettings() {
    navigate(NavigationRoute.Settings.route)
}
