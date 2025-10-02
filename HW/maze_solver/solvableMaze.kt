import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

class solvableMaze() {

    private val logMessages: MutableList<String> = mutableListOf()
    /**
     * @param maze that is passed through a text file
     * this function is used for interpreting what the maze's look like
     * <number of rows> <number of columns>
     * <0-based row number of S> <0-based column number of S>
     * <0-based row number of F> <0-based column number of F>
     * <row 0 description>
     * <row 1 description>
     * ...
     * 7 means that the square has both a top wall and a right wall
     * | (vertical bar or “pipe”) means that the square has a right wall, but no top wall
     * _ (underscore) means that the square has a top wall, but no right wall
     * * (asterisk) means that the square has neither a top wall nor a right wall
     * 
     * e.g.
     * 2 3
     * 1 1
     * 0 2
     * __7
     * *_7
     */
    fun buildMaze(inputFilePath: String) {
        val inputFile: File = File(inputFilePath)
        val listOfLines: MutableList<String> = mutableListOf()
        try {
            inputFile.forEachLine() {
                if (it.any { it.isWhitespace() }) {
                    listOfLines.addAll(it.split(" "))
                } else {
                    listOfLines.add(it)
                }
            }
        } catch (e: FileNotFoundException) {
            println(e)
            exitProcess(1)
        }
        logMessages.add("List of lines read from the file: \n$listOfLines \n")
        var maze = Array(listOfLines[0].toInt()) {Array<String?>(listOfLines[1].toInt()) {null}}
        maze[listOfLines[2].toInt()][listOfLines[3].toInt()] = "S"
        maze[listOfLines[4].toInt()][listOfLines[5].toInt()] = "F"
        logMessages.add("2d Array of Maze with Start and Finish added: \n${maze.contentDeepToString()}")
        logMessages()
    }

    fun solveMaze() {
        
    }

    fun logMessages() {
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
    }
}


fun main() {
    solvableMaze().buildMaze("TestMaze.txt")
}