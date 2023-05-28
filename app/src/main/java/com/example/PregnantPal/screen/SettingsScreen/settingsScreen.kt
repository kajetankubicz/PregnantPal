import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.pregnantpal.R
import com.example.pregnantpal.screen.Navigation.Screens
import com.google.firebase.auth.FirebaseAuth
import com.example.pregnantpal.screen.DetailScreen.PregnantPalScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
) {

    val composition= rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.settings))
    val progress by animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                },
                elevation = AppBarDefaults.TopAppBarElevation,
                backgroundColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Projekt grupowy 10@KIBI'2023",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Kajetan Kubicz, Renata Bańka, Agnieszka Blok, Antoni Górecki",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        FirebaseAuth.getInstance().currentUser?.let {
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(route = Screens.SignInScreen.name)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Sign out",
                        fontWeight = FontWeight.Bold
                    )
                }
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
}


