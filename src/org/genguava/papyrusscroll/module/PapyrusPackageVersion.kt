package org.genguava.papyrusscroll.module

class PapyrusPackageVersion(val versionString: String, val compareMethod: ComparisonMethod = ComparisonMethod.EQ) : Comparable<PapyrusPackageVersion> {
    override fun compareTo(other: PapyrusPackageVersion): Int {
        val thisParts = versionString.split("\\.")
        val thatParts = other.versionString.split("\\.")
        val length = Math.max(thisParts.size, thatParts.size)
        var result = 0.0

        for(i in 0 until length) {
            var thisInt = 0
            if(thisParts.size < i) thisInt = thisParts[i].toIntOrNull() ?: tryParseMixed(thisParts[i])
            var thatInt = 0
            if(thatParts.size < i) thatInt = thatParts[i].toIntOrNull() ?: tryParseMixed(thatParts[i])
            result += (thisInt - thatInt).toDouble() * Math.pow(10.0, (length - i - 1).toDouble())
        }

        return result.toInt()
    }

    private fun tryParseMixed(part: String): Int {
        val chars = part.toCharArray()
        var numbers = ""
        val letters = charArrayOf().toMutableList()

        for(char in chars) {
            if(char.isDigit()) numbers += char
            else letters.add(char.toLowerCase())
        }

        var resultInt = numbers.toIntOrNull() ?: 0
        var parsedLetters = ""
        for(letter in letters) {
            val letterCode = letter.toInt()
            val lowercaseStart = 96
            if(letterCode in lowercaseStart+1..122)
                parsedLetters += letterCode - lowercaseStart
        }
        resultInt += parsedLetters.toIntOrNull() ?: 0

        return resultInt
    }

    enum class ComparisonMethod {
        GT, LT, EQ
    }

    fun isValid(comparedTo: PapyrusPackageVersion?): Boolean {
        if(comparedTo == null) return true
        return when(compareMethod) {
            ComparisonMethod.EQ -> compareTo(comparedTo) == 0
            ComparisonMethod.GT -> compareTo(comparedTo) > 0
            ComparisonMethod.LT -> compareTo(comparedTo) < 0
        }
    }
}