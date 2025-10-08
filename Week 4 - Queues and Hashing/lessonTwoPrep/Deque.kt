// Kotlin version of Deque code from
// https://www.geeksforgeeks.org/implementation-deque-using-doubly-linked-list/
// Ported by Dave Musicant

class Deque {

    // Node of a doubly linked list
    private data class Node(
        var data: Int,
        var prev: Node? = null,
        var next: Node? = null,
    )

    private var front: Node? = null
    private var rear: Node? = null
    var size = 0

    // Function to check whether deque is empty or not
    fun isEmpty(): Boolean {
        return (front == null)
    }

    // Function to insert an element at the front end
    fun insertFront(data: Int) {
        val newNode = Node(data)

        // If deque is empty
        if (front == null) {
            front = newNode
            rear = newNode
        }
        // Inserts node at the front end
        else {
            newNode.next = front
            front!!.prev = newNode
            front = newNode
        }

        // Increments count of elements by 1
        size++
    }

    // Function to insert an element at the rear end
    fun insertRear(data: Int) {
        val newNode = Node(data)

        // If deque is empty
        if (rear == null) {
            front = newNode
            rear = newNode
        }
        // Inserts node at the rear end
        else {
            newNode.prev = rear
            rear!!.next = newNode
            rear = newNode
        }

        size++
    }

    // Function to delete the element from the front end
    fun deleteFront() {

        // If deque is empty then 'Underflow' condition
        if (isEmpty()) {
            throw RuntimeException("Underflow")
        }

        // Deletes the node from the front end and makes the adjustment in the
        // links
        else {
            front = front!!.next

            // If only one element was present
            if (front == null) {
                rear = null
            } else {
                front!!.prev = null
            }

            // Decrements count of elements by 1
            size--;
        }
    }

    // Function to delete the element
    // from the rear end
    fun deleteRear() {

        // If deque is empty then 'Underflow' condition
        if (isEmpty()) {
            throw RuntimeException("Underflow")
        }

        // Deletes the node from the rear end and makes the adjustment in the
        // links
        else {
            rear = rear!!.prev

            // If only one element was present
            if (rear == null) {
                front = null
            } else {
                rear!!.next = null
            }

            // Decrements count of elements by 1
            size--
        }
    }

    // Function to return the element at the front end
    fun getFront(): Int {
        // If deque is empty, then error
        if (isEmpty()) {
            throw RuntimeException("Empty")
        } else {
            return front!!.data
        }
    }

    // Function to return the element at the rear end
    fun getRear(): Int {
        // If deque is empty, then error
        if (isEmpty()) {
            throw RuntimeException("Empty")
        }

        return rear!!.data
    }

    // Function to delete all the elements from Deque
    fun erase() {
        rear = null
        while (front != null) {
            front = front!!.next
        }
        size = 0
    }
}

// Driver program to test above
fun main() {
    val dq = Deque()
    println("Insert element '5' at rear end")
    dq.insertRear(5)

    println("Insert element '10' at rear end")
    dq.insertRear(10)
    println("Rear end element: ${dq.getRear()}")
    dq.deleteRear()
    println("After deleting rear element new rear is ${dq.getRear()}")
    println("Inserting element '15' at front end")
    dq.insertFront(15)
    println("Front end element: ${dq.getFront()}")

    println("Number of elements in Deque: ${dq.size}")
    dq.deleteFront()

    println("After deleting front element new front is: ${dq.getFront()}")
}
