package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.Order
import com.example.backend_foodhub.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(private val service: OrderService) {

    @PostMapping
    fun create(@RequestBody order: Order) = service.createOrder(order)

    @GetMapping("/user/{userId}")
    fun getByUser(@PathVariable userId: Long) = service.getOrdersByUser(userId)
}