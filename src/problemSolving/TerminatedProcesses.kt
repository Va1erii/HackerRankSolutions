package problemSolving

import java.util.regex.Pattern

//fun solution(t: Int, logs: Array<String>): String {
//}

val pattern: Pattern = Pattern.compile("(\\d+) ([A-Za-z]) (.+)")

fun checkProcesses(t: Int, logs: Array<String>): String {
    val mapOfProcesses = mutableMapOf<CharSequence, CharSequence>(
            "A" to "",
            "B" to "",
            "C" to "",
            "D" to "",
            "E" to "",
            "F" to "",
            "G" to "",
            "H" to "",
    )
    var running = 0
    for (log in logs) {
        val matcher = pattern.matcher(log)
        if (matcher.matches()) {
            val time = matcher.group(1).toInt()
            val process = matcher.group(2)
            val status = matcher.group(3)
            if (status == "running") {
                running++
            }
            if (t < time) {
                break
            }
            mapOfProcesses.compute(process) { key, value ->
                if (mapOfProcesses[key] == "running")  {
                    running--
                }
                status
            }
        }
    }
    return if (running == 0 || running > 1) {
        "-1"
    } else {
        return mapOfProcesses.entries.find { it.value == "running" }!!.key.toString()
    }
}

fun main() {
    val t = 15
    val logs = arrayListOf(
            "0 A created",
            "1 B created",
            "10 A running",
            "12 B waiting",
            "13 B running",
            "14 A waiting",
            "17 B terminated",
            "18 A terminated"
    )
    println(checkProcesses(t, logs.toTypedArray()))
}