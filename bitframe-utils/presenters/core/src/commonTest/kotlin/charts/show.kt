package charts

import presenters.charts.Chart

fun show(chart: Chart<*>) {
    val entries = chart.datasets
    val max = entries.maxOfOrNull { it.value.toInt() } ?: 10
    println("- ".repeat(max / 2) + chart.title + " -".repeat(max / 2))
    for (entry in entries) {
        println(entry.label)
        print("= ".repeat(entry.value.toInt()))
        println("=>")
    }
}