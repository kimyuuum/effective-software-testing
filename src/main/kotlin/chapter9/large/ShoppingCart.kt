package chapter9.large

class ShoppingCart {
    private val items: MutableList<Item> = mutableListOf()

    fun add(item: Item) {
        items.add(item)
    }

    fun getItems(): List<Item> {
//        return unmodifiableList(items)
        return items.toList()
    }

    fun numberOfItems(): Int {
        return items.size
    }
}
