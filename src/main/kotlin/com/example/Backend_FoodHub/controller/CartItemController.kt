package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.CartItem
import com.example.backend_foodhub.service.CartItemService
import org.springframework.web.bind.annotation.*

// DTO corregido y limpio
data class AddToCartRequest(
    val userId: Long,
    val productId: Long,
    val quantity: Int
)

@RestController
@RequestMapping("/api/cart")
class CartItemController(private val cartService: CartItemService) {

    // GET /api/cart/{userId} -> Obtener carrito
    // Corregido: {userId} en lugar de (userid)
    @GetMapping("/{userId}")
    fun getCart(@PathVariable userId: Long): List<CartItem> {
        return cartService.getCartByUserId(userId)
    }

    // POST /api/cart/add -> Agregar producto
    @PostMapping("/add")
    fun addToCart(@RequestBody req: AddToCartRequest): CartItem {
        return cartService.addToCart(req.userId, req.productId, req.quantity)
    }

    // PUT /api/cart/{itemId}?quantity=5 -> Actualizar cantidad
    // Corregido: {itemId} y limpieza de sintaxis
    @PutMapping("/{itemId}")
    fun updateQuantity(
        @PathVariable itemId: Long,
        @RequestParam quantity: Int
    ): CartItem {
        return cartService.updateQuantity(itemId, quantity)
    }

    // DELETE /api/cart/{itemId} -> Eliminar un item
    // Corregido: {itemId} y cierre correcto de paréntesis
    @DeleteMapping("/{itemId}")
    fun deleteItem(@PathVariable itemId: Long) {
        cartService.removeCartItem(itemId)
    }
}