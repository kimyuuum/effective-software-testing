package chapter7.ports

import chapter7.domain.ShoppingCart

interface CustomerNotifier {
    fun sendEstimatedDeliveryNotification(cart: ShoppingCart)
}
