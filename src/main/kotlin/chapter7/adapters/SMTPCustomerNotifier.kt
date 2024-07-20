package chapter7.adapters

import chapter7.domain.ShoppingCart
import chapter7.ports.CustomerNotifier

class SMTPCustomerNotifier : CustomerNotifier {
    override fun sendEstimatedDeliveryNotification(cart: ShoppingCart) {
        TODO("Not yet implemented")
    }
}
