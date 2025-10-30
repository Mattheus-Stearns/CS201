import java.io.File

fun main() {

    // Names are not unique, so maintain a list of names for each id
    val performerNamesToIds = mutableMapOf<String, MutableList<String>>()
    val graph = IMDBGraph()

    // Read in names
    println("Reading names")
    var reader = File("src/main/resources/performer-names.tsv").bufferedReader()
    var rowNum = 0
    while (reader.ready()) {
        val (nameid, name) = reader.readLine().split("\t")
        var ids = performerNamesToIds.getOrDefault(name, mutableListOf<String>())
        ids.add(nameid)
        performerNamesToIds[name] = ids
        if (++rowNum % 100000 == 0) {
            print("$rowNum...")
        }
    }
    println()
    reader.close()

    while (true) {
        print("Start [or type 'quit']? ")
        val startPerformer = readln()
        if (startPerformer=="quit") {
            break
        }

        print("End? ")
        val endPerformer = readln()
        val startPerformerIds = performerNamesToIds[startPerformer]
        if (startPerformerIds == null) {
            println("Start performer not in data. Try again.")
            continue
        }
        val endPerformerIds = performerNamesToIds[endPerformer]
        if (endPerformerIds == null) {
            println("End performer not in data. Try again.")
            continue
        }
        val results = graph.shortestPath(startPerformerIds[0],
                                         endPerformerIds[0])

        if (results.size > 0) {
            results.forEach { connection ->
                println(connection.performerId + " " + graph.performerIdsToNames[connection.performerId])
                println("    " + connection.movieId + " " + graph.movieIdsToTitles[connection.movieId])
            }
            println("" + performerNamesToIds[endPerformer]!![0] + " " + endPerformer)
        } else {
            println("No connections found.")
        }
    }
}
