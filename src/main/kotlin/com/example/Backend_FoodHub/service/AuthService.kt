package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.User
import com.example.backend_foodhub.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(private val userRepository: UserRepository) {

    fun register(user: User): User {
        if (userRepository.findByEmail(user.email).isPresent) {
            throw RuntimeException("El email ya existe")
        }
        return userRepository.save(user)
    }

    fun login(email: String, pass: String): User {
        val user = userRepository.findByEmail(email).orElseThrow { RuntimeException("Usuario no encontrado") }
        if (user.password != pass) {
            throw RuntimeException("Contraseña incorrecta")
        }
        return user
    }
}