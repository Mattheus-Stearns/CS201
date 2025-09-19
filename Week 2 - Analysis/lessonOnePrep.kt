/*

    Bibliography:

    - https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/join-to-string.html
    - https://www.programiz.com/kotlin-programming/constructors
    - https://www.geeksforgeeks.org/kotlin/kotlin-inheritance/
    - https://www.geeksforgeeks.org/kotlin/kotlin-interfaces/

*/

open class Person(val name: String)

class Student(name: String,
            classYear: Int, 
            var major1: String = "Undeclared", 
            var major2: String = "Undeclared", 
            minor: List<String>) : Person(name) {
    
    fun declareMajor(major: String){
        when {
            major1 == "Undeclared" -> major1 = major
            major2 == "Undeclared" -> major2 = major
            else -> println("You have too many majors! Talk to your advisor.")
        }
    }
}

interface RegistrarRecord{
	var courses: List<String>
	fun printCourses()
}

class StudentRecord: RegistrarRecord {
    override var courses: List<String>
    override fun printCourses() {
        super.print()
        println("I'm currently taking these courses: ${courses.joinToString()}")
    }
}

class ProfessorRecord: RegistrarRecord {
    override var courses: List<String>
    override fun printCourses() {
        super.print()
        println("I'm currently teaching these courses: ${courses.joinToString()}")
    }
}
