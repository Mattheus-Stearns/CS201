
/**
 * Garden is a simulation of an actual garden that instantiates plants, waters them,
 * and displays them.  (Add details if you change things about the class.)
 * Look at the lab description for details about what to do with the Garden class.
 */
class Garden {
    // TODO: fill in this class as an extension
}

fun main() {
    val Plants: MutableList<Plant> = mutableListOf(Carrot(), Potato(), Tomato(), Carrot(), Potato(), Tomato())

    println("These are your planted plants in your garden!")
    for (plant in Plants) {
        // Potatos and Tomatos are greedy and need water when planted:
        if (plant.getName() == "Tomato" || plant.getName() == "Potato") {
            plant.waterPlant(3)
        }
        println("${plant.getName()}: ${plant.getStatus()} (${plant.getWaterLevel()})")
    }

    // Okay, let's simulate a week then...
    val Week: List<String> = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    println("A week passes...")
    for (day in Week) {
        println("Today is ${day}!")
        for (plant in Plants) {
            when {
                day == "Monday" -> {
                    plant.waterPlant(1)
                    plant.elapseDay()
                }
                day == "Tuesday" -> {
                    plant.elapseDay()
                }
                day == "Wednesday" -> {
                    plant.waterPlant(3)
                    plant.elapseDay()
                }
                day == "Thursday" -> {
                    plant.waterPlant(1)
                    plant.elapseDay()
                }
                day == "Friday" -> {
                    plant.elapseDay()
                }
                day == "Saturday" -> {
                    plant.waterPlant(2)
                    plant.elapseDay()
                }
                day == "Sunday" -> {
                    plant.waterPlant(1)
                    plant.elapseDay()
                }
            }
            println("${plant.getName()}: ${plant.getStatus()} (${plant.getWaterLevel()})")
        }
    }
    
    println("How have your plants fared in your garden!?")
    for (plant in Plants) {
        // Potatos and Tomatos are greedy and need water when planted:
        if (plant.getName() == "Tomato" || plant.getName() == "Potato") {
            plant.waterPlant(3)
        }
        println("${plant.getName()}: ${plant.getStatus()} (${plant.getWaterLevel()})")
    }
}
/**
 * How do I run this file?
 * 
 * Step 1: Compile
 * 
 * kotlinc -classpath . Plant.kt Potato.kt Tomato.kt Garden.kt -d out
 * 
 * Step 2: Run
 * 
 * /opt/homebrew/bin/kotlin -classpath out:. GardenKt
 */