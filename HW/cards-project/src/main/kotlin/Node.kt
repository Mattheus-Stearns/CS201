data class Node(
    // Keep this as a val, don't change it to a var
    val value: Int,
    var previous: Node?,
    var next: Node?,
) {

    // If you don't override this to just print the item,
    // you'll end up recursively printing way too much.
    override fun toString(): String {
        return "Node(value=${value}, " +
            "uniqueid=${System.identityHashCode(this)}, " +
            "previous=${System.identityHashCode(previous)}, " +
            "next=${System.identityHashCode(next)})"
    }
}
