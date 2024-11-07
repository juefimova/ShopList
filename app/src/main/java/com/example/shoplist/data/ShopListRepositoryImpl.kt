package com.example.shoplist.data

import com.example.shoplist.domain.ShopItem
import com.example.shoplist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementID = 0

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementID++

        }

        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldSHopElement = shopList.get(shopItem.id)
        shopList.remove(oldSHopElement)
        addShopItem(shopItem)
    }

    override fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with $shopItemId was not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList() //gets an unchangable copy
    }
}