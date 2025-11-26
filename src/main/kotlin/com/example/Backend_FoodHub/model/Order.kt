package com.example.backend_foodhub.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // Usuario que hizo la compra
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    // Items asociados a la orden
    @OneToMany
    @JoinColumn(name = "order_id") // FK en cart_items
    val items: MutableList<CartItem> = mutableListOf(),
    @Column(nullable = false)
    val total: Int = 0,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
