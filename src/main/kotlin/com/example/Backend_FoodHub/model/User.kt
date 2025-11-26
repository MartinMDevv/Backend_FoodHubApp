package com.example.backend_foodhub.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val email: String = "",

    val password: String = "", // En producción esto debería ir encriptado
    val name: String = "",
    val role: String = "CLIENT" // ADMIN o CLIENT
)