fun main() {

    println("=== KALKULATOR SEDERHANA ===")
    while(true){
        println("---Perhitungan Baru---")
        try {
            print("Masukkan angka pertama (atau ketik 'keluar' untuk tutup): ")
            val input1 = readLine()!!

            if (input1.lowercase() == "keluar") {
                println("Terima kasih telah menggunakan kalkulator!")
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
                        println("Error: Tidak bisa dibagi dengan nol!")
                        continue
                    }
                }
                "mod" -> num1 % num2
                else -> {
                    println("Error: Operator tidak valid!")
                    continue
                }
            }

            println("Hasil: $result")
        } catch (e: NumberFormatException){
            println("Error: Input tidak valid! Harap masukkan angka, bukan huruf.")
        } catch (e: Exception){
            println("Error: Terjadi kesalahan sistem (${e.message})")
        }
    }
}
