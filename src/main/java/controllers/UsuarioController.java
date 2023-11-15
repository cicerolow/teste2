package com.senai.apigastsix.controllers;

import com.senai.apigastsix.models.UsuarioModel;
import com.senai.apigastsix.repositories.UsuarioRepository;
import com.senai.apigastsix.services.FileUploadService;
import dtos.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    FileUploadService fileUploadService;
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }
    @GetMapping("/{matricula}")
    public ResponseEntity<Object> buscarUsuario(@PathVariable(value = "matricula")String id){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if(usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }
    @PostMapping()
    public ResponseEntity<Object> criarUsuario(@ModelAttribute @Valid UsuarioDto usuarioDto){
        if(usuarioRepository.findByEmail(usuarioDto.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");
        }

        UsuarioModel novoUsuario = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, novoUsuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(novoUsuario));
    }

    @PutMapping(value = "/{matricula}")
    public ResponseEntity<Object> editarUsuario(@PathVariable(value = "matricula") String id, @ModelAttribute @Valid UsuarioDto usuarioDto){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if(usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        UsuarioModel usuarioBd = usuarioBuscado.get();
        BeanUtils.copyProperties(usuarioDto, usuarioBd);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioBd));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "matricula")String id){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if(usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        usuarioRepository.delete(usuarioBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
    }
}

