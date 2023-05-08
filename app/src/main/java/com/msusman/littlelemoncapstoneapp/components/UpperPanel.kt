package com.msusman.littlelemoncapstoneapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msusman.littlelemoncapstoneapp.R
import com.msusman.littlelemoncapstoneapp.ui.theme.LittleLemonColors

@Composable
fun UpperPanel(onCategorySelect:(String)->Unit) {
    Column(
        modifier = Modifier
            .background(color = LittleLemonColors.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 40.sp,
            color = LittleLemonColors.yellow,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(id = R.string.location),
            color = LittleLemonColors.cloud,
            fontSize = 24.sp,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.body1,
                color = LittleLemonColors.cloud,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.upperpanelimage),
                contentDescription = "Upper Panel Image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )
        }
        Button(
            onClick = { }
        ) {
            Text(
                text = stringResource(id = R.string.order_button_text)
            )
        }
        var searchPhrase by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(value = searchPhrase,
            modifier = Modifier
                .fillMaxWidth().background(color= Color.White, shape = RoundedCornerShape(12.dp)) ,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search"
                )
            },
            onValueChange = {
                searchPhrase = it
                onCategorySelect.invoke(it)
            },

            placeholder = { Text(text = "Enter Search Phrase") })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpperPanelPreview() {
    UpperPanel({})
}
