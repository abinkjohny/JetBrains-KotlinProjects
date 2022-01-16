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
                        return "Out od Question"
                    }
                }
            }
            return "X wins"
        }
        else if (setRow(cells, row) == "OOO") {
            for (otherRow in 0..2) {
                if (row != otherRow) {
                    if (setRow(cells, otherRow) == "XXX") {
                        return "Out od Question"
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
                        return "Out od Question"
                    }
                }
            }
            return "X wins"
        }
        else if (setCol(cells, col) == "OOO") {
            for (otherCol in 0..2) {
                if (col != otherCol) {
                    if (setCol(cells, otherCol) == "XXX") {
                        return "Out od Question"
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
        println("Out od Question")
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

fun main() {
    print("Enter cells:")
    val cells = readLine()!!

    printTable(cells)

    valuateState(cells)
}
