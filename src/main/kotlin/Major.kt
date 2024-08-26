class Major(val name: String) {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgAverage = if (averages.isEmpty()) 0.0 else averages.average()
        return Triple(minAverage, maxAverage, avgAverage)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val averages = students.mapNotNull { student ->
            student.courses
                .filter { it.name == courseName }
                .takeIf { it.isNotEmpty() }
                ?.let { it.sumByDouble { course -> course.grade * course.credits } / it.sumBy { course -> course.credits } }
        }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgAverage = if (averages.isEmpty()) 0.0 else averages.average()
        return Triple(minAverage, maxAverage, avgAverage)
    }
}