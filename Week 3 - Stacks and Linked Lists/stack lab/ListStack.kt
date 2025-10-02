import Stack
class ListStack() : Stack{
    var list: MutableList<Int> = mutableListOf()
    override fun push(item: Int) {
        list.add(item)
    }
    override fun pop(): Int? {
        val a: Int = list[list.size - 1]
        list.remove(a)
        return a
    }
    override fun peek(): Int? {
        return list[list.size - 1]
    }
    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }
    override fun toString(): String {
        var b: String = ""
        for (i in list.size - 1 downTo 0) {
            b += list[i] 
            b += "\n"
        }
        return b
    }
 }


fun main() {
    val s = ListStack()
    s.push(2025)
    s.push(4)
    s.push(16)

    println("Elements present in stack: ")
    println(s) //This implicitly calls your toString

    println("" + s.pop() + " popped from stack")
    println("Top element is: " + s.peek())

    println("Elements present in stack: ")
    println(s)
}