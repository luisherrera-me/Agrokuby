package com.kubyapp.agrokuby.presentation.home_screen.navigationbar.bar_view.psychiatry_screen

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter

class ChartAxisValueFormatter<Position : AxisPosition> : AxisValueFormatter<Position>,
    ChartValueFormatter()