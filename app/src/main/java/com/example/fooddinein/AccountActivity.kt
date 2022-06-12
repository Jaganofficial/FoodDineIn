package com.example.fooddinein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContent {
            Column(modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
                    Text(
                        text = "My Cart.",
                        color = Color.DarkGray,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CartItems()
                    Text(
                        text = "My Orders.",
                        color = Color.DarkGray,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OrderAttributes()
                    LazyColumn{
                        items(10){
                            OrderRow("Chicken Pizza","$5.68", 3, "Pending")
                        }
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background)
                            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total Bill: ", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                        Text(text = "$214.69", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Button(onClick = {

                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.theme)
                    ), modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Logout", color = Color.White, fontSize = 24.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }

    @Composable
    fun CartItems(){
        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
            items(10){
                CartItemOrder()
            }
        }
    }

    @Composable
    fun CartItemOrder() {
        Box(modifier = Modifier.padding(10.dp)){
            Card(shape = CircleShape, elevation = 2.dp) {
                Box(modifier = Modifier.size(70.dp)){
                    Image(painter = painterResource(id = R.mipmap.food), contentDescription = "food", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                listOf(Color.White, Color.White)
                            ), alpha = 0.6f
                        ), contentAlignment = Alignment.Center){
                        Text(text = "x3", color = Color.DarkGray, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }

    @Composable
    fun OrderAttributes(){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Ordered Item", fontSize = 16.sp, color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.ExtraBold, modifier = Modifier.weight(2f))
            Text(text = "Price", fontSize = 16.sp, color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.ExtraBold, modifier = Modifier.weight(1f))
            Text(text = "Quantity", fontSize = 16.sp, color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.ExtraBold, modifier = Modifier.weight(1f))
            Text(text = "Status", fontSize = 16.sp, color = MaterialTheme.colors.onBackground, fontWeight = FontWeight.ExtraBold, modifier = Modifier.weight(1f))
        }
    }

    @Composable
    fun OrderRow(title: String, price: String, quantity: Int, status: String){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, color = MaterialTheme.colors.onBackground, fontSize = 16.sp, modifier = Modifier.weight(2f))
            Text(text = price, color = Color.LightGray, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = "x$quantity", color = Color.LightGray, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = status, color = colorResource(id = R.color.theme), fontSize = 14.sp, modifier = Modifier.weight(1f))
        }
    }
}