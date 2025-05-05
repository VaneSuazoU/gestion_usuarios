package com.ms_c.gestion_usuarios.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
    public CollectionModel<EntityModel<User>> obtenerUsuarios() {
        List<EntityModel<User>> usuarios = userServices.obtenerUsuarios().stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).obtenerUsuarioPorId(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).obtenerUsuarios()).withRel("usuarios")))
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UserController.class).obtenerUsuarios()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<User> userOpt = userServices.obtenerUsuarioPorId(id);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        EntityModel<User> model = EntityModel.of(user,
                linkTo(methodOn(UserController.class).obtenerUsuarioPorId(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).obtenerUsuarios()).withRel("usuarios"));
        return ResponseEntity.ok(model);
    }

    @PostMapping("/users")
    public ResponseEntity<EntityModel<User>> crearUsuario(@RequestBody User user) {
        User creado = userServices.crearUsuario(user);
        EntityModel<User> model = EntityModel.of(creado,
                linkTo(methodOn(UserController.class).obtenerUsuarioPorId(creado.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).obtenerUsuarios()).withRel("usuarios"));
        return ResponseEntity.created(linkTo(methodOn(UserController.class).obtenerUsuarioPorId(creado.getId())).toUri())
                .body(model);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> actualizarUsuario(@PathVariable Long id, @RequestBody User user) {
        Optional<User> actualizado = userServices.actualizarUsuario(id, user);
        if (actualizado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User actualizadoUser = actualizado.get();
        EntityModel<User> model = EntityModel.of(actualizadoUser,
                linkTo(methodOn(UserController.class).obtenerUsuarioPorId(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).obtenerUsuarios()).withRel("usuarios"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!userServices.eliminarUsuario(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login")
    public ResponseEntity<EntityModel<User>> iniciarSesion(@RequestParam String correo, @RequestParam String contrasena) {
        Optional<User> userOpt = userServices.iniciarSesion(correo, contrasena);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        User user = userOpt.get();
        EntityModel<User> model = EntityModel.of(user,
                linkTo(methodOn(UserController.class).obtenerUsuarioPorId(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).obtenerUsuarios()).withRel("usuarios"));
        return ResponseEntity.ok(model);
    }
} 