/**
 *  Bibliography: 
 *      - https://www.geeksforgeeks.org/dsa/doubly-linked-list/ 
 *      - https://kotlinlang.org/docs/visibility-modifiers.html#class-members/
 */

open class DoublyLinkedList<T> {

    // We'll keep this class a secret to ourselves; no one using
    // a doubly linked list has to know about how we've stored the
    // data and pointers.
    //
    // Food for thought: why do we need next and prev, in particular,
    // to be declared with var instead of val?
    protected data class Node<T>(
        var item: T,
        var next: Node<T>?,
        var prev: Node<T>?)

    // The list knows which nodes, if any, are at its
    // head and tail
    protected var head: Node<T>? = null
    protected var tail: Node<T>? = null

    // Let's make it easy to print
    override fun toString(): String {
        var result: String = ""
        var current: Node<T>? = head
        while (current != null) {
            result = result + current.item + " "
            current = current.next
            // ponder: why don't we need !! in the above two lines?
        }
        return result.trim() // take off that extra " "
    }

    // We should, at the least, be able to insert somewhere
    open fun insertAtBeginning(item: T) { //O(1)
        // Our new node has the current head as its next (which may be null)
        // and no prev, because it'll be at the beginning
        val newNode = Node(item, head, null)

        // Wire up the previous head to have our new node as its prev
        val oldHead: Node<T>? = head
        if (oldHead != null) {
            oldHead.prev = newNode
        }

        // Now see if this new node is also the tail (ponder: it what
        // situation is this true?)
        if (tail == null) {
            tail = newNode
        }

        // Now properly update the head pointer for the list
        head = newNode
    }

    open fun length(): Int {
        var count: Int = 0
        var current: Node<T>? = tail 
        while (current != null) {
            count++
            current = current.prev
        }
        return count
    }

     // TODO: insert at the tail
    open fun insertAtEnd(item: T) {  // insertAtEnd has O(1)
        var newNode: Node<T>? = Node(item, null, tail)
        
        val oldTail: Node<T>? = tail
        if (oldTail != null) {
            oldTail.next = newNode
        }

        if (head == null) {
            head = newNode
        }

        tail = newNode
    }

    // TODO: find an element in the list
    open fun search(target: T): Boolean {
        var current: Node<T>? = head
        while (current != null) {
            if (current.item == target) { return true }
            current = current.next
        }
        return false
    }
    
    // TODO: look up the node at a given position
    private fun findNodeAtPosition(position: Int): Node<T>? {
        var index: Int = 0
        var current: Node<T>? = head
        while (index < position) {
            index++
            current = current!!.next
        }
        return current
    }

    // TODO: insert at a given position
    open fun insertAtPosition(position: Int, item: T) {
        var current: Node<T>? = findNodeAtPosition(position)
        var newNode: Node<T>? = Node(item, current, current!!.prev)
        current?.prev?.next = newNode
        current?.prev = newNode

        if (current == head) {
            head = newNode
        }
    }

   // TODO: swap the given node with its next neighbor
    // (make sure to update head/tail if appropriate)
    // NOTE: assumes curr is not the tail
    private fun swapWithNeighbor(curr: Node<T>) {
        val neighbor = curr.next
        val beforeCurr = curr.prev
        val afterNeighbor = neighbor?.next
        curr.prev = neighbor
        beforeCurr?.next = neighbor
        neighbor?.prev = beforeCurr
        curr.next = afterNeighbor
        afterNeighbor?.prev = curr
        neighbor?.next = curr

        if (curr == head) {
            head = neighbor
        }
        if (neighbor == tail) {
            tail = curr
        }
    }

    // Swap the node at the given position with its next neighbor
    // (fails if position is out of bounds or corresponds to the tail)
    fun swapPositionWithNeighbor(position: Int) {
        // Find the node, if it exists
        val current: Node<T>? = findNodeAtPosition(position)

        // If the node to swap is the tail, that's a problem
        if (current == tail) {
            throw RuntimeException("Cannot swap the tail with its next!")
        }

        // Swap the node with its next neighbor
        swapWithNeighbor(current!!)
    }

    protected fun headItem(): T? = head?.item
    protected fun tailItem(): T? = tail?.item
    protected fun removeHead() {
        head = head?.next
        if (head == null) {
            tail = null
        } else {
            head?.prev = null
        }
    }
    protected fun removeTail() {
        tail = tail?.prev
        if (tail == null) {
            head = null
        } else {
            tail?.next = null
        }
    }
}

/**
 * Figured that I might as well just avoid ctrl+c & ctrl+v and instead inherit most methods from the Doubly Linked List class 
 */
class linkedDeque<T> : DoublyLinkedList<T>() {

    fun addFirst(item: T) = insertAtBeginning(item)
    fun addLast(item: T) = insertAtEnd(item)

    fun removeFirst(): T? {
        val value = peekFirst()
        removeHead()
        return value
    }

    fun removeLast(): T? {
        val value = peekLast()
        removeTail()
        return value
    }

    fun peekFirst(): T? = headItem()
    fun peekLast(): T? = tailItem()
    

}

fun main() {
    // First, test basic inserts and prints
    val mylist = DoublyLinkedList<String>()

    mylist.insertAtBeginning("Lulu")
    mylist.insertAtBeginning("Hobbes")
    mylist.insertAtBeginning("Cheddar")

    println()
    println("List with 3 elements: $mylist")

    // Now try checking the length and inserting at the end
    println("Length: ${mylist.length()}")
    mylist.insertAtEnd("Marshmallow")
    println()
    println("List with 4 elements: $mylist")
    println("Length: ${mylist.length()}")

    // What if we look for an element?
    println()
    println("List contains \"Lulu\": ${mylist.search("Lulu")}")
    println("List contains \"LULU\": ${mylist.search("LULU")}")


    // Can we insert just anywhere?
    mylist.insertAtPosition(2, "Sadie")
    mylist.insertAtPosition(1, "Therese")
    println()
    println("List with 6 elements: $mylist")

    // What if we swap some nodes?
    mylist.swapPositionWithNeighbor(1)
    println()
    println("List after swapping node at position=1 with its next: $mylist")
    mylist.swapPositionWithNeighbor(4)
    mylist.insertAtEnd("Milo")
    println("List after swapping node at position=4 with its next and adding to tail: $mylist")
}

