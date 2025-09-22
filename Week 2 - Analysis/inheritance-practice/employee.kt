open class Employee(val name: String,
                    val id: Int,
                    val jobTitle: String) {
    open fun getUser(): String {
        return "${id}: ${name} [${jobTitle}]"
    }
}