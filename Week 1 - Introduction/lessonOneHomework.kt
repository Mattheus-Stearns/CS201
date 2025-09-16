/*

Bibliography: https://kotlinlang.org/docs

*/

fun main() {
	// Using the main function to call the individual excercises, so that they are modularized

	gpaCalc()

	passOfGPA()
}

fun gpaCalc() {
    // Number of classes with each letter grade
    val numA = 12
    val numAminus = 14
    val numBplus = 6
    val numB = 4

    // Start of lines in wrong order

    var totalGradePoints :Double = 0.0
    val totalClasses = numA + numAminus + numBplus + numB

    totalGradePoints = totalGradePoints + numBplus * 3.333
    totalGradePoints = totalGradePoints + numB * 3.0
    totalGradePoints = totalGradePoints + numAminus * 3.667
    totalGradePoints = totalGradePoints + numA * 4.0

    val gpa = String.format("%.2f", totalGradePoints / totalClasses)

    // End of lines in wrong order

    println("Student's GPA: ${gpa}")
}

fun calculateLetterGPA(grades: List<String>) : String {
        
    var totalGradePoints:Double = 0.0

    for (grade in grades) {

        // I didn't want to have a massive when satement; so I broke it up into two

        // In order to do this cleanly, I wanted to split grade into two variables, and it threw an error when it was null
        // So I had to use null safety on my variables because it wasn't working unless I did that
        
        var letter: Char? = grade.getOrNull(0)
        var modifier: Char? = grade.getOrNull(1)

        when(letter){
		    'A' -> totalGradePoints += 4.0
		    'B' -> totalGradePoints += 3.0
		    'C' -> totalGradePoints += 2.0
		    'D' -> totalGradePoints += 1.0
		    'F' -> totalGradePoints += 0.0
    	}

    	when(modifier){
    		'+' -> totalGradePoints += 0.333
    		'-' -> totalGradePoints -= 0.333
    	}
    }

    val gpa = String.format("%.2f", totalGradePoints / grades.count())
    
    return gpa
}


fun passOfGPA() {
    println("Student's GPA: ${calculateLetterGPA(listOf("A-", "B+", "C", "A"))}")

    println("Student's GPA: ${calculateLetterGPA(listOf("C-", "B", "D+", "A-"))}")

    println("Student's GPA: ${calculateLetterGPA(listOf("F", "A-", "C+", "B-"))}")

 }