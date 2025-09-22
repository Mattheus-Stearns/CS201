/**
 * @param employee, @param manager
 * @return instantiated objects
 * 
 * How to run:
 * 
 * kotlinc employee.kt manager.kt main.kt -include-runtime -d app.jar
 * /opt/homebrew/bin/kotlin -classpath app.jar MainKt
 */

fun main() {
    val e = Employee("Alice", 1, "Engineer")
    val m = Manager("Bob", 42, "Lead", 6)

    println(e.getUser())
    println(m.getUser())
}