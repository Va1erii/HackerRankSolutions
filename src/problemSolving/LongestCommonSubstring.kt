package problemSolving

fun longestCommonSubsequence(s1: String, s2: String): Int {
    val array = Array(s1.length) { Array(s2.length) { 0 } }
    var max = 0
    var index = 0 to 0
    for (i in 0 until s1.length) {
        for (j in 0 until s2.length) {
            if (s1.get(i) != s2.get(j)) {
                array[i][j] = 0
            } else {
                val prev = if (i > 0 && j > 0) array[i - 1][j - 1] else 0
                array[i][j] = prev + 1
                if (array[i][j] > max) {
                    max = array[i][j]
                    index = i to j
                }
            }
        }
    }
    var i = index.first
    var j = index.second
    val substring = StringBuilder()
    while (i >= 0 && j >= 0) {
        substring.append(s1.get(i))
        i--
        j--
    }
    println(substring.reverse())
    return max
}

fun main() {
    val s1 = "abacdfgdcaba"
    val s2 = "abacdgfdcaba"
    println(longestCommonSubsequence(s1, s2))
}