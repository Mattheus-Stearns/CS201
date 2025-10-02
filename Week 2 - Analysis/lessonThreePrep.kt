/**
 * Three laws of recursion:
 *
 *	- A recursive algorithm must have a base case.
 *	- A recursive algorithm must change its state and move toward the base case.
 *	- A recursive algorithm must call itself recursively.
 */

/**
 * A function modeled after the function implemented in `Converting an Integer to a String in Any Base` in Problem Solving with Algorithms and Data Structures using Python The Interactive Edition by Bradley N. Miller, David L. Ranum, Roman Yasinovskyy
 * @param n as the int to be converted 
 * @param base as the base in which the conversion occurs
 * @return String as the result of the recursive conversion
 */
fun to_str(n: Int, base: Int) : String {
    val convertString: String = "0123456789ABCDEF"
    if (n < base) {
        return convertString[n].toString()
    } else {
        return to_str(n.floorDiv(base), base) + convertString[n % base]
    }

}

fun main() {
    println(to_str(1453, 16))
    //should print 5AD
}

/**
 * Other recursive function that I did for practice as an excercise on the same page, but its in python:
 * def reverse(s):
 *   if len(s) <= 1:
 *       return s
 *   else:
 *       return reverse(s[1:]) + s[0]
 */