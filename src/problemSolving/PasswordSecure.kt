package problemSolving

import problemSolving.PasswordSecure.solution
import java.util.regex.Pattern


object PasswordSecure {
    private val passwordRegex: String = "((?=.+[a-z])(?=.+\\d)(?=.+[A-Z])(?=.+[@#\$%!])(?=\\S+\$).{6,})"
    private val passwordPattern: Pattern = Pattern.compile(passwordRegex)

    fun solution(string: String): Boolean {
        return passwordPattern.matcher(string).matches()
    }
}

fun main() {
    val case1 = "FooBar123!"
    val case2 = "foobar123!"
    val case3 = "FooBar123"
    val case4 = "F0bar! F0bar!"
    val case5 = "Fo0"

    println(solution(case1))
    println(solution(case2))
    println(solution(case3))
    println(solution(case4))
    println(solution(case5))
}