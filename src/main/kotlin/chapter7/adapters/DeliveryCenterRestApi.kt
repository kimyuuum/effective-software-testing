package chapter7.adapters

import chapter7.domain.ShoppingCart
import chapter7.ports.DeliveryCenter
import java.time.LocalDate

class DeliveryCenterRestApi : DeliveryCenter {
    override fun deliver(cart: ShoppingCart): LocalDate {
        return LocalDate.now()
    }
}
