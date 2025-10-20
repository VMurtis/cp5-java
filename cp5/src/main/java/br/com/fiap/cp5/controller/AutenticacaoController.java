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
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

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

    private final UserDetailsService userDetailsService;


    private RSAService rsaService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository repository, JwtUtil jwtUtil, UserDetailsService userDetailsService, RSAService rsaService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.rsaService = rsaService;
    }

    @GetMapping("/public-key")
    public ResponseEntity<Map<String, String>> getPublicKey() {
        Map<String, String> body = new HashMap<>();
        body.put("publicKey", rsaService.getPublicKeyBase64());
        return ResponseEntity.ok(body);
    }

    // link: http://localhost:8080/autenticacao/login

    @PostMapping("/login")
    public ResponseEntity<?>  login(@RequestBody @Valid AutenticacaoDTO data){

        try{

            String loginDecifrado = rsaService.decrypt(data.login());
            String senhaDecifrada = rsaService.decrypt(data.password());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDecifrado, senhaDecifrada));

            var userDetails = userDetailsService.loadUserByUsername(loginDecifrado);

            // Gera o token JWT
            String token = jwtUtil.generateToken(userDetails);

            Map<String, Object> body = new HashMap<>();
            body.put("accessToken", token);
            body.put("tokenType", "Bearer");
            body.put("expiresIn", jwtUtil.getJwtExpirationMs());

            return ResponseEntity.ok(body);


        } catch (Exception ex){
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }

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
