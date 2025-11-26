package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.CartItem
import com.example.backend_foodhub.repository.CartItemRepository
import com.example.backend_foodhub.repository.ProductRepository
import com.example.backend_foodhub.repository.UserRepository // Asumiendo que existe
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CartItemService(
    private val cartRepo: CartItemRepository,
    private val productRepo: ProductRepository,
    private val userRepo: UserRepository
) {

    fun getCartByUserId(userId: Long): List<CartItem> {
        return cartRepo.findByUserId(userId)
    }

    @Transactional
    fun addToCart(userId: Long, productId: Long, quantity: Int): CartItem {
        // 1. Verificar si ya existe ese producto en el carrito del usuario
        val existingItem = cartRepo.findByUserIdAndProductId(userId, productId)

        if (existingItem != null) {
            // Si existe, actualizamos la cantidad
            existingItem.quantity += quantity
            return cartRepo.save(existingItem)
        } else {
            // Si no existe, creamos uno nuevo
            val user = userRepo.findById(userId).orElseThrow { RuntimeException("User not found") }
            val product = productRepo.findById(productId).orElseThrow { RuntimeException("Product not found") }

            val newItem = CartItem(
                user = user,
                product = product,
                quantity = quantity
            )
            return cartRepo.save(newItem)
        }
    }

    fun updateQuantity(cartItemId: Long, newQuantity: Int): CartItem {
        val item = cartRepo.findById(cartItemId).orElseThrow { RuntimeException("Item not found") }
        item.quantity = newQuantity
        return cartRepo.save(item)
    }

    fun removeCartItem(cartItemId: Long) {
        cartRepo.deleteById(cartItemId)
    }

    @Transactional
    fun clearCart(userId: Long) {
        cartRepo.deleteByUserId(userId)
    }
}