package com.ms_c.gestion_usuarios.service;

import com.ms_c.gestion_usuarios.model.User;
import com.ms_c.gestion_usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<User> obtenerUsuarios() {
        return userRepository.findAll();
    }

    public Optional<User> obtenerUsuarioPorId(Long id) {
        return userRepository.findById(id);
    }

    public User crearUsuario(User user) {
        return userRepository.save(user);
    }

    public Optional<User> actualizarUsuario(Long id, User userActualizado) {
        return userRepository.findById(id).map(user -> {
            user.setNombre(userActualizado.getNombre());
            user.setCorreo(userActualizado.getCorreo());
            user.setRol(userActualizado.getRol());
            user.setContrasena(userActualizado.getContrasena());
            return userRepository.save(user);
        });
    }

    public boolean eliminarUsuario(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<User> iniciarSesion(String correo, String contrasena) {
        return userRepository.findByCorreoAndContrasena(correo, contrasena);
    }
}