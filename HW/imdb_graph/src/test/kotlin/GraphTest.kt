import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import java.io.File
import org.junit.jupiter.api.BeforeEach

class GraphTest {

    lateinit var graph: IMDBGraph

    @BeforeEach
    fun setUp() {
        println("Reading data...")
        graph = IMDBGraph()
        println("Finished reading data")
    }

    @Test
    fun testConnectedPerformers() {
        // Millie Davis, nm4438246
        var performers = graph
            .connectedPerformers("nm4438246", 1)
            .map {graph.performerIdsToNames[it]}

        // via tt5889372 Odd Squad: The Movie
        assertTrue(performers.contains("Ana Sani"))

        // via tt10346674 Lamya's Poem
        assertTrue(performers.contains("Faran Tahir"))

        // not there
        assertFalse(performers.contains("Bill Murray"))

        performers = graph
            .connectedPerformers("nm4438246", 2)
            .map {graph.performerIdsToNames[it]}

        // nm4438246 Millie Davis
        //     tt10346674 Lamya's Poem
        // nm0846687 Faran Tahir
        //     tt1369571 I Am Fear
        // nm0608405 Bill Moseley
        assertTrue(performers.contains("Bill Moseley"))

        // nm4438246 Millie Davis
        //     tt6977338 Good Boys
        // nm0287182 Will Forte
        //     tt3152592 Scoob!
        // nm1752221 Gina Rodriguez
        assertTrue(performers.contains("Gina Rodriguez"))

        // not there
        assertFalse(performers.contains("Bill Murray"))
        assertFalse(performers.contains("Taylor Swift"))
    }

    
    @Test
    fun testShortestPath() {
        /* nm0000197 Jack Nicholson
           The Postman Always Rings Twice
           nm0001448 Jessica Lange
           In Secret
           nm0647634 Elizabeth Olsen]
         */

        assertEquals(2, graph.shortestPath("nm0000197", "nm0647634").size)

        /* nm9924609 Trey Peyton
           tt14049394 House of Dolls
           nm0908914 Dee Wallace
           tt0078721 10
           nm0001133 Brian Dennehy
           tt0430779 Everyone's Hero
           nm1159180 Ed Helms
           tt1482459 The Lorax
           nm2357847 Taylor Swift
         */

        assertEquals(4, graph.shortestPath("nm9924609", "nm2357847").size)

        /* No connections for these two:
           nm8334525 Ninel Ternovskaya
           nm7994460 Elphis Corrales
         */

        assertEquals(0, graph.shortestPath("nm8334525", "nm7994460").size)

        /*
         nm0056440 Ljubisa Barovic
         tt14688330 Dobra ponuda
         nm6584235 Micko Markovic
         tt10656034 Moras biti ziv da bi umro
         nm0711003 Eva Ras
         tt0071178 The Car
         nm0304839 Dezsö Garas
         tt0097472 Hagyjátok Robinsont!
         nm0690021 Raúl Pomares
         tt0076619 Retrato de Teresa
         nm0070281 Miguel Benavides
         */

        assertEquals(5, graph.shortestPath("nm0056440", "nm0070281").size)
    }
}
