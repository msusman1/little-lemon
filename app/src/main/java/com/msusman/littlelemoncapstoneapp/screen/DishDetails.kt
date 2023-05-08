package com.msusman.littlelemoncapstoneapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.msusman.littlelemoncapstoneapp.MainViewModel
import com.msusman.littlelemoncapstoneapp.R
import com.msusman.littlelemoncapstoneapp.components.TopAppBarWIthBack

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DishDetails(mainViewModel: MainViewModel, id: Int,navController : NavController) {
    val dish = mainViewModel.getDisDetail(id).observeAsState().value
    if (dish != null) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            TopAppBarWIthBack(navController::popBackStack)

            GlideImage(
                contentScale = ContentScale.FillWidth,
                model = dish.image, contentDescription = "Dish Image",
                modifier = Modifier.fillMaxWidth(),
            )
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                Text(text = dish.title ?: "", style = MaterialTheme.typography.h6)
                Text(text = dish.description ?: "", style = MaterialTheme.typography.body1)
                Counter()
                Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.add_for) + " $${dish.price}",
                        textAlign = TextAlign.Center
                    )
                }
            }


        }
    }
}

@Composable
fun Counter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        var counter by remember {
            mutableStateOf(1)
        }
        TextButton(onClick = {
            counter--
        }) {
            Text(
                text = "-", style = MaterialTheme.typography.h6
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(onClick = {
            counter++
        }) {
            Text(
                text = "+", style = MaterialTheme.typography.h6
            )
        }
    }
}


