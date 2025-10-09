import Deck
import kotlin.test.Test
import kotlin.test.assertEquals

class DeckTest {

    // Verify that the linked list in deck matches a provided string
    private fun checkForward(deck: Deck, cardString: String) {
        val cardNumberList = cardString.split(' ')
        var currentNode = deck.cards
        for (card in cardNumberList) {
            assertEquals(card.toInt(), currentNode!!.value)
            currentNode = currentNode.next
        }
    }

    // Verify that the linked list in deck matches a provided string
    private fun checkBackward(deck: Deck, cardString: String) {
        val cardNumberList = cardString.split(' ')
        var currentNode = deck.cards!!.previous
        for (position in cardNumberList.size-1 downTo 0) {
            assertEquals(cardNumberList[position].toInt(), currentNode!!.value)
            currentNode = currentNode.previous
        }
    }

    @Test
    fun testConstructor() {
        val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
        checkForward(deck, "1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
        checkBackward(deck, "1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
    }

    @Test
    fun testGetString() {
        val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
        assertEquals("1", deck.getString(1))
        assertEquals("1 4 7 10", deck.getString(4))
        assertEquals("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26",
                     deck.getString(28))
        assertEquals("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26 1 4 7 10",
                     deck.getString(32))
    }

    @Test
    fun testGetStringBackwards() {
        val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
        assertEquals("26", deck.getStringBackwards(1))
        assertEquals("26 23 20 17", deck.getStringBackwards(4))
        assertEquals("26 23 20 17 14 11 8 5 2 27 24 21 18 15 12 9 6 3 28 25 22 19 16 13 10 7 4 1",
                     deck.getStringBackwards(28))
        assertEquals("26 23 20 17 14 11 8 5 2 27 24 21 18 15 12 9 6 3 28 25 22 19 16 13 10 7 4 1 26 23 20 17",
                     deck.getStringBackwards(32))
    }
    @Test
    fun testCountdown() {
        assertEquals(4, Deck("1 4 7 10").countDown())
        assertEquals(9, Deck("4 1 7 10 9 8 2").countDown())
        assertEquals(11, Deck("22 4 7 10 13 16 19 1 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26").countDown())
        assertEquals(26, Deck("27 4 7 10 13 16 19 1 25 28 3 6 9 12 15 18 21 24 22 2 5 8 11 14 17 20 23 26").countDown())
        assertEquals(26, Deck("28 4 7 10 13 16 19 1 25 27 3 6 9 12 15 18 21 24 22 2 5 8 11 14 17 20 23 26").countDown())


    }
    @Test
    fun testSwapJokerA() {
        val deck = Deck("27 1 2")
        deck.swapJokerA()
        checkForward(deck, "1 27 2")
        checkBackward(deck, "1 27 2")
        deck.swapJokerA()
        checkForward(deck, "1 2 27")
        checkBackward(deck, "1 2 27")
        deck.swapJokerA()
        checkForward(deck, "27 2 1")
        checkBackward(deck,"27 2 1")
    }

    @Test
    fun testSwapJokerB() {
        val deck = Deck("28 1 2 3 4")
        deck.swapJokerB()
        checkForward(deck,"1 2 28 3 4")
        checkBackward(deck,"1 2 28 3 4")
        deck.swapJokerB()
        checkForward(deck,"1 2 3 4 28")
        checkBackward(deck,"1 2 3 4 28")
        deck.swapJokerB()
        checkForward(deck, "2 28 3 4 1")
        checkBackward(deck, "2 28 3 4 1")
        deck.swapJokerB()
        checkForward(deck,"2 3 4 28 1")
        checkBackward(deck,"2 3 4 28 1")
        deck.swapJokerB()
        checkForward(deck,"28 3 4 1 2")
        checkBackward(deck,"28 3 4 1 2")
    }

    @Test
    fun testTripleCut() {
        var deck = Deck("1 2 27 3 4 28 5 6")
        deck.tripleCut()
        checkForward(deck, "5 6 27 3 4 28 1 2")
        checkBackward(deck, "5 6 27 3 4 28 1 2")

        deck = Deck("27 1 2 3 4 28 5 6")
        deck.tripleCut()
        checkForward(deck, "5 6 27 1 2 3 4 28")
        checkBackward(deck, "5 6 27 1 2 3 4 28")

        deck = Deck("5 6 27 1 2 3 4 28")
        deck.tripleCut()
        checkForward(deck,"27 1 2 3 4 28 5 6")
        checkBackward(deck,"27 1 2 3 4 28 5 6")

        deck = Deck("7 27 1 2 3 4 28 5 6")
        deck.tripleCut()
        checkForward(deck,"5 6 27 1 2 3 4 28 7")
        checkBackward(deck,"5 6 27 1 2 3 4 28 7")

        deck = Deck("5 6 27 1 2 3 4 28 7")
        deck.tripleCut()
        checkForward(deck,"7 27 1 2 3 4 28 5 6")
        checkBackward(deck,"7 27 1 2 3 4 28 5 6")

        deck = Deck("7 27 1 2 3 4 28 6")
        deck.tripleCut()
        checkForward(deck,"6 27 1 2 3 4 28 7")
        checkBackward(deck,"6 27 1 2 3 4 28 7")

        deck = Deck("6 27 1 2 3 4 28 7")
        deck.tripleCut()
        checkForward(deck,"7 27 1 2 3 4 28 6")
        checkBackward(deck,"7 27 1 2 3 4 28 6")
    }
}
