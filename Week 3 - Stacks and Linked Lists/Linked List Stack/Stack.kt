// Kotlin version of stack code from
// https://www.geeksforgeeks.org/introduction-to-stack-data-structure-and-algorithm-tutorials/
// Ported by Dave Musicant
class Stack<T> {

    private val list = mutableListOf<T>()

    fun isEmpty(): Boolean {
        return list.size == 0
    }

    fun push(item: T) {
        list.add(item)
    }

    fun pop(): T {
        if (list.size == 0) {
            throw Exception("Stack is empty.")
        }

        return list.removeLast()
    }

    fun peek(): T {
        if (list.size == 0) {
            throw Exception("Stack is empty.")
        }

        return list[list.size-1]
    }

    override fun toString(): String {
        return list.toString()
    }
}

// Driver code
fun main() {
    val s = Stack<Int>()
    s.push(10)
    s.push(20)
    s.push(30)
    println("" + s.pop() + " Popped from stack")
    println("Top element is :" + s.peek())
    println("Elements present in stack :")
    println(s)
}
