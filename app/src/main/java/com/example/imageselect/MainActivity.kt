package com.example.imageselect

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.imageselect.ui.theme.ImageSelectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSelectTheme {
                PhotoPickerScreen()
            }
        }
    }
}

@Composable
fun PhotoPickerScreen() {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
           //.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
           Text(
               text = "Wybierz zdjęcie",
               style = TextStyle(
                   fontSize = 24.sp,
                   fontWeight = FontWeight.Medium,
                   color = Color.Black
               )
           )
            Spacer(modifier = Modifier.size(20.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                shape = RoundedCornerShape(8),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0073E6),
                    contentColor = Color.White

                ),
                onClick = { photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
                }
            ) {

                Row (
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_input_add),
                        contentDescription = "Dodaj zdjęcie")

                    Text(
                        text = "Wybierz zdjęcie",
                        style = TextStyle(
                            fontSize = 18.sp
                        )
                    )
                }
            AsyncImage(modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(8.dp)),
                model = selectedImageUri,
                contentDescription = null,
                contentScale = ContentScale.FillWidth)
}
        }

    }
}
@Preview
@Composable
fun PhotoPickerScreenPreview() {
    PhotoPickerScreen()
}