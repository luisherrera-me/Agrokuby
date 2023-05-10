package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.psychiatry_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kubyapp.agrokuby.ui.theme.VerdeMenta
import com.kubyapp.agrokuby.ui.theme.blue
import com.kubyapp.agrokuby.ui.theme.gray200
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.axis.AxisRenderer
import com.patrykandpatrick.vico.core.axis.horizontal.HorizontalAxis
import com.patrykandpatrick.vico.core.chart.decoration.Decoration
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.marker.Marker
import kotlinx.coroutines.delay
import java.lang.Double.max
import java.util.Random
import kotlin.math.max



@OptIn(ExperimentalMaterialApi::class)
@Preview(backgroundColor = 0xFF00BCD4, showBackground = true)
@Composable
fun Psychiatry(){

    var isPressed by remember { mutableStateOf(false) }
    val pointShape = shapeComponent(
        shape = Shapes.pillShape,
        color = blue,
        strokeColor = blue,
        strokeWidth = 1.dp,
    )
    val random = remember { Random(0) }
    val model = entryModelOf(*Array(21) { random.nextDouble() })

    Card(
        onClick = { isPressed = true },
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .width(60.dp)
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .scale(if (isPressed) 0.996f else 1f)//Escala
            .alpha(if (isPressed) 0.98f else 1f)//Opacidad
            .clickable { },
        elevation = if (isPressed) 0.dp else 0.dp, //Modificación de la elevación
        backgroundColor = gray200,
        shape = RoundedCornerShape(20.dp)
    ){
        Chart(
            chart = lineChart(),
            model = model,
            bottomAxis = bottomAxis(
                tickPosition = HorizontalAxis.TickPosition.Center(
                    offset = 0,
                    //spacing = max(1, model.maxX.toInt()),
                    spacing = 5,
                ),
                valueFormatter = { value, _ ->
                    "Label: $value"
                },
            ),

            chartScrollSpec = rememberChartScrollSpec(isScrollEnabled = false),
            isZoomEnabled = false,
        )
    }


    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(400)
            isPressed = false
        }
    }

}







