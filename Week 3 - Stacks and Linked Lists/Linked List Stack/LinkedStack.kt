// Kotlin linked list version of stack
// by Dave Musicant
class LinkedStack<T> {

    private data class Node<T>(
        var item: T,
        var next: Node<T>?)

    private var head: Node<T>? = null

    fun isEmpty(): Boolean {
        return head == null
    }

    fun push(item: T) {
        val newNode = Node(item, null)
        newNode.next = head
        head = newNode
    }

    fun pop(): T {
        val current = head
        if (current == null) {
            throw Exception("List is empty!")
        } else {
            val poppedValue = current.item
            head = current.next
            return poppedValue
        }
    }

    fun peek(): T {
        val current = head
        if (current == null) {
            throw Exception("Stack is empty.")
        } else {
            return current.item
        }
    }

    override fun toString(): String {
        var result = ""
        var current = head
        while (current != null) {
            result = result + current.item + " "
            current = current.next
        }
        return result.trim()
    }
}

// Driver code
fun main() {
    val s = LinkedStack<Int>()
    s.push(10)
    s.push(20)
    s.push(30)
    println("" + s.pop() + " Popped from stack")
    println("Top element is :" + s.peek())
    println("Elements present in stack :")
    println(s)
}
