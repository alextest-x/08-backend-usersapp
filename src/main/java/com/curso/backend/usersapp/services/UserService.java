package com.curso.backend.usersapp.services;


import com.curso.backend.usersapp.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //findAll() pude ser cualquier nombre
    List<User> findAll();


    //el optional valida
    Optional<User> findById(Long id);


    //el save regresa el objeto entity ya actualizado
    //le pasamos un User y user guarda el id del json
    //si tiene el id lo actualiza sino hace un insert
     User save(User user);

     //otra forma de actualizar al usuario pasando la logica del controlador
     //al service y lo implementamos en el ServiceUserImpl

    Optional<User> update(User user, Long id);






     void remove(Long id);

}
