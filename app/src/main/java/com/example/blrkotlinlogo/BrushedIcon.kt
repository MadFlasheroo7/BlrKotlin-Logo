package com.example.blrkotlinlogo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BrushedIcon(
    painter: Painter,
    brush: Brush,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .graphicsLayer { alpha = 0.99f }
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        brush = brush,
                        blendMode = BlendMode.SrcIn
                    )
                }
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GradientIconPreview() {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xFF9E00FF), // Magenta
            Color(0xFFFF00A8)  // Pink
        )
    )

    BrushedIcon(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        brush = gradientBrush,
        modifier = Modifier.size(96.dp),
        contentDescription = "Icon with Gradient"
    )
}