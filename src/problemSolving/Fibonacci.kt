package problemSolving


fun fibRecursive(n: Int): Int {
    if (n == 1 || n == 2) {
        return 1
    } else {
        return fibRecursive(n - 1) + fibRecursive(n - 2)
    }
}

fun fibDynamic(n: Int, memo: Array<Int?>): Int {
    return if (memo[n] != null) {
        memo[n]!!
    } else {
        memo[n] = fibDynamic(n - 1, memo) + fibDynamic(n - 2, memo)
        memo[n]!!
    }
}

fun prepareMemo(n: Int): Array<Int?> {
    return Array<Int?>(n + 1) { null }.also {
        it[1] = 1
        it[2] = 1
    }
}

fun fibBottomUp(n: Int): Int {
    return if (n == 1 || n == 2) {
        return 1
    } else {
        var n1 = 1
        var n2 = 1
        var result = 1
        for (i in 3..n) {
            result = n1 + n2
            n1 = n2
            n2 = result
        }
        result
    }
}

fun fibBottomUpMemo(n: Int): Int {
    if (n == 1 || n == 2) {
        return 1
    }
    val array = Array<Int?>(n + 1) { null }
    array[1] = 1
    array[2] = 1
    for (i in 3..n) {
        array[i] = array[i - 1]!! + array[i - 2]!!
    }
    return array[n]!!
}

fun main() {
    println(fibRecursive(33))
    println(fibDynamic(33, prepareMemo(33)))
    println(fibBottomUp(33))
    println(fibBottomUpMemo(33))
}