package com.curso.backend.usersapp.models.entities;

import jakarta.persistence.*;

//se anota con entity para indicar que es una clase de persistencia
@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(unique = true) campo unico no se puede repetir
    //este es cuando se crea la tabla de forma automatica con el ddl-auto
    //sino hay que ponerlo en el esquema de la base de datos mysql
    @Column(unique = true)
    private String username;


    private String password;


    @Column(unique = true)
    private String email;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
