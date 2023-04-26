package com.example.pregnantpal.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pregnantpal.R
import com.example.PregnantPal.screen.Navigation.Screens

//MainScreen that takes NavController as a parameter to handle navigation between screens
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
){

    //Scaffold provides basic layout structure
    Scaffold(
        topBar = {

            TopAppBar(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                backgroundColor = (androidx.compose.material3.MaterialTheme.colorScheme.onTertiary),
                elevation = 5.dp,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Icon that displays jajo
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
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        },
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
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
                        navController.navigate(route = Screens.PregnantPalScreen.name)
                    }
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
                    title = "Settings",
                    icon = Icons.Filled.Settings,
                    onClick = { navController.navigate(route = Screens.SettingsScreen.name) }
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Icon(
                painterResource(id = R.drawable.hpw),
                contentDescription = "Happy Pregnant Women",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(500.dp)
                    .padding(top = 20.dp, bottom = 0.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .height(500.dp)
                    .wrapContentHeight(Alignment.Bottom)
            )

        }
    }


}


//Composable function that is used above
// !!!Worth to remember -> This functionality needs to be added in PregnantPal Screen
@Composable
fun Tile(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
            .size(150.dp),
        elevation = 4.dp,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.5.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
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
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
            )
        }
    }
}