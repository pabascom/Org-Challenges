package phil.homework.week2test

class Node(val name: String) {
    val children: MutableList<Node> = mutableListOf()

    fun print(depth: Int) {
        val o = StringBuilder()
        for (i in 0..depth) o.append('\t')
        o.append(name)
        println(o.toString())
        for (c in children) c.print(depth+1)
    }


    fun encode(codes: List<String>, register: HashMap<String, Node>) {
        for (c in codes) {
            if(register.containsKey(c)){
                this.children.add(register[c]!!)
            } else {
                val node = Node(c)
                children.add(node)
                register[c] = node
            }
        }
    }
}

class Tree() {
    lateinit var root: Node
    val register = HashMap<String, Node>()

    fun encode(input:String) {
        val codes = input.split(',')
        if(register.containsKey(codes[0])) {
            register[codes[0]]!!.encode(codes.subList(1,codes.size), register)
        } else {
            root = Node(codes[0])
            register[codes[0]] = root
            root.encode(codes.subList(1,codes.size), register)
        }
    }

    fun print(){
        root.print(0)
    }
}

class Org() {
    val roster = Tree()

    fun encode(chart: List<String>){
        for(string in chart){
            roster.encode(string)
        }
    }

    fun print(){
        roster.print()
    }
}

fun main() {
    val org = Org()

    org.encode(listOf(
        "B2,E5,F6",
        "A1,B2,C3,D4",
        "D4,G7,I9",
        "G7,H8"
    ))

    org.print()
}