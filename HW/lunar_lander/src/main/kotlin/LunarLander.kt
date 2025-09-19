/*
 * Author: Mattheus Stearns
 * 
 * Bibliography: 
 * 
 *    - https://kotlinlang.org/docs/home.html?s=full
 *    - https://discuss.kotlinlang.org/t/multiple-return-types-from-function/664
 * 
 * Collaboration statement: N/A
 */

import java.io.File

// Initializing a class with instance variables as my constructor variables
class LunarLander (var altitude: Int = 1000, var velocity: Int = 40, var fuel: Int = 25) {
    
    // This creates a mutable list of log notes that is written to Log.txt
    private val logMessages: MutableList<String> = mutableListOf()

    init {
        logMessages.add("Initial Altitude: ${altitude}")
        logMessages.add("Initial Velocity: ${velocity}")
        logMessages.add("Initial Fuel: ${fuel}")
    }

    // This data class is so that I can easily return multiple values and connect it to the player
    data class LanderInfo(val altitude: Int, val velocity: Int, val fuel: Int)

    // A Function that allows the user to burn their fuel
    fun burn (fuelRequested: Int) : LanderInfo {

        logMessages.add("Requested Fuel Burn: ${fuelRequested}")
        var fuelBurnt: Int = fuelRequested

        // Just preventing the user from burning more fuel than they have
        if (fuelBurnt > fuel) {
            fuelBurnt = fuel
        }

        fuel -= fuelBurnt
        logMessages.add("Resultant Fuel: ${fuel}")

        val fuelImpulse: Int = 4
        velocity -= (fuelImpulse * fuelBurnt)
        logMessages.add("Resultant Velocity after Fuel burn: ${velocity}")

        val gravityEffect: Int = 2
        velocity += gravityEffect
        logMessages.add("Resultant Velocity after gravity's effect: ${velocity}")

        altitude -= velocity
        logMessages.add("Resultant Altitude: ${altitude}")

        // This is for logging purposes so that I can easier pass the tests, and also just understand what is going on under the hood
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

        return LanderInfo(altitude, velocity, fuel)
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
    println("Welcome to Lunar Lander!™\n")
    // Establishing number of players
    println("How many players wish to play? (1 | 2)")

    // TODO: Think about this in more detail
    val gameType: Int = readln().toInt()
    
    if (!(gameType == 1 || gameType == 2)) {
        return
    }
    
    // Initializes a player1 object
    val player1 = LunarLander()
    // Initializes a player2 object regardless of need (to temporarily avoid the code fritzing out)
    val player2 = LunarLander()


    var userInput: Int

    // I decided to throw the whole loops in a single if-statement, I could have done it differently but then I would constantly be running if-statements
    if (gameType == 1) {
        game@ while (player1.status() == "inflight" ) {
            
            println()
            println("   Alt = ${player1.altitude} Vel = ${player1.velocity} Fuel = ${player1.fuel}")
            println("   How much fuel do you wish to burn this round? ")
            print("   ")

            userInput = readln().toInt()
            if (userInput == -1) break@game
            
            player1.burn(userInput)

        }
    } else if (gameType == 2) {
        game@ while (player1.status() == "inflight" && player2.status() == "inflight") {
        
            println("")
            println("   It's player 1's turn now!")
            println("   Alt = ${player1.altitude} Vel = ${player1.velocity} Fuel = ${player1.fuel}")
            println("   How much fuel do you wish to burn this round? ")
            print("   ")

            userInput = readln().toInt()
            if (userInput == -1) break@game
            
            player1.burn(userInput)

            println("")
            println("   It's player 2's turn now!")
            println("   Alt = ${player2.altitude} Vel = ${player2.velocity} Fuel = ${player2.fuel}")
            println("   How much fuel do you wish to burn this round? ")
            print("   ")
            
            userInput = readln().toInt()
            if (userInput == -1) break@game
            
            player2.burn(userInput)

        }
    } else {
        return
    }
    
    println("")
    println("   Player 1's Results: Alt = ${player1.altitude} Vel = ${player1.velocity} Fuel = ${player1.fuel}")
    if (gameType == 2) {
        println("   Player 2's Results: Alt = ${player2.altitude} Vel = ${player2.velocity} Fuel = ${player2.fuel}")
    }
    
    if (player1.status() == "landed" && player2.status() == "landed") {
        println("   Congratulations! Both players landed successfully!\n\nThis game™ was made by Mattheus-Stearns.")
    }

    when {
        player1.status() == "crashed" -> println("  Oh no, player 1 crashed!")
        player1.status() == "landed" -> println("   Wow! Player 1 landed successfully!")
        player2.status() == "crashed" -> println("  Oh no, player 2 crashed!")
        player2.status() == "landed" -> println("   Wow! Player 2 landed successfully!")
    }
    
}

// Reflection:
// It would have been nice if the mvn library worked with me, I think I spent like 2 hours tinkering with it
// As soon as I got to running the kotlin natively for Parts B & C I started realizing that while I have some
// knowledge about how kotlin works, it doesnt come to me as quickly as other languages so I had to spend like 
// 3 hours pouring through documentation and visited a kotlin forum to find kotlin's version of what functions 
// I would have instantly been able to use in python, but I think that it will just come with practice, which I
// intend to do. 
// As of writing this reflection I spent 8 hours on this assigment, but I need to optimize some parts to satisfy
// expectation #8.
