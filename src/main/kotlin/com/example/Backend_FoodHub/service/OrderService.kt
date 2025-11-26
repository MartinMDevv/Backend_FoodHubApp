package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.Order
import com.example.backend_foodhub.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository) {

    fun createOrder(order: Order): Order = repository.save(order)

    fun getOrdersByUser(userId: Long): List<Order> = repository.findByUserId(userId)
}