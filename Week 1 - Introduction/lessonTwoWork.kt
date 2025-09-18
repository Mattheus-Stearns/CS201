fun calculateGPAmap(grades: List<String>): String {

    val gpaMap: Map<String, Double> = mapOf(
        "A" to 4.0,
        "A-" to 3.667,
        "B+" to 3.333,
        "B" to 3.0, "B-" to 2.667,
        "C+" to 2.333,
        "C" to 2.0,
        "C-" to 1.667,
        "D+" to 1.333,
        "D" to 1.0,
        "D-" to 0.667,
        "F" to 0.0
        )

    var totalGradePoints:Double = 0.0

    for (grade in grades){
        if (gpaMap[grade] != null){
            totalGradePoints += gpaMap.getValue(grade)
        }
    }
    
    val gpa = String.format("%.2f", totalGradePoints/grades.count())
    
    return gpa
}

fun main() {


    println("Student's GPA: ${calculateGPAmap(listOf("A-", "B+", "C", "A"))}")
    println("Student's GPA: ${calculateGPAmap(listOf("C-", "B", "D+", "A-"))}")
	println("Student's GPA: ${calculateGPAmap(listOf("F", "A-", "C+", "B-"))}")

}