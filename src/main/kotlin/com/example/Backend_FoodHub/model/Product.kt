package com.example.backend_foodhub.model

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String = "",
    val price: Int = 0,
    val description: String = "",
    val imageUrl: String = "",
    val category: String = "General"
)