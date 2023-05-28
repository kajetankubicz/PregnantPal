import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pregnantpal.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Composable
fun ResultsScreen(
    navController: NavController,
) {
    // Retrieve the current user ID
    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid

    // Create a mutable state to hold the result data
    var resultData = remember { mutableStateOf("") }

    LaunchedEffect(currentUserId) {
        currentUserId?.let { userId ->
            // Access the Firestore collection and document for the user ID
            val firestore = FirebaseFirestore.getInstance()
            val collectionRef = firestore.collection("users")
            val documentRef = collectionRef.document(userId)

            try {
                // Fetch the result data
                val documentSnapshot = documentRef.get().await()
                if (documentSnapshot.exists()) {
                    // Result exists, retrieve the data
                    val data = documentSnapshot.data
                    // Update the resultData state with the retrieved data
                    resultData.value = data?.get("result")?.toString() ?: ""
                } else {
                    // Result does not exist
                    resultData.value = "No result found"
                }
            } catch (e: Exception) {
                // Handle any errors that occur during fetching
                resultData.value = "Error fetching result: ${e.message}"
            }
        }
    }


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
                val floatValue = resultData.value.toFloatOrNull()
                val imageResource = when {
                    floatValue != null -> {
                        when (floatValue) {
                            in 0.0f..0.4f -> R.drawable.red_woman
                            in 0.4f..0.8f -> R.drawable.orange_woman
                            in 0.8f..1.0f -> R.drawable.green_woman
                            else -> R.drawable.splash_image
                        }
                    }

                    else -> R.drawable.splash_image
                }

                val announcement = when {
                    floatValue != null -> {
                        when (floatValue) {
                            in 0.0f..0.4f -> "Immediate medical attention warranted: Woman's pregnancy in danger."
                            in 0.4f..0.8f -> "Medical evaluation warranted: Uncertain state of woman's pregnancy."
                            in 0.8f..1.0f -> "No immediate concern warranted: Woman's pregnancy in good shape."
                            else -> "No data available"
                        }
                    }

                    else -> "No data available"
                }

                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = "Result Image",
                            modifier = Modifier
                                .padding(10.dp)
                                .size(100.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column() {
                            Text(
                                text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(
                                    Date()
                                ),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Divider(
                                modifier = Modifier.padding(10.dp),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = announcement,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
            }

        }
    }
}