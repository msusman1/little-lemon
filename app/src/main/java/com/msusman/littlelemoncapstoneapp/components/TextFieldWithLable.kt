package com.msusman.littlelemoncapstoneapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/8/2023.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithLabel(initial: String="", label: String, onChange: (String) -> Unit) {
    var fieldValue: String by remember { mutableStateOf(initial) }

    Text(text = label, modifier = Modifier.padding(bottom = 8.dp))
    OutlinedTextField(
        value = fieldValue, onValueChange = {
            fieldValue = it
            onChange(it)
        }, modifier = Modifier
            .fillMaxWidth()
    )
}