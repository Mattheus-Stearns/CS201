class Pet(var name: String, var age: Int, val kind: String) {

    fun rename(newName: String) {
        name = newName
    }

    fun updateAge() {
        age += 1
    }

}

fun main() {
    // Make a few Pet objects
    val lulu: Pet = Pet("Lulu", 13, "cat")
    val hobbes: Pet = Pet("Hobbes", 12, "cat")
    val cheddar: Pet = Pet("Cheddar", 1, "dog")
    val mal: Pet = Pet("Marshmallow", 1, "dog")

    // Print some info
    println("Hobbes is currently ${hobbes.age} years old, and Lulu is ${lulu.age}.")
    
    print("The dogs are: ")
    for (pet: Pet in listOf(lulu, hobbes, cheddar, mal)) {
        if (pet.kind == "dog") {
            print("${pet.name} ")
        }
    }
    println()

    // Hobbes has a birthday next Tuesday!
    hobbes.updateAge()
    println("On Tuesday Hobbes will be older!  (${hobbes.age} years)")
}