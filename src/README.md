# Microservicio: Gestión de Usuarios

Este microservicio forma parte de una aplicación de viajes con mascotas. Permite registrar y autenticar usuarios con distintos roles: `dueño de mascota` y `conductor pet-friendly`.

## 🧱 Tecnologías
- Java 24
- Spring Boot 3.4.4
- Maven

## 🚀 Cómo ejecutar

Desde la raíz del proyecto, ejecuta:

```bash
./mvnw spring-boot:run
```

> En Windows:  
```bash
mvnw.cmd spring-boot:run
```

## 🌐 Endpoints disponibles

### Obtener todos los usuarios
```http
GET /api/users
```
**Ejemplo:**  
[http://localhost:8080/api/users](http://localhost:8080/api/users)

---

### Iniciar sesión
```http
GET /api/login?correo={correo}&contrasena={contrasena}
```

**Ejemplo válido:**  
[http://localhost:8080/api/login?correo=ana@email.com&contrasena=1234](http://localhost:8080/api/login?correo=ana@email.com&contrasena=1234)

---

## 👥 Usuarios precargados

| ID | Nombre        | Correo            | Rol                    | Contraseña |
|----|---------------|-------------------|-------------------------|------------|
| 1  | Ana Torres    | ana@email.com     | dueño de mascota        | 1234       |
| 2  | Carlos Díaz   | carlos@email.com  | conductor pet-friendly  | abcd       |
| 3  | Lucía Pérez   | lucia@email.com   | dueño de mascota        | lucia123   |

---

## 📂 Estructura del proyecto

```
com.ms_c.gestion_usuarios
│
├── model
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
├── service
│   └── UserServices.java
│
├── controller
│   └── UserController.java
│
└── GestionUsuariosApplication.java
```

---

## 🧪 Pruebas

Puedes usar navegador o Postman para probar los endpoints.

