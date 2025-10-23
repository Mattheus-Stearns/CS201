import kotlin.math.abs
import kotlin.random.Random

// Mattheus Stearns
// Collobration: N/A

class CuckooHashMap<K, V>(tableSize: Int) : CuckooSetup<K, V>(tableSize) {

    /**
     * Looks up a
     * @param key
     * and find the corresponding pair to
     * @return the value
     * and tries both tables, if neeeded, and
     * @return null if not found
     */
    fun get(key: K): V? {
        val hash1: Int = cuckooHashCode(0, key) % tables[0].size
        val hash2: Int = cuckooHashCode(1, key) % tables[1].size
        if (tables[0][hash1]?.key == key) {
            return tables[0][hash1]?.value
        } else if (tables[1][hash2]?.key == key) {
            return tables[1][hash2]?.value
        } else {
            return null
        }
    }

    /**
     * Adds a 
     * @param key
     * @param value pair to both tables, handling only if there is 'free' space in the first table
     * otherwise, calls the displacement function which handles the movement of values
     */
    fun set(key: K, value: V) {
        val index0 = cuckooHashCode(0, key) % tables[0].size
        if (tables[0][index0] == null) {
            tables[0][index0] = Entry(key, value)
            return
        }
        startDisplacement(key, value)
    }

    /**
     * Kicks out
     * @param key
     * @param value
     * pairs to try and hash them into the different table, flip flopping back and forth if there is a value already present in that table and starting the call all over again
     * but it limits itself to iterating 100 times, and then rehashes. In most cases of rehashing it was found that the displaced value was 'lost' so there are two functions
     * one that keeps track of displaced values and one that doesn't
     */
    private fun startDisplacement(key: K, value: V) {
        var currentTable = 0
        var currentKey = key
        var currentValue = value
        var count = 0
        val visitedKeys = mutableSetOf<K>()
        visitedKeys.add(key)
        do {
            val currentIndex = cuckooHashCode(currentTable, currentKey) % tables[currentTable].size
            val displacedEntry = tables[currentTable][currentIndex]
            if (displacedEntry != null && visitedKeys.contains(displacedEntry.key)) {
                rehashWithCurrentEntries(key, value, currentKey, currentValue, displacedEntry)
                return
            }
            tables[currentTable][currentIndex] = Entry(currentKey, currentValue)
            if (displacedEntry == null) {
                return
            }
            visitedKeys.add(displacedEntry.key)
            currentKey = displacedEntry.key
            currentValue = displacedEntry.value
            currentTable = (currentTable + 1) % 2
            count++
        } while (count < MAX_LOOP)
        
        rehash()
        set(key, value)
    }

    /**
     * keeps stock of a lot of different variables so they aren't lost in the rehashing process, adding them to a temp array:
     * @param originalKey the key the function set out to add
     * @param originalValue the value tied to the originalKey
     * @param currentKey the key that is currently being checked
     * @param currentValue the value tied to the currentKey
     * @param displacedEntry the entry that is currently being tried to be inserted into the current key-value pair
     * has to completely rehash all of the values anyways, so stores everything to a temp array while increasing the seed
     * then tries to add the original key-value pair that started this mess
     */
    private fun rehashWithCurrentEntries(originalKey: K, originalValue: V, currentKey: K, currentValue: V, displacedEntry: Entry<K, V>) {
        val allEntries = mutableSetOf<Entry<K, V>>()
        for (table in tables) {
            for (entry in table) {
                if (entry != null) {
                    allEntries.add(entry)
                }
            }
        }
        allEntries.add(Entry(currentKey, currentValue))
        allEntries.add(displacedEntry)
        var success = false
        while (!success) {
            tables = listOf(
                Array<Entry<K, V>?>(tableSize) { null },
                Array<Entry<K, V>?>(tableSize) { null }
            )
            seed++
            success = true
            for (entry in allEntries) {
                val idx0 = cuckooHashCode(0, entry.key) % tables[0].size
                val idx1 = cuckooHashCode(1, entry.key) % tables[1].size
                if (tables[0][idx0] == null) {
                    tables[0][idx0] = Entry(entry.key, entry.value)
                } else if (tables[1][idx1] == null) {
                    tables[1][idx1] = Entry(entry.key, entry.value)
                } else {
                    success = false
                    break
                }
            }
        }
        set(originalKey, originalValue)
    }

    /**
     * the ideal rehash!
     * after iterating 100 times, realizes that the values are never going to be inserted with a table of this size
     * so it just doubles the table size, and copies all of the values over from the old table into the new one
     * and during that copying rehashes every value with the increased seed
     */
    fun rehash() {
        val allEntries = mutableSetOf<Entry<K, V>>()
        for (table in tables) {
            for (entry in table) {
                if (entry != null) {
                    allEntries.add(entry)
                }
            }
        }
        tableSize *= 2
        tables = listOf(
            Array<Entry<K, V>?>(tableSize) { null },
            Array<Entry<K, V>?>(tableSize) { null }
        )
        seed++
        for (entry in allEntries) {
            set(entry.key, entry.value)
        }
    }
}

/** Reflection
 * 
 * I got stuck with losing values in the rehashing process, so I just had to make a beefy helper function that would handle it. Otherwise, this 
 * homework was actually quite fun. I was challenged, but not as much as in HW4 and it was a fun kind of challenge.
 * 
 * I spent 6 hours on this assignment.
 */
