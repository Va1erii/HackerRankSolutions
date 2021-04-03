package problemSolving

fun lengthOfLongestSubstring(s: String): Int {
    var max = 0
    var current = 0
    val map = HashMap<Char, Int>()
    for (i in 0..s.length - 1) {
        if (map.contains(s.get(i))) {
            if (current > max) max = current
            var index = map[s.get(i)]!!
            map.clear()
            for (j in index + 1..i) {
                map[s.get(j)] = j
            }
            current = map.size
        } else {
            map[s.get(i)] = i
            current++
        }
    }
    if (current > max) max = current
    return max
}