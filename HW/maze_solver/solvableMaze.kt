import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

class solvableMaze() {

    private val logMessages: MutableList<String> = mutableListOf()

    data class Cell(
            val topWall: Boolean,
            val rightWall: Boolean,
            var leftWall: Boolean,
            var downWall: Boolean,
            var isStart: Boolean = false,
            var isFinish: Boolean = false
        )

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
        
        val rows = listOfLines[0].toInt()
        val cols = listOfLines[1].toInt()
        val sRow = listOfLines[2].toInt()
        val sCol = listOfLines[3].toInt()
        val fRow = listOfLines[4].toInt()
        val fCol = listOfLines[5].toInt()

        val maze = Array(rows) { r ->
            Array(cols) { c ->
                val ch = listOfLines[6 + r][c]
                when (ch) {
                    '7' -> Cell(topWall = true,  rightWall = true, leftWall = false, downWall = false)
                    '|' -> Cell(topWall = false, rightWall = true, leftWall = false, downWall = false)
                    '_' -> Cell(topWall = true,  rightWall = false, leftWall = false, downWall = false)
                    '*' -> Cell(topWall = false, rightWall = false, leftWall = false, downWall = false)
                    else -> throw IllegalArgumentException("Unknown char $ch")
                }
            }
        }

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                val cell = maze[r][c]
                cell.leftWall = if (c == 0) true else maze[r][c - 1].rightWall
                cell.downWall = if (r == rows - 1) true else maze[r + 1][c].topWall
            }
        }

        maze[sRow][sCol].isStart = true
        maze[fRow][fCol].isFinish = true
        logMessages.add("UnRendered 2D Maze: \n${maze.contentDeepToString()}")
        logMessages.add("Rendered 2D Maze: \n${renderMaze(maze)}")
        logMessages()
    }

    fun solveMaze() {
        
    }

    /**
     * This gets the
     * @param maze , which is a 2d array with data class Cell, and then is interpreted to be easily presentable 
     * for the user by
     * @return the rendered mazestring
     */
    fun renderMaze(maze: Array<Array<Cell>>): String {
        val sb = StringBuilder()
        val rows = maze.size
        val cols = maze[0].size

        for (r in 0 until rows) {
            sb.append("+")
            for (c in 0 until cols) {
                sb.append(if (maze[r][c].topWall) "-----" else "     ")
                sb.append("+")
            }
            sb.append("\n")

            repeat(3) { line ->
                sb.append("|")
                for (c in 0 until cols) {
                    val cell = maze[r][c]
                    val content = when {
                        line == 1 && cell.isStart  -> "  S  "
                        line == 1 && cell.isFinish -> "  F  "
                        line == 1                  -> "  .  "
                        else                       -> "     "
                    }
                    sb.append(content)
                    sb.append(if (cell.rightWall) "|" else " ")
                }
                sb.append("\n")
            }
        }

        sb.append("+")
        repeat(cols) { sb.append("-----+")}
        sb.append("\n")

        return sb.toString()
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