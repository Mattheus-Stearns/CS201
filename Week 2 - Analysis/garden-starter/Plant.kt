/**
 * Interface for a Plant.  Specifies the behaviors that all plants
 * must implement.
 * 
 * Author: Tanya Amert (based on Java code from Anna Rafferty)
 */
interface Plant {

    /**
     * Returns the name of the plant.
     */
    fun getName(): String

    /**
     * Waters the plant with unitsWater water.
     */
    fun waterPlant(unitsWater: Int)

    /**
     * Ages the plant by a day.  This could change the
     * plant's water level or other characteristics.
     */
    fun elapseDay()

    /**
     * Returns a string describing the state of the plant.  E.g., might say
     * "overly dry" or "abundantly happy".  State of the plant may vary with
     * water level or other characteristics.
     */
    fun getStatus(): String

    /**
     * Returns the current water level for the plant.  Each plant has a water
     * level indicating whether it is dry, water-logged, or somewhere in between.
     */
    fun getWaterLevel(): Int
}