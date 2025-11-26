package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.User
import com.example.backend_foodhub.service.AuthService
import org.springframework.web.bind.annotation.*

// Clase simple para recibir datos de login
data class LoginRequest(val email: String, val password: String)

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody user: User): User {
        return authService.register(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): User {
        return authService.login(req.email, req.password)
    }
}