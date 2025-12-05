package com.sena.citaspsicologia.citas_psicologia_app.controller;

import com.sena.citaspsicologia.citas_psicologia_app.model.Usuario;
import com.sena.citaspsicologia.citas_psicologia_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- 1. SERVICIO DE REGISTRO ---
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        // Validar si el correo ya existe
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El correo ya está registrado.");
        }
        
        // Guardar usuario (En producción aquí se encriptaría la contraseña)
        usuarioRepository.save(usuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    // --- 2. SERVICIO DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String email = credenciales.get("email");
        String password = credenciales.get("password");

        // Buscar usuario en BD
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Validar contraseña
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Respuesta de éxito (JSON)
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Autenticación satisfactoria");
            response.put("usuario", usuario.getNombre());
            response.put("rol", usuario.getRol());
            response.put("id", usuario.getId());
            
            return ResponseEntity.ok(response);
        } else {
            // Error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Credenciales incorrectas");
        }
    }
}