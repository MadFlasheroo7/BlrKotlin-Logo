package com.example.blrkotlinlogo

import android.graphics.ComposeShader
import android.graphics.PorterDuff
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.blrkotlinlogo.ui.theme.kfugSecondary
import com.example.blrkotlinlogo.ui.theme.kotlinOrange
import com.example.blrkotlinlogo.ui.theme.kotlinPink
import com.example.blrkotlinlogo.ui.theme.kotlinPurple
import com.example.blrkotlinlogo.ui.theme.sansFont
import kotlinx.coroutines.launch

object KfugLogo {
    val layer1 = PathParser().parsePathString(
        "M155.964 37.7462L118.774 0.55661L81.5845 37.7462L118.774 74.9358V37.7462C118.774 37.7462 156.605 37.1508 155.964 37.7462Z"
    ).toNodes()

    val layer2 = PathParser().parsePathString(
        "M178.864 97.8358V45.2574H126.285V97.8358L152.574 71.5466C152.574 71.5466 179.734 97.8358 178.864 97.8358Z"
    ).toNodes()

    val layer3 = PathParser().parsePathString(
        "M152.574 156.551L189.764 119.362L152.574 82.1722L115.385 119.362H152.574C152.574 119.362 153.17 157.147 152.574 156.551Z"
    ).toNodes()

    val layer4 = PathParser().parsePathString(
        "M92.4849 179.451H145.063V126.827H92.4849L118.774 153.116C118.774 153.162 92.4849 180.322 92.4849 179.451Z"
    ).toNodes()

    val layer5 = PathParser().parsePathString(
        "M33.7692 153.162L70.9588 190.352L108.148 153.162L70.9588 115.973V153.162C71.0046 153.162 33.1738 153.758 33.7692 153.162Z"
    ).toNodes()

    val layer6 = PathParser().parsePathString(
        "M10.8691 93.0726V145.651H63.4933V93.0726L37.2041 119.362C37.2041 119.362 9.99887 93.0726 10.8691 93.0726Z"
    ).toNodes()

    val layer7 = PathParser().parsePathString(
        "M37.2043 34.357L0.0146484 71.5466L37.2043 108.736L74.3939 71.5466H37.2043C37.2043 71.5466 36.563 33.7616 37.2043 34.357Z"
    ).toNodes()

    val layer8 = PathParser().parsePathString(
        "M97.2936 64.0812H44.6694V11.457H97.2936L71.0044 37.7462L97.2936 64.0812Z"
    ).toNodes()
}

@Composable
fun KfugLogo(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "text gradient animation")

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = EaseInExpo),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val brush = remember(offset) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * offset
                val heightOffset = size.height * offset
                return LinearGradientShader(
                    colorStops = customColorStops,
                    colors = shaderTextGradient,
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset / size.width, heightOffset + size.height),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

    val strokeProgress = remember { Animatable(1f) }
    val fillProgress = remember { Animatable(0f) }
    val strokeAlpha = remember { Animatable(1f) }

    val durationMillis = 5500
    LaunchedEffect(Unit) {
        strokeProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = durationMillis, easing = EaseInExpo)
        )
        launch {
            strokeAlpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = durationMillis / 2, easing = LinearEasing)
            )
        }
        fillProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
    }

    var time by remember { mutableFloatStateOf(0f) }


    val noiseShader = remember { NoiseShader() }

    val shaderBrush = remember(time) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * offset
                val heightOffset = size.height * offset
                noiseShader.updateTime(time)

                val gradient = LinearGradientShader(
                    colors = shaderTextGradient,
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width, heightOffset + size.height),
                    tileMode = TileMode.Mirror
                )

                return ComposeShader(
                    noiseShader,
                    gradient,
                    PorterDuff.Mode.MULTIPLY
                )
            }
        }
    }

    val icon = rememberVectorPainter(
        defaultWidth = 190.dp,
        defaultHeight = 190.dp,
        viewportWidth = 190f,
        viewportHeight = 190f,
        autoMirror = true
    ) { _, _ ->
        Group("logo") {
            Group("layer1") {
                Path(
                    pathData = KfugLogo.layer1,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinOrange),
                    strokeLineWidth = 2f,
                    strokeAlpha = strokeAlpha.value,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                )
            }

            Group("layer2") {
                Path(
                    pathData = KfugLogo.layer2,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinOrange),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value,
                )
            }
            Group("layer3") {
                Path(
                    pathData = KfugLogo.layer3,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinOrange),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value,
                )
            }
            Group("layer4") {
                Path(
                    pathData = KfugLogo.layer4,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinPink),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value,
                )
            }
            Group("layer5") {
                Path(
                    pathData = KfugLogo.layer5,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinPurple),
                    strokeLineWidth = 2f,
                    strokeAlpha = strokeAlpha.value,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                )
            }
            Group("layer6") {
                Path(
                    pathData = KfugLogo.layer6,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinPurple),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value,
                )
            }
            Group("layer7") {
                Path(
                    pathData = KfugLogo.layer7,
                    fill = SolidColor(kfugSecondary),
                    stroke = SolidColor(kotlinPurple),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value,
                )
            }
            Group("layer8") {
                Path(
                    pathData = KfugLogo.layer8,
                    fill = brush,
                    stroke = SolidColor(kotlinPink),
                    strokeLineWidth = 2f,
                    fillAlpha = fillProgress.value,
                    trimPathStart = strokeProgress.value,
                    strokeAlpha = strokeAlpha.value
                )
            }

        }
    }

    LaunchedEffect(Unit) {
        val startTime = withFrameNanos { it } / 7_000_000_000f

        while (true) {
            withFrameNanos { frameTime ->
                val currentTime = frameTime / 7_000_000_000f - startTime
                time = currentTime

                if (currentTime >= 1000 / 1000f) {
                    time = 1f
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        modifier = modifier.fillMaxSize()
    ) {

//        BrushedIcon(
//            painter = icon,
//            brush = shaderBrush,
//            modifier = modifier
//                .onSizeChanged {
//                    noiseShader.updateResolution(it.toSize())
//                }
//        )

        Image(
            painter = icon,
            contentDescription = null,
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
                .onSizeChanged {
                    noiseShader.updateResolution(it.toSize())
                }
        )
        Text(
            text = "Blr",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 140.sp,
                brush = shaderBrush,
                fontWeight = FontWeight.ExtraBold,
                textMotion = TextMotion.Animated
            ),
            modifier = modifier
                .onSizeChanged {
                    noiseShader.updateResolution(it.toSize())
                }
        )
        Text(
            text = "Kotlin",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 140.sp,
                brush = shaderBrush,
                fontFamily = sansFont,
                textMotion = TextMotion.Animated
            ),
            modifier = modifier
                .onSizeChanged {
                    noiseShader.updateResolution(it.toSize())
                }
        )
    }
}