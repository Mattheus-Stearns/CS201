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

    fun swapJokerA() {
        var curr = cards
        while (curr?.value != 27) {
            curr = curr?.next
        }
        val neighbor = curr?.next
        val beforeCurr = curr.previous
        val afterNeighbor = neighbor?.next
        curr.previous = neighbor
        beforeCurr?.next = neighbor
        neighbor?.previous = beforeCurr
        curr.next = afterNeighbor
        afterNeighbor?.previous = curr
        neighbor?.next = curr
    }

    fun swapJokerB() {
    }

    fun tripleCut() {
    }
}
