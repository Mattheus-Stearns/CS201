class Manager(
    name: String,
    id: Int,
    jobTitle: String,
    val teamSize: Int
) : Employee(name, id, jobTitle) {

    override fun getUser(): String {
        return super.getUser() + " | Team size: $teamSize"
    }
}