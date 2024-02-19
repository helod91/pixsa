package com.roche.android.bpi.presentation.features.router

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.roche.android.bpi.presentation.navigation.Navigation

@Composable
fun RouterScreen(navController: NavController) {

    Column(Modifier.padding(24.dp)) {
        Button(
            onClick = {
                navController.navigate(Navigation.Routes.HOME_TABS)
            }
        ) {
            Text("Bottom navigation with tabs")
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(Navigation.Routes.HOME_NAV)
            }
        ) {
            Text("Bottom navigation with nav bar")
        }
    }
}