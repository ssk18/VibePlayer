package com.ssk.vibeplayer.feature.onboarding.presentation.screen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.vibeplayer.core.presentation.designsystem.theme.accent

@Composable
fun RadarScanAnimation(
    modifier: Modifier = Modifier,
    primaryColor: Color = accent
) {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweep"
    )

    val faintAlpha = 0.1f

    Canvas(modifier = modifier.size(150.dp)) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2

        val ring1Radius = radius * 0.25f
        val ring2Radius = radius * 0.50f
        val ring3Radius = radius * 0.75f
        val ring4Radius = radius - 2.dp.toPx()

        val strokeThin = 1.dp.toPx()
        val strokeThick = 2.dp.toPx()
        val faintColor = primaryColor.copy(alpha = faintAlpha)

        drawCircle(
            color = faintColor,
            radius = ring1Radius,
            center = center,
            style = Stroke(width = strokeThin)
        )

        drawCircle(
            color = faintColor,
            radius = ring2Radius,
            center = center,
            style = Stroke(width = strokeThin)
        )

        drawCircle(
            color = primaryColor,
            radius = ring3Radius,
            center = center,
            style = Stroke(width = strokeThick)
        )

        drawCircle(
            color = faintColor,
            radius = ring4Radius,
            center = center,
            style = Stroke(width = strokeThin)
        )

        val arcTopLeft = Offset(center.x - ring3Radius, center.y - ring3Radius)
        val arcSize = Size(ring3Radius * 2, ring3Radius * 2)

        // Fixed gradient: solid at leading edge, transparent at trailing edge
        // Leading edge at -120° (11 o'clock), trailing at -210° (8 o'clock)
        // Normalized: -120° = 240° = 0.667, -210° = 150° = 0.417
        val sweepGradient = Brush.sweepGradient(
            colorStops = arrayOf(
                0.417f to Color.Transparent,  // 8 o'clock (trailing)
                0.667f to primaryColor        // 11 o'clock (leading)
            ),
            center = center
        )

        rotate(degrees = rotationAngle, pivot = center) {
            drawArc(
                brush = sweepGradient,
                startAngle = -120f,
                sweepAngle = -90f,
                useCenter = true,
                topLeft = arcTopLeft,
                size = arcSize
            )
        }

        // 6. Center dot
        drawCircle(
            color = primaryColor,
            radius = 4.dp.toPx(),
            center = center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A131D)
@Composable
private fun RadarScanAnimationPreview() {
    RadarScanAnimation()
}
