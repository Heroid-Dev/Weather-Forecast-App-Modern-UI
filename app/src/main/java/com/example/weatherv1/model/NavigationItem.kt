package com.example.weatherv1.model

import androidx.annotation.DrawableRes
import com.example.weatherv1.R

enum class NavigationItem(
    val title: String,
    @DrawableRes val icon: Int
) {
    Home(
        title = "Home",
        icon = R.drawable.home_nav_icon
    ),
    Favorites(
        title = "Favorites",
        icon = R.drawable.star_nav_icon
    ),
    Setting(
        title = "Setting",
        icon = R.drawable.setting_nav_icon
    ),
    About(
        title = "About",
        icon = R.drawable.about_nav_icon
    ),
}