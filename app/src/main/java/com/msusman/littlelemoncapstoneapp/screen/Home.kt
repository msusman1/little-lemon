package com.msusman.littlelemoncapstoneapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.msusman.littlelemoncapstoneapp.Profile
import com.msusman.littlelemoncapstoneapp.components.LowerPanel
import com.msusman.littlelemoncapstoneapp.components.TopAppBar
import com.msusman.littlelemoncapstoneapp.components.UpperPanel
import com.msusman.littlelemoncapstoneapp.data.DishEntity

@Composable
fun HomeScreen(navController: NavHostController, dishes: List<DishEntity>) {
    var category: String? by remember { mutableStateOf(null) }
    var searchPhrase: String by remember { mutableStateOf("") }

    val filtered =
        (if (category != null) dishes.filter { it.category == category } else dishes).filter {
            it.title.contains(searchPhrase, true)
        }
    val categories = dishes.map { it.category }.distinct()
    Column {
        TopAppBar {
            navController.navigate(Profile.route)
        }
        UpperPanel { searchPhrases ->
            searchPhrase = searchPhrases
        }
        LowerPanel(navController, filtered,categories) { categorys ->
            category = categorys
        }
    }
}
