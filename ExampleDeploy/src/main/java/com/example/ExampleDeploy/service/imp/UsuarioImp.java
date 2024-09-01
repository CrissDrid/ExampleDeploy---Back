package com.example.ExampleDeploy.service.imp;

import com.example.ExampleDeploy.entities.Usuario;
import com.example.ExampleDeploy.repositories.UsuarioRepository;
import com.example.ExampleDeploy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioImp implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() throws Exception {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer identificacion) {
        return this.usuarioRepository.findById(identificacion).orElse(null);
    }

    @Override
    public void create(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        this.usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        this.usuarioRepository.delete(usuario);
    }
}
