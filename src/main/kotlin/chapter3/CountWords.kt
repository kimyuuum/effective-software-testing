package chapter3

class CountWords {
    fun count(str: String): Int {
        var words = 0
        var last = ' '

        for (i in str.indices) {
            if (str[i].isLetter() == false && (last == 's' || last == 'r') == true) {
                words++
            }

            last = str[i]
        }

        if (last == 'r' || last == 's') {
            words++
        }

        return words
    }
}
