fun main() {
	val a = 12
	val b = 7

	printMax(a, b)


}

fun max(a: Int, b: Int): Int {
	return if (a > b) a else b
}

fun printMax(c: Int, d: Int) {
	val maxValue = max(c, d)
	println(maxValue)
}