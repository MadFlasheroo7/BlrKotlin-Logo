package com.example.blrkotlinlogo

import android.graphics.RenderEffect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blrkotlinlogo.ui.theme.BlrkotlinLogoTheme
import com.example.blrkotlinlogo.ui.theme.kotlinOrange
import com.example.blrkotlinlogo.ui.theme.kotlinPink
import com.example.blrkotlinlogo.ui.theme.kotlinPurple

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlrkotlinLogoTheme {
                Reveal()
            }
        }
    }
}


object KotlinLogo {
    val ktLogo = PathParser().parsePathString(
        "M499.8 499.9H0V0.400024H499.8L249.9 250L499.8 499.9Z"
    ).toNodes()

    val layer1 = PathParser().parsePathString(
        "M396.6 18.8H296.3L80.0999 250.7V20.4H0.199951V519.9H80.0999V278.6L297.9 519.9H399.7L169.4 261.6L396.6 18.8Z"
    ).toNodes()

    val layer2 = PathParser().parsePathString(
        "M667.6 170.8C639.4 155.1 606.5 145.7 572 145.7C536 145.7 503.1 153.5 474.9 170.8C446.7 188.1 423.2 210 407.5 238.2C391.8 268 384 300.9 384 336.9C384 372.9 391.8 407.4 407.5 435.6C423.2 463.8 445.1 487.3 474.9 503C503.1 518.7 536 528.1 572 528.1C608 528.1 640.9 520.3 669.1 503C697.3 487.3 719.2 463.8 734.9 435.6C750.6 405.8 758.4 372.9 758.4 336.9C758.4 300.9 749 268 733.3 238.2C717.8 209.9 695.8 186.4 667.6 170.8ZM661.4 400.8C652 419.6 639.5 433.7 623.8 443.1C608.1 454.1 589.3 458.8 569 458.8C548.7 458.8 529.8 454.1 512.6 443.1C496.9 432.1 484.4 418 475 400.8C465.6 382 462.5 361.6 462.5 338.1C462.5 314.6 467.2 292.7 476.6 275.4C486 256.6 498.5 242.5 514.2 233.1C529.9 222.1 548.7 217.4 570.6 217.4C591 217.4 609.8 222.1 625.4 233.1C641.1 244.1 653.6 258.2 663 275.4C672.4 294.2 675.5 314.6 675.5 338.1C675.5 361.6 670.8 382 661.4 400.8Z"
    ).toNodes()

    val layer3 = PathParser().parsePathString(
        "M919.9 62.7H843.1V125.4C843.1 134.8 841.5 142.6 835.3 147.3C830.6 152 822.8 155.1 813.4 155.1H775.8V220.9H841.6V418.3C841.6 438.7 846.3 457.5 854.1 473.1C861.9 488.8 874.5 501.3 890.1 509.1C905.8 518.5 923 521.6 944.9 521.6H1004.4V452.7H960.5C949.5 452.7 940.1 449.6 932.3 440.2C926 432.4 921.3 421.4 921.3 408.9V220.9H1005.9V155.1H921.3V62.7H919.9Z"
    ).toNodes()

    val layer4 = PathParser().parsePathString(
        "M1143.9 0H1065.6V519.9H1143.9V0Z"
    ).toNodes()

    val layer5 = PathParser().parsePathString(
        "M1313.1 1.59998H1233.2V83.1H1313.1V1.59998Z"
    ).toNodes()

    val layer6 = PathParser().parsePathString(
        "M1311.5 155.1H1233.2V521.4H1311.5V155.1Z"
    ).toNodes()

    val layer7 = PathParser().parsePathString(
        "M1711.1 214.6C1700.1 192.7 1684.5 175.4 1664.1 164.5C1643.7 152 1620.2 145.7 1593.6 145.7C1565.4 145.7 1540.3 153.5 1518.4 166.1C1501.2 177.1 1487.1 192.7 1476.1 210V153.6H1400.9V519.9H1479.2V314.6C1479.2 294.2 1482.3 277 1490.2 261.3C1498 245.6 1509 234.7 1521.5 225.3C1535.6 217.5 1551.3 212.8 1570.1 212.8C1587.3 212.8 1599.9 215.9 1612.4 223.8C1624.9 231.7 1632.8 241 1639 253.6C1645.3 266.1 1648.4 281.8 1648.4 299V518.3H1726.7V289.6C1728.4 261.6 1722.2 236.6 1711.1 214.6Z"
    ).toNodes()

}


val shaderTextGradient = listOf(
    kotlinOrange,
    kotlinPink,
    kotlinPurple
)
val customColorStops = listOf(
    0.00343514f,
    0.4689f,
    1.0f
)

/**
 * kotlin logo with gradient
 */
@Preview
@Composable
fun KotlinLogo(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "text gradient animation")

    val brush = Brush.linearGradient(
        colorStops = arrayOf(
            0.0034f to kotlinOrange,
            0.4689f to kotlinPink,
            1.0f to kotlinPurple
        ),
        start = Offset(499.73f, 0.32f),
        end = Offset(0.07f, 499.98f)
    )

    val icon = rememberVectorPainter(
        defaultWidth = 500.dp,
        defaultHeight = 500.dp,
        viewportWidth = 500f,
        viewportHeight = 500f,
        autoMirror = true
    ) { _, _ ->

        Group("logo") {
            androidx.compose.ui.graphics.vector.Path(
                pathData = KotlinLogo.ktLogo,
                fill = brush,
            )
        }
    }

    /*    val icon = rememberVectorPainter(
            defaultWidth = 1727.dp,
            defaultHeight = 528.dp,
            viewportWidth = 1727f,
            viewportHeight = 528f,
            autoMirror = true
        ) { _, _ ->

            Group("logo") {
                androidx.compose.ui.graphics.vector.Path(
                    pathData = KotlinLogo.ktLogo,
                    fill = SolidColor(Color.Magenta),
                    stroke = SolidColor(Color(0xFF00C798)),
                )
            }
        }*/

    Image(
        painter = icon,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun Reveal(modifier: Modifier = Modifier) {
    var reveal by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (reveal) Color.Black else Color.Transparent,
        animationSpec = tween(2000),
        label = ""
    )

    val blur = remember { Animatable(0f) }

    LaunchedEffect(reveal) {
        blur.animateTo(100f, tween(500, easing = LinearEasing))
        blur.animateTo(0f, tween(1000, easing = FastOutLinearInEasing))
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable(
                indication = null,
                onClick = {
                    reveal = !reveal
                },
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Image(
            painter = painterResource(R.drawable.oldlogo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(500.dp)
        )
        AnimatedContent(
            targetState = reveal,
            transitionSpec = {
                scaleIn() togetherWith scaleOut()
            },
            modifier = modifier
                .fillMaxSize()
                .background(backgroundColor),
        ) {
            AnimatedVisibility(
                it,
                enter = fadeIn() + scaleIn(),
                exit = scaleOut(tween(2000)) + fadeOut(
                    tween(2000)
                ),
                modifier = Modifier.customBlur(blur.value),
                label = ""
            ) {
                KfugLogo(modifier)
            }
        }
    }
}

fun Modifier.customBlur(blur: Float): Modifier {
    return graphicsLayer {
        if (blur > 0f)
            renderEffect = RenderEffect
                .createBlurEffect(
                    blur,
                    blur,
                    android.graphics.Shader.TileMode.DECAL,
                )
                .asComposeRenderEffect()
    }
}