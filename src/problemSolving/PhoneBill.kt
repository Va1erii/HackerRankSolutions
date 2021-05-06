package problemSolving

import problemSolving.PhoneBill.solution
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import kotlin.collections.HashMap

object PhoneBill {
    private val dateFormatter = SimpleDateFormat("hh:mm:ss")
    private var removeTimeZoneDate: Date = dateFormatter.parse("00:00:00")

    fun solution(string: String): Int {
        val logs = string.split('\n')
        val callMap = HashMap<String, CallInfo>()
        logs.forEach {
            val callInfo = resolveCallInfo(it)
            callMap.compute(callInfo.number) { key, old ->
                old?.copy(
                        conversationTime = old.conversationTime + callInfo.conversationTime,
                        pay = old.pay + callInfo.pay
                ) ?: callInfo
            }
        }
        // Find max conversation time
        val maxCall = callMap.values
                .maxOf { it.conversationTime }

        // Find max conversation calls
        val multipleMax = callMap.values
                .asSequence()
                .filter { it.conversationTime == maxCall }
                .toList()

        // Find a call which should be removed
        val toRemove = multipleMax
                .minByOrNull { it.resolveNumericalValue() }
        callMap.remove(toRemove?.number)

        return callMap.values
                .sumBy { it.pay }
    }

    private fun resolveCallInfo(info: String): CallInfo {
        val i = info.split(',')
        val number = i[1]
        val conversationTime = dateFormatter.parse(i[0]).time - removeTimeZoneDate.time
        val pay = if (conversationTime < TimeUnit.MINUTES.toMillis(5)) {
            3 * conversationTime / 1000
        } else {
            var minutes = conversationTime / 1000 / 60
            val hasSeconds = conversationTime - 5 * 1000 * 60 > 0
            if (hasSeconds) {
                minutes++
            }
            minutes * 150
        }
        return CallInfo(number, conversationTime, pay.toInt())
    }

    data class CallInfo(
            val number: String,
            val conversationTime: Long,
            val pay: Int
    ) {
        private val numberPattern = Pattern.compile("(\\d+)-(\\d+)(\\d+)")
        override fun toString(): String {
            return "Number: $number, timeInMillis: $conversationTime, pay: $pay, numericalNumber: ${resolveNumericalValue()}"
        }

        fun resolveNumericalValue(): Int {
            return numberPattern.matcher(number).let {
                it.group(1).toInt() + it.group(2).toInt() + it.group(3).toInt()
            }
        }
    }
}

fun main() {
    val case1 =
            """
                00:01:07,400-234-090
                00:05:01,701-080-080
                00:05:00,400-234-090
    """.trimIndent()

    println(solution(case1))
}