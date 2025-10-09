/**
 * Bibliography:
 * 
 *  - https://www.baeldung.com/kotlin/convert-list-to-string
 */
fun main() {
    val deck = Deck("1 4 7 10 13 16 19 22 25 28 3 6 9 12 15 18 21 24 27 2 5 8 11 14 17 20 23 26")
    println(deck.getString(28))
    println(deck.getStringBackwards(28))
}
