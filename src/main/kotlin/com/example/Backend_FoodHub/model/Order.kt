package com.example.backend_foodhub.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val userId: Long = 0, // Quién compró
    val total: Int = 0,
    val itemsSummary: String = "", // Ej: "2x Pizza, 1x Bebida"
    val status: String = "PENDING",
    val createdAt: LocalDateTime = LocalDateTime.now()
)