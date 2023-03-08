package com.example.pregnantpal.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pregnantpal.R
import com.example.pregnantpal.ui.theme.iconsWhite
import com.example.pregnantpal.ui.theme.pregnantPalColor


@Composable
fun MainScreen(navController: NavController){


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                backgroundColor = (pregnantPalColor),
                elevation = 5.dp,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier.size(48.dp),
                        painter = rememberAsyncImagePainter(R.drawable.splash_image),
                        contentDescription = "Logo",
                        tint= Color.Unspecified
                    )

                    Spacer(Modifier.width(22.dp))

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h5,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )


                }
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Tile(
                    title = "My Account",
                    icon = Icons.Filled.AccountCircle,
                    onClick = { /* navigate to account screen */ }
                )

                Tile(
                    title = "Fill Data",
                    icon = Icons.Filled.Edit,
                    onClick = {
                        navController.navigate(route = Screens.PregnantPalScreen.name+"/pregnantPal_screen") }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Tile(
                    title = "View Results",
                    icon = Icons.Filled.AccountBox,
                    onClick = { /* navigate to results screen */ }
                )

                Tile(
                    title = "Contact Us",
                    icon = Icons.Filled.Call,
                    onClick = { /* navigate to contact screen */ }
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=80.dp, start = 40.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Projekt grupowy 10@KIBI'2023",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.caption
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Kajetan Kubicz, Renata Bańka, Agnieszka Blok, Antoni Górecki",
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }


}


@Composable
fun Tile(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
            .size(150.dp),
        elevation = 4.dp,
        backgroundColor = (pregnantPalColor),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.5.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconsWhite,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}