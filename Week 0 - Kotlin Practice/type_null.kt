fun main(){
	var score: Int

	var car: Car? = null

	car = Car("Mercedes-Benz") // This is assuming that you create an object for it

	// with a nullable type, you can't use something like car.drive(), instead:
	car?.drive()

	// or

	var realCar: Car = ?: Car("Porsche") // this ?: is the safe-call operator

	car!!.drive() // of course the not-safe operator exists as well
}