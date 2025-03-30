package com.ms_c.gestion_usuarios.controller;

import com.ms_c.gestion_usuarios.model.User;
import com.ms_c.gestion_usuarios.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/users")
    public List<User> obtenerUsuarios() {
        return userServices.obtenerUsuarios();
    }

    @GetMapping("/login")
    public User iniciarSesion(@RequestParam String correo, @RequestParam String contrasena) {
        return userServices.iniciarSesion(correo, contrasena);
    }
}
