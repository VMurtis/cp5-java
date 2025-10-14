package br.com.fiap.cp5.dto;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public record UsuarioDto(
        Long id,

        String login,
        String password,
        String role
) {
}
