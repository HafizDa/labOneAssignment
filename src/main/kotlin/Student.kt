import java.io.Serializable

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalCredits = courses.sumBy { it.credits }
        return if (totalCredits == 0) 0.0 else courses.sumByDouble { it.grade * it.credits } / totalCredits
    }

    fun weightedAverage(year: Int): Double {
        val coursesOfYear = courses.filter { it.yearCompleted == year }
        val totalCredits = coursesOfYear.sumBy { it.credits }
        return if (totalCredits == 0) 0.0 else coursesOfYear.sumByDouble { it.grade * it.credits } / totalCredits
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val grades = courses.map { it.grade }
        val minGrade = grades.minOrNull() ?: 0.0
        val maxGrade = grades.maxOrNull() ?: 0.0
        return minGrade to maxGrade
    }
}