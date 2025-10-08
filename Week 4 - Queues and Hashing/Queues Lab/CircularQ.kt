import Queue

class CircularQueue<T>(private val capacity: Int = 5) : Queue<T> {
    var items: MutableList<T?> = MutableList(capacity) { null }
    var front: Int = -1
    var rear: Int = -1
    
    override fun isEmpty(): Boolean {
        return if (front == -1) { true } else { false }
    }

    fun isFull(): Boolean {
        return if ((rear + 1) % items.size == front) { true } else { false }
    }

    override fun enqueue(item: T) {
        if (isFull()) {
            val tmp: MutableList<T> = mutableListOf()
            tmp.addAll(items.subList(front, items.size))
            tmp.addAll(items.subList(0, rear))
            tmp.add(item)
            front = 0
            rear = tmp.size - 1
            items = tmp
        }
        if (isEmpty()) {
            front = 0
            rear = 0
            items[rear] = item
        } else {
            rear = (rear + 1) % items.size
            items[rear] = item
        }
    }
    override fun dequeue(): T? {
        if (isEmpty()) { return null }

        val item = items[front]
        items[front] = null

        if (front == rear) {
            front = -1
            rear = -1
        } else {
            front = (front + 1) % items.size
        }

        return item
    }
    override fun toString(): String {
        if (isEmpty()) return "[]"
        val tmp: MutableList<T> = mutableListOf()
        var i = front
        while (true) {
            val item = items[i]
            if (item != null) tmp.add(item)
            if (i == rear) break
            i = (i + 1) % items.size
        }
        return tmp.toString()
    }
}

fun main() {
    val q = CircularQueue<Int>()
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
 * kotlinc QueueInt.kt CircularQ.kt
 * 
 * Step 2: Run
 * 
 * /opt/homebrew/bin/kotlin CircularQKt.class
 */