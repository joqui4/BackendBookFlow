package com.BookFlow.usuarios.controller;

import com.BookFlow.usuarios.domain.usuario;
import com.BookFlow.usuarios.service.usuarioService;
import com.BookFlow.usuarios.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class usuarioController {

    @Autowired
    private usuarioService usuarioService;

    @GetMapping
    public List<usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuario> getUsuarioById(@PathVariable Long id) {
        Optional<usuario> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createUsuario(@RequestBody usuario usuario) {
        Optional<usuario> existingUsuario = usuarioService.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        usuarioService.save(usuario);
        return ResponseEntity.ok("Usuario creado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<usuario> updateUsuario(@PathVariable Long id, @RequestBody usuario usuarioDetails) {
        Optional<usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            usuario existingUsuario = usuario.get();
            existingUsuario.setFirstname(usuarioDetails.getFirstname());
            existingUsuario.setLastname(usuarioDetails.getLastname());
            existingUsuario.setEmail(usuarioDetails.getEmail());
            existingUsuario.setPassword(usuarioDetails.getPassword());
            usuario updatedUsuario = usuarioService.save(existingUsuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.findById(id).isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<usuario> partiallyUpdateUsuario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            usuario usuario = usuarioOptional.get();
            updates.forEach((k, v) -> {
                switch (k) {
                    case "firstname":
                        usuario.setFirstname((String) v);
                        break;
                    case "lastname":
                        usuario.setLastname((String) v);
                        break;
                    case "email":
                        if (!usuario.getEmail().equals(v) && usuarioService.findByEmail((String) v).isPresent()) {
                            throw new IllegalArgumentException("El email ya está registrado");
                        }
                        usuario.setEmail((String) v);
                        break;
                    case "password":
                        usuario.setPassword((String) v);
                        break;
                    default:
                        throw new IllegalArgumentException("Campo no permitido para actualización: " + k);
                }
            });
            usuario updatedUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<usuario> login(@RequestBody LoginRequest loginRequest) {
        Optional<usuario> usuario = usuarioService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return usuario.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.status(401).build());
    }
}