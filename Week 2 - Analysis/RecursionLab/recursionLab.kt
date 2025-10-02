/**
 * Bibliography:
 * - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin/require.html
 * - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/split.html
 * 
 * Reflection:
 * 
 * This was so much work. It took me like four hours.
 */

// Computes a*b by adding b to itself a times.
fun mult(a: Int, b: Int): Int {
    if (a == 0) { //base case
        return 0
    }

    return b + mult(a-1, b) //recursive call: a gets subtracted
}

// Computes n! = n * (n-1) * (n-2) * ... * 1.
fun fact(n: Int): Int { 
    if (n == 1) { // base case
        return 1
    }

    return n * fact(n-1) // recursive call: subtract 1 from n
}

// Given a list of numbers, return the largest number
fun findMax(numList: List<Int>): Int?{
    if (numList.isEmpty()) {
        return null
    } else if (numList.size == 1) {
        return numList[0]
    } else {
        if (numList[0] > findMax(numList.subList(1, numList.size))!!) {
            return numList[0]
        } else {
            return numList[1]
        }
    }
}
/**
 * The first base case is on line 3, checking if the list is empty
 * The second base case is on line 5, checking if the list has one value
 * The recursive call is on line 8
 * And the recursive function works towards the base case by passing smaller and smaller sublists into the function recursively,
 * While also making a selection of if the integer is smaller or larger
 */

// Given a list of numbers, return the sum of all the numbers in the list
fun sumList(numList: List<Int>): Int?{
    if (numList.isEmpty()) {
        return null
    } else if (numList.size == 1) {
        return numList[0]
    } else {
        return numList[0] + sumList(numList.subList(1, numList.size))!!
    }
}
/**
 * The first base case is on line 25, checking if the list is empty
 * The second base case is on line 27, checking if the list has one value
 * The recursive call is on line 30
 * And the recursive function works towards the base case by passing smaller and smaller sublists into the function recursively,
 * While also adding the not included item of the list to the result
 */

/**
 * Given two numbers,
 * @param b as the base integer and
 * @param n as the integer which b is being raised to the power of,
 * recursively perform exponentiation without using built-in exponentiation (**)
 */
fun power(b: Int, n: Int): Int?{
    if (n == 0) {
        if (b != 0) { return 1 } else { return null }
    } else if (n > 0) {
        return b * power(b, n - 1)!!
    } else {
        return null
    }
}
/**
 * The base case is on line 48, checking if n is 0
 * The recursive call is on line 51
 * And the recursive function works towards the base case by decreasing the n passed into the function recursively,
 * While also multiplying the base to the result
 */

/**
 * Given a number,
 * @param n as the counting number
 * with which you will be recursively counting down to 0 and printing each number
 */
fun countdown(n: Int) {
    if (n == 0) {
        println("$n! The count is up!")
    } else if (n < 0) {
        println("Error: Passed a non-positive integer.")
        return
    } else {
        println("$n...")
        countdown(n - 1)
    }
}
/**
 * The base case is on line 69, checking if n is 0
 * The recursive call is on line 73
 * And the recursive function works towards the base case by decreasing the n passed into the function recursively,
 * While also printing the value of the @param n
 * I don't think that anything needs to be returned in this case
 */

/**
 * Given a number,
 * @param n as the nth term of fibbonacci's number which will
 * recursively compute Fibonacci's numbers and
 * @return the nth Fibbonacci number
 */
fun fib(n: Int): Int{
    require(n >= 0) { "n must be non-negative, was $n" }
    return when (n) {
        0 -> 0
        1 -> 1
        else -> fib(n - 1) + fib(n - 2)
    }
}


/**
 * Given a value and a list,
 * @param val as the value which we will be checking for duplicates of, and
 * @param lst as the list where we will be looking for instances of val,
 * recursively compute the num of instances of val
 * @return the count of val
 */
fun countVal(Val: String, lst: List<String>): Int{
    if (lst.isEmpty()) {
        return 0
    }
    return if (lst.first() == Val) {
        1 + countVal(Val, lst.drop(1))
    } else {
        countVal(Val, lst.drop(1))
    }
}

/**
 * Given a number,
 * @param n as the counting number
 * with which you will be recursively counting up to n from 0 and printing each number
 */
fun countup(n: Int) {
    if (n < 0) { return }
    if (n == 0) {
        println("$n...")
    }
    if (n > 0) { 
        countup(n - 1)
        println("$n...")
    }
    
}

/**
 * Given a list of which
 * @param numList is the list in which we will
 * @return the smallest number
 */
fun findMin(numList: List<Int>): Int?{
    if (numList.isEmpty()) {
        return null
    }
    if (numList.size == 1) {
        return numList[0]
    }
    return if (numList[0] < findMin(numList.subList(1, numList.size))!!) {
        numList[0]
    } else {
        findMin(numList.subList(1, numList.size))!!
    }
}

/**
 * Given a number,
 * @param n as the nth term of fibbonacci's number which will
 * recursively compute Fibonacci's numbers and
 * @return the nth Fibbonacci number and
 * @return the number of times in which countFib() was called
 */
fun countFib(n: Int): Pair<Int, Int>{
    require(n >= 0) { "n must be non-negative, was $n" }
    return when (n) {
        0 -> Pair(0, 1)
        1 -> Pair(1, 1)
        else -> {
            val (f1, c1) = countFib(n - 1)
            val (f2, c2) = countFib(n - 2)
            Pair(f1+ f2, 1 + c1 + c2)
        }
    }
}

/**
 * Given two numbers,
 * @param a the number which is being divided by
 * @param b , recursively compute and 
 * @return the remainder of the division
 */
fun mod(a: Int, b:Int): Int?{
    if (a < 0 || b < 0) {
        return null
    } else if (a == 0 || b == 0) {
        return 0
    } else if (a >= b) {
        return mod(a - b, b)!!
    } else if (a < b) {
        return a
    } else {
        return null
    }
}

fun main(){
    //Testing findMax()
    println("Solution to findMax: ${findMax(listOf(26, 30, 8))}")
    println("Solution to findMax: ${findMax(listOf(-19, -61))}")
    println("Solution to findMax: ${findMax(listOf(-1))}")
    println("Solution to findMax: ${findMax(listOf<Int>())}")

    //Testing sumList()
    println("Solution to sumList: ${sumList(listOf(-6, 4, 12))}")
    println("Solution to sumList: ${sumList(listOf(-47, 53))}")
    println("Solution to sumList: ${sumList(listOf(-5))}")
    println("Solution to sumList: ${sumList(listOf<Int>())}")

    //Testing powerOf()
    println("Solution to powerOf: ${power(2, 4)}")
    println("Solution to powerOf: ${power(5, -3)}")
    println("Solution to powerOf: ${power(0, 0)}")
    println("Solution to powerOf: ${power(286, 0)}")

    //Testing countdown()
    println("Solution to countdown:")
    countdown(10)
    println("Solution to countdown:")
    countdown(-3)
    println("Solution to countdown:")
    countdown(0)
    println("Solution to countdown:")
    countdown(5)

    //Testing fib()
    println("Solution to fib: ${fib(3)}")
    println("Solution to fib: ${fib(7)}")
    println("Solution to fib: ${fib(0)}")
    println("Solution to fib: ${fib(3)}")
    
    //Testing countVal()
    val sentence: String = "Thisisadeathsentence!"
    println("Solution to countVal: ${countVal("a", sentence.split(""))}")
    println("Solution to countVal: ${countVal("b", sentence.split(""))}")
    println("Solution to countVal: ${countVal("c", sentence.split(""))}")
    println("Solution to countVal: ${countVal("d", sentence.split(""))}")

    //Testing countup()
    println("Solution to countup:")
    countup(3)
    println("Solution to countup:")
    countup(7)
    println("Solution to countup:")
    countup(0)
    println("Solution to countup:")
    countup(10)

    //Testing findMin()
    println("Solution to findMin: ${findMin(listOf(26, 30, 8))}")
    println("Solution to findMin: ${findMin(listOf(-19, -61))}")
    println("Solution to findMin: ${findMin(listOf(-1))}")
    println("Solution to findMin: ${findMin(listOf<Int>())}")

    //Testing countFib()
    println("Solution to countFib: ${countFib(3)}")
    println("Solution to countFib: ${countFib(7)}")
    println("Solution to countFib: ${countFib(0)}")
    println("Solution to countFib: ${countFib(3)}")
    
    //Testing mod()
    println("Solution to mod: ${mod(5, 2)}")
    println("Solution to mod: ${mod(48, 35)}")
    println("Solution to mod: ${mod(0, 0)}")
    println("Solution to mod: ${mod(3, -1)}")
}