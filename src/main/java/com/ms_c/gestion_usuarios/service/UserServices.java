package com.ms_c.gestion_usuarios.service;

import com.ms_c.gestion_usuarios.model.User;
import com.ms_c.gestion_usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> obtenerUsuarios() {
        return userRepository.getAllUsers();
    }

    public User iniciarSesion(String correo, String contrasena) {
        return userRepository.login(correo, contrasena);
    }
}
