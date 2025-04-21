# Microservicio: Gestión de Usuarios

Este microservicio forma parte de una aplicación de viajes con mascotas. Permite registrar y autenticar usuarios con distintos roles: `dueño de mascota` y `conductor pet-friendly`.

Primera sumativa Desarrollo FullStack

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

### Obtener usuario por ID
```http
GET /api/users/{id}
```
**Ejemplo:**  
[http://localhost:8080/api/users/1](http://localhost:8080/api/users/1)

---

### Crear nuevo usuario
```http
POST /api/users
Content-Type: application/json
```
**Body JSON:**
```json
{
  "nombre": "Valeria Silva",
  "correo": "valeria@email.com",
  "rol": "dueño de mascota",
  "contrasena": "v1234"
}
```

---

### Actualizar usuario existente
```http
PUT /api/users/{id}
Content-Type: application/json
```
**Body JSON:**
```json
{
  "nombre": "Ana Torres",
  "correo": "ana@email.com",
  "rol": "dueño de mascota",
  "contrasena": "nuevo1234"
}
```

---

### Eliminar usuario
```http
DELETE /api/users/{id}
```
**Ejemplo:**  
[http://localhost:8080/api/users/3](http://localhost:8080/api/users/3)

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

Ejemplos completos se encuentran en el archivo `pruebas_postman_usuarios.txt`.

