package com.example.fooddinein

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class SearchActivity : AppCompatActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent{
            Column(modifier = Modifier.fillMaxSize()) {
                SearchField()
                LazyVerticalGrid(cells = GridCells.Fixed(2)){
                    items(50){
                        FoodBox("Chicken Pizza", "$4.5")
                    }
                }
            }
        }
    }

    @Composable
    fun FoodBox(title: String, price: String){
        Box(
            Modifier
                .fillMaxWidth(.5f)
                .padding(5.dp), contentAlignment = Alignment.Center){
            Card(shape = RoundedCornerShape(10.dp), elevation = 5.dp, modifier = Modifier.padding(10.dp)) {
                Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(shape = CircleShape, elevation = 2.dp) {
                        Image(painter = painterResource(id = R.mipmap.food), contentDescription = "food", modifier = Modifier
                            .size(150.dp), contentScale = ContentScale.Crop)
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(text = title, color = MaterialTheme.colors.onBackground, fontSize = 22.sp)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(text = price, color = Color.DarkGray, fontSize = 20.sp)

                        CountButton()
                    }
                }
            }
        }
    }

    @Composable
    fun CountButton() {
        Card(shape = RoundedCornerShape(5.dp), backgroundColor = colorResource(id = R.color.theme)) {
            Row(modifier = Modifier.padding(vertical = 0.dp, horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "-", color = Color.White, fontSize = 22.sp, modifier = Modifier.clickable {

                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "0", color = Color.White, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "+", color = Color.White, fontSize = 22.sp, modifier = Modifier.clickable{

                })
            }
        }
    }

    @Composable
    fun SearchField(){
        val textfieldValue = remember{
            mutableStateOf(
                ""
            )
        }
        TextField(value = textfieldValue.value, onValueChange ={
            textfieldValue.value = it
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search", tint = Color.DarkGray)
        }, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ), placeholder = {
            Text(text = "Search...", color = Color.LightGray, fontSize = 24.sp)
        }, textStyle = TextStyle(fontSize = 24.sp)
        )
    }
}