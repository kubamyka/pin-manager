package com.kmcoding.pinmanager.ui.nav

import androidx.annotation.StringRes
import com.kmcoding.pinmanager.R

enum class NavScreen(
    @StringRes
    val title: Int,
) {
    Home(title = R.string.pin_manager),
    AddNewPin(title = R.string.add_new_pin),
}
