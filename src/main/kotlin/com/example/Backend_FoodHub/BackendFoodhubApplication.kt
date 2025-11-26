package com.example.backend_foodhub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BackendFoodhubApplication

fun main(args: Array<String>) {
    runApplication<BackendFoodhubApplication>(*args)
    print("Exito")
}
