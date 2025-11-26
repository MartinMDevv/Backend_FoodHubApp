package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.User
import com.example.backend_foodhub.service.AuthService
import org.springframework.web.bind.annotation.*

// Clase simple para recibir datos de login
data class LoginRequest(val email: String, val password: String)

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    // --- AUTH BÁSICO ---

    @PostMapping("/register")
    fun register(@RequestBody user: User): User {
        return authService.register(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): User {
        return authService.login(req.email, req.password)
    }

    // --- CRUD DE USUARIOS ---

    // GET: obtener todos los usuarios
    @GetMapping
    fun getAll(): List<User> = authService.getAll()

    // GET: obtener un usuario por id
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User = authService.getById(id)

    // PUT: actualizar un usuario por id
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody user: User
    ): User = authService.update(id, user)

    // DELETE: eliminar un usuario por id
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        authService.delete(id)
    }
}
