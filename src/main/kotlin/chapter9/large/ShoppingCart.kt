package chapter9.large

import java.util.Collections.unmodifiableList

class ShoppingCart {
    private val items: MutableList<Item> = ArrayList()

    fun add(item: Item) {
        items.add(item)
    }

    fun getItems(): List<Item> {
        return unmodifiableList(items)
    }

    fun numberOfItems(): Int {
        return items.size
    }
}
