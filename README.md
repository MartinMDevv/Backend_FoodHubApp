# 🍔 FoodHub Backend

Backend REST API desarrollado en Kotlin con Spring Boot para una aplicación móvil de pedidos de comida. Proporciona servicios completos de autenticación, gestión de productos, carrito de compras y procesamiento de órdenes.

## 📋 Descripción del Proyecto

FoodHub Backend es la columna vertebral de una aplicación móvil para pedidos de comida, desarrollada como proyecto universitario. Implementa una arquitectura REST con Spring Boot, ofreciendo endpoints para la gestión completa de usuarios, productos, carrito de compras y órdenes. El sistema diferencia entre usuarios ADMIN (gestión de productos) y CLIENT (realizar pedidos), manejando todo el flujo desde el registro hasta la confirmación de compra con control de stock.

## 🛠️ Tecnologías Utilizadas

- **Kotlin 1.9.25**: Lenguaje principal del backend
- **Spring Boot 3.5.8**: Framework para desarrollo de API REST
  - Spring Data JPA: Persistencia de datos
  - Spring Web: Controladores REST
  - Spring Validation: Validación de datos
- **MySQL**: Base de datos relacional (vía XAMPP)
- **Gradle 8.14.3**: Gestor de dependencias y construcción
- **Jackson**: Serialización JSON
- **MockK**: Framework de testing para Kotlin

## 📦 Requisitos Previos

Para ejecutar este proyecto necesitas tener instalado:

- **JDK 17** o superior
- **XAMPP** (con MySQL activo)
- **Gradle** (o usar el wrapper incluido)
- **Git**

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/backend-foodhub.git
cd backend-foodhub
```

### 2. Configurar la Base de Datos

1. Inicia XAMPP y arranca MySQL
2. Accede a phpMyAdmin (http://localhost/phpmyadmin)
3. Crea una nueva base de datos llamada `foodhub_db`
4. Configura el archivo `application.properties`:

```properties
spring.application.name=Backend_FoodHub

# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/foodhub_db
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 3. Ejecutar la Aplicación

#### Usando Gradle Wrapper (recomendado):

**En Windows:**
```bash
gradlew.bat bootRun
```

**En Linux/Mac:**
```bash
./gradlew bootRun
```

#### O usando Gradle instalado:
```bash
gradle bootRun
```

La aplicación estará disponible en `http://localhost:8080`

### 4. Verificar la Instalación

Al iniciar, deberías ver en la consola:
```
*** Exito***
```

Y las tablas se crearán automáticamente en la base de datos con datos de ejemplo.

## 🎯 Features

### Autenticación y Usuarios
- ✅ **Registro de usuarios** (CLIENT/ADMIN)
- ✅ **Login** con validación de credenciales
- ✅ **CRUD completo de usuarios**
- ✅ **Sistema de roles** (diferenciación entre cliente y administrador)

### Gestión de Productos
- ✅ **Listado de productos** disponibles
- ✅ **Creación de productos** (solo ADMIN)
- ✅ **Actualización de productos** (precio, stock, disponibilidad)
- ✅ **Eliminación de productos**
- ✅ **Control de stock automático**
- ✅ **Categorización** (Comida, Bebida, Otros)

### Carrito de Compras
- ✅ **Agregar productos al carrito**
- ✅ **Visualizar carrito del usuario**
- ✅ **Actualizar cantidades**
- ✅ **Eliminar items del carrito**
- ✅ **Validación de duplicados** (incrementa cantidad si el producto ya existe)

### Gestión de Órdenes
- ✅ **Crear orden desde el carrito**
- ✅ **Validación de stock** antes de confirmar
- ✅ **Descuento automático de stock**
- ✅ **Cálculo de total**
- ✅ **Historial de órdenes por usuario**
- ✅ **Limpieza automática del carrito** al completar orden

## 📡 Endpoints Principales

### Auth (Autenticación)
```
POST   /api/auth/register       - Registrar nuevo usuario
POST   /api/auth/login          - Iniciar sesión
GET    /api/auth               - Listar todos los usuarios
GET    /api/auth/{id}          - Obtener usuario por ID
PUT    /api/auth/{id}          - Actualizar usuario
DELETE /api/auth/{id}          - Eliminar usuario
```

### Products (Productos)
```
GET    /api/products            - Listar todos los productos
GET    /api/products/{id}       - Obtener producto por ID
POST   /api/products            - Crear nuevo producto
PUT    /api/products/{id}       - Actualizar producto
DELETE /api/products/{id}       - Eliminar producto
```

### Cart (Carrito)
```
GET    /api/cart/{userId}       - Obtener carrito del usuario
POST   /api/cart/add            - Agregar producto al carrito
PUT    /api/cart/{itemId}       - Actualizar cantidad de un item
DELETE /api/cart/{itemId}       - Eliminar item del carrito
```

### Orders (Órdenes)
```
POST   /api/orders?userId={id}  - Crear orden desde carrito
GET    /api/orders/user/{userId} - Obtener órdenes del usuario
```

## 🧪 Testing

Ejecutar los tests:
```bash
./gradlew test
```

## 📱 Integración con App Móvil

Este backend está diseñado para funcionar con la aplicación móvil Android desarrollada en Kotlin. La app móvil incluye:

- Interfaz de usuario intuitiva
- Uso de cámara nativa del dispositivo
- Sincronización con backend vía HTTP
- Gestión local y remota de datos

**Repositorio de la app móvil:** *(agregar link cuando esté disponible)*

## 👥 Equipo de Desarrollo

Desarrollado en colaboración con:
- [@Rau1ignacio](https://github.com/Rau1ignacio)

## 📚 Aprendizajes del Proyecto

Este proyecto me permitió desarrollar y consolidar conocimientos en:

### Desarrollo Backend
- **Arquitectura REST**: Diseño e implementación de APIs RESTful siguiendo buenas prácticas
- **Spring Boot**: Configuración de proyectos empresariales con Spring Framework
- **Kotlin en Backend**: Aprovechamiento de características de Kotlin (data classes, null safety, extension functions)

### Gestión de Datos
- **JPA/Hibernate**: Mapeo objeto-relacional y gestión de entidades
- **Relaciones entre tablas**: Implementación de relaciones @ManyToOne, @OneToMany
- **Transacciones**: Manejo de operaciones transaccionales complejas (creación de órdenes)

### Lógica de Negocio
- **Sistema de autenticación**: Registro, login y gestión de sesiones
- **Control de inventario**: Validación y actualización automática de stock
- **Flujo de compra completo**: Carrito → Validación → Orden → Stock
- **Manejo de roles**: Diferenciación de permisos ADMIN/CLIENT

### Buenas Prácticas
- **Separación de responsabilidades**: Arquitectura en capas (Controller → Service → Repository)
- **DTOs**: Uso de Data Transfer Objects para comunicación con el frontend
- **Manejo de errores**: Validaciones y excepciones personalizadas
- **Testing**: Implementación de pruebas unitarias con MockK

### Integración
- **Backend-Frontend**: Comunicación entre backend Spring y app móvil Android
- **Base de datos**: Configuración y administración de MySQL con XAMPP
- **Trabajo en equipo**: Colaboración efectiva usando Git y GitHub

## 🔄 Posibles Mejoras Futuras

- [ ] Implementar JWT para autenticación segura
- [ ] Agregar encriptación de contraseñas (BCrypt)
- [ ] Sistema de notificaciones al confirmar órdenes
- [ ] Implementar paginación en listados
- [ ] Agregar filtros y búsqueda de productos
- [ ] Sistema de descuentos y promociones
- [ ] Reportes y estadísticas para administradores
- [ ] Upload de imágenes de productos

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

⭐ Si te gustó este proyecto, no olvides darle una estrella en GitHub
