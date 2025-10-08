class SinglyLinkedList<T> {

    // This "private" "data class" is visible only within the SinglyLinkedList
    // class, and contains only data with no additional functions (besides a
    // few that Kotlin provides, like for printing it)
    private data class Node<T>(
        var item: T,
        var next: Node<T>?)

    // We'll maintain just a reference to the head of the linked list
    // (which may be null, in the case the list is empty)
    private var head: Node<T>? = null

    fun insertAtBeginning(item: T) {
        // The current head will be the next node after this new node
        val newNode = Node(item, head)

        // Put this new node at the head, inserting it
        head = newNode
    }

    fun insertAtEnd(item: T) {
        // The new node will be at the end, so it won't have anywhere
        // to point (hence, its next is null)
        val newNode = Node(item, null)

        // If the list is empty, we just make the new node the head
        if (head == null) {
            head = newNode
            return
        }

        // If not, we need to step through the list until we find
        // a node with its next set to null (that's the end)
        var current: Node<T>? = head
        while (current!!.next != null) { // at first current isn't null
            current = current.next
        }

        // We found the end!  Put the new node just after it
        current.next = newNode
    }

    override fun toString(): String {

        var returnString: String = ""

        if (head == null) {
            return ""
        }

        var current: Node<T>? = head
        while (current != null) {
            returnString += current.item.toString()
            returnString += ", "
            current = current.next
        }
        return returnString
    }

    fun length(): Int {
        var countList: Int = 0

        if (head == null) {
            return 0
        }

        var current: Node<T>? = head
        while (current != null) {
            countList++
            current = current.next
        }
        return countList
    }

    fun search(target: T): Boolean {
        
        if (head == null) {
            return false
        }

        var current: Node<T>? = head
        while (current != null) {
            if (current.item == target) { return true }
            current = current.next
        }
        return false
    }

    fun removeFirstNode() {
        if (head == null) {
            return 
        }

        head = head?.next
        return
    }

    fun removeLastNode() {
        if (head == null) {
            return 
        }

        if (head?.next == null) {
            head = null
            return
        }

        var current: Node<T>? = head
        while (current?.next?.next != null) {
            current = current.next
        }
        current?.next = null
        return
    }

    fun insertAtPosition(position: Int, item: T) {
        if (head == null) {
            return 
        }

        val newNode = Node(item, null)

        if (position == 0) {
            newNode.next = head
            head = newNode
            return
        }

        var current: Node<T>? = head
        var index = 0

        while (current != null && index < position - 1) {
            current = current.next
            index++
        }

        if (current == null) {
            throw IndexOutOfBoundsException("Position $position is out of bounds")
        }

        newNode.next = current.next
        current.next = newNode

    }

    fun deleteAtPosition(position: Int) {
        if (head == null) {
            return 
        }

        if (position == 0) {
            head = head?.next
            return
        }

        var current: Node<T>? = head
        var index = 0

        while (current != null && index < position - 1) {
            current = current.next
            index++
        }

        if (current == null || current.next == null) {
            throw IndexOutOfBoundsException("Position $position is out of bounds")
        }

        current.next = current.next?.next
    }

    fun deleteSubList(start: Int, end: Int) {
        if (head == null) {
            return 
        }

        if (start > end || start < 0) {
            throw IndexOutOfBoundsException()
        }

        if (start == 0) {
            var current = head
            var index = 0
            while (current != null && index <= end) {
                current = current.next
                index++
            }
            head = current
            return
        }

        var current: Node<T>? = head
        var index = 0
        while (current != null && index < start - 1) {
            current = current.next
            index++
        }

        if (current == null || current.next == null) {
            throw IndexOutOfBoundsException()
        }

        var endNode = current.next
        var endIndex = start
        while (endNode != null && endIndex <= end) {
            endNode = endNode.next
            endIndex++
        }

        current.next = endNode
    }
}

fun main() {
    val list = SinglyLinkedList<String>()

    // Try adding some things
    list.insertAtBeginning("apple")
    list.insertAtBeginning("banana")
    list.insertAtBeginning("canteloupe")

    // Now try searching
    println(list.search("apple"))
    println(list.search("banana"))
    println(list.search("canteloupe"))
    println(list.search("durian"))

    // Let's print some things
    println(list)
    println(list.length())

    // What if we put something at the end?
    list.insertAtEnd("starfruit")
    println(list)

    list.removeFirstNode()
    println(list)

    list.removeLastNode()
    println(list)

    list.insertAtPosition(1, "peach")
    println(list)

    list.deleteAtPosition(1)
    println(list)

    list.insertAtBeginning("kiwi")
    list.insertAtBeginning("lychee")
    list.insertAtBeginning("mango")
    println(list)

    list.deleteSubList(1, 3)
    println(list)
}