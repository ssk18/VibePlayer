package com.ssk.vibeplayer.feature.scanner.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.ssk.vibeplayer.core.domain.model.Track
import com.ssk.vibeplayer.core.presentation.designsystem.theme.VibePlayerTheme
import com.ssk.vibeplayer.feature.scanner.presentation.R
import com.ssk.vibeplayer.feature.scanner.presentation.trackhandler.TrackUi

private val GradientStart = Color(0x33F1FF95) // F1FF95 at 20% opacity
private val GradientEnd = Color(0xFF1A1A1A) // Dark blackish

@Composable
fun TrackScreenItem(
    modifier: Modifier = Modifier,
    track: TrackUi
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = track.albumArtUri,
            contentDescription = "album image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            error = {
                AlbumArtPlaceholder()
            },
            loading = {
                AlbumArtPlaceholder()
            }
        )
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = track.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = track.artist,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = track.formattedDuration,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
private fun AlbumArtPlaceholder() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(GradientStart, GradientEnd)
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_music),
            contentDescription = "music icon",
            modifier = Modifier.size(32.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF030303)
@Composable
fun TrackScreenItemPreview() {
    VibePlayerTheme {
        TrackScreenItem(
            track = TrackUi(
                id = 1L,
                title = "Pardesi",
                artist = "Shashwat",
                duration = 1L,
                filePath = "abcd",
                albumArtUri = null,
                fileSize = 12L
            )
        )
    }
}