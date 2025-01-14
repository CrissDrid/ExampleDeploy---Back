package com.example.ExampleDeploy.service;

import com.example.ExampleDeploy.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    public List<Usuario> findAll() throws Exception;
    public Usuario findById(Integer identificacion);
    public void create(Usuario usuario);
    public void update(Usuario usuario);
    public void delete(Usuario usuario);

}
