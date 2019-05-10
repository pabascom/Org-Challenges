package phil.homework.week2test

import java.util.*

fun encodeString(inputString: String): String{
    val o = StringBuilder()

    val numberStack = Stack<Char>()

    fun disgorgeStack() {
        while(!numberStack.empty()){
            o.append(numberStack.pop())
        }
    }

    for(c: Char in inputString){
        if(c in ('0'..':')) numberStack.push(c)
        else {
            if (!numberStack.empty()) disgorgeStack()
            when (c) {
                'a', 'A' -> o.append('1')
                'e', 'E' -> o.append('2')
                'i', 'I' -> o.append('3')
                'o', 'O' -> o.append('4')
                'u', 'U' -> o.append('5')
                'y', 'Y' -> o.append(' ')
                ' ' -> o.append('y')
                else -> {
                    val i = c.toInt()
                    when (i) {
                        in (66..90) -> o.append((i + 31).toChar())
                        in (98..122) -> o.append((i - 1).toChar())
                        else -> o.append(c)
                    }
                }
            }
        }
    }
    if(!numberStack.empty()) disgorgeStack()
    return o.toString()
}

fun main() {
    println(encodeString("Hello World!"))
    println(encodeString("These are some numbers: 1234, 5678, 920"))
    println(encodeString("For a good time, call Jenny: (860) 867-5309"))
}