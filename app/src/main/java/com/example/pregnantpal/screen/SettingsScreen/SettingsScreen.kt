package com.example.pregnantpal.screen.SettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.buildSpannedString
import androidx.navigation.NavController
import com.example.pregnantpal.R
import com.example.pregnantpal.screen.Navigation.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                    )
                }
            },
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.size(30.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)) {

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

            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ){
                Button(
                    onClick = {
                        FirebaseAuth.getInstance().currentUser?.let {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(route = Screens.SignInScreen.name)
                        }
                    }
                ) {
                    Text(
                        text = "Sign out",
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }

        

    }
}