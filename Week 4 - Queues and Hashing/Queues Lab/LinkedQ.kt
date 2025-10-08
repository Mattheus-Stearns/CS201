import Queue

private data class Node<T>(
    var item: T,
    var next: Node<T>? = null)

class LinkedQueue<T>(private val capacity: Int = 5) : Queue<T> {
    var items: MutableList<T?> = MutableList(capacity) { null }
    private var front: Node<T>? = null
    private var rear: Node<T>? = null
    
    override fun isEmpty(): Boolean {
        return if (front == null) { true } else { false }
    }

    override fun enqueue(item: T) {
        val newNode = Node(item)
        if (isEmpty()) {
            front = newNode
            rear = newNode
            rear!!.next = front
        } else {
            rear!!.next = newNode
            rear = newNode
            rear!!.next = front
        }
    }
    override fun dequeue(): T? {
        if (isEmpty()) { return null }

        val item = front!!.item

        if (front == rear) {
            front = null
            rear = null
        } else {
            front = front!!.next
            rear!!.next = front
        }

        return item
    }
    override fun toString(): String {
        if (isEmpty()) return "[]"
        val tmp: MutableList<T> = mutableListOf()
        var current = front
        do {
            tmp.add(current!!.item)
            current = current.next
        } while (current != front)
        return tmp.toString()
    }
}

fun main() {
    val q = LinkedQueue<Int>()
    q.enqueue(1)
    q.enqueue(2)
    q.dequeue()
    q.enqueue(3)
    q.enqueue(4)
    q.enqueue(5)

    println(q)

    q.dequeue()

    println(q)
    q.dequeue()
    q.dequeue()
    q.dequeue()

    q.enqueue(6)
    println(q)
}

/**
 * How do I run this file?
 * 
 * Step 1: Compile
 * 
 * kotlinc QueueInt.kt LinkedQ.kt
 * 
 * Step 2: Run
 * 
 * /opt/homebrew/bin/kotlin LinkedQKt.class
 */