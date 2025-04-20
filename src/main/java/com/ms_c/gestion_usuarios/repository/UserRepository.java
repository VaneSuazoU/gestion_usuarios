package com.ms_c.gestion_usuarios.repository;

import com.ms_c.gestion_usuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCorreoAndContrasena(String correo, String contrasena);
}
