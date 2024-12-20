package br.com.cinepoti.cinepoti_api.controller;

import br.com.cinepoti.cinepoti_api.dto.request.UserRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.UserResponseDTO;
import br.com.cinepoti.cinepoti_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")  // Define o caminho base para os endpoints relacionados ao usuário
@CrossOrigin

public class UserController {


    private final UserService userService;

    @Autowired
    public  UserController(UserService userService){
        this.userService = userService;
    }

    // Endpoint para registrar um novo usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<UserResponseDTO> createUser(@Valid  @RequestBody UserRequestDTO userRequestDTO) {
        // Chama o serviço para salvar o usuário
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);

        // Retorna a resposta com o status HTTP 201 (Created)
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    // Endpoint para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para atualizar um usuário
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO userResponseDTO = userService.updateUser(id, userRequestDTO);
        if (userResponseDTO != null) {
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();


    }

}
