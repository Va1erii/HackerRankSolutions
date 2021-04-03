package problemSolving

import java.util.*


fun solution(n: Int): Array<String> {
    return generateParenthesis(n).toTypedArray()
}

fun generateParenthesis(n: Int): List<String> {
    val result = ArrayList<String>()
    generateParenthesis(result, "", n, n)
    return result
}

fun generateParenthesis(result: ArrayList<String>, s: String, left: Int, right: Int) {
    if (left > right) return
    if (left == 0 && right == 0) {
        result.add(s)
        return
    }
    if (left > 0) {
        generateParenthesis(result, "$s(", left - 1, right)
    }
    if (right > 0) {
        generateParenthesis(result, "$s)", left, right - 1)
    }
}

fun main() {
    generateParenthesis(3).forEach {
        println(it)
    }

}