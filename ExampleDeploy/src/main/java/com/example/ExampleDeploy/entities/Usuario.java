package com.example.ExampleDeploy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @Column(name="PK_Identificacion")
    private int identificacion;

    @Column(name = "Nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "Apellido", length = 20, nullable = false)
    private String apellido;

    @Column(name = "userName", length = 20, nullable = false)
    private String userName;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Contrasena", columnDefinition = "TEXT", nullable = false)
    private String contrasena;
}
