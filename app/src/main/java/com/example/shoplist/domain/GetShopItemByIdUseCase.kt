package com.example.shoplist.domain

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemById(shopItemId: Int) : ShopItem {
        return shopListRepository.getShopItemById(shopItemId)

    }
}