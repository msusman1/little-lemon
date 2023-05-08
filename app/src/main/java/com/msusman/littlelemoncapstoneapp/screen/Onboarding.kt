package com.msusman.littlelemoncapstoneapp.screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.msusman.littlelemoncapstoneapp.App
import com.msusman.littlelemoncapstoneapp.Home
import com.msusman.littlelemoncapstoneapp.R
import com.msusman.littlelemoncapstoneapp.UserSession
import com.msusman.littlelemoncapstoneapp.components.TextFieldWithLabel
import com.msusman.littlelemoncapstoneapp.ui.theme.LittleLemonColors

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/6/2023.
 */
@Composable
fun OnboardingScreen(navController: NavController) {
    val context = LocalContext.current
    val userSession = UserSession()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(16.dp),
            contentDescription = "Logo"
        )
        Text(
            text = stringResource(id = R.string.lets_get_to_know_you),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = LittleLemonColors.Secondary)
                .padding(32.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Personal Information", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(36.dp))
            TextFieldWithLabel(label = "First name", onChange = userSession::firstName::set)
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldWithLabel(label = "Last name", onChange = userSession::lastName::set)
            Spacer(modifier = Modifier.height(24.dp))
            TextFieldWithLabel(label = "Email", onChange = userSession::email::set)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    if (userSession.isNotInitialized()) {
                        Toast.makeText(context, "Please enter all info", Toast.LENGTH_SHORT).show()
                    } else {
                        App.appPrefs.userSession = userSession
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Home.route) {
                            this.popUpTo(Home.route) {
                                this.inclusive = false
                            }
                        }
                    }

                }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Register", color = Color.Black)
            }
        }

    }
}