/**
 * Bibliography:
 * 
 * - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-hash-map/
 * - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/-set/
 * - https://runestone.academy/ns/books/published/pswadsup/graphs_implementing-breadth-first-search.html 
 * 
 * Collaboration:
 * - N/A
 */

import kotlin.collections.emptyList
import java.io.File

class IMDBGraph {

    /***************** Constructor (plus helper method) *****************/

    // Our graph is represented by a pair of maps
    val performersToMovies = HashMap<String, MutableList<String>>()
    val moviesToPerformers = HashMap<String, MutableList<String>>()

    // These two maps just connect performer ID to name and movie ID to name; it
    // might make playing around with the data more pleasant to have access to them
    val movieIdsToTitles = HashMap<String, String>()
    val performerIdsToNames = HashMap<String, String>()

    // In the constructor, read in all of the data from the .tsv files
    init {
        // Read in performer IDs and names
        var reader = File("src/main/resources/performer-names.tsv").bufferedReader()
        while (reader.ready()) {
            val (nameid, name) = reader.readLine().split("\t")
            performerIdsToNames[nameid] = name
        }
        reader.close()

        // Read in titles
        println("Reading titles")
        reader = File("src/main/resources/movie-titles.tsv").bufferedReader()
        while (reader.ready()) {
            val (titleid, title) = reader.readLine().split("\t")
            movieIdsToTitles[titleid] = title
        }
        reader.close()

        // Read in actual performances (a performer appearing in a movie)
        reader = File("src/main/resources/performances.tsv").bufferedReader()
        while (reader.ready()) {
            val (titleid, nameid) = reader.readLine().split("\t")
            addEntry(nameid, titleid)
        }
        reader.close()
    }

    /* This function adds a performer ID and a movie title ID to both maps. */
    fun addEntry(performerId: String, movieId: String) {
        var titles = performersToMovies[performerId]
        if (titles == null) {
            titles = mutableListOf<String>()
            performersToMovies[performerId] = titles
        }
        titles.add(movieId)

        var names = moviesToPerformers[movieId]
        if (names == null) {
            names = mutableListOf()
            moviesToPerformers[movieId] = names
        }
        names.add(performerId)
    }
    
    /***************** Part A: Find connected performers *****************/

    /**
     * a function which travels across the imdb database to find all of the performers who have been in the same movie as the
     * @param performerId , sometimes even crossing over multiple movies, up to the specified
     * @param maxDistance , and which 
     * @return the set of performers who were found to be shared in movies with the performerID across the maxDistance
     */
    fun connectedPerformers(performerId: String, maxDistance: Int): Set<String> {
        return recConnectedPerformers(performerId, maxDistance, mutableSetOf<String>())

        // what follows is a BFS algorithm that I later assumed was implied to be used for this problem:
        if (maxDistance <= 0) return emptySet()
        
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<Pair<String, Int>>()
        val result = mutableSetOf<String>()
        queue.add(performerId to 0)
        visited.add(performerId)

        while (queue.isNotEmpty()) {
            val (currentPerformer, distance) = queue.removeFirst()
            val movies = performersToMovies[currentPerformer] ?: mutableListOf()
            for (movie in movies) {
                val coPerformers = moviesToPerformers[movie] ?: mutableListOf()
                for (coPerformer in coPerformers) {
                    if (coPerformer != currentPerformer && !visited.contains(coPerformer)) {
                        visited.add(coPerformer)
                        if (distance + 1 <= maxDistance) {
                            result.add(coPerformer)
                            queue.add(coPerformer to distance + 1)
                        }
                    }
                }
            }
        }
        
        return result
    }
    /**
     * a helper (recursive) function that does all of the heavy work for connectedPerformers(), the
     * @param performerId that we are going to be looking through along movies, the
     * @param maxDistance that we will be using to move towards our base case, and the 
     * @param visited that tracks what performers we have 'visited' i.e. already added to our set
     * @return the set of performers for one specific movie, which we add into our result while inside the function 
     */
    private fun recConnectedPerformers(performerId: String, maxDistance: Int, visited: MutableSet<String>): Set<String> {
        if (maxDistance <= 0 || visited.contains(performerId)) {
            return emptySet()
        }

        visited.add(performerId)
        val result = mutableSetOf<String>()
        val movies = performersToMovies[performerId] ?: emptySet()

        for (movie in movies) {
            val coPerformers = moviesToPerformers[movie] ?: emptySet()
            for (coPerformer in coPerformers) {
                if (coPerformer != performerId && !visited.contains(coPerformer)) {
                    result.add(coPerformer)
                    if (maxDistance > 1) {
                        result.addAll(recConnectedPerformers(coPerformer, maxDistance - 1, visited))
                    }
                }
            }
        }

        return result
    }

    /***************** Part B: Find a path between performers *****************/

    /* This data class represents a performer-movie pair. */
    data class Connection(val performerId: String, val movieId: String)

    /**
     * This function 
     * @return a list of connections from the 
     * @param startPerformer to the 
     * @param endPerformer 
     * This function uses BFS instead of recursion (because I read up on it and apparently it should be used in this case)
     */
    fun shortestPath(startPerformerId: String, endPerformerId: String): List<Connection> {
        if (startPerformerId == endPerformerId) return emptyList()
        
        val queue = ArrayDeque<String>()
        val visited = mutableSetOf<String>()
        val pathTo = mutableMapOf<String, List<Connection>>()
        
        queue.add(startPerformerId)
        visited.add(startPerformerId)
        pathTo[startPerformerId] = emptyList()
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current == endPerformerId) {
                return pathTo[current]!!
            }
            
            val movies = performersToMovies[current] ?: mutableListOf()
            for (movie in movies) {
                val coPerformers = moviesToPerformers[movie] ?: mutableListOf()
                
                for (coPerformer in coPerformers) {
                    if (!visited.contains(coPerformer)) {
                        visited.add(coPerformer)
                        val newPath = pathTo[current]!! + Connection(current, movie)
                        pathTo[coPerformer] = newPath
                        queue.add(coPerformer)
                    }
                }
            }
        }
        
        return emptyList()
    }
}

/**
 * Reflection:
 * 
 * When I first set my eyes on this homework, I thought of a recursive way to solve part A, and it was really nice looking and worked, but figured that it wasn't the 
 * point of this hw and while I had read the graph prep I just automatically went to the recursive implementation because I have been doing that a lot. Then, I looked 
 * back at the runestone BFS algorithm explanation and figured that it was probably better suited, and I looked it up and found out that in large data sets it definitely
 * works better, so then I did part A using BFS. Then part B came naturally because it was basically a perfect extension of part A. I spent 2.5 hours on this assignment.
 * 
 * P.S: apparently recursion for part A was like ~0.2s faster on average!?!?!?!?
 */