/*
 * Author: Mattheus Stearns
 * 
 * Bibliography: https://kotlinlang.org/docs/home.html?s=full
 * 
 * Collaboration statement: N/A
 */

import java.io.File

// Initializing a class with instance variables as my constructor variables
class LunarLander (var altitude: Int = 1000, var velocity: Int = 40, var fuel: Int = 25) {
    
    private val logMessages: MutableList<String> = mutableListOf()

    init {
        logMessages.add("Initial Altitude: ${altitude}")
        logMessages.add("Initial Velocity: ${velocity}")
        logMessages.add("Initial Fuel: ${fuel}")
    }



    // A Function that allows the user to burn their fuel
    fun burn (fuelRequested: Int) {

        logMessages.add("Requested Fuel Burn: ${fuelRequested}")
        var fuelBurnt: Int = fuelRequested

        // Just preventing the user from burning more fuel than they have
        if (fuelBurnt > fuel) {
            fuelBurnt = fuel
        }

        fuel -= fuelBurnt
        logMessages.add("Resultant Fuel: ${fuel}")

        velocity -= (4 * fuelBurnt)
        logMessages.add("Resultant Velocity after Fuel burn: ${velocity}")

        velocity += 2
        logMessages.add("Resultant Velocity after gravity's effect: ${velocity}")

        altitude -= velocity
        logMessages.add("Resultant Altitude: ${altitude}")

        // This is for logging purposes so that I can easier pass the tests
        val fileName = "Log.txt"
        
        try {
            File(fileName).writeText("=========================\n\n     Log     Message\n\n=========================\n\n")
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }

        for (message in logMessages) {
            try {
            File(fileName).appendText(message + "\n")
            } catch (e: Exception) {
                println("An error occurred: ${e.message}")
            }
        }

        
    }

    // A Function that checks on the status of the spaceship
    fun status() : String {
        
        when {
            altitude > 0 -> return "inflight"
            velocity >= 5 -> return "crashed"
            else -> return "landed"
        }

    }


}


fun main() {

}

// Reflection: TODO