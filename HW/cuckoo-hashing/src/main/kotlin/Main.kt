fun main() {
    println("Testing CuckooHashMap...")
    
    // Test 1: Basic insertion and retrieval
    val map = CuckooHashMap<Int, String>(10)
    println("Test 1 - Basic operations:")
    map.set(1, "one")
    map.set(2, "two")
    map.set(3, "three")
    
    println("Get 1: ${map.get(1)}")  // Should be "one"
    println("Get 2: ${map.get(2)}")  // Should be "two" 
    println("Get 3: ${map.get(3)}")  // Should be "three"
    println("Get 4: ${map.get(4)}")  // Should be null
    
    // Test 2: Update existing key
    println("\nTest 2 - Update:")
    map.set(2, "TWO")
    println("Get 2 after update: ${map.get(2)}")  // Should be "TWO"
    
    // Test 3: Display tables
    println("\nTest 3 - Table contents:")
    map.display()
    
    // Test 4: Test with strings
    println("\nTest 4 - String keys:")
    val stringMap = CuckooHashMap<String, Int>(5)
    stringMap.set("apple", 5)
    stringMap.set("banana", 3)
    stringMap.set("cherry", 8)
    println("Get 'apple': ${stringMap.get("apple")}")    // Should be 5
    println("Get 'banana': ${stringMap.get("banana")}")  // Should be 3
    
    // Test 5: Test collision handling
    println("\nTest 5 - Collisions:")
    val smallMap = CuckooHashMap<Int, String>(2)
    smallMap.set(10, "ten")
    smallMap.set(20, "twenty") 
    smallMap.set(30, "thirty")
    println("Get 10: ${smallMap.get(10)}")
    println("Get 20: ${smallMap.get(20)}") 
    println("Get 30: ${smallMap.get(30)}")
    smallMap.display()
    
    println("\nAll tests completed!")
}