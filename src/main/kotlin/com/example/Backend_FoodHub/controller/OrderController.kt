package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.Order
import com.example.backend_foodhub.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {

    /**
     * Crea una orden a partir del carrito del usuario indicado.
     * Ejemplo de llamada:
     *  POST /api/orders?userId=1
     */
    @PostMapping
    fun createOrder(@RequestParam userId: Long): Order {
        return orderService.createOrderFromCart(userId)
    }

    /**
     * Obtiene las órdenes de un usuario.
     * Ejemplo:
     *  GET /api/orders/user/1
     */
    @GetMapping("/user/{userId}")
    fun getOrdersByUser(@PathVariable userId: Long): List<Order> {
        return orderService.getOrdersByUser(userId)
    }
}
