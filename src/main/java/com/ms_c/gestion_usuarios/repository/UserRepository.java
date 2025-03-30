package com.ms_c.gestion_usuarios.repository;

import com.ms_c.gestion_usuarios.model.User;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final List<User> usuarios = new ArrayList<>();

    public UserRepository() {
        usuarios.add(new User(1L, "Ana Torres", "ana@email.com", "dueño de mascota", "1234"));
        usuarios.add(new User(2L, "Carlos Díaz", "carlos@email.com", "conductor pet-friendly", "abcd"));
        usuarios.add(new User(3L, "Lucía Pérez", "lucia@email.com", "dueño de mascota", "lucia123"));
    }

    public List<User> getAllUsers() {
        return usuarios;
    }

    public User login(String correo, String contrasena) {
        return usuarios.stream()
                .filter(user -> user.getCorreo().equals(correo) && user.getContrasena().equals(contrasena))
                .findFirst()
                .orElse(null);
    }
}
