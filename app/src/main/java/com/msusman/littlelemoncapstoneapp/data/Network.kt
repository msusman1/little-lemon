package com.msusman.littlelemoncapstoneapp.data

import com.msusman.littlelemoncapstoneapp.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DishesResponse(

    @SerialName("menu")
    val menu: List<DishApi> = listOf()
)

@Serializable
data class DishApi(


    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("price")
    val price: Double,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
) {
    fun toMenuItemRoom() = DishEntity(

        id = this.id,
        title = this.title,
        price = this.price,
        description = this.description,
        image = this.image,
        category = this.category
    )
}
