/**
 * In class lab for learning about hash code functions and collisions
 * Based on Java lab by Anna Rafferty
 * Translated to Kotlin and updated by Anya Vostinar
 */


class HashTable {
    var tableSize = 11
    //array of nulls to hold keys
    var keys: Array<String?> = arrayOfNulls<String>(tableSize)


    /**
     * A hashing function that uses the ASCII value of the first character of a string
     * @param s the string to hash
     * @return the hash code
     */
    fun hashCode1(s: String) : Int {
        return s[0].code
    }

    /**
     * A hashing function that uses the sum of the ASCII values of the characters in a string.
     * @param s the string to hash
     * @return the hash code
     */
    fun hashCode2(s: String) : Int {
        var sum: Int = 0
        for (i: Int in 0 until s.length) {
            sum += s[i].code
        }
        return sum
    }

    /**
     * A hashing function that uses the sum of the ASCII values of the characters in a string,
     * weighted by the position of the character in the string.
     * @param s the string to hash
     * @return the hash code
     */
    fun hashCode3(s: String) : Int {
       var sum: Int = 0
        for (i: Int in 0 until s.length) {
            sum += (i + 1) * s[i].code
        }
        return sum
    }

    fun compressToSize(hashCode: Int) : Int {
        var compressedValue = hashCode % tableSize
        if(compressedValue >= 0) {
            return compressedValue
        } else {
            return compressedValue+tableSize
            //Mod of a negative number is negative - 
            //map back to positive so in the range of 
            //[0,numberOfBuckets)
        }
    }

    fun insertKey(key : String, skipSize : Int = 1) {
        var insertPos: Int = compressToSize(hashCode1(key))
        var count: Int = 0
        while (keys[insertPos] != null) {
            insertPos = (insertPos + skipSize) % tableSize
            count++
            if (count >= tableSize) {
                println("Escaped insertKey loop: HashTable Full")
                return
            }
        }
        keys[insertPos] = key 
    }

    override fun toString() : String {
        var returnString = ""
        for(key in keys) {
            returnString += key + " "
        }
        return returnString
    }

}

fun main() {
    var test = HashTable()
    val testList: MutableList<String> = mutableListOf("ant", "tan", "mop", "tiger", "arctic", "mold")
    var testSkipSize: Int
    for (i: Int in 0 until testList.size) {
        testSkipSize = 1 + (test.hashCode1(testList[i]) % (test.tableSize - 1) )
        test.insertKey(testList[i], testSkipSize)
    }
    print("Insert Key: ")
    println(test)
    
}