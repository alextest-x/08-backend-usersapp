package com.curso.backend.usersapp.controllers;

import com.curso.backend.usersapp.models.entities.User;
import com.curso.backend.usersapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    //lo mapeamos si nada
    @GetMapping
    public List<User> list(){
        return  service.findAll();
    }


    /*
       cuando ponemos una ruta /{id}
       hay que poner el @PathVariable

       en caso de venir con otro nombre
       @GetMapping(name="/{id}")
       User show(@PathVariable(name="id") Long idUser)

     */


   /*

    @GetMapping(name="/{id}")
    public  User show(@PathVariable Long id){
        //return service.findById(id); //error
        return service.findById(id).orElseThrow();
    }
  */


    // ResponseEntity para dar una repuesta de un ok o no encontrado

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        // regresa un optional
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    /*
    //el user viene del request
    //anotamos @RequestBody que viene con un json
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  User create(@RequestBody User user) {
        return service.save(user);
    }
    */






    //de otra forma el post
    //.body se guarda el objeto usuario de la respuesta  que se crea en base de datos
    @PostMapping
    public  ResponseEntity<?> create(@RequestBody User user) {

        //en una sola linea
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));

        // de otra forma
        //User userDb= service.save(user);
        //return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));

    }

    /*
    //primera forma del update x-update
    //implmenatdo en el controlado
    //se comenta porque la logica se puso en el service UserServiceImpl
    //pero solo el if y se mantiene el metodo PutMapping 1.1
    //hay que poner @RequestBody, @PathVariable
    //update(@RequestBody User user, @PathVariable Long id)
    //donde el user son los datos del json
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){

        //validar en la bd que exista para poder modificarlo
         Optional<User> o = service.findById(id);
         if (o.isPresent()){
             //obteniendo el user de la base de datos con userDb para guardar ya actualizado
             User userDb = o.orElseThrow(); //orElseThrow() en caso de ser null

             //le modificamos los datos que solo usamos del RequestBody user
             userDb.setUsername(user.getUsername());
             userDb.setEmail(user.getEmail());
             return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDb));

         }
         //sino encuentra regresa un not found
         // con build genera la respuesta en el ResponseEntity
        return ResponseEntity.notFound().build();
    }
    */


    //PutMapping 1.1
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id){
        Optional <User> o = service.update(user, id);
        if (o.isPresent()){
            //.body(o. pasamos el objeto que viene en el update
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
       return ResponseEntity.notFound().build();
    }


    //eliminamos por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id){

        Optional<User> o = service.findById(id);

        //validar si se encuentra el usaurio en bd
        if (o.isPresent()){
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
          return ResponseEntity.notFound().build();
    }

}
