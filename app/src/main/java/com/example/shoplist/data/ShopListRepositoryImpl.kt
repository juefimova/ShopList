package com.example.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {


    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>(Comparator<ShopItem> { p0, p1 -> p0.id.compareTo(p1.id)})
    //мы указали что сортируем по id

    private var autoIncrementID = 0
//ЗДЕСЬ РАЗОБРАТЬСЯ
    init {
        for(i in 0 until 10) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementID++

        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldSHopElement = getShopItemById(shopItem.id) //get(shopItem.id)
        shopList.remove(oldSHopElement)
        addShopItem(shopItem) //тут у нас и так addShopItem wiec не нужен updateList()
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with $shopItemId was not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    //метод для обновления объекта
    private fun updateList() {
        shopListLD.value = shopList.toList() //toList() неизменяемая копия

    }
}