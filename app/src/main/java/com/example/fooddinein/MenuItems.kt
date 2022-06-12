package com.example.fooddinein

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

class MenuItems : AppCompatActivity() {

    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        id = intent.getStringExtra("id").toString()

        setContent {
            MaterialTheme {
                MainContent()
            }
        }
    }

    @Composable
    fun MainContent() {
        val scrollableState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollableState)
        ) {
            TopDecor()
            Veg()
            NonVeg()
            Desserts()
        }
    }

    @Composable
    fun Desserts() {
        val url = remember{
            "https://static.toiimg.com/photo/msid-87930581/87930581.jpg"
        }
        TitleText("Desserts & Cool Drinks")
        LazyRow {
            items(50){
                FoodCard(color = colorResource(id = R.color.theme), url = url, title = "Chicken Pizza", price = "$4.55")
            }
        }
    }

    @Composable
    fun NonVeg() {
        val url = remember{
            "https://static.toiimg.com/photo/msid-87930581/87930581.jpg"
        }
        TitleText("Non-Veg Varieties")
        LazyRow {
            items(50){
                FoodCard(color = colorResource(id = R.color.theme), url = url, title = "Chicken Pizza", price = "$4.55")
            }
        }
    }

    @Composable
    fun Veg() {
        val url = remember{
            "https://static.toiimg.com/photo/msid-87930581/87930581.jpg"
        }
        TitleText("Veg Varieties")
        LazyRow {
            items(50){
                FoodCard(color = colorResource(id = R.color.theme), url = url, title = "Chicken Pizza", price = "$4.55")
            }
        }
    }

    @Composable
    fun FoodCard(color: Color, url: String, title: String, price: String) {
        val orderCount = remember{
            mutableStateOf(0)
        }
        Surface {
            Column(Modifier.width(260.dp)) {
                Surface(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    {
                        Card(
                            modifier = Modifier
                                .height(190.dp)
                                .width(220.dp)
                                .alpha(.7f),
                            backgroundColor = color,
                            contentColor = color,
                            shape = RoundedCornerShape(15.dp)
                        ) {

                        }
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.BottomCenter
                        )
                        {
                            Card(
                                modifier = Modifier
                                    .height(235.dp)
                                    .width(165.dp),
                                elevation = 15.dp,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        model = url,
                                        placeholder = painterResource(id = R.mipmap.placeholder)
                                    ),
                                    contentDescription = "image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 25.dp)
                        .fillMaxWidth()
                )
                {
                    Column() {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                text = price,
                                style = TextStyle(
                                    Color.Gray,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Card(shape = CircleShape, backgroundColor = Color(255,100,0), modifier = Modifier.padding(5.dp).clickable{
                                    if(orderCount.value>0){
                                        orderCount.value--
                                    }
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.ic_down), contentDescription = "decrement", modifier = Modifier.size(25.dp), tint = MaterialTheme.colors.background )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "${orderCount.value}", color = MaterialTheme.colors.onBackground, fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(5.dp))
                                Card(shape = CircleShape, backgroundColor = Color(10,185,10), modifier = Modifier.padding(5.dp).clickable{
                                    orderCount.value++
                                }) {
                                    Icon(painter = painterResource(id = R.drawable.ic_up), contentDescription = "decrement", modifier = Modifier.size(25.dp), tint = MaterialTheme.colors.background )
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @Composable
    fun TitleText(s: String) {
        Text(
            text = s,
            fontSize = 28.sp,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp, start = 30.dp),
            fontWeight = FontWeight(300)
        )
    }

    @Composable
    fun TopDecor(context: Context = LocalContext.current) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            shape = RoundedCornerShape(bottomEnd = 350.dp, bottomStart = 35.dp)
        ) {
            Box()
            {

                Image(
                    painter = painterResource(id = R.mipmap.foodbg),
                    contentDescription = "Food",
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Top
                )
                {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                        Icon(painter = painterResource(id = R.drawable.ic_person), contentDescription = "account", tint = Color.White, modifier = Modifier.padding(10.dp).size(40.dp).clickable {
                            val intent = Intent(context,AccountActivity::class.java)
                            startActivity(intent)
                        })
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(start = 10.dp, end = 20.dp)
                                .clickable {
                                    val intent = Intent(context,SearchActivity::class.java)
                                    startActivity(intent)
                                },
                            shape = RoundedCornerShape(25.dp),
                            backgroundColor = Color.White
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(15.dp))
                                Image(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "search",
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Search...",
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(color = Color.Gray, fontSize = 16.sp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = buildAnnotatedString {
                            append("Order\n")
                            withStyle(
                                style = SpanStyle(
                                    Color(254, 255, 90),
                                    fontSize = 40.sp,
                                    shadow = Shadow(color = Color(0xFF000000))
                                )
                            )
                            {
                                append("Your\n")
                            }
                            append("Food now.")
                        },
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.offset(35.dp)
                    )
                }
            }
        }

    }
}