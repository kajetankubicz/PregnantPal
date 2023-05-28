package com.example.pregnantpal.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.*
import com.example.pregnantpal.R
import com.example.pregnantpal.screen.Navigation.Screens

//MainScreen that takes NavController as a parameter to handle navigation between screens
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
){
    val composition= rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.home_lottie))
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )
    //Scaffold provides basic layout structure
    Scaffold(
        topBar = {

            TopAppBar(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                backgroundColor = (MaterialTheme.colorScheme.onSurface),
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
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        },
        backgroundColor = MaterialTheme.colorScheme.surface,
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
                    onClick = {
                        navController.navigate(route = Screens.MyAccountScreen.name)
                    }
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
                    onClick = { navController.navigate(route = Screens.ResultsScreen.name) }
                )

                Tile(
                    title = "Settings",
                    icon = Icons.Filled.Settings,
                    onClick = { navController.navigate(route = Screens.SettingsScreen.name) }
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Box(modifier = Modifier
                .fillMaxSize()
            ){
                    LottieAnimation(
                        modifier = Modifier.fillMaxSize(),
                        composition = composition.value,
                        progress = {progress}
                    )
            }

        }
    }


}


//Composable function that is used above
@Composable
fun Tile(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
            .size(150.dp),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colorScheme.onSurface,
        shape = RoundedCornerShape(8.dp)
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
                tint = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color =MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.W600
            )
        }
    }
}