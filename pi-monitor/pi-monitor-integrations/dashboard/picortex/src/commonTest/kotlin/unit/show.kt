package unit

import presenters.charts.BarChart

fun show(chart: BarChart<*>) {
    val entries = chart.entries
    val max = entries.maxOfOrNull { it.value.toInt() } ?: 10
    println("- ".repeat(max / 2) + chart.title + " -".repeat(max / 2))
    for (entry in entries) {
        println(entry.label)
        print("= ".repeat(entry.value.toInt()))
        println("=> (${entry.value})")
    }
}