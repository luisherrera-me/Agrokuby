package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.psychiatry_screen

import com.patrykandpatrick.vico.core.chart.values.ChartValues
import com.patrykandpatrick.vico.core.formatter.ValueFormatter

open class ChartValueFormatter : ValueFormatter {

    override fun formatValue(value: Float, chartValues: ChartValues): CharSequence {
        return if (VALUES_TO_SHOW.contains(value.toInt())) {
            value.toString()
        } else {
            ""
        }
    }

    companion object {
        private val VALUES_TO_SHOW = listOf(1, 5, 10, 15, 20, 25, 30)
    }
}