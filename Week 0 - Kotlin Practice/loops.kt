fun main() {

	// For

	for (i in 1..3) {
		println(i)
	}

	// EX: for (item in collection) println(item)

	// While

	var x = 10
	while (x > 0) {
		x--
		println(x)
	}

	x = 10
	do {
		x--
		println(x)
	} while (x > 0)

	x = 1
	while (x > 0) {
		x++
		println(x)
		if (x > 50) { // Beware of eternal loops!
			break
		}
	}
	println("The light at the end of the tunnel!")
}