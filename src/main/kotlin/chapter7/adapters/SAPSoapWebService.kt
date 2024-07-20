package chapter7.adapters

import chapter7.domain.ShoppingCart
import chapter7.ports.SAP

class SAPSoapWebService : SAP {
    override fun cartReadyForDelivery(cart: ShoppingCart) {
        TODO("Not yet implemented")
    }
}
