/**
 * Mattheus Stearns
 * 
 * Bibliography:
 *  - https://kotlinlang.org/docs/kotlin-tour-collections.html#map
 * 
 * Collaboration Policy: Mattheus Stearns (two days younger)
 */


/**
 * WordCountTree class contains word counts within a provided series of strings.
 * Each node of the WordCountTree (WCT) contains a count. The children of a node
 * are all of the characters that could come after the node's character to
 * represent a word.
 */
class WordCountTree {
    // Nobody else needs to see how we internally store nodes, so
    // we keep the data class private
    private data class Node(
        var count: Int = 0,
        val children: MutableMap<Char, Node> = mutableMapOf<Char, Node>())
    
    // Store a pointer to the root node in the tree
    private var root: Node = Node()

    /**
     * Returns a string representation of the tree.
     */
    override fun toString(): String {
        return root.toString()
    }

    /**
     * Adds 1 to the existing count for given word, 
     * or adds given word to the WordCountTree with a count of 1 
     * if it is not already present.
     * Implementation must be recursive, not iterative.
     */
    fun incrementCount(word: String) {
        countRecursive(root, word, 0, true)
    }
    
    /**
     * This is a helper function that implements both getCount() and incrementCount() with
     * @param curr keeping track of the current node
     * @param word keeping track of what word were indexing into
     * @param index keeping track of where in the word we are
     * @param incrementOrGet determining what function called this helper function and
     * @return int in the case if it being getCount()
     */
    private fun countRecursive(curr: Node?, word: String, index: Int, incrementOrGet: Boolean) : Int{
        if (curr == null) { return 0 }
        
        if (index == word.length) {
            if (incrementOrGet) curr.count++
            return curr.count
        }

        val char = word[index]
        val next = if (incrementOrGet) {curr.children.getOrPut(char) { Node() } } else { curr.children[char] }

        return countRecursive(next, word, index + 1, incrementOrGet)
    }

    /**
     * Returns the count of word. Returns 0 if word is not present.
     * Implementation must be recursive, not iterative.
     */
    fun getCount(word: String): Int {
        return countRecursive(root, word, 0, false)
    }

    /**
     * Returns true if word is stored in this WordCountTree
     * with a count greater than 0, and false otherwise.
     * Implementation must be recursive, not iterative.
     */
    fun contains(word: String): Boolean {
        return (countRecursive(root, word, 0, false) > 0)
    }

    /**
     * Returns a MutableMap of all words in WordCountTree that
     * start with the given prefix, mapped to their counts.
     * If prefix is not present, returns an empty MutableMap.
     */
    fun getAutocompletionMap(prefix: String): MutableMap<String, Int> {
        val result = mutableMapOf<String, Int>()
        val prefixNode = findNode(root, prefix, 0)
        collectWords(prefixNode, prefix, result)
        return result
    }

    /**
     * This helper function iterates through the whole Trie until it gets to the end of the prefix with
     * @param curr keeping track of the current node
     * @param prefix keeping track of what prefix we are indexing into
     * @param index keeping track of where in the prefix we are and
     * @return the latest node in the prefix
     */
    private fun findNode(curr: Node?, prefix: String, index: Int): Node? {
        if (curr == null) { return null }
        if (index == prefix.length) { return curr }

        val char = prefix[index]
        return findNode(curr.children[char], prefix, index + 1)
    }

    /**
     * This helper function loops over all of the children from the last node in the prefix
     * @param curr keeping track of the children nodes that we are recursing over
     * @param prefix keeping track of what the prefix so that we can index into result with it
     * @param result being an empty map that we recursively add to 
     */
    private fun collectWords(curr: Node?, prefix: String, result: MutableMap<String, Int>) {
        if (curr == null) { return }
        
        if (curr.count > 0) {
            result[prefix] = curr.count
        }

        for ((char, child) in curr.children) {
            collectWords(child, prefix + char, result)
        }
    }
}

/** Reflection:
 * I thought about it a while before I actually started so I already had the time to think about what ways to implement the code, also I was not anxious about 
 * including more parameters in my functions which made using the recursive functions a lot more intuitive and easier to work with. I was writing code for 1.5 hrs
 * but I was thinking about it during my work shift for 2 hrs. So it took me 3.5 hrs overall. 
 */