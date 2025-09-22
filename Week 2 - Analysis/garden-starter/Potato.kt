import Plant

class Potato: Plant {
     /**
     * Returns the name of the plant.
     */
    var water: Int = 0
    override fun getName(): String{
        return "Potato"
    }

    /**
     * Waters the plant with unitsWater water.
     */
    override fun waterPlant(unitsWater: Int){
        water += unitsWater
    }
    

    /**
     * Ages the plant by a day.  This could change the
     * plant's water level or other characteristics.
     */
    override fun elapseDay(){
        val evaporation: Int = 1
        water -= evaporation
    }

    /**
     * Returns a string describing the state of the plant.  E.g., might say
     * "overly dry" or "abundantly happy".  State of the plant may vary with
     * water level or other characteristics.
     */
    override fun getStatus(): String{
        if (water <= 2) {
            return "Potato thirsty"
        } else if (water <= 5){
            return "Potato happy"
        } else {
            return "Potato over water"
        }
    }

    /**
     * Returns the current water level for the plant.  Each plant has a water
     * level indicating whether it is dry, water-logged, or somewhere in between.
     */
    override fun getWaterLevel(): Int{
            return water
    }
}