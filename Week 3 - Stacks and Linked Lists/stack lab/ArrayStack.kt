import Stack
class ArrayStack() : Stack{

    var list: Array<Int?> = arrayOfNulls<Int>(10)

    override fun push(item: Int) {
        for (i in list.indices) {
            if (list[i] == null) {
                list[i] = item
                break
            }
        }
    }

    override fun pop(): Int? {
        for (i in list.indices.reversed()) {
            if (list[i] != null) {
                val a: Int = list[i]!!
                list[i] = null
                return a
            }
        }
        return null
    }

    override fun peek(): Int? {
        for (i in list.indices) {
            if (list[i] == null) {
                return list[i - 1]!!
            }
        }
        return null
    }

    override fun isEmpty(): Boolean {
        if (list[0] == null) {
            return true
        }
        return false
    }

    override fun toString(): String {
        var b: String = ""
        for (i in list.size - 1 downTo 0) {
            if (list[i] != null) {
                b += list[i] 
                b += "\n"
            }
        }
        return b
    }
 }


fun main() {
    val s = ArrayStack()
    s.push(2025)
    s.push(4)
    s.push(16)

    println("Elements present in stack: ")
    println(s)

    println("" + s.pop() + " popped from stack")
    println("Top element is: " + s.peek())

    println("Elements present in stack: ")
    println(s)
}

/**
 * How do I run this file?
 * 
 * Step 1: Compile
 * 
 * kotlinc StackInt.kt ArrayStack.kt
 * 
 * Step 2: Run
 * 
 * /opt/homebrew/bin/kotlin ArrayStackKt
 */