package problemSolving

/**
 * Calculate and print the factorial of a given integer
 * For example n = 30, we get 265252859812191058636308480000000
 */

class ExtraLongFactorialsSolution {
    companion object {
        const val SIZE = 64
    }

    private val one = Array(SIZE) { 0 }.also {
        it[SIZE - 1] = 1
    }

    fun extraLongFactorials(n: Int) {
        val initialValue = Array(SIZE) { 0 }
        val value = n.toString()
        var index = SIZE - 1
        for (c in value.length - 1 downTo 0) {
            initialValue[index--] = value[c].toString().toInt()
        }
        val result = factorial(initialValue)
        var start = false
        for (i in result.indices) {
            if (result[i] > 0) {
                start = true
            }
            if (start) print(result[i])
        }
    }

    // It will be better to use stack. And just loop from 1 ... n without decrementing
    private fun factorial(n: Array<Int>): Array<Int> {
        var result = n
        while (!n.contentEquals(one)) {
            result = multiply(result, decrement(n.copyOf()))
            decrement(n)
        }
        return result
    }

    private fun multiply(a: Array<Int>, b: Array<Int>): Array<Int> {
        val result: Array<Int> = Array(SIZE) { 0 }
        var index = SIZE
        var temp = 0
        var m = 0
        var prev = 0
        main@ for (i in a.size - 1 downTo 0) {
            index = i
            m = 0
            for (j in b.size - 1 downTo 0) {
                if (index > 0) {
                    prev = result[index]
                    temp = a[i] * b[j] + m
                    if (prev != 0) temp += prev
                    result[index] = temp % 10
                    m = (temp - (temp % 10)) / 10
                    index--
                }
            }
        }
        return result
    }

    private fun decrement(n: Array<Int>): Array<Int> {
        for (i in n.size - 1 downTo 0) {
            if (n[i] == 0) n[i] = 9
            else {
                n[i] = n[i] - 1
                break
            }
        }
        return n
    }
}


