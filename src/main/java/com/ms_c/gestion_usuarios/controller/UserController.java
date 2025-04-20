package com.ms_c.gestion_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms_c.gestion_usuarios.model.User;
import com.ms_c.gestion_usuarios.service.UserServices;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    public List<User> obtenerUsuarios() {
        return userServices.obtenerUsuarios();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable Long id) {
        return userServices.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public User crearUsuario(@RequestBody User user) {
        return userServices.crearUsuario(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> actualizarUsuario(@PathVariable Long id, @RequestBody User user) {
        return userServices.actualizarUsuario(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        return userServices.eliminarUsuario(id) ?
                ResponseEntity.ok("Usuario eliminado.") :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> iniciarSesion(@RequestParam String correo, @RequestParam String contrasena) {
        return userServices.iniciarSesion(correo, contrasena)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}