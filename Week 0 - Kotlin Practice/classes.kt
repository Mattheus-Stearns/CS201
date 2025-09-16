fun main () {

	val box = Box<Int>()
	box.put(4)
	println(box.retrieve())

	val boolBox = Box<Boolean>()
	boolBox.put(true)
	boolBox.isEmpty()
	println(boolBox.retrieve())
}

// What's special about this box is that it is able to store the type of value put into it!
class Box<T> {
	var content: T? = null

	fun put(content: T?) {
		this.content = content
	}

	fun retrieve(): T? {
		return content
	}

	fun isEmpty(): Boolean {
		return content == null
	}
}