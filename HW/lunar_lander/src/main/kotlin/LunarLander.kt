/*
 * Author: Mattheus Stearns
 * 
 * Collaboration statement: TODO
 */

// Initializing a class with instance variables as my constructor variables
class LunarLander (var altitude: Int = 1000, var velocity: Int = 40, var fuel: Int = 25) {
    
    // A Function that allows the user to burn their fuel
    fun burn (fuelRequested: Int) {
        
        // Just preventing the user from burning more fuel than they have
        if (fuelRequested > fuel) {
            fuel = 0
        } else {
            fuel -= fuelRequested
        }

        velocity -= (4 * fuel)
        velocity += 2

        altitude -= velocity
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