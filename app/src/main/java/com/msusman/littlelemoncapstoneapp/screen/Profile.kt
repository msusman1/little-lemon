package com.msusman.littlelemoncapstoneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msusman.littlelemoncapstoneapp.App
import com.msusman.littlelemoncapstoneapp.Home
import com.msusman.littlelemoncapstoneapp.OnBoarding
import com.msusman.littlelemoncapstoneapp.R
import com.msusman.littlelemoncapstoneapp.UserSession
import com.msusman.littlelemoncapstoneapp.components.TextFieldWithLabel
import com.msusman.littlelemoncapstoneapp.components.TopAppBarWIthBack
import com.msusman.littlelemoncapstoneapp.ui.theme.LittleLemonColors

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/6/2023.
 */
@Composable
fun ProfileScreen(navController: NavController) {
    val userSession = App.appPrefs.userSession ?: UserSession()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        TopAppBarWIthBack(navController::popBackStack)
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(16.dp),
            contentDescription = "Logo"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Personal Information", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(36.dp))
            TextFieldWithLabel(
                initial = userSession.firstName ?: "",
                label = "First name",
                onChange = userSession::firstName::set
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldWithLabel(
                initial = userSession.lastName ?: "",
                label = "Last name",
                onChange = userSession::lastName::set
            )
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldWithLabel(
                initial = userSession.email ?: "",
                label = "Email",
                onChange = userSession::email::set
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    App.appPrefs.logout()
                    navController.navigate(OnBoarding.route) {
                        this.popUpTo(OnBoarding.route) {
                            inclusive = true
                        }
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Logout", color = Color.Black)
            }
        }

    }
}