/**
 * Bibliography:
 * 
 *  - https://www.baeldung.com/kotlin/convert-list-to-string
 *  - https://kotlinlang.org/docs/equality.html
 * 
 *  Collaboration Statement:
 *  - N/A
 */

class Deck(initialCardOrdering: String) {

    var cards: Node? = null
    var numCards = 0

    init {
        val cardNumberList = initialCardOrdering.split(' ')
        if (cardNumberList.isEmpty()) {
            throw Exception("Deck empty.")
        }

        numCards = cardNumberList.size
        lateinit var current: Node
        for (cardString in cardNumberList) {
            val cardValue = cardString.toInt()
            if (cards == null) {
                // First node, special case
                val firstNode = Node(cardValue, null, null)
                cards = firstNode
                current = firstNode
            } else {
                // Any other node
                val card = Node(cardValue, current, null)
                current.next = card
                current = card
            }
        }

        // Wrap last card around
        current.next = cards
        cards!!.previous = current
    }

    val jokerA: Int = 27
    val jokerB: Int = 28

    /**
     * this fun 
     * @return a space deliminited string consisting of 
     * @param n numbers of the deck, rotating clockwise around the deck
     */
    fun getString(n: Int): String {
        var curr = cards
        var count: Int = 0
        val cardsAccessed = mutableListOf<Int?>()
        while (count < n) {
            cardsAccessed.add(curr?.value)
            curr = curr?.next
            count++
        }
        return cardsAccessed.joinToString(separator = " ")
    }

    /**
     * this fun 
     * @return a space deliminited string consisting of 
     * @param n numbers of the deck, rotating counter-clockwise around the deck
     */
    fun getStringBackwards(n: Int): String {
        var curr = cards
        var count: Int = 0
        val cardsAccessed = mutableListOf<Int?>()
        while (count < n) {
            curr = curr?.previous
            cardsAccessed.add(curr?.value)
            count++
        }
        return cardsAccessed.joinToString(separator = " ")
    }

    /**
     * This function looks at the top cards value, and counts down that many cards,
     * @return the value of the card that it counted to
     */
    fun countDown(): Int {
        var topCard: Int = cards!!.value
        if (topCard == 28) {
            topCard--
        }
        var curr = cards
        var count: Int = 0
        while (count < topCard) {
            curr = curr?.next
            count++
        }
        val cardFound: Int = curr!!.value
        return cardFound
    }

    /**
     * This function swaps two cards positions, given the 
     * @param current node and
     * @param neighbor node
     */
    fun exchange(curr: Node?, neighbor: Node?) {
        if (curr == null || neighbor == null) { return }
        if (neighbor != curr.next) { return }

        val beforeCurr = curr?.previous
        val afterNeighbor = neighbor?.next
        
        beforeCurr?.next = neighbor
        neighbor?.previous = beforeCurr

        curr?.next = afterNeighbor
        afterNeighbor?.previous = curr

        neighbor?.next = curr
        curr?.previous = neighbor

        if (cards === curr) {
            cards = neighbor
        } else if (cards === neighbor) {
            cards = curr
        }
    }

    /**
     * This function finds the next joker, given the
     * @param jokerType that we want to find
     * @param typeMatters whether we actually care about what type of joker we find
     * @param head our starting point
     */
    fun findJoker(jokerType: Int, typeMatters: Boolean, head: Node?) : Node? {
        var curr = head
        if (typeMatters) {
            while (curr?.value != jokerType) {
                curr = curr?.next
            }
        } else {
            while (curr?.value != jokerA && curr?.value != jokerB) {
                curr = curr?.next
            }
        }
        return curr
    }

    /**
     * This function finds the first 27
     * then exchanges it with the card after it in the deck
     */
    fun swapJokerA() {
        
        var curr = findJoker(jokerA, true, cards)
        val neighbor = curr?.next ?: cards
        
        exchange(curr, neighbor)
    }

    /**
     * This function finds the first 28
     * then exchanges it twice
     */
    fun swapJokerB() {
        var curr = findJoker(jokerB, true, cards)

        val firstNeighbor = curr?.next ?: cards
        exchange(curr, firstNeighbor)

        val secondNeighbor = curr?.next ?: cards
        exchange(curr, secondNeighbor)
    }


    /**
     * This method swaps all of the cards 'above' the first joker (before it)
     * with all of the cards 'below' the second joker (after it)
     */
    fun tripleCut() {
        if (cards == null) { return }

        val firstJoker = findJoker(jokerA, false, cards) ?: return
        val secondJoker = findJoker(jokerA, false, firstJoker.next) ?: return
        
        if (cards == firstJoker && cards?.previous == secondJoker) { return }
        
        val head = cards!!
        val tail = head.previous!!
        
        val segment1Head = if (head == firstJoker) { null } else { head }
        val segment1Tail = if (segment1Head == null) { null } else { firstJoker.previous }
        
        val segment2Head = firstJoker
        val segment2Tail = secondJoker
        
        val segment3Head = if (secondJoker == tail) { null } else { secondJoker.next }
        val segment3Tail = if (segment3Head == null) { null } else { tail } 
        
        if (segment1Head != null) {
            segment1Tail!!.next = null
            segment1Head.previous = null
        }
        segment2Head.previous = null
        segment2Tail.next = null
        if (segment3Head != null) {
            segment3Head.previous = null
            segment3Tail!!.next = null
        }
        
        val newHead = when {
            segment3Head != null -> {
                segment3Tail!!.next = segment2Head
                segment2Head.previous = segment3Tail
                segment3Head
            }
            else -> segment2Head
        }
        
        val newTail = when {
            segment1Head != null -> {
                segment2Tail.next = segment1Head
                segment1Head.previous = segment2Tail
                segment1Tail!!
            }
            else -> segment2Tail
        }
        
        newHead.previous = newTail
        newTail.next = newHead
        
        cards = newHead
    }
}
/** Reflection
 *  I really hated this assignment. At a high level, drawing on a whiteboard, I can certainly conceptually understand the methods of doubly linked lists and implement
 * them in certain, simpler, cases (e.g. every single function except tripleCut) but then there are times when I try to translate the whiteboard understanding, keeping track of 
 * every single pointer in every node and making sure that they are accounted for, and then the computer gets my code and just makes me question.... life. Like the interpreter-compiler
 * combo that reads my lines was built by a human and they know what I want to do, but find some technicality that in the transposition of a subsection of a circular doubly linked list
 * the pointers in between the values that arent being changed also needs to be carefully monitored. At some point I figured that it was probably just easier to cut everything away, and then
 * compose the deck like it would have been done in real life. *breathes in* *breathes out* Ok. Fine. Apart from triplecut() I feel relatively confident in my doubly linked list expertise. 
 * 
 * I spent 12 hours on this assignment. 
 */