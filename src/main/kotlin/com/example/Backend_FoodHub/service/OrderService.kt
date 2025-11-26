package com.example.backend_foodhub.service

import com.example.backend_foodhub.model.Order
import com.example.backend_foodhub.repository.CartItemRepository
import com.example.backend_foodhub.repository.OrderRepository
import com.example.backend_foodhub.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val cartItemRepository: CartItemRepository,
    private val productRepository: ProductRepository
) {

    /**
     * Crea una orden a partir del carrito de un usuario:
     * 1. Obtiene los items del carrito del usuario.
     * 2. Valida que haya stock suficiente para cada producto.
     * 3. Descuenta stock de productos y los guarda.
     * 4. Calcula total.
     * 5. Crea y persiste la orden.
     * 6. Limpia el carrito.
     */
    @Transactional
    fun createOrderFromCart(userId: Long): Order {
        val cartItems = cartItemRepository.findByUserId(userId)

        if (cartItems.isEmpty()) {
            throw IllegalStateException("No hay productos en el carrito")
        }

        // 1) Validar stock
        cartItems.forEach { item ->
            val product = item.product   // asumimos no-null
            if (product.stock < item.quantity) {
                throw IllegalStateException("Stock insuficiente para ${product.name}")
            }
        }

        // 2) Descontar stock y guardar productos
        cartItems.forEach { item ->
            val product = item.product
            product.stock -= item.quantity
            productRepository.save(product)
        }

        // 3) Calcular total
        val total = cartItems.sumOf { item ->
            val product = item.product
            item.quantity * product.price
        }

        // 4) Obtener usuario del primer item (mismo user en todos los items)
        val firstUser = cartItems.first().user

        // 5) Crear orden
        val order = Order(
            user = firstUser,
            items = cartItems.toMutableList(),
            total = total
        )

        val saved = orderRepository.save(order)

        // 6) Limpiar carrito
        cartItemRepository.deleteAll(cartItems)

        return saved
    }

    /**
     * Devuelve todas las órdenes de un usuario dado.
     * Implementado de forma segura usando findAll() y filtrando
     * para no depender de un método especial en OrderRepository.
     */
    fun getOrdersByUser(userId: Long): List<Order> {
        return orderRepository.findAll()
            .filter { it.user.id == userId }
    }
}
