class LinkedList<T : Any>: Collection<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set

    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)
    }

    override fun isEmpty(): Boolean = size == 0

    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }

        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (item in elements) {
            if (!contains(item)) return false
        }

        return true
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        } else {
            return head.toString()
        }
    }

    fun push(value: T): LinkedList<T> = apply {
        head = Node(value = value, next = head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    fun append(value: T): LinkedList<T> = apply {
        // 1
        if (isEmpty()) {
            push(value)
        } else {
            // 2
            val newNode = Node(value = value)
            tail!!.next = newNode

            // 3
            tail = newNode
            size++
        }
    }

    fun nodeAt(index: Int): Node<T>? {
        // 1
        var currentNode = head
        var currentIndex = 0

        // 2
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        // 1
        if (tail == afterNode) {
            append(value)
            return tail!!
        }
        // 2
        val newNode = Node(value = value, next = afterNode.next)
        // 3
        afterNode.next = newNode
        size++
        return newNode
    }

    fun pop(): T? {
        if (isEmpty()) return null

        val result = head?.value
        head = head?.next
        size--
        if (isEmpty()) {
            tail = null
        }

        return result
    }

    fun removeLast(): T? {
        // 1
        val head = head ?: return null
        // 2
        if (head.next == null) return pop()
        // 3
        size--

        // 4
        var prev = head
        var current = head

        var next = current.next
        while (next != null) {
            prev = current
            current = next
            next = current.next
        }
        // 5
        prev.next = null
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }

        node.next = node.next?.next
        return result
    }

}