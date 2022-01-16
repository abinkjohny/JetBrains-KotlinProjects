package tictactoe

fun printTable(cells: String) {
    println("---------")
    println("| ${cells[0]} ${cells[1]} ${cells[2]} |")
    println("| ${cells[3]} ${cells[4]} ${cells[5]} |")
    println("| ${cells[6]} ${cells[7]} ${cells[8]} |")
    println("---------")
}

fun main() {
    print("Enter cells:")
    val cells = readLine()!!

    printTable(cells)
}
