package problemSolving

// O(N^3)
fun bruteForceLongestPalindrome(s: String): String {
    val size = s.length
    var result = ""
    for (i in 0 until size) { // O(N)
        for (j in i..size) { // O(N)
            if (isPalindrome(s.substring(i, j)) && result.length < j - i) { // O(N)
                result = s.substring(i, j)
            }
        }
    }
    return result
}

// O (N)
private fun isPalindrome(s: String): Boolean {
    if (s.length == 1) return true
    var startIndex = 0
    var endIndex = s.length - 1
    while (startIndex < endIndex) {
        if (s[startIndex] != s[endIndex]) {
            return false
        }
        startIndex++
        endIndex--
    }
    return true
}

fun main() {
    println(bruteForceLongestPalindrome("aacabdkacaa"))
}