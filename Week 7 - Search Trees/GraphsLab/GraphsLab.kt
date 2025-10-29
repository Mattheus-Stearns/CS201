// Adjacency Matrix & List graph representation
// Ported to Kotlin by Dave Musicant from Java code at
// https://www.programiz.com/dsa/graph-adjacency-matrix
// https://www.programiz.com/dsa/graph-adjacency-list
// Adapted for in-class lab by Jean Salac and Anya Vostinar

class AdjMatrixGraph(numVertices: Int) {
    var adjMatrix = Array<Array<Boolean>>(numVertices) {Array<Boolean>(numVertices) {false}}

    /**
     * Add an edge to the graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     */
    fun addEdge(i: Int, j: Int) {
        adjMatrix[i][j] = true
        adjMatrix[j][i] = true
    }

    /**
     * Remove edge from graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     */
    fun removeEdge(i: Int, j: Int) {
        adjMatrix[i][j] = false
        adjMatrix[j][i] = false
    }

    /**
     * Check if edge exists in graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     * return: Boolean with result
     */
    fun hasEdge(v1 : Int, v2 : Int) : Boolean{
        if (v1 >= adjMatrix.count() || v2 >= adjMatrix.count()) return false
        return adjMatrix[v1][v2]
    }

    /**
     * Get the neighbors of a vertex 
     * param: int, vertex to retrieve neighbors of
     * return: MutableList<Int> of neighbors
     */
    fun getNeighbors(vertex : Int) : MutableList<Int> {
        
        var neighbors = mutableListOf<Int>()

        for (i in 0 until adjMatrix[vertex].size) {
            if (adjMatrix[vertex][i]) {
                neighbors.add(i)
            }
        }

        return neighbors
    }
    

    /**
     * Create string visualization of the graph, to be used with println
     * return: string
     */
    override fun toString(): String {
        var s = StringBuilder()
        for (i in 0..<adjMatrix.count()) {
            s.append("$i: ")
            for (j in adjMatrix[i]) {
                s.append((if (j) 1 else 0).toString() + " ")
            }
            s.append("\n")
        }
        return s.toString()
    }

    /**
     * Add a vertex to the graph with no neighbors
     */
    fun addVertex() {
        adjMatrix = adjMatrix + Array<Boolean>(adjMatrix.size) {false}
        for (i in 0 until adjMatrix.size) {
            adjMatrix[i] += false
        }
    }
}

class AdjListGraph(numVertices: Int) {
    var adjList = mutableListOf<MutableList<Int>>()
    
    init{
        for (i in 0..<numVertices) {
            adjList.add(mutableListOf<Int>())
        }
    }

    /**
     * Add an edge to the graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     */
    fun addEdge(s: Int, d: Int) {
        if (!(d in adjList[s])) {
            adjList[s].add(d)
        }
        if (!(s in adjList[d])) {
            adjList[d].add(s)         
        }
    }
    

    /**
     * Remove edge from graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     */
    fun removeEdge(i: Int, j: Int) {
        adjList[i].remove(j)
        adjList[j].remove(i)
    }

    /**
     * Check if edge exists in graph
     * param: int, starting vertex for edge
     * param: int, ending vertex for edge
     * return: Boolean with result
     */
    fun hasEdge(v1 : Int, v2: Int) : Boolean{
        return adjList[v1].contains(v2)
    }

    /**
     * Get the neighbors of a vertex 
     * param: int, vertex to retrieve neighbors of
     * return: MutableList<Int> of neighbors
     */
    fun getNeighbors(vertex : Int): MutableList<Int> {
        var neighbors = mutableListOf<Int>()
        for (item in adjList[vertex]) {
            neighbors.add(item)
        }
        return neighbors
    }

    /**
     * Create string visualization of the graph, to be used with println
     * return: string
     */
    override fun toString(): String {
        var s = StringBuilder()
        for (i in 0..<adjList.count()) {
            s.append("\nVertex $i:")
            for (j in 0..<adjList[i].count()) {
                s.append(" -> ${adjList[i][j]}")
            }
            s.append("\n")
        }
        return s.toString()
    }

    /**
     * Add a vertex to the graph with no neighbors
     */
    fun addVertex() {
        adjList.add(mutableListOf<Int>())
    }

}

fun main(){
    // Adjacency Matrix Graph Representation
    val gMatrix = AdjMatrixGraph(4)

    gMatrix.addEdge(0, 1)
    gMatrix.addEdge(0, 2)
    gMatrix.addEdge(1, 2)
    gMatrix.addEdge(2, 0)
    gMatrix.addEdge(2, 3)

    println(gMatrix.toString())

    println("hasEdge 0 1: " + gMatrix.hasEdge(0, 1))
    println("hasEdge 0 7: " + gMatrix.hasEdge(0, 7))

    gMatrix.removeEdge(2, 0)
    println("hasEdge 0 2 should be false: " + gMatrix.hasEdge(0, 2))

    println("getNeighbors for 1: " + gMatrix.getNeighbors(1))

    gMatrix.addVertex()
    println(gMatrix)

    // Adjacency List Graph Representation
    val gList = AdjListGraph(4)
     
    gList.addEdge(0, 1)
    gList.addEdge(0, 2)
    gList.addEdge(1, 2)
    gList.addEdge(2, 0)
    gList.addEdge(2, 3)

    println(gList)

    println("hasEdge 0 1: " + gList.hasEdge(0, 1))
    println("hasEdge 0 7: " + gList.hasEdge(0, 7))

    gList.removeEdge(2, 0)
    println("hasEdge 0 2 should be false: " + gList.hasEdge(0, 2))

    println("getNeighbors for 1: " + gList.getNeighbors(1))

    gList.addVertex()
    println(gList)

}