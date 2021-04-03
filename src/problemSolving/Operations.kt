package problemSolving

import java.util.regex.Pattern

class OperationVisitor {
    private val pattern: Pattern = Pattern.compile("(\\w+).?(\\d+)?.?(\\d+)?")
    private val map = HashMap<Int, Int>()
    private var evictValue: Int? = null

    fun visit(operation: String): String {
        val matcher = pattern.matcher(operation)
        if (matcher.matches()) {
            val op = matcher.group(1)
            return when (op) {
                "add" -> {
                    val param1 = matcher.group(2).toInt()
                    val param2 = matcher.group(3).toInt()
                    onAdd(param1, param2)
                }
                "get" -> {
                    val param1 = matcher.group(2).toInt()
                    onGet(param1)
                }
                "remove" -> {
                    val param1 = matcher.group(2).toInt()
                    onRemove(param1)
                }
                "evict" -> {
                    onEvict()
                }
                "exit" -> onExit()
                else -> ""
            }
        }
        return ""
    }

    private fun onAdd(key: Int, value: Int): String {
        evictValue = key
        map[key] = value
        return ""
    }

    private fun onEvict(): String {
        map.remove(evictValue)
        evictValue = null
        return ""
    }

    private fun onGet(key: Int): String {
        val value = map[key]?.let {
            it
        } ?: "-1"
        return value.toString()
    }

    private fun onRemove(key: Int): String {
        if (evictValue == key) {
            evictValue = null
        }
        return map.remove(key)?.toString() ?: "-1"
    }

    private fun onExit(): String {
        return "exit"
    }
}

val operationVisitor = OperationVisitor()

fun solution(n: Array<String>): Array<String> {
    val result = ArrayList<String>()
    for (operation in n) {
        val opRes = operationVisitor.visit(operation)
        if (opRes == "exit") break
        if (opRes.isNotEmpty()) {
            result.add(opRes)
        }
    }
    return result.toTypedArray()
}

fun main() {
    val operations = arrayListOf(
            "add 5 3",
            "add 1 2",
            "get 5",
            "evict",
            "get 1",
            "remove 5",
            "exit"
    ).toTypedArray()
    solution(operations).forEach {
        println(it)
    }
}
