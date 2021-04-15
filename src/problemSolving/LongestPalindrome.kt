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

// O(N^2)
fun dynamicLongestPalindrome(s: String): String {
    val array = Array(s.length) { Array(s.length) { false } }
    var maxLength = 1
    var startIndex = 0
    // O(N)
    for (i in 0 until s.length) { // One sized palindromes
        array[i][i] = true
    }
    // O(N)
    for (i in 0 until s.length - 1) { // Two sized palindromes
        if (s[i] == s[i + 1]) {
            array[i][i+1] = true
            startIndex = i
            maxLength = 2
        }
    }

    for (k in 3 .. s.length) { // O(N)
        for (i in 0 .. s.length - k) { // O(N)
            val j = i + k - 1
            if (array[i + 1][j - 1] && s[i] == s[j]) {
                array[i][j] = true
                if (maxLength < k) {
                    startIndex = i
                    maxLength = k
                }
            }
        }
    }
    // O(N) + O(N) + O(N) * O(N) = O(2N) + O(N^2) = O(N^2)
    return s.substring(startIndex, startIndex + maxLength)
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