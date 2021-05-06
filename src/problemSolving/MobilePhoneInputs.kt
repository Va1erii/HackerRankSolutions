package problemSolving

import java.util.*

object MobilePhoneInputs {
    fun solution(string: String): String {
        var capsLock = false
        val result = LinkedList<Char>()
        string.forEach {
            when (it) {
                'C' -> capsLock = !capsLock
                'B' -> if (result.size > 0) result.removeLast()
                else -> result.add(if (capsLock) it.toUpperCase() else it)
            }
        }
        return result.joinToString(separator = "")
    }
}

fun main() {
    val case1 = "CrCellBax"
    val case2 = "CgCoodlBClCuck"
    val case3 = "aCaBBCCab"
    val case4 = "aBB"

    println(MobilePhoneInputs.solution(case1))
    println(MobilePhoneInputs.solution(case2))
    println(MobilePhoneInputs.solution(case3))
    println(MobilePhoneInputs.solution(case4))
}