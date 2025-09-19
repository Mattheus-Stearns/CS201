/*
*
*   Bibliography:
*
*   - https://www.rapidtables.com/convert/
*   - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-mutable-list/
*   - https://kotlinlang.org/docs/generics.html
*
*/


class MyMap {
    // TODO: initiailize any necessary data structures
    var Map: MutableList<Pair<String, Long>> = mutableListOf(Pair("Key", 371001685349)) // 508440638821 -> 0101011001100001011011000111010101100101 -> "Value"
    
    // TODO: add methods

    fun add(key: String, valuePair: Long) {
        Map.add(Pair(key, valuePair))
    }

    fun getSize() : Int {
        return (Map.size - 1)
    }
    
}



fun main() {
    val map: MyMap = MyMap()

    map.add("blue", 314)
    map.add("yellow", 271)

    println("There are ${map.getSize()} pairs in the map.")
}
