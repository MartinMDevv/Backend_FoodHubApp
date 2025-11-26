package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.Product
import com.example.backend_foodhub.repository.ProductRepository
import org.springframework.stereotype.Service
import jakarta.annotation.PostConstruct

@Service
class ProductService(private val repository: ProductRepository) {

    fun getAll(): List<Product> = repository.findAll()

    fun getById(id: Long): Product = repository.findById(id).orElseThrow { RuntimeException("No existe") }

    fun save(product: Product): Product = repository.save(product)

    fun delete(id: Long) = repository.deleteById(id)

    // Esto crea datos automáticamente al iniciar la app
    @PostConstruct
    fun initData() {
        if (repository.count() == 0L) {
            repository.save(Product(name = "Hamburguesa Cheese", price = 5900, description = "Doble carne", category = "Comida", imageUrl = "http://img..."))
            repository.save(Product(name = "Coca Cola", price = 1500, description = "Lata fria", category = "Bebida", imageUrl = "http://img..."))
        }
    }
}