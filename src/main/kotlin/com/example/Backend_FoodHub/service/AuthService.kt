package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.User
import com.example.backend_foodhub.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(private val userRepository: UserRepository) {

    // --- REGISTRO / LOGIN ---

    fun register(user: User): User {
        if (userRepository.findByEmail(user.email).isPresent) {
            throw RuntimeException("El email ya existe")
        }
        return userRepository.save(user)
    }

    fun login(email: String, pass: String): User {
        val user = userRepository.findByEmail(email)
            .orElseThrow { RuntimeException("Usuario no encontrado") }

        if (user.password != pass) {
            throw RuntimeException("Contraseña incorrecta")
        }
        return user
    }

    // --- CRUD DE USUARIOS ---

    fun getAll(): List<User> = userRepository.findAll()

    fun getById(id: Long): User =
        userRepository.findById(id).orElseThrow { RuntimeException("Usuario no encontrado") }

    fun update(id: Long, req: User): User {
        val existing = getById(id)

        // Actualizamos TODOS los campos editables
        val updated = existing.copy(
            name = req.name,
            email = req.email,
            password = req.password,
            role = req.role
        )

        return userRepository.save(updated)
    }

    fun delete(id: Long) {
        if (!userRepository.existsById(id)) {
            throw RuntimeException("Usuario no encontrado")
        }
        userRepository.deleteById(id)
    }
}
