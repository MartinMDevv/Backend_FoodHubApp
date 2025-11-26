package com.example.backend_foodhub.repository

import com.example.backend_foodhub.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem, Long> {

    // Obtener todo el carrito de un usuario
    fun findByUserId(userId: Long): List<CartItem>

    // Buscar si ya existe un producto X en el carrito del usuario Y
    fun findByUserIdAndProductId(userId: Long, productId: Long): CartItem?

    // Borrar todo el carrito de un usuario (al comprar)
    fun deleteByUserId(userId: Long)
}