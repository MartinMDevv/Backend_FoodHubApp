package com.example.backend_foodhub.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false)
    val price: Int = 0,

    @Column(nullable = false, name = "image_url")
    val imageUrl: String = "",

    @Column(nullable = false)
    val category: String = "Otros",

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false)
    var available: Boolean = true
)
