package chapter5

class PassingGrade {
    fun passed(grade: Float): Boolean {
        if (grade < 1.0 || grade > 10.0) {
            throw IllegalArgumentException()
        }
        return grade >= 5.0
    }
}
