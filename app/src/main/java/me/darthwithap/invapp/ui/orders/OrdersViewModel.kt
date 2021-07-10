package me.darthwithap.invapp.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.darthwithap.invapp.data.models.Godown
import me.darthwithap.invapp.data.models.GodownOrder
import me.darthwithap.invapp.data.models.OrderProductItem
import kotlin.random.Random

class OrdersViewModel : ViewModel() {


    private var _godowns: MutableLiveData<List<Godown>> = MutableLiveData()
    val godowns: LiveData<List<Godown>> = _godowns
    private val godownList: MutableList<Godown> = mutableListOf()

    private var _currentGodown: MutableLiveData<Godown> = MutableLiveData()
    val currentGodown: LiveData<Godown> = _currentGodown

    // Todo chip Implementation from recipe app
    // Todo pass godown order here!
    fun getGodowns() {
        godownList.clear()
        val rand = Random.nextInt(1, 5)
        for (i in 1..rand) {
            godownList.add(
                Godown(
                    i.toString(), "Godown $i", generateOrders()
                )
            )
        }
        _godowns.postValue(godownList)
    }

    private fun generateOrders(): List<GodownOrder>? {
        val rand = Random.nextInt(1, 4)
        val orders: MutableList<GodownOrder> = mutableListOf()
        for (i in 0..rand) {
            orders.add(i, GodownOrder("Customer $i", "username $i", generateProducts()))
        }
        return orders
    }

    private fun generateProducts(): List<OrderProductItem> {
        val rand = Random.nextInt(1, 3)
        val products: MutableList<OrderProductItem> = mutableListOf()
        for (i in 0..rand) {
            products.add(
                i, OrderProductItem(
                    "Product $i",
                    "Code $i", Random.nextFloat() * 10000,
                    brand = "Brand $i",
                    qty = Random.nextInt(1, 100),
                    selected = Random.nextBoolean()
                )
            )
        }
        return products
    }

}