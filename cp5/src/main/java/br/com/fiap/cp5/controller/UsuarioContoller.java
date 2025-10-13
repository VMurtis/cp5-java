package br.com.fiap.cp5.controller;


import br.com.fiap.cp5.entity.UsuarioEntity;
import br.com.fiap.cp5.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioContoller {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioContoller(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioEntity>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuarioEntity){
        usuarioEntity.setSenha(encoder.encode(usuarioEntity.getSenha()));
        return ResponseEntity.ok(repository.save(usuarioEntity));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(
            @PathVariable Long id,
            @RequestBody UsuarioEntity usuarioAtualizado) {

        Optional<UsuarioEntity> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UsuarioEntity usuario = usuarioOptional.get();
        usuario.setLogin(usuarioAtualizado.getLogin());


        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
            usuario.setSenha(encoder.encode(usuarioAtualizado.getSenha()));
        }

        UsuarioEntity atualizado = repository.save(usuario);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<UsuarioEntity> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
