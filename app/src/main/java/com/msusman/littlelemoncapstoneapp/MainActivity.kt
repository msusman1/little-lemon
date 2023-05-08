package com.msusman.littlelemoncapstoneapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.msusman.littlelemoncapstoneapp.screen.DishDetails
import com.msusman.littlelemoncapstoneapp.screen.HomeScreen
import com.msusman.littlelemoncapstoneapp.screen.OnboardingScreen
import com.msusman.littlelemoncapstoneapp.screen.ProfileScreen
import com.msusman.littlelemoncapstoneapp.ui.theme.LittleLemonCapstoneAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()
        setContent {
            LittleLemonCapstoneAppTheme {
                val navController = rememberNavController()
                val startDestination =
                    if (App.appPrefs.userSession!=null) Home.route else OnBoarding.route
                val dishes=mainViewModel.dishes.observeAsState().value?: emptyList()
                NavHost(navController = navController, startDestination = startDestination) {
                    composable(Home.route) {
                        HomeScreen(navController,dishes)
                    }
                    composable(
                        DishDetails.route + "/{${DishDetails.argDishId}}",
                        arguments = listOf(navArgument(DishDetails.argDishId) {
                            type = NavType.IntType
                        })
                    ) {
                        val id =
                            requireNotNull(it.arguments?.getInt(DishDetails.argDishId)) { "Dish id is null" }
                        DishDetails(mainViewModel,id,navController = navController)
                    }
                    composable(OnBoarding.route) {
                        OnboardingScreen(navController = navController)
                    }
                    composable(Profile.route) {
                        ProfileScreen(navController = navController)
                    }
                }
            }
        }
    }


}
