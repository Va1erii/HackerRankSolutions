package problemSolving

import kotlin.math.abs

fun solution1(A: IntArray): Int {
    var currentLength = 0
    var maxLength = 0
    val map = HashMap<Int, Int>()
    for (i in A.indices) {
        if (map.size == 2 && !map.contains(A[i])) {
            if (currentLength > maxLength) maxLength = currentLength
            var latestEntry = 0 to 0
            map.entries.forEach {
                if (it.value > latestEntry.second) latestEntry = it.key to it.value
            }
            map.clear()
            map[latestEntry.first] = latestEntry.second
            map[A[i]] = i
            currentLength = i - latestEntry.second + 1
        } else {
            map[A[i]] = i
            currentLength++
            if (currentLength > maxLength) maxLength = currentLength
        }
    }
    return maxLength
}

// n log n
fun solution(A: IntArray): Int {
    val map = HashMap<Int, Int>()
    // n
    for (i in A.indices) {
        map[A[i]] = i
    }
    // n log n
    val keys = map.keys.sorted()

    // n
    var maxDistance = 0
    for (i in 0..keys.size - 2) {
        val currentDistance = abs(map[keys[i]]!! - map[keys[i + 1]]!!)
        if (currentDistance > maxDistance) maxDistance = currentDistance
    }
    return maxDistance
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun addTwoNumbers(l1: ListNode, l2: ListNode): ListNode {
    val result = ListNode(-1)
    var current = result
    var prev = current
    var currentNode1: ListNode? = l1
    var currentNode2: ListNode? = l2

    var mode = false
    while (currentNode1 != null || currentNode2 != null) {
        var value = (currentNode1?.`val` ?: 0) + (currentNode2?.`val` ?: 0)
        if (mode) {
            value = value + 1
        }
        current.`val` = value % 10
        mode = value >= 10
        current.next = ListNode(-1)

        prev = current
        current = current.next!!
        currentNode1 = currentNode1?.next
        currentNode2 = currentNode2?.next
    }
    if (mode) {
        prev.next = ListNode(1)
    } else {
        prev.next = null
    }
    return result
}

fun main() {
    val l1 = ListNode(2)
    var current = l1
    current.next = ListNode(4)
    current = current.next!!
    current.next = ListNode(3)

    val l2 = ListNode(5)
    current = l2
    current.next = ListNode(6)
    current = current.next!!
    current.next = ListNode(4)

    println(12/10)
}

fun addTwoNumbers2(l1: ListNode, l2: ListNode): ListNode {
    val result = ListNode(0)
    var current = result
    var currentNode1: ListNode? = l1
    var currentNode2: ListNode? = l2
    var carry = 0

    while (currentNode1 != null || currentNode2 != null) {
        val value = (currentNode1?.`val` ?: 0) + (currentNode2?.`val` ?: 0) + carry
        current.next = ListNode(value % 10)
        carry = value / 10

        current = current.next!!
        currentNode1 = currentNode1?.next
        currentNode2 = currentNode2?.next
    }
    if (carry > 0) {
        current.next = ListNode(carry)
    }
    return result.next!!
}