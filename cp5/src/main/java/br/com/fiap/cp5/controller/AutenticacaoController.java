package br.com.fiap.cp5.controller;

import br.com.fiap.cp5.dto.AutenticacaoDTO;
import br.com.fiap.cp5.dto.RegistrarDto;
import br.com.fiap.cp5.entity.UsuarioEntity;
import br.com.fiap.cp5.repository.UsuarioRepository;
import br.com.fiap.cp5.utilities.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    //@Autowired
    //private TokenService tokenService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository repository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        //var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().build();
        //return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegistrarDto data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuarioEntity novoUsuario = new UsuarioEntity(data.login(), encryptedPassword, data.role());

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
