package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.Product
import com.example.backend_foodhub.repository.ProductRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productRepository: ProductRepository
) {

    @GetMapping
    fun getAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow { RuntimeException("Producto no encontrado con id $id") }
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        // ignoramos cualquier id que venga en el body
        val toSave = product.copy(id = 0)
        return productRepository.save(toSave)
    }

    /**
     * Actualiza un producto existente.
     */
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody product: Product
    ): Product {
        if (!productRepository.existsById(id)) {
            throw RuntimeException("Producto no encontrado con id $id")
        }

        val toSave = product.copy(id = id)
        return productRepository.save(toSave)
    }

    /**
     * Elimina un producto por id.
     */
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        if (!productRepository.existsById(id)) {
            throw RuntimeException("Producto no encontrado con id $id")
        }
        productRepository.deleteById(id)
    }
}
