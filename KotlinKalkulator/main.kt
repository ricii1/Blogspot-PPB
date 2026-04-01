// ANSI color codes
const val RED = "\u001B[31m"
const val GREEN = "\u001B[32m"
const val YELLOW = "\u001B[33m"
const val RESET = "\u001B[0m"

fun main() {

    println("${GREEN}=== KALKULATOR SEDERHANA ===${RESET}")
    while(true){
        println("${YELLOW}---Perhitungan Baru---${RESET}")
        try {
            print("Masukkan angka pertama (atau ketik 'keluar' untuk tutup): ")
            val input1 = readLine()!!

            if (input1.lowercase() == "keluar") {
                println("${GREEN}Terima kasih telah menggunakan kalkulator!${RESET}")
                break
            }

            val num1 = input1.toDouble()
            print("Masukkan operator (+, -, *, /, mod): ")
            val operator = readLine()

            print("Masukkan angka kedua: ")
            val num2 = readLine()!!.toDouble()

            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 != 0.0) num1 / num2
                    else {
                        println("${RED}Error: Tidak bisa dibagi dengan nol!${RESET}")
                        continue
                    }
                }
                "mod" -> num1 % num2
                else -> {
                    println("${RED}Error: Operator tidak valid!${RESET}")
                    continue
                }
            }

            println("${GREEN}Hasil: $result${RESET}")
        } catch (e: NumberFormatException){
            println("${RED}Error: Input tidak valid! Harap masukkan angka, bukan huruf.${RESET}")
        } catch (e: Exception){
            println("${RED}Error: Terjadi kesalahan sistem (${e.message})${RESET}")
        }
    }
}
