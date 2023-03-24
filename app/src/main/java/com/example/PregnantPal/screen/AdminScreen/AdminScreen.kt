package com.example.pregnantpal.screen.AdminScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pregnantpal.R
import com.example.pregnantpal.screen.Navigation.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AdminScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {

            var expanded = remember {
                mutableStateOf(false)
            }

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

                    Box {
                        IconButton(
                            onClick = { expanded.value = true },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                Icons.Filled.Settings, contentDescription = "Menu",
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier
                                .background(
                                    androidx.compose.material3.MaterialTheme.colorScheme.secondaryContainer,
                                    RoundedCornerShape(0.dp)
                                )
                                .width(95.dp)
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    FirebaseAuth.getInstance().currentUser?.let {
                                        FirebaseAuth.getInstance().signOut()
                                        navController.navigate(route = Screens.SignInScreen.name)
                                    }
                                }
                            ) {
                                Text(
                                    text = "Sign out",
                                    color = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }


                }
            }
        },
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome, Admin!",
                style = androidx.compose.material3.MaterialTheme.typography.displayMedium,
                color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ParameterCard(
                    title = "Blood Pressure",
                    value = "120/80 mmHg"
                )
                ParameterCard(
                    title = "Weight",
                    value = "65 kg"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ParameterCard(
                    title = "Blood Sugar",
                    value = "120 mg/dL"
                )
                ParameterCard(
                    title = "Hemoglobin",
                    value = "12 g/dL"
                )
            }
        }
    }
}

@Composable
fun ParameterCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(150.dp),
        elevation = 4.dp,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(width = 2.dp, color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}
