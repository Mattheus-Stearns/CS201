class SolvableMaze(filename: String) : Maze(filename) {
    /**
     * A function that utilizes the other classes to make mazesquare data classes put on a stack, 
     * then calls a function to find available squares,
     * and if there are no pathways forward, it backtracks and as it does it changes the stack to reflect such behaviour
     * and at the end it looks through the stack and changes the properties of the squares used in the solution to reflect their usage
     * @return true if there is a solution, false otherwise
     */ 
    fun solveMaze(): Boolean {
        val MazeStart: MazeSquare = squares[startRow][startCol]
        val MazeEnd: MazeSquare = squares[finishRow][finishCol]
        var currentSquare: MazeSquare = squares[MazeStart.row][MazeStart.col]
        var mazeSquares: ListStack<MazeSquare> = ListStack()
        var nextSquare: MazeSquare?
        var previousSquare: MazeSquare

        while (!(currentSquare.row == MazeEnd.row && currentSquare.col == MazeEnd.col)) {
            currentSquare.visited = true
            mazeSquares.push(currentSquare)
            var nextSquare = availableSquare(currentSquare)

            if (nextSquare == null) {
                // Backtrack until a square with unvisited neighbors is found
                while (!mazeSquares.isEmpty() && nextSquare == null) {
                    val previousSquare = mazeSquares.peek()
                    nextSquare = availableSquare(previousSquare)
                    if (nextSquare == null) {
                        mazeSquares.pop()
                    }
                }

                if (nextSquare == null) return false
            }

            currentSquare = nextSquare
        }

        // Marking the Solution Squares
        val tempStack = ListStack<MazeSquare>()
        while (!mazeSquares.isEmpty()) {
            val square = mazeSquares.pop()
            square.solutionPiece = true
            tempStack.push(square)
        }

        while (!tempStack.isEmpty()) {
            mazeSquares.push(tempStack.pop())
        }

        return true
    }

    /**
     * A function that
     * @return 's an available MazeSquare with the 
     * @param currentPosition
     * after iteratively checking if each direction does not have a wall and has not been visited
     */
    fun availableSquare(currentPosition: MazeSquare) : MazeSquare? {
        // Up
        if (currentPosition.row > 0){
            val aboveSquare = squares[currentPosition.row - 1][currentPosition.col]
            if (!currentPosition.hasTopWall && !aboveSquare.visited) { return aboveSquare }
        }
        // Right
        if (currentPosition.col < numCols - 1) {
            val rightSquare = squares[currentPosition.row][currentPosition.col + 1]
            if (!currentPosition.hasRightWall && !rightSquare.visited) { return rightSquare }
        }
        // Down
        if (currentPosition.row < numRows - 1) {
            val belowSquare = squares[currentPosition.row + 1][currentPosition.col]
            if (!belowSquare.hasTopWall && !belowSquare.visited) { return belowSquare }
        }
        // Left
        if (currentPosition.col > 0) {
            val leftSquare = squares[currentPosition.row][currentPosition.col - 1]
            if (!leftSquare.hasRightWall && !leftSquare.visited) { return leftSquare }
        }
        return null
    }
}
