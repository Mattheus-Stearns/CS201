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