fun main() {

	// Testing

	printCar(Car(2)) // should return "Coupes are awesome"
	printCar(Car(4)) // shouldn't return anything
	printCar(null) // shouldn't return anything

	// printCar2() should be returning the same outputs
	printCar2(Car(2))
	printCar2(Car(4))
	printCar2(null)

	// ... printCar3() should be returning the same outputs (but because the function is mutating the objects from the inside, will that really be the case???)
	printCar3(Car(2))
	printCar3(Car(4))
	printCar3(null)

	// who knows what will happen with printCar4()!?
	printCar4(Car(2))
	printCar4(Car(4))
	printCar4(null)

	// List is a general-purpose, generic container for storing an ordered collection of elements
	val places = listOf("Paris", "London", "Bucharest") // however listOf makes a read-only data structure
	// wheras mutableListOf() makes the list mutable
	val mutablePlaces = mutableListOf("Paris", "London", "Bucharest")

	// you can access the value of an element within a list like this:
	places[0] // Paris
	places[1] // London
	places[2] // Bucharest

	println(mutablePlaces)
	// Examples of insertion location affecting list performance
	mutablePlaces.add("Budapest") // O(1) time 
	println(mutablePlaces)

	mutablePlaces.add(0, "Kiev") // O(n) time
	println(mutablePlaces)

	// Example Map
	val scores = mutableMapOf("Eric" to 9, "Mark" to 12, "Wayne" to 1)
	// You can add a new entry to the Map with the following syntax:
	scores["Andrew"] = 0
	// This creates a new key-value pair in the map:
	// {Eric=9, Mark=12, Wayne=1, Andrew=0}

	payTaxes() // TODO() call
}

data class Car(var doors: Int)
// The let function helps with null-checks and creates a new local scope to safely perform operations
fun printCar(car: Car?) {
	val isCoupe = car?.let {
		(it.doors <= 2)
	}

	if (isCoupe == true) {
		println("Coupes are awesome")
	}
}

// run is similar to let, but its more focused on the target object
// yet they are both transformational functions -- which can return objects different from the object you call the function on
fun printCar2(car: Car?) {
	val isCoupe = car?.run {
		(this.doors <= 2)
	}

	if (isCoupe == true) {
		println("Coupes are awesome")
	}
}

// also returns the same car object, but that means that you can mutate the object and chain other calls to it
fun printCar3(car: Car?) {
	car?.also {
		it.doors = 4 // mutating the object within the function
	}.let {
		if (it?.doors != null && it. doors <= 2) {
			println("Coupes are awesome")
		}
	}
}

// apply is like the run of let, but for also instead. i.e. it uses a similar implementation to also but focuses on the target object
fun printCar4(car: Car?) {
	car?.apply {
		doors = 4
	}.let {
		if (it?.doors != null && it.doors <= 2) {
			println("Coupes are awesome")
		}
	}
}

// TODO() is a clever trick to prevent you from forgetting that you still have to write something
fun payTaxes(): Float = TODO("Waiting for Income to be large enough.")