 interface Queue<T> {
     fun isEmpty(): Boolean
     fun enqueue(item: T)
     fun dequeue(): T?
     override fun toString(): String
 }