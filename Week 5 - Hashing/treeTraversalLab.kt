// Binary Search Tree operations
// Tree traversal in Kotlin
// Ported to Kotlin by Dave Musicant
// Adapted for in-class lab by Jean Salac

data class Node(var item: Int,
                var left: Node? = null,
                var right: Node? = null)

class BinaryTree {

    // Root of Binary Tree
    var root: Node? = null

    // Print Preorder Traversal
    fun preorder(node: Node?) {
        print(node?.item) 
        if (node?.left != null) {
            // process the left subtree
            preorder(node?.left)
        }  
        if (node?.right != null) {
            // process the right subtree
            preorder(node?.right)
        }
        return
    }

    // Print Inorder Traversal
    fun inorder(node: Node?) { 
        if (node?.left != null) {
            // process the left subtree
            inorder(node?.left)
        }
        print(node?.item)
        if (node?.right != null) {
            // process the right subtree
            inorder(node?.right)
        }
        return
    }

    // Print Postorder Traversal
    fun postorder(node: Node?) { 
        if (node?.left != null) {
            // process the left subtree
            postorder(node?.left)
        }  
        if (node?.right != null) {
            // process the right subtree
            postorder(node?.right)
        }
        print(node?.item)
        return
    }

}

// Driver Program to test above functions
fun main() {
    val tree = BinaryTree()

    // Tree Setup
    tree.root = Node(9)
    tree.root!!.left = Node(4)
    tree.root!!.right = Node(11)
    tree.root!!.left!!.left = Node(2)
    tree.root!!.left!!.right = Node(7)

    // Testing Preorder Traversal
    print("Preorder traversal: ")
    tree.preorder(tree.root)
    print("\n")

    // Testing Inorder Traversal
    print("Inorder traversal: ")
    tree.inorder(tree.root)
    print("\n")

    // Testing Postorder Traversal
    print("Postorder traversal: ")
    tree.postorder(tree.root)
    print("\n")

}