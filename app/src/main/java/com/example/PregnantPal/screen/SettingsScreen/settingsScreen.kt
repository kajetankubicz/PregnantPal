import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pregnantpal.R
import com.example.PregnantPal.screen.Navigation.Screens
import com.google.firebase.auth.FirebaseAuth


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController
) {

    Column( modifier = Modifier
        .background(androidx.compose.material3.MaterialTheme.colorScheme.tertiary)
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                },
                elevation = 5.dp,
                backgroundColor = (androidx.compose.material3.MaterialTheme.colorScheme.onTertiary),
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "Projekt grupowy 10@KIBI'2023",
                    fontSize = 25.sp,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Kajetan Kubicz, Renata Bańka, Agnieszka Blok, Antoni Górecki",
                    fontSize = 25.sp,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.tertiaryContainer
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                ) {
                    Button(
                        onClick = {
                            FirebaseAuth.getInstance().currentUser?.let {
                                FirebaseAuth.getInstance().signOut()
                                navController.navigate(route = Screens.SignInScreen.name)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
                            contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    ) {
                        Text(
                            text = "Sign out",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

}
