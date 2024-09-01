package com.example.ExampleDeploy.controller;

import com.example.ExampleDeploy.entities.Usuario;
import com.example.ExampleDeploy.service.imp.UsuarioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioImp usuarioImp;

    // CONTROLLER CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            int identificacion = Integer.parseInt(request.get("identificacion").toString());

            // Check if user with the same identificacion already exists
            if (usuarioImp.findById(identificacion) != null) {
                response.put("status", "error");
                response.put("data", "Usuario con la misma identificación ya existe");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            Usuario usuario = new Usuario();
            usuario.setIdentificacion(identificacion);
            usuario.setNombre(request.get("nombre").toString());
            usuario.setApellido(request.get("apellido").toString());
            usuario.setUserName(request.get("userName").toString());
            usuario.setEmail(request.get("email").toString());
            usuario.setContrasena(request.get("contrasena").toString());

            this.usuarioImp.create(usuario);

            response.put("status", "success");
            response.put("data", "Registro exitoso");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // CONTROLLER READ
    // READ ALL
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Usuario> usuarios = this.usuarioImp.findAll();
            response.put("status", "success");
            response.put("data", usuarios);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/list/{identificacion}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("identificacion") int identificacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioImp.findById(identificacion);
            if (usuario != null) {
                response.put("status", "success");
                response.put("data", usuario);
            } else {
                response.put("status", "error");
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // CONTROLLER UPDATE
    @PutMapping("/update/{identificacion}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable("identificacion") int identificacion,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioImp.findById(identificacion);
            if (usuario != null) {
                usuario.setNombre(request.get("nombre").toString());
                usuario.setApellido(request.get("apellido").toString());
                usuario.setUserName(request.get("userName").toString());
                usuario.setEmail(request.get("email").toString());
                usuario.setContrasena(request.get("contrasena").toString());
                usuarioImp.update(usuario);
                response.put("status", "success");
                response.put("data", "Usuario actualizado exitosamente");
            } else {
                response.put("status", "error");
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // CONTROLLER DELETE
    @DeleteMapping("/delete/{identificacion}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("identificacion") int identificacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioImp.findById(identificacion);
            if (usuario != null) {
                usuarioImp.delete(usuario);
                response.put("status", "success");
                response.put("data", "Usuario eliminado exitosamente");
            } else {
                response.put("status", "error");
                response.put("data", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // CONTROLLER LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = request.get("email");
            String contrasena = request.get("contrasena");

            List<Usuario> usuarios = this.usuarioImp.findAll();
            Optional<Usuario> usuarioOpt = usuarios.stream()
                    .filter(usuario -> usuario.getEmail().equals(email) && usuario.getContrasena().equals(contrasena))
                    .findFirst();

            if (usuarioOpt.isPresent()) {
                response.put("status", "success");
                response.put("data", usuarioOpt.get());
            } else {
                response.put("status", "error");
                response.put("data", "Credenciales inválidas");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
