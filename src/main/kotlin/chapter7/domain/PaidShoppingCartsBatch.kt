package chapter7.domain

import chapter7.ports.CustomerNotifier
import chapter7.ports.DeliveryCenter
import chapter7.ports.SAP
import chapter7.ports.ShoppingCartRepository

class PaidShoppingCartsBatch(
    private val db: ShoppingCartRepository,
    deliveryCenter: DeliveryCenter,
    notifier: CustomerNotifier,
    sap: SAP,
) {
    private val deliveryCenter: DeliveryCenter
    private val notifier: CustomerNotifier
    private val sap: SAP

    init {
        this.deliveryCenter = deliveryCenter
        this.notifier = notifier
        this.sap = sap
    }

    fun processAll() {
        val paidShoppingCarts = db.cartsPaidToday()
        for (cart in paidShoppingCarts) {
            val estimatedDayOfDelivery = deliveryCenter.deliver(cart)
            cart.markAsReadyForDelivery(estimatedDayOfDelivery)
            db.persist(cart)

            // send e-mail
            notifier.sendEstimatedDeliveryNotification(cart)

            // notify SAP
            sap.cartReadyForDelivery(cart)
        }
    }
}
