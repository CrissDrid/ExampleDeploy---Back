package com.example.ExampleDeploy.repositories;

import com.example.ExampleDeploy.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
