// Kotlin version of linked list code from
// https://www.geeksforgeeks.org/singly-linked-list-tutorial/
// Ported by Dave Musicant

class SinglyLinkedList<T> {

    private data class Node<T>(
        var item: T,
        var next: Node<T>?)

    private var head: Node<T>? = null

    override fun toString(): String {
        var result = ""
        var current = head
        while (current != null) {
            result = result + current.item + " "
            current = current.next
        }
        return result.trim()
    }

    fun search(target: T): Boolean {
        var current = head
        while (current != null) {
            if (current.item == target) {
                return true
            }
            current = current.next
        }
        return false
    }

    fun length(): Int {
        var length = 0
        var current = head
        while (current != null) {
            length++
            current = current.next
        }
        return length
    }

    fun insertAtBeginning(item: T) {
        val newNode = Node(item, null)
        newNode.next = head
        head = newNode
    }

    fun insertAtEnd(item: T) {
        val newNode = Node(item, null)

        // If the list is empty, make the new node the head
        if (head == null) {
            head = newNode
            return
        }

        var current = head
        // current cannot be null because just checked, and are checking through loop
        while (current!!.next != null) {
            current = current.next
        }
        current.next = newNode
    }

    fun insertAtPosition(position: Int, item: T) {
        if (position < 0) {
            throw Exception("Invalid position!")
        }

        else if (position == 0) {
            insertAtBeginning(item)
        }

        else {
            var current = head
            for (skips in 0..<position-1) {
                // current cannot be null because just checked head
                // first time, and then checked immediately afterwards
                current = current!!.next
                if (current == null) {
                    throw Exception("List not long enough!")
                }
            }
            // current cannot be null because just checked
            val temp = Node(item, current!!.next)
            current.next = temp
        }
    }

    fun removeFirstNode() {
        val current = head
        if (current == null) {
            throw Exception("List is empty!")
        } else {
            head = current.next
        }
    }

    fun removeLastNode() {
        var current = head

        // If the list is empty, do nothing
        if (current == null) {
            return
        }

        // If the list has only one node, delete it
        if (current.next == null) {
            head = null
            return
        }

        // Have already verified there are at least two nodes
        while (current!!.next!!.next != null) {
            current = current.next
        }

        // Remove the last node
        current.next = null
    }


    fun deleteAtPosition(position: Int) {
        var current = head

        // If the list is empty or the position is invalid
        if (current == null || position < 0) {
            throw Exception("Invalid position!")
        }

        // If the head needs to be deleted
        if (position == 0) {
            head = current.next
        }

        // Traverse to the node before the position to be deleted
        for (skips in 0..<position-1) {
            // current cannot be null because just checked head
            // first time, and then checked immediately afterwards
            current = current!!.next
            if (current == null) {
                throw Exception("List not long enough!")
            }
        }

        // Verify position was not too large
        if (current!!.next == null) {
            throw Exception("List not long enough!")
        }

        current.next = current.next!!.next
    }
}


fun main() {
    val list = SinglyLinkedList<Int>()
    list.insertAtBeginning(1)
    list.insertAtBeginning(2)
    list.insertAtBeginning(3)
    println(list.search(1))
    println(list.search(2))
    println(list.search(3))
    println(list.search(4))
    println(list)
    println(list.length())
    list.insertAtEnd(8)
    println(list)
    list.insertAtPosition(0, 10)
    list.insertAtPosition(1, 11)
    list.insertAtPosition(2, 12)
    list.insertAtPosition(4, 13)
    list.insertAtPosition(8, 14)
    println(list)
    list.removeFirstNode()
    println(list)
    list.removeLastNode()
    println(list)
    list.deleteAtPosition(0)
    println(list)
    list.deleteAtPosition(4)
    println(list)
    list.deleteAtPosition(4)
    println(list)
}
