package com.example.backend_foodhub.controller

import com.example.backend_foodhub.model.Product
import com.example.backend_foodhub.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val service: ProductService) {

    @GetMapping
    fun getAll() = service.getAll()

    @PostMapping
    fun create(@RequestBody p: Product) = service.save(p)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody p: Product): Product {
        val existing = service.getById(id)
        val updated = existing.copy(name = p.name, price = p.price, description = p.description)
        return service.save(updated)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}