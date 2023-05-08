package com.msusman.littlelemoncapstoneapp

interface Destinations {
    val route: String
}

object OnBoarding : Destinations {
    override val route = "onboarding"
}

object Profile : Destinations {
    override val route = "profile"
}

object Home : Destinations {
    override val route = "Home"
}

object DishDetails : Destinations {
    override val route = "Menu"
    const val argDishId = "dishId"
}
