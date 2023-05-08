package com.msusman.littlelemoncapstoneapp.components

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.msusman.littlelemoncapstoneapp.R
import com.msusman.littlelemoncapstoneapp.DishDetails
import com.msusman.littlelemoncapstoneapp.data.DishEntity
import com.msusman.littlelemoncapstoneapp.ui.theme.LittleLemonColors

@Composable
fun LowerPanel(
    navController: NavHostController,
    dishes: List<DishEntity> = listOf(),
    categories: List<String> = listOf(),
    onCategorySelected: (String?) -> Unit
) {

    Column {
        CategoryLayout(categories, onCategorySelected = onCategorySelected)
        LazyColumn {
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryLayout(categories: List<String>, onCategorySelected: (String?) -> Unit) {

    var selectedIndex: Int? by remember {
        mutableStateOf(null)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "ORDER FOR DELIVERY",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(8.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(state = rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                categories.forEachIndexed { index, cat ->
                    FilterChip(selectedIndex == index,
                        colors = ChipDefaults.filterChipColors(selectedBackgroundColor = LittleLemonColors.yellow),
                        onClick = {
                            selectedIndex = if (selectedIndex == index) null else index
                            onCategorySelected(if (selectedIndex == null) null else cat)
                        }) {
                        Text(text = cat)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: DishEntity) {
    Card(onClick = {
        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(DishDetails.route + "/${dish.id}")
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(text = dish.title, style = MaterialTheme.typography.h6)

                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp, end = 5.dp)
                )
                Text(text = "${dish.price}", style = MaterialTheme.typography.body2)
            }
            GlideImage(
                model = dish.image, contentDescription = "",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )

        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = LittleLemonColors.yellow,
        thickness = 1.dp,
    )
}
