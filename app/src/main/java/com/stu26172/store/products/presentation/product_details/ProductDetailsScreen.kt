package com.stu26172.store.products.presentation.product_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    uiState: ProductDetailsUiState, onClickAddToCart: (Int) -> Unit, onNavigateToCart: () -> Unit
) {
    val product = uiState.product
    var showFullDescription by remember {
        mutableStateOf(false)
    }
    val isDescriptionLarge = product.description.length >= 121

    val description = when {
        isDescriptionLarge && !showFullDescription -> {
            product.description.substring(0, 121) + "...."
        }

        else -> {
            product.description
        }
    }
    var isFavorite by remember {
        mutableStateOf(false)
    }
    val iconFavorite = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    var productQuantity by remember {
        mutableIntStateOf(1)
    }

    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .size(44.dp),
                onClick = onNavigateToCart,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ) {

                Icon(
                    imageVector = Icons.Outlined.ShoppingCart, contentDescription = null
                )
            }
        }
        AnimatedContent(targetState = uiState.isLoading, label = "") { it ->
            if (it) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = product.title, fontSize = 22.sp, fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = product.category, fontSize = 16.sp, fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "$" + product.price,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    AsyncImage(
                        modifier = Modifier
                            .clip(Shapes().large)
                            .fillMaxWidth()
                            .height(300.dp),
                        model = product.image,
                        contentDescription = null
                    )

                    Column(
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = description,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light
                        )
                        if (isDescriptionLarge) {
                            Text(
                                modifier = Modifier.clickable {
                                    showFullDescription = !showFullDescription
                                },
                                text = if (showFullDescription) "Show less" else "Show more",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ) {

                        IconButton(
                            onClick = { isFavorite = !isFavorite },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color(0xFFD9D9D9).copy(alpha = 0.4f)
                            )
                        ) {
                            Icon(
                                imageVector = iconFavorite,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))


                        ProductQuantityCount(
                            quantity = productQuantity,
                            onQuantityChange = { quantity ->
                                productQuantity = quantity
                            })

                        Button(
                            modifier = Modifier.width(200.dp), onClick = {
                                onClickAddToCart(productQuantity)
                            }, shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AddShoppingCart,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(text = "Add to Cart")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ProductQuantityCount(
    quantity: Int, onQuantityChange: (Int) -> Unit
) {


    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Quantity", fontSize = 14.sp
        )
        IconButton(enabled = quantity > 1, onClick = {
            onQuantityChange(quantity - 1)
        }) {
            Text(text = "-")
        }

        Text(text = quantity.toString(), color = MaterialTheme.colorScheme.primary)

        IconButton(onClick = {
            onQuantityChange(quantity + 1)
        }) {
            Text(text = "+")
        }
    }
}