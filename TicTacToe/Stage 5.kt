package tictactoe

fun printTable(cells: String) {
    println("---------")
    println("| ${cells[0]} ${cells[1]} ${cells[2]} |")
    println("| ${cells[3]} ${cells[4]} ${cells[5]} |")
    println("| ${cells[6]} ${cells[7]} ${cells[8]} |")
    println("---------")
}

fun setRow(cells: String, row: Int): String {
    return "${cells[row * 3]}${cells[row * 3 + 1]}${cells[row * 3 + 2]}"
}

fun setCol(cells: String, col: Int): String {
    return "${cells[col]}${cells[col + 3]}${cells[col + 6]}"
}

// Suppose if one row is owned by one player check
// the other two rows are NOT owned by the other player
fun valuateRows(cells: String): String? {
    for (row in 0..2) {
        if (setRow(cells, row) == "XXX") {
            for (otherRow in 0..2) {
                if (row != otherRow) {
                    if (setRow(cells, otherRow) == "OOO") {
                        return "Out of Question!"
                    }
                }
            }
            return "X wins"
        }
        else if (setRow(cells, row) == "OOO") {
            for (otherRow in 0..2) {
                if (row != otherRow) {
                    if (setRow(cells, otherRow) == "XXX") {
                        return "Out of Question!"
                    }
                }
            }
            return "O wins"
        }
    }
    return null
}

// Suppose if one col is owned by one player check
// the other two cols are NOT owned by the other player
fun valuateColumns(cells: String): String? {
    for (col in 0..2) {
        if (setCol(cells, col) == "XXX") {
            for (otherCol in 0..2) {
                if (col != otherCol) {
                    if (setCol(cells, otherCol) == "OOO") {
                        return "Out of Question!"
                    }
                }
            }
            return "X wins"
        }
        else if (setCol(cells, col) == "OOO") {
            for (otherCol in 0..2) {
                if (col != otherCol) {
                    if (setCol(cells, otherCol) == "XXX") {
                        return "Out of Question!"
                    }
                }
            }
            return "O wins"
        }
    }
    return null
}

fun valuateDiagonals(cells: String): String? {
    if (cells[0] == cells[4] && cells[4] == cells[8]) { // top left to bottom right
        return "${cells[0]} wins"
    }
    if (cells[2] == cells[4] && cells[4] == cells[6]) { // top right to bottom left
        return "${cells[2]} wins"
    }
    return null
}

fun valuateState(cells: String) {
    val countX = cells.filter { it == 'X' }.length
    val countO = cells.filter { it == 'O' }.length
    if (kotlin.math.abs(countO - countX) > 1) {
        println("Out of Question!")
        return
    }

    val rowResult = valuateRows(cells)
    if (rowResult != null) {
        println(rowResult)
        return
    }

    val colResult = valuateColumns(cells)
    if (colResult != null) {
        println(colResult)
        return
    }

    val diagResult = valuateDiagonals(cells)
    if (diagResult != null) {
        println(diagResult)
        return
    }

    if (cells.contains("_")) {
        println("This not the End!!")
    } else {
        println("Draw")
    }
}

// get user input
fun userInput(cells: String): String {
    while (true) {
        println("Enter the location:")

        val input = readLine()!!.trim().split(" ")
        if (input.size != 2) {
            println("You should enter numerals!")
            continue
        }
        try {
            val row = Integer.parseInt(input[0])
            val col = Integer.parseInt(input[1])

            if (row in 1..3 && col in 1..3) {
                val index =  3 * (row - 1) + (col - 1)
                val cell = cells[index]
                if (cell == '_') {
                    return cells.replaceRange(index..index, "X")
                } else if (cell in "XO") {
                    println("This cell is occupied! Choose another one!")
                }
            } else {
                println("Location should be from 1 to 3!")
            }
        } catch (ex: NumberFormatException) {
            println("You should enter numerals!")
        }
    }
}

fun togglePlayer(player: String): String {
    return when (player) {
        "X" -> "O"
        "O" -> "X"
        else -> throw Exception("??? A third player found!!")
    }
}


fun main() {
    var cells = "_________" // empty field
    var player = "X"

    printTable(cells)

    do {
        cells = userInput(cells, player)
        player = togglePlayer(player)
        printTable(cells)

        val end = valuateState(cells)
    } while (!end)
}
