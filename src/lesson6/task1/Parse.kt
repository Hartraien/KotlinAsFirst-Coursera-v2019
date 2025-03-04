@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.Integer.max
import java.util.*

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val mapOfMonths = mapOf(
        "января" to 1,
        "февраля" to 2,
        "марта" to 3,
        "апреля" to 4,
        "мая" to 5,
        "июня" to 6,
        "июля" to 7,
        "августа" to 8,
        "сентября" to 9,
        "октября" to 10,
        "ноября" to 11,
        "декабря" to 12,
    )
    val elems = str.split(" ")
    if (elems.size != 3)
        return ""
    val month = mapOfMonths.getOrDefault(elems[1], -1)
    val date = elems[0].toInt()
    val year = elems[2].toInt()
    return if (month == -1 || date > daysInMonth(month, year))
        ""
    else
        String.format("%02d.%02d.%04d", date, month, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val mapOfMonths = mapOf(
        1 to "января",
        2 to "февраля",
        3 to "марта",
        4 to "апреля",
        5 to "мая",
        6 to "июня",
        7 to "июля",
        8 to "августа",
        9 to "сентября",
        10 to "октября",
        11 to "ноября",
        12 to "декабря",
    )
    val elems = digital.split(".")
    if (elems.size != 3)
        return ""
    val month: String
    val date: Int
    val year: Int
    try {
        month = mapOfMonths.getOrDefault(elems[1].toInt(), "None")
        date = elems[0].toInt()
        year = elems[2].toInt()
    } catch (e: NumberFormatException) {
        return ""
    }

    return if (month == "None" || date > daysInMonth(elems[1].toInt(), year))
        ""
    else
        "$date $month $year"
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phoneNumber: String): String {
    val removeMinuses = "- "
    val phone = phoneNumber.filter { it !in removeMinuses }
    val removesquares = "()"
    val allowed = "0123456789$removesquares$removeMinuses"
    if (phone[0] !in allowed && phone[0] != '+')
        return ""
    for (elem in phone.substring(1 until phone.length))
        if (elem !in allowed)
            return ""
    val openIndex = phone.indexOf("(")
    val closeIndex = phone.indexOf(")")
    if (openIndex != -1 && closeIndex != -1)
        try {
            phone.substring(openIndex + 1 until closeIndex).toInt()
        } catch (e: NumberFormatException) {
            return ""
        }
    else if (openIndex != closeIndex)
        return ""
    return phone.filter { it !in removesquares }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val elems = jumps.split(" ")
    var result = -1
    for (elem in elems) {
        try {
            result = max(result, elem.toInt())
        } catch (e: NumberFormatException) {
            if (elem != "%" && elem != "-")
                return -1
        }
    }
    return result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val allowed = "+%-"
    val elems = jumps.split(" ")
    var result = -1
    var mem = -1
    for (elem in elems) {
        try {
            mem = elem.toInt()
        } catch (e: NumberFormatException) {
            if (elem == "+") {
                result = max(result, mem)
            } else {
                for (letter in elem) {
                    if (letter !in allowed) {
                        return -1
                    }
                }
            }
            mem = -1
        }
    }
    return result
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var state = 1
    var result = 0
    for (elem in expression.split(" ")) {
        when (state) {
            1, -1 -> {
                if (elem.any { !it.isDigit() })
                    throw IllegalArgumentException()
                result += state * elem.toInt()
                state = 0
            }
            0 -> {
                state = if (elem == "+")
                    1
                else if (elem == "-")
                    -1
                else
                    throw IllegalArgumentException()
            }
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val listOfWords = str.split(" ").map { it.toLowerCase() }
    var result = 0
    for (i in 1 until listOfWords.size) {
        if (listOfWords[i] == listOfWords[i - 1])
            return result
        result += listOfWords[i - 1].length + 1
    }
    return -1
}


/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    if (description == "")
        return ""
    val objects = description.split("; ").map { e ->
        val temp = e.split(" ")
        Pair(temp[0], temp[1].toDouble())
    }
    if (objects.isEmpty())
        return ""
    val result = objects.maxByOrNull { it.second }
    return result?.first ?: ""

}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val converter = mapOf(
        "I" to 1,
        "V" to 5,
        "X" to 10,
        "L" to 50,
        "C" to 100,
        "D" to 500,
        "M" to 1000,
    )
    var result = 0
    var maxVal = 1
    var neg = 0
    for (elem in roman.reversed()) {
        val value = converter.getOrDefault(elem.toString(), -1)
        if (value == -1)
            return -1
        if (value < maxVal) {
            if (neg == 1)
                return -1
            result -= value
            neg = 1
        } else {
            maxVal = value
            result += value
            neg = 0
        }
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun checkSymbols(commands: String): Boolean {
    val allowed = "<>+-[] "
    for (elem in commands)
        if (elem !in allowed)
            return false
    return true
}

fun constructPairsMap(commands: String): Map<Int, Int> {
    val stack = ArrayDeque<Int>()
    val result = mutableMapOf<Int, Int>()
    for (i in commands.indices) {
        try {
            if (commands[i] == '[')
                stack.push(i)
            else if (commands[i] == ']') {
                val pairVal = stack.pop()
                result[pairVal] = i
                result[i] = pairVal
            } else
                continue
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }
    if (stack.isNotEmpty())
        throw IllegalArgumentException()
    return result
}

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (!checkSymbols(commands))
        throw IllegalArgumentException()
    val mapCycles = constructPairsMap(commands)
    val cellsArray = Array<Int>(cells) { 0 }
    var position = cells / 2
    var commandNumber = 0
    var commandIndex = 0
    while (commandNumber < limit) {
        if (commandIndex >= commands.length)
            return cellsArray.toList()
        val command = commands[commandIndex]
        commandNumber++
        when (command) {
            '>' -> {
                if (position < cellsArray.size - 1)
                    position++
                else
                    throw IllegalStateException()
            }
            '<' -> {
                if (position != 0)
                    position--
                else
                    throw IllegalStateException()
            }
            '+' -> cellsArray[position] += 1
            '-' -> cellsArray[position] -= 1
            '[' -> if (cellsArray[position] == 0) commandIndex = mapCycles[commandIndex]!!
            ']' -> if (cellsArray[position] != 0) commandIndex = mapCycles[commandIndex]!!
        }
        commandIndex += 1
    }
    return cellsArray.toList()
}
