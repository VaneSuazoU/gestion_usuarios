package com.ms_c.gestion_usuarios.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ms_c.gestion_usuarios.model.User;
import com.ms_c.gestion_usuarios.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @Test
    void obtenerUsuarios_devuelveListaUsuarios() {
        List<User> lista = List.of(new User(1L, "Ana", "ana@mail.com", "USER", "1234"));
        when(userRepository.findAll()).thenReturn(lista);

        List<User> resultado = userServices.obtenerUsuarios();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Ana");
    }

    @Test
    void obtenerUsuarioPorId_devuelveUsuarioSiExiste() {
        User user = new User(1L, "Ana", "ana@mail.com", "USER", "1234");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> resultado = userServices.obtenerUsuarioPorId(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getCorreo()).isEqualTo("ana@mail.com");
    }

    @Test
    void crearUsuario_devuelveUsuarioGuardado() {
        User user = new User(null, "Luis", "luis@mail.com", "ADMIN", "abcd");
        when(userRepository.save(user)).thenReturn(new User(2L, "Luis", "luis@mail.com", "ADMIN", "abcd"));

        User guardado = userServices.crearUsuario(user);

        assertThat(guardado.getId()).isEqualTo(2L);
        verify(userRepository).save(user);
    }

    @Test
    void actualizarUsuario_actualizaDatosExistentes() {
        User original = new User(3L, "Carlos", "c@mail.com", "USER", "1234");
        User actualizado = new User(3L, "Carlos Updated", "c@mail.com", "ADMIN", "xyz");
        when(userRepository.findById(3L)).thenReturn(Optional.of(original));
        when(userRepository.save(any())).thenReturn(actualizado);

        Optional<User> result = userServices.actualizarUsuario(3L, actualizado);

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Carlos Updated");
    }

    @Test
    void eliminarUsuario_trueSiExiste() {
        when(userRepository.existsById(4L)).thenReturn(true);

        boolean resultado = userServices.eliminarUsuario(4L);

        assertThat(resultado).isTrue();
        verify(userRepository).deleteById(4L);
    }

    @Test
    void iniciarSesion_devuelveUsuarioSiCredencialesValidas() {
        User user = new User(5L, "Eva", "eva@mail.com", "USER", "pass");
        when(userRepository.findByCorreoAndContrasena("eva@mail.com", "pass"))
                .thenReturn(Optional.of(user));

        Optional<User> result = userServices.iniciarSesion("eva@mail.com", "pass");

        assertThat(result).isPresent();
        assertThat(result.get().getNombre()).isEqualTo("Eva");
    }
}
