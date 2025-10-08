/**
 * Biblography:
 * 
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/is-whitespace.html
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/split.html
 *  - https://kotlinlang.org/docs/collection-write.html
 *  - https://kotlinlang.org/docs/arrays.html#access-and-modify-elements
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/-string-builder/
 *  - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/replace.html
 * 
 * Collaboration Statement:
 * 
 *  - N/A (Done Individually)
 */


fun main(args: Array<String> = arrayOf("maze1.txt")) {
    
    var userMaze = SolvableMaze(args[0])
    if (args.size >= 2) {
        if (args[1] == "--solve") {
            userMaze.solveMaze()
            userMaze.printMaze()
        }
    }
}

/** Reflection:
 * I spent two hours on this assignment building my own source code that could be given text files and would interpret them and then build a maze,
 * and then I realized that it was already implemented for you by talking to Anya, and then I struggled for another hour with getting use to the source
 * libraries that were provided. Once I got used to them, I had two major hurdles/bugs to get over, and that was one where I was not properly referencing
 * the list 'squares' that was not in SolvableMaze.kt, but once I saw the error stack a few times and tried to parse it I realized what was happening. Then,
 * when implementing the printed solution, I realized that my algorithm was ending if it could get either the same column or the same row of the finish, not both. 
 * So, I had to fix my algorithm for that, and then I encountered another issue while backtracking I would pop some elements from my stack when I should have 
 * first looked at them, and that caused a cursed mazepath to occur. But, after a total of 6 hours (including building some of the source code myself)
 * I was able to crack it. 
 */