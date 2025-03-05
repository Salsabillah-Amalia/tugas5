package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryApp()
        }
    }
}

@Composable
fun ArtGalleryApp() {
    var currentIndex by remember { mutableStateOf(0) }

    // Daftar karya seni
    val artworks = listOf(
        Artwork(R.drawable.artwork1, "koki bajak laut", "vinsmoke sanji", "2025"),
        Artwork(R.drawable.artwork2, "kaki hitam", "vinsmoke sanji", "2020"),
        Artwork(R.drawable.artwork3, "pangeran", "vinsmoke sanji", "2019"),
        Artwork(R.drawable.artwork4, "anak nomor 4", "vinsmoke sanji", "2022"),
        Artwork(R.drawable.artwork5, "ganteng", "vinsmoke sanji", "2018")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Menampilkan gambar
        Image(
            painter = painterResource(id = artworks[currentIndex].imageRes),
            contentDescription = "Artwork",
            modifier = Modifier
                .size(300.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        // Menampilkan judul dan deskripsi karya seni
        Text(
            text = artworks[currentIndex].title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "${artworks[currentIndex].artist} (${artworks[currentIndex].year})",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol navigasi
        Row {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                modifier = Modifier.padding(end = 8.dp),
                enabled = currentIndex > 0
            ) {
                Text("Previous")
            }

            Button(
                onClick = { if (currentIndex < artworks.size - 1) currentIndex++ },
                enabled = currentIndex < artworks.size - 1
            ) {
                Text("Next")
            }
        }
    }
}

// Data class untuk menyimpan informasi karya seni
data class Artwork(val imageRes: Int, val title: String, val artist: String, val year: String)